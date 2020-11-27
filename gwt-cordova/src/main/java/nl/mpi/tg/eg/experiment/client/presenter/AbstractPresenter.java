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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.GroupParticipantService;
import nl.mpi.tg.eg.experiment.client.service.LocalNotifications;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.model.ExperimentMetadataFieldProvider;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.SimpleView;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 28, 2014 3:32:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractPresenter implements Presenter {

    protected final Messages messages = GWT.create(Messages.class);
    protected final ExperimentMetadataFieldProvider metadataFieldProvider = new ExperimentMetadataFieldProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    protected final RootLayoutPanel widgetTag;
    final protected ComplexView simpleView;
    private PresenterEventListner backEventListner = null;
    protected final List<TimedStimulusListener> backEventListners = new ArrayList<>();
    private final HashMap<String, ArrayList<StimulusButton>> buttonGroupsList = new HashMap<>();
    private final HashMap<String, ArrayList<StimulusFreeText>> inputGroupsList = new HashMap<>();
    private PresenterEventListner nextEventListner = null;
    private PresenterEventListner windowClosingEventListner = null;
    private final Timer audioTickerTimer;
    protected ApplicationState nextState;
    protected final UserResults userResults;
    protected final LocalStorage localStorage;
    protected final TimerService timerService;
    protected GroupParticipantService groupParticipantService = null;
    private final ArrayList<ValueChangeListener<Double>> audioLevelIndicators = new ArrayList<>();

    public AbstractPresenter(RootLayoutPanel widgetTag, ComplexView simpleView, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        this.widgetTag = widgetTag;
        this.simpleView = simpleView;
        this.userResults = userResults;
        this.timerService = timerService;
        this.localStorage = localStorage;
        audioTickerTimer = new Timer() {
            public void run() {
//                isAudioRecording();
                getAudioRecorderTime();
            }
        };
    }

    @Override
    public void setState(final AppEventListner appEventListner, final ApplicationState prevState, final ApplicationState nextState) {
        this.nextState = nextState;
        widgetTag.clear();
        if (prevState != null) {
            backEventListner = new PresenterEventListner() {

                @Override
                public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                    if (allowBackAction(appEventListner)) {
                        savePresenterState();
                        appEventListner.requestApplicationState(prevState);
                    }
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
                    return prevState.label;
                }
            };
        } else {
            // todo: on android there needs to be a back action available
//            backEventListner = new PresenterEventListner() {
//
//                @Override
//                public void eventFired(ButtonBase button) {
//                    appEventListner.requestApplicationState(ApplicationState.end);
//                }
//
//                @Override
//                public String getLabel() {
//                    return ApplicationState.menu.label;
//                }
//            };
        }
        simpleView.addTitle(getTitle(), backEventListner);

        if (nextState != null) {
            nextEventListner = new PresenterEventListner() {

                @Override
                public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                    savePresenterState();
                    appEventListner.requestApplicationState(nextState);
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
                    return nextState.label;
                }
            };
            simpleView.setButton(SimpleView.ButtonType.next, nextEventListner);
        }
        setContent(appEventListner);
        simpleView.resizeView();
        widgetTag.add(simpleView);
    }

    protected void addText(String textString) {
        simpleView.addText(textString);
    }

    protected void addHtmlText(String textString) {
        simpleView.addHtmlText(textString, null);
    }

    protected void addHtmlText(String textString, String styleName) {
        simpleView.addHtmlText(textString, styleName);
    }

    protected void showHtmlPopup(String textString, final PresenterEventListner... buttonListeners) {
        simpleView.showHtmlPopup(textString, buttonListeners);
    }

    protected void svgLoadGroups(HTML svgHTML) {
        simpleView.addWidget(svgHTML);
    }

    public void targetButton(final PresenterEventListner presenterListerner, final String buttonGroup) {
        addButtonToGroup(buttonGroup, simpleView.addOptionButton(presenterListerner));
    }

    protected void clearButtonList() {
        buttonGroupsList.clear();
        inputGroupsList.clear();
    }

    protected void addButtonToGroup(final String buttonGroup, final List<StimulusButton> stimulusButtonList) {
        for (StimulusButton stimulusButton : stimulusButtonList) {
            addButtonToGroup(buttonGroup, stimulusButton);
        }
    }

    protected StimulusButton addButtonToGroup(final String buttonGroup, final StimulusButton stimulusButton) {
        ArrayList<StimulusButton> buttonList = buttonGroupsList.get(buttonGroup);
        if (buttonList == null) {
            buttonList = new ArrayList<>();
            buttonGroupsList.put(buttonGroup, buttonList);
        }
        buttonList.add(stimulusButton);
        return stimulusButton;
    }

    protected StimulusFreeText addButtonToGroup(final String buttonGroup, final StimulusFreeText stimulusFreeText) {
        ArrayList<StimulusFreeText> inputList = inputGroupsList.get(buttonGroup);
        if (inputList == null) {
            inputList = new ArrayList<>();
            inputGroupsList.put(buttonGroup, inputList);
        }
        inputList.add(stimulusFreeText);
        return stimulusFreeText;
    }

    protected void disableButtonGroup(final String machingRegex) {
        for (String keyString : buttonGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusButton currentButton : buttonGroupsList.get(keyString)) {
                    currentButton.setEnabled(false);
                }
            }
        }
        for (String keyString : inputGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusFreeText stimulusFreeText : inputGroupsList.get(keyString)) {
                    stimulusFreeText.setEnabled(false);
                }
            }
        }
