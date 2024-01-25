function getFeatureBlocks() {
  Blockly.defineBlocksWithJsonArray([
    {
      "type": "frinex_experimentType",
      "message0": 'experiment %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": 'appNameDisplay %1',
      "args1": [
        {
          "type": "field_input",
          "name": "appNameDisplay",
          "check": "String"
        }
      ],
      "message2": " %1",
      "args2": [
        {
          "type": "input_value",
          "name": "DO",
          "check": [
            "frinex_preventWindowCloseType",
            "frinex_deploymentType",
            "frinex_scssType",
          ]
        }
      ],
      "message3": "Administration %1",
      "args3": [
        {
          "type": "input_statement",
          "name": "Administration",
          "check": [
            "frinex_validationType",
            "frinex_adminUserType",
            "frinex_dataAgreementFieldType",
            "frinex_dataChannelType",
            "frinex_adminChartType",
            "frinex_dataTableType",
          ]
        }
      ],
      "message4": "Metadata %1",
      "args4": [
        {
          "type": "input_statement",
          "name": "Metadata",
          "check": [
            "frinex_fieldType",
          ]
        }
      ],
      "message5": "Presenters %1",
      "args5": [
        {
          "type": "input_statement",
          "name": "Presenters",
          "check": [
            "frinex_presenterType",
          ]
        }
      ],
      "message6": "Stimuli %1",
      "args6": [
        {
          "type": "input_statement",
          "name": "Stimuli",
          "check": [
            "frinex_stimulusType",
          ]
        }
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
      "message1": 'state %1',
      "args1": [
        {
          "type": "field_input",
          "name": "state",
          "check": "String"
        }
      ],
      "output": "frinex_deploymentType",
      "colour": 160
    },
    {
      "type": "frinex_validationServiceType",
      "message0": 'validationService %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "output": "frinex_validationServiceType",
      "colour": 160
    },
    {
      "type": "frinex_validationType",
      "message0": 'validation %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": 'errorField %1',
      "args1": [
        {
          "type": "field_input",
          "name": "errorField",
          "check": "String"
        }
      ],
      "message2": 'errorMessage %1',
      "args2": [
        {
          "type": "field_input",
          "name": "errorMessage",
          "check": "String"
        }
      ],
      "message3": 'allowValidationOnMissing %1',
      "args3": [
        {
          "type": "field_input",
          "name": "allowValidationOnMissing",
          "check": "String"
        }
      ],
      "message4": "recordMatchType %1",
      "args4": [
        {
          "type": "input_statement",
          "name": "recordMatchType",
          "check": [
            "frinex_recordMatchType",
            "frinex_fieldMatchType",
          ]
        }
      ],
      "previousStatement": [
        "frinex_validationType",
      ],
      "nextStatement": [
        "frinex_validationType",
      ],
      "colour": 160
    },
    {
      "type": "frinex_administrationType",
      "message0": 'administration %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": " %1",
      "args1": [
        {
          "type": "input_value",
          "name": "DO",
          "check": [
            "frinex_dataAgreementFieldType",
          ]
        }
      ],
      "message2": "adminUserType %1",
      "args2": [
        {
          "type": "input_statement",
          "name": "adminUserType",
          "check": [
            "frinex_adminUserType",
            "frinex_dataChannelType",
            "frinex_adminChartType",
            "frinex_dataTableType",
          ]
        }
      ],
      "output": "frinex_administrationType",
      "colour": 160
    },
    {
      "type": "frinex_adminChartType",
      "message0": 'chart %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": 'label %1',
      "args1": [
        {
          "type": "field_input",
          "name": "label",
          "check": "String"
        }
      ],
      "message2": 'type %1',
      "args2": [
        {
          "type": "field_input",
          "name": "type",
          "check": "String"
        }
      ],
      "message3": "metadataType %1",
      "args3": [
        {
          "type": "input_statement",
          "name": "metadataType",
          "check": [
            "frinex_metadataType",
            "frinex_stimulusResponseType",
          ]
        }
      ],
      "previousStatement": [
        "frinex_validationType",
        "frinex_adminUserType",
        "frinex_dataAgreementFieldType",
        "frinex_dataChannelType",
        "frinex_adminChartType",
        "frinex_dataTableType",
      ],
      "nextStatement": [
        "frinex_validationType",
        "frinex_adminUserType",
        "frinex_dataAgreementFieldType",
        "frinex_dataChannelType",
        "frinex_adminChartType",
        "frinex_dataTableType",
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
      "message1": 'label %1',
      "args1": [
        {
          "type": "field_input",
          "name": "label",
          "check": "String"
        }
      ],
      "message2": 'source %1',
      "args2": [
        {
          "type": "field_input",
          "name": "source",
          "check": "String"
        }
      ],
      "message3": 'columnNames %1',
      "args3": [
        {
          "type": "field_input",
          "name": "columnNames",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_validationType",
        "frinex_adminUserType",
        "frinex_dataAgreementFieldType",
        "frinex_dataChannelType",
        "frinex_adminChartType",
        "frinex_dataTableType",
      ],
      "nextStatement": [
        "frinex_validationType",
        "frinex_adminUserType",
        "frinex_dataAgreementFieldType",
        "frinex_dataChannelType",
        "frinex_adminChartType",
        "frinex_dataTableType",
      ],
      "colour": 160
    },
    {
      "type": "frinex_fieldType",
      "message0": 'field %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": 'controlledMessage %1',
      "args1": [
        {
          "type": "field_input",
          "name": "controlledMessage",
          "check": "String"
        }
      ],
      "message2": 'controlledRegex %1',
      "args2": [
        {
          "type": "field_input",
          "name": "controlledRegex",
          "check": "String"
        }
      ],
      "message3": 'postName %1',
      "args3": [
        {
          "type": "field_input",
          "name": "postName",
          "check": "String"
        }
      ],
      "message4": 'registrationField %1',
      "args4": [
        {
          "type": "field_input",
          "name": "registrationField",
          "check": "String"
        }
      ],
      "message5": " %1",
      "args5": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
              ]
          }
        ],
      "previousStatement": [
        "frinex_fieldType",
      ],
      "nextStatement": [
        "frinex_fieldType",
      ],
      "colour": 160
    },
    {
      "type": "frinex_presenterType",
      "message0": 'presenter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": " %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
              ]
          }
        ],
      "previousStatement": [
        "frinex_presenterType",
      ],
      "nextStatement": [
        "frinex_presenterType",
      ],
      "colour": 160
    },
    {
      "type": "frinex_stimulusType",
      "message0": 'stimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": 'identifier %1',
      "args1": [
        {
          "type": "field_input",
          "name": "identifier",
          "check": "String"
        }
      ],
      "message2": " %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusType",
      ],
      "nextStatement": [
        "frinex_stimulusType",
      ],
      "colour": 160
    },
    {
      "type": "frinex_htmlTextType",
      "message0": 'htmlText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_htmlTokenTextType",
      "message0": 'htmlTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_evaluateTokenTextType",
      "message0": 'evaluateTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_logTokenTextType",
      "message0": 'logTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_plainTextType",
      "message0": 'plainText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_imageType",
      "message0": 'image %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_menuItemType",
      "message0": 'menuItem %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_withStimuliType",
      "message0": 'withStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupStimuliType",
      "message0": 'groupStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_loadStimulusType",
      "message0": 'loadStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_withMatchingStimulusType",
      "message0": 'withMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_loadSdCardStimulusType",
      "message0": 'loadSdCardStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_currentStimulusHasTagType",
      "message0": 'currentStimulusHasTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_clearStimulusResponsesType",
      "message0": 'clearStimulusResponses %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_validateStimuliResponsesType",
      "message0": 'validateStimuliResponses %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusExistsType",
      "message0": 'stimulusExists %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showStimuliReportType",
      "message0": 'showStimuliReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendStimuliReportType",
      "message0": 'sendStimuliReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_targetButtonType",
      "message0": 'targetButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hotKeyInputType",
      "message0": 'hotKeyInput %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_actionButtonType",
      "message0": 'actionButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_actionTokenButtonType",
      "message0": 'actionTokenButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_disableButtonGroupType",
      "message0": 'disableButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_enableButtonGroupType",
      "message0": 'enableButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hideButtonGroupType",
      "message0": 'hideButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_styleButtonGroupType",
      "message0": 'styleButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showButtonGroupType",
      "message0": 'showButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_requestFocusType",
      "message0": 'requestFocus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgLoadGroupsType",
      "message0": 'svgLoadGroups %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgGroupAddType",
      "message0": 'svgGroupAdd %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgSetLabelType",
      "message0": 'svgSetLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgGroupShowType",
      "message0": 'svgGroupShow %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgGroupActionType",
      "message0": 'svgGroupAction %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_svgGroupMatchingType",
      "message0": 'svgGroupMatching %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusButtonType",
      "message0": 'stimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusSliderType",
      "message0": 'stimulusSlider %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchInputLabelButtonType",
      "message0": 'touchInputLabelButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchInputImageButtonType",
      "message0": 'touchInputImageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchInputVideoButtonType",
      "message0": 'touchInputVideoButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchInputCaptureType",
      "message0": 'touchInputCapture %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_captureStartType",
      "message0": 'captureStart %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchEndType",
      "message0": 'touchEnd %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_touchInputStopType",
      "message0": 'touchInputStop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ratingButtonType",
      "message0": 'ratingButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ratingRadioButtonType",
      "message0": 'ratingRadioButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ratingCheckboxType",
      "message0": 'ratingCheckbox %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusFreeTextType",
      "message0": 'stimulusFreeText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusRatingButtonType",
      "message0": 'stimulusRatingButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusRatingRadioType",
      "message0": 'stimulusRatingRadio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusRatingCheckboxType",
      "message0": 'stimulusRatingCheckbox %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusHasRatingOptionsType",
      "message0": 'stimulusHasRatingOptions %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_clearStimulusResponseType",
      "message0": 'clearStimulusResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusHasResponseType",
      "message0": 'stimulusHasResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_setStimulusCodeResponseType",
      "message0": 'setStimulusCodeResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addStimulusCodeResponseValidationType",
      "message0": 'addStimulusCodeResponseValidation %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ratingFooterButtonType",
      "message0": 'ratingFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_targetFooterButtonType",
      "message0": 'targetFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_actionFooterButtonType",
      "message0": 'actionFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addPaddingType",
      "message0": 'addPadding %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_localStorageDataType",
      "message0": 'localStorageData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimuliValidationType",
      "message0": 'stimuliValidation %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addKeyboardDebugType",
      "message0": 'addKeyboardDebug %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addDebugWidgetsType",
      "message0": 'addDebugWidgets %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_allMetadataFieldsType",
      "message0": 'allMetadataFields %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_metadataFieldType",
      "message0": 'metadataField %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusMetadataFieldType",
      "message0": 'stimulusMetadataField %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_metadataFieldConnectionType",
      "message0": 'metadataFieldConnection %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_metadataFieldVisibilityDependantType",
      "message0": 'metadataFieldVisibilityDependant %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_metadataFieldDateTriggeredType",
      "message0": 'metadataFieldDateTriggered %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_saveMetadataButtonType",
      "message0": 'saveMetadataButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_createUserButtonType",
      "message0": 'createUserButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_switchUserIdButtonType",
      "message0": 'switchUserIdButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_selectUserMenuType",
      "message0": 'selectUserMenu %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_selectLocaleMenuType",
      "message0": 'selectLocaleMenu %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_eraseLocalStorageButtonType",
      "message0": 'eraseLocalStorageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_eraseUsersDataButtonType",
      "message0": 'eraseUsersDataButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showCurrentMsType",
      "message0": 'showCurrentMs %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_cancelPauseTimersType",
      "message0": 'cancelPauseTimers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_cancelPauseAllType",
      "message0": 'cancelPauseAll %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showStimulusProgressType",
      "message0": 'showStimulusProgress %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_displayCompletionCodeType",
      "message0": 'displayCompletionCode %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_generateCompletionCodeType",
      "message0": 'generateCompletionCode %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendAllDataType",
      "message0": 'sendAllData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendMetadataType",
      "message0": 'sendMetadata %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_redirectToUrlType",
      "message0": 'redirectToUrl %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_eraseLocalStorageOnWindowClosingType",
      "message0": 'eraseLocalStorageOnWindowClosing %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_keepStimulusType",
      "message0": 'keepStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_removeMatchingStimulusType",
      "message0": 'removeMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_centrePageType",
      "message0": 'centrePage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_clearPageType",
      "message0": 'clearPage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_backgroundImageType",
      "message0": 'backgroundImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_allMenuItemsType",
      "message0": 'allMenuItems %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_prevStimulusButtonType",
      "message0": 'prevStimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_nextStimulusButtonType",
      "message0": 'nextStimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_nextStimulusType",
      "message0": 'nextStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_prevStimulusType",
      "message0": 'prevStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_nextMatchingStimulusType",
      "message0": 'nextMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addKinTypeGuiType",
      "message0": 'addKinTypeGui %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hasGetParameterType",
      "message0": 'hasGetParameter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hasMetadataValueType",
      "message0": 'hasMetadataValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_setMetadataValueType",
      "message0": 'setMetadataValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_matchOnEvalTokensType",
      "message0": 'matchOnEvalTokens %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_progressIndicatorType",
      "message0": 'progressIndicator %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_setMetadataEvalTokensType",
      "message0": 'setMetadataEvalTokens %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_activateRandomItemType",
      "message0": 'activateRandomItem %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_gotoPresenterType",
      "message0": 'gotoPresenter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_gotoNextPresenterType",
      "message0": 'gotoNextPresenter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_logTimeStampType",
      "message0": 'logTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_zeroStimulusStopwatchType",
      "message0": 'zeroStimulusStopwatch %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stopStimulusStopwatchType",
      "message0": 'stopStimulusStopwatch %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hardwareTimeStampType",
      "message0": 'hardwareTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_recorderToneInjectionType",
      "message0": 'recorderToneInjection %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_dtmfToneType",
      "message0": 'dtmfTone %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_audioButtonType",
      "message0": 'audioButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_preloadAllStimuliType",
      "message0": 'preloadAllStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showStimulusType",
      "message0": 'showStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showStimulusGridType",
      "message0": 'showStimulusGrid %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_matchingStimulusGridType",
      "message0": 'matchingStimulusGrid %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_pauseType",
      "message0": 'pause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_doLaterType",
      "message0": 'doLater %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_requestNotificationType",
      "message0": 'requestNotification %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_startTimerType",
      "message0": 'startTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_compareTimerType",
      "message0": 'compareTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_clearTimerType",
      "message0": 'clearTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_logTimerValueType",
      "message0": 'logTimerValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_timerLabelType",
      "message0": 'timerLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_randomMsPauseType",
      "message0": 'randomMsPause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_evaluatePauseType",
      "message0": 'evaluatePause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addTimerTriggerType",
      "message0": 'addTimerTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_startFrameRateTimerType",
      "message0": 'startFrameRateTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addFrameTimeTriggerType",
      "message0": 'addFrameTimeTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addMediaTriggerType",
      "message0": 'addMediaTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addRecorderDtmfTriggerType",
      "message0": 'addRecorderDtmfTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_addRecorderLevelTriggerType",
      "message0": 'addRecorderLevelTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_triggerDefinitionType",
      "message0": 'triggerDefinition %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_habituationParadigmListenerType",
      "message0": 'habituationParadigmListener %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_triggerMatchingType",
      "message0": 'triggerMatching %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_triggerRandomType",
      "message0": 'triggerRandom %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_resetTriggerType",
      "message0": 'resetTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_countdownLabelType",
      "message0": 'countdownLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusPauseType",
      "message0": 'stimulusPause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusLabelType",
      "message0": 'stimulusLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onTimeType",
      "message0": 'onTime %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onTimerType",
      "message0": 'onTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_conditionTrueType",
      "message0": 'conditionTrue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_conditionFalseType",
      "message0": 'conditionFalse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onSuccessType",
      "message0": 'onSuccess %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onActivateType",
      "message0": 'onActivate %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_kinTypeStringDiagramType",
      "message0": 'kinTypeStringDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_loadKinTypeStringDiagramType",
      "message0": 'loadKinTypeStringDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_editableKinEntitesDiagramType",
      "message0": 'editableKinEntitesDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onKeyUpType",
      "message0": 'onKeyUp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onKeyDownType",
      "message0": 'onKeyDown %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_responseCorrectType",
      "message0": 'responseCorrect %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_responseIncorrectType",
      "message0": 'responseIncorrect %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupNetworkType",
      "message0": 'groupNetwork %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_hasMoreStimulusType",
      "message0": 'hasMoreStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_beforeStimulusType",
      "message0": 'beforeStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_eachStimulusType",
      "message0": 'eachStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_afterStimulusType",
      "message0": 'afterStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_endOfStimulusType",
      "message0": 'endOfStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_existingUserCheckType",
      "message0": 'existingUserCheck %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_multipleUsersType",
      "message0": 'multipleUsers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_singleUserType",
      "message0": 'singleUser %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_aboveThresholdType",
      "message0": 'aboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_withinThresholdType",
      "message0": 'withinThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_mediaLoadedType",
      "message0": 'mediaLoaded %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_mediaLoadFailedType",
      "message0": 'mediaLoadFailed %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_mediaPlaybackStartedType",
      "message0": 'mediaPlaybackStarted %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_mediaPlaybackCompleteType",
      "message0": 'mediaPlaybackComplete %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_tableType",
      "message0": 'table %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_rowType",
      "message0": 'row %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_columnType",
      "message0": 'column %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionAppendType",
      "message0": 'regionAppend %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionStyleType",
      "message0": 'regionStyle %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionCodeStyleType",
      "message0": 'regionCodeStyle %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionReplaceType",
      "message0": 'regionReplace %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionClearType",
      "message0": 'regionClear %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_regionDragDropType",
      "message0": 'regionDragDrop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ondragstartType",
      "message0": 'ondragstart %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ondragoverType",
      "message0": 'ondragover %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_ondropType",
      "message0": 'ondrop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusPresentType",
      "message0": 'stimulusPresent %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusImageType",
      "message0": 'stimulusImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusCodeImageType",
      "message0": 'stimulusCodeImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusCodeImageButtonType",
      "message0": 'stimulusCodeImageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusCodeVideoType",
      "message0": 'stimulusCodeVideo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusVideoType",
      "message0": 'stimulusVideo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusCodeAudioType",
      "message0": 'stimulusCodeAudio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusAudioType",
      "message0": 'stimulusAudio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_playMediaType",
      "message0": 'playMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_rewindMediaType",
      "message0": 'rewindMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_pauseMediaType",
      "message0": 'pauseMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_logMediaTimeStampType",
      "message0": 'logMediaTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stimulusImageCaptureType",
      "message0": 'stimulusImageCapture %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_VideoPanelType",
      "message0": 'VideoPanel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_AnnotationTimelinePanelType",
      "message0": 'AnnotationTimelinePanel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_audioInputSelectWebType",
      "message0": 'audioInputSelectWeb %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_startAudioRecorderWebType",
      "message0": 'startAudioRecorderWeb %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_startAudioRecorderAppType",
      "message0": 'startAudioRecorderApp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_stopAudioRecorderType",
      "message0": 'stopAudioRecorder %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_startAudioRecorderTagType",
      "message0": 'startAudioRecorderTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_endAudioRecorderTagType",
      "message0": 'endAudioRecorderTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showHtmlPopupType",
      "message0": 'showHtmlPopup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_helpDialogueType",
      "message0": 'helpDialogue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_userInfoType",
      "message0": 'userInfo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_versionDataType",
      "message0": 'versionData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_showColourReportType",
      "message0": 'showColourReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupInitialisationErrorType",
      "message0": 'groupInitialisationError %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupNetworkConnectingType",
      "message0": 'groupNetworkConnecting %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupFindingMembersType",
      "message0": 'groupFindingMembers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupNetworkSynchronisingType",
      "message0": 'groupNetworkSynchronising %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupPhaseListenersType",
      "message0": 'groupPhaseListeners %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupMemberActivityType",
      "message0": 'groupMemberActivity %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupMemberCodeLabelType",
      "message0": 'groupMemberCodeLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupMemberLabelType",
      "message0": 'groupMemberLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupMessageLabelType",
      "message0": 'groupMessageLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupResponseStimulusImageType",
      "message0": 'groupResponseStimulusImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupResponseFeedbackType",
      "message0": 'groupResponseFeedback %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupScoreLabelType",
      "message0": 'groupScoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_groupChannelScoreLabelType",
      "message0": 'groupChannelScoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_scoreLabelType",
      "message0": 'scoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_submitGroupEventType",
      "message0": 'submitGroupEvent %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendGroupMessageButtonType",
      "message0": 'sendGroupMessageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendGroupMessageType",
      "message0": 'sendGroupMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendGroupStoredMessageType",
      "message0": 'sendGroupStoredMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_streamGroupCanvasType",
      "message0": 'streamGroupCanvas %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_streamGroupCameraType",
      "message0": 'streamGroupCamera %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_sendGroupTokenMessageType",
      "message0": 'sendGroupTokenMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_clearCurrentScoreType",
      "message0": 'clearCurrentScore %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_scoreIncrementType",
      "message0": 'scoreIncrement %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_bestScoreAboveThresholdType",
      "message0": 'bestScoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_totalScoreAboveThresholdType",
      "message0": 'totalScoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_scoreAboveThresholdType",
      "message0": 'scoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_resetStimulusType",
      "message0": 'resetStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_submitTestResultsType",
      "message0": 'submitTestResults %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_validateMetadataType",
      "message0": 'validateMetadata %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_transmitResultsType",
      "message0": 'transmitResults %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_onErrorType",
      "message0": 'onError %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_randomGroupingType",
      "message0": 'randomGrouping %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": " %1",
      "args1": [
        {
          "type": "input_value",
          "name": "DO",
          "check": [
            "frinex_tagType",
            "frinex_listType",
          ]
        }
      ],
      "output": "frinex_randomGroupingType",
      "colour": 160
    },
    {
      "type": "frinex_stimuliSelectType",
      "message0": 'stimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "output": "frinex_stimuliSelectType",
      "colour": 160
    },
    {
      "type": "frinex_TemplateAType",
      "message0": 'TemplateA %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_TemplateBType",
      "message0": 'TemplateB %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
    {
      "type": "frinex_TemplateCType",
      "message0": 'TemplateC %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ]},
  ]);
    javascript.javascriptGenerator.forBlock['frinex_experimentType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_experimentType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_deploymentType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_deploymentType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validationServiceType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_validationServiceType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validationType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_validationType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_administrationType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_administrationType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_adminChartType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_adminChartType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_dataTableType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_dataTableType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_fieldType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_fieldType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_presenterType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_presenterType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_randomGroupingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_randomGroupingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliSelectType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimuliSelectType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_htmlTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTokenTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_htmlTokenTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluateTokenTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_evaluateTokenTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTokenTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_logTokenTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_plainTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_plainTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_imageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_imageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_menuItemType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_menuItemType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withStimuliType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_withStimuliType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupStimuliType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupStimuliType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_loadStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_withMatchingStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadSdCardStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_loadSdCardStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_currentStimulusHasTagType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_currentStimulusHasTagType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponsesType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_clearStimulusResponsesType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validateStimuliResponsesType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_validateStimuliResponsesType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusExistsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusExistsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimuliReportType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showStimuliReportType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendStimuliReportType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendStimuliReportType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_targetButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_targetButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hotKeyInputType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hotKeyInputType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_actionButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionTokenButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_actionTokenButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_disableButtonGroupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_disableButtonGroupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_enableButtonGroupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_enableButtonGroupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hideButtonGroupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hideButtonGroupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_styleButtonGroupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_styleButtonGroupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showButtonGroupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showButtonGroupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_requestFocusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_requestFocusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgLoadGroupsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgLoadGroupsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupAddType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgGroupAddType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgSetLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgSetLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupShowType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgGroupShowType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupActionType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgGroupActionType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupMatchingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_svgGroupMatchingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusSliderType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusSliderType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputLabelButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchInputLabelButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputImageButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchInputImageButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputVideoButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchInputVideoButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputCaptureType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchInputCaptureType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_captureStartType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_captureStartType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchEndType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchEndType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputStopType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_touchInputStopType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ratingButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingRadioButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ratingRadioButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingCheckboxType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ratingCheckboxType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusFreeTextType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusFreeTextType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusRatingButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingRadioType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusRatingRadioType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingCheckboxType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusRatingCheckboxType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasRatingOptionsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusHasRatingOptionsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_clearStimulusResponseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasResponseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusHasResponseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setStimulusCodeResponseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_setStimulusCodeResponseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addStimulusCodeResponseValidationType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addStimulusCodeResponseValidationType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingFooterButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ratingFooterButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_targetFooterButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_targetFooterButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionFooterButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_actionFooterButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addPaddingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addPaddingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_localStorageDataType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_localStorageDataType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliValidationType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimuliValidationType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addKeyboardDebugType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addKeyboardDebugType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addDebugWidgetsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addDebugWidgetsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_allMetadataFieldsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_allMetadataFieldsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_metadataFieldType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusMetadataFieldType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusMetadataFieldType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldConnectionType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_metadataFieldConnectionType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldVisibilityDependantType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_metadataFieldVisibilityDependantType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldDateTriggeredType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_metadataFieldDateTriggeredType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_saveMetadataButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_saveMetadataButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_createUserButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_createUserButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_switchUserIdButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_switchUserIdButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_selectUserMenuType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_selectUserMenuType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_selectLocaleMenuType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_selectLocaleMenuType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_eraseLocalStorageButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseUsersDataButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_eraseUsersDataButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showCurrentMsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showCurrentMsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseTimersType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_cancelPauseTimersType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseAllType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_cancelPauseAllType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusProgressType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showStimulusProgressType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_displayCompletionCodeType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_displayCompletionCodeType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_generateCompletionCodeType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_generateCompletionCodeType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendAllDataType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendAllDataType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendMetadataType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendMetadataType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_redirectToUrlType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_redirectToUrlType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageOnWindowClosingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_eraseLocalStorageOnWindowClosingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_keepStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_keepStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_removeMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_removeMatchingStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_centrePageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_centrePageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearPageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_clearPageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_backgroundImageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_backgroundImageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_allMenuItemsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_allMenuItemsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_prevStimulusButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_nextStimulusButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_nextStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_prevStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_nextMatchingStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addKinTypeGuiType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addKinTypeGuiType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasGetParameterType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hasGetParameterType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMetadataValueType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hasMetadataValueType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataValueType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_setMetadataValueType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_matchOnEvalTokensType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_matchOnEvalTokensType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_progressIndicatorType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_progressIndicatorType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataEvalTokensType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_setMetadataEvalTokensType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_activateRandomItemType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_activateRandomItemType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoPresenterType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_gotoPresenterType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoNextPresenterType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_gotoNextPresenterType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimeStampType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_logTimeStampType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_zeroStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_zeroStimulusStopwatchType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stopStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stopStimulusStopwatchType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hardwareTimeStampType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hardwareTimeStampType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_recorderToneInjectionType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_recorderToneInjectionType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_dtmfToneType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_dtmfToneType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_audioButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_audioButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_preloadAllStimuliType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_preloadAllStimuliType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusGridType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showStimulusGridType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_matchingStimulusGridType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_matchingStimulusGridType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_pauseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_doLaterType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_doLaterType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_requestNotificationType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_requestNotificationType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startTimerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_startTimerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_compareTimerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_compareTimerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearTimerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_clearTimerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimerValueType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_logTimerValueType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_timerLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_timerLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_randomMsPauseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_randomMsPauseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluatePauseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_evaluatePauseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addTimerTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addTimerTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startFrameRateTimerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_startFrameRateTimerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addFrameTimeTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addFrameTimeTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addMediaTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addMediaTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderDtmfTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addRecorderDtmfTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderLevelTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_addRecorderLevelTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerDefinitionType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_triggerDefinitionType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_habituationParadigmListenerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_habituationParadigmListenerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerMatchingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_triggerMatchingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerRandomType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_triggerRandomType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_resetTriggerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_resetTriggerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_countdownLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_countdownLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPauseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusPauseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimeType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onTimeType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimerType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onTimerType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionTrueType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_conditionTrueType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionFalseType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_conditionFalseType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onSuccessType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onSuccessType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onActivateType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onActivateType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_kinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_kinTypeStringDiagramType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadKinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_loadKinTypeStringDiagramType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_editableKinEntitesDiagramType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_editableKinEntitesDiagramType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyUpType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onKeyUpType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyDownType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onKeyDownType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_responseCorrectType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_responseCorrectType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_responseIncorrectType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_responseIncorrectType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupNetworkType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMoreStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_hasMoreStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_beforeStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_beforeStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eachStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_eachStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_afterStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_afterStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_endOfStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_endOfStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_existingUserCheckType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_existingUserCheckType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_multipleUsersType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_multipleUsersType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_singleUserType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_singleUserType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_aboveThresholdType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_aboveThresholdType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withinThresholdType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_withinThresholdType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadedType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_mediaLoadedType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadFailedType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_mediaLoadFailedType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackStartedType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_mediaPlaybackStartedType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackCompleteType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_mediaPlaybackCompleteType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_tableType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_tableType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_rowType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_rowType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_columnType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_columnType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionAppendType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionAppendType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionStyleType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionStyleType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionCodeStyleType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionCodeStyleType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionReplaceType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionReplaceType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionClearType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionClearType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionDragDropType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_regionDragDropType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragstartType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ondragstartType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragoverType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ondragoverType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondropType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_ondropType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPresentType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusPresentType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusImageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusCodeImageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusCodeImageButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeVideoType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusCodeVideoType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusVideoType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusVideoType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeAudioType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusCodeAudioType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusAudioType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusAudioType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_playMediaType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_playMediaType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_rewindMediaType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_rewindMediaType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseMediaType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_pauseMediaType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logMediaTimeStampType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_logMediaTimeStampType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageCaptureType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stimulusImageCaptureType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_VideoPanelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_VideoPanelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_AnnotationTimelinePanelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_AnnotationTimelinePanelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_audioInputSelectWebType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_audioInputSelectWebType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderWebType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_startAudioRecorderWebType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderAppType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_startAudioRecorderAppType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stopAudioRecorderType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_stopAudioRecorderType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_startAudioRecorderTagType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_endAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_endAudioRecorderTagType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showHtmlPopupType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showHtmlPopupType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_helpDialogueType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_helpDialogueType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_userInfoType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_userInfoType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_versionDataType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_versionDataType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showColourReportType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_showColourReportType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupInitialisationErrorType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupInitialisationErrorType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkConnectingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupNetworkConnectingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupFindingMembersType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupFindingMembersType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkSynchronisingType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupNetworkSynchronisingType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupPhaseListenersType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupPhaseListenersType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberActivityType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupMemberActivityType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberCodeLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupMemberCodeLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupMemberLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMessageLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupMessageLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseStimulusImageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupResponseStimulusImageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseFeedbackType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupResponseFeedbackType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupScoreLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupScoreLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupChannelScoreLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_groupChannelScoreLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreLabelType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_scoreLabelType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_submitGroupEventType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_submitGroupEventType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageButtonType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendGroupMessageButtonType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendGroupMessageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupStoredMessageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendGroupStoredMessageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCanvasType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_streamGroupCanvasType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCameraType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_streamGroupCameraType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupTokenMessageType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_sendGroupTokenMessageType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearCurrentScoreType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_clearCurrentScoreType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreIncrementType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_scoreIncrementType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_bestScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_bestScoreAboveThresholdType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_totalScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_totalScoreAboveThresholdType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_scoreAboveThresholdType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_resetStimulusType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_resetStimulusType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_submitTestResultsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_submitTestResultsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validateMetadataType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_validateMetadataType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_transmitResultsType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_transmitResultsType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onErrorType'] = function(block, generator) {
    var childData = '';
    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++){     childData += generator.statementToCode(block, block.getChildren()[childIndex]);
    }
    return 'frinex_onErrorType(\'block_id_' + block.id + '\', '  + childData + '\);\n';
  };
  return {
    "kind": "categoryToolbox",
    "contents": [
            {
                "kind":"category",
            "name":"Experiment",
            "categorystyle":"logic_category",            "contents":[      {
        "kind": "block",
        "type": "frinex_experimentType"
      },
      {
        "kind": "block",
        "type": "frinex_deploymentType"
      },
      {
        "kind": "block",
        "type": "frinex_validationServiceType"
      },
      {
        "kind": "block",
        "type": "frinex_validationType"
      },
      {
        "kind": "block",
        "type": "frinex_administrationType"
      },
      {
        "kind": "block",
        "type": "frinex_adminChartType"
      },
      {
        "kind": "block",
        "type": "frinex_dataTableType"
      },
      {
        "kind": "block",
        "type": "frinex_fieldType"
      },
      {
        "kind": "block",
        "type": "frinex_presenterType"
      },
      {
        "kind": "block",
        "type": "frinex_stimulusType"
      },
      {
        "kind": "block",
        "type": "frinex_randomGroupingType"
      },
      {
        "kind": "block",
        "type": "frinex_stimuliSelectType"
      },
            ]}, {
                "kind":"category",
            "name":"Feature",
            "categorystyle":"loop_category",            "contents":[      {
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
            ]}, {
                "kind":"category",
            "name":"Template",
            "categorystyle":"logic_category",            "contents":[      {
        "kind": "block",
        "type": "frinex_TemplateAType"
      },
      {
        "kind": "block",
        "type": "frinex_TemplateBType"
      },
      {
        "kind": "block",
        "type": "frinex_TemplateCType"
      },
    ]}]
  };
}
