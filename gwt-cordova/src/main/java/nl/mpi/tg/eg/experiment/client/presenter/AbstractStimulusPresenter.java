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

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.media.client.Video;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.typedarrays.shared.Uint8Array;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.CurrentStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener;
import nl.mpi.tg.eg.experiment.client.listener.FrameTimeTrigger;
import nl.mpi.tg.eg.experiment.client.listener.GroupActivityListener;
import nl.mpi.tg.eg.experiment.client.listener.HabituationParadigmListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.listener.TouchInputCapture;
import nl.mpi.tg.eg.experiment.client.listener.TouchInputZone;
import nl.mpi.tg.eg.experiment.client.listener.TriggerListener;
import nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener;
import nl.mpi.tg.eg.experiment.client.model.BooleanToggle;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.SdCardStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.GroupParticipantService;
import nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.MatchingStimuliGroup;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp.DTMF;
import nl.mpi.tg.eg.experiment.client.service.SdCardImageCapture;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.util.AbstractRecorder;
import nl.mpi.tg.eg.experiment.client.util.AudioRecorder;
import nl.mpi.tg.eg.frinex.common.StimuliProvider;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.util.VideoRecorder;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.MetadataFieldWidget;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;
import nl.mpi.tg.eg.frinex.common.model.Stimulus.Tag;
import nl.mpi.tg.eg.frinex.common.model.StimulusSelector;

