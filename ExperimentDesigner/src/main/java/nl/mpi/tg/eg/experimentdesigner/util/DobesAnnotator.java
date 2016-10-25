/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
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
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import static nl.mpi.tg.eg.experimentdesigner.util.DefaultExperiments.getDefault;

/**
 * @since Oct 25, 2016 1:08:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DobesAnnotator {

    private static final Logger LOG = Logger.getLogger(DefaultExperiments.class.getName());
    private final WizardController wizardController = new WizardController();

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
        final PresenterScreen autoMenuPresenter = null;//addAutoMenu(1);
//        experiment.getPresenterScreen().add(autoMenuPresenter);
        experiment.getPresenterScreen().add(addAnnotationTimelinePanel(autoMenuPresenter, 2));
//        experiment.getPresenterScreen().add(addVideosMenu(autoMenuPresenter, 3));
//        experiment.getPresenterScreen().add(addTargetScreen(autoMenuPresenter, 4));
//        experiment.getPresenterScreen().add(addVideoAspen(autoMenuPresenter, 5));
//        experiment.getPresenterScreen().add(addVideoWorksPage(autoMenuPresenter, 6));
//        experiment.getPresenterScreen().add(addVideoFailedPage(autoMenuPresenter, 7));
//        presenterScreenRepository.save(experiment.getPresenterScreen());
        return experiment;
    }

    private void addDobesStimuli(final Experiment experiment) {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();

        tagSet.add("videotag");
        for (int i = 0; i < 10; i++) {
            final Stimulus stimulus = new Stimulus("videotag" + i, null, null, "videotag" + i + ".png", "videotag" + i, null, 0, tagSet, null);
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
                stimuliList.add(new Stimulus(tag + "_" + label, tag + "_" + label, tag + "_" + label, tag + "_" + label + ".jpg", tag + "_" + label, tag + "_" + label, 0, tagSet, null));
            }
        }
        tagSet.clear();
        for (String word : "termites scorpions centipedes".split(" ")) {
            for (String speaker : "Rocket Festival Thai ประเพณีบุญบั้งไฟ Lao ບຸນບັ້ງໄຟ".split(" ")) {
                for (int i = 0; i < 6; i++) {
                    stimuliList.add(new Stimulus(word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i + ".mp3", word + "_" + speaker + "_" + i + ".mp4", word + "_" + speaker + "_" + i + ".jpg", word + "_" + speaker + "_" + i, word + "_" + speaker + "_" + i, 0, new HashSet<>(Arrays.asList(new String[]{word, speaker})), null));
                }
            }
        }
        experiment.setStimuli(stimuliList);
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

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("Dobes Annotator");
        wizardData.setShowMenuBar(false);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen2 = new WizardTextScreen("InformationScreen1", "DobesAnnotator",
                "volgende [ spatiebalk ]"
        );
        wizardData.addScreen(wizardTextScreen2);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
