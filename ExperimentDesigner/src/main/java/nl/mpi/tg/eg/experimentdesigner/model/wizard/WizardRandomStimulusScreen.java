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

    private String stimuliPath = "";
    private String[] stimuliSet = null;
    private String[] stimuliRandomTags = null;
    private String stimulusCodeMatch = null;
    private int stimulusCodeMsDelay = 0;
    private int stimulusMsDelay = 0;
    private String stimulusCodeFormat = null;
    private int stimuliCount = 1;
    private String stimulusResponseLabelLeft = null;
    private String stimulusResponseLabelRight = null;
    private String stimulusResponseOptions = null;
    private boolean randomiseStimuli = false;
    private String buttonLabelEventTag;

    public WizardRandomStimulusScreen() {
        super("RandomStimulus", "RandomStimulus", "RandomStimulus");
    }

    public WizardRandomStimulusScreen(String screenName, boolean centreScreen, String[] screenTextArray, String[] randomStimuliTags, int maxStimuli, final boolean randomiseStimuli, String stimulusCodeMatch, int stimulusDelay, int codeStimulusDelay, String codeFormat, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight, final String spacebar) {
        super(screenName, screenName, screenName);
        this.screenTitle = screenName;
        this.centreScreen = centreScreen;
        this.stimuliPath = "";
        this.stimuliSet = screenTextArray;
        this.stimuliRandomTags = randomStimuliTags;
        this.stimulusCodeMatch = stimulusCodeMatch;
        this.stimulusCodeMsDelay = 0;
        this.stimulusMsDelay = 0;
        this.stimulusCodeFormat = codeFormat;
        this.stimuliCount = maxStimuli;
        this.stimulusResponseLabelLeft = responseOptionsLabelLeft;
        this.stimulusResponseLabelRight = responseOptionsLabelRight;
        this.stimulusResponseOptions = responseOptions;
        this.randomiseStimuli = randomiseStimuli;
        if (spacebar == null) {
            throw new UnsupportedOperationException("button text cannot be null");
        }
        this.buttonLabelEventTag = spacebar;
    }

    public WizardRandomStimulusScreen(String screenName, String[] screenTextArray, int maxStimuli, final boolean randomiseStimuli, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight) {
        super(screenName, screenName, screenName);
        this.screenTitle = screenName;
        this.stimuliPath = "";
        this.stimuliSet = screenTextArray;
        this.stimuliRandomTags = null;
        this.stimulusCodeMatch = null;
        this.stimulusCodeMsDelay = 0;
        this.stimulusMsDelay = 0;
        this.stimulusCodeFormat = null;
        this.stimuliCount = maxStimuli;
        this.stimulusResponseLabelLeft = responseOptionsLabelLeft;
        this.stimulusResponseLabelRight = responseOptionsLabelRight;
        this.stimulusResponseOptions = responseOptions;
        this.randomiseStimuli = randomiseStimuli;
    }

    public String getStimuliPath() {
        return stimuliPath;
    }

    public void setStimuliPath(String stimuliPath) {
        this.stimuliPath = stimuliPath;
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

    public String getStimulusCodeMatch() {
        return stimulusCodeMatch;
    }

    public void setStimulusCodeMatch(String stimulusCodeMatch) {
        this.stimulusCodeMatch = stimulusCodeMatch;
    }

    public int getStimulusCodeMsDelay() {
        return stimulusCodeMsDelay;
    }

    public void setStimulusCodeMsDelay(int stimulusCodeMsDelay) {
        this.stimulusCodeMsDelay = stimulusCodeMsDelay;
    }

    public int getStimulusMsDelay() {
        return stimulusMsDelay;
    }

    public void setStimulusMsDelay(int stimulusMsDelay) {
        this.stimulusMsDelay = stimulusMsDelay;
    }

    public String getStimulusCodeFormat() {
        return stimulusCodeFormat;
    }

    public void setStimulusCodeFormat(String stimulusCodeFormat) {
        this.stimulusCodeFormat = stimulusCodeFormat;
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

    public String getStimulusResponseOptions() {
        return stimulusResponseOptions;
    }

    public void setStimulusResponseOptions(String stimulusResponseOptions) {
        this.stimulusResponseOptions = stimulusResponseOptions;
    }

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
    private static final String BASE_FILE_REGEX = "\\.[a-zA-Z]+$";

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        final List<Stimulus> stimuliList = experiment.getStimuli();
        final Pattern stimulusCodePattern = (stimulusCodeMatch != null) ? Pattern.compile(stimulusCodeMatch) : null;
        if (stimuliSet != null) {
            for (String stimulusLine : stimuliSet) {
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{screenTitle}));
                final Stimulus stimulus;
                if (stimulusCodePattern != null) {
                    System.out.println("stimulusCodeMatch:" + stimulusCodeMatch);
                    Matcher matcher = stimulusCodePattern.matcher(stimulusLine);
                    final String codeString = (matcher.find()) ? matcher.group(1) : null;
                    System.out.println("codeString: " + codeString);
                    final String baseFileName = stimulusLine.replaceAll(BASE_FILE_REGEX, "");
                    tagSet.addAll(Arrays.asList(baseFileName.split("/")));
                    stimulus = new Stimulus(baseFileName, null, null, null, stimulusLine, null, codeString, 0, tagSet);
                } else if (stimulusLine.endsWith(".png")) {
                    tagSet.addAll(Arrays.asList(stimulusLine.split("/")));
                    stimulus = new Stimulus(stimulusLine.replace(".png", ""), null, null, null, stimulusLine, null, stimulusLine.replace(".png", ""), 0, tagSet);
                } else {
                    final String[] splitScreenText = stimulusLine.split(":", 2);
                    tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                    stimulus = new Stimulus(null, null, null, null, null, splitScreenText[1].replace("\n", "<br/>"), splitScreenText[0].replace(" ", "_").replace("/", "_"), 0, tagSet);
                }
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
        final RandomGrouping randomGrouping = new RandomGrouping();
        if (stimuliRandomTags != null) {
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
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(imageFeature);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusMsDelay));
        final PresenterFeature presenterFeature;
        if (stimulusCodeFormat != null) {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
            nextButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(stimulusCodeMsDelay));
            nextButtonFeature.getPresenterFeatureList().add(pauseFeature);
            final PresenterFeature stimulusCodeAudio = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.codeFormat, stimulusCodeFormat);
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
        if (stimulusResponseOptions != null) {
            final PresenterFeature ratingFooterButtonFeature = new PresenterFeature(FeatureType.ratingButton, null);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, stimulusResponseOptions);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, stimulusResponseLabelLeft);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, stimulusResponseLabelRight);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + screenTitle);
            ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
        } else {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, buttonLabelEventTag);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, buttonLabelEventTag);
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + screenTitle);
            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
