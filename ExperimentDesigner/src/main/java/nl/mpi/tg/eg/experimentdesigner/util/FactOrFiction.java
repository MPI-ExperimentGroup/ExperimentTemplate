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
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;

/**
 * @since Mar 16, 2016 2:35:56 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FactOrFiction {

    private final WizardController wizardController = new WizardController();
    String[] images = new String[]{
        "Emotioneel1_1stperson.png",
        "Emotioneel2_3rdperson.png",
        "Koffiemolen2_3rdperson.png",
        "Matroesjka1_3rdperson.png",
        "Meesterwerk1_1stperson.png",
        "Meesterwerk2_3rdperson.png",
        "Emotioneel1_3rdperson.png",
        "Koffiemoeln_3rdperson.png",
        "Koffiemolen_1stperson_V2.png",
        "Matroesjka2_1stperson.png",
        "Meesterwerk1_3rdperson.png",
        "Emotioneel2_1stperson.png",
        "Koffiemolen1_1stperson.png",
        "Matroesjka1_1stperson.png",
        "Matroesjka2_3rdperson.png",
        "Meesterwerk2_1stperson.png"
    };
    String[] servey1Stimuli = new String[]{
    };

    String multipleChoiceText = "1= niet zo graag, 7 = heel graag (same as survey 1 and 3)";
    String[] readingBehavior = new String[]{
        "RB1:Lees je graag fictie? <br/>(1=niet zo graag, 7=heel graag)",
        "RB2:Consumeer je ook andere soorten fictie (b.v. films of series, comic boeken etc.) <br/>(1=niet zo graag, 7=heel graag)",
        "RB3:Hoe vaak consumeer je fictie? <br/>(1=nooit, 7=dagelijks)",
        "RB4:Lees je graag waargebeurde verhalen? <br/>(1=niet zo graag, 7=heel graag)",
        "RB5:Consumeer je vaak andere non-fictie media zoals b.v. journalistische artikelen, wetenschapsnieuws,  (auto-) biografien, etc. <br/>(1=niet zo graag, 7=heel graag)",
        "RB6:Hoe vaak consumeer je non-fictie? <br/>(1=nooit, 7=dagelijks)"
    };
    final String[] storyTexts = new String[]{
    };

    final String completionScreenText = "";

    final String agreementScreenText = "";

    final String[] stimuliGroupAorB = new String[]{
    };

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment("leeservaring", "leeservaring");
        final WizardData wizardData = new WizardData();
        wizardData.setObfuscateScreenNames(true);
//        wizardData.setAgeField(true);
//        wizardData.setGenderField(true);
//        wizardData.setCustomTextField("level of proficiency in Dutch");
        final PresenterScreen agreementScreen = wizardController.addAgreementScreen(experiment, null, "EditUser", 1, agreementScreenText, wizardData.isObfuscateScreenNames());
        final String[] medataFields = new String[]{
            "leeftijd:Leeftijd:[0-9]+:voer een getal.",
            "geslacht:Geslacht:|man|vrouw|anders:.",
            "opleidingsniveau:Opleidingsniveau:primair onderwijs (basisschool)|voortgezet onderwijs|middelbaar beroepsonderwijs (MBO)|hoger onderwijs (HBO, universiteit)|anders:.",
            "nederlandsMoedertaal:Is Nederlands uw moedertaal:true|false:.",
            "hoeveelJaarNederlands:Als Nederlands niet uw moedertaal is, hoeveel jaar leert u al Nederlands?:[0-9]*:voer een getal."
        };
        final PresenterScreen editUserScreen = wizardController.addEditUserScreen(experiment, null, null, 2, wizardData, medataFields, wizardData.isObfuscateScreenNames());
        final PresenterScreen groupAorBScreen = wizardController.addRandomTextScreen(experiment, null, 3, "Introduciton", false, stimuliGroupAorB,
                new String[]{"IntroductionAFact", "IntroductionBFiction"}, 1, null, 0, 0, null, null, null, null, wizardData.isObfuscateScreenNames());
        final PresenterScreen storyScreen = wizardController.addRandomTextScreen(experiment, null, 4, "StoryPresentation", false, storyTexts, new String[]{"Emotioneel_hij", "Emotioneel_ik", "Koffiemolen_hij", "Koffiemolen_ik", "Matroesjka_hij", "Matroesjka_ik", "Meesterwerk_hij", "Meesterwerk_ik"}, 1, null, 0, 0, null, null, null, null, wizardData.isObfuscateScreenNames());
        final PresenterScreen survey1Screen = wizardController.addRandomTextScreen(experiment, null, 5, "Survey1", servey1Stimuli, 1000, "1,2,3,4,5,6,7", "helemaal niet van toepassing", "helemaal van toepassing", wizardData.isObfuscateScreenNames());
        final PresenterScreen survey1InstructionsScreen = wizardController.addTextScreen(experiment, null, "Survey1Instructions", null, 16, "<b>U krijgt nu enkele stellingen te zien over uw ervaringen tijdens het lezen. Geef aan in hoeverre de stellingen van toepassing zijn op uw ervaring tijdens het lezen.</b>", wizardData.isObfuscateScreenNames());
        final PresenterScreen survey2InstructionsScreen = wizardController.addTextScreen(experiment, null, "Survey2Instructions", null, 16, "<b>U krijgt nu enkele woorden te zien. Uw taak is om aan te geven in hoeverre de woorden van toepassing zijn op uw leeservaring.</b>", wizardData.isObfuscateScreenNames());
        final PresenterScreen survey2Screen = wizardController.addRandomTextScreen(experiment, null, 6, "Survey2", new String[]{"Interessant:interessant", "goedgeschreven:goed geschreven", "vanhogeliterairekwaliteit:van hoge literaire kwaliteit", "makkelijktebegrijpen:makkelijk te begrijpen", "toegankelijk:toegankelijk", "spannend:spannend", "mooi:mooi", "boeiend:boeiend", "emotioneel:emotioneel", "saai:saai"}, 1000, "1,2,3,4,5,6,7", "helemaal niet van toepassing", "helemaal van toepassing", wizardData.isObfuscateScreenNames());
        final PresenterScreen pictureInstructionsScreen = wizardController.addTextScreen(experiment, null, "PictureInstructions", null, 16, "U zult zo enkele afbeeldingen zien die situaties tonen. Uw taak is te kiezen of de afgebeelde acties overeenkomen met acties in het verhaal dat u net las, of niet. Als het beeld een actie toont die in het verhaal voorkwam, toetst u “Z”, als de actie niet in het verhaal voorkwam toetst u “.”. Probeer zo snel mogelijk te reageren.", wizardData.isObfuscateScreenNames());
        final PresenterScreen pictureTaskScreen = wizardController.addRandomTextScreen(experiment, null, 17, "PictureTask", images, 1000, "yes [ z ],no [ . ]", "", "", wizardData.isObfuscateScreenNames());
        final PresenterScreen readingBehaviorInstructionsScreen = wizardController.addTextScreen(experiment, null, "ReadingBehaviorInstructions", null, 18, "Het experiment is bijna klaar. We hebben nog 6 korte vragen aan u.", wizardData.isObfuscateScreenNames());
        final PresenterScreen readingBehaviorScreen = wizardController.addRandomTextScreen(experiment, null, 19, "ReadingBehavior", readingBehavior, 1000, "1,2,3,4,5,6,7", "", "", wizardData.isObfuscateScreenNames());

        final PresenterScreen completionScreen = wizardController.addCompletionScreen(experiment, null, null, 20, completionScreenText, true, "Clear data and restart experiment", wizardData.isObfuscateScreenNames());
        agreementScreen.setNextPresenter(editUserScreen);
        editUserScreen.setNextPresenter(groupAorBScreen);
        groupAorBScreen.setNextPresenter(storyScreen);
        storyScreen.setNextPresenter(survey1InstructionsScreen);
        survey1InstructionsScreen.setNextPresenter(survey1Screen);
        survey1Screen.setNextPresenter(survey2InstructionsScreen);
        survey2InstructionsScreen.setNextPresenter(survey2Screen);
        survey2Screen.setNextPresenter(pictureInstructionsScreen);
        pictureInstructionsScreen.setNextPresenter(pictureTaskScreen);
        pictureTaskScreen.setNextPresenter(readingBehaviorInstructionsScreen);
        readingBehaviorInstructionsScreen.setNextPresenter(readingBehaviorScreen);
        readingBehaviorScreen.setNextPresenter(completionScreen);
        return experiment;
    }
}
