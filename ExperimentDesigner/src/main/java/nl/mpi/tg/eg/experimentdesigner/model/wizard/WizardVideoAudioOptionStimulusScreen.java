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

    public WizardVideoAudioOptionStimulusScreen() {
        super(WizardScreenEnum.WizardVideoAudioOptionStimulusScreen, "VideoAudioOption", "VideoAudioOption", "VideoAudioOption");
    }

    public WizardVideoAudioOptionStimulusScreen(String screenName, boolean centreScreen, String[] screenTextArray, boolean useCodeVideo, boolean useCodeAudio, String[] randomStimuliTags, int maxStimuli, int repeatCount, int repeatRandomWindow, final boolean randomiseStimuli, int stimulusMsDelay, String responseOptionsLabelLeft, String responseOptionsLabelRight, boolean showHurryIndicator) {
        super(WizardScreenEnum.WizardVideoAudioOptionStimulusScreen, screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.setCentreScreen(centreScreen);

        final List<Stimulus> stimuliList = new ArrayList<>();
        if (screenTextArray != null) {
            for (String stimulusLine : screenTextArray) {
                // "list_1/list_2:AV_happy.mpg:prevoicing9_e_440Hz_coda_k.wav:bik,bek",
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{screenName}));
                final Stimulus stimulus;

                final String[] splitScreenText = stimulusLine.split(":", 6);
                tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                final String audioPath;
                final String codeVideoPath;
                final String responseOptions;
                final String stimuliCorrectResponses;
                if (useCodeVideo || useCodeAudio) {
                    audioPath = splitScreenText[2].replaceAll(BASE_FILE_REGEX, "");
                    codeVideoPath = splitScreenText[1].replaceAll(BASE_FILE_REGEX, "");
                    responseOptions = splitScreenText[3];
                    stimuliCorrectResponses = (splitScreenText.length > 4) ? splitScreenText[4] : null;
                } else {
                    audioPath = splitScreenText[1].replaceAll(BASE_FILE_REGEX, "");
                    codeVideoPath = null;
                    responseOptions = (splitScreenText[2].length() > 0) ? splitScreenText[2] : null;
                    stimuliCorrectResponses = (splitScreenText.length > 3) ? splitScreenText[3] : null;
                }

                stimulus = new Stimulus(stimulusLine, audioPath, null, null, null, codeVideoPath, 0, tagSet, responseOptions, stimuliCorrectResponses);
                stimuliList.add(stimulus);
            }
            wizardScreenData.setStimuli(stimuliList);
        }

        this.wizardScreenData.setScreenBoolean(0, useCodeVideo);
        this.wizardScreenData.setScreenBoolean(1, useCodeAudio);
        this.wizardScreenData.setStimuliRandomTags(randomStimuliTags);
        this.wizardScreenData.setStimulusMsDelay(stimulusMsDelay);
        this.wizardScreenData.setStimuliCount(maxStimuli);
        this.wizardScreenData.setRepeatCount(repeatCount);
        this.wizardScreenData.setRepeatRandomWindow(repeatRandomWindow);
        this.wizardScreenData.setStimulusResponseLabelLeft(responseOptionsLabelLeft);
        this.wizardScreenData.setStimulusResponseLabelRight(responseOptionsLabelRight);
