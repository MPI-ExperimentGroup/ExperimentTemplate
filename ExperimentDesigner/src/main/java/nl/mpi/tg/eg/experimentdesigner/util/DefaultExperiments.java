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
        experiment.getPresenterScreen().add(addVideosMenu(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addAutoMenu(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoAspen(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoWorksPage(presenterFeatureRepository));
        experiment.getPresenterScreen().add(addVideoFailedPage(presenterFeatureRepository));
        addAllFeaturesAsPages(presenterFeatureRepository, experiment);
        presenterScreenRepository.save(experiment.getPresenterScreen());
        experimentRepository.save(experiment);
    }

    private void addAllFeaturesAsPages(PresenterFeatureRepository presenterFeatureRepository, final Experiment experiment) {
        for (PresenterType presenterType : PresenterType.values()) {
            final PresenterScreen presenterScreen = new PresenterScreen(presenterType.name(), presenterType.name(), "AutoMenu", presenterType.name(), null, presenterType);
            for (FeatureType featureType : presenterType.getFeatureTypes()) {
                final PresenterFeature presenterFeature = new PresenterFeature(featureType, (featureType.canHaveText()) ? featureType.name() : null);
                if (featureType.getFeatureAttributes() != null) {
                    for (FeatureAttribute attribute : featureType.getFeatureAttributes()) {
                        presenterFeature.addFeatureAttributes(attribute, attribute.name());
                    }
                }
                presenterScreen.getPresenterFeatureList().add(presenterFeature);
            }
            presenterFeatureRepository.save(presenterScreen.getPresenterFeatureList());
            experiment.getPresenterScreen().add(presenterScreen);
        }
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
        final PresenterScreen presenterScreen = new PresenterScreen("Auto Menu", "Menu", "VideosPage", "AutoMenu", null, PresenterType.menu);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));

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
}