/**
 * @since Jun 23, 2015 11:36:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractStimulusPresenter extends AbstractTimedPresenter implements Presenter {

    private static final String LOADED_STIMULUS_LIST = "loadedStimulusList";
    private static final String CONSUMED_TAGS_LIST = "consumedTagsList";
    private static final String SEEN_STIMULUS_INDEX = "seenStimulusIndex";
    private final TimedEventMonitor timedEventMonitor;
    final ArrayList<StimulusButton> stimulusButtonList = new ArrayList<>();
    private CurrentStimulusListener hasMoreStimulusListener;
    private TimedStimulusListener endOfStimulusListener;
    final private ArrayList<PresenterEventListener> nextButtonEventListenerList = new ArrayList<>();
    private final ArrayList<StimulusFreeText> stimulusFreeTextList = new ArrayList<>();
    private final HashMap<String, TriggerListener> triggerListeners = new HashMap<>();
    MatchingStimuliGroup matchingStimuliGroup = null;
    private boolean hasSubdirectories = false;
    private TouchInputCapture touchInputCapture = null;
    private final HardwareTimeStamp hardwareTimeStamp; // note that this hardwareTimeStamp instance of HardwareTimeStamp is different from the toneGenerator used in AbstractPresenter although the tone generator objects are shared

    protected enum AnimateTypes {
        bounce, none, stimuliCode
    }

    public enum OrientationType {
        vertical, horizontal, flow
    }

    public AbstractStimulusPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new TimedStimulusView(), submissionService, userResults, localStorage, timerService);
        timedEventMonitor = new TimedEventMonitor(duration);

//        final Label debugLabel = new Label();
//        debugLabel.setStyleName("debugLabel");
//        new Timer() {
//            public void run() {
//                todo: verify that these are cleared correctly: domHandlerArray scaledImagesList
//                final String debugString = "BEL:" + backEventListeners.size() + "PT:" + pauseTimers.size() + "NB:" + nextButtonEventListenerList.size() + "FT:" + stimulusFreeTextList.size() + "TL:" + triggerListeners.size() + "BL:" + stimulusButtonList.size();
//                debugLabel.setText(debugString);
//                timedStimulusView.addWidget(debugLabel);
//                schedule(1000);
//            }
//        }.schedule(1000);
        final String hardwareTimeStampOptions = Window.Location.getParameter("hardwareTimeStamp");
        if (hardwareTimeStampOptions != null) {
            hardwareTimeStamp = new HardwareTimeStamp(hardwareTimeStampOptions, false);
        } else {
            hardwareTimeStamp = null;
        }
    }

    @Override
    public void setState(AppEventListener appEventListener, ApplicationController.ApplicationState prevState, ApplicationController.ApplicationState nextState) {
        super.setState(appEventListener, prevState, null);
        this.nextState = nextState;
    }

    protected void loadSdCardStimulus(final String eventTag,
            final StimulusSelector[] selectionTags, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final CurrentStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener) {
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        //submissionService.submitTimestamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
        //timedEventMonitor.registerEvent(eventTag);
        final String storedStimulusList = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag() + subDirectory);
        int seenStimulusIndextemp;
        try {
            seenStimulusIndextemp = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndextemp = 0;
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
                    loadSdCardStimulus(eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName, stimulusProvider, hasMoreStimulusListener, endOfStimulusListener);
                }
            }
        };
        ArrayList<String> directoryTagArray = new ArrayList<>();
        if (subDirectory == null || subDirectory.isEmpty()) {
            for (StimulusSelector directoryTag : selectionTags) {
                directoryTagArray.add(directoryTag.getTag().name().substring("tag_".length()));
            }
        } else {
            // if a sub directory is passed then only load stimuli from that directory
            directoryTagArray.add(subDirectory);
        }
        final List<String[]> directoryList = new ArrayList<>();
        // @todo: add the limits for maxStimulusCount and maxStimulusPerTag -
        final HtmlTokenFormatter htmlTokenFormatter = new HtmlTokenFormatter(null, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray());
        // todo: this use of setters and getters with the formatter has been done with a very tight time limit and this use case could have also been done with the randomTags if time permitted
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setFormattedIncludeRegex(htmlTokenFormatter.formatString(((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).getAttributeIncludeRegex()));
        stimulusProvider.getSdCardSubset(directoryTagArray, directoryList, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                clearPage();
                if (directoryList.isEmpty()) {
                    showStimulus(stimulusProvider, 0);
                } else {
                    hasSubdirectories = true;
                    for (final String[] directory : directoryList) {
                        final boolean directoryCompleted = Boolean.parseBoolean(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "completed-directory-" + directory[0] + "-" + getSelfTag()));
                        timedStimulusView.addOptionButton(new PresenterEventListener() {
                            @Override
                            public String getLabel() {
                                return directory[1] + ((directoryCompleted) ? " (complete)" : "");
                            }

                            @Override
                            public String getStyleName() {
                                return null;
                            }

                            @Override
                            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                                // show the subdirectorydirectory[0], 
                                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), directory[0]);
                                loadSdCardStimulus(directory[1], selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName, stimulusProvider, hasMoreStimulusListener, new TimedStimulusListener() {
                                    @Override
                                    public void postLoadTimerFired() {
                                        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "completed-directory-" + directory[0] + "-" + getSelfTag(), Boolean.toString(true));
                                        // go back to the initial directory 
                                        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), "");
                                        loadSdCardStimulus(eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName, stimulusProvider, hasMoreStimulusListener, endOfStimulusListener);
                                    }
                                });
                            }

                            @Override
                            public int getHotKey() {
                                return -1;
                            }
                        });
                        timedStimulusView.addPadding();
                    }
                }
            }
        }, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                timedStimulusView.addText("Stimulus loading error");
                // @todo: when sdcard stimuli sub directories are used:  + "Plase make sure that the directory " + stimuliDirectory + "/" + cleanedTag + " exists and has stimuli files."
            }
        }, storedStimulusList, seenStimulusIndex);
    }

    @Override
    protected boolean allowBackAction(final AppEventListener appEventListener) {
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        if (subDirectory != null) {
            if (!subDirectory.isEmpty()) {
                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag(), "");
                setContent(appEventListener);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    protected void clearStimulusResponses(final Tag[] selectionTags, final StimuliProvider stimulusProvider) {
        final List<Stimulus> stimuliMatchingTags = stimulusProvider.getStimuliWithAllTags(Arrays.asList(selectionTags));
        for (Stimulus currentItem : stimuliMatchingTags) {
            localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentItem, new JSONObject());
        }
    }

    protected void clearStimulusResponse(final Stimulus currentStimulus, final String groupId) {
        if (currentStimulus != null) {
            if (groupId == null) {
                localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, new JSONObject());
            } else {
                JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
                storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                storedStimulusJSONObject.put(groupId, null);
                localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, storedStimulusJSONObject);
            }
        }
    }

    protected void withStimuli(String eventTag,
            final StimulusSelector[] selectionTags, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final TimedStimulusListener beforeStimuliListener,
            final CurrentStimulusListener eachStimulusListener,
            final TimedStimulusListener afterStimuliListener
    ) {
        loadStimulus(stimulusProvider, eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName);
        this.hasMoreStimulusListener = null;
        this.endOfStimulusListener = null;
        beforeStimuliListener.postLoadTimerFired();
        timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "withStimuli" : eventTag);
        while (stimulusProvider.hasNextStimulus(0)) {
            eachStimulusListener.postLoadTimerFired(stimulusProvider, stimulusProvider.getCurrentStimulus());
            stimulusProvider.nextStimulus(1);
        }
        afterStimuliListener.postLoadTimerFired();
    }

    protected void groupStimuli(String eventTag,
            final StimulusSelector[] selectionTags, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final CurrentStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener
    ) {
        loadStimulus(eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName, stimulusProvider, hasMoreStimulusListener, endOfStimulusListener);
    }

    protected void loadStimulusPlugin(String eventTag,
            final StimulusSelector[] selectionTags,
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final CurrentStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener
    ) {
        loadStimulus(eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName, stimulusProvider, hasMoreStimulusListener, endOfStimulusListener);
    }

    protected void loadStimulus(String eventTag,
            final StimulusSelector[] selectionTags, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName,
            final StimuliProvider stimulusProvider,
            final CurrentStimulusListener hasMoreStimulusListener,
            final TimedStimulusListener endOfStimulusListener
    ) {
        loadStimulus(stimulusProvider, eventTag, selectionTags, idListField, randomTags, stimuliLists, stimulusAllocationField, consumedTagsGroupName);
        this.hasMoreStimulusListener = hasMoreStimulusListener;
        this.endOfStimulusListener = endOfStimulusListener;
        timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "loadStimulus" : eventTag);
        showStimulus(stimulusProvider, 0);
    }

    private void loadStimulus(
            final StimuliProvider stimulusProvider,
            final String eventTag,
            final StimulusSelector[] selectionTags, // only stimuli with tags in this list can be included
            final MetadataField idListField, // when not null any stimulus with an ID found in this metadata field will be included
            final StimulusSelector[] randomTags,
            final StimulusSelector[] stimuliLists,
            final MetadataField stimulusAllocationField,
            final String consumedTagsGroupName
    ) {
//        timedEventMonitor.registerEvent(eventTag);
        String predefinedStimulusList = null;
        if (stimuliLists.length > 0) {
            final String storedStimulusAllocation = userResults.getUserData().getMetadataValue(stimulusAllocationField);
            if (storedStimulusAllocation != null && !storedStimulusAllocation.isEmpty()) {
                for (StimulusSelector currentListSelector : stimuliLists) {
                    if (storedStimulusAllocation.equals(currentListSelector.getAlias())) {
                        predefinedStimulusList = currentListSelector.getList();
                    }
                }
            }
        } else if (idListField != null) {
            predefinedStimulusList = userResults.getUserData().getMetadataValue(idListField);
            // if predefinedStimulusList is empty then the stimuli loader will ignore it and load all stimuli
            // replace the ","s with - and make sure the "-" is not repeated and is found on each end of the string
            predefinedStimulusList = predefinedStimulusList.replaceAll("[\\,]+", "-");
            predefinedStimulusList = (predefinedStimulusList.isEmpty()) ? "-" : predefinedStimulusList;
            predefinedStimulusList = ((predefinedStimulusList.startsWith("-")) ? "" : "-") + predefinedStimulusList + ((predefinedStimulusList.endsWith("-")) ? "" : "-");
        }
        final String storedStimulusList = (predefinedStimulusList != null) ? predefinedStimulusList : localStorage.getStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag());
        int seenStimulusIndex;
        try {
            seenStimulusIndex = Integer.parseInt(localStorage.getStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag()));
        } catch (NumberFormatException exception) {
            seenStimulusIndex = 0;
        }
        final List<Stimulus.Tag> allocatedTags = new ArrayList<>();
//        final List<StimulusSelector> allocatedTags = new ArrayList<>(Arrays.asList(selectionTags));
        for (StimulusSelector selector : selectionTags) {
            allocatedTags.add(selector.getTag());
        }
        if (randomTags.length > 0) {
            final String storedStimulusAllocation = userResults.getUserData().getMetadataValue(stimulusAllocationField);
            if (storedStimulusAllocation != null && !storedStimulusAllocation.isEmpty()) {
                for (StimulusSelector selector : randomTags) {
                    if (storedStimulusAllocation.equals(selector.getAlias())) {
                        allocatedTags.add(selector.getTag());
                    }
                }
            } else {
                final List<StimulusSelector> randomTagsList = new ArrayList();
                String consumedTagsGroupString = (consumedTagsGroupName != null) ? localStorage.getStoredDataValue(userResults.getUserData().getUserId(), CONSUMED_TAGS_LIST + consumedTagsGroupName) : "";
                for (StimulusSelector currentSelector : randomTags) {
                    if (!consumedTagsGroupString.contains("-" + currentSelector.getAlias() + "-")) {
                        randomTagsList.add(currentSelector);
                    }
                }
                StimulusSelector stimulusAllocation = randomTagsList.get(new Random().nextInt(randomTagsList.size()));
                if (consumedTagsGroupName != null) {
                    localStorage.appendStoredDataValue(userResults.getUserData().getUserId(), CONSUMED_TAGS_LIST + consumedTagsGroupName, "-" + stimulusAllocation.getAlias() + "-");
                }
                userResults.getUserData().setMetadataValue(stimulusAllocationField, stimulusAllocation.getAlias());
                localStorage.storeData(userResults, metadataFieldProvider);
                allocatedTags.add(stimulusAllocation.getTag());
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
        stimulusProvider.getSubset(allocatedTags, storedStimulusList, seenStimulusIndex);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, stimulusProvider.generateStimuliStateSnapshot(), duration.elapsedMillis());
    }

    protected void sendStimuliReport(final StimuliProvider stimulusProvider, String reportType, final int dataChannel) {
        final Map<String, String> stimuliReport = stimulusProvider.getStimuliReport(reportType);
        for (String keyString : stimuliReport.keySet()) {
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, reportType, keyString, stimuliReport.get(keyString), duration.elapsedMillis());
        }
    }

    protected void showStimuliReport(final StimuliProvider stimulusProvider) {
        timedStimulusView.addHtmlText(stimulusProvider.getHtmlStimuliReport(), null);
    }

    protected void withMatchingStimulus(String eventTag, final String matchingRegex, final StimuliProvider stimulusProvider, final CurrentStimulusListener hasMoreStimulusListener, final TimedStimulusListener endOfStimulusListener) {
        matchingStimuliGroup = new MatchingStimuliGroup(stimulusProvider.getCurrentStimulus(), stimulusProvider.getMatchingStimuli(matchingRegex), true, hasMoreStimulusListener, endOfStimulusListener);
        matchingStimuliGroup.getNextStimulus(stimulusProvider);
        matchingStimuliGroup.showNextStimulus(stimulusProvider);
    }

    protected void countdownLabel(final String timesUpLabel, final int postLoadMs, final String msLabelFormat, final TimedStimulusListener timedStimulusListener) {
        countdownLabel(timesUpLabel, null, postLoadMs, msLabelFormat, timedStimulusListener);
    }

    protected void countdownLabel(final String timesUpLabel, final String styleName, final int postLoadMs, final String msLabelFormat, final TimedStimulusListener timedStimulusListener) {
        final Duration labelDuration = new Duration();
        final DateTimeFormat formatter = DateTimeFormat.getFormat(msLabelFormat);
        final HTML html = timedStimulusView.addHtmlText(formatter.format(new Date((long) postLoadMs)), styleName);
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

    protected void stimulusPause(final Stimulus currentStimulus, final TimedStimulusListener timedStimulusListener) {
        pause(currentStimulus.getPauseMs(), timedStimulusListener);
    }

    protected void currentStimulusHasTag(final Stimulus.Tag[] tagList, final Stimulus currentStimulus, final TimedStimulusListener hasTagListener, final TimedStimulusListener hasntTagListener) {
// todo: implement randomTags
//        List<Stimulus.Tag> editableList = new LinkedList<Stimulus.Tag>(tagList);
//        editableList.retainAll();
//        if (editableList.isEmpty()) {
        if (currentStimulus.getTags().containsAll(Arrays.asList(tagList))) {
            hasTagListener.postLoadTimerFired();
        } else {
            hasntTagListener.postLoadTimerFired();
        }
    }

    public void stimulusLabel(final Stimulus currentStimulus) {
        stimulusLabel(currentStimulus, null);
    }

    public void stimulusLabel(final Stimulus currentStimulus, String styleName) {
        final String label = currentStimulus.getLabel();
        if (label != null) {
            HTML html = timedStimulusView.addHtmlText(label, styleName);
            timedEventMonitor.addVisibilityListener(widgetTag.getElement(), html.getElement(), "stimulusLabel");
        }
    }

    protected void showStimulus(final StimuliProvider stimulusProvider) {
        showStimulus(stimulusProvider, 0);
    }

    protected void showStimulus(final StimuliProvider stimulusProvider, final int increment) {
        final int currentStimulusIndex = stimulusProvider.getCurrentStimulusIndex();
        final String subDirectory = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "sdcard-directory-" + getSelfTag());
        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag() + ((subDirectory != null) ? subDirectory : ""), Integer.toString(currentStimulusIndex + increment));
        localStorage.setStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag() + ((subDirectory != null) ? subDirectory : ""), stimulusProvider.generateStimuliStateSnapshot());
        localStorage.storeUserScore(userResults);
        if (stimulusProvider.hasNextStimulus(increment)) {
            stimulusProvider.nextStimulus(increment);
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "NextStimulus", stimulusProvider.getCurrentStimulus().getUniqueId(), duration.elapsedMillis());
//            super.startAudioRecorderTag(STIMULUS_TIER);
            hasMoreStimulusListener.postLoadTimerFired(stimulusProvider, stimulusProvider.getCurrentStimulus());
//        } else if (!hasSubdirectories) {
        } else {
            localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "completed-screen-" + getSelfTag(), Boolean.toString(true));
            submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "showStimulus.endOfStimulusListener", (currentStimulusIndex + increment) + "/" + stimulusProvider.getTotalStimuli(), duration.elapsedMillis()); // todo: this is sent
            localStorage.setStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag() + ((subDirectory != null) ? subDirectory : ""), stimulusProvider.generateStimuliStateSnapshot());
            if (groupParticipantService != null && !groupParticipantService.isEndOfStimuli()) {
                // nothing to do, the group service will send us back here when the group is ready
            } else {
                endOfStimulusListener.postLoadTimerFired();
            }
        }
    }
//    private static final int STIMULUS_TIER = 2;

//    @Deprecated // @todo: is this really used anymore? -
//    protected void removeStimulus(final StimuliProvider stimulusProvider, final Stimulus currentStimulus) {
//        stimulusProvider.nextStimulus(1);
//        //localStorage.setStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + getSelfTag(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()));
//    }
    protected void nextMatchingStimulus(final StimuliProvider stimulusProvider) {
        matchingStimuliGroup.getNextStimulus(stimulusProvider);
        matchingStimuliGroup.showNextStimulus(stimulusProvider);
    }

    protected void removeMatchingStimulus(final String matchingRegex) {
        throw new UnsupportedOperationException("todo: removeMatchingStimulus");
    }

    protected void keepStimulus(final StimuliProvider stimulusProvider, final Stimulus currentStimulus) {
        stimulusProvider.pushCurrentStimulusToEnd();
    }

    protected void groupNetwork(final AppEventListener appEventListener, final ApplicationState selfApplicationState, final StimuliProvider stimulusProvider, final String groupMembers, final String groupCommunicationChannels, final boolean containsStreamingMedia, final int phasesPerStimulus, final TimedStimulusListener groupInitialisationError, final TimedStimulusListener groupFindingMembers, final TimedStimulusListener groupNetworkConnecting, final TimedStimulusListener groupNetworkSynchronising, final TimedStimulusListener endOfStimulusGroupMessage) {
        if (groupStreamHandler == null && containsStreamingMedia) {
            groupStreamHandler = new GroupStreamHandler() {
                @Override
                public void addCanvasElement(String elementId, String groupId, String groupUUID, String memberCode) {
                    simpleView.clearRegion(elementId + "Region");
                    final InsertPanel.ForIsWidget groupLocalCanvasRegion = simpleView.startRegion(elementId + "Region", null);
                    final Canvas groupLocalCanvas = Canvas.createIfSupported();
                    if (groupLocalCanvas != null) {
                        groupLocalCanvas.addAttachHandler(new AttachEvent.Handler() {
                            @Override
                            public void onAttachOrDetach(AttachEvent event) {
                                if (!event.isAttached()) {
                                    notifyDetatchedElement(elementId, null, userResults.getUserData().getUserId().toString(), groupId, groupUUID, memberCode, getSelfTag());
                                }
                            }
                        });
                        groupLocalCanvas.getCanvasElement().setId(elementId);
                        groupLocalCanvas.setSize("30vw", "30vw");
                        simpleView.addWidget(groupLocalCanvas);
                    } else {
                        // TODO: add error handling and remove this html text 
                        timedStimulusView.addText("Canvas is not supported");
                    }
                    simpleView.endRegion(groupLocalCanvasRegion);
                }

                @Override
                public void addVideoElement(String elementId, String groupId, String groupUUID, String memberCode) {
                    simpleView.clearRegion(elementId + "Region");
                    final InsertPanel.ForIsWidget groupLocalVideoRegion = simpleView.startRegion(elementId + "Region", null);
                    final Video groupLocalVideo = Video.createIfSupported();
                    if (groupLocalVideo != null) {
                        groupLocalVideo.addAttachHandler(new AttachEvent.Handler() {
                            @Override
                            public void onAttachOrDetach(AttachEvent event) {
                                if (!event.isAttached()) {
                                    notifyDetatchedElement(elementId, null, userResults.getUserData().getUserId().toString(), groupId, groupUUID, memberCode, getSelfTag());
                                }
                            }
                        });
                        groupLocalVideo.getVideoElement().setId(elementId);
                        groupLocalVideo.setSize("30vw", "30vw");
                        groupLocalVideo.setAutoplay(true);
                        groupLocalVideo.setMuted(true);
                        simpleView.addWidget(groupLocalVideo);
                    } else {
                        // TODO: add error handling and remove this html text 
                        timedStimulusView.addText("Video is not supported");
                    }
                    simpleView.endRegion(groupLocalVideoRegion);
                }
            };
        }
        if (groupParticipantService == null) {
            groupParticipantService = new GroupParticipantService(
                    userResults.getUserData().getUserId().toString(),
                    getSelfTag(), groupMembers, groupCommunicationChannels,
                    phasesPerStimulus,
                    stimulusProvider.generateStimuliStateSnapshot()
            //                    , endOfStimulusListener
            ) {
                TimedStimulusListener lastTriggeredListener = null;

                @Override
                public void groupInitialisationError() {
                    if (!groupInitialisationError.equals(lastTriggeredListener)) {
                        groupInitialisationError.postLoadTimerFired();
                        lastTriggeredListener = groupInitialisationError;
                    }
                }

                @Override
                public void groupNetworkConnecting() {
                    // do not clear the screen at this point because reconnects when the stimuli list is at the end will need to keep its UI items
//                    clearPage();
                    lastTriggeredListener = null;
                    groupNetworkConnecting.postLoadTimerFired();
//                    ((ComplexView) simpleView).addText("not connected");
                    // this endOfStimulusGroupMessage is perhaps a stray and should be "solved"
                    endOfStimulusGroupMessage.postLoadTimerFired();
                    groupParticipantService.messageGroup(0, 0, stimulusProvider.getCurrentStimulusUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null, (int) userResults.getUserData().getCurrentScore(), groupMembers);

                }

                @Override
                public void groupFindingMembers() {
                    // do not clear the screen at this point because reconnects when the stimuli list is at the end will need to keep its UI items
//                    clearPage();
                    lastTriggeredListener = null;
                    groupFindingMembers.postLoadTimerFired();
                    //((ComplexView) simpleView).addText("connected, waiting for other members");
                    // this endOfStimulusGroupMessage is perhaps a stray and should be "solved"
                    endOfStimulusGroupMessage.postLoadTimerFired();
                }

                @Override
                public String synchroniseStimulusList(final String stimuliListGroup) {
//                    ((ComplexView) simpleView).addPadding();
//                    ((ComplexView) simpleView).addText("synchronising the stimuli");
//                        final String stimuliListGroup = groupParticipantService.getStimuliListGroup();
                    // when the stimuli list for this screen does not match that of the group, this listener is fired to: save the group stimuli list and then load the group stimuli list
                    stimulusProvider.initialiseStimuliState(stimuliListGroup);
                    final String loadedStimulusString = stimulusProvider.generateStimuliStateSnapshot();
                    localStorage.setStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + getSelfTag(), loadedStimulusString);
//                    groupParticipantService.setStimuliListLoaded(loadedStimulusString);
                    lastTriggeredListener = null;
                    groupNetworkSynchronising.postLoadTimerFired();
                    return loadedStimulusString;
                }

                @Override
                public Stimulus synchroniseCurrentStimulus(final int currentIndex) {
                    // when a valid message has been received the current stimuli needs to be synchronised with the group
                    stimulusProvider.setCurrentStimuliIndex(currentIndex);
                    if (currentIndex < /* todo: we can rely on the showStimulus to handle this  end of stimulus check */ stimulusProvider.getTotalStimuli()) {
                        if (currentIndex != stimulusProvider.getCurrentStimulusIndex()) {
                            groupParticipantService.setResponseStimulusId(null);
                            groupParticipantService.setResponseStimulusOptions(null);
                        } else if (groupParticipantService.getMessageString() != null && !groupParticipantService.getMessageString().isEmpty()) {
                            JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus());
                            storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                            storedStimulusJSONObject.put("groupMessage", new JSONString(groupParticipantService.getMessageString()));
                            localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), stimulusProvider.getCurrentStimulus(), storedStimulusJSONObject);
                            // submissionService.writeJsonData would be called on next stimulus anyway: submissionService.writeJsonData(userResults.getUserData().getUserId().toString(), currentStimulus.getUniqueId(), storedStimulusJSONObject.toString());
                        }
                    } else {
                        // if the group message puts the stimuli list at the end then fire the end of stimulus listener
                        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "group message puts the stimuli list at the end", stimulusProvider.getCurrentStimulusUniqueId() + ":" + stimulusProvider.getCurrentStimulusIndex() + "/" + stimulusProvider.getTotalStimuli(), duration.elapsedMillis());
                        groupParticipantService.setEndOfStimuli(true); // block further messages
                    }
                    showStimulus(stimulusProvider, 0/*stimulusProvider.getCurrentStimulusIndex()*/);
                    return stimulusProvider.getCurrentStimulus();
                }

                @Override
                public void groupInfoChanged() {
                    ((ComplexView) simpleView).addInfoButton(new PresenterEventListener() {
                        @Override
                        public String getLabel() {
//                            final Integer stimulusIndex = groupParticipantService.getStimulusIndex();
//                            final String activeChannel = groupParticipantService.getActiveChannel();
                            return groupParticipantService.getMemberCode() //                                    + ((stimulusIndex != null) ? "("
                                    //                                            + activeChannel
                                    //                                            + ")" : "")
                                    //                                    + ((stimulusIndex != null) ? "-T"
                                    //                                            + (stimulusIndex + 1) : "")
                                    ;
                        }

                        @Override
                        public void eventFired(ButtonBase button, final SingleShotEventListener shotEventListener) {
                            groupParticipantService.messageGroup(0, 0, stimulusProvider.getCurrentStimulusUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null, (int) userResults.getUserData().getCurrentScore(), groupMembers);
//                            ((ComplexView) simpleView).addHtmlText(
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
//                                    + "\n\nGroupReady\n" + groupParticipantService.isGroupReady(),
//                                    null
//                            );
                            shotEventListener.resetSingleShot();
                        }

                        @Override
                        public String getStyleName() {
                            return null;
                        }

                        @Override
                        public int getHotKey() {
                            return -1;
                        }
                    });
                }

                @Override
                public void synchroniseStreamingPhase(int currentPhase, String groupId, String groupUUID, String memberCode) {
                    if (groupStreamHandler != null) {
                        if (!groupStreamHandler.isConnected()) {
                            // TODO: remove the debug output when the GroupStreamHandler is ready
                            timedStimulusView.addHtmlText("Connect STUN_SERVER " + ApplicationController.STUN_SERVER, "groupStreamContainer");
                            groupStreamHandler.connect(ApplicationController.STUN_SERVER, currentPhase, userResults.getUserData().getUserId().toString(), groupId, groupUUID, memberCode, getSelfTag());
                        }
                        groupStreamHandler.synchronisePhase(currentPhase);
                    }
                }
            };
            groupParticipantService.joinGroupNetwork(serviceLocations.groupServerUrl());
        } else {
            // todo: should this endOfStimulusGroupMessage exist?
            endOfStimulusGroupMessage.postLoadTimerFired();
//            groupParticipantService.messageGroup(0, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, null);
//              groupParticipantService.messageGroup(0, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId());
        }
    }

    protected void groupMemberActivity(final GroupActivityListener timedStimulusListener) {
        groupParticipantService.addGroupActivity(timedStimulusListener);
    }

    public void submitGroupEvent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus) {
        submissionService.submitGroupEvent(userResults.getUserData().getUserId(),
                groupParticipantService.getGroupUUID(),
                groupParticipantService.getGroupId(),
                groupParticipantService.getAllMemberCodes(),
                groupParticipantService.getGroupCommunicationChannels(),
                getSelfTag(),
                groupParticipantService.getMemberCode(),
                groupParticipantService.getUserLabel(),
                groupParticipantService.getStimulusId(),
                stimulusProvider.getCurrentStimulusIndex(),
                groupParticipantService.getMessageSenderId(),
                groupParticipantService.getMessageString(),
                groupParticipantService.getResponseStimulusId(),
                groupParticipantService.getResponseStimulusOptions(),
                groupParticipantService.getMessageSenderId(),
                groupParticipantService.getMessageSenderMemberCode(),
                duration.elapsedMillis());
    }

    protected void scoreAboveThreshold(final Integer scoreThreshold, final Integer errorThreshold, final Integer potentialThreshold, final Integer gamesPlayed, final Integer correctStreak, final Integer errorStreak, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) {
        boolean isWithin = true;
        if (scoreThreshold != null) {
            if (userResults.getUserData().getCurrentScore() > scoreThreshold) {
                isWithin = false;
            }
        }
        if (errorThreshold != null) {
            if (userResults.getUserData().getPotentialScore() - userResults.getUserData().getCurrentScore() > errorThreshold) {
                isWithin = false;
            }
        }
        if (potentialThreshold != null) {
            if (userResults.getUserData().getPotentialScore() > potentialThreshold) {
                isWithin = false;
            }
        }
        if (gamesPlayed != null) {
            if (userResults.getUserData().getGamesPlayed() > gamesPlayed) {
                isWithin = false;
            }
        }
        if (correctStreak != null) {
            if (userResults.getUserData().getCorrectStreak() > correctStreak) {
                isWithin = false;
            }
        }
        if (errorStreak != null) {
            if (userResults.getUserData().getErrorStreak() > errorStreak) {
                isWithin = false;
            }
        }
        if (isWithin) {
            withinThreshold.postLoadTimerFired();
        } else {
            aboveThreshold.postLoadTimerFired();
        }
    }

    protected void bestScoreAboveThreshold(final Integer scoreThreshold, final Integer errorThreshold, final Integer potentialThreshold, final Integer gamesPlayed, final Integer correctStreak, final Integer errorStreak, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) {
        boolean isWithin = true;
        if (scoreThreshold != null) {
            if (userResults.getUserData().getMaxScore() > scoreThreshold) {
                isWithin = false;
            }
        }
        if (errorThreshold != null) {
            if (userResults.getUserData().getMaxErrors() > errorThreshold) {
                isWithin = false;
            }
        }
        if (potentialThreshold != null) {
            if (userResults.getUserData().getMaxPotentialScore() > potentialThreshold) {
                isWithin = false;
            }
        }
        if (gamesPlayed != null) {
            if (userResults.getUserData().getGamesPlayed() > gamesPlayed) {
                isWithin = false;
            }
        }
        if (correctStreak != null) {
            if (userResults.getUserData().getMaxCorrectStreak() > correctStreak) {
                isWithin = false;
            }
        }
        if (errorStreak != null) {
            if (userResults.getUserData().getMaxErrorStreak() > errorStreak) {
                isWithin = false;
            }
        }
        if (isWithin) {
            withinThreshold.postLoadTimerFired();
        } else {
            aboveThreshold.postLoadTimerFired();
        }
    }

    protected void totalScoreAboveThreshold(final Integer scoreThreshold, final Integer errorThreshold, final Integer potentialThreshold, final Integer gamesPlayed, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) {
        boolean isWithin = true;
        if (scoreThreshold != null) {
            if (userResults.getUserData().getTotalScore() > scoreThreshold) {
                isWithin = false;
            }
        }
        if (errorThreshold != null) {
            if (userResults.getUserData().getTotalPotentialScore() - userResults.getUserData().getTotalScore() > errorThreshold) {
                isWithin = false;
            }
        }
        if (potentialThreshold != null) {
            if (userResults.getUserData().getTotalPotentialScore() > potentialThreshold) {
                isWithin = false;
            }
        }
        if (gamesPlayed != null) {
            if (userResults.getUserData().getGamesPlayed() > gamesPlayed) {
                isWithin = false;
            }
        }
        if (isWithin) {
            withinThreshold.postLoadTimerFired();
        } else {
            aboveThreshold.postLoadTimerFired();
        }
    }

    protected void resetStimulus(final String stimuliScreenToReset) {
        if (stimuliScreenToReset != null) {
            localStorage.deleteStoredDataValue(userResults.getUserData().getUserId(), LOADED_STIMULUS_LIST + stimuliScreenToReset);
            localStorage.deleteStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_INDEX + stimuliScreenToReset);
        }
    }

    protected void clearCurrentScore(final Stimulus currentStimulus, final String scoreGroupTokens, final int dataChannel) {
        if (userResults.getUserData().getPotentialScore() > 0) {
            userResults.getUserData().addGamePlayed();
        }
        final String formattedScoreGroup = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(scoreGroupTokens);
        userResults.getUserData().clearCurrentScore(formattedScoreGroup);
        localStorage.storeUserScore(userResults);
        submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), dataChannel, "clearCurrentScore", currentStimulus, null, null, duration.elapsedMillis());
    }

    protected void scoreIncrement(final Stimulus currentStimulus, final int dataChannel, final int scoreValue) {
        userResults.getUserData().addPotentialScore(scoreValue);
        localStorage.storeUserScore(userResults);
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "scoreIncrement", userResults.getUserData().getCurrentScore() + "/" + userResults.getUserData().getPotentialScore(), duration.elapsedMillis());
        submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), dataChannel, "scoreIncrement", currentStimulus, null, null, duration.elapsedMillis());
    }

    protected void scoreLabel() {
        scoreLabel(null);
    }

    protected void scoreLabel(String styleName) {
        timedStimulusView.addHtmlText("Current Score: " + userResults.getUserData().getCurrentScore() + "/" + userResults.getUserData().getPotentialScore(), styleName);
        timedStimulusView.addHtmlText("Best Score: " + userResults.getUserData().getMaxScore(), styleName);
    }

    protected void groupChannelScoreLabel() {
        groupChannelScoreLabel(null);
    }

    protected void groupChannelScoreLabel(String styleName) {
        if (groupParticipantService != null) {
            timedStimulusView.addHtmlText("Channel Score: " + groupParticipantService.getChannelScore(), styleName);
        }
    }

    protected void groupMessageLabel() {
        groupMessageLabel(null);
    }

    protected void groupMessageLabel(String styleName) {
        timedStimulusView.addHtmlText(groupParticipantService.getMessageString(), styleName);
    }

    protected void groupScoreLabel() {
        groupScoreLabel(null);

    }

    protected void groupScoreLabel(String styleName) {
        if (groupParticipantService != null) {
            timedStimulusView.addHtmlText("Group Score: " + groupParticipantService.getGroupScore(), styleName);
        }
    }

    protected void groupMemberLabel() {
        groupMemberLabel(null);
    }

    protected void groupMemberLabel(String styleName) {
        timedStimulusView.addHtmlText(groupParticipantService.getUserLabel(), styleName);
    }

    protected void groupMemberCodeLabel() {
        groupMemberCodeLabel(null);
    }

    protected void groupMemberCodeLabel(String styleName) {
        timedStimulusView.addHtmlText(groupParticipantService.getMemberCode(), styleName);
    }

    protected void groupResponseFeedback(final AppEventListener appEventListener, int postLoadMs1, final TimedStimulusListener correctListener, int postLoadMs2, final TimedStimulusListener incorrectListener) {
        // todo: make use of the postLoadMs 
        groupResponseFeedback(appEventListener, correctListener, incorrectListener);
    }

    protected void groupResponseFeedback(final AppEventListener appEventListener, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener) {
        if (groupParticipantService.getStimulusId().equals(groupParticipantService.getResponseStimulusId())) {
            correctListener.postLoadTimerFired();
        } else {
            incorrectListener.postLoadTimerFired();
        }

    }

    public void stimulusHasResponse(final AppEventListener appEventListener, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener, final String groupId, final String matchingRegex) {
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
        if (storedStimulusJSONObject != null) {
            if (groupId == null || groupId.isEmpty()) {
                if (storedStimulusJSONObject.size() > 0) {
                    correctListener.postLoadTimerFired();
                } else {
                    incorrectListener.postLoadTimerFired();
                }
            } else {
                final String formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(groupId);
                String fieldValue = storedStimulusJSONObject.containsKey(formattedGroupId) ? storedStimulusJSONObject.get(formattedGroupId).isString().stringValue() : "";
                if (fieldValue.matches(matchingRegex)) {
                    correctListener.postLoadTimerFired();
                } else {
                    incorrectListener.postLoadTimerFired();
                }
            }
        } else {
            incorrectListener.postLoadTimerFired();
        }
    }

    protected void stimulusMetadataField(final Stimulus currentStimulus, MetadataField metadataField, final int dataChannel) {
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
        final String fieldValue;
        if (storedStimulusJSONObject != null) {
            fieldValue = storedStimulusJSONObject.containsKey(metadataField.getPostName()) ? storedStimulusJSONObject.get(metadataField.getPostName()).isString().stringValue() : "";
        } else {
            fieldValue = "";
        }
        final MetadataFieldWidget metadataFieldWidget = new MetadataFieldWidget(metadataField, currentStimulus, fieldValue, dataChannel);
        timedStimulusView.addWidget(metadataFieldWidget.getLabel());
        timedStimulusView.addWidget(metadataFieldWidget.getWidget());
        stimulusFreeTextList.add(addButtonToGroup(metadataField.getPostName(), metadataFieldWidget));
    }

    protected void stimulusFreeText(final Stimulus currentStimulus, String validationRegex, String keyCodeChallenge, String validationChallenge, final String allowedCharCodes, final int hotKey, String styleName, final String buttonGroup, final int dataChannel) {
        String validationRegexFormatted = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(validationRegex);
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
        final String postName = (buttonGroup != null && !buttonGroup.isEmpty()) ? buttonGroup : "freeText";
        final JSONValue freeTextValue = (storedStimulusJSONObject == null) ? null : storedStimulusJSONObject.get(postName);
        StimulusFreeText stimulusFreeText = timedStimulusView.addStimulusFreeText(currentStimulus, postName, validationRegexFormatted, keyCodeChallenge, validationChallenge, allowedCharCodes, new SingleShotEventListener() {
            @Override
            protected void singleShotFired() {
                for (PresenterEventListener nextButtonEventListener : nextButtonEventListenerList) {
                    // this process is to make sure that group events are submitted and not just call nextStimulus
                    if (nextButtonEventListener.getHotKey() == hotKey) {
                        nextButtonEventListener.eventFired(null, this);
                    } else {
//                    nextStimulus("stimulusFreeTextEnter", false);
                    }
                }
                this.resetSingleShot();
            }
        }, hotKey, styleName, dataChannel, ((freeTextValue != null) ? freeTextValue.isString().stringValue() : null));
        stimulusFreeTextList.add(addButtonToGroup(buttonGroup, stimulusFreeText));
    }

    protected void stimulusImageCapture(final StimuliProvider stimulusProvider, final Stimulus currentStimulusO, final String captureLabel, int percentOfPage, int maxHeight, int maxWidth, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        final SdCardStimulus currentStimulus = (SdCardStimulus) currentStimulusO;
        final SdCardImageCapture sdCardImageCapture = new SdCardImageCapture(new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                clearPage();
                // cause the taken image to be shown
                hasMoreStimulusListener.postLoadTimerFired(stimulusProvider, currentStimulusO);
            }
        }, currentStimulus, userResults.getUserData().getUserId(), localStorage);
        if (sdCardImageCapture.hasBeenCaptured()) {
            final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
                @Override
                protected void trigggerCancelableEvent() {
                    timedStimulusListener.postLoadTimerFired();
                }
            };
            timedStimulusView.addTimedImage(timedEventMonitor, UriUtils.fromTrustedString(sdCardImageCapture.getCapturedPath()), percentOfPage, maxHeight, maxWidth, null, null, postLoadMs, shownStimulusListener, shownStimulusListener, null, null);
        }
        timedStimulusView.addOptionButton(new PresenterEventListener() {
            @Override
            public String getLabel() {
                return captureLabel;
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                sdCardImageCapture.captureImage();
            }

            @Override
            public int getHotKey() {
                return -1;
            }
        });
    }

    protected void stimulusImage(final Stimulus currentStimulus, final String styleName, int postLoadMs, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        final String imageString = currentStimulus.getImage();
        timedStimulusView.addTimedImage(timedEventMonitor, UriUtils.fromString(imageString), styleName, postLoadMs, loadedStimulusListener, failedStimulusListener, null);
    }

    @Deprecated
    protected void stimulusPresent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final boolean showControls, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        stimulusPresent(stimulusProvider, currentStimulus, percentOfPage, maxHeight, maxWidth, AnimateTypes.none, showControls, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener);
    }

    @Deprecated
    protected void stimulusPresent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final boolean showControls, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        stimulusPresent(stimulusProvider, currentStimulus, percentOfPage, maxHeight, maxWidth, animateType, showControls, null, null, null, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, null);
    }

    @Deprecated
    protected void stimulusPresent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final boolean showControls, String regex, String replacement, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        stimulusPresent(stimulusProvider, currentStimulus, percentOfPage, maxHeight, maxWidth, animateType, showControls, null, regex, replacement, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, null);
    }

    @Deprecated
    protected void stimulusPresent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final Integer fixedPositionY, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        stimulusPresent(stimulusProvider, currentStimulus, percentOfPage, maxHeight, maxWidth, animateType, true, fixedPositionY, null, null, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, null);
    }

