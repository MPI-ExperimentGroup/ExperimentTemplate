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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.GroupActivityListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.SdCardStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.GroupParticipantService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.MatchingStimuliGroup;
import nl.mpi.tg.eg.experiment.client.service.MetadataFieldProvider;
import nl.mpi.tg.eg.experiment.client.service.SdCardImageCapture;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Jun 23, 2015 11:36:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractStimulusPresenter extends AbstractPresenter implements Presenter {

    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private static final String LOADED_STIMULUS_LIST = "loadedStimulusList";
    private static final String SEEN_STIMULUS_INDEX = "seenStimulusIndex";
    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final LocalStorage localStorage;
    private final DataSubmissionService submissionService;
    private GroupParticipantService groupParticipantService = null;
    final UserResults userResults;
    private final Duration duration;
    final ArrayList<ButtonBase> buttonList = new ArrayList<>();
    private TimedStimulusListener hasMoreStimulusListener;
    private TimedStimulusListener endOfStimulusListener;
    final private ArrayList<PresenterEventListner> nextButtonEventListnerList = new ArrayList<>();
    private final ArrayList<StimulusFreeText> stimulusFreeTextList = new ArrayList<>();
    MatchingStimuliGroup matchingStimuliGroup = null;
    private boolean hasSubdirectories = false;

    protected enum AnimateTypes {
        bounce, none, stimuliCode
    }

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
    protected void loadSubsetStimulus(String eventTag, final List<Stimulus.Tag> selectionTags, final List<GeneratedStimulus.Tag> randomTags, final MetadataField stimulusAllocationField, final GeneratedStimulus.Tag condition0Tag, final GeneratedStimulus.Tag condition1Tag, final GeneratedStimulus.Tag condition2Tag, final int maxStimulusCount) {
        // todo: implement randomTags
        final String storedDataValue = userResults.getUserData().getMetadataValue(stimulusAllocationField);
        int stimulusAllocation;
        try {
            stimulusAllocation = Integer.parseInt(storedDataValue);
        } catch (NumberFormatException exception) {
            stimulusAllocation = new Random().nextInt(5);
            userResults.getUserData().setMetadataValue(stimulusAllocationField, Integer.toString(stimulusAllocation));
            localStorage.storeData(userResults);
        }
        final String storedStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag());
        int seenStimulusIndex;
        try {
            seenStimulusIndex = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndex = -1;
        }
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
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, "Condition0", condition0Tag.name(), duration.elapsedMillis());
                stimulusProvider.getSubset(condition0Tag, maxStimulusCount, selectionTags, storedStimulusList, seenStimulusIndex);
                break;
            case 1:
                stimulusProvider.getSubset(condition1Tag, maxStimulusCount, selectionTags, storedStimulusList, seenStimulusIndex);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, "Condition1", condition1Tag.name(), duration.elapsedMillis());
                break;
            case 2:
                stimulusProvider.getSubset(condition2Tag, maxStimulusCount, selectionTags, storedStimulusList, seenStimulusIndex);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, "Condition2", condition2Tag.name(), duration.elapsedMillis());
                break;
            default:
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, "Condition3", "", duration.elapsedMillis());
                stimulusProvider.getSubset(maxStimulusCount, storedStimulusList, seenStimulusIndex, Arrays.asList(new Stimulus.Tag[]{condition1Tag, condition2Tag, condition0Tag}), selectionTags, 32);
                break;
        }
    }

    protected void loadAllStimulus(String eventTag, final List<Stimulus.Tag> selectionTags, final boolean randomise, int repeatCount, final int repeatRandomWindow, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String storedStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag());
        int seenStimulusIndex;
        try {
            seenStimulusIndex = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndex = -1;
        }
        stimulusProvider.getSubset(selectionTags, randomise, repeatCount, repeatRandomWindow, storedStimulusList, seenStimulusIndex);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        showStimulus(null);
    }

    protected void loadSdCardStimulus(final String eventTag, final List<Stimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String storedStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag() + subDirectory);
        int seenStimulusIndextemp;
        try {
            seenStimulusIndextemp = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndextemp = -1;
        }
        final int seenStimulusIndex = seenStimulusIndextemp;
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                if (subDirectory == null || subDirectory.isEmpty()) {
                    endOfStimulusListener.postLoadTimerFired();
                } else {
                    localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), "");
                    loadSdCardStimulus(eventTag, selectionTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, hasMoreStimulusListener, endOfStimulusListener);
                }
            }
        };
        ArrayList<String> directoryTagArray = new ArrayList<>();
        if (subDirectory == null || subDirectory.isEmpty()) {
            for (Stimulus.Tag directoryTag : selectionTags) {
                directoryTagArray.add(directoryTag.name().substring("tag_".length()));
            }
        } else {
            // if a sub directory is passed then only load stimuli from that directory
            directoryTagArray.add(subDirectory);
        }
        final List<String[]> directoryList = new ArrayList<>();
        // @todo: add the limits for maxStimulusCount and maxStimulusPerTag -
        stimulusProvider.getSdCardSubset(directoryTagArray, directoryList, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                ((TimedStimulusView) simpleView).clearPage();
                if (directoryList.isEmpty()) {
                    showStimulus(null);
                } else {
                    hasSubdirectories = true;
                    for (final String[] directory : directoryList) {
                        final boolean directoryCompleted = Boolean.parseBoolean(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "completed-directory-" + directory[0] + "-" + getSelfTag()));
                        ((TimedStimulusView) simpleView).addOptionButton(new PresenterEventListner() {
                            @Override
                            public String getLabel() {
                                return directory[1] + ((directoryCompleted) ? " (complete)" : "");
                            }

                            @Override
                            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                                // show the subdirectorydirectory[0], 
                                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), directory[0]);
                                loadSdCardStimulus(directory[1], selectionTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, hasMoreStimulusListener, new TimedStimulusListener() {
                                    @Override
                                    public void postLoadTimerFired() {
                                        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "completed-directory-" + directory[0] + "-" + getSelfTag(), Boolean.toString(true));
                                        // go back to the initial directory 
                                        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), "");
                                        loadSdCardStimulus(eventTag, selectionTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, hasMoreStimulusListener, endOfStimulusListener);
                                    }
                                });
                            }

                            @Override
                            public int getHotKey() {
                                return -1;
                            }
                        });
                    }
                }
            }
        }, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                ((TimedStimulusView) simpleView).addText("Stimulus loading error");
            }
        }, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, storedStimulusList, seenStimulusIndex);
    }

    @Override
    protected boolean allowBackAction(final AppEventListner appEventListner) {
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        if (subDirectory != null) {
            if (!subDirectory.isEmpty()) {
                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), "");
                setContent(appEventListner);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    protected void loadStimulus(String eventTag, final List<Stimulus.Tag> selectionTags, final int maxStimulusCount, final boolean randomise, int repeatCount, final int repeatRandomWindow, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        loadStimulus(eventTag, selectionTags, Arrays.asList(new Stimulus.Tag[]{}), null, maxStimulusCount, null, null, randomise, repeatCount, repeatRandomWindow, hasMoreStimulusListener, endOfStimulusListener);
    }

    protected void loadStimulus(String eventTag, final List<Stimulus.Tag> selectionTags, final List<Stimulus.Tag> randomTags, final MetadataField stimulusAllocationField, final int maxStimulusCount, final boolean randomise, int repeatCount, final int repeatRandomWindow, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        // todo: remove this when the experiment definition is updated
        loadStimulus(eventTag, selectionTags, randomTags, stimulusAllocationField, maxStimulusCount, maxStimulusCount, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, hasMoreStimulusListener, endOfStimulusListener);
    }

    protected void loadStimulus(String eventTag,
            final List<Stimulus.Tag> selectionTags, // only stimuli with tags in this list can be included
            final List<Stimulus.Tag> randomTags,
            final MetadataField stimulusAllocationField,
            final int maxStimulusCount,
            final Integer minStimuliPerTag,
            final Integer maxStimuliPerTag,
            final boolean randomise,
            int repeatCount,
            final int repeatRandomWindow,
            final TimedStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener
    ) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        final String storedStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag());
        int seenStimulusIndex;
        try {
            seenStimulusIndex = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndex = -1;
        }
        final List<Stimulus.Tag> allocatedTags = new ArrayList<>(selectionTags);
        if (!randomTags.isEmpty()) {
            final String storedStimulusAllocation = userResults.getUserData().getMetadataValue(stimulusAllocationField);
            try {
                allocatedTags.add(GeneratedStimulus.Tag.valueOf(storedStimulusAllocation));
            } catch (IllegalArgumentException exception) {
                Stimulus.Tag stimulusAllocation = randomTags.get(new Random().nextInt(randomTags.size()));
                userResults.getUserData().setMetadataValue(stimulusAllocationField, stimulusAllocation.name());
                localStorage.storeData(userResults);
                allocatedTags.add(stimulusAllocation);
                // submit the user metadata so that the selected stimuli group is stored
                submissionService.submitMetadata(userResults, new DataSubmissionListener() {
                    @Override
                    public void scoreSubmissionFailed(DataSubmissionException exception) {
                    }

                    @Override
                    public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                    }
                });
            }
        }
        // @todo: add the limits for maxStimulusCount and maxStimulusPerTag -
        stimulusProvider.getSubset(allocatedTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, storedStimulusList, seenStimulusIndex);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        showStimulus(null);
    }

    protected void withMatchingStimulus(String eventTag, final String matchingRegex, final int maxStimulusCount, final boolean randomise, int repeatCount, final int repeatRandomWindow, final TimedStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        matchingStimuliGroup = new MatchingStimuliGroup(stimulusProvider.getCurrentStimulus(), stimulusProvider.getMatchingStimuli(matchingRegex, maxStimulusCount), randomise, repeatCount, hasMoreStimulusListener, endOfStimulusListener);
        matchingStimuliGroup.getNextStimulus(stimulusProvider);
        matchingStimuliGroup.showNextStimulus();
    }

    public void htmlTokenText(String textString) {
        ((TimedStimulusView) simpleView).addHtmlText(new HtmlTokenFormatter(groupParticipantService, userResults.getUserData()).formatString(textString), null);
    }

    public void htmlText(String textString) {
        ((TimedStimulusView) simpleView).addHtmlText(textString, null);
    }

    protected void countdownLabel(final String timesUpLabel, final int postLoadMs, final String msLabelFormat, final TimedStimulusListener timedStimulusListener) {
        countdownLabel(timesUpLabel, postLoadMs, msLabelFormat, null, timedStimulusListener);
    }

    protected void countdownLabel(final String timesUpLabel, final int postLoadMs, final String msLabelFormat, String styleName, final TimedStimulusListener timedStimulusListener) {
        final Duration labelDuration = new Duration();
        final DateTimeFormat formatter = DateTimeFormat.getFormat(msLabelFormat);
        final HTML html = ((TimedStimulusView) simpleView).addHtmlText(formatter.format(new Date((long) postLoadMs)), styleName);
        Timer labelTimer = new Timer() {
            @Override
            public void run() {
                final long remainingMs = (long) postLoadMs - labelDuration.elapsedMillis();
                if (remainingMs > 0) {
                    Date msBasedDate = new Date(remainingMs);
                    String labelText = formatter.format(msBasedDate);
                    html.setHTML(labelText);
                    schedule(500);
                } else {
                    html.setHTML(timesUpLabel);
                    timedStimulusListener.postLoadTimerFired();
                }
            }
        };
        labelTimer.schedule(500);
    }

    protected void row(final TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).startHorizontalPanel();
        timedStimulusListener.postLoadTimerFired();
        ((TimedStimulusView) simpleView).endHorizontalPanel();
    }

    protected void column(final TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).startCell();
        timedStimulusListener.postLoadTimerFired();
        ((TimedStimulusView) simpleView).endCell();
    }

    protected void pause(int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        Timer timer = new Timer() {
            @Override
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

    protected void currentStimulusHasTag(int postLoadMs, final List<Stimulus.Tag> tagList, final TimedStimulusListener hasTagListener, final TimedStimulusListener hasntTagListener) {
// todo: implement randomTags
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
        stimulusLabel(null);
    }

    public void stimulusLabel(String styleName) {
        final String label = stimulusProvider.getCurrentStimulus().getLabel();
        if (label != null) {
            ((TimedStimulusView) simpleView).addHtmlText(label, styleName);
        }
    }

    protected void showStimulus(GroupActivityListener groupActivityListener) {
        final int currentStimulusIndex = stimulusProvider.getCurrentStimulusIndex();
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag() + ((subDirectory != null) ? subDirectory : ""), Integer.toString(currentStimulusIndex));
        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag() + ((subDirectory != null) ? subDirectory : ""), stimulusProvider.getLoadedStimulusString());
        localStorage.storeUserScore(userResults);
        if (stimulusProvider.hasNextStimulus()) {
            stimulusProvider.nextStimulus();
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "NextStimulus", stimulusProvider.getCurrentStimulus().getUniqueId(), duration.elapsedMillis());
//            super.startAudioRecorderTag(STIMULUS_TIER);
            hasMoreStimulusListener.postLoadTimerFired();
//        } else if (!hasSubdirectories) {
//            localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "completed-screen-" + getSelfTag(), Boolean.toString(true));
        } else {
            submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "showStimulus.endOfStimulusListener", stimulusProvider.getCurrentStimulus().getUniqueId() + ":" + stimulusProvider.getCurrentStimulusIndex() + "/" + stimulusProvider.getTotalStimuli(), duration.elapsedMillis()); // todo: this is sent
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

    @Deprecated // @todo: is this really used anymore? -
    protected void removeStimulus() {
        stimulusProvider.nextStimulus();
        //localStorage.setStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()));
    }

    protected void nextMatchingStimulus() {
        matchingStimuliGroup.getNextStimulus(stimulusProvider);
        matchingStimuliGroup.showNextStimulus();
    }

    protected void removeMatchingStimulus(final String matchingRegex) {
        throw new UnsupportedOperationException("todo: removeMatchingStimulus");
    }

    protected void keepStimulus() {
        stimulusProvider.pushCurrentStimulusToEnd();
    }

    protected void groupNetwork(final AppEventListner appEventListner, final ApplicationState selfApplicationState, final String groupMembers, final String groupCommunicationChannels, final int phasesPerStimulus, final TimedStimulusListener timedStimulusListener) {
        if (groupParticipantService == null) {
            final Timer groupKickTimer = new Timer() {
                @Override
                public void run() {
                    groupParticipantService.messageGroup(0, 0, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null, (int) userResults.getUserData().getBestScore(), groupMembers);
                }
            };
            groupParticipantService = new GroupParticipantService(
                    userResults.getUserData().getUserId().toString(),
                    getSelfTag(), groupMembers, groupCommunicationChannels,
                    phasesPerStimulus,
                    stimulusProvider.getLoadedStimulusString(),
                    new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    // do not clear the screen at this point because reconnects when the stimuli list is at the end will need to keep its UI items
                    ((ComplexView) simpleView).clearPage();
                    ((ComplexView) simpleView).addPadding();
                    if (groupParticipantService.isConnected()) {
                        ((ComplexView) simpleView).addText("connected, waiting for other members");
                    } else {
                        ((ComplexView) simpleView).addText("not connected");
                    }
                    timedStimulusListener.postLoadTimerFired();
                    groupKickTimer.schedule(1000);
                }
            }, new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    ((ComplexView) simpleView).clearPage();
                    ((ComplexView) simpleView).addPadding();
//                    ((ComplexView) simpleView).addText("connected: " + groupParticipantService.isConnected());
                    ((ComplexView) simpleView).addHtmlText("Group not ready", "highlightedText");
                    ((ComplexView) simpleView).addPadding();
                    groupKickTimer.schedule(1000);
                }
            }, new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    ((ComplexView) simpleView).addPadding();
                    ((ComplexView) simpleView).addText("synchronising the stimuli");
                    final String stimuliListGroup = groupParticipantService.getStimuliListGroup();
                    // when the stimuli list for this screen does not match that of the group, this listener is fired to: save the group stimuli list and then load the group stimuli list
                    stimulusProvider.loadStoredStimulusList(stimuliListGroup);
                    final String loadedStimulusString = stimulusProvider.getLoadedStimulusString();
                    localStorage.setStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag(), loadedStimulusString);
                    groupParticipantService.setStimuliListLoaded(loadedStimulusString);
                    groupKickTimer.schedule(1000);
                }
            }, new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    if (groupParticipantService.getStimulusIndex() < stimulusProvider.getTotalStimuli()) {
                        if (groupParticipantService.getStimulusIndex() != stimulusProvider.getCurrentStimulusIndex()) {
                            groupParticipantService.setResponseStimulusId(null);
                            groupParticipantService.setResponseStimulusOptions(null);
                        } else {
                            if (groupParticipantService.getMessageString() != null && !groupParticipantService.getMessageString().isEmpty()) {
                                JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus());
                                storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                                storedStimulusJSONObject.put("groupMessage", new JSONString(groupParticipantService.getMessageString()));
                                localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus(), storedStimulusJSONObject);
                            }
                        }
                        // when a valid message has been received the current stimuli needs to be synchronised with the group
                        stimulusProvider.setCurrentStimuliIndex(groupParticipantService.getStimulusIndex());
                    } else {
                        // if the group message puts the stimuli list at the end then fire the end of stimulus listner
                        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "group message puts the stimuli list at the end", stimulusProvider.getCurrentStimulus().getUniqueId() + ":" + stimulusProvider.getCurrentStimulusIndex() + "/" + stimulusProvider.getTotalStimuli(), duration.elapsedMillis());
                        groupParticipantService.setEndOfStimuli(true); // block further messages
                        endOfStimulusListener.postLoadTimerFired();
                    }
                }
            }, new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    ((ComplexView) simpleView).addInfoButton(new PresenterEventListner() {
                        @Override
                        public String getLabel() {
                            final Integer stimulusIndex = groupParticipantService.getStimulusIndex();
                            final String activeChannel = groupParticipantService.getActiveChannel();
                            return groupParticipantService.getMemberCode()
                                    + ((stimulusIndex != null) ? "("
                                            + activeChannel
                                            + ")" : "")
                                    + ((stimulusIndex != null) ? "G"
                                            + (stimulusIndex + 1) : "");
                        }

                        @Override
                        public void eventFired(ButtonBase button, final SingleShotEventListner shotEventListner) {
                            groupParticipantService.messageGroup(0, 0, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null, (int) userResults.getUserData().getBestScore(), groupMembers);
//                            ((ComplexView) simpleView).showHtmlPopup(null,
//                                    "Group Members\n" + groupParticipantService.getAllMemberCodes()
//                                    + "\n\nGroup Communication Channels\n" + groupParticipantService.getGroupCommunicationChannels()
//                                    + "\n\nGroupId\n" + groupParticipantService.getGroupId()
//                                    + "\n\nGroupUUID\n" + groupParticipantService.getGroupUUID()
//                                    + "\n\nMemberCode\n" + groupParticipantService.getMemberCode()
//                                    + "\n\nMessageSender\n" + groupParticipantService.getMessageSenderId()
//                                    + "\n\nMessageString\n" + groupParticipantService.getMessageString()
//                                    + "\n\nStimulusId\n" + groupParticipantService.getStimulusId()
//                                    + "\n\nStimuliListLoaded\n" + groupParticipantService.getStimuliListLoaded()
//                                    + "\n\nStimuliListGroup\n" + groupParticipantService.getStimuliListGroup()
//                                    + "\n\nResponseStimulusOptions\n" + groupParticipantService.getResponseStimulusOptions()
//                                    + "\n\nResponseStimulusId\n" + groupParticipantService.getResponseStimulusId()
//                                    + "\n\nStimulusIndex\n" + groupParticipantService.getStimulusIndex()
//                                    + "\n\nRequestedPhase\n" + groupParticipantService.getRequestedPhase()
//                                    + "\n\nUserLabel\n" + groupParticipantService.getUserLabel()
//                                    + "\n\nGroupReady\n" + groupParticipantService.isGroupReady()
//                            );
                            shotEventListner.resetSingleShot();
                        }

                        @Override
                        public int getHotKey() {
                            return -1;
                        }
                    });
                }
            }
            //                    , endOfStimulusListener
            );
            groupParticipantService.joinGroupNetwork(serviceLocations.groupServerUrl());
        } else {
            timedStimulusListener.postLoadTimerFired();
//            groupParticipantService.messageGroup(0, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null);
//              groupParticipantService.messageGroup(0, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId());
        }
    }

    protected void groupNetworkActivity(final GroupActivityListener timedStimulusListener) {
        groupParticipantService.addGroupActivity(timedStimulusListener);
    }

    public void submitGroupEvent() {
        submissionService.submitGroupEvent(userResults.getUserData().getUserId(),
                groupParticipantService.getGroupUUID(),
                groupParticipantService.getGroupId(),
                groupParticipantService.getAllMemberCodes(),
                groupParticipantService.getGroupCommunicationChannels(),
                getSelfTag(),
                groupParticipantService.getMemberCode(),
                groupParticipantService.getUserLabel(),
                groupParticipantService.getStimulusId(),
                groupParticipantService.getStimulusIndex(),
                groupParticipantService.getMessageSenderId(),
                groupParticipantService.getMessageString(),
                groupParticipantService.getResponseStimulusId(),
                groupParticipantService.getResponseStimulusOptions(),
                groupParticipantService.getMessageSenderId(),
                groupParticipantService.getMessageSenderMemberCode(),
                duration.elapsedMillis());
    }

    protected void scoreAboveThreshold(final int scoreThreshold, final TimedStimulusListener aboveThreshold, final TimedStimulusListener belowThreshold) {
        if (userResults.getUserData().getCurrentScore() >= scoreThreshold) {
            aboveThreshold.postLoadTimerFired();
        } else {
            belowThreshold.postLoadTimerFired();
        }
    }

    protected void bestScoreAboveThreshold(final int scoreThreshold, final TimedStimulusListener aboveThreshold, final TimedStimulusListener belowThreshold) {
        if (userResults.getUserData().getBestScore() >= scoreThreshold) {
            aboveThreshold.postLoadTimerFired();
        } else {
            belowThreshold.postLoadTimerFired();
        }
    }

    protected void resetStimulus(final String stimuliScreenToReset) {
        if (stimuliScreenToReset != null) {
            localStorage.deleteStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + stimuliScreenToReset);
            localStorage.deleteStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + stimuliScreenToReset);
        }
    }

    protected void scoreIncrement(final boolean isCorrect) {
        userResults.getUserData().addPotentialScore(isCorrect);
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "scoreIncrement", userResults.getUserData().getCurrentScore() + "/" + userResults.getUserData().getPotentialScore(), duration.elapsedMillis());
    }

    protected void scoreLabel() {
        scoreLabel(null);
    }

    protected void scoreLabel(String styleName) {
        ((TimedStimulusView) simpleView).addHtmlText("Current Score: " + userResults.getUserData().getCurrentScore() + "/" + userResults.getUserData().getPotentialScore(), styleName);
        ((TimedStimulusView) simpleView).addHtmlText("Best Score: " + userResults.getUserData().getBestScore(), styleName);
    }

    protected void groupChannelScoreLabel() {
        groupChannelScoreLabel(null);
    }

    protected void groupChannelScoreLabel(String styleName) {
        if (groupParticipantService != null) {
            ((TimedStimulusView) simpleView).addHtmlText("Channel Score: " + groupParticipantService.getChannelScore(), styleName);
        }
    }

    protected void groupMessageLabel() {
        groupMessageLabel(null);
    }

    protected void groupMessageLabel(String styleName) {
        ((TimedStimulusView) simpleView).addHtmlText(groupParticipantService.getMessageString(), styleName);
    }

    protected void groupScoreLabel() {
        groupScoreLabel(null);

    }

    protected void groupScoreLabel(String styleName) {
        if (groupParticipantService != null) {
            ((TimedStimulusView) simpleView).addHtmlText("Group Score: " + groupParticipantService.getGroupScore(), styleName);
        }
    }

    protected void groupMemberLabel() {
        groupMemberLabel(null);
    }

    protected void groupMemberLabel(String styleName) {
        ((TimedStimulusView) simpleView).addHtmlText(groupParticipantService.getUserLabel(), styleName);
    }

    protected void groupMemberCodeLabel() {
        groupMemberCodeLabel(null);
    }

    protected void groupMemberCodeLabel(String styleName) {
        ((TimedStimulusView) simpleView).addHtmlText(groupParticipantService.getMemberCode(), styleName);
    }

    protected void groupResponseFeedback(final AppEventListner appEventListner, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener) {
        if (groupParticipantService.getStimulusId().equals(groupParticipantService.getResponseStimulusId())) {
            correctListener.postLoadTimerFired();
        } else {
            incorrectListener.postLoadTimerFired();
        }

    }

    public void stimulusHasResponse(final AppEventListner appEventListner, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener) {
        if (localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus()) != null) {
            correctListener.postLoadTimerFired();
        } else {
            incorrectListener.postLoadTimerFired();
        }
    }

    protected void stimulusMetadataField(MetadataField metadataField) {
        ((TimedStimulusView) simpleView).addHtmlText(metadataField.getFieldLabel());
    }

    protected void stimulusFreeText(String validationRegex, String keyCodeChallenge, String validationChallenge, final String allowedCharCodes, final int hotKey, String styleName) {
        StimulusFreeText stimulusFreeText = ((TimedStimulusView) simpleView).addStimulusFreeText(validationRegex, keyCodeChallenge, validationChallenge, allowedCharCodes, new SingleShotEventListner() {
            @Override
            protected void singleShotFired() {
                for (PresenterEventListner nextButtonEventListner : nextButtonEventListnerList) {
                    // this process is to make sure that group events are submitted and not just call nextStimulus
                    if (nextButtonEventListner.getHotKey() == hotKey) {
                        nextButtonEventListner.eventFired(null, this);
                    } else {
//                    nextStimulus("stimulusFreeTextEnter", false);
                    }
                }
                this.resetSingleShot();
            }
        }, hotKey, styleName);
        stimulusFreeTextList.add(stimulusFreeText);
    }

    protected void stimulusImageCapture(final String captureLabel, int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        final SdCardStimulus currentStimulus = (SdCardStimulus) stimulusProvider.getCurrentStimulus();
        final SdCardImageCapture sdCardImageCapture = new SdCardImageCapture(new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                ((TimedStimulusView) simpleView).clearPage();
                // cause the taken image to be shown
                hasMoreStimulusListener.postLoadTimerFired();
            }
        }, currentStimulus, userResults.getUserData().getUserId(), localStorage);
        if (sdCardImageCapture.hasBeenCaptured()) {
            final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    timedStimulusListener.postLoadTimerFired();
                }
            };
            ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromTrustedString(sdCardImageCapture.getCapturedPath()), percentOfPage, maxHeight, maxWidth, null, null, postLoadMs, shownStimulusListener, timedStimulusListener, null);
        }
        ((TimedStimulusView) simpleView).addOptionButton(new PresenterEventListner() {
            @Override
            public String getLabel() {
                return captureLabel;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                sdCardImageCapture.captureImage();
            }

            @Override
            public int getHotKey() {
                return -1;
            }
        });
    }

    protected void backgroundImage(final String imageSrc, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).addBackgroundImage(UriUtils.fromTrustedString((imageSrc.startsWith("file")) ? imageSrc : serviceLocations.staticFilesUrl() + imageSrc), postLoadMs, timedStimulusListener);
    }

    @Deprecated
    protected void stimulusImage(int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        stimulusImage(percentOfPage, maxHeight, maxWidth, AnimateTypes.none, postLoadMs, timedStimulusListener);
    }

    protected void stimulusImage(int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        stimulusImage(stimulusProvider.getCurrentStimulus(), percentOfPage, maxHeight, maxWidth, animateType, null, postLoadMs, null, null, timedStimulusListener, null);
    }

    protected void stimulusImage(int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, int postLoadMs, String regex, String replacement, final TimedStimulusListener timedStimulusListener) {
        stimulusImage(stimulusProvider.getCurrentStimulus(), percentOfPage, maxHeight, maxWidth, animateType, null, postLoadMs, regex, replacement, timedStimulusListener, null);
    }

    protected void stimulusImage(int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final Integer fixedPositionY, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        stimulusImage(stimulusProvider.getCurrentStimulus(), percentOfPage, maxHeight, maxWidth, animateType, fixedPositionY, postLoadMs, null, null, timedStimulusListener, null);
    }

    protected void stimulusImage(final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final Integer fixedPositionY, int postLoadMs, String regex, String replacement, final TimedStimulusListener timedStimulusListener, final TimedStimulusListener clickedStimulusListener) {
        if (currentStimulus.hasImage()) {
            final String image = currentStimulus.getImage();
            final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    // send image shown tag
                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusImageShown", currentStimulus.getUniqueId(), image, duration.elapsedMillis());
                }
            };
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusImage", image, duration.elapsedMillis());
            final String animateStyle;
            if (animateType == AnimateTypes.stimuliCode) {
                animateStyle = currentStimulus.getCode() + "Animation";
            } else if (animateType != AnimateTypes.none) {
                animateStyle = animateType.name() + "Animation";
            } else {
                animateStyle = null;
            }
            ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromTrustedString(image), percentOfPage, maxHeight, maxWidth, animateStyle, fixedPositionY, postLoadMs, shownStimulusListener, timedStimulusListener, clickedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
        } else if (currentStimulus.hasAudio()) {
            String mp3 = currentStimulus.getAudio() + ".mp3";
            String ogg = currentStimulus.getAudio() + ".ogg";
            final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(ogg);
            final SafeUri mp3TrustedString = (mp3 == null) ? null : UriUtils.fromTrustedString(mp3);
            final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    // send audio shown tag
                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusAudioShown", currentStimulus.getUniqueId(), currentStimulus.getAudio(), duration.elapsedMillis());
                }
            };
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", currentStimulus.getAudio(), duration.elapsedMillis());
            ((TimedStimulusView) simpleView).addTimedAudio(oggTrustedString, mp3TrustedString, postLoadMs, false, shownStimulusListener, timedStimulusListener);
        } else if (currentStimulus.hasVideo()) {
            String ogg = currentStimulus.getVideo() + ".ogg";
            String mp4 = currentStimulus.getVideo() + ".mp4";
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusVideo", currentStimulus.getVideo(), duration.elapsedMillis());
            final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(ogg);
            final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString(mp4);
            final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    // send video shown tag
                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusVideoShown", currentStimulus.getUniqueId(), currentStimulus.getVideo(), duration.elapsedMillis());
                }
            };
            ((TimedStimulusView) simpleView).addTimedVideo(oggTrustedString, mp4TrustedString, percentOfPage, maxHeight, maxWidth, postLoadMs, shownStimulusListener, timedStimulusListener);
        } else if (currentStimulus.getLabel() != null) {
            ((TimedStimulusView) simpleView).addHtmlText(currentStimulus.getLabel(), null);
            // send label shown tag
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusLabelShown", currentStimulus.getUniqueId(), currentStimulus.getLabel(), duration.elapsedMillis());
            timedStimulusListener.postLoadTimerFired();
        } else {
            final String incorrect_stimulus_format = "incorrect stimulus format";
            nextStimulusButton(incorrect_stimulus_format, incorrect_stimulus_format + " " + currentStimulus.getLabel(), true, -1);
        }
    }

    protected void stimulusCodeImage(int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, int postLoadMs, String codeFormat, TimedStimulusListener timedStimulusListener) {
        final String formattedCode = codeFormat.replace("<code>", stimulusProvider.getCurrentStimulus().getCode());
        final String uniqueId = stimulusProvider.getCurrentStimulus().getUniqueId();
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeImage", formattedCode, duration.elapsedMillis());
        final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusCodeImageShown", uniqueId, formattedCode, duration.elapsedMillis());
            }
        };
        ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + formattedCode), percentOfPage, maxHeight, maxWidth, (animateType.equals(AnimateTypes.bounce)) ? "bounceAnimation" : null, null, postLoadMs, shownStimulusListener, timedStimulusListener, null);
