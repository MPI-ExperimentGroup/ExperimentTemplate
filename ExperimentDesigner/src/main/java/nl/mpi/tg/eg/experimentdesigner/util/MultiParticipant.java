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
//    int[] possibleAngles = new int[]{30, 45, 60, 90, 120, 135, 150, 180, 210, 225, 240, 270, 300, 315, 330, 360};   
//    private String[] getStimuli(){
//        for (int textureIndex = 1; textureIndex < 29; textureIndex++) {
//            
//        }
//    }
    final int numberOfStimuli = 9;
    final String preStimuliText = "Add information before the beginning of each round, telling the participants who they’re about to play with";
    final String postStimuliText = "there should be a summary informing participants at the end of each round about how many points they made as a dyad and as a group for that round (and so far).";

    private final String[] stimuliArray = new String[]{
        "1.png:shape1:version4:quadrant1:moveRotated90",
        "1.png:shape1:version2:version3:quadrant2:moveRotated150",
        "1.png:shape1:version1:version5:quadrant3:moveRotated210",
        "2.png:shape1:version2:version2zero:quadrant1:moveRotated30",
        "2.png:shape1:version6:quadrant2:moveRotated120",
        "2.png:shape1:version5:quadrant2:moveRotated135",
        "2.png:shape1:version1:quadrant3:moveRotated270",
        "2.png:shape1:version4:version4zero:quadrant4:moveRotated315",
        "3.png:shape1:version1:quadrant1:moveRotated90",
        "3.png:shape1:version2:version4:version4zero:quadrant3:moveRotated210",
        "3.png:shape1:version6:quadrant4:moveRotated315",
        "3.png:shape1:version3:version3zero:quadrant4:moveRotated360",
        "4.png:shape1:version4:quadrant1:moveRotated30",
        "4.png:shape1:version2:version2zero:quadrant1:moveRotated90",
        "4.png:shape1:version3:version3zero:quadrant3:moveRotated210",
        "4.png:shape1:version1:version5:version5zero:quadrant4:moveRotated330",
        "5.png:shape1:version1:version1zero:version6:quadrant1:moveRotated30",
        "5.png:shape1:version3:quadrant1:moveRotated60",
        "5.png:shape1:version2:quadrant4:moveRotated300",
        "6.png:shape1:version5:quadrant1:moveRotated90",
        "6.png:shape1:version6:version6zero:quadrant3:moveRotated225",
        "6.png:shape1:version3:quadrant3:moveRotated240",
        "6.png:shape1:version1:quadrant4:moveRotated300",
        "6.png:shape1:version2:quadrant4:moveRotated360",
        "7.png:shape1:version5:version5zero:quadrant1:moveRotated30",
        "7.png:shape1:version1:version1zero:quadrant2:moveRotated135",
        "7.png:shape1:version4:quadrant3:moveRotated225",
        "7.png:shape1:version2:quadrant3:moveRotated240",
        "7.png:shape1:version6:quadrant3:moveRotated270",
        "7.png:shape1:version3:quadrant4:moveRotated315",
        "8.png:shape2:version5:quadrant1:moveRotated45",
        "8.png:shape2:version4:quadrant1:moveRotated60",
        "8.png:shape2:version2:version2zero:quadrant2:moveRotated120",
        "8.png:shape2:version6:quadrant3:moveRotated225",
        "8.png:shape2:version3:version3zero:quadrant3:moveRotated270",
        "9.png:shape2:version2:quadrant1:moveRotated45",
        "9.png:shape2:version3:version3zero:version4:version4zero:quadrant1:moveRotated90",
        "9.png:shape2:version1:version1zero:quadrant3:moveRotated270",
        "9.png:shape2:version6:version6zero:quadrant4:moveRotated300",
        "9.png:shape2:version5:quadrant4:moveRotated330",
        "10.png:shape2:version5:quadrant2:moveRotated120",
        "10.png:shape2:version1:version1zero:quadrant2:moveRotated150",
        "10.png:shape2:version3:quadrant3:moveRotated210",
        "10.png:shape2:version2:quadrant3:moveRotated225",
        "11.png:shape2:version3:quadrant1:moveRotated45",
        "11.png:shape2:version5:quadrant1:moveRotated90",
        "11.png:shape2:version4:quadrant2:moveRotated150",
        "11.png:shape2:version6:quadrant2:moveRotated180",
        "11.png:shape2:version1:quadrant4:moveRotated300",
        "11.png:shape2:version1:quadrant4:moveRotated330",
        "12.png:shape2:version1:quadrant1:moveRotated30",
        "12.png:shape2:version6:version6zero:quadrant1:moveRotated60",
        "12.png:shape2:version3:quadrant2:moveRotated135",
        "12.png:shape2:version5:version5zero:quadrant3:moveRotated225",
        "12.png:shape2:version4:quadrant4:moveRotated315",
        "13.png:shape2:version2:quadrant2:moveRotated180",
        "13.png:shape2:version4:version4zero:quadrant3:moveRotated210",
        "13.png:shape2:version5:quadrant3:moveRotated270",
        "13.png:shape2:version3:version3zero:quadrant4:moveRotated330",
        "13.png:shape2:version6:quadrant4:moveRotated360",
        "14.png:shape2:version1:quadrant2:moveRotated120",
        "14.png:shape2:version5:version6:quadrant2:moveRotated135",
        "14.png:shape2:version3:quadrant2:moveRotated180",
        "14.png:shape2:version2:quadrant4:moveRotated330",
        "15.png:shape3:version3:version3zero:quadrant1:moveRotated45",
        "15.png:shape3:version2:quadrant1:moveRotated60",
        "15.png:shape3:version5:version5zero:quadrant4:moveRotated300",
        "15.png:shape3:version6:quadrant4:moveRotated315",
        "15.png:shape3:version4:quadrant4:moveRotated330",
        "16.png:shape3:version1:version4:version4zero:quadrant1:moveRotated45",
        "16.png:shape3:version3:version3zero:quadrant2:moveRotated135",
        "16.png:shape3:version2:version2zero:version5:quadrant2:moveRotated180",
        "16.png:shape3:version6:quadrant3:moveRotated240",
        "17.png:shape3:version4:version6:version6zero:quadrant2:moveRotated120",
        "17.png:shape3:version5:version5zero:quadrant3:moveRotated240",
        "17.png:shape3:version1:version1zero:version2:quadrant4:moveRotated315",
        "18.png:shape3:version6:quadrant1:moveRotated45",
        "18.png:shape3:version5:version5zero:quadrant2:moveRotated150",
        "18.png:shape3:version3:quadrant2:moveRotated180",
        "18.png:shape3:version4:version4zero:quadrant4:moveRotated300",
        "19.png:shape3:version1:version1zero:quadrant2:moveRotated135",
        "19.png:shape3:version3:version4:quadrant3:moveRotated240",
        "19.png:shape3:version6:version6zero:quadrant3:moveRotated270",
        "20.png:shape3:version4:version4zero:quadrant2:moveRotated120",
        "20.png:shape3:version6:version6zero:quadrant2:moveRotated180",
        "20.png:shape3:version1:quadrant3:moveRotated240",
        "20.png:shape3:version3:quadrant4:moveRotated315",
        "21.png:shape3:version5:quadrant1:moveRotated30",
        "21.png:shape3:version6:quadrant1:moveRotated60",
        "21.png:shape3:version4:quadrant2:moveRotated180",
        "21.png:shape3:version2:quadrant3:moveRotated240",
        "21.png:shape3:version3:quadrant3:moveRotated270",
        "21.png:shape3:version1:quadrant4:moveRotated360",
        "22.png:shape4:version4:quadrant1:moveRotated45",
        "22.png:shape4:version1:version1zero:quadrant1:moveRotated60",
        "22.png:shape4:version2:quadrant1:moveRotated90",
        "22.png:shape4:version5:version5zero:quadrant2:moveRotated150",
        "22.png:shape4:version6:quadrant4:moveRotated330",
        "23.png:shape4:version2:quadrant2:moveRotated135",
        "23.png:shape4:version6:quadrant3:moveRotated210",
        "23.png:shape4:version3:quadrant4:moveRotated300",
        "23.png:shape4:version4:version4zero:quadrant4:moveRotated330",
        "23.png:shape4:version1:version5:quadrant4:moveRotated360",
        "24.png:shape4:version5:version5zero:quadrant1:moveRotated90",
        "24.png:shape4:version6:quadrant2:moveRotated150",
        "24.png:shape4:version1:version4:version4zero:quadrant2:moveRotated180",
        "24.png:shape4:version2:version2zero:quadrant3:moveRotated210",
        "25.png:shape4:version2:version2zero:quadrant1:moveRotated45",
        "25.png:shape4:version3:version3zero:quadrant2:moveRotated120",
        "25.png:shape4:version4:quadrant2:moveRotated150",
        "25.png:shape4:version5:quadrant3:moveRotated225",
        "25.png:shape4:version1:quadrant4:moveRotated315",
        "26.png:shape4:version6:quadrant1:moveRotated90",
        "26.png:shape4:version3:quadrant3:moveRotated225",
        "26.png:shape4:version2:quadrant3:moveRotated270",
        "27.png:shape4:version5:quadrant1:moveRotated45",
        "27.png:shape4:version1:quadrant2:moveRotated120",
        "27.png:shape4:version4:quadrant3:moveRotated270",
        "28.png:shape4:version3:version6:version6zero:quadrant1:moveRotated30",
        "28.png:shape4:version1:version1zero:quadrant3:moveRotated225",
        "28.png:shape4:version4:version5:quadrant4:moveRotated300",
        "28.png:shape4:version2:version2zero:quadrant4:moveRotated330"
    };

    // @todo: server shared variables to be used in animations and interactions concurrently displayed on multiple users devices 
    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("MultiParticipant");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);
        wizardData.setTextFontSize(24);

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
        final String textWaitPhaseRoles4 = "B,D:-:-:A,C:-:-";
        final String textWaitPhaseRoles8 = "B,D,F,H:-:-:A,C,E,G:-:-";
        final String gridWaitPhaseRoles4 = "-:A,C:-:-:B,D:-";
        final String gridWaitPhaseRoles8 = "-:A,C,E,G:-:-:B,D,F,H:-";
        final String responseGridPhaseRoles4 = "-:B,D:-:-:A,C:-";
        final String responseGridPhaseRoles8 = "-:B,D,F,H:-:-:A,C,E,G:-";
        final String mutualFeedbackPhaseRoles4 = "-:-:A,B,C,D:-:-:A,B,C,D";
        final String mutualFeedbackPhaseRoles8 = "-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H";

        WizardMenuScreen menuScreen4or8Members = new WizardMenuScreen("GroupSizeMenu", "GroupSizeMenu", "GroupSizeMenu");
        menuScreen4or8Members.setBranchOnGetParam(true, "A choice must be provided out of the following:<br/>");
        WizardCompletionScreen completionScreen = new WizardCompletionScreen("Einde van het experiment", false, false,
                //                                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                "Einde van het experiment1",
                "Opnieuw beginnen",
                "Einde van het experiment2",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");

        wizardData.addScreen(wizardAgreementScreen);
        wizardData.addScreen(wizardEditUserScreen);
        wizardData.addScreen(menuScreen4or8Members);

        String[][] groupOfFourCommunicationChannels = new String[][]{
            {"0", "A,B,C,D", "naming"},
            {"1", "A,B|C,D", "play"},
            {"2", "A,C|B,D", "play"},
            {"3", "A,D|B,C", "play"},
            {"4", "A,B|C,D", "play"},
            {"5", "A,C|B,D", "play"},
            {"6", "A,D|B,C", "play"},
            {"7", "A,B|C,D", "play"},
            {"8", "A|B|C|D", "test"}
        };
        WizardMultiParticipantScreen roundOfFourScreenOuter = null;
        for (String[] currentChannel : groupOfFourCommunicationChannels) {
            final WizardMultiParticipantScreen roundScreen;
            if ("naming".equals(currentChannel[2])) {
                roundScreen = getNamingRound("Round " + currentChannel[0] + " - 4", groupMembers4, currentChannel[1], "A:-:B:-:C:-:D:-", "B,C,D:-:A,C,D:-:B,A,D:-:B,C,A:-", "-:A,B,C,D:-:A,B,C,D:-:A,B,C,D:-:A,B,C,D");
            } else if ("test".equals(currentChannel[2])) {
                roundScreen = getTestRound("Round " + currentChannel[0] + " - 4", groupMembers4, currentChannel[1], "A,B,C,D");
            } else {
                roundScreen = getPlayingRound("Round " + currentChannel[0] + " - 4", groupMembers4, currentChannel[1], textEntryPhaseRoles4, textWaitPhaseRoles4, gridWaitPhaseRoles4, responseGridPhaseRoles4, mutualFeedbackPhaseRoles4);
            }
            roundScreen.setStimuliSet(stimuliArray);
            roundScreen.setStimulusFreeText(true, "[etuiopasdfgkzbnm ]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
            wizardData.addScreen(roundScreen);
            if (roundOfFourScreenOuter == null) {
                menuScreen4or8Members.addTargetScreen(roundScreen);
            } else {
                roundOfFourScreenOuter.setNextWizardScreen(roundScreen);
            }
            roundScreen.setBackWizardScreen(wizardAgreementScreen);
            roundOfFourScreenOuter = roundScreen;
        }
        if (roundOfFourScreenOuter != null) {
            roundOfFourScreenOuter.setNextWizardScreen(completionScreen);
        }
        String[][] groupOfEightCommunicationChannels = new String[][]{
            {"0", "A,B,C,D,E,F,G,H", "naming"},
            {"1", "A,B|C,D|E,F|G,H", "play"},
            {"2", "A,H|E,D|C,F|G,B", "play"},
            {"3", "A,D|G,F|E,B|C,H", "play"},
            {"4", "A,C|B,D|E,G|F,H", "play"},
            {"5", "A,E|H,D|B,F|G,C", "play"},
            {"6", "A,F|G,D|C,B|E,H", "play"},
            {"7", "A,G|B,H|E,C|D,F", "play"},
            {"8", "A|B|C|D|E|F|G|H", "test"},
            {"9", "A,B|C,D|E,F|G,H", "play"},
            {"10", "A,H|E,D|C,F|G,B", "play"},
            {"11", "A,D|G,F|E,B|C,H", "play"},
            {"12", "A,C|B,D|E,G|F,H", "play"},
            {"13", "A,E|H,D|B,F|G,C", "play"},
            {"14", "A,F|G,D|C,B|E,H", "play"},
            {"15", "A,G|B,H|E,C|D,F", "play"},
            {"16", "A|B|C|D|E|F|G|H", "test"}
        };
        WizardMultiParticipantScreen roundOfEightScreenOuter = null;
        for (String[] currentChannel : groupOfEightCommunicationChannels) {
            final WizardMultiParticipantScreen roundScreen;
            if ("naming".equals(currentChannel[2])) {
                roundScreen = getNamingRound("Round " + currentChannel[0] + " - 8", groupMembers8, currentChannel[1], "A:-:B:-:C:-:D:-:E:-:F:-:G:-:H:-", "B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-", "-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H:-:A,B,C,D,E,F,G,H");
            } else if ("test".equals(currentChannel[2])) {
                roundScreen = getTestRound("Round " + currentChannel[0] + " - 8", groupMembers8, currentChannel[1], "A,B,C,D,E,F,G,H");
            } else {
                roundScreen = getPlayingRound("Round " + currentChannel[0] + " - 8", groupMembers8, currentChannel[1], textEntryPhaseRoles8, textWaitPhaseRoles8, gridWaitPhaseRoles8, responseGridPhaseRoles8, mutualFeedbackPhaseRoles8);
            }
            roundScreen.setStimuliSet(stimuliArray);
            roundScreen.setStimulusFreeText(true, "[etuiopasdfgkzbnm ]{2,}", "Vul een woord in de tekstbox in dat volgens u het best aan het einde van de zin past.");
            wizardData.addScreen(roundScreen);
            if (roundOfEightScreenOuter == null) {
                menuScreen4or8Members.addTargetScreen(roundScreen);
            } else {
                roundOfEightScreenOuter.setNextWizardScreen(roundScreen);
            }
            roundScreen.setBackWizardScreen(wizardAgreementScreen);
            roundOfEightScreenOuter = roundScreen;
        }
        if (roundOfEightScreenOuter != null) {
            roundOfEightScreenOuter.setNextWizardScreen(completionScreen);
        }

        wizardData.addScreen(wizardAboutScreen);
        wizardData.addScreen(completionScreen);

        wizardAgreementScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardEditUserScreen.setBackWizardScreen(wizardAgreementScreen);
        wizardEditUserScreen.setNextWizardScreen(menuScreen4or8Members);

//        endTextScreen.setNextWizardScreen(wizardAboutScreen);
        wizardAboutScreen.setBackWizardScreen(wizardAgreementScreen);

        return wizardData;
    }

    protected WizardMultiParticipantScreen getTestRound(final String screenName, final String groupMembers4, final String communicationChannels, final String textEntryPhaseRoles) {
        return new WizardMultiParticipantScreen(screenName,
                groupMembers4,
                communicationChannels, textEntryPhaseRoles,
                "All 23 stimuli are presented all participants in random order similar to the producer screen",
                "",
                true,
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                preStimuliText, postStimuliText, numberOfStimuli, 0,
                0, 0, null
        );
    }

    protected WizardMultiParticipantScreen getNamingRound(final String screenName, final String groupMembers4, final String communicationChannels, final String textEntryPhaseRoles, final String waitingForProducerPhaseRoles, final String outcomeDisplayedPhaseRoles) {
        return new WizardMultiParticipantScreen(screenName,
                groupMembers4,
                communicationChannels, textEntryPhaseRoles,
                "The producer sees the stimulus and enters some text", waitingForProducerPhaseRoles,
                true,
                "All other participants wait for the producer",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen",
                "",
                "This phase is not used in this screen", outcomeDisplayedPhaseRoles,
                "The stimulus and the producers text are shown to all participants",
                preStimuliText, postStimuliText, numberOfStimuli, 7000,
                0, 0, null);
    }

    protected WizardMultiParticipantScreen getPlayingRound(final String screenName, final String groupMembers, final String communicationChannels, final String textEntryPhaseRoles, final String textWaitPhaseRoles, final String gridWaitPhaseRoles, final String responseGridPhaseRoles, final String mutualFeedbackPhaseRoles) {
        final String textEntryPhaseText = "The producer sees the stimulus and enters some text";
        final String textWaitPhaseText = "The guesser waits for the producer";
        final String gridWaitPhaseText = "The producer waits for the guesser";
        final String responseGridPhaseText = "The guesser sees the text with a grid of stimuli, from which they select one based on the text";
        final String mutualFeedbackPhaseText = "The guesser and producer see the allocated stimuli with the guessers selected stimuli and the text from the producer. Following this the next stimulus is selected and the flow returns to activity 0.";
        final int timerCountDownProducerMs = 30 * 1000;
        final int timerCountDownGuesserMs = 20 * 1000;
        final String timerCountDownLabel = "Time is up! Play now!";

        return new WizardMultiParticipantScreen(screenName,
                groupMembers,
                communicationChannels,
                textEntryPhaseRoles,
                textEntryPhaseText,
                textWaitPhaseRoles,
                false,
                textWaitPhaseText,
                gridWaitPhaseRoles,
                gridWaitPhaseText,
                responseGridPhaseRoles,
                responseGridPhaseText,
                mutualFeedbackPhaseRoles,
                mutualFeedbackPhaseText,
                "",
                "This phase is not used in this screen",
                preStimuliText, postStimuliText, numberOfStimuli, 0,
                timerCountDownProducerMs, timerCountDownGuesserMs, timerCountDownLabel);
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
