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
package nl.mpi.tg.eg.experiment.client.service;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.typedarrays.shared.Uint8Array;
import com.google.gwt.user.client.Timer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.TimedEvent;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jul 2, 2015 5:17:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DataSubmissionService extends AbstractSubmissionService {

    private enum ServiceEndpoint {

        timeStamp, screenChange, tagEvent, tagPairEvent, metadata, stowedData, groupEvent, stimulusResponse
    }
    private final LocalStorage localStorage;
    private final String experimentName;
    final DateTimeFormat format = DateTimeFormat.getFormat(messages.jsonDateFormat());

    public DataSubmissionService(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.experimentName = messages.appNameInternal();
    }

    @Override
    public boolean isProductionVersion() {
        boolean dataSubmitUrlOk = serviceLocations.dataSubmitUrl().contains("ems12") || serviceLocations.dataSubmitUrl().contains("frinexproduction");
        boolean groupServerUrlOk = serviceLocations.groupServerUrl().contains("ems12") || serviceLocations.groupServerUrl().contains("frinexproduction");
//        boolean registrationUrlOk = serviceLocations.registrationUrl().contains("www.mpi.nl");
//        boolean staticFilesUrlOk = serviceLocations.staticFilesUrl().contains("ems12");
        return dataSubmitUrlOk && groupServerUrlOk; // && staticFilesUrlOk;
    }

    public String getCompletionCode(UserId userId) {
        // todo: this should be generated on the server rather than on the client
        String completionCode = localStorage.getCompletionCode(userId);
        if (completionCode == null) {
            final Random random = new Random();
            final StringBuffer stringBuffer = new StringBuffer();
            while (stringBuffer.length() < 12) {
                stringBuffer.append(Integer.toHexString(random.nextInt(16)));
            }
            completionCode = stringBuffer.toString();
            localStorage.saveCompletionCode(userId, completionCode);
        }
        return completionCode;
    }

    private String getAudioSubmitPath() {
        return serviceLocations.dataSubmitUrl() + "audioBlob";
    }

    public native void submitAudioData(final String userIdString, final String screenName, final String stimulusIdString, final Uint8Array dataArray, final MediaSubmissionListener mediaSubmissionListener, final Integer downloadPermittedWindowMs, final String audioType) /*-{
        var dataBlob = new Blob([dataArray], {type: 'audio/' + audioType});
        var xhr = new XMLHttpRequest();
        xhr.onload = function() {
            if(xhr.readyState === 4) {
                if(xhr.status === 200) {
                    var urlAudioData = URL.createObjectURL(new Blob([dataArray], {type: 'audio/' + audioType}));
                    mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::submissionComplete(Ljava/lang/String;Ljava/lang/String;)(xhr.responseText,urlAudioData);
                } else {
                    mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::submissionFailed(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/typedarrays/shared/Uint8Array;)(xhr.status + ' ' + xhr.statusText, userIdString, screenName, stimulusIdString, dataArray);
                }
            }
        };
        xhr.onerror = function() {
            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::submissionFailed(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/typedarrays/shared/Uint8Array;)(xhr.status + ' ' + xhr.statusText, userIdString, screenName, stimulusIdString, dataArray);
        }
        var formData = new FormData();
        formData.append("userId", userIdString);
        formData.append("screenName", screenName);
        formData.append("stimulusId", stimulusIdString);
        formData.append("audioType", audioType);
        formData.append("downloadPermittedWindowMs", downloadPermittedWindowMs);
        formData.append("dataBlob", dataBlob);
        xhr.open("POST", this.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::getAudioSubmitPath()(), true);
        xhr.send(formData);
     }-*/;

    public void submitMetadata(final UserResults userResults, final DataSubmissionListener dataSubmissionListener) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (MetadataField key : userResults.getUserData().getMetadataFields()) {
            String value = jsonEscape(userResults.getUserData().getMetadataValue(key));
            stringBuilder.append(jsonEscape(key.getPostName())).append(": ").append(value).append(",\n");
        }
        stringBuilder.append("\"userId\": \"").append(userResults.getUserData().getUserId()).append("\"\n}");
        //localStorage.addStoredScreenData(userResults.getUserData().getUserId(), ServiceEndpoint.metadata.name(), stringBuilder.toString());
        submitData(ServiceEndpoint.metadata, userResults.getUserData().getUserId(), "[" + stringBuilder.toString() + "]", dataSubmissionListener);
        writeJsonData(userResults.getUserData().getUserId().toString(), "metadata", stringBuilder.toString());
    }

    public void submitTagValue(final UserId userId, final String screenName, String eventTag, String tagValue, int eventMs) {
        submitData(ServiceEndpoint.tagEvent, userId, "{\"tagDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"userId\": " + jsonEscape(userId.toString()) + ",\n"
                + "\"screenName\": " + jsonEscape(screenName) + ",\n"
                + "\"eventTag\": " + jsonEscape(eventTag) + ",\n"
                + "\"tagValue\": " + jsonEscape(tagValue) + ",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
    }

    public void submitTagPairValue(final UserId userId, final String screenName, final int dataChannel, String eventTag, String tagValue1, String tagValue2, int eventMs) {
        submitData(ServiceEndpoint.tagPairEvent, userId, "{\"tagDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"userId\": " + jsonEscape(userId.toString()) + ",\n"
                + "\"screenName\": " + jsonEscape(screenName) + ",\n"
                + "\"dataChannel\": " + dataChannel + ",\n"
                + "\"eventTag\": " + jsonEscape(eventTag) + ",\n"
                + "\"tagValue1\": " + jsonEscape(tagValue1) + ",\n"
                + "\"tagValue2\": " + jsonEscape(tagValue2) + ",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
        boolean containsChannel = false;
        for (int channel : ApplicationController.SDCARD_DATACHANNELS) {
            if (dataChannel == channel) {
                containsChannel = true;
            }
        }
        if (containsChannel) {
            writeCsvLine(userId.toString(), screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs);
        }
    }

    public void submitStimulusResponse(final UserData userData, final String screenName, final int dataChannel, final String responseGroup, Stimulus stimulus, String response, Boolean isCorrect, int eventMs) {
        submitData(ServiceEndpoint.stimulusResponse, userData.getUserId(), "{\"tagDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"userId\": " + jsonEscape(userData.getUserId().toString()) + ",\n"
                + "\"screenName\": " + jsonEscape(screenName) + ",\n"
                + "\"dataChannel\": " + dataChannel + ",\n"
                + "\"responseGroup\": " + jsonEscape(responseGroup) + ",\n"
                + "\"stimulusId\": " + ((stimulus != null) ? jsonEscape(stimulus.getUniqueId()) : "\"\"") + ",\n"
                + "\"response\": " + jsonEscape(response) + ",\n"
                + "\"isCorrect\": " + ((isCorrect != null) ? isCorrect.toString() : "null") + ",\n"
                + "\"gamesPlayed\": " + jsonEscape(Integer.toString(userData.getGamesPlayed())) + ",\n"
                + "\"totalScore\": " + jsonEscape(Integer.toString(userData.getTotalScore())) + ",\n"
                + "\"totalPotentialScore\": " + jsonEscape(Integer.toString(userData.getTotalPotentialScore())) + ",\n"
                + "\"currentScore\": " + jsonEscape(Integer.toString(userData.getCurrentScore())) + ",\n"
                + "\"correctStreak\": " + jsonEscape(Integer.toString(userData.getCorrectStreak())) + ",\n"
                + "\"errorStreak\": " + jsonEscape(Integer.toString(userData.getErrorStreak())) + ",\n"
                + "\"potentialScore\": " + jsonEscape(Integer.toString(userData.getPotentialScore())) + ",\n"
                + "\"maxScore\": " + jsonEscape(Double.toString(userData.getMaxScore())) + ",\n"
                + "\"maxErrors\": " + jsonEscape(Integer.toString(userData.getMaxErrors())) + ",\n"
                + "\"maxCorrectStreak\": " + jsonEscape(Integer.toString(userData.getMaxCorrectStreak())) + ",\n"
                + "\"maxErrorStreak\": " + jsonEscape(Integer.toString(userData.getMaxErrorStreak())) + ",\n"
                + "\"maxPotentialScore\": " + jsonEscape(Integer.toString(userData.getMaxPotentialScore())) + ",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
//        boolean containsChannel = false;
//        for (int channel : ApplicationController.SDCARD_DATACHANNELS) {
//            if (dataChannel == channel) {
//                containsChannel = true;
//            }
//        }
//        if (containsChannel) {
//            writeCsvLine(userData.getUserId().toString(), screenName, dataChannel, stimulus.getUniqueId(), response, correctness, eventMs);
//        }
    }

    private String jsonEscape(String inputString) {
        return (inputString == null) ? null : JsonUtils.escapeValue(inputString);
    }

    public void submitGroupEvent(final UserId messageRespondentId,
            String groupUUID,
            String groupName,
            String allMemberCodes,
            String groupCommunicationChannels,
            String screenName,
            String respondentMemberCode,
            String userLabel,
            String stimulusId,
            int stimulusIndex,
            String messageSenderId,
            String messageString,
            String responseStimulusId,
            String stimulusOptionIds,
            String senderId,
            String senderMemberCode,
            int eventMs) {
        submitData(ServiceEndpoint.groupEvent, messageRespondentId, "{\"eventDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"screenName\": " + jsonEscape(screenName) + ",\n"
                + "\"messageRespondentId\": " + jsonEscape(messageRespondentId.toString()) + ",\n"
                + "\"groupUUID\": " + jsonEscape(groupUUID) + ",\n"
                + "\"groupName\": " + jsonEscape(groupName) + ",\n"
                + "\"groupCommunicationChannels\": " + jsonEscape(groupCommunicationChannels) + ",\n"
                + "\"senderMemberCode\": " + jsonEscape(senderMemberCode) + ",\n"
                + "\"respondentMemberCode\": " + jsonEscape(respondentMemberCode) + ",\n"
                + "\"allMemberCodes\": " + jsonEscape(allMemberCodes) + ",\n"
                + "\"userLabel\": " + jsonEscape(userLabel) + ",\n"
                + "\"senderId\": " + jsonEscape(senderId) + ",\n"
                + "\"messageString\": " + jsonEscape(messageString) + ",\n"
                + "\"stimulusId\": " + jsonEscape(stimulusId) + ",\n"
                + "\"stimulusIndex\": " + stimulusIndex + ",\n"
                + "\"messageSenderId\": " + jsonEscape(messageSenderId) + ",\n"
                + "\"responseStimulusId\": " + jsonEscape(responseStimulusId) + ",\n"
                + "\"stimulusOptionIds\": " + jsonEscape(stimulusOptionIds) + ",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
    }

    public void submitTimestamps(final UserId userId, final TimedEventMonitor timedEventMonitor) {
        final List<TimedEvent> eventList = timedEventMonitor.getEventList();
        if (!eventList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean isFirst = true;
            for (TimedEvent timedEvent : eventList) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("{\"tagDate\" : ")
                        .append(jsonEscape(format.format(new Date())))
                        .append(",\n\"experimentName\": ")
                        .append(jsonEscape(experimentName))
                        .append(",\n\"userId\": ")
                        .append(jsonEscape(userId.toString()))
                        .append(",\n\"eventTag\": ")
                        .append(jsonEscape(timedEvent.getEventName()))
                        .append(",\n\"eventMs\": \"")
                        .append(timedEvent.getEventMs())
                        .append("\" \n}");
            }
            submitData(ServiceEndpoint.timeStamp, userId, stringBuilder.toString());
            timedEventMonitor.clearEvents(eventList);
        }
    }

    public void submitImmediateTimestamp(final UserId userId, String eventTag, int eventMs) {
        submitData(ServiceEndpoint.timeStamp, userId, "{\"tagDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"userId\": " + jsonEscape(userId.toString()) + ",\n"
                + "\"eventTag\": " + jsonEscape(eventTag) + ",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
    }

    public void submitScreenChange(final UserId userId, String applicationState) {
        submitData(ServiceEndpoint.screenChange, userId, "{\"viewDate\" : " + jsonEscape(format.format(new Date())) + ",\n"
                + "\"experimentName\": " + jsonEscape(experimentName) + ",\n"
                + "\"userId\": " + jsonEscape(userId.toString()) + ",\n"
                + "\"screenName\": " + jsonEscape(applicationState) + " \n}");

        // todo: optionally include the analytics call also
//        trackView(applicationState);
    }

    public void submitAllData(final UserResults userResults, final DataSubmissionListener dataSubmissionListener) {
        final UserId userId = userResults.getUserData().getUserId();
        class ResultCounts {

            int successCounter = 0;
            int errorCounter = 0;

            void checkOutcome() {
                if (errorCounter + successCounter >= ServiceEndpoint.values().length) {
                    if (errorCounter > 0) {
                        dataSubmissionListener.scoreSubmissionFailed(null);
                    } else {
                        dataSubmissionListener.scoreSubmissionComplete(null);
                    }
                }
            }
        }
        final ResultCounts resultCounts = new ResultCounts();
        for (final ServiceEndpoint endpoint : ServiceEndpoint.values()) {
            // todo: the ServiceEndpoint.metadata never seems to get its data stored so this data might not get sent on retries
            final String storedScreenData = localStorage.getStoredScreenData(userId, endpoint.name());
            if (storedScreenData.isEmpty()) {
                resultCounts.successCounter++;
                resultCounts.checkOutcome();
            } else {
                submitData(endpoint, userId, "[" + storedScreenData + "]", new DataSubmissionListener() {

                    @Override
                    public void scoreSubmissionFailed(DataSubmissionException exception) {
                        resultCounts.errorCounter++;
                        resultCounts.checkOutcome();
                    }

                    @Override
                    public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                        localStorage.deleteStoredScreenData(userId, endpoint.name(), storedScreenData);
                        resultCounts.successCounter++;
                        resultCounts.checkOutcome();
                    }
                });
            }
        }
    }
    private final List<Timer> dataSubmitTimerList = new ArrayList<>();

    private void submitData(final ServiceEndpoint endpoint, final UserId userId, final String jsonData) {
        final boolean canSendData;
        switch (endpoint) {
            case stowedData:
            case timeStamp:
            case screenChange:
            case tagEvent:
            case tagPairEvent:
            case stimulusResponse:
                canSendData = true;
                break;
            case groupEvent:
            case metadata:
            default:
                canSendData = localStorage.getDataAgreementValue(userId, metadataFieldProvider);
                break;
        }
        // data at this point has been neither stored nor sent
        if (canSendData) {
            localStorage.addStoredScreenData(userId, endpoint.name(), jsonData);
            final Timer timer = new Timer() {
                @Override
                public void run() {
                    final Timer selfTimer = this;
                    final String storedScreenData = localStorage.getStoredScreenData(userId, endpoint.name());
                    if (!storedScreenData.isEmpty()) {
                        submitData(endpoint, userId, "[" + storedScreenData + "]", new DataSubmissionListener() {

                            @Override
                            public void scoreSubmissionFailed(DataSubmissionException exception) {
                                dataSubmitTimerList.remove(selfTimer);
//                                if (!dataSubmitTimerList.isEmpty()) {
//                                    dataSubmitTimerList.get(0).schedule(500);
//                                }
                            }

                            @Override
                            public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                                localStorage.deleteStoredScreenData(userId, endpoint.name(), storedScreenData);
                                dataSubmitTimerList.remove(selfTimer);
//                                if (!dataSubmitTimerList.isEmpty()) {
//                                    dataSubmitTimerList.get(0).schedule(500);
//                                }
                            }
                        });
                    } else {
                        dataSubmitTimerList.remove(selfTimer);
//                        if (!dataSubmitTimerList.isEmpty()) {
//                            dataSubmitTimerList.get(0).schedule(500);
//                        }
                    }
                }
            };
            dataSubmitTimerList.add(timer);
            timer.schedule(1000 * dataSubmitTimerList.size());
//            if (dataSubmitTimerList.size() == 1) {
//                timer.schedule(500);
//            }
        }
    }

    private void submitData(final ServiceEndpoint endpoint, final UserId userId, final String jsonData, final DataSubmissionListener dataSubmissionListener) {
        final boolean canSendData;
        switch (endpoint) {
            case stowedData:
            case timeStamp:
            case screenChange:
            case tagEvent:
            case tagPairEvent:
                canSendData = true;
                break;
            case groupEvent:
            case metadata:
            case stimulusResponse:
            default:
                canSendData = localStorage.getDataAgreementValue(userId, metadataFieldProvider);
                break;
        }
        // data at this point has been stored but not sent
        if (canSendData) {
            final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, serviceLocations.dataSubmitUrl() + endpoint.name());
            builder.setHeader("Content-type", "application/json");
            RequestCallback requestCallback = new RequestCallback() {

                @Override
                public void onError(Request request, Throwable exception) {
                    logger.warning(builder.getUrl());
                    logger.log(Level.WARNING, "RequestCallback", exception);
                    dataSubmissionListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.connectionerror, endpoint.name()));
                }

                @Override
                public void onResponseReceived(Request request, Response response) {
                    final JsArray<DataSubmissionResult> sumbmissionResult = JsonUtils.<JsArray<DataSubmissionResult>>safeEval("[" + response.getText() + "]");
                    // here we also check that the JSON return value contains the correct user id, to test for cases where a web cashe or wifi login redirect returns stale data or a 200 code for a wifi login
                    if (200 == response.getStatusCode() && sumbmissionResult.length() > 0 && sumbmissionResult.get(0).getSuccess() && userId.toString().equals(sumbmissionResult.get(0).getUserId())) {
                        final String text = response.getText();
                        logger.info(text);
//                    localStorage.stowSentData(userId, jsonData);
                        dataSubmissionListener.scoreSubmissionComplete(sumbmissionResult);
                    } else {
                        logger.warning(builder.getUrl());
                        logger.warning(response.getStatusText());
                        if (sumbmissionResult.length() > 0) {
                            dataSubmissionListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.dataRejected, sumbmissionResult.get(0).getMessage()));
                        } else {
                            dataSubmissionListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.non202response, endpoint.name()));
                        }
                    }
                }
            };
            try {
                // todo: add the application build number to the submitted data
                builder.sendRequest(jsonData, requestCallback);
            } catch (RequestException exception) {
                logger.log(Level.SEVERE, "submit data failed", exception);
                dataSubmissionListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.buildererror, endpoint.name()));
            }
        }
    }

    public void eraseUsersStoredData(final UserId userId) {
        localStorage.clearUserData(userId);
    }

