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
const workspace = Blockly.inject('editorDiv', {toolbox: featureTypes});
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

function loadTemplateAction(toolboxButton){
    
}

workspace.addChangeListener(updatePreview);
