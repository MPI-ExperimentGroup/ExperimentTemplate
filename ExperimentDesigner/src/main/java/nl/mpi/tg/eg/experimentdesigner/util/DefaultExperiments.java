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
import java.util.HashSet;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.dao.ExperimentRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since Sep 8, 2015 3:19:56 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DefaultExperiments {

    public void insertDefaultExperiment(
            PresenterScreenRepository presenterScreenRepository,
            PresenterFeatureRepository presenterFeatureRepository,
            MetadataRepository metadataRepository,
            ExperimentRepository experimentRepository) {
        final Experiment experiment = new Experiment();
        experiment.setAppNameDisplay("Dobes Annotator");
        experiment.setAppNameInternal("DobesAnnotator");
        experiment.setDataSubmitUrl("http://ems12.mpi.nl/dobes-frinex-admin-0.1.38-testing/");
        experiment.setStaticFilesUrl("static/");

        final Metadata metadata = new Metadata("workerId", "Reporter name *", ".'{'3,'}'", "Please enter at least three letters.", true, "This test can only be done once per worker.");
        final Metadata metadata1 = new Metadata("errordevice", "Device model", ".'{'2,'}'", "Please enter the device model", false, null);
        final Metadata metadata2 = new Metadata("errordescription", "Please describe the error", ".'{'2,'}'", "Please enter a short description of the issue", false, null);
        experiment.getMetadata().add(metadata);
        experiment.getMetadata().add(metadata1);
        experiment.getMetadata().add(metadata2);
        metadataRepository.save(experiment.getMetadata());
        addStimuli(experiment);
        experiment.getPresenterScreen().add(addAnnotationTimelinePanel(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideosMenu(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addAutoMenu(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addTargetScreen(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoAspen(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoWorksPage(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoFailedPage(presenterFeatureRepository));
        addAllFeaturesAsPages(presenterFeatureRepository, experiment);
        presenterScreenRepository.save(experiment.getPresenterScreen());
        experimentRepository.save(experiment);
    }

    private void addStimuli(final Experiment experiment) {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();
        tagSet.add("number");
        tagSet.add("interesting");
        stimuliList.add(new Stimulus("one.mp3", "one.mp4", "one.jpg", "One", tagSet));
        tagSet.add("multiple words");
        stimuliList.add(new Stimulus("two.mp3", "two.mp4", "two.jpg", "Two", tagSet));
        tagSet.clear();
        tagSet.add("FILLER_AUDIO");
        stimuliList.add(new Stimulus("three.mp3", "three.mp4", "three.jpg", "Three", tagSet));
        stimuliList.add(new Stimulus("four.mp3", "four.mp4", "four.jpg", "Four", tagSet));
        tagSet.clear();
        tagSet.add("NOISE_AUDIO");
        stimuliList.add(new Stimulus("five.mp3", "five.mp4", "five.jpg", "Five", tagSet));
        stimuliList.add(new Stimulus("six.mp3", "six.mp4", "six.jpg", "Six", tagSet));
        for (String tag : new String[]{"sim", "mid", "diff", "noise"}) {
            for (String label : new String[]{"rabbit", "cat", "muffin", "you"}) {
                tagSet.clear();
                tagSet.add(tag);
                stimuliList.add(new Stimulus(tag + "_" + label, tag + "_" + label, tag + "_" + label + ".jpg", tag + "_" + label, tagSet));
            }
        }
        experiment.setStimuli(stimuliList);
    }

    private void addAllFeaturesAsPages(PresenterFeatureRepository presenterFeatureRepository, final Experiment experiment) {
//        int maxScreenAddCount = 5;
        for (PresenterType presenterType : PresenterType.values()) {
//            maxScreenAddCount--;
            final PresenterScreen presenterScreen = new PresenterScreen(presenterType.name(), presenterType.name(), "AutoMenu", presenterType.name() + "Screen", null, presenterType);
            for (FeatureType featureType : presenterType.getFeatureTypes()) {
                presenterScreen.getPresenterFeatureList().add(addFeature(featureType, presenterFeatureRepository));
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
                    case condition:
                        presenterFeature.addFeatureAttributes(attribute, "true");
                        break;
                    case columnCount:
                    case setCount:
                        presenterFeature.addFeatureAttributes(attribute, "3");
                        break;
                    case width:
                    case timeToNext:
                        presenterFeature.addFeatureAttributes(attribute, "60");
                        break;
                    default:
                        presenterFeature.addFeatureAttributes(attribute, attribute.name());
                }
            }
        }
        if (featureType.requiresCorrectIncorrect()) {
            presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.responseCorrect, presenterFeatureRepository));
            presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.responseIncorrect, presenterFeatureRepository));
            presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
        }
        if (featureType.canHaveFeatures()) {
            presenterFeature.getPresenterFeatureList().add(addFeature(FeatureType.text, presenterFeatureRepository));
            presenterFeatureRepository.save(presenterFeature.getPresenterFeatureList());
        }
        return presenterFeature;
    }

    private PresenterScreen addVideosMenu(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video List", "Videos", "AutoMenu", "VideosPage", null, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "This is a simple video codec testing application."));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.padding, null));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.AudioRecorderPanel, null);
        presenterFeature.addFeatureAttributes(FeatureType.AudioRecorderPanel.getFeatureAttributes()[0], "some path");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);

        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Video Test Page (aspen)");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoTestPageAspen");
        presenterScreen.getPresenterFeatureList().add(optionButton1);

        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.padding, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.versionData, null));

        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addAutoMenu(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Auto Menu", "Menu", null, "AutoMenu", null, PresenterType.menu);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));

        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addTargetScreen(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Target Screen", "Target", "AutoMenu", "target", null, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "A simple page so that there is a screen with the target value of 'target' for testing purposes."));

        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoFailedPage(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Failed", "Video Failed Page", "VideosPage", "VideoFailedPage", null, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "A message indicating that the video fails on your device has been sent."));
        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Videos");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideosPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoWorksPage(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Works", "Video Works Page", "VideosPage", "VideoWorksPage", null, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "A message indicating that the video works on your device has been sent."));
        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Videos");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideosPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addVideoAspen(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("Video Test", "Video Test Page (aspen)", "VideosPage", "VideoTestPageAspen", null, PresenterType.text);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4"));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.padding, null));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.VideoPanel, null);
        presenterFeature.addFeatureAttributes(FeatureType.VideoPanel.getFeatureAttributes()[3], "70%");
        presenterFeature.addFeatureAttributes(FeatureType.VideoPanel.getFeatureAttributes()[0], "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);

        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Video Works");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoWorksPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
        final PresenterFeature optionButton2 = new PresenterFeature(FeatureType.optionButton, "Video Failed");
        optionButton2.addFeatureAttributes(FeatureAttribute.target, "VideoFailedPage");
        presenterScreen.getPresenterFeatureList().add(optionButton2);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }

    private PresenterScreen addAnnotationTimelinePanel(PresenterFeatureRepository presenterFeatureRepository) {
        final PresenterScreen presenterScreen = new PresenterScreen("AnnotationTimelinePanel", "AnnotationTimelinePanel", "VideosPage", "AnnotationTimelinePanel", null, PresenterType.timeline);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "AnnotationTimelinePanel"));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.padding, null));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.VideoPanel, null);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.AnnotationTimelinePanel, null);
        presenterFeature.addFeatureAttributes(FeatureType.VideoPanel.getFeatureAttributes()[3], "70%");
        presenterFeature.addFeatureAttributes(FeatureType.VideoPanel.getFeatureAttributes()[0], "http://corpus1.mpi.nl/media-archive/Info/enctest/aspen.mp4");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);

        final PresenterFeature optionButton1 = new PresenterFeature(FeatureType.optionButton, "Video Works");
        optionButton1.addFeatureAttributes(FeatureAttribute.target, "VideoWorksPage");
        presenterScreen.getPresenterFeatureList().add(optionButton1);
        final PresenterFeature optionButton2 = new PresenterFeature(FeatureType.optionButton, "Video Failed");
        optionButton2.addFeatureAttributes(FeatureAttribute.target, "VideoFailedPage");
        presenterScreen.getPresenterFeatureList().add(optionButton2);
        presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
        return presenterScreen;
    }
}
