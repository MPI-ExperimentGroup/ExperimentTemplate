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
        "args1": [
            { "type": "input_statement", "check": ["frinex_preventWindowCloseType","frinex_deploymentType","frinex_validationServiceType","frinex_administrationType","frinex_scssType","frinex_metadataType","frinex_presenterType","frinex_stimuliType",]}
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
        "args1": [
            { "type": "input_statement", "check": ["frinex_recordMatchType","frinex_fieldMatchType",]}
        ],
        "output": "frinex_validationType",
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
        "args1": [
            { "type": "input_statement", "check": ["frinex_adminUserType","frinex_dataAgreementFieldType","frinex_dataChannelType","frinex_adminChartType","frinex_dataTableType",]}
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
        "args1": [
            { "type": "input_statement", "check": ["frinex_metadataType","frinex_stimulusResponseType",]}
        ],
        "previousStatement": "frinex_adminChartType",
        "nextStatement": "frinex_adminChartType",
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
        "previousStatement": "frinex_dataTableType",
        "nextStatement": "frinex_dataTableType",
        "colour": 160
},
{
        "type": "frinex_metadataType",
 "message0": 'metadata %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "output": "frinex_metadataType",
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
        "previousStatement": "frinex_fieldType",
        "nextStatement": "frinex_fieldType",
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
        "previousStatement": "frinex_presenterType",
        "nextStatement": "frinex_presenterType",
        "colour": 160
},
{
        "type": "frinex_stimuliType",
 "message0": 'stimuli %1',
 "args0": [
 {
 "type": "input_dummy",
 }
 ],
        "output": "frinex_stimuliType",
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
        "previousStatement": "frinex_stimulusType",
        "nextStatement": "frinex_stimulusType",
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
        "args1": [
            { "type": "input_statement", "check": ["frinex_tagType","frinex_listType",]}
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
                "type": "frinex_metadataType"
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
                "type": "frinex_stimuliType"
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
}
