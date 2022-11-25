/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics
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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioTestScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardGridStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardSelectUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Jan 09, 2018 10:33 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class PlayhouseStudy {

    private final WizardController wizardController = new WizardController();
    final String completionScreenText1 = "Dit is het einde van het experiment.<br/>"
            + "Hartelijk dank voor uw deelname! <br/>"
            + "<br/>";
    final String completionScreenText2 = "<br/>"
            + "Het bovenstaande nummer is het bewijs dat u het experiment heeft voltooid, en is vereist voor het in orde maken van uw vergoeding.";

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("Playhouse Study");
        wizardData.setShowMenuBar(false);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        // insert the zoomToGarden etc into the CSS section of the XML and remove it from the SCSS of Frinex
        String css = "\n"
                + "        .gwt-Button.optionButton.rightOverlayButton, .borderedVideoRight,\n"
                + "        .right3TouchButton, .right3OverlayButton {\n"
                + "            right: 1vw;\n"
                + "        }\n"
                + "\n"
                + "        .centre3TouchButton, .centre3OverlayButton {\n"
                + "            left: 50%; \n"
                + "            transform: translate(-50%,0);\n"
                + "        }\n"
                + "\n"
                + "        .gwt-Button.optionButton.leftOverlayButton, .borderedVideoLeft,\n"
                + "        .left3TouchButton, .left3OverlayButton {\n"
                + "            left: 1vw;\n"
                + "        }\n"
                + "        .gwt-Image.topLeftTouchButton,\n"
                + "        .gwt-Image.topRightTouchButton,\n"
                + "        .gwt-Image.bottomLeftTouchButton,\n"
                + "        .gwt-Image.bottomRightTouchButton,\n"
                + "        .gwt-Image.borderedVideoLeft, .gwt-Image.borderedVideoRight, .borderedVideoFull {\n"
                + "            object-fit: contain;\n"
                + "            -webkit-touch-callout: none;\n"
                + "            -webkit-user-select: none;\n"
                + "            -khtml-user-select: none;\n"
                + "            -moz-user-select: none;\n"
                + "            -ms-user-select: none;\n"
                + "            user-select: none;\n"
                + "        }\n"
                + "        .borderedVideoFull {\n"
                + "            width: 98vw;\n"
                + "            top: 50vh;\n"
                + "            left: 50vw;\n"
                + "            transform: translate(-50%,-50%);\n"
                + "            padding: 1vw;\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton, .gwt-Button.optionButton.topRightOverlayButton,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton, .gwt-Button.optionButton.bottomRightOverlayButton,\n"
                + "        .gwt-Button.optionButton.rightOverlayButton, .gwt-Button.optionButton.leftOverlayButton,\n"
                + "        .gwt-Button.optionButton.left3OverlayButton,\n"
                + "        .gwt-Button.optionButton.centre3OverlayButton,\n"
                + "        .gwt-Button.optionButton.right3OverlayButton {\n"
                + "            position: fixed;\n"
                + "            opacity: 0.0;\n"
                + "            background: grey;\n"
                + "            color: grey;\n"
                + "            -webkit-touch-callout: none;\n"
                + "            -webkit-user-select: none;\n"
                + "            -khtml-user-select: none;\n"
                + "            -moz-user-select: none;\n"
                + "            -ms-user-select: none;\n"
                + "            user-select: none;\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.left3OverlayButton,\n"
                + "        .gwt-Button.optionButton.centre3OverlayButton,\n"
                + "        .gwt-Button.optionButton.right3OverlayButton {\n"
                + "            top: 80px;\n"
                + "            width: 32vw;\n"
                + "            height: 25vw;\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.rightOverlayButton, .gwt-Button.optionButton.leftOverlayButton {\n"
                + "            top: 80px;\n"
                + "            width: 47vw;\n"
                + "            height: 36vw;\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton.optionButtonActivated, .gwt-Button.optionButton.topRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton.optionButtonActivated, .gwt-Button.optionButton.bottomRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButtonIntersection, .gwt-Button.optionButton.topRightOverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButtonIntersection, .gwt-Button.optionButton.bottomRightOverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.leftOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.rightOverlayButton.optionButtonActivated,\n"
                + "        /*.gwt-Button.optionButton.rightOverlayButton:hover, .gwt-Button.optionButton.leftOverlayButton:hover,*/\n"
                + "        /*.gwt-Button.optionButton.rightOverlayButton:active, .gwt-Button.optionButton.leftOverlayButton:active,*/\n"
                + "        .gwt-Button.optionButton.rightOverlayButtonIntersection, .gwt-Button.optionButton.leftOverlayButtonIntersection,\n"
                + "\n"
                + "        .gwt-Button.optionButton.left3OverlayButton.optionButtonActivated, .gwt-Button.optionButton.left3OverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.centre3OverlayButton.optionButtonActivated, .gwt-Button.optionButton.centre3OverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.right3OverlayButton.optionButtonActivated, .gwt-Button.optionButton.right3OverlayButtonIntersection,\n"
                + "        {\n"
                + "            opacity: 0.5;\n"
                + "        }\n"
                + "        .left3TouchButton, .centre3TouchButton, .right3TouchButton {\n"
                + "            width: 30vw;\n"
                + "            height: 23vw;\n"
                + "            top: 80px;\n"
                + "            padding: 1vw;\n"
                + "        }\n"
                + "\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton,\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButtonIntersection,\n"
                + "        .gwt-Image.topLeftTouchButton {\n"
                + "            transform: translate(-105%,-105%);\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.topRightOverlayButton,\n"
                + "        .gwt-Button.optionButton.topRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.topRightOverlayButtonIntersection,\n"
                + "        .gwt-Image.topRightTouchButton {\n"
                + "            transform: translate(5%,-105%);\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButtonIntersection,\n"
                + "        .gwt-Image.bottomLeftTouchButton {\n"
                + "            transform: translate(-105%,5%);\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.bottomRightOverlayButton,\n"
                + "        .gwt-Button.optionButton.bottomRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.bottomRightOverlayButtonIntersection,\n"
                + "        .gwt-Image.bottomRightTouchButton {\n"
                + "            transform: translate(5%,5%);\n"
                + "        }\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton.optionButtonActivated, .gwt-Button.optionButton.topRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton.optionButtonActivated, .gwt-Button.optionButton.bottomRightOverlayButton.optionButtonActivated,\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButtonIntersection, .gwt-Button.optionButton.topRightOverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButtonIntersection, .gwt-Button.optionButton.bottomRightOverlayButtonIntersection,\n"
                + "        .gwt-Button.optionButton.topLeftOverlayButton, .gwt-Button.optionButton.topRightOverlayButton,\n"
                + "        .gwt-Button.optionButton.bottomLeftOverlayButton, .gwt-Button.optionButton.bottomRightOverlayButton {\n"
                + "            width: 47vmin;\n"
                + "            height: 36vmin;\n"
                + "            left: 50vw;\n"
                + "            top: 50vh;\n"
                + "        }\n"
                + "        .gwt-Image.topLeftTouchButton,\n"
                + "        .gwt-Image.topRightTouchButton,\n"
                + "        .gwt-Image.bottomLeftTouchButton,\n"
                + "        .gwt-Image.bottomRightTouchButton {\n"
                + "            width: 45vmin;\n"
                + "            height: 34vmin;\n"
                + "            left: 50vw;\n"
                + "            top: 50vh;\n"
                + "            padding: 1vmin;\n"
                + "        }\n"
                + "        .borderedVideoLeft, .borderedVideoRight {\n"
                + "            width: 45vw;\n"
                + "            height: 34vw;\n"
                + "            top: 80px;\n"
                + "            padding: 1vw;\n"
                + "        }\n"
                + "        .topLeftTouchButton,\n"
                + "        .topRightTouchButton,\n"
                + "        .bottomLeftTouchButton,\n"
                + "        .bottomRightTouchButton,\n"
                + "        .left3TouchButton, .centre3TouchButton, .right3TouchButton,\n"
                + "        .borderedVideoLeft, .borderedVideoRight, .borderedVideoFull {\n"
                + "            position: fixed;\n"
                + "            border-color: $primaryColour2;\n"
                + "            border-radius: 15px;\n"
                + "            outline: none;\n"
                + "            border-width: 1px;\n"
                + "            border-style: solid;\n"
                + "        }\n";
        wizardData.setScss(css);
        final WizardTextScreen bluetoothInstructionsScreen = new WizardTextScreen("Instructions", "Some useful instructions", "Volgende");
        bluetoothInstructionsScreen.setNextHotKey("ENTER");
        wizardData.addScreen(bluetoothInstructionsScreen);

        final WizardExistingUserCheckScreen existingUserCheckScreen = new WizardExistingUserCheckScreen("Select Participant", "New interview", "Resume interview", "Begin a new interview with a new participant", "Resume an interview with an existing participant");
        final WizardSelectUserScreen selectUserScreen = new WizardSelectUserScreen("Select Participant");
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Gegevens");
        wizardEditUserScreen.setMenuLabel("Terug");
        wizardEditUserScreen.setScreenTag("Gegevens");
        wizardEditUserScreen.setNextButton("Volgende");
        wizardEditUserScreen.setSendData(false);
        wizardEditUserScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:ppcode:.'{'3,'}':Voer minimaal drie letters.", // @todo: update the regex to date format and in the future add a calandar popup
            "datOfBirth:Geboortedatum:[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]:Voer een getal.",
            "groupCode:Group Code:[01]?[0-9]?[0-9]:Voer een nummer (000-199).",
            "experimentVersion:Experiment Version:1|2|3|4:Kies en experiment."
        });
        existingUserCheckScreen.setNextWizardScreen(wizardEditUserScreen);
        final WizardMenuScreen menuScreen = new WizardMenuScreen("Menu", "Menu", "Menu");
        wizardData.addScreen(menuScreen);
        wizardData.addScreen(existingUserCheckScreen);
        wizardData.addScreen(selectUserScreen);
        wizardData.addScreen(wizardEditUserScreen);
        String backgroundImage = "huisje_02.jpg";
