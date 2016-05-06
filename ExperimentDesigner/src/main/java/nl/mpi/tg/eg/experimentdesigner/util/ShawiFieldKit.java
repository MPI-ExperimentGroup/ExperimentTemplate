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
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.AbstractWizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioRecorderMetadataScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardWelcomeScreen;

/**
 * @since Feb 22, 2016 4:39:04 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ShawiFieldKit {

    private final WizardController wizardController = new WizardController();

    public Experiment getShawiExperiment() {
        Experiment experiment = wizardController.getExperiment("shawifieldkit", "Shawi FieldKit", true);
        boolean obfuscateScreenNames = false;
        experiment.setBackgroundColour("#ffeda0");
        experiment.setPrimaryColour4("#feb24c");
        experiment.setPrimaryColour2("#f03b20");
//        wizardController.addMetadata(experiment);
        final PresenterScreen autoMenuPresenter = wizardController.addAutoMenu(experiment, 18, obfuscateScreenNames);
        final WizardWelcomeScreen wizardWelcomeScreen = new WizardWelcomeScreen("Welcome", "Instructions", "Go directly to program");
        wizardWelcomeScreen.setBackWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return autoMenuPresenter;
            }
        });

        final PresenterScreen welcomePresenter = wizardWelcomeScreen.populatePresenterScreen(experiment, obfuscateScreenNames, 1);
        final PresenterScreen welcomeMenuPresenter = wizardController.addWelcomeMenu(experiment, welcomePresenter, "Start", null, 2, "New Interview.", "Resume Interview", "Is this a new recording?", "Have you already started a recording and do you want to go back to it?", obfuscateScreenNames);
        final PresenterScreen instructionsPresenter = wizardController.addInstructionsScreen(experiment, welcomePresenter, "Instructions", welcomeMenuPresenter, 3, "With this app you can make recordings of your language. "
                + "Describe pictures in this app by speaking and the app records what you say.", "Go directly to program", obfuscateScreenNames);
        StimuliSubAction[] picturesValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", "the informant talks/says whatever s/he wants", "next")};
        StimuliSubAction[] grammaticalityValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})};
// this should not be random but use alphanum sorting 

        final PresenterScreen cutbreakScreen = createStimulusScreen(experiment, welcomePresenter, "cutbreak", welcomePresenter, new String[]{"cutbreak"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 15, obfuscateScreenNames);
        final PresenterScreen grammarScreen = createStimulusScreen(experiment, welcomePresenter, "grammar", welcomePresenter, new String[]{"grammar"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 14, obfuscateScreenNames);
        final PresenterScreen vanuatuScreen = createStimulusScreen(experiment, welcomePresenter, "vanuatu", welcomePresenter, new String[]{"vanuatu"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 13, obfuscateScreenNames);
        final PresenterScreen bodiesScreen = createStimulusScreen(experiment, welcomePresenter, "bodies", welcomePresenter, new String[]{"bodies"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 12, obfuscateScreenNames);
        final PresenterScreen bowpedScreen = createStimulusScreen(experiment, welcomePresenter, "bowped", welcomePresenter, new String[]{"bowped"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 11, obfuscateScreenNames);
        final PresenterScreen grammaticalityScreen = createStimulusScreen(experiment, welcomePresenter, "Grammaticality", welcomePresenter, new String[]{"Grammaticality"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 7, obfuscateScreenNames);
        final PresenterScreen picturesScreen = createStimulusScreen(experiment, welcomePresenter, "Pictures", grammaticalityScreen, new String[]{"Pictures"}, picturesValuesArray, true, 1000, false, "end_of_stimuli", 8, obfuscateScreenNames);
        final PresenterScreen animalsScreen = createStimulusScreen(experiment, welcomePresenter, "Animals", grammaticalityScreen, new String[]{"Animals"}, picturesValuesArray, true, 1000, false, "end_of_stimuli", 9, obfuscateScreenNames);
        final PresenterScreen frogsScreen = createStimulusScreen(experiment, welcomePresenter, "Frogs", grammaticalityScreen, new String[]{"Frogs"}, picturesValuesArray, false, 1000, false, "end_of_stimuli", 10, obfuscateScreenNames);
        final WizardAudioRecorderMetadataScreen wizardAudioRecorderMetadataScreen = new WizardAudioRecorderMetadataScreen(new String[]{"Nombre", "Sexo", "Edad", "Estado civil", "Origen", "Lugar de residencia", "Nombre de la comunidad a la que pertenece", "Actividad laboral", "Nivel de estudios", "Número de hijos", "Religión", "Idiomas"}, "next", "end of stimuli");
        wizardAudioRecorderMetadataScreen.setBackWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return autoMenuPresenter;
            }
        });
        wizardAudioRecorderMetadataScreen.setNextWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return picturesScreen;
            }
        });
        final PresenterScreen metadataScreen = wizardAudioRecorderMetadataScreen.populatePresenterScreen(experiment, obfuscateScreenNames, 4);
        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen();
        wizardSelectUserScreen.setBackWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return welcomePresenter;
            }
        });
        wizardSelectUserScreen.setNextWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return metadataScreen;
            }
        });
        final PresenterScreen selectUserPresenter = wizardSelectUserScreen.populatePresenterScreen(experiment, false, 5);
        final PresenterScreen editUserPresenter = wizardController.addEditUserScreen(experiment, welcomePresenter, "Edit User", "Edit User", metadataScreen, 6, null, null, new String[]{"workerId:Speaker name *:.'{'3,'}':Please enter at least three letters."}, "Save and continue", null, null, null, false, "Could not contact the server, please check your internet connection and try again.", false);
        final PresenterScreen debugScreenPresenter = wizardController.addDebugScreen(experiment, autoMenuPresenter, 17, obfuscateScreenNames);
        final PresenterScreen kinshipPresenter = addKinshipScreen(experiment, autoMenuPresenter, null, 16);
        welcomeMenuPresenter.setNextPresenter(metadataScreen);
        return experiment;
    }

    public PresenterScreen createStimulusScreen(final Experiment experiment, final PresenterScreen backPresenter, final String screenLabel, final PresenterScreen nextPresenter, final String stimulusTagArray[], final StimuliSubAction[] featureValuesArray, final boolean randomiseStimuli, final int maxStimuli, boolean filePerStimulus, final String end_of_stimuli, long displayOrder, boolean obfuscateScreenNames) {
        final WizardStimulusScreen wizardStimulusScreen = new WizardStimulusScreen();
        wizardStimulusScreen.setScreenTitle(screenLabel);
        wizardStimulusScreen.setScreenLabel(screenLabel);
        wizardStimulusScreen.setStimulusTagArray(stimulusTagArray);
        wizardStimulusScreen.setFeatureValuesArray(featureValuesArray);
        wizardStimulusScreen.setMaxStimuli(maxStimuli);
        wizardStimulusScreen.setRandomiseStimuli(randomiseStimuli);
        wizardStimulusScreen.setFilePerStimulus(filePerStimulus);
        wizardStimulusScreen.setEnd_of_stimuli(end_of_stimuli);
        wizardStimulusScreen.setBackWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return backPresenter;
            }

            @Override
            public String getScreenTag() {
                return backPresenter.getSelfPresenterTag();
            }

        });
        wizardStimulusScreen.setNextWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return nextPresenter;
            }

            @Override
            public String getScreenTag() {
                return nextPresenter.getSelfPresenterTag();
            }

        });
        return wizardStimulusScreen.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
    }

    public PresenterScreen addKinshipScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Kinship", "Kinship", backPresenter, "Kinship", nextPresenter, PresenterType.kindiagram, displayOrder);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.addKinTypeGui, null);
        presenterFeature1.addFeatureAttributes(FeatureAttribute.diagramName, "kinDiagram");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        final PresenterFeature presenterFeature2 = new PresenterFeature(FeatureType.loadKinTypeStringDiagram, null);
        presenterFeature2.addFeatureAttributes(FeatureAttribute.diagramName, "kinDiagram");
        presenterFeature2.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        presenterScreen.getPresenterFeatureList().add(presenterFeature2);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
