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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserLabelData;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationException;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationListener;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationService;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jul 16, 2015 11:05:26 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractDataSubmissionPresenter extends AbstractTimedPresenter implements Presenter {

    private final DataSubmissionService submissionService;
    private final Duration duration;

    public AbstractDataSubmissionPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new TimedStimulusView(), userResults, localStorage, timerService);
        duration = new Duration();
        this.submissionService = submissionService;
    }

    @Override
    public void setState(final AppEventListner appEventListner, ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        super.setState(appEventListner, prevState, null);
        this.nextState = nextState;
    }

    public void redirectToUrl(final String targetUrl/*, final boolean submitDataFirst*/) {
        submissionService.submitAllData(userResults, new DataSubmissionListener() {
            @Override
            public void scoreSubmissionFailed(DataSubmissionException exception) {
//                onError.postLoadTimerFired();
                Window.Location.replace(targetUrl);
            }

            @Override
            public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                Window.Location.replace(targetUrl);
            }
        });
    }

    public void displayCompletionCode() {
        final String completionCode = submissionService.getCompletionCode(userResults.getUserData().getUserId());
        ((ComplexView) simpleView).addTextField(completionCode, true);
    }

    public void generateCompletionCode(final int dataChannel, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        // this method requires, send all data success before displaying the code
        final String completionCode = submissionService.getCompletionCode(userResults.getUserData().getUserId());
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "DataSubmission", "CompletionCode", completionCode, 0);

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

    // todo: update xslt so the nullObject can be removed
    public void sendMetadata(Object nullObject, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        submissionService.submitMetadata(userResults, new DataSubmissionListener() {

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

    public void transmitResults(final Stimulus currentStimulus, final String sendingRegex, final String receivingRegex, final String dataLogFormat, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        final String dataLogFormatted = new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.metadataFieldArray).formatString(dataLogFormat);
        new RegistrationService().submitRegistration(userResults, sendingRegex, receivingRegex, dataLogFormatted, new RegistrationListener() {
            @Override
            public void registrationFailed(RegistrationException exception) {
                onError.postLoadTimerFired();
            }

            @Override
            public void registrationComplete() {
                // because the received data can be used to set metadata fields, we store that data here
                localStorage.storeData(userResults, metadataFieldProvider);
                onSuccess.postLoadTimerFired();
            }
        });
    }

    protected void createUserButton(final AppEventListner appEventListner, final String label, final String styleName, final ApplicationController.ApplicationState targetApplicationState, final String buttonGroup) {
        optionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                userResults.setUser(new UserData());
                localStorage.storeData(userResults, metadataFieldProvider);
                appEventListner.requestApplicationState(targetApplicationState);
            }
        }, styleName, buttonGroup);
    }

    protected void eraseUsersDataButton(final String buttonLabel, final String styleName, final ApplicationController.ApplicationState nextState, final String buttonGroup) {
        optionButton(new PresenterEventListner() {

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
                submissionService.eraseUsersStoredData(userResults.getUserData().getUserId());
                List<UserLabelData> userList = localStorage.getUserIdList(metadataFieldProvider.workerIdMetadataField);
                if (!userList.isEmpty()) {
                    final UserLabelData nextUser = userList.get(0);
                    localStorage.saveAppState(nextUser.getUserId(), nextState);
                } else {
                    localStorage.saveAppState(new UserId(), ApplicationController.ApplicationState.start);
                }
                Window.Location.replace(Window.Location.getPath());
            }
        }, styleName, buttonGroup);
    }

    protected void pause(int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        final Timer timer = new Timer() {
            @Override
            public void run() {
                timedStimulusListener.postLoadTimerFired();
            }
        };
        timer.schedule(postLoadMs);
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
                submissionService.eraseUsersStoredData(userResults.getUserData().getUserId());
            }
        });
    }
}
