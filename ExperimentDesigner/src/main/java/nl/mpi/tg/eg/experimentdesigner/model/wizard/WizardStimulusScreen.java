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
package nl.mpi.tg.eg.experimentdesigner.model.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since May 4, 2016 3:25:04 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardStimulusScreen extends AbstractWizardScreen {

//    private WizardScreen endOfStimulisWizardScreen = null;
    public WizardStimulusScreen() {
        super(WizardScreenEnum.WizardStimulusScreen, "Stimulus", "Stimulus", "Stimulus");
        wizardScreenData.setStimulusImageCapture(Boolean.FALSE);
    }

    public WizardStimulusScreen(String screenName) {
        super(WizardScreenEnum.WizardStimulusScreen, screenName, screenName, screenName);
        wizardScreenData.setStimulusImageCapture(Boolean.FALSE);
    }

    public void setStimulusTagArray(String[] stimulusTagArray) {
        this.wizardScreenData.setStimuliRandomTags(stimulusTagArray);
//    }
//    public void setStimulusArray(String[] stimulusTagArray) {
        if (this.wizardScreenData.getStimuli() == null) {
            this.wizardScreenData.setStimuli(new ArrayList<>());
        }
        final List<Stimulus> stimuliList = this.wizardScreenData.getStimuli();
        for (final String stimulusTag : stimulusTagArray) {
            final Stimulus stimulus = new Stimulus(stimulusTag, null, null, stimulusTag, stimulusTag, null, 0, new HashSet<>(Arrays.asList(new String[]{stimulusTag})), null);
            if (!stimuliList.contains(stimulus)) {
                // keep the stimulus list unique while still ordered
                stimuliList.add(stimulus);
            }
        }
    }

    public void setFeatureValuesArray(StimuliSubAction[] featureValuesArray) {
        wizardScreenData.setStimuliSubActions(featureValuesArray);
    }

    public void setRandomiseStimuli(boolean randomiseStimuli) {
        wizardScreenData.setRandomiseStimuli(randomiseStimuli);
    }

    public void setStimulusImageCapture(boolean stimulusImageCapture) {
        wizardScreenData.setStimulusImageCapture(stimulusImageCapture);
    }

    public void setMaxStimuli(int maxStimuli) {
        wizardScreenData.setStimuliCount(maxStimuli);
    }

    public void setFilePerStimulus(boolean filePerStimulus) {
        wizardScreenData.setFilePerStimulus(filePerStimulus);
    }

    public void setEnd_of_stimuli(String end_of_stimuli) {
        wizardScreenData.setScreenText1(end_of_stimuli);
    }

    public void setEndOfStimulisWizardScreen(WizardScreen endOfStimulisWizardScreen) {
        this.wizardScreenData.getMenuWizardScreenData().add(0, endOfStimulisWizardScreen.getWizardScreenData());
    }

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        experiment.appendUniqueStimuli(storedWizardScreenData.getStimuli());
        storedWizardScreenData.getPresenterScreen().setMenuLabel(storedWizardScreenData.getScreenTitle());
//        setScreenTag(screenName + "Screen");
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);

        List<PresenterFeature> presenterFeatureList = storedWizardScreenData.getPresenterScreen().getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "This screen will show " + maxStimuli + " stimuli in random order from the directories:"));
//        for (final String stimulusTag : stimulusTagArray) {
//            presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "MPI_STIMULI/" + stimulusTag));
//        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadSdCardStimulus, null);
        for (final String stimulusTag : storedWizardScreenData.getStimuliRandomTags()) {
            loadStimuliFeature.addStimulusTag(stimulusTag);
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(storedWizardScreenData.isRandomiseStimuli()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, "0");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        // todo: add more reverter tags as required
        if (storedWizardScreenData.getStimulusImageCapture()) {
            final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.stimulusImageCapture, "Take Photo"); // todo: add a paramter for this string
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "80");
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);
        } else {
            final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
            startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, (storedWizardScreenData.getFilePerStimulus()) ? "true" : "false");
            hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);
        }
        PresenterFeature previousPresenterFeature = hasMoreStimulusFeature;
        if (!storedWizardScreenData.getStimulusImageCapture()) {
            for (StimuliSubAction imageFeatureValues : storedWizardScreenData.getStimuliSubActions()) {
                final PresenterFeature lanwisImage = addImageFeature(previousPresenterFeature, imageFeatureValues);
                previousPresenterFeature = lanwisImage;
            }
            final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
            autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            previousPresenterFeature.getPresenterFeatureList().add(autoNextFeature);
        } else {
            final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulusButton, "Next");
            autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
            autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            autoNextFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
            previousPresenterFeature.getPresenterFeatureList().add(autoNextFeature);
        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, storedWizardScreenData.getScreenText1()));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, storedWizardScreenData.getMenuWizardScreenData().get(0).getScreenTag());
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, storedWizardScreenData.getMenuWizardScreenData().get(0).getScreenTag());
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }

    public PresenterFeature addImageFeature(PresenterFeature parentFeature, StimuliSubAction imageFeatureValues) {
        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        parentFeature.getPresenterFeatureList().add(startTagFeature);
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        parentFeature.getPresenterFeatureList().add(imageFeature);
        final PresenterFeature actionFeature;
        if (imageFeatureValues.getButtons().length == 1) {
            imageFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, imageFeatureValues.getLabel()));
            actionFeature = new PresenterFeature(FeatureType.actionButton, imageFeatureValues.getButtons()[0]);
            final PresenterFeature endAudioRecorderTagFeature = new PresenterFeature(FeatureType.endAudioRecorderTag, null);
            endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTag, imageFeatureValues.getLabel());
            actionFeature.getPresenterFeatureList().add(endAudioRecorderTagFeature);
        } else {
            actionFeature = new PresenterFeature(FeatureType.ratingFooterButton, null);
            actionFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, String.join(",", imageFeatureValues.getButtons()));
            actionFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        }
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        imageFeature.getPresenterFeatureList().add(actionFeature);
        return actionFeature;
    }
}
