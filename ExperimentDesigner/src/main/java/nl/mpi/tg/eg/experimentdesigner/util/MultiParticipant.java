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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMultiParticipantScreen;

/**
 * @since Oct 21, 2016 11:52:03 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class MultiParticipant {

    private final WizardController wizardController = new WizardController();
    // @todo: scoring for each participant 
    // @todo: the allocated member id must maintained thoughout the experiment
    // @todo: show the experiment total score not the participants score
    // @todo: round 0 is the naming screen and does not collect guesses and therefore no scores
    // @todo: make the stimuli list common between all group members
    //  @todo: limit the communication to within each channel
    // @todo: add the group data collection
    // @todo: add the group data CSV output
    // @todo: add an option in the admin reporting app to "hide ugly details" which hides strings like UUID and browser version strings

//,Round,Dyad,Game.no,Item.ID,Shape,Size,RawSize,ItemCurrentAge,Producer,Word,ACC
//165,1,AB,1,10,2,small,2.74,2,A,flup,0
//167,1,CD,1,10,2,small,2.74,2,C,mozel,1
//65,1,AB,2,5,1,small,2.94,2,B,mozel,1
//67,1,CD,2,5,1,small,2.94,2,D,tjalp,1
//297,1,AB,3,19,4,medium,4.4,2,A,flolp,1
//299,1,CD,3,19,4,medium,3.95,2,C,vlolp,1
//147,1,AB,4,9,2,medium,4.51,2,B,kimi,0
//149,1,CD,4,9,2,medium,4.51,2,D,vlalp,0
//85,1,AB,5,6,2,big,7.6,2,A,dauft,0
//87,1,CD,5,6,2,big,7.6,2,C,potmik,1
    private final String[] stimuliArray = new String[]{
        "1-1.png:small", "1-7.png:small", "2-6.png:small", "3-5.png:small", "4-4.png:small", "1-2.png:small", "2-1.png:small", "2-7.png:small",
        "3-6.png:small", "4-5.png:small", "1-3.png:small", "2-2.png:small", "3-1.png:small", "3-7.png:small", "4-6.png:small", "1-4.png:small",
        "2-3.png:small", "3-2.png:small", "4-1.png:small", "4-7.png:small", "1-5.png:small", "2-4.png:small", "3-3.png:small", "4-2.png:small",
        "1-6.png:small", "2-5.png:small", "3-4.png:small", "4-3.png:small",
        "1-1.png:large", "1-7.png:large", "2-6.png:large", "3-5.png:large", "4-4.png:large", "1-2.png:large", "2-1.png:large", "2-7.png:large",
        "3-6.png:large", "4-5.png:large", "1-3.png:large", "2-2.png:large", "3-1.png:large", "3-7.png:large", "4-6.png:large", "1-4.png:large",
        "2-3.png:large", "3-2.png:large", "4-1.png:large", "4-7.png:large", "1-5.png:large", "2-4.png:large", "3-3.png:large", "4-2.png:large",
        "1-6.png:large", "2-5.png:large", "3-4.png:large", "4-3.png:large",
        "1-1.png:medium", "1-7.png:medium", "2-6.png:medium", "3-5.png:medium", "4-4.png:medium", "1-2.png:medium", "2-1.png:medium", "2-7.png:medium",
        "3-6.png:medium", "4-5.png:medium", "1-3.png:medium", "2-2.png:medium", "3-1.png:medium", "3-7.png:medium", "4-6.png:medium", "1-4.png:medium",
        "2-3.png:medium", "3-2.png:medium", "4-1.png:medium", "4-7.png:medium", "1-5.png:medium", "2-4.png:medium", "3-3.png:medium", "4-2.png:medium",
        "1-6.png:medium", "2-5.png:medium", "3-4.png:medium", "4-3.png:medium"
    /*, "beep.wav"*/
    };

    // @todo: server shared variables to be used in animations and interactions concurrently displayed on multiple users devices 
    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("MultiParticipant");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);

        final WizardAgreementScreen wizardAgreementScreen = new WizardAgreementScreen("Agreement", "<b>This is a prototype multiparticipant experiment.</b><br/><br/>"
                + "With this prototype you can:<br/>"
                + "<li>view the group activity and add dummy users to a group for testing purposes <a href='/multiparticipant/grouptestpage.html'>/multiparticipant/grouptestpage.html</a>.</li>"
                + "<li>erase the local data for this browser <a href='/multiparticipant?debug'>/multiparticipant?debug</a></li>"
                + "<li>random data can be generated with the testing robot <a href='/multiparticipant/TestingFrame.html'>/multiparticipant/TestingFrame.html</a></li><br/>"
                + "<li>eight users intracting can be randomly simulated testing robot <a href='/multiparticipant/grouptestframes.html'>/multiparticipant/grouptestframes.html</a></li><br/>"
                + "The group name must be allocated with the following with <a href='/multiparticipant/?group=a_group_name'>/multiparticipant/?group=a_group_name</a> where a_group_name should be replaced with a suitable string. A second user can be tested on one computer via the incognito browser window with this link, providing the group name matches.<br/><br/>"
                + "There needs to be eight users connected for the group process to begin. Once a group is full, any subsequent users will need to be allocated a different group via the a_group_name parameter.<br/><br/><br/>"
                + "You can view the collected group data with <a href='/multiparticipant-admin/groupdataviewer'>/multiparticipant-admin/groupdataviewer</a> with the user and password you have been supplied.<br/><br/><br/>",
                "Continue");
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen(true);

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

        final WizardMultiParticipantScreen wizardMultiParticipantScreen = new WizardMultiParticipantScreen();
        wizardMultiParticipantScreen.setStimuliSet(stimuliArray);
        wizardMultiParticipantScreen.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");

        wizardData.addScreen(wizardAgreementScreen);
        wizardData.addScreen(wizardEditUserScreen);
        wizardData.addScreen(wizardMultiParticipantScreen);
        wizardData.addScreen(wizardAboutScreen);

        wizardAgreementScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardEditUserScreen.setBackWizardScreen(wizardAgreementScreen);
        wizardEditUserScreen.setNextWizardScreen(wizardMultiParticipantScreen);
        wizardMultiParticipantScreen.setBackWizardScreen(wizardEditUserScreen);
        wizardMultiParticipantScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardAboutScreen.setBackWizardScreen(wizardAgreementScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
