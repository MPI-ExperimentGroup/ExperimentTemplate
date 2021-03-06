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

    public WizardWelcomeScreen() {
        super(WizardScreenEnum.WizardWelcomeScreen, "Welcome", "Welcome", "Welcome");
    }

    public WizardWelcomeScreen(final String screenTitle, final String screenLabel, final String instructions_button, final String go_directly_to_program, WizardScreen instructionsScreen, WizardScreen programWizardScreen) {
        super(WizardScreenEnum.WizardWelcomeScreen, screenTitle, screenLabel, screenLabel);
        wizardScreenData.setNextButton(new String[]{go_directly_to_program, instructions_button});
        wizardScreenData.getMenuWizardScreenData().add(0, instructionsScreen.getWizardScreenData());
        wizardScreenData.getMenuWizardScreenData().add(1, programWizardScreen.getWizardScreenData());
    }

//    public void setScreens(WizardScreen programWizardScreen, WizardScreen instructionsScreen) {
//        wizardScreenData.getMenuWizardScreenData().add(0, programWizardScreen.getWizardScreenData());
//        wizardScreenData.getMenuWizardScreenData().add(1, instructionsScreen.getWizardScreenData());
//    }
    @Override
    public String getScreenBooleanInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getScreenTextInfo(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Go Directly To Program Button Label", "Instructions Button Label"}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{}[index];
    }

    @Override
    public PresenterScreen[] populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.menu);
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.menuItem, storedWizardScreenData.getNextButton()[1]);
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, storedWizardScreenData.getMenuWizardScreenData().get(1).getScreenTag());
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(presenterFeature);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.menuItem, storedWizardScreenData.getNextButton()[0]);
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, storedWizardScreenData.getMenuWizardScreenData().get(0).getScreenTag());
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(presenterFeature1);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return new PresenterScreen[]{storedWizardScreenData.getPresenterScreen()};
    }
}
