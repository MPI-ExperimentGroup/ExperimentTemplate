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
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardStimuliJsonMetadataScreen;

/**
 * @since Mar 7, 2017 11:44:39 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class NonWacq {
    private final WizardController wizardController = new WizardController();
    private final String[] stimuliString = {
        //        "UttAnnotApp-Logo.png",
        //        "UttAnnotApp-Logo512.png", 
        "d1e128.jpg",
        "d1e140.jpg",
        "d1e152.jpg",
        "videotag2.png",
        "videotag6.png",
        "d1e131.jpg",
        "d1e143.jpg",
        "d1e155.jpg",
        "videotag3.png",
        "videotag7.png",
        "d1e134.jpg",
        "d1e146.jpg",
        "videotag0.png",
        "videotag4.png",
        "videotag8.png",
        "d1e137.jpg",
        "d1e149.jpg",
        "videotag1.png",
        "videotag5.png",
        "videotag9.png"
    };

    private WizardStimuliJsonMetadataScreen getJsonMetadataScreenrScreen(final String screenName) {
        final WizardStimuliJsonMetadataScreen jsonMetadataScreenrScreen = new WizardStimuliJsonMetadataScreen(stimuliString);
        jsonMetadataScreenrScreen.setScreenTitle(screenName);
        jsonMetadataScreenrScreen.setMenuLabel("Terug");
        jsonMetadataScreenrScreen.setScreenTag(screenName);
        jsonMetadataScreenrScreen.setNextButton("Volgende");
        jsonMetadataScreenrScreen.getWizardScreenData().getMetadataFields();

        jsonMetadataScreenrScreen.addStimuliMetadataField("DateCreated", "DateCreated");
        jsonMetadataScreenrScreen.addStimuliMetadataField("DateSaved", "DateSaved");
        jsonMetadataScreenrScreen.addStimuliMetadataField("Coder", "Coder");
        jsonMetadataScreenrScreen.addStimuliMetadataField("ChildId", "ChildId");
        jsonMetadataScreenrScreen.addStimuliMetadataField("Informant", "Informant");
        jsonMetadataScreenrScreen.addStimuliMetadataField("StimulusId", "StimulusId");
        jsonMetadataScreenrScreen.addStimuliBooleanMetadataField("ChildSpeaks", "ChildSpeaks");
        jsonMetadataScreenrScreen.addStimuliMetadataField("WhoSpeaks", "WhoSpeaks");
        jsonMetadataScreenrScreen.addStimuliBooleanMetadataField("Understandable", "Understandable");
        jsonMetadataScreenrScreen.addStimuliBooleanMetadataField("IsChildAddressed", "IsChildAddressed");
        jsonMetadataScreenrScreen.addStimuliMetadataField("WhichLanguage", "WhichLanguage");
        jsonMetadataScreenrScreen.addStimuliMetadataField("LanguageLat", "LanguageLat");
        jsonMetadataScreenrScreen.addStimuliMetadataField("LanguageLon", "LanguageLon");
        jsonMetadataScreenrScreen.addStimuliMetadataField("TimesPlayed", "TimesPlayed");
        jsonMetadataScreenrScreen.addStimuliMetadataField("TimesContextPlayed", "TimesContextPlayed");
        return jsonMetadataScreenrScreen;
    }

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("NonWacq");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        final WizardStimuliJsonMetadataScreen jsonMetadataScreenrScreenSdCard = getJsonMetadataScreenrScreen("MetadataScreenrScreenSdCard");
        final WizardStimuliJsonMetadataScreen jsonMetadataScreenrScreenSample = getJsonMetadataScreenrScreen("jsonMetadataScreenrScreenSample");

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
        wizardData.addScreen(jsonMetadataScreenrScreenSample);
        wizardData.addScreen(jsonMetadataScreenrScreenSdCard);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen(true);
        wizardData.addScreen(wizardAboutScreen);
        jsonMetadataScreenrScreenSdCard.setBackWizardScreen(wizardAboutScreen);
        jsonMetadataScreenrScreenSample.setBackWizardScreen(wizardAboutScreen);
        wizardAboutScreen.setBackWizardScreen(jsonMetadataScreenrScreenSample);
        jsonMetadataScreenrScreenSdCard.setNextWizardScreen(jsonMetadataScreenrScreenSample);
        jsonMetadataScreenrScreenSample.setNextWizardScreen(jsonMetadataScreenrScreenSdCard);
        return wizardData;
    }

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment(getWizardData());
        experiment.getMetadata().add(new Metadata("workerId", "Login code", ".'{'3,'}'", "Please enter your login code.", false, null));
        return experiment;
    }
}
