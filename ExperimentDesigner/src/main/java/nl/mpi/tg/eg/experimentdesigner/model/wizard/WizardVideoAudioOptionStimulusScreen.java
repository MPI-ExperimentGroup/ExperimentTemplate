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
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.RandomGrouping;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since May 11, 2016 5:08:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardVideoAudioOptionStimulusScreen extends AbstractWizardScreen {

    private String[] stimuliSet = null;
    private String[] stimuliRandomTags = null;
    private int stimulusMsDelay = 0;
    private int stimuliCount = 1;
    private String stimulusResponseLabelLeft = null;
    private String stimulusResponseLabelRight = null;
//    private String stimulusResponseOptions = null;
    private boolean randomiseStimuli = false;
    private boolean useCodeVideo = true;
    private String buttonLabelEventTag;

    public WizardVideoAudioOptionStimulusScreen() {
        super("VideoAudioOption", "VideoAudioOption", "VideoAudioOption");
    }

    public WizardVideoAudioOptionStimulusScreen(String screenName, boolean centreScreen, String[] screenTextArray, boolean useCodeVideo, String[] randomStimuliTags, int maxStimuli, final boolean randomiseStimuli, int stimulusMsDelay, String responseOptionsLabelLeft, String responseOptionsLabelRight, final String spacebar) {
        super(screenName, screenName, screenName);
        this.screenTitle = screenName;
        this.centreScreen = centreScreen;
        this.stimuliSet = screenTextArray;
        this.useCodeVideo = useCodeVideo;
        this.stimuliRandomTags = randomStimuliTags;
        this.stimulusMsDelay = stimulusMsDelay;
        this.stimuliCount = maxStimuli;
        this.stimulusResponseLabelLeft = responseOptionsLabelLeft;
        this.stimulusResponseLabelRight = responseOptionsLabelRight;
//        this.stimulusResponseOptions = responseOptions;
        this.randomiseStimuli = randomiseStimuli;
        if (spacebar == null) {
            throw new UnsupportedOperationException("button text cannot be null");
        }
        this.buttonLabelEventTag = spacebar;
    }

    public String[] getStimuliSet() {
        return stimuliSet;
    }

    public void setStimuliSet(String[] stimuliSet) {
        this.stimuliSet = stimuliSet;
    }

    public String[] getStimuliRandomTags() {
        return stimuliRandomTags;
    }

    public void setStimuliRandomTags(String[] stimuliRandomTags) {
        this.stimuliRandomTags = stimuliRandomTags;
    }

    public int getStimulusMsDelay() {
        return stimulusMsDelay;
    }

    public void setStimulusMsDelay(int stimulusMsDelay) {
        this.stimulusMsDelay = stimulusMsDelay;
    }

    public int getStimuliCount() {
        return stimuliCount;
    }

    public void setStimuliCount(int stimuliCount) {
        this.stimuliCount = stimuliCount;
    }

    public String getStimulusResponseLabelLeft() {
        return stimulusResponseLabelLeft;
    }

    public void setStimulusResponseLabelLeft(String stimulusResponseLabelLeft) {
        this.stimulusResponseLabelLeft = stimulusResponseLabelLeft;
    }

    public String getStimulusResponseLabelRight() {
        return stimulusResponseLabelRight;
    }

    public void setStimulusResponseLabelRight(String stimulusResponseLabelRight) {
        this.stimulusResponseLabelRight = stimulusResponseLabelRight;
    }

//    public String getStimulusResponseOptions() {
//        return stimulusResponseOptions;
//    }
//
//    public void setStimulusResponseOptions(String stimulusResponseOptions) {
//        this.stimulusResponseOptions = stimulusResponseOptions;
//    }
    public boolean isRandomiseStimuli() {
        return randomiseStimuli;
    }

    public void setRandomiseStimuli(boolean randomiseStimuli) {
        this.randomiseStimuli = randomiseStimuli;
    }

    public String getButtonLabel() {
        return buttonLabelEventTag;
    }

    public void setButtonLabel(String buttonLabelEventTag) {
        this.buttonLabelEventTag = buttonLabelEventTag;
    }
    private static final String BASE_FILE_REGEX = "\\.[a-zA-Z34]+$";

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        final List<Stimulus> stimuliList = experiment.getStimuli();
        if (stimuliSet != null) {
            for (String stimulusLine : stimuliSet) {
                // "list_1/list_2:AV_happy.mpg:prevoicing9_e_440Hz_coda_k.wav:bik,bek",
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{screenTitle}));
                final Stimulus stimulus;

                final String[] splitScreenText = stimulusLine.split(":", 5);
                tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                final String audioPath;
                final String codeVideoPath;
                final String responseOptions;
                if (useCodeVideo) {
                    audioPath = splitScreenText[2].replaceAll(BASE_FILE_REGEX, "");
                    codeVideoPath = splitScreenText[1].replaceAll(BASE_FILE_REGEX, "");
                    responseOptions = splitScreenText[3];
                } else {
                    audioPath = splitScreenText[1].replaceAll(BASE_FILE_REGEX, "");
                    codeVideoPath = null;
                    responseOptions = splitScreenText[2];
                }
                stimulus = new Stimulus(stimulusLine, audioPath, null, null, null, codeVideoPath, 0, tagSet, responseOptions);
                stimuliList.add(stimulus);
            }
        }

