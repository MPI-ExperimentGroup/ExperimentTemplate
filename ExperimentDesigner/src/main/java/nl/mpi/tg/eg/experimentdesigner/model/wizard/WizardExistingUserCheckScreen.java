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
 * @since May 12, 2016 5:05:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardExistingUserCheckScreen extends AbstractWizardScreen {

    private String new_Interview;
    private String resume_Interview;
    private String startNewText;
    private String resumeoldText;

    public WizardExistingUserCheckScreen() {
        super("ExistingUserCheck", "ExistingUserCheck", "ExistingUserCheck");
    }

    public WizardExistingUserCheckScreen(final String screenTitle, final String new_Interview, final String resume_Interview, final String startNewText, final String resumeoldText) {
        super(screenTitle, screenTitle, screenTitle);
        this.new_Interview = new_Interview;
        this.resume_Interview = resume_Interview;
        this.startNewText = startNewText;
        this.resumeoldText = resumeoldText;
    }

    public String getNew_Interview() {
        return new_Interview;
    }

    public void setNew_Interview(String new_Interview) {
        this.new_Interview = new_Interview;
    }

    public String getResume_Interview() {
        return resume_Interview;
    }

    public void setResume_Interview(String resume_Interview) {
        this.resume_Interview = resume_Interview;
    }

    public String getStartNewText() {
        return startNewText;
    }

    public void setStartNewText(String startNewText) {
        this.startNewText = startNewText;
    }

    public String getResumeoldText() {
        return resumeoldText;
    }

    public void setResumeoldText(String resumeoldText) {
        this.resumeoldText = resumeoldText;
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        getPresenterScreen().setPresenterType(PresenterType.metadata);
        final PresenterFeature userCheckFeature = new PresenterFeature(FeatureType.existingUserCheck, null);
        final PresenterFeature multipleUsersFeature = new PresenterFeature(FeatureType.multipleUsers, null);
        userCheckFeature.getPresenterFeatureList().add(multipleUsersFeature);
        final PresenterFeature singleUserFeature = new PresenterFeature(FeatureType.singleUser, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        singleUserFeature.getPresenterFeatureList().add(autoNextPresenter);
        userCheckFeature.getPresenterFeatureList().add(singleUserFeature);
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, startNewText));
        final PresenterFeature createUserFeature = new PresenterFeature(FeatureType.createUserButton, new_Interview);
        createUserFeature.addFeatureAttributes(FeatureAttribute.target, "Edit_User");
        multipleUsersFeature.getPresenterFeatureList().add(createUserFeature);
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, resumeoldText));

        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.targetButton, resume_Interview);
        selectUserFeature.addFeatureAttributes(FeatureAttribute.target, "SelectUser");
        multipleUsersFeature.getPresenterFeatureList().add(selectUserFeature);
        getPresenterScreen().getPresenterFeatureList().add(userCheckFeature);
        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }
}