//    @Deprecated
//    protected void stimulusPresent(final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final boolean showControls, final Integer fixedPositionY, String regex, String replacement, final TimedStimulusListener timedStimulusListener, final TimedStimulusListener clickedStimulusListener) {
//        stimulusPresent(currentStimulus, percentOfPage, maxHeight, maxWidth, animateType, showControls, fixedPositionY, regex, replacement, timedStimulusListener, clickedStimulusListener);
//    }
    protected void stimulusPresent(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final boolean showControls, final Integer fixedPositionY, String regex, String replacement, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, final CancelableStimulusListener clickedStimulusListener) {
        if (currentStimulus.hasImage()) {
            final String image;
            if (regex != null && replacement != null) {
                image = currentStimulus.getImage().replaceAll(regex, replacement);
            } else {
                image = currentStimulus.getImage();
            }
            final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
                @Override
                protected void trigggerCancelableEvent() {
                    // send image shown tag
//                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusImageShown", currentStimulus.getUniqueId(), image, duration.elapsedMillis());
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
            timedStimulusView.addTimedImage(timedEventMonitor, UriUtils.fromTrustedString(image), percentOfPage, maxHeight, maxWidth, animateStyle, fixedPositionY, 0, shownStimulusListener, new CancelableStimulusListener() {
                @Override
                protected void trigggerCancelableEvent() {
                    loadedStimulusListener.postLoadTimerFired();
                    playedStimulusListener.postLoadTimerFired();
                }
            }, failedStimulusListener, clickedStimulusListener);
//        timedStimulusView.addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
        } else if (currentStimulus.hasAudio()) {
            String mp3 = currentStimulus.getAudio() + ".mp3";
            String ogg = currentStimulus.getAudio() + ".ogg";
            String wav = currentStimulus.getAudio() + ".wav";
            if (regex != null && replacement != null) {
                mp3 = mp3.replaceAll(regex, replacement);
                ogg = ogg.replaceAll(regex, replacement);
                wav = wav.replaceAll(regex, replacement);
            }
            final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString(ogg);
            final SafeUri mp3TrustedString = (mp3 == null) ? null : UriUtils.fromTrustedString(mp3);
            final SafeUri wavTrustedString = (wav == null) ? null : UriUtils.fromTrustedString(wav);
            final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
                @Override
                protected void trigggerCancelableEvent() {
                    // send audio shown tag
                    //submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusAudioShown", currentStimulus.getUniqueId(), currentStimulus.getAudio(), duration.elapsedMillis());
                    loadedStimulusListener.postLoadTimerFired();
                }
            };
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", currentStimulus.getAudio(), duration.elapsedMillis());
            timedStimulusView.addTimedAudio(timedEventMonitor, oggTrustedString, mp3TrustedString, wavTrustedString, false, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, true, "audio-" + currentStimulus.getUniqueId());
        } else if (currentStimulus.hasVideo()) {
            String ogv = currentStimulus.getVideo() + ".ogv";
            String mp4 = currentStimulus.getVideo() + ".mp4";
            if (regex != null && replacement != null) {
                mp4 = mp4.replaceAll(regex, replacement);
                ogv = ogv.replaceAll(regex, replacement);
            }
//            submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusVideo", currentStimulus.getVideo(), duration.elapsedMillis());
            final SafeUri ogvTrustedString = (ogv == null) ? null : UriUtils.fromTrustedString(ogv);
            final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString(mp4);
            final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
                @Override
                protected void trigggerCancelableEvent() {
                    // send video shown tag
//                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusVideoShown", currentStimulus.getUniqueId(), currentStimulus.getVideo(), duration.elapsedMillis());
                    loadedStimulusListener.postLoadTimerFired();
                }
            };
            timedStimulusView.addTimedVideo(timedEventMonitor, ogvTrustedString, mp4TrustedString, percentOfPage, maxHeight, maxWidth, null, !showControls, false, showControls, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, "video-" + currentStimulus.getUniqueId());
        } else if (currentStimulus.getLabel() != null) {
            timedStimulusView.addHtmlText(currentStimulus.getLabel(), null);
            // send label shown tag
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusLabelShown", currentStimulus.getUniqueId(), currentStimulus.getLabel(), duration.elapsedMillis());
            loadedStimulusListener.postLoadTimerFired();
            playedStimulusListener.postLoadTimerFired();
        } else {
            final String incorrect_stimulus_format = "incorrect stimulus format";
            nextStimulusButton(stimulusProvider, currentStimulus, incorrect_stimulus_format, incorrect_stimulus_format + " " + currentStimulus.getLabel(), null, true, -1, "incorrectStimulusFormat");
        }
    }

    protected void regionCodeStyle(final Stimulus currentStimulus, final String regionId, final String codeStyleName) {
        final String styleName = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeStyleName);
        ((ComplexView) simpleView).setRegionStyle(regionId, styleName);
    }

    public void stimulusCodeImageButton(final Stimulus currentStimulus, final String codeStyleName, String codeFormat, final String buttonGroup, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener clickedStimulusListener) {
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        final String styleName = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeStyleName);
        addButtonToGroup(buttonGroup, timedStimulusView.addTimedImage(timedEventMonitor, UriUtils.fromString((formattedCode.startsWith("file")) ? formattedCode : serviceLocations.staticFilesUrl() + formattedCode), styleName, 0, loadedStimulusListener, failedStimulusListener, clickedStimulusListener));
    }

    protected void stimulusCodeImage(final Stimulus currentStimulus, final String codeStyleName, int postLoadMs, String codeFormat, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        final String styleName = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeStyleName);
        timedStimulusView.addTimedImage(timedEventMonitor, UriUtils.fromString((formattedCode.startsWith("file")) ? formattedCode : serviceLocations.staticFilesUrl() + formattedCode), styleName, postLoadMs, loadedStimulusListener, failedStimulusListener, null);
