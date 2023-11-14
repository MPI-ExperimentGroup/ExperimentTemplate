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
            { "type": "input_statement", "name": "preventWindowClose", "check": "frinex_preventWindowCloseTypeType" }
        ],
"message2": "deployment %1",
        "args2": [
            { "type": "input_statement", "name": "deployment", "check": "frinex_deploymentTypeType" }
        ],
"message3": "validationService %1",
        "args3": [
            { "type": "input_statement", "name": "validationService", "check": "frinex_validationServiceTypeType" }
        ],
"message4": "administration %1",
        "args4": [
            { "type": "input_statement", "name": "administration", "check": "frinex_administrationTypeType" }
        ],
"message5": "scss %1",
        "args5": [
            { "type": "input_statement", "name": "scss", "check": "frinex_scssTypeType" }
        ],
"message6": "metadata %1",
        "args6": [
            { "type": "input_statement", "name": "metadata", "check": "frinex_metadataTypeType" }
        ],
"message7": "presenter %1",
        "args7": [
            { "type": "input_statement", "name": "presenter", "check": "frinex_presenterTypeType" }
        ],
"message8": "stimuli %1",
        "args8": [
            { "type": "input_statement", "name": "stimuli", "check": "frinex_stimuliTypeType" }
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
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "validation", "check": "frinex_validationTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "recordMatch", "check": "frinex_recordMatchTypeType" }
        ],
"message2": "fieldMatch %1",
        "args2": [
            { "type": "input_statement", "name": "fieldMatch", "check": "frinex_fieldMatchTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "adminUser", "check": "frinex_adminUserTypeType" }
        ],
"message2": "dataAgreementField %1",
        "args2": [
            { "type": "input_statement", "name": "dataAgreementField", "check": "frinex_dataAgreementFieldTypeType" }
        ],
"message3": "dataChannel %1",
        "args3": [
            { "type": "input_statement", "name": "dataChannel", "check": "frinex_dataChannelTypeType" }
        ],
"message4": "chart %1",
        "args4": [
            { "type": "input_statement", "name": "chart", "check": "frinex_adminChartTypeType" }
        ],
"message5": "dataTable %1",
        "args5": [
            { "type": "input_statement", "name": "dataTable", "check": "frinex_dataTableTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "metadata", "check": "frinex_metadataTypeType" }
        ],
"message2": "stimulusResponse %1",
        "args2": [
            { "type": "input_statement", "name": "stimulusResponse", "check": "frinex_stimulusResponseTypeType" }
        ],
        "output": "frinex_null",
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
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "field", "check": "frinex_fieldTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "translation", "check": "frinex_translationTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "translation", "check": "frinex_translationTypeType" }
        ],
        "output": "frinex_presenterBaseType",
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
            { "type": "input_statement", "name": "stimulus", "check": "frinex_stimulusTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "translation", "check": "frinex_translationTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "tag", "check": "frinex_tagTypeType" }
        ],
"message2": "list %1",
        "args2": [
            { "type": "input_statement", "name": "list", "check": "frinex_listTypeType" }
        ],
        "output": "frinex_null",
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
            { "type": "input_statement", "name": "tag", "check": "frinex_tagTypeType" }
        ],
        "output": "frinex_null",
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
