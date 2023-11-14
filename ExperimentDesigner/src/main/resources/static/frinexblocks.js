function getFeatureBlocks() {
    Blockly.defineBlocksWithJsonArray([
{
        "type": "frinex_experimentType",
"message0": "preventWindowClose %1",
        "args0": [
            { "type": "input_statement", "name": "preventWindowClose", "check": "frinex_preventWindowCloseType" }
        ],
"message1": "deployment %1",
        "args1": [
            { "type": "input_statement", "name": "deployment", "check": "frinex_deploymentType" }
        ],
"message2": "validationService %1",
        "args2": [
            { "type": "input_statement", "name": "validationService", "check": "frinex_validationServiceType" }
        ],
"message3": "administration %1",
        "args3": [
            { "type": "input_statement", "name": "administration", "check": "frinex_administrationType" }
        ],
"message4": "scss %1",
        "args4": [
            { "type": "input_statement", "name": "scss", "check": "frinex_scssType" }
        ],
"message5": "metadata %1",
        "args5": [
            { "type": "input_statement", "name": "metadata", "check": "frinex_metadataType" }
        ],
"message6": "presenter %1",
        "args6": [
            { "type": "input_statement", "name": "presenter", "check": "frinex_presenterType" }
        ],
"message7": "stimuli %1",
        "args7": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliType" }
        ],
"output": "frinex_experimentType",
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
"output": "frinex_deploymentType",
        "colour": 160
    },
{
        "type": "frinex_validationServiceType",
"message0": "validation %1",
        "args0": [
            { "type": "input_statement", "name": "validation", "check": "frinex_validationType" }
        ],
"output": "frinex_validationServiceType",
        "colour": 160
    },
{
        "type": "frinex_validationType",
"message0": "recordMatch %1",
        "args0": [
            { "type": "input_statement", "name": "recordMatch", "check": "frinex_recordMatchType" }
        ],
"message1": "fieldMatch %1",
        "args1": [
            { "type": "input_statement", "name": "fieldMatch", "check": "frinex_fieldMatchType" }
        ],
"output": "frinex_validationType",
        "colour": 160
    },
{
        "type": "frinex_administrationType",
"message0": "adminUser %1",
        "args0": [
            { "type": "input_statement", "name": "adminUser", "check": "frinex_adminUserType" }
        ],
"message1": "dataAgreementField %1",
        "args1": [
            { "type": "input_statement", "name": "dataAgreementField", "check": "frinex_dataAgreementFieldType" }
        ],
"message2": "dataChannel %1",
        "args2": [
            { "type": "input_statement", "name": "dataChannel", "check": "frinex_dataChannelType" }
        ],
"message3": "chart %1",
        "args3": [
            { "type": "input_statement", "name": "chart", "check": "frinex_chartType" }
        ],
"message4": "dataTable %1",
        "args4": [
            { "type": "input_statement", "name": "dataTable", "check": "frinex_dataTableType" }
        ],
"output": "frinex_administrationType",
        "colour": 160
    },
{
        "type": "frinex_adminChartType",
"message0": "metadata %1",
        "args0": [
            { "type": "input_statement", "name": "metadata", "check": "frinex_metadataType" }
        ],
"message1": "stimulusResponse %1",
        "args1": [
            { "type": "input_statement", "name": "stimulusResponse", "check": "frinex_stimulusResponseType" }
        ],
"output": "frinex_adminChartType",
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
"output": "frinex_dataTableType",
        "colour": 160
    },
{
        "type": "frinex_metadataType",
"message0": "field %1",
        "args0": [
            { "type": "input_statement", "name": "field", "check": "frinex_fieldType" }
        ],
"output": "frinex_metadataType",
        "colour": 160
    },
{
        "type": "frinex_fieldType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_fieldType",
        "colour": 160
    },
{
        "type": "frinex_presenterType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_presenterType",
        "colour": 160
    },
{
        "type": "frinex_stimuliType",
"message0": "stimulus %1",
        "args0": [
            { "type": "input_statement", "name": "stimulus", "check": "frinex_stimulusType" }
        ],
"output": "frinex_stimuliType",
        "colour": 160
    },
{
        "type": "frinex_stimulusType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_stimulusType",
        "colour": 160
    },
{
        "type": "frinex_htmlTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_htmlTextType",
        "colour": 160
    },
{
        "type": "frinex_htmlTokenTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_htmlTokenTextType",
        "colour": 160
    },
{
        "type": "frinex_evaluateTokenTextType",
"output": "frinex_evaluateTokenTextType",
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
"output": "frinex_logTokenTextType",
        "colour": 160
    },
{
        "type": "frinex_plainTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_plainTextType",
        "colour": 160
    },
{
        "type": "frinex_imageType",
"output": "frinex_imageType",
        "colour": 160
    },
{
        "type": "frinex_menuItemType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_menuItemType",
        "colour": 160
    },
{
        "type": "frinex_withStimuliType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "randomGrouping", "check": "frinex_randomGroupingType" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliType" }
        ],
"output": "frinex_withStimuliType",
        "colour": 160
    },
{
        "type": "frinex_groupStimuliType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "randomGrouping", "check": "frinex_randomGroupingType" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliType" }
        ],