//        final PresenterScreen presenterScreen = new PresenterScreen((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : screenTitle, screenTitle, backPresenter, screenName, null, PresenterType.stimulus, displayOrder);
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        if (centreScreen) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(screenTitle);
        if (stimuliRandomTags != null) {
            final RandomGrouping randomGrouping = new RandomGrouping();
            for (String randomTag : stimuliRandomTags) {
                randomGrouping.addRandomTag(randomTag);
            }
            final String metadataFieldname = "groupAllocation_" + getScreenTag();
            randomGrouping.setStorageField(metadataFieldname);
            loadStimuliFeature.setRandomGrouping(randomGrouping);
            experiment.getMetadata().add(new Metadata(metadataFieldname, metadataFieldname, ".*", ".", false, null));
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenTitle);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(randomiseStimuli));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(stimuliCount));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));

        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusAudio, null);
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
//        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusMsDelay));

        if (useCodeVideo) {
            final PresenterFeature codeVideoFeature = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusMsDelay));
            hasMoreStimulusFeature.getPresenterFeatureList().add(codeVideoFeature);
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusMsDelay));
            codeVideoFeature.getPresenterFeatureList().add(pauseFeature);

            pauseFeature.getPresenterFeatureList().add(imageFeature);
        } else {
            hasMoreStimulusFeature.getPresenterFeatureList().add(imageFeature);
        }
        final PresenterFeature presenterFeature;
//        if (stimulusCodeFormat != null) {
//            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
//            nextButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
//            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
//            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusCodeMsDelay));
//            nextButtonFeature.getPresenterFeatureList().add(pauseFeature);
//            final PresenterFeature stimulusCodeAudio = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
//            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.codeFormat, stimulusCodeFormat);
//            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.msToNext, "0");
//            pauseFeature.getPresenterFeatureList().add(stimulusCodeAudio);
//            imageFeature.getPresenterFeatureList().add(nextButtonFeature);
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "Het antwoord is:"));
//            final PresenterFeature logTImeStamp = new PresenterFeature(FeatureType.logTimeStamp, null);
//            logTImeStamp.addFeatureAttributes(FeatureAttribute.eventTag, "hetAntwoordIs");
//            stimulusCodeAudio.getPresenterFeatureList().add(logTImeStamp);
////            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
////            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
////            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
////            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "<style=\"text-align: left;\">zeer waarschijnlijk negatief</style><style=\"text-align: right;\">zeer waarschijnlijk positief</style>"));
//            presenterFeature = stimulusCodeAudio;
//        } else {
        presenterFeature = imageFeature;
//        }
        presenterFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//        if (stimulusResponseOptions != null) {
        final PresenterFeature ratingFooterButtonFeature = new PresenterFeature(FeatureType.stimulusRatingButton, null);
//            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, stimulusResponseOptions);
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, stimulusResponseLabelLeft);
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, stimulusResponseLabelRight);
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + screenTitle);
        ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
        presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
//        } else {
//            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
//            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + screenTitle);
//            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
//            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
//        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
