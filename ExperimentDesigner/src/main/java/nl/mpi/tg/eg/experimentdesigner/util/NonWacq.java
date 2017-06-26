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
 * @since Mar 7, 2017 11:44:39 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class NonWacq {
    private final WizardController wizardController = new WizardController();
    private final String[] stimuliString = {};

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("NonWacq");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
//        final WizardStimuliJsonMetadataScreen wizardEditUserScreen = new WizardStimuliJsonMetadataScreen();
//        wizardEditUserScreen.setScreenTitle("Gegevens");
//        wizardEditUserScreen.setMenuLabel("Terug");
//        wizardEditUserScreen.setScreenTag("Gegevens");
//        wizardEditUserScreen.setNextButton("Volgende");
//        wizardEditUserScreen.setSendData(true);
//        wizardEditUserScreen.setOn_Error_Text("Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.");
//        wizardEditUserScreen.setCustomFields(new String[]{
//            "Coder:Coder:.'{'3,'}':Voer minimaal drie letters.",
//            "ChildId:ChildId:.'{'3,'}':Voer minimaal drie letters.",
//            "Informant:Informant:.'{'3,'}':Voer minimaal drie letters.",
//            "WhoSpeaks:WhoSpeaks:.'{'3,'}':Voer minimaal drie letters.",
//            "ChildSpeaks:ChildSpeaks:|true|false:.",
//            "Understandable:Understandable:|true|false:.",
//            "IsChildAddressed:IsChildAddressed:|true|false:.",
//            "WhichLanguage:WhichLanguage:.'{'3,'}':Voer minimaal drie letters.",
//            "LanguageLat:LanguageLat:[0-9]+:Voer een getal.",
//            "LanguageLon:LanguageLon:[0-9]+:Voer een getal."
//        });
//        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
//        wizardAboutScreen.setBackWizardScreen(wizardEditUserScreen);
//        wizardEditUserScreen.setNextWizardScreen(wizardAboutScreen);
//        wizardData.addScreen(wizardAboutScreen);
//        wizardData.addScreen(wizardEditUserScreen);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
