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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMultiParticipantScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Mar 23, 2017 3:45:08 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ShortMultiparticipant01 {

    private final WizardController wizardController = new WizardController();

    protected String getExperimentName() {
        return "shortmultiparticipant01";
//        return "SilentGestureRepair01";
    }

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName(getExperimentName());
        wizardData.setShowMenuBar(false);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen instructionsScreen = new WizardTextScreen("Instruction", "test syncing and participant use?",
                "volgende [ spatiebalk ]"
        );
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Accord", "agreementScreenText", "Je suis d'accord");
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen("screenTitle", "screenTag", "dispalyText", "saveButtonLabel", "postText", instructionsScreen, "alternateButtonLabel", true, false, "on_Error_Text");
        final WizardMultiParticipantScreen multiParticipantScreen = new WizardMultiParticipantScreen("Set1",
                "A,B,C,D", 1,
                "A|B|C|D",
                "A,B,C,D",
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
                "",
                "",
                "This phase is not used in this screen",
                "",
                "",
                "preStimuliText", "postStimuliText", 23, 1, 0, 0, 0,
                0, 0, null);
        multiParticipantScreen.setStimuliSet(new String[]{"5.png:shape1:Set1:quadrant1:moveRotated30"});
        WizardCompletionScreen completionScreen = new WizardCompletionScreen("completionScreenText1", false, true,
                null, //Si quelqu'un d'autre veut participer à l'expérience sur cet ordinateur, veuillez cliquer sur le bouton ci-dessous.",
                "Redémarrer",
                "Fini",
                "Impossible de contacter le serveur, vérifiez votre connexion Internet s'il vous plaît.", "Réessayer");
        instructionsScreen.setNextWizardScreen(agreementScreen);
        agreementScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardEditUserScreen.setNextWizardScreen(multiParticipantScreen);
        multiParticipantScreen.setNextWizardScreen(completionScreen);

        agreementScreen.setBackWizardScreen(instructionsScreen);
        wizardEditUserScreen.setBackWizardScreen(agreementScreen);
        multiParticipantScreen.setBackWizardScreen(wizardEditUserScreen);
        completionScreen.setBackWizardScreen(multiParticipantScreen);

        wizardData.addScreen(instructionsScreen);
        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardEditUserScreen);
        wizardData.addScreen(multiParticipantScreen);
        wizardData.addScreen(completionScreen);
        return wizardData;
    }

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment(getWizardData());
        experiment.setScss("\n        $angleArray: 10 15 30 45 60 75 90 105 120 135 150 165 180 195 200 210 225 240 255 270 285 300 315 330 345 350 360;\n"
                + "        @each $currentAngle in $angleArray {\n"
                + "            .gwt-Image.moveRotated#{$currentAngle}Animation,\n"
                + "            .gwt-Button.stimulusButton.moveRotated#{$currentAngle}Animation {\n"
                + "                animation: moveRotated#{$currentAngle}KeyFrames 1.7s infinite;\n"
                + "                /*-webkit-animation: moveRotated10KeyFrames 2s infinite;*/\n"
                + "                margin: 100px;\n"
                + "                background: none;\n"
                + "                position: relative;\n"
                + "                animation-timing-function: linear;\n"
                + "                /*-webkit-animation-timing-function: ease;*/\n"
                + "                width: 100px;\n"
                + "            }\n"
                + "        }\n"
                + "        @each $currentAngle in $angleArray {\n"
                + "            @keyframes moveRotated#{$currentAngle}KeyFrames {\n"
                + "                from {\n"
                + "                    transform: rotate(#{$currentAngle}deg)\n"
                + "                    translate(0px)\n"
                + "                    rotate(-#{$currentAngle}deg);\n"
                + "                }\n"
                + "                to {\n"
                + "                    transform: rotate(#{$currentAngle}deg)\n"
                + "                    translate(100px)\n"
                + "                    rotate(-#{$currentAngle}deg);\n"
                + "                }\n"
                + "            }\n"
                + "        }\n"
                + "    ");
        return experiment;
    }
}
