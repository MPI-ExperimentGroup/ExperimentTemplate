/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.model.HighScoreData;

/**
 * @since Jul 16, 2015 11:05:26 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractDataSubmissionPresenter extends AbstractPresenter implements Presenter {

    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final DataSubmissionService submissionService;
    final UserResults userResults;
    private final Duration duration;
    private TimedStimulusListener successEventListner;
    private TimedStimulusListener errorEventListner;

    public AbstractDataSubmissionPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults) {
        super(widgetTag, new ComplexView());
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
    }

    public void generateCompletionCode() {
        final Random random = new Random();
        final StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() < 12) {
            stringBuffer.append(Integer.toHexString(random.nextInt(16)));
        }
        String completionCode = stringBuffer.toString();
        // todo: consider changing this to something other than just a tag value
        submissionService.submitTagValue(userResults.getUserData().getUserId(), "CompletionCode", completionCode, duration.elapsedMillis());
        ((ComplexView) simpleView).addTextField(completionCode);
    }

    public void sendAllData() {
        submissionService.submitStowedData(userResults.getUserData().getUserId(), new DataSubmissionListener() {

            @Override
            public void scoreSubmissionFailed(DataSubmissionException exception) {
                errorEventListner.postLoadTimerFired();
            }

            @Override
            public void scoreSubmissionComplete(JsArray<HighScoreData> highScoreData) {
                successEventListner.postLoadTimerFired();
            }
        });
    }

    public void onSuccess(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        successEventListner = timedStimulusListener;
    }

    public void onError(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        errorEventListner = timedStimulusListener;
    }
}
