/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics
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
 * @since Mar 23, 2017 3:12:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardGridStimulusScreen extends AbstractWizardScreen {

    public WizardGridStimulusScreen() {
        super(WizardScreenEnum.WizardGridStimulusScreen, "GridStimulusScreen", "GridStimulusScreen", "GridStimulusScreen");
        setRandomiseStimuli(false);
        this.wizardScreenData.setStimuliCount(1);
//        setStimulusMsDelay(0);
        setFullScreenGrid(false);
        setCodeAudio(false);
        setSdCardStimuli(false);
        setIntroAudio(null);
        setRewardImage(null);
        setIntroAudioDelay(0);
        this.wizardScreenData.setButtonLabelEventTag("");
        this.wizardScreenData.setCentreScreen(true);
    }

    public WizardGridStimulusScreen(String screenName, boolean centreScreen, String[][] stimuliStringArray, String[] randomStimuliTags, int maxStimuli, final boolean randomiseStimuli, String stimulusCodeMatch, int stimulusDelay, int codeStimulusDelay, String codeFormat) {
        super(WizardScreenEnum.WizardGridStimulusScreen, screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.wizardScreenData.setStimuliRandomTags(randomStimuliTags);
        this.wizardScreenData.setStimulusCodeMatch(stimulusCodeMatch);
        this.wizardScreenData.setStimulusCodeMsDelay(0);
//        setStimulusMsDelay(0);
        setFullScreenGrid(false);
        setSdCardStimuli(false);
        setCodeAudio(false);
        setIntroAudio(null);
        setRewardImage(null);
        setIntroAudioDelay(0);
        this.wizardScreenData.setStimulusCodeFormat(codeFormat);
        this.wizardScreenData.setStimuliCount(maxStimuli);
        setRandomiseStimuli(randomiseStimuli);
        this.wizardScreenData.setCentreScreen(centreScreen);
        setStimuliSet(stimuliStringArray);
    }

    final public void setRandomiseStimuli(boolean randomiseStimuli) {
        this.wizardScreenData.setScreenBoolean(0, randomiseStimuli);
    }

    final public void setFullScreenGrid(boolean fullScreenGrid) {
        this.wizardScreenData.setScreenBoolean(2, fullScreenGrid);
    }

    final public void setCodeAudio(boolean stimulusCodeAudio) {
        this.wizardScreenData.setScreenBoolean(3, stimulusCodeAudio);
    }

    private boolean isRandomiseStimuli(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(0);
    }

    private boolean isFullScreenGrid(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(2);
    }

    private boolean isCodeAudio(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(3);
    }

    private String getBackgroundImage(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(0);
    }

    public void setBackgroundImage(String backgroundImage) {
        this.wizardScreenData.setScreenText(0, backgroundImage);
    }

    final public void setIntroAudioDelay(int initialAudioDelay) {
        this.wizardScreenData.setScreenIntegers(0, initialAudioDelay);
    }

    final public void setIntroAudio(String initialAudio) {
        this.wizardScreenData.setScreenText(2, initialAudio);
    }

    private String getRewardImage(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(3);
    }

    final public void setRewardImage(String rewardImage) {
        this.wizardScreenData.setScreenText(3, rewardImage);
    }

    private String getBackgroundStyle(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(1);
    }

    private String getIntroAudio(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(2);
    }

    private int getIntroAudioDelay(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenInteger(0);
    }

    public void setBackgroundStyle(String backgroundStyle) {
        this.wizardScreenData.setScreenText(1, backgroundStyle);
    }

    final public void setSdCardStimuli(boolean sdCardStimuli) {
        this.wizardScreenData.setScreenBoolean(1, sdCardStimuli);
    }

    private boolean isSdCardStimuli(WizardScreenData wizardScreenData) {
        return wizardScreenData.getScreenBoolean(1);
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        return new String[]{
            "Randomise Stimuli",
            "Full Screen Grid",
            "SDcard Stimuli",
            "StimulusCodeAudio"
        }[index];
    }

    @Override
    public String getScreenTextInfo(int index) {
        return new String[]{
            "BackgroundImage",
            "BackgroundStyle",
            "IntroAudio",
            "RewardImage"
        }[index];
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Next Button Label"}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{"IntroAudioDelay"}[index];
    }

    public final void setStimuliSet(String[][] stimuliSet) {
//        String[] tempStimuli = {
//            "filler_1_1", "intro_1", "test_2_2", "test_6_2", "training_2_2",
//            "filler_1_2", "intro_2", "test_2_3", "test_6_3", "training_2_3",
//            "filler_1_3", "intro_3", "test_3_1", "test_7_1", "training_3_1",
//            "filler_2_1", "room_1", "test_3_2", "test_7_2", "training_3_2",
//            "filler_2_2", "room_2", "test_3_3", "test_7_3", "training_3_3",
//            "filler_2_3", "room_3", "test_4_1", "test_8_1", "training_4_1",
//            "filler_3_1", "room_4", "test_4_2", "test_8_2", "training_4_2",
//            "filler_3_2", "room_5", "test_4_3", "test_8_3", "training_4_3",
//            "filler_3_3", "test_1_1", "test_5_1", "training_1_1",
//            "filler_4_1", "test_1_2", "test_5_2", "training_1_2",
//            "filler_4_2", "test_1_3", "test_5_3", "training_1_3",
//            "filler_4_3", "test_2_1", "test_6_1", "training_2_1",};
        if (this.wizardScreenData.getStimuli() == null) {
            this.wizardScreenData.setStimuli(new ArrayList<>());
        }
        final List<Stimulus> stimuliList = this.wizardScreenData.getStimuli();
        final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{this.wizardScreenData.getScreenTitle()}));
        for (String[] stimulusEntry : stimuliSet) {
            // @todo: perhaps this is clearer if there is an explicit adjacency regex used on the identifyer in the GUI app or an explicit adjacency / sort field in the stimuli
//            final String adjacencyString = stimulusEntry.replaceAll("test_[15]", "adjacency_a").replaceAll("test_[26]", "adjacency_b").replaceAll("test_[37]", "adjacency_c").replaceAll("test_[48]", "adjacency_d");
            stimuliList.add(new Stimulus(stimulusEntry[0], stimulusEntry[0], null, stimulusEntry[1], null, stimulusEntry[0] /*.substring(0, stimulusEntry.length() - 1)*/, 0, tagSet, null, null));
        }
        // add screen text
        // add grid with full screen option
        // add stimulus to the grid and add distractors by using the rating options as the stimuli id selector
        // add code audio
        // next stimulis based on grid click
        //        if (this.wizardScreenData.getStimuli() == null) {
        //            this.wizardScreenData.setStimuli(new ArrayList<>());
        //        }
        //        final List<Stimulus> stimuliList = this.wizardScreenData.getStimuli();
        //        final Pattern stimulusCodePattern = (wizardScreenData.getStimulusCodeMatch() != null) ? Pattern.compile(wizardScreenData.getStimulusCodeMatch()) : null;
        //        if (stimuliSet != null) {
        //            for (String stimulusLine : stimuliSet) {
        //                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{this.wizardScreenData.getScreenTitle()}));
        //                final Stimulus stimulus;
        //                if (stimulusCodePattern != null) {
        //                    System.out.println("stimulusCodeMatch:" + wizardScreenData.getStimulusCodeMatch());
        //                    Matcher matcher = stimulusCodePattern.matcher(stimulusLine);
        //                    final String codeString = (matcher.find()) ? matcher.group(1) : null;
        //                    System.out.println("codeString: " + codeString);
        //                    final String baseFileName = stimulusLine.replaceAll(BASE_FILE_REGEX, "");
        //                    tagSet.addAll(Arrays.asList(baseFileName.split("/")));
        //                    stimulus = new Stimulus(baseFileName, null, null, stimulusLine, null, codeString, 0, tagSet, null, null);
        //                } else if (stimulusLine.endsWith(".png")) {
        //                    tagSet.addAll(Arrays.asList(stimulusLine.split("/")));
        //                    stimulus = new Stimulus(stimulusLine.replace(".png", ""), null, null, stimulusLine, null, null, 0, tagSet, null, null);
        //                } else {
        //                    final String[] splitScreenText = stimulusLine.split(":", 2);
        //                    tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
        //                    final String substring = (stimulusLine.length() < 55) ? stimulusLine : stimulusLine.substring(0, 54);
        //                    stimulus = new Stimulus(substring, null, null, null, splitScreenText[1].replace("\n", "<br/>"), null/*splitScreenText[0].replace(" ", "_").replace("/", "_")*/, 0, tagSet, null, null);
        //                }
        //                stimuliList.add(stimulus);
        //            }
        //        }
    }

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment,
            boolean obfuscateScreenNames, long displayOrder
    ) {
        experiment.appendUniqueStimuli(storedWizardScreenData.getStimuli());
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = storedWizardScreenData.getPresenterScreen().getPresenterFeatureList();
        if (storedWizardScreenData.isCentreScreen()) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        }
        if (getBackgroundImage(storedWizardScreenData) != null) {
            final PresenterFeature backgoundFeature = new PresenterFeature(FeatureType.backgroundImage, null);
            backgoundFeature.addFeatureAttributes(FeatureAttribute.msToNext, "3000");
//        backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "zoomTo3of6 zoom to house");
            backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, getBackgroundStyle(storedWizardScreenData));
