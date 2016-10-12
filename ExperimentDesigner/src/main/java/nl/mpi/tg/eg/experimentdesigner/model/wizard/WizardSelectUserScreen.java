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
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since May 6, 2016 3:50:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardSelectUserScreen extends AbstractWizardScreen {

    public WizardSelectUserScreen() {
        super("Select User", "Select User", "SelectUser");
    }

    public WizardSelectUserScreen(String titleString) {
        super(titleString, titleString, "SelectUser");
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        getPresenterScreen().setPresenterType(PresenterType.metadata);
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.selectUserMenu, null);
        getPresenterScreen().getPresenterFeatureList().add(selectUserFeature);
        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }
}
