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
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;

/**
 * @since Dec 22, 2015 11:07:54 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class JenaFieldKit {

    private final WizardController wizardController = new WizardController();

    public Experiment getJenaExperiment(MetadataRepository metadataRepository, PresenterFeatureRepository presenterFeatureRepository, PresenterScreenRepository presenterScreenRepository) {
        Experiment experiment = wizardController.getExperiment(metadataRepository, presenterFeatureRepository, presenterScreenRepository, "vanuatufieldkit", "Vanuatu FieldKit");
        wizardController.addMetadata(experiment);
        final PresenterScreen autoMenuPresenter = wizardController.addAutoMenu(experiment);
        final PresenterScreen welcomePresenter = wizardController.addWelcomeScreen(experiment, autoMenuPresenter, null);
        final PresenterScreen welcomeMenuPresenter = wizardController.addWelcomeMenu(experiment, welcomePresenter, null);
        final PresenterScreen instructionsPresenter = wizardController.addInstructionsScreen(experiment, welcomePresenter, null);
        final PresenterScreen stimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, welcomePresenter, new String[]{"Pictures"});
        final PresenterScreen vanuatuScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"vanuatu"});
        final PresenterScreen bowpedStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, vanuatuScreen, new String[]{"bowped"});
        final PresenterScreen bodiesStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, bowpedStimulusScreen, new String[]{"bodies"});
        final PresenterScreen metadataScreen = wizardController.createMetadataScreen(experiment, autoMenuPresenter, bodiesStimulusScreen, new String[]{"name of speaker", "language", "where are you now", "where were you born", "when were you born"});
        final PresenterScreen selectUserPresenter = wizardController.addUserSelectMenu(experiment, welcomePresenter, metadataScreen);
        final PresenterScreen editUserPresenter = wizardController.addEditUserScreen(experiment, welcomePresenter, metadataScreen);
        final PresenterScreen debugScreenPresenter = wizardController.addDebugScreen(experiment, autoMenuPresenter);
        return experiment;
    }
}
