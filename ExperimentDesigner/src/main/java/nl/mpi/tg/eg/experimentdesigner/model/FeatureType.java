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

import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.*;

/**
 * this can be updated with the output of: grep match=
 * ~/Documents/ExperimentTemplate/gwt-cordova/src/main/xsl/config2java.xsl
 *
 * @since Aug 18, 2015 4:29:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl,
 */
public enum FeatureType {

    htmlText(false, true, null),
    plainText(false, true, null),
    image(false, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, src, link}),
    menuItem(false, true, new FeatureAttribute[]{target}),
    popupMessage(false, true, null),
    loadSubsetStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, condition0Tag, condition1Tag, condition2Tag}, true, Contitionals.none),
    loadStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise}, true, Contitionals.hasMoreStimulus),
    loadSdCardStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise}, true, Contitionals.hasMoreStimulus),
    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise}, true, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(true, false, new FeatureAttribute[]{timeToNext}, true, Contitionals.hasStimulusTag),
    targetButton(false, true, new FeatureAttribute[]{target}),
    actionButton(true, true, new FeatureAttribute[]{hotKey}),
    ratingFooterButton(false, true, new FeatureAttribute[]{eventTier, ratingLabels}),
    targetFooterButton(false, true, new FeatureAttribute[]{target}),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey}),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null),
    allMetadataFields(false, false, null),
    metadataField(false, false, new FeatureAttribute[]{fieldName}),
    saveMetadataButton(true, true, new FeatureAttribute[]{sendData}, false, Contitionals.hasErrorSuccess),
    createUserButton(false, true, new FeatureAttribute[]{target}),
    selectUserMenu(false, false, null),
    eraseLocalStorageButton(false, false, null),
    showCurrentMs(false, false, null),
    enableStimulusButtons(false, false, null),
    disableStimulusButtons(false, false, null),
    showStimulusProgress(false, false, null),
    hideStimulusButtons(false, false, null),
    showStimulusButtons(false, false, null),
    generateCompletionCode(false, false, null),
    sendAllData(false, false, null, false, Contitionals.hasErrorSuccess),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, false, null),
    removeStimulus(false, false, null),
    clearStimulus(false, false, null),
    centrePage(false, false, null),
    clearPage(false, false, null),
    allMenuItems(false, false, null),
    nextStimulusButton(false, true, new FeatureAttribute[]{eventTag, norepeat, hotKey}),
    nextStimulus(false, false, new FeatureAttribute[]{eventTag, norepeat}),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    autoNextPresenter(false, false, null),
    logTimeStamp(false, false, new FeatureAttribute[]{eventTag}),
    audioButton(false, false, new FeatureAttribute[]{eventTag, mp3, ogg, poster}),
    preloadAllStimuli(true, false, null, true, Contitionals.none),
    showStimulus(true, false, null, false, Contitionals.none), // todo: should this be here?
    showStimulusGrid(true, false, new FeatureAttribute[]{columnCount, imageWidth, eventTag}, true, Contitionals.hasCorrectIncorrect),
    pause(true, false, new FeatureAttribute[]{timeToNext}),
    stimulusPause(true, false, null),
    stimulusLabel(false, false, null),
    onError(true, false, null),
    onSuccess(true, false, null),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{timeToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{timeToNext, diagramName}),
    responseCorrect(true, false, new FeatureAttribute[]{timeToNext}),
    responseIncorrect(true, false, new FeatureAttribute[]{timeToNext}),
    hasMoreStimulus(true, false, null),
    endOfStimulus(true, false, null),
    hasTag(true, false, null),
    withoutTag(true, false, null),
    existingUserCheck(true, false, null, false, Contitionals.hasUserCount),
    multipleUsers(true, false, null),
    singleUser(true, false, null),
    stimulusImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, timeToNext}),
    stimulusCodeImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, timeToNext, codeFormat}),
    stimulusCodeAudio(true, false, new FeatureAttribute[]{timeToNext, codeFormat}),
    stimulusAudio(true, false, new FeatureAttribute[]{timeToNext, mp3,}),
    VideoPanel(false, false, new FeatureAttribute[]{mp4, ogg, webm, percentOfPage, maxHeight, maxWidth, poster}),
    AnnotationTimelinePanel(true, false, new FeatureAttribute[]{mp4, ogg, webm, poster, eventTag, columnCount, maxStimuli}, true, Contitionals.none),
    startAudioRecorder(false, false, new FeatureAttribute[]{wavFormat, filePerStimulus, eventTag}),
    stopAudioRecorder(false, false, new FeatureAttribute[]{}),
    startAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier}),
    endAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier, eventTag}),
    userInfo(false, false, null),
    versionData(false, false, null),
    preventWindowClose(false, false, null);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final boolean canHaveStimulusTags; // todo: this could well be canHaveTagList so that it is more generic
    private final FeatureAttribute[] featureAttributes;
    private final Contitionals contitionals;

    public enum Contitionals {
        hasCorrectIncorrect,
        hasMoreStimulus,
        hasStimulusTag,
        hasErrorSuccess,
        hasUserCount,
        none
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.contitionals = Contitionals.none;
        this.canHaveStimulusTags = false;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, Contitionals contitionals) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.contitionals = contitionals;
    }

    public boolean canHaveFeatures() {
        return canHaveFeatures;
    }

    public boolean canHaveText() {
        return canHaveText;
    }

    public boolean canHaveStimulusTags() {
        return canHaveStimulusTags;
    }

    public FeatureAttribute[] getFeatureAttributes() {
        return featureAttributes;
    }

    public Contitionals getContitionals() {
        return contitionals;
    }
}
