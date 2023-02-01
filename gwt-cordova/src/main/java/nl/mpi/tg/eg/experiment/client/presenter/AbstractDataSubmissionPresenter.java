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
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
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

    public AbstractDataSubmissionPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new TimedStimulusView(), submissionService, userResults, localStorage, timerService);
    }

    @Override
    public void setState(final AppEventListener appEventListener, ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        super.setState(appEventListener, prevState, null);
        this.nextState = nextState;
    }

    public void displayCompletionCode() {
        final String completionCode = submissionService.getCompletionCode(userResults.getUserData().getUserId());
        ((ComplexView) simpleView).addTextField(completionCode, true);
    }

    public void generateCompletionCode(final int dataChannel, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        // this method requires, send all data success before displaying the code
        final String completionCode = submissionService.getCompletionCode(userResults.getUserData().getUserId());
        submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), dataChannel, "DataSubmission", "CompletionCode", completionCode, duration.elapsedMillis());

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

    public void validateMetadata(final Stimulus currentStimulus, final String sendingRegex, final String receivingRegex, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        transmitResults(currentStimulus, sendingRegex, receivingRegex, null, onError, onSuccess);
    }

    public void transmitResults(final Stimulus currentStimulus, final String sendingRegex, final String receivingRegex, final String dataLogFormat, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        final Duration transmitDuration = new Duration();
        final String dataLogFormatted = (dataLogFormat == null) ? null : new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.getMetadataFieldArray()).formatString(dataLogFormat);
        submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "transmitResults", receivingRegex, transmitDuration.elapsedMillis());
        new RegistrationService().submitRegistration(userResults, sendingRegex, receivingRegex, dataLogFormatted, new RegistrationListener() {
            @Override
            public void registrationFailed(RegistrationException exception) {
                submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "transmitFailed", exception.getMessage(), transmitDuration.elapsedMillis());
                onError.postLoadTimerFired();
            }

            @Override
            public void registrationComplete(String responseMetadataFields) {
                submissionService.submitTagValue(userResults.getUserData().getUserId(), getSelfTag(), "transmitComplete", responseMetadataFields, transmitDuration.elapsedMillis());
                // because the received data can be used to set metadata fields, we store that data here
                localStorage.storeData(userResults, metadataFieldProvider);
                onSuccess.postLoadTimerFired();
            }
        });
    }

    protected void createUserButton(final AppEventListener appEventListener, final String label, final String styleName, final ApplicationController.ApplicationState targetApplicationState, final String buttonGroup) {
        optionButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                userResults.setUser(new UserData());
                localStorage.storeData(userResults, metadataFieldProvider);
                appEventListener.requestApplicationState(targetApplicationState);
            }
        }, buttonGroup);
    }

    protected void eraseUsersDataButton(final AppEventListener appEventListener, final String buttonLabel, final String styleName, final ApplicationController.ApplicationState nextState, final String buttonGroup) {
        optionButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return buttonLabel;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                final UserId previousUserId = userResults.getUserData().getUserId();
                submissionService.eraseUsersStoredData(previousUserId);
                List<UserLabelData> userList = localStorage.getUserIdList(metadataFieldProvider.getMetadataFieldArray()[0]);
                if (!userList.isEmpty()) {
                    final UserLabelData nextUser = userList.get(0);
                    userResults.setUser(new UserData(nextUser.getUserId()));
                } else {
                    final UserId nextUserId = new UserId();
                    userResults.setUser(new UserData(nextUserId));
                }
                submissionService.eraseUsersStoredData(previousUserId);
                appEventListener.requestApplicationState(nextState);
            }
        }, buttonGroup);
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
        setWindowClosingListener(new PresenterEventListener() {

            @Override
            public String getLabel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                submissionService.eraseUsersStoredData(userResults.getUserData().getUserId());
            }
        });
    }
}
