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
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
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

    final public void setStartingSet(int scoreThreshold, String targetScreen, String fieldName) {
        this.wizardScreenData.setScreenText(0, targetScreen);
        this.wizardScreenData.setScreenText(1, fieldName);
        this.wizardScreenData.setScreenIntegers(0, scoreThreshold);
    }

    final public void setExitSet(int scoreThreshold, String targetScreen, String fieldName) {
        this.wizardScreenData.setScreenText(2, targetScreen);
        this.wizardScreenData.setScreenText(3, fieldName);
        this.wizardScreenData.setScreenIntegers(1, scoreThreshold);
    }

    final public void setStimuliLabel(String setStimuliLabel) {
        this.wizardScreenData.setScreenText(4, setStimuliLabel);
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
        String loggedValue = (storedWizardScreenData.getScreenText(4) != null) ? storedWizardScreenData.getScreenText(4) : storedWizardScreenData.getScreenTitle();
        if (storedWizardScreenData.getScreenText(0) != null) {
            final String postNameStart = cleanScreenTag(storedWizardScreenData.getScreenText(1));
            wizardScreenData.getMetadataFields().add(new Metadata(postNameStart, postNameStart, ".*", ".", false, null));

            final PresenterFeature hasMetadataValue = storedWizardScreenData.getPresenterScreen().addFeature(FeatureType.hasMetadataValue, null, postNameStart, ".*");
            final PresenterFeature hasMetadataValueTrue = hasMetadataValue.addFeature(FeatureType.conditionTrue, null);
            final PresenterFeature hasMetadataValueFalse = hasMetadataValue.addFeature(FeatureType.conditionFalse, null);

            final PresenterFeature minimumScoreThreshold = hasMetadataValueFalse.addFeature(FeatureType.scoreAboveThreshold, null, Integer.toString(storedWizardScreenData.getScreenInteger(0)), null, null, null, null);
            final PresenterFeature minimumScoreAboveThreshold = minimumScoreThreshold.addFeature(FeatureType.aboveThreshold, null);
            minimumScoreAboveThreshold.addFeature(FeatureType.setMetadataValue, null, postNameStart, loggedValue);
            minimumScoreAboveThreshold.addFeature(FeatureType.clearCurrentScore, null);
            minimumScoreAboveThreshold.addFeature(FeatureType.gotoNextPresenter, null);
            final PresenterFeature minimumWithinThreshold = minimumScoreThreshold.addFeature(FeatureType.withinThreshold, null);
            minimumWithinThreshold.addFeature(FeatureType.clearCurrentScore, null);
            minimumWithinThreshold.addFeature(FeatureType.gotoPresenter, null, cleanScreenTag(storedWizardScreenData.getScreenText(0)));
            if (storedWizardScreenData.getScreenText(3) != null) {
                final String postNameExit = cleanScreenTag(storedWizardScreenData.getScreenText(3));
                wizardScreenData.getMetadataFields().add(new Metadata(postNameExit, postNameExit, ".*", ".", false, null));

                final PresenterFeature maximumErrorThreshold = hasMetadataValueTrue.addFeature(FeatureType.scoreAboveThreshold, null, null, Integer.toString(storedWizardScreenData.getScreenInteger(1)), null, null, null);
                maximumErrorThreshold.addFeature(FeatureType.withinThreshold, null).addFeatures(FeatureType.clearCurrentScore, FeatureType.gotoNextPresenter);
                final PresenterFeature maximumErrorAboveThreshold = maximumErrorThreshold.addFeature(FeatureType.aboveThreshold, null);
                maximumErrorAboveThreshold.addFeature(FeatureType.setMetadataValue, null, postNameExit, loggedValue);
                maximumErrorAboveThreshold.addFeature(FeatureType.clearCurrentScore, null);
                maximumErrorAboveThreshold.addFeature(FeatureType.gotoPresenter, null, cleanScreenTag(storedWizardScreenData.getScreenText(2)));
            }
        }
        for (Metadata metadataField : wizardScreenData.getMetadataFields()) {
            if (!experiment.getMetadata().contains(metadataField)) {
                experiment.getMetadata().add(metadataField);
            }
        }
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return new PresenterScreen[]{storedWizardScreenData.getPresenterScreen()};
    }
}
