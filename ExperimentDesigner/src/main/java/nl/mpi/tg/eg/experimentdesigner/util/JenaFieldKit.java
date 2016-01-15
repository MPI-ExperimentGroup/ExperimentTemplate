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

    public Experiment getJenaExperiment(MetadataRepository metadataRepository, PresenterFeatureRepository presenterFeatureRepository, PresenterScreenRepository presenterScreenRepository) {
        Experiment experiment = getDefault();
        experiment.setAppNameDisplay("Jena Recorder");
        experiment.setAppNameInternal("JenaRecorder");
        experiment.setDataSubmitUrl("http://ems13.mpi.nl/jenarecorder-admin/");
        final Metadata metadata = new Metadata("workerId", "Reporter name *", ".'{'3,'}'", "Please enter at least three letters.", true, "This test can only be done once per worker.");
        experiment.getMetadata().add(metadata);
        metadataRepository.save(experiment.getMetadata());
        final PresenterScreen autoMenuPresenter = addAutoMenu(presenterFeatureRepository);
        final PresenterScreen selectUserPresenter = addUserSelectMenu(presenterFeatureRepository, autoMenuPresenter, autoMenuPresenter);
        final PresenterScreen editUserPresenter = addEditUserScreen(presenterFeatureRepository, autoMenuPresenter, autoMenuPresenter);
        experiment.getPresenterScreen().add(selectUserPresenter);
        experiment.getPresenterScreen().add(editUserPresenter);
        experiment.getPresenterScreen().add(autoMenuPresenter);
        experiment.getPresenterScreen().add(addDebugScreen(autoMenuPresenter));
        experiment.getPresenterScreen().add(createMetadataScreen(autoMenuPresenter));
        experiment.getPresenterScreen().add(createStimulusScreen(autoMenuPresenter, new String[]{"bowped"}));
        experiment.getPresenterScreen().add(createStimulusScreen(autoMenuPresenter, new String[]{"bowped", "bodies"}));
        experiment.getPresenterScreen().add(createStimulusScreen(autoMenuPresenter, new String[]{"vanuatu"}));
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

    private PresenterScreen addUserSelectMenu(PresenterFeatureRepository presenterFeatureRepository, final PresenterScreen backPresenter, final PresenterScreen nextPresenter) {
        final PresenterScreen presenterScreen = new PresenterScreen("Select User", "Select User", backPresenter, "SelectUser", nextPresenter, PresenterType.metadata);
        final PresenterFeature createUserFeature = new PresenterFeature(FeatureType.createUserButton, "New User");
        createUserFeature.addFeatureAttributes(FeatureAttribute.target, "EditUser");
        presenterScreen.getPresenterFeatureList().add(createUserFeature);
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.selectUserMenu, null);
        selectUserFeature.addFeatureAttributes(FeatureAttribute.target, "EditUser");
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
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, "AutoMenu");
        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        presenterScreen.getPresenterFeatureList().add(saveMetadataButton);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    public PresenterScreen createMetadataScreen(PresenterScreen autoMenuPresenter) {
        final String screenName = "Metadata";
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, autoMenuPresenter, "MetadataScreen", null, PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag("metadata");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulusButton, "spacebar");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusMetadata");
        hasMoreStimulusFeature.getPresenterFeatureList().add(nextStimulusFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        autoNextPresenter.addFeatureAttributes(FeatureAttribute.target, "bowpedScreen");
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }

    private PresenterScreen createStimulusScreen(PresenterScreen autoMenuPresenter, final String stimulusTagArray[]) {
        //
//The stimuli consist of images on the SD card, each stimuli has various stages (each step below creates a time marker to the audio recording in the CSV):
//see image, [large image]
//speak the name in the language (lanwis)
//kids speak the name in Bislama [smaller image]
//ask for personal experience with... in language (lanwis)
//kids repeat personal experience with... in Bislama [smaller image]
//custom story relating to ... (lanwis)
//kids repeat ... in Bislama [smaller image]
        String screenName = "";
        for (final String stimulusTag : stimulusTagArray) {
            screenName += stimulusTag;
        }
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, autoMenuPresenter, screenName + "Screen", null, PresenterType.stimulus);
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

        final PresenterFeature lanwisImage = addImageFeature(hasMoreStimulusFeature, "50", "90", "speak the name in the language (lanwis)", "done");
        final PresenterFeature bislamaImage = addImageFeature(lanwisImage, "40", "70", "It''s your turn! What did they say? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisExperience = addImageFeature(bislamaImage, "50", "90", "ask for personal experience with... in language (lanwis)", "done");
        final PresenterFeature bislamaExperience = addImageFeature(lanwisExperience, "40", "70", "It''s your turn! What experience did they have? Translate it into Bislama if you can.", "done");
        final PresenterFeature lanwisStory = addImageFeature(bislamaExperience, "50", "90", "custom story relating to ... (lanwis)", "done");
        final PresenterFeature bislamaStory = addImageFeature(lanwisStory, "40", "70", "It''s your turn! What story did they tell? Translate it into Bislama if you can.", "done");
        final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
        autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        bislamaStory.getPresenterFeatureList().add(autoNextFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, "Menu");
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, "AutoMenu");
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }

    private PresenterFeature addImageFeature(PresenterFeature parentFeature, String maxHeight, String maxWidth, String label, String button) {
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, maxHeight);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, maxWidth);
        imageFeature.addFeatureAttributes(FeatureAttribute.timeToNext, "0");
        parentFeature.getPresenterFeatureList().add(imageFeature);
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, label));
        final PresenterFeature actionFeature = new PresenterFeature(FeatureType.actionButton, button);
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        parentFeature.getPresenterFeatureList().add(actionFeature);
        return actionFeature;
    }

    private ArrayList<Stimulus> createStimuli() {
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
        stimuliList.add(new Stimulus(null, null, null, "bowped", "bowped", "bowped", 0, new HashSet<>(Arrays.asList(new String[]{"bowped"}))));
        stimuliList.add(new Stimulus(null, null, null, "bodies", "bodies", "bodies", 0, new HashSet<>(Arrays.asList(new String[]{"bodies"}))));
        stimuliList.add(new Stimulus(null, null, null, "vanuatu", "vanuatu", "vanuatu", 0, new HashSet<>(Arrays.asList(new String[]{"vanuatu"}))));
        return stimuliList;
    }
}
