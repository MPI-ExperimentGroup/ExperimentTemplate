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
 * this can be updated with the output of: grep match= ~/Documents/ExperimentTemplate/gwt-cordova/src/main/xsl/config2java.xsl
 *
 * @since Aug 18, 2015 4:29:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum FeatureType {

    htmlText(false, true, new FeatureAttribute[]{styleName}, "The contents of featureText will be shown as HTML and styled with styleName if provided"),
    htmlTokenText(false, true, new FeatureAttribute[]{styleName}, "When the featureText contains string tokens they will be replaced with the relevant values, eg score values &lt;groupScore&gt; &lt;channelScore&gt; etc.") /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */,
    logTokenText(false, false, new FeatureAttribute[]{dataChannel, type, headerKey, dataLogFormat}) /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */, // if headerKey is not provided then the stimulus id will be used // todo: headerKey is perhaps in conflict with sendStimuliReport where headerKey is used by the administration and perhaps should be moved to the administration element
    plainText(false, true, null),
    image(false, false, new FeatureAttribute[]{src, styleName, msToNext}, false, false, false, Contitionals.hasMediaLoading, Contitionals.none),
    menuItem(false, true, new FeatureAttribute[]{target, hotKey, styleName}),
    //    popupMessage(true, true, null),
    withStimuli(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.eachStimulus, Contitionals.none), // loop over all loaded stimuli rather than using next stimulus on user input
    // todo: document that Tags require all to exist on the stimuli "stimulus.getTags().containsAll(selectionTags)"
    loadStimulus(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none, true),
    withMatchingStimulus(false, false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, matchingRegex}, false, false, false, Contitionals.hasMoreStimulus, Contitionals.stimulusAction),
    loadSdCardStimulus(false, false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, excludeRegex /* excludes files matching */, matchingRegex /* includes files matching */, replacementRegex /* generates stimuli code by regex replacement on the file path */, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none),
    //    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, false, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(false, false, new FeatureAttribute[]{}, true, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction), // todo: consider updating this to take a tags attribute rather than a stimuli element
    clearStimulusResponses(false, false, new FeatureAttribute[]{}, true, false, false, Contitionals.none, Contitionals.none),
    validateStimuliResponses(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    stimulusExists(false, false, new FeatureAttribute[]{offset}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    showStimuliReport(false, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    sendStimuliReport(false, false, new FeatureAttribute[]{type, dataChannel, headerKey, separator}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    targetButton(false, true, new FeatureAttribute[]{hotKey, target, styleName, groupId}),
    actionButton(true, true, new FeatureAttribute[]{hotKey, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.hasActionButtons, Contitionals.none),
    actionTokenButton(true, true, new FeatureAttribute[]{hotKey, styleName, groupId}),
    disableButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    enableButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    hideButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    showButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    stimulusButton(true, true, new FeatureAttribute[]{hotKey, dataChannel, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    touchInputStimulusButton(true, true, new FeatureAttribute[]{eventTag, dataChannel, src, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //// todo: touch input needs a threshold before touch is registered and another before touch is ended to allow gaps in touch being recorded as on touch
    touchInputCaptureStart(true, false, new FeatureAttribute[]{showControls, msToNext}, false, false, false, Contitionals.none, Contitionals.stimulusAction), /* sub elements are triggered after the touch ends or after msToNext of no touch activity */
    touchInputReportSubmit(false, false, new FeatureAttribute[]{dataChannel}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    ratingButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    ratingRadioButton(false, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    ratingCheckbox(false, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    stimulusFreeText(false, true, new FeatureAttribute[]{validationRegex, dataChannel, allowedCharCodes, hotKey, styleName, inputErrorMessage, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction), // the hotKey in stimulusFreeText will trigger any button with the same hotkey. // todo: The current use of the featureText attribute could be changed to allowedCharErrorMessage and inputErrorMessage could be changed to validationErrorMessage
    stimulusRatingButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    stimulusRatingRadio(false, false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    stimulusRatingCheckbox(false, false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    stimulusHasRatingOptions(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    stimulusHasResponse(false, new FeatureAttribute[]{groupId, matchingRegex}, "When groupId is omitted conditionTrue will trigger if the current stimulus has any response. If groupId and matchingRegex are provided this will only trigger if a the current stimulus has a response in that group that matches the regex.", Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    setStimulusCodeResponse(false, false, new FeatureAttribute[]{codeFormat, dataChannel, applyScore}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    addStimulusCodeResponseValidation(false, true, new FeatureAttribute[]{validationRegex, dataChannel}, /* this validation is specific to the StimulusCodeResponses*/ false, false, false, Contitionals.none, Contitionals.stimulusAction),
    ratingFooterButton(true, false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    targetFooterButton(false, true, new FeatureAttribute[]{target, styleName, groupId}),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey, styleName, groupId}),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null),
    stimuliValidation(false, false, null),
    addKeyboardDebug(false, false, null),
    // metadataField fields:
    //    displays all or one metadata field for data entry
    allMetadataFields(false, false, null),
    metadataField(false, false, new FeatureAttribute[]{fieldName}),
    // metadataField stimulus fields:
    //    a metadata field is available for each stimulus
    stimulusMetadataField(false, false, new FeatureAttribute[]{fieldName, dataChannel}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    // metadataField linking fields:
    //    which make a connection between entities
    //    which allow data entry to describe the connection between entities
    //    linkedFieldName is perhaps ambugious since suggests it is the link, but it is the label field for the linked entity
    metadataFieldConnection(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, oneToMany}), // oneToMany determines cardinality so that when the field is populated another will be offered so that lists of data can be entered for a given field
    // metadataField linked fields:
    //    when one value determines the default value of the other
    //    when one value determines the pressence of the other
    metadataFieldVisibilityDependant(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, visibleRegex, enabledRegex}), // metadataFieldDependant fields are only shown when the linkedFieldName matches the visibleRegex, whereas the enabledRegex determins if it is enabled
    metadataFieldDateTriggered(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, daysThresholds, visibleRegex, enabledRegex}), // the daysThresholds is a list of values trigger the selection of the item index of this metadataField based on the calculated age in days from linkedFieldName

    saveMetadataButton(false, true, new FeatureAttribute[]{sendData, networkErrorMessage, styleName, groupId}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    createUserButton(false, true, new FeatureAttribute[]{target, styleName, groupId}),
    switchUserIdButton(true, new FeatureAttribute[]{styleName, groupId, fieldName, validationRegex}, "Switch the user id to the value in the specified metadata field. The value of the field is first validated against the provided regex. Care should be used to make sure that the field contains a valid user id.", Contitionals.hasErrorSuccess, Contitionals.none),
    selectUserMenu(false, false, new FeatureAttribute[]{styleName, fieldName}, "Shows a menu listing the users in the system. The label on each menu item is determined by value of the provided metadata field. When a menu item is clicked the active user is changed and the next presenter is shown."),
    selectLocaleMenu(false, false, new FeatureAttribute[]{styleName}),
    eraseLocalStorageButton(false, false, new FeatureAttribute[]{styleName, groupId}),
    eraseUsersDataButton(false, true, new FeatureAttribute[]{target, styleName, groupId}), // if users still exist in the system target will be used, otherwise the application will start at the begining.
    showCurrentMs(false, false, null),
    //    @Deprecated //???
    //    enableStimulusButtons(false, false, null),
    //    @Deprecated // ???
    //    disableStimulusButtons(false, false, null),
    cancelPauseTimers(false, false, null),
    cancelPauseAll(false, false, null), // pause all playing media, cancel all timers and cancel all current on media load events
    showStimulusProgress(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    @Deprecated // ???
    //    hideStimulusButtons(false, false, null),
    //    @Deprecated // ???
    //    showStimulusButtons(false, false, null),
    displayCompletionCode(false, false, null),
    generateCompletionCode(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendAllData(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendMetadata(false, false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    redirectToUrl(false, false, new FeatureAttribute[]{src}),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    removeStimulus(false, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    removeMatchingStimulus(false, false, new FeatureAttribute[]{matchingRegex}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    clearStimulus(false, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    centrePage(false, false, null),
    clearPage(false, false, new FeatureAttribute[]{styleName}),
    backgroundImage(true, false, new FeatureAttribute[]{msToNext, src, styleName}),
    allMenuItems(false, false, new FeatureAttribute[]{styleName}),
    prevStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextStimulusButton(false, true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextStimulus(false, false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    prevStimulus(false, false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    skipStimulus(false, false, new FeatureAttribute[]{offset /* when offset is < 1 the stimuli will not repeat, when it is > 0 the stimulus will be shown again after X items or at the end of the list, which ever is the lesser */}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextMatchingStimulus(false, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    hasGetParameter(false, false, new FeatureAttribute[]{parameterName}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    hasMetadataValue(false, false, new FeatureAttribute[]{fieldName, matchingRegex}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    setMetadataValue(false, new FeatureAttribute[]{fieldName, dataLogFormat, replacementRegex}, "The value of dataLogFormat will have any string tokens replaced. Next if the replacementRegex is provided then the regex is applied and only the values of the regex capture groups will be kept, other wise the entire string is used. The result is then stored in the specified metadata field.", Contitionals.none, Contitionals.none),
    progressIndicator(false, false, new FeatureAttribute[]{evaluateTokens, styleName}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    setMetadataEvalTokens(false, new FeatureAttribute[]{fieldName, evaluateTokens}, "The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The resulting number is then stored in the specified metadata field.", Contitionals.hasErrorSuccess, Contitionals.none),
    activateRandomItem(false, false, new FeatureAttribute[]{}, "Randomly activates one menu item on the current presenter providing that the target presenter has not already been completed. If all targets have been completed then the user will be sent to the next presenter as specified by the current presenter."),
    gotoPresenter(false, false, new FeatureAttribute[]{target}),
    gotoNextPresenter(false, false, new FeatureAttribute[]{}),
    logTimeStamp(false, false, new FeatureAttribute[]{eventTag, dataChannel}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    // todo: document audioButton which fires the played event once and only once after the first playback finishes
    audioButton(false, false, new FeatureAttribute[]{eventTag, dataChannel, poster, autoPlay, hotKey, styleName, src, groupId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    preloadAllStimuli(false, false, null, true, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    showStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction), // todo: should this be here? or should it have an increment for next back etc
    showStimulusGrid(false, false, new FeatureAttribute[]{maxStimuli, dataChannel, columnCount, imageWidth, eventTag, animate}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    matchingStimulusGrid(false, false, new FeatureAttribute[]{columnCount, dataChannel, maxWidth, animate, matchingRegex, maxStimuli, randomise}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    pause(true, false, new FeatureAttribute[]{msToNext}),
    requestNotification(true, new FeatureAttribute[]{fieldName, targetOptions, dataLogFormat}, "Request mobile notifications at given times in the future on Android and iOS apps. The presenters listed in targetOptions need to be comma separated and will be shown as buttons in the notification.", Contitionals.hasErrorSuccess, Contitionals.none),
    // todo: consider renaming so that timer is the first part: timerStart, timerCompare, timerClear, timerLog
    startTimer(true, false, new FeatureAttribute[]{msToNext, listenerId}), // the start time of the first instance of startTimer is persistent over page loads and navigation, but the content of the startTimer element is scoped to the presenter and will not fire outside of the presenter, each startTimer tag will create a callback that will trigger when the timer passes the ms value, but will not fire on reload if that time has already passed.
    compareTimer(false, false, new FeatureAttribute[]{msToNext, listenerId}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    //    compareDateFormat(false, false, new FeatureAttribute[]{dateFormat, matchingRegex}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    clearTimer(false, false, new FeatureAttribute[]{listenerId}),
    logTimerValue(false, false, new FeatureAttribute[]{listenerId, eventTag, dataChannel}),
    timerLabel(false, false, new FeatureAttribute[]{listenerId, msToNext, msLabelFormat, styleName}), // this is a label like countdownLabel but shows the time value of listenerId minus msToNext and formats the result with msLabelFormat
    randomMsPause(true, false, new FeatureAttribute[]{minimum, maximum, ranges}),//"1..100", "1,100" ,"1..3,10,20,30..35,60"
     triggerDefinition(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}, "The contents of this element will be activated when matched by the listenerId attribute of trigger or triggerRandom (for example), providing the number of trigger occurences has reached the threshold and within the maximum."),
    habituationParadigmListener(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}), //  threshold is in ms eg 2000 is the minimum length of an event to be considered, maximum is the max shows eg 10.
    triggerMatching(false, false, new FeatureAttribute[]{listenerId}, "Trigger all triggerListeners matching the listenerId providing the threshold and maximum values are within the required values. The listenerId can contain tokens eg %lt;stimulusCode&gt;."),
    triggerRandom(true, false, new FeatureAttribute[]{matchingRegex}, "Randomly trigger one any of the triggerListeners where the listenerId matches the matchingRegex and its maximum trigger count has not been reached. When there are no triggerListeners that match these criteria the child contents of tag will be triggered."),
    resetTrigger/* todo: consider changing to triggerReset */(false, false, new FeatureAttribute[]{listenerId}, "Reset the threshold and maximum counters for triggerListeners matching the listenerId."),
    countdownLabel(true, true, new FeatureAttribute[]{msToNext, msLabelFormat, styleName}),
    stimulusPause(true, false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    stimulusLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    onError(true, false, null, false, false, false, Contitionals.none, Contitionals.hasErrorSuccess),
    onSuccess(true, false, null, false, false, false, Contitionals.none, Contitionals.hasErrorSuccess),
    onActivate(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaLoadingButton),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    conditionTrue(true, false, null, false, false, false, Contitionals.none, Contitionals.hasTrueFalseCondition),
    conditionFalse(true, false, null, false, false, false, Contitionals.none, Contitionals.hasTrueFalseCondition),
    responseCorrect(true, false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.hasCorrectIncorrect),
    responseIncorrect(true, false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.hasCorrectIncorrect),
    hasMoreStimulus(true, false, null, false, false, false, Contitionals.stimulusAction, Contitionals.hasMoreStimulus),
    endOfStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMoreStimulus),
    beforeStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.eachStimulus),
    eachStimulus(true, false, null, false, false, false, Contitionals.stimulusAction, Contitionals.eachStimulus),
    afterStimulus(true, false, null, false, false, false, Contitionals.none, Contitionals.eachStimulus),
    existingUserCheck(false, false, null, false, false, false, Contitionals.hasUserCount, Contitionals.none),
    multipleUsers(true, false, null, false, false, false, Contitionals.none, Contitionals.hasUserCount),
    // todo: this should be suppressed from normal use like the other conditional child elements
    singleUser(true, false, null, false, false, false, Contitionals.none, Contitionals.hasUserCount),
    aboveThreshold(true, false, null, false, false, false, Contitionals.none, Contitionals.hasThreshold),
    withinThreshold(true, false, null, false, false, false, Contitionals.none, Contitionals.hasThreshold),
    mediaLoaded(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaLoading, Contitionals.hasMediaRecorderPlayback),
    mediaLoadFailed(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaLoading, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackStarted(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackComplete(true, false, null, false, false, false, Contitionals.none, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    //    clearRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    //    updateRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    table(true, false, new FeatureAttribute[]{styleName, showOnBackButton}),
    row(true, false, null),
    column(true, false, new FeatureAttribute[]{styleName}),
    region(true, false, new FeatureAttribute[]{regionId, styleName}),
    regionStyle(false, false, new FeatureAttribute[]{regionId, styleName}),
    regionCodeStyle(false, false, new FeatureAttribute[]{regionId, styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    regionReplace(true, false, new FeatureAttribute[]{regionId, styleName}),
    regionClear(false, false, new FeatureAttribute[]{regionId}),
    // todo: look for and update to add the show any stimuli tag and make stimulusImage only show images (true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaLoading), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusPresent(false, false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate, replacementRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusImage(false, false, new FeatureAttribute[]{msToNext, styleName, dataChannel}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusCodeImage(false, false, new FeatureAttribute[]{msToNext, dataChannel, codeFormat, styleName}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeImageButton(false, false, new FeatureAttribute[]{dataChannel, codeFormat, styleName, groupId}, false, false, false, Contitionals.hasMediaLoadingButton, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeVideo(false, false, new FeatureAttribute[]{dataChannel, maxHeight, codeFormat, percentOfPage, loop, styleName, autoPlay, showControls, maxWidth, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusVideo(false, false, new FeatureAttribute[]{dataChannel, loop, styleName, autoPlay, showControls, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusCodeAudio(false, false, new FeatureAttribute[]{dataChannel, codeFormat, showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusAudio(false, false, new FeatureAttribute[]{dataChannel, showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    playMedia(false, false, new FeatureAttribute[]{mediaId}),
    rewindMedia(false, false, new FeatureAttribute[]{mediaId}),
    pauseMedia(false, false, new FeatureAttribute[]{mediaId}),
    stimulusImageCapture(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    captureStimulusImage(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth}),
    VideoPanel(false, false, new FeatureAttribute[]{src, percentOfPage, maxHeight, maxWidth, poster}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    AnnotationTimelinePanel(true, false, new FeatureAttribute[]{src, poster, eventTag, columnCount, maxStimuli}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    startAudioRecorderWeb(false, new FeatureAttribute[]{downloadPermittedWindowMs, deviceRegex, mediaId}, "Starts the HTML5 audio recorder which will send the recorded audio to the admin database. If downloadPermittedWindowMs is given a suitable period of time, the user can also listen to the recorded audio from the server for verification.", Contitionals.hasMediaRecorderPlayback, Contitionals.stimulusAction),
    startAudioRecorderApp(false, new FeatureAttribute[]{filePerStimulus, eventTag, fieldName}, "Starts the Android wav recorder, the recorded wav files will be saved on the device in a sub directories based on value of the provided metadata field and the eventTag.", Contitionals.hasErrorSuccess, Contitionals.stimulusAction),
    stopAudioRecorder(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    stopAudioRecorder(false, false, new FeatureAttribute[]{minimum, maximum, average}, false, false, false, Contitionals.hasThreshold, Contitionals.stimulusAction),
    startAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    endAudioRecorderTag(false, false, new FeatureAttribute[]{eventTier, eventTag}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    showHtmlPopup(false, true, new FeatureAttribute[]{}, false, false, false, Contitionals.hasActionButtons, Contitionals.none),
    helpDialogue(false, true, new FeatureAttribute[]{closeButtonLabel}), // helpDialogue is probably only used in SynQuiz
    userInfo(false, false, null),
    versionData(false, false, null),
    //preventWindowClose(false, true, null), // note: preventWindowClose should only be allowed once in the experiment element
    showColourReport(false, false, new FeatureAttribute[]{scoreThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    // @todo: groupMembers could be used to determing the sequence of who goes when and therefore could be changed to groupMembersSequence
    groupNetwork(false, false, new FeatureAttribute[]{groupMembers, groupCommunicationChannels, phasesPerStimulus}, false, false, false, Contitionals.groupNetworkActivity, Contitionals.stimulusAction),
    groupNetworkActivity(true, false, new FeatureAttribute[]{groupRole}, false, false, false, Contitionals.groupNetworkAction, Contitionals.groupNetworkActivity),
    groupMemberCodeLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMemberLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMessageLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    // todo: groupResponseStimulusImage could be changed to groupResponseStimulusPresent
    groupResponseStimulusImage(false, false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.groupNetworkAction),
    groupResponseFeedback(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.groupNetworkAction),
    groupScoreLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupChannelScoreLabel(false, false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    scoreLabel(false, false, new FeatureAttribute[]{styleName}),
    submitGroupEvent(false, false, null, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessageButton(false, true, new FeatureAttribute[]{hotKey, dataChannel, eventTag, repeatIncorrect, incrementPhase, styleName, groupId/* incrementPhaseOnDictionaryincrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupStoredMessage(false, false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupEndOfStimuli(false, false, new FeatureAttribute[]{eventTag}, false, false, false, Contitionals.none, Contitionals.groupNetworkActivity),
    clearCurrentScore(false, false, new FeatureAttribute[]{dataChannel}, false, false, false, Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    scoreIncrement(false, false, new FeatureAttribute[]{dataChannel, scoreValue}, false, false, false, Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    // todo: document that all score parameters provided in the attribute list must be above its threshold for this to evaluate as above threshold
    bestScoreAboveThreshold(false, false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    totalScoreAboveThreshold(false, false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    scoreAboveThreshold(false, false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    resetStimulus(false, false, new FeatureAttribute[]{target}, false, false, false, Contitionals.none, Contitionals.none),
    submitTestResults(false, false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    validateMetadata(false, new FeatureAttribute[]{/* take the fields from the vaidate section. matchingRegex, enabledRegex, dataLogFormat*/}, "Validates the current user id and the metadata fields (as listed in the administration section of the experiment configuration file) to the to the Frinex admin (unless another endpoint is defined in registrationUrlStaging and registrationUrlProduction of the listing.json), the corresponding return metadata fields from ths adminstration secion will be updated localy from the values previously saved in the admin system.", Contitionals.hasErrorSuccess, Contitionals.none),
    transmitResults(false, new FeatureAttribute[]{matchingRegex, enabledRegex, dataLogFormat}, "Transmits the values of the metadata fields that match the matchingRegex to the endpoint defined in registrationUrlStaging and registrationUrlProduction (listing.json), the metadata fields which match the enabledRegex will be updated if there is such a field returned from that service.", Contitionals.hasErrorSuccess, Contitionals.none);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final boolean canHaveStimulusTags; // todo: this could well be canHaveTagList so that it is more generic
    private final boolean canHaveRandomGrouping;
    private final boolean canHaveUndefinedAttribute;
    private final boolean allowsCustomImplementation;
    private final FeatureAttribute[] featureAttributes;
    private final Contitionals requiresChildType;
    private final Contitionals[] isChildType;
    private final String documentationText;

    public enum Contitionals {
        hasTrueFalseCondition(false),
        hasCorrectIncorrect(false),
        hasMoreStimulus(false),
        eachStimulus(false),
        stimulusAction(true),
        hasErrorSuccess(false),
        hasUserCount(false),
        hasThreshold(false),
        groupNetworkActivity(true),
        groupNetworkAction(true),
        hasMediaLoading(false),
        hasMediaLoadingButton(false),
        hasMediaPlayback(false),
        hasMediaRecorderPlayback(false),
        hasActionButtons(true),
        none(true);
//        needsConditionalParent // when true, the element cannot be used alone but must be in its conditional parent element
        final public boolean areChildenOptional;

        private Contitionals(boolean areChildenOptional) {
            this.areChildenOptional = areChildenOptional;
        }

    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = Contitionals.none;
        this.isChildType = new Contitionals[]{Contitionals.none};
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
        this.documentationText = null;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, final String documentationText) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = Contitionals.none;
        this.isChildType = new Contitionals[]{Contitionals.none};
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
        this.documentationText = documentationText;
    }

    private FeatureType(final boolean canHaveText, final FeatureAttribute[] featureAttributes, final String documentationText, final Contitionals requiresChildType, final Contitionals... isChildType) {
        this.canHaveFeatures = false;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = requiresChildType;
        this.isChildType = isChildType;
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
        this.documentationText = documentationText;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, final Contitionals requiresChildType, final Contitionals... isChildType) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.isChildType = isChildType;
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = false;
        if (requiresChildType != Contitionals.none && isChildType[0] != Contitionals.none
                && isChildType[0] != Contitionals.groupNetworkAction
                && isChildType[0] != Contitionals.groupNetworkActivity
                // todo: this does not consider other elements in the array, which at this point never exist, so update this if that changes
                && requiresChildType != Contitionals.stimulusAction
                && canHaveFeatures) {
            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
        }
        //    todo: set all hasMediaPlayback and hasMediaLoading to canHaveFeatures = false
        this.documentationText = null;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, Contitionals requiresChildType, Contitionals isChildType, final boolean allowsCustomImplementation) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.isChildType = new Contitionals[]{isChildType};
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = allowsCustomImplementation;
        if (requiresChildType != Contitionals.none && isChildType != Contitionals.none && canHaveFeatures) {
            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
        }
        this.documentationText = null;
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

    public Contitionals[] getIsChildType() {
        return isChildType;
    }

    public boolean isChildType(Contitionals value) {
        for (Contitionals contitional : isChildType) {
            if (contitional == value) {
                return true;
            }
        }
        return false;
    }

    public String getDocumentationText() {
        return documentationText;
    }
}
