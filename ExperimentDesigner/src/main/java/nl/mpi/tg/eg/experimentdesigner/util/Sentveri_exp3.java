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

    int[] QorNOT = new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0};
    boolean[] slowA = new boolean[]{false, false, false, false, true, true, true, true, false, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, true, true, true, true, false, false, true, false, true, false, false, false, true, false, true, true, true, false, false, true, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, true, false, false, false, true, true, true, true, false, true, false, false};
    boolean[] slowB = new boolean[]{false, false, false, false, true, true, true, true, true, false, false, true, false, false, true, false, false, false, true, false, true, false, true, false, false, false, false, false, true, true, false, true, false, false, false, false, false, true, true, false, true, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, true, false, true, false, true, true, false, true, false, true, false, false, false, true, false, true, false, false, false, false, true, false, false, true};
    boolean[] slowC = new boolean[]{false, false, false, false, true, true, true, true, false, false, true, false, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, true, true, false, true, true, true, false, true, false, false, false, false, true, false, false, false, true, true, false, false, true, false, false, false, false, false, false, false, true, false};
    int[] pictureIndex = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72};

    final String[] stimulusTagList = new String[]{"list1", "list2", "list3", "list4"};

    public void create3c(PresenterScreenRepository presenterScreenRepository, PresenterFeatureRepository presenterFeatureRepository, final List<PresenterScreen> presenterScreenList) {
        for (char setChar : new char[]{'a', 'b', 'c'}) {
            for (String tagString : stimulusTagList) {
                String screenName = tagString + setChar;
                final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, null, screenName + "Screen", null, PresenterType.stimulus);
                List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
                final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
                loadStimuliFeature.addStimulusTag(screenName);
                loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
                loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
                loadStimuliFeature.addFeatureAttributes(FeatureAttribute.norepeat, "false");
                presenterFeatureList.add(loadStimuliFeature);
//        final PresenterFeature showStimulusFeature = new PresenterFeature(FeatureType.showStimulus, null);
                final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

//        presenterFeatureList.add(new PresenterFeature(FeatureType.clearPage, null));
                hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
                hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
                final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulusButton, "next stimulus");
                nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus");
                hasMoreStimulusFeature.getPresenterFeatureList().add(nextStimulusFeature);
//        presenterFeatureRepository.save(hasMoreStimulusFeature.getPresenterFeatureList());
                loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
                final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
                endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
//        presenterFeatureRepository.save(endOfStimulusFeature.getPresenterFeatureList());
                loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
//        presenterFeatureList.add(showStimulusFeature);
//        presenterFeatureList.add(new PresenterFeature(FeatureType.clearPage, null));
//        presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
//        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.image, null);
//        imageFeature.addFeatureAttributes(FeatureAttribute.src, index + ".jpg");
//        imageFeature.addFeatureAttributes(FeatureAttribute.width, "70");
//        presenterFeatureList.add(imageFeature);
//        final PresenterFeature delayFeature = new PresenterFeature(FeatureType.pause, null);
//        delayFeature.addFeatureAttributes(FeatureAttribute.timeToNext, (slow3c[index]) ? "1000" : "50");
//        presenterFeatureList.add(delayFeature);
//        presenterFeatureRepository.save(presenterFeatureList);
//        final PresenterFeature nextFeature = new PresenterFeature(FeatureType.actionButton, "index " + index);
//        delayFeature.getPresenterFeatureList().add(nextFeature);
//        presenterFeatureRepository.save(delayFeature.getPresenterFeatureList());
//        presenterFeatureList = nextFeature.getPresenterFeatureList();
                presenterScreenList.add(presenterScreen);
            }
        }
    }

    public ArrayList<Stimulus> createStimuli() {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();
        for (char setChar : new char[]{'a', 'b', 'c'}) {
            for (String tagString : stimulusTagList) {
                tagSet.clear();
                final boolean[] currendSlow;
                switch (setChar) {
                    case 'a':
                        currendSlow = slowA;
                        break;
                    case 'b':
                        currendSlow = slowB;
                        break;
                    default:
                        currendSlow = slowC;
                }
                tagSet.add(tagString + setChar);
                for (int index = 0; index < pictureIndex.length; index++) {
                    final Stimulus stimulus = new Stimulus(null, null, null, "index" + index + ".png", "index " + index, (currendSlow[index]) ? 1000 : 1, tagSet);
                    stimuliList.add(stimulus);
                }
            }
        }
        return stimuliList;
    }
}
