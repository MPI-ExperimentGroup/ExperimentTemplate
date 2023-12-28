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
      "message3": " %1",
      "args3": [
        {
          "type": "input_statement",
          "name": "DO",
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
      "message4": "field %1",
      "args4": [
        {
          "type": "input_statement",
          "name": "DO",
          "check": [
            "frinex_fieldType",
          ]
        }
      ],
      "message5": "presenter %1",
      "args5": [
        {
          "type": "input_statement",
          "name": "DO",
          "check": [
            "frinex_presenterType",
          ]
        }
      ],
      "message6": "stimulus %1",
      "args6": [
        {
          "type": "input_statement",
          "name": "DO",
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
      "message4": " %1",
      "args4": [
        {
          "type": "input_statement",
          "name": "DO",
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
      "message2": " %1",
      "args2": [
        {
          "type": "input_statement",
          "name": "DO",
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
      "message3": "metadata %1",
      "args3": [
        {
          "type": "input_statement",
          "name": "DO",
          "check": [
            "frinex_metadataType",
          ]
        }
      ],
      "message4": " %1",
      "args4": [
        {
          "type": "input_statement",
          "name": "DO",
          "check": [
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
    ]
  };
javascript.javascriptGenerator.forBlock['frinex_experimentType'] = function(block) {
    return 'frinex_experimentType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_deploymentType'] = function(block) {
    return 'frinex_deploymentType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_validationServiceType'] = function(block) {
    return 'frinex_validationServiceType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_validationType'] = function(block) {
    return 'frinex_validationType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_administrationType'] = function(block) {
    return 'frinex_administrationType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_adminChartType'] = function(block) {
    return 'frinex_adminChartType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_dataTableType'] = function(block) {
    return 'frinex_dataTableType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_fieldType'] = function(block) {
    return 'frinex_fieldType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_presenterType'] = function(block) {
    return 'frinex_presenterType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_stimulusType'] = function(block) {
    return 'frinex_stimulusType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_randomGroupingType'] = function(block) {
    return 'frinex_randomGroupingType(\'block_id_' + block.id + '\');\n';
  };javascript.javascriptGenerator.forBlock['frinex_stimuliSelectType'] = function(block) {
    return 'frinex_stimuliSelectType(\'block_id_' + block.id + '\');\n';
  };}
