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

    public WizardCompletionScreen() {
        super("Completion", "Completion", "Completion");
    }

    public WizardCompletionScreen(String completedText1, final boolean allowUserRestart, boolean generateCompletionCode, String completedText2, String eraseUsersDataButtonlabel, final String screenTitle, final String could_not_contact_the_server_please_check, final String retryButtonLabel) {
        super(screenTitle, screenTitle, screenTitle);
        wizardScreenData.setScreenText1(completedText1);
        wizardScreenData.setScreenText2(completedText2);
        wizardScreenData.setAllowUserRestart(allowUserRestart);
        wizardScreenData.setGenerateCompletionCode(generateCompletionCode);
        wizardScreenData.setEraseUsersDataButtonlabel(eraseUsersDataButtonlabel);
        wizardScreenData.setCould_not_contact_the_server_please_check(could_not_contact_the_server_please_check);
        wizardScreenData.setRetryButtonLabel(retryButtonLabel);
    }

    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        getPresenterScreen().setPresenterType(PresenterType.transmission);
        final PresenterFeature sendAllDataFeature = new PresenterFeature(FeatureType.sendAllData, null);
        getPresenterScreen().getPresenterFeatureList().add(sendAllDataFeature);

        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        sendAllDataFeature.getPresenterFeatureList().add(onSuccessFeature);
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, wizardScreenData.getScreenText1()));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        if (wizardScreenData.getGenerateCompletionCode()) {
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.generateCompletionCode, null));
        }

        if (wizardScreenData.getScreenText2() != null) {
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, wizardScreenData.getScreenText2()));
        }
        if (wizardScreenData.getAllowUserRestart()) {
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseUsersDataButton, wizardScreenData.getEraseUsersDataButtonlabel()));
        }
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        sendAllDataFeature.getPresenterFeatureList().add(onErrorFeature);
//        final String could_not_contact_the_server_please_check = "Could not contact the server, please check your internet connection and try again.";
        onErrorFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, wizardScreenData.getCould_not_contact_the_server_please_check()));
//        final String retryButtonLabel = "Retry";
        final PresenterFeature retryFeature = new PresenterFeature(FeatureType.targetButton, wizardScreenData.getRetryButtonLabel());
        onErrorFeature.getPresenterFeatureList().add(retryFeature);
        retryFeature.addFeatureAttributes(FeatureAttribute.target, getScreenTag());

        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }
}
