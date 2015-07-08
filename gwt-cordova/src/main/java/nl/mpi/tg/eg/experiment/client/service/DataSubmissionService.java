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

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;
import java.util.logging.Level;
import nl.mpi.tg.eg.experiment.client.model.UserId;

/**
 * @since Jul 2, 2015 5:17:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DataSubmissionService extends AbstractSubmissionService {

    private enum ServiceEndpoint {

        timeStamp, screenChange
    }
    private final LocalStorage localStorage;
    private final String experimentName;
    final DateTimeFormat format = DateTimeFormat.getFormat(messages.jsonDateFormat());

    public DataSubmissionService(LocalStorage localStorage, String experimentName) {
        this.localStorage = localStorage;
        this.experimentName = experimentName;
    }

    public void submitTimeStamp(final UserId userId, String tagName, int eventMs) {
        submitData(ServiceEndpoint.timeStamp, userId, "{\"tagDate\" :\"" + format.format(new Date()) + "\",\n"
                + "\"experimentName\": \"" + experimentName + "\",\n"
                + "\"userId\": \"" + userId + "\",\n"
                + "\"tagName\": \"" + tagName + "\",\n"
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

    private void submitData(final ServiceEndpoint endpoint, final UserId userId, String jsonData) {

        localStorage.addStoredScreenData(userId, jsonData);

        final String storedScreenData = localStorage.getStoredScreenData(userId);

        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, messages.dataSubmitUrl() + endpoint.name());
        builder.setHeader("Content-type", "application/json");
        RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                logger.warning(builder.getUrl());
                logger.log(Level.WARNING, "RequestCallback", exception);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    localStorage.stowSentScreenData(userId);
                } else if (207 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    localStorage.stowSentScreenData(userId);
                    // if there was an issue on the server then store the problematic data. // todo: this might be removed when prodution status is reached
                    // todo: add handling of 200 responses given by some wifi login services that do not provide propper redirect codes, to make sure we dont get tricked into thinking data has been sent when it might not have
                    localStorage.addStoredScreenData(userId, text);
                } else {
                    logger.warning(builder.getUrl());
                    logger.warning(response.getStatusText());
                }
            }
        };
        try {
            builder.sendRequest("[" + storedScreenData + "]", requestCallback);
        } catch (RequestException exception) {
            logger.log(Level.SEVERE, "submit data failed", exception);
        }
    }

    private static native void trackView(String applicationState) /*-{
     if($wnd.analytics) $wnd.analytics.trackView(applicationState);
     }-*/;

    private static native void trackEvent(String applicationState, String label, String value) /*-{
     if($wnd.analytics) $wnd.analytics.trackEvent(applicationState, "view", label, value);
     }-*/;

}
