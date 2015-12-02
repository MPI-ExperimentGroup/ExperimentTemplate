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
    boolean[] slow3c = new boolean[]{false, false, false, false, true, true, true, true, false, false, true, false, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, true, true, false, true, true, true, false, true, false, false, false, false, true, false, false, false, true, true, false, false, true, false, false, false, false, false, false, false, true, false};
    int[] pictureIndex = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72};

    public PresenterScreen create3c(PresenterScreenRepository presenterScreenRepository, PresenterFeatureRepository presenterFeatureRepository) {
        String name = "Sentveri_exp3";
        final PresenterScreen presenterScreen = new PresenterScreen(name, name, null, name + "Screen", null, PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        for (int index = 0; index < pictureIndex.length; index++) {
//            presenterFeatureList.add(new PresenterFeature(FeatureType.clearPage, null));
//            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
//            final PresenterFeature imageFeature = new PresenterFeature(FeatureType.image, null);
//            imageFeature.addFeatureAttributes(FeatureAttribute.src, index + ".jpg");
//            imageFeature.addFeatureAttributes(FeatureAttribute.width, "70");
//            presenterFeatureList.add(imageFeature);
//            final PresenterFeature delayFeature = new PresenterFeature(FeatureType.pause, null);
//            delayFeature.addFeatureAttributes(FeatureAttribute.timeToNext, (slow3c[index]) ? "1000" : "50");
//            presenterFeatureList.add(delayFeature);
//            presenterFeatureRepository.save(presenterFeatureList);
//            final PresenterFeature nextFeature = new PresenterFeature(FeatureType.actionButton, "index " + index);
//            delayFeature.getPresenterFeatureList().add(nextFeature);
//            presenterFeatureRepository.save(delayFeature.getPresenterFeatureList());
//            presenterFeatureList = nextFeature.getPresenterFeatureList();
//        }
        return presenterScreen;
    }

    public ArrayList<Stimulus> createStimuli() {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final HashSet<String> tagSet = new HashSet<>();

        tagSet.add("videotag");
        for (int index = 0; index < pictureIndex.length; index++) {
            final Stimulus stimulus = new Stimulus(null, null, null, "index" + index + ".png", "index " + index, (slow3c[index]) ? 1000 : 1, tagSet);
            stimuliList.add(stimulus);
        }
        return stimuliList;
    }
}