//        backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "zoomOut");
            backgoundFeature.addFeatureAttributes(FeatureAttribute.src, getBackgroundImage(storedWizardScreenData));
            presenterFeatureList.add(backgoundFeature);
//        set the image as the parent to subsequent features
            presenterFeatureList = backgoundFeature.getPresenterFeatureList();
        }
        if (getIntroAudio(storedWizardScreenData) != null) {
            final PresenterFeature introAudioFeature = new PresenterFeature(FeatureType.audioButton, null);
            introAudioFeature.addFeatureAttributes(FeatureAttribute.eventTag, getIntroAudio(storedWizardScreenData));
            introAudioFeature.addFeatureAttributes(FeatureAttribute.src, getIntroAudio(storedWizardScreenData));
            introAudioFeature.addFeatureAttributes(FeatureAttribute.poster, "intro_1.jpg");
            introAudioFeature.addFeatureAttributes(FeatureAttribute.autoPlay, Boolean.toString(true));
            introAudioFeature.addFeatureAttributes(FeatureAttribute.hotKey, "R1_MA_A");
            introAudioFeature.addFeatureAttributes(FeatureAttribute.styleName, "titleBarButton");
            presenterFeatureList.add(introAudioFeature);
            presenterFeatureList = introAudioFeature.getPresenterFeatureList();
        }
        if (getIntroAudioDelay(storedWizardScreenData) > 0) {
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(getIntroAudioDelay(storedWizardScreenData)));
            presenterFeatureList.add(pauseFeature);
            presenterFeatureList = pauseFeature.getPresenterFeatureList();
        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature((!isSdCardStimuli(storedWizardScreenData)) ? FeatureType.loadStimulus : FeatureType.loadSdCardStimulus, null);
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
//        if (isSdCardStimuli(storedWizardScreenData)) {
//            loadStimuliFeature.addFeatureAttributes(FeatureAttribute.excludeRegex, ".*_question\\....$");
//        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(isRandomiseStimuli(storedWizardScreenData)));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, "0");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        final PresenterFeature backgoundFeatureRemove = new PresenterFeature(FeatureType.backgroundImage, null);
        backgoundFeatureRemove.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        backgoundFeatureRemove.addFeatureAttributes(FeatureAttribute.styleName, "");
        backgoundFeatureRemove.addFeatureAttributes(FeatureAttribute.src, "");
        hasMoreStimulusFeature.getPresenterFeatureList().add(backgoundFeatureRemove);
        final PresenterFeature clearScreenFeature = new PresenterFeature(FeatureType.clearPage, null);
        clearScreenFeature.addFeatureAttributes(FeatureAttribute.styleName, "fullScreenWidth");
        hasMoreStimulusFeature.getPresenterFeatureList().add(clearScreenFeature);

        final PresenterFeature stimulusRelatedTags;
        if (isCodeAudio(storedWizardScreenData)) {
            final PresenterFeature stimulusCodeAudio1 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio1.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
            stimulusCodeAudio1.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>_1");
            stimulusCodeAudio1.addFeatureAttributes(FeatureAttribute.msToNext, "1000");
            hasMoreStimulusFeature.getPresenterFeatureList().add(stimulusCodeAudio1);
            final PresenterFeature stimulusCodeAudio2 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio2.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
            stimulusCodeAudio2.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>_2");
            stimulusCodeAudio2.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            stimulusCodeAudio1.getPresenterFeatureList().add(stimulusCodeAudio2);
            final PresenterFeature stimulusCodeAudio3 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio3.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
            stimulusCodeAudio3.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>_3");
            stimulusCodeAudio3.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            final PresenterFeature stimulusCodeVideoL = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>_L");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.maxHeight, "100");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.maxWidth, "100");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.showControls, "false");
//        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.styleName, "leftHalfScreen");
            stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.styleName, "borderedVideoLeft");
            final PresenterFeature stimulusCodeVideoR = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>_R");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.maxHeight, "100");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.maxWidth, "100");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.showControls, "false");
