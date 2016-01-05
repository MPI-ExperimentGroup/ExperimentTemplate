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
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        localStorageData,
        allMetadataFields, createUserButton, selectUserMenu,
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
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        showStimulusGrid,
        showStimulus,
        pause,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        userInfo,
        menuItem
    }),
    metadata(new FeatureType[]{
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
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
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        showStimulus,
        showStimulusGrid,
        pause,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        stimulusImage,
        stimulusAudio,
        popupMessage,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        userInfo,
        menuItem
    }),
    preload(new FeatureType[]{
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        localStorageData,
        allMetadataFields, createUserButton, selectUserMenu,
        eraseLocalStorageButton,
        generateCompletionCode,
        sendAllData,
        eraseLocalStorageOnWindowClosing,
        allMenuItems,
        nextStimulusButton,
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        autoNextPresenter,
        audioButton,
        userInfo,
        menuItem,
        showStimulus,
        showStimulusGrid,
        stimulusPause,
        stimulusLabel,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        popupMessage,
        pause,
        responseCorrect, responseIncorrect, hasMoreStimulus, endOfStimulus}),
    stimulus(new FeatureType[]{responseCorrect, responseIncorrect, hasMoreStimulus, endOfStimulus,
        localStorageData,
        userInfo,
        allMetadataFields, createUserButton, selectUserMenu,
        eraseLocalStorageButton,
        generateCompletionCode,
        sendAllData,
        eraseLocalStorageOnWindowClosing,
        allMenuItems,
        menuItem,
        addKinTypeGui,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        preloadAllStimuli}),
    kindiagram(new FeatureType[]{responseCorrect, responseIncorrect, hasMoreStimulus, endOfStimulus,
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        popupMessage,
        localStorageData,
        allMetadataFields, createUserButton, selectUserMenu,
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
        menuItem,
        AnnotationTimelinePanel,
        nextStimulusButton,
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        showStimulus,
        showStimulusGrid,
        pause,
        onError,
        onSuccess,
        userInfo,
        stimulusImage,
        stimulusAudio
    }),
    menu(new FeatureType[]{responseCorrect, responseIncorrect, hasMoreStimulus, endOfStimulus,
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        popupMessage,
        localStorageData,
        allMetadataFields, createUserButton, selectUserMenu,
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
        nextStimulusButton,
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        showStimulus,
        showStimulusGrid,
        pause,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        userInfo,
        stimulusImage,
        stimulusAudio}),
    debug(new FeatureType[]{responseCorrect, responseIncorrect, hasMoreStimulus, endOfStimulus,
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        popupMessage,
        allMetadataFields, createUserButton, selectUserMenu,
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
        menuItem,
        nextStimulusButton,
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        autoNextPresenter,
        logTimeStamp,
        audioButton,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        showStimulus,
        showStimulusGrid,
        pause,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        loadKinTypeStringDiagram,
        stimulusImage,
        userInfo,
        stimulusAudio}),
    text(new FeatureType[]{loadKinTypeStringDiagram, localStorageData,
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        allMetadataFields, createUserButton, selectUserMenu,
        eraseLocalStorageButton,
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
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        showCurrentMs,
        autoNextPresenter,
        audioButton,
        showStimulus,
        showStimulusGrid,
        pause,
        popupMessage,
        logTimeStamp,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        AnnotationTimelinePanel,
        stimulusImage,
        stimulusAudio,
        userInfo,
        menuItem}),
    timeline(new FeatureType[]{loadKinTypeStringDiagram, localStorageData,
        loadSubsetStimulus, loadStimulus, loadAllStimulus,
        allMetadataFields, createUserButton, selectUserMenu,
        eraseLocalStorageButton,
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
        stimulusCodeImage,
        currentStimulusHasTag,
        //        autoNextStimulus,
        addKinTypeGui,
        preloadAllStimuli,
        stimulusPause,
        stimulusLabel,
        responseCorrect,
        responseIncorrect,
        hasMoreStimulus,
        endOfStimulus,
        showCurrentMs,
        autoNextPresenter,
        audioButton,
        showStimulusGrid,
        showStimulus,
        pause,
        popupMessage,
        logTimeStamp,
        onError,
        onSuccess,
        kinTypeStringDiagram,
        stimulusImage,
        stimulusAudio,
        userInfo,
        menuItem});

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
