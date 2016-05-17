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

import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScreen;
import java.util.ArrayList;
import java.util.List;

/**
 * @since Mar 4, 2016 3:10:35 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardData {

    private String appName = "";
    private boolean showMenuBar = true;
    private boolean obfuscateScreenNames = false;
    
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
    private String userRestartButtonText = null;
    private String completionText1 = "";
    private String completionText2 = "";
    private int textFontSize = 17;

    private final List<WizardScreen> wizardScreens = new ArrayList<>();

    public WizardData() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isShowMenuBar() {
        return showMenuBar;
    }

    public void setShowMenuBar(boolean showMenuBar) {
        this.showMenuBar = showMenuBar;
    }

    public int getTextFontSize() {
        return textFontSize;
    }

    public void setTextFontSize(int textFontSize) {
        this.textFontSize = textFontSize;
    }

    public boolean isObfuscateScreenNames() {
        return obfuscateScreenNames;
    }

    public void setObfuscateScreenNames(boolean obfuscateScreenNames) {
        this.obfuscateScreenNames = obfuscateScreenNames;
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

    public void addScreen(final WizardScreen wizardScreen) {
        wizardScreens.add(wizardScreen);
    }

    public List<WizardScreen> getWizardScreens() {
        return wizardScreens;
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

    public String getCompletionText1() {
        return completionText1;
    }

    public void setCompletionText1(String completionText1) {
        this.completionText1 = completionText1;
    }

    public String getCompletionText2() {
        return completionText2;
    }

    public void setCompletionText2(String completionText2) {
        this.completionText2 = completionText2;
    }

    public String getUserRestartButtonText() {
        return userRestartButtonText;
    }

    public void setUserRestartButtonText(String userRestartButtonText) {
        this.userRestartButtonText = userRestartButtonText;
    }

}