//        simpleView.addText("disableButtonGroup: " + duration.elapsedMillis() + "ms");
    }

    protected void hideButtonGroup(final String machingRegex) {
        for (String keyString : buttonGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusButton currentButton : buttonGroupsList.get(keyString)) {
                    currentButton.setVisible(false);
                }
            }
        }
        for (String keyString : inputGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusFreeText stimulusFreeText : inputGroupsList.get(keyString)) {
                    stimulusFreeText.setVisible(false);
                }
            }
        }
//        simpleView.addText("hideButtonGroup: " + duration.elapsedMillis() + "ms");
    }

    protected void showButtonGroup(final String machingRegex) {
        for (String keyString : buttonGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusButton currentButton : buttonGroupsList.get(keyString)) {
                    currentButton.setVisible(true);
                }
            }
        }
        for (String keyString : inputGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusFreeText stimulusFreeText : inputGroupsList.get(keyString)) {
                    stimulusFreeText.setVisible(true);
                }
            }
        }
//        simpleView.addText("showButtonGroup: " + duration.elapsedMillis() + "ms");
    }

    protected void enableButtonGroup(final String machingRegex) {
        for (String keyString : buttonGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusButton currentButton : buttonGroupsList.get(keyString)) {
                    currentButton.setEnabled(true);
                    currentButton.removeStyleName("optionButtonActivated");
                }
            }
        }
        for (String keyString : inputGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusFreeText stimulusFreeText : inputGroupsList.get(keyString)) {
                    stimulusFreeText.setEnabled(true);
                }
            }
        }
