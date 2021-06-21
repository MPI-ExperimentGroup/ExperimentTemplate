/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.mpi.tg.eg.experiment.client.presenter;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.FrameTimeTrigger;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractStimulusPresenter.OrientationType;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jan 31, 2019 5:07:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractTimedPresenter extends AbstractPresenter implements Presenter {

    final protected TimedStimulusView timedStimulusView;
    protected final DataSubmissionService submissionService;
    protected final Duration duration;
    private final ArrayList<Timer> pauseTimers = new ArrayList<>();
    private final ArrayList<MediaTriggerListener> frameTriggerListeners = new ArrayList<>();
    private int pauseRegionCounter = 0;

    public AbstractTimedPresenter(RootLayoutPanel widgetTag, final TimedStimulusView timedStimulusView, final DataSubmissionService submissionService, UserResults userResults, LocalStorage localStorage, TimerService timerService) {
        super(widgetTag, timedStimulusView, userResults, localStorage, timerService);
        this.timedStimulusView = timedStimulusView;
        this.submissionService = submissionService;
        duration = new Duration();
    }

    public void setMetadataValue(final Stimulus currentStimulus, final MetadataField metadataField, final String dataLogFormat, final String replacementRegex) {
        final HtmlTokenFormatter htmlTokenFormatter = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray());
        final String dataLogString = (replacementRegex == null || replacementRegex.isEmpty()) ? htmlTokenFormatter.formatString(dataLogFormat) : htmlTokenFormatter.formatReplaceString(dataLogFormat, replacementRegex);
        userResults.getUserData().setMetadataValue(metadataField, dataLogString);
        localStorage.storeData(userResults, metadataFieldProvider);
    }

    public void setMetadataEvalTokens(final Stimulus currentStimulus, final String evaluateTokens, final MetadataField metadataField, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        try {
            final String resultValue = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).evaluateTokensString(evaluateTokens);
            userResults.getUserData().setMetadataValue(metadataField, resultValue);
            localStorage.storeData(userResults, metadataFieldProvider);
            onSuccess.postLoadTimerFired();
        } catch (EvaluateTokensException exception) {
            onError.postLoadTimerFired();
        }
    }

    public void switchUserIdButton(final String textString, final MetadataField metadataField, final String styleName, final String validationRegex, final String buttonGroup, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        addButtonToGroup(buttonGroup, timedStimulusView.addOptionButton(new PresenterEventListner() {
            @Override
            public String getLabel() {
                return textString;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                final String metadataString = userResults.getUserData().getMetadataValue(metadataField);
                if (metadataString != null && !metadataString.isEmpty() && metadataString.matches(validationRegex)) {
                    try {
                        // todo: why does this set the user before store and what about the results array etc that should be updated to the new user
                        userResults.setUser(localStorage.getStoredData(new UserId(metadataString), metadataFieldProvider));
                        localStorage.storeData(userResults, metadataFieldProvider);
                        // todo: note that previous implementations that change the user id, all have changed the application state directly after the user change, so care should be made in testing that no residual data is left behind
//                        appEventListner.requestApplicationState(nextState);
                        onSuccess.postLoadTimerFired();
                    } catch (UserIdException exception) {
                        onError.postLoadTimerFired();
                    }
                } else {
                    onError.postLoadTimerFired();
                }
            }

            @Override
            public int getHotKey() {
                return -1;
            }
        }));
    }

    protected void progressIndicator(final Stimulus currentStimulus, final String evaluateTokens, final String styleName, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        try {
            final Number resultValue = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).evaluateTokensNumber(evaluateTokens);
            if (resultValue.intValue() >= 0 && resultValue.intValue() <= 100) {
                timedStimulusView.addBarGraphElement(resultValue.intValue(), 100, styleName);
                onSuccess.postLoadTimerFired();
            } else {
                onError.postLoadTimerFired();
            }
        } catch (EvaluateTokensException exception) {
            onError.postLoadTimerFired();
        }
    }

    public void htmlTokenText(final Stimulus currentStimulus, final String textString, final String styleName) {
        timedStimulusView.addHtmlText(new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(textString), styleName);
        // the submitTagValue previously used here by the multiparticipant configuration has been migrated to logTokenText which should function the sames for the multiparticipant experiment except that it now uses submitTagPairValue
    }

    public void htmlTokenText(final Stimulus currentStimulus, String textString) {
        htmlTokenText(currentStimulus, textString, null);
    }

    public void htmlText(String textString, final String styleName) {
        timedStimulusView.addHtmlText(textString, styleName);
    }

    protected void image(final String imageString, int postLoadMs, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        image(imageString, null, postLoadMs, loadedStimulusListener, failedStimulusListener);
    }

    protected void image(final String imageString, final String styleName, int postLoadMs, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        // consider fromTrustedString if there are issues with fromString when sdcard stimuli are used
        timedStimulusView.addTimedImage(null, UriUtils.fromString((imageString.startsWith("file")) ? imageString : serviceLocations.staticFilesUrl() + imageString), styleName, postLoadMs, loadedStimulusListener, failedStimulusListener, null);
    }

    protected void addPadding() {
        timedStimulusView.addPadding();
    }

    protected void centrePage() {
        timedStimulusView.centrePage();
    }

    public final List<StimulusButton> ratingButtons(final List<PresenterEventListner> presenterListeners, final String ratingLabelLeft, final String ratingLabelRight, boolean footerButtons, String styleName, final String radioGroupName, final boolean allowMultiple, final String savedValue, final String buttonGroup, final Panel ratingStylePanel, final OrientationType orientationType) {
        final List<StimulusButton> ratingButtons = timedStimulusView.addRatingButtons(presenterListeners, ratingLabelLeft, ratingLabelRight, footerButtons, styleName, radioGroupName, allowMultiple, savedValue, ratingStylePanel, orientationType);
        addButtonToGroup(buttonGroup, ratingButtons);
//        addButtonToGroup(buttonGroupName, ratingButtons);
        return ratingButtons;
    }

    public StimulusButton imageButton(final PresenterEventListner presenterListerner, final SafeUri imagePath, final boolean isTouchZone, final String buttonGroup) {
        return addButtonToGroup(buttonGroup, timedStimulusView.addImageButton(presenterListerner, imagePath, isTouchZone));
    }

    public void actionFooterButton(final PresenterEventListner presenterListerner, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addFooterButton(presenterListerner));
    }

    public void targetFooterButton(final PresenterEventListner presenterListerner, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addFooterButton(presenterListerner));
    }

    public void actionTokenButton(final Stimulus currentStimulus, final PresenterEventListner presenterListener, final String buttonGroup) {
        final HtmlTokenFormatter htmlTokenFormatter = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray());
        addButtonToGroup(buttonGroup, ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {
            @Override
            public String getLabel() {
                return htmlTokenFormatter.formatString(presenterListener.getLabel());
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                presenterListener.eventFired(button, shotEventListner);
            }

            @Override
            public String getStyleName() {
                return presenterListener.getStyleName();
            }

            @Override
            public int getHotKey() {
                return presenterListener.getHotKey();
            }
        }));
    }

    public void actionButton(final PresenterEventListner presenterListerner, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addOptionButton(presenterListerner));
    }

    public StimulusButton optionButton(final PresenterEventListner presenterListerner, final String buttonGroup) {
        final StimulusButton optionButton = timedStimulusView.addOptionButton(presenterListerner);
        addButtonToGroup(buttonGroup, optionButton);
        return optionButton;
    }

    protected void doLater(int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        pause(postLoadMs, timedStimulusListener);
    }

    protected void pause(int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        // pauseRegionCounter is used to keep the position in the page where the pause is expected, rather than appending to the end of the page on trigger
        final String pauseRegionId = "pauseRegionCounter_" + pauseRegionCounter;
        pauseRegionCounter++;
        final InsertPanel.ForIsWidget isWidget1 = ((ComplexView) simpleView).startRegion(pauseRegionId, null);
        ((ComplexView) simpleView).endRegion(isWidget1);
        final Timer timer = new Timer() {
            @Override
            public void run() {
                final InsertPanel.ForIsWidget isWidget2 = ((ComplexView) simpleView).startRegion(pauseRegionId, null);
                timedStimulusListener.postLoadTimerFired();
                pauseTimers.remove(this);
                ((ComplexView) simpleView).endRegion(isWidget2);
            }
        };
        pauseTimers.add(timer);
        timer.schedule(postLoadMs);
    }

    public void cancelPauseTimers() {
//        timedStimulusView.stopTimers();
        for (Timer currentTimer : pauseTimers) {
            if (currentTimer != null) {
                currentTimer.cancel();
            }
        }
        pauseTimers.clear();
        for (MediaTriggerListener frameTriggerListener : frameTriggerListeners) {
            if (frameTriggerListener != null) {
                frameTriggerListener.clearTriggers();
            }
        }
    }

    protected void addTimerTrigger(final Stimulus currentStimulus, final String listenerId, int minimumMs, int maximumMs, final String evaluateTokens, final TimedStimulusListener onError, final TimedStimulusListener onTimer) {
        try {
            final Number evaluateResult = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).evaluateTokensNumber(evaluateTokens);
            int pauseMs = (evaluateResult.intValue() <= maximumMs) ? evaluateResult.intValue() : maximumMs;
            pauseMs = (pauseMs >= minimumMs) ? pauseMs : minimumMs;
            startTimer(pauseMs, listenerId, onTimer);
        } catch (EvaluateTokensException ete) {
            onError.postLoadTimerFired();
        }
    }

    protected void /* this could be changed to addTimer or setTimer since it now allows multiple timer listeners */ startTimer(final int msToNext, final String listenerId, final TimedStimulusListener timeoutListener) {
        final String storedDataValue = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "timer_" + listenerId);
        final long initialTimerStartMs;
        if (storedDataValue == null || storedDataValue.isEmpty()) {
            initialTimerStartMs = new Date().getTime();
            localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "timer_" + listenerId, Long.toString(initialTimerStartMs));
        } else {
            initialTimerStartMs = Long.parseLong(storedDataValue);
        }
        timerService.startTimer(initialTimerStartMs, msToNext, listenerId, timeoutListener);
    }

    protected void randomMsPause(int minimumMs, int maximumMs, final TimedStimulusListener timedStimulusListener) {
        final Timer timer = new Timer() {
            @Override
            public void run() {
                timedStimulusListener.postLoadTimerFired();
                pauseTimers.remove(this);
            }
        };
        pauseTimers.add(timer);
        timer.schedule((int) (new Random().nextInt(maximumMs - minimumMs + 1) + minimumMs)); // since the attributes minimum, maximum are inclusive we convert maximumMs - minimumMs into upper bound (exclusive) by adding 1
    }

    protected void evaluatePause(final Stimulus currentStimulus, int minimumMs, int maximumMs, final String evaluateTokens, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        try {
            final Number evaluateResult = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).evaluateTokensNumber(evaluateTokens);
            final Timer timer = new Timer() {
                @Override
                public void run() {
                    onSuccess.postLoadTimerFired();
                    pauseTimers.remove(this);
                }
            };
            int pauseMs = (evaluateResult.intValue() <= maximumMs) ? evaluateResult.intValue() : maximumMs;
            pauseMs = (pauseMs >= minimumMs) ? pauseMs : minimumMs;
            pauseTimers.add(timer);
            timer.schedule(pauseMs);
        } catch (EvaluateTokensException ete) {
            onError.postLoadTimerFired();
        }
    }

    protected void compareTimer(final int msToNext, final String listenerId, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) {
        if (timerService.getTimerValue(listenerId) > msToNext) {
            aboveThreshold.postLoadTimerFired();
        } else {
            withinThreshold.postLoadTimerFired();
        }
    }

    protected void clearTimer(final String listenerId) {
        localStorage.deleteStoredDataValue(userResults.getUserData().getUserId(), "timer_" + listenerId);
        timerService.clearTimer(listenerId);
    }

    protected void logTimerValue(final String listenerId, final String eventTag, final int dataChannel) {
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, listenerId, Integer.toString(timerService.getTimerValue(listenerId)), duration.elapsedMillis());
    }

    protected void timerLabel(final String styleName, final int postLoadMs, final String listenerId, final String msLabelFormat) {
        final DateTimeFormat formatter = DateTimeFormat.getFormat(msLabelFormat);
        final HTML html = timedStimulusView.addHtmlText("", styleName);
        Timer labelTimer = new Timer() {
            @Override
            public void run() {
                final int timerValue = timerService.getTimerValue(listenerId);
                final long remainingMs;
                if (postLoadMs > 0) {
                    if (postLoadMs > timerValue) {
                        remainingMs = (long) postLoadMs - timerValue;
                    } else {
                        remainingMs = 0;
                    }
                } else {
                    remainingMs = (long) timerValue;
                }
                Date msBasedDate = new Date(remainingMs);
                String labelText = formatter.format(msBasedDate);
                html.setHTML(labelText);
                schedule(500);
            }
        };
        labelTimer.schedule(5);
    }

    protected void startFrameRateTimer(final FrameTimeTrigger... frameTimeTriggers) {
        final MediaTriggerListener frameTriggerListener = new MediaTriggerListener();
        frameTriggerListeners.add(frameTriggerListener);
        for (final FrameTimeTrigger currentTrigger : frameTimeTriggers) {
            frameTriggerListener.addMediaTriggerListener(currentTrigger.msToNext, currentTrigger);
        }
        Duration frameDuration = new Duration();
        AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback() {
            @Override
            public void execute(double arg0) {
                boolean hasMoreListeners = frameTriggerListener.triggerWhenReady(frameDuration.elapsedMillis());
                if (hasMoreListeners) {
                    AnimationScheduler.get().requestAnimationFrame(this);
                }
            }
        });
    }

    protected FrameTimeTrigger addFrameTimeTrigger(final Stimulus currentStimulus, final int msToNext, final SingleStimulusListener listener) {
        return new FrameTimeTrigger(currentStimulus, listener, msToNext);
    }

    protected void table(final String styleName, final TimedStimulusListener timedStimulusListener) {
        table(styleName, false, timedStimulusListener);
    }

    protected void table(final String styleName, boolean showOnBackButton, final TimedStimulusListener timedStimulusListener) {
        final Widget tableWidget = timedStimulusView.startTable(styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endTable();
        if (showOnBackButton) {
            tableWidget.setVisible(false);
            // todo: backEventListners list should be emptied on screen clear etc
            backEventListners.add(new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    tableWidget.setVisible(!tableWidget.isVisible());
                }
            });
        }
    }

    protected void row(final TimedStimulusListener timedStimulusListener) {
        final InsertPanel.ForIsWidget isWidget = timedStimulusView.startRow();
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRow(isWidget);
    }

    protected void column(final String styleName, final TimedStimulusListener timedStimulusListener) {
        final InsertPanel.ForIsWidget isWidget = timedStimulusView.startCell(styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endCell(isWidget);
    }

    protected void regionAppend(final Stimulus currentStimulus, final String regionIdToken, final String styleName, final TimedStimulusListener timedStimulusListener) {
        final String regionId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(regionIdToken);
        final InsertPanel.ForIsWidget isWidget = timedStimulusView.startRegion(regionId, styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRegion(isWidget);
    }

    protected void regionStyle(final Stimulus currentStimulus, final String regionIdToken, final String styleName) {
        final String regionId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(regionIdToken);
        timedStimulusView.setRegionStyle(regionId, styleName);
    }

    protected void regionReplace(final Stimulus currentStimulus, final String regionIdToken, final String styleName, final TimedStimulusListener timedStimulusListener) {
        final String regionId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(regionIdToken);
        timedStimulusView.clearRegion(regionId);
        final InsertPanel.ForIsWidget isWidget = timedStimulusView.startRegion(regionId, styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRegion(isWidget);
    }

    protected void regionClear(final Stimulus currentStimulus, final String regionIdToken) {
        final String regionId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(regionIdToken);
        timedStimulusView.clearRegion(regionId);
    }

    public void logTokenText(final Stimulus currentStimulus, final String reportType, final String headerKey, final int dataChannel, final String dataLogFormat) {
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, reportType, headerKey, new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(dataLogFormat), duration.elapsedMillis());
    }

    @Override
    public void savePresenterState() {
        if (submissionService != null && userResults.getUserData().isMetadataChanged()) {
            submissionService.submitMetadata(userResults, new DataSubmissionListener() {
                @Override
                public void scoreSubmissionFailed(DataSubmissionException exception) {
                }

                @Override
                public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                    userResults.getUserData().clearMetadataChanged();
                }
            });
        }
        super.savePresenterState();
    }
}
