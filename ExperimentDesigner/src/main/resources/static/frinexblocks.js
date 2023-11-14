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
 "message2": 'appNameInternal %1',
 "args2": [
 {
 "type": "field_input",
 "name": "appNameInternal",
 "check": "String"
 }
 ],
 "message3": 'backgroundColour %1',
 "args3": [
 {
 "type": "field_input",
 "name": "backgroundColour",
 "check": "String"
 }
 ],
 "message4": 'complementColour0 %1',
 "args4": [
 {
 "type": "field_input",
 "name": "complementColour0",
 "check": "String"
 }
 ],
 "message5": 'complementColour1 %1',
 "args5": [
 {
 "type": "field_input",
 "name": "complementColour1",
 "check": "String"
 }
 ],
 "message6": 'complementColour2 %1',
 "args6": [
 {
 "type": "field_input",
 "name": "complementColour2",
 "check": "String"
 }
 ],
 "message7": 'complementColour3 %1',
 "args7": [
 {
 "type": "field_input",
 "name": "complementColour3",
 "check": "String"
 }
 ],
 "message8": 'complementColour4 %1',
 "args8": [
 {
 "type": "field_input",
 "name": "complementColour4",
 "check": "String"
 }
 ],
 "message9": 'primaryColour0 %1',
 "args9": [
 {
 "type": "field_input",
 "name": "primaryColour0",
 "check": "String"
 }
 ],
 "message10": 'primaryColour1 %1',
 "args10": [
 {
 "type": "field_input",
 "name": "primaryColour1",
 "check": "String"
 }
 ],
 "message11": 'primaryColour2 %1',
 "args11": [
 {
 "type": "field_input",
 "name": "primaryColour2",
 "check": "String"
 }
 ],
 "message12": 'primaryColour3 %1',
 "args12": [
 {
 "type": "field_input",
 "name": "primaryColour3",
 "check": "String"
 }
 ],
 "message13": 'primaryColour4 %1',
 "args13": [
 {
 "type": "field_input",
 "name": "primaryColour4",
 "check": "String"
 }
 ],
 "message14": 'defaultLocale %1',
 "args14": [
 {
 "type": "field_input",
 "name": "defaultLocale",
 "check": "String"
 }
 ],
 "message15": 'availableLocales %1',
 "args15": [
 {
 "type": "field_input",
 "name": "availableLocales",
 "check": "String"
 }
 ],
 "message16": 'isScalable %1',
 "args16": [
 {
 "type": "field_input",
 "name": "isScalable",
 "check": "String"
 }
 ],
 "message17": 'preserveLastState %1',
 "args17": [
 {
 "type": "field_input",
 "name": "preserveLastState",
 "check": "String"
 }
 ],
 "message18": 'splashPresenter %1',
 "args18": [
 {
 "type": "field_input",
 "name": "splashPresenter",
 "check": "String"
 }
 ],
 "message19": 'userIdGetParam %1',
 "args19": [
 {
 "type": "field_input",
 "name": "userIdGetParam",
 "check": "String"
 }
 ],
 "message20": 'rotatable %1',
 "args20": [
 {
 "type": "field_input",
 "name": "rotatable",
 "check": "String"
 }
 ],
 "message21": 'showMenuBar %1',
 "args21": [
 {
 "type": "field_input",
 "name": "showMenuBar",
 "check": "String"
 }
 ],
 "message22": 'defaultScale %1',
 "args22": [
 {
 "type": "field_input",
 "name": "defaultScale",
 "check": "String"
 }
 ],
 "message23": 'textFontSize %1',
 "args23": [
 {
 "type": "field_input",
 "name": "textFontSize",
 "check": "String"
 }
 ],
 "message24": 'obfuscateBrowserStorage %1',
 "args24": [
 {
 "type": "field_input",
 "name": "obfuscateBrowserStorage",
 "check": "String"
 }
 ],
