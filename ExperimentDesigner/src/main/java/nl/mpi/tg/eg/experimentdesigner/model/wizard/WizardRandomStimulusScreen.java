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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class WizardRandomStimulusScreen extends AbstractWizardScreen {

    public WizardRandomStimulusScreen() {
        super(WizardScreenEnum.WizardRandomStimulusScreen, "RandomStimulus", "RandomStimulus", "RandomStimulus");
        this.wizardScreenData.setRandomiseStimuli(false);
        this.wizardScreenData.setStimuliCount(1);
        this.wizardScreenData.setStimulusMsDelay(0);
        this.wizardScreenData.setStimulusFreeText(false);
        this.wizardScreenData.setAllowHotkeyButtons(true);
//        this.wizardScreenData.setButtonLabelEventTag("");
        this.wizardScreenData.setCentreScreen(true);
    }

    public WizardRandomStimulusScreen(String screenName, boolean centreScreen, String[] screenTextArray, String[] randomStimuliTags, int maxStimuli, final boolean randomiseStimuli, String stimulusCodeMatch, int stimulusDelay, int codeStimulusDelay, String codeFormat, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight, final String spacebar) {
        super(WizardScreenEnum.WizardRandomStimulusScreen, screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.wizardScreenData.setStimuliRandomTags(randomStimuliTags);
        this.wizardScreenData.setStimulusCodeMatch(stimulusCodeMatch);
        this.wizardScreenData.setStimulusCodeMsDelay(0);
        this.wizardScreenData.setStimulusMsDelay(0);
        this.wizardScreenData.setStimulusCodeFormat(codeFormat);
        this.wizardScreenData.setStimuliCount(maxStimuli);
        this.wizardScreenData.setStimulusResponseLabelLeft(responseOptionsLabelLeft);
        this.wizardScreenData.setStimulusResponseLabelRight(responseOptionsLabelRight);
        this.wizardScreenData.setStimulusResponseOptions(responseOptions);
        this.wizardScreenData.setRandomiseStimuli(randomiseStimuli);
        this.wizardScreenData.setCentreScreen(centreScreen);
        this.wizardScreenData.setAllowHotkeyButtons(true);
        this.wizardScreenData.setStimulusFreeText(false);
        if (spacebar == null) {
            throw new UnsupportedOperationException("button text cannot be null");
        }
        setButtonLabel(spacebar);
//        this.wizardScreenData.setButtonLabelEventTag(spacebar);
        setStimuliSet(screenTextArray);
    }

    public WizardRandomStimulusScreen(String screenName, String[] screenTextArray, int maxStimuli, final boolean randomiseStimuli, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight) {
        super(WizardScreenEnum.WizardRandomStimulusScreen, screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.wizardScreenData.setStimulusCodeMsDelay(0);
        this.wizardScreenData.setStimulusMsDelay(0);
        this.wizardScreenData.setStimuliCount(maxStimuli);
        this.wizardScreenData.setStimulusResponseLabelLeft(responseOptionsLabelLeft);
        this.wizardScreenData.setStimulusResponseLabelRight(responseOptionsLabelRight);
        this.wizardScreenData.setStimulusResponseOptions(responseOptions);
        this.wizardScreenData.setRandomiseStimuli(randomiseStimuli);
        this.wizardScreenData.setCentreScreen(true);
        this.wizardScreenData.setStimulusFreeText(false);
        setStimuliSet(screenTextArray);
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getScreenTextInfo(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Next Button Label"}[index];
    }

//    public void setStimuliPath(String stimuliPath) {
//        this.stimuliPath = stimuliPath;
//    }
    public final void setStimuliSet(String[] stimuliSet) {
        if (this.wizardScreenData.getStimuli() == null) {
            this.wizardScreenData.setStimuli(new ArrayList<>());
        }
        final List<Stimulus> stimuliList = this.wizardScreenData.getStimuli();
        final Pattern stimulusCodePattern = (wizardScreenData.getStimulusCodeMatch() != null) ? Pattern.compile(wizardScreenData.getStimulusCodeMatch()) : null;
        if (stimuliSet != null) {
            for (String stimulusLine : stimuliSet) {
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{this.wizardScreenData.getScreenTitle()}));
                final Stimulus stimulus;
                if (stimulusCodePattern != null) {
                    System.out.println("stimulusCodeMatch:" + wizardScreenData.getStimulusCodeMatch());
                    Matcher matcher = stimulusCodePattern.matcher(stimulusLine);
                    final String codeString = (matcher.find()) ? matcher.group(1) : null;
                    System.out.println("codeString: " + codeString);
                    final String baseFileName = stimulusLine.replaceAll(BASE_FILE_REGEX, "");
                    tagSet.addAll(Arrays.asList(baseFileName.split("/")));
                    stimulus = new Stimulus(baseFileName, null, null, stimulusLine, null, codeString, 0, tagSet, null);
                } else if (stimulusLine.endsWith(".png")) {
                    tagSet.addAll(Arrays.asList(stimulusLine.split("/")));
                    stimulus = new Stimulus(stimulusLine.replace(".png", ""), null, null, stimulusLine, null, null, 0, tagSet, null);
                } else {
                    final String[] splitScreenText = stimulusLine.split(":", 2);
                    tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                    final String substring = (stimulusLine.length() < 55) ? stimulusLine : stimulusLine.substring(0, 54);
                    stimulus = new Stimulus(substring, null, null, null, splitScreenText[1].replace("\n", "<br/>"), null/*splitScreenText[0].replace(" ", "_").replace("/", "_")*/, 0, tagSet, null);
                }
                stimuliList.add(stimulus);
            }
        }
    }

    public void setStimulusFreeText(boolean stimulusFreeText, String freeTextValidationRegex, String freeTextValidationMessage) {
        this.wizardScreenData.setStimulusFreeText(stimulusFreeText);
        this.wizardScreenData.setFreeTextValidationRegex(freeTextValidationRegex);
        this.wizardScreenData.setFreeTextValidationMessage(freeTextValidationMessage);
    }

    final public void setButtonLabel(String buttonLabelEventTag) {
        this.wizardScreenData.setNextButton(new String[]{buttonLabelEventTag});
        this.wizardScreenData.setButtonLabelEventTag(buttonLabelEventTag);
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
        final RandomGrouping randomGrouping = new RandomGrouping();
        if (storedWizardScreenData.getStimuliRandomTags() != null) {
            for (String randomTag : storedWizardScreenData.getStimuliRandomTags()) {
                randomGrouping.addRandomTag(randomTag);
            }
            final String metadataFieldname = "groupAllocation_" + storedWizardScreenData.getScreenTag();
            randomGrouping.setStorageField(metadataFieldname);
            loadStimuliFeature.setRandomGrouping(randomGrouping);
            experiment.getMetadata().add(new Metadata(metadataFieldname, metadataFieldname, ".*", ".", false, null));
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(storedWizardScreenData.isRandomiseStimuli()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, "0");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(imageFeature);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
        final PresenterFeature presenterFeature;
        final String hotKeyString = "SPACE";
        if (storedWizardScreenData.getStimulusCodeFormat() != null) {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, storedWizardScreenData.getNextButton()[0]);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getButtonLabelEventTag());
            if (storedWizardScreenData.getAllowHotkeyButtons()) {
                nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, hotKeyString);
            }
            nextButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusCodeMsDelay()));
            nextButtonFeature.getPresenterFeatureList().add(pauseFeature);
            final PresenterFeature stimulusCodeAudio = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.codeFormat, storedWizardScreenData.getStimulusCodeFormat());
            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            pauseFeature.getPresenterFeatureList().add(stimulusCodeAudio);
            imageFeature.getPresenterFeatureList().add(nextButtonFeature);
            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "Het antwoord is:"));
            final PresenterFeature logTImeStamp = new PresenterFeature(FeatureType.logTimeStamp, null);
            logTImeStamp.addFeatureAttributes(FeatureAttribute.eventTag, "hetAntwoordIs");
            stimulusCodeAudio.getPresenterFeatureList().add(logTImeStamp);
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "<style=\"text-align: left;\">zeer waarschijnlijk negatief</style><style=\"text-align: right;\">zeer waarschijnlijk positief</style>"));
            presenterFeature = stimulusCodeAudio;
        } else {
            presenterFeature = imageFeature;
        }
        presenterFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        if (storedWizardScreenData.getStimulusFreeText()) {
            final PresenterFeature stimulusFreeTextFeature = new PresenterFeature(FeatureType.stimulusFreeText, storedWizardScreenData.getFreeTextValidationMessage());
            stimulusFreeTextFeature.addFeatureAttributes(FeatureAttribute.validationRegex, storedWizardScreenData.getFreeTextValidationRegex());
            presenterFeature.getPresenterFeatureList().add(stimulusFreeTextFeature);
        }
        if (storedWizardScreenData.getStimulusResponseOptions() != null) {
            final PresenterFeature ratingFooterButtonFeature = new PresenterFeature(FeatureType.ratingButton, null);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, storedWizardScreenData.getStimulusResponseOptions());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, storedWizardScreenData.getStimulusResponseLabelLeft());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, storedWizardScreenData.getStimulusResponseLabelRight());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + storedWizardScreenData.getScreenTitle());
            ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
        } else {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, storedWizardScreenData.getNextButton()[0]);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getButtonLabelEventTag());
            if (storedWizardScreenData.getAllowHotkeyButtons()) {
                nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, hotKeyString);
            }
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + storedWizardScreenData.getScreenTitle());
            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
