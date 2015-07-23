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
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserResults;

/**
 * @since Jul 2, 2015 5:17:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DataSubmissionService extends AbstractSubmissionService {

    private enum ServiceEndpoint {

        timeStamp, screenChange, tagEvent, metadata, stowedData
    }
    private final LocalStorage localStorage;
    private final String experimentName;
    final DateTimeFormat format = DateTimeFormat.getFormat(messages.jsonDateFormat());

    public DataSubmissionService(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.experimentName = messages.appNameInternal();
    }

    public String getCompletionCode() {
        String completionCode = localStorage.getCompletionCode();
        if (completionCode == null) {
            final Random random = new Random();
            final StringBuffer stringBuffer = new StringBuffer();
            while (stringBuffer.length() < 12) {
                stringBuffer.append(Integer.toHexString(random.nextInt(16)));
            }
            completionCode = stringBuffer.toString();
            localStorage.saveCompletionCode(completionCode);
        }
        return completionCode;
    }

    public void submitMetadata(final UserResults userResults, final DataSubmissionListener dataSubmissionListener) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (MetadataField key : userResults.getUserData().getMetadataFields()) {
            String value = URL.encodeQueryString(userResults.getUserData().getMetadataValue(key));
            stringBuilder.append("\"").append(key.getPostName()).append("\": \"").append(value).append("\",\n");
        }
        stringBuilder.append("\"userId\": \"").append(userResults.getUserData().getUserId()).append("\"\n}");
        localStorage.addStoredScreenData(userResults.getUserData().getUserId(), ServiceEndpoint.metadata.name(), stringBuilder.toString());
        submitData(ServiceEndpoint.metadata, userResults.getUserData().getUserId(), "[" + stringBuilder.toString() + "]", dataSubmissionListener);
    }

    public void submitTagValue(final UserId userId, String eventTag, String tagValue, int eventMs) {
        submitData(ServiceEndpoint.tagEvent, userId, "{\"tagDate\" :\"" + format.format(new Date()) + "\",\n"
                + "\"experimentName\": \"" + experimentName + "\",\n"
                + "\"userId\": \"" + userId + "\",\n"
                + "\"eventTag\": \"" + eventTag + "\",\n"
                + "\"tagValue\": \"" + tagValue + "\",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
    }

    public void submitTimeStamp(final UserId userId, String eventTag, int eventMs) {
        submitData(ServiceEndpoint.timeStamp, userId, "{\"tagDate\" :\"" + format.format(new Date()) + "\",\n"
                + "\"experimentName\": \"" + experimentName + "\",\n"
                + "\"userId\": \"" + userId + "\",\n"
                + "\"eventTag\": \"" + eventTag + "\",\n"
                + "\"eventMs\": \"" + eventMs + "\" \n}");
    }

    public void submitScreenChange(final UserId userId, String applicationState) {
        submitData(ServiceEndpoint.screenChange, userId, "{\"viewDate\" :\"" + format.format(new Date()) + "\",\n"
                + "\"experimentName\": \"" + experimentName + "\",\n"
                + "\"userId\": \"" + userId + "\",\n"
                + "\"screenName\": \"" + applicationState + "\" \n}");

        // todo: optionally include the analytics call also
        trackView(applicationState);
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
                        localStorage.deleteStoredScreenData(userId, endpoint.name());
                        resultCounts.successCounter++;
                        resultCounts.checkOutcome();
                    }
                });
            }
        }
    }

    private void submitData(final ServiceEndpoint endpoint, final UserId userId, final String jsonData) {
        localStorage.addStoredScreenData(userId, endpoint.name(), jsonData);

        final String storedScreenData = localStorage.getStoredScreenData(userId, endpoint.name());
        submitData(endpoint, userId, "[" + storedScreenData + "]", new DataSubmissionListener() {

            @Override
            public void scoreSubmissionFailed(DataSubmissionException exception) {
            }

            @Override
            public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
            }
        });
    }

    private void submitData(final ServiceEndpoint endpoint, final UserId userId, final String jsonData, final DataSubmissionListener dataSubmissionListener) {
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, messages.dataSubmitUrl() + endpoint.name());
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
                    localStorage.stowSentData(userId, jsonData);
                    localStorage.deleteStoredScreenData(userId, endpoint.name());
                    dataSubmissionListener.scoreSubmissionComplete(sumbmissionResult);
                } else {
                    logger.warning(builder.getUrl());
                    logger.warning(response.getStatusText());
                    dataSubmissionListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.non202response, endpoint.name()));
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

    private static native void trackView(String applicationState) /*-{
     if($wnd.analytics) $wnd.analytics.trackView(applicationState);
     }-*/;

    private static native void trackEvent(String applicationState, String label, String value) /*-{
     if($wnd.analytics) $wnd.analytics.trackEvent(applicationState, "view", label, value);
     }-*/;
}