//        this.stimulusResponseOptions = responseOptions;
        setRandomiseStimuli(randomiseStimuli);
        setShowProgress(true);
        setShowHurryIndicator(showHurryIndicator);
        setAllowFreeText(false, null, null, null, null, null);
        setRepeatIncorrect(false);
    }

    final public void setUseCodeVideo(boolean useCodeVideo) {
        this.wizardScreenData.setScreenBoolean(0, useCodeVideo);
    }

    final public void setUseCodeAudio(boolean useCodeAudio) {
        this.wizardScreenData.setScreenBoolean(1, useCodeAudio);
    }

    final public void setRandomiseStimuli(boolean randomiseStimuli) {
        this.wizardScreenData.setScreenBoolean(2, randomiseStimuli);
    }

    final public void setShowProgress(boolean showProgress) {
        this.wizardScreenData.setScreenBoolean(3, showProgress);
    }

    final public void setShowHurryIndicator(boolean showHurryIndicator) {
        this.wizardScreenData.setScreenBoolean(4, showHurryIndicator);
    }

    final public void setRepeatIncorrect(boolean showHurryIndicator) {
        this.wizardScreenData.setScreenBoolean(6, showHurryIndicator);
    }

    final public void setAllowFreeText(boolean allowFreeText, String nextStimulusButton,
            String validationRegex, String validationChallenge, final String allowedCharCodes, final String hotKey) {
        this.wizardScreenData.setScreenBoolean(5, allowFreeText);
        this.wizardScreenData.setScreenText(0, nextStimulusButton);
        this.wizardScreenData.setScreenText(1, validationRegex);
        this.wizardScreenData.setScreenText(2, validationChallenge);
        this.wizardScreenData.setScreenText(3, allowedCharCodes);
        this.wizardScreenData.setScreenText(4, hotKey);
    }

    private String getNextStimulusButton(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenText(0);
    }

    private String getFreeTextValidationRegex(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenText(1);
    }

    private String getFreeTextValidationChallenge(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenText(2);
    }

    private String getFreeTextAllowedCharCodes(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenText(3);
    }

    private String getFreeTextHotKey(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenText(4);
    }

    private boolean isUseCodeVideo(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(0);
    }

    private boolean isUseCodeAudio(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(1);
    }

    private boolean isRandomiseStimuli(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(2);
    }

    private boolean isShowProgress(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(3);
    }

    private boolean isShowHurryIndicator(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(4);
    }

    private boolean isAllowFreeText(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(5);
    }

    private boolean isRepeatIncorrect(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(6);
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        return new String[]{"Use Code Video", "Use Code Audio", "Randomise Stimuli", "Show Progress", "Show Hurry Indicator", "Allow Free Text"}[index];
    }

    @Override
    public String getScreenTextInfo(int index) {
        return new String[]{"Next Stimulus Button Label (when Allow Free Text is enabled)", "Validation Regex (when Allow Free Text is enabled)", "Validation Challenge (when Allow Free Text is enabled)", "Allowed Char Codes (when Allow Free Text is enabled)", "Next Button HotKey (when Allow Free Text is enabled)", ""}[index];
    }

    @Override
    public String getNextButtonInfo(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }
    private static final String BASE_FILE_REGEX = "\\.[a-zA-Z34]+$";

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        experiment.appendUniqueStimuli(storedWizardScreenData.getStimuli());
//        final PresenterScreen presenterScreen = new PresenterScreen((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : getScreenTitle(), getScreenTitle(), backPresenter, screenName, null, PresenterType.stimulus, displayOrder);
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = storedWizardScreenData.getPresenterScreen().getPresenterFeatureList();
        if (storedWizardScreenData.isCentreScreen()) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(storedWizardScreenData.getScreenTitle());
        if (storedWizardScreenData.getStimuliRandomTags() != null) {
            final RandomGrouping randomGrouping = new RandomGrouping();
            for (String randomTag : storedWizardScreenData.getStimuliRandomTags()) {
                randomGrouping.addRandomTag(randomTag);
            }
            final String metadataFieldname = "groupAllocation_" + storedWizardScreenData.getScreenTag();
            randomGrouping.setStorageField(metadataFieldname);
            loadStimuliFeature.setRandomGrouping(randomGrouping);
            experiment.getMetadata().add(new Metadata(metadataFieldname, metadataFieldname, ".*", ".", false, null));
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(isRandomiseStimuli(storedWizardScreenData)));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, Integer.toString(storedWizardScreenData.getRepeatCount()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, Integer.toString(storedWizardScreenData.getRepeatRandomWindow()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));

        if (isShowProgress(storedWizardScreenData)) {
            // todo: remove after testing or parameterise
            hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        }
        if (isAllowFreeText(storedWizardScreenData)) {
            hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        }
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusAudio, null);
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
//        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(isShowHurryIndicator(storedWizardScreenData)));
//        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));

        boolean useCodeVideo = isUseCodeVideo(storedWizardScreenData);
        boolean useCodeAudio = isUseCodeAudio(storedWizardScreenData);

        if (useCodeVideo) {
            final PresenterFeature codeVideoFeature = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>");
            codeVideoFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
            hasMoreStimulusFeature.getPresenterFeatureList().add(codeVideoFeature);
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            codeVideoFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
            codeVideoFeature.getPresenterFeatureList().add(pauseFeature);

            pauseFeature.getPresenterFeatureList().add(imageFeature);
        } else if (useCodeAudio) {
            final PresenterFeature codeAudioFeature = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            codeAudioFeature.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>");
            codeAudioFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
            hasMoreStimulusFeature.getPresenterFeatureList().add(codeAudioFeature);
            codeAudioFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            codeAudioFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
            codeAudioFeature.getPresenterFeatureList().add(pauseFeature);
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
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, storedWizardScreenData.getStimulusResponseLabelLeft());
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, storedWizardScreenData.getStimulusResponseLabelRight());
        ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.repeatIncorrect, (isRepeatIncorrect(storedWizardScreenData)) ? "true" : "false");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + storedWizardScreenData.getScreenTitle());
        ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
        if (isAllowFreeText(storedWizardScreenData)) {
            final PresenterFeature stimulusHasRatingOptions = new PresenterFeature(FeatureType.stimulusHasRatingOptions, null);
            final PresenterFeature stimulusFreeText = new PresenterFeature(FeatureType.stimulusFreeText, getFreeTextValidationChallenge(storedWizardScreenData));
            stimulusFreeText.addFeatureAttributes(FeatureAttribute.validationRegex, getFreeTextValidationRegex(storedWizardScreenData));
            if (getFreeTextAllowedCharCodes(storedWizardScreenData) != null) {
                stimulusFreeText.addFeatureAttributes(FeatureAttribute.allowedCharCodes, getFreeTextAllowedCharCodes(storedWizardScreenData));
            }
//            stimulusFreeText.addFeatureAttributes(FeatureAttribute.hotKey, getFreeTextHotKey(storedWizardScreenData));

            final PresenterFeature conditionTrue = new PresenterFeature(FeatureType.conditionTrue, null);
            final PresenterFeature conditionFalse = new PresenterFeature(FeatureType.conditionFalse, null);
            conditionTrue.getPresenterFeatureList().add(ratingFooterButtonFeature);
            conditionFalse.getPresenterFeatureList().add(stimulusFreeText);
            final PresenterFeature nextStimulusButton = new PresenterFeature(FeatureType.nextStimulusButton, getNextStimulusButton(storedWizardScreenData));
            conditionFalse.getPresenterFeatureList().add(nextStimulusButton);
            nextStimulusButton.addFeatureAttributes(FeatureAttribute.eventTag, getNextStimulusButton(storedWizardScreenData));
            nextStimulusButton.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
            nextStimulusButton.addFeatureAttributes(FeatureAttribute.hotKey, getFreeTextHotKey(storedWizardScreenData));
            stimulusHasRatingOptions.getPresenterFeatureList().add(conditionTrue);
            stimulusHasRatingOptions.getPresenterFeatureList().add(conditionFalse);
            presenterFeature.getPresenterFeatureList().add(stimulusHasRatingOptions);
        } else {
            presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
        }
//        } else {
//            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
//            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + getScreenTitle());
//            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
//            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
//        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
