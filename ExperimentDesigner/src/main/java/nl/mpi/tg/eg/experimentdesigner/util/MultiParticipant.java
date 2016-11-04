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
 * @since Oct 21, 2016 11:52:03 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class MultiParticipant {

    private final WizardController wizardController = new WizardController();

    // @todo: server shared variables to be used in animations and interactions concurrently displayed on multiple users devices 
    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("MultiParticipant");
        wizardData.setShowMenuBar(true);
        wizardData.setObfuscateScreenNames(false);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