//        simpleView.addText("enableButtonGroup: " + duration.elapsedMillis() + "ms");
    }

    public void hasMetadataValue(final Stimulus currentStimulus, MetadataField metadataField, final String inputRegex, final TimedStimulusListener conditionTrue, final TimedStimulusListener conditionFalse) {
        final String matchingRegex = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(inputRegex);
        final String valueString = userResults.getUserData().getMetadataValue(metadataField);
        if (valueString.matches(matchingRegex)) {
            conditionTrue.postLoadTimerFired();
        } else {
            conditionFalse.postLoadTimerFired();
        }
    }

    public void matchOnEvalTokens(final Stimulus currentStimulus, final String evaluateTokens, final String inputRegex, final TimedStimulusListener conditionTrue, final TimedStimulusListener conditionFalse, final TimedStimulusListener onError) {
        final String matchingRegex = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(inputRegex);
        try {
            final String resultValue = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).evaluateTokensString(evaluateTokens);
            if (resultValue.matches(matchingRegex)) {
                conditionTrue.postLoadTimerFired();
            } else {
                conditionFalse.postLoadTimerFired();
            }
        } catch (EvaluateTokensException exception) {
            onError.postLoadTimerFired();
        }
    }

    protected void backgroundImage(final String imageSrc, String styleName, int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        simpleView.addBackgroundImage((imageSrc == null || imageSrc.isEmpty()) ? null : UriUtils.fromTrustedString((imageSrc.startsWith("file")) ? imageSrc : serviceLocations.staticFilesUrl() + imageSrc), styleName, postLoadMs, timedStimulusListener);
    }

    @Override
    public void fireBackEvent() {
        if (backEventListner != null) {
            backEventListner.eventFired(null, null);
        } else {
            for (TimedStimulusListener listner : backEventListners) {
                listner.postLoadTimerFired();
            }
        }
    }

    @Override
    public void fireResizeEvent() {
        simpleView.resizeView();
    }

    @Override
    public void fireWindowClosing() {
        if (windowClosingEventListner != null) {
            windowClosingEventListner.eventFired(null, null);
        }
    }

    public void setWindowClosingListener(PresenterEventListner windowClosingEventListner) {
        this.windowClosingEventListner = windowClosingEventListner;
    }

    /**
     * called before the back event listener is triggered
     *
     * @param appEventListner
     * @return {@code true} if the back event is to continue
     */
    protected boolean allowBackAction(final AppEventListner appEventListner) {
        return true;
    }

    protected abstract String getTitle();

    protected abstract String getSelfTag();

    protected abstract void setContent(final AppEventListner appEventListner);

    protected void gotoNextPresenter(final AppEventListner appEventListner) {
        savePresenterState();
        Timer timer = new Timer() {
            public void run() {
                appEventListner.requestApplicationState(nextState);
            }
        };
        timer.schedule(100);
    }

    protected void gotoPresenter(final AppEventListner appEventListner, final ApplicationState targetState) {
        savePresenterState();
        Timer timer = new Timer() {
            public void run() {
                appEventListner.requestApplicationState(targetState);
            }
        };
        timer.schedule(100);
    }

    public void hasGetParameter(final AppEventListner appEventListner, final TimedStimulusListener conditionTrue, final TimedStimulusListener conditionFalse, final String getParamName) {
        Timer timer = new Timer() {
            public void run() {
                String paramValue = Window.Location.getParameter(getParamName);
                if (paramValue != null) {
                    conditionTrue.postLoadTimerFired();
                } else {
                    conditionFalse.postLoadTimerFired();
                }
            }
        };
        timer.schedule(100);
    }

    public void requestNotification(final Stimulus currentStimulus, final String messageTitle, final DataSubmissionService dataSubmissionService, final ApplicationState[] targetOptionStates, final MetadataField metadataField, final String dataLogFormat, final TimedStimulusListener errorEventListner, final TimedStimulusListener successEventListner) {
        StringBuilder targetStateJsonBuilder = new StringBuilder();
        targetStateJsonBuilder.append("[");
        for (ApplicationState targetState : targetOptionStates) {
            if (targetStateJsonBuilder.length() > 1) {
                targetStateJsonBuilder.append(",");
            }
            targetStateJsonBuilder.append("{\"id\": \"");
            targetStateJsonBuilder.append(targetState.name());
            targetStateJsonBuilder.append("\", \"title\": \"");
            targetStateJsonBuilder.append(targetState.label);
            targetStateJsonBuilder.append("\", \"launch\": true}");
        }
        targetStateJsonBuilder.append("]");
        JavaScriptObject targetStateJsonData = JsonUtils.safeEval(targetStateJsonBuilder.toString());
        new LocalNotifications() {
            @Override
            protected void setNotificationSucceded() {
                successEventListner.postLoadTimerFired();
            }

            @Override
            protected void setNotificationFailed() {
                errorEventListner.postLoadTimerFired();
            }

            @Override
            protected void logNotificationRequest(String debugValue) {
                dataSubmissionService.submitImmediateTimestamp(userResults.getUserData().getUserId(), debugValue, 0);
            }
        }.requestNotification(messageTitle, dataLogFormat, targetStateJsonData, userResults.getUserData().getMetadataValue(metadataField));
        ((ComplexView) simpleView).addPadding();
    }

    protected void bumpAudioTicker() {
        audioTickerTimer.schedule(100);
    }

    protected void audioOk(Boolean isRecording, String message) {
        if (simpleView instanceof ComplexView) {
            ((ComplexView) simpleView).setRecorderState(message, isRecording);
//            if (isRecording) {
            bumpAudioTicker();
//            }
        }
    }

    protected void audioError(String message) {
        if (simpleView instanceof ComplexView) {
            ((ComplexView) simpleView).setRecorderState(message, false);
//            ((ComplexView) simpleView).clearPage();
//            ((ComplexView) simpleView).addText("Could not start the audio recorder");
            bumpAudioTicker();
        }
    }

