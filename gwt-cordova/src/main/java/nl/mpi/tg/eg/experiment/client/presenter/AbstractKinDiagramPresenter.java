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
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Aug 3, 2015 1:21:43 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractKinDiagramPresenter extends AbstractPresenter implements Presenter {

    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final DataSubmissionService submissionService;
    final UserResults userResults;
    Stimulus currentStimulus = null;
    private final Duration duration;
    final ArrayList<ButtonBase> buttonList = new ArrayList<>();

    public AbstractKinDiagramPresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults) {
        super(widgetTag, new TimedStimulusView(audioPlayer));
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
    }

    public void kinTypeStringDiagram(final AppEventListner appEventListner, final int postLoadMs, final TimedStimulusListener timedStimulusListener /*, final String imageWidth*/, String kinTypeString) {
        // todo: migrate this block into GWT rather than using the REST service to generate
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, UriUtils.fromString("http://ems12.mpi.nl:8080/kinoath-rest/kinoath/getkin/svg?kts=" + kinTypeString).asString());
        requestBuilder.setCallback(new RequestCallback() {

            @Override
            public void onResponseReceived(Request request, Response response) {
                ((TimedStimulusView) simpleView).addSvgImage(response.getText(), 100);
                Timer timer = new Timer() {
                    public void run() {
                        timedStimulusListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadMs);
            }

            @Override
            public void onError(Request request, Throwable exception) {
                // todo: handle such errors in a more user friendly way
                ((TimedStimulusView) simpleView).addHtmlText(exception.getMessage());
            }
        });
        try {
            requestBuilder.send();
        } catch (RequestException exception) {
            // todo: handle such errors in a more user friendly way
            ((TimedStimulusView) simpleView).addHtmlText(exception.getMessage());
        }
//        ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromString("http://ems12.mpi.nl:8080/kinoath-rest/kinoath/getkin/svg?kts=" + kinTypeString), 100, postLoadMs, timedStimulusListener);
//        ((TimedStimulusView) simpleView).addSvgImage(UriUtils.fromString("http://ems12.mpi.nl:8080/kinoath-rest/kinoath/getkin/svg?kts=" + kinTypeString), 100, postLoadMs, timedStimulusListener);
    }

}
