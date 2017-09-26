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
        setSdCardStimuli(false);
        this.wizardScreenData.setButtonLabelEventTag("");
        this.wizardScreenData.setCentreScreen(true);
    }

    public WizardGridStimulusScreen(String screenName, boolean centreScreen, String[] stimuliStringArray, String[] randomStimuliTags, int maxStimuli, final boolean randomiseStimuli, String stimulusCodeMatch, int stimulusDelay, int codeStimulusDelay, String codeFormat) {
        super(WizardScreenEnum.WizardGridStimulusScreen, screenName, screenName, screenName);
        this.setScreenTitle(screenName);
        this.wizardScreenData.setStimuliRandomTags(randomStimuliTags);
        this.wizardScreenData.setStimulusCodeMatch(stimulusCodeMatch);
        this.wizardScreenData.setStimulusCodeMsDelay(0);
//        setStimulusMsDelay(0);
        setSdCardStimuli(false);
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
        this.wizardScreenData.setScreenBoolean(1, fullScreenGrid);
    }

    private boolean isRandomiseStimuli(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(0);
    }

    private boolean isFullScreenGrid(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(1);
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
            "SDcard Stimuli"
        }[index];
    }

    @Override
    public String getScreenTextInfo(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Next Button Label"}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{}[index];
    }

    public final void setStimuliSet(String[] stimuliSet) {
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
        for (String stimulusEntry : stimuliSet) {
            stimuliList.add(new Stimulus(stimulusEntry, stimulusEntry, null, null, null, stimulusEntry.substring(0, stimulusEntry.length() - 1), 0, tagSet, null, null));
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
        final PresenterFeature backgoundFeature = new PresenterFeature(FeatureType.backgroundImage, null);
        backgoundFeature.addFeatureAttributes(FeatureAttribute.msToNext, "7000");
//        backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "zoomTo3of6 zoom to house");
        backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "zoomToBlock1");
//        backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "zoomOut");
        backgoundFeature.addFeatureAttributes(FeatureAttribute.src, "background.png");
        presenterFeatureList.add(backgoundFeature);
//        set the image as the parent to subsequent features
        presenterFeatureList = backgoundFeature.getPresenterFeatureList();
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

        final PresenterFeature stimulusCodeAudio = new PresenterFeature(FeatureType.stimulusAudio, null);
        stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.showPlaybackIndicator, Boolean.toString(true));
//        stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>");
        stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        hasMoreStimulusFeature.getPresenterFeatureList().add(stimulusCodeAudio);
        final PresenterFeature stimulusCodeVideoL = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>L");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.maxHeight, "100");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.maxWidth, "100");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.showControls, "false");
//        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.styleName, "leftHalfScreen");
        stimulusCodeVideoL.addFeatureAttributes(FeatureAttribute.styleName, "");
        final PresenterFeature stimulusCodeVideoR = new PresenterFeature(FeatureType.stimulusCodeVideo, null);
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.codeFormat, "<code>R");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.maxHeight, "100");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.maxWidth, "100");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.showControls, "false");
//        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.styleName, "rightHalfScreen");
        stimulusCodeVideoR.addFeatureAttributes(FeatureAttribute.styleName, "");
//        stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        final PresenterFeature tableFeature = new PresenterFeature(FeatureType.table, null);
        stimulusCodeAudio.getPresenterFeatureList().add(tableFeature);
        final PresenterFeature rowFeature = new PresenterFeature(FeatureType.row, null);
        tableFeature.getPresenterFeatureList().add(rowFeature);
        final PresenterFeature leftColumnFeature = new PresenterFeature(FeatureType.column, null);
        rowFeature.getPresenterFeatureList().add(leftColumnFeature);
        final PresenterFeature rightColumnFeature = new PresenterFeature(FeatureType.column, null);
        rowFeature.getPresenterFeatureList().add(rightColumnFeature);
        leftColumnFeature.getPresenterFeatureList().add(stimulusCodeVideoL);
        rightColumnFeature.getPresenterFeatureList().add(stimulusCodeVideoR);
        final PresenterFeature touchInputZoneL = new PresenterFeature(FeatureType.touchInputZone, null);
        touchInputZoneL.addFeatureAttributes(FeatureAttribute.eventTag, "leftHalfScreen");
        touchInputZoneL.addFeatureAttributes(FeatureAttribute.styleName, "leftHalfScreen");
        touchInputZoneL.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
        stimulusCodeVideoR.getPresenterFeatureList().add(touchInputZoneL);
        final PresenterFeature touchInputZoneR = new PresenterFeature(FeatureType.touchInputZone, null);
        touchInputZoneR.addFeatureAttributes(FeatureAttribute.eventTag, "rightHalfScreen");
        touchInputZoneR.addFeatureAttributes(FeatureAttribute.styleName, "rightHalfScreen");
        touchInputZoneR.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
        stimulusCodeVideoR.getPresenterFeatureList().add(touchInputZoneR);
        final PresenterFeature nextStimulusL = new PresenterFeature(FeatureType.nextStimulus, null);
//        nextStimulusL.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusL");
        nextStimulusL.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
        touchInputZoneL.getPresenterFeatureList().add(nextStimulusL);
        final PresenterFeature nextStimulusR = new PresenterFeature(FeatureType.nextStimulus, null);
//        nextStimulusR.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusR");
        nextStimulusR.addFeatureAttributes(FeatureAttribute.repeatIncorrect, "false");
        touchInputZoneR.getPresenterFeatureList().add(nextStimulusR);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
