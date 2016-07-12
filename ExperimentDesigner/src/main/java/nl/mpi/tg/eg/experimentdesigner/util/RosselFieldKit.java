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
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
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

        final WizardWelcomeScreen welcomePresenter = new WizardWelcomeScreen("Welcome", "Instructions", "Begin", null, null);
        final WizardExistingUserCheckScreen welcomeMenuPresenter = new WizardExistingUserCheckScreen("Start", "New interview", "Resume interview", "Begin a new interview with a new participant", "Resume an interview with an existing participant");
        final WizardTextScreen instructionsPresenter = new WizardTextScreen("Instructions", "Show the pictures and interact with the screen.", "Begin");
        String[] images = new String[]{
            "pig.png",
            "bat.png",
            "fish.png",
            "rat.png"
        };
        final WizardRandomStimulusScreen pictureTaskScreen = new WizardRandomStimulusScreen("PictureTask", images, 1000, true, "ja [ z ],nee [ . ]", "", "");
        welcomePresenter.setInstructionsScreen(instructionsPresenter);
        welcomePresenter.setProgramWizardScreen(welcomeMenuPresenter);
        final WizardSelectUserScreen wizardSelectUserScreen = new WizardSelectUserScreen();
        wizardSelectUserScreen.setBackWizardScreen(welcomePresenter);
        final WizardEditUserScreen editUserPresenter = new WizardEditUserScreen("Infomesen blong man/woman we i toktok", "Edit User", null, null, new String[]{"workerId:Nem blong man/woman we i toktok:.'{'3,'}':Please enter at least three letters."}, "Savem infomesen", null, null, null, false, "Could not contact the server, please check your internet connection and try again.");
        final WizardAboutScreen debugScreenPresenter = new WizardAboutScreen();
        editUserPresenter.setBackWizardScreen(welcomePresenter);
        pictureTaskScreen.setBackWizardScreen(welcomePresenter);
        editUserPresenter.setNextWizardScreen(pictureTaskScreen);
        welcomeMenuPresenter.setNextWizardScreen(editUserPresenter);
        welcomeMenuPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setBackWizardScreen(welcomePresenter);
        instructionsPresenter.setNextWizardScreen(welcomeMenuPresenter);
        wizardData.addScreen(welcomePresenter);
        wizardData.addScreen(welcomeMenuPresenter);
        wizardData.addScreen(instructionsPresenter);
        wizardData.addScreen(editUserPresenter);
        wizardData.addScreen(wizardSelectUserScreen);
        wizardData.addScreen(pictureTaskScreen);
        wizardData.addScreen(debugScreenPresenter);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