"output": "frinex_groupStimuliType",
        "colour": 160
    },
{
        "type": "frinex_loadStimulusType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "randomGrouping", "check": "frinex_randomGroupingType" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliType" }
        ],
"output": "frinex_loadStimulusType",
        "colour": 160
    },
{
        "type": "frinex_withMatchingStimulusType",
"output": "frinex_withMatchingStimulusType",
        "colour": 160
    },
{
        "type": "frinex_loadSdCardStimulusType",
"message0": "randomGrouping %1",
        "args0": [
            { "type": "input_statement", "name": "randomGrouping", "check": "frinex_randomGroupingType" }
        ],
"message1": "stimuli %1",
        "args1": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliType" }
        ],
"output": "frinex_loadSdCardStimulusType",
        "colour": 160
    },
{
        "type": "frinex_currentStimulusHasTagType",
"output": "frinex_currentStimulusHasTagType",
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
"output": "frinex_clearStimulusResponsesType",
        "colour": 160
    },
{
        "type": "frinex_validateStimuliResponsesType",
"output": "frinex_validateStimuliResponsesType",
        "colour": 160
    },
{
        "type": "frinex_stimulusExistsType",
"output": "frinex_stimulusExistsType",
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
"output": "frinex_showStimuliReportType",
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
"output": "frinex_sendStimuliReportType",
        "colour": 160
    },
{
        "type": "frinex_targetButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_targetButtonType",
        "colour": 160
    },
{
        "type": "frinex_hotKeyInputType",
"output": "frinex_hotKeyInputType",
        "colour": 160
    },
{
        "type": "frinex_actionButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_actionButtonType",
        "colour": 160
    },
{
        "type": "frinex_actionTokenButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_actionTokenButtonType",
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
"output": "frinex_disableButtonGroupType",
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
"output": "frinex_enableButtonGroupType",
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
"output": "frinex_hideButtonGroupType",
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
"output": "frinex_styleButtonGroupType",
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
"output": "frinex_showButtonGroupType",
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
"output": "frinex_requestFocusType",
        "colour": 160
    },
{
        "type": "frinex_svgLoadGroupsType",
"output": "frinex_svgLoadGroupsType",
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
"output": "frinex_svgGroupAddType",
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
"output": "frinex_svgSetLabelType",
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
"output": "frinex_svgGroupShowType",
        "colour": 160
    },
{
        "type": "frinex_svgGroupActionType",
"output": "frinex_svgGroupActionType",
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
"output": "frinex_svgGroupMatchingType",
        "colour": 160
    },
{
        "type": "frinex_stimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_stimulusButtonType",
        "colour": 160
    },
{
        "type": "frinex_stimulusSliderType",
"output": "frinex_stimulusSliderType",
        "colour": 160
    },
{
        "type": "frinex_touchInputLabelButtonType",
"output": "frinex_touchInputLabelButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputImageButtonType",
"output": "frinex_touchInputImageButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputVideoButtonType",
"output": "frinex_touchInputVideoButtonType",
        "colour": 160
    },
{
        "type": "frinex_touchInputCaptureType",
"output": "frinex_touchInputCaptureType",
        "colour": 160
    },
{
        "type": "frinex_captureStartType",
"output": "frinex_captureStartType",
        "colour": 160
    },
{
        "type": "frinex_touchEndType",
"output": "frinex_touchEndType",
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
"output": "frinex_touchInputStopType",
        "colour": 160
    },
{
        "type": "frinex_ratingButtonType",
"output": "frinex_ratingButtonType",
        "colour": 160
    },
{
        "type": "frinex_ratingRadioButtonType",
"output": "frinex_ratingRadioButtonType",
        "colour": 160
    },
{
        "type": "frinex_ratingCheckboxType",
"output": "frinex_ratingCheckboxType",
        "colour": 160
    },
{
        "type": "frinex_stimulusFreeTextType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_stimulusFreeTextType",
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingButtonType",
"output": "frinex_stimulusRatingButtonType",
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingRadioType",
"output": "frinex_stimulusRatingRadioType",
        "colour": 160
    },
{
        "type": "frinex_stimulusRatingCheckboxType",
"output": "frinex_stimulusRatingCheckboxType",
        "colour": 160
    },
{
        "type": "frinex_stimulusHasRatingOptionsType",
"output": "frinex_stimulusHasRatingOptionsType",
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
"output": "frinex_clearStimulusResponseType",
        "colour": 160
    },
{
        "type": "frinex_stimulusHasResponseType",
"output": "frinex_stimulusHasResponseType",
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
"output": "frinex_setStimulusCodeResponseType",
        "colour": 160
    },
{
        "type": "frinex_addStimulusCodeResponseValidationType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_addStimulusCodeResponseValidationType",
        "colour": 160
    },
{
        "type": "frinex_ratingFooterButtonType",
"output": "frinex_ratingFooterButtonType",
        "colour": 160
    },
{
        "type": "frinex_targetFooterButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_targetFooterButtonType",
        "colour": 160
    },
{
        "type": "frinex_actionFooterButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_actionFooterButtonType",
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
"output": "frinex_addPaddingType",
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
"output": "frinex_localStorageDataType",
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
"output": "frinex_stimuliValidationType",
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
"output": "frinex_addKeyboardDebugType",
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
"output": "frinex_addDebugWidgetsType",
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
"output": "frinex_allMetadataFieldsType",
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
"output": "frinex_metadataFieldType",
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
"output": "frinex_stimulusMetadataFieldType",
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
"output": "frinex_metadataFieldConnectionType",
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
"output": "frinex_metadataFieldVisibilityDependantType",
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
"output": "frinex_metadataFieldDateTriggeredType",
        "colour": 160
    },
{
        "type": "frinex_saveMetadataButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_saveMetadataButtonType",
        "colour": 160
    },
{
        "type": "frinex_createUserButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_createUserButtonType",
        "colour": 160
    },
{
        "type": "frinex_switchUserIdButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_switchUserIdButtonType",
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
"output": "frinex_selectUserMenuType",
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
"output": "frinex_selectLocaleMenuType",
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
"output": "frinex_eraseLocalStorageButtonType",
        "colour": 160
    },
{
        "type": "frinex_eraseUsersDataButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_eraseUsersDataButtonType",
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
"output": "frinex_showCurrentMsType",
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
"output": "frinex_cancelPauseTimersType",
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
"output": "frinex_cancelPauseAllType",
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
"output": "frinex_showStimulusProgressType",
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
"output": "frinex_displayCompletionCodeType",
        "colour": 160
    },
{
        "type": "frinex_generateCompletionCodeType",
"output": "frinex_generateCompletionCodeType",
        "colour": 160
    },
{
        "type": "frinex_sendAllDataType",
"output": "frinex_sendAllDataType",
        "colour": 160
    },
{
        "type": "frinex_sendMetadataType",
"output": "frinex_sendMetadataType",
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
"output": "frinex_redirectToUrlType",
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
"output": "frinex_eraseLocalStorageOnWindowClosingType",
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
"output": "frinex_keepStimulusType",
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
"output": "frinex_removeMatchingStimulusType",
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
"output": "frinex_centrePageType",
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
"output": "frinex_clearPageType",
        "colour": 160
    },
{
        "type": "frinex_backgroundImageType",
"output": "frinex_backgroundImageType",
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
"output": "frinex_allMenuItemsType",
        "colour": 160
    },
{
        "type": "frinex_prevStimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_prevStimulusButtonType",
        "colour": 160
    },
{
        "type": "frinex_nextStimulusButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_nextStimulusButtonType",
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
"output": "frinex_nextStimulusType",
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
"output": "frinex_prevStimulusType",
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
"output": "frinex_nextMatchingStimulusType",
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
"output": "frinex_addKinTypeGuiType",
        "colour": 160
    },
{
        "type": "frinex_hasGetParameterType",
"output": "frinex_hasGetParameterType",
        "colour": 160
    },
{
        "type": "frinex_hasMetadataValueType",
"output": "frinex_hasMetadataValueType",
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
"output": "frinex_setMetadataValueType",
        "colour": 160
    },
{
        "type": "frinex_matchOnEvalTokensType",
"output": "frinex_matchOnEvalTokensType",
        "colour": 160
    },
{
        "type": "frinex_progressIndicatorType",
"output": "frinex_progressIndicatorType",
        "colour": 160
    },
{
        "type": "frinex_setMetadataEvalTokensType",
"output": "frinex_setMetadataEvalTokensType",
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
"output": "frinex_activateRandomItemType",
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
"output": "frinex_gotoPresenterType",
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
"output": "frinex_gotoNextPresenterType",
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
"output": "frinex_logTimeStampType",
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
"output": "frinex_zeroStimulusStopwatchType",
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
"output": "frinex_stopStimulusStopwatchType",
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
"output": "frinex_hardwareTimeStampType",
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
"output": "frinex_recorderToneInjectionType",
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
"output": "frinex_dtmfToneType",
        "colour": 160
    },
{
        "type": "frinex_audioButtonType",
"output": "frinex_audioButtonType",
        "colour": 160
    },
{
        "type": "frinex_preloadAllStimuliType",
"output": "frinex_preloadAllStimuliType",
        "colour": 160
    },
{
        "type": "frinex_showStimulusType",
"output": "frinex_showStimulusType",
        "colour": 160
    },
{
        "type": "frinex_showStimulusGridType",
"output": "frinex_showStimulusGridType",
        "colour": 160
    },
{
        "type": "frinex_matchingStimulusGridType",
"output": "frinex_matchingStimulusGridType",
        "colour": 160
    },
{
        "type": "frinex_pauseType",
"output": "frinex_pauseType",
        "colour": 160
    },
{
        "type": "frinex_doLaterType",
"output": "frinex_doLaterType",
        "colour": 160
    },
{
        "type": "frinex_requestNotificationType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_requestNotificationType",
        "colour": 160
    },
{
        "type": "frinex_startTimerType",
"output": "frinex_startTimerType",
        "colour": 160
    },
{
        "type": "frinex_compareTimerType",
"output": "frinex_compareTimerType",
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
"output": "frinex_clearTimerType",
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
"output": "frinex_logTimerValueType",
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
"output": "frinex_timerLabelType",
        "colour": 160
    },
{
        "type": "frinex_randomMsPauseType",
"output": "frinex_randomMsPauseType",
        "colour": 160
    },
{
        "type": "frinex_evaluatePauseType",
"output": "frinex_evaluatePauseType",
        "colour": 160
    },
{
        "type": "frinex_addTimerTriggerType",
"output": "frinex_addTimerTriggerType",
        "colour": 160
    },
{
        "type": "frinex_startFrameRateTimerType",
"output": "frinex_startFrameRateTimerType",
        "colour": 160
    },
{
        "type": "frinex_addFrameTimeTriggerType",
"output": "frinex_addFrameTimeTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addMediaTriggerType",
"output": "frinex_addMediaTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addRecorderDtmfTriggerType",
"output": "frinex_addRecorderDtmfTriggerType",
        "colour": 160
    },
{
        "type": "frinex_addRecorderLevelTriggerType",
"output": "frinex_addRecorderLevelTriggerType",
        "colour": 160
    },
{
        "type": "frinex_triggerDefinitionType",
"output": "frinex_triggerDefinitionType",
        "colour": 160
    },
{
        "type": "frinex_habituationParadigmListenerType",
"output": "frinex_habituationParadigmListenerType",
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
"output": "frinex_triggerMatchingType",
        "colour": 160
    },
{
        "type": "frinex_triggerRandomType",
"output": "frinex_triggerRandomType",
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
"output": "frinex_resetTriggerType",
        "colour": 160
    },
{
        "type": "frinex_countdownLabelType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_countdownLabelType",
        "colour": 160
    },
{
        "type": "frinex_stimulusPauseType",
"output": "frinex_stimulusPauseType",
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
"output": "frinex_stimulusLabelType",
        "colour": 160
    },
{
        "type": "frinex_onTimeType",
"output": "frinex_onTimeType",
        "colour": 160
    },
{
        "type": "frinex_onTimerType",
"output": "frinex_onTimerType",
        "colour": 160
    },
{
        "type": "frinex_conditionTrueType",
"output": "frinex_conditionTrueType",
        "colour": 160
    },
{
        "type": "frinex_conditionFalseType",
"output": "frinex_conditionFalseType",
        "colour": 160
    },
{
        "type": "frinex_onSuccessType",
"output": "frinex_onSuccessType",
        "colour": 160
    },
{
        "type": "frinex_onActivateType",
"output": "frinex_onActivateType",
        "colour": 160
    },
{
        "type": "frinex_kinTypeStringDiagramType",
"output": "frinex_kinTypeStringDiagramType",
        "colour": 160
    },
{
        "type": "frinex_loadKinTypeStringDiagramType",
"output": "frinex_loadKinTypeStringDiagramType",
        "colour": 160
    },
{
        "type": "frinex_editableKinEntitesDiagramType",
"output": "frinex_editableKinEntitesDiagramType",
        "colour": 160
    },
{
        "type": "frinex_onKeyUpType",
"output": "frinex_onKeyUpType",
        "colour": 160
    },
{
        "type": "frinex_onKeyDownType",
"output": "frinex_onKeyDownType",
        "colour": 160
    },
{
        "type": "frinex_responseCorrectType",
"output": "frinex_responseCorrectType",
        "colour": 160
    },
{
        "type": "frinex_responseIncorrectType",
"output": "frinex_responseIncorrectType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkType",
"output": "frinex_groupNetworkType",
        "colour": 160
    },
{
        "type": "frinex_hasMoreStimulusType",
"output": "frinex_hasMoreStimulusType",
        "colour": 160
    },
{
        "type": "frinex_beforeStimulusType",
"output": "frinex_beforeStimulusType",
        "colour": 160
    },
{
        "type": "frinex_eachStimulusType",
"output": "frinex_eachStimulusType",
        "colour": 160
    },
{
        "type": "frinex_afterStimulusType",
"output": "frinex_afterStimulusType",
        "colour": 160
    },
{
        "type": "frinex_endOfStimulusType",
"output": "frinex_endOfStimulusType",
        "colour": 160
    },
{
        "type": "frinex_existingUserCheckType",
"output": "frinex_existingUserCheckType",
        "colour": 160
    },
{
        "type": "frinex_multipleUsersType",
"output": "frinex_multipleUsersType",
        "colour": 160
    },
{
        "type": "frinex_singleUserType",
"output": "frinex_singleUserType",
        "colour": 160
    },
{
        "type": "frinex_aboveThresholdType",
"output": "frinex_aboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_withinThresholdType",
"output": "frinex_withinThresholdType",
        "colour": 160
    },
{
        "type": "frinex_mediaLoadedType",
"output": "frinex_mediaLoadedType",
        "colour": 160
    },
{
        "type": "frinex_mediaLoadFailedType",
"output": "frinex_mediaLoadFailedType",
        "colour": 160
    },
{
        "type": "frinex_mediaPlaybackStartedType",
"output": "frinex_mediaPlaybackStartedType",
        "colour": 160
    },
{
        "type": "frinex_mediaPlaybackCompleteType",
"output": "frinex_mediaPlaybackCompleteType",
        "colour": 160
    },
{
        "type": "frinex_tableType",
"output": "frinex_tableType",
        "colour": 160
    },
{
        "type": "frinex_rowType",
"output": "frinex_rowType",
        "colour": 160
    },
{
        "type": "frinex_columnType",
"output": "frinex_columnType",
        "colour": 160
    },
{
        "type": "frinex_regionAppendType",
"output": "frinex_regionAppendType",
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
"output": "frinex_regionStyleType",
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
"output": "frinex_regionCodeStyleType",
        "colour": 160
    },
{
        "type": "frinex_regionReplaceType",
"output": "frinex_regionReplaceType",
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
"output": "frinex_regionClearType",
        "colour": 160
    },
{
        "type": "frinex_regionDragDropType",
"output": "frinex_regionDragDropType",
        "colour": 160
    },
{
        "type": "frinex_ondragstartType",
"output": "frinex_ondragstartType",
        "colour": 160
    },
{
        "type": "frinex_ondragoverType",
"output": "frinex_ondragoverType",
        "colour": 160
    },
{
        "type": "frinex_ondropType",
"output": "frinex_ondropType",
        "colour": 160
    },
{
        "type": "frinex_stimulusPresentType",
"output": "frinex_stimulusPresentType",
        "colour": 160
    },
{
        "type": "frinex_stimulusImageType",
"output": "frinex_stimulusImageType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeImageType",
"output": "frinex_stimulusCodeImageType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeImageButtonType",
"output": "frinex_stimulusCodeImageButtonType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeVideoType",
"output": "frinex_stimulusCodeVideoType",
        "colour": 160
    },
{
        "type": "frinex_stimulusVideoType",
"output": "frinex_stimulusVideoType",
        "colour": 160
    },
{
        "type": "frinex_stimulusCodeAudioType",
"output": "frinex_stimulusCodeAudioType",
        "colour": 160
    },
{
        "type": "frinex_stimulusAudioType",
"output": "frinex_stimulusAudioType",
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
"output": "frinex_playMediaType",
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
"output": "frinex_rewindMediaType",
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
"output": "frinex_pauseMediaType",
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
"output": "frinex_logMediaTimeStampType",
        "colour": 160
    },
{
        "type": "frinex_stimulusImageCaptureType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_stimulusImageCaptureType",
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
"output": "frinex_VideoPanelType",
        "colour": 160
    },
{
        "type": "frinex_AnnotationTimelinePanelType",
"output": "frinex_AnnotationTimelinePanelType",
        "colour": 160
    },
{
        "type": "frinex_audioInputSelectWebType",
"output": "frinex_audioInputSelectWebType",
        "colour": 160
    },
{
        "type": "frinex_startAudioRecorderWebType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_startAudioRecorderWebType",
        "colour": 160
    },
{
        "type": "frinex_startAudioRecorderAppType",
"output": "frinex_startAudioRecorderAppType",
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
"output": "frinex_stopAudioRecorderType",
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
"output": "frinex_startAudioRecorderTagType",
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
"output": "frinex_endAudioRecorderTagType",
        "colour": 160
    },
{
        "type": "frinex_showHtmlPopupType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_showHtmlPopupType",
        "colour": 160
    },
{
        "type": "frinex_helpDialogueType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_helpDialogueType",
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
"output": "frinex_userInfoType",
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
"output": "frinex_versionDataType",
        "colour": 160
    },
{
        "type": "frinex_showColourReportType",
"output": "frinex_showColourReportType",
        "colour": 160
    },
{
        "type": "frinex_groupInitialisationErrorType",
"output": "frinex_groupInitialisationErrorType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkConnectingType",
"output": "frinex_groupNetworkConnectingType",
        "colour": 160
    },
{
        "type": "frinex_groupFindingMembersType",
"output": "frinex_groupFindingMembersType",
        "colour": 160
    },
{
        "type": "frinex_groupNetworkSynchronisingType",
"output": "frinex_groupNetworkSynchronisingType",
        "colour": 160
    },
{
        "type": "frinex_groupPhaseListenersType",
"output": "frinex_groupPhaseListenersType",
        "colour": 160
    },
{
        "type": "frinex_groupMemberActivityType",
"output": "frinex_groupMemberActivityType",
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
"output": "frinex_groupMemberCodeLabelType",
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
"output": "frinex_groupMemberLabelType",
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
"output": "frinex_groupMessageLabelType",
        "colour": 160
    },
{
        "type": "frinex_groupResponseStimulusImageType",
"output": "frinex_groupResponseStimulusImageType",
        "colour": 160
    },
{
        "type": "frinex_groupResponseFeedbackType",
"output": "frinex_groupResponseFeedbackType",
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
"output": "frinex_groupScoreLabelType",
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
"output": "frinex_groupChannelScoreLabelType",
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
"output": "frinex_scoreLabelType",
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
"output": "frinex_submitGroupEventType",
        "colour": 160
    },
{
        "type": "frinex_sendGroupMessageButtonType",
"message0": "translation %1",
        "args0": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
        ],
