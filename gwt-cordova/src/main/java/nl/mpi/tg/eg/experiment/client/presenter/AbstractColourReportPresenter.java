/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.Date;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.model.colour.StimulusResponseGroup;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.view.ReportView;
import nl.mpi.tg.eg.experiment.client.model.colour.GroupScoreData;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationException;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationListener;
import nl.mpi.tg.eg.experiment.client.service.synaesthesia.registration.RegistrationService;
import nl.mpi.tg.eg.experiment.client.util.ScoreCalculator;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Mar 7, 2016 4:10:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractColourReportPresenter extends AbstractTimedPresenter implements Presenter {

    private final DataSubmissionService submissionService;
    private String reportScreenScore = "<playerScore>";
    private String userfeedbackscreentext = "";

    public AbstractColourReportPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new ReportView(), submissionService, userResults, localStorage, timerService);
        this.submissionService = submissionService;
    }

    @Override
    public void setState(final AppEventListener appEventListener, ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        super.setState(appEventListener, prevState, null);
        this.nextState = nextState;
    }

    @Override
    public void htmlTokenText(final Stimulus currentStimulus, String textString, String styleName, XmlId xmlId) {
        switch (styleName) {
            case "reportScreenScore":
                reportScreenScore = textString;
                break;
            case "userfeedbackscreentext":
                userfeedbackscreentext = textString;
                break;
            default:
                htmlTokenText(currentStimulus, textString, styleName, xmlId);
        }
    }

    public void submitTestResults(final MetadataField emailAddressMetadataField, final TimedStimulusListener onError, final TimedStimulusListener onSuccess) {
        StringBuilder stringBuilder = new StringBuilder();
        final DateTimeFormat format = DateTimeFormat.getFormat(messages.reportDateFormat());
        final ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);

        for (final StimulusResponseGroup stimuliGroup : userResults.getStimulusResponseGroups()) {
            final GroupScoreData calculatedScores = scoreCalculator.calculateScores(stimuliGroup);
            stringBuilder.append("\t");
            stringBuilder.append(stimuliGroup.getPostName());
            stringBuilder.append("\t");
            stringBuilder.append(format.format(new Date()));
            stringBuilder.append("\t");
            stringBuilder.append(calculatedScores.getScore());
            stringBuilder.append("\t");
            stringBuilder.append(calculatedScores.getMeanReactionTime());
            stringBuilder.append("\t");
            stringBuilder.append(calculatedScores.getReactionTimeDeviation());
            stringBuilder.append("\n");
        }

        final String scoreLog = stringBuilder.toString();
//        submissionService.submitTagValue(userResults.getUserData().getUserId(), "ScoreLog", scoreLog.replaceAll("\t", ","), 0);
        new RegistrationService().submitRegistration(userResults, new RegistrationListener() {
            @Override
            public void registrationFailed(RegistrationException exception) {
                onError.postLoadTimerFired();
                ((ReportView) simpleView).addText("Could not connect to the server.");
                ((ReportView) simpleView).addOptionButton(new PresenterEventListener() {
                    @Override
                    public String getLabel() {
                        return "Retry";
                    }

                    @Override
                    public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                        Timer timer = new Timer() {
                            @Override
                            public void run() {
                                ((ReportView) simpleView).clearPageAndTimers(null);
                                submitTestResults(emailAddressMetadataField, onError, onSuccess);
                            }
                        };
                        timer.schedule(1000);
                    }

                    @Override
                    public String getStyleName() {
                        return null;
                    }

                    @Override
                    public int getHotKey() {
                        return -1;
                    }
                });
            }

            @Override
            public void registrationComplete(String responseMetadataFields) {
                onSuccess.postLoadTimerFired();
            }
        }, messages.reportDateFormat(), emailAddressMetadataField, scoreLog);
    }

    public void showColourReport(final int scoreThreshold, final MetadataField emailAddressMetadataField, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) { // todo: use scoreThreshold
        showColourReport((float) scoreThreshold, emailAddressMetadataField, aboveThreshold, withinThreshold);
    }

    public void showColourReport(final double scoreThreshold, final MetadataField emailAddressMetadataField, final TimedStimulusListener aboveThreshold, final TimedStimulusListener withinThreshold) { // todo: use scoreThreshold
        final NumberFormat numberFormat2 = NumberFormat.getFormat("0.00");
//        final NumberFormat numberFormat3 = NumberFormat.getFormat("0.000");
        final ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);
        Double minScore = null;
        for (final StimulusResponseGroup stimuliGroup : userResults.getStimulusResponseGroups()) {
            if (stimuliGroup.isComplete()) {
                final GroupScoreData calculatedScores = scoreCalculator.calculateScores(stimuliGroup);
                ((ReportView) simpleView).showResults(stimuliGroup, calculatedScores);
                ((ReportView) simpleView).addText(reportScreenScore.replace("<playerScore>", numberFormat2.format(calculatedScores.getScore())));
                ((ReportView) simpleView).addText(userfeedbackscreentext);
                ((ReportView) simpleView).addPadding();
                userResults.getUserData().updateMaxScore(calculatedScores.getScore(), 0, 0, 0, 0);
                for (MetadataField field : userResults.getUserData().getMetadataFields()) {
                    if (field.getPostName().equals(stimuliGroup.getPostName() + "_result")) {
                        userResults.getUserData().setMetadataValue(field, Double.toString(calculatedScores.getScore()));
                        localStorage.storeData(userResults, metadataFieldProvider);
                    }
                }
                minScore = (minScore == null) ? calculatedScores.getScore() : (minScore > calculatedScores.getScore()) ? calculatedScores.getScore() : minScore;
                //            ((ReportView) simpleView).addText(messages.reportScreenSCT());
                //            ((ReportView) simpleView).addText(messages.reportScreenSCTaccuracy(numberFormat2.format(calculatedScores.getAccuracy())));
                //            ((ReportView) simpleView).addText(messages.reportScreenSCTmeanreactionTime(numberFormat3.format(calculatedScores.getMeanReactionTime() / 1000), numberFormat3.format(calculatedScores.getReactionTimeDeviation() / 1000)));
                //            stringBuilder.append(userResults.getUserData().getMetadataValue(MetadataFieldProvider.));
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, "Score", stimuliGroup.getPostName(), Double.toString(calculatedScores.getScore()), 0);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, "MeanReactionTime", stimuliGroup.getPostName(), Double.toString(calculatedScores.getMeanReactionTime()), 0);
                submissionService.submitTagPairValue(userResults.getUserData().getUserId(), getSelfTag(), 0, "ReactionTimeDeviation", stimuliGroup.getPostName(), Double.toString(calculatedScores.getReactionTimeDeviation()), 0);
            }
        }
//        ((ReportView) simpleView).addText(messages.reportScreenPostSCTtext());
        if (minScore != null) {
            if (minScore <= scoreThreshold) {
                withinThreshold.postLoadTimerFired();
//            ((ReportView) simpleView).addHighlightedText(messages.positiveresultscreentext1());
//            ((ReportView) simpleView).addHighlightedText(messages.positiveresultscreentext2());
//            ((ReportView) simpleView).addHighlightedText(messages.positiveresultscreentext3());
            } else {
                aboveThreshold.postLoadTimerFired();
//            ((ReportView) simpleView).addHighlightedText(messages.negativeresultscreentext1());
//            ((ReportView) simpleView).addHighlightedText(messages.negativeresultscreentext2());
//            ((ReportView) simpleView).addHighlightedText(messages.negativeresultscreentext3());
            }
        }
        ((ReportView) simpleView).addPadding();
    }
}
