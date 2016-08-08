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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

/**
 * @since Aug 8, 2016 11:15:50 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity

public class WizardScreenData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(targetEntity = AbstractWizardScreen.class, cascade = CascadeType.ALL)
    WizardScreen backWizardScreen = null;
    @OneToOne(targetEntity = AbstractWizardScreen.class, cascade = CascadeType.ALL)
    WizardScreen nextWizardScreen = null;
    @Size(max = 3500)
    String screenText = null;
    String nextButton = null;
    String screenTitle = null;
    String menuLabel = null;
    String screenTag = null;
    Boolean centreScreen = true;

    private String[] stimuliSet = null;
    private String[] stimuliRandomTags = null;
    private Integer stimulusMsDelay = null;
    private Boolean randomiseStimuli = false;
    private Integer stimuliCount = null;
//    private String buttonLabelEventTag = null;
    private String backgroundImage = null;
    private Boolean sdCardStimuli = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WizardScreen getBackWizardScreen() {
        return backWizardScreen;
    }

    public void setBackWizardScreen(WizardScreen backWizardScreen) {
        this.backWizardScreen = backWizardScreen;
    }

    public WizardScreen getNextWizardScreen() {
        return nextWizardScreen;
    }

    public void setNextWizardScreen(WizardScreen nextWizardScreen) {
        this.nextWizardScreen = nextWizardScreen;
    }

    public String getScreenText() {
        return screenText;
    }

    public void setScreenText(String screenText) {
        this.screenText = screenText;
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

}
