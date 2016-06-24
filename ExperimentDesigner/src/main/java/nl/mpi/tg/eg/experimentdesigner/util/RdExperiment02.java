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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Jun 6, 2016 11:41:41 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class RdExperiment02 {

    private final WizardController wizardController = new WizardController();
    final String agreementScreenText = "Toestemmingsverklaring voor deelname aan het onderzoek:<br/>"
            + "<br/>"
            + "Als je ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan.<br/>"
            + "Als je besluit niet deel te nemen aan het experiment, kun je de pagina sluiten of naar een andere website gaan.<br/>"
            + "<br/>"
            + "";
    final String informationScreenText = "";
    final String completionScreenText1 = "Dit is het einde van het experiment.<br/>"
            + "<br/>"
            + "Bedankt voor je deelname!"
            + "<br/>";
    private final String[] stimuliString = {
        };

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("three lists");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(22);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen = new WizardTextScreen("Informatie", informationScreenText,
                "volgende [ spatiebalk ]"
        );
        wizardTextScreen.setMenuLabel("Terug");
        //Information screen 
        //Agreement
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Toestemming", agreementScreenText, "Akkoord");
        agreementScreen.setMenuLabel("Terug");
//        wizardData.setAgreementText("agreementText");
//        wizardData.setDisagreementScreenText("disagreementScreenText");
        //metadata
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Gegevens");
        wizardEditUserScreen.setMenuLabel("Terug");
        wizardEditUserScreen.setScreenTag("Gegevens");
        wizardEditUserScreen.setSaveButtonLabel("Volgende");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setMetadataScreen(true);
//        wizardData.setAgeField(true);
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:Proefpersoon id:.*:.",
            "firstName:Voornaam:.'{'3,'}':Voer minimaal drie letters.",
            "lastName:Achternaam:.'{'3,'}':Voer minimaal drie letters.",
            "age:Leeftijd:[0-9]+:Voer een getal.",
            "gender:Geslacht:|man|vrouw|anders:."
        });

        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen);
        wizardData.addScreen(wizardEditUserScreen);

        final WizardRandomStimulusScreen list1234Screen = new WizardRandomStimulusScreen("Stimulus", false, stimuliString,
                new String[]{"list_a", "list_b", "list_c"}, 1000, true, null, 0, 0, null, null, null, null, "volgende [ spatiebalk ]");
        list1234Screen.setStimulusResponseOptions("1,2,3,4,5");
        list1234Screen.setStimulusResponseLabelLeft("");
        list1234Screen.setStimulusResponseLabelRight("");
        wizardData.addScreen(list1234Screen);

        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, true,
                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                "Opnieuw beginnen",
                "Finished",
                "Could not contact the server, please check your internet connection and try again.", "Retry");
        wizardData.addScreen(completionScreen);

        wizardTextScreen.setNextWizardScreen(wizardEditUserScreen);
        agreementScreen.setNextWizardScreen(wizardTextScreen);
        wizardEditUserScreen.setNextWizardScreen(list1234Screen);
        list1234Screen.setNextWizardScreen(completionScreen);

        wizardEditUserScreen.setBackWizardScreen(wizardTextScreen);
        wizardTextScreen.setBackWizardScreen(agreementScreen);
        list1234Screen.setBackWizardScreen(wizardEditUserScreen);
        //completionScreen.setBackWizardScreen(list1234Screen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