//        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.styleName, "rightHalfScreen");
            stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.styleName, "borderedVideoRight");
            stimulusCodeVideoR.getPresenterFeatureList().add(stimulusCodeAudio3);
//        stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
//        final PresenterFeature tableFeature = new PresenterFeature(FeatureType.table, null);
//        stimulusCodeAudio1.getPresenterFeatureList().add(tableFeature);
//        final PresenterFeature rowFeature = new PresenterFeature(FeatureType.row, null);
//        tableFeature.getPresenterFeatureList().add(rowFeature);
//        final PresenterFeature leftColumnFeature = new PresenterFeature(FeatureType.column, null);
//        rowFeature.getPresenterFeatureList().add(leftColumnFeature);
//        final PresenterFeature middleColumnFeature = new PresenterFeature(FeatureType.column, null);
//        final PresenterFeature middlePadding = new PresenterFeature(FeatureType.htmlText, "&nbsp;");
//        middleColumnFeature.addFeatureAttributes(FeatureAttribute.styleName, "middlePadding");
//        middleColumnFeature.getPresenterFeatureList().add(middlePadding);
//        rowFeature.getPresenterFeatureList().add(middleColumnFeature);
//        final PresenterFeature rightColumnFeature = new PresenterFeature(FeatureType.column, null);
//        rowFeature.getPresenterFeatureList().add(rightColumnFeature);
            stimulusCodeAudio1.getPresenterFeatureList().add(stimulusCodeVideoL);
            stimulusCodeAudio1.getPresenterFeatureList().add(stimulusCodeVideoR);
            stimulusRelatedTags = stimulusCodeAudio3;

            final PresenterFeature touchInputCaptureStart = new PresenterFeature(FeatureType.touchInputCaptureStart, null);
            touchInputCaptureStart.addFeatureAttributes(FeatureAttribute.showControls, "true");
            hasMoreStimulusFeature.getPresenterFeatureList().add(touchInputCaptureStart);
