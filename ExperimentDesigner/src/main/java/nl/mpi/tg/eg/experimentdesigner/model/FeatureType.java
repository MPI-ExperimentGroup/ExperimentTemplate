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

    htmlText(false, true, new FeatureAttribute[]{styleName}),
    htmlTokenText(false, true, new FeatureAttribute[]{styleName}) /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */,
    plainText(false, true, null),
    image(true, false, new FeatureAttribute[]{src, styleName, msToNext}),
    menuItem(false, true, new FeatureAttribute[]{target, hotKey}),
    //    popupMessage(true, true, null),
    loadStimulus(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none, true),
    withMatchingStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, matchingRegex}, false, false, false, Contitionals.hasMoreStimulus, Contitionals.none),
    loadSdCardStimulus(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, excludeRegex, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none),
    //    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, false, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(false, false, new FeatureAttribute[]{msToNext}, true, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none), // todo: consider updating this to take a tags attribute rather than a stimuli element
    showStimuliReport(false, false, null),
    sendStimuliReport(false, false, new FeatureAttribute[]{type, dataChannel, headerKey, separator}),
    targetButton(false, true, new FeatureAttribute[]{hotKey, target, styleName}),
    actionButton(true, true, new FeatureAttribute[]{hotKey, styleName}),
    stimulusButton(true, true, new FeatureAttribute[]{hotKey, dataChannel, styleName}),
    touchInputStimulusButton(true, true, new FeatureAttribute[]{eventTag, dataChannel, src, styleName, listenerId}),
    //// todo: touch input needs a threshold before touch is registered and another before touch is ended to allow gaps in touch being recorded as on touch
    touchInputCaptureStart(true, false, new FeatureAttribute[]{showControls, msToNext}), /* sub elements are triggered after the touch ends or after msToNext of no touch activity */
    touchInputReportSubmit(false, false, new FeatureAttribute[]{dataChannel}),
    sendGroupMessageButton(false, true, new FeatureAttribute[]{hotKey, dataChannel, eventTag, repeatIncorrect, incrementPhase, /* incrementPhaseOnDictionaryincrementStimulus */}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    sendGroupMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    sendGroupStoredMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    sendGroupEndOfStimuli(false, false, new FeatureAttribute[]{eventTag}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    ratingButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    stimulusFreeText(true, true, new FeatureAttribute[]{validationRegex, dataChannel, allowedCharCodes, hotKey, styleName, inputErrorMessage}),
    stimulusRatingButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, styleName}),
    stimulusHasRatingOptions(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    stimulusHasResponse(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    ratingFooterButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight}),
    targetFooterButton(false, true, new FeatureAttribute[]{target}),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey}),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null),
    allMetadataFields(false, false, null),
    metadataField(false, false, new FeatureAttribute[]{fieldName}),
    stimulusMetadataField(false, false, new FeatureAttribute[]{fieldName, dataChannel}),
    metadataFieldConnection(false, false, new FeatureAttribute[]{fieldName, linkedFieldName}),
    saveMetadataButton(false, true, new FeatureAttribute[]{sendData, networkErrorMessage}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    createUserButton(false, true, new FeatureAttribute[]{target}),
    selectUserMenu(false, false, null),
    eraseLocalStorageButton(false, false, null),
    eraseUsersDataButton(false, true, new FeatureAttribute[]{target}), // if users still exist in the system target will be used, otherwise the application will start at the begining.
    showCurrentMs(false, false, null),
    enableStimulusButtons(false, false, null),
    disableStimulusButtons(false, false, null),
    cancelPauseTimers(false, false, null),
    showStimulusProgress(false, false, new FeatureAttribute[]{styleName}),
    hideStimulusButtons(false, false, null),
    showStimulusButtons(false, false, null),
    displayCompletionCode(false, false, null),
    generateCompletionCode(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendAllData(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendMetadata(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, false, null),
    removeStimulus(false, false, null),
    removeMatchingStimulus(false, false, new FeatureAttribute[]{matchingRegex}),
    clearStimulus(false, false, null),
    centrePage(false, false, null),
    clearPage(false, false, new FeatureAttribute[]{styleName}),
    backgroundImage(true, false, new FeatureAttribute[]{msToNext, src, styleName}),
    allMenuItems(false, false, null),
    prevStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey}),
    nextStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey}),
    nextStimulus(false, false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect}),
    prevStimulus(false, false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect}),
    nextMatchingStimulus(false, false, null),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    hasGetParameter(false, false, new FeatureAttribute[]{parameterName}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    activateRandomItem(false, false, new FeatureAttribute[]{}),
    autoNextPresenter(false, false, new FeatureAttribute[]{target}),
    logTimeStamp(false, false, new FeatureAttribute[]{eventTag, dataChannel}),
    audioButton(false, false, new FeatureAttribute[]{eventTag, dataChannel, poster, autoPlay, hotKey, styleName, src}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    preloadAllStimuli(true, false, null, true, false, false, Contitionals.none, Contitionals.none),
    showStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.none), // todo: should this be here? or should it have an increment for next back etc
    showStimulusGrid(false, false, new FeatureAttribute[]{maxStimuli, dataChannel, columnCount, imageWidth, eventTag, animate}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.none),
    matchingStimulusGrid(false, false, new FeatureAttribute[]{columnCount, dataChannel, maxWidth, animate, matchingRegex, maxStimuli, randomise}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.none),
    pause(true, false, new FeatureAttribute[]{msToNext}),
    randomMsPause(true, false, new FeatureAttribute[]{minimum, maximum}),
    triggerListener(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}),
    trigger(false, false, new FeatureAttribute[]{listenerId}),
    countdownLabel(true, true, new FeatureAttribute[]{msToNext, msLabelFormat, styleName}),
    stimulusPause(true, false, null),
    stimulusLabel(false, false, new FeatureAttribute[]{styleName}),
    onError(true, false, null, false, false, false, Contitionals.none, Contitionals.hasErrorSuccess),
    onSuccess(true, false, null, false, false, false, Contitionals.none, Contitionals.hasErrorSuccess),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    conditionTrue(true, false, null, false, false, false, Contitionals.none, Contitionals.hasTrueFalseCondition),
    conditionFalse(true, false, null, false, false, false, Contitionals.none, Contitionals.hasTrueFalseCondition),
    responseCorrect(true, false, new FeatureAttribute[]{msToNext}, false, false, false, Contitionals.none, Contitionals.hasCorrectIncorrect),
    responseIncorrect(true, false, new FeatureAttribute[]{msToNext}, false, false, false, Contitionals.none, Contitionals.hasCorrectIncorrect),
    hasMoreStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMoreStimulus),
    endOfStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMoreStimulus),
    existingUserCheck(false, false, null, false, false, false, Contitionals.hasUserCount, Contitionals.none),
    multipleUsers(true, false, null, false, false, false, Contitionals.none, Contitionals.hasUserCount),
    // todo: this should be suppressed from normal use like the other conditional child elements
    singleUser(true, false, null, false, false, false, Contitionals.none, Contitionals.hasUserCount),
    aboveThreshold(true, false, null, false, false, false, Contitionals.none, Contitionals.hasThreshold),
    belowThreshold(true, false, null, false, false, false, Contitionals.none, Contitionals.hasThreshold),
    mediaLoaded(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaLoading),
    mediaLoadFailed(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaLoading),
    mediaPlaybackComplete(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaPlayback),
    //    clearRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    //    updateRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    table(true, false, new FeatureAttribute[]{styleName, showOnBackButton}),
    row(true, false, null),
    column(true, false, new FeatureAttribute[]{styleName}),
    // todo: change this to be at the same level as laodStimulus and take the same parameters
    withStimuli(true, false, null, false, false, false, Contitionals.none, Contitionals.none), // loop over all loaded stimuli rather than using next stimulus on user input
    // todo: look for and update to add the show any stimuli tag and make stimulusImage only show images (true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaLoading), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusPresent(false, false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusImage(false, false, new FeatureAttribute[]{msToNext, styleName, dataChannel}, false, false, false, Contitionals.hasMediaLoading, Contitionals.none), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusCodeImage(false, false, new FeatureAttribute[]{msToNext, dataChannel, codeFormat, styleName}, false, false, false, Contitionals.hasMediaLoading, Contitionals.none), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeVideo(false, false, new FeatureAttribute[]{msToNext, dataChannel, maxHeight, codeFormat, percentOfPage, loop, styleName, autoPlay, showControls, maxWidth}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusCodeAudio(false, false, new FeatureAttribute[]{msToNext, dataChannel, codeFormat, showPlaybackIndicator}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusAudio(false, false, new FeatureAttribute[]{msToNext, dataChannel, showPlaybackIndicator}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    playVideo(false, false, new FeatureAttribute[]{}),
    rewindVideo(false, false, new FeatureAttribute[]{}),
    pauseVideo(false, false, new FeatureAttribute[]{}),
    stimulusImageCapture(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}),
    //    captureStimulusImage(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth}),
    VideoPanel(false, false, new FeatureAttribute[]{src, percentOfPage, maxHeight, maxWidth, poster}),
    AnnotationTimelinePanel(true, false, new FeatureAttribute[]{src, poster, eventTag, columnCount, maxStimuli}, false, false, false, Contitionals.none, Contitionals.none),
    startAudioRecorder(false, false, new FeatureAttribute[]{wavFormat, filePerStimulus, eventTag}),
    stopAudioRecorder(false, false, new FeatureAttribute[]{}),
    startAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier}),
    endAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier, eventTag}),
    helpDialogue(false, true, new FeatureAttribute[]{closeButtonLabel}),
    userInfo(false, false, null),
    versionData(false, false, null),
    preventWindowClose(false, true, null), // note: preventWindowClose should only be allowed once in the experiment element
    showColourReport(false, false, new FeatureAttribute[]{scoreThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    // @todo: groupMembers could be used to determing the sequence of who goes when and therefore could be changed to groupMembersSequence
    groupNetwork(false, false, new FeatureAttribute[]{groupMembers, groupCommunicationChannels, phasesPerStimulus}, false, false, false, Contitionals.hasGroupActivities, Contitionals.none),
    groupNetworkActivity(true, false, new FeatureAttribute[]{groupRole}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupMemberCodeLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupMemberLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupMessageLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupResponseStimulusImage(true, false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, msToNext, animate}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupResponseFeedback(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.hasGroupActivities),
    groupScoreLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    groupChannelScoreLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    scoreLabel(false, false, new FeatureAttribute[]{styleName}),
    submitGroupEvent(false, false, null, false, false, false, Contitionals.none, Contitionals.hasGroupActivities),
    clearCurrentScore(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.none),
    scoreIncrement(true, false, new FeatureAttribute[]{scoreValue}, false, false, false, Contitionals.none, Contitionals.none),
    bestScoreAboveThreshold(false, false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    scoreAboveThreshold(false, false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    resetStimulus(false, false, new FeatureAttribute[]{target}, false, false, false, Contitionals.none, Contitionals.none),
    submitTestResults(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final boolean canHaveStimulusTags; // todo: this could well be canHaveTagList so that it is more generic
    private final boolean canHaveRandomGrouping;
    private final boolean canHaveUndefinedAttribute;
    private final boolean allowsCustomImplementation;
    private final FeatureAttribute[] featureAttributes;
    private final Contitionals requiresChildType;
    private final Contitionals requiresParentType;

    public enum Contitionals {
        hasTrueFalseCondition,
        hasCorrectIncorrect,
        hasMoreStimulus,
        hasErrorSuccess,
        hasUserCount,
        hasThreshold,
        hasGroupActivities,
        hasMediaLoading,
        hasMediaPlayback,
        none,
//        needsConditionalParent // when true, the element cannot be used alone but must be in its conditional parent element
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = Contitionals.none;
        this.requiresParentType = Contitionals.none;
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, Contitionals requiresChildType, Contitionals requiresParentType) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.requiresParentType = requiresParentType;
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = false;
        if (requiresChildType != Contitionals.none && requiresParentType != Contitionals.none && canHaveFeatures) {
            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
        }
        //    todo: set all hasMediaPlayback and hasMediaLoading to canHaveFeatures = false
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, Contitionals requiresChildType, Contitionals requiresParentType, final boolean allowsCustomImplementation) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.requiresParentType = requiresParentType;
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = allowsCustomImplementation;
        if (requiresChildType != Contitionals.none && requiresParentType != Contitionals.none && canHaveFeatures) {
            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
        }
        //    todo: set all hasMediaPlayback and hasMediaLoading to canHaveFeatures = false
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

    public boolean isCanHaveUndefinedAttribute() {
        return canHaveUndefinedAttribute;
    }

    public boolean allowsCustomImplementation() {
        return allowsCustomImplementation;
    }

    public FeatureAttribute[] getFeatureAttributes() {
        return featureAttributes;
    }

    public Contitionals getRequiresChildType() {
        return requiresChildType;
    }

    public Contitionals getRequiresParentType() {
        return requiresParentType;
    }

}
