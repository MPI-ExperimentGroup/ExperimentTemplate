function getFeatureBlocks() {
    Blockly.defineBlocksWithJsonArray([{
        "type": "frinex_experiment",
        "message0": 'Experiment %1',
        "args0": [
            {
                "type": "input_value",
                "name": "Name",
                "check": "String"
            }
        ],
        "message1": "Metadata %1",
        "args1": [
            { "type": "input_statement", "name": "DO" }
        ],
        "colour": 160
    }]);
    return {
        "kind": "flyoutToolbox",
        "contents": [
            {
                "kind": "block",
                "type": "frinex_experiment"
            },
            {
                "kind": "block",
                "type": "controls_if"
            },
            {
                "kind": "block",
                "type": "controls_repeat_ext"
            },
            {
                "kind": "block",
                "type": "logic_compare"
            },
            {
                "kind": "block",
                "type": "math_number"
            },
            {
                "kind": "block",
                "type": "math_arithmetic"
            },
            {
                "kind": "block",
                "type": "text"
            },
            {
                "kind": "block",
                "type": "text_print"
            },
        ]
    };
}
