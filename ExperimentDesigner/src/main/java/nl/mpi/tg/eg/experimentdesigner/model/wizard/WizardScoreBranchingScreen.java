/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Aug 23, 2018 6:44:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardScoreBranchingScreen extends AbstractWizardScreen {

    public WizardScoreBranchingScreen(String screenTitle, String menuLabel, String screenTag) {
        super(WizardScreenEnum.WizardScoreBranchingScreen, screenTitle, menuLabel, screenTag);
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        return new String[]{}[index];
    }

    @Override
    public String getScreenTextInfo(int index) {
        return new String[]{}[index];
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{}[index];
    }

    final public void setBranchOnScore(int branchOnGetParam, String targetScreen) {
        this.wizardScreenData.setScreenText(0, targetScreen);
        this.wizardScreenData.setScreenIntegers(0, branchOnGetParam);
    }

    public void addTargetScreen(final AbstractWizardScreen targetScreen) {
        wizardScreenData.getMenuWizardScreenData().add(targetScreen.getWizardScreenData());
    }

    @Override
    public PresenterScreen[] populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return new PresenterScreen[]{storedWizardScreenData.getPresenterScreen()};
    }
}
