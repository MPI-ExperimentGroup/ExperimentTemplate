/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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
package nl.mpi.tg.eg.experimentdesigner.model;

import java.util.ArrayList;
import java.util.Arrays;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureType.*;

/**
 * @since Aug 18, 2015 4:16:06 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum PresenterType {

    transmission(new FeatureType[]{
        endOfStimulusButton,
        localStorageData,
        allMetadataFields,
        eraseLocalStorageButton,
        showCurrentMs,
        enableStimulusButtons,
        disableStimulusButtons,
        showStimulusProgress,
        hideStimulusButtons,
        showStimulusButtons,
        popupMessage,
        stimulusAudio,
        stimulusImage,
        clearStimulus,
        removeStimulus,
        keepStimulus,
        nextStimulus,
        allMenuItems,
        nextStimulusButton,
        autoNextStimulus,
        conditionalHtml,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        showStimulusGrid,
        pause,
        kinTypeStringDiagram,
        loadKinTypeStringDiagram,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        userInfo,
        menuItem
    }),
    metadata(new FeatureType[]{
        localStorageData,
        eraseLocalStorageButton,
        showCurrentMs,
        enableStimulusButtons,
        disableStimulusButtons,
        showStimulusProgress,
        hideStimulusButtons,
        showStimulusButtons,
        generateCompletionCode,
        sendAllData,
        eraseLocalStorageOnWindowClosing,
        clearStimulus,
        removeStimulus,
        keepStimulus,
        nextStimulus,
        allMenuItems,
        nextStimulusButton,
        autoNextStimulus,
        conditionalHtml,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        showStimulusGrid,
        pause,
        onSuccess,
        kinTypeStringDiagram,
        loadKinTypeStringDiagram,
        stimulusImage,
        stimulusAudio,
        endOfStimulusButton,
        popupMessage,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        userInfo,
        menuItem
    }),
    preload(new FeatureType[]{}),
    stimulus(new FeatureType[]{}),
    kindiagram(new FeatureType[]{}),
    menu(new FeatureType[]{}),
    debug(new FeatureType[]{}),
    text(new FeatureType[]{loadKinTypeStringDiagram, localStorageData,
        allMetadataFields,
        eraseLocalStorageButton,
        //            showCurrentMs,
        enableStimulusButtons,
        disableStimulusButtons,
        showStimulusProgress,
        hideStimulusButtons,
        showStimulusButtons,
        generateCompletionCode,
        sendAllData,
        eraseLocalStorageOnWindowClosing,
        clearStimulus,
        removeStimulus,
        keepStimulus,
        nextStimulus,
        allMenuItems,
        nextStimulusButton,
        autoNextStimulus,
        addKinTypeGui,
        //            autoNextPresenter,
        //            logTimeStamp,
        //            audioButton,
        preloadAllStimuli,
        endOfStimulusButton,
        responseCorrect,
        responseIncorrect,
        showCurrentMs,
        conditionalHtml,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        showStimulusGrid,
        pause});

    private final FeatureType[] featureTypes;

    private PresenterType(FeatureType[] excludedFeatureTypes) {
        ArrayList<FeatureType> features = new ArrayList<>();
        features.addAll(Arrays.asList(FeatureType.values()));
        for (FeatureType excludedFeature : excludedFeatureTypes) {
            features.remove(excludedFeature);
        }
        this.featureTypes = features.toArray(new FeatureType[features.size()]);
    }

    public FeatureType[] getFeatureTypes() {
        return featureTypes;
    }
}
