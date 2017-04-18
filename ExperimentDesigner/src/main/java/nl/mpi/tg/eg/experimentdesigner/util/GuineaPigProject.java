/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics
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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAnimatedStimuliScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardGridStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Mar 23, 2017 2:50:16 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GuineaPigProject {

    private final WizardController wizardController = new WizardController();
    final String agreementScreenText = "Toestemmingsverklaring voor deelname aan het onderzoek:<br/>"
            + "<br/>"
            + "Ik stem geheel vrijwillig in met deelname aan dit onderzoek.<br/>"
            + "<br/>"
            + "Als u ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan.<br/>"
            + "<br/>"
            + "Als u besluit niet deel te nemen aan het experiment, kunt u de pagina sluiten of naar een andere website gaan.";
    final String informationScreenText = "Alvast ontzettend bedankt voor uw deelname aan dit onderzoek.<br/>";
    final String completionScreenText1 = "Dit is het einde van het experiment.<br/>"
            + "Hartelijk dank voor uw deelname! <br/>"
            + "<br/>";
    final String completionScreenText2 = "<br/>"
            + "Het bovenstaande nummer is het bewijs dat u het experiment heeft voltooid, en is vereist voor het in orde maken van uw vergoeding.";
    private final String[] stimuliString = {};

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("GuineaPigProject");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen = new WizardTextScreen("Informatie", informationScreenText,
                "volgende [ spatiebalk ]"
        );
        wizardTextScreen.setMenuLabel("Terug");
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Toestemming", agreementScreenText, "Akkoord");
        agreementScreen.setMenuLabel("Terug");
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Gegevens");
        wizardEditUserScreen.setMenuLabel("Terug");
        wizardEditUserScreen.setScreenTag("Gegevens");
        wizardEditUserScreen.setNextButton("Volgende");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:Proefpersoon ID:.'{'3,'}':Voer minimaal drie letters.",
            "age:Leeftijd:[0-9]+:Voer een getal.",
            "gender:Geslacht:|man|vrouw|anders:."
        });

        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen);
        wizardData.addScreen(wizardEditUserScreen);

        WizardAnimatedStimuliScreen overviewHighlightGarden = new WizardAnimatedStimuliScreen("Overview Highlight Garden", new String[]{"Overview picture of the house, highlight garden"}, false, 0, false, "overview-highlight-garden", "overview-highlight-garden.jpg", false);
        WizardAnimatedStimuliScreen trainingPhase = new WizardAnimatedStimuliScreen("Training phase", new String[]{"four training trials (practice the game in the garden)"}, false, 0, false, "game-in-the-garden", "game-in-the-garden.jpg", false);
        WizardAnimatedStimuliScreen overviewHighlight1stRoom = new WizardAnimatedStimuliScreen("Overview Highlight 1st Room", new String[]{"Overview picture of the house, highlight 1st room"}, false, 0, false, "overview-highlight-1st-room", "overview-highlight-1st-room.jpg", false);
        final WizardGridStimulusScreen gridStimulusScreen = new WizardGridStimulusScreen("VideoScreen", false, stimuliString,
                new String[]{"list_a",
                    "list_b",
                    "list_c"}, 1000, true, null, 0, 0, null);
        wizardData.addScreen(gridStimulusScreen);

        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, true, true, completionScreenText2,
                "Opnieuw beginnen",
                "Einde van het experiment",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");
        wizardData.addScreen(completionScreen);
        completionScreen.setScreenTag("completion");
        wizardTextScreen.setNextWizardScreen(wizardEditUserScreen);
        agreementScreen.setNextWizardScreen(wizardTextScreen);
        wizardEditUserScreen.setNextWizardScreen(gridStimulusScreen);
        gridStimulusScreen.setNextWizardScreen(completionScreen);

        wizardEditUserScreen.setBackWizardScreen(wizardTextScreen);
        wizardTextScreen.setBackWizardScreen(agreementScreen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        wizardAboutScreen.setBackWizardScreen(wizardEditUserScreen);
        wizardData.addScreen(wizardAboutScreen);

        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