//        WizardAudioTestScreen introductionAudio1 = new WizardAudioTestScreen("Introduction 1", "&nbsp;", "continue button", "intro_1");
//        wizardData.addScreen(introductionAudio1);
//        WizardAudioTestScreen introductionAudio2 = new WizardAudioTestScreen("Introduction 2", "&nbsp;", "continue button", "intro_2");
//        wizardData.addScreen(introductionAudio2);
//        WizardAudioTestScreen introductionAudio3 = new WizardAudioTestScreen("Introduction 3", "&nbsp;", "continue button", "intro_3");
//        wizardData.addScreen(introductionAudio3);
//        introductionAudio1.setBackgroundImage(backgroundImage);
//        introductionAudio2.setBackgroundImage(backgroundImage);
//        introductionAudio3.setBackgroundImage(backgroundImage);
//        introductionAudio1.setAutoNext(true);
//        introductionAudio2.setAutoNext(true);
//        introductionAudio3.setAutoNext(true);
//        introductionAudio1.setAudioHotKey("ENTER");
//        introductionAudio2.setAudioHotKey("ENTER");
//        introductionAudio3.setAudioHotKey("ENTER");
//        introductionAudio1.setStyleName("titleBarButton");
//        introductionAudio2.setStyleName("titleBarButton");
//        introductionAudio3.setStyleName("titleBarButton");
        String[][][][] testList = new String[][][][]{
            // TODO: insert the zoomToGarden etc into the CSS section of the XML and remove it from the SCSS of Frinex
            {{{"Practice1", "zoomToGarden", "Practice", "balloon.jpg", "AudioAB", "1000", "chimes_welldoneditbetekend", null}, {}},
            {{"P_01", "P_01.jpg"}, {"P_02", "P_02.jpg"}, {"P_03", "P_03.jpg"}}},
            {{{"Practice2", "zoomToGarden", "Practice", "balloon.jpg", "AudioAB", "1000", "chimes_welldoneditbetekend", null}, {}},
            {{"P_04", "P_04.jpg"}}},
            //            {{{"PracticeA", "zoomToGarden", "PracticeA", null, "AudioAB", "1000", null, null}, {}},
            //            {{"P_01", "P_01.jpg"}, {"P_02", "P_02.jpg"}, {"P_03", "P_03.jpg"}}},
            //            {{{"PracticeB", null, "PracticeB", "balloon.jpg", null, "1000", "chimes_welldoneditbetekend", null}, {}},
            //            {}},
            //            {{{"PracticeC", null, "PracticeC", null, "AudioAB", "1000", null, null}, {}},
            //            {{"P_04", "P_04.jpg"}}},
            //            {{{"PracticeD", null, "PracticeD", "balloon.jpg", null, "1000", "chimes_welldone", null}, {}},
            //            {}},

            {{{"Matching1", "zoomToBlock1", "Matching1", "balloon.jpg", "AudioAB", "2000", "chimes_fantastisch", "BeginMatching"}, {}},
            {{"M_01_A", "M_01_A.jpg"}, {"M_02_B", "M_02_B.jpg"}, {"M_03_N", "M_03_N.jpg"}, {"M_04_B", "M_04_B.jpg"}, {"M_05_A", "M_05_A.jpg"}, {"M_06_N", "M_06_N.jpg"}, {"M_07_B", "M_07_B.jpg"}, {"M_08_A", "M_08_A.jpg"}, {"M_09_A", "M_09_A.jpg"}}},
            {{{"Matching2", "zoomToBlock2", "Matching2", "balloon.jpg", "AudioAB", "2000", "chimes_goedgedaan", "ChangeMatching"}, {}},
            {{"M_10_N", "M_10_N.jpg"}, {"M_11_N", "M_11_N.jpg"}, {"M_12_B", "M_12_B.jpg"}, {"M_13_A", "M_13_A.jpg"}, {"M_14_B", "M_14_B.jpg"}, {"M_15_N", "M_15_N.jpg"}, {"M_16_B", "M_16_B.jpg"}, {"M_17_A", "M_17_A.jpg"}, {"M_18_N", "M_18_N.jpg"}}},
            {{{"Test3", "zoomToBlock3", "Test3", "balloon.jpg", "AudioRepeat2", "2000", "chimes_super", "BeginTest"}, {}},
            {{"T_01", "T_01.jpg"}, {"T_02", "T_02.jpg"}, {"T_03", "T_03.jpg"}, {"T_04", "T_04.jpg"}, {"T_05", "T_05.jpg"}, {"T_06", "T_06.jpg"}, {"T_07", "T_07.jpg"}, {"T_08", "T_08.jpg"}, {"T_09", "T_09.jpg"}}},
            {{{"Test4", "zoomToBlock4", "Test4", "balloon.jpg", "AudioRepeat2", "2000", "chimes_prima", "ChangeTest"}, {}},
            {{"T_10", "T_10.jpg"}, {"T_11", "T_11.jpg"}, {"T_12", "T_12.jpg"}, {"T_13", "T_13.jpg"}, {"T_14", "T_14.jpg"}, {"T_15", "T_15.jpg"}, {"T_16", "T_16.jpg"}, {"T_17", "T_17.jpg"}, {"T_18", "T_18.jpg"}}},};

        final WizardMenuScreen textMenuScreen = new WizardMenuScreen("TestMenu", "TestMenu", "TestMenu");
