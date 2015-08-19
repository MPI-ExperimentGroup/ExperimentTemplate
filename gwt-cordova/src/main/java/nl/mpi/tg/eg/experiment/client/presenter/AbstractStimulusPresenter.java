/*
 * Copyright (C) 2015 
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
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Jun 23, 2015 11:36:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractStimulusPresenter extends AbstractPresenter implements Presenter {

    private static final String STIMULUS_ALLOCATION = "stimulusAllocation";
    private static final String SEEN_STIMULUS_LIST = "seenStimulusList";
    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final LocalStorage localStorage;
    private final DataSubmissionService submissionService;
    final UserResults userResults;
    private final Duration duration;
    final ArrayList<ButtonBase> buttonList = new ArrayList<>();

    public AbstractStimulusPresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage) {
        super(widgetTag, new TimedStimulusView(audioPlayer));
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
        this.localStorage = localStorage;
    }

    protected void loadSubsetStimulus(int setCount) {
        final String storedDataValue = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), STIMULUS_ALLOCATION);
        int stimulusAllocation;
        try {
            stimulusAllocation = Integer.parseInt(storedDataValue);
        } catch (NumberFormatException exception) {
            stimulusAllocation = new Random().nextInt(5);
        }
        final String seenStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST);
//        Participants will be exposed to 36 audio+picture combinations, 
//        which are in fact 6 word-picture combination, 
//        but each word repeats 6 times with a different audio files each time (see xls file).
//        There will be two conditions.
//        In one condition, all 36 audio recordings will come from the same speaker (sampled randomly out of 3 speakers). 
//        In the other condition, each word will be presented twice with recordings of speaker1, 
//        twice with recordings of speaker2 and twice with recordings of speaker3 
//        (and the two recordings per speaker will be randomly sampled from the 6 existing recordings per speaker).
//        The picture should always appear one second before the word is played. 
//        It should stay on the screen for 3 seconds (including the pre-word 1 sec).

        switch (stimulusAllocation) {
            case 0:
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), "SubsetStimulus", "Condition1", Stimulus.Similarity.sim.name(), duration.elapsedMillis());
                stimulusProvider.getSubset(Stimulus.Similarity.sim, setCount, seenStimulusList);
                break;
            case 1:
                stimulusProvider.getSubset(Stimulus.Similarity.mid, setCount, seenStimulusList);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), "SubsetStimulus", "Condition1", Stimulus.Similarity.mid.name(), duration.elapsedMillis());
                break;
            case 2:
                stimulusProvider.getSubset(Stimulus.Similarity.diff, setCount, seenStimulusList);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), "SubsetStimulus", "Condition1", Stimulus.Similarity.diff.name(), duration.elapsedMillis());
                break;
            default:
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), "SubsetStimulus", "Condition2", "", duration.elapsedMillis());
                stimulusProvider.getSubset(setCount, seenStimulusList);
                break;
        }
    }

    protected void loadNoiseStimulus() {
        stimulusProvider.getNoisyStimuli();
    }

    protected void pause(final AppEventListner appEventListner, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        Timer timer = new Timer() {
            public void run() {
//                ((TimedStimulusView) simpleView).addText("pause: " + duration.elapsedMillis() + "ms");
                timedStimulusListener.postLoadTimerFired();
            }
        };
        timer.schedule(postLoadMs);
    }

    protected void autoNextPresenter(final AppEventListner appEventListner, final ApplicationController.ApplicationState state) {
        Timer timer = new Timer() {
            public void run() {
                appEventListner.requestApplicationState(state);
            }
        };
        timer.schedule(100);
    }

    protected void endOfStimulus(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        if (!stimulusProvider.hasNextStimulus()) {
            timedStimulusListener.postLoadTimerFired();
        }
    }

    protected void hasMoreStimulus(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        if (stimulusProvider.hasNextStimulus()) {
            timedStimulusListener.postLoadTimerFired();
        }
    }

    protected void responseCorrect(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        timedStimulusListener.postLoadTimerFired();
        throw new UnsupportedOperationException("todo: responseCorrect");
    }

    protected void responseIncorrect(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        timedStimulusListener.postLoadTimerFired();
        throw new UnsupportedOperationException("todo: responseIncorrect");
    }

    protected void nextStimulus() {
        stimulusProvider.getNextStimulus();
    }

    protected void removeStimulus() {
        localStorage.appendStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST, stimulusProvider.getCurrentStimulus().getAudioTag());
    }

    protected void keepStimulus() {
        stimulusProvider.pushCurrentStimulusToEnd();
    }

    protected void addStimulusImage(int width, int postLoadMs, TimedStimulusListener timedStimulusListener) {
        String image = stimulusProvider.getCurrentStimulus().getJpg();
        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusImage", image, duration.elapsedMillis());
        ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + image), width, postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
    }

    protected void playStimulusAudio(long postLoadMs, TimedStimulusListener timedStimulusListener) {
        String ogg = stimulusProvider.getCurrentStimulus().getOgg();
        String mp3 = stimulusProvider.getCurrentStimulus().getMp3();
        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", ogg, duration.elapsedMillis());
        ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(serviceLocations.staticFilesUrl() + ogg), UriUtils.fromString(serviceLocations.staticFilesUrl() + mp3), postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("playStimulusAudio: " + duration.elapsedMillis() + "ms");
    }

    protected void showCurrentMs() {
//        ((TimedStimulusView) simpleView).addText(duration.elapsedMillis() + "ms");
    }

    protected void logTimeStamp(String eventTag) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
    }

    protected void showStimulusGrid(final AppEventListner appEventListner, final int postLoadCorrectMs, final TimedStimulusListener correctListener, final int postLoadIncorrectMs, final TimedStimulusListener incorrectListener, final int columnCount, final String imageWidth, final String eventTag) {
        showStimulusGrid(appEventListner, postLoadCorrectMs, correctListener, postLoadIncorrectMs, incorrectListener, columnCount, imageWidth, eventTag, null);
    }

    protected void showStimulusGrid(final AppEventListner appEventListner, final int postLoadCorrectMs, final TimedStimulusListener correctListener, final int postLoadIncorrectMs, final TimedStimulusListener incorrectListener, final int columnCount, final String imageWidth, final String eventTag, final String alternativeChoice) {
        ((TimedStimulusView) simpleView).stopAudio();
        TimedStimulusListener correctTimedListener = new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
                Timer timer = new Timer() {
                    public void run() {
                        buttonList.clear();
                        correctListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadCorrectMs);
            }
        };
        TimedStimulusListener incorrectTimedListener = new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
                Timer timer = new Timer() {
                    public void run() {
                        buttonList.clear();
                        incorrectListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadIncorrectMs);
            }
        };
        // todo: the appendStoredDataValue should occur in the correct or incorrect response within stimulusListener
        //localStorage.appendStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST, stimulusProvider.getCurrentStimulus().getAudioTag());
        ((TimedStimulusView) simpleView).startGrid();
        int imageCounter = 0;
        if (alternativeChoice != null) {
            buttonList.add(((TimedStimulusView) simpleView).addStringItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus().getAudioTag(), alternativeChoice, correctTimedListener, incorrectTimedListener), alternativeChoice, 0, 0, imageWidth));
        }
        for (final String nextJpg : stimulusProvider.getPictureList()) {
            buttonList.add(((TimedStimulusView) simpleView).addImageItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus().getAudioTag(), nextJpg, correctTimedListener, incorrectTimedListener), UriUtils.fromString(serviceLocations.staticFilesUrl() + nextJpg), imageCounter / columnCount, 1 + imageCounter++ % columnCount, imageWidth));
        }
        disableStimulusButtons();
        ((TimedStimulusView) simpleView).endGrid();
        //((TimedStimulusView) simpleView).addAudioPlayerGui();
    }

    private PresenterEventListner getEventListener(final ArrayList<ButtonBase> buttonList, final String eventTag, final String tagValue1, final String tagValue2, final TimedStimulusListener correctTimedListener, final TimedStimulusListener incorrectTimedListener) {
        return new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button) {
                for (ButtonBase currentButton : buttonList) {
                    currentButton.setEnabled(false);
                }
                button.addStyleName("stimulusButtonHighlight");
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, tagValue1, tagValue2, duration.elapsedMillis());
                if (stimulusProvider.getCurrentStimulus().getJpg().equals(tagValue2)) {
                    correctTimedListener.postLoadTimerFired();
                } else {
                    incorrectTimedListener.postLoadTimerFired();
                }
            }
        };
    }

    public void disableStimulusButtons() {
        for (ButtonBase currentButton : buttonList) {
            currentButton.setEnabled(false);
        }
//        ((TimedStimulusView) simpleView).addText("disableStimulusButtons: " + duration.elapsedMillis() + "ms");
    }

    public void hideStimulusButtons() {
        for (ButtonBase currentButton : buttonList) {
            currentButton.setVisible(false);
        }
//        ((TimedStimulusView) simpleView).addText("hideStimulusButtons: " + duration.elapsedMillis() + "ms");
    }

    public void showStimulusButtons() {
        for (ButtonBase currentButton : buttonList) {
            currentButton.setVisible(true);
        }
//        ((TimedStimulusView) simpleView).addText("showStimulusButtons: " + duration.elapsedMillis() + "ms");
    }

    public void enableStimulusButtons() {
        for (ButtonBase currentButton : buttonList) {
            currentButton.setEnabled(true);
        }
//        ((TimedStimulusView) simpleView).addText("enableStimulusButtons: " + duration.elapsedMillis() + "ms");
    }

    public void showStimulusProgress() {
        ((TimedStimulusView) simpleView).addHtmlText((stimulusProvider.getTotalStimuli() - stimulusProvider.getRemainingStimuli()) + " / " + stimulusProvider.getTotalStimuli());
//        ((TimedStimulusView) simpleView).addText("showStimulusProgress: " + duration.elapsedMillis() + "ms");
    }

    public void popupMessage(final PresenterEventListner presenterListerner, String message, boolean condition) {
        if (condition) {
            ((TimedStimulusView) simpleView).showHtmlPopup(presenterListerner, message);
        }
    }

    protected boolean stimulusIndexIn(int[] validIndexes) {
        int currentIndex = stimulusProvider.getTotalStimuli() - stimulusProvider.getRemainingStimuli();
        for (int currentValid : validIndexes) {
            if (currentIndex == currentValid) {
                return true;
            }
        }
        return false;
    }

    protected void clearStimulus() {
        ((TimedStimulusView) simpleView).clearGui();
    }

    protected void autoNextStimulus(final AppEventListner appEventListner, final String eventTag, boolean condition) {
        if (condition) {
            logTimeStamp(eventTag);
            ((TimedStimulusView) simpleView).stopAudio();
            buttonList.clear();
            ((TimedStimulusView) simpleView).clearGui();
            setContent(appEventListner);
        }
    }

    protected void nextStimulusButton(final AppEventListner appEventListner, final String eventTag, boolean condition, final String buttonLabel) {
        if (condition && stimulusProvider.hasNextStimulus()) {
            PresenterEventListner eventListner = new PresenterEventListner() {

                @Override
                public String getLabel() {
                    return buttonLabel;
                }

                @Override
                public void eventFired(ButtonBase button) {
                    autoNextStimulus(appEventListner, eventTag, true);
                }
            };
            ((TimedStimulusView) simpleView).addOptionButton(eventListner);
        }
    }

    protected void conditionalHtml(final AppEventListner appEventListner, boolean condition, final String label) {
        if (condition) {
            ((ComplexView) simpleView).addHtmlText(label);
        }
    }

    protected void endOfStimulusButton(final PresenterEventListner appEventListner, final String eventTag) {
        logTimeStamp(eventTag);
        if (!stimulusProvider.hasNextStimulus()) {
            ((TimedStimulusView) simpleView).addOptionButton(appEventListner);
        }
    }

    protected void audioButton(final String eventTag, final String mp3Path, final String oggPath, final String imagePath) {
        ((TimedStimulusView) simpleView).addImageButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return imagePath;
            }

            @Override
            public void eventFired(ButtonBase button) {
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "PlayAudio", eventTag, duration.elapsedMillis());
                ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(serviceLocations.staticFilesUrl() + oggPath), UriUtils.fromString(serviceLocations.staticFilesUrl() + mp3Path), 0, new TimedStimulusListener() {

                    @Override
                    public void postLoadTimerFired() {
                    }
                });
            }
        }, UriUtils.fromString(serviceLocations.staticFilesUrl() + imagePath));
    }
}
