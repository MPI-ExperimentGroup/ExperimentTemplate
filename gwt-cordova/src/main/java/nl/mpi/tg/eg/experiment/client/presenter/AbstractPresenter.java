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

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.ButtonGroupMember;
import nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener;
import nl.mpi.tg.eg.experiment.client.listener.FrameTimeTrigger;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.RecorderDtmfListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleStimulusListener;
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
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp.DTMF;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;
import nl.mpi.tg.eg.experiment.client.util.AbstractRecorder;
import nl.mpi.tg.eg.experiment.client.util.AudioRecorder;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.util.VideoRecorder;
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
    private final HashMap<String, ArrayList<ButtonGroupMember>> buttonGroupsList = new HashMap<>();
    private final HashMap<String, ArrayList<StimulusFreeText>> inputGroupsList = new HashMap<>();
    private PresenterEventListner nextEventListner = null;
    private PresenterEventListner windowClosingEventListner = null;
    private final Timer audioTickerTimer;
    protected ApplicationState nextState;
    protected final UserResults userResults;
    protected final LocalStorage localStorage;
    protected final TimerService timerService; // todo: this maybe needs to be avaiable in all presenter types
    protected GroupParticipantService groupParticipantService = null;
    protected GroupStreamHandler groupStreamHandler = null;
    private final ArrayList<ValueChangeListener<Double>> audioLevelIndicators = new ArrayList<>();
    private final RecorderDtmfListener recorderDtmfListener = new RecorderDtmfListener();
    private final MediaTriggerListener recorderMediaTriggerListener = new MediaTriggerListener();
    private HardwareTimeStamp toneGenerator = null; // note that this toneGenerator instance of HardwareTimeStamp is different from the hardwareTimeStamp used in AbstractStimulusPresenter although the tone generator objects are shared
    protected AbstractRecorder mediaRecorder = new AudioRecorder();

    public AbstractPresenter(RootLayoutPanel widgetTag, ComplexView simpleView, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        this.widgetTag = widgetTag;
        this.simpleView = simpleView;
        this.userResults = userResults;
        this.timerService = timerService;
        this.localStorage = localStorage;
        audioTickerTimer = new Timer() {
            public void run() {
//                isAudioRecording();
                mediaRecorder.getRecorderTime(AbstractPresenter.this);
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
        simpleView.addText(textString, null);
    }

    protected void addText(String textString, XmlId xmlId) {
        simpleView.addText(textString, xmlId);
    }

    protected void addHtmlText(String textString, XmlId xmlId) {
        simpleView.addHtmlText(textString, null, xmlId);
    }

    protected void addHtmlText(String textString, String styleName, XmlId xmlId) {
        simpleView.addHtmlText(textString, styleName, xmlId);
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

    public void hotKeyInput(final int hotKey, final String buttonGroup, final TimedStimulusListener onKeyDownListener, final TimedStimulusListener onKeyUpListener) {
        ArrayList<ButtonGroupMember> buttonList = buttonGroupsList.get(buttonGroup);
        if (buttonList == null) {
            buttonList = new ArrayList<>();
            buttonGroupsList.put(buttonGroup, buttonList);
        }
        final ButtonGroupMember buttonGroupMember = new ButtonGroupMember() {
            private boolean enabledState = true;

            @Override
            public void addStyleName(String styleName) {
            }

            @Override
            public void removeStyleName(String styleName) {
            }

            @Override
            public void setEnabled(boolean enabled) {
                enabledState = enabled;
            }

            @Override
            public boolean isEnabled() {
                return enabledState;
            }

            @Override
            public void setVisible(boolean visible) {
            }
        };
        buttonList.add(buttonGroupMember);
        simpleView.addHotKeyListner(hotKey, buttonGroupMember, onKeyDownListener, onKeyUpListener);
    }

    protected void addButtonToGroup(final String buttonGroup, final List<StimulusButton> stimulusButtonList) {
        for (StimulusButton stimulusButton : stimulusButtonList) {
            // TODO: this <ratingValue> feature needs to be documented for stimus rating buttons, radio etc
            addButtonToGroup(buttonGroup.replaceAll("<ratingValue>", stimulusButton.getValue()), stimulusButton);
        }
    }

    protected StimulusButton addButtonToGroup(final String buttonGroup, final StimulusButton stimulusButton) {
        ArrayList<ButtonGroupMember> buttonList = buttonGroupsList.get(buttonGroup);
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
                for (ButtonGroupMember currentButton : buttonGroupsList.get(keyString)) {
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
                for (ButtonGroupMember currentButton : buttonGroupsList.get(keyString)) {
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

    protected void requestFocus(final String machingRegex) {
        for (String keyString : inputGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (StimulusFreeText stimulusFreeText : inputGroupsList.get(keyString)) {
                    stimulusFreeText.setFocus(true);
                }
            }
        }
    }

    protected void showButtonGroup(final String machingRegex) {
        for (String keyString : buttonGroupsList.keySet()) {
            if (keyString.matches(machingRegex)) {
                for (ButtonGroupMember currentButton : buttonGroupsList.get(keyString)) {
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
                for (ButtonGroupMember currentButton : buttonGroupsList.get(keyString)) {
                    currentButton.setEnabled(true);
                    // TODO: 22/09/2022 it is unclear why optionButtonActivated was cleared at this point, but it is causing issues for checkbox enabling with values
//                    currentButton.removeStyleName("optionButtonActivated");
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

    Timer inaccurateToneOffTimer = null;

    protected void dtmfTone(final DTMF dtmfCode, final int msToNext) {
        if (toneGenerator == null) {
            toneGenerator = new HardwareTimeStamp(null, true);
        }
        if (inaccurateToneOffTimer != null) {
            inaccurateToneOffTimer.cancel();
            inaccurateToneOffTimer = null;
        }
        toneGenerator.setDtmf(dtmfCode);
        if (msToNext > 0) {
            inaccurateToneOffTimer = new Timer() {
                public void run() {
                    // there will still be cases where the hardware timestamps might have set the same DTMF code and this timer could have an effect on the duration.
                    if (toneGenerator.getCurrentTone() == dtmfCode) {
                        toneGenerator.setDtmf(DTMF.codeoff);
                    }
                }
            };
            inaccurateToneOffTimer.schedule(msToNext);
        }
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

    // it is possible for the XML trigger requestApplicationState multiple times concurrently so we use this timer to reduce the risk of duplicate audio and other negative effects
    private Timer nextStateTimer = null;

    protected void gotoNextPresenter(final AppEventListner appEventListner) {
        if (nextStateTimer != null) {
            nextStateTimer.cancel();
        }
        savePresenterState();
        nextStateTimer = new Timer() {
            public void run() {
                appEventListner.requestApplicationState(nextState);
            }
        };
        nextStateTimer.schedule(100);
    }

    protected void gotoPresenter(final AppEventListner appEventListner, final ApplicationState targetState) {
        if (nextStateTimer != null) {
            nextStateTimer.cancel();
        }
        savePresenterState();
        nextStateTimer = new Timer() {
            public void run() {
                appEventListner.requestApplicationState(targetState);
            }
        };
        nextStateTimer.schedule(100);
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
            if ("hidden".equals(message)) {
                ((ComplexView) simpleView).clearRecorderIndicator();
            } else {
                ((ComplexView) simpleView).setRecorderState(message, isRecording);
            }
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
        // first we trigger the request to record because when permission is not given then the list is always empty
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            $wnd.requestPermissions(false, true,
            function() {
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
            }, function(errorMessage) {
                deviceListingListener.@nl.mpi.tg.eg.experiment.client.listener.DeviceListingListener::listingFailed()();
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

    public boolean updateDtmfListener(Double row, Double column) {
        boolean hasListeners = recorderDtmfListener.triggerOnMatching(row.intValue(), column.intValue());
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
        if ($wnd.recorder || $wnd.audioAnalyser) {
            if (!$wnd.audioAnalyser) {
                $wnd.audioAnalyser = $wnd.recorder.audioContext.createAnalyser();
                $wnd.audioAnalyser.fftSize = 2048;
                $wnd.recorder.sourceNode.connect($wnd.audioAnalyser);
            }
            bufferLength = $wnd.audioAnalyser.frequencyBinCount;
            dataArray = new Uint8Array(bufferLength);
            lastValue = 0;
            console.log("start updateLevelIndicator");
            function updateLevelIndicator() {
                if ($wnd.recorder && $wnd.audioAnalyser) {
                    $wnd.audioAnalyser.getByteTimeDomainData(dataArray);
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
                    var hasListeners = abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::updateLevelMeter(Ljava/lang/Double;)(percentValue);
                    //console.log(hasListeners);
                    if(hasListeners === true) {
                        requestAnimationFrame(updateLevelIndicator);
                    } else {
                        console.log("end updateLevelIndicator no more listeners");
                    }
                } else {
                    console.log("end updateLevelIndicator");
                }
            }
            requestAnimationFrame(updateLevelIndicator);
        }
    }-*/;

    protected void clearRecorderTriggersWeb() {
        recorderMediaTriggerListener.clearTriggers();
        recorderDtmfListener.clearTriggers();
    }

    protected void addRecorderTriggersWeb(final FrameTimeTrigger triggerListener) {
        if (recorderMediaTriggerListener.addMediaTriggerListener(triggerListener)) {
            mediaRecorder.startRecorderTriggersWeb(recorderMediaTriggerListener);
        }
    }

    protected void addRecorderDtmfTrigger(final Stimulus definitionScopeStimulus, HardwareTimeStamp.DTMF triggerCode, SingleStimulusListener triggerListener) {
        final SingleStimulusListener definitionScopeStimulusListener = new SingleStimulusListener() {
            @Override
            public void postLoadTimerFired(final Stimulus triggerScopeStimulus) {
                // the web recorder does not have stimulus in scope so we rely on the definition scope stimulus
                triggerListener.postLoadTimerFired(definitionScopeStimulus);
            }
        };
        if (recorderDtmfListener.addDtmfListener(triggerCode, definitionScopeStimulusListener)) {
            if (simpleView.hasRegion("dtmfFrequencyCanvas")) {
                final Canvas frequencyCanvas = Canvas.createIfSupported();
                if (frequencyCanvas != null) {
                    frequencyCanvas.getCanvasElement().setId("frequencyCanvas");
                    frequencyCanvas.setCoordinateSpaceHeight(256);
                    // we are only visualising the lower half of the spectrum here so 1024 is enough
                    frequencyCanvas.setCoordinateSpaceWidth(1024);
                    frequencyCanvas.setSize("1024px", "256px");
                    simpleView.clearRegion("dtmfFrequencyCanvas");
                    final InsertPanel.ForIsWidget canvasRegion = simpleView.startRegion("dtmfFrequencyCanvas", null);
                    simpleView.addWidget(frequencyCanvas);
                    simpleView.endRegion(canvasRegion);
                }
            }
            mediaRecorder.startRecorderDtmfTriggersWeb(this, recorderMediaTriggerListener);
        }
    }

    protected void clearVideoRecorderPreview() {
        if (simpleView.hasRegion("VideoRecorderPreview")) {
            simpleView.clearRegion("VideoRecorderPreview");
            final InsertPanel.ForIsWidget videoRecorderPreview = simpleView.startRegion("VideoRecorderPreview", null);
            final HTML videoRecorderPreviewContainer = new HTML();
            videoRecorderPreviewContainer.getElement().setId("VideoRecorderPreview");
            simpleView.addWidget(videoRecorderPreviewContainer);
            simpleView.endRegion(videoRecorderPreview);
        }
    }

    protected void requestFilePermissions() {
        mediaRecorder.requestFilePermissions(this);
    }

    protected native void templateFeature(String presenterName, String domId, String featureAttribute, String jsonPath, String instructionalText)/*-{
        console.log("domId: " + domId);
        console.log("featureAttribute: " + featureAttribute);
        console.log("jsonPath: " + jsonPath);
        console.log("instructionalText: " + instructionalText);
            if (typeof $wnd.templateFeatureHandler !== 'undefined') {
                // TODO: add an onClick handler to the element so that when clicked the text becomes editable and on focus lost send the updated text to the wizard code
                // TODO: when editing
                $wnd.templateFeatureHandler(presenterName, domId, featureAttribute, jsonPath, instructionalText);
            }
    }-*/;

    protected void cleanUpPresenterState() {
        if (simpleView instanceof ComplexView) {
            ((ComplexView) simpleView).clearDomHandlers();
        }
        if (toneGenerator != null) {
            // terminate any tones when the presenter exits
            toneGenerator.terminate();
        }
    }
}
