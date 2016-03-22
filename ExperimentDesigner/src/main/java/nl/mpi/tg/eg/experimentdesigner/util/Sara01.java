/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner.util;

import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;

/**
 * @since Mar 15, 2016 4:50:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Sara01 {

    private final WizardController wizardController = new WizardController();

    public Experiment getExperiment() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("Sara01");
        //Here is the updated experiment flow and the suggested directory structure:
        //Directory/introimage
        //Directory/testwav
        //Directory/practice/wav
        //Directory/practice/groupA
        //Directory/practice/groupB
        //Directory/experiment/wav
        //Directory/experiment/groupA
        //Directory/experiment/groupB
        //E_001_P_A
        //T_001_N_B
        //I have added the 1/2 second pause in my list below:
        //User follows link with group id and invite id
        wizardData.setUserSelectScreen(true);
        //Information screen 
        wizardData.setAgreementScreenText("agreementScreenText");
        //Agreement
        wizardData.setAgreementScreen(true);
        wizardData.setAgreementText("agreementText");
        wizardData.setDisagreementScreenText("disagreementScreenText");
        //metadata
        wizardData.setMetadataScreen(true);
        wizardData.setAgeField(true);
        wizardData.setFirstNameField(true);
        wizardData.setLastNameField(true);
        wizardData.setGenderField(true);
        //audio test page
        wizardData.setAudioTestScreen(true);
        wizardData.setTestAudioPath("testAudioPath");
        //practice (5 items):
        //                image
        //                next button
        //                Clear screen
        //                pause 1/2 second
        //                audio
        //                end of audio 1-7 rating buttons
        //                next stimuli
        wizardData.setPracticeScreen(true);
        wizardData.setPracticeStimuliPath("practiceStimuliPath");
        //experiment round (120 items):
        //                …
        wizardData.setStimuliScreen(true);
        wizardData.setStimuliPath("stimuliPath");
        wizardData.setStimuliSet(new String[]{"a:some text", "b:some more text"});

        //data upload verification 
        wizardData.setCompletionScreen(true);
        //completion code and thank you screen
        wizardData.setCompletionText("completionText");
        wizardData.setMenuScreen(true);
        return wizardController.getExperiment(wizardData);
    }
}
