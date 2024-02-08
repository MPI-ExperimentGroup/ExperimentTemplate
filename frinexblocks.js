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
                "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasTrueFalseErrorCondition %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasCorrectIncorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasCorrectIncorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasMediaRecorderPlayback %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasActionButtons %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasCorrectIncorrect %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
      "message1": "hasErrorSuccess %1",
      "args1": [
            {
              "type": "input_statement",
              "name": "DO",
              "check": [
                "frinex_featureType",
              ]
          }
        ],
      "previousStatement": [
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
        "frinex_featureType",
      ],
      "nextStatement": [
        "frinex_featureType",
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
    return '"frinex_experimentType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_deploymentType'] = function(block, generator) {
    var childData = '';
    return '"frinex_deploymentType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validationServiceType'] = function(block, generator) {
    var childData = '';
    return '"frinex_validationServiceType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validationType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'recordMatchType');
    return '"frinex_validationType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_administrationType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'adminUserType');
    return '"frinex_administrationType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_adminChartType'] = function(block, generator) {
    var childData = '';
     childData += generator.statementToCode(block, 'metadataType');
    return '"frinex_adminChartType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_dataTableType'] = function(block, generator) {
    var childData = '';
    return '"frinex_dataTableType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_fieldType'] = function(block, generator) {
    var childData = '';
    return '"frinex_fieldType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_presenterType'] = function(block, generator) {
    var childData = '';
    return '"frinex_presenterType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_randomGroupingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_randomGroupingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliSelectType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimuliSelectType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_htmlTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_htmlTokenTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_htmlTokenTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluateTokenTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_evaluateTokenTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTokenTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_logTokenTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_plainTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_plainTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_imageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_imageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_menuItemType'] = function(block, generator) {
    var childData = '';
    return '"frinex_menuItemType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withStimuliType'] = function(block, generator) {
    var childData = '';
    return '"frinex_withStimuliType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupStimuliType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupStimuliType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_loadStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_withMatchingStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadSdCardStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_loadSdCardStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_currentStimulusHasTagType'] = function(block, generator) {
    var childData = '';
    return '"frinex_currentStimulusHasTagType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponsesType'] = function(block, generator) {
    var childData = '';
    return '"frinex_clearStimulusResponsesType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validateStimuliResponsesType'] = function(block, generator) {
    var childData = '';
    return '"frinex_validateStimuliResponsesType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusExistsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusExistsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimuliReportType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showStimuliReportType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendStimuliReportType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendStimuliReportType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_targetButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_targetButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hotKeyInputType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hotKeyInputType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_actionButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionTokenButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_actionTokenButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_disableButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_disableButtonGroupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_enableButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_enableButtonGroupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hideButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hideButtonGroupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_styleButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_styleButtonGroupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showButtonGroupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showButtonGroupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_requestFocusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_requestFocusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgLoadGroupsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgLoadGroupsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupAddType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgGroupAddType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgSetLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgSetLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupShowType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgGroupShowType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupActionType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgGroupActionType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_svgGroupMatchingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_svgGroupMatchingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusSliderType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusSliderType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputLabelButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchInputLabelButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputImageButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchInputImageButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputVideoButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchInputVideoButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputCaptureType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchInputCaptureType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_captureStartType'] = function(block, generator) {
    var childData = '';
    return '"frinex_captureStartType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchEndType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchEndType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_touchInputStopType'] = function(block, generator) {
    var childData = '';
    return '"frinex_touchInputStopType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ratingButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingRadioButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ratingRadioButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingCheckboxType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ratingCheckboxType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusFreeTextType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusFreeTextType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusRatingButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingRadioType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusRatingRadioType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusRatingCheckboxType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusRatingCheckboxType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasRatingOptionsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusHasRatingOptionsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearStimulusResponseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_clearStimulusResponseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusHasResponseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusHasResponseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setStimulusCodeResponseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_setStimulusCodeResponseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addStimulusCodeResponseValidationType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addStimulusCodeResponseValidationType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ratingFooterButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ratingFooterButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_targetFooterButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_targetFooterButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_actionFooterButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_actionFooterButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addPaddingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addPaddingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_localStorageDataType'] = function(block, generator) {
    var childData = '';
    return '"frinex_localStorageDataType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimuliValidationType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimuliValidationType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addKeyboardDebugType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addKeyboardDebugType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addDebugWidgetsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addDebugWidgetsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_allMetadataFieldsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_allMetadataFieldsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldType'] = function(block, generator) {
    var childData = '';
    return '"frinex_metadataFieldType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusMetadataFieldType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusMetadataFieldType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldConnectionType'] = function(block, generator) {
    var childData = '';
    return '"frinex_metadataFieldConnectionType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldVisibilityDependantType'] = function(block, generator) {
    var childData = '';
    return '"frinex_metadataFieldVisibilityDependantType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_metadataFieldDateTriggeredType'] = function(block, generator) {
    var childData = '';
    return '"frinex_metadataFieldDateTriggeredType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_saveMetadataButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_saveMetadataButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_createUserButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_createUserButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_switchUserIdButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_switchUserIdButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_selectUserMenuType'] = function(block, generator) {
    var childData = '';
    return '"frinex_selectUserMenuType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_selectLocaleMenuType'] = function(block, generator) {
    var childData = '';
    return '"frinex_selectLocaleMenuType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_eraseLocalStorageButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseUsersDataButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_eraseUsersDataButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showCurrentMsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showCurrentMsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseTimersType'] = function(block, generator) {
    var childData = '';
    return '"frinex_cancelPauseTimersType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_cancelPauseAllType'] = function(block, generator) {
    var childData = '';
    return '"frinex_cancelPauseAllType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusProgressType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showStimulusProgressType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_displayCompletionCodeType'] = function(block, generator) {
    var childData = '';
    return '"frinex_displayCompletionCodeType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_generateCompletionCodeType'] = function(block, generator) {
    var childData = '';
    return '"frinex_generateCompletionCodeType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendAllDataType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendAllDataType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendMetadataType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendMetadataType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_redirectToUrlType'] = function(block, generator) {
    var childData = '';
    return '"frinex_redirectToUrlType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eraseLocalStorageOnWindowClosingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_eraseLocalStorageOnWindowClosingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_keepStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_keepStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_removeMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_removeMatchingStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_centrePageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_centrePageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearPageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_clearPageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_backgroundImageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_backgroundImageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_allMenuItemsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_allMenuItemsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_prevStimulusButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_nextStimulusButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_nextStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_prevStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_prevStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_nextMatchingStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_nextMatchingStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addKinTypeGuiType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addKinTypeGuiType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasGetParameterType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hasGetParameterType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMetadataValueType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hasMetadataValueType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataValueType'] = function(block, generator) {
    var childData = '';
    return '"frinex_setMetadataValueType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_matchOnEvalTokensType'] = function(block, generator) {
    var childData = '';
    return '"frinex_matchOnEvalTokensType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_progressIndicatorType'] = function(block, generator) {
    var childData = '';
    return '"frinex_progressIndicatorType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_setMetadataEvalTokensType'] = function(block, generator) {
    var childData = '';
    return '"frinex_setMetadataEvalTokensType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_activateRandomItemType'] = function(block, generator) {
    var childData = '';
    return '"frinex_activateRandomItemType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoPresenterType'] = function(block, generator) {
    var childData = '';
    return '"frinex_gotoPresenterType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_gotoNextPresenterType'] = function(block, generator) {
    var childData = '';
    return '"frinex_gotoNextPresenterType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimeStampType'] = function(block, generator) {
    var childData = '';
    return '"frinex_logTimeStampType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_zeroStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    return '"frinex_zeroStimulusStopwatchType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stopStimulusStopwatchType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stopStimulusStopwatchType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hardwareTimeStampType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hardwareTimeStampType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_recorderToneInjectionType'] = function(block, generator) {
    var childData = '';
    return '"frinex_recorderToneInjectionType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_dtmfToneType'] = function(block, generator) {
    var childData = '';
    return '"frinex_dtmfToneType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_audioButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_audioButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_preloadAllStimuliType'] = function(block, generator) {
    var childData = '';
    return '"frinex_preloadAllStimuliType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showStimulusGridType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showStimulusGridType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_matchingStimulusGridType'] = function(block, generator) {
    var childData = '';
    return '"frinex_matchingStimulusGridType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_pauseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_doLaterType'] = function(block, generator) {
    var childData = '';
    return '"frinex_doLaterType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_requestNotificationType'] = function(block, generator) {
    var childData = '';
    return '"frinex_requestNotificationType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startTimerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_startTimerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_compareTimerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_compareTimerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearTimerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_clearTimerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logTimerValueType'] = function(block, generator) {
    var childData = '';
    return '"frinex_logTimerValueType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_timerLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_timerLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_randomMsPauseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_randomMsPauseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_evaluatePauseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_evaluatePauseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addTimerTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addTimerTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startFrameRateTimerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_startFrameRateTimerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addFrameTimeTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addFrameTimeTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addMediaTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addMediaTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderDtmfTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addRecorderDtmfTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_addRecorderLevelTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_addRecorderLevelTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerDefinitionType'] = function(block, generator) {
    var childData = '';
    return '"frinex_triggerDefinitionType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_habituationParadigmListenerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_habituationParadigmListenerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerMatchingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_triggerMatchingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_triggerRandomType'] = function(block, generator) {
    var childData = '';
    return '"frinex_triggerRandomType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_resetTriggerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_resetTriggerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_countdownLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_countdownLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPauseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusPauseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimeType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onTimeType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onTimerType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onTimerType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionTrueType'] = function(block, generator) {
    var childData = '';
    return '"frinex_conditionTrueType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_conditionFalseType'] = function(block, generator) {
    var childData = '';
    return '"frinex_conditionFalseType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onSuccessType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onSuccessType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onActivateType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onActivateType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_kinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
    return '"frinex_kinTypeStringDiagramType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_loadKinTypeStringDiagramType'] = function(block, generator) {
    var childData = '';
    return '"frinex_loadKinTypeStringDiagramType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_editableKinEntitesDiagramType'] = function(block, generator) {
    var childData = '';
    return '"frinex_editableKinEntitesDiagramType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyUpType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onKeyUpType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onKeyDownType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onKeyDownType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_responseCorrectType'] = function(block, generator) {
    var childData = '';
    return '"frinex_responseCorrectType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_responseIncorrectType'] = function(block, generator) {
    var childData = '';
    return '"frinex_responseIncorrectType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupNetworkType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_hasMoreStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_hasMoreStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_beforeStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_beforeStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_eachStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_eachStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_afterStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_afterStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_endOfStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_endOfStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_existingUserCheckType'] = function(block, generator) {
    var childData = '';
    return '"frinex_existingUserCheckType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_multipleUsersType'] = function(block, generator) {
    var childData = '';
    return '"frinex_multipleUsersType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_singleUserType'] = function(block, generator) {
    var childData = '';
    return '"frinex_singleUserType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_aboveThresholdType'] = function(block, generator) {
    var childData = '';
    return '"frinex_aboveThresholdType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_withinThresholdType'] = function(block, generator) {
    var childData = '';
    return '"frinex_withinThresholdType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadedType'] = function(block, generator) {
    var childData = '';
    return '"frinex_mediaLoadedType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaLoadFailedType'] = function(block, generator) {
    var childData = '';
    return '"frinex_mediaLoadFailedType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackStartedType'] = function(block, generator) {
    var childData = '';
    return '"frinex_mediaPlaybackStartedType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_mediaPlaybackCompleteType'] = function(block, generator) {
    var childData = '';
    return '"frinex_mediaPlaybackCompleteType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_tableType'] = function(block, generator) {
    var childData = '';
    return '"frinex_tableType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_rowType'] = function(block, generator) {
    var childData = '';
    return '"frinex_rowType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_columnType'] = function(block, generator) {
    var childData = '';
    return '"frinex_columnType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionAppendType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionAppendType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionStyleType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionStyleType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionCodeStyleType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionCodeStyleType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionReplaceType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionReplaceType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionClearType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionClearType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_regionDragDropType'] = function(block, generator) {
    var childData = '';
    return '"frinex_regionDragDropType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragstartType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ondragstartType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondragoverType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ondragoverType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_ondropType'] = function(block, generator) {
    var childData = '';
    return '"frinex_ondropType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusPresentType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusPresentType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusImageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusCodeImageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeImageButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusCodeImageButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeVideoType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusCodeVideoType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusVideoType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusVideoType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusCodeAudioType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusCodeAudioType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusAudioType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusAudioType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_playMediaType'] = function(block, generator) {
    var childData = '';
    return '"frinex_playMediaType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_rewindMediaType'] = function(block, generator) {
    var childData = '';
    return '"frinex_rewindMediaType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_pauseMediaType'] = function(block, generator) {
    var childData = '';
    return '"frinex_pauseMediaType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_logMediaTimeStampType'] = function(block, generator) {
    var childData = '';
    return '"frinex_logMediaTimeStampType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stimulusImageCaptureType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stimulusImageCaptureType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_VideoPanelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_VideoPanelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_AnnotationTimelinePanelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_AnnotationTimelinePanelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_audioInputSelectWebType'] = function(block, generator) {
    var childData = '';
    return '"frinex_audioInputSelectWebType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderWebType'] = function(block, generator) {
    var childData = '';
    return '"frinex_startAudioRecorderWebType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderAppType'] = function(block, generator) {
    var childData = '';
    return '"frinex_startAudioRecorderAppType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_stopAudioRecorderType'] = function(block, generator) {
    var childData = '';
    return '"frinex_stopAudioRecorderType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_startAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    return '"frinex_startAudioRecorderTagType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_endAudioRecorderTagType'] = function(block, generator) {
    var childData = '';
    return '"frinex_endAudioRecorderTagType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showHtmlPopupType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showHtmlPopupType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_helpDialogueType'] = function(block, generator) {
    var childData = '';
    return '"frinex_helpDialogueType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_userInfoType'] = function(block, generator) {
    var childData = '';
    return '"frinex_userInfoType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_versionDataType'] = function(block, generator) {
    var childData = '';
    return '"frinex_versionDataType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_showColourReportType'] = function(block, generator) {
    var childData = '';
    return '"frinex_showColourReportType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupInitialisationErrorType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupInitialisationErrorType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkConnectingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupNetworkConnectingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupFindingMembersType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupFindingMembersType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupNetworkSynchronisingType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupNetworkSynchronisingType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupPhaseListenersType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupPhaseListenersType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberActivityType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupMemberActivityType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberCodeLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupMemberCodeLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMemberLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupMemberLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupMessageLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupMessageLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseStimulusImageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupResponseStimulusImageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupResponseFeedbackType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupResponseFeedbackType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupScoreLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupScoreLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_groupChannelScoreLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_groupChannelScoreLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreLabelType'] = function(block, generator) {
    var childData = '';
    return '"frinex_scoreLabelType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_submitGroupEventType'] = function(block, generator) {
    var childData = '';
    return '"frinex_submitGroupEventType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageButtonType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendGroupMessageButtonType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupMessageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendGroupMessageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupStoredMessageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendGroupStoredMessageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCanvasType'] = function(block, generator) {
    var childData = '';
    return '"frinex_streamGroupCanvasType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_streamGroupCameraType'] = function(block, generator) {
    var childData = '';
    return '"frinex_streamGroupCameraType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_sendGroupTokenMessageType'] = function(block, generator) {
    var childData = '';
    return '"frinex_sendGroupTokenMessageType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_clearCurrentScoreType'] = function(block, generator) {
    var childData = '';
    return '"frinex_clearCurrentScoreType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreIncrementType'] = function(block, generator) {
    var childData = '';
    return '"frinex_scoreIncrementType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_bestScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    return '"frinex_bestScoreAboveThresholdType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_totalScoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    return '"frinex_totalScoreAboveThresholdType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_scoreAboveThresholdType'] = function(block, generator) {
    var childData = '';
    return '"frinex_scoreAboveThresholdType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_resetStimulusType'] = function(block, generator) {
    var childData = '';
    return '"frinex_resetStimulusType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_submitTestResultsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_submitTestResultsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_validateMetadataType'] = function(block, generator) {
    var childData = '';
    return '"frinex_validateMetadataType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_transmitResultsType'] = function(block, generator) {
    var childData = '';
    return '"frinex_transmitResultsType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
  };
    javascript.javascriptGenerator.forBlock['frinex_onErrorType'] = function(block, generator) {
    var childData = '';
    return '"frinex_onErrorType": {\n  "block_id": "' + block.id + '",\n'  + childData + '},\n';
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
