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
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since May 6, 2016 3:20:04 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardAudioRecorderMetadataScreen extends AbstractWizardScreen {

    String[] metadataStrings;
    String next_button;
    String end_of_stimuli;

    public WizardAudioRecorderMetadataScreen(String[] metadataStrings, String next_button, String end_of_stimuli) {
        super("Metadata", "Metadata", "MetadataScreen");
        this.metadataStrings = metadataStrings;
        this.next_button = next_button;
        this.end_of_stimuli = end_of_stimuli;
    }

    public String[] getMetadataStrings() {
        return metadataStrings;
    }

    public void setMetadataStrings(String[] metadataStrings) {
        this.metadataStrings = metadataStrings;
    }

    public String getNext_button() {
        return next_button;
    }

    public void setNext_button(String next_button) {
        this.next_button = next_button;
    }

    public String getEnd_of_stimuli() {
        return end_of_stimuli;
    }

    public void setEnd_of_stimuli(String end_of_stimuli) {
        this.end_of_stimuli = end_of_stimuli;
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.stimulus);
        //    Metadata is collected in the spoken form (audio recording) with screen prompts for each item in metadataStrings:
        final List<Stimulus> stimuliList = experiment.getStimuli();
        final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{"metadata"}));
        for (String metadataString : metadataStrings) {
            final Stimulus stimulus = new Stimulus(metadataString.replaceAll("[^A-Za-z0-9]", "_"), null, null, null, metadataString, null, 0, tagSet,null);
            stimuliList.add(stimulus);
        }
//        experiment.setStimuli(stimuliList);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag("metadata");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, "Metadata");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, "false");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, "Metadata");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startTagFeature);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, next_button);
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
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stopAudioRecorder, null));
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, end_of_stimuli));
//        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, nextPresenter.getMenuLabel());
//        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
//        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