//        textMenuScreen.setJumpToRandomScreen(true);
        wizardData.addScreen(textMenuScreen);
        WizardScreen backScreen = wizardEditUserScreen;
        for (String[][][] testSubList : testList) {
            final WizardGridStimulusScreen testStimulusScreen = new WizardGridStimulusScreen(testSubList[0][0][0], false, testSubList[1],
                    null, 1000, false, null, 0, 0, null);
            testStimulusScreen.setBackgroundImage(backgroundImage);
            testStimulusScreen.setCodeAudio(false);
            testStimulusScreen.setBackgroundStyle(testSubList[0][0][1]);
            testStimulusScreen.setRewardImage(testSubList[0][0][3]);
            testStimulusScreen.setAudioAB(testSubList[0][0][4]);
            testStimulusScreen.setSelectedPause(Integer.parseInt(testSubList[0][0][5]));
            testStimulusScreen.setCorrectAudio(testSubList[0][0][6]);
            testStimulusScreen.setIntroAudio(testSubList[0][0][7]);
            testStimulusScreen.setIntroAudioDelay(2000);
            testStimulusScreen.setStimuliButtonArray("left,centre,right");
            textMenuScreen.addTargetScreen(testStimulusScreen);
            wizardData.addScreen(testStimulusScreen);
            testStimulusScreen.setBackWizardScreenData(textMenuScreen.getWizardScreenData());
            backScreen.setNextWizardScreen(testStimulusScreen);
            backScreen = testStimulusScreen;
//            testStimulusScreen.setNextWizardScreen(textMenuScreen);
        }
        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, true, false, false, completionScreenText2,
                "Opnieuw beginnen",
                "Einde van het experiment",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");
        completionScreen.setSendData(true);
        completionScreen.setScreenTag("completion");

        WizardAudioTestScreen atticScreen = new WizardAudioTestScreen("Attic", "&nbsp;", "continue button", null);
        wizardData.addScreen(atticScreen);
        textMenuScreen.addTargetScreen(atticScreen);
        wizardData.addScreen(completionScreen);
        atticScreen.setBackgroundImage(backgroundImage);
        atticScreen.setBackgroundStyle("zoomToAttic");
        atticScreen.setAutoPlay(true);
        atticScreen.setAutoNext(false);
        atticScreen.setAutoNextDelay(2000);
        atticScreen.setAudioHotKey("R1_MA_A");
        atticScreen.setImageName("Playroom.jpg");
        atticScreen.setNextHotKey("ENTER");
        atticScreen.setImageStyle("zoomToPlayroom");
        atticScreen.setButtonStyle("titleBarButton");
        atticScreen.setBackWizardScreen(textMenuScreen);
        atticScreen.setNextWizardScreen(completionScreen);
        bluetoothInstructionsScreen.setBackWizardScreen(menuScreen);
        bluetoothInstructionsScreen.setNextWizardScreen(existingUserCheckScreen);
        existingUserCheckScreen.setBackWizardScreen(bluetoothInstructionsScreen);
