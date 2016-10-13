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

    private String buttonLabelEventTag;

    public WizardVideoAudioOptionStimulusScreen() {
        super("VideoAudioOption", "VideoAudioOption", "VideoAudioOption");
    }

    public WizardVideoAudioOptionStimulusScreen(String screenName, boolean centreScreen, String[] screenTextArray, boolean useCodeVideo, String[] randomStimuliTags, int maxStimuli, int repeatCount, int repeatRandomWindow, final boolean randomiseStimuli, int stimulusMsDelay, String responseOptionsLabelLeft, String responseOptionsLabelRight, final String spacebar) {
        super(screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.setCentreScreen(centreScreen);
        this.wizardScreenData.setStimuliSet(screenTextArray);
        this.wizardScreenData.setUseCodeVideo(useCodeVideo);
        this.wizardScreenData.setStimuliRandomTags(randomStimuliTags);
        this.wizardScreenData.setStimulusMsDelay(stimulusMsDelay);
        this.wizardScreenData.setStimuliCount(maxStimuli);
        this.wizardScreenData.setRepeatCount(repeatCount);
        this.wizardScreenData.setRepeatRandomWindow(repeatRandomWindow);
        this.wizardScreenData.setStimulusResponseLabelLeft(responseOptionsLabelLeft);
        this.wizardScreenData.setStimulusResponseLabelRight(responseOptionsLabelRight);
//        this.stimulusResponseOptions = responseOptions;
        this.wizardScreenData.setRandomiseStimuli(randomiseStimuli);
        this.wizardScreenData.setShowProgress(true);
        if (spacebar == null) {
            throw new UnsupportedOperationException("button text cannot be null");
        }
        this.buttonLabelEventTag = spacebar;
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
        if (wizardScreenData.getStimuliSet() != null) {
            for (String stimulusLine : wizardScreenData.getStimuliSet()) {
                // "list_1/list_2:AV_happy.mpg:prevoicing9_e_440Hz_coda_k.wav:bik,bek",
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{getScreenTitle()}));
                final Stimulus stimulus;

                final String[] splitScreenText = stimulusLine.split(":", 5);
                tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                final String audioPath;
                final String codeVideoPath;
                final String responseOptions;
                if (wizardScreenData.getUseCodeVideo()) {
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

//        final PresenterScreen presenterScreen = new PresenterScreen((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : getScreenTitle(), getScreenTitle(), backPresenter, screenName, null, PresenterType.stimulus, displayOrder);
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        getPresenterScreen().setPresenterType(PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = getPresenterScreen().getPresenterFeatureList();
        if (isCentreScreen()) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(getScreenTitle());
        if (this.wizardScreenData.getStimuliRandomTags() != null) {
            final RandomGrouping randomGrouping = new RandomGrouping();
            for (String randomTag : this.wizardScreenData.getStimuliRandomTags()) {
                randomGrouping.addRandomTag(randomTag);
            }
            final String metadataFieldname = "groupAllocation_" + getScreenTag();
            randomGrouping.setStorageField(metadataFieldname);
            loadStimuliFeature.setRandomGrouping(randomGrouping);
            experiment.getMetadata().add(new Metadata(metadataFieldname, metadataFieldname, ".*", ".", false, null));
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(this.wizardScreenData.isRandomiseStimuli()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, Integer.toString(this.wizardScreenData.getRepeatCount()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, Integer.toString(this.wizardScreenData.getRepeatRandomWindow()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(this.wizardScreenData.getStimuliCount()));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));

        if (wizardScreenData.getShowProgress()) {
            hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        }
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusAudio, null);
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
//        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(this.wizardScreenData.getStimulusMsDelay()));

        if (wizardScreenData.getUseCodeVideo()) {
            final PresenterFeature codeVideoFeature = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(this.wizardScreenData.getStimulusMsDelay()));
            hasMoreStimulusFeature.getPresenterFeatureList().add(codeVideoFeature);
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(this.wizardScreenData.getStimulusMsDelay()));
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
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, wizardScreenData.getStimulusResponseLabelLeft());
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, wizardScreenData.getStimulusResponseLabelRight());
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + getScreenTitle());
        ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
        presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
//        } else {
//            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
//            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + getScreenTitle());
//            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
//            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
//        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }
}