//        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
    }

    protected void stimulusCodeAudio(int postLoadMs, String codeFormat, TimedStimulusListener timedStimulusListener) {
        final String formattedCode = codeFormat.replace("<code>", stimulusProvider.getCurrentStimulus().getCode());
        final String uniqueId = stimulusProvider.getCurrentStimulus().getUniqueId();
        String mp3 = formattedCode + ".mp3";
        String ogg = formattedCode + ".ogg";
        final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString((ogg.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + ogg);
        final SafeUri mp3TrustedString = (mp3 == null) ? null : UriUtils.fromTrustedString((mp3.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + mp3);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeAudio", formattedCode, duration.elapsedMillis());
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", formattedCode, duration.elapsedMillis());
        final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusCodeAudioShown", uniqueId, formattedCode, duration.elapsedMillis());
            }
        };
        ((TimedStimulusView) simpleView).addTimedAudio(oggTrustedString, mp3TrustedString, postLoadMs, false, shownStimulusListener, timedStimulusListener);
    }

    protected void stimulusCodeVideo(int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, String codeFormat, TimedStimulusListener timedStimulusListener) {
        final String formattedCode = codeFormat.replace("<code>", stimulusProvider.getCurrentStimulus().getCode());
        final String uniqueId = stimulusProvider.getCurrentStimulus().getUniqueId();
        String mp4 = formattedCode + ".mp4";
        String ogg = formattedCode + ".ogg";
        final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(serviceLocations.staticFilesUrl() + ogg);
        final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString(serviceLocations.staticFilesUrl() + mp4);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeVideo", formattedCode, duration.elapsedMillis());
        final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusCodeVideoShown", uniqueId, formattedCode, duration.elapsedMillis());
            }
        };
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", formattedCode, duration.elapsedMillis());
        ((TimedStimulusView) simpleView).addTimedVideo(oggTrustedString, mp4TrustedString, percentOfPage, maxHeight, maxWidth, postLoadMs, shownStimulusListener, timedStimulusListener);
    }

    protected void stimulusAudio(int postLoadMs, boolean showPlaybackIndicator, TimedStimulusListener timedStimulusListener) {
        final String audio = stimulusProvider.getCurrentStimulus().getAudio();
        final String uniqueId = stimulusProvider.getCurrentStimulus().getUniqueId();
        String ogg = audio + ".ogg";
        String mp3 = audio + ".mp3";
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", ogg, duration.elapsedMillis());
        final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusAudioShown", uniqueId, audio, duration.elapsedMillis());
            }
        };
        ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromTrustedString(ogg), UriUtils.fromTrustedString(mp3), postLoadMs, showPlaybackIndicator, shownStimulusListener, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addText("playStimulusAudio: " + duration.elapsedMillis() + "ms");
    }

    public void stimulusHasRatingOptions(final AppEventListner appEventListner, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener) {
        if (stimulusProvider.getCurrentStimulus().hasRatingLabels()) {
            correctListener.postLoadTimerFired();
        } else {
            incorrectListener.postLoadTimerFired();
        }
    }

    public void stimulusRatingButton(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener, final String ratingLabelLeft, final String ratingLabelRight, final int eventTier) {
        ((ComplexView) simpleView).addRatingButtons(getRatingEventListners(appEventListner, timedStimulusListener, stimulusProvider.getCurrentStimulus().getUniqueId(), stimulusProvider.getCurrentStimulus().getRatingLabels(), stimulusProvider.getCurrentStimulus().getCorrectResponses(), eventTier), ratingLabelLeft, ratingLabelRight, false);
    }

    public void ratingButton(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final int eventTier) {
        ((ComplexView) simpleView).addRatingButtons(getRatingEventListners(appEventListner, timedStimulusListener, stimulusProvider.getCurrentStimulus().getUniqueId(), ratingLabels, null, eventTier), ratingLabelLeft, ratingLabelRight, false);
    }

    public void ratingFooterButton(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final int eventTier) {
        ((ComplexView) simpleView).addRatingButtons(getRatingEventListners(appEventListner, timedStimulusListener, stimulusProvider.getCurrentStimulus().getUniqueId(), ratingLabels, null, eventTier), ratingLabelLeft, ratingLabelRight, true);
    }

    public List<PresenterEventListner> getRatingEventListners(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener, final String stimulusString, final String ratingLabels, final String correctLabels, final int eventTier) {
        ArrayList<PresenterEventListner> eventListners = new ArrayList<>();
        final String[] splitRatingLabels = ratingLabels.split(",");
        final String[] splitCorrectLabels = (correctLabels != null) ? correctLabels.split(",") : new String[]{};
        for (final String ratingItem : splitRatingLabels) {
            int derivedHotKey = -1;
            if (ratingItem.equals("0")) {
                derivedHotKey = KeyCodes.KEY_ZERO;
            } else if (ratingItem.equals("1")) {
                derivedHotKey = KeyCodes.KEY_ONE;
            } else if (ratingItem.equals("2")) {
                derivedHotKey = KeyCodes.KEY_TWO;
            } else if (ratingItem.equals("3")) {
                derivedHotKey = KeyCodes.KEY_THREE;
            } else if (ratingItem.equals("4")) {
                derivedHotKey = KeyCodes.KEY_FOUR;
            } else if (ratingItem.equals("5")) {
                derivedHotKey = KeyCodes.KEY_FIVE;
            } else if (ratingItem.equals("6")) {
                derivedHotKey = KeyCodes.KEY_SIX;
            } else if (ratingItem.equals("7")) {
                derivedHotKey = KeyCodes.KEY_SEVEN;
            } else if (ratingItem.equals("8")) {
                derivedHotKey = KeyCodes.KEY_EIGHT;
            } else if (ratingItem.equals("9")) {
                derivedHotKey = KeyCodes.KEY_NINE;
            } else if (splitRatingLabels.length == 2) {
                // if there are only two options then use z and . as the hot keys
                if (splitRatingLabels[0].equals(ratingItem)) {
                    derivedHotKey = KeyCodes.KEY_Z;
                } else {
                    derivedHotKey = KeyCodes.KEY_NUM_PERIOD;
                }
            }

            final int hotKey = derivedHotKey;
            eventListners.add(new PresenterEventListner() {
                @Override
                public String getLabel() {
                    return ratingItem;
                }

                @Override
                public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                    endAudioRecorderTag(eventTier, ratingItem);
                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "RatingButton", stimulusString, ratingItem, duration.elapsedMillis());
                    boolean itemIsCorrect = Arrays.asList(splitCorrectLabels).contains(ratingItem);
                    if (itemIsCorrect) {
                        userResults.getUserData().addPotentialScore(true);
                    } else if (splitCorrectLabels.length > 0) {
                        userResults.getUserData().addPotentialScore(false);
                    }
                    timedStimulusListener.postLoadTimerFired();
                }

                @Override
                public int getHotKey() {
                    return hotKey;
                }
            });
        }
        return eventListners;
    }

    protected void showCurrentMs() {
//        ((TimedStimulusView) simpleView).addText(duration.elapsedMillis() + "ms");
    }

    protected void logTimeStamp(String eventTag) {
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "CustomTag", stimulusProvider.getCurrentStimulus().getUniqueId(), eventTag, duration.elapsedMillis());
    }

    protected void endAudioRecorderTag(int tier, String tagString) {
        super.endAudioRecorderTag(tier, stimulusProvider.getCurrentStimulus().getUniqueId(), stimulusProvider.getCurrentStimulus().getUniqueId(), tagString);
    }

    @Override
    protected void startAudioRecorderTag(int tier) {
        super.startAudioRecorderTag(tier); //((tier < 1) ? 1 : tier) + 2); //  tier 1 and 2 are reserved for stimulus set loading and stimulus display events
    }

    protected void startAudioRecorder(boolean wavFormat, boolean filePerStimulus, String directoryName) {
//        final String subdirectoryName = userResults.getUserData().getUserId().toString();
        final String subdirectoryName = userResults.getUserData().getMetadataValue(new MetadataFieldProvider().workerIdMetadataField);
        super.startAudioRecorder(true, subdirectoryName, directoryName, (filePerStimulus) ? stimulusProvider.getCurrentStimulus().getUniqueId() : "");
    }

    protected void showStimulusGrid(final AppEventListner appEventListner, final int postLoadCorrectMs, final TimedStimulusListener correctListener, final int postLoadIncorrectMs, final TimedStimulusListener incorrectListener, final int maxStimuli, final int columnCount, final String imageWidth, final AnimateTypes animateType, final String eventTag) {
        ((TimedStimulusView) simpleView).stopAudio();
        TimedStimulusListener correctTimedListener = new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
                Timer timer = new Timer() {
                    @Override
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
                    @Override
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
//        if (alternativeChoice != null) {
//            buttonList.add(((TimedStimulusView) simpleView).addStringItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus(), alternativeChoice, correctTimedListener, incorrectTimedListener), alternativeChoice, 0, 0, imageWidth));
//        }
        for (final Stimulus nextJpgStimulus : stimulusProvider.getPictureList(groupParticipantService, maxStimuli)) {
            final String styleName;
            if (animateType == AnimateTypes.stimuliCode) {
                styleName = nextJpgStimulus.getCode() + "Animation";
            } else if (animateType != AnimateTypes.none) {
                styleName = animateType.name() + "Animation";
            } else {
                styleName = null;
            }
            final ButtonBase imageItem = ((TimedStimulusView) simpleView).addImageItem(getEventListener(buttonList, eventTag, stimulusProvider.getCurrentStimulus(), nextJpgStimulus, correctTimedListener, incorrectTimedListener), UriUtils.fromString(nextJpgStimulus.getImage()), imageCounter / columnCount, 1 + imageCounter++ % columnCount, imageWidth, styleName, imageCounter);
            buttonList.add(imageItem);
        }
        disableStimulusButtons();
        ((TimedStimulusView) simpleView).endGrid();
        //((TimedStimulusView) simpleView).addAudioPlayerGui();
    }

    protected void matchingStimulusGrid(final AppEventListner appEventListner, final int postLoadCorrectMs, final TimedStimulusListener correctListener, final int postLoadIncorrectMs, final TimedStimulusListener incorrectListener, final String matchingRegex, final int maxStimulusCount, final boolean randomise, final int columnCount, int maxWidth, final AnimateTypes animateType) {
        matchingStimuliGroup = new MatchingStimuliGroup(stimulusProvider.getCurrentStimulus(), stimulusProvider.getMatchingStimuli(matchingRegex, maxStimulusCount), randomise, 1, hasMoreStimulusListener, endOfStimulusListener);
        ((TimedStimulusView) simpleView).startHorizontalPanel();
        int ySpacing = (int) (100.0 / (matchingStimuliGroup.getStimulusCount() + 1));
        int yPos = 0;
        while (matchingStimuliGroup.getNextStimulus(stimulusProvider)) {
            yPos += ySpacing;
            if (matchingStimuliGroup.isCorrect(stimulusProvider.getCurrentStimulus())) {
                stimulusImage(stimulusProvider.getCurrentStimulus(), 0, maxWidth, maxWidth, animateType, yPos - (maxWidth / 2), postLoadCorrectMs, null, null, new TimedStimulusListener() {
                    @Override
                    public void postLoadTimerFired() {

                    }
                }, correctListener);
            } else {
                stimulusImage(stimulusProvider.getCurrentStimulus(), 0, maxWidth, maxWidth, animateType, yPos - (maxWidth / 2), postLoadIncorrectMs, null, null, new TimedStimulusListener() {
                    @Override
                    public void postLoadTimerFired() {

                    }
                }, incorrectListener);
            }
        }
        ((TimedStimulusView) simpleView).endHorizontalPanel();
    }

    private PresenterEventListner getEventListener(final ArrayList<ButtonBase> buttonList, final String eventTag, final Stimulus correctStimulusItem, final Stimulus currentStimulusItem, final TimedStimulusListener correctTimedListener, final TimedStimulusListener incorrectTimedListener) {
        final String tagValue1 = correctStimulusItem.getImage();
        final String tagValue2 = currentStimulusItem.getImage();
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
                if (groupParticipantService != null) {
                    groupParticipantService.setResponseStimulusId(currentStimulusItem.getUniqueId());
//                    groupParticipantService.messageGroup(0, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, currentStimulusItem.getUniqueId());
                }
                button.addStyleName("stimulusButtonHighlight");
                // eventTag is set by the user and is different for each state (correct/incorrect).
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, tagValue1, tagValue2, duration.elapsedMillis());
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
        showStimulusProgress(null);
    }

    public void showStimulusProgress(String styleName) {
        ((TimedStimulusView) simpleView).addHtmlText((stimulusProvider.getCurrentStimulusIndex() + 1) + " / " + stimulusProvider.getTotalStimuli(), styleName);
//        ((TimedStimulusView) simpleView).addText("showStimulusProgress: " + duration.elapsedMillis() + "ms");
    }

