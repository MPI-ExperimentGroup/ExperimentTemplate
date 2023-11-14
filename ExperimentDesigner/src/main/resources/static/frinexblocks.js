function getFeatureBlocks() {
    Blockly.defineBlocksWithJsonArray([
{
        "type": "frinex_experimentType",
"message0": "preventWindowClose %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "deployment %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
"message2": "validationService %1",
        "args2": [
            { "type": "input_statement", "name": "DO" }
        ],
"message3": "administration %1",
        "args3": [
            { "type": "input_statement", "name": "DO" }
        ],
"message4": "scss %1",
        "args4": [
            { "type": "input_statement", "name": "DO" }
        ],
"message5": "metadata %1",
        "args5": [
            { "type": "input_statement", "name": "DO" }
        ],
"message6": "presenter %1",
        "args6": [
            { "type": "input_statement", "name": "DO" }
        ],
"message7": "stimuli %1",
        "args7": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_deploymentType",
 "message0": 'deployment %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_validationServiceType",
"message0": "validation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_validationType",
"message0": "recordMatch %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "fieldMatch %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_administrationType",
"message0": "adminUser %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "dataAgreementField %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
"message2": "dataChannel %1",
        "args2": [
            { "type": "input_statement", "name": "DO" }
        ],
"message3": "chart %1",
        "args3": [
            { "type": "input_statement", "name": "DO" }
        ],
"message4": "dataTable %1",
        "args4": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_adminChartType",
"message0": "metadata %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "stimulusResponse %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_dataTableType",
 "message0": 'dataTable %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_metadataType",
"message0": "field %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_fieldType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_presenterType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimuliType",
"message0": "stimulus %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimulusType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_htmlTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_htmlTokenTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_evaluateTokenTextType",
        "colour": 160
    },
{
        "type": "frinex_logTokenTextType",
 "message0": 'logTokenText %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_plainTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_imageType",
        "colour": 160
    },
{
        "type": "frinex_menuItemType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_withStimuliType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_groupStimuliType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_loadStimulusType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_withMatchingStimulusType",
        "colour": 160
    },
{
        "type": "frinex_loadSdCardStimulusType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_currentStimulusHasTagType",
        "colour": 160
    },
{
        "type": "frinex_clearStimulusResponsesType",
 "message0": 'clearStimulusResponses %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_validateStimuliResponsesType",
        "colour": 160
    },
{
        "type": "frinex_stimulusExistsType",
        "colour": 160
    },
{
        "type": "frinex_showStimuliReportType",
 "message0": 'showStimuliReport %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_sendStimuliReportType",
 "message0": 'sendStimuliReport %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_targetButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_hotKeyInputType",
        "colour": 160
    },
{
        "type": "frinex_actionButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_actionTokenButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_disableButtonGroupType",
 "message0": 'disableButtonGroup %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_enableButtonGroupType",
 "message0": 'enableButtonGroup %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_hideButtonGroupType",
 "message0": 'hideButtonGroup %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_styleButtonGroupType",
 "message0": 'styleButtonGroup %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_showButtonGroupType",
 "message0": 'showButtonGroup %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_requestFocusType",
 "message0": 'requestFocus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_svgLoadGroupsType",
        "colour": 160
    },
{
        "type": "frinex_svgGroupAddType",
 "message0": 'svgGroupAdd %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_svgSetLabelType",
 "message0": 'svgSetLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_svgGroupShowType",
 "message0": 'svgGroupShow %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_svgGroupActionType",
        "colour": 160
    },
{
        "type": "frinex_svgGroupMatchingType",
 "message0": 'svgGroupMatching %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimulusSliderType",
        "colour": 160
    },
{
        "type": "frinex_touchInputLabelButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputImageButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputVideoButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputCaptureType",
        "colour": 160
    },
{
        "type": "frinex_captureStartType",
        "colour": 160
    },
{
        "type": "frinex_touchEndType",
        "colour": 160
    },
{
        "type": "frinex_touchInputStopType",
 "message0": 'touchInputStop %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_ratingButtonType",
        "colour": 160
    },
{
        "type": "frinex_ratingRadioButtonType",
        "colour": 160
    },
{
        "type": "frinex_ratingCheckboxType",
        "colour": 160
    },
{
        "type": "frinex_stimulusFreeTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingButtonType",
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingRadioType",
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingCheckboxType",
        "colour": 160
    },
{
        "type": "frinex_stimulusHasRatingOptionsType",
        "colour": 160
    },
{
        "type": "frinex_clearStimulusResponseType",
 "message0": 'clearStimulusResponse %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stimulusHasResponseType",
        "colour": 160
    },
{
        "type": "frinex_setStimulusCodeResponseType",
 "message0": 'setStimulusCodeResponse %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_addStimulusCodeResponseValidationType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_ratingFooterButtonType",
        "colour": 160
    },
{
        "type": "frinex_targetFooterButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_actionFooterButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_addPaddingType",
 "message0": 'addPadding %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_localStorageDataType",
 "message0": 'localStorageData %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stimuliValidationType",
 "message0": 'stimuliValidation %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_addKeyboardDebugType",
 "message0": 'addKeyboardDebug %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_addDebugWidgetsType",
 "message0": 'addDebugWidgets %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_allMetadataFieldsType",
 "message0": 'allMetadataFields %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_metadataFieldType",
 "message0": 'metadataField %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stimulusMetadataFieldType",
 "message0": 'stimulusMetadataField %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_metadataFieldConnectionType",
 "message0": 'metadataFieldConnection %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_metadataFieldVisibilityDependantType",
 "message0": 'metadataFieldVisibilityDependant %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_metadataFieldDateTriggeredType",
 "message0": 'metadataFieldDateTriggered %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_saveMetadataButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_createUserButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_switchUserIdButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_selectUserMenuType",
 "message0": 'selectUserMenu %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_selectLocaleMenuType",
 "message0": 'selectLocaleMenu %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_eraseLocalStorageButtonType",
 "message0": 'eraseLocalStorageButton %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_eraseUsersDataButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_showCurrentMsType",
 "message0": 'showCurrentMs %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_cancelPauseTimersType",
 "message0": 'cancelPauseTimers %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_cancelPauseAllType",
 "message0": 'cancelPauseAll %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_showStimulusProgressType",
 "message0": 'showStimulusProgress %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_displayCompletionCodeType",
 "message0": 'displayCompletionCode %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_generateCompletionCodeType",
        "colour": 160
    },
{
        "type": "frinex_sendAllDataType",
        "colour": 160
    },
{
        "type": "frinex_sendMetadataType",
        "colour": 160
    },
{
        "type": "frinex_redirectToUrlType",
 "message0": 'redirectToUrl %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_eraseLocalStorageOnWindowClosingType",
 "message0": 'eraseLocalStorageOnWindowClosing %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_keepStimulusType",
 "message0": 'keepStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_removeMatchingStimulusType",
 "message0": 'removeMatchingStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_centrePageType",
 "message0": 'centrePage %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_clearPageType",
 "message0": 'clearPage %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_backgroundImageType",
        "colour": 160
    },
{
        "type": "frinex_allMenuItemsType",
 "message0": 'allMenuItems %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_prevStimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_nextStimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_nextStimulusType",
 "message0": 'nextStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_prevStimulusType",
 "message0": 'prevStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_nextMatchingStimulusType",
 "message0": 'nextMatchingStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_addKinTypeGuiType",
 "message0": 'addKinTypeGui %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_hasGetParameterType",
        "colour": 160
    },
{
        "type": "frinex_hasMetadataValueType",
        "colour": 160
    },
{
        "type": "frinex_setMetadataValueType",
 "message0": 'setMetadataValue %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_matchOnEvalTokensType",
        "colour": 160
    },
{
        "type": "frinex_progressIndicatorType",
        "colour": 160
    },
{
        "type": "frinex_setMetadataEvalTokensType",
        "colour": 160
    },
{
        "type": "frinex_activateRandomItemType",
 "message0": 'activateRandomItem %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_gotoPresenterType",
 "message0": 'gotoPresenter %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_gotoNextPresenterType",
 "message0": 'gotoNextPresenter %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_logTimeStampType",
 "message0": 'logTimeStamp %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_zeroStimulusStopwatchType",
 "message0": 'zeroStimulusStopwatch %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stopStimulusStopwatchType",
 "message0": 'stopStimulusStopwatch %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_hardwareTimeStampType",
 "message0": 'hardwareTimeStamp %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_recorderToneInjectionType",
 "message0": 'recorderToneInjection %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_dtmfToneType",
 "message0": 'dtmfTone %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_audioButtonType",
        "colour": 160
    },
{
        "type": "frinex_preloadAllStimuliType",
        "colour": 160
    },
{
        "type": "frinex_showStimulusType",
        "colour": 160
    },
{
        "type": "frinex_showStimulusGridType",
        "colour": 160
    },
{
        "type": "frinex_matchingStimulusGridType",
        "colour": 160
    },
{
        "type": "frinex_pauseType",
        "colour": 160
    },
{
        "type": "frinex_doLaterType",
        "colour": 160
    },
{
        "type": "frinex_requestNotificationType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_startTimerType",
        "colour": 160
    },
{
        "type": "frinex_compareTimerType",
        "colour": 160
    },
{
        "type": "frinex_clearTimerType",
 "message0": 'clearTimer %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_logTimerValueType",
 "message0": 'logTimerValue %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_timerLabelType",
 "message0": 'timerLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_randomMsPauseType",
        "colour": 160
    },
{
        "type": "frinex_evaluatePauseType",
        "colour": 160
    },
{
        "type": "frinex_addTimerTriggerType",
        "colour": 160
    },
{
        "type": "frinex_startFrameRateTimerType",
        "colour": 160
    },
{
        "type": "frinex_addFrameTimeTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addMediaTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addRecorderDtmfTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addRecorderLevelTriggerType",
        "colour": 160
    },
{
        "type": "frinex_triggerDefinitionType",
        "colour": 160
    },
{
        "type": "frinex_habituationParadigmListenerType",
        "colour": 160
    },
{
        "type": "frinex_triggerMatchingType",
 "message0": 'triggerMatching %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_triggerRandomType",
        "colour": 160
    },
{
        "type": "frinex_resetTriggerType",
 "message0": 'resetTrigger %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_countdownLabelType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimulusPauseType",
        "colour": 160
    },
{
        "type": "frinex_stimulusLabelType",
 "message0": 'stimulusLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_onTimeType",
        "colour": 160
    },
{
        "type": "frinex_onTimerType",
        "colour": 160
    },
{
        "type": "frinex_conditionTrueType",
        "colour": 160
    },
{
        "type": "frinex_conditionFalseType",
        "colour": 160
    },
{
        "type": "frinex_onSuccessType",
        "colour": 160
    },
{
        "type": "frinex_onActivateType",
        "colour": 160
    },
{
        "type": "frinex_kinTypeStringDiagramType",
        "colour": 160
    },
{
        "type": "frinex_loadKinTypeStringDiagramType",
        "colour": 160
    },
{
        "type": "frinex_editableKinEntitesDiagramType",
        "colour": 160
    },
{
        "type": "frinex_onKeyUpType",
        "colour": 160
    },
{
        "type": "frinex_onKeyDownType",
        "colour": 160
    },
{
        "type": "frinex_responseCorrectType",
        "colour": 160
    },
{
        "type": "frinex_responseIncorrectType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkType",
        "colour": 160
    },
{
        "type": "frinex_hasMoreStimulusType",
        "colour": 160
    },
{
        "type": "frinex_beforeStimulusType",
        "colour": 160
    },
{
        "type": "frinex_eachStimulusType",
        "colour": 160
    },
{
        "type": "frinex_afterStimulusType",
        "colour": 160
    },
{
        "type": "frinex_endOfStimulusType",
        "colour": 160
    },
{
        "type": "frinex_existingUserCheckType",
        "colour": 160
    },
{
        "type": "frinex_multipleUsersType",
        "colour": 160
    },
{
        "type": "frinex_singleUserType",
        "colour": 160
    },
{
        "type": "frinex_aboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_withinThresholdType",
        "colour": 160
    },
{
        "type": "frinex_mediaLoadedType",
        "colour": 160
    },
{
        "type": "frinex_mediaLoadFailedType",
        "colour": 160
    },
{
        "type": "frinex_mediaPlaybackStartedType",
        "colour": 160
    },
{
        "type": "frinex_mediaPlaybackCompleteType",
        "colour": 160
    },
{
        "type": "frinex_tableType",
        "colour": 160
    },
{
        "type": "frinex_rowType",
        "colour": 160
    },
{
        "type": "frinex_columnType",
        "colour": 160
    },
{
        "type": "frinex_regionAppendType",
        "colour": 160
    },
{
        "type": "frinex_regionStyleType",
 "message0": 'regionStyle %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_regionCodeStyleType",
 "message0": 'regionCodeStyle %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_regionReplaceType",
        "colour": 160
    },
{
        "type": "frinex_regionClearType",
 "message0": 'regionClear %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_regionDragDropType",
        "colour": 160
    },
{
        "type": "frinex_ondragstartType",
        "colour": 160
    },
{
        "type": "frinex_ondragoverType",
        "colour": 160
    },
{
        "type": "frinex_ondropType",
        "colour": 160
    },
{
        "type": "frinex_stimulusPresentType",
        "colour": 160
    },
{
        "type": "frinex_stimulusImageType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeImageType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeImageButtonType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeVideoType",
        "colour": 160
    },
{
        "type": "frinex_stimulusVideoType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeAudioType",
        "colour": 160
    },
{
        "type": "frinex_stimulusAudioType",
        "colour": 160
    },
{
        "type": "frinex_playMediaType",
 "message0": 'playMedia %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_rewindMediaType",
 "message0": 'rewindMedia %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_pauseMediaType",
 "message0": 'pauseMedia %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_logMediaTimeStampType",
 "message0": 'logMediaTimeStamp %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_stimulusImageCaptureType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_VideoPanelType",
 "message0": 'VideoPanel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_AnnotationTimelinePanelType",
        "colour": 160
    },
{
        "type": "frinex_audioInputSelectWebType",
        "colour": 160
    },
{
        "type": "frinex_startAudioRecorderWebType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_startAudioRecorderAppType",
        "colour": 160
    },
{
        "type": "frinex_stopAudioRecorderType",
 "message0": 'stopAudioRecorder %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_startAudioRecorderTagType",
 "message0": 'startAudioRecorderTag %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_endAudioRecorderTagType",
 "message0": 'endAudioRecorderTag %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_showHtmlPopupType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_helpDialogueType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_userInfoType",
 "message0": 'userInfo %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_versionDataType",
 "message0": 'versionData %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_showColourReportType",
        "colour": 160
    },
{
        "type": "frinex_groupInitialisationErrorType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkConnectingType",
        "colour": 160
    },
{
        "type": "frinex_groupFindingMembersType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkSynchronisingType",
        "colour": 160
    },
{
        "type": "frinex_groupPhaseListenersType",
        "colour": 160
    },
{
        "type": "frinex_groupMemberActivityType",
        "colour": 160
    },
{
        "type": "frinex_groupMemberCodeLabelType",
 "message0": 'groupMemberCodeLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_groupMemberLabelType",
 "message0": 'groupMemberLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_groupMessageLabelType",
 "message0": 'groupMessageLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_groupResponseStimulusImageType",
        "colour": 160
    },
{
        "type": "frinex_groupResponseFeedbackType",
        "colour": 160
    },
{
        "type": "frinex_groupScoreLabelType",
 "message0": 'groupScoreLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_groupChannelScoreLabelType",
 "message0": 'groupChannelScoreLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_scoreLabelType",
 "message0": 'scoreLabel %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_submitGroupEventType",
 "message0": 'submitGroupEvent %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_sendGroupMessageButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_sendGroupMessageType",
 "message0": 'sendGroupMessage %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_sendGroupStoredMessageType",
 "message0": 'sendGroupStoredMessage %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_streamGroupCanvasType",
        "colour": 160
    },
{
        "type": "frinex_streamGroupCameraType",
        "colour": 160
    },
{
        "type": "frinex_sendGroupTokenMessageType",
 "message0": 'sendGroupTokenMessage %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_clearCurrentScoreType",
 "message0": 'clearCurrentScore %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_scoreIncrementType",
 "message0": 'scoreIncrement %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_bestScoreAboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_totalScoreAboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_scoreAboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_resetStimulusType",
 "message0": 'resetStimulus %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "colour": 160
    },
{
        "type": "frinex_submitTestResultsType",
        "colour": 160
    },
{
        "type": "frinex_validateMetadataType",
        "colour": 160
    },
{
        "type": "frinex_transmitResultsType",
        "colour": 160
    },
{
        "type": "frinex_onErrorType",
        "colour": 160
    },
{
        "type": "frinex_randomGroupingType",
"message0": "tag %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
"message1": "list %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
{
        "type": "frinex_stimuliSelectType",
"message0": "tag %1",
        "args0": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    },
]);
    return {
        "kind": "flyoutToolbox",
        "contents": [
            {
                "kind": "block",
                "type": "frinex_experimentType"
            },
            {
                "kind": "block",
                "type": "frinex_htmlTextType"
            },
            {
                "kind": "block",
                "type": "frinex_htmlTokenTextType"
            },
            {
                "kind": "block",
                "type": "frinex_evaluateTokenTextType"
            },
            {
                "kind": "block",
                "type": "frinex_logTokenTextType"
            },
            {
                "kind": "block",
                "type": "frinex_plainTextType"
            },
            {
                "kind": "block",
                "type": "frinex_imageType"
            },
            {
                "kind": "block",
                "type": "frinex_menuItemType"
            },
            {
                "kind": "block",
                "type": "frinex_withStimuliType"
            },
            {
                "kind": "block",
                "type": "frinex_groupStimuliType"
            },
            {
                "kind": "block",
                "type": "frinex_loadStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_withMatchingStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_loadSdCardStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_currentStimulusHasTagType"
            },
            {
                "kind": "block",
                "type": "frinex_clearStimulusResponsesType"
            },
            {
                "kind": "block",
                "type": "frinex_validateStimuliResponsesType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusExistsType"
            },
            {
                "kind": "block",
                "type": "frinex_showStimuliReportType"
            },
            {
                "kind": "block",
                "type": "frinex_sendStimuliReportType"
            },
            {
                "kind": "block",
                "type": "frinex_targetButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_hotKeyInputType"
            },
            {
                "kind": "block",
                "type": "frinex_actionButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_actionTokenButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_disableButtonGroupType"
            },
            {
                "kind": "block",
                "type": "frinex_enableButtonGroupType"
            },
            {
                "kind": "block",
                "type": "frinex_hideButtonGroupType"
            },
            {
                "kind": "block",
                "type": "frinex_styleButtonGroupType"
            },
            {
                "kind": "block",
                "type": "frinex_showButtonGroupType"
            },
            {
                "kind": "block",
                "type": "frinex_requestFocusType"
            },
            {
                "kind": "block",
                "type": "frinex_svgLoadGroupsType"
            },
            {
                "kind": "block",
                "type": "frinex_svgGroupAddType"
            },
            {
                "kind": "block",
                "type": "frinex_svgSetLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_svgGroupShowType"
            },
            {
                "kind": "block",
                "type": "frinex_svgGroupActionType"
            },
            {
                "kind": "block",
                "type": "frinex_svgGroupMatchingType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusSliderType"
            },
            {
                "kind": "block",
                "type": "frinex_touchInputLabelButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_touchInputImageButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_touchInputVideoButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_touchInputCaptureType"
            },
            {
                "kind": "block",
                "type": "frinex_captureStartType"
            },
            {
                "kind": "block",
                "type": "frinex_touchEndType"
            },
            {
                "kind": "block",
                "type": "frinex_touchInputStopType"
            },
            {
                "kind": "block",
                "type": "frinex_ratingButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_ratingRadioButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_ratingCheckboxType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusFreeTextType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusRatingButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusRatingRadioType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusRatingCheckboxType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusHasRatingOptionsType"
            },
            {
                "kind": "block",
                "type": "frinex_clearStimulusResponseType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusHasResponseType"
            },
            {
                "kind": "block",
                "type": "frinex_setStimulusCodeResponseType"
            },
            {
                "kind": "block",
                "type": "frinex_addStimulusCodeResponseValidationType"
            },
            {
                "kind": "block",
                "type": "frinex_ratingFooterButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_targetFooterButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_actionFooterButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_addPaddingType"
            },
            {
                "kind": "block",
                "type": "frinex_localStorageDataType"
            },
            {
                "kind": "block",
                "type": "frinex_stimuliValidationType"
            },
            {
                "kind": "block",
                "type": "frinex_addKeyboardDebugType"
            },
            {
                "kind": "block",
                "type": "frinex_addDebugWidgetsType"
            },
            {
                "kind": "block",
                "type": "frinex_allMetadataFieldsType"
            },
            {
                "kind": "block",
                "type": "frinex_metadataFieldType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusMetadataFieldType"
            },
            {
                "kind": "block",
                "type": "frinex_metadataFieldConnectionType"
            },
            {
                "kind": "block",
                "type": "frinex_metadataFieldVisibilityDependantType"
            },
            {
                "kind": "block",
                "type": "frinex_metadataFieldDateTriggeredType"
            },
            {
                "kind": "block",
                "type": "frinex_saveMetadataButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_createUserButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_switchUserIdButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_selectUserMenuType"
            },
            {
                "kind": "block",
                "type": "frinex_selectLocaleMenuType"
            },
            {
                "kind": "block",
                "type": "frinex_eraseLocalStorageButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_eraseUsersDataButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_showCurrentMsType"
            },
            {
                "kind": "block",
                "type": "frinex_cancelPauseTimersType"
            },
            {
                "kind": "block",
                "type": "frinex_cancelPauseAllType"
            },
            {
                "kind": "block",
                "type": "frinex_showStimulusProgressType"
            },
            {
                "kind": "block",
                "type": "frinex_displayCompletionCodeType"
            },
            {
                "kind": "block",
                "type": "frinex_generateCompletionCodeType"
            },
            {
                "kind": "block",
                "type": "frinex_sendAllDataType"
            },
            {
                "kind": "block",
                "type": "frinex_sendMetadataType"
            },
            {
                "kind": "block",
                "type": "frinex_redirectToUrlType"
            },
            {
                "kind": "block",
                "type": "frinex_eraseLocalStorageOnWindowClosingType"
            },
            {
                "kind": "block",
                "type": "frinex_keepStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_removeMatchingStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_centrePageType"
            },
            {
                "kind": "block",
                "type": "frinex_clearPageType"
            },
            {
                "kind": "block",
                "type": "frinex_backgroundImageType"
            },
            {
                "kind": "block",
                "type": "frinex_allMenuItemsType"
            },
            {
                "kind": "block",
                "type": "frinex_prevStimulusButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_nextStimulusButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_nextStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_prevStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_nextMatchingStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_addKinTypeGuiType"
            },
            {
                "kind": "block",
                "type": "frinex_hasGetParameterType"
            },
            {
                "kind": "block",
                "type": "frinex_hasMetadataValueType"
            },
            {
                "kind": "block",
                "type": "frinex_setMetadataValueType"
            },
            {
                "kind": "block",
                "type": "frinex_matchOnEvalTokensType"
            },
            {
                "kind": "block",
                "type": "frinex_progressIndicatorType"
            },
            {
                "kind": "block",
                "type": "frinex_setMetadataEvalTokensType"
            },
            {
                "kind": "block",
                "type": "frinex_activateRandomItemType"
            },
            {
                "kind": "block",
                "type": "frinex_gotoPresenterType"
            },
            {
                "kind": "block",
                "type": "frinex_gotoNextPresenterType"
            },
            {
                "kind": "block",
                "type": "frinex_logTimeStampType"
            },
            {
                "kind": "block",
                "type": "frinex_zeroStimulusStopwatchType"
            },
            {
                "kind": "block",
                "type": "frinex_stopStimulusStopwatchType"
            },
            {
                "kind": "block",
                "type": "frinex_hardwareTimeStampType"
            },
            {
                "kind": "block",
                "type": "frinex_recorderToneInjectionType"
            },
            {
                "kind": "block",
                "type": "frinex_dtmfToneType"
            },
            {
                "kind": "block",
                "type": "frinex_audioButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_preloadAllStimuliType"
            },
            {
                "kind": "block",
                "type": "frinex_showStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_showStimulusGridType"
            },
            {
                "kind": "block",
                "type": "frinex_matchingStimulusGridType"
            },
            {
                "kind": "block",
                "type": "frinex_pauseType"
            },
            {
                "kind": "block",
                "type": "frinex_doLaterType"
            },
            {
                "kind": "block",
                "type": "frinex_requestNotificationType"
            },
            {
                "kind": "block",
                "type": "frinex_startTimerType"
            },
            {
                "kind": "block",
                "type": "frinex_compareTimerType"
            },
            {
                "kind": "block",
                "type": "frinex_clearTimerType"
            },
            {
                "kind": "block",
                "type": "frinex_logTimerValueType"
            },
            {
                "kind": "block",
                "type": "frinex_timerLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_randomMsPauseType"
            },
            {
                "kind": "block",
                "type": "frinex_evaluatePauseType"
            },
            {
                "kind": "block",
                "type": "frinex_addTimerTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_startFrameRateTimerType"
            },
            {
                "kind": "block",
                "type": "frinex_addFrameTimeTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_addMediaTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_addRecorderDtmfTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_addRecorderLevelTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_triggerDefinitionType"
            },
            {
                "kind": "block",
                "type": "frinex_habituationParadigmListenerType"
            },
            {
                "kind": "block",
                "type": "frinex_triggerMatchingType"
            },
            {
                "kind": "block",
                "type": "frinex_triggerRandomType"
            },
            {
                "kind": "block",
                "type": "frinex_resetTriggerType"
            },
            {
                "kind": "block",
                "type": "frinex_countdownLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusPauseType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_onTimeType"
            },
            {
                "kind": "block",
                "type": "frinex_onTimerType"
            },
            {
                "kind": "block",
                "type": "frinex_conditionTrueType"
            },
            {
                "kind": "block",
                "type": "frinex_conditionFalseType"
            },
            {
                "kind": "block",
                "type": "frinex_onSuccessType"
            },
            {
                "kind": "block",
                "type": "frinex_onActivateType"
            },
            {
                "kind": "block",
                "type": "frinex_kinTypeStringDiagramType"
            },
            {
                "kind": "block",
                "type": "frinex_loadKinTypeStringDiagramType"
            },
            {
                "kind": "block",
                "type": "frinex_editableKinEntitesDiagramType"
            },
            {
                "kind": "block",
                "type": "frinex_onKeyUpType"
            },
            {
                "kind": "block",
                "type": "frinex_onKeyDownType"
            },
            {
                "kind": "block",
                "type": "frinex_responseCorrectType"
            },
            {
                "kind": "block",
                "type": "frinex_responseIncorrectType"
            },
            {
                "kind": "block",
                "type": "frinex_groupNetworkType"
            },
            {
                "kind": "block",
                "type": "frinex_hasMoreStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_beforeStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_eachStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_afterStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_endOfStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_existingUserCheckType"
            },
            {
                "kind": "block",
                "type": "frinex_multipleUsersType"
            },
            {
                "kind": "block",
                "type": "frinex_singleUserType"
            },
            {
                "kind": "block",
                "type": "frinex_aboveThresholdType"
            },
            {
                "kind": "block",
                "type": "frinex_withinThresholdType"
            },
            {
                "kind": "block",
                "type": "frinex_mediaLoadedType"
            },
            {
                "kind": "block",
                "type": "frinex_mediaLoadFailedType"
            },
            {
                "kind": "block",
                "type": "frinex_mediaPlaybackStartedType"
            },
            {
                "kind": "block",
                "type": "frinex_mediaPlaybackCompleteType"
            },
            {
                "kind": "block",
                "type": "frinex_tableType"
            },
            {
                "kind": "block",
                "type": "frinex_rowType"
            },
            {
                "kind": "block",
                "type": "frinex_columnType"
            },
            {
                "kind": "block",
                "type": "frinex_regionAppendType"
            },
            {
                "kind": "block",
                "type": "frinex_regionStyleType"
            },
            {
                "kind": "block",
                "type": "frinex_regionCodeStyleType"
            },
            {
                "kind": "block",
                "type": "frinex_regionReplaceType"
            },
            {
                "kind": "block",
                "type": "frinex_regionClearType"
            },
            {
                "kind": "block",
                "type": "frinex_regionDragDropType"
            },
            {
                "kind": "block",
                "type": "frinex_ondragstartType"
            },
            {
                "kind": "block",
                "type": "frinex_ondragoverType"
            },
            {
                "kind": "block",
                "type": "frinex_ondropType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusPresentType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusImageType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusCodeImageType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusCodeImageButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusCodeVideoType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusVideoType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusCodeAudioType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusAudioType"
            },
            {
                "kind": "block",
                "type": "frinex_playMediaType"
            },
            {
                "kind": "block",
                "type": "frinex_rewindMediaType"
            },
            {
                "kind": "block",
                "type": "frinex_pauseMediaType"
            },
            {
                "kind": "block",
                "type": "frinex_logMediaTimeStampType"
            },
            {
                "kind": "block",
                "type": "frinex_stimulusImageCaptureType"
            },
            {
                "kind": "block",
                "type": "frinex_VideoPanelType"
            },
            {
                "kind": "block",
                "type": "frinex_AnnotationTimelinePanelType"
            },
            {
                "kind": "block",
                "type": "frinex_audioInputSelectWebType"
            },
            {
                "kind": "block",
                "type": "frinex_startAudioRecorderWebType"
            },
            {
                "kind": "block",
                "type": "frinex_startAudioRecorderAppType"
            },
            {
                "kind": "block",
                "type": "frinex_stopAudioRecorderType"
            },
            {
                "kind": "block",
                "type": "frinex_startAudioRecorderTagType"
            },
            {
                "kind": "block",
                "type": "frinex_endAudioRecorderTagType"
            },
            {
                "kind": "block",
                "type": "frinex_showHtmlPopupType"
            },
            {
                "kind": "block",
                "type": "frinex_helpDialogueType"
            },
            {
                "kind": "block",
                "type": "frinex_userInfoType"
            },
            {
                "kind": "block",
                "type": "frinex_versionDataType"
            },
            {
                "kind": "block",
                "type": "frinex_showColourReportType"
            },
            {
                "kind": "block",
                "type": "frinex_groupInitialisationErrorType"
            },
            {
                "kind": "block",
                "type": "frinex_groupNetworkConnectingType"
            },
            {
                "kind": "block",
                "type": "frinex_groupFindingMembersType"
            },
            {
                "kind": "block",
                "type": "frinex_groupNetworkSynchronisingType"
            },
            {
                "kind": "block",
                "type": "frinex_groupPhaseListenersType"
            },
            {
                "kind": "block",
                "type": "frinex_groupMemberActivityType"
            },
            {
                "kind": "block",
                "type": "frinex_groupMemberCodeLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_groupMemberLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_groupMessageLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_groupResponseStimulusImageType"
            },
            {
                "kind": "block",
                "type": "frinex_groupResponseFeedbackType"
            },
            {
                "kind": "block",
                "type": "frinex_groupScoreLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_groupChannelScoreLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_scoreLabelType"
            },
            {
                "kind": "block",
                "type": "frinex_submitGroupEventType"
            },
            {
                "kind": "block",
                "type": "frinex_sendGroupMessageButtonType"
            },
            {
                "kind": "block",
                "type": "frinex_sendGroupMessageType"
            },
            {
                "kind": "block",
                "type": "frinex_sendGroupStoredMessageType"
            },
            {
                "kind": "block",
                "type": "frinex_streamGroupCanvasType"
            },
            {
                "kind": "block",
                "type": "frinex_streamGroupCameraType"
            },
            {
                "kind": "block",
                "type": "frinex_sendGroupTokenMessageType"
            },
            {
                "kind": "block",
                "type": "frinex_clearCurrentScoreType"
            },
            {
                "kind": "block",
                "type": "frinex_scoreIncrementType"
            },
            {
                "kind": "block",
                "type": "frinex_bestScoreAboveThresholdType"
            },
            {
                "kind": "block",
                "type": "frinex_totalScoreAboveThresholdType"
            },
            {
                "kind": "block",
                "type": "frinex_scoreAboveThresholdType"
            },
            {
                "kind": "block",
                "type": "frinex_resetStimulusType"
            },
            {
                "kind": "block",
                "type": "frinex_submitTestResultsType"
            },
            {
                "kind": "block",
                "type": "frinex_validateMetadataType"
            },
            {
                "kind": "block",
                "type": "frinex_transmitResultsType"
            },
            {
                "kind": "block",
                "type": "frinex_onErrorType"
            },
        ]
    };
}
