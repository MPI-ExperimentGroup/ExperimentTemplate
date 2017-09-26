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
    htmlTokenText(false, true, null) /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */,
    plainText(false, true, null),
    image(true, false, new FeatureAttribute[]{src, styleName, msToNext}),
    menuItem(false, true, new FeatureAttribute[]{target}),
    //    popupMessage(true, true, null),
    loadSubsetStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, condition0Tag, condition1Tag, condition2Tag}, true, true, Contitionals.none),
    loadStimulus(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, Contitionals.hasMoreStimulus),
    withMatchingStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, matchingRegex}, false, false, Contitionals.hasMoreStimulus),
    loadSdCardStimulus(false, false, new FeatureAttribute[]{eventTag, /*minStimuliPerTag, maxStimuliPerTag,*/ maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, Contitionals.hasMoreStimulus),
    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(true, false, new FeatureAttribute[]{msToNext}, true, false, Contitionals.hasStimulusTag),
    targetButton(false, true, new FeatureAttribute[]{target, styleName}),
    actionButton(true, true, new FeatureAttribute[]{hotKey, styleName}),
    touchInputZone(true, false, new FeatureAttribute[]{eventTag, hotKey, styleName}),
    sendGroupMessageButton(false, true, new FeatureAttribute[]{hotKey, eventTag, repeatIncorrect, incrementPhase, /* incrementPhaseOnDictionaryincrementStimulus */}),
    sendGroupMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}),
    sendGroupStoredMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}),
    sendGroupEndOfStimuli(false, false, new FeatureAttribute[]{eventTag}),
    ratingButton(false, false, new FeatureAttribute[]{eventTier, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    stimulusFreeText(true, true, new FeatureAttribute[]{validationRegex, allowedCharCodes, hotKey, styleName}),
    stimulusRatingButton(false, false, new FeatureAttribute[]{eventTier, ratingLabelLeft, ratingLabelRight}),
    stimulusHasRatingOptions(true, false, new FeatureAttribute[]{}, false, false, Contitionals.hasTrueFalseCondition),
    stimulusHasResponse(true, false, new FeatureAttribute[]{}, false, false, Contitionals.hasTrueFalseCondition),
    ratingFooterButton(false, true, new FeatureAttribute[]{eventTier, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    targetFooterButton(false, true, new FeatureAttribute[]{target}),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey}),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null),
    allMetadataFields(false, false, null),
    metadataField(false, false, new FeatureAttribute[]{fieldName}),
    stimulusMetadataField(false, false, new FeatureAttribute[]{fieldName}),
    metadataFieldConnection(false, false, new FeatureAttribute[]{fieldName, linkedFieldName}),
    saveMetadataButton(true, true, new FeatureAttribute[]{sendData, networkErrorMessage}, false, false, Contitionals.hasErrorSuccess),
    createUserButton(false, true, new FeatureAttribute[]{target}),
    selectUserMenu(false, false, null),
    eraseLocalStorageButton(false, false, null),
    eraseUsersDataButton(false, true, null),
    showCurrentMs(false, false, null),
    enableStimulusButtons(false, false, null),
    disableStimulusButtons(false, false, null),
    showStimulusProgress(false, false, new FeatureAttribute[]{styleName}),
    hideStimulusButtons(false, false, null),
    showStimulusButtons(false, false, null),
    generateCompletionCode(false, false, null),
    sendAllData(false, false, null, false, false, Contitionals.hasErrorSuccess),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, false, null),
    removeStimulus(false, false, null),
    removeMatchingStimulus(false, false, new FeatureAttribute[]{matchingRegex}),
    clearStimulus(false, false, null),
    centrePage(false, false, null),
    clearPage(false, false, null),
    backgroundImage(true, false, new FeatureAttribute[]{src, styleName, msToNext}),
    allMenuItems(false, false, null),
    prevStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey}),
    nextStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey}),
    nextStimulus(false, false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect}),
    nextMatchingStimulus(false, false, null),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    hasGetParameter(true, false, new FeatureAttribute[]{parameterName}, false, false, Contitionals.hasTrueFalseCondition),
    autoNextPresenter(false, false, new FeatureAttribute[]{target}),
    logTimeStamp(false, false, new FeatureAttribute[]{eventTag}),
    audioButton(true, false, new FeatureAttribute[]{eventTag, poster, autoPlay, hotKey}),
    preloadAllStimuli(true, false, null, true, false, Contitionals.none),
    showStimulus(true, false, null, false, false, Contitionals.none), // todo: should this be here?
    showStimulusGrid(true, false, new FeatureAttribute[]{maxStimuli, columnCount, imageWidth, eventTag, animate}, true, false, Contitionals.hasCorrectIncorrect),
    matchingStimulusGrid(false, false, new FeatureAttribute[]{columnCount, maxWidth, animate, matchingRegex, maxStimuli, randomise}, false, false, Contitionals.hasCorrectIncorrect),
    pause(true, false, new FeatureAttribute[]{msToNext}),
    countdownLabel(true, true, new FeatureAttribute[]{msToNext, msLabelFormat, styleName}),
    stimulusPause(true, false, null),
    stimulusLabel(false, false, new FeatureAttribute[]{styleName}),
    onError(true, false, null),
    onSuccess(true, false, null),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    conditionTrue(true, false, null),
    conditionFalse(true, false, null),
    responseCorrect(true, false, new FeatureAttribute[]{}),
    responseIncorrect(true, false, new FeatureAttribute[]{}),
    hasMoreStimulus(true, false, null),
    endOfStimulus(true, false, null),
    hasTag(true, false, null),
    withoutTag(true, false, null),
    existingUserCheck(true, false, null, false, false, Contitionals.hasUserCount),
    multipleUsers(true, false, null),
    singleUser(true, false, null),
    aboveThreshold(true, false, null),
    belowThreshold(true, false, null),
    table(true, false, null),
    row(true, false, null),
    column(true, false, null),
    stimulusImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement}), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusCodeImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, codeFormat, animate}),
    stimulusCodeVideo(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, codeFormat, styleName, showControls}),
    stimulusCodeAudio(true, false, new FeatureAttribute[]{msToNext, codeFormat}),
    stimulusAudio(true, false, new FeatureAttribute[]{msToNext, showPlaybackIndicator}),
    stimulusImageCapture(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}),
    //    captureStimulusImage(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth}),
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
    showColourReport(true, false, new FeatureAttribute[]{scoreThreshold}, false, false, Contitionals.hasThreshold),
    // @todo: groupMembers could be used to determing the sequence of who goes when and therefore could be changed to groupMembersSequence
    groupNetwork(true, false, new FeatureAttribute[]{groupMembers, groupCommunicationChannels, phasesPerStimulus}, false, false, Contitionals.hasGroupActivities),
    groupNetworkActivity(true, false, new FeatureAttribute[]{groupRole}, false, false, Contitionals.none),
    groupMemberCodeLabel(false, false, new FeatureAttribute[]{styleName}),
    groupMemberLabel(false, false, new FeatureAttribute[]{styleName}),
    groupMessageLabel(false, false, new FeatureAttribute[]{styleName}),
    groupResponseStimulusImage(true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate}),
    groupResponseFeedback(false, false, new FeatureAttribute[]{}, false, false, Contitionals.hasCorrectIncorrect),
    groupScoreLabel(false, false, new FeatureAttribute[]{styleName}),
    groupChannelScoreLabel(false, false, new FeatureAttribute[]{styleName}),
    scoreLabel(false, false, new FeatureAttribute[]{styleName}),
    submitGroupEvent(false, false, null),
    clearCurrentScore(false, false, new FeatureAttribute[]{}, false, false, Contitionals.none),
    scoreIncrement(true, false, new FeatureAttribute[]{scoreValue}, false, false, Contitionals.none),
    bestScoreAboveThreshold(true, false, new FeatureAttribute[]{scoreThreshold}, false, false, Contitionals.hasThreshold),
    scoreAboveThreshold(true, false, new FeatureAttribute[]{scoreThreshold}, false, false, Contitionals.hasThreshold),
    resetStimulus(false, false, new FeatureAttribute[]{target}, false, false, Contitionals.none),
    submitTestResults(true, false, null, false, false, Contitionals.hasErrorSuccess);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final boolean canHaveStimulusTags; // todo: this could well be canHaveTagList so that it is more generic
    private final boolean canHaveRandomGrouping;
    private final FeatureAttribute[] featureAttributes;
    private final Contitionals contitionals;

    public enum Contitionals {
        hasTrueFalseCondition,
        hasCorrectIncorrect,
        hasMoreStimulus,
        hasStimulusTag,
        hasErrorSuccess,
        hasUserCount,
        hasThreshold,
        hasGroupActivities,
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
