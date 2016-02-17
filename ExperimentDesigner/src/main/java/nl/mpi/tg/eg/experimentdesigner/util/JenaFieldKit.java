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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;
import static nl.mpi.tg.eg.experimentdesigner.util.DefaultExperiments.getDefault;

/**
 * @since Dec 22, 2015 11:07:54 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class JenaFieldKit {

    public Experiment getJenaExperiment(MetadataRepository metadataRepository, PresenterFeatureRepository presenterFeatureRepository, PresenterScreenRepository presenterScreenRepository) {
        Experiment experiment = getDefault();
        experiment.setAppNameDisplay("Vanuatu FieldKit");
        experiment.setAppNameInternal("vanuatufieldkit");
        experiment.setDataSubmitUrl("http://ems13.mpi.nl/vanuatufieldkit-admin/");
        final Metadata metadata = new Metadata("workerId", "Speaker name *", ".'{'3,'}'", "Please enter at least three letters.", false, null);

        experiment.getMetadata().add(metadata);
        metadataRepository.save(experiment.getMetadata());
        final PresenterScreen autoMenuPresenter = addAutoMenu(presenterFeatureRepository);
        final PresenterScreen welcomePresenter = addWelcomeScreen(presenterFeatureRepository, autoMenuPresenter, null);
        final PresenterScreen welcomeMenuPresenter = addWelcomeMenu(presenterFeatureRepository, welcomePresenter, null);
        final PresenterScreen instructionsPresenter = addInstructionsScreen(presenterFeatureRepository, welcomePresenter, null);
        final PresenterScreen stimulusScreen = createStimulusScreen(welcomePresenter, welcomePresenter, new String[]{"Pictures"});
        final PresenterScreen vanuatuScreen = createStimulusScreen(welcomePresenter, stimulusScreen, new String[]{"vanuatu"});
        final PresenterScreen bowpedStimulusScreen = createStimulusScreen(welcomePresenter, vanuatuScreen, new String[]{"bowped"});
        final PresenterScreen bodiesStimulusScreen = createStimulusScreen(welcomePresenter,bowpedStimulusScreen, new String[]{"bodies"});
        final PresenterScreen metadataScreen = createMetadataScreen(autoMenuPresenter, bodiesStimulusScreen);
        final PresenterScreen selectUserPresenter = addUserSelectMenu(presenterFeatureRepository, welcomePresenter, metadataScreen);
        final PresenterScreen editUserPresenter = addEditUserScreen(presenterFeatureRepository, welcomePresenter, metadataScreen);
        experiment.getPresenterScreen().add(welcomePresenter);
        experiment.getPresenterScreen().add(welcomeMenuPresenter);
        experiment.getPresenterScreen().add(instructionsPresenter);
        experiment.getPresenterScreen().add(selectUserPresenter);
        experiment.getPresenterScreen().add(editUserPresenter);
        experiment.getPresenterScreen().add(autoMenuPresenter);
        experiment.getPresenterScreen().add(addDebugScreen(autoMenuPresenter));
        experiment.getPresenterScreen().add(metadataScreen);
        experiment.getPresenterScreen().add(stimulusScreen);
        experiment.getPresenterScreen().add(vanuatuScreen);
        experiment.getPresenterScreen().add(bowpedStimulusScreen);
        experiment.getPresenterScreen().add(bodiesStimulusScreen);
//        experiment.getPresenterScreen().add(addAnnotationTimelinePanel(presenterFeatureRepository, autoMenuPresenter));
//        experiment.getPresenterScreen().add(addVideosMenu(presenterFeatureRepository, autoMenuPresenter));
//        experiment.getPresenterScreen().add(addTargetScreen(presenterFeatureRepository, autoMenuPresenter));
//        experiment.getPresenterScreen().add(addVideoAspen(presenterFeatureRepository, autoMenuPresenter));
//        experiment.getPresenterScreen().add(addVideoWorksPage(presenterFeatureRepository, autoMenuPresenter));
//        experiment.getPresenterScreen().add(addVideoFailedPage(presenterFeatureRepository, autoMenuPresenter));
        presenterScreenRepository.save(experiment.getPresenterScreen());
        experiment.setStimuli(createStimuli());
        return experiment;
    }

    private PresenterScreen addAutoMenu(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Auto Menu", "Menu", null, "AutoMenu", null, PresenterType.menu);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addDebugScreen(PresenterScreen autoMenuPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Debug Screen", "Debug Screen", autoMenuPresenter, "DebugScreen", null, PresenterType.debug);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.versionData, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseLocalStorageButton, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.localStorageData, null));
        return presenterScreen;
    }

    private PresenterScreen addWelcomeScreen(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Welcome", "Welcome", backPresenter, "Welcome", nextPresenter, PresenterType.menu);
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.menuItem, "Instructions");
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, "Instructions");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.menuItem, "Go directly to program");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, "Start");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addInstructionsScreen(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Instructions", "Instructions", backPresenter, "Instructions", nextPresenter, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "Instructions"));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "Instructions are to be provided here"));
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.targetButton, "Start");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, "Start");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addWelcomeMenu(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
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
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addUserSelectMenu(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Select User", "Select User", backPresenter, "SelectUser", nextPresenter, PresenterType.metadata);
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.selectUserMenu, null);
        selectUserFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        presenterScreen.getPresenterFeatureList().add(selectUserFeature);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addEditUserScreen(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Edit User", "Edit User", backPresenter, "EditUser", nextPresenter, PresenterType.metadata);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMetadataFields, null));
        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, "Save Metadata");
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, "false");
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        onErrorFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "on Error Feature"));
        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.autoNextPresenter, null);
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        presenterScreen.getPresenterFeatureList().add(saveMetadataButton);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    public PresenterScreen createMetadataScreen(final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final String screenName = "Metadata";
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, "MetadataScreen", null, PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag("metadata");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, "false");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startTagFeature);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, "spacebar");
        final PresenterFeature endAudioRecorderTagFeature = new PresenterFeature(FeatureType.endAudioRecorderTag, null);
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTag, "");
        actionButtonFeature.getPresenterFeatureList().add(endAudioRecorderTagFeature);
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusMetadata");
        actionButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
        hasMoreStimulusFeature.getPresenterFeatureList().add(actionButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        autoNextPresenter.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stopAudioRecorder, null));
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }

    private PresenterScreen createStimulusScreen(final PresenterScreen backPresenter, final PresenterScreen nextPresenter, final String stimulusTagArray[]) {
        String screenName = "";
        for (final String stimulusTag : stimulusTagArray) {
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

        final PresenterFeature lanwisImage = addImageFeature(hasMoreStimulusFeature, "80", "speak the name in the language (lanwis)", "done");
        final PresenterFeature bislamaImage = addImageFeature(lanwisImage, "60", "It''s your turn! What did they say? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisExperience = addImageFeature(bislamaImage, "80", "ask for personal experience with... in language (lanwis)", "done");
        final PresenterFeature bislamaExperience = addImageFeature(lanwisExperience, "60", "It''s your turn! What experience did they have? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisStory = addImageFeature(bislamaExperience, "80", "custom story relating to ... (lanwis)", "done");
        final PresenterFeature bislamaStory = addImageFeature(lanwisStory, "60", "It''s your turn! What story did they tell? Translate it into Bislama if you can.", "done");
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
        return presenterScreen;
    }

    private PresenterFeature addImageFeature(PresenterFeature parentFeature, String percentOfPage, String label, String button) {
        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        parentFeature.getPresenterFeatureList().add(startTagFeature);
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, percentOfPage);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, percentOfPage);
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, percentOfPage);
        imageFeature.addFeatureAttributes(FeatureAttribute.timeToNext, "0");
        parentFeature.getPresenterFeatureList().add(imageFeature);
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, label));
        final PresenterFeature actionFeature = new PresenterFeature(FeatureType.actionButton, button);
        final PresenterFeature endAudioRecorderTagFeature = new PresenterFeature(FeatureType.endAudioRecorderTag, null);
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTag, label);
        actionFeature.getPresenterFeatureList().add(endAudioRecorderTagFeature);
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        parentFeature.getPresenterFeatureList().add(actionFeature);
        return actionFeature;
    }

    private ArrayList<Stimulus> createStimuli() {

        //    Metadata is collected in the spoken form (audio recording) with screen prompts for each item:
        String[] metadataStrings = new String[]{"name of speaker",
            "language",
            "where are you now",
            "where were you born",
            "when were you born"};
        String[] imageStrings = new String[]{"Bambu.jpg",
            "Foto shoreline.jpg",
            "Papaya.jpg",
            "Canoe.jpg",
            "Nakamal.jpg",
            "Pig.jpg",
            "Children's foto of themselves - Caroline Bay.jpg",
            "Ocean.jpg",
            "Thatch roof.jpg",
            "Cocoa.jpg",
            "Pantanas plant 2.jpg",
            "Flying fox.jpg",
            "Pantanas plant.jpg"
        };

        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{"metadata"}));
        for (String metadataString : metadataStrings) {
            final Stimulus stimulus = new Stimulus(null, null, null, metadataString.replace(" ", "_"), metadataString, metadataString.replace(" ", "_"), 0, tagSet);
            stimuliList.add(stimulus);
        }
        final HashSet<String> tagImageSet = new HashSet<>(Arrays.asList(new String[]{"image"}));
        for (String imageString : imageStrings) {
            final Stimulus stimulus = new Stimulus(null, null, null, "jena/" + imageString, imageString, imageString.replace(" ", "_"), 0, tagImageSet);
            stimuliList.add(stimulus);
        }
        stimuliList.add(new Stimulus(null, null, null, "stimulus", "stimulus", "stimulus", 0, new HashSet<>(Arrays.asList(new String[]{"stimulus"}))));
        stimuliList.add(new Stimulus(null, null, null, "Pictures", "Pictures", "Pictures", 0, new HashSet<>(Arrays.asList(new String[]{"Pictures"}))));
        stimuliList.add(new Stimulus(null, null, null, "bowped", "bowped", "bowped", 0, new HashSet<>(Arrays.asList(new String[]{"bowped"}))));
        stimuliList.add(new Stimulus(null, null, null, "bodies", "bodies", "bodies", 0, new HashSet<>(Arrays.asList(new String[]{"bodies"}))));
        stimuliList.add(new Stimulus(null, null, null, "vanuatu", "vanuatu", "vanuatu", 0, new HashSet<>(Arrays.asList(new String[]{"vanuatu"}))));
        return stimuliList;
    }
}
