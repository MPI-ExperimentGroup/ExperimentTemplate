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
        for (char setChar : new char[]{'a', 'b', 'c'}) {
            for (String tagString : stimulusTagList) {
                String screenName = tagString + "_" + setChar;
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
                final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
                imageFeature.addFeatureAttributes(FeatureAttribute.width, "100");
                imageFeature.addFeatureAttributes(FeatureAttribute.timeToNext, "0");
                hasMoreStimulusFeature.getPresenterFeatureList().add(imageFeature);
                hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
                final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulusButton, "next stimulus");
                nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus");
                imageFeature.getPresenterFeatureList().add(nextStimulusFeature);
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
//                    for (String qOrA : new String[]{"image_list1_a", "list1_a_Q", "list1_a_sent"}) {
                    final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{tagString + "_" + setChar}));
                    final Stimulus stimulus = new Stimulus(null, null, null, null, tagString + "_" + setChar + "_" + Sentveri_exp3Data.pictureIndex[index] + "_" + ((Sentveri_exp3Data.QorNOT[index]) ? "q" : "a"), "" + Sentveri_exp3Data.pictureIndex[index], (currendSlow[index]) ? 1000 : 1, tagSet);
                    stimuliList.add(stimulus);
//                    }
                }
            }
        }
        return stimuliList;
    }
}