"output": "frinex_sendGroupMessageButtonType",
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
"output": "frinex_sendGroupMessageType",
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
"output": "frinex_sendGroupStoredMessageType",
        "colour": 160
    },
{
        "type": "frinex_streamGroupCanvasType",
"output": "frinex_streamGroupCanvasType",
        "colour": 160
    },
{
        "type": "frinex_streamGroupCameraType",
"output": "frinex_streamGroupCameraType",
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
"output": "frinex_sendGroupTokenMessageType",
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
"output": "frinex_clearCurrentScoreType",
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
"output": "frinex_scoreIncrementType",
        "colour": 160
    },
{
        "type": "frinex_bestScoreAboveThresholdType",
"output": "frinex_bestScoreAboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_totalScoreAboveThresholdType",
"output": "frinex_totalScoreAboveThresholdType",
        "colour": 160
    },
{
        "type": "frinex_scoreAboveThresholdType",
"output": "frinex_scoreAboveThresholdType",
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
"output": "frinex_resetStimulusType",
        "colour": 160
    },
{
        "type": "frinex_submitTestResultsType",
"output": "frinex_submitTestResultsType",
        "colour": 160
    },
{
        "type": "frinex_validateMetadataType",
"output": "frinex_validateMetadataType",
        "colour": 160
    },
{
        "type": "frinex_transmitResultsType",
"output": "frinex_transmitResultsType",
        "colour": 160
    },
{
        "type": "frinex_onErrorType",
"output": "frinex_onErrorType",
        "colour": 160
    },
{
        "type": "frinex_randomGroupingType",
"message0": "tag %1",
        "args0": [
            { "type": "input_statement", "name": "tag", "check": "frinex_tagType" }
        ],
"message1": "list %1",
        "args1": [
            { "type": "input_statement", "name": "list", "check": "frinex_listType" }
        ],
"output": "frinex_randomGroupingType",
        "colour": 160
    },
{
        "type": "frinex_stimuliSelectType",
"message0": "tag %1",
        "args0": [
            { "type": "input_statement", "name": "tag", "check": "frinex_tagType" }
        ],
"output": "frinex_stimuliSelectType",
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