//        timedStimulusView.addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
    }

    protected void stimulusCodeAudio(final Stimulus currentStimulus, final boolean autoPlay, final String mediaId, String codeFormat, boolean showPlaybackIndicator, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        final String uniqueId = currentStimulus.getUniqueId();

        String mp3 = formattedCode + ".mp3";
        String ogg = formattedCode + ".ogg";
        String wav = formattedCode + ".wav";
        final SafeUri oggTrustedString = (ogg == null) ? null : UriUtils.fromTrustedString((ogg.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + ogg);
        final SafeUri mp3TrustedString = (mp3 == null) ? null : UriUtils.fromTrustedString((mp3.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + mp3);
        final SafeUri wavTrustedString = (wav == null) ? null : UriUtils.fromTrustedString((wav.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + wav);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeAudio", formattedCode, duration.elapsedMillis());
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", formattedCode, duration.elapsedMillis());
        final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
            @Override
            protected void trigggerCancelableEvent() {
//                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusCodeAudioShown", uniqueId, formattedCode, duration.elapsedMillis());
                loadedStimulusListener.postLoadTimerFired();
            }
        };
        timedStimulusView.addTimedAudio(timedEventMonitor, oggTrustedString, mp3TrustedString, wavTrustedString, showPlaybackIndicator, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, autoPlay, formattedMediaId);
    }

    protected void stimulusVideo(final Stimulus currentStimulus, final String styleName, final boolean autoPlay, final String mediaId, final boolean loop, final boolean showControls, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final String videoName = currentStimulus.getVideo();
        final String uniqueId = currentStimulus.getUniqueId();
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        String mp4 = videoName + ".mp4";
        String ogv = videoName + ".ogv";
        final SafeUri ogvTrustedString = (ogv == null) ? null : UriUtils.fromTrustedString(ogv);
        final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString(mp4);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeVideo", formattedCode, duration.elapsedMillis());
        final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
            @Override
            protected void trigggerCancelableEvent() {
//                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusVideoShown", uniqueId, videoName, duration.elapsedMillis());
                loadedStimulusListener.postLoadTimerFired();
            }
        };
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", formattedCode, duration.elapsedMillis());
        Element videoElement = timedStimulusView.addTimedVideo(timedEventMonitor, ogvTrustedString, mp4TrustedString, 0, 0, 0, styleName, autoPlay, loop, showControls, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, formattedMediaId);
        if (videoElement != null) {
            timedEventMonitor.addVisibilityListener(widgetTag.getElement(), videoElement, "stimulusVideo");
        }
    }

    protected void stimulusCodeVideo(final Stimulus currentStimulus, int percentOfPage, int maxHeight, int maxWidth, final String codeStyleName, final boolean autoPlay, final String mediaId, final boolean loop, final boolean showControls, String codeFormat, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        final String styleName = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeStyleName);
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        final String uniqueId = currentStimulus.getUniqueId();
        String mp4 = formattedCode + ".mp4";
        String ogv = formattedCode + ".ogv";
        final SafeUri ogvTrustedString = (ogv == null) ? null : UriUtils.fromTrustedString((ogv.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + ogv);
        final SafeUri mp4TrustedString = (mp4 == null) ? null : UriUtils.fromTrustedString((mp4.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + mp4);
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusCodeVideo", formattedCode, duration.elapsedMillis());
        final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
            @Override
            protected void trigggerCancelableEvent() {
//                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusCodeVideoShown", uniqueId, formattedCode, duration.elapsedMillis());
                loadedStimulusListener.postLoadTimerFired();
            }
        };
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", formattedCode, duration.elapsedMillis());
        timedStimulusView.addTimedVideo(timedEventMonitor, ogvTrustedString, mp4TrustedString, percentOfPage, maxHeight, maxWidth, styleName, autoPlay, loop, showControls, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, formattedMediaId);
    }

    protected void stimulusAudio(final Stimulus currentStimulus, final boolean autoPlay, final String mediaId, boolean showPlaybackIndicator, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final String audio = currentStimulus.getAudio();
        final String uniqueId = currentStimulus.getUniqueId();
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        String ogg = audio + ".ogg";
        String mp3 = audio + ".mp3";
        String wav = audio + ".wav";
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "StimulusAudio", ogg, duration.elapsedMillis());
        final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
            @Override
            protected void trigggerCancelableEvent() {
                //submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusAudioShown", uniqueId, audio, duration.elapsedMillis());
                loadedStimulusListener.postLoadTimerFired();
            }
        };
        timedStimulusView.addTimedAudio(timedEventMonitor, UriUtils.fromTrustedString(ogg), UriUtils.fromTrustedString(mp3), UriUtils.fromTrustedString(wav), showPlaybackIndicator, shownStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, autoPlay, formattedMediaId);
