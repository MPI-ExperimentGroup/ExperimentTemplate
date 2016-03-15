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
    private boolean userSelectScreen = false;
    private boolean agreementScreen = false;
    private String agreementText = "";
    private String agreementScreenText = "";
    private String disagreementScreenText = "";
    private boolean metadataScreen = false;
    private boolean nameField = false;
    private boolean ageField = false;
    private boolean audioTestScreen = false;
    private String testAudioPath = "";

    private boolean practiceScreen = false;
    private String practiceStimuliPath = "";
    private boolean stimuliScreen = false;
    private String stimuliPath = "";
    private boolean completionScreen = false;
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

    public boolean isNameField() {
        return nameField;
    }

    public void setNameField(boolean nameField) {
        this.nameField = nameField;
    }

    public boolean isAgeField() {
        return ageField;
    }

    public void setAgeField(boolean ageField) {
        this.ageField = ageField;
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

    public String getTestAudioPath() {
        return testAudioPath;
    }

    public void setTestAudioPath(String testAudioPath) {
        this.testAudioPath = testAudioPath;
    }

    public boolean isPracticeScreen() {
        return practiceScreen;
    }

    public void setPracticeScreen(boolean practiceScreen) {
        this.practiceScreen = practiceScreen;
    }

    public String getPracticeStimuliPath() {
        return practiceStimuliPath;
    }

    public void setPracticeStimuliPath(String practiceStimuliPath) {
        this.practiceStimuliPath = practiceStimuliPath;
    }

    public boolean isCompletionScreen() {
        return completionScreen;
    }

    public void setCompletionScreen(boolean completionScreen) {
        this.completionScreen = completionScreen;
    }

    public String getCompletionText() {
        return completionText;
    }

    public void setCompletionText(String completionText) {
        this.completionText = completionText;
    }

}
