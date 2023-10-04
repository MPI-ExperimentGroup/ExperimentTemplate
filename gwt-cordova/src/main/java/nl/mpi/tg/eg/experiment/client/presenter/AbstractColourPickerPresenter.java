/*
 * Copyright (C) 2014 Language In Interaction
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

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.frinex.common.StimuliProvider;
import nl.mpi.tg.eg.experiment.client.view.ColourPickerCanvasView;
import nl.mpi.tg.eg.experiment.client.exception.CanvasError;
import nl.mpi.tg.eg.experiment.client.listener.CurrentStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.model.colour.StimulusResponse;
import nl.mpi.tg.eg.experiment.client.model.colour.StimulusResponseGroup;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
//import nl.mpi.tg.eg.experiment.client.util.GeneratedStimulusProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.frinex.common.model.StimulusSelector;

/**
 * @since Oct 10, 2014 9:52:25 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractColourPickerPresenter implements Presenter {

    protected final Messages messages = GWT.create(Messages.class);
    private final RootLayoutPanel widgetTag;
    private final DataSubmissionService submissionService;
//    private final ArrayList<Stimulus> stimuli;
//    private final int maxStimuli;
    private final UserResults userResults;
    private final ColourPickerCanvasView colourPickerCanvasView;
    protected StimuliProvider stimulusProviderInternal;
//    private Stimulus currentStimulus = null;
//    final List<GeneratedStimulus.Tag> selectionTags;
    private final Duration duration;
    private long startMs;
//    private final int repeatCount;
    //private int shownSetCount;
    private int shownCount = 0;
    private CurrentStimulusListener hasMoreStimulusListener;
    private TimedStimulusListener endOfStimulusListener;
    private final LocalStorage localStorage;
    private AppEventListener appEventListener;
    private ApplicationController.ApplicationState nextState;
    private StimulusResponseGroup stimulusResponseGroup = null;
    private String progressLabelFormatString = null;

    public AbstractColourPickerPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) throws CanvasError {
        this.widgetTag = widgetTag;
//        stimulusProvider = new nl.mpi.tg.eg.experiment.client.service.StimulusProvider(GeneratedStimulusProvider.values);
//        this.stimuliGroup = userResults.getPendingStimuliGroup();
//        userResults.setPendingStimuliGroup(null);
//        this.stimuli = new ArrayList<>(stimuliGroup.getStimuli());
        this.localStorage = localStorage;
        this.submissionService = submissionService;
        this.userResults = userResults;
//        this.repeatCount = repeatCount;
//        maxStimuli = this.stimuli.size();
//        this.selectionTags = selectionTags;
        colourPickerCanvasView = new ColourPickerCanvasView();
        colourPickerCanvasView.setRandomColour();
        duration = new Duration();
    }

//    private void submitFrinexResults() {
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "ScoreLog", new ResultsSerialiser() {
//            final DateTimeFormat format = DateTimeFormat.getFormat(messages.reportDateFormat());
//
//            @Override
//            protected String formatDate(Date date) {
//                return format.format(date);
//            }
//
//            @Override
//            protected String getSeparator() {
//                return ",";
//            }
//
//            @Override
//            protected String getRowSeparator() {
//                return "\\n";
//            }
//
//        }.serialise(stimulusResponseGroup, userResults.getUserData().getUserId().toString()), 0);
//    }
    protected void requestFilePermissions() {
        // not used but required by default
    }

    private void triggerEvent() {
        if (!stimulusProviderInternal.hasNextStimulus(1)) {
            //shownSetCount++;
//            if (repeatCount > shownSetCount) {
//                stimulusProviderInternal.getSubset(selectionTags, false, "");
//            }
//            submitFrinexResults();
            stimulusResponseGroup.setIsComplete(true);
            appEventListener.requestApplicationState(nextState);
            endOfStimulusListener.postLoadTimerFired();
        } else {
            colourPickerCanvasView.setRandomColour();
            stimulusProviderInternal.nextStimulus(1);
            startMs = System.currentTimeMillis();
            shownCount++;
            int repeatCount = 1;
            final double percentageDone = ((double) (shownCount - 1) / (stimulusProviderInternal.getTotalStimuli() * repeatCount)) * 100;
            final String progressLabelString = (progressLabelFormatString == null) ? Integer.toString((int) percentageDone) + "%"
                    : progressLabelFormatString
                            .replace("<colourPicker_total>", Integer.toString(stimulusProviderInternal.getTotalStimuli() * repeatCount))
                            .replace("<colourPicker_index>", Integer.toString(shownCount))
                            .replace("<colourPicker_percent>", Integer.toString((int) percentageDone));
            colourPickerCanvasView.setStimulus(stimulusProviderInternal.getCurrentStimulus(), progressLabelString
            //                    messages.stimulusscreenprogresstext(Integer.toString(shownCount), Integer.toString(stimulusProviderInternal.getTotalStimuli() * repeatCount))

            );
        }
    }

    protected abstract String getTitle();

    protected abstract String getSelfTag();

    abstract void setContent(final AppEventListener appEventListener);

    @Override
    public void setState(final AppEventListener appEventListener, final ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        this.appEventListener = appEventListener;
        this.nextState = nextState;
        widgetTag.clear();
        colourPickerCanvasView.setQuitButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return prevState.label;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                // delete the incomplete test results
//                userResults.deleteStimuliGroupResults(stimuliGroup);
                appEventListener.requestApplicationState(prevState);
            }
        });
        colourPickerCanvasView.resizeView();
        widgetTag.add(colourPickerCanvasView);

        setContent(appEventListener);
    }

    public void htmlTokenText(final Stimulus currentStimulus, String textString, String styleName, XmlId xmlId) {
        // addText and htmlTokenText with styleName are not offered by the ColourPicker, but these features are used in the auto generated test builds
        htmlTokenText(currentStimulus, textString, xmlId);
    }

    public void htmlTokenText(final Stimulus currentStimulus, String textString, XmlId xmlId) {
        progressLabelFormatString = textString;
        // formatting options are <colourPicker_total> <colourPicker_index> <colourPicker_percent>
    }

    public void actionButton(final PresenterEventListener eventListener, final String group) {
        switch (group) {
            case "RejectButton":
                setRejectButton(eventListener.getLabel());
                break;
            case "AcceptButton":
                setAcceptButton(eventListener.getLabel());
                break;
            default:
                break;
        }
        colourPickerCanvasView.resizeView();
    }

    private void setRejectButton(final String buttonLabel) {
        colourPickerCanvasView.setRejectButton(new PresenterEventListener() {

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                final long durationMs = System.currentTimeMillis() - startMs;
                stimulusResponseGroup.addResponse(stimulusProviderInternal.getCurrentStimulus(), new StimulusResponse(null, new Date(), durationMs));
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, stimulusResponseGroup.getPostName(), stimulusProviderInternal.getCurrentStimulus().getUniqueId(), "", (int) (durationMs));
                submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), 0, "Reject", stimulusProviderInternal.getCurrentStimulus(), "", null, (int) durationMs, new String[]{});
                triggerEvent();
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public String getLabel() {
                return buttonLabel;
            }
        });
    }

    private void setAcceptButton(final String buttonLabel) {
        colourPickerCanvasView.setAcceptButton(new PresenterEventListener() {

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                final long durationMs = System.currentTimeMillis() - startMs;
                stimulusResponseGroup.addResponse(stimulusProviderInternal.getCurrentStimulus(), new StimulusResponse(colourPickerCanvasView.getColour(), new Date(), durationMs));
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, stimulusResponseGroup.getPostName(), stimulusProviderInternal.getCurrentStimulus().getUniqueId(), colourPickerCanvasView.getColour().getHexValue(), (int) (durationMs));
                submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), 0, "Accept", stimulusProviderInternal.getCurrentStimulus(), colourPickerCanvasView.getColour().getHexValue(), null, (int) durationMs, new String[]{});
                triggerEvent();
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public String getLabel() {
                return buttonLabel;
            }
        });
    }

    public void helpDialogue(String helpText, String closeButtonLabel) {
        colourPickerCanvasView.setInstructions(helpText, messages.helpButtonChar(), closeButtonLabel);
    }

    protected void loadStimulus(String eventTag,
            final StimulusSelector[] stimulusSelectors, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final CurrentStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener
    ) {
        this.stimulusProviderInternal = stimulusProvider;
        final List<Stimulus.Tag> selectionTags = new ArrayList<>();
        for (StimulusSelector selector : stimulusSelectors) {
            selectionTags.add(selector.getTag());
        }
        stimulusProviderInternal.getSubset(selectionTags, "", -1);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        stimulusResponseGroup = new StimulusResponseGroup(getTitle(), getSelfTag());
        userResults.addStimulusResponseGroup(stimulusResponseGroup);
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, stimulusProviderInternal.generateStimuliStateSnapshot(), duration.elapsedMillis());
//        showStimulus();
        triggerEvent();
    }

    @Override
    public void fireBackEvent() {
    }

    @Override
    public void fireResizeEvent() {
    }

    @Override
    public void fireWindowClosing() {
    }

    @Override
    public void savePresenterState() {
    }
}
