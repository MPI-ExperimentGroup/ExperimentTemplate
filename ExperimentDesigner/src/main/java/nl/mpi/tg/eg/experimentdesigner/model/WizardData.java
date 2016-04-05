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
package nl.mpi.tg.eg.experimentdesigner.model;

/**
 * @since Mar 4, 2016 3:10:35 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardData {

    private String appName = "";
    private boolean obfuscateScreenNames = false;
    private boolean userSelectScreen = false;
    private boolean agreementScreen = false;
    private String informationScreenText = "";
    private String agreementText = "";
    private String agreementScreenText = "";
    private String disagreementScreenText = "";
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
    private boolean audioTestScreen = false;
    private String audioTestScreenText = "";
    private String testAudioPath = "";

    private boolean practiceStimuliScreen = false;
    private String practiceStimuliPath = "";
    private String[] practiceStimuliSet = null;
    private String[] practiceStimuliRandomTags = null;
    private String practiceStimulusCodeMatch = null;
    private int practiceStimulusCodeMsDelay = 0;
    private int practiceStimulusMsDelay = 0;
    private String practiceStimulusCodeFormat = null;
    private int practiceStimuliCount = 1;
    private String practiceStimulusResponseLabelLeft = null;
    private String practiceStimulusResponseLabelRight = null;
    private String practiceStimulusResponseOptions = null;

    private boolean stimuliScreen = false;
    private String stimuliPath = "";
    private String[] stimuliSet = null;
    private String[] stimuliRandomTags = null;
    private String stimulusCodeMatch = null;
    private int stimulusCodeMsDelay = 0;
    private int stimulusMsDelay = 0;
    private String stimulusCodeFormat = null;
    private int stimuliCount = 1;
    private String stimulusResponseLabelLeft = null;
    private String stimulusResponseLabelRight = null;
    private String stimulusResponseOptions = null;
    private boolean completionScreen = false;
    private boolean allowUserRestart = true;
    private String completionText = "";
    private boolean menuScreen = false;

    public WizardData() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isObfuscateScreenNames() {
        return obfuscateScreenNames;
    }

    public void setObfuscateScreenNames(boolean obfuscateScreenNames) {
        this.obfuscateScreenNames = obfuscateScreenNames;
    }

    public String getInformationScreenText() {
        return informationScreenText;
    }

    public void setInformationScreenText(String informationScreenText) {
        this.informationScreenText = informationScreenText;
    }

    public boolean isUserSelectScreen() {
        return userSelectScreen;
    }

    public void setUserSelectScreen(boolean userSelectScreen) {
        this.userSelectScreen = userSelectScreen;
    }

    public boolean isAgreementScreen() {
        return agreementScreen;
    }

    public void setAgreementScreen(boolean agreementScreen) {
        this.agreementScreen = agreementScreen;
    }

    public String getAgreementScreenText() {
        return agreementScreenText;
    }

    public void setAgreementScreenText(String agreementScreenText) {
        this.agreementScreenText = agreementScreenText;
    }

    public String getDisagreementScreenText() {
        return disagreementScreenText;
    }

    public void setDisagreementScreenText(String disagreementScreenText) {
        this.disagreementScreenText = disagreementScreenText;
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

    public boolean isPracticeStimuliScreen() {
        return practiceStimuliScreen;
    }

    public void setPracticeStimuliScreen(boolean practiceStimuliScreen) {
        this.practiceStimuliScreen = practiceStimuliScreen;
    }

    public String getPracticeStimuliPath() {
        return practiceStimuliPath;
    }

    public void setPracticeStimuliPath(String practiceStimuliPath) {
        this.practiceStimuliPath = practiceStimuliPath;
    }

    public String[] getPracticeStimuliSet() {
        return practiceStimuliSet;
    }

    public void setPracticeStimuliSet(String[] practiceStimuliSet) {
        this.practiceStimuliSet = practiceStimuliSet;
    }

    public String[] getPracticeStimuliRandomTags() {
        return practiceStimuliRandomTags;
    }

    public void setPracticeStimuliRandomTags(String[] practiceStimuliRandomTags) {
        this.practiceStimuliRandomTags = practiceStimuliRandomTags;
    }

    public String getPracticeStimulusCodeMatch() {
        return practiceStimulusCodeMatch;
    }

    public void setPracticeStimulusCodeMatch(String practiceStimulusCodeMatch) {
        this.practiceStimulusCodeMatch = practiceStimulusCodeMatch;
    }

    public int getPracticeStimulusCodeMsDelay() {
        return practiceStimulusCodeMsDelay;
    }

    public void setPracticeStimulusCodeMsDelay(int practiceStimulusCodeMsDelay) {
        this.practiceStimulusCodeMsDelay = practiceStimulusCodeMsDelay;
    }

    public int getPracticeStimulusMsDelay() {
        return practiceStimulusMsDelay;
    }

    public void setPracticeStimulusMsDelay(int practiceStimulusMsDelay) {
        this.practiceStimulusMsDelay = practiceStimulusMsDelay;
    }

    public String getPracticeStimulusCodeFormat() {
        return practiceStimulusCodeFormat;
    }

    public void setPracticeStimulusCodeFormat(String practiceStimulusCodeFormat) {
        this.practiceStimulusCodeFormat = practiceStimulusCodeFormat;
    }

    public int getPracticeStimuliCount() {
        return practiceStimuliCount;
    }

    public void setPracticeStimuliCount(int practiceStimuliCount) {
        this.practiceStimuliCount = practiceStimuliCount;
    }

    public String getPracticeStimulusResponseLabelLeft() {
        return practiceStimulusResponseLabelLeft;
    }

    public void setPracticeStimulusResponseLabelLeft(String practiceStimulusResponseLabelLeft) {
        this.practiceStimulusResponseLabelLeft = practiceStimulusResponseLabelLeft;
    }

    public String getPracticeStimulusResponseLabelRight() {
        return practiceStimulusResponseLabelRight;
    }

    public void setPracticeStimulusResponseLabelRight(String practiceStimulusResponseLabelRight) {
        this.practiceStimulusResponseLabelRight = practiceStimulusResponseLabelRight;
    }

    public String getPracticeStimulusResponseOptions() {
        return practiceStimulusResponseOptions;
    }

    public void setPracticeStimulusResponseOptions(String practiceStimulusResponseOptions) {
        this.practiceStimulusResponseOptions = practiceStimulusResponseOptions;
    }

    public boolean isStimuliScreen() {
        return stimuliScreen;
    }

    public void setStimuliScreen(boolean stimuliScreen) {
        this.stimuliScreen = stimuliScreen;
    }

    public String getStimuliPath() {
        return stimuliPath;
    }

    public void setStimuliPath(String stimuliPath) {
        this.stimuliPath = stimuliPath;
    }

    public String[] getStimuliSet() {
        return stimuliSet;
    }

    public void setStimuliSet(String[] stimuliSet) {
        this.stimuliSet = stimuliSet;
    }

    public String[] getStimuliRandomTags() {
        return stimuliRandomTags;
    }

    public void setStimuliRandomTags(String[] stimuliRandomTags) {
        this.stimuliRandomTags = stimuliRandomTags;
    }

    public int getStimuliCount() {
        return stimuliCount;
    }

    public void setStimuliCount(int stimuliCount) {
        this.stimuliCount = stimuliCount;
    }

    public String getStimulusResponseOptions() {
        return stimulusResponseOptions;
    }

    public void setStimulusResponseOptions(String stimulusResponseOptions) {
        this.stimulusResponseOptions = stimulusResponseOptions;
    }

    public String getStimulusResponseLabelLeft() {
        return stimulusResponseLabelLeft;
    }

    public void setStimulusResponseLabelLeft(String stimulusResponseLabelLeft) {
        this.stimulusResponseLabelLeft = stimulusResponseLabelLeft;
    }

    public String getStimulusResponseLabelRight() {
        return stimulusResponseLabelRight;
    }

    public void setStimulusResponseLabelRight(String stimulusResponseLabelRight) {
        this.stimulusResponseLabelRight = stimulusResponseLabelRight;
    }

    public String getStimulusCodeMatch() {
        return stimulusCodeMatch;
    }

    public void setStimulusCodeMatch(String stimulusCodeMatch) {
        this.stimulusCodeMatch = stimulusCodeMatch;
    }

    public int getStimulusCodeMsDelay() {
        return stimulusCodeMsDelay;
    }

    public void setStimulusCodeMsDelay(int stimulusCodeMsDelay) {
        this.stimulusCodeMsDelay = stimulusCodeMsDelay;
    }

    public int getStimulusMsDelay() {
        return stimulusMsDelay;
    }

    public void setStimulusMsDelay(int stimulusMsDelay) {
        this.stimulusMsDelay = stimulusMsDelay;
    }

    public String getStimulusCodeFormat() {
        return stimulusCodeFormat;
    }

    public void setStimulusCodeFormat(String stimulusCodeFormat) {
        this.stimulusCodeFormat = stimulusCodeFormat;
    }

    public boolean isMenuScreen() {
        return menuScreen;
    }

    public void setMenuScreen(boolean menuScreen) {
        this.menuScreen = menuScreen;
    }

    public String getAgreementText() {
        return agreementText;
    }

    public void setAgreementText(String agreementText) {
        this.agreementText = agreementText;
    }

    public boolean isAudioTestScreen() {
        return audioTestScreen;
    }

    public void setAudioTestScreen(boolean audioTestScreen) {
        this.audioTestScreen = audioTestScreen;
    }

    public String getAudioTestScreenText() {
        return audioTestScreenText;
    }

    public void setAudioTestScreenText(String audioTestScreenText) {
        this.audioTestScreenText = audioTestScreenText;
    }

    public String getTestAudioPath() {
        return testAudioPath;
    }

    public void setTestAudioPath(String testAudioPath) {
        this.testAudioPath = testAudioPath;
    }

    public boolean isCompletionScreen() {
        return completionScreen;
    }

    public void setCompletionScreen(boolean completionScreen) {
        this.completionScreen = completionScreen;
    }

    public boolean isAllowUserRestart() {
        return allowUserRestart;
    }

    public void setAllowUserRestart(boolean allowRestart) {
        this.allowUserRestart = allowRestart;
    }

    public String getCompletionText() {
        return completionText;
    }

    public void setCompletionText(String completionText) {
        this.completionText = completionText;
    }

}
