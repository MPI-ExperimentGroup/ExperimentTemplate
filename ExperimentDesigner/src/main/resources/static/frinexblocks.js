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
"message1": "preventWindowClose %1",
        "args1": [
            { "type": "input_value", "name": "preventWindowClose", "check": "frinex_preventWindowCloseType" }
        ],
"message2": "deployment %1",
        "args2": [
            { "type": "input_value", "name": "deployment", "check": "frinex_deploymentType" }
        ],
"message3": "validationService %1",
        "args3": [
            { "type": "input_value", "name": "validationService", "check": "frinex_validationServiceType" }
        ],
"message4": "administration %1",
        "args4": [
            { "type": "input_value", "name": "administration", "check": "frinex_administrationType" }
        ],
"message5": "scss %1",
        "args5": [
            { "type": "input_value", "name": "scss", "check": "frinex_scssType" }
        ],
"message6": "metadata %1",
        "args6": [
            { "type": "input_field", "name": "metadata", "check": "frinex_metadataType" }
        ],
"message7": "presenter %1",
        "args7": [
            { "type": "input_statement", "name": "presenter", "check": "frinex_presenterType" }
        ],
"message8": "stimuli %1",
        "args8": [
            { "type": "input_field", "name": "stimuli", "check": "frinex_stimuliType" }
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
"message1": "validation %1",
        "args1": [
            { "type": "input_value", "name": "validation", "check": "frinex_validationType" }
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
"message1": "recordMatch %1",
        "args1": [
            { "type": "input_statement", "name": "recordMatch", "check": "frinex_recordMatchType" }
        ],
"message2": "fieldMatch %1",
        "args2": [
            { "type": "input_statement", "name": "fieldMatch", "check": "frinex_fieldMatchType" }
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
"message1": "adminUser %1",
        "args1": [
            { "type": "input_statement", "name": "adminUser", "check": "frinex_adminUserType" }
        ],
"message2": "dataAgreementField %1",
        "args2": [
            { "type": "input_value", "name": "dataAgreementField", "check": "frinex_dataAgreementFieldType" }
        ],
"message3": "dataChannel %1",
        "args3": [
            { "type": "input_statement", "name": "dataChannel", "check": "frinex_dataChannelType" }
        ],
"message4": "chart %1",
        "args4": [
            { "type": "input_statement", "name": "chart", "check": "frinex_adminChartType" }
        ],
"message5": "dataTable %1",
        "args5": [
            { "type": "input_statement", "name": "dataTable", "check": "frinex_dataTableType" }
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
"message1": "metadata %1",
        "args1": [
            { "type": "input_statement", "name": "metadata", "check": "frinex_metadataType" }
        ],
"message2": "stimulusResponse %1",
        "args2": [
            { "type": "input_statement", "name": "stimulusResponse", "check": "frinex_stimulusResponseType" }
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
"message1": "field %1",
        "args1": [
            { "type": "input_statement", "name": "field", "check": "frinex_fieldType" }
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
"message1": "translation %1",
        "args1": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
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
"message1": "translation %1",
        "args1": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
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
"message1": "stimulus %1",
        "args1": [
            { "type": "input_statement", "name": "stimulus", "check": "frinex_stimulusType" }
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
"message1": "translation %1",
        "args1": [
            { "type": "input_statement", "name": "translation", "check": "frinex_translationType" }
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
"message1": "tag %1",
        "args1": [
            { "type": "input_statement", "name": "tag", "check": "frinex_tagType" }
        ],
"message2": "list %1",
        "args2": [
            { "type": "input_statement", "name": "list", "check": "frinex_listType" }
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
"message1": "tag %1",
        "args1": [
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
