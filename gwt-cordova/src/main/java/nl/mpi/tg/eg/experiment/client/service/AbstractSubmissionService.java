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
package nl.mpi.tg.eg.experiment.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experiment.client.Version;
import nl.ru.languageininteraction.language.client.model.HighScoreData;
import nl.mpi.tg.eg.experiment.client.model.UserResults;

/**
 * @since Oct 29, 2014 11:18:31 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AbstractSubmissionService {

    final protected static Logger logger = Logger.getLogger(AbstractSubmissionService.class.getName());
    final protected ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    final protected MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    final protected Version version = GWT.create(Version.class);

    protected RequestCallback geRequestBuilder(final RequestBuilder builder, final DataSubmissionListener highScoreListener, final String targetUri, final UserResults userResults) {
        return new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                highScoreListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.connectionerror, exception));
                logger.warning(builder.getUrl());
                logger.log(Level.WARNING, "RequestCallback", exception);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    highScoreListener.scoreSubmissionComplete(JsonUtils.<JsArray<HighScoreData>>safeEval(response.getText()));
                } else {
                    highScoreListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.non202response, "An error occured on the server: " + response.getStatusText()));
                    logger.warning(targetUri);
                    logger.warning(response.getStatusText());
                }
            }
        };
    }
}
