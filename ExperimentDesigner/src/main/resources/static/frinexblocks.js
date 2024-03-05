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
                "frinex_anyType",
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
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_htmlTokenTextType",
      "message0": 'htmlTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_evaluateTokenTextType",
      "message0": 'evaluateTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_logTokenTextType",
      "message0": 'logTokenText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_plainTextType",
      "message0": 'plainText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_imageType",
      "message0": 'image %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoading %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_menuItemType",
      "message0": 'menuItem %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_withStimuliType",
      "message0": 'withStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "eachStimulus %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_eachStimulusType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupStimuliType",
      "message0": 'groupStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "groupStimulus %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_groupStimulusType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_loadStimulusType",
      "message0": 'loadStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMoreStimulus %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "hasMoreStimulus",
              "check": [
                "frinex_stimulusActionType",
              ]
          }
        ],
      "message2": "endOfStimulus %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "endOfStimulus",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_withMatchingStimulusType",
      "message0": 'withMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMoreStimulus %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "hasMoreStimulus",
              "check": [
                "frinex_stimulusActionType",
              ]
          }
        ],
      "message2": "endOfStimulus %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "endOfStimulus",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_loadSdCardStimulusType",
      "message0": 'loadSdCardStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMoreStimulus %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "hasMoreStimulus",
              "check": [
                "frinex_stimulusActionType",
              ]
          }
        ],
      "message2": "endOfStimulus %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "endOfStimulus",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_currentStimulusHasTagType",
      "message0": 'currentStimulusHasTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_clearStimulusResponsesType",
      "message0": 'clearStimulusResponses %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_validateStimuliResponsesType",
      "message0": 'validateStimuliResponses %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusExistsType",
      "message0": 'stimulusExists %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showStimuliReportType",
      "message0": 'showStimuliReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendStimuliReportType",
      "message0": 'sendStimuliReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_targetButtonType",
      "message0": 'targetButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hotKeyInputType",
      "message0": 'hotKeyInput %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasKeyInputsCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasKeyInputsConditionType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_actionButtonType",
      "message0": 'actionButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasActionButtonsType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_hasActionButtonsType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_actionTokenButtonType",
      "message0": 'actionTokenButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasActionButtonsType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_hasActionButtonsType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_disableButtonGroupType",
      "message0": 'disableButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_enableButtonGroupType",
      "message0": 'enableButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hideButtonGroupType",
      "message0": 'hideButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_styleButtonGroupType",
      "message0": 'styleButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showButtonGroupType",
      "message0": 'showButtonGroup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_requestFocusType",
      "message0": 'requestFocus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgLoadGroupsType",
      "message0": 'svgLoadGroups %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "svgGroupsLoaded %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_svgGroupsLoadedType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgGroupAddType",
      "message0": 'svgGroupAdd %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "nextStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgSetLabelType",
      "message0": 'svgSetLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "nextStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgGroupShowType",
      "message0": 'svgGroupShow %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "nextStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgGroupActionType",
      "message0": 'svgGroupAction %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "nextStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_svgGroupMatchingType",
      "message0": 'svgGroupMatching %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "nextStatement": [
        "frinex_svgGroupsLoadedType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusButtonType",
      "message0": 'stimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusSliderType",
      "message0": 'stimulusSlider %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchInputLabelButtonType",
      "message0": 'touchInputLabelButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchInputImageButtonType",
      "message0": 'touchInputImageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoadingButton %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingButtonType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchInputVideoButtonType",
      "message0": 'touchInputVideoButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoadingButton %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingButtonType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchInputCaptureType",
      "message0": 'touchInputCapture %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "touchInputCaptureType %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_touchInputCaptureTypeType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_captureStartType",
      "message0": 'captureStart %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "touchInputStartType %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_touchInputStartTypeType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_touchInputCaptureTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputCaptureTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchEndType",
      "message0": 'touchEnd %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_touchInputCaptureTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputCaptureTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_touchInputStopType",
      "message0": 'touchInputStop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "nextStatement": [
        "frinex_touchInputStartTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ratingButtonType",
      "message0": 'ratingButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ratingRadioButtonType",
      "message0": 'ratingRadioButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ratingCheckboxType",
      "message0": 'ratingCheckbox %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusFreeTextType",
      "message0": 'stimulusFreeText %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusRatingButtonType",
      "message0": 'stimulusRatingButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusRatingRadioType",
      "message0": 'stimulusRatingRadio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusRatingCheckboxType",
      "message0": 'stimulusRatingCheckbox %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusHasRatingOptionsType",
      "message0": 'stimulusHasRatingOptions %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_clearStimulusResponseType",
      "message0": 'clearStimulusResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusHasResponseType",
      "message0": 'stimulusHasResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_setStimulusCodeResponseType",
      "message0": 'setStimulusCodeResponse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addStimulusCodeResponseValidationType",
      "message0": 'addStimulusCodeResponseValidation %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ratingFooterButtonType",
      "message0": 'ratingFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_targetFooterButtonType",
      "message0": 'targetFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_actionFooterButtonType",
      "message0": 'actionFooterButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addPaddingType",
      "message0": 'addPadding %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_localStorageDataType",
      "message0": 'localStorageData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimuliValidationType",
      "message0": 'stimuliValidation %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addKeyboardDebugType",
      "message0": 'addKeyboardDebug %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_uploadUsersDataMenuType",
      "message0": 'uploadUsersDataMenu %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addDebugWidgetsType",
      "message0": 'addDebugWidgets %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_allMetadataFieldsType",
      "message0": 'allMetadataFields %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_metadataFieldType",
      "message0": 'metadataField %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusMetadataFieldType",
      "message0": 'stimulusMetadataField %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_metadataFieldConnectionType",
      "message0": 'metadataFieldConnection %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_metadataFieldVisibilityDependantType",
      "message0": 'metadataFieldVisibilityDependant %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_metadataFieldDateTriggeredType",
      "message0": 'metadataFieldDateTriggered %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_saveMetadataButtonType",
      "message0": 'saveMetadataButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "onSuccess %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message3": "onError %1",
      "args3": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_createUserButtonType",
      "message0": 'createUserButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_switchUserIdButtonType",
      "message0": 'switchUserIdButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "onSuccess %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message3": "onError %1",
      "args3": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_selectUserMenuType",
      "message0": 'selectUserMenu %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_selectLocaleMenuType",
      "message0": 'selectLocaleMenu %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_eraseLocalStorageButtonType",
      "message0": 'eraseLocalStorageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_eraseUsersDataButtonType",
      "message0": 'eraseUsersDataButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showCurrentMsType",
      "message0": 'showCurrentMs %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_cancelPauseTimersType",
      "message0": 'cancelPauseTimers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_cancelPauseAllType",
      "message0": 'cancelPauseAll %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showStimulusProgressType",
      "message0": 'showStimulusProgress %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_displayCompletionCodeType",
      "message0": 'displayCompletionCode %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_generateCompletionCodeType",
      "message0": 'generateCompletionCode %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendAllDataType",
      "message0": 'sendAllData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendMetadataType",
      "message0": 'sendMetadata %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_redirectToUrlType",
      "message0": 'redirectToUrl %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_eraseLocalStorageOnWindowClosingType",
      "message0": 'eraseLocalStorageOnWindowClosing %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_keepStimulusType",
      "message0": 'keepStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_removeMatchingStimulusType",
      "message0": 'removeMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_centrePageType",
      "message0": 'centrePage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_clearPageType",
      "message0": 'clearPage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_backgroundImageType",
      "message0": 'backgroundImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_allMenuItemsType",
      "message0": 'allMenuItems %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_prevStimulusButtonType",
      "message0": 'prevStimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_nextStimulusButtonType",
      "message0": 'nextStimulusButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_nextStimulusType",
      "message0": 'nextStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_prevStimulusType",
      "message0": 'prevStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_nextMatchingStimulusType",
      "message0": 'nextMatchingStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addKinTypeGuiType",
      "message0": 'addKinTypeGui %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hasGetParameterType",
      "message0": 'hasGetParameter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hasMetadataValueType",
      "message0": 'hasMetadataValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_setMetadataValueType",
      "message0": 'setMetadataValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_matchOnEvalTokensType",
      "message0": 'matchOnEvalTokens %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "conditionTrue %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "conditionTrue",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "conditionFalse %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "conditionFalse",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message3": "onError %1",
      "args3": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_progressIndicatorType",
      "message0": 'progressIndicator %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_setMetadataEvalTokensType",
      "message0": 'setMetadataEvalTokens %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_activateRandomItemType",
      "message0": 'activateRandomItem %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_gotoPresenterType",
      "message0": 'gotoPresenter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_gotoNextPresenterType",
      "message0": 'gotoNextPresenter %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_logTimeStampType",
      "message0": 'logTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_zeroStimulusStopwatchType",
      "message0": 'zeroStimulusStopwatch %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stopStimulusStopwatchType",
      "message0": 'stopStimulusStopwatch %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hardwareTimeStampType",
      "message0": 'hardwareTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_recorderToneInjectionType",
      "message0": 'recorderToneInjection %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_dtmfToneType",
      "message0": 'dtmfTone %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_audioButtonType",
      "message0": 'audioButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_preloadAllStimuliType",
      "message0": 'preloadAllStimuli %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showStimulusType",
      "message0": 'showStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showStimulusGridType",
      "message0": 'showStimulusGrid %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "responseCorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "responseCorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "responseIncorrect %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "responseIncorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_matchingStimulusGridType",
      "message0": 'matchingStimulusGrid %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "responseCorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "responseCorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "responseIncorrect %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "responseIncorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_pauseType",
      "message0": 'pause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_doLaterType",
      "message0": 'doLater %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_requestNotificationType",
      "message0": 'requestNotification %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "onSuccess %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message3": "onError %1",
      "args3": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_startTimerType",
      "message0": 'startTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_compareTimerType",
      "message0": 'compareTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasThreshold %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasThresholdType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_clearTimerType",
      "message0": 'clearTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_logTimerValueType",
      "message0": 'logTimerValue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_timerLabelType",
      "message0": 'timerLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_randomMsPauseType",
      "message0": 'randomMsPause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_evaluatePauseType",
      "message0": 'evaluatePause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addTimerTriggerType",
      "message0": 'addTimerTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasErrorTimer %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasErrorTimerType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_startFrameRateTimerType",
      "message0": 'startFrameRateTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasFrameRateTriggers %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasFrameRateTriggersType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addFrameTimeTriggerType",
      "message0": 'addFrameTimeTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasErrorTimeCritical %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasErrorTimeCriticalType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasFrameRateTriggersType",
      ],
      "nextStatement": [
        "frinex_hasFrameRateTriggersType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addMediaTriggerType",
      "message0": 'addMediaTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasErrorTimeCritical %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasErrorTimeCriticalType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addRecorderDtmfTriggerType",
      "message0": 'addRecorderDtmfTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "isTimeCritical %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_isTimeCriticalType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_isRecursiveTypeType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_isRecursiveTypeType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_addRecorderLevelTriggerType",
      "message0": 'addRecorderLevelTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "isTimeCritical %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_isTimeCriticalType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_isRecursiveTypeType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_isRecursiveTypeType",
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_triggerDefinitionType",
      "message0": 'triggerDefinition %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_habituationParadigmListenerType",
      "message0": 'habituationParadigmListener %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_triggerMatchingType",
      "message0": 'triggerMatching %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_triggerRandomType",
      "message0": 'triggerRandom %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_resetTriggerType",
      "message0": 'resetTrigger %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_countdownLabelType",
      "message0": 'countdownLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusPauseType",
      "message0": 'stimulusPause %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusLabelType",
      "message0": 'stimulusLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onTimeType",
      "message0": 'onTime %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "isTimeCritical %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_isTimeCriticalType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasErrorTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_hasErrorTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onTimerType",
      "message0": 'onTimer %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasErrorTimerType",
      ],
      "nextStatement": [
        "frinex_hasErrorTimerType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_conditionTrueType",
      "message0": 'conditionTrue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasTrueFalseConditionType",
        "frinex_hasTrueFalseErrorConditionType",
      ],
      "nextStatement": [
        "frinex_hasTrueFalseConditionType",
        "frinex_hasTrueFalseErrorConditionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_conditionFalseType",
      "message0": 'conditionFalse %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasTrueFalseConditionType",
        "frinex_hasTrueFalseErrorConditionType",
      ],
      "nextStatement": [
        "frinex_hasTrueFalseConditionType",
        "frinex_hasTrueFalseErrorConditionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onSuccessType",
      "message0": 'onSuccess %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasErrorSuccessType",
      ],
      "nextStatement": [
        "frinex_hasErrorSuccessType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onActivateType",
      "message0": 'onActivate %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMediaLoadingButtonType",
      ],
      "nextStatement": [
        "frinex_hasMediaLoadingButtonType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_kinTypeStringDiagramType",
      "message0": 'kinTypeStringDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_loadKinTypeStringDiagramType",
      "message0": 'loadKinTypeStringDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_editableKinEntitesDiagramType",
      "message0": 'editableKinEntitesDiagram %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onKeyUpType",
      "message0": 'onKeyUp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasKeyInputsConditionType",
      ],
      "nextStatement": [
        "frinex_hasKeyInputsConditionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onKeyDownType",
      "message0": 'onKeyDown %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasKeyInputsConditionType",
      ],
      "nextStatement": [
        "frinex_hasKeyInputsConditionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_responseCorrectType",
      "message0": 'responseCorrect %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasCorrectIncorrectType",
      ],
      "nextStatement": [
        "frinex_hasCorrectIncorrectType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_responseIncorrectType",
      "message0": 'responseIncorrect %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasCorrectIncorrectType",
      ],
      "nextStatement": [
        "frinex_hasCorrectIncorrectType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupNetworkType",
      "message0": 'groupNetwork %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "groupNetwork %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_groupNetworkType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupStimulusType",
      ],
      "nextStatement": [
        "frinex_groupStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_hasMoreStimulusType",
      "message0": 'hasMoreStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "stimulusAction %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_stimulusActionType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMoreStimulusType",
      ],
      "nextStatement": [
        "frinex_hasMoreStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_beforeStimulusType",
      "message0": 'beforeStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_eachStimulusType",
      ],
      "nextStatement": [
        "frinex_eachStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_eachStimulusType",
      "message0": 'eachStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "stimulusAction %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_stimulusActionType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_eachStimulusType",
      ],
      "nextStatement": [
        "frinex_eachStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_afterStimulusType",
      "message0": 'afterStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_eachStimulusType",
      ],
      "nextStatement": [
        "frinex_eachStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_endOfStimulusType",
      "message0": 'endOfStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMoreStimulusType",
        "frinex_groupStimulusType",
      ],
      "nextStatement": [
        "frinex_hasMoreStimulusType",
        "frinex_groupStimulusType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_existingUserCheckType",
      "message0": 'existingUserCheck %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasUserCount %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasUserCountType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_multipleUsersType",
      "message0": 'multipleUsers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasUserCountType",
      ],
      "nextStatement": [
        "frinex_hasUserCountType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_singleUserType",
      "message0": 'singleUser %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasUserCountType",
      ],
      "nextStatement": [
        "frinex_hasUserCountType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_aboveThresholdType",
      "message0": 'aboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasThresholdType",
      ],
      "nextStatement": [
        "frinex_hasThresholdType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_withinThresholdType",
      "message0": 'withinThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasThresholdType",
      ],
      "nextStatement": [
        "frinex_hasThresholdType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_mediaLoadedType",
      "message0": 'mediaLoaded %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMediaLoadingType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "nextStatement": [
        "frinex_hasMediaLoadingType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_mediaLoadFailedType",
      "message0": 'mediaLoadFailed %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMediaLoadingType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "nextStatement": [
        "frinex_hasMediaLoadingType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_mediaPlaybackStartedType",
      "message0": 'mediaPlaybackStarted %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMediaPlaybackType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "nextStatement": [
        "frinex_hasMediaPlaybackType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_mediaPlaybackCompleteType",
      "message0": 'mediaPlaybackComplete %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasMediaPlaybackType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "nextStatement": [
        "frinex_hasMediaPlaybackType",
        "frinex_hasMediaRecorderPlaybackType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_tableType",
      "message0": 'table %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_rowType",
      "message0": 'row %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_columnType",
      "message0": 'column %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionAppendType",
      "message0": 'regionAppend %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionStyleType",
      "message0": 'regionStyle %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionCodeStyleType",
      "message0": 'regionCodeStyle %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionReplaceType",
      "message0": 'regionReplace %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionClearType",
      "message0": 'regionClear %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_regionDragDropType",
      "message0": 'regionDragDrop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "dragDropType %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_dragDropTypeType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ondragstartType",
      "message0": 'ondragstart %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_dragDropTypeType",
      ],
      "nextStatement": [
        "frinex_dragDropTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ondragoverType",
      "message0": 'ondragover %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_dragDropTypeType",
      ],
      "nextStatement": [
        "frinex_dragDropTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_ondropType",
      "message0": 'ondrop %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_dragDropTypeType",
      ],
      "nextStatement": [
        "frinex_dragDropTypeType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusPresentType",
      "message0": 'stimulusPresent %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusImageType",
      "message0": 'stimulusImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoading %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusCodeImageType",
      "message0": 'stimulusCodeImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoading %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusCodeImageButtonType",
      "message0": 'stimulusCodeImageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaLoadingButton %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaLoadingButtonType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusCodeVideoType",
      "message0": 'stimulusCodeVideo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusVideoType",
      "message0": 'stimulusVideo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusCodeAudioType",
      "message0": 'stimulusCodeAudio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusAudioType",
      "message0": 'stimulusAudio %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_playMediaType",
      "message0": 'playMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_rewindMediaType",
      "message0": 'rewindMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_pauseMediaType",
      "message0": 'pauseMedia %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_logMediaTimeStampType",
      "message0": 'logMediaTimeStamp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stimulusImageCaptureType",
      "message0": 'stimulusImageCapture %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "any %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_VideoPanelType",
      "message0": 'VideoPanel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_AnnotationTimelinePanelType",
      "message0": 'AnnotationTimelinePanel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_audioInputSelectWebType",
      "message0": 'audioInputSelectWeb %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_startAudioRecorderWebType",
      "message0": 'startAudioRecorderWeb %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "hasMediaRecorderPlayback %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaRecorderPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_startAudioRecorderAppType",
      "message0": 'startAudioRecorderApp %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_stopAudioRecorderType",
      "message0": 'stopAudioRecorder %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_startAudioRecorderTagType",
      "message0": 'startAudioRecorderTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_endAudioRecorderTagType",
      "message0": 'endAudioRecorderTag %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_stimulusActionType",
        "frinex_isTimeCriticalType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showHtmlPopupType",
      "message0": 'showHtmlPopup %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "message2": "hasActionButtons %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasActionButtonsType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_helpDialogueType",
      "message0": 'helpDialogue %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_userInfoType",
      "message0": 'userInfo %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_versionDataType",
      "message0": 'versionData %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_showColourReportType",
      "message0": 'showColourReport %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasThreshold %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasThresholdType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupInitialisationErrorType",
      "message0": 'groupInitialisationError %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkType",
      ],
      "nextStatement": [
        "frinex_groupNetworkType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupNetworkConnectingType",
      "message0": 'groupNetworkConnecting %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkType",
      ],
      "nextStatement": [
        "frinex_groupNetworkType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupFindingMembersType",
      "message0": 'groupFindingMembers %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkType",
      ],
      "nextStatement": [
        "frinex_groupNetworkType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupNetworkSynchronisingType",
      "message0": 'groupNetworkSynchronising %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkType",
      ],
      "nextStatement": [
        "frinex_groupNetworkType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupPhaseListenersType",
      "message0": 'groupPhaseListeners %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "groupMemberActivity %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_groupMemberActivityType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkType",
      ],
      "nextStatement": [
        "frinex_groupNetworkType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupMemberActivityType",
      "message0": 'groupMemberActivity %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "groupNetworkAction %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_groupNetworkActionType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupMemberActivityType",
      ],
      "nextStatement": [
        "frinex_groupMemberActivityType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupMemberCodeLabelType",
      "message0": 'groupMemberCodeLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupMemberLabelType",
      "message0": 'groupMemberLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupMessageLabelType",
      "message0": 'groupMessageLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupResponseStimulusImageType",
      "message0": 'groupResponseStimulusImage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasMediaPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasMediaPlaybackType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupResponseFeedbackType",
      "message0": 'groupResponseFeedback %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "responseCorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "responseCorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "responseIncorrect %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "responseIncorrect",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupScoreLabelType",
      "message0": 'groupScoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_groupChannelScoreLabelType",
      "message0": 'groupChannelScoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_scoreLabelType",
      "message0": 'scoreLabel %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_submitGroupEventType",
      "message0": 'submitGroupEvent %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendGroupMessageButtonType",
      "message0": 'sendGroupMessageButton %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": '%1',
      "args1": [
        {
          "type": "field_input",
          "name": "featureText",
          "check": "String"
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendGroupMessageType",
      "message0": 'sendGroupMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendGroupStoredMessageType",
      "message0": 'sendGroupStoredMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_streamGroupCanvasType",
      "message0": 'streamGroupCanvas %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_streamGroupCameraType",
      "message0": 'streamGroupCamera %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_sendGroupTokenMessageType",
      "message0": 'sendGroupTokenMessage %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_groupNetworkActionType",
      ],
      "nextStatement": [
        "frinex_groupNetworkActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_clearCurrentScoreType",
      "message0": 'clearCurrentScore %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_scoreIncrementType",
      "message0": 'scoreIncrement %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
        "frinex_stimulusActionType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_bestScoreAboveThresholdType",
      "message0": 'bestScoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasThreshold %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasThresholdType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_totalScoreAboveThresholdType",
      "message0": 'totalScoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasThreshold %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasThresholdType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_scoreAboveThresholdType",
      "message0": 'scoreAboveThreshold %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "hasThreshold %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_hasThresholdType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_resetStimulusType",
      "message0": 'resetStimulus %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_submitTestResultsType",
      "message0": 'submitTestResults %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_validateMetadataType",
      "message0": 'validateMetadata %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_transmitResultsType",
      "message0": 'transmitResults %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "onSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "onSuccess",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "message2": "onError %1",
      "args2": [
            {
              "type": "input_statement",
              "name": "onError",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "nextStatement": [
        "frinex_noneType",
        "frinex_anyType",
      ],
      "colour": 140,
      },
    {
      "type": "frinex_onErrorType",
      "message0": 'onError %1',
      "args0": [
        {
          "type": "input_dummy",
        }
      ],
      "message1": "any %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_anyType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_hasErrorSuccessType",
        "frinex_hasTrueFalseErrorConditionType",
        "frinex_hasErrorTimerType",
        "frinex_hasErrorTimeCriticalType",
      ],
      "nextStatement": [
        "frinex_hasErrorSuccessType",
        "frinex_hasTrueFalseErrorConditionType",
        "frinex_hasErrorTimerType",
        "frinex_hasErrorTimeCriticalType",
      ],
      "colour": 140,
      },
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
     childData += generator.statementToCode(block, 'Administration');
     childData += generator.statementToCode(block, 'Metadata');
     childData += generator.statementToCode(block, 'Presenters');
     childData += generator.statementToCode(block, 'Stimuli');
    return '<experiment block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</experiment>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_deploymentType'] = function(block, generator) {
    var childData = '';
    return '<deployment block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</deployment>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_validationServiceType'] = function(block, generator) {
    var childData = '';
    return '<validationService block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</validationService>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_validationType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'recordMatchType');
    return '<validation block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</validation>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_administrationType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'adminUserType');
    return '<administration block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</administration>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_adminChartType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'metadataType');
    return '<adminChart block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</adminChart>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_dataTableType'] = function(block, generator) {
    var childData = '';
    return '<dataTable block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</dataTable>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_fieldType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<field block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</field>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_presenterType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<presenter block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</presenter>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_randomGroupingType'] = function(block, generator) {
    var childData = '';
    return '<randomGrouping block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</randomGrouping>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliSelectType'] = function(block, generator) {
    var childData = '';
    return '<stimuliSelect block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimuliSelect>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTextType'] = function(block, generator) {
    var childData = '';
    return '<htmlText block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</htmlText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTokenTextType'] = function(block, generator) {
    var childData = '';
    return '<htmlTokenText block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</htmlTokenText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluateTokenTextType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<evaluateTokenText block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</evaluateTokenText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_logTokenTextType'] = function(block, generator) {
    var childData = '';
    return '<logTokenText block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</logTokenText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_plainTextType'] = function(block, generator) {
    var childData = '';
    return '<plainText block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</plainText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_imageType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<image block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</image>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_menuItemType'] = function(block, generator) {
    var childData = '';
    return '<menuItem block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</menuItem>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_withStimuliType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<withStimuli block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</withStimuli>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupStimuliType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupStimuli block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupStimuli>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_loadStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'hasMoreStimulus');
     childData += generator.statementToCode(block, 'endOfStimulus');
    return '<loadStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</loadStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_withMatchingStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'hasMoreStimulus');
     childData += generator.statementToCode(block, 'endOfStimulus');
    return '<withMatchingStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</withMatchingStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_loadSdCardStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'hasMoreStimulus');
     childData += generator.statementToCode(block, 'endOfStimulus');
    return '<loadSdCardStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</loadSdCardStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_currentStimulusHasTagType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<currentStimulusHasTag block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</currentStimulusHasTag>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponsesType'] = function(block, generator) {
    var childData = '';
    return '<clearStimulusResponses block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</clearStimulusResponses>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_validateStimuliResponsesType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<validateStimuliResponses block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</validateStimuliResponses>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusExistsType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<stimulusExists block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusExists>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimuliReportType'] = function(block, generator) {
    var childData = '';
    return '<showStimuliReport block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showStimuliReport>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendStimuliReportType'] = function(block, generator) {
    var childData = '';
    return '<sendStimuliReport block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendStimuliReport>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_targetButtonType'] = function(block, generator) {
    var childData = '';
    return '<targetButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</targetButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hotKeyInputType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<hotKeyInput block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hotKeyInput>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_actionButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<actionButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</actionButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_actionTokenButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<actionTokenButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</actionTokenButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_disableButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '<disableButtonGroup block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</disableButtonGroup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_enableButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '<enableButtonGroup block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</enableButtonGroup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hideButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '<hideButtonGroup block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hideButtonGroup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_styleButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '<styleButtonGroup block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</styleButtonGroup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '<showButtonGroup block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showButtonGroup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_requestFocusType'] = function(block, generator) {
    var childData = '';
    return '<requestFocus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</requestFocus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgLoadGroupsType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<svgLoadGroups block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgLoadGroups>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupAddType'] = function(block, generator) {
    var childData = '';
    return '<svgGroupAdd block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgGroupAdd>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgSetLabelType'] = function(block, generator) {
    var childData = '';
    return '<svgSetLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgSetLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupShowType'] = function(block, generator) {
    var childData = '';
    return '<svgGroupShow block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgGroupShow>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupActionType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<svgGroupAction block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgGroupAction>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupMatchingType'] = function(block, generator) {
    var childData = '';
    return '<svgGroupMatching block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</svgGroupMatching>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusSliderType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusSlider block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusSlider>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputLabelButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<touchInputLabelButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchInputLabelButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputImageButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<touchInputImageButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchInputImageButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputVideoButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<touchInputVideoButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchInputVideoButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputCaptureType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<touchInputCapture block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchInputCapture>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_captureStartType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<captureStart block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</captureStart>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchEndType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<touchEnd block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchEnd>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputStopType'] = function(block, generator) {
    var childData = '';
    return '<touchInputStop block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</touchInputStop>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ratingButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ratingButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingRadioButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ratingRadioButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ratingRadioButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingCheckboxType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ratingCheckbox block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ratingCheckbox>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusFreeTextType'] = function(block, generator) {
    var childData = '';
    return '<stimulusFreeText block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusFreeText>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusRatingButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusRatingButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingRadioType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusRatingRadio block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusRatingRadio>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingCheckboxType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusRatingCheckbox block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusRatingCheckbox>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasRatingOptionsType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<stimulusHasRatingOptions block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusHasRatingOptions>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponseType'] = function(block, generator) {
    var childData = '';
    return '<clearStimulusResponse block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</clearStimulusResponse>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasResponseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<stimulusHasResponse block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusHasResponse>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_setStimulusCodeResponseType'] = function(block, generator) {
    var childData = '';
    return '<setStimulusCodeResponse block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</setStimulusCodeResponse>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addStimulusCodeResponseValidationType'] = function(block, generator) {
    var childData = '';
    return '<addStimulusCodeResponseValidation block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addStimulusCodeResponseValidation>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingFooterButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ratingFooterButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ratingFooterButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_targetFooterButtonType'] = function(block, generator) {
    var childData = '';
    return '<targetFooterButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</targetFooterButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_actionFooterButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<actionFooterButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</actionFooterButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addPaddingType'] = function(block, generator) {
    var childData = '';
    return '<addPadding block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addPadding>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_localStorageDataType'] = function(block, generator) {
    var childData = '';
    return '<localStorageData block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</localStorageData>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliValidationType'] = function(block, generator) {
    var childData = '';
    return '<stimuliValidation block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimuliValidation>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addKeyboardDebugType'] = function(block, generator) {
    var childData = '';
    return '<addKeyboardDebug block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addKeyboardDebug>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_uploadUsersDataMenuType'] = function(block, generator) {
    var childData = '';
    return '<uploadUsersDataMenu block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</uploadUsersDataMenu>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addDebugWidgetsType'] = function(block, generator) {
    var childData = '';
    return '<addDebugWidgets block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addDebugWidgets>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_allMetadataFieldsType'] = function(block, generator) {
    var childData = '';
    return '<allMetadataFields block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</allMetadataFields>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldType'] = function(block, generator) {
    var childData = '';
    return '<metadataField block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</metadataField>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusMetadataFieldType'] = function(block, generator) {
    var childData = '';
    return '<stimulusMetadataField block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusMetadataField>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldConnectionType'] = function(block, generator) {
    var childData = '';
    return '<metadataFieldConnection block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</metadataFieldConnection>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldVisibilityDependantType'] = function(block, generator) {
    var childData = '';
    return '<metadataFieldVisibilityDependant block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</metadataFieldVisibilityDependant>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldDateTriggeredType'] = function(block, generator) {
    var childData = '';
    return '<metadataFieldDateTriggered block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</metadataFieldDateTriggered>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_saveMetadataButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<saveMetadataButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</saveMetadataButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_createUserButtonType'] = function(block, generator) {
    var childData = '';
    return '<createUserButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</createUserButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_switchUserIdButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<switchUserIdButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</switchUserIdButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_selectUserMenuType'] = function(block, generator) {
    var childData = '';
    return '<selectUserMenu block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</selectUserMenu>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_selectLocaleMenuType'] = function(block, generator) {
    var childData = '';
    return '<selectLocaleMenu block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</selectLocaleMenu>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageButtonType'] = function(block, generator) {
    var childData = '';
    return '<eraseLocalStorageButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</eraseLocalStorageButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseUsersDataButtonType'] = function(block, generator) {
    var childData = '';
    return '<eraseUsersDataButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</eraseUsersDataButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showCurrentMsType'] = function(block, generator) {
    var childData = '';
    return '<showCurrentMs block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showCurrentMs>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseTimersType'] = function(block, generator) {
    var childData = '';
    return '<cancelPauseTimers block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</cancelPauseTimers>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseAllType'] = function(block, generator) {
    var childData = '';
    return '<cancelPauseAll block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</cancelPauseAll>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusProgressType'] = function(block, generator) {
    var childData = '';
    return '<showStimulusProgress block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showStimulusProgress>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_displayCompletionCodeType'] = function(block, generator) {
    var childData = '';
    return '<displayCompletionCode block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</displayCompletionCode>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_generateCompletionCodeType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<generateCompletionCode block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</generateCompletionCode>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendAllDataType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<sendAllData block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendAllData>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendMetadataType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<sendMetadata block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendMetadata>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_redirectToUrlType'] = function(block, generator) {
    var childData = '';
    return '<redirectToUrl block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</redirectToUrl>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageOnWindowClosingType'] = function(block, generator) {
    var childData = '';
    return '<eraseLocalStorageOnWindowClosing block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</eraseLocalStorageOnWindowClosing>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_keepStimulusType'] = function(block, generator) {
    var childData = '';
    return '<keepStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</keepStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_removeMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    return '<removeMatchingStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</removeMatchingStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_centrePageType'] = function(block, generator) {
    var childData = '';
    return '<centrePage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</centrePage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_clearPageType'] = function(block, generator) {
    var childData = '';
    return '<clearPage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</clearPage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_backgroundImageType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<backgroundImage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</backgroundImage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_allMenuItemsType'] = function(block, generator) {
    var childData = '';
    return '<allMenuItems block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</allMenuItems>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusButtonType'] = function(block, generator) {
    var childData = '';
    return '<prevStimulusButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</prevStimulusButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusButtonType'] = function(block, generator) {
    var childData = '';
    return '<nextStimulusButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</nextStimulusButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusType'] = function(block, generator) {
    var childData = '';
    return '<nextStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</nextStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusType'] = function(block, generator) {
    var childData = '';
    return '<prevStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</prevStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_nextMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    return '<nextMatchingStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</nextMatchingStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addKinTypeGuiType'] = function(block, generator) {
    var childData = '';
    return '<addKinTypeGui block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addKinTypeGui>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hasGetParameterType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<hasGetParameter block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hasGetParameter>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMetadataValueType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
    return '<hasMetadataValue block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hasMetadataValue>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataValueType'] = function(block, generator) {
    var childData = '';
    return '<setMetadataValue block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</setMetadataValue>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_matchOnEvalTokensType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'conditionTrue');
     childData += generator.statementToCode(block, 'conditionFalse');
     childData += generator.statementToCode(block, 'onError');
    return '<matchOnEvalTokens block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</matchOnEvalTokens>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_progressIndicatorType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<progressIndicator block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</progressIndicator>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataEvalTokensType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<setMetadataEvalTokens block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</setMetadataEvalTokens>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_activateRandomItemType'] = function(block, generator) {
    var childData = '';
    return '<activateRandomItem block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</activateRandomItem>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoPresenterType'] = function(block, generator) {
    var childData = '';
    return '<gotoPresenter block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</gotoPresenter>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoNextPresenterType'] = function(block, generator) {
    var childData = '';
    return '<gotoNextPresenter block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</gotoNextPresenter>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimeStampType'] = function(block, generator) {
    var childData = '';
    return '<logTimeStamp block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</logTimeStamp>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_zeroStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    return '<zeroStimulusStopwatch block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</zeroStimulusStopwatch>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stopStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    return '<stopStimulusStopwatch block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stopStimulusStopwatch>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hardwareTimeStampType'] = function(block, generator) {
    var childData = '';
    return '<hardwareTimeStamp block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hardwareTimeStamp>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_recorderToneInjectionType'] = function(block, generator) {
    var childData = '';
    return '<recorderToneInjection block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</recorderToneInjection>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_dtmfToneType'] = function(block, generator) {
    var childData = '';
    return '<dtmfTone block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</dtmfTone>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_audioButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<audioButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</audioButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_preloadAllStimuliType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<preloadAllStimuli block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</preloadAllStimuli>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<showStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusGridType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'responseCorrect');
     childData += generator.statementToCode(block, 'responseIncorrect');
    return '<showStimulusGrid block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showStimulusGrid>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_matchingStimulusGridType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'responseCorrect');
     childData += generator.statementToCode(block, 'responseIncorrect');
    return '<matchingStimulusGrid block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</matchingStimulusGrid>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<pause block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</pause>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_doLaterType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<doLater block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</doLater>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_requestNotificationType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<requestNotification block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</requestNotification>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_startTimerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<startTimer block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</startTimer>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_compareTimerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<compareTimer block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</compareTimer>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_clearTimerType'] = function(block, generator) {
    var childData = '';
    return '<clearTimer block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</clearTimer>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimerValueType'] = function(block, generator) {
    var childData = '';
    return '<logTimerValue block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</logTimerValue>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_timerLabelType'] = function(block, generator) {
    var childData = '';
    return '<timerLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</timerLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_randomMsPauseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<randomMsPause block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</randomMsPause>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluatePauseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<evaluatePause block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</evaluatePause>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addTimerTriggerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<addTimerTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addTimerTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_startFrameRateTimerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<startFrameRateTimer block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</startFrameRateTimer>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addFrameTimeTriggerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<addFrameTimeTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addFrameTimeTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addMediaTriggerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<addMediaTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addMediaTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderDtmfTriggerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<addRecorderDtmfTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addRecorderDtmfTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderLevelTriggerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<addRecorderLevelTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</addRecorderLevelTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerDefinitionType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<triggerDefinition block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</triggerDefinition>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_habituationParadigmListenerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<habituationParadigmListener block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</habituationParadigmListener>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerMatchingType'] = function(block, generator) {
    var childData = '';
    return '<triggerMatching block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</triggerMatching>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerRandomType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<triggerRandom block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</triggerRandom>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_resetTriggerType'] = function(block, generator) {
    var childData = '';
    return '<resetTrigger block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</resetTrigger>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_countdownLabelType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<countdownLabel block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</countdownLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPauseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusPause block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusPause>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusLabelType'] = function(block, generator) {
    var childData = '';
    return '<stimulusLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimeType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onTime block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onTime>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimerType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onTimer block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onTimer>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionTrueType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<conditionTrue block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</conditionTrue>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionFalseType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<conditionFalse block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</conditionFalse>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onSuccessType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onSuccess block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onSuccess>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onActivateType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onActivate block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onActivate>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_kinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<kinTypeStringDiagram block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</kinTypeStringDiagram>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_loadKinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<loadKinTypeStringDiagram block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</loadKinTypeStringDiagram>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_editableKinEntitesDiagramType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<editableKinEntitesDiagram block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</editableKinEntitesDiagram>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyUpType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onKeyUp block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onKeyUp>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyDownType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onKeyDown block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onKeyDown>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_responseCorrectType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<responseCorrect block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</responseCorrect>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_responseIncorrectType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<responseIncorrect block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</responseIncorrect>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupNetwork block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupNetwork>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMoreStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<hasMoreStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</hasMoreStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_beforeStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<beforeStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</beforeStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_eachStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<eachStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</eachStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_afterStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<afterStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</afterStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_endOfStimulusType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<endOfStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</endOfStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_existingUserCheckType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<existingUserCheck block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</existingUserCheck>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_multipleUsersType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<multipleUsers block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</multipleUsers>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_singleUserType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<singleUser block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</singleUser>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_aboveThresholdType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<aboveThreshold block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</aboveThreshold>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_withinThresholdType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<withinThreshold block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</withinThreshold>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadedType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<mediaLoaded block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</mediaLoaded>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadFailedType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<mediaLoadFailed block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</mediaLoadFailed>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackStartedType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<mediaPlaybackStarted block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</mediaPlaybackStarted>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackCompleteType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<mediaPlaybackComplete block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</mediaPlaybackComplete>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_tableType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<table block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</table>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_rowType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<row block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</row>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_columnType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<column block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</column>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionAppendType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<regionAppend block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionAppend>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionStyleType'] = function(block, generator) {
    var childData = '';
    return '<regionStyle block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionStyle>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionCodeStyleType'] = function(block, generator) {
    var childData = '';
    return '<regionCodeStyle block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionCodeStyle>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionReplaceType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<regionReplace block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionReplace>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionClearType'] = function(block, generator) {
    var childData = '';
    return '<regionClear block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionClear>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_regionDragDropType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<regionDragDrop block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</regionDragDrop>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragstartType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ondragstart block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ondragstart>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragoverType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ondragover block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ondragover>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_ondropType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<ondrop block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</ondrop>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPresentType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusPresent block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusPresent>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusImage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusImage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusCodeImage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusCodeImage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageButtonType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusCodeImageButton block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusCodeImageButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeVideoType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusCodeVideo block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusCodeVideo>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusVideoType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusVideo block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusVideo>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeAudioType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusCodeAudio block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusCodeAudio>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusAudioType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusAudio block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusAudio>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_playMediaType'] = function(block, generator) {
    var childData = '';
    return '<playMedia block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</playMedia>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_rewindMediaType'] = function(block, generator) {
    var childData = '';
    return '<rewindMedia block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</rewindMedia>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseMediaType'] = function(block, generator) {
    var childData = '';
    return '<pauseMedia block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</pauseMedia>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_logMediaTimeStampType'] = function(block, generator) {
    var childData = '';
    return '<logMediaTimeStamp block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</logMediaTimeStamp>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageCaptureType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<stimulusImageCapture block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stimulusImageCapture>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_VideoPanelType'] = function(block, generator) {
    var childData = '';
    return '<VideoPanel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</VideoPanel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_AnnotationTimelinePanelType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<AnnotationTimelinePanel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</AnnotationTimelinePanel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_audioInputSelectWebType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<audioInputSelectWeb block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</audioInputSelectWeb>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderWebType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<startAudioRecorderWeb block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</startAudioRecorderWeb>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderAppType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<startAudioRecorderApp block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</startAudioRecorderApp>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_stopAudioRecorderType'] = function(block, generator) {
    var childData = '';
    return '<stopAudioRecorder block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</stopAudioRecorder>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    return '<startAudioRecorderTag block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</startAudioRecorderTag>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_endAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    return '<endAudioRecorderTag block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</endAudioRecorderTag>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showHtmlPopupType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<showHtmlPopup block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showHtmlPopup>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_helpDialogueType'] = function(block, generator) {
    var childData = '';
    return '<helpDialogue block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</helpDialogue>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_userInfoType'] = function(block, generator) {
    var childData = '';
    return '<userInfo block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</userInfo>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_versionDataType'] = function(block, generator) {
    var childData = '';
    return '<versionData block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</versionData>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_showColourReportType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<showColourReport block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</showColourReport>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupInitialisationErrorType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupInitialisationError block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupInitialisationError>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkConnectingType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupNetworkConnecting block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupNetworkConnecting>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupFindingMembersType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupFindingMembers block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupFindingMembers>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkSynchronisingType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupNetworkSynchronising block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupNetworkSynchronising>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupPhaseListenersType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupPhaseListeners block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupPhaseListeners>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberActivityType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupMemberActivity block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupMemberActivity>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberCodeLabelType'] = function(block, generator) {
    var childData = '';
    return '<groupMemberCodeLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupMemberCodeLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberLabelType'] = function(block, generator) {
    var childData = '';
    return '<groupMemberLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupMemberLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMessageLabelType'] = function(block, generator) {
    var childData = '';
    return '<groupMessageLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupMessageLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseStimulusImageType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<groupResponseStimulusImage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupResponseStimulusImage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseFeedbackType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'responseCorrect');
     childData += generator.statementToCode(block, 'responseIncorrect');
    return '<groupResponseFeedback block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupResponseFeedback>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupScoreLabelType'] = function(block, generator) {
    var childData = '';
    return '<groupScoreLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupScoreLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_groupChannelScoreLabelType'] = function(block, generator) {
    var childData = '';
    return '<groupChannelScoreLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</groupChannelScoreLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreLabelType'] = function(block, generator) {
    var childData = '';
    return '<scoreLabel block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</scoreLabel>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_submitGroupEventType'] = function(block, generator) {
    var childData = '';
    return '<submitGroupEvent block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</submitGroupEvent>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageButtonType'] = function(block, generator) {
    var childData = '';
    return '<sendGroupMessageButton block_id="' + block.id + '" featureText="' + block.getFieldValue('featureText') +'" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendGroupMessageButton>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageType'] = function(block, generator) {
    var childData = '';
    return '<sendGroupMessage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendGroupMessage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupStoredMessageType'] = function(block, generator) {
    var childData = '';
    return '<sendGroupStoredMessage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendGroupStoredMessage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCanvasType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<streamGroupCanvas block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</streamGroupCanvas>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCameraType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<streamGroupCamera block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</streamGroupCamera>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupTokenMessageType'] = function(block, generator) {
    var childData = '';
    return '<sendGroupTokenMessage block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</sendGroupTokenMessage>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_clearCurrentScoreType'] = function(block, generator) {
    var childData = '';
    return '<clearCurrentScore block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</clearCurrentScore>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreIncrementType'] = function(block, generator) {
    var childData = '';
    return '<scoreIncrement block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</scoreIncrement>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_bestScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<bestScoreAboveThreshold block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</bestScoreAboveThreshold>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_totalScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<totalScoreAboveThreshold block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</totalScoreAboveThreshold>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<scoreAboveThreshold block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</scoreAboveThreshold>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_resetStimulusType'] = function(block, generator) {
    var childData = '';
    return '<resetStimulus block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</resetStimulus>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_submitTestResultsType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<submitTestResults block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</submitTestResults>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_validateMetadataType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<validateMetadata block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</validateMetadata>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_transmitResultsType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'onSuccess');
     childData += generator.statementToCode(block, 'onError');
    return '<transmitResults block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</transmitResults>\n');
  };
    javascript.javascriptGenerator.forBlock['frinex_onErrorType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'DO');
    return '<onError block_id="' + block.id + '" ' + ((childData === '')? '/>\n' : '>\n' + childData + '</onError>\n');
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
        "type": "frinex_uploadUsersDataMenuType"
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