//    protected void startAudioRecorderFailed(String message) {
//        backEventListner.eventFired(null, null);
//        ((ComplexView) simpleView).clearPage();
//        ((ComplexView) simpleView).addText("Could not start the audio recorder");
//        ((ComplexView) simpleView).addText(message);
//    }
    protected native void startAudioRecorderApp(final String subDirectoryName, final String directoryName, boolean filePerStimulus, final String stimulusIdString, final String userIdString, final String screenName, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) /*-{
        var abstractPresenter = this;
        console.log("startAudioRecorder: " + subDirectoryName + " : " + directoryName + " : " + stimulusIdString + " : " + userIdString);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.record(function (tagvalue) {
                console.log("startAudioRecorderOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, tagvalue);
                onSuccess.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
            }, function (tagvalue) {
                console.log("startAudioRecorderError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
                onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
            },  subDirectoryName, directoryName,  (filePerStimulus)?stimulusIdString:'');
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        }
     }-*/;

    protected native void listAudioDevicesWeb(final String deviceRegex, final DeviceListingListener deviceListingListener) /*-{
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            navigator.mediaDevices.enumerateDevices().then(function (deviceInfos) {
                for (var index = 0; (index < deviceInfos.length); index++) {
                    var deviceInfo = deviceInfos[index];
                    console.log("deviceInfo: " + deviceInfo.label + " : " + deviceInfo.kind + " match: " + deviceInfo.label.search(deviceRegex));
                    if (deviceInfo.kind === 'audioinput' && deviceInfo.label.search(deviceRegex) >= 0){
                        deviceListingListener.@nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener::deviceFound(Ljava/lang/String;)(deviceInfo.label);
                    }
                }
                deviceListingListener.@nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener::listingComplete()();
            });
        } else {
            deviceListingListener.@nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener::listingFailed()();
        }
     }-*/;

    public boolean updateLevelMeter(Double updatedVale) {
        boolean hasListeners = false;
        for (final ValueChangeListener<Double> changeListener : audioLevelIndicators) {
            changeListener.<Double>onValueChange(updatedVale);
            hasListeners = true;
        }
        return hasListeners;
    }

    protected void addRecorderLevelIndicatorWeb(final ValueChangeListener<Double> changeListener) {
        audioLevelIndicators.add(changeListener);
        addRecorderLevelCallbackWeb();
    }

    protected void removeRecorderLevelIndicatorWeb() {
        audioLevelIndicators.clear();
    }

    protected native void addRecorderLevelCallbackWeb()/*-{
         var abstractPresenter = this;
        //$wnd.attachLevelMeter(function(){changeListener.@nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener::onValueChange(Ljava/lang/Object;)(updatedValue)});
        if ($wnd.recorder) {
            audioAnalyser = $wnd.recorder.audioContext.createAnalyser();
            audioAnalyser.fftSize = 2048;
            bufferLength = audioAnalyser.frequencyBinCount;
            dataArray = new Uint8Array(bufferLength);
            $wnd.recorder.sourceNode.connect(audioAnalyser);
            lastValue = 0;
            function updateLevelIndicator() {
                audioAnalyser.getByteTimeDomainData(dataArray);
                sumSqrValues = 0;
                for (var bufferIndex = 0; bufferIndex < bufferLength; bufferIndex++) {                   
                    var currentValue = dataArray[bufferIndex] - 128;
                    sumSqrValues += currentValue * currentValue;
                }
                rmsValue = Math.sqrt(sumSqrValues / bufferLength);
                smoothedValue = rmsValue;
                if (rmsValue < lastValue) {
                    smoothedValue += (lastValue - rmsValue) * 0.9;
                }
                lastValue = smoothedValue;
                //console.log(dataArray);
                percentValue = smoothedValue * (100 / 127);
                //console.log(percentValue);
                //abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::updateLevelMeter(Lnl/mpi/tg/eg/experiment/client/listener/ValueChangeListener;Ljava/lang/Double;)(changeListener);//, rmsValue
                //changeListener.@nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener::onValueChange(Ljava/lang/Object;)(rmsValue);
                hasListeners = abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::updateLevelMeter(Ljava/lang/Double;)(percentValue);
                //console.log(hasListeners);
                if(hasListeners === true) {
                    requestAnimationFrame(updateLevelIndicator);
                }
            }
            requestAnimationFrame(updateLevelIndicator());
        }
    }-*/;

    protected native void startAudioRecorderWeb(final DataSubmissionService dataSubmissionService, final String recordingLabelString, final String deviceRegex, final String stimulusIdString, final String userIdString, final String screenName, final MediaSubmissionListener mediaSubmissionListener, final int downloadPermittedWindowMs, final String recordingFormat) /*-{
            var abstractPresenter = this;
            if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            console.log("isRecordingSupported");
            $wnd.recordingLabelString = recordingLabelString;
//            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::addText(Ljava/lang/String;)("(debug) enumerateDevices");
            console.log("enumerateDevices: ");
            var firstDeviceId = -1;
            var firstDeviceLabel = -1;
            var targetDeviceId = -1;
            var targetDeviceLabel = -1;
            navigator.mediaDevices.enumerateDevices().then(function (deviceInfos) {
                // it is likely that the first item in the list is the default input, so we stop on the first matching device
                for (var index = 0; (index < deviceInfos.length && targetDeviceId === -1); index++) {
                    var deviceInfo = deviceInfos[index];
//                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::addText(Ljava/lang/String;)("(debug) " + deviceInfo.label.search(deviceRegex));    
                    console.log("deviceInfo: " + deviceInfo.label + " : " + deviceInfo.kind + " match: " + deviceInfo.label.search(deviceRegex));
                    if(deviceInfo.kind === 'audioinput' && firstDeviceId === -1){
                        firstDeviceId = deviceInfo.deviceId;
                        firstDeviceLabel = deviceInfo.label;
                    }
                    if(deviceInfo.kind === 'audioinput' && deviceInfo.label.search(deviceRegex) >= 0){
                        console.log(deviceInfo.kind);            
                        console.log(deviceInfo.label);                    
                        console.log(deviceInfo.deviceId);  
                        targetDeviceId = deviceInfo.deviceId;
                        targetDeviceLabel = deviceInfo.label;
                    }
                }
                //abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, "isRecordingSupported");
                if ($wnd.recorder) {
                    $wnd.recorder.stop();
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::removeRecorderLevelIndicatorWeb()();
                }
                if(targetDeviceId === -1) {
                    console.log("Device not found, defaulting to first device seen.");
                    targetDeviceId = firstDeviceId;
                    targetDeviceLabel = firstDeviceLabel;
                }
                if(targetDeviceId === -1) {
                    console.log("Device not found: " + deviceRegex);
                    mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)("Device not found: " + deviceRegex);
                } else {
                    if (recordingFormat === 'wav') {
                        $wnd.recorder = new $wnd.Recorder({numberOfChannels: 1, encoderPath: "opus-recorder/waveWorker.min.js", monitorGain: 0, recordingGain: 1, encoderSampleRate: 48000, wavBitDepth: 16, mediaTrackConstraints: {deviceId: targetDeviceId}, bufferLength: 1024});
                    } else {
                        $wnd.recorder = new $wnd.Recorder({numberOfChannels: 1, encoderPath: "opus-recorder/encoderWorker.min.js", monitorGain: 0, recordingGain: 1, encoderSampleRate: 48000, mediaTrackConstraints: {deviceId: targetDeviceId}, bufferLength: 1024});
                    }
                    $wnd.recorder.ondataavailable = function( typedArray ){
                        // console.log("ondataavailable: " + typedArray.length);
                        dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::submitAudioData(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/typedarrays/shared/Uint8Array;Lnl/mpi/tg/eg/experiment/client/listener/MediaSubmissionListener;Ljava/lang/Integer;Ljava/lang/String;)(userIdString, screenName, stimulusIdString, typedArray, mediaSubmissionListener, downloadPermittedWindowMs, recordingFormat);
                    };
                    try {
                        $wnd.startRecorder(function(){mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderStarted(Ljava/lang/String;)(targetDeviceLabel)}, function(errorMessage){mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(errorMessage)});
                        // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, $wnd.recorder.state);
                        //$wnd.recorder.start();
                    } catch(e) {
                        console.log(e.message);
                        mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(e.message);
                        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
                    };
                }
            });
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void requestRecorderPermissions() /*-{
        var abstractPresenter = this;
        console.log("requestRecorderPermissions");
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.requestRecorderPermissions(function () {
                console.log("requestRecorderPermissionsOk");
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, null);
            }, function (tagvalue) {
                console.log("requestRecorderPermissionsError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            });
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void requestFilePermissions() /*-{
        var abstractPresenter = this;
        console.log("requestFilePermissions");
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.requestFilePermissions(function () {
                console.log("requestFilePermissionsOk");
//                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, null);
            }, function (tagvalue) {
                console.log("requestFilePermissionsError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            });
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void isAudioRecording() /*-{
        var abstractPresenter = this;
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.isRecording(function () {
//                console.log("isAudioRecording");
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, null);
            }, function (tagvalue) {
//                console.log("isAudioRecording: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::FALSE, null);
            });
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void getAudioRecorderTime() /*-{
        var abstractPresenter = this;
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.getTime(function (currentTime) {
//                console.log("isAudioRecording: " + " : " + currentTime);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, currentTime);
            }, function (tagvalue) {
//                console.log("isAudioRecording: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::FALSE, null);
            });
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            if ($wnd.recorder.state === 'recording') {
                if ($wnd.recordingLabelString == '00:00:00') {
                    var recordingMilliSeconds = Math.floor($wnd.recorder.encodedSamplePosition / 48);
                    var recordingTimeDate = new Date(recordingMilliSeconds);
                    var recordingTimeString = recordingTimeDate.toISOString().substr(11, 8);
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, recordingTimeString);
                } else {
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, $wnd.recordingLabelString);
                }
            } else {
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void stopAudioRecorder() /*-{
        var abstractPresenter = this;
        console.log("stopAudioRecorder");
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.stop(function (tagvalue) {
                console.log("stopAudioRecorderOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::FALSE, tagvalue);
            }, function (tagvalue) {
                console.log("stopAudioRecorderError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            });
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                $wnd.recorder.stop();
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::FALSE, null);
                $wnd.recorder = null;
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::removeRecorderLevelIndicatorWeb()();
     }-*/;

    static public native void pauseAudioRecorderWeb() /*-{
        console.log("pauseAudioRecorderWeb");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                $wnd.recorder.pause();
            }
        }
     }-*/;

    static public native void resumeAudioRecorderWeb() /*-{
        console.log("resumeAudioRecorderWeb");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                $wnd.recorder.resume();
            }
        }
     }-*/;

    static public native void logAudioRecorderWebTimeStamp(String eventTag, final TimedEventMonitor timedEventMonitor) /*-{
        console.log("logAudioRecorderWebTimeStamp");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                // note that this assumes the bit rate of 48000 which is expected with this encoder
                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)(eventTag, $wnd.recorder.encodedSamplePosition / 48);
            }
        }
     }-*/;

    protected native void startAudioRecorderTag(int tier, final TimedEventMonitor timedEventMonitor) /*-{
        var abstractPresenter = this;
        console.log("startAudioRecorderTag: " + tier);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.startTag(function (tagvalue) {
                console.log("startAudioRecorderTagOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, tagvalue);
            }, function (tagvalue) {
                console.log("startAudioRecorderTagError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            }, tier);
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            // note that this assumes the bit rate of 48000 which is expected with this encoder
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)("startAudioRecorderTag", $wnd.recorder.encodedSamplePosition / 48);
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void endAudioRecorderTag(int tier, String stimulusId, String stimulusCode, String eventTag, final TimedEventMonitor timedEventMonitor) /*-{
        var abstractPresenter = this;
        console.log("endAudioRecorderTag: " + tier + " : " + stimulusId + " : " + stimulusCode + " : " + eventTag);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.endTag(function (tagvalue) {
                console.log("endAudioRecorderTagOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::TRUE, tagvalue);
            }, function (tagvalue) {
                console.log("endAudioRecorderTagError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            }, tier, stimulusId, stimulusCode, eventTag);
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            // note that this assumes the bit rate of 48000 which is expected with this encoder
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)(eventTag, $wnd.recorder.encodedSamplePosition / 48);
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    @Override
    public void savePresenterState() {
        if (simpleView instanceof ComplexView) {
            ((ComplexView) simpleView).clearDomHandlers();
        }
    }

}
