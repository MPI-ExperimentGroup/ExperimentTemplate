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
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since May 3, 2016 2:11:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardEditUserScreen extends AbstractWizardScreen {

    private String postText;
    private WizardScreen alternateNextScreen;
    private String alternateButtonLabel;
    private boolean sendData;
    private String on_Error_Text = "Could not contact the server, please check your internet connection and try again.";

    public WizardEditUserScreen() {
        super(WizardScreenEnum.WizardEditUserScreen, "EditUser", "EditUser", "EditUser");
    }

    public WizardEditUserScreen(final String screenTitle, final String screenTag, String dispalyText, final String saveButtonLabel, final String postText, final AbstractWizardScreen alternateNextScreen, final String alternateButtonLabel, final boolean sendData, final String on_Error_Text) {
        super(WizardScreenEnum.WizardEditUserScreen, screenTitle, screenTitle, screenTag);
        this.setScreenText(dispalyText);
        this.wizardScreenData.setNextButton(saveButtonLabel);
        this.postText = postText;
        this.alternateButtonLabel = alternateButtonLabel;
        this.sendData = sendData;
        this.on_Error_Text = on_Error_Text;
        this.alternateNextScreen = alternateNextScreen;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public WizardScreen getAlternateNextScreen() {
        return alternateNextScreen;
    }

    public void setAlternateNextScreen(WizardScreen alternateNextScreen) {
        this.alternateNextScreen = alternateNextScreen;
    }

    public String getAlternateButtonLabel() {
        return alternateButtonLabel;
    }

    public void setAlternateButtonLabel(String alternateButtonLabel) {
        this.alternateButtonLabel = alternateButtonLabel;
    }

    public boolean isSendData() {
        return sendData;
    }

    public void setSendData(boolean sendData) {
        this.sendData = sendData;
    }

    public String getOn_Error_Text() {
        return on_Error_Text;
    }

    public void setOn_Error_Text(String on_Error_Text) {
        this.on_Error_Text = on_Error_Text;
    }

    public void setFirstNameField() {
        wizardScreenData.getMetadataFields().add(new Metadata("firstName", "First name", ".'{'3,'}'", "Please enter at least three letters.", false, null));
    }

    public void setLastNameField() {
        wizardScreenData.getMetadataFields().add(new Metadata("lastName", "Last name", ".'{'3,'}'", "Please enter at least three letters.", false, null));

    }

    public void setEmailAddressField() {
        wizardScreenData.getMetadataFields().add(new Metadata("emailAddress", "Email address", "^[^@]+@[^@]+$", "Please enter a valid email address.", false, null));
    }

    public void setOptionCheckBox1(String optionCheckBox1) {
        wizardScreenData.getMetadataFields().add(new Metadata("optionCheckBox1", optionCheckBox1, "true|false", "Please enter true or false.", false, null));
    }

    public void setOptionCheckBox2(String optionCheckBox2) {
        wizardScreenData.getMetadataFields().add(new Metadata("optionCheckBox2", optionCheckBox2, "true|false", "Please enter true or false.", false, null));
    }

    public void setMandatoryCheckBox(String mandatoryCheckBox) {
        wizardScreenData.getMetadataFields().add(new Metadata("mandatoryCheckBox", mandatoryCheckBox, "true", "Please agree to continue.", false, null));
    }

    public void setAgeField() {
        wizardScreenData.getMetadataFields().add(new Metadata("age", "Age", "[0-9]+", "Please enter a valid age.", false, null));
    }

    public void setGenderField() {
        wizardScreenData.getMetadataFields().add(new Metadata("gender", "Gender", "|male|female|other", null, false, null));
    }

    public void setCustomTextField(String customTextField) {
        wizardScreenData.getMetadataFields().add(new Metadata("customTextField1", customTextField, ".'{'3,'}'", "Please enter at least three letters.", false, null));
    }

    public void setCustomFields(String[] customFields) {
        if (customFields != null) {
            for (String fieldString : customFields) {
                insertMetadataByString(fieldString);
            }
        }
    }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public PresenterScreen addEditUserScreen(final Experiment experiment, final PresenterScreen backPresenter, final String screenTitle, final PresenterScreen nextPresenter, long displayOrder, final boolean sendData, final String on_Error_Text, boolean obfuscateScreenNames) {
//        return addEditUserScreen(experiment, backPresenter, screenTitle, "Edit User", nextPresenter, displayOrder, null, null, null, "Save Details", null, null, null, sendData, on_Error_Text, obfuscateScreenNames);
//    }
//
//    public PresenterScreen addEditUserScreen() {
    @Override
    public PresenterScreen populatePresenterScreen(Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        getPresenterScreen().setPresenterType(PresenterType.metadata);
        if (getScreenText() != null) {
            getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, getScreenText()));
        }
        for (Metadata metadata : wizardScreenData.getMetadataFields()) {
            experiment.getMetadata().add(metadata);
            final PresenterFeature metadataField = new PresenterFeature(FeatureType.metadataField, null);
            metadataField.addFeatureAttributes(FeatureAttribute.fieldName, metadata.getPostName());
            getPresenterScreen().getPresenterFeatureList().add(metadataField);
        }
//        if (wizardScreenData.getMetadataFields().isEmpty()) {
//            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMetadataFields, null));
//        }
        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, wizardScreenData.getNextButton());
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, Boolean.toString(sendData));
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.networkErrorMessage, on_Error_Text);
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.autoNextPresenter, null);
        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        getPresenterScreen().getPresenterFeatureList().add(saveMetadataButton);
        if (postText != null || alternateNextScreen != null) {
            getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            if (postText != null) {
                getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, postText));
            }
            if (alternateNextScreen != null) {
                final PresenterFeature alternateNextFeature = new PresenterFeature(FeatureType.targetButton, alternateButtonLabel);
                alternateNextFeature.addFeatureAttributes(FeatureAttribute.target, alternateNextScreen.getScreenTag());
                getPresenterScreen().getPresenterFeatureList().add(alternateNextFeature);
            }
        }
        experiment.getPresenterScreen().add(getPresenterScreen());
        return getPresenterScreen();
    }

    final public void insertMetadataByString(String fieldString) {
        final String[] splitFieldString = fieldString.split(":");
        wizardScreenData.getMetadataFields().add(new Metadata(splitFieldString[0], splitFieldString[1], splitFieldString[2], splitFieldString[3], false, null));
    }

    public void insertMetadataField(final String label) {
        wizardScreenData.getMetadataFields().add(new Metadata(label.replaceAll("[^A-Za-z0-9]", "_"), label, "true|false", "Please enter true or false.", false, null));
    }

    public void insertMetadataField(final Metadata metadata) {
        wizardScreenData.getMetadataFields().add(metadata);
    }
}
