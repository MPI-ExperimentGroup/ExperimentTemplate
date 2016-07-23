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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAnimatedStimuliScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardWelcomeScreen;

/**
 * @since July 12, 2016 13:33:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class RosselFieldKit {

    private final WizardController wizardController = new WizardController();

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("RosselFieldKit");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);

        final WizardWelcomeScreen welcomePresenter = new WizardWelcomeScreen("Rossel Island FieldKit", "Welcome", "Instructions", "Begin", null, null);
        final WizardExistingUserCheckScreen welcomeMenuPresenter = new WizardExistingUserCheckScreen("Start", "New interview", "Resume interview", "Begin a new interview with a new participant", "Resume an interview with an existing participant");
        final WizardTextScreen instructionsPresenter = new WizardTextScreen("Instructions", "Show the pictures and interact with the screen.", "Begin");

        final WizardMenuScreen menuScreen = new WizardMenuScreen("Menu", "Menu", "Menu");
        menuScreen.setBackWizardScreen(welcomePresenter);
        String[] images = new String[]{
            "1_pig.png",
            "2_bat.png",
            "2_fish.png",
            "1_rat.png", //            "ffmpeg -i 1_pig.wav 1_pig.mp3",
        //            "ffmpeg -i 2_bat.wav 2_bat.mp3",
        //            "ffmpeg -i 2_fish.wav 2_fish.mp3",
        //            "ffmpeg -i 1_rat.wav 1_rat.mp3"
        };
        final WizardAnimatedStimuliScreen pictureTaskScreen = new WizardAnimatedStimuliScreen("PictureTask", images, false, 1000, true, "Next", "background.png");
        final WizardAnimatedStimuliScreen pictureTaskScreenSD = new WizardAnimatedStimuliScreen("SDCardPictureTask", new String[]{"AnimatedPictures"}, true, 1000, true, "Next", "file:///storage/emulated/0/MPI_STIMULI/background.png");
        welcomePresenter.setInstructionsScreen(instructionsPresenter);
        welcomePresenter.setProgramWizardScreen(welcomeMenuPresenter);
        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen();
        wizardSelectUserScreen.setBackWizardScreen(welcomePresenter);
        wizardSelectUserScreen.setNextWizardScreen(menuScreen);
        final WizardEditUserScreen editUserPresenter = new WizardEditUserScreen("Infomesen blong man/woman we i toktok", "Edit User", null, null, new String[]{"workerId:Nem blong man/woman we i toktok:.'{'3,'}':Please enter at least three letters."}, "Savem infomesen", null, null, null, false, "Could not contact the server, please check your internet connection and try again.");
        final WizardAboutScreen debugScreenPresenter = new WizardAboutScreen();

        final WizardStimulusScreen wizardStimulusScreen = new WizardStimulusScreen();
        wizardStimulusScreen.setScreenTitle("MPI Stimuli");
        wizardStimulusScreen.setMenuLabel("MPI Stimuli");
        wizardStimulusScreen.setScreenLabel("MPI Stimuli");
        wizardStimulusScreen.setScreenTag("MPI_STIMULIScreen");
//        experiment, welcomePresenter, "cutbreak", welcomePresenter, new String[]{"cutbreak"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 15, obfuscateScreenNames
        wizardStimulusScreen.setStimulusTagArray(new String[]{"MPI_STIMULI"});
        wizardStimulusScreen.setFeatureValuesArray(new StimuliSubAction[]{new StimuliSubAction("80", "the informant talks/says whatever s/he wants", "next")});
        wizardStimulusScreen.setMaxStimuli(1000);
        wizardStimulusScreen.setRandomiseStimuli(true);
        wizardStimulusScreen.setFilePerStimulus(false);
        wizardStimulusScreen.setEnd_of_stimuli("Complete");

        editUserPresenter.setBackWizardScreen(welcomePresenter);
        pictureTaskScreen.setBackWizardScreen(menuScreen);
        pictureTaskScreen.setNextWizardScreen(menuScreen);
        pictureTaskScreenSD.setBackWizardScreen(menuScreen);
        pictureTaskScreenSD.setNextWizardScreen(menuScreen);
        editUserPresenter.setNextWizardScreen(menuScreen);
        welcomeMenuPresenter.setNextWizardScreen(editUserPresenter);
        welcomeMenuPresenter.setBackWizardScreen(instructionsPresenter);
        instructionsPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setNextWizardScreen(welcomeMenuPresenter);
        wizardStimulusScreen.setBackWizardScreen(menuScreen);
        wizardStimulusScreen.setEndOfStimulisWizardScreen(menuScreen);
        menuScreen.addTargetScreen(pictureTaskScreen);
        menuScreen.addTargetScreen(pictureTaskScreenSD);
        menuScreen.addTargetScreen(wizardStimulusScreen);
        menuScreen.addTargetScreen(debugScreenPresenter);
        menuScreen.addTargetScreen(instructionsPresenter);
        wizardData.addScreen(welcomePresenter);
        wizardData.addScreen(welcomeMenuPresenter);
        wizardData.addScreen(instructionsPresenter);
        wizardData.addScreen(editUserPresenter);
        wizardData.addScreen(menuScreen);
        wizardData.addScreen(wizardSelectUserScreen);
        wizardData.addScreen(wizardStimulusScreen);
        wizardData.addScreen(pictureTaskScreen);
        wizardData.addScreen(pictureTaskScreenSD);
        wizardData.addScreen(debugScreenPresenter);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