//        timedStimulusView.addText("playStimulusAudio: " + duration.elapsedMillis() + "ms");
    }

    public void stimulusHasRatingOptions(final AppEventListener appEventListener, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener) {
        if (currentStimulus.hasRatingLabels()) {
            correctListener.postLoadTimerFired();
        } else {
            incorrectListener.postLoadTimerFired();
        }
    }

    public void touchInputLabelButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final TimedStimulusListener onActivateListener, final String eventTag, final String codeFormat, final String styleName, final String buttonGroup) {
        // TODO: utilise the media listeners
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return formattedCode;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                onActivateListener.postLoadTimerFired();
            }
        };
        final StimulusButton buttonItem = optionButton(eventListener, buttonGroup);
        touchInputStimulusButton(buttonItem, eventListener, eventTag, buttonGroup);
    }

    public void touchInputImageButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final TimedStimulusListener mediaLoadedListener, final TimedStimulusListener mediaLoadFailedListener, final TimedStimulusListener onActivateListener, final String codeFormat, final String styleName, final String buttonGroup) {
        // TODO: utilise the media listeners
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return null;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                onActivateListener.postLoadTimerFired();
            }
        };
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        final StimulusButton buttonItem = imageButton(eventListener, UriUtils.fromString((formattedCode.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + formattedCode), true, buttonGroup);
        touchInputStimulusButton(buttonItem, eventListener, eventTag, buttonGroup);
    }

    public void touchInputVideoButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final TimedStimulusListener mediaLoadedListener, final TimedStimulusListener mediaLoadFailedListener, final TimedStimulusListener onActivateListener, final String codeFormat, final String styleName, final String buttonGroup) {
        // TODO: utilise the media listeners
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return null;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                onActivateListener.postLoadTimerFired();
            }
        };
        final String formattedCode = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(codeFormat);
        // TODO: make the video button a thing
        final StimulusButton buttonItem = imageButton(eventListener, UriUtils.fromString((formattedCode.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + formattedCode), true, buttonGroup);
        touchInputStimulusButton(buttonItem, eventListener, eventTag, buttonGroup);
    }

    private void touchInputStimulusButton(final StimulusButton buttonItem, final PresenterEventListener presenterListener, final String eventTag, final String buttonGroup) {
        stimulusButtonList.add(buttonItem);
        touchInputCapture.addTouchZone(new TouchInputZone() {
            boolean isTriggered = false;

            @Override
            public String getEventTag() {
                return eventTag;
            }

            @Override
            public String getGroupName() {
                return buttonGroup;
            }

            @Override
            public boolean intersects(int xPos, int yPos) {
                boolean returnValue = (yPos >= buttonItem.getWidget().getAbsoluteTop()
                        && yPos <= buttonItem.getWidget().getAbsoluteTop() + buttonItem.getWidget().getOffsetHeight()
                        && xPos >= buttonItem.getWidget().getAbsoluteLeft()
                        && xPos <= buttonItem.getWidget().getAbsoluteLeft() + buttonItem.getWidget().getOffsetWidth());
                return returnValue;
            }

            @Override
            public void triggerEvent() {
                if (!isTriggered) {
                    isTriggered = true;
                    buttonItem.addStyleName(presenterListener.getStyleName() + "Intersection");
                    buttonItem.triggerSingleShotEventListener();
                }
            }

            @Override
            public void clearEvent() {
                buttonItem.removeStyleName(presenterListener.getStyleName() + "Intersection");
                isTriggered = false;
            }
        });
    }

    public void stimulusSlider(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final PresenterEventListener presenterListener, final int dataChannel, final OrientationType orientationType, final int initial, final int minimum, final int maximum, final String buttonGroup) {
        final String postName = (buttonGroup != null && !buttonGroup.isEmpty()) ? buttonGroup : "stimulusSlider";
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
        final String initialValue;
        if (storedStimulusJSONObject != null) {
            initialValue = storedStimulusJSONObject.containsKey(postName) ? storedStimulusJSONObject.get(postName).toString().replaceAll("\\\"", "") : Integer.toString(initial);
        } else {
            initialValue = Integer.toString(initial);
        }
        final StimulusFreeText slider = timedStimulusView.addSlider(currentStimulus, postName, presenterListener, initialValue, minimum, maximum, dataChannel);
        stimulusFreeTextList.add(addButtonToGroup(buttonGroup, slider));
    }

    public void stimulusButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final PresenterEventListener presenterListener, final String eventTag, final int dataChannel, final String buttonGroup) {
        final StimulusButton buttonItem = optionButton(new PresenterEventListener() {
            @Override
            public String getLabel() {
                // this stimulusButton label comes from featureText
                return presenterListener.getLabel();
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "stimulusButton" : eventTag);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "StimulusButton", currentStimulus.getUniqueId(), presenterListener.getLabel(), duration.elapsedMillis());
                Boolean isCorrect = null;
                if (currentStimulus.hasCorrectResponses()) {
                    final boolean correctness = currentStimulus.isCorrect(presenterListener.getLabel());
                    submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, presenterListener.getLabel(), currentStimulus.getUniqueId(), (correctness) ? "correct" : "incorrect", duration.elapsedMillis());
                    // if there are correct responses to this stimulus then increment the score
                    userResults.getUserData().addPotentialScore(correctness);
                    isCorrect = correctness;
                }
                HashMap<Stimulus, JSONObject> jsonStimulusMap = new HashMap<>();
                if (!jsonStimulusMap.containsKey(currentStimulus)) {
                    JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
                    storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                    jsonStimulusMap.put(currentStimulus, storedStimulusJSONObject);
                }
                jsonStimulusMap.get(currentStimulus).put("stimulusButton", new JSONString(presenterListener.getLabel()));
                localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, jsonStimulusMap.get(currentStimulus));
                // @todo: probably good to check if the data has changed before writing to disk
                submissionService.writeJsonData(userResults.getUserData().getUserId().toString(), currentStimulus.getUniqueId(), jsonStimulusMap.get(currentStimulus).toString());
                submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), dataChannel, "stimulusButton", currentStimulus, presenterListener.getLabel(), isCorrect, duration.elapsedMillis());
                presenterListener.eventFired(button, shotEventListener);
            }

            @Override
            public String getStyleName() {
                return presenterListener.getStyleName();
            }

            @Override
            public int getHotKey() {
                return presenterListener.getHotKey();
            }
        }, buttonGroup);
        stimulusButtonList.add(buttonItem);
    }

    public void stimulusRatingButton(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel) {
        final String formattedGroupId;
        if (buttonGroup == null || buttonGroup.isEmpty()) {
            formattedGroupId = "stimulusRatingButton";
        } else {
            formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(buttonGroup);
        }
        ratingButtons(getRatingEventListeners(appEventListener, /*stimulusProvider,*/ currentStimulus, eventTag, timedStimulusListener, currentStimulus.getUniqueId(), currentStimulus.getRatingLabels(), formattedGroupId, dataChannel), ratingLabelLeft, ratingLabelRight, false, styleName, null, false, null, formattedGroupId, null, orientationType);
    }

    public void stimulusRatingRadio(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel, final String radioGroupName) {
        ratingRadioButton(appEventListener, /*stimulusProvider,*/ currentStimulus, buttonGroup, timedStimulusListener, orientationType, currentStimulus.getRatingLabels(), ratingLabelLeft, ratingLabelRight, eventTag, styleName, dataChannel, radioGroupName, false);
    }

    public void stimulusRatingCheckbox(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel, final String radioGroupName) {
        ratingRadioButton(appEventListener, /*stimulusProvider,*/ currentStimulus, buttonGroup, timedStimulusListener, orientationType, currentStimulus.getRatingLabels(), ratingLabelLeft, ratingLabelRight, eventTag, styleName, dataChannel, radioGroupName, true);
    }

    public void ratingCheckbox(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel, final String radioGroupName) {
        ratingRadioButton(appEventListener, /*stimulusProvider,*/ currentStimulus, buttonGroup, timedStimulusListener, orientationType, ratingLabels, ratingLabelLeft, ratingLabelRight, eventTag, styleName, dataChannel, radioGroupName, true);
    }

    public void ratingRadioButton(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel, final String radioGroupName) {
        ratingRadioButton(appEventListener, /*stimulusProvider,*/ currentStimulus, buttonGroup, timedStimulusListener, orientationType, ratingLabels, ratingLabelLeft, ratingLabelRight, eventTag, styleName, dataChannel, radioGroupName, false);
    }

    private void ratingRadioButton(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel, final String radioGroupName, final boolean allowMultiple) {
        final List<PresenterEventListener> ratingEventListeners = new ArrayList<>();//getRatingEventListeners(appEventListener, stimulusProvider, currentStimulus, timedStimulusListener, currentStimulus.getUniqueId(), currentStimulus.getRatingLabels(), dataChannel);
        final String stimulusRatingType;
        if (buttonGroup == null || buttonGroup.isEmpty()) {
            stimulusRatingType = (buttonGroup != null && !buttonGroup.isEmpty()) ? buttonGroup : (allowMultiple) ? "stimulusRatingCheckbox" : "stimulusRatingRadio";
        } else {
            stimulusRatingType = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(buttonGroup);
        }
        final List<StimulusButton> ratingButtons = new ArrayList<>();
        if (ratingLabels != null) {
            final String[] splitRatingLabels = ratingLabels.split(",");
            for (final String ratingItem : splitRatingLabels) {
                ratingEventListeners.add(new PresenterEventListener() {
                    @Override
                    public String getLabel() {
                        return ratingItem;
                    }

                    @Override
                    public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                        timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "ratingRadioButton" : eventTag);
                        JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
                        storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                        String ratingValue = "";
                        if (allowMultiple) {
                            for (StimulusButton stimulusButton : ratingButtons) {
                                if (stimulusButton.isChecked()) {
                                    if (!ratingValue.isEmpty()) {
                                        ratingValue += ",";
                                    }
                                    ratingValue += stimulusButton.getValue();
                                    stimulusButton.addStyleName("optionButtonActivated");
                                } else {
                                    stimulusButton.removeStyleName("optionButtonActivated");
                                }
                            }
                        } else {
                            for (StimulusButton stimulusButton : ratingButtons) {
                                stimulusButton.removeStyleName("optionButtonActivated");
                            }
                            button.addStyleName("optionButtonActivated");
                            ratingValue = ratingItem;
                        }
                        storedStimulusJSONObject.put(stimulusRatingType, new JSONString(ratingValue));
                        localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, storedStimulusJSONObject);
                        timedStimulusListener.postLoadTimerFired();
                    }

                    @Override
                    public String getStyleName() {
                        return null; // should this one return styleName or a sibling object?
                    }

                    @Override
                    public int getHotKey() {
                        return -1;
                    }
                });
            }
        }
        final Panel ratingStylePanel = new VerticalPanel();
        final StimulusFreeText stimulusFreeText = new StimulusFreeText() {
            private boolean isVisible = true;
            private boolean isEnabled = true;

            @Override
            public Stimulus getStimulus() {
                return currentStimulus;
            }

            @Override
            public String getPostName() {
                return stimulusRatingType;
            }

            @Override
            public String getResponseTimes() {
                return null;
            }

            @Override
            public String getValue() {
                final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
                final String fieldValue;
                if (storedStimulusJSONObject != null) {
                    fieldValue = storedStimulusJSONObject.containsKey(stimulusRatingType) ? storedStimulusJSONObject.get(stimulusRatingType).isString().stringValue() : "";
                } else {
                    fieldValue = "";
                }
                return fieldValue;
            }

            @Override
            public void setVisible(boolean isVisible) {
                // the visibility is already handled in the ratingButtons array
                this.isVisible = isVisible;
                if (!isEnabled()) {
                    for (StimulusButton stimulusButton : ratingButtons) {
                        stimulusButton.addStyleName("metadataOK");
                        stimulusButton.removeStyleName("metadataError");
                    }
                    ratingStylePanel.addStyleName("metadataOK");
                    ratingStylePanel.removeStyleName("metadataError");
                }
            }

            @Override
            public void setEnabled(boolean isEnabled) {
                // the enabled is already handled in the ratingButtons array
                this.isEnabled = isEnabled;
                if (!isEnabled()) {
                    for (StimulusButton stimulusButton : ratingButtons) {
                        stimulusButton.addStyleName("metadataOK");
                        stimulusButton.removeStyleName("metadataError");
                    }
                    ratingStylePanel.addStyleName("metadataOK");
                    ratingStylePanel.removeStyleName("metadataError");
                }
            }

            @Override
            public boolean isEnabled() {
                return isEnabled && isVisible;
            }

            @Override
            public boolean isValid() {
                if (getValue().isEmpty()) {
                    for (StimulusButton stimulusButton : ratingButtons) {
                        stimulusButton.addStyleName("metadataError");
                        stimulusButton.removeStyleName("metadataOK");
                    }
                    ratingStylePanel.addStyleName("metadataError");
                    ratingStylePanel.removeStyleName("metadataOK");
                    return false;
                } else {
                    for (StimulusButton stimulusButton : ratingButtons) {
                        stimulusButton.addStyleName("metadataOK");
                        stimulusButton.removeStyleName("metadataError");
                    }
                    ratingStylePanel.addStyleName("metadataOK");
                    ratingStylePanel.removeStyleName("metadataError");
                    return true;
                }
            }

            @Override
            public int getDataChannel() {
                return dataChannel;
            }

            @Override
            public void setFocus(boolean wantsFocus) {
            }
        };
        ratingButtons.addAll(ratingButtons(ratingEventListeners, ratingLabelLeft, ratingLabelRight, false, styleName, radioGroupName, allowMultiple, stimulusFreeText.getValue(), stimulusRatingType, ratingStylePanel, orientationType));
        stimulusFreeTextList.add(addButtonToGroup(stimulusRatingType, stimulusFreeText));
    }

    public void ratingButton(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final OrientationType orientationType, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel) {
        final String formattedGroupId;
        if (buttonGroup == null || buttonGroup.isEmpty()) {
            formattedGroupId = "ratingButton";
        } else {
            formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(buttonGroup);
        }
        ratingButtons(getRatingEventListeners(appEventListener, /*stimulusProvider,*/ currentStimulus, eventTag, timedStimulusListener, currentStimulus.getUniqueId(), ratingLabels, formattedGroupId, dataChannel), ratingLabelLeft, ratingLabelRight, false, styleName, null, false, null, formattedGroupId, null, orientationType);
    }

    public void ratingFooterButton(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String buttonGroup, final TimedStimulusListener timedStimulusListener, final String ratingLabels, final String ratingLabelLeft, final String ratingLabelRight, final String eventTag, final String styleName, final int dataChannel) {
        final String formattedGroupId;
        if (buttonGroup == null || buttonGroup.isEmpty()) {
            formattedGroupId = "ratingFooterButton";
        } else {
            formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(buttonGroup);
        }
        ratingButtons(getRatingEventListeners(appEventListener, /*stimulusProvider,*/ currentStimulus, eventTag, timedStimulusListener, currentStimulus.getUniqueId(), ratingLabels, formattedGroupId, dataChannel), ratingLabelLeft, ratingLabelRight, true, styleName, null, false, null, formattedGroupId, null, OrientationType.horizontal);
    }

    public List<PresenterEventListener> getRatingEventListeners(final AppEventListener appEventListener, /*final StimuliProvider stimulusProvider,*/ final Stimulus currentStimulus, final String eventTag, final TimedStimulusListener timedStimulusListener, final String stimulusString, final String ratingLabels, final String formattedGroupId, final int dataChannel) {
        ArrayList<PresenterEventListener> eventListeners = new ArrayList<>();
        if (ratingLabels != null) {
            final String[] splitRatingLabels = ratingLabels.split(",");
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
                eventListeners.add(new PresenterEventListener() {
                    @Override
                    public String getLabel() {
                        return ratingItem;
                    }

                    @Override
                    public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                        timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? ratingItem : eventTag + "_" + ratingItem);
                        //timedEventMonitor.registerEvent(formattedGroupId); // should this be eventTag
                        endAudioRecorderTag(dataChannel, ratingItem, currentStimulus);
                        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, formattedGroupId, stimulusString, ratingItem, duration.elapsedMillis());
                        Boolean isCorrect = null;
                        if (currentStimulus.hasCorrectResponses()) {
                            final boolean correctness = currentStimulus.isCorrect(ratingItem);
                            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, ratingItem, stimulusString, (correctness) ? "correct" : "incorrect", duration.elapsedMillis());
                            // if there are correct responses to this stimulus then increment the score
                            userResults.getUserData().addPotentialScore(correctness);
                            isCorrect = correctness;
                        }
                        HashMap<Stimulus, JSONObject> jsonStimulusMap = new HashMap<>();
                        if (!jsonStimulusMap.containsKey(currentStimulus)) {
                            JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
                            storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                            jsonStimulusMap.put(currentStimulus, storedStimulusJSONObject);
                        }
                        jsonStimulusMap.get(currentStimulus).put(formattedGroupId, new JSONString(ratingItem));
                        localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, jsonStimulusMap.get(currentStimulus));
                        // @todo: probably good to check if the data has changed before writing to disk
                        submissionService.writeJsonData(userResults.getUserData().getUserId().toString(), currentStimulus.getUniqueId(), jsonStimulusMap.get(currentStimulus).toString());
                        submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), dataChannel, formattedGroupId, currentStimulus, ratingItem, isCorrect, duration.elapsedMillis());
                        timedStimulusListener.postLoadTimerFired();
                    }

                    @Override
                    public String getStyleName() {
                        return null;
                    }

                    @Override
                    public int getHotKey() {
                        return hotKey;
                    }
                });
            }
        }
        return eventListeners;
    }

    protected void showCurrentMs() {
//        timedStimulusView.addText(duration.elapsedMillis() + "ms");
    }

    protected void recorderToneInjection(DTMF dtmf) {
        mediaRecorder.injectTone(dtmf, timedEventMonitor);
    }

    protected void hardwareTimeStamp(BooleanToggle opto1, BooleanToggle opto2, DTMF dtmf) {
        if (hardwareTimeStamp != null) {
            if (opto1 != null) {
                hardwareTimeStamp.setOpto1(opto1);
            }
            if (opto2 != null) {
                hardwareTimeStamp.setOpto2(opto2);
            }
            if (dtmf != null) {
                hardwareTimeStamp.setDtmf(dtmf);
            }
        }
    }

    protected void logTimeStamp(String eventTag) {
        timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "logTimeStamp" : eventTag);;
        //logTimeStamp(stimulusProvider, currentStimulus, "logTimeStamp", eventTag, dataChannel);
    }

    protected void logTagPairStamp(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventName, final String eventTag, final int dataChannel) {
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, (currentStimulus != null) ? currentStimulus.getUniqueId() : "", eventName, duration.elapsedMillis());
    }

    protected void endAudioRecorderTag(int tier, String tagString, final Stimulus currentStimulus) {
        mediaRecorder.endRecorderTag(this, tier, (currentStimulus != null) ? currentStimulus.getUniqueId() : "", (currentStimulus != null) ? currentStimulus.getCode() : "", tagString, timedEventMonitor);
    }

    protected void startAudioRecorderTag(int tier) {
        mediaRecorder.startRecorderTag(this, tier, timedEventMonitor); //((tier < 1) ? 1 : tier) + 2); //  tier 1 and 2 are reserved for stimulus set loading and stimulus display events
    }

    protected void audioInputSelectWeb(final String deviceRegexL, final String styleName, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        final String deviceRegex = (deviceRegexL == null) ? ".*" : deviceRegexL;
        final String selectedDevice = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "AudioRecorderDeviceId");
        final ValueChangeListener itemAddedListener = timedStimulusView.addListBox(selectedDevice, null, styleName, new ValueChangeListener<String>() {
            @Override
            public void onValueChange(String value) {
                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "AudioRecorderDeviceId", value);
            }
        });
        listAudioDevicesWeb(deviceRegex, new DeviceListingListener() {
            boolean hasDeviceNames = false;

            @Override
            public void listingFailed() {
                onError.postLoadTimerFired();
            }

            @Override
            public void listingComplete() {
                if (hasDeviceNames) {
                    onSuccess.postLoadTimerFired();
                } else {
                    onError.postLoadTimerFired();
                }
            }

            @Override
            public void deviceFound(String targetDeviceId) {
                if (targetDeviceId != null && !targetDeviceId.isEmpty()) {
                    hasDeviceNames = true;
                }
                itemAddedListener.onValueChange(targetDeviceId);
            }
        });
    }

    // TODO: replace all AudioRecorderWeb methods with startMediaRecorderWeb type="ogg" / type="ogv" etc.
    protected void startAudioRecorderWeb(final String recordingLabel, final String recordingFormatL, final int downloadPermittedWindowMs, final String mediaId, final String deviceRegexL, final String levelIndicatorStyle, final boolean noiseSuppression, final boolean echoCancellation, final boolean autoGainControl, final Stimulus currentStimulus, final TimedStimulusListener onError, final TimedStimulusListener onSuccess, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        super.clearRecorderTriggersWeb();
        final ValueChangeListener<Double> changeListener;
        if (levelIndicatorStyle != null) {
            // the levelIndicator is added separately so that it does not get added more than once when the (audioContextCurrentMS > 100) triggers a restart of the recorder
            simpleView.clearRegion("AudioRecorderWebLevelIndicator");
            final InsertPanel.ForIsWidget levelIndicatorRegion = simpleView.startRegion("AudioRecorderWebLevelIndicator", null);
            changeListener = timedStimulusView.addBarGraphElement(0, 100, levelIndicatorStyle);
            simpleView.endRegion(levelIndicatorRegion);
        } else {
            changeListener = null;
        }
        startAudioRecorderWeb(recordingLabel, recordingFormatL, downloadPermittedWindowMs, mediaId, deviceRegexL, changeListener, noiseSuppression, echoCancellation, autoGainControl, currentStimulus, onError, onSuccess, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, 15);
    }

    private void startAudioRecorderWeb(final String recordingLabel, final String recordingFormatL, final int downloadPermittedWindowMs, final String mediaId, final String deviceRegexL, final ValueChangeListener<Double> changeListener, final boolean noiseSuppression, final boolean echoCancellation, final boolean autoGainControl, final Stimulus currentStimulus, final TimedStimulusListener onError, final TimedStimulusListener onSuccess, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, int retryCount) {
        // it is important that this mediaId is claimed at this point to prevent later issues in playback or with existing media of the same id.
        // todo: when the wasm is not in the server mime types the recorder silently fails leaving the record indicator running
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        timedStimulusView.setWebRecorderMediaId(formattedMediaId);

        final String deviceRegex = (deviceRegexL == null) ? localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "AudioRecorderDeviceId") : deviceRegexL;
        final String recordingFormat = (recordingFormatL == null) ? "ogg" : recordingFormatL;
        final MediaSubmissionListener mediaSubmissionListener = new MediaSubmissionListener() {
            boolean recordingAborted = false;

            @Override
            public void recorderNotReady() {
                Timer pauseTimer = new Timer() {
                    public void run() {
                        if (retryCount > 0) {
                            startAudioRecorderWeb(recordingLabel, recordingFormatL, downloadPermittedWindowMs, mediaId, deviceRegexL, changeListener, noiseSuppression, echoCancellation, autoGainControl, currentStimulus, onError, onSuccess, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, retryCount - 1);
                        } else {
                            onError.postLoadTimerFired();
                        }
                    }
                };
                pauseTimer.schedule(50);
            }

            @Override
            public void recorderStarted(final String targetDeviceId, final Double audioContextCurrentMS) {
                // ((ComplexView) simpleView).addText("audioContextCurrentMS: " + audioContextCurrentMS);
                if (audioContextCurrentMS > 100) {
                    recordingAborted = true;
                    final String errorMessage = "rejecting due to audioContextCurrentMS out of spec: " + audioContextCurrentMS;
                    submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "AudioRecorder", errorMessage, duration.elapsedMillis());
                    GWT.log("stimulusPath: " + errorMessage);
                    stopAudioRecorder();
                    if (retryCount > 0) {
                        startAudioRecorderWeb(recordingLabel, recordingFormatL, downloadPermittedWindowMs, mediaId, deviceRegexL, changeListener, noiseSuppression, echoCancellation, autoGainControl, currentStimulus, onError, onSuccess, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, retryCount - 1);
                    } else {
                        onError.postLoadTimerFired();
                    }
                } else {
                    localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "AudioRecorderDeviceId", targetDeviceId);
                    submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "AudioRecorderDeviceId", targetDeviceId, duration.elapsedMillis());
                    if (changeListener != null) {
                        AbstractStimulusPresenter.super.addRecorderLevelIndicatorWeb(changeListener);
                    }
                    onSuccess.postLoadTimerFired();
                }
            }

            @Override
            public void submissionFailed(final String message, final String userIdString, final String screenName, final String stimulusIdString, final Uint8Array dataArray) {
                if (!recordingAborted) {
                    // todo: consider storing unsent data for retries, but keep in mind that the local storage will overfill very quickly
//                timedStimulusView.addText("(debug) Media Submission Failed (retry not implemented): " + message);
                    failedStimulusListener.postLoadTimerFired();
                    final MediaSubmissionListener mediaSubmissionListener = this;
                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            submissionService.submitMediaData(userIdString, screenName, stimulusIdString, dataArray, mediaSubmissionListener, downloadPermittedWindowMs, recordingFormat);
                        }
                    };
                    timer.schedule(1000);
                }
            }

            @Override
            public void recorderFailed(final String message) {
                onError.postLoadTimerFired();
                submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "AudioRecorder", message, duration.elapsedMillis());
            }

            @Override
            public void submissionComplete(String message, String urlAudioData) {
                if (!recordingAborted) {
                    if (downloadPermittedWindowMs > 0) {
                        String replayAudioUrl = serviceLocations.dataSubmitUrl() + "replayAudio/" + message.replaceAll("[^a-zA-Z0-9\\-]", "") + "/" + userResults.getUserData().getUserId();
//                timedStimulusView.addText("(debug) Media Submission OK: " + message);
                        // playback can be done from RAM or from the server which is why do we do: (downloadPermittedWindowMs <= 0) ? UriUtils.fromTrustedString(urlAudioData) : UriUtils.fromString(replayAudioUrl)
                        // TODO: this callback loadedStimulusListener might be able to traverse the nextStimulus and then trigger another nextStimulus in mskonopka
                        timedStimulusView.addTimedAudio(timedEventMonitor, (downloadPermittedWindowMs <= 0) ? UriUtils.fromTrustedString(urlAudioData) : UriUtils.fromString(replayAudioUrl), null, null, false, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, false, formattedMediaId);
                    } else {
                        loadedStimulusListener.postLoadTimerFired();
                    }
                }
            }
        };
        if (recordingFormat.equals("ogv")) {
            // TODO: this could be more elegant in relation to the termination of the preceding recording type
            mediaRecorder = new VideoRecorder();
        } else {
            // TODO: this could be more elegant in relation to the termination of the preceding recording type
            mediaRecorder = new AudioRecorder();
        }
        mediaRecorder.startRecorderWeb(this, submissionService, recordingLabel, deviceRegex, noiseSuppression, echoCancellation, autoGainControl, currentStimulus.getUniqueId(), userResults.getUserData().getUserId().toString(), getSelfTag(), mediaSubmissionListener, downloadPermittedWindowMs, recordingFormat);
    }

    protected void stopAudioRecorder() {
        mediaRecorder.stopRecorder(this);
    }

    protected void requestRecorderPermissions() {
        mediaRecorder.requestRecorderPermissions(this);
    }

    protected void startAudioRecorderTag(int tier, final TimedEventMonitor timedEventMonitor) {
        mediaRecorder.startRecorderTag(this, 8, null);
    }

    protected void startAudioRecorderApp(final MetadataField directoryMetadataField, boolean filePerStimulus, String directoryName, final Stimulus currentStimulus, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        final String subdirectoryName = userResults.getUserData().getMetadataValue(directoryMetadataField);
//        final String subdirectoryName = userResults.getUserData().getUserId().toString();
        super.startAudioRecorderApp(subdirectoryName, directoryName, filePerStimulus, currentStimulus.getUniqueId(), userResults.getUserData().getUserId().toString(), getSelfTag(), onError, onSuccess);
    }

    protected void showStimulusGrid(final AppEventListener appEventListener, final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener, final int columnCount, final String imageWidth, final String eventTag, final int dataChannel) {
        final int maxStimuli = -1;
        final AnimateTypes animateType = AnimateTypes.none;
        showStimulusGrid(appEventListener, stimulusProvider, currentStimulus, correctListener, incorrectListener, maxStimuli, columnCount, imageWidth, animateType, eventTag, dataChannel);
    }

    protected void showStimulusGrid(final AppEventListener appEventListener, final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener, final int maxStimuli, final int columnCount, final String imageWidth, final AnimateTypes animateType, final String eventTag, final int dataChannel) {
        timedStimulusView.stopAudio();
        TimedStimulusListener correctTimedListener = new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
                correctListener.postLoadTimerFired();
            }
        };
        TimedStimulusListener incorrectTimedListener = new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
                incorrectListener.postLoadTimerFired();
            }
        };
        final String gridStyle = "stimulusGrid";
        // todo: the appendStoredDataValue should occur in the correct or incorrect response within stimulusListener
        //localStorage.appendStoredDataValue(userResults.getUserData().getUserId(), SEEN_STIMULUS_LIST, currentStimulus.getAudioTag());
        timedStimulusView.startGrid(gridStyle);
        int imageCounter = 0;