//    public void popupMessage(final PresenterEventListner presenterListerner, String message) {
//        ((TimedStimulusView) simpleView).showHtmlPopup(presenterListerner, message);
//    }

    /*protected boolean stimulusIndexIn(int[] validIndexes) {
        int currentIndex = stimulusProvider.getTotalStimuli() - stimulusProvider.getRemainingStimuli();
        for (int currentValid : validIndexes) {
            if (currentIndex == currentValid) {
                return true;
            }
        }
        return false;
    }*/
    protected void clearStimulus() {
        ((TimedStimulusView) simpleView).clearPage();
        buttonList.clear();
    }

    protected void nextStimulus(final String eventTag, final boolean repeatIncorrect) {
        if (groupParticipantService != null) {
            ((ComplexView) simpleView).addText("showStimulus should not be used with the groupParticipantService");
            throw new UnsupportedOperationException("showStimulus should not be used with the groupParticipantService");
        }
        for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
            if (!stimulusFreeText.isValid()) {
                return;
            }
        }
        for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "StimulusFreeText", stimulusProvider.getCurrentStimulus().getUniqueId(), stimulusFreeText.getValue(), duration.elapsedMillis());
            final String correctResponses = stimulusProvider.getCurrentStimulus().getCorrectResponses();
            if (correctResponses != null && !correctResponses.isEmpty()) {
                // if there are correct responses to this stimulus then increment the score
                userResults.getUserData().addPotentialScore(stimulusFreeText.getValue().matches(correctResponses));
            }
        }
        if (repeatIncorrect && userResults.getUserData().isCurrentIncorrect()) {
            stimulusProvider.pushCurrentStimulusToEnd();
        }
        userResults.getUserData().clearCurrentResponse();
        ((TimedStimulusView) simpleView).stopAudio();
        ((TimedStimulusView) simpleView).clearPage();
        stimulusFreeTextList.clear();
        buttonList.clear();
        showStimulus(null);
    }

    protected void groupResponseStimulusImage(int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        stimulusImage(stimulusProvider.getStimuliFromString(groupParticipantService.getResponseStimulusId()), percentOfPage, maxHeight, maxWidth, animateType, null, postLoadMs, null, null, timedStimulusListener, null);
    }

    protected void sendGroupEndOfStimuli(final String eventTag) {
        groupParticipantService.messageGroup(groupParticipantService.getRequestedPhase(), 1, null, Integer.toString(stimulusProvider.getCurrentStimulusIndex() + 1), groupParticipantService.getMessageString(), groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getBestScore(), "");
//        showStimulusProgress();
    }

    protected void sendGroupStoredMessage(final String eventTag, final int originPhase, final int incrementPhase, String expectedRespondents) {
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus());
        final JSONValue freeTextValue = (storedStimulusJSONObject == null) ? null : storedStimulusJSONObject.get("groupMessage");
        String messageString = ((freeTextValue != null) ? freeTextValue.isString().stringValue() : null);
        groupParticipantService.messageGroup(originPhase, incrementPhase, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getBestScore(), expectedRespondents);
    }

    protected void sendGroupMessage(final String eventTag, final int originPhase, final int incrementPhase, String expectedRespondents) {
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, (groupParticipantService != null) ? groupParticipantService.getMessageString() : null, duration.elapsedMillis());
        final String uniqueId = (stimulusProvider.getCurrentStimulusIndex() < stimulusProvider.getTotalStimuli()) ? stimulusProvider.getCurrentStimulus().getUniqueId() : null;
        if (groupParticipantService != null) {
            groupParticipantService.messageGroup(originPhase, incrementPhase, uniqueId, Integer.toString(stimulusProvider.getCurrentStimulusIndex()), groupParticipantService.getMessageString(), groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getBestScore(), expectedRespondents);
        }
        ((TimedStimulusView) simpleView).stopAudio();
        ((TimedStimulusView) simpleView).clearPage();
        stimulusFreeTextList.clear();
        buttonList.clear();
        ((TimedStimulusView) simpleView).addText("Waiting for a group response."); // + eventTag + ":" + originPhase + ":" + incrementPhase + ":" + groupParticipantService.getRequestedPhase());
    }

    protected void sendGroupMessageButton(final String eventTag, final String buttonLabel, final boolean norepeat, final int hotKey, final int originPhase, final int incrementPhase, final String expectedRespondents) {
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
                for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
                    if (!stimulusFreeText.isValid()) {
                        return;
                    }
                }
                String messageString = "";
                for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
                    messageString += stimulusFreeText.getValue();
                }
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, (stimulusProvider.getCurrentStimulusIndex() < stimulusProvider.getTotalStimuli()) ? stimulusProvider.getCurrentStimulus().getUniqueId() : null, messageString, duration.elapsedMillis());
                groupParticipantService.messageGroup(originPhase, incrementPhase, stimulusProvider.getCurrentStimulus().getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getBestScore(), expectedRespondents);
                ((TimedStimulusView) simpleView).stopAudio();
                ((TimedStimulusView) simpleView).clearPage();
                stimulusFreeTextList.clear();
                buttonList.clear();
                nextButtonEventListnerList.clear(); // clear this now to prevent refires of the event
            }
        };
        nextButtonEventListnerList.add(eventListner);
        ((TimedStimulusView) simpleView).addOptionButton(eventListner);
    }

    protected void prevStimulusButton(final String eventTag, final String buttonLabel, final boolean repeatIncorrect, final int hotKey) {
        PresenterEventListner eventListner = new PresenterEventListner() {

            @Override
            public String getLabel() {
                return buttonLabel + " (not yet implemented)";
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                nextButtonEventListnerList.clear(); // clear this now to prevent refires of the event
//                prevStimulus(eventTag, repeatIncorrect);
            }
        };
        nextButtonEventListnerList.add(eventListner);
        ((TimedStimulusView) simpleView).addOptionButton(eventListner);
    }

    protected void nextStimulusButton(final String eventTag, final String buttonLabel, final boolean repeatIncorrect, final int hotKey) {
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
                nextButtonEventListnerList.clear(); // clear this now to prevent refires of the event
                nextStimulus(eventTag, repeatIncorrect);
            }
        };
        nextButtonEventListnerList.add(eventListner);
        ((TimedStimulusView) simpleView).addOptionButton(eventListner);