//        final PresenterFeature nextStimulusL = new PresenterFeature(FeatureType.nextStimulus, null);
//        nextStimulusL.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusL");
//        nextStimulusL.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
//        touchInputZoneL.getPresenterFeatureList().add(nextStimulusL);
            final PresenterFeature leftOverlayButton = new PresenterFeature(FeatureType.touchInputStimulusButton, "Left Overlay Button");
            leftOverlayButton.addFeatureAttributes(FeatureAttribute.eventTag, "Left");
            leftOverlayButton.addFeatureAttributes(FeatureAttribute.styleName, "leftOverlayButton");
            leftOverlayButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.disableStimulusButtons, null));
            final PresenterFeature responseAudio1 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            responseAudio1.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
            responseAudio1.addFeatureAttributes(FeatureAttribute.codeFormat, "Correct");
            responseAudio1.addFeatureAttributes(FeatureAttribute.msToNext, "500");
            responseAudio1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.enableStimulusButtons, null));
            leftOverlayButton.getPresenterFeatureList().add(responseAudio1);
            stimulusRelatedTags.getPresenterFeatureList().add(leftOverlayButton);
            final PresenterFeature rightOverlayButton = new PresenterFeature(FeatureType.touchInputStimulusButton, "Right Overlay Button");
            rightOverlayButton.addFeatureAttributes(FeatureAttribute.eventTag, "Right");
            rightOverlayButton.addFeatureAttributes(FeatureAttribute.styleName, "rightOverlayButton");
            rightOverlayButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.disableStimulusButtons, null));
            final PresenterFeature responseAudio2 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            responseAudio2.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
            responseAudio2.addFeatureAttributes(FeatureAttribute.codeFormat, "Correct");
            responseAudio2.addFeatureAttributes(FeatureAttribute.msToNext, "500");
            responseAudio2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.enableStimulusButtons, null));
            rightOverlayButton.getPresenterFeatureList().add(responseAudio2);
            stimulusRelatedTags.getPresenterFeatureList().add(rightOverlayButton);
        } else {
            for (String[] additionString : new String[][]{{"<code>_left.jpg", "borderedVideoLeft", "Left Overlay Button", "Left", "leftOverlayButton"}, {"<code>_right.jpg", "borderedVideoRight", "Right Overlay Button", "Right", "rightOverlayButton"}}) {
                final PresenterFeature stimulusImage = hasMoreStimulusFeature.addFeature(FeatureType.stimulusCodeImage, null, "250", additionString[0], additionString[1]);
                final PresenterFeature stimulusAudio = stimulusImage.addFeature(FeatureType.stimulusAudio, null, "0", Boolean.toString(false));
                stimulusAudio.addFeature(FeatureType.backgroundImage, null, "0", "", "backgroundHighlight");
                final PresenterFeature leftOverlayButton = stimulusAudio.addFeature(FeatureType.touchInputStimulusButton, additionString[2], additionString[3], "", additionString[4]);
                leftOverlayButton.addFeature(FeatureType.disableStimulusButtons, null);
                leftOverlayButton.addFeature(FeatureType.disablePauseTimers, null);
                final PresenterFeature pause = leftOverlayButton.addFeature(FeatureType.pause, null, "1000");
                pause.addFeature(FeatureType.clearPage, null);
                if (getRewardImage(storedWizardScreenData) != null) {
                    pause.addFeature(FeatureType.backgroundImage, null, "0", getRewardImage(storedWizardScreenData), "");
                }
                final PresenterFeature stimulusCodeAudio = pause.addFeature(FeatureType.stimulusCodeAudio, null, "500", "Correct", "false");
                stimulusCodeAudio.addFeature(FeatureType.touchInputReportSubmit, null);
                stimulusCodeAudio.addFeature(FeatureType.nextStimulus, null, "false");
                final PresenterFeature pause2 = stimulusAudio.addFeature(FeatureType.pause, null, "3000");
                pause2.addFeature(FeatureType.showStimulus, null);
            }
            stimulusRelatedTags = hasMoreStimulusFeature;

//                                        <touchInputStimulusButton featureText="Left Overlay Button" eventTag="Left" styleName="leftOverlayButton">
//                                <disableStimulusButtons/>
//                                <disablePauseTimers/>
//                                <pause msToNext="1000">
//                                    <clearPage/>
//                                    <backgroundImage msToNext="0" src="P0.png" styleName=""/>
//                                    <stimulusCodeAudio msToNext="500" codeFormat="Correct" showPlaybackIndicator="false">
//                                        <touchInputReportSubmit/>
//                                        <nextStimulus repeatIncorrect="false"/>
//                                    </stimulusCodeAudio>
//                                </pause>
//                            </touchInputStimulusButton>
//                            <pause msToNext="3000">
//                                <showStimulus/>
//                            </pause>
//            hasMoreStimulusFeature.addFeature(FeatureType.htmlText, "&nbsp;");
            hasMoreStimulusFeature.addFeature(FeatureType.touchInputCaptureStart, null, "false", "-1");
//            final PresenterFeature rightOverlayButton = new PresenterFeature(FeatureType.touchInputStimulusButton, "Right Overlay Button");
//            rightOverlayButton.addFeatureAttributes(FeatureAttribute.eventTag, "Right");
//            rightOverlayButton.addFeatureAttributes(FeatureAttribute.styleName, "rightOverlayButton");
//            rightOverlayButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.disableStimulusButtons, null));
//            final PresenterFeature responseAudio2 = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
//            responseAudio2.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(false));
//            responseAudio2.addFeatureAttributes(FeatureAttribute.codeFormat, "Correct");
//            responseAudio2.addFeatureAttributes(FeatureAttribute.msToNext, "500");
//            responseAudio2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.enableStimulusButtons, null));
//            rightOverlayButton.getPresenterFeatureList().add(responseAudio2);
//            stimulusRelatedTags.getPresenterFeatureList().add(rightOverlayButton);

        }

        final PresenterFeature repeatStimulusButton = new PresenterFeature(FeatureType.actionButton, "Repeat");
        repeatStimulusButton.addFeatureAttributes(FeatureAttribute.eventTag, "Repeat");
        repeatStimulusButton.addFeatureAttributes(FeatureAttribute.hotKey, "R1_MA_A");
        final PresenterFeature repeatStimulus = new PresenterFeature(FeatureType.showStimulus, null);
        repeatStimulusButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.touchInputReportSubmit, null));
        repeatStimulusButton.getPresenterFeatureList().add(repeatStimulus);
        final PresenterFeature nextStimulusButton = new PresenterFeature(FeatureType.actionButton, "Next");
        nextStimulusButton.addFeatureAttributes(FeatureAttribute.eventTag, "Next");
        nextStimulusButton.addFeatureAttributes(FeatureAttribute.hotKey, "ENTER");
        final PresenterFeature nextStimulus = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulus.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
        nextStimulusButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.touchInputReportSubmit, null));
        nextStimulusButton.getPresenterFeatureList().add(nextStimulus);
        final PresenterFeature tableFeature = new PresenterFeature(FeatureType.table, null);
        tableFeature.addFeatureAttributes(FeatureAttribute.styleName, "titleBarButton");
        stimulusRelatedTags.getPresenterFeatureList().add(tableFeature);
        final PresenterFeature rowFeature = new PresenterFeature(FeatureType.row, null);
        tableFeature.getPresenterFeatureList().add(rowFeature);
        final PresenterFeature leftColumnFeature = new PresenterFeature(FeatureType.column, null);
        rowFeature.getPresenterFeatureList().add(leftColumnFeature);
//        final PresenterFeature middleColumnFeature = new PresenterFeature(FeatureType.column, null);
//        final PresenterFeature middlePadding = new PresenterFeature(FeatureType.htmlText, "&nbsp;");
//        middleColumnFeature.getPresenterFeatureList().add(middlePadding);
//        rowFeature.getPresenterFeatureList().add(middleColumnFeature);
        final PresenterFeature rightColumnFeature = new PresenterFeature(FeatureType.column, null);
        rowFeature.getPresenterFeatureList().add(rightColumnFeature);
        leftColumnFeature.getPresenterFeatureList().add(repeatStimulusButton);
        rightColumnFeature.getPresenterFeatureList().add(nextStimulusButton);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
