/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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
 * this can be updated with the output of: grep match=
 * ~/Documents/ExperimentTemplate/gwt-cordova/src/main/xsl/config2java.xsl
 *
 * @since Aug 18, 2015 4:29:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl,
 */
public enum FeatureType {

    htmlText(false, true, null),
    text(false, true, null),
    image(false, false, null),
    menuItem(false, false, null),
    popupMessage(false, false, null),
    optionButton(false, false, null),
    endOfStimulusButton(false, false, null),
    padding(false, false, null),
    localStorageData(false, false, null),
    allMetadataFields(false, false, null),
    eraseLocalStorageButton(false, false, null),
    showCurrentMs(false, false, null),
    enableStimulusButtons(false, false, null),
    disableStimulusButtons(false, false, null),
    showStimulusProgress(false, false, null),
    hideStimulusButtons(false, false, null),
    showStimulusButtons(false, false, null),
    generateCompletionCode(false, false, null),
    sendAllData(false, false, null),
    eraseLocalStorageOnWindowClosing(false, false, null),
    clearStimulus(false, false, null),
    removeStimulus(false, false, null),
    keepStimulus(false, false, null),
    nextStimulus(false, false, null),
    centrePage(false, false, null),
    allMenuItems(false, false, null),
    nextStimulusButton(false, false, null),
    autoNextStimulus(false, false, null),
    conditionalHtml(false, false, null),
    addKinTypeGui(false, false, null),
    autoNextPresenter(false, false, null),
    logTimeStamp(false, false, null),
    audioButton(false, false, null),
    preloadAllStimuli(false, false, null),
    showStimulusGrid(false, false, null),
    pause(false, false, null),
    onError(false, false, null),
    onSuccess(false, false, null),
    kinTypeStringDiagram(false, false, null),
    loadKinTypeStringDiagram(false, false, null),
    responseCorrect(false, false, null),
    responseIncorrect(false, false, null),
    hasMoreStimulus(false, false, null),
    endOfStimulus(false, false, null),
    stimulusImage(false, false, null),
    stimulusAudio(false, false, null),
    userInfo(false, false, null),
    versionData(false, false, null),
    preventWindowClose(false, false, null);
    private final boolean canHaveFeatures;
    private final boolean canHaveText;
    private final FeatureAttribute[] featureAttributes;

    private FeatureType(boolean canHaveFeatures, boolean canHaveText, FeatureAttribute[] featureAttributes) {
        this.canHaveFeatures = canHaveFeatures;
        this.canHaveText = canHaveText;
        this.featureAttributes = featureAttributes;
    }

    public boolean isCanHaveFeatures() {
        return canHaveFeatures;
    }

    public boolean isCanHaveText() {
        return canHaveText;
    }

    public FeatureAttribute[] getFeatureAttributes() {
        return featureAttributes;
    }
}
