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

    String screenLabel;
    String stimulusTagArray[];
    StimuliSubAction[] featureValuesArray;
    boolean randomiseStimuli;
    int maxStimuli;
    boolean filePerStimulus;
    String end_of_stimuli;

    private WizardScreen endOfStimulisWizardScreen = null;

    public WizardStimulusScreen() {
        super("Stimulus", "Stimulus", "Stimulus");
    }

    public String getScreenLabel() {
        return screenLabel;
    }

    public void setScreenLabel(String screenLabel) {
        this.screenLabel = screenLabel;
    }

    public String[] getStimulusTagArray() {
        return stimulusTagArray;
    }

    public void setStimulusTagArray(String[] stimulusTagArray) {
        this.stimulusTagArray = stimulusTagArray;
    }

    public StimuliSubAction[] getFeatureValuesArray() {
        return featureValuesArray;
    }

    public void setFeatureValuesArray(StimuliSubAction[] featureValuesArray) {
        this.featureValuesArray = featureValuesArray;
    }

    public boolean isRandomiseStimuli() {
        return randomiseStimuli;
    }

    public void setRandomiseStimuli(boolean randomiseStimuli) {
        this.randomiseStimuli = randomiseStimuli;
    }

    public int getMaxStimuli() {
        return maxStimuli;
    }

    public void setMaxStimuli(int maxStimuli) {
        this.maxStimuli = maxStimuli;
    }

    public boolean isFilePerStimulus() {
        return filePerStimulus;
    }

    public void setFilePerStimulus(boolean filePerStimulus) {
        this.filePerStimulus = filePerStimulus;
    }

    public String getEnd_of_stimuli() {
        return end_of_stimuli;
    }

    public void setEnd_of_stimuli(String end_of_stimuli) {
        this.end_of_stimuli = end_of_stimuli;
    }

    public WizardScreen getEndOfStimulisWizardScreen() {
        return endOfStimulisWizardScreen;
    }

    public void setEndOfStimulisWizardScreen(WizardScreen endOfStimulisWizardScreen) {
        this.endOfStimulisWizardScreen = endOfStimulisWizardScreen;
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        String screenName = "";
        final List<Stimulus> stimuliList = experiment.getStimuli();
        for (final String stimulusTag : stimulusTagArray) {
            stimuliList.add(new Stimulus(stimulusTag, null, null, stimulusTag, stimulusTag, null, 0, new HashSet<>(Arrays.asList(new String[]{stimulusTag})), null));
            screenName += stimulusTag;
        }
        presenterScreen.setMenuLabel(screenTitle);
        setScreenTag(screenName + "Screen");
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.stimulus);

        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "This screen will show " + maxStimuli + " stimuli in random order from the directories:"));
//        for (final String stimulusTag : stimulusTagArray) {
//            presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "MPI_STIMULI/" + stimulusTag));
//        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadSdCardStimulus, null);
        for (final String stimulusTag : stimulusTagArray) {
            loadStimuliFeature.addStimulusTag(stimulusTag);
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(maxStimuli));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenLabel);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(randomiseStimuli));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        // todo: add more reverter tags as required
        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenLabel);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, (filePerStimulus) ? "true" : "false");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        PresenterFeature previousPresenterFeature = hasMoreStimulusFeature;
        for (StimuliSubAction imageFeatureValues : featureValuesArray) {
            final PresenterFeature lanwisImage = addImageFeature(previousPresenterFeature, imageFeatureValues);
            previousPresenterFeature = lanwisImage;
        }
        final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
        autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        previousPresenterFeature.getPresenterFeatureList().add(autoNextFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, end_of_stimuli));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, endOfStimulisWizardScreen.getScreenTag());
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, endOfStimulisWizardScreen.getScreenTag());
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
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
