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
    loadSubsetStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, condition0Tag, condition1Tag, condition2Tag}, true, true, Contitionals.none),
    loadStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount}, true, true, Contitionals.hasMoreStimulus),
    loadSdCardStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount}, true, false, Contitionals.hasMoreStimulus),
    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount}, true, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(true, false, new FeatureAttribute[]{msToNext}, true, false, Contitionals.hasStimulusTag),
    targetButton(false, true, new FeatureAttribute[]{target}),
    actionButton(true, true, new FeatureAttribute[]{hotKey}),
    ratingButton(false, false, new FeatureAttribute[]{eventTier, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    stimulusFreeText(true, false, new FeatureAttribute[]{validationRegex}),
    stimulusRatingButton(false, false, new FeatureAttribute[]{eventTier, ratingLabelLeft, ratingLabelRight}),
    ratingFooterButton(false, true, new FeatureAttribute[]{eventTier, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    targetFooterButton(false, true, new FeatureAttribute[]{target}),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey}),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null),
    allMetadataFields(false, false, null),
    metadataField(false, false, new FeatureAttribute[]{fieldName}),
    saveMetadataButton(true, true, new FeatureAttribute[]{sendData, networkErrorMessage}, false, false, Contitionals.hasErrorSuccess),
    createUserButton(false, true, new FeatureAttribute[]{target}),
    selectUserMenu(false, false, null),
    eraseLocalStorageButton(false, false, null),
    eraseUsersDataButton(false, true, null),
    showCurrentMs(false, false, null),
    enableStimulusButtons(false, false, null),
    disableStimulusButtons(false, false, null),
    showStimulusProgress(false, false, null),
    hideStimulusButtons(false, false, null),
    showStimulusButtons(false, false, null),
    generateCompletionCode(false, false, null),
    sendAllData(false, false, null, false, false, Contitionals.hasErrorSuccess),
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
    preloadAllStimuli(true, false, null, true, false, Contitionals.none),
    showStimulus(true, false, null, false, false, Contitionals.none), // todo: should this be here?
    showStimulusGrid(true, false, new FeatureAttribute[]{columnCount, imageWidth, eventTag}, true, false, Contitionals.hasCorrectIncorrect),
    pause(true, false, new FeatureAttribute[]{msToNext}),
    stimulusPause(true, false, null),
    stimulusLabel(false, false, null),
    onError(true, false, null),
    onSuccess(true, false, null),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    responseCorrect(true, false, new FeatureAttribute[]{msToNext}),
    responseIncorrect(true, false, new FeatureAttribute[]{msToNext}),
    hasMoreStimulus(true, false, null),
    endOfStimulus(true, false, null),
    hasTag(true, false, null),
    withoutTag(true, false, null),
    existingUserCheck(true, false, null, false, false, Contitionals.hasUserCount),
    multipleUsers(true, false, null),
    singleUser(true, false, null),
    aboveThreshold(true, false, null),
    belowThreshold(true, false, null),
    stimulusImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}),
    stimulusImageCapture(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}),
    stimulusCodeImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, codeFormat}),
    stimulusCodeVideo(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, codeFormat}),
    stimulusCodeAudio(true, false, new FeatureAttribute[]{msToNext, codeFormat}),
    stimulusAudio(true, false, new FeatureAttribute[]{msToNext, mp3,}),
    VideoPanel(false, false, new FeatureAttribute[]{mp4, ogg, webm, percentOfPage, maxHeight, maxWidth, poster}),
    AnnotationTimelinePanel(true, false, new FeatureAttribute[]{mp4, ogg, webm, poster, eventTag, columnCount, maxStimuli}, true, false, Contitionals.none),
    startAudioRecorder(false, false, new FeatureAttribute[]{wavFormat, filePerStimulus, eventTag}),
    stopAudioRecorder(false, false, new FeatureAttribute[]{}),
    startAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier}),
    endAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier, eventTag}),
    helpDialogue(false, true, new FeatureAttribute[]{closeButtonLabel}),
    userInfo(false, false, null),
    versionData(false, false, null),
    preventWindowClose(false, false, null),
    showColourReport(true, false, new FeatureAttribute[]{scoreThreshold}, false, false, Contitionals.hasThreshold);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final boolean canHaveStimulusTags; // todo: this could well be canHaveTagList so that it is more generic
    private final boolean canHaveRandomGrouping;
    private final FeatureAttribute[] featureAttributes;
    private final Contitionals contitionals;

    public enum Contitionals {
        hasCorrectIncorrect,
        hasMoreStimulus,
        hasStimulusTag,
        hasErrorSuccess,
        hasUserCount,
        hasThreshold,
        none
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.contitionals = Contitionals.none;
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, Contitionals contitionals) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
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

    public boolean isCanHaveRandomGrouping() {
        return canHaveRandomGrouping;
    }

    public FeatureAttribute[] getFeatureAttributes() {
        return featureAttributes;
    }

    public Contitionals getContitionals() {
        return contitionals;
    }
}
