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

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.dao.ExperimentRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PublishEventRepository;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.condition0Tag;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.condition1Tag;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.condition2Tag;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.maxHeight;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.maxWidth;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.mp4;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.ogg;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.percentOfPage;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.poster;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.webm;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.PublishEvents;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since Sep 8, 2015 3:19:56 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DefaultExperiments {

    private static final Logger LOG = Logger.getLogger(DefaultExperiments.class.getName());

    public void insertDefaultExperiment(
            PresenterScreenRepository presenterScreenRepository,
            PresenterFeatureRepository presenterFeatureRepository,
            MetadataRepository metadataRepository,
            ExperimentRepository experimentRepository,
            PublishEventRepository eventRepository) {
        experimentRepository.save(getSentveri_exp3Experiment());
        experimentRepository.save(getDobesExperiment());
        experimentRepository.save(getAllOptionsExperiment(metadataRepository, presenterFeatureRepository, presenterScreenRepository));
        experimentRepository.save(new JenaFieldKit().getJenaExperiment());
        experimentRepository.save(new ShawiFieldKit().getShawiExperiment());
        experimentRepository.save(new Sara01().getExperiment());
        experimentRepository.save(new FactOrFiction().getExperiment());
        experimentRepository.save(new SynQuiz2().getExperiment());

        for (Experiment experiment : experimentRepository.findAll()) {
            eventRepository.save(new PublishEvents(experiment, new Date(), new Date(), PublishEvents.PublishState.published, true, true, true));
        }
    }

    public Experiment getDobesExperiment() {
        Experiment experiment = getDefault();
        experiment.setAppNameDisplay("Dobes Annotator");
        experiment.setAppNameInternal("DobesAnnotator");
        final Metadata metadata = new Metadata("workerId", "Reporter name *", ".'{'3,'}'", "Please enter at least three letters.", true, "This test can only be done once per worker.");
        final Metadata metadata1 = new Metadata("errordevice", "Device model", ".'{'2,'}'", "Please enter the device model", false, null);
        final Metadata metadata2 = new Metadata("errordescription", "Please describe the error", ".'{'2,'}'", "Please enter a short description of the issue", false, null);
        experiment.getMetadata().add(metadata);
        experiment.getMetadata().add(metadata1);
        experiment.getMetadata().add(metadata2);
        addDobesStimuli(experiment);
        final PresenterScreen autoMenuPresenter = addAutoMenu(1);
        experiment.getPresenterScreen().add(autoMenuPresenter);
        experiment.getPresenterScreen().add(addAnnotationTimelinePanel(autoMenuPresenter, 2));
        experiment.getPresenterScreen().add(addVideosMenu(autoMenuPresenter, 3));
        experiment.getPresenterScreen().add(addTargetScreen(autoMenuPresenter, 4));
        experiment.getPresenterScreen().add(addVideoAspen(autoMenuPresenter, 5));
        experiment.getPresenterScreen().add(addVideoWorksPage(autoMenuPresenter, 6));
        experiment.getPresenterScreen().add(addVideoFailedPage(autoMenuPresenter, 7));
//        presenterScreenRepository.save(experiment.getPresenterScreen());
        return experiment;
    }

    public Experiment getSentveri_exp3Experiment() {
        Experiment experiment = getDefault("Sentveri_exp3");
        experiment.setStimuli(new Sentveri_exp3().createStimuli());
        new Sentveri_exp3().create3c(experiment.getPresenterScreen());
        return experiment;
    }

    public final Experiment getDefault(final String appName) {
        final Experiment experiment = getDefault();
        experiment.setAppNameDisplay(appName);
        experiment.setAppNameInternal(appName);
        final PresenterScreen autoMenuPresenter = addAutoMenu(10);
        experiment.getPresenterScreen().add(autoMenuPresenter);
        final Metadata metadata = new Metadata("workerId", "Reporter name *", ".'{'3,'}'", "Please enter at least three letters.", true, "This test can only be done once per worker.");
        final Metadata metadata1 = new Metadata("errordevice", "Device model", ".'{'2,'}'", "Please enter the device model", false, null);
        final Metadata metadata2 = new Metadata("errordescription", "Please describe the error", ".'{'2,'}'", "Please enter a short description of the issue", false, null);
        experiment.getMetadata().add(metadata);
        experiment.getMetadata().add(metadata1);
        experiment.getMetadata().add(metadata2);
        return experiment;
    }

    public final static Experiment getDefault() {
        final Experiment experiment = new Experiment();
        experiment.setAppNameDisplay("");
        experiment.setAppNameInternal("");
        experiment.setPrimaryColour0("#628D8D");
        experiment.setPrimaryColour1("#385E5E");
        experiment.setPrimaryColour2("#4A7777");
        experiment.setPrimaryColour3("#96ADAD");
        experiment.setPrimaryColour4("#D5D8D8");
        experiment.setComplementColour0("#EAC3A3");
        experiment.setComplementColour1("#9D7B5E");
        experiment.setComplementColour2("#C69E7C");
        experiment.setComplementColour3("#FFEDDE");
        experiment.setComplementColour4("#FFFDFB");
        experiment.setBackgroundColour("#FFFFFF");
        return experiment;
    }

    public Experiment getAllOptionsExperiment(MetadataRepository metadataRepository, PresenterFeatureRepository presenterFeatureRepository, PresenterScreenRepository presenterScreenRepository) {
        final Experiment experiment = new Experiment();
        experiment.setAppNameDisplay("All Options");
        experiment.setAppNameInternal("AllOptions");
        experiment.setPrimaryColour0("#413B52");
        experiment.setPrimaryColour1("#656469");
        experiment.setPrimaryColour2("#514E5C");
        experiment.setPrimaryColour3("#342954");
        experiment.setPrimaryColour4("#271460");
        experiment.setComplementColour0("#777151");
        experiment.setComplementColour1("#999891");
        experiment.setComplementColour2("#85816F");
        experiment.setComplementColour3("#7B6F34");
        experiment.setComplementColour4("#8B770E");
        experiment.setBackgroundColour("#FFFFFF");
        final Metadata metadata = new Metadata("workerId", "Reporter name *", ".'{'3,'}'", "Please enter at least three letters.", true, "This test can only be done once per worker.");
        final Metadata metadata1 = new Metadata("errordevice", "Device model", ".'{'2,'}'", "Please enter the device model", false, null);
        final Metadata metadata2 = new Metadata("errordescription", "Please describe the error", ".'{'2,'}'", "Please enter a short description of the issue", false, null);
        experiment.getMetadata().add(metadata);
        experiment.getMetadata().add(metadata1);
        experiment.getMetadata().add(metadata2);
        metadataRepository.save(experiment.getMetadata());
        addStimuli(experiment);
//        experiment.getPresenterScreen().add(addAnnotationTimelinePanel(presenterFeatureRepository));
//        experiment.getPresenterScreen().add(addVideosMenu(presenterFeatureRepository));
//        experiment.getPresenterScreen().add(addAutoMenu(presenterFeatureRepository));
//        experiment.getPresenterScreen().add(addVideoAspen(presenterFeatureRepository));
//        experiment.getPresenterScreen().add(addVideoWorksPage(presenterFeatureRepository));
//        experiment.getPresenterScreen().add(addVideoFailedPage(presenterFeatureRepository));
        final PresenterScreen autoMenu = addAutoMenu(0);
        presenterFeatureRepository.save(autoMenu.getPresenterFeatureList());
        experiment.getPresenterScreen().add(addTargetScreen(autoMenu, 0));
        experiment.getPresenterScreen().add(autoMenu);
        addAllFeaturesAsPages(presenterFeatureRepository, experiment, autoMenu, 0);
        presenterScreenRepository.save(experiment.getPresenterScreen());
        return experiment;
    }

    private void addDobesStimuli(final Experiment experiment) {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();

        tagSet.add("videotag");
        for (int i = 0; i < 10; i++) {
            final Stimulus stimulus = new Stimulus("videotag" + i, null, null, null, "videotag" + i + ".png", "videotag" + i, "videotag" + i, 0, tagSet);
            final URL resourceUrl = DefaultExperiments.class.getResource("/stimuli/videotag" + (i + 1) + ".png");
            File file = new File(resourceUrl.getFile());
            byte[] fileBytes = new byte[(int) file.length()];

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(fileBytes);
                fileInputStream.close();
                stimulus.setImageData(fileBytes);
            } catch (Exception e) {
                LOG.log(Level.INFO, "failed to load stimuli resource file", e);
            }
            stimuliList.add(stimulus);
        }
        for (String tag : new String[]{"sim", "mid", "diff", "noise"}) {
            for (String label : new String[]{"rabbit", "cat", "muffin", "you"}) {
                tagSet.clear();
                tagSet.add(tag);
                stimuliList.add(new Stimulus(tag + "_" + label, tag + "_" + label, tag + "_" + label, tag + "_" + label, tag + "_" + label + ".jpg", tag + "_" + label, tag + "_" + label, 0, tagSet));
            }
        }
        tagSet.clear();
        for (String word : "termites scorpions centipedes".split(" ")) {
            for (String speaker : "Rocket Festival Thai ประเพณีบุญบั้งไฟ Lao ບຸນບັ້ງໄຟ".split(" ")) {
                for (int i = 0; i < 6; i++) {
                    stimuliList.add(new Stimulus(word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i + ".mp3", word + "_" + speaker + "_" + i + ".mp4", word + "_" + speaker + "_" + i + ".ogg", word + "_" + speaker + "_" + i + ".jpg", word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i, 0, new HashSet<>(Arrays.asList(new String[]{word, speaker}))));
                }
            }
        }
        experiment.setStimuli(stimuliList);
    }

    private void addStimuli(final Experiment experiment) {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();
        tagSet.add("number");
        tagSet.add("interesting");
        stimuliList.add(new Stimulus("one", "one.mp3", "one.mp4", "one.ogg", "one.jpg", "One", "One", 0, tagSet));
        tagSet.add("multiple words");
        stimuliList.add(new Stimulus("two", "two.mp3", "two.mp4", "two.ogg", "two.jpg", "Two", "Two", 0, tagSet));
        tagSet.clear();
        tagSet.add("FILLER_AUDIO");
        stimuliList.add(new Stimulus("three", "three.mp3", "three.mp4", "three.ogg", "three.jpg", "Three", "Three", 0, tagSet));
        stimuliList.add(new Stimulus("four", "four.mp3", "four.mp4", "four.ogg", "four.jpg", "Four", "Four", 0, tagSet));
        tagSet.clear();
        tagSet.add("NOISE_AUDIO");
        stimuliList.add(new Stimulus("five", "five.mp3", "five.mp4", "five.ogg", "five.jpg", "Five", "Five", 0, tagSet));
        stimuliList.add(new Stimulus("six", "six.mp3", "six.mp4", "six.ogg", "six.jpg", "Six", "Six", 0, tagSet));
        for (String tag : new String[]{"sim", "mid", "diff", "noise"}) {
            for (String label : new String[]{"rabbit", "cat", "muffin", "you"}) {
                tagSet.clear();
                tagSet.add(tag);
                stimuliList.add(new Stimulus(tag + "_" + label, tag + "_" + label, tag + "_" + label, tag + "_" + label, tag + "_" + label + ".jpg", tag + "_" + label, tag + "_" + label, 0, tagSet));
            }
        }
        tagSet.clear();
        for (String word : "termites scorpions centipedes".split(" ")) {
            for (String speaker : "Rocket Festival Thai ประเพณีบุญบั้งไฟ Lao ບຸນບັ້ງໄຟ".split(" ")) {
                for (int i = 0; i < 6; i++) {
                    stimuliList.add(new Stimulus(word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i + ".mp3", word + "_" + speaker + "_" + i + ".mp4", word + "_" + speaker + "_" + i + ".ogg", word + "_" + speaker + "_" + i + ".jpg", word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i, 0, new HashSet<>(Arrays.asList(new String[]{word, speaker}))));
                }
            }
        }
        stimuliList.add(new Stimulus("bad chars", "bad chars", "bad chars", "bad chars", "bad chars", "bad chars", "bad chars", 0, new HashSet<>(Arrays.asList("bad chars bad_chars bad_chars  ( ) {\n    ( ) {\n         = .(\"[ \\\\t\\\\n\\\\x0B\\\\f\\\\r\\\\(\\\\)\\\\{\\\\};\\\\?\\\\/\\\\\\\\]\", \"_\");\n        this..add();\n    }         = .(\"[ \\\\t\\\\n\\\\x0B\\\\f\\\\r\\\\(\\\\)\\\\{\\\\};\\\\?\\\\/\\\\\\\\]\", \"_\");\n        this..add();\n    }".split(" ")))));
        experiment.setStimuli(stimuliList);
    }

    private void addAllFeaturesAsPages(PresenterFeatureRepository presenterFeatureRepository, final Experiment experiment, PresenterScreen backPresenter, long displayOrder) {
//        int maxScreenAddCount = 5;
        for (PresenterType presenterType : PresenterType.values()) {
//            maxScreenAddCount--;
            final PresenterScreen presenterScreen = new PresenterScreen(presenterType.name(), presenterType.name(), backPresenter, presenterType.name() + "Screen", null, presenterType, displayOrder);
            for (FeatureType featureType : presenterType.getFeatureTypes()) {
                if (featureType != FeatureType.hasTag
                        && featureType != FeatureType.withoutTag
                        && featureType != FeatureType.onError
                        && featureType != FeatureType.onSuccess
                        && featureType != FeatureType.hasMoreStimulus
                        && featureType != FeatureType.endOfStimulus) {
                    presenterScreen.getPresenterFeatureList().add(addFeature(featureType, presenterFeatureRepository));
                }
            }
            presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
            experiment.getPresenterScreen().add(presenterScreen);
//            if (maxScreenAddCount <= 0) {
//                break;
//            }
        }
    }

    private PresenterFeature addFeature(FeatureType featureType, PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterFeature presenterFeature = new PresenterFeature(featureType, (featureType.canHaveText()) ? featureType.name() : null);
        if (featureType.getFeatureAttributes() != null) {
            for (FeatureAttribute attribute : featureType.getFeatureAttributes()) {
                switch (attribute) {
                    case columnCount:
                    case maxStimuli:
                    case scoreThreshold:
                        presenterFeature.addFeatureAttributes(attribute, "3");
                        break;
                    case maxHeight:
                    case maxWidth:
                    case msToNext:
                        presenterFeature.addFeatureAttributes(attribute, "60");
                        break;
                    case condition0Tag:
                        presenterFeature.addFeatureAttributes(attribute, "centipedes");
                        break;
                    case condition1Tag:
                        presenterFeature.addFeatureAttributes(attribute, "scorpions");
                        break;
                    case condition2Tag:
                        presenterFeature.addFeatureAttributes(attribute, "termites");
                        break;
                    case hotKey:
                        presenterFeature.addFeatureAttributes(attribute, "A");
                        break;
                    case percentOfPage:
                        presenterFeature.addFeatureAttributes(attribute, "56");
                        break;
                    case eventTier:
                        presenterFeature.addFeatureAttributes(attribute, "8");
                        break;
                    case fieldName:
                        presenterFeature.addFeatureAttributes(attribute, "workerId");
                        break;
                    default:
                        presenterFeature.addFeatureAttributes(attribute, attribute.name());
                }
            }
        }
        if (featureType.canHaveStimulusTags()) {
            for (String stimulusTag : new String[]{"ประเพณีบุญบั้งไฟ", "Rocket", "Festival", "Lao", "Thai", "ບຸນບັ້ງໄຟ"}) {
                presenterFeature.addStimulusTag(stimulusTag);
            }
        }
        switch (featureType.getContitionals()) {
            case hasCorrectIncorrect:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.responseCorrect, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.responseIncorrect, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;
            case hasStimulusTag:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.hasTag, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.withoutTag, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;
            case hasMoreStimulus:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.hasMoreStimulus, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.endOfStimulus, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;
            case hasErrorSuccess:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.onError, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.onSuccess, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;
            case hasUserCount:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.multipleUsers, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.singleUser, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;
            case hasThreshold:
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.aboveThreshold, presenterFeatureRepository));
                presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.belowThreshold, presenterFeatureRepository));
                presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
                break;

            default:
                break;
        }
        if (featureType.canHaveFeatures()) {
            presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.plainText, presenterFeatureRepository));
            presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
        }
        return presenterFeature;
    }

    private PresenterScreen addVideosMenu(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video List", "Videos", backPresenter, "VideosPage", null, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "This is a simple video codec testing application."));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.AudioRecorderPanel, null);
//        presenterFeature.addFeatureAttributes(FeatureType.AudioRecorderPanel.getFeatureAttributes()[0], "some path");
//        presenterScreen.getPresenterFeatureList().add(presenterFeature);

        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.targetButton, "Video Test Page (aspen)");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoTestPageAspen");
        presenterScreen.getPresenterFeatureList().add(optionButton1);

        final PresenterFeature optionButton2 = new PresenterFeature(FeatureType.targetButton, "Annotation Timeline Screen");
        optionButton2.addFeatureAttributes(FeatureAttribute.target, "AnnotationTimelinePanel");
        presenterScreen.getPresenterFeatureList().add(optionButton2);

        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.versionData, null));

