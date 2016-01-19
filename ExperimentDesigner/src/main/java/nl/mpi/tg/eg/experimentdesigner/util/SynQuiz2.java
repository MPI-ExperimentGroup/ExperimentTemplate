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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Jan 18, 2016 11:20:47 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SynQuiz2 {

    public void create(PresenterScreenRepository presenterScreenRepository, PresenterFeatureRepository presenterFeatureRepository, final List<PresenterScreen> presenterScreenList) {
        PresenterScreen practiceScreen = createStimulusScreen("colourPicker");
        presenterScreenList.add(practiceScreen);
    }

    private PresenterScreen createStimulusScreen(String screenName) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, null, screenName + "Screen", null, PresenterType.colourPicker);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag(screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "true");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, "Menu");
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, "AutoMenu");
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }
}
