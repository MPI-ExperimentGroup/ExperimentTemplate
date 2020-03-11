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
    image(false, new FeatureAttribute[]{src, styleName, msToNext}, false, false, false, Contitionals.hasMediaLoading, Contitionals.none),
    menuItem(false, true, new FeatureAttribute[]{target, hotKey, styleName}),
    //    popupMessage(true, true, null),
    withStimuli(false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.eachStimulus, Contitionals.none), // loop over all loaded stimuli rather than using next stimulus on user input
    // todo: document that Tags require all to exist on the stimuli "stimulus.getTags().containsAll(selectionTags)"
    loadStimulus(false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none, true),
    withMatchingStimulus(false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, matchingRegex}, false, false, false, Contitionals.hasMoreStimulus, Contitionals.stimulusAction),
    loadSdCardStimulus(false, new FeatureAttribute[]{eventTag, minStimuliPerTag, maxStimuliPerTag, maxStimuli, excludeRegex /* excludes files matching */, matchingRegex /* includes files matching */, replacementRegex /* generates stimuli code by regex replacement on the file path */, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none),
    //    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, false, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(false, new FeatureAttribute[]{}, true, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction), // todo: consider updating this to take a tags attribute rather than a stimuli element
    clearStimulusResponses(false, new FeatureAttribute[]{}, true, false, false, Contitionals.none, Contitionals.none),
    validateStimuliResponses(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    stimulusExists(false, new FeatureAttribute[]{offset}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    showStimuliReport(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    sendStimuliReport(false, new FeatureAttribute[]{type, dataChannel, headerKey, separator}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    targetButton(false, true, new FeatureAttribute[]{hotKey, target, styleName, groupId}, "Creates a button which when clicked navigates to the target presenter."),
    actionButton(true, new FeatureAttribute[]{hotKey, styleName, groupId}, "Creates a button which when clicked evaluates the contents of its element.", Contitionals.any, Contitionals.hasActionButtons, Contitionals.none),
    actionTokenButton(true, new FeatureAttribute[]{hotKey, styleName, groupId}, "Creates a button where any tokens in its label are evaluated before displaying. When clicked evaluates the contents of its element.", Contitionals.any, Contitionals.hasActionButtons, Contitionals.none),
    disableButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    enableButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    hideButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    showButtonGroup(false, false, new FeatureAttribute[]{matchingRegex}),
    stimulusButton(true, new FeatureAttribute[]{hotKey, dataChannel, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    touchInputStimulusButton(true, new FeatureAttribute[]{eventTag, dataChannel, src, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    //// todo: touch input needs a threshold before touch is registered and another before touch is ended to allow gaps in touch being recorded as on touch
    touchInputCaptureStart(false, new FeatureAttribute[]{showControls, msToNext}, false, false, false, Contitionals.any, Contitionals.stimulusAction), /* sub elements are triggered after the touch ends or after msToNext of no touch activity */
    touchInputReportSubmit(false, new FeatureAttribute[]{dataChannel}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    ratingButton(false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, /* document the automaticly generated hot key listners for numbers and some letters z . */ /*"If groupId contains tokens they will be replaced with the respective values.", */ false, false, false, Contitionals.any, Contitionals.stimulusAction),
    ratingRadioButton(false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false,/*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    ratingCheckbox(false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, /*"If groupId contains tokens they will be replaced with the respective values.", */ false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusFreeText(true, new FeatureAttribute[]{validationRegex, dataChannel, allowedCharCodes, hotKey, styleName, inputErrorMessage, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction), // the hotKey in stimulusFreeText will trigger any button with the same hotkey. // todo: The current use of the featureText attribute could be changed to allowedCharErrorMessage and inputErrorMessage could be changed to validationErrorMessage
    stimulusRatingButton(false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false,/*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    stimulusRatingRadio(false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusRatingCheckbox(false, new FeatureAttribute[]{dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusHasRatingOptions(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    stimulusHasResponse(false, new FeatureAttribute[]{groupId, matchingRegex}, "When groupId is omitted conditionTrue will trigger if the current stimulus has any response. If groupId and matchingRegex are provided this will only trigger if a the current stimulus has a response in that group that matches the regex. If groupId contains tokens they will be replaced with the respective values before the evaluation.", Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    setStimulusCodeResponse(false, new FeatureAttribute[]{codeFormat, dataChannel, applyScore, groupId}, "Sets a response to the current stimulus with the value of codeFormat. The groupId determines the stimulus response group so that multiple responses can exist for each stimulus. If groupId or codeFormat contain tokens they will be replaced with the respective values.", Contitionals.none, Contitionals.stimulusAction),
    addStimulusCodeResponseValidation(true, new FeatureAttribute[]{validationRegex, dataChannel, groupId}, "Adds a stimulus validation listener for StimulusCodeResponses, so that they can be validated like other stimulus response types. The response is validated based on the matching of the validationRegex. If the validation fails the featureText will be shown to hint the user what is required. The groupId determines the stimulus response group so that multiple responses can exist for each stimulus. If groupId contain tokens they will be replaced with the respective values.",/* this validation is specific to the StimulusCodeResponses*/ Contitionals.none, Contitionals.stimulusAction),
    ratingFooterButton(false, new FeatureAttribute[]{dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, styleName, groupId}, false, false, false, /*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    targetFooterButton(false, true, new FeatureAttribute[]{target, styleName, groupId}, "Creates a button in the footer which when clicked navigates to the target presenter."),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey, styleName, groupId}, "Creates a button in the footer which when clicked evaluates the contents of its element."),
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
    stimulusMetadataField(false, new FeatureAttribute[]{fieldName, dataChannel}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
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

    saveMetadataButton(true, new FeatureAttribute[]{sendData, networkErrorMessage, styleName, groupId}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    createUserButton(false, true, new FeatureAttribute[]{target, styleName, groupId}, "Creates a button that when clicked creates a new user and then navigates to the target presenter as the new user."),
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
    showStimulusProgress(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    @Deprecated // ???
    //    hideStimulusButtons(false, false, null),
    //    @Deprecated // ???
    //    showStimulusButtons(false, false, null),
    displayCompletionCode(false, false, null),
    generateCompletionCode(false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendAllData(false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    sendMetadata(false, null, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    redirectToUrl(false, false, new FeatureAttribute[]{src}),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    removeStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    removeMatchingStimulus(false, new FeatureAttribute[]{matchingRegex}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    clearStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    centrePage(false, false, null),
    clearPage(false, false, new FeatureAttribute[]{styleName}),
    backgroundImage(true, false, new FeatureAttribute[]{msToNext, src, styleName}),
    allMenuItems(false, false, new FeatureAttribute[]{styleName}),
    prevStimulusButton(true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextStimulusButton(true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextStimulus(false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    prevStimulus(false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    skipStimulus(false, false, new FeatureAttribute[]{offset /* when offset is < 1 the stimuli will not repeat, when it is > 0 the stimulus will be shown again after X items or at the end of the list, which ever is the lesser */}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextMatchingStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    hasGetParameter(false, new FeatureAttribute[]{parameterName}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    hasMetadataValue(false, new FeatureAttribute[]{fieldName, matchingRegex}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    setMetadataValue(false, new FeatureAttribute[]{fieldName, dataLogFormat, replacementRegex}, "The value of dataLogFormat will have any string tokens replaced. Next if the replacementRegex is provided then the regex is applied and only the values of the regex capture groups will be kept, other wise the entire string is used. The result is then stored in the specified metadata field.", Contitionals.none, Contitionals.none),
    progressIndicator(false, new FeatureAttribute[]{evaluateTokens, styleName}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    setMetadataEvalTokens(false, new FeatureAttribute[]{fieldName, evaluateTokens}, "The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The resulting number is then stored in the specified metadata field.", Contitionals.hasErrorSuccess, Contitionals.none),
    activateRandomItem(false, false, new FeatureAttribute[]{}, "Randomly activates one menu item on the current presenter providing that the target presenter has not already been completed. If all targets have been completed then the user will be sent to the next presenter as specified by the current presenter."),
    gotoPresenter(false, false, new FeatureAttribute[]{target}),
    gotoNextPresenter(false, false, new FeatureAttribute[]{}),
    logTimeStamp(false, new FeatureAttribute[]{eventTag}, "Records a timestamp similar to the way image and media onLoad events etc. are logged. The logged ms is relative to the load time of the presenter.", Contitionals.none, Contitionals.none),
    // todo: document audioButton which fires the played event once and only once after the first playback finishes
    audioButton(false, new FeatureAttribute[]{eventTag, dataChannel, poster, autoPlay, hotKey, styleName, src, groupId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    preloadAllStimuli(false, null, true, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    showStimulus(false, null, false, false, false, Contitionals.any, Contitionals.stimulusAction), // todo: should this be here? or should it have an increment for next back etc
    showStimulusGrid(false, new FeatureAttribute[]{maxStimuli, dataChannel, columnCount, imageWidth, eventTag, animate}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    matchingStimulusGrid(false, new FeatureAttribute[]{columnCount, dataChannel, maxWidth, animate, matchingRegex, maxStimuli, randomise}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    pause(true, false, new FeatureAttribute[]{msToNext}),
    requestNotification(true, new FeatureAttribute[]{fieldName, targetOptions, dataLogFormat}, "Request mobile notifications at given times in the future on Android and iOS apps. The presenters listed in targetOptions need to be comma separated and will be shown as buttons in the notification.", Contitionals.hasErrorSuccess, Contitionals.none),
    // todo: consider renaming so that timer is the first part: timerStart, timerCompare, timerClear, timerLog
    startTimer(true, false, new FeatureAttribute[]{msToNext, listenerId}), // the start time of the first instance of startTimer is persistent over page loads and navigation, but the content of the startTimer element is scoped to the presenter and will not fire outside of the presenter, each startTimer tag will create a callback that will trigger when the timer passes the ms value, but will not fire on reload if that time has already passed.
    compareTimer(false, new FeatureAttribute[]{msToNext, listenerId}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    //    compareDateFormat(false, false, new FeatureAttribute[]{dateFormat, matchingRegex}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    clearTimer(false, false, new FeatureAttribute[]{listenerId}),
    logTimerValue(false, false, new FeatureAttribute[]{listenerId, eventTag, dataChannel}),
    timerLabel(false, false, new FeatureAttribute[]{listenerId, msToNext, msLabelFormat, styleName}), // this is a label like countdownLabel but shows the time value of listenerId minus msToNext and formats the result with msLabelFormat
    randomMsPause(true, false, new FeatureAttribute[]{minimum, maximum, ranges}),//"1..100", "1,100" ,"1..3,10,20,30..35,60"
    // todo: does triggerListener maximum value of 0 allow infinit? document this
    triggerDefinition(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}, "The contents of this element will be activated when matched by the listenerId attribute of trigger or triggerRandom (for example), providing the number of trigger occurences has reached the threshold and within the maximum."),
    habituationParadigmListener(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}), //  threshold is in ms eg 2000 is the minimum length of an event to be considered, maximum is the max shows eg 10.
    triggerMatching(false, false, new FeatureAttribute[]{listenerId}, "Trigger all triggerListeners matching the listenerId providing the threshold and maximum values are within the required values. The listenerId can contain tokens eg %lt;stimulusCode&gt;."),
    triggerRandom(true, false, new FeatureAttribute[]{matchingRegex}, "Randomly trigger one any of the triggerListeners where the listenerId matches the matchingRegex and its maximum trigger count has not been reached. When there are no triggerListeners that match these criteria the child contents of tag will be triggered."),
    resetTrigger/* todo: consider changing to triggerReset */(false, false, new FeatureAttribute[]{listenerId}, "Reset the threshold and maximum counters for triggerListeners matching the listenerId."),
    countdownLabel(true, true, new FeatureAttribute[]{msToNext, msLabelFormat, styleName}),
    stimulusPause(false, null, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    onError(false, null, false, false, false, Contitionals.any, Contitionals.hasErrorSuccess),
    onSuccess(false, null, false, false, false, Contitionals.any, Contitionals.hasErrorSuccess),
    onActivate(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaLoadingButton),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    conditionTrue(false, null, false, false, false, Contitionals.any, Contitionals.hasTrueFalseCondition),
    conditionFalse(false, null, false, false, false, Contitionals.any, Contitionals.hasTrueFalseCondition),
    responseCorrect(false, new FeatureAttribute[]{}, false, false, false, Contitionals.any, Contitionals.hasCorrectIncorrect),
    responseIncorrect(false, new FeatureAttribute[]{}, false, false, false, Contitionals.any, Contitionals.hasCorrectIncorrect),
    hasMoreStimulus(false, null, false, false, false, Contitionals.stimulusAction, Contitionals.hasMoreStimulus),
    endOfStimulus(false, null, false, false, false, Contitionals.any, Contitionals.hasMoreStimulus),
    beforeStimulus(false, null, false, false, false, Contitionals.any, Contitionals.eachStimulus),
    eachStimulus(false, null, false, false, false, Contitionals.stimulusAction, Contitionals.eachStimulus),
    afterStimulus(false, null, false, false, false, Contitionals.any, Contitionals.eachStimulus),
    existingUserCheck(false, null, false, false, false, Contitionals.hasUserCount, Contitionals.none),
    multipleUsers(false, null, false, false, false, Contitionals.any, Contitionals.hasUserCount),
    // todo: this should be suppressed from normal use like the other conditional child elements
    singleUser(false, null, false, false, false, Contitionals.any, Contitionals.hasUserCount),
    aboveThreshold(false, null, false, false, false, Contitionals.any, Contitionals.hasThreshold),
    withinThreshold(false, null, false, false, false, Contitionals.any, Contitionals.hasThreshold),
    mediaLoaded(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaLoading, Contitionals.hasMediaRecorderPlayback),
    mediaLoadFailed(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaLoading, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackStarted(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackComplete(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    //    clearRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    //    updateRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    table(true, false, new FeatureAttribute[]{styleName, showOnBackButton}),
    row(true, false, null),
    column(true, false, new FeatureAttribute[]{styleName}),
    regionAppend(true, false, new FeatureAttribute[]{regionId, styleName}, "Starts or resumes a region, allowing contents to be added to a given location in the presenter. If no region matching the regionId exists then a new region is created in the current location and then appended to. If a region of the same regionId already exists then it will be be appended to."),
    regionStyle(false, false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then it will have the style applied. If no region matching exists then nothing will be done."),
    regionCodeStyle(false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then the styleName will have any tokens evaluated before the resulting style name is applied to the region. If no region matching exists then nothing will be done.", Contitionals.none, Contitionals.stimulusAction),
    regionReplace(true, false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then it will cleared. If no region matching the regionId exists then a new region is created in the current location. The resulting region is then appended to."),
    regionClear(false, false, new FeatureAttribute[]{regionId}),
    // todo: look for and update to add the show any stimuli tag and make stimulusImage only show images (true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaLoading), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusPresent(false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate, replacementRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusImage(false, new FeatureAttribute[]{msToNext, styleName, dataChannel}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusCodeImage(false, new FeatureAttribute[]{msToNext, dataChannel, codeFormat, styleName}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeImageButton(false, new FeatureAttribute[]{dataChannel, codeFormat, styleName, groupId}, false, false, false, Contitionals.hasMediaLoadingButton, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeVideo(false, new FeatureAttribute[]{dataChannel, maxHeight, codeFormat, percentOfPage, loop, styleName, autoPlay, showControls, maxWidth, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusVideo(false, new FeatureAttribute[]{dataChannel, loop, styleName, autoPlay, showControls, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusCodeAudio(false, new FeatureAttribute[]{dataChannel, codeFormat, showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusAudio(false, new FeatureAttribute[]{dataChannel, showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    playMedia(false, false, new FeatureAttribute[]{mediaId}),
    rewindMedia(false, false, new FeatureAttribute[]{mediaId}),
    pauseMedia(false, false, new FeatureAttribute[]{mediaId}),
    stimulusImageCapture(true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    //    captureStimulusImage(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth}),
    VideoPanel(false, new FeatureAttribute[]{src, percentOfPage, maxHeight, maxWidth, poster}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    AnnotationTimelinePanel(false, new FeatureAttribute[]{src, poster, eventTag, columnCount, maxStimuli}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    startAudioRecorderWeb(false, new FeatureAttribute[]{downloadPermittedWindowMs, deviceRegex, mediaId}, "Starts the HTML5 audio recorder which will send the recorded audio to the admin database. If downloadPermittedWindowMs is given a suitable period of time, the user can also listen to the recorded audio from the server for verification.", Contitionals.hasMediaRecorderPlayback, Contitionals.stimulusAction),
    startAudioRecorderApp(false, new FeatureAttribute[]{filePerStimulus, eventTag, fieldName}, "Starts the Android wav recorder, the recorded wav files will be saved on the device in a sub directories based on value of the provided metadata field and the eventTag.", Contitionals.hasErrorSuccess, Contitionals.stimulusAction),
    stopAudioRecorder(false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    stopAudioRecorder(false, false, new FeatureAttribute[]{minimum, maximum, average}, false, false, false, Contitionals.hasThreshold, Contitionals.stimulusAction),
    startAudioRecorderTag(false, new FeatureAttribute[]{eventTier}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    endAudioRecorderTag(false, new FeatureAttribute[]{eventTier, eventTag}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    showHtmlPopup(true, new FeatureAttribute[]{}, false, false, false, Contitionals.hasActionButtons, Contitionals.none),
    helpDialogue(false, true, new FeatureAttribute[]{closeButtonLabel}), // helpDialogue is probably only used in SynQuiz
    userInfo(false, false, null),
    versionData(false, false, null),
    //preventWindowClose(false, true, null), // note: preventWindowClose should only be allowed once in the experiment element
    showColourReport(false, new FeatureAttribute[]{scoreThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    // @todo: groupMembers could be used to determing the sequence of who goes when and therefore could be changed to groupMembersSequence
    groupNetwork(false, new FeatureAttribute[]{groupMembers, groupCommunicationChannels, phasesPerStimulus}, false, false, false, Contitionals.groupNetworkActivity, Contitionals.stimulusAction),
    groupNetworkActivity(false, new FeatureAttribute[]{groupRole}, false, false, false, Contitionals.groupNetworkAction, Contitionals.groupNetworkActivity),
    groupMemberCodeLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMemberLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMessageLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    // todo: groupResponseStimulusImage could be changed to groupResponseStimulusPresent
    groupResponseStimulusImage(false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.groupNetworkAction),
    groupResponseFeedback(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.groupNetworkAction),
    groupScoreLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupChannelScoreLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    scoreLabel(false, false, new FeatureAttribute[]{styleName}),
    submitGroupEvent(false, null, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessageButton(true, new FeatureAttribute[]{hotKey, dataChannel, eventTag, repeatIncorrect, incrementPhase, styleName, groupId/* incrementPhaseOnDictionaryincrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessage(false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupStoredMessage(false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupEndOfStimuli(false, new FeatureAttribute[]{eventTag}, false, false, false, Contitionals.none, Contitionals.groupNetworkActivity),
    clearCurrentScore(false, new FeatureAttribute[]{dataChannel}, false, false, false, Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    scoreIncrement(false, new FeatureAttribute[]{dataChannel, scoreValue}, false, false, false, Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    // todo: document that all score parameters provided in the attribute list must be above its threshold for this to evaluate as above threshold
    bestScoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    totalScoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    scoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    resetStimulus(false, new FeatureAttribute[]{target}, false, false, false, Contitionals.none, Contitionals.none),
    submitTestResults(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    validateMetadata(false, new FeatureAttribute[]{/* take the fields from the vaidate section. matchingRegex, enabledRegex, dataLogFormat*/}, "Validates the current user id and the metadata fields (as listed in the administration section of the experiment configuration file) to the to the Frinex admin (unless another endpoint is defined in registrationUrlStaging and registrationUrlProduction of the listing.json), the corresponding return metadata fields from ths adminstration secion will be updated localy from the values previously saved in the admin system.", Contitionals.hasErrorSuccess, Contitionals.none),
    transmitResults(false, new FeatureAttribute[]{matchingRegex, enabledRegex, dataLogFormat}, "Transmits the values of the metadata fields that match the matchingRegex to the endpoint defined in registrationUrlStaging and registrationUrlProduction (listing.json), the metadata fields which match the enabledRegex will be updated if there is such a field returned from that service.", Contitionals.hasErrorSuccess, Contitionals.none);
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
        none(true),
        any(true);
//        needsConditionalParent // when true, the element cannot be used alone but must be in its conditional parent element
        final public boolean areChildenOptional;

        private Contitionals(boolean areChildenOptional) {
            this.areChildenOptional = areChildenOptional;
        }

    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = (canHaveFeatures) ? Contitionals.any : Contitionals.none;
        this.isChildType = new Contitionals[]{Contitionals.none};
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
        this.documentationText = null;
    }

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes, final String documentationText) {
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = (canHaveFeatures) ? Contitionals.any : Contitionals.none;
        this.isChildType = new Contitionals[]{Contitionals.none};
        this.canHaveStimulusTags = false;
        this.canHaveRandomGrouping = false;
        this.canHaveUndefinedAttribute = false;
        this.allowsCustomImplementation = false;
        this.documentationText = documentationText;
    }

    private FeatureType(final boolean canHaveText, final FeatureAttribute[] featureAttributes, final String documentationText, final Contitionals requiresChildType, final Contitionals... isChildType) {
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

    private FeatureType(boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, final Contitionals requiresChildType, final Contitionals... isChildType) {
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.isChildType = isChildType;
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = false;
//        if (requiresChildType != Contitionals.none && isChildType[0] != Contitionals.none
//                && isChildType[0] != Contitionals.groupNetworkAction
//                && isChildType[0] != Contitionals.groupNetworkActivity
//                // todo: this does not consider other elements in the array, which at this point never exist, so update this if that changes
//                && requiresChildType != Contitionals.stimulusAction
//                && canHaveFeatures) {
//            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
//        }
        //    todo: set all hasMediaPlayback and hasMediaLoading to canHaveFeatures = false
        this.documentationText = null;
    }

    private FeatureType(boolean canHaveText, FeatureAttribute[] featureAttributes, boolean canHaveStimulus, boolean canHaveRandomGrouping, boolean canHaveUndefinedAttribute, Contitionals requiresChildType, Contitionals isChildType, final boolean allowsCustomImplementation) {
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
        this.canHaveStimulusTags = canHaveStimulus;
        this.canHaveRandomGrouping = canHaveRandomGrouping;
        this.requiresChildType = requiresChildType;
        this.isChildType = new Contitionals[]{isChildType};
        this.canHaveUndefinedAttribute = canHaveUndefinedAttribute;
        this.allowsCustomImplementation = allowsCustomImplementation;
//        if (requiresChildType != Contitionals.none && isChildType != Contitionals.none && canHaveFeatures) {
//            throw new IllegalArgumentException("canHaveFeatures may only be used with Contitionals.none");
//        }
        this.documentationText = null;
        //    todo: set all hasMediaPlayback and hasMediaLoading to canHaveFeatures = false
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
