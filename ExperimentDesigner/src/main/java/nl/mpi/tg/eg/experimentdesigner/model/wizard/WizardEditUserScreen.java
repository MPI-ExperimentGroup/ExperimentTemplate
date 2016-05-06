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
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;

/**
 * @since May 3, 2016 2:11:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardEditUserScreen extends AbstractWizardScreen {

    private boolean metadataScreen = false;
    private String metadataScreenText = "";
    private boolean speakerNameField = false;

    private boolean firstNameField = false;
    private boolean lastNameField = false;
    private boolean genderField = false;
    private boolean emailAddressField = false;
    private String customTextField = "";
    private String optionCheckBox1 = "";
    private String optionCheckBox2 = "";
    private String mandatoryCheckBox = "";
    private boolean ageField = false;
    private String[] customFields = null;
    private String saveButtonLabel;
    private String postText;
    private WizardScreen alternateNextScreen;
    private String alternateButtonLabel;
    private boolean sendData;
    private String on_Error_Text="Could not contact the server, please check your internet connection and try again.";

    public String getSaveButtonLabel() {
        return saveButtonLabel;
    }

    public void setSaveButtonLabel(String saveButtonLabel) {
        this.saveButtonLabel = saveButtonLabel;
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

    public boolean isMetadataScreen() {
        return metadataScreen;
    }

    public void setMetadataScreen(boolean metadataScreen) {
        this.metadataScreen = metadataScreen;
    }

    public String getMetadataScreenText() {
        return metadataScreenText;
    }

    public void setMetadataScreenText(String metadataScreenText) {
        this.metadataScreenText = metadataScreenText;
    }

    public boolean isSpeakerNameField() {
        return speakerNameField;
    }

    public void setSpeakerNameField(boolean speakerNameField) {
        this.speakerNameField = speakerNameField;
    }

    public boolean isFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(boolean firstNameField) {
        this.firstNameField = firstNameField;
    }

    public boolean isLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(boolean lastNameField) {
        this.lastNameField = lastNameField;
    }

    public boolean isEmailAddressField() {
        return emailAddressField;
    }

    public void setEmailAddressField(boolean emailAddressField) {
        this.emailAddressField = emailAddressField;
    }

    public String getOptionCheckBox1() {
        return optionCheckBox1;
    }

    public void setOptionCheckBox1(String optionCheckBox1) {
        this.optionCheckBox1 = optionCheckBox1;
    }

    public String getOptionCheckBox2() {
        return optionCheckBox2;
    }

    public void setOptionCheckBox2(String optionCheckBox2) {
        this.optionCheckBox2 = optionCheckBox2;
    }

    public String getMandatoryCheckBox() {
        return mandatoryCheckBox;
    }

    public void setMandatoryCheckBox(String mandatoryCheckBox) {
        this.mandatoryCheckBox = mandatoryCheckBox;
    }

    public boolean isAgeField() {
        return ageField;
    }

    public void setAgeField(boolean ageField) {
        this.ageField = ageField;
    }

    public boolean isGenderField() {
        return genderField;
    }

    public void setGenderField(boolean genderField) {
        this.genderField = genderField;
    }

    public String getCustomTextField() {
        return customTextField;
    }

    public void setCustomTextField(String customTextField) {
        this.customTextField = customTextField;
    }

    public String[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String[] customFields) {
        this.customFields = customFields;
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
        presenterScreen.setPresenterType(PresenterType.metadata);
        if (screenText != null) {
            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, screenText));
        }
        if (getCustomFields() != null) {
            for (String fieldString : getCustomFields()) {
                insertMetadataByString(fieldString, experiment, presenterScreen);
            }
        }
        if (!getMetadataScreenText().isEmpty()) {
            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, getMetadataScreenText()));
        }
//            addMetadata(experiment);
//            insertMetadataField(experiment, new Metadata("workerId", "Worker ID", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
        if (isSpeakerNameField()) {
            insertMetadataField(experiment, new Metadata("speakerName", "Speaker name *", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
        }
        if (isFirstNameField()) {
            insertMetadataField(experiment, new Metadata("firstName", "First name", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
        }
        if (isLastNameField()) {
            insertMetadataField(experiment, new Metadata("lastName", "Last name", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
        }
        if (isAgeField()) {
            insertMetadataField(experiment, new Metadata("age", "Age", "[0-9]+", "Please enter a valid age.", false, null), presenterScreen);
        }
        if (isGenderField()) {
            insertMetadataField(experiment, new Metadata("gender", "Gender", "|male|female|other", null, false, null), presenterScreen);
        }
        if (isEmailAddressField()) {
            insertMetadataField(experiment, new Metadata("emailAddress", "Email address", "^[^@]+@[^@]+$", "Please enter a valid email address.", false, null), presenterScreen);
        }
        if (!getCustomTextField().isEmpty()) {
            insertMetadataField(experiment, new Metadata("customTextField1", getCustomTextField(), ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
        }
        if (!getOptionCheckBox1().isEmpty()) {
            insertMetadataField(experiment, new Metadata("optionCheckBox1", getOptionCheckBox1(), "true|false", "Please enter true or false.", false, null), presenterScreen);
        }
        if (!getOptionCheckBox2().isEmpty()) {
            insertMetadataField(experiment, new Metadata("optionCheckBox2", getOptionCheckBox2(), "true|false", "Please enter true or false.", false, null), presenterScreen);
        }
        if (!getMandatoryCheckBox().isEmpty()) {
            insertMetadataField(experiment, new Metadata("mandatoryCheckBox", getMandatoryCheckBox(), "true", "Please agree to continue.", false, null), presenterScreen);
        }
        
//        if (customFields != null) {
//            for (String fieldString : customFields) {
//                insertMetadataByString(fieldString, experiment, presenterScreen);
//            }
//        }
//        if (wizardData == null && customFields == null) {
//            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMetadataFields, null));
//        }
        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, saveButtonLabel);
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, Boolean.toString(sendData));
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, on_Error_Text);
        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.autoNextPresenter, null);
        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        presenterScreen.getPresenterFeatureList().add(saveMetadataButton);
        if (postText != null || alternateNextScreen != null) {
            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            if (postText != null) {
                presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, postText));
            }
            if (alternateNextScreen != null) {
                final PresenterFeature alternateNextFeature = new PresenterFeature(FeatureType.targetButton, alternateButtonLabel);
                alternateNextFeature.addFeatureAttributes(FeatureAttribute.target, alternateNextScreen.getScreenTag());
                presenterScreen.getPresenterFeatureList().add(alternateNextFeature);
            }
        }
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    private void insertMetadataByString(String fieldString, final Experiment experiment, final PresenterScreen presenterScreen) {
        final String[] splitFieldString = fieldString.split(":");
        insertMetadataField(experiment, new Metadata(splitFieldString[0], splitFieldString[1], splitFieldString[2], splitFieldString[3], false, null), presenterScreen);
    }

    public void insertMetadataField(final Experiment experiment, final String label, final PresenterScreen presenterScreen) {
        insertMetadataField(experiment, new Metadata(label.replaceAll("[^A-Za-z0-9]", "_"), label, "true|false", "Please enter true or false.", false, null), presenterScreen);
    }

    public void insertMetadataField(final Experiment experiment, final Metadata metadata, final PresenterScreen presenterScreen) {
        experiment.getMetadata().add(metadata);
        final PresenterFeature metadataField = new PresenterFeature(FeatureType.metadataField, null);
        metadataField.addFeatureAttributes(FeatureAttribute.fieldName, metadata.getPostName());
        presenterScreen.getPresenterFeatureList().add(metadataField);
    }
}