//        if (alternativeChoice != null) {
//            stimulusButtonList.add(timedStimulusView.addStringItem(getEventListener(stimulusButtonList, eventTag, currentStimulus, alternativeChoice, correctTimedListener, incorrectTimedListener), alternativeChoice, 0, 0, imageWidth));
//        }
        String groupResponseOptions = null;
        for (final Stimulus nextJpgStimulus : stimulusProvider.getDistractorList(maxStimuli)) {
            final String styleName;
            if (animateType == AnimateTypes.stimuliCode) {
                styleName = nextJpgStimulus.getCode() + "Animation";
            } else if (animateType != AnimateTypes.none) {
                styleName = animateType.name() + "Animation";
            } else {
                styleName = null;
            }
            // collect the distractor list for later reporting
            if (groupResponseOptions == null) {
                groupResponseOptions = "";
            } else {
                groupResponseOptions += ",";
            }
            groupResponseOptions += nextJpgStimulus.getUniqueId();
            final StimulusButton imageItem = timedStimulusView.addImageItem(getEventListener(currentStimulus, stimulusButtonList, eventTag, dataChannel, currentStimulus, nextJpgStimulus, correctTimedListener, incorrectTimedListener), UriUtils.fromString(nextJpgStimulus.getImage()), imageCounter / columnCount, 1 + imageCounter++ % columnCount, imageWidth, styleName, imageCounter);
            stimulusButtonList.add(imageItem);
        }
        if (groupParticipantService != null) {
            groupParticipantService.setResponseStimulusOptions(groupResponseOptions);
        }
        disableStimulusButtons();
        timedStimulusView.endGrid();
        //timedStimulusView.addAudioPlayerGui();
    }

    protected void matchingStimulusGrid(final AppEventListener appEventListener, final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener, final String matchingRegex, final boolean randomise, final int columnCount, int maxWidth, final int dataChannel) {
        final int maxStimulusCount = -1;
        final AnimateTypes animateType = AnimateTypes.none;
        matchingStimulusGrid(appEventListener, stimulusProvider, currentStimulus, correctListener, incorrectListener, matchingRegex, maxStimulusCount, randomise, columnCount, maxWidth, animateType, dataChannel);
    }

    protected void matchingStimulusGrid(final AppEventListener appEventListener, final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final TimedStimulusListener correctListener, final TimedStimulusListener incorrectListener, final String matchingRegex, final int maxStimulusCount, final boolean randomise, final int columnCount, int maxWidth, final AnimateTypes animateType, final int dataChannel) {
        matchingStimuliGroup = new MatchingStimuliGroup(currentStimulus, stimulusProvider.getMatchingStimuli(matchingRegex), true, hasMoreStimulusListener, endOfStimulusListener);
        final InsertPanel.ForIsWidget isWidget = timedStimulusView.startHorizontalPanel();
        int ySpacing = (int) (100.0 / (matchingStimuliGroup.getStimulusCount() + 1));
        int yPos = 0;
        while (matchingStimuliGroup.getNextStimulus(stimulusProvider)) {
            yPos += ySpacing;
            if (matchingStimuliGroup.isCorrect(currentStimulus)) {
                stimulusPresent(stimulusProvider, currentStimulus, 0, maxWidth, maxWidth, animateType, false, yPos - (maxWidth / 2), null, null, dataChannel,
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {
                        correctListener.postLoadTimerFired();
                    }
                });
            } else {
                stimulusPresent(stimulusProvider, currentStimulus, 0, maxWidth, maxWidth, animateType, false, yPos - (maxWidth / 2), null, null, dataChannel,
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                },
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {
                        incorrectListener.postLoadTimerFired();
                    }
                });
            }
        }
        timedStimulusView.endHorizontalPanel(isWidget);
    }

    private PresenterEventListener getEventListener(final Stimulus currentStimulus, final ArrayList<StimulusButton> buttonList, final String eventTag, final int dataChannel, final Stimulus correctStimulusItem, final Stimulus currentStimulusItem, final TimedStimulusListener correctTimedListener, final TimedStimulusListener incorrectTimedListener) {
        final String tagValue1 = correctStimulusItem.getImage();
        final String tagValue2 = currentStimulusItem.getImage();
        return new PresenterEventListener() {

            @Override
            public String getLabel() {
                return "";
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
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "stimulusGridButton" : eventTag);
                for (StimulusButton currentButton : buttonList) {
                    currentButton.setEnabled(false);
                }
                if (groupParticipantService != null) {
                    groupParticipantService.setResponseStimulusId(currentStimulusItem.getUniqueId());
//                    groupParticipantService.messageGroup(0, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), null, null, currentStimulusItem.getUniqueId());
                }
                button.addStyleName("stimulusButtonHighlight");
                // eventTag is set by the user and is different for each state (correct/incorrect).
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, tagValue1, tagValue2, duration.elapsedMillis());
                if (currentStimulus.getImage().equals(tagValue2)) {
                    correctTimedListener.postLoadTimerFired();
                } else {
                    incorrectTimedListener.postLoadTimerFired();
                }
            }
        };
    }

    public void addMediaTrigger(final Stimulus definitionScopeStimulus, final String mediaId, final String evaluateMs, final int threshold, final TimedStimulusListener onLateListener, final SingleStimulusListener triggerListener) {
        final String formattedMediaId = new HtmlTokenFormatter(definitionScopeStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        // the web recorder does not have stimulus in scope so we rely on the definition scope stimulus
        final FrameTimeTrigger frameTimeTrigger = addFrameTimeTrigger(definitionScopeStimulus, evaluateMs, threshold, onLateListener, triggerListener);
        if (timedStimulusView.isWebRecorderMediaId(formattedMediaId)) {
            addRecorderTriggersWeb(frameTimeTrigger);
        } else {
            timedStimulusView.addMediaTriggers(formattedMediaId, frameTimeTrigger);
        }
    }

    public void triggerDefinition(final Stimulus currentStimulus, final String listenerId, final int threshold, final int maximum, final SingleStimulusListener triggerListener) {
        final String formattedListenerId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(listenerId);
        triggerListeners.put(formattedListenerId, new TriggerListener(currentStimulus, formattedListenerId, threshold, maximum, triggerListener));
    }

    public void habituationParadigmListener(final Stimulus currentStimulus, final String listenerId, final int threshold, final int maximum, final SingleStimulusListener triggerListener) {
        final String formattedListenerId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(listenerId);
        triggerListeners.put(formattedListenerId, new HabituationParadigmListener(currentStimulus, listenerId, threshold, maximum, triggerListener, triggerListeners.containsKey(listenerId)));
    }

    public void triggerMatching(final String listenerId, final Stimulus currentStimulus) {
        final String formattedListenerId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(listenerId);
        // TODO: this would be nice to match on regex, but this will require a loop on the keys which would cause issues for time critical sections
        final TriggerListener matchedListener = triggerListeners.get(formattedListenerId);
        if (matchedListener != null) {
            matchedListener.trigger(currentStimulus);
        } else {
            // todo: perhaps we should have trigger not found onError
        }
    }

    public void triggerRandom(final Stimulus currentStimulus, final String matchingRegex, final TimedStimulusListener endOfTriggersListener) {
        ArrayList<TriggerListener> matchingListeners = new ArrayList<>();
        for (TriggerListener triggerListener : triggerListeners.values()) {
            if (triggerListener.canTrigger()) {
                if (triggerListener.getListenerId().matches(matchingRegex)) {
                    matchingListeners.add(triggerListener);
                }
            }
        }
        if (!matchingListeners.isEmpty()) {
            matchingListeners.get(new Random().nextInt(matchingListeners.size())).trigger(currentStimulus);
        } else {
            endOfTriggersListener.postLoadTimerFired();
        }
    }

    public void resetTrigger(final String listenerId, final Stimulus currentStimulus) {
        final String formattedListenerId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(listenerId);
        triggerListeners.get(formattedListenerId).reset();
    }

    public void cancelPauseAll() {
        cancelPauseTimers();
        timedStimulusView.stopListeners();
        timedStimulusView.stopTimers();
        timedStimulusView.stopAudio();
        timedStimulusView.stopAllMedia();
        timedStimulusView.clearDomHandlers();
        stopAudioRecorder();
        timerService.clearAllTimers(); // clear all callbacks in timerService before exiting the presenter
        submissionService.submitTimestamps(userResults.getUserData().getUserId(), timedEventMonitor);
    }

    public void disableStimulusButtons() {
        for (StimulusButton currentButton : stimulusButtonList) {
            currentButton.setEnabled(false);
        }
//        timedStimulusView.addText("disableStimulusButtons: " + duration.elapsedMillis() + "ms");
    }

    public void showStimulusProgress(final StimuliProvider stimulusProvider) {
        showStimulusProgress(stimulusProvider, null);
    }

    public void showStimulusProgress(final StimuliProvider stimulusProvider, String styleName) {
        timedStimulusView.addHtmlText((stimulusProvider.getCurrentStimulusIndex() + 1) + " / " + stimulusProvider.getTotalStimuli(), styleName);
//        timedStimulusView.addText("showStimulusProgress: " + duration.elapsedMillis() + "ms");
    }

//    public void popupMessage(final PresenterEventListener presenterListener, String message) {
//        timedStimulusView.showHtmlPopup(presenterListener, message);
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
//    protected void clearStimulus() {
//        // when is this used?
//        clearPage();
//        stimulusButtonList.clear();
//    }
    public void stimulusExists(final int offsetInteger, final StimuliProvider stimulusProvider, final TimedStimulusListener conditionTrue, final TimedStimulusListener conditionFalse) {
        if (stimulusProvider.hasNextStimulus(offsetInteger)) {
            conditionTrue.postLoadTimerFired();
        } else {
            conditionFalse.postLoadTimerFired();
        }
    }

    public void validateStimuliResponses(final boolean unusedValue, final TimedStimulusListener conditionTrue, final TimedStimulusListener conditionFalse) {
        if (validateStimuliResponses()) {
            conditionTrue.postLoadTimerFired();
        } else {
            conditionFalse.postLoadTimerFired();
        }
    }

    private boolean validateStimuliResponses(/* this must use the stimuli for each StimulusFreeText and not from the stimulusProvider */) {
        HashMap<Stimulus, JSONObject> jsonStimulusMap = new HashMap<>();
        for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
            if (!jsonStimulusMap.containsKey(stimulusFreeText.getStimulus())) {
                JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), stimulusFreeText.getStimulus());
                storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
                jsonStimulusMap.put(stimulusFreeText.getStimulus(), storedStimulusJSONObject);
            }
            final String value = stimulusFreeText.getValue();
            if (value != null) {
                jsonStimulusMap.get(stimulusFreeText.getStimulus()).put(stimulusFreeText.getPostName(), new JSONString(value));
            }
        }
        for (Stimulus stimulus : jsonStimulusMap.keySet()) { // we save the current responses here, so that a page reload can pre populate the page when allowed
            localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), stimulus, jsonStimulusMap.get(stimulus));
        }
        StimulusFreeText firstInvalidStimulusFreeText = null;
        for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
            if (stimulusFreeText.isEnabled() && !stimulusFreeText.isValid()) {
                // by checking isValid we also set the on error style for all relevant fields, but we only set the focus on the first
                if (firstInvalidStimulusFreeText == null) {
                    stimulusFreeText.setFocus(true);
                    firstInvalidStimulusFreeText = stimulusFreeText;
                }
            }
        }
        if (firstInvalidStimulusFreeText != null) {
            // if any field is invalid then do not proceed
            return false;
        }
        // @todo: probably good to check if the data has changed before writing to disk
        for (Stimulus stimulus : jsonStimulusMap.keySet()) {
            submissionService.writeJsonData(userResults.getUserData().getUserId().toString(), stimulus.getUniqueId(), jsonStimulusMap.get(stimulus).toString());
        }
        for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
            // @todo: checking the free text boxes is also done in the group stimulus sync code, therefore this should be shared in a single function
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), stimulusFreeText.getDataChannel(), stimulusFreeText.getPostName(), stimulusFreeText.getStimulus().getUniqueId(), stimulusFreeText.getValue(), duration.elapsedMillis());
            final String responseTimes = stimulusFreeText.getResponseTimes();
            if (responseTimes != null) {
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), stimulusFreeText.getDataChannel(), stimulusFreeText.getPostName() + "_ms", stimulusFreeText.getStimulus().getUniqueId(), responseTimes, duration.elapsedMillis());
            }
            Boolean isCorrect = null;
            if (stimulusFreeText.getStimulus().hasCorrectResponses()) {
                final boolean correctness = stimulusFreeText.getStimulus().isCorrect(stimulusFreeText.getValue());
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), stimulusFreeText.getDataChannel(), stimulusFreeText.getPostName(), stimulusFreeText.getStimulus().getUniqueId(), (correctness) ? "correct" : "incorrect", duration.elapsedMillis());
                // if there are correct responses to this stimulus then increment the score
                userResults.getUserData().addPotentialScore(correctness);
                isCorrect = correctness;
            }
            submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), stimulusFreeText.getDataChannel(), stimulusFreeText.getPostName(), stimulusFreeText.getStimulus(), stimulusFreeText.getValue(), isCorrect, duration.elapsedMillis());
        }
        return true;
    }

    protected void prevStimulus(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final boolean repeatIncorrect) {
        nextStimulus(stimulusProvider, currentStimulus, repeatIncorrect, -1);
    }

    protected void nextStimulus(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final boolean repeatIncorrect) {
        nextStimulus(stimulusProvider, currentStimulus, repeatIncorrect, 1);
    }

    private void nextStimulus(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final boolean repeatIncorrect, final int increment) {
        if (!stimulusProvider.getCurrentStimulus().equals(currentStimulus)) {
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, "stale nextStimulus blocked", stimulusProvider.getCurrentStimulus().getUniqueId(), currentStimulus.getUniqueId(), duration.elapsedMillis());
        } else {
            if (groupParticipantService != null) {
                ((ComplexView) simpleView).addText("showStimulus should not be used with the groupParticipantService");
                throw new UnsupportedOperationException("showStimulus should not be used with the groupParticipantService");
            }
            if (!validateStimuliResponses()) {
                return;
            }
            if (repeatIncorrect && userResults.getUserData().isCurrentIncorrect()) {
                stimulusProvider.pushCurrentStimulusToEnd();
            }
            userResults.getUserData().clearCurrentResponse();
//        clearPage();
            showStimulus(stimulusProvider, increment);
        }
    }

    protected void clearPage() {
        clearPage(null);
    }

    protected void clearPage(String styleName) {
        cancelPauseTimers();
        timedStimulusView.stopListeners();
        timedStimulusView.stopTimers();
        timedStimulusView.stopAudio();
        timedStimulusView.stopAllMedia();
//        timedStimulusView.clearDomHandlers();
        timedStimulusView.clearPageAndTimers(styleName);
        nextButtonEventListenerList.clear(); // clear this now to prevent refires of the event
        stimulusFreeTextList.clear();
        stimulusButtonList.clear();
        clearButtonList();
        backEventListeners.clear();
        submissionService.submitTimestamps(userResults.getUserData().getUserId(), timedEventMonitor);
        super.cleanUpPresenterState();
    }

    protected void logMediaTimeStamp(final Stimulus currentStimulus, final String mediaId, final String eventTag) {
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        timedStimulusView.logMediaTimeStamp(formattedMediaId, eventTag, timedEventMonitor);
    }

    protected void playMedia(final String mediaId, Boolean loop, final Stimulus currentStimulus) {
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        timedStimulusView.startMedia(formattedMediaId, loop);
    }

    protected void rewindMedia(final String mediaId, final Stimulus currentStimulus) {
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        timedStimulusView.rewindMedia(formattedMediaId);
    }

    protected void pauseMedia(final String mediaId, final Stimulus currentStimulus) {
        final String formattedMediaId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(mediaId);
        timedStimulusView.stopMedia(formattedMediaId);
    }

    protected void streamGroupCanvas(final Stimulus currentStimulus, final String eventTag, final int dataChannel, final String streamChannels) {
        // TODO: Creates a canvas that is streamed to other members of the group based on the stream communication channels. The stream is terminated when the containing region or page is cleared or when the group network ends.
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, currentStimulus.getUniqueId(), streamChannels, duration.elapsedMillis());
        groupStreamHandler.negotiateCanvas(streamChannels, groupParticipantService.getRequestedPhase(), userResults.getUserData().getUserId(), groupParticipantService.getGroupId(), groupParticipantService.getGroupUUID(), groupParticipantService.getMemberCode(), getSelfTag());
    }

    protected void streamGroupCamera(final Stimulus currentStimulus, final String eventTag, final int dataChannel, final String streamChannels) {
        // TODO: Shares a camera stream to other members of the group based on the stream communication channels. The stream is terminated when the containing region or page is cleared or when the group network ends.
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, currentStimulus.getUniqueId(), streamChannels, duration.elapsedMillis());
        groupStreamHandler.negotiateCamera(streamChannels, groupParticipantService.getRequestedPhase(), userResults.getUserData().getUserId(), groupParticipantService.getGroupId(), groupParticipantService.getGroupUUID(), groupParticipantService.getMemberCode(), getSelfTag());
    }

    /* protected void updateGroupStream(final Stimulus currentStimulus, final String eventTag, final int dataChannel, final StreamState streamState, final StreamTypes streamType) {
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, currentStimulus.getUniqueId(), streamType.name() + "" + streamState.name(), duration.elapsedMillis());

        // TODO: remove this debug output when the GroupStreamHandler is ready
        timedStimulusView.addText("GroupStream " + streamState.name() + " " + streamType.name());
        groupStreamHandler.updateStream(streamState, streamType, groupParticipantService.getRequestedPhase(), userResults.getUserData().getUserId(), groupParticipantService.getGroupId(), groupParticipantService.getGroupUUID(), groupParticipantService.getMemberCode(), getSelfTag());
    } */
    protected void groupResponseStimulusImage(final StimuliProvider stimulusProvider, int percentOfPage, int maxHeight, int maxWidth, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final AnimateTypes animateType = AnimateTypes.none;
        groupResponseStimulusImage(stimulusProvider, percentOfPage, maxHeight, maxWidth, animateType, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener);
    }

    protected void groupResponseStimulusImage(final StimuliProvider stimulusProvider, int percentOfPage, int maxHeight, int maxWidth, final AnimateTypes animateType, final int dataChannel, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        stimulusPresent(stimulusProvider, stimulusProvider.getStimuliFromString(groupParticipantService.getResponseStimulusId()), percentOfPage, maxHeight, maxWidth, animateType, false, null, null, null, dataChannel, loadedStimulusListener, failedStimulusListener, playbackStartedStimulusListener, playedStimulusListener, null);
    }

    protected void sendGroupEndOfStimuli(final StimuliProvider stimulusProvider, final String eventTag) {
        groupParticipantService.messageGroup(groupParticipantService.getRequestedPhase(), 1, null, Integer.toString(stimulusProvider.getCurrentStimulusIndex() + 1), groupParticipantService.getMessageString(), groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getCurrentScore(), "");
//        showStimulusProgress();
    }

    protected void sendGroupStoredMessage(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final int originPhase, final int incrementPhase, String expectedRespondents, final String groupId) {
        final JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
        final String formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString((groupId.isEmpty()) ? "groupMessage" : groupId);
        String messageString = (storedStimulusJSONObject == null) ? "" : storedStimulusJSONObject.containsKey(formattedGroupId) ? storedStimulusJSONObject.get(formattedGroupId).isString().stringValue() : "";
        groupParticipantService.messageGroup(originPhase, incrementPhase, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getCurrentScore(), expectedRespondents);
    }

    protected void sendGroupTokenMessage(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final int originPhase, final int incrementPhase, String expectedRespondents, final String dataLogFormat) {
        final String formattedMessage = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(dataLogFormat);
        groupParticipantService.messageGroup(originPhase, incrementPhase, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), formattedMessage, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getCurrentScore(), expectedRespondents);
    }

    protected void sendGroupMessage(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final int originPhase, final int incrementPhase, String expectedRespondents) {
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), eventTag, (groupParticipantService != null) ? groupParticipantService.getMessageString() : null, duration.elapsedMillis());
        final String uniqueId = (stimulusProvider.getCurrentStimulusIndex() < stimulusProvider.getTotalStimuli()) ? currentStimulus.getUniqueId() : null;
        if (groupParticipantService != null) {
            groupParticipantService.messageGroup(originPhase, incrementPhase, uniqueId, Integer.toString(stimulusProvider.getCurrentStimulusIndex()), groupParticipantService.getMessageString(), groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getCurrentScore(), expectedRespondents);
        }
