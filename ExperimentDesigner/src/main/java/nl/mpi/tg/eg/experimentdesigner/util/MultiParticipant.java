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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMultiParticipantScreen;

/**
 * @since Oct 21, 2016 11:52:03 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class MultiParticipant {

    private final WizardController wizardController = new WizardController();

    private final String[] stimuliArray
            = new String[]{"1-1.png", "1-7.png", "2-6.png", "3-5.png", "4-4.png"
                + "1-2.png", "2-1.png", "2-7.png", "3-6.png", "4-5.png"
                + "1-3.png", "2-2.png", "3-1.png", "3-7.png", "4-6.png"
                + "1-4.png", "2-3.png", "3-2.png", "4-1.png", "4-7.png"
                + "1-5.png", "2-4.png", "3-3.png", "4-2.png",
                "1-6.png", "2-5.png", "3-4.png", "4-3.png", "beep.wav"};

    // @todo: server shared variables to be used in animations and interactions concurrently displayed on multiple users devices 
    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("MultiParticipant");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);

        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Edit User");
        wizardEditUserScreen.setMenuLabel("Edit User");
        wizardEditUserScreen.setScreenTag("Edit_User");
        wizardEditUserScreen.setNextButton("Next");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Could not contact the server, please check your internet connection and try again.");
        wizardEditUserScreen.setAgeField();
        wizardEditUserScreen.setFirstNameField();
        wizardEditUserScreen.setGenderField();
        wizardEditUserScreen.setWorkerIdField();
        wizardData.addScreen(wizardEditUserScreen);

        final WizardMultiParticipantScreen wizardMultiParticipantScreen = new WizardMultiParticipantScreen();
        wizardMultiParticipantScreen.setStimuliSet(stimuliArray);
        wizardData.addScreen(wizardMultiParticipantScreen);
        wizardEditUserScreen.setNextWizardScreen(wizardMultiParticipantScreen);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
