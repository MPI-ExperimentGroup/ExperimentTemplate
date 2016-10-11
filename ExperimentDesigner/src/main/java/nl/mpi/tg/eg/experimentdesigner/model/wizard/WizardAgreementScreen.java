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
 * @since May 6, 2016 3:57:37 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardAgreementScreen extends AbstractWizardScreen {

    public WizardAgreementScreen(String screenTitle, String screenText, final String agreementButtonLabel) {
        super(screenTitle, screenTitle, screenTitle);
        this.setNextButton(agreementButtonLabel);
        this.setScreenText(screenText);

    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        getPresenterScreen().setPresenterType(PresenterType.metadata);
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, getScreenText()));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.targetButton, getNextButton());
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, getNextWizardScreenData().getScreenTag());
        getPresenterScreen().getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }
}
