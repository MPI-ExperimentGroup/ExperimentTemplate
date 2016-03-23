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
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
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
    private TimedStimulusListener hasMoreStimulusListener;
    private TimedStimulusListener endOfStimulusListener;

    public AbstractStimulusPresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage) {
        super(widgetTag, new TimedStimulusView(audioPlayer));
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
        this.localStorage = localStorage;
    }

    @Override
    public void setState(AppEventListner appEventListner, ApplicationController.ApplicationState prevState, ApplicationController.ApplicationState nextState) {
        super.setState(appEventListner, prevState, null);
        this.nextState = nextState;
    }

    // todo: maxSpeakerWordCount needs to be utilised correctly
    protected void loadSubsetStimulus(String eventTag, final List<GeneratedStimulus.Tag> selectionTags, final GeneratedStimulus.Tag condition0Tag, final GeneratedStimulus.Tag condition1Tag, final GeneratedStimulus.Tag condition2Tag, final int maxStimulusCount) {
        final String storedDataValue = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), STIMULUS_ALLOCATION);
        int stimulusAllocation;
        try {
            stimulusAllocation = Integer.parseInt(storedDataValue);
        } catch (NumberFormatException exception) {
            stimulusAllocation = new Random().nextInt(5);
            localStorage.setStoredDataValue(userResults.getUserData().getUserId(), STIMULUS_ALLOCATION, Integer.toString(stimulusAllocation));
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
//        final Stimulus.Tag[] selectionTags = new Stimulus.Tag[]{Stimulus.Tag.tag_ประเพณีบุญบั้งไฟ, Stimulus.Tag.tag_Rocket, Stimulus.Tag.tag_Festival, Stimulus.Tag.tag_Lao, Stimulus.Tag.tag_Thai, Stimulus.Tag.tag_ບຸນບັ້ງໄຟ};
//        final Stimulus.Tag case0Tag = Stimulus.Tag.tag_centipedes;
//        final Stimulus.Tag case1Tag = Stimulus.Tag.tag_scorpions;
//        final Stimulus.Tag case2Tag = Stimulus.Tag.tag_termites;

        switch (stimulusAllocation) {
            case 0:
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, "Condition0", condition0Tag.name(), duration.elapsedMillis());
                stimulusProvider.getSubset(condition0Tag, maxStimulusCount, selectionTags, seenStimulusList);
                break;
            case 1:
                stimulusProvider.getSubset(condition1Tag, maxStimulusCount, selectionTags, seenStimulusList);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, "Condition1", condition1Tag.name(), duration.elapsedMillis());
                break;
            case 2:
                stimulusProvider.getSubset(condition2Tag, maxStimulusCount, selectionTags, seenStimulusList);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, "Condition2", condition2Tag.name(), duration.elapsedMillis());
                break;
            default:
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, "Condition3", "", duration.elapsedMillis());
                stimulusProvider.getSubset(maxStimulusCount, seenStimulusList, Arrays.asList(new GeneratedStimulus.Tag[]{condition1Tag, condition2Tag, condition0Tag}), selectionTags, 32);
                break;
        }
    }

    protected void loadAllStimulus(String eventTag, final List<GeneratedStimulus.Tag> selectionTags, final boolean randomise, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String seenStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST);
        stimulusProvider.getSubset(selectionTags, randomise, seenStimulusList);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        showStimulus();
    }

    protected void loadSdCardStimulus(String eventTag, final List<GeneratedStimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String seenStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        ArrayList<String> directoryTagArray = new ArrayList<>();
        for (GeneratedStimulus.Tag directoryTag : selectionTags) {
            directoryTagArray.add(directoryTag.name().substring("tag_".length()));
        }
        stimulusProvider.getSdCardSubset(directoryTagArray, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                showStimulus();
            }
        }, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                ((TimedStimulusView) simpleView).addText("Stimulus loading error");
            }
        }, maxStimulusCount, randomise, seenStimulusList);
    }

    protected void loadStimulus(String eventTag, final List<GeneratedStimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String seenStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST);
        stimulusProvider.getSubset(selectionTags, maxStimulusCount, randomise, seenStimulusList);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        showStimulus();
    }

    protected void pause(int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        Timer timer = new Timer() {
            public void run() {
//                ((TimedStimulusView) simpleView).addText("pause: " + duration.elapsedMillis() + "ms");
                timedStimulusListener.postLoadTimerFired();
            }
        };
        timer.schedule(postLoadMs);
    }

    protected void stimulusPause(final TimedStimulusListener timedStimulusListener) {
        pause(stimulusProvider.getCurrentStimulus().getPauseMs(), timedStimulusListener);
    }

    protected void currentStimulusHasTag(int postLoadMs, final List<GeneratedStimulus.Tag> tagList, final TimedStimulusListener hasTagListener, final TimedStimulusListener hasntTagListener) {
//        List<Stimulus.Tag> editableList = new LinkedList<Stimulus.Tag>(tagList);
//        editableList.retainAll();
//        if (editableList.isEmpty()) {
        if (stimulusProvider.getCurrentStimulus().getTags().containsAll(tagList)) {
            pause(postLoadMs, hasTagListener);
        } else {
            pause(postLoadMs, hasntTagListener);
        }
    }

    public void stimulusLabel() {
        final String label = stimulusProvider.getCurrentStimulus().getLabel();
        if (label != null) {
            ((TimedStimulusView) simpleView).addHtmlText(label);
        }
    }

    protected void showStimulus() {
        if (stimulusProvider.hasNextStimulus()) {
            stimulusProvider.getNextStimulus();
//            super.startAudioRecorderTag(STIMULUS_TIER);
            hasMoreStimulusListener.postLoadTimerFired();
        } else {
            endOfStimulusListener.postLoadTimerFired();
        }
    }
