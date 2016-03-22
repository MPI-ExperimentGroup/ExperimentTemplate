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
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;

/**
 * @since Mar 16, 2016 2:35:56 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FactOrFiction {

    private final WizardController wizardController = new WizardController();

    String[] attentionStimuli = new String[]{
    };
    String[] transportationStimuli = new String[]{
    };

    String multipleChoiceText = "1= niet zo graag, 7 = heel graag (same as survey 1 and 3)";
    String[] readingBehavior = new String[]{
    };

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment("FactOrFiction", "Fact or Fiction");
        final WizardData wizardData = new WizardData();
        wizardData.setAgeField(true);
        wizardData.setGenderField(true);
        wizardData.setCustomTextField("level of proficiency in Dutch");
        final PresenterScreen agreementScreen = wizardController.addAgreementScreen(experiment, null, "EditUser", 1, "Information about study & agreeing to participate");
        final PresenterScreen randomTextScreen = wizardController.addRandomTextScreen(experiment, null, agreementScreen.getSelfPresenterTag(), 2, "GroupAorB", new String[]{"IntroductionA:Will get some text", "IntroductionB:Will also get some text"});
        final PresenterScreen editUserScreen = wizardController.addEditUserScreen(experiment, agreementScreen, randomTextScreen, 2, wizardData);
        randomTextScreen.setBackPresenter(editUserScreen);
        return experiment;
    }
}