//    private static native void trackView(String applicationState) /*-{
//     if($wnd.analytics) $wnd.analytics.trackView(applicationState);
//     }-*/;
//
//    private static native void trackEvent(String applicationState, String label, String value) /*-{
//     if($wnd.analytics) $wnd.analytics.trackEvent(applicationState, "view", label, value);
//     }-*/;
    protected void sdWriteOk(String message) {
    }

    protected void sdWriteError(String message) {
    }

    protected native void writeCsvLine(final String userIdString, final String screenName, final int dataChannel, String eventTag, String tagValue1, String tagValue2, int eventMs) /*-{
        var dataSubmissionService = this;
        console.log("writeCsvLine: " + userIdString + " : " + tagValue1 + " : " + tagValue2);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.writeCsvLine(function (tagvalue) {
                console.log("writeCsvLine: " + tagvalue);
//                dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteOk(Ljava/lang/String;)(tagvalue);
            }, function (tagvalue) {
                console.log("writeCsvLine: " + tagvalue);
                dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteError(Ljava/lang/String;)(tagvalue);
            },  userIdString, screenName, dataChannel, eventTag, tagValue1, tagValue2, eventMs);
        } else {
            dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteError(Ljava/lang/String;)(null);
        }
     }-*/;

    public native void writeJsonData(String userIdString, String stimulusIdString, String stimulusJsonData) /*-{
        var dataSubmissionService = this;
        console.log("writeStimuliData: " + userIdString + " : " + stimulusIdString + " : " + stimulusJsonData);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.writeStimuliData(function (tagvalue) {
                console.log("writeJsonData: " + tagvalue);
//                dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteOk(Ljava/lang/String;)(tagvalue);
            }, function (tagvalue) {
                console.log("writeJsonData: " + tagvalue);
                dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteError(Ljava/lang/String;)(tagvalue);
            },  userIdString, stimulusIdString,  stimulusJsonData);
        } else {
            dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::sdWriteError(Ljava/lang/String;)(null);
        }
     }-*/;
}