//        }
    }

    protected void endOfStimulusButton(final PresenterEventListner appEventListner, final String eventTag) {
        //logTimeStamp(eventTag);
        if (!stimulusProvider.hasNextStimulus()) {
            ((TimedStimulusView) simpleView).addOptionButton(appEventListner);
        }
    }

    protected void audioButton(final String eventTag, final String mp3Path, final String oggPath, final String imagePath, final TimedStimulusListener audioFinishedListner) {
        ((TimedStimulusView) simpleView).addImageButton(new PresenterEventListner() {
            private boolean hasPlayed = false;

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
                final TimedStimulusListener shownStimulusListener = new TimedStimulusListener() {
                    @Override
                    public void postLoadTimerFired() {
                        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), "PlayAudio", eventTag, mp3Path, duration.elapsedMillis());
                    }
                };
                ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(serviceLocations.staticFilesUrl() + oggPath), UriUtils.fromString(serviceLocations.staticFilesUrl() + mp3Path), 0, false, shownStimulusListener, new TimedStimulusListener() {

                    @Override
                    public void postLoadTimerFired() {
                        if (!hasPlayed) {
                            audioFinishedListner.postLoadTimerFired();
                        }
                        hasPlayed = true;
                    }
                });
            }
        }, UriUtils.fromString(serviceLocations.staticFilesUrl() + imagePath));
    }

    @Override
    public void savePresenterState() {
        ((TimedStimulusView) simpleView).stopAudio();
        super.savePresenterState();
        stopAudioRecorder();
    }
}
