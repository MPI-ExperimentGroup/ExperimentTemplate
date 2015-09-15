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
        presenterScreenRepository.save(experiment.getPresenterScreen());
        experimentRepository.save(experiment);
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
}
