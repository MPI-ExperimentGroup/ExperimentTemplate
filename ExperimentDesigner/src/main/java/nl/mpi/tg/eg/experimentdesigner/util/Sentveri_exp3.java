/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since Dec 2, 2015 1:21:07 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Sentveri_exp3 {

    final String[] stimulusTagList = new String[]{"list1", "list2", "list3", "list4"};

    public void create3c(PresenterScreenRepository presenterScreenRepository, PresenterFeatureRepository presenterFeatureRepository, final List<PresenterScreen> presenterScreenList) {
        PresenterScreen practiceScreen = createStimulusScreen("practice");
        presenterScreenList.add(practiceScreen);
        for (char setChar : new char[]{'a', 'b', 'c'}) {
            for (String tagString : stimulusTagList) {
                String screenName = tagString + "_" + setChar;
                PresenterScreen stimuliSetScreen = createStimulusScreen(screenName);
                presenterScreenList.add(stimuliSetScreen);
            }
        }
    }

    private PresenterScreen createStimulusScreen(String screenName) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, null, screenName + "Screen", null, PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag(screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        //There are 12 sets/lists in total and each participant only responds to one of them.
        //There are 8 practice trials at the beginning of each set, which are fixed.
        //each trial starts with:
        //1. a "cross" for fixation in the center (1000ms or 1ms);
        hasMoreStimulusFeature.getPresenterFeatureList().add(addSentenceFeature(screenName));
        //6. a blank screen (1000ms) before starting the next trial (loop).
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }

    private PresenterFeature addStimulusImage(final String imageWidth, String screenName, String imageSet, final String timeToNext) {
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusCodeImage, null);
        imageFeature.addFeatureAttributes(FeatureAttribute.width, imageWidth);
        imageFeature.addFeatureAttributes(FeatureAttribute.codeFormat, screenName + "/" + imageSet + "/<code>.jpg");
        imageFeature.addFeatureAttributes(FeatureAttribute.timeToNext, timeToNext);
        return imageFeature;
    }

    private PresenterFeature addSentenceFeature(final String screenName) {
        //2. the image of the "sentence" in the center (self-paced - wait till a "spacebar" response, lock out all the other button responses)
        final PresenterFeature sentenceFeature = addStimulusImage("100", screenName, screenName + "_sent", "0");
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.actionButton, "spacebar");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus");
        nextStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        nextStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        nextStimulusFeature.getPresenterFeatureList().add(addImageFeature(screenName));
        sentenceFeature.getPresenterFeatureList().add(nextStimulusFeature);
        return sentenceFeature;
    }

    private PresenterFeature addImageFeature(final String screenName) {
        //3. an arbitrary fast (0ms) or slow (500ms) delay with a blank screen between sentence and picture -defined by the variable "delay"
        //4. the image of the "picture" in the center (self-paced - wait till a "." for yes or a "z" for no response, lock out all the other button responses)
        final PresenterFeature imageFeature = addStimulusImage("30", screenName, "image_" + screenName, "0");
        final PresenterFeature delayFeature = new PresenterFeature(FeatureType.stimulusPause, null);
        imageFeature.getPresenterFeatureList().add(delayFeature);
        final PresenterFeature responseZFeature = new PresenterFeature(FeatureType.actionButton, "z");
        responseZFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        responseZFeature.addFeatureAttributes(FeatureAttribute.eventTag, "responseZ");
        responseZFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        responseZFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        delayFeature.getPresenterFeatureList().add(responseZFeature);
        final PresenterFeature responseDotFeature = new PresenterFeature(FeatureType.actionButton, ".");
        responseDotFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        responseDotFeature.addFeatureAttributes(FeatureAttribute.eventTag, "responseDot");
        responseDotFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        responseDotFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        delayFeature.getPresenterFeatureList().add(responseDotFeature);
        responseDotFeature.getPresenterFeatureList().add(addNextStimulusButtons(screenName));
        responseZFeature.getPresenterFeatureList().add(addNextStimulusButtons(screenName));
        return imageFeature;
    }

    private PresenterFeature addNextStimulusButtons(final String screenName) {
        final PresenterFeature checkTagFeature = new PresenterFeature(FeatureType.currentStimulusHasTag, null);
        checkTagFeature.addFeatureAttributes(FeatureAttribute.timeToNext, "3");
        checkTagFeature.addStimulusTag("question");
        final PresenterFeature withoutTagFeature = new PresenterFeature(FeatureType.withoutTag, null);
        final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.autoNextStimulus, null);
        autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nonquestion");
        autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        withoutTagFeature.getPresenterFeatureList().add(autoNextFeature);
        checkTagFeature.getPresenterFeatureList().add(withoutTagFeature);
        final PresenterFeature hasTagFeature = new PresenterFeature(FeatureType.hasTag, null);
        checkTagFeature.getPresenterFeatureList().add(hasTagFeature);
        //5. on half of the trials (36/72), the image of the "question" in the center (self-paced - wait till a "." for yes or a "z" for no response, lock out all the other button responses) - arbitrarily defined by the variable "QorNOT"
        final PresenterFeature questionFeature = addStimulusImage("100", screenName, screenName + "_Q", "0");
        final PresenterFeature responseZFeature = new PresenterFeature(FeatureType.nextStimulusButton, "z");
        responseZFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        responseZFeature.addFeatureAttributes(FeatureAttribute.eventTag, "responseZ");
        hasTagFeature.getPresenterFeatureList().add(responseZFeature);
        questionFeature.getPresenterFeatureList().add(checkTagFeature);
        final PresenterFeature responseDotFeature = new PresenterFeature(FeatureType.nextStimulusButton, ".");
        responseDotFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        responseDotFeature.addFeatureAttributes(FeatureAttribute.eventTag, "responseDot");
        hasTagFeature.getPresenterFeatureList().add(responseDotFeature);
        return questionFeature;
    }

    public ArrayList<Stimulus> createStimuli() {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();

        for (int index = 0; index < Sentveri_exp3Data.practPictureIndex.length; index++) {
            final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{"practice"}));
            if (Sentveri_exp3Data.practQorNOT[index]) {
                tagSet.add("question");
            }
            final Stimulus stimulus = new Stimulus(null, null, null, null, "prac_" + Sentveri_exp3Data.practPictureIndex[index] + ((Sentveri_exp3Data.practQorNOT[index]) ? "_q" : ""), "" + Sentveri_exp3Data.practPictureIndex[index], (Sentveri_exp3Data.practslow[index]) ? 1000 : 0, tagSet);
            stimuliList.add(stimulus);
        }

        for (char setChar : new char[]{'a', 'b', 'c'}) {
            for (String tagString : stimulusTagList) {
                final boolean[] currendSlow;
                switch (setChar) {
                    case 'a':
                        currendSlow = Sentveri_exp3Data.slowA;
                        break;
                    case 'b':
                        currendSlow = Sentveri_exp3Data.slowB;
                        break;
                    default:
                        currendSlow = Sentveri_exp3Data.slowC;
                }
                for (int index = 0; index < Sentveri_exp3Data.pictureIndex.length; index++) {
                    final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{tagString + "_" + setChar}));
                    if (Sentveri_exp3Data.QorNOT[index]) {
                        tagSet.add("question");
                    }
                    final Stimulus stimulus = new Stimulus(null, null, null, null, tagString + "_" + setChar + "_" + Sentveri_exp3Data.pictureIndex[index] + ((Sentveri_exp3Data.QorNOT[index]) ? "_q" : ""), "" + Sentveri_exp3Data.pictureIndex[index], (currendSlow[index]) ? 1000 : 0, tagSet);
                    stimuliList.add(stimulus);
                }
            }
        }
        return stimuliList;
    }
}
