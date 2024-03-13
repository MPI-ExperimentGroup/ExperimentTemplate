/* 
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */


/* 
 Created on : 29 Februrary 2024, 15:59 PM
 Author     : Peter Withers <peter.withers@mpi.nl>
 */

const featureTypes = getFeatureBlocks();
const workspace = Blockly.inject('editorDiv', { toolbox: featureTypes });
setupTemplateCallback();

const supportedEvents = new Set([
    Blockly.Events.BLOCK_CHANGE,
    Blockly.Events.BLOCK_CREATE,
    Blockly.Events.BLOCK_DELETE,
    Blockly.Events.BLOCK_MOVE,
]);

function updatePreview(event) {
    if (workspace.isDragging())
        return;
    if (!supportedEvents.has(event.type))
        return;

    const code = javascript.javascriptGenerator.workspaceToCode(workspace);
    document.getElementById('previewArea').value = code;
    // preview the blocks data in the ExperimentTemplate via the WizardStimulusPresenter
    document.querySelector("iframe").contentWindow.wizardStimulusPresenter(code, event.blockId);
    document.querySelector("iframe").contentWindow.document.body.focus();
}

function loadTemplateAction(toolboxButton) {
    loadAction("template", toolboxButton.text);
}

function loadExampleCallback(toolboxButton) {
    loadAction("example", toolboxButton.text);
}

function loadMySnippetsCallback(toolboxButton) {
    loadAction("snippet", toolboxButton.text);
}

function loadAction(actionType, actionName) {
    // var parentBlock = workspace.newBlock('frinex_actionButtonType');
    // parentBlock.initSvg();
    // parentBlock.render();

    // var childBlock = workspace.newBlock('frinex_actionTokenButtonType');
    // childBlock.setFieldValue('TODO: parse the XML ' + actionType + ': ' + actionName, 'featureText');
    // childBlock.initSvg();
    // childBlock.render();
    // var parentConnection = parentBlock.getInput('DO').connection;
    // var childConnection = childBlock.previousConnection;
    // parentConnection.connect(childConnection);


    $.ajax({
        type: "get",
        url: "/" + actionType + "/" + actionName,
        dataType: "xml",
        success: function (data) {
            for (let childIndex = 0; childIndex < $(data).children().length; childIndex++) {
                buildFromXml($(data).children()[childIndex], null);
            }
            // var successBlock = workspace.newBlock('frinex_htmlTextType');
            // successBlock.setFieldValue(data, 'featureText');
            // successBlock.initSvg();
            // successBlock.render();
        },
        error: function (xhr, status) {
            let errorBlock = workspace.newBlock('frinex_htmlTextType');
            errorBlock.setFieldValue(status, 'featureText');
            errorBlock.initSvg();
            errorBlock.render();
        }
    });
}

function buildFromXml(currentElement, parentBlock) {
    try {
        let childBlock = workspace.newBlock('frinex_' + currentElement.tagName + 'Type');
        for (attributeIndex = 0; attributeIndex < currentElement.attributes.length; attributeIndex++) {
            try {
                childBlock.setFieldValue(currentElement.attributes[attributeIndex].value, currentElement.attributes[attributeIndex].name);
            } catch (exception) {
                // TODO: test if the block field exists first
                console.error(exception);
            }
        }
        childBlock.initSvg();
        childBlock.render();
        if (parentBlock != null) {
            let parentConnection = parentBlock.getInput('DO').connection;
            let childConnection = childBlock.previousConnection;
            parentConnection.connect(childConnection);
        }
    } catch (exception) {
        // TODO: test if the block type exists first
        console.error(exception);
    }
    for (let childIndex = 0; childIndex < $(currentElement).children().length; childIndex++) {
        // TODO: we probably should be passing the relevant connection not the block
        buildFromXml($(currentElement).children()[childIndex], childBlock);
    }
}

workspace.addChangeListener(updatePreview);
