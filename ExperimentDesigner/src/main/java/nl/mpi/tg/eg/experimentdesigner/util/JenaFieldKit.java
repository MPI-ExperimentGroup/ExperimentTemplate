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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

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
        final PresenterScreen welcomeMenuPresenter = addWelcomeMenu(experiment, welcomePresenter, null);
        final PresenterScreen instructionsPresenter = addInstructionsScreen(experiment, welcomePresenter, null);
        final PresenterScreen stimulusScreen = createStimulusScreen(experiment, welcomePresenter, welcomePresenter, new String[]{"Pictures"});
        final PresenterScreen vanuatuScreen = createStimulusScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"vanuatu"});
        final PresenterScreen bowpedStimulusScreen = createStimulusScreen(experiment, welcomePresenter, vanuatuScreen, new String[]{"bowped"});
        final PresenterScreen bodiesStimulusScreen = createStimulusScreen(experiment, welcomePresenter, bowpedStimulusScreen, new String[]{"bodies"});
        final PresenterScreen metadataScreen = wizardController.createMetadataScreen(experiment, autoMenuPresenter, bodiesStimulusScreen, new String[]{"name of speaker", "language", "where are you now", "where were you born", "when were you born"});
        final PresenterScreen selectUserPresenter = wizardController.addUserSelectMenu(experiment, welcomePresenter, metadataScreen);
        final PresenterScreen editUserPresenter = wizardController.addEditUserScreen(experiment, welcomePresenter, metadataScreen);
        final PresenterScreen debugScreenPresenter = wizardController.addDebugScreen(experiment, autoMenuPresenter);
        return experiment;
    }

    private PresenterScreen addInstructionsScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Instructions", "Instructions", backPresenter, "Instructions", nextPresenter, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "Instructions"));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "Instructions are to be provided here"));
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.targetButton, "Start");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, "Start");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    private PresenterScreen addWelcomeMenu(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Start", "Start", backPresenter, "Start", nextPresenter, PresenterType.metadata);
        final PresenterFeature userCheckFeature = new PresenterFeature(FeatureType.existingUserCheck, null);
        final PresenterFeature multipleUsersFeature = new PresenterFeature(FeatureType.multipleUsers, null);
        userCheckFeature.getPresenterFeatureList().add(multipleUsersFeature);
        final PresenterFeature singleUserFeature = new PresenterFeature(FeatureType.singleUser, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        autoNextPresenter.addFeatureAttributes(FeatureAttribute.target, "EditUser");
        singleUserFeature.getPresenterFeatureList().add(autoNextPresenter);
        userCheckFeature.getPresenterFeatureList().add(singleUserFeature);
        final PresenterFeature createUserFeature = new PresenterFeature(FeatureType.createUserButton, "New interview.");
        createUserFeature.addFeatureAttributes(FeatureAttribute.target, "EditUser");
        multipleUsersFeature.getPresenterFeatureList().add(createUserFeature);
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.targetButton, "Old interview");
        selectUserFeature.addFeatureAttributes(FeatureAttribute.target, "SelectUser");
        multipleUsersFeature.getPresenterFeatureList().add(selectUserFeature);
        presenterScreen.getPresenterFeatureList().add(userCheckFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    private PresenterScreen createStimulusScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, final String stimulusTagArray[]) {
        String screenName = "";
        final List<Stimulus> stimuliList = experiment.getStimuli();
        for (final String stimulusTag : stimulusTagArray) {
            stimuliList.add(new Stimulus(null, null, null, stimulusTag, stimulusTag, stimulusTag, 0, new HashSet<>(Arrays.asList(new String[]{stimulusTag}))));
            screenName += stimulusTag;
        }
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName + "Screen", null, PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final String maxStimuli = "15";
        presenterFeatureList.add(new PresenterFeature(FeatureType.text, "This screen will show " + maxStimuli + " stimuli in random order from the directories:"));
        for (final String stimulusTag : stimulusTagArray) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.text, "MPI_STIMULI/" + stimulusTag));
        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadSdCardStimulus, null);
        for (final String stimulusTag : stimulusTagArray) {
            loadStimuliFeature.addStimulusTag(stimulusTag);
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, maxStimuli);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "true");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        // todo: add more reverter tags as required
        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, "true");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        final PresenterFeature lanwisImage = wizardController.addImageFeature(hasMoreStimulusFeature, "80", "speak the name in the language (lanwis)", "done");
        final PresenterFeature bislamaImage = wizardController.addImageFeature(lanwisImage, "60", "It''s your turn! What did they say? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisExperience = wizardController.addImageFeature(bislamaImage, "80", "ask for personal experience with... in language (lanwis)", "done");
        final PresenterFeature bislamaExperience = wizardController.addImageFeature(lanwisExperience, "60", "It''s your turn! What experience did they have? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisStory = wizardController.addImageFeature(bislamaExperience, "80", "custom story relating to ... (lanwis)", "done");
        final PresenterFeature bislamaStory = wizardController.addImageFeature(lanwisStory, "60", "It''s your turn! What story did they tell? Translate it into Bislama if you can.", "done");
        final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
        autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        bislamaStory.getPresenterFeatureList().add(autoNextFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, nextPresenter.getSelfPresenterTag());
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