//        clearPage();
//        timedStimulusView.addText("Waiting for a group response."); // + eventTag + ":" + originPhase + ":" + incrementPhase + ":" + groupParticipantService.getRequestedPhase());
    }

    // @todo: tag pair data and tag data tables could show the number of stimuli show events and the unique stimuli (grouped by tag strings) show events per screen
    protected void sendGroupMessageButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final int dataChannel, final String buttonLabel, final String styleName, final boolean norepeat, final int hotKey, final int originPhase, final int incrementPhase, final String expectedRespondents, final String buttonGroup) {
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "sendGroupMessageButton" : eventTag);
                for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
                    if (stimulusFreeText.isEnabled() && !stimulusFreeText.isValid()) {
                        return;
                    }
                }
                String messageString = "";
                for (StimulusFreeText stimulusFreeText : stimulusFreeTextList) {
                    if (stimulusFreeText.isEnabled()) {
                        messageString += stimulusFreeText.getValue();
                    }
                }
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, (stimulusProvider.getCurrentStimulusIndex() < stimulusProvider.getTotalStimuli()) ? currentStimulus.getUniqueId() : null, messageString, duration.elapsedMillis());
                groupParticipantService.messageGroup(originPhase, incrementPhase, currentStimulus.getUniqueId(), Integer.toString(stimulusProvider.getCurrentStimulusIndex()), messageString, groupParticipantService.getResponseStimulusOptions(), groupParticipantService.getResponseStimulusId(), (int) userResults.getUserData().getCurrentScore(), expectedRespondents);