//        existingUserCheckScreen.setNextWizardScreen(selectUserScreen);
        selectUserScreen.setBackWizardScreen(existingUserCheckScreen);
        selectUserScreen.setNextWizardScreen(wizardEditUserScreen);

//        wizardTextScreen.setNextWizardScreen(wizardEditUserScreen);
//        agreementScreen.setNextWizardScreen(wizardTextScreen);
//        wizardTextScreen.setBackWizardScreen(agreementScreen);
//        wizardEditUserScreen.setNextWizardScreen(trainingStimulusScreen);
//        introductionAudio1.setNextWizardScreen(introductionAudio2);
//        introductionAudio2.setNextWizardScreen(introductionAudio3);
//        introductionAudio3.setNextWizardScreen(trainingStimulusScreen);
//        fillerStimulusScreen.setNextWizardScreen(trainingStimulusScreen);
//        trainingStimulusScreen.setNextWizardScreen(textMenuScreen);
//        introductionAudio1.setBackWizardScreen(menuScreen);
//        introductionAudio2.setBackWizardScreen(menuScreen);
//        introductionAudio3.setBackWizardScreen(menuScreen);
//        fillerStimulusScreen.setBackWizardScreen(introductionAudio3);
//        trainingStimulusScreen.setBackWizardScreen(menuScreen);
        textMenuScreen.setBackWizardScreen(menuScreen);
        backScreen.setNextWizardScreen(atticScreen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        wizardAboutScreen.setBackWizardScreen(menuScreen);
//        completionScreen.setBackWizardScreen(menuScreen);
        completionScreen.setNextWizardScreen(bluetoothInstructionsScreen);
        wizardData.addScreen(wizardAboutScreen);
        wizardEditUserScreen.setBackWizardScreen(existingUserCheckScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
