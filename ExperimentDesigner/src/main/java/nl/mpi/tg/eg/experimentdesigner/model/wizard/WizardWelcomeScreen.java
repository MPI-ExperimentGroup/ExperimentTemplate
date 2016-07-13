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
package nl.mpi.tg.eg.experimentdesigner.model.wizard;

import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since May 6, 2016 1:04:17 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardWelcomeScreen extends AbstractWizardScreen {

    String instructions_button;
    String go_directly_to_program;
    private WizardScreen instructionsScreen = null;
    private WizardScreen programWizardScreen = null;

    public WizardWelcomeScreen() {
        super("Welcome", "Welcome", "Welcome");
    }

    public WizardWelcomeScreen(final String screenTitle, final String screenLabel, final String instructions_button, final String go_directly_to_program, WizardScreen instructionsScreen, WizardScreen programWizardScreen) {
        super(screenTitle, screenLabel, screenLabel);
        this.instructions_button = instructions_button;
        this.go_directly_to_program = go_directly_to_program;
        this.programWizardScreen = programWizardScreen;
        this.instructionsScreen = instructionsScreen;
    }

    public String getInstructions_button() {
        return instructions_button;
    }

    public void setInstructions_button(String instructions_button) {
        this.instructions_button = instructions_button;
    }

    public String getGo_directly_to_program() {
        return go_directly_to_program;
    }

    public void setGo_directly_to_program(String go_directly_to_program) {
        this.go_directly_to_program = go_directly_to_program;
    }

    public WizardScreen getInstructionsScreen() {
        return instructionsScreen;
    }

    public void setInstructionsScreen(WizardScreen instructionsScreen) {
        this.instructionsScreen = instructionsScreen;
    }

    public WizardScreen getProgramWizardScreen() {
        return programWizardScreen;
    }

    public void setProgramWizardScreen(WizardScreen programWizardScreen) {
        this.programWizardScreen = programWizardScreen;
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.menu);
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.menuItem, instructions_button);
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, instructionsScreen.getScreenTag());
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.menuItem, go_directly_to_program);
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, programWizardScreen.getScreenTag());
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
