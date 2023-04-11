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
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum FeatureType {

    htmlText(false, true, new FeatureAttribute[]{styleName}, "The contents of featureText will be shown as HTML and styled with styleName if provided"),
    htmlTokenText(false, true, new FeatureAttribute[]{styleName}, "When the featureText contains string tokens they will be replaced with the relevant values, eg score values &lt;groupScore&gt; &lt;channelScore&gt; etc.") /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */,
    evaluateTokenText(false, new FeatureAttribute[]{evaluateTokens, styleName}, "The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The result be shown as HTML and styled with styleName if provided.", Contitionals.hasErrorSuccess, Contitionals.none),
    logTokenText(false, false, new FeatureAttribute[]{dataChannel, type, headerKey, dataLogFormat}, "Logs data to the tag pair data table. The value of dataLogFormat will have any string tokens replaced and stored in the TagValue2 column. When provided the value of type and headerKey will be stored in the EventTag	and TagValue1 columns respectively.") /* string tokens will be replaced with score values eg <groupScore> <channelScore> etc. */, // if headerKey is not provided then the stimulus id will be used // todo: headerKey is perhaps in conflict with sendStimuliReport where headerKey is used by the administration and perhaps should be moved to the administration element
    plainText(false, true, null),
    image(false, new FeatureAttribute[]{src, styleName, msToNext}, false, false, false, Contitionals.hasMediaLoading, Contitionals.none),
    menuItem(false, true, new FeatureAttribute[]{target, hotKey, styleName}), // TODO: menuItem could take groupId to allow for multiple menus and adding of items subsequent to the menu being created
    //    popupMessage(true, true, null),
    withStimuli(false, new FeatureAttribute[]{eventTag, /*minStimuliPerTag,*/ /*maxStimuliPerTag,*/ maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.eachStimulus, Contitionals.none), // loop over all loaded stimuli rather than using next stimulus on user input
    groupStimuli(false, new FeatureAttribute[]{eventTag, /*minStimuliPerTag,*/ /*maxStimuliPerTag,*/ maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.groupStimulus, Contitionals.none),
    // todo: document that Tags require all to exist on the stimuli "stimulus.getTags().containsAll(selectionTags)"
    loadStimulus(false, new FeatureAttribute[]{eventTag, /*minStimuliPerTag,*/ /*maxStimuliPerTag,*/ maxStimuli, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none, true),
    withMatchingStimulus(false, new FeatureAttribute[]{eventTag, maxStimuli, randomise, repeatCount, repeatRandomWindow, matchingRegex}, false, false, false, Contitionals.hasMoreStimulus, Contitionals.stimulusAction),
    loadSdCardStimulus(false, new FeatureAttribute[]{eventTag, /*minStimuliPerTag,*/ /*maxStimuliPerTag,*/ maxStimuli, excludeRegex /* excludes files matching */, matchingRegex /* includes files matching */, replacementRegex /* generates stimuli code by regex replacement on the file path */, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, true, true, Contitionals.hasMoreStimulus, Contitionals.none),
    //    loadAllStimulus(false, false, new FeatureAttribute[]{eventTag, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold}, true, false, false, Contitionals.hasMoreStimulus),
    currentStimulusHasTag(false, new FeatureAttribute[]{}, true, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction), // todo: consider updating this to take a tags attribute rather than a stimuli element
    clearStimulusResponses(new FeatureAttribute[]{}, true, "Clears all locally stored responses to all stimulus that have all the supplied tags. Stimuli that do not have ALL the provided tags will not be affected.", Contitionals.none, Contitionals.none),
    validateStimuliResponses(new FeatureAttribute[]{}, false, "Validates the current stimulus input that is visible in the presenter. Only when the stimulus input is enabled and visible will its validity be considered. After validation the stimulus response can be accessed via htmlTokenText and similar methods.", Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    stimulusExists(false, new FeatureAttribute[]{offset}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    showStimuliReport(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    sendStimuliReport(false, new FeatureAttribute[]{type, dataChannel, headerKey, separator}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    targetButton(false, true, new FeatureAttribute[]{hotKey, target, styleName, groupId}, "Creates a button which when clicked navigates to the target presenter."),
    hotKeyInput(false, new FeatureAttribute[]{hotKey, groupId}, "Creates a key listener that will respond to keyboard or bluetooth button input. This input will not be visible but can be enabled or disabled with enableButtonGroup and disableButtonGroup. The behaviour of this feature will be to some degree platform dependant, for example the behaviour of key repeat and the certainty of onKeyUp being triggered.", Contitionals.hasKeyInputsCondition, Contitionals.none),
    actionButton(true, new FeatureAttribute[]{eventTag, hotKey, styleName, groupId}, "Creates a button which when clicked evaluates the contents of its element.", Contitionals.any, Contitionals.hasActionButtons, Contitionals.none),
    actionTokenButton(true, new FeatureAttribute[]{eventTag, hotKey, styleName, groupId}, "Creates a button where any tokens in its label are evaluated before displaying. When clicked evaluates the contents of its element.", Contitionals.any, Contitionals.hasActionButtons, Contitionals.none),
    disableButtonGroup(false, new FeatureAttribute[]{matchingRegex}, "Disables any buttons with a groupId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    enableButtonGroup(false, new FeatureAttribute[]{matchingRegex}, "Enables any buttons with a groupId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    hideButtonGroup(false, new FeatureAttribute[]{matchingRegex}, "Hides any buttons with a groupId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    styleButtonGroup(false, new FeatureAttribute[]{matchingRegex, styleName}, "Applies the provided style to any buttons with a groupId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    showButtonGroup(false, new FeatureAttribute[]{matchingRegex}, "Shows any buttons with a groupId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    requestFocus(false, new FeatureAttribute[]{matchingRegex}, "Requests focus to a single input specified by the matchingRegex if it exists and is visible. Only one input can have focus at a time so if multiple inputs match then the rest will be fail to gain focus.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    svgLoadGroups(false, new FeatureAttribute[]{src}, "Load compiled SVG data for use as image groups. Each group must be named in the SVG file.", Contitionals.svgGroupsLoaded, Contitionals.none),
    svgGroupAdd(false, new FeatureAttribute[]{groupId, visible}, "Appends the named section of the loaded SVG into the presenter.", Contitionals.none, Contitionals.svgGroupsLoaded),
    //    svgAddMarker(false, new FeatureAttribute[]{groupId}, "Appends an overlay of horizontal and vertical lines which move in response to user input relative to the provided groupId.", Contitionals.any, Contitionals.svgGroupsLoaded),
    svgSetLabel(false, new FeatureAttribute[]{groupId, evaluateTokens}, "Sets the text value of the SVG text element matching groupId.", Contitionals.none, Contitionals.svgGroupsLoaded),
    svgGroupShow(false, new FeatureAttribute[]{groupId, visible}, "Sets the visibility of the section of SVG which must have already been added to the presenter.", Contitionals.none, Contitionals.svgGroupsLoaded),
    svgGroupAction(false, new FeatureAttribute[]{groupId}, "Sets a button action to the image group as specified in the loaded SVG data.", Contitionals.any, Contitionals.svgGroupsLoaded),
    svgGroupMatching(false, new FeatureAttribute[]{groupId, visible, evaluateTokens}, "Sets the visibility of the matching child elements of the group which must have already been added to the presenter.", Contitionals.none, Contitionals.svgGroupsLoaded),
    stimulusButton(true, new FeatureAttribute[]{eventTag, hotKey, dataChannel, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusSlider(false, new FeatureAttribute[]{dataChannel, styleName, groupId, initial, minimum, maximum, orientation}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    touchInputLabelButton(false, new FeatureAttribute[]{eventTag, codeFormat, styleName, groupId}, "Creates touch input label button for use on tablets/mobile devices which responds to touch input from the participant.", Contitionals.any, Contitionals.touchInputStartType), // this must be a child of touchInputCapture but also allow nesting in that parent
    touchInputImageButton(false, new FeatureAttribute[]{eventTag, codeFormat, styleName, groupId}, "Creates touch input image button for use on tablets/mobile devices which responds to touch input from the participant.", Contitionals.hasMediaLoadingButton, Contitionals.touchInputStartType), // this must be a child of touchInputCapture but also allow nesting in that parent
    touchInputVideoButton(false, new FeatureAttribute[]{eventTag, codeFormat, loop, styleName, autoPlay, groupId, mediaId}, "Creates touch input video button for use on tablets/mobile devices which responds to touch input from the participant.", Contitionals.hasMediaLoadingButton, Contitionals.touchInputStartType), // this must be a child of touchInputCapture but also allow nesting in that parent
    //// todo: touch input needs a threshold before touch is registered and another before touch is ended to allow gaps in touch being recorded as on touch
    touchInputCapture(false, new FeatureAttribute[]{showControls, dataChannel}, "Starts logging touch input of the participant.", Contitionals.touchInputCaptureType, Contitionals.stimulusAction), /* sub elements are triggered after the touch ends or after msToNext of no touch activity */
    captureStart(false, new FeatureAttribute[]{}, "When the touch input capture has started the contents of this element will be evaluated.", Contitionals.touchInputStartType, Contitionals.touchInputCaptureType),
    touchEnd(false, new FeatureAttribute[]{msToNext}, "When the touch input capture anywhere on the screen has ended the contents of this element will be evaluated. If msToNext to next is provided then a gap of that ms time is permitted between touches without triggering the touchEnd.", Contitionals.any, Contitionals.touchInputCaptureType),
    touchInputStop(false, new FeatureAttribute[]{}, "Stops the touch input capture and submits the logged touch input of the participant since touch input capture was started and then stops the input capture. This action is implicit but this feature allows the capture to be terminated early.", Contitionals.none, Contitionals.touchInputStartType),
    ratingButton(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, /* document the automaticly generated hot key listeners for numbers and some letters z . */ /*"If groupId contains tokens they will be replaced with the respective values.", */ false, false, false, Contitionals.any, Contitionals.stimulusAction),
    ratingRadioButton(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false /* TODO: "Shows rating buttons" the value is stored by stimulus and groupId so if you use a different groupId in each presenter the initial value for each presenter will be blank *//* why ratingRadioButtons are already filled in when they are first loaded on the page? And they copy the last filled in ratingRadioButton of the previous presenter.*/,/*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    ratingCheckbox(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId},/* TODO: the difference between ratingCheckbox and stimulusRatingCheckbox is where the ratings come from because they share the same code */ /* both stimulusRatingCheckbox and stimulusRatingRadio share the same code so the documentation is much the same */ /*"If groupId contains tokens they will be replaced with the respective values.", */ false, false, false, Contitionals.any, Contitionals.stimulusAction),
    // TODO: stimulusFreeText could be made clearer because the attribute featureText of stimulusFreeText is the error message displayed when not conforming to the regex and there is also the input key restriction message inputErrorMessage, these messages could have more obvious error text attributes
    // TODO: the current use of the featureText attribute could be changed to allowedCharErrorMessage and inputErrorMessage could be changed to validationErrorMessage
    stimulusFreeText(true, new FeatureAttribute[]{validationRegex, dataChannel, allowedCharCodes, hotKey, styleName, inputErrorMessage, groupId}, "Adds a free text input for stimulus responses to be entered. If allowedCharCodes is provided then only those characters can be entered into the text box, if a disallowed character is entered then the inputErrorMessage will be shown. The stimulus response text is validated against the validationRegex which can also contain tokens such as &lt;stimulusCorrectResponses&gt;. If the stimulus response text does not match the validationRegex then the featureText message will be shown on validation. If a hotKey is provided and its value corresponds to the hotKey of a button in the presenter then that button will be activated by that key in the free text field. If the hotKey=\"ENTER\" then the text input will be limited to a single line (because only one line can be entered without the enter key).", Contitionals.none, Contitionals.stimulusAction),
    stimulusRatingButton(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false,/*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    stimulusRatingRadio(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusRatingCheckbox(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabelLeft, ratingLabelRight, orientation, styleName, groupId}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusHasRatingOptions(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    clearStimulusResponse(false, new FeatureAttribute[]{groupId}, "Clears the locally stored response to the current stimulus. If groupId is provided then only the matching response group will be cleared, otherwise all responses for that stimulus are cleared.", Contitionals.none, Contitionals.stimulusAction),
    stimulusHasResponse(false, new FeatureAttribute[]{groupId, matchingRegex}, "When groupId is omitted conditionTrue will trigger if the current stimulus has any response. If groupId and matchingRegex are provided this will only trigger if a the current stimulus has a response in that group that matches the regex. If groupId contains tokens they will be replaced with the respective values before the evaluation.", Contitionals.hasTrueFalseCondition, Contitionals.stimulusAction),
    setStimulusCodeResponse(false, new FeatureAttribute[]{codeFormat, dataChannel, applyScore, groupId}, "Sets a response to the current stimulus with the value of codeFormat. The groupId determines the stimulus response group so that multiple responses can exist for each stimulus. If groupId or codeFormat contain tokens they will be replaced with the respective values.", Contitionals.none, Contitionals.stimulusAction),
    addStimulusCodeResponseValidation(true, new FeatureAttribute[]{validationRegex, dataChannel, groupId}, "Adds a stimulus validation listener for StimulusCodeResponses, so that they can be validated like other stimulus response types. The response is validated based on the matching of the validationRegex. If the validation fails the featureText will be shown to hint the user what is required. The groupId determines the stimulus response group so that multiple responses can exist for each stimulus. If groupId contain tokens they will be replaced with the respective values.",/* this validation is specific to the StimulusCodeResponses*/ Contitionals.none, Contitionals.stimulusAction),
    ratingFooterButton(false, new FeatureAttribute[]{eventTag, dataChannel, ratingLabels, ratingLabelLeft, ratingLabelRight, styleName, groupId}, false, false, false, /*"If groupId contains tokens they will be replaced with the respective values.", */ Contitionals.any, Contitionals.stimulusAction),
    targetFooterButton(false, true, new FeatureAttribute[]{target, styleName, groupId}, "Creates a button in the footer which when clicked navigates to the target presenter."),
    actionFooterButton(true, true, new FeatureAttribute[]{eventTag, hotKey, styleName, groupId}, "Creates a button in the footer which when clicked evaluates the contents of its element."),
    //    endOfStimulusButton(false, true, new FeatureAttribute[]{eventTag, target}),
    addPadding(false, false, null),
    localStorageData(false, false, null, "For use in the debug type presenter to show the data stored in the local storage. Use with caution because there can be a lot of data."),
    stimuliValidation(false, false, null, "For use in the debug type presenter to show the results of stimuli randomisation for each selection in the configuration file."),
    addKeyboardDebug(false, false, null, "For use in the debug type presenter to show the keycodes entered by devices attached to the device. Such as bluetooth remotes or pointers."),
    // metadataField fields:
    //    displays all or one metadata field for data entry
    allMetadataFields(false, false, null, "Adds an input for all metadata fields. Equivalent to metadataField for each field."),
    metadataField(false, false, new FeatureAttribute[]{fieldName}, "Adds a single metadata field input. The type of input depends on the regex of the metadata field. "
            + "A date input will be used if the regex is [0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]. "
            + "A checkbox will be used if the regex is true|false. "
            + "A text area will be used (multiline) rather than a text box (single line) if the regex contains \\\\s. "
            + "A select box will be used if the regex is a | separated list (except when { is used indicating a regular expression)."),
    // metadataField stimulus fields:
    //    a metadata field is available for each stimulus
    stimulusMetadataField(false, new FeatureAttribute[]{fieldName, dataChannel}, "Adds a metadata field input for the current stimulus. The user entered value will be stored in the stimulus data identifiable via the postName of the field.", Contitionals.none, Contitionals.stimulusAction),
    // metadataField linking fields:
    //    which make a connection between entities
    //    which allow data entry to describe the connection between entities
    //    linkedFieldName is perhaps ambugious since suggests it is the link, but it is the label field for the linked entity
    metadataFieldConnection(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, oneToMany}), // oneToMany determines cardinality so that when the field is populated another will be offered so that lists of data can be entered for a given field
    // metadataField linked fields:
    //    when one value determines the default value of the other
    //    when one value determines the pressence of the other
    metadataFieldVisibilityDependant(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, visibleRegex, enabledRegex}, "Adds a metadata field input, the state of which is linked to the value of another input field. When value of the linked input field matches the visibleRegex this input field will be visible, otherwise it will be hidden. If the value of the linked input field matches the enabledRegex this input field will be enabled, otherwise it will be disabled to prevent input."),
    metadataFieldDateTriggered(false, false, new FeatureAttribute[]{fieldName, linkedFieldName, daysThresholds, visibleRegex, enabledRegex}, "Adds a metadata field input, the value and state of which is linked to the value of a date input field. This field must be a selection list type and the selected item is determined by a corresponding item in the daysThresholds which is a list of values representing the days since the other field date. The intended use is to trigger the selection of the item index of this metadataField based on the calculated age in days from linkedFieldName. When value of the linked input field matches the visibleRegex this input field will be visible, otherwise it will be hidden. If the value of the linked input field matches the enabledRegex this input field will be enabled and the user can modify the selected value, otherwise it will be disabled to prevent the date assigned value being changed."),
    saveMetadataButton(true, new FeatureAttribute[]{sendData, networkErrorMessage, styleName, groupId}, "Checks the currently displayed metadata fields for valid input. If there are errors the relevant field input is highlighted. If sendData is true then the metadata is sent to the admin system before proceeding and network errors will trigger an onError. If sendData is false onSuccess will be triggered if the metadata is valid regardless of network connectivity.", Contitionals.hasErrorSuccess, Contitionals.none),
    createUserButton(false, true, new FeatureAttribute[]{target, styleName, groupId}, "Creates a button that when clicked creates a new user and then navigates to the target presenter as the new user."),
    switchUserIdButton(true, new FeatureAttribute[]{styleName, groupId, fieldName, validationRegex}, "Switch the user id to the value in the specified metadata field. The value of the field is first validated against the provided regex. Care should be used to make sure that the field contains a valid user id.", Contitionals.hasErrorSuccess, Contitionals.none),
    selectUserMenu(false, false, new FeatureAttribute[]{styleName, fieldName}, "Shows a menu listing the users in the system. The label on each menu item is determined by value of the provided metadata field. When a menu item is clicked the active user is changed and the next presenter is shown."),
    selectLocaleMenu(false, false, new FeatureAttribute[]{styleName}),
    eraseLocalStorageButton(false, false, new FeatureAttribute[]{styleName, groupId}, "For use in the debug type presenter to erase the local storage data. This action cannot be undone and if the local storage data has not be sent to the server or stored in the device storage, the data will be lost permanently."),
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
    sendMetadata(false, null, "Sends the currently stored metadata field values to the admin system. For use in transmission type presenters.", Contitionals.hasErrorSuccess, Contitionals.none),
    // The <redirectToUrl> does not have  <onError> and <onSuccess> because the calling page has gone out scope by the time the error has occurred.
    redirectToUrl(false, false, new FeatureAttribute[]{src}, "Redirects the experiment to the URL specified in src. This feature cannot do error checking and therefore does not have onError or onSuccess because the experiment (calling page) has gone out scope by the time any error might occur."),
    eraseLocalStorageOnWindowClosing(false, false, null),
    //    nextStimulus(false, false, null),
    keepStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    removeStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    removeMatchingStimulus(false, new FeatureAttribute[]{matchingRegex}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    //    clearStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    centrePage(false, false, null),
    clearPage(false, false, new FeatureAttribute[]{styleName}, "Clears visual elements from the current presenter, stops timers and other listeners then submits any pending timestamps."),
    backgroundImage(false, new FeatureAttribute[]{msToNext, src, styleName}, "Sets the background with the provided image.", /* todo: but cannot not indicate when the image has loaded because its done in CSS. The evaluates the contents of this element.", perhaps this should not take child elements? */ Contitionals.any, Contitionals.none),
    allMenuItems(false, false, new FeatureAttribute[]{styleName}),
    prevStimulusButton(true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, "Adds a button that when clicked decrements the current stimulus and triggers hasMoreStimulus. If however there is no previous stimuli then endOfStimulus will be triggered when the button is clicked.", Contitionals.none, Contitionals.stimulusAction),
    nextStimulusButton(true, new FeatureAttribute[]{eventTag, repeatIncorrect, hotKey, styleName, groupId}, "Adds a button that when clicked increments the current stimulus and triggers hasMoreStimulus. If however there are no more stimuli then endOfStimulus will be triggered when the button is clicked.", Contitionals.none, Contitionals.stimulusAction),
    nextStimulus(false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, "Increments the current stimulus and triggers hasMoreStimulus. If however there are no more stimuli then endOfStimulus will be triggered.", Contitionals.none, Contitionals.stimulusAction),
    prevStimulus(false, new FeatureAttribute[]{/*eventTag,*/repeatIncorrect/*, repeatMatching*/}, "Decrements the current stimulus and triggers hasMoreStimulus. If however there is no previous stimulus then endOfStimulus will be triggered.", Contitionals.none, Contitionals.stimulusAction),
    //    skipStimulus(false, false, new FeatureAttribute[]{offset /* when offset is < 1 the stimuli will not repeat, when it is > 0 the stimulus will be shown again after X items or at the end of the list, which ever is the lesser */}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    nextMatchingStimulus(false, null, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    addKinTypeGui(false, false, new FeatureAttribute[]{diagramName}),
    hasGetParameter(false, new FeatureAttribute[]{parameterName}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    hasMetadataValue(false, new FeatureAttribute[]{fieldName, matchingRegex}, "Replaces any token values in the matchingRegex then checks if the metadata field value matches the resulting regex. If the regex matches then coditionTrue is triggered otherwise conditionFalse is triggered.", Contitionals.hasTrueFalseCondition, Contitionals.none),
    setMetadataValue(false, new FeatureAttribute[]{fieldName, dataLogFormat, replacementRegex}, "The value of dataLogFormat will have any string tokens replaced. Next if the replacementRegex is provided then the regex is applied and only the values of the regex capture groups will be kept, otherwise the entire string is used. The result is then stored in the specified metadata field.", Contitionals.none, Contitionals.none),
    matchOnEvalTokens(false, new FeatureAttribute[]{evaluateTokens, matchingRegex}, "Compares the matchingRegex against the result of evaluateTokens after any metadata field tokens have been resolved, any arithmetic and boolean comparisons have been resolved.", Contitionals.hasTrueFalseErrorCondition, Contitionals.none),
    progressIndicator(false, new FeatureAttribute[]{evaluateTokens, styleName}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    setMetadataEvalTokens(false, new FeatureAttribute[]{fieldName, evaluateTokens}, "The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The resulting number is then stored in the specified metadata field.", Contitionals.hasErrorSuccess, Contitionals.none),
    activateRandomItem(false, false, new FeatureAttribute[]{}, "Randomly activates one menu item on the current presenter providing that the target presenter has not already been completed. If all targets have been completed then the user will be sent to the next presenter as specified by the current presenter."),
    gotoPresenter(false, false, new FeatureAttribute[]{target}),
    gotoNextPresenter(false, false, new FeatureAttribute[]{}),
    logTimeStamp(false, new FeatureAttribute[]{eventTag}, "Records a timestamp similar to the way image and media onLoad events etc. are logged. The logged ms is relative to the load time of the presenter.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    hardwareTimeStamp(false, new FeatureAttribute[]{opto1, opto2, dtmf}, "When the URL parameter &quot;hardwareTimeStamp&quot; is provided hardware measurable markers will be produced either visually on screen or as an audible tone or both. The visual markers are in the form of two black/white squares at the top of the screen. The audio markers are in the form of tones. These markers are equivalent to &lt;logTimeStamp&gt; and should be used in a similar way.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    recorderToneInjection(false, new FeatureAttribute[]{dtmf}, "Injects an audible tone into the current recording stream (providing there is an active recording) for use as a timestamp when processing the resulting data. The audio marker is in the form a DTMF tone. A timestamp will also be stored in the database similar to that produced by &lt;logTimeStamp&gt;.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    dtmfTone(false, new FeatureAttribute[]{msToNext, dtmf}, "Produces the specified DTMF tone for the given time in milliseconds. When the milliseconds is set to 0 or omitted the tone will continue until it is stopped by setting the DTMF code to off.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    // todo: document audioButton which fires the played event once and only once after the first playback finishes
    audioButton(false, new FeatureAttribute[]{eventTag, dataChannel, poster, autoPlay, hotKey, styleName, src, groupId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.none), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    preloadAllStimuli(false, null, true, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    showStimulus(false, null, "Repeats the current stimulus by triggering hasMoreStimulus. If there is no current stimulus then endOfStimulus will be triggered.", Contitionals.any, Contitionals.stimulusAction), // todo: should this be here? or should it have an increment for next back etc
    showStimulusGrid(false, new FeatureAttribute[]{maxStimuli, dataChannel, columnCount, imageWidth, eventTag, animate}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    matchingStimulusGrid(false, new FeatureAttribute[]{columnCount, dataChannel, maxWidth, animate, matchingRegex, maxStimuli, randomise}, false, false, false, Contitionals.hasCorrectIncorrect, Contitionals.stimulusAction),
    pause(false, new FeatureAttribute[]{msToNext}, "Pause in milliseconds. When the time is up the contents of this element are evaluated.", Contitionals.any, Contitionals.none),
    doLater(false, new FeatureAttribute[]{msToNext}, "After the specified milliseconds have passed the contents of this element are evaluated.", Contitionals.any, Contitionals.none, Contitionals.isTimeCritical),
    requestNotification(true, new FeatureAttribute[]{fieldName, targetOptions, dataLogFormat}, "Request mobile notifications at given times in the future on Android and iOS apps. The presenters listed in targetOptions need to be comma separated and will be shown as buttons in the notification.", Contitionals.hasErrorSuccess, Contitionals.none),
    // todo: consider renaming so that timer is the first part: timerStart, timerCompare, timerClear, timerLog
    // todo: consider renaming startTimer to addTimerTrigger
    startTimer(true, false, new FeatureAttribute[]{msToNext, listenerId}, "Starts a persistent timer which is identified by its listenerId. The start time is the moment the first startTimer for a given listenerId is encountered (this start time is recorded in the local storage data). This start time is persistent over page loads and navigation and can only be reset by a clearTimer followed by a startTimer. The content of the startTimer element is scoped to the presenter and will not trigger outside of the presenter that contains it. Each startTimer creates a callback that will trigger when the timer reaches the ms value, but will not trigger if that time has already passed while the presenter was not active. Multiple startTimer tags with the same listenerId can be used, each will share the same start moment allowing multiple events to be synchronised on one timer."),
    compareTimer(false, new FeatureAttribute[]{msToNext, listenerId}, "Compares the current time of a persistent timer that was started by startTimer with the same listenerId.", Contitionals.hasThreshold, Contitionals.none),
    //    compareDateFormat(false, false, new FeatureAttribute[]{dateFormat, matchingRegex}, false, false, false, Contitionals.hasTrueFalseCondition, Contitionals.none),
    clearTimer(false, new FeatureAttribute[]{listenerId}, "Clears the start time and terminates any pending events of any persistent timer matching listenerId (from the local storage data). After this a subsequent startTimer can be used to assign a new start time with this listenerId.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    logTimerValue(false, false, new FeatureAttribute[]{listenerId, eventTag, dataChannel}, "Added a record in the admin system of the current milliseconds for a persistent timer which is identified by its listenerId."),
    timerLabel(false, false, new FeatureAttribute[]{listenerId, msToNext, msLabelFormat, styleName}, "Adds a label that shows the time value of listenerId minus msToNext and formats the result with msLabelFormat (similar to countdownLabel)."),
    randomMsPause(true, false, new FeatureAttribute[]{minimum, maximum}, "Pause for a random number of milliseconds that is not less than minimum and not more than maximum. When the time is up the contents of this element are evaluated."),
    evaluatePause(false, new FeatureAttribute[]{minimum, maximum, evaluateTokens}, "Pause based on the value of metadata, stimulus responses or scores etc. The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The resulting number is the time of the pause in milliseconds. The evaluated value will be constrained so that it is within the minimum or maximum values. When the time is up the onSuccess element will be triggered. If there is an error evaluating the token string then onError will be triggered.", Contitionals.hasErrorSuccess, Contitionals.none),
    addTimerTrigger(false, new FeatureAttribute[]{minimum, maximum, evaluateTokens, listenerId}, "Adds an onTimer action to the named persistent timer that will be triggered at the moment in ms defined in evaluateTokens. If the named persistent timer is not running it will be started. The persistent timer is identified by its listenerId based on the value of metadata, stimulus responses or scores etc. The value of evaluateTokens will have any string tokens replaced, followed by mathematical evaluation. The resulting number is the time in milliseconds at which the onTimer should occur. The evaluated value will be constrained so that it is within the minimum or maximum values. When the time is up the onTimer element will be triggered. If there is an error evaluating the token string then onError will be triggered.", Contitionals.hasErrorTimer, Contitionals.none),
    startFrameRateTimer(new FeatureAttribute[]{}, false, "Starts a timer that evaluates its triggers before each frame is rendered in the browser. The resolution of these triggering events will be no less that the framerate, but the actual time resolution is browser dependant.", Contitionals.hasFrameRateTriggers, Contitionals.none),
    addFrameTimeTrigger(new FeatureAttribute[]{evaluateMs, threshold}, false, "Adds a trigger to the containing frame timer. When the timer has a value equal or greater than the specified milliseconds this trigger. Only one trigger per millisecond value can be assigned. If the events is triggered later than the threshold value in milliseconds (20ms is the minimum recommended value) beyond the requested time the onError will be triggered and the framerate timer will stop processing further events.", Contitionals.hasErrorTimeCritical, Contitionals.hasFrameRateTriggers),
    addMediaTrigger(false, new FeatureAttribute[]{evaluateMs, mediaId, threshold}, "Adds a media recording or playback event that will trigger when the media first passes the provided milliseconds value. The timing of this event will have a resolution not less than the length of the recording buffer. If the events is triggered later than the threshold value in milliseconds (20ms is the minimum recommended value) beyond the requested time the onError will be triggered.", Contitionals.hasErrorTimeCritical, Contitionals.none),
    addRecorderDtmfTrigger(false, new FeatureAttribute[]{dtmf}, "Adds a web recorder event that will trigger when the the provided DTMF tone is detected in the recorded audio stream. Only one trigger can be assigned per DTMF code.", Contitionals.isTimeCritical, Contitionals.isRecursiveType, Contitionals.none),
    // todo: does triggerListener maximum value of 0 allow infinit? document this
    triggerDefinition(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}, "The contents of this element will be activated when matched by the listenerId attribute of trigger or triggerRandom (for example). Based on the threshold and maximum values a trigger request does not always result in the trigger activating. The threshold is the number of activation requests before it will trigger. The maximum is the number of times the trigger can occur after which activation requests will be ignored. So a triggerDefinition with threshold 3 and maximum 1 would require three activation requests to trigger and it would not trigger again. A triggerDefinition can have more than one stimulus available (eg when it is in an eachStimulus or hasMoreStimulus). If this is the case then stimulus where triggerDefinition is defined will be used. If there is no stimulus where the triggerDefinition is defined then the stimulus from the point at which it is triggered will be used."),
    habituationParadigmListener(true, false, new FeatureAttribute[]{listenerId, threshold, maximum}), //  threshold is in ms eg 2000 is the minimum length of an event to be considered, maximum is the max shows eg 10.
    triggerMatching(false, false, new FeatureAttribute[]{listenerId}, "Trigger all triggerListeners matching the listenerId providing the threshold and maximum values are within the required values. The listenerId can contain tokens eg &lt;stimulusCode&gt;."),
    triggerRandom(true, false, new FeatureAttribute[]{matchingRegex}, "Randomly trigger one any of the triggerListeners where the listenerId matches the matchingRegex and its maximum trigger count has not been reached. When there are no triggerListeners that match these criteria the child contents of tag will be triggered."),
    resetTrigger/* todo: consider changing to triggerReset */(false, false, new FeatureAttribute[]{listenerId}, "Reset the threshold and maximum counters for triggerListeners matching the listenerId."),
    countdownLabel(true, true, new FeatureAttribute[]{msToNext, msLabelFormat, styleName}),
    stimulusPause(false, null, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    stimulusLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    onTime(false, null, false, false, false, Contitionals.isTimeCritical, Contitionals.hasErrorTimeCritical),
    onTimer(false, null, false, false, false, Contitionals.any, Contitionals.hasErrorTimer),
    conditionTrue(false, null, false, false, false, Contitionals.any, Contitionals.hasTrueFalseCondition, Contitionals.hasTrueFalseErrorCondition),
    conditionFalse(false, null, false, false, false, Contitionals.any, Contitionals.hasTrueFalseCondition, Contitionals.hasTrueFalseErrorCondition),
    onSuccess(false, null, false, false, false, Contitionals.any, Contitionals.hasErrorSuccess),
    onError(false, null, false, false, false, Contitionals.any, Contitionals.hasErrorSuccess, Contitionals.hasTrueFalseErrorCondition, Contitionals.hasErrorTimer, Contitionals.hasErrorTimeCritical),
    onActivate(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaLoadingButton),
    kinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, kintypestring}),
    loadKinTypeStringDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    editableKinEntitesDiagram(true, false, new FeatureAttribute[]{msToNext, diagramName}),
    onKeyUp(false, null, false, false, false, Contitionals.any, Contitionals.hasKeyInputsCondition),
    onKeyDown(false, null, false, false, false, Contitionals.any, Contitionals.hasKeyInputsCondition),
    responseCorrect(false, new FeatureAttribute[]{}, false, false, false, Contitionals.any, Contitionals.hasCorrectIncorrect),
    responseIncorrect(false, new FeatureAttribute[]{}, false, false, false, Contitionals.any, Contitionals.hasCorrectIncorrect),
    hasMoreStimulus(false, null, false, false, false, Contitionals.stimulusAction, Contitionals.hasMoreStimulus),
    endOfStimulus(false, null, "Will be triggered if there are zero stimuli loaded or when a nextStimulus/Button event occurs on the last stimulus.", Contitionals.any, Contitionals.hasMoreStimulus, Contitionals.groupStimulus),
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
    mediaLoadFailed(false, null, "Will be triggered when the host system reports a failure. The timing of this depends on a few factors. If the network is unreachable it will fail quickly. Otherwise it will wait the timeout and probably some retry iterations. The timeout and retry parameters would differ per browser and probably are user configurable as well.", Contitionals.any, Contitionals.hasMediaLoading, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackStarted(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    mediaPlaybackComplete(false, null, false, false, false, Contitionals.any, Contitionals.hasMediaPlayback, Contitionals.hasMediaRecorderPlayback),
    //    clearRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    //    updateRegion(true, false, new FeatureAttribute[]{target}), // these tags would require the handling the clearing of timers and button handlers
    table(true, false, new FeatureAttribute[]{styleName, showOnBackButton}),
    row(true, false, null),
    column(true, false, new FeatureAttribute[]{styleName}),
    regionAppend(true, false, new FeatureAttribute[]{regionId, styleName}, "Starts or resumes a region, allowing contents to be added to a given location in the presenter. If no region matching the regionId exists then a new region is created in the current location and then appended to. If a region of the same regionId already exists then it will be appended to."),
    regionStyle(false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then it will have the style applied. If no matching region exists then nothing will be done.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    regionCodeStyle(false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then the styleName will have any tokens evaluated before the resulting style name is applied to the region. If no matching region exists then nothing will be done.", Contitionals.none, Contitionals.stimulusAction),
    regionReplace(true, false, new FeatureAttribute[]{regionId, styleName}, "If a region matching the regionId exists then it will cleared. If no region matching the regionId exists then a new region is created in the current location. The resulting region is then appended to."),
    regionClear(false, false, new FeatureAttribute[]{regionId}, "If a region matching the regionId exists then it will cleared. Unlike clearPage clearing a region only removes the visual components, so any media that has been placed in the region should be stopped first."),
    regionDragDrop(false, new FeatureAttribute[]{regionId, draggable, droptarget, codeFormat, dataChannel}, "If a region matching the regionId exists then the options of draggable and droppable will be applied making the region draggable or a drop target or both. The codeFormat of both the dragged item and the drop target will be set as the stimulus response for the drag action. If no matching region exists then nothing will be done.", Contitionals.dragDropType, Contitionals.stimulusAction),
    ondragstart(false, new FeatureAttribute[]{}, "", Contitionals.any, Contitionals.dragDropType),
    ondragover(false, new FeatureAttribute[]{}, "", Contitionals.any, Contitionals.dragDropType),
    ondrop(false, new FeatureAttribute[]{}, "", Contitionals.any, Contitionals.dragDropType),
    // todo: look for and update to add the show any stimuli tag and make stimulusImage only show images (true, false, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext, animate, matchingRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaLoading), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusPresent(false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate, replacementRegex, replacement, showControls}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusImage(false, new FeatureAttribute[]{msToNext, styleName, dataChannel}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), // todo: the child nodes of this (for example) are not in the same order after the unit test vs out of the DB
    stimulusCodeImage(false, new FeatureAttribute[]{msToNext, dataChannel, codeFormat, styleName}, false, false, false, Contitionals.hasMediaLoading, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeImageButton(false, new FeatureAttribute[]{dataChannel, codeFormat, styleName, groupId}, false, false, false, Contitionals.hasMediaLoadingButton, Contitionals.stimulusAction), //stimulusCodeImage can take both <code> and <rating_" + index + "> tags in its string value
    stimulusCodeVideo(false, new FeatureAttribute[]{maxHeight, codeFormat, percentOfPage, loop, styleName, autoPlay, showControls, maxWidth, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusVideo(false, new FeatureAttribute[]{loop, styleName, autoPlay, showControls, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusCodeAudio(false, new FeatureAttribute[]{codeFormat, showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    stimulusAudio(false, new FeatureAttribute[]{showPlaybackIndicator, autoPlay, mediaId}, false, false, false, Contitionals.hasMediaPlayback, Contitionals.stimulusAction), // todo: add loading complete, failed and additinally for time based media, playback complete Contitionals.requiresLoading, isTimeBasedMedia
    playMedia(false, new FeatureAttribute[]{mediaId, loop}, "Triggers play on any media with a mediaId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    rewindMedia(false, new FeatureAttribute[]{mediaId}, "Rewinds any media with a mediaId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    pauseMedia(false, new FeatureAttribute[]{mediaId}, "Triggers pause on any media with a mediaId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    logMediaTimeStamp(false, new FeatureAttribute[]{mediaId, eventTag}, "Logs a timestamp with the current time position for any media with a mediaId that matches the provided regex string.", Contitionals.none, Contitionals.none, Contitionals.isTimeCritical),
    stimulusImageCapture(true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth, msToNext}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    //    captureStimulusImage(true, true, new FeatureAttribute[]{percentOfPage, maxHeight, maxWidth}),
    VideoPanel(false, new FeatureAttribute[]{src, percentOfPage, maxHeight, maxWidth, poster}, false, false, false, Contitionals.none, Contitionals.stimulusAction),
    AnnotationTimelinePanel(false, new FeatureAttribute[]{src, poster, eventTag, columnCount, maxStimuli}, false, false, false, Contitionals.any, Contitionals.stimulusAction),
    audioInputSelectWeb(false, new FeatureAttribute[]{deviceRegex, styleName}, "Shows a select box of the available audio input devices for use with startAudioRecorderWeb. When an device is selected it will be used as the first preference when the next recording is made.", Contitionals.hasErrorSuccess, Contitionals.none),
    startAudioRecorderWeb(true, new FeatureAttribute[]{downloadPermittedWindowMs, deviceRegex, mediaId, recordingFormat, levelIndicatorStyle, echoCancellation, noiseSuppression, autoGainControl}, "Starts the HTML5 audio recorder which will send the recorded audio to the admin database. If downloadPermittedWindowMs is a positive number then the uploaded audio can be downloaded from the server for playback within that period of time for user verification, otherwise playback will not be possible. The onError will be triggered if the recorder cannot start. If the upload or playback fails then mediaLoadFailed will be triggered. Upload failure will also result in a subsequent upload attempt. When the recorded audio data has been submitted the mediaLoaded will be triggered even if playback is not enabled by downloadPermittedWindowMs, this allows upload verification before proceeding. The featureText is shown in the recording label. When the featureText is 00:00:00 the recording time will be shown in the recording label. When the featureText is &quot;hidden&quot; the recording label will not be shown (never record without explicit consent). The name of the audio input device used for the recording will be stored in the admin system. If the levelIndicatorStyle is set then a recording level indicator will be shown. If levelIndicatorStyle is set and a region with the ID of AudioRecorderWebLevelIndicator exists then that region will be used to position the recording level indicator.", Contitionals.hasMediaRecorderPlayback, Contitionals.stimulusAction),
    startAudioRecorderApp(false, new FeatureAttribute[]{filePerStimulus, eventTag, fieldName}, "Starts the Android wav recorder, the recorded wav files will be saved on the device in a sub directories based on value of the provided metadata field and the eventTag.", Contitionals.hasErrorSuccess, Contitionals.stimulusAction),
    stopAudioRecorder(false, new FeatureAttribute[]{}, false, false, false, Contitionals.none, Contitionals.stimulusAction, Contitionals.isTimeCritical),
    //    stopAudioRecorder(false, false, new FeatureAttribute[]{minimum, maximum, average}, false, false, false, Contitionals.hasThreshold, Contitionals.stimulusAction),
    startAudioRecorderTag(false, new FeatureAttribute[]{eventTier}, false, false, false, Contitionals.none, Contitionals.stimulusAction, Contitionals.isTimeCritical), // also now adds a timestamp in the admin DB for the web recorder
    endAudioRecorderTag(false, new FeatureAttribute[]{eventTier, eventTag}, false, false, false, Contitionals.none, Contitionals.stimulusAction, Contitionals.isTimeCritical),
    showHtmlPopup(true, new FeatureAttribute[]{}, false, false, false, Contitionals.hasActionButtons, Contitionals.none),
    helpDialogue(false, true, new FeatureAttribute[]{closeButtonLabel}), // helpDialogue is probably only used in SynQuiz
    userInfo(false, false, null),
    versionData(false, false, null),
    //preventWindowClose(false, true, null), // note: preventWindowClose should only be allowed once in the experiment element
    showColourReport(false, new FeatureAttribute[]{scoreThreshold}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    // @todo: groupMembers could be used to determing the sequence of who goes when and therefore could be changed to groupMembersSequence
    groupNetwork(false, new FeatureAttribute[]{groupMembers, groupCommunicationChannels /* TODO: can we remove groupCommunicationChannels by replacing it with an equivalent of streamChannels eg messageChannels*/ , groupCameraChannels, groupAudioChannels, groupCanvasChannels, phasesPerStimulus}, "Connects a group of participants so that they can interact in a defined manner during the experiment. For each stimulus there is a number of phases which is determined by phasesPerStimulus.", Contitionals.groupNetwork, Contitionals.groupStimulus),
    groupInitialisationError(false, new FeatureAttribute[]{}, "When the specified group already has enough members new members cannot be assigned to it or the member code has not been provided the contents of this element will be triggered. It is recommended that the paricipant is instructed here to request a new group which can be assigned in the URL along with the member code. Please note that a group and its members are rememberd on the server by design and reloading the page or clearing the browser storage will have no effect on group status.", Contitionals.any, Contitionals.groupNetwork),
    groupFindingMembers(false, new FeatureAttribute[]{}, "When the the connection to the group server is established the second stage is waiting for the other group members.", Contitionals.any, Contitionals.groupNetwork),
    groupNetworkConnecting(false, new FeatureAttribute[]{}, "The first stage in setting up a group is connecting to the group server. The contents of this element will be triggered while this connection is being established.", Contitionals.any, Contitionals.groupNetwork),
    groupNetworkSynchronising(false, new FeatureAttribute[]{}, "For each stimulus change and phase change the group network needs to be synchronised. The contents of this element will be triggered while this synchronisation is being done.", Contitionals.any, Contitionals.groupNetwork),
    groupPhaseListeners(false, new FeatureAttribute[]{}, "The activities in the phase/participant matrix is defined here. Not all participants will see the same activity at the same time. It is possible for one, many or none to be triggered for any given phase member code combination.", Contitionals.groupMemberActivity, Contitionals.groupNetwork),
    groupMemberActivity(false, new FeatureAttribute[]{phaseMembers}, "Member activities will be triggered when the member code and the current phase coincide. This is determined by the phase matrix defined in each phaseMembers attribute.", Contitionals.groupNetworkAction, Contitionals.groupMemberActivity),
    groupMemberCodeLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMemberLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupMessageLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    // todo: groupResponseStimulusImage could be changed to groupResponseStimulusPresent
    groupResponseStimulusImage(false, new FeatureAttribute[]{percentOfPage, dataChannel, maxHeight, maxWidth, animate}, "Displays the stimulus image that the last group message had responded with.", Contitionals.hasMediaPlayback, Contitionals.groupNetworkAction),
    groupResponseFeedback(false, new FeatureAttribute[]{}, "Compares the current stimulus to the last group response stimulus. If the group response stimulus equals the current group stimulus then it is considered to be correct.", Contitionals.hasCorrectIncorrect, Contitionals.groupNetworkAction),
    groupScoreLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    groupChannelScoreLabel(false, new FeatureAttribute[]{styleName}, false, false, false, Contitionals.none, Contitionals.groupNetworkAction),
    scoreLabel(false, false, new FeatureAttribute[]{styleName}),
    submitGroupEvent(false, null, "Submits a record of the current group state from the perspective of the current group member to the admin database.", Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessageButton(true, new FeatureAttribute[]{hotKey, dataChannel, eventTag, repeatIncorrect, incrementPhase, styleName, groupId/* incrementPhaseOnDictionaryincrementStimulus */}, "Adds a button that will send a message to the group members that are in the current member's communication channel. The message content is the concatenated values of all freetext inputs currently in the presenter. If any freetext input is not valid then the relevant freetext input will show an error and the message will not be sent.", Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupMessage(false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */}, "Sends a message to the group members that are in the current member's communication channel. The contents of the message is the last message received in the current users communication channel.", Contitionals.none, Contitionals.groupNetworkAction),
    sendGroupStoredMessage(false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */, groupId}, "Sends a message to the group members that are in the current member's communication channel. The message is the stored value of groupId for current stimulus. If groupId is not provided 'groupMessage' will be used. If the is no stored value then an empty message will be sent.", Contitionals.none, Contitionals.groupNetworkAction),
    // updateGroupStream(false, new FeatureAttribute[]{eventTag, dataChannel, streamState, streamType}, "Update the streaming state for the current group phase members.", Contitionals.none, Contitionals.groupNetworkAction),
    streamGroupCanvas(false, new FeatureAttribute[]{eventTag, dataChannel, streamChannels}, "Creates a canvas that is streamed to other members of the group based on the stream communication channels. The stream is terminated when the containing region or page is cleared or when the group network ends.", Contitionals.none, Contitionals.groupNetworkAction),
    streamGroupCamera(false, new FeatureAttribute[]{eventTag, dataChannel, streamChannels}, "Shares a camera stream to other members of the group based on the stream communication channels. The stream is terminated when the containing region or page is cleared or when the group network ends.", Contitionals.none, Contitionals.groupNetworkAction),
    // TODO: consider streamGroupMicrophone
    sendGroupTokenMessage(false, new FeatureAttribute[]{eventTag, incrementPhase /*, incrementStimulus */, dataLogFormat}, "Sends a message to the group members that are in the current member's communication channel. The contents of the message is the value of dataLogFormat after any string tokens have been replaced.", Contitionals.none, Contitionals.groupNetworkAction),
    //    sendGroupEndOfStimuli(false, new FeatureAttribute[]{eventTag}, false, false, false, Contitionals.none, Contitionals.groupMemberActivity),
    clearCurrentScore(false, new FeatureAttribute[]{dataChannel, evaluateTokens}, "Clears the current score and sets the current score group string based on the evaluateTokens. The score group is a marker shown in the admin system to keep track of the records and does not affect the score collection process.", Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    scoreIncrement(false, new FeatureAttribute[]{dataChannel, scoreValue}, "Applies the provided score. When the value is positive, both the score and potential are incremented by the value and any correct streak is maintained. When the score is negative only the potential is incremented by the value and any incorrect streak is maintained. When the score is zero no change is made to either the score or potential, however both the correct and incorrect streaks are cleared.", Contitionals.none, Contitionals.none, Contitionals.stimulusAction),
    // todo: document that all score parameters provided in the attribute list must be above its threshold for this to evaluate as above threshold
    bestScoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    totalScoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    scoreAboveThreshold(false, new FeatureAttribute[]{scoreThreshold, errorThreshold, potentialThreshold, correctStreak, errorStreak, gamesPlayed}, false, false, false, Contitionals.hasThreshold, Contitionals.none),
    resetStimulus(false, new FeatureAttribute[]{target}, "Clears the presenter's selected stimulus and the list of stimulus that have been seen so that the presenter can select a fresh list of stimuli.", Contitionals.none, Contitionals.none),
    submitTestResults(false, new FeatureAttribute[]{}, false, false, false, Contitionals.hasErrorSuccess, Contitionals.none),
    validateMetadata(false, new FeatureAttribute[]{/* take the fields from the vaidate section. matchingRegex, enabledRegex, dataLogFormat*/}, "Validates the current user id and the metadata fields (as listed in the validationService section of the experiment configuration file) to the to the Frinex admin (unless another endpoint is defined in registrationUrlStaging and registrationUrlProduction of the listing.json), the corresponding return metadata fields from this validationService section will be updated locally from the values previously saved in the admin system.", Contitionals.hasErrorSuccess, Contitionals.none),
    transmitResults(false, new FeatureAttribute[]{receivingRegex, sendingRegex, dataLogFormat}, "Transmits the values of the metadata fields that match the sendingRegex to the endpoint defined in registrationUrlStaging and registrationUrlProduction (listing.json), the metadata fields which match the sendingRegex will be updated if there is such a field returned from that service. If the registration service returns a 200 response then onSuccess is triggered. When onSuccess is triggered this does not always indicate a validated state, you will also need to check the receiving fields. If there is a network error or the registration service returns a non 200 response then onError will be triggered. One and only one of either onError or onSuccess will always be triggered, however the time required to trigger will depend on network conditions.", Contitionals.hasErrorSuccess, Contitionals.none);
    // TODO: (2 of 3) allocateMetadata participant allocation service with attribute allocationServiceName, to preserve the allocation across browsers and devices
    //allocateMetadata(new FeatureAttribute[]{allocationServiceName, fieldName, /*tagValues or use canHaveStimulusTags*/},true, "", Contitionals.hasErrorSuccess, Contitionals.none);
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
        hasTrueFalseErrorCondition(false),
        hasKeyInputsCondition(false),
        hasCorrectIncorrect(false),
        hasMoreStimulus(false),
        eachStimulus(false),
        groupStimulus(false),
        stimulusAction(true),
        hasErrorSuccess(false),
        hasErrorTimer(false),
        hasErrorTimeCritical(false),
        hasUserCount(false),
        hasThreshold(false),
        groupNetwork(false),
        groupMemberActivity(true),
        groupNetworkAction(true),
        hasMediaLoading(false),
        hasMediaLoadingButton(false),
        hasMediaPlayback(false),
        hasMediaRecorderPlayback(false),
        hasActionButtons(true),
        svgGroupsLoaded(true),
        hasFrameRateTriggers(true),
        isTimeCritical(true),
        isRecursiveType(true),
        touchInputCaptureType(false),
        touchInputStartType(true),
        dragDropType(true),
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

    private FeatureType(final FeatureAttribute[] featureAttributes, boolean canHaveStimulus, final String documentationText, final Contitionals requiresChildType, final Contitionals... isChildType) {
        this.canHaveText = false;
        this.featureAttributes = featureAttributes;
        this.requiresChildType = requiresChildType;
        this.isChildType = isChildType;
        this.canHaveStimulusTags = canHaveStimulus;
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
//                && isChildType[0] != Contitionals.groupMemberActivity
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

    public String getChildTypeString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Contitionals contitional : isChildType) {
            stringBuilder.append("_");
            stringBuilder.append(contitional.name());
        }
        return stringBuilder.toString();
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
