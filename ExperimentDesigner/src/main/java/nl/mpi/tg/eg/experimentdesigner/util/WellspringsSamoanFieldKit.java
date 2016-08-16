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
package nl.mpi.tg.eg.experimentdesigner.util;

import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioRecorderMetadataScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardWelcomeScreen;

/**
 * @since Dec 15, 2016 17:05:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WellspringsSamoanFieldKit {

    private final WizardController wizardController = new WizardController();

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("WellspringsSamoan");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);

        final PresenterScreen autoMenuPresenter = null;//wizardController.addAutoMenu(experiment, 12, false);//(Blong programa)
        final WizardWelcomeScreen welcomePresenter = new WizardWelcomeScreen("Welcome", "Welcome", "Instructions", "Stat - Go long program nao", null, null);
//wizardController.addWelcomeScreen(experiment, autoMenuPresenter, "Welcome", null, 1, "Instructions", "Stat - Go long program nao", false);
        final WizardExistingUserCheckScreen welcomeMenuPresenter = new WizardExistingUserCheckScreen("Start", "Niu rikording", "Gobak long wan olfala rikoding", "Makem wan niufala rikoding", "Gobak long wan rikoding we yu stat hem finis");
        final WizardTextScreen instructionsPresenter = new WizardTextScreen("Instructions", "Wetem aplikasen ia yu save makem rikoding blong lanwis blong yu,"
                + " bambai ol pipol blong Vanuatu mo ol pipol blong evri ples long world save harem lanwis blong yu. I gat fulap foto blong difren ples long Malakula wea i stap insaed long aplikasen ia. "
                + "Bai yu showem ol foto ia long wan olfala woman o wan olfala man blong vilej blong yu mo askem long hem se i tokabaot ol foto ia long lanwis blong hem. Yu save transletem wanem i talem, tu.", "Go long program nao");
        StimuliSubAction[] featureValuesArray = new StimuliSubAction[]{
            new StimuliSubAction("80", "Askem long man o woman wea i toktok se i talem nem blong wanem i stap lo foto long lanwis blong hem.", "Finis")
        };

        final WizardStimulusScreen wizardStimulusScreen = new WizardStimulusScreen();
        wizardStimulusScreen.setScreenTitle("Lukluk ol foto");
        wizardStimulusScreen.setMenuLabel("Lukluk ol foto");
        wizardStimulusScreen.setScreenLabel("Lukluk ol foto");
        wizardStimulusScreen.setEnd_of_stimuli("Finis olgeta");
        wizardStimulusScreen.setStimulusTagArray(new String[]{"Pictures"});
        wizardStimulusScreen.setFeatureValuesArray(featureValuesArray);
        wizardStimulusScreen.setMaxStimuli(1000);
        wizardStimulusScreen.setRandomiseStimuli(true);
//        wizardStimulusScreen.setStimulusImageCapture(true);
        wizardStimulusScreen.setFilePerStimulus(true);
        wizardStimulusScreen.setBackWizardScreen(welcomePresenter);
        wizardStimulusScreen.setEndOfStimulisWizardScreen(welcomePresenter);
        welcomePresenter.setInstructionsScreen(instructionsPresenter);
        welcomePresenter.setProgramWizardScreen(welcomeMenuPresenter);
//        final PresenterScreen vanuatuScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"vanuatu"}, featureValuesArray, true, 1000, true, 7, false);
//        final PresenterScreen bowpedStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, vanuatuScreen, new String[]{"bowped"}, featureValuesArray, true, 1000, true, 9, false);
//        final PresenterScreen bodiesStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, bowpedStimulusScreen, new String[]{"bodies"}, featureValuesArray, true, 1000, true, 10, false);
        final WizardAudioRecorderMetadataScreen metadataScreen = new WizardAudioRecorderMetadataScreen(new String[]{
            "the full name of the interviewer, date and place of the interview",
            "the name of the person interviewed â€“ their real name, and/or preferred name, and title",
            "age and gender of interviewee"
        }, "Neks"
        //                , "Finis olgeta"
        );
        metadataScreen.setBackWizardScreen(welcomePresenter);
        metadataScreen.setNextWizardScreen(wizardStimulusScreen);
        WizardAgreementScreen wizardTextScreen = new WizardAgreementScreen("Consent", "This is an interview for Hedvig's research project and that it will be recorded and stored at ANU, and possible for anyone in our research to listen to. Do you agree?", "Agree");

        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen();
        wizardSelectUserScreen.setBackWizardScreen(welcomePresenter);
        wizardSelectUserScreen.setNextWizardScreen(wizardTextScreen);
        final WizardEditUserScreen editUserPresenter = new WizardEditUserScreen("Infomesen blong man/woman we i toktok", "Edit User", null, null, "Savem infomesen", null, null, null, false, "Could not contact the server, please check your internet connection and try again.");
        editUserPresenter.setCustomFields(new String[]{"workerId:Nem blong man/woman we i toktok:.'{'3,'}':Please enter at least three letters."});
        final WizardAboutScreen debugScreenPresenter = new WizardAboutScreen(true);
        editUserPresenter.setBackWizardScreen(welcomePresenter);
        wizardTextScreen.setBackWizardScreen(welcomePresenter);
        wizardTextScreen.setNextWizardScreen(metadataScreen);
        editUserPresenter.setNextWizardScreen(wizardTextScreen);
        welcomeMenuPresenter.setNextWizardScreen(editUserPresenter);
        welcomeMenuPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setNextWizardScreen(welcomeMenuPresenter);
        wizardData.addScreen(welcomePresenter);
        wizardData.addScreen(welcomeMenuPresenter);
        wizardData.addScreen(instructionsPresenter);
        wizardData.addScreen(editUserPresenter);
        wizardData.addScreen(wizardSelectUserScreen);
        wizardData.addScreen(wizardTextScreen);
        wizardData.addScreen(metadataScreen);
        wizardData.addScreen(wizardStimulusScreen);
        wizardData.addScreen(debugScreenPresenter);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
