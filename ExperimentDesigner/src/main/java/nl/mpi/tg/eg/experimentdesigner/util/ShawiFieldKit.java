/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioRecorderMetadataScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardKinshipScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardWelcomeScreen;

/**
 * @since Feb 22, 2016 4:39:04 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ShawiFieldKit {

    private final WizardController wizardController = new WizardController();

    public WizardData getWizardData() {
        final WizardData wizardData = new WizardData();
        return wizardData;
    }

    public Experiment getShawiExperiment() {
//       return wizardController.getExperiment(getWizardData());
        Experiment experiment = wizardController.getExperiment("shawifieldkit", "Shawi FieldKit", true);
        boolean obfuscateScreenNames = false;
        experiment.setBackgroundColour("#ffeda0");
        experiment.setPrimaryColour4("#feb24c");
        experiment.setPrimaryColour2("#f03b20");
//        wizardController.addMetadata(experiment);
        final WizardMenuScreen autoMenuPresenter = new WizardMenuScreen("Auto Menu", "Menu", "AutoMenu");
        final WizardWelcomeScreen welcomePresenter = new WizardWelcomeScreen("Welcome", "Welcome", "Instructions", "Go directly to program", null, null);
        final WizardExistingUserCheckScreen welcomeMenuPresenter = new WizardExistingUserCheckScreen("Start", "New Interview.", "Resume Interview", "Is this a new recording?", "Have you already started a recording and do you want to go back to it?");
        final WizardTextScreen instructionsPresenter = new WizardTextScreen("Instructions", "With this app you can make recordings of your language. "
                + "Describe pictures in this app by speaking and the app records what you say.", "Go directly to program");
        StimuliSubAction[] picturesValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", "the informant talks/says whatever s/he wants", "next")};
        StimuliSubAction[] grammaticalityValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})};
// this should not be random but use alphanum sorting 

        final WizardStimulusScreen cutbreakScreen = createStimulusScreen(experiment, welcomePresenter, "cutbreak", welcomePresenter, new String[]{"cutbreak"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 15, obfuscateScreenNames);
        final WizardStimulusScreen grammarScreen = createStimulusScreen(experiment, welcomePresenter, "grammar", welcomePresenter, new String[]{"grammar"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 14, obfuscateScreenNames);
        final WizardStimulusScreen vanuatuScreen = createStimulusScreen(experiment, welcomePresenter, "vanuatu", welcomePresenter, new String[]{"vanuatu"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 13, obfuscateScreenNames);
        final WizardStimulusScreen bodiesScreen = createStimulusScreen(experiment, welcomePresenter, "bodies", welcomePresenter, new String[]{"bodies"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 12, obfuscateScreenNames);
        final WizardStimulusScreen bowpedScreen = createStimulusScreen(experiment, welcomePresenter, "bowped", welcomePresenter, new String[]{"bowped"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 11, obfuscateScreenNames);
        final WizardStimulusScreen grammaticalityScreen = createStimulusScreen(experiment, welcomePresenter, "Grammaticality", welcomePresenter, new String[]{"Grammaticality"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 7, obfuscateScreenNames);
        final WizardStimulusScreen picturesScreen = createStimulusScreen(experiment, welcomePresenter, "Pictures", grammaticalityScreen, new String[]{"Pictures"}, picturesValuesArray, true, 1000, false, "end_of_stimuli", 8, obfuscateScreenNames);
        final WizardStimulusScreen animalsScreen = createStimulusScreen(experiment, welcomePresenter, "Animals", grammaticalityScreen, new String[]{"Animals"}, picturesValuesArray, true, 1000, false, "end_of_stimuli", 9, obfuscateScreenNames);
        final WizardStimulusScreen frogsScreen = createStimulusScreen(experiment, welcomePresenter, "Frogs", grammaticalityScreen, new String[]{"Frogs"}, picturesValuesArray, false, 1000, false, "end_of_stimuli", 10, obfuscateScreenNames);
        final WizardAudioRecorderMetadataScreen metadataScreen = new WizardAudioRecorderMetadataScreen(new String[]{"Nombre", "Sexo", "Edad", "Estado civil", "Origen", "Lugar de residencia", "Nombre de la comunidad a la que pertenece", "Actividad laboral", "Nivel de estudios", "Número de hijos", "Religión", "Idiomas"}, "next"
        //                , "end of stimuli"
        );
        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen();
        final WizardEditUserScreen editUserPresenter = new WizardEditUserScreen("Edit User", "Edit User", null, null, "Save and continue", null, null, null, false, "Could not contact the server, please check your internet connection and try again.");
        editUserPresenter.setCustomFields(new String[]{"workerId:Speaker name *:.'{'3,'}':Please enter at least three letters."});
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen();
        final WizardKinshipScreen kinshipPresenter = addKinshipScreen(experiment, autoMenuPresenter, null, 16, obfuscateScreenNames);
        welcomePresenter.setInstructionsScreen(instructionsPresenter);
        welcomePresenter.setProgramWizardScreen(welcomeMenuPresenter);
        instructionsPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setNextWizardScreen(welcomeMenuPresenter);
        metadataScreen.setBackWizardScreen(autoMenuPresenter);
        metadataScreen.setNextWizardScreen(picturesScreen);
        welcomeMenuPresenter.setBackWizardScreen(welcomePresenter);
        welcomeMenuPresenter.setNextWizardScreen(metadataScreen);
        wizardSelectUserScreen.setBackWizardScreen(welcomePresenter);
        wizardSelectUserScreen.setNextWizardScreen(metadataScreen);
        welcomeMenuPresenter.setNextWizardScreen(metadataScreen);
        editUserPresenter.setBackWizardScreen(welcomePresenter);
        editUserPresenter.setNextWizardScreen(metadataScreen);
        wizardAboutScreen.setBackWizardScreen(autoMenuPresenter);
        welcomePresenter.setBackWizardScreen(autoMenuPresenter);
        wizardAboutScreen.populatePresenterScreen(experiment, obfuscateScreenNames, 17);
        autoMenuPresenter.populatePresenterScreen(experiment, obfuscateScreenNames, 18);
        welcomePresenter.populatePresenterScreen(experiment, obfuscateScreenNames, 1);
        welcomeMenuPresenter.populatePresenterScreen(experiment, obfuscateScreenNames, 2);
        instructionsPresenter.populatePresenterScreen(experiment, obfuscateScreenNames, 3);
        metadataScreen.populatePresenterScreen(experiment, obfuscateScreenNames, 4);
        wizardSelectUserScreen.populatePresenterScreen(experiment, false, 5);
        editUserPresenter.populatePresenterScreen(experiment, obfuscateScreenNames, 6);
        return experiment;
    }

    public WizardStimulusScreen createStimulusScreen(final Experiment experiment, final WizardScreen backPresenter, final String screenLabel, final WizardScreen nextPresenter, final String stimulusTagArray[], final StimuliSubAction[] featureValuesArray, final boolean randomiseStimuli, final int maxStimuli, boolean filePerStimulus, final String end_of_stimuli, long displayOrder, boolean obfuscateScreenNames) {
        final WizardStimulusScreen wizardStimulusScreen = new WizardStimulusScreen();
        wizardStimulusScreen.setScreenTitle(screenLabel);
        wizardStimulusScreen.setMenuLabel(screenLabel);
        wizardStimulusScreen.setScreenLabel(screenLabel);
        wizardStimulusScreen.setStimulusTagArray(stimulusTagArray);
        wizardStimulusScreen.setFeatureValuesArray(featureValuesArray);
        wizardStimulusScreen.setMaxStimuli(maxStimuli);
        wizardStimulusScreen.setRandomiseStimuli(randomiseStimuli);
        wizardStimulusScreen.setFilePerStimulus(filePerStimulus);
        wizardStimulusScreen.setEnd_of_stimuli(end_of_stimuli);
        wizardStimulusScreen.setBackWizardScreen(backPresenter);
        wizardStimulusScreen.setEndOfStimulisWizardScreen(nextPresenter);
        wizardStimulusScreen.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        return wizardStimulusScreen;
    }

    public WizardKinshipScreen addKinshipScreen(final Experiment experiment, final WizardScreen backPresenter, final WizardScreen nextPresenter, long displayOrder, boolean obfuscateScreenNames) {
        final String diagramName = "kinDiagram";
        final String title = "Kinship";
        WizardKinshipScreen kinshipScreen = new WizardKinshipScreen(title, diagramName);
        kinshipScreen.setBackWizardScreen(backPresenter);
        kinshipScreen.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        return kinshipScreen;
    }
}