"message25": " %1",
        "args25": [
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
 "message1": 'publishDate %1',
 "args1": [
 {
 "type": "field_input",
 "name": "publishDate",
 "check": "String"
 }
 ],
 "message2": 'expiryDate %1',
 "args2": [
 {
 "type": "field_input",
 "name": "expiryDate",
 "check": "String"
 }
 ],
 "message3": 'stagingServer %1',
 "args3": [
 {
 "type": "field_input",
 "name": "stagingServer",
 "check": "String"
 }
 ],
 "message4": 'productionServer %1',
 "args4": [
 {
 "type": "field_input",
 "name": "productionServer",
 "check": "String"
 }
 ],
 "message5": 'frinexVersion %1',
 "args5": [
 {
 "type": "field_input",
 "name": "frinexVersion",
 "check": "String"
 }
 ],
 "message6": 'stunServer %1',
 "args6": [
 {
 "type": "field_input",
 "name": "stunServer",
 "check": "String"
 }
 ],
 "message7": 'isWebApp %1',
 "args7": [
 {
 "type": "field_input",
 "name": "isWebApp",
 "check": "String"
 }
 ],
 "message8": 'isDesktop %1',
 "args8": [
 {
 "type": "field_input",
 "name": "isDesktop",
 "check": "String"
 }
 ],
 "message9": 'isiOS %1',
 "args9": [
 {
 "type": "field_input",
 "name": "isiOS",
 "check": "String"
 }
 ],
 "message10": 'isAndroid %1',
 "args10": [
 {
 "type": "field_input",
 "name": "isAndroid",
 "check": "String"
 }
 ],
 "message11": 'isUnity %1',
 "args11": [
 {
 "type": "field_input",
 "name": "isUnity",
 "check": "String"
 }
 ],
 "message12": 'state %1',
 "args12": [
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
 "message1": 'stagingUrl %1',
 "args1": [
 {
 "type": "field_input",
 "name": "stagingUrl",
 "check": "String"
 }
 ],
 "message2": 'productionUrl %1',
 "args2": [
 {
 "type": "field_input",
 "name": "productionUrl",
 "check": "String"
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
 "message1": 'allowDataDeletion %1',
 "args1": [
 {
 "type": "field_input",
 "name": "allowDataDeletion",
 "check": "String"
 }
 ],
"message2": " %1",
        "args2": [
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
"message3": " %1",
        "args3": [
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
 "message4": 'screenName %1',
 "args4": [
 {
 "type": "field_input",
 "name": "screenName",
 "check": "String"
 }
 ],
 "message5": 'responseGroup %1',
 "args5": [
 {
 "type": "field_input",
 "name": "responseGroup",
 "check": "String"
 }
 ],
 "message6": 'scoreGroup %1',
 "args6": [
 {
 "type": "field_input",
 "name": "scoreGroup",
 "check": "String"
 }
 ],
 "message7": 'stimulusId %1',
 "args7": [
 {
 "type": "field_input",
 "name": "stimulusId",
 "check": "String"
 }
 ],
 "message8": 'isCorrect %1',
 "args8": [
 {
 "type": "field_input",
 "name": "isCorrect",
 "check": "String"
 }
 ],
 "message9": 'eventTag %1',
 "args9": [
 {
 "type": "field_input",
 "name": "eventTag",
 "check": "String"
 }
 ],
 "message10": 'tagValue1 %1',
 "args10": [
 {
 "type": "field_input",
 "name": "tagValue1",
 "check": "String"
 }
 ],
 "message11": 'tagValue2 %1',
 "args11": [
 {
 "type": "field_input",
 "name": "tagValue2",
 "check": "String"
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
 "message5": 'duplicatesControlledMessage %1',
 "args5": [
 {
 "type": "field_input",
 "name": "duplicatesControlledMessage",
 "check": "String"
 }
 ],
 "message6": 'preventServerDuplicates %1',
 "args6": [
 {
 "type": "field_input",
 "name": "preventServerDuplicates",
 "check": "String"
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
 "message1": 'self %1',
 "args1": [
 {
 "type": "field_input",
 "name": "self",
 "check": "String"
 }
 ],
 "message2": 'title %1',
 "args2": [
 {
 "type": "field_input",
 "name": "title",
 "check": "String"
 }
 ],
 "message3": 'menuLabel %1',
 "args3": [
 {
 "type": "field_input",
 "name": "menuLabel",
 "check": "String"
 }
 ],
 "message4": 'back %1',
 "args4": [
 {
 "type": "field_input",
 "name": "back",
 "check": "String"
 }
 ],
 "message5": 'next %1',
 "args5": [
 {
 "type": "field_input",
 "name": "next",
 "check": "String"
 }
 ],
 "message6": 'type %1',
 "args6": [
 {
 "type": "field_input",
 "name": "type",
 "check": "String"
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
 "message1": 'identifier %1',
 "args1": [
 {
 "type": "field_input",
 "name": "identifier",
 "check": "String"
 }
 ],
 "message2": 'videoPath %1',
 "args2": [
 {
 "type": "field_input",
 "name": "videoPath",
 "check": "String"
 }
 ],
 "message3": 'imagePath %1',
 "args3": [
 {
 "type": "field_input",
 "name": "imagePath",
 "check": "String"
 }
 ],
 "message4": 'code %1',
 "args4": [
 {
 "type": "field_input",
 "name": "code",
 "check": "String"
 }
 ],
 "message5": 'audioPath %1',
 "args5": [
 {
 "type": "field_input",
 "name": "audioPath",
 "check": "String"
 }
 ],
 "message6": 'label %1',
 "args6": [
 {
 "type": "field_input",
 "name": "label",
 "check": "String"
 }
 ],
 "message7": 'correctResponses %1',
 "args7": [
 {
 "type": "field_input",
 "name": "correctResponses",
 "check": "String"
 }
 ],
 "message8": 'ratingLabels %1',
 "args8": [
 {
 "type": "field_input",
 "name": "ratingLabels",
 "check": "String"
 }
 ],
 "message9": 'tags %1',
 "args9": [
 {
 "type": "field_input",
 "name": "tags",
 "check": "String"
 }
 ],
 "message10": 'pauseMs %1',
 "args10": [
 {
 "type": "field_input",
 "name": "pauseMs",
 "check": "String"
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
 "message1": 'storageField %1',
 "args1": [
 {
 "type": "field_input",
 "name": "storageField",
 "check": "String"
 }
 ],
 "message2": 'consumedTagGroup %1',
 "args2": [
 {
 "type": "field_input",
 "name": "consumedTagGroup",
 "check": "String"
 }
 ],
"message3": " %1",
        "args3": [
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
 "message1": 'idListField %1',
 "args1": [
 {
 "type": "field_input",
 "name": "idListField",
 "check": "String"
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
