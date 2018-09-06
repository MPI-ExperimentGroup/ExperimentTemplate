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
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Aug 23, 2018 6:44:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardScoreBranchingScreen extends AbstractWizardScreen {

    public WizardScoreBranchingScreen() {
        super(WizardScreenEnum.WizardScoreBranchingScreen);
    }

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

    final public void setBranchOnScoreBelow(int branchOnGetParam, String targetScreen) {
        this.wizardScreenData.setScreenText(0, targetScreen);
        this.wizardScreenData.setScreenIntegers(0, branchOnGetParam);
    }

    final public void setBranchOnScoreAbove(int branchOnGetParam, String targetScreen) {
        this.wizardScreenData.setScreenText(1, targetScreen);
        this.wizardScreenData.setScreenIntegers(1, branchOnGetParam);
    }

    public void addTargetScreen(final AbstractWizardScreen targetScreen) {
        wizardScreenData.getMenuWizardScreenData().add(targetScreen.getWizardScreenData());
    }

    @Override
    public PresenterScreen[] populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        storedWizardScreenData.getPresenterScreen().addFeature(FeatureType.logTokenText, null, "3", "logTokenTextType", "logTokenTextKey", "playerScore:"
                + "<playerScore><br/>"
                + "playerErrors:"
                + "<playerErrors><br/>"
                + "playerPotentialScore:"
                + "<playerPotentialScore><br/>"
                + "playerErrorStreak:"
                + "<playerErrorStreak><br/>"
                + "playerCorrectStreak:"
                + "<playerCorrectStreak><br/>"
                + "playerMaxScore:"
                + "<playerMaxScore><br/>"
                + "playerMaxErrors:"
                + "<playerMaxErrors><br/>"
                + "playerMaxPotentialScore:"
                + "<playerMaxPotentialScore><br/>"
                + "playerMaxErrorStreak:"
                + "<playerMaxErrorStreak><br/>"
                + "playerMaxCorrectStreak:"
                + "<playerMaxCorrectStreak><br/>"
                + "playerTotalScore:"
                + "<playerTotalScore><br/>"
                + "playerTotalErrors:"
                + "<playerTotalErrors><br/>"
                + "playerTotalPotentialScore:"
                + "<playerTotalPotentialScore><br/>"
                + "playerGamesPlayed:"
                + "<playerGamesPlayed>"
        );
        PresenterFeature withinThreshold = null;
        if (storedWizardScreenData.getScreenText(1) != null) {
            final PresenterFeature maxScoreAboveThreshold = storedWizardScreenData.getPresenterScreen().addFeature(FeatureType.bestScoreAboveThreshold, null, Integer.toString(storedWizardScreenData.getScreenInteger(1)), null, null, null, null);
            final PresenterFeature scoreAboveThreshold = maxScoreAboveThreshold.addFeature(FeatureType.aboveThreshold, null).addFeature(FeatureType.scoreAboveThreshold, null, null, Integer.toString(storedWizardScreenData.getScreenInteger(1)), null, null, null);
            final PresenterFeature aboveThreshold = scoreAboveThreshold.addFeature(FeatureType.aboveThreshold, null);
            aboveThreshold.addFeature(FeatureType.clearCurrentScore, null);
            aboveThreshold.addFeature(FeatureType.gotoPresenter, null, cleanScreenTag(storedWizardScreenData.getScreenText(1)));
            scoreAboveThreshold.addFeature(FeatureType.withinThreshold, null).addFeatures(FeatureType.clearCurrentScore, FeatureType.gotoNextPresenter);
            withinThreshold = maxScoreAboveThreshold.addFeature(FeatureType.withinThreshold, null);
        }
        if (storedWizardScreenData.getScreenText(0) != null) {
            final PresenterFeature scoreAboveThreshold = ((withinThreshold == null) ? storedWizardScreenData.getPresenterScreen() : withinThreshold).addFeature(FeatureType.scoreAboveThreshold, null, null, Integer.toString(storedWizardScreenData.getScreenInteger(0)), null, null, null);
            final PresenterFeature aboveThreshold = scoreAboveThreshold.addFeature(FeatureType.aboveThreshold, null);
            aboveThreshold.addFeature(FeatureType.clearCurrentScore, null);
            aboveThreshold.addFeature(FeatureType.gotoPresenter, null, cleanScreenTag(storedWizardScreenData.getScreenText(0)));
            withinThreshold = scoreAboveThreshold.addFeature(FeatureType.withinThreshold, null);
        }
        if (withinThreshold != null) {
            withinThreshold.addFeature(FeatureType.clearCurrentScore, null);
            withinThreshold.addFeature(FeatureType.gotoNextPresenter, null);
        }
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return new PresenterScreen[]{storedWizardScreenData.getPresenterScreen()};
    }
}
