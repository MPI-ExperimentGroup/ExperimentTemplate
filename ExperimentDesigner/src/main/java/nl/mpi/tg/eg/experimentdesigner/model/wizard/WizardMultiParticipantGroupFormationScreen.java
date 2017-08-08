/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
 * @since Aug 8, 2017 11:41:23 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardMultiParticipantGroupFormationScreen extends AbstractWizardScreen {

    public WizardMultiParticipantGroupFormationScreen() {
        super(WizardScreenEnum.WizardMultiParticipantGroupFormationScreen);
    }

    public WizardMultiParticipantGroupFormationScreen(String screenTitle, String screenText, final String agreementButtonLabel) {
        super(WizardScreenEnum.WizardMultiParticipantGroupFormationScreen, screenTitle, screenTitle, screenTitle);
        this.setNextButton(agreementButtonLabel);
        this.setScreenText(screenText);

    }

    @Override
    public String getScreenBooleanInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getScreenTextInfo(int index) {
        return new String[]{"Agreement Text"}[index];
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Agreement Button Label"}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{}[index];
    }

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.metadata);
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(0)));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.targetButton, storedWizardScreenData.getNextButton()[0]);
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, storedWizardScreenData.getNextWizardScreenData().getScreenTag());
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