//                clearPage();
            }
        };
        nextButtonEventListenerList.add(eventListener);
        optionButton(eventListener, buttonGroup);
    }

    protected void addStimulusCodeResponseValidation(final Stimulus currentStimulus, final String validationRegex, final String validationChallenge, final String groupId, final int dataChannel) {
        final String formattedGroupId;
        if (groupId == null || groupId.isEmpty()) {
            formattedGroupId = "CodeResponse";
        } else {
            formattedGroupId = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(groupId);
        }
        stimulusFreeTextList.add(addButtonToGroup(groupId, timedStimulusView.addStimulusValidation(localStorage, userResults.getUserData().getUserId(), currentStimulus, formattedGroupId, validationRegex, validationChallenge, dataChannel)));
    }

    protected void setStimulusCodeResponse(
            final Stimulus currentStimulus,
            final String codeFormat,
            final boolean applyScore,
            final String groupId,
            final int dataChannel
    ) {
        final HtmlTokenFormatter htmlTokenFormatter = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray());
        final String formattedGroupId;
        if (groupId == null || groupId.isEmpty()) {
            formattedGroupId = "CodeResponse";
        } else {
            formattedGroupId = htmlTokenFormatter.formatString(groupId);
        }
        final String formattedCode = htmlTokenFormatter.formatString(codeFormat);
        HashMap<Stimulus, JSONObject> jsonStimulusMap = new HashMap<>();
        if (!jsonStimulusMap.containsKey(currentStimulus)) {
            JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
            storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
            jsonStimulusMap.put(currentStimulus, storedStimulusJSONObject);
        }
        jsonStimulusMap.get(currentStimulus).put(formattedGroupId, new JSONString(formattedCode));
        localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, jsonStimulusMap.get(currentStimulus));
        // @todo: probably good to check if the data has changed before writing to disk
        submissionService.writeJsonData(userResults.getUserData().getUserId().toString(), currentStimulus.getUniqueId(), jsonStimulusMap.get(currentStimulus).toString());
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, formattedGroupId, currentStimulus.getUniqueId(), formattedCode, duration.elapsedMillis());
        Boolean isCorrect = null;
        if (applyScore) {
            if (currentStimulus.hasCorrectResponses()) {
                final boolean correctness = currentStimulus.isCorrect(formattedCode);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, formattedGroupId, currentStimulus.getUniqueId(), (correctness) ? "correct" : "incorrect", duration.elapsedMillis());
                // if there are correct responses to this stimulus then increment the score
                userResults.getUserData().addPotentialScore(correctness);
                isCorrect = correctness;
            }
        }
        submissionService.submitStimulusResponse(userResults.getUserData(), getSelfTag(), dataChannel, formattedGroupId, currentStimulus, formattedCode, isCorrect, duration.elapsedMillis());
    }

    protected void touchInputReportSubmit(final Stimulus currentStimulus, final int dataChannel) {
        if (touchInputCapture != null) {
            final String touchReport = touchInputCapture.getTouchReport(Window.getClientWidth(), Window.getClientHeight());
            submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "touchInputReport", currentStimulus.getUniqueId(), touchReport, duration.elapsedMillis());
//            // todo: perhaps this is a bit heavy on local storage but at least only one touch event would be stored
//            JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus);
//            storedStimulusJSONObject = (storedStimulusJSONObject == null) ? new JSONObject() : storedStimulusJSONObject;
//            storedStimulusJSONObject.put("touchInputReport", new JSONString(touchReport));
//            localStorage.setStoredJSONObject(userResults.getUserData().getUserId(), currentStimulus, storedStimulusJSONObject);
        }
        touchInputCapture = null;
    }

    protected void touchInputCapture(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final int dataChannel, final boolean showDebug, final TimedStimulusListener startOfTouchEventListener, final int msAfterEndOfTouchToNext, final TimedStimulusListener endOfTouchEventListener) {
        // TODO: maybe send report here and start a new capture
        if (touchInputCapture == null) {
            final HTML debugHtmlLabel;
            if (showDebug) {
                debugHtmlLabel = timedStimulusView.addHtmlText("&nbsp;", "footerLabel");
            } else {
                debugHtmlLabel = null;
            }
            touchInputCapture = new TouchInputCapture(endOfTouchEventListener, msAfterEndOfTouchToNext) {
                @Override
                public void setDebugLabel(String debugLabel) {
                    if (debugHtmlLabel != null) {
                        timedStimulusView.addWidget(debugHtmlLabel);
                        debugHtmlLabel.setHTML(debugLabel);
                    }
                }

                @Override
                public void endOfTouchEvent(String groupName) {
                    logTagPairStamp(stimulusProvider, currentStimulus, "endOfTouchEvent", groupName, dataChannel);
                }

            };
            timedStimulusView.addTouchInputCapture(touchInputCapture);
            startOfTouchEventListener.postLoadTimerFired();
        }
    }

    protected void prevStimulusButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final String buttonLabel, final String styleName, final boolean repeatIncorrect, final String buttonGroup) {
        final int hotKey = -1;
        prevStimulusButton(stimulusProvider, currentStimulus, eventTag, buttonLabel, styleName, repeatIncorrect, hotKey, buttonGroup);
    }

    protected void prevStimulusButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final String buttonLabel, final String styleName, final boolean repeatIncorrect, final int hotKey, final String buttonGroup) {
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "prevStimulusButton" : eventTag);
                nextStimulus(stimulusProvider, currentStimulus, repeatIncorrect, -1);
            }
        };
        nextButtonEventListenerList.add(eventListener);
        final StimulusButton prevButton = optionButton(eventListener, buttonGroup);
        prevButton.setEnabled(stimulusProvider.hasNextStimulus(-1));
    }

    protected void nextStimulusButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final String buttonLabel, final String styleName, final boolean repeatIncorrect, final String buttonGroup) {
        final int hotKey = -1;
        nextStimulusButton(stimulusProvider, currentStimulus, eventTag, buttonLabel, styleName, repeatIncorrect, hotKey, buttonGroup);
    }

    protected void nextStimulusButton(final StimuliProvider stimulusProvider, final Stimulus currentStimulus, final String eventTag, final String buttonLabel, final String styleName, final boolean repeatIncorrect, final int hotKey, final String buttonGroup) {
//        if (stimulusProvider.hasNextStimulus()) {
        PresenterEventListener eventListener = new PresenterEventListener() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "nextStimulusButton" : eventTag);
                nextStimulus(stimulusProvider, currentStimulus, repeatIncorrect, 1);
            }
        };
        nextButtonEventListenerList.add(eventListener);
        optionButton(eventListener, buttonGroup);
    }

    protected void audioButton(final String eventTag, final int dataChannel, final String srcString, final String styleName, final String imagePath, final boolean autoPlay, final int hotKey, final String buttonGroup, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener) {
        final String mp3Path = srcString + ".mp3";
        final String oggPath = srcString + ".ogg";
        final String wavPath = srcString + ".wav";
        final PresenterEventListener presenterEventListener = new PresenterEventListener() {
            private boolean hasPlayed = false;

            @Override
            public String getLabel() {
                return imagePath;
            }

            @Override
            public int getHotKey() {
                return hotKey;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                timedEventMonitor.registerEvent((eventTag == null || eventTag.isEmpty()) ? "audioButton" : eventTag);
                final CancelableStimulusListener shownStimulusListener = new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {
                        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, eventTag, "PlayAudio", srcString, duration.elapsedMillis());
                        loadedStimulusListener.postLoadTimerFired();
                    }
                };
                timedStimulusView.addTimedAudio(timedEventMonitor, UriUtils.fromString((oggPath.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + oggPath), UriUtils.fromString((mp3Path.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + mp3Path), UriUtils.fromString((wavPath.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + wavPath), false, shownStimulusListener, failedStimulusListener,
                        new CancelableStimulusListener() {
                    @Override
                    protected void trigggerCancelableEvent() {

                    }
                }, new CancelableStimulusListener() {

                    @Override
                    protected void trigggerCancelableEvent() {
                        if (!hasPlayed) {
                            playedStimulusListener.postLoadTimerFired();
                        }
                        hasPlayed = true;
                    }
                }, true, "audioButton");
            }
        };
        imageButton(presenterEventListener, UriUtils.fromString((imagePath.startsWith("file") ? "" : serviceLocations.staticFilesUrl()) + imagePath), false, buttonGroup);
        if (autoPlay) {
            presenterEventListener.eventFired(null, null);
        }
    }

    @Override
    public void savePresenterState() {
        cancelPauseTimers();
        timedStimulusView.stopListeners();
        timedStimulusView.stopTimers();
        timedStimulusView.stopAudio();
        timedStimulusView.stopAllMedia();
        timedStimulusView.clearDomHandlers();
        if (groupParticipantService != null) {
            groupParticipantService.stopListeners();
        }
        super.cleanUpPresenterState();
        super.savePresenterState();
        stopAudioRecorder();
        timerService.clearAllTimers(); // clear all callbacks in timerService before exiting the presenter
        triggerListeners.clear();
        submissionService.submitTimestamps(userResults.getUserData().getUserId(), timedEventMonitor);
        if (hardwareTimeStamp != null) {
            // terminate any tones and clear the opto indicators when the presenter exits
            hardwareTimeStamp.terminate();
        }
    }
}
