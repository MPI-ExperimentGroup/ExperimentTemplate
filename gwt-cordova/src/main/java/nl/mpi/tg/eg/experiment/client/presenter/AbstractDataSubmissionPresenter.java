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
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;

/**
 * @since Jul 16, 2015 11:05:26 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractDataSubmissionPresenter extends AbstractPresenter implements Presenter {

    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final DataSubmissionService submissionService;
    final UserResults userResults;
    private final Duration duration;

    public AbstractDataSubmissionPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults) {
        super(widgetTag, new ComplexView());
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
    }

    @Override
    public void setState(final AppEventListner appEventListner, ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        super.setState(appEventListner, prevState, null);
        this.nextState = nextState;
    }

    public void generateCompletionCode() {
        String completionCode = submissionService.getCompletionCode();
        // todo: consider changing this to something other than just a tag value
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "CompletionCode", completionCode, duration.elapsedMillis());
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), "DataSubmissionComplete", "CompletionCode", completionCode, duration.elapsedMillis());
        ((ComplexView) simpleView).addTextField(completionCode, true);
    }

    // todo: update xslt so the nullObject can be removed
    public void sendAllData(Object nullObject, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        submissionService.submitAllData(userResults, new DataSubmissionListener() {

            @Override
            public void scoreSubmissionFailed(DataSubmissionException exception) {
                onError.postLoadTimerFired();
            }

            @Override
            public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                onSuccess.postLoadTimerFired();
            }
        });
    }

    protected void eraseUsersDataButton(final String buttonLabel) {
        ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                submissionService.terminateAndDeleteStoredData(userResults.getUserData().getUserId());
                Window.Location.replace(Window.Location.getPath());
            }
        });
    }

    protected void eraseLocalStorageOnWindowClosing() {
        setWindowClosingListener(new PresenterEventListner() {

            @Override
            public String getLabel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                submissionService.terminateAndDeleteStoredData(userResults.getUserData().getUserId());
            }
        });
    }
}
