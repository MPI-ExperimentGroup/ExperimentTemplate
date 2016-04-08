/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;

/**
 * @since Dec 22, 2015 11:07:54 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class JenaFieldKit {

    private final WizardController wizardController = new WizardController();

    public Experiment getJenaExperiment() {
        Experiment experiment = wizardController.getExperiment("vanuatufieldkit", "Vanuatu FieldKit");
        wizardController.addMetadata(experiment);

        final PresenterScreen autoMenuPresenter = wizardController.addAutoMenu(experiment, 12, false);
        final PresenterScreen welcomePresenter = wizardController.addWelcomeScreen(experiment, autoMenuPresenter, null, 1, false);
        final PresenterScreen welcomeMenuPresenter = wizardController.addWelcomeMenu(experiment, welcomePresenter, null, 2, "Is this a new recording?", "Have you already started a recording and do you want to go back to it?", false);
        final PresenterScreen instructionsPresenter = wizardController.addInstructionsScreen(experiment, welcomePresenter, welcomeMenuPresenter, 3, "With this app you can make recordings of your language. "
                + "People from your country and from all over the world will be able to hear your recordings now and in the future. "
                + "Show the pictures in this app to your grandparents and other older people. "
                + "You will ask them to talk about these pictures in their custom language. "
                + "You can translate what they say, too.", false);
        StimuliSubAction[] featureValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", "speak the name in the language (lanwis)", "done"),
            new StimuliSubAction("60", "It''s your turn! What did they say? Translate it into Bislama if you can.", "done"),
            new StimuliSubAction("80", "ask for personal experience with... in language (lanwis)", "done"),
            new StimuliSubAction("60", "It''s your turn! What experience did they have? Translate it into Bislama if you can.", "done"),
            new StimuliSubAction("80", "custom story relating to ... (lanwis)", "done"),
            new StimuliSubAction("60", "It''s your turn! What story did they tell? Translate it into Bislama if you can.", "done")
        };

        final PresenterScreen stimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, welcomePresenter, new String[]{"Pictures"}, featureValuesArray, true, 1000, true, 8, false);
//        final PresenterScreen vanuatuScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"vanuatu"}, featureValuesArray, true, 1000, true, 7, false);
//        final PresenterScreen bowpedStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, vanuatuScreen, new String[]{"bowped"}, featureValuesArray, true, 1000, true, 9, false);
//        final PresenterScreen bodiesStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, bowpedStimulusScreen, new String[]{"bodies"}, featureValuesArray, true, 1000, true, 10, false);
        final PresenterScreen metadataScreen = wizardController.createMetadataScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"name of speaker", "language", "where are you now", "where were you born", "when were you born"}, 6, false);
        final PresenterScreen selectUserPresenter = wizardController.addUserSelectMenu(experiment, welcomePresenter, metadataScreen, 5, false);
        final PresenterScreen editUserPresenter = wizardController.addEditUserScreen(experiment, welcomePresenter, metadataScreen, 4, "Could not contact the server, please check your internet connection and try again.", false);
        final PresenterScreen debugScreenPresenter = wizardController.addDebugScreen(experiment, autoMenuPresenter, 11, false);
        welcomeMenuPresenter.setNextPresenter(editUserPresenter);
//        instructionsPresenter.setNextPresenter(metadataScreen);
        return experiment;
    }
}
