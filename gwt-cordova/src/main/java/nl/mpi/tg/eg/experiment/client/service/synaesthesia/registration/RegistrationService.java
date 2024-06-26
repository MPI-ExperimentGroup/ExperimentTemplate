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
package nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experiment.client.MetadataFields;
import nl.mpi.tg.eg.experiment.client.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.Version;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserResults;

/**
 * @since Oct 29, 2014 11:18:31 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegistrationService {

    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());
    final private ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    private final Version version = GWT.create(Version.class);

    public void submitRegistration(final UserResults userResults, final String sendingRegex, final String receivingRegex, final String dataLogFormated, RegistrationListener registrationListener) {
        final String registrationUrl = (serviceLocations.registrationUrl() != null && serviceLocations.registrationUrl().toLowerCase().startsWith("http")) ? serviceLocations.registrationUrl() : serviceLocations.dataSubmitUrl() + "validate";
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, registrationUrl);
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("requestingUserId=").append(URL.encodeQueryString(userResults.getUserData().getUserId().toString()));
        for (MetadataField key : userResults.getUserData().getMetadataFields()) {
            final String postName = key.getPostName();
            if (sendingRegex == null || postName.matches(sendingRegex)) {
                String value = URL.encodeQueryString(userResults.getUserData().getMetadataValue(key));
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(postName).append("=").append(value);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        stringBuilder.append("applicationversion").append("=").append(version.projectVersion()).append("&");
        if (dataLogFormated != null) {
            String dataLogEncoded = URL.encodeQueryString(dataLogFormated);
            stringBuilder.append("datalog").append("=").append(dataLogEncoded).append("&");
        }
        try {
            builder.sendRequest(stringBuilder.toString(), geRequestBuilder(userResults, builder, registrationListener, registrationUrl, receivingRegex));
        } catch (RequestException exception) {
            registrationListener.registrationFailed(new RegistrationException(RegistrationException.ErrorType.buildererror, exception));
            logger.log(Level.SEVERE, "SubmitRegistration", exception);
        }
    }

    public void submitRegistration(UserResults userResults, RegistrationListener registrationListener, final String reportDateFormat, final MetadataField emailAddressMetadataField, final String scoreLog) {
        final String registrationUrl = serviceLocations.registrationUrl();
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, registrationUrl);
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        StringBuilder stringBuilder = new StringBuilder();
        for (MetadataField key : userResults.getUserData().getMetadataFields()) {
            String value = URL.encodeQueryString(userResults.getUserData().getMetadataValue(key));
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key.getPostName()).append("=").append(value);
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        stringBuilder.append("applicationversion").append("=").append(version.projectVersion()).append("&");
        String scoreLogEncoded = URL.encodeQueryString(scoreLog/*userResults.getScoreLog()*/);
        stringBuilder.append("scorelog").append("=").append(scoreLogEncoded).append("&");
        String restultsData = URL.encodeQueryString(new ResultsSerialiser() {
            final DateTimeFormat format = DateTimeFormat.getFormat(reportDateFormat);

            @Override
            protected String formatDate(Date date) {
                return format.format(date);
            }

            @Override
            protected String getSeparator() {
                return "\t";
            }

            @Override
            protected String getRowSeparator() {
                return "\n";
            }
        }.serialise(userResults, emailAddressMetadataField));
        stringBuilder.append("quiz_results=").append(restultsData);
        try {
            builder.sendRequest(stringBuilder.toString(), geRequestBuilder(userResults, builder, registrationListener, registrationUrl, null));
        } catch (RequestException exception) {
            registrationListener.registrationFailed(new RegistrationException(RegistrationException.ErrorType.buildererror, exception));
            logger.log(Level.SEVERE, "SubmitRegistration", exception);
        }
    }

    private RequestCallback geRequestBuilder(final UserResults userResults, final RequestBuilder builder, final RegistrationListener registrationListener, final String targetUri, final String receivingRegex) {
        return new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                registrationListener.registrationFailed(new RegistrationException(RegistrationException.ErrorType.connectionerror, exception));
                logger.warning(builder.getUrl());
                logger.log(Level.WARNING, "RequestCallback", exception);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    String responseMetadataFields = "";
                    if (receivingRegex != null) {
                        // todo: if the JSON is blank and the receivingRegex is non empty, should we trigger an onError
                        try {
                            JSONObject jsonObject = (JSONObject) JSONParser.parseStrict(text);
                            if (jsonObject.containsKey("metadata")) {
                                JSONObject metadataJson = (JSONObject) jsonObject.get("metadata");
                                for (MetadataField key : userResults.getUserData().getMetadataFields()) {
                                    final String postName = key.getPostName();
                                    if (postName.matches(receivingRegex)) {
                                        if (metadataJson.containsKey(postName)) {
                                            responseMetadataFields = (responseMetadataFields.isEmpty()) ? postName : responseMetadataFields + "|" + postName;
                                            userResults.getUserData().setMetadataValue(key, metadataJson.get(postName).toString().replaceFirst("^\"", "").replaceFirst("\"$", ""));
                                        } // todo: if the response does not contain the matching metata, should we call onError?
                                    }
                                }
                            } else {
                                for (MetadataField key : userResults.getUserData().getMetadataFields()) {
                                    final String postName = key.getPostName();
                                    if (postName.matches(receivingRegex)) {
                                        if (jsonObject.containsKey(postName)) {
                                            responseMetadataFields = (responseMetadataFields.isEmpty()) ? postName : responseMetadataFields + "|" + postName;
                                            userResults.getUserData().setMetadataValue(key, jsonObject.get(postName).toString().replaceFirst("^\"", "").replaceFirst("\"$", ""));
                                        }
                                    }
                                }
                            }
                            if (jsonObject.containsKey("scoredata")) {
                                JSONObject scoreDataJson = (JSONObject) jsonObject.get("scoredata");
                                userResults.getUserData().setGamesPlayed(Integer.parseInt(scoreDataJson.get("gamesPlayed").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setTotalScore(Integer.parseInt(scoreDataJson.get("totalScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setTotalPotentialScore(Integer.parseInt(scoreDataJson.get("totalPotentialScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setCurrentScoreGroup(scoreDataJson.get("scoreGroup").toString().replaceFirst("^\"", "").replaceFirst("\"$", ""));
                                userResults.getUserData().setCurrentScore(Integer.parseInt(scoreDataJson.get("currentScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setCorrectStreak(Integer.parseInt(scoreDataJson.get("correctStreak").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setErrorStreak(Integer.parseInt(scoreDataJson.get("errorStreak").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setPotentialScore(Integer.parseInt(scoreDataJson.get("potentialScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setMaxScore(Double.parseDouble(scoreDataJson.get("maxScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setMaxErrors(Integer.parseInt(scoreDataJson.get("maxErrors").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setMaxCorrectStreak(Integer.parseInt(scoreDataJson.get("maxCorrectStreak").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setMaxErrorStreak(Integer.parseInt(scoreDataJson.get("maxErrorStreak").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                                userResults.getUserData().setMaxPotentialScore(Integer.parseInt(scoreDataJson.get("maxPotentialScore").toString().replaceFirst("^\"", "").replaceFirst("\"$", "")));
                            }
                        } catch (IllegalArgumentException iae) {
                            // handle malformed or wrong responses like wifi login redirects etc
                            registrationListener.registrationFailed(new RegistrationException(RegistrationException.ErrorType.jsonError, "Error parsing JSON: " + text));
                            logger.warning(iae.getMessage());
                        }
                    }
                    registrationListener.registrationComplete(responseMetadataFields);
                } else {
                    registrationListener.registrationFailed(new RegistrationException(RegistrationException.ErrorType.non202response, "An error occured on the server: " + response.getStatusText()));
                    logger.warning(targetUri);
                    logger.warning(response.getStatusText());
                }
            }
        };
    }
}
