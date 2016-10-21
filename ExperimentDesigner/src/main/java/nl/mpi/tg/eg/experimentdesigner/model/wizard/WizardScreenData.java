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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since Aug 8, 2016 11:15:50 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class WizardScreenData {

    @Transient
    final PresenterScreen presenterScreen = new PresenterScreen();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(targetEntity = WizardScreenData.class, cascade = CascadeType.ALL)
    private WizardScreenData backWizardScreenData = null;
    @OneToOne(targetEntity = WizardScreenData.class, cascade = CascadeType.ALL)
    private WizardScreenData nextWizardScreenData = null;
    @Size(max = 6000)
    private String screenText1 = null;
    private String screenText2 = null;
    private String nextButton = null;
    private String screenTitle = null;
    private String menuLabel = null;
    private String screenTag = null;
    private Boolean centreScreen = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Stimulus> stimuli = null;

    private String[] stimuliRandomTags = null;
    private String screenMediaPath = null;
    private Integer stimulusMsDelay = null;
    private Boolean randomiseStimuli = null;
    private Integer stimuliCount = null;
    private Integer repeatCount = null;
    private Integer repeatRandomWindow = null;
//    private String buttonLabelEventTag = null;
    private String backgroundImage = null;
    private Boolean sdCardStimuli = null;
    private String eraseUsersDataButtonlabel = null;
    private String could_not_contact_the_server_please_check = null;
    private String retryButtonLabel = null;

    private Boolean generateCompletionCode = null;
    private Boolean allowUserRestart = null;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Metadata> metadataFields = null;
    private Boolean useCodeVideo = null;
    private String stimulusResponseLabelRight = null;
    private String stimulusResponseLabelLeft = null;
    private Boolean showProgress = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public PresenterScreen getPresenterScreen() {
        return presenterScreen;
    }

    public WizardScreenData getBackWizardScreenData() {
        return backWizardScreenData;
    }

    public void setBackWizardScreenData(WizardScreenData backWizardScreenData) {
        this.backWizardScreenData = backWizardScreenData;
    }

    public WizardScreenData getNextWizardScreenData() {
        return nextWizardScreenData;
    }

    public void setNextWizardScreenData(WizardScreenData nextWizardScreenData) {
        this.nextWizardScreenData = nextWizardScreenData;
    }

    public String getScreenText1() {
        return screenText1;
    }

    public void setScreenText1(String screenText) {
        this.screenText1 = screenText;
    }

    public String getScreenText2() {
        return screenText2;
    }

    public void setScreenText2(String screenText) {
        this.screenText2 = screenText;
    }

    public String getNextButton() {
        return nextButton;
    }

    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getScreenTag() {
        return screenTag;
    }

    public void setScreenTag(String screenTag) {
        this.screenTag = screenTag;
    }

    public Boolean isCentreScreen() {
        return centreScreen;
    }

    public void setCentreScreen(Boolean centreScreen) {
        this.centreScreen = centreScreen;
    }

    public List<Stimulus> getStimuli() {
        return stimuli;
    }

    public void setStimuli(List<Stimulus> stimuli) {
        this.stimuli = stimuli;
    }

    public String getScreenMediaPath() {
        return screenMediaPath;
    }

    public void setScreenMediaPath(String screenMediaPath) {
        this.screenMediaPath = screenMediaPath;
    }

    public String[] getStimuliRandomTags() {
        return stimuliRandomTags;
    }

    public void setStimuliRandomTags(String[] stimuliRandomTags) {
        this.stimuliRandomTags = stimuliRandomTags;
    }

    public Integer getStimulusMsDelay() {
        return stimulusMsDelay;
    }

    public void setStimulusMsDelay(int stimulusMsDelay) {
        this.stimulusMsDelay = stimulusMsDelay;
    }

    public Boolean isRandomiseStimuli() {
        return randomiseStimuli;
    }

    public void setRandomiseStimuli(Boolean randomiseStimuli) {
        this.randomiseStimuli = randomiseStimuli;
    }

    public Integer getStimuliCount() {
        return stimuliCount;
    }

    public void setStimuliCount(int stimuliCount) {
        this.stimuliCount = stimuliCount;
    }

//    public String getButtonLabelEventTag() {
//        return buttonLabelEventTag;
//    }
//
//    public void setButtonLabelEventTag(String buttonLabelEventTag) {
//        this.buttonLabelEventTag = buttonLabelEventTag;
//    }
    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Boolean isSdCardStimuli() {
        return sdCardStimuli;
    }

    public void setSdCardStimuli(Boolean sdCardStimuli) {
        this.sdCardStimuli = sdCardStimuli;
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

    public Boolean getGenerateCompletionCode() {
        return generateCompletionCode;
    }

    public void setGenerateCompletionCode(Boolean generateCompletionCode) {
        this.generateCompletionCode = generateCompletionCode;
    }

    public Boolean getAllowUserRestart() {
        return allowUserRestart;
    }

    public void setAllowUserRestart(Boolean allowUserRestart) {
        this.allowUserRestart = allowUserRestart;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Integer getRepeatRandomWindow() {
        return repeatRandomWindow;
    }

    public void setRepeatRandomWindow(Integer repeatRandomWindow) {
        this.repeatRandomWindow = repeatRandomWindow;
    }

    public List<Metadata> getMetadataFields() {
        if (metadataFields == null) {
            metadataFields = new ArrayList<>();
        }
        return metadataFields;
    }

    public Boolean getUseCodeVideo() {
        return useCodeVideo;
    }

    public void setUseCodeVideo(Boolean useCodeVideo) {
        this.useCodeVideo = useCodeVideo;
    }

    public String getStimulusResponseLabelRight() {
        return stimulusResponseLabelRight;
    }

    public void setStimulusResponseLabelRight(String stimulusResponseLabelRight) {
        this.stimulusResponseLabelRight = stimulusResponseLabelRight;
    }

    public String getStimulusResponseLabelLeft() {
        return stimulusResponseLabelLeft;
    }

    public void setStimulusResponseLabelLeft(String stimulusResponseLabelLeft) {
        this.stimulusResponseLabelLeft = stimulusResponseLabelLeft;
    }

    public Boolean getShowProgress() {
        return showProgress;
    }

    public void setShowProgress(Boolean showProgress) {
        this.showProgress = showProgress;
    }

}
