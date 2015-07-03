/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
package nl.ru.languageininteraction.language.client.registration;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;
import java.util.logging.Level;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AbstractSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.ResultsSerialiser;

/**
 * @since Jul 3, 2015 2:18:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GameDataSubmissionService extends AbstractSubmissionService {

    public void submitScores(final boolean isShareData, UserResults userResults, DataSubmissionListener highScoreListener, final String reportDateFormat) {
        final String highScoresUrl = serviceLocations.dataSubmitUrl();
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, highScoresUrl);
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        StringBuilder stringBuilder = new StringBuilder();
        if (isShareData) {
            for (MetadataField key : userResults.getUserData().getMetadataFields()) {
                String value = URL.encodeQueryString(userResults.getUserData().getMetadataValue(key));
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key.getPostName()).append("=").append(value);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        stringBuilder.append("userid").append("=").append(userResults.getUserData().getUserId()).append("&");
        stringBuilder.append("highscore").append("=").append(userResults.getUserData().getBestScore()).append("&");
        stringBuilder.append("gamesplayed").append("=").append(userResults.getUserData().getGamesPlayed()).append("&");
        stringBuilder.append("applicationversion").append("=").append(version.projectVersion()).append("&");
        if (!isShareData) {
            stringBuilder.append(metadataFieldProvider.shareMetadataField.getPostName()).append("=").append(userResults.getUserData().getMetadataValue(metadataFieldProvider.shareMetadataField)).append("&");
        }

        if (isShareData) {
//            String scoreLog = URL.encodeQueryString(userResults.getScoreLog());
//            stringBuilder.append("scorelog").append("=").append(scoreLog).append("&");
            String restultsData = URL.encodeQueryString(new ResultsSerialiser() {
                final DateTimeFormat format = DateTimeFormat.getFormat(reportDateFormat);

                @Override
                protected String formatDate(Date date) {
                    return format.format(date);
                }
            }.serialise(userResults));
            stringBuilder.append("quest_results=").append(new LocalStorage().getStoredGameData(userResults.getUserData().getUserId())).append(restultsData);
        }
        try {
            builder.sendRequest(stringBuilder.toString(), geRequestBuilder(builder, highScoreListener, highScoresUrl, userResults));
        } catch (RequestException exception) {
            highScoreListener.scoreSubmissionFailed(new DataSubmissionException(DataSubmissionException.ErrorType.buildererror, exception));
            logger.log(Level.SEVERE, "SubmitHighScore", exception);
        }
    }
}