//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addAutoMenu(long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Auto Menu", "Menu", null, "AutoMenu", null, PresenterType.menu, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));
        return presenterScreen;
    }

    private PresenterScreen addTargetScreen(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Target Screen", "Target", backPresenter, "target", null, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "A simple page so that there is a screen with the target value of 'target' for testing purposes."));

//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoFailedPage(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Failed", "Video Failed Page", backPresenter, "VideoFailedPage", null, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "A message indicating that the video fails on your device has been sent."));
        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.targetButton, "Videos");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideosPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoWorksPage(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Works", "Video Works Page", backPresenter, "VideoWorksPage", null, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "A message indicating that the video works on your device has been sent."));
        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.targetButton, "Videos");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideosPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoAspen(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Test", "Video Test Page (aspen)", backPresenter, "VideoTestPageAspen", null, PresenterType.timeline, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4"));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.VideoPanel, null);
        presenterFeature.addFeatureAttributes(mp4, "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4");
        presenterFeature.addFeatureAttributes(percentOfPage, "50");
        presenterFeature.addFeatureAttributes(maxHeight, "70");
        presenterFeature.addFeatureAttributes(maxWidth, "70");
        presenterFeature.addFeatureAttributes(poster, "");
        presenterFeature.addFeatureAttributes(ogg, "");
        presenterFeature.addFeatureAttributes(webm, "");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);

        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.targetButton, "Video Works");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoWorksPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
        final PresenterFeature optionButton2 = new PresenterFeature(FeatureType.targetButton, "Video Failed");
        optionButton2.addFeatureAttributes(FeatureAttribute.target, "VideoFailedPage");
        presenterScreen.getPresenterFeatureList().add(optionButton2);
//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addAnnotationTimelinePanel(PresenterScreen backPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Annotation Timeline", "AnnotationTimelinePanel", backPresenter, "AnnotationTimelinePanel", null, PresenterType.timeline, displayOrder);
//        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "AnnotationTimelinePanel"));
//        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.padding, null));
//        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.VideoPanel, null);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.AnnotationTimelinePanel, null);
//        presenterFeature1.addFeatureAttributes(FeatureAttribute.width, "70%");
        presenterFeature1.addStimulusTag("videotag");
//        presenterFeature1.addStimulusTag("centipedes");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.columnCount, "2");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.eventTag, "annotationtimeline");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.maxStimuli, "10");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.mp4, "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4");
//        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);

//        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Video Works");
//        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoWorksPage");
//        presenterScreen.getPresenterFeatureList().add(optionButton1);
//        final PresenterFeature optionButton2 = new PresenterFeature(FeatureType.optionButton, "Video Failed");
//        optionButton2.addFeatureAttributes(FeatureAttribute.target, "VideoFailedPage");
//        presenterScreen.getPresenterFeatureList().add(optionButton2);
//        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }
}