//    private static final int STIMULUS_TIER = 2;

    protected void responseCorrect(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        timedStimulusListener.postLoadTimerFired();
        throw new UnsupportedOperationException("todo: responseCorrect");
    }

    protected void responseIncorrect(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        timedStimulusListener.postLoadTimerFired();
        throw new UnsupportedOperationException("todo: responseIncorrect");
    }

    protected void removeStimulus() {
        localStorage.appendStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST, "-" + stimulusProvider.getCurrentStimulus().getUniqueId());
    }

    protected void keepStimulus() {
        stimulusProvider.pushCurrentStimulusToEnd();
    }

    protected void stimulusImage(int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, TimedStimulusListener timedStimulusListener) {
        final Stimulus currentStimulus = stimulusProvider.getCurrentStimulus();
        if (currentStimulus.isImage()) {
            String image = currentStimulus.getImage();
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusImage", image, duration.elapsedMillis());
            ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromTrustedString(image), percentOfPage, maxHeight, maxWidth, postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
        } else if (currentStimulus.isMp3()) {
            String mp3 = currentStimulus.getMp3();
            String ogg = currentStimulus.getOgg();
            final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(ogg);
            final SafeUri mp3TrustedString = (mp3 == null) ? null : UriUtils.fromTrustedString(mp3);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", (ogg == null) ? mp3 : ogg, duration.elapsedMillis());
            ((TimedStimulusView) simpleView).addTimedAudio(oggTrustedString, mp3TrustedString, postLoadMs, timedStimulusListener);
        } else if (currentStimulus.isMp4() || currentStimulus.isOgg()) {
            String ogg = currentStimulus.getOgg();
            String mp4 = currentStimulus.getMp4();
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusVideo", (ogg == null) ? mp4 : ogg, duration.elapsedMillis());
            final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(ogg);
            final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString(mp4);
            ((TimedStimulusView) simpleView).addTimedVideo(oggTrustedString, mp4TrustedString, percentOfPage, maxHeight, maxWidth, postLoadMs, timedStimulusListener);
        } else if (currentStimulus.getLabel() != null) {
            ((TimedStimulusView) simpleView).addHtmlText(currentStimulus.getLabel());
            timedStimulusListener.postLoadTimerFired();
        } else {
            final String incorrect_stimulus_format = "incorrect stimulus format";
            nextStimulusButton(incorrect_stimulus_format, incorrect_stimulus_format + " " + currentStimulus.getLabel(), true, -1);
        }
    }

    protected void stimulusCodeImage(int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, String codeFormat, TimedStimulusListener timedStimulusListener) {
        String formattedCode = codeFormat.replace("<code>", stimulusProvider.getCurrentStimulus().getCode());
        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeImage", formattedCode, duration.elapsedMillis());
        ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + formattedCode), percentOfPage, maxHeight, maxWidth, postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
    }

    protected void stimulusAudio(long postLoadMs, TimedStimulusListener timedStimulusListener) {
        String ogg = stimulusProvider.getCurrentStimulus().getOgg();
        String mp3 = stimulusProvider.getCurrentStimulus().getMp3();
        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", ogg, duration.elapsedMillis());
        ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(ogg), UriUtils.fromString(mp3), postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("playStimulusAudio: " + duration.elapsedMillis() + "ms");
    }

    public void ratingFooterButton(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener, final String ratingLabels, final int eventTier) {
        for (final String ratingItem : ratingLabels.split(",")) {
            ((ComplexView) simpleView).addFooterButton(new PresenterEventListner() {
                @Override
                public String getLabel() {
                    return ratingItem;
                }

                @Override
                public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                    endAudioRecorderTag(eventTier, ratingItem);
                    timedStimulusListener.postLoadTimerFired();
                }

                @Override
                public int getHotKey() {
                    return -1;
                }
            });
        }
    }

    protected void showCurrentMs() {
//        ((TimedStimulusView) simpleView).addText(duration.elapsedMillis() + "ms");
    }

    protected void logTimeStamp(String eventTag) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
    }

    protected void endAudioRecorderTag(int tier, String tagString) {
        super.endAudioRecorderTag(tier, stimulusProvider.getCurrentStimulus().getUniqueId(), stimulusProvider.getCurrentStimulus().getCode(), tagString);
    }

    protected void startAudioRecorderTag(int tier) {
        super.startAudioRecorderTag(tier); //((tier < 1) ? 1 : tier) + 2); //  tier 1 and 2 are reserved for stimulus set loading and stimulus display events
    }

    protected void startAudioRecorder(boolean wavFormat, boolean filePerStimulus, String directoryName) {
        super.startAudioRecorder(true, userResults.getUserData().getUserId().toString(), directoryName, (filePerStimulus) ? stimulusProvider.getCurrentStimulus().getUniqueId() : "");
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
            buttonList.add(((TimedStimulusView) simpleView).addStringItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus().getImage(), alternativeChoice, correctTimedListener, incorrectTimedListener), alternativeChoice, 0, 0, imageWidth));
        }
        for (final String nextJpg : stimulusProvider.getPictureList()) {
            buttonList.add(((TimedStimulusView) simpleView).addImageItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus().getImage(), nextJpg, correctTimedListener, incorrectTimedListener), UriUtils.fromString(nextJpg), imageCounter / columnCount, 1 + imageCounter++ % columnCount, imageWidth));
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
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                for (ButtonBase currentButton : buttonList) {
                    currentButton.setEnabled(false);
                }
                button.addStyleName("stimulusButtonHighlight");
                // eventTag is set by the user and is different for each state (correct/incorrect).
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), eventTag, tagValue1, tagValue2, duration.elapsedMillis());
                if (stimulusProvider.getCurrentStimulus().getImage().equals(tagValue2)) {
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

    public void popupMessage(final PresenterEventListner presenterListerner, String message) {
        ((TimedStimulusView) simpleView).showHtmlPopup(presenterListerner, message);
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
        ((TimedStimulusView) simpleView).clearPage();
        buttonList.clear();
    }

    protected void nextStimulus(final String eventTag, final boolean norepeat) {
        logTimeStamp(eventTag);
        if (norepeat) {
            removeStimulus();
        }
        ((TimedStimulusView) simpleView).stopAudio();
        ((TimedStimulusView) simpleView).clearPage();
        buttonList.clear();
        showStimulus();
    }

    protected void nextStimulusButton(final String eventTag, final String buttonLabel, final boolean norepeat, final int hotKey) {
//        if (stimulusProvider.hasNextStimulus()) {
        PresenterEventListner eventListner = new PresenterEventListner() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                nextStimulus(eventTag, norepeat);
            }
        };
        ((TimedStimulusView) simpleView).addOptionButton(eventListner);
//        }
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
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "PlayAudio", eventTag, duration.elapsedMillis());
                ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(oggPath), UriUtils.fromString(mp3Path), 0, new TimedStimulusListener() {

                    @Override
                    public void postLoadTimerFired() {
                    }
                });
            }
        }, UriUtils.fromString(imagePath));
    }

    @Override
    public void savePresenterState() {
        super.savePresenterState();
        stopAudioRecorder();
    }

}
