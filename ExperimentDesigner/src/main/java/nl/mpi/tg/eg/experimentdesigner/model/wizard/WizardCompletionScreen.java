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
 * @since May 12, 2016 3:38:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardCompletionScreen extends AbstractWizardScreen {

    private String completedText1;
    private boolean allowUserRestart;
    private String completedText2;
    private String eraseUsersDataButtonlabel;
    private String could_not_contact_the_server_please_check;
    private String retryButtonLabel;

    public WizardCompletionScreen() {
        super("Completion", "Completion", "Completion");
    }

    public WizardCompletionScreen(String completedText1, final boolean allowUserRestart, String completedText2, String eraseUsersDataButtonlabel, final String screenTitle, final String could_not_contact_the_server_please_check, final String retryButtonLabel) {
        super(screenTitle, screenTitle, screenTitle);
        this.completedText1 = completedText1;
        this.allowUserRestart = allowUserRestart;
        this.completedText2 = completedText2;
        this.eraseUsersDataButtonlabel = eraseUsersDataButtonlabel;
        this.could_not_contact_the_server_please_check = could_not_contact_the_server_please_check;
        this.retryButtonLabel = retryButtonLabel;
    }

    public String getCompletedText1() {
        return completedText1;
    }

    public void setCompletedText1(String completedText1) {
        this.completedText1 = completedText1;
    }

    public boolean isAllowUserRestart() {
        return allowUserRestart;
    }

    public void setAllowUserRestart(boolean allowUserRestart) {
        this.allowUserRestart = allowUserRestart;
    }

    public String getCompletedText2() {
        return completedText2;
    }

    public void setCompletedText2(String completedText2) {
        this.completedText2 = completedText2;
    }

    public String getEraseUsersDataButtonlabel() {
        return eraseUsersDataButtonlabel;
    }

    public void setEraseUsersDataButtonlabel(String eraseUsersDataButtonlabel) {
        this.eraseUsersDataButtonlabel = eraseUsersDataButtonlabel;
    }

    public String getCould_not_contact_the_server_please_check() {
        return could_not_contact_the_server_please_check;
    }

    public void setCould_not_contact_the_server_please_check(String could_not_contact_the_server_please_check) {
        this.could_not_contact_the_server_please_check = could_not_contact_the_server_please_check;
    }

    public String getRetryButtonLabel() {
        return retryButtonLabel;
    }

    public void setRetryButtonLabel(String retryButtonLabel) {
        this.retryButtonLabel = retryButtonLabel;
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.transmission);
        final PresenterFeature sendAllDataFeature = new PresenterFeature(FeatureType.sendAllData, null);
        presenterScreen.getPresenterFeatureList().add(sendAllDataFeature);

        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        sendAllDataFeature.getPresenterFeatureList().add(onSuccessFeature);
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, completedText1));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.generateCompletionCode, null));

        if (completedText2 != null) {
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, completedText2));
        }
        if (allowUserRestart) {
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseUsersDataButton, eraseUsersDataButtonlabel));
        }
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        sendAllDataFeature.getPresenterFeatureList().add(onErrorFeature);
//        final String could_not_contact_the_server_please_check = "Could not contact the server, please check your internet connection and try again.";
        onErrorFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, could_not_contact_the_server_please_check));
//        final String retryButtonLabel = "Retry";
        final PresenterFeature retryFeature = new PresenterFeature(FeatureType.targetButton, retryButtonLabel);
        onErrorFeature.getPresenterFeatureList().add(retryFeature);
        retryFeature.addFeatureAttributes(FeatureAttribute.target, screenTitle);

        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
