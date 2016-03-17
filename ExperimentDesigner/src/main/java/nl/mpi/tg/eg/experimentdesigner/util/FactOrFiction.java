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

/**
 * @since Mar 16, 2016 2:35:56 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FactOrFiction {

    private final WizardController wizardController = new WizardController();

    public Experiment getExperiment() {
        final Experiment experiment = wizardController.getExperiment("FactOrFiction", "Fact or Fiction");
        final PresenterScreen editUserScreen = wizardController.addEditUserScreen(experiment, null, null, 2);
        wizardController.addAgreementScreen(experiment, null, editUserScreen, 1, "Information about study & agreeing to participate");
        wizardController.addRandomTextScreen(experiment, null, null, 2, "GroupAorB", new String[]{"IntroductionA:Will get some text", "IntroductionB:Will also get some text"});
        return experiment;
    }
}
