/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @since Mar 1, 2018 10:43:43 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@XmlRootElement
public class WizardUtilData {

    protected WizardUtilEnum wizardUtilEnum;

    protected String experimentTitle;

    protected String allowedCharCodes;

    protected String freeTextValidationMessage;

    protected String feedbackScreenText;

    protected String instructionsText;

    protected String agreementText;

    protected boolean showProgress;

    protected boolean allowUserRestart;

    protected String debriefingText1;

    protected String debriefingText2; 

    protected String[] stimuliArray;

    protected String[] randomStimuliTags;

    public WizardUtilEnum getWizardUtilEnum() {
        return wizardUtilEnum;
    }

    public void setWizardUtilEnum(WizardUtilEnum wizardUtilEnum) {
        this.wizardUtilEnum = wizardUtilEnum;
    }

    public String getExperimentTitle() {
        return experimentTitle;
    }

    public void setExperimentTitle(String experimentTitle) {
        this.experimentTitle = experimentTitle;
    }

    public String getAllowedCharCodes() {
        return allowedCharCodes;
    }

    public void setAllowedCharCodes(String allowedCharCodes) {
        this.allowedCharCodes = allowedCharCodes;
    }

    public String getFreeTextValidationMessage() {
        return freeTextValidationMessage;
    }

    public void setFreeTextValidationMessage(String freeTextValidationMessage) {
        this.freeTextValidationMessage = freeTextValidationMessage;
    }

    public String getFeedbackScreenText() {
        return feedbackScreenText;
    }

    public void setFeedbackScreenText(String feedbackScreenText) {
        this.feedbackScreenText = feedbackScreenText;
    }

    public String getInstructionsText() {
        return instructionsText;
    }

    public void setInstructionsText(String instructionsText) {
        this.instructionsText = instructionsText;
    }

    public String getAgreementText() {
        return agreementText;
    }

    public void setAgreementText(String agreementText) {
        this.agreementText = agreementText;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isAllowUserRestart() {
        return allowUserRestart;
    }

    public void setAllowUserRestart(boolean allowUserRestart) {
        this.allowUserRestart = allowUserRestart;
    }

    public String getDebriefingText1() {
        return debriefingText1;
    }

    public void setDebriefingText1(String debriefingText1) {
        this.debriefingText1 = debriefingText1;
    }

    public String getDebriefingText2() {
        return debriefingText2;
    }

    public void setDebriefingText2(String debriefingText2) {
        this.debriefingText2 = debriefingText2;
    }

    public String[] getStimuliArray() {
        return stimuliArray;
    }

    public void setStimuliArray(String[] stimuliArray) {
        this.stimuliArray = stimuliArray;
    }

    public String[] getRandomStimuliTags() {
        return randomStimuliTags;
    }

    public void setRandomStimuliTags(String[] randomStimuliTags) {
        this.randomStimuliTags = randomStimuliTags;
    }
}
