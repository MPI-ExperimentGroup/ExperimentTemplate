/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.HashMap;
import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.AbstractWizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioTestScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardExistingUserCheckScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardGridStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScoreBranchingScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilAudioTest;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilMenu;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilMetadata;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilSelectParticipant;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilSendData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilStimuliData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilText;

/**
 * @since Nov 16, 2017 3:25:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SentenceCompletion {

    private final WizardController wizardController = new WizardController();
    private final WizardUtilData wizardUtilData;

    public SentenceCompletion(WizardUtilData wizardUtilData) {
        this.wizardUtilData = wizardUtilData;
    }

    private void updateScreenToMenuRelations(HashMap<String, WizardMenuScreen> screenToMenuMap, AbstractWizardScreen lastScreen, AbstractWizardScreen currentScreen, boolean showBackButton) {
        boolean isInMenu = false;
        for (String menuItemString : screenToMenuMap.keySet()) {
            if (menuItemString.equals(currentScreen.getWizardScreenData().getMenuLabel())) {
                screenToMenuMap.get(menuItemString).addTargetScreen(currentScreen);
                currentScreen.setBackWizardScreen(screenToMenuMap.get(menuItemString));
                isInMenu = true;
            }
        }
        if (lastScreen != null && !isInMenu) {
            if (showBackButton) {
                currentScreen.setBackWizardScreen(lastScreen);
            }
        }
        if (lastScreen != null) {
            lastScreen.setNextWizardScreen(currentScreen);
        }
    }

    public WizardData getWizardData() {
        HashMap<String, WizardMenuScreen> screenToMenuMap = new HashMap<>();
        WizardData wizardData = new WizardData();
        wizardData.setAppName(wizardUtilData.getExperimentTitle());
        wizardData.setShowMenuBar(wizardUtilData.isShowMenuBar());
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardMenuScreen mainMenuScreen = null;
        AbstractWizardScreen firstScreen = null;
        AbstractWizardScreen lastScreen = null;

//        WizardTextScreen wizardTextScreen = new WizardTextScreen("Informatie", wizardUtilData.getInstructionsText(),
//                "volgende [ spatiebalk ]"
//        );
//        wizardTextScreen.setMenuLabel("Terug");
        //Information screen 
        //Agreement
//        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Toestemming", wizardUtilData.getAgreementText(), "Akkoord");
//        agreementScreen.setMenuLabel("Terug");
//        wizardData.addScreen(agreementScreen);
//        wizardData.addScreen(wizardTextScreen);
//        if (lastScreen != null) {
//            lastScreen.setNextWizardScreen(wizardTextScreen);
//        }
//        lastScreen = wizardTextScreen;
//        wizardData.setAgreementText("agreementText");
//        wizardData.setDisagreementScreenText("disagreementScreenText");
        for (WizardUtilScreen screenData : wizardUtilData.getScreenData()) {
            final WizardUtilText introductionText = screenData.getTextScreen();
            if (introductionText != null) {
                WizardTextScreen introductionScreen = new WizardTextScreen(introductionText.getTitle(), introductionText.getText(),
                        introductionText.getButonLabel()
                );
                introductionScreen.setMenuLabel(introductionText.getMenuLabel());
                if (introductionText.getHotkey() != null) {
                    introductionScreen.setNextHotKey(introductionText.getHotkey());
                }
                wizardData.addScreen(introductionScreen);
                if (introductionText.isShowBackButton()) {
                    if (mainMenuScreen != null) {
                        introductionScreen.setBackWizardScreen(mainMenuScreen);
                    } else if (lastScreen != null) {
                        introductionScreen.setBackWizardScreen(lastScreen);
                    }
                }
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(introductionScreen.getWizardScreenData());
                }
                updateScreenToMenuRelations(screenToMenuMap, lastScreen, introductionScreen, introductionText.isShowBackButton());
                lastScreen = introductionScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
            final WizardUtilMenu menuScreenData = screenData.getMenuScreen();
            if (menuScreenData != null) {
                WizardMenuScreen menuScreen = new WizardMenuScreen(menuScreenData.getTitle(), menuScreenData.getTitle(), menuScreenData.getTitle());
                if (menuScreenData.isIsMainMenu()) {
                    mainMenuScreen = menuScreen;
                    if (lastScreen != null) {
                        lastScreen.getWizardScreenData().setBackWizardScreenData(mainMenuScreen.getWizardScreenData());
                    }
                } else if (mainMenuScreen != null) {
                    menuScreen.getWizardScreenData().setBackWizardScreenData(mainMenuScreen.getWizardScreenData());
                }
                if (menuScreenData.getMenuItems() != null) {
                    for (String menuItemString : menuScreenData.getMenuItems()) {
                        screenToMenuMap.put(menuItemString, menuScreen);
                    }
                }
//                menuScreen.setScreenText(screenData.getMenuScreen().getText());
//                if (lastScreen != null) {
//                    lastScreen.getWizardScreenData().setNextWizardScreenData(menuScreen.getWizardScreenData());
//                }
                // the menu screen should not be the first screen
//                wizardData.getWizardScreens().add(1, menuScreen.getWizardScreenData());
                wizardData.addScreen(menuScreen);
//                lastScreen = menuScreen;
//                if (firstScreen == null) {
//                    firstScreen = lastScreen;
//                }
            }
            final WizardUtilText agreementScreenText = screenData.getAgreementScreen();
            if (agreementScreenText != null) {
                WizardAgreementScreen agreementScreen = new WizardAgreementScreen(agreementScreenText.getTitle(), agreementScreenText.getText(),
                        agreementScreenText.getButonLabel()
                );
                agreementScreen.setMenuLabel(agreementScreenText.getMenuLabel());
                wizardData.addScreen(agreementScreen);
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(agreementScreen.getWizardScreenData());
                }
                updateScreenToMenuRelations(screenToMenuMap, lastScreen, agreementScreen, agreementScreenText.isShowBackButton());
                lastScreen = agreementScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
            final WizardUtilSelectParticipant selectParticipantMenu = screenData.getSelectParticipantMenu();
            if (selectParticipantMenu != null) {
                final WizardExistingUserCheckScreen existingUserCheckScreen = new WizardExistingUserCheckScreen(
                        selectParticipantMenu.getSelectParticipantTitle(),
                        selectParticipantMenu.getNewParticipantButton(),
                        selectParticipantMenu.getResumeParticipantButton(),
                        selectParticipantMenu.getNewParticipantText(),
                        selectParticipantMenu.getResumeParticipantText());
                existingUserCheckScreen.setGenerateSelectUserMenuScreen(true);
//                final WizardSelectUserScreen selectUserScreen = new WizardSelectUserScreen(selectParticipantMenu.getSelectParticipantTitle());
                wizardData.addScreen(existingUserCheckScreen);
//                wizardData.addScreen(selectUserScreen);
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(existingUserCheckScreen.getWizardScreenData());
                    existingUserCheckScreen.setBackWizardScreen(lastScreen);
                }
                updateScreenToMenuRelations(screenToMenuMap, lastScreen, existingUserCheckScreen, false);
                lastScreen = existingUserCheckScreen;
//                selectUserScreen.getWizardScreenData().setBackWizardScreenData(existingUserCheckScreen.getWizardScreenData());
                if (firstScreen == null) {
                    firstScreen = existingUserCheckScreen;
                }
            }
            final WizardUtilMetadata metadataScreen = screenData.getMetadataScreen();
            // todo: add the metadata screen intro text here as 

            // todo: some flag needs to be added where "dateOfBirth:Geboortedatum","dateOfTest:Testdatum:" are used to display age in months. 
            if (metadataScreen != null) {
                //metadata
                final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
                wizardEditUserScreen.setScreenText(metadataScreen.getText());
                wizardEditUserScreen.setScreenTitle(metadataScreen.getTitle());
                wizardEditUserScreen.setMenuLabel(metadataScreen.getMenuLabel());
                wizardEditUserScreen.setScreenTag(metadataScreen.getTitle());
                wizardEditUserScreen.setNextButton(metadataScreen.getButonLabel());
                wizardEditUserScreen.setSendData(metadataScreen.isSendData());
                wizardEditUserScreen.setOn_Error_Text(metadataScreen.getConnectionErrorText());
//        wizardData.setAgeField(true);
                wizardEditUserScreen.setCustomFields(metadataScreen.getMetadataFields());

                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(wizardEditUserScreen.getWizardScreenData());
                    if (metadataScreen.isShowBackButton()) {
                        wizardEditUserScreen.setBackWizardScreen(lastScreen);
                    }
                }
                wizardData.addScreen(wizardEditUserScreen);
                updateScreenToMenuRelations(screenToMenuMap, lastScreen, wizardEditUserScreen, metadataScreen.isShowBackButton());
                lastScreen = wizardEditUserScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
//            WizardScreenData firstStimuliScreen = wizardEditUserScreen.getWizardScreenData();
            final WizardUtilStimuliData stimuliData = screenData.getStimuliData();
            if (stimuliData != null) {
                if (stimuliData.getInstructions() != null) {
                    WizardTextScreen stimulusInstructionsScreen = new WizardTextScreen(stimuliData.getStimuliName() + " Informatie", stimuliData.getInstructions(),
                            "volgende [ spatiebalk ]"
                    );
                    stimulusInstructionsScreen.setMenuLabel("Terug");
                    wizardData.addScreen(stimulusInstructionsScreen);
//                    stimulusInstructionsScreen.setNextWizardScreen(wizardEditUserScreen);
//                    if (lastScreen != null) {
//                        agreementScreen.setNextWizardScreen(stimulusInstructionsScreen);
//                    }
                    if (lastScreen != null) {
                        lastScreen.setNextWizardScreenData(stimulusInstructionsScreen.getWizardScreenData());
                    }
                    updateScreenToMenuRelations(screenToMenuMap, lastScreen, stimulusInstructionsScreen, false);
                    lastScreen = stimulusInstructionsScreen;
                    if (firstScreen == null) {
                        firstScreen = lastScreen;
                    }
                }
                final AbstractWizardScreen abstractWizardScreen;
                final WizardUtilStimuliData.StimuliType stimuliType = (stimuliData.getStimuliType() == null) ? WizardUtilStimuliData.StimuliType.text : stimuliData.getStimuliType();
                final boolean enableBackButton;
                switch (stimuliType) {
                    case touch:
                        abstractWizardScreen = new WizardGridStimulusScreen(stimuliData);
                        if (lastScreen != null) {
                            abstractWizardScreen.getWizardScreenData().getMenuWizardScreenData().add(lastScreen.getWizardScreenData());
                        }
                        enableBackButton = true;
                        break;
                    case text:
                    default:
                        abstractWizardScreen = new WizardRandomStimulusScreen(stimuliData);
                        enableBackButton = false;
                        break;
                }
                wizardData.addScreen(abstractWizardScreen);

                updateScreenToMenuRelations(screenToMenuMap, lastScreen, abstractWizardScreen, enableBackButton);
                lastScreen = abstractWizardScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
            if (screenData.getScoreBranching() != null) {
                String branchingScreenName = "ScoreBranching" + wizardData.getWizardScreens().size();
//                for (String value : screenData.getScoreBranching()) {
//                    branchingScreenName += value;
//                }
                WizardScoreBranchingScreen branchingScreen = new WizardScoreBranchingScreen(branchingScreenName, branchingScreenName, branchingScreenName);
                if (stimuliData != null) {
                branchingScreen.setStimuliLabel(stimuliData.getStimuliName());
                }
                if (screenData.getScoreBranching().length > 2) {
                    branchingScreen.setStartingSet(Integer.parseInt(screenData.getScoreBranching()[0]), screenData.getScoreBranching()[1], screenData.getScoreBranching()[2]);
                }
                if (screenData.getScoreBranching().length > 5) {
                    branchingScreen.setExitSet(Integer.parseInt(screenData.getScoreBranching()[3]), screenData.getScoreBranching()[4], screenData.getScoreBranching()[5]);
                }
                wizardData.addScreen(branchingScreen);
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(branchingScreen.getWizardScreenData());
                }
                lastScreen = branchingScreen;
            }
            final WizardUtilSendData utilSendData = screenData.getSendDataScreen();
            if (utilSendData != null) {
                WizardCompletionScreen completionScreen = new WizardCompletionScreen(utilSendData.getText(), utilSendData.isAllowUserRestart(), utilSendData.isGenerateCompletionCode(), utilSendData.getPostCompletionCodeText(),
                        "Opnieuw beginnen",
                        "Einde van het experiment",
                        "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                        "Probeer opnieuw");
                wizardData.addScreen(completionScreen);
                completionScreen.setScreenTag((utilSendData.getTitle() != null) ? utilSendData.getTitle() : "completion");
                completionScreen.setNextWizardScreen(firstScreen);
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(completionScreen.getWizardScreenData());
                }
//                updateScreenToMenuRelations(screenToMenuMap, lastScreen, completionScreen, false);
                lastScreen = completionScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
            final WizardUtilAudioTest utilAudioTest = screenData.getAudioScreen();
            if (utilAudioTest != null) {
                WizardAudioTestScreen atticScreen = new WizardAudioTestScreen(utilAudioTest);
                wizardData.addScreen(atticScreen);
                atticScreen.setNextWizardScreen(firstScreen);
                if (lastScreen != null) {
                    lastScreen.getWizardScreenData().setNextWizardScreenData(atticScreen.getWizardScreenData());
                }
                updateScreenToMenuRelations(screenToMenuMap, lastScreen, atticScreen, true);
                lastScreen = atticScreen;
                if (firstScreen == null) {
                    firstScreen = lastScreen;
                }
            }
        }

//        if (wizardUtilData.getFeedbackScreenText() != null) {
//            final WizardEditUserScreen wizardFeedbackScreen = new WizardEditUserScreen();
//            wizardFeedbackScreen.setScreenTitle("Opmerkingen");
//            wizardFeedbackScreen.setScreenTag("opmerkingen");
//            wizardFeedbackScreen.setMenuLabel("Opmerkingen");
//            wizardFeedbackScreen.setScreenText(wizardUtilData.getFeedbackScreenText());
//            wizardFeedbackScreen.setSendData(true);
//            wizardFeedbackScreen.setNextButton("Volgende");
//            wizardFeedbackScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
//            wizardFeedbackScreen.setCustomFields(new String[]{
//                "feedBack::.*:."
//            });
//            wizardData.addScreen(wizardFeedbackScreen);
//            if (lastScreen != null) {
//                lastScreen.setNextWizardScreen(wizardFeedbackScreen);
//            }
//        }
//        agreementScreen.setNextWizardScreen(wizardTextScreen);
//        wizardTextScreen.setBackWizardScreen(agreementScreen);
//        list1234Screen.setBackWizardScreen(wizardEditUserScreen);
        //completionScreen.setBackWizardScreen(list1234Screen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        if (mainMenuScreen != null) {
            wizardAboutScreen.setBackWizardScreen(mainMenuScreen);
        } else {
            wizardAboutScreen.setBackWizardScreen(firstScreen);
        }
        wizardData.addScreen(wizardAboutScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment(getWizardData());
//        experiment.getDataChannels().add(new DataChannel(3, "Example DataChannel", false));
        return experiment;
    }
}
