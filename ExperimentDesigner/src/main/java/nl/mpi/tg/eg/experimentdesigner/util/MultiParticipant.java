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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
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
        "1-1.png:small:moveRotated10", "1-7.png:small:moveRotated180", "2-6.png:small:moveRotated350", "3-5.png:small:moveRotated300", "4-4.png:small:moveRotated200", "1-2.png:small:moveRotated200", "2-1.png:small:moveRotated200", "2-7.png:small:moveRotated200",
        "3-6.png:small:moveRotated10", "4-5.png:small:moveRotated180", "1-3.png:small:moveRotated350", "2-2.png:small:moveRotated300", "3-1.png:small:moveRotated200", "3-7.png:small:moveRotated200", "4-6.png:small:moveRotated200", "1-4.png:small:moveRotated200",
        "2-3.png:small:moveRotated10", "3-2.png:small:moveRotated180", "4-1.png:small:moveRotated350", "4-7.png:small:moveRotated300", "1-5.png:small:moveRotated200", "2-4.png:small:moveRotated200", "3-3.png:small:moveRotated200", "4-2.png:small:moveRotated200",
        "1-6.png:small:moveRotated10", "2-5.png:small:moveRotated180", "3-4.png:small:moveRotated350", "4-3.png:small:moveRotated300",
        "1-1.png:large:moveRotated10", "1-7.png:large:moveRotated180", "2-6.png:large:moveRotated350", "3-5.png:large:moveRotated300", "4-4.png:large:moveRotated200", "1-2.png:large:moveRotated200", "2-1.png:large:moveRotated200", "2-7.png:large:moveRotated200",
        "3-6.png:large:moveRotated10", "4-5.png:large:moveRotated180", "1-3.png:large:moveRotated350", "2-2.png:large:moveRotated300", "3-1.png:large:moveRotated200", "3-7.png:large:moveRotated200", "4-6.png:large:moveRotated200", "1-4.png:large:moveRotated200",
        "2-3.png:large:moveRotated10", "3-2.png:large:moveRotated180", "4-1.png:large:moveRotated350", "4-7.png:large:moveRotated300", "1-5.png:large:moveRotated200", "2-4.png:large:moveRotated200", "3-3.png:large:moveRotated200", "4-2.png:large:moveRotated200",
        "1-6.png:large:moveRotated10", "2-5.png:large:moveRotated180", "3-4.png:large:moveRotated350", "4-3.png:large:moveRotated300",
        "1-1.png:medium:moveRotated10", "1-7.png:medium:moveRotated180", "2-6.png:medium:moveRotated350", "3-5.png:medium:moveRotated300", "4-4.png:medium:moveRotated200", "1-2.png:medium:moveRotated200", "2-1.png:medium:moveRotated200", "2-7.png:medium:moveRotated200",
        "3-6.png:medium:moveRotated10", "4-5.png:medium:moveRotated180", "1-3.png:medium:moveRotated350", "2-2.png:medium:moveRotated300", "3-1.png:medium:moveRotated200", "3-7.png:medium:moveRotated200", "4-6.png:medium:moveRotated200", "1-4.png:medium:moveRotated200",
        "2-3.png:medium:moveRotated10", "3-2.png:medium:moveRotated180", "4-1.png:medium:moveRotated350", "4-7.png:medium:moveRotated300", "1-5.png:medium:moveRotated200", "2-4.png:medium:moveRotated200", "3-3.png:medium:moveRotated200", "4-2.png:medium:moveRotated200",
        "1-6.png:medium:moveRotated10", "2-5.png:medium:moveRotated180", "3-4.png:medium:moveRotated350", "4-3.png:medium:moveRotated300"
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

        final String groupMembers4 = "A,B,C,D";
        final String groupMembers8 = "A,B,C,D,E,F,G,H";
        final String textEntryPhaseRoles4 = "A,C:-:-:B,D:-:-";
        final String textEntryPhaseRoles8 = "A,C,E,G:-:-:B,D,F,H:-:-";
        final String textEntryPhaseText = "The producer sees the stimulus and enters some text";
        final String textWaitPhaseRoles4 = "B,D:-:-:A,C:-:-";
        final String textWaitPhaseRoles8 = "B,D,F,H:-:-:A,C,E,G:-:-";
        final String textWaitPhaseText = "The guesser waits for the producer";
        final String gridWaitPhaseRoles4 = "-:A,C:-:-:B,D:-";
        final String gridWaitPhaseRoles8 = "-:A,C,E,G:-:-:B,D,F,H:-";
        final String gridWaitPhaseText = "The producer waits for the guesser";
        final String responseGridPhaseRoles4 = "-:B,D:-:-:A,C:-";
        final String responseGridPhaseRoles8 = "-:B,D,F,H:-:-:A,C,E,G:-";
        final String responseGridPhaseText = "The guesser sees the text with a grid of stimuli, from which they select one based on the text";
        final String mutualFeedbackPhaseRoles4 = "-:-:A,B,C,D:-:-:A,B,C,D";
        final String mutualFeedbackPhaseRoles8 = "-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H";
        final String mutualFeedbackPhaseText = "The guesser and producer see the allocated stimuli with the guessers selected stimuli and the text from the producer. Following this the next stimulus is selected and the flow returns to activity 0.";

        final WizardMultiParticipantScreen round0MultiParticipantScreen4 = new WizardMultiParticipantScreen("Round 0 - 4",
                groupMembers4,
                "A,B,C,D",
                "A:-:B:-:C:-:D:-",
                "The producer sees the stimulus and enters some text",
                "B,C,D:-:A,C,D:-:B,A,D:-:B,C,A:-",
                true,
                "All other participants wait for the producer",
                "-:A:-:B:-:C:-:D",
                "The producer waits for all other participants",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "-:B,C,D:-:A,C,D:-:B,A,D:-:B,C,A",
                "The stimulus and the producers text are shown to all other participants", 23);
        final WizardMultiParticipantScreen round0MultiParticipantScreen8 = new WizardMultiParticipantScreen("Round 0 - 8",
                groupMembers8,
                "A,B,C,D,E,F,G,H",
                "A:-:B:-:C:-:D:-:E:-:F:-:G:-:H:-",
                "The producer sees the stimulus and enters some text",
                "B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-",
                true,
                "All other participants wait for the producer",
                "-:A:-:B:-:C:-:D:-:E:-:F:-:G:-:H",
                "The producer waits for all other participants",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A",
                "The stimulus and the producers text are shown to all other participants", 23);
        final WizardMultiParticipantScreen round1MultiParticipantScreen4 = new WizardMultiParticipantScreen("Round 1 - 4",
                groupMembers4,
                "A,B|C,D",
                textEntryPhaseRoles4,
                textEntryPhaseText,
                textWaitPhaseRoles4,
                false,
                textWaitPhaseText,
                gridWaitPhaseRoles4,
                gridWaitPhaseText,
                responseGridPhaseRoles4,
                responseGridPhaseText,
                mutualFeedbackPhaseRoles4,
                mutualFeedbackPhaseText,
                "",
                "This phase is not used in this screen", 23);
        final WizardMultiParticipantScreen round1MultiParticipantScreen8 = new WizardMultiParticipantScreen("Round 1 - 8",
                groupMembers8,
                "A,B|C,D|E,F|G,H",
                textEntryPhaseRoles8,
                textEntryPhaseText,
                textWaitPhaseRoles8,
                false,
                textWaitPhaseText,
                gridWaitPhaseRoles8,
                gridWaitPhaseText,
                responseGridPhaseRoles8,
                responseGridPhaseText,
                mutualFeedbackPhaseRoles8,
                mutualFeedbackPhaseText,
                "",
                "This phase is not used in this screen", 23);
        final WizardMultiParticipantScreen round2MultiParticipantScreen4 = new WizardMultiParticipantScreen("Round 2 - 4",
                groupMembers4,
                "B,C|D,A",
                textEntryPhaseRoles4,
                textEntryPhaseText,
                textWaitPhaseRoles4,
                false,
                textWaitPhaseText,
                gridWaitPhaseRoles4,
                gridWaitPhaseText,
                responseGridPhaseRoles4,
                responseGridPhaseText,
                mutualFeedbackPhaseRoles4,
                mutualFeedbackPhaseText,
                "",
                "This phase is not used in this screen", 23);
        final WizardMultiParticipantScreen round2MultiParticipantScreen8 = new WizardMultiParticipantScreen("Round 2 - 8",
                groupMembers8,
                "B,C|D,E|F,G|H,A",
                textEntryPhaseRoles8,
                textEntryPhaseText,
                textWaitPhaseRoles8,
                false,
                textWaitPhaseText,
                gridWaitPhaseRoles8,
                gridWaitPhaseText,
                responseGridPhaseRoles8,
                responseGridPhaseText,
                mutualFeedbackPhaseRoles8,
                mutualFeedbackPhaseText,
                "",
                "This phase is not used in this screen", 23);
        round0MultiParticipantScreen4.setStimuliSet(stimuliArray);
        round1MultiParticipantScreen4.setStimuliSet(stimuliArray);
        round2MultiParticipantScreen4.setStimuliSet(stimuliArray);
        round0MultiParticipantScreen8.setStimuliSet(stimuliArray);
        round1MultiParticipantScreen8.setStimuliSet(stimuliArray);
        round2MultiParticipantScreen8.setStimuliSet(stimuliArray);
        round0MultiParticipantScreen4.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
        round1MultiParticipantScreen4.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
        round2MultiParticipantScreen4.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
        round0MultiParticipantScreen8.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
        round1MultiParticipantScreen8.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
        round2MultiParticipantScreen8.setStimulusFreeText(true, "[^0-9^S^J^s^j^C^c^V^v^W^w^T^t^Z^z^Y^y^X^x]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");

        WizardCompletionScreen completionScreen = new WizardCompletionScreen("Einde van het experiment", false, false,
                //                                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                "Einde van het experiment1",
                "Opnieuw beginnen",
                "Einde van het experiment2",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");
        WizardMenuScreen menuScreen4or8Members = new WizardMenuScreen("GroupSizeMenu", "GroupSizeMenu", "GroupSizeMenu");

        wizardData.addScreen(wizardAgreementScreen);
        wizardData.addScreen(wizardEditUserScreen);
        wizardData.addScreen(menuScreen4or8Members);
        wizardData.addScreen(round0MultiParticipantScreen4);
        wizardData.addScreen(round1MultiParticipantScreen4);
        wizardData.addScreen(round2MultiParticipantScreen4);
        wizardData.addScreen(round0MultiParticipantScreen8);
        wizardData.addScreen(round1MultiParticipantScreen8);
        wizardData.addScreen(round2MultiParticipantScreen8);
        wizardData.addScreen(wizardAboutScreen);
        wizardData.addScreen(completionScreen);

        wizardAgreementScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardEditUserScreen.setBackWizardScreen(wizardAgreementScreen);
        wizardEditUserScreen.setNextWizardScreen(menuScreen4or8Members);
        menuScreen4or8Members.addTargetScreen(round0MultiParticipantScreen4);
        menuScreen4or8Members.addTargetScreen(round0MultiParticipantScreen8);
        round0MultiParticipantScreen4.setNextWizardScreen(round1MultiParticipantScreen4);
        round0MultiParticipantScreen8.setNextWizardScreen(round1MultiParticipantScreen8);
        round1MultiParticipantScreen4.setNextWizardScreen(round2MultiParticipantScreen4);
        round1MultiParticipantScreen8.setNextWizardScreen(round2MultiParticipantScreen8);
        round0MultiParticipantScreen4.setBackWizardScreen(wizardAgreementScreen);
        round0MultiParticipantScreen8.setBackWizardScreen(wizardAgreementScreen);
        round1MultiParticipantScreen4.setBackWizardScreen(wizardAgreementScreen);
        round1MultiParticipantScreen8.setBackWizardScreen(wizardAgreementScreen);
        round2MultiParticipantScreen4.setBackWizardScreen(wizardAgreementScreen);
        round2MultiParticipantScreen8.setBackWizardScreen(wizardAgreementScreen);
        round2MultiParticipantScreen4.setNextWizardScreen(completionScreen);
        round2MultiParticipantScreen8.setNextWizardScreen(completionScreen);
//        endTextScreen.setNextWizardScreen(wizardAboutScreen);
        wizardAboutScreen.setBackWizardScreen(wizardAgreementScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
