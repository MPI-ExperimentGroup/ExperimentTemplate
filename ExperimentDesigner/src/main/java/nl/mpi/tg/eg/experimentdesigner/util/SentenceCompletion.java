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

import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Nov 16, 2017 3:25:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class SentenceCompletion {

    private final WizardController wizardController = new WizardController();

    abstract String getExperimentTitle();

    abstract String getAllowedCharCodes();

    abstract String getFreeTextValidationMessage();

    abstract String getFeedbackScreenText();

    abstract String getInstructionsText();

    abstract String getAgreementText();

    abstract boolean isShowProgress();

    abstract boolean isAllowUserRestart();

    abstract String getDebriefingText1();

    abstract String getDebriefingText2();

    abstract String[] getStimuliArray();

    abstract String[] getRandomStimuliTags();

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName(getExperimentTitle());
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen = new WizardTextScreen("Informatie", getInstructionsText(),
                "volgende [ spatiebalk ]"
        );
        wizardTextScreen.setMenuLabel("Terug");
        //Information screen 
        //Agreement
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Toestemming", getAgreementText(), "Akkoord");
        agreementScreen.setMenuLabel("Terug");
//        wizardData.setAgreementText("agreementText");
//        wizardData.setDisagreementScreenText("disagreementScreenText");
        //metadata
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Gegevens");
        wizardEditUserScreen.setMenuLabel("Terug");
        wizardEditUserScreen.setScreenTag("Gegevens");
        wizardEditUserScreen.setNextButton("Volgende");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
//        wizardData.setAgeField(true);
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:Proefpersoon ID:.'{'3,'}':Voer minimaal drie letters.",
            "age:Leeftijd:[0-9]+:Voer een getal.",
            //            "firstName:Voornaam:.'{'3,'}':Voer minimaal drie letters.",
            //            "lastName:Achternaam:.'{'3,'}':Voer minimaal drie letters.",
            //            "education:Opleidingsniveau:primair onderwijs (basisschool)|voortgezet onderwijs|middelbaar beroepsonderwijs (MBO)|hoger onderwijs (HBO, universiteit)|anders:.",
            "education:Opleidingsniveau:basisonderwijs|voortgezet onderwijs|MBO|HBO|universiteit|anders:.",
            "educationOther:Opleidingsniveau (anders, namelijk):.*:.",
            //            "education:Opleidingsniveau:.'{'3,'}':Voer minimaal drie letters.",
            "gender:Geslacht:|man|vrouw|anders:."
        });

        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen);
        wizardData.addScreen(wizardEditUserScreen);

        final WizardRandomStimulusScreen list1234Screen = new WizardRandomStimulusScreen("Zinnen afmaken", false, getStimuliArray(),
                getRandomStimuliTags(), 1000, true, null, 0, 0, null, null, null, null, "Volgende [tab + enter]");
        list1234Screen.setStimulusFreeText(true, ".{2,}",
                getFreeTextValidationMessage());
        list1234Screen.setAllowedCharCodes(getAllowedCharCodes());
        list1234Screen.setInputKeyErrorMessage("Sorry, dit teken is niet toegestaan.");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelLeft("");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelRight("");
        list1234Screen.setRandomStimuliTagsField("item");
        list1234Screen.setAllowHotkeyButtons(false);
        if (isShowProgress()) {
            list1234Screen.setShowProgress(true);
        }
        wizardData.addScreen(list1234Screen);

        // @todo: remove the restart button
        // 
        WizardCompletionScreen completionScreen = new WizardCompletionScreen(getDebriefingText1(), isAllowUserRestart(), true,
                //                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                getDebriefingText2(),
                "Opnieuw beginnen",
                "Einde van het experiment",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");
        wizardData.addScreen(completionScreen);
        completionScreen.setScreenTag("completion");

        if (getFeedbackScreenText() != null) {
            final WizardEditUserScreen wizardFeedbackScreen = new WizardEditUserScreen();
            wizardFeedbackScreen.setScreenTitle("Opmerkingen");
            wizardFeedbackScreen.setScreenTag("opmerkingen");
            wizardFeedbackScreen.setMenuLabel("Opmerkingen");
            wizardFeedbackScreen.setScreenText(getFeedbackScreenText());
            wizardFeedbackScreen.setSendData(true);
            wizardFeedbackScreen.setNextButton("Volgende");
            wizardFeedbackScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
            wizardFeedbackScreen.setCustomFields(new String[]{
                "feedBack::.*:."
            });
            wizardData.addScreen(wizardFeedbackScreen);
            list1234Screen.setNextWizardScreen(wizardFeedbackScreen);
            wizardFeedbackScreen.setNextWizardScreen(completionScreen);
        } else {
            list1234Screen.setNextWizardScreen(completionScreen);
        }
        wizardTextScreen.setNextWizardScreen(wizardEditUserScreen);
        agreementScreen.setNextWizardScreen(wizardTextScreen);
        wizardEditUserScreen.setNextWizardScreen(list1234Screen);

        wizardEditUserScreen.setBackWizardScreen(wizardTextScreen);
        wizardTextScreen.setBackWizardScreen(agreementScreen);
//        list1234Screen.setBackWizardScreen(wizardEditUserScreen);
        //completionScreen.setBackWizardScreen(list1234Screen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        wizardAboutScreen.setBackWizardScreen(wizardEditUserScreen);
        wizardData.addScreen(wizardAboutScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
