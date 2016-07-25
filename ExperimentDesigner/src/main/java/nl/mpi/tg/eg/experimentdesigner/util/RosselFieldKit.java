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
        final WizardTextScreen instructionsPresenter = new WizardTextScreen("Instructions", "Task 1 and Task 2 each have different background images to be located as follows:<br/>"
                + "/MPI_STIMULI/background1.png<br/>"
                + "/MPI_STIMULI/background2.png<br/>"
                + "<br/>"
                + "The stimuli for task 1 and task 2 are to be placed in the following directories:<br/>"
                + "/MPI_STIMULI/Task_1<br/>"
                + "/MPI_STIMULI/Task_2<br/>"
                + "<br/>"
                + "Each image for task 1 and task 2 should have an mp3 file with the same name.<br/>"
                + "Each image group for task 1 and task 2 should be prefixed by a string common to that group followed by an underscore:<br/>"
                + "\"1_pig.png\" \"1_pig.mp3\"<br/>"
                + "\"2_bat.png\" \"2_bat.mp3\"<br/>"
                + "\"2_fish.png\" \"2_fish.mp3\"<br/>"
                + "\"1_rat.png\" \"1_rat.mp3\"<br/>"
                + "<br/>"
                + "Task 2 has an additional audio file per stimilus. This file has the postfix _question as follows:<br/>"
                + "\"1_pig.png\" \"1_pig.mp3\" \"1_pig_question.mp3\"<br/>"
                + "<br/>"
                + "The MPI Stimuli task<br/>"
                + ""
                + ""
                + ""
                + "Recorded audio will be stored on the sdcard in the MPI_Recorder directory."
                + "/data/media/0/MPI_Recorder/155feab481d-87fb-9eb9-46c9-3e40/SDCardPictureTask/2016-07-18-175348.wav'."
                + ""
                + "Import recordings into ELAN"
                + ""
                + ""
                + "Hidden buttons"
                + ""
                + "",
                "Begin");

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
        final WizardAnimatedStimuliScreen task1Screen = new WizardAnimatedStimuliScreen("Task 1 Demo", images, false, 1000, true, "Next", "background1.png", false);
        final WizardAnimatedStimuliScreen task2Screen = new WizardAnimatedStimuliScreen("Task 2 Demo", images, false, 1000, true, "Next", "background2.png", true);
//        final WizardAnimatedStimuliScreen pictureTaskScreen = new WizardAnimatedStimuliScreen("PictureTask", images, false, 1000, true, "Next", "background.png", false);
        final WizardAnimatedStimuliScreen task1ScreenSD = new WizardAnimatedStimuliScreen("Task 1", new String[]{"Task1"}, true, 1000, true, "Next", "file:///storage/emulated/0/MPI_STIMULI/background1.png", false);
        final WizardAnimatedStimuliScreen task2ScreenSD = new WizardAnimatedStimuliScreen("Task 2", new String[]{"Task2"}, true, 1000, true, "Next", "file:///storage/emulated/0/MPI_STIMULI/background2.png", true);
        welcomePresenter.setInstructionsScreen(instructionsPresenter);
        welcomePresenter.setProgramWizardScreen(welcomeMenuPresenter);
        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen("Select Participant");
        wizardSelectUserScreen.setBackWizardScreen(welcomePresenter);
        wizardSelectUserScreen.setNextWizardScreen(menuScreen);
        final WizardEditUserScreen editUserPresenter = new WizardEditUserScreen("Information about the participant", "Edit User", null, null, new String[]{"workerId:Participant ID:.'{'3,'}':Please enter at least three letters."}, "Save and continue", null, null, null, false, "Could not contact the server, please check your internet connection and try again.");
//        final WizardAboutScreen debugScreenPresenter = new WizardAboutScreen();

        final WizardStimulusScreen wizardStimulusScreen = new WizardStimulusScreen();
        wizardStimulusScreen.setScreenTitle("FieldKit Stimuli");
        wizardStimulusScreen.setMenuLabel("FieldKit Stimuli");
        wizardStimulusScreen.setScreenLabel("FieldKit Stimuli");
        wizardStimulusScreen.setScreenTag("FieldKitScreen");
//        experiment, welcomePresenter, "cutbreak", welcomePresenter, new String[]{"cutbreak"}, grammaticalityValuesArray, true, 1000, false, "end_of_stimuli", 15, obfuscateScreenNames
        wizardStimulusScreen.setStimulusTagArray(new String[]{"FieldKit"});
        wizardStimulusScreen.setFeatureValuesArray(new StimuliSubAction[]{new StimuliSubAction("80", "the informant talks about the image", "next")});
        wizardStimulusScreen.setMaxStimuli(1000);
        wizardStimulusScreen.setRandomiseStimuli(true);
        wizardStimulusScreen.setFilePerStimulus(false);
        wizardStimulusScreen.setEnd_of_stimuli("Complete");

        editUserPresenter.setBackWizardScreen(welcomePresenter);
        task1Screen.setBackWizardScreen(menuScreen);
        task2Screen.setBackWizardScreen(menuScreen);
        task1Screen.setNextWizardScreen(menuScreen);
        task2Screen.setNextWizardScreen(menuScreen);
//        debugScreenPresenter.setBackWizardScreen(menuScreen);
        task1ScreenSD.setBackWizardScreen(menuScreen);
        task1ScreenSD.setNextWizardScreen(menuScreen);
        task2ScreenSD.setBackWizardScreen(menuScreen);
        task2ScreenSD.setNextWizardScreen(menuScreen);
        editUserPresenter.setNextWizardScreen(menuScreen);
        welcomeMenuPresenter.setNextWizardScreen(editUserPresenter);
        welcomeMenuPresenter.setBackWizardScreen(instructionsPresenter);
        instructionsPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setNextWizardScreen(welcomeMenuPresenter);
        wizardStimulusScreen.setBackWizardScreen(menuScreen);
        wizardStimulusScreen.setEndOfStimulisWizardScreen(menuScreen);
        menuScreen.addTargetScreen(task1ScreenSD);
        menuScreen.addTargetScreen(task2ScreenSD);
        menuScreen.addTargetScreen(task1Screen);
        menuScreen.addTargetScreen(task2Screen);
        menuScreen.addTargetScreen(wizardStimulusScreen);
//        menuScreen.addTargetScreen(debugScreenPresenter);
        menuScreen.addTargetScreen(instructionsPresenter);
        wizardData.addScreen(welcomePresenter);
        wizardData.addScreen(welcomeMenuPresenter);
        wizardData.addScreen(instructionsPresenter);
        wizardData.addScreen(editUserPresenter);
        wizardData.addScreen(menuScreen);
        wizardData.addScreen(wizardSelectUserScreen);
        wizardData.addScreen(wizardStimulusScreen);
        wizardData.addScreen(task1Screen);
        wizardData.addScreen(task2Screen);
        wizardData.addScreen(task1ScreenSD);
        wizardData.addScreen(task2ScreenSD);
//        wizardData.addScreen(debugScreenPresenter);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
