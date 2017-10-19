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
 * @since May 2, 2016 5:05:16 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardAudioTestScreen extends AbstractWizardScreen {

    public WizardAudioTestScreen() {
        super(WizardScreenEnum.WizardAudioTestScreen, "AudioTest", "AudioTest", "AudioTest");
        this.wizardScreenData.setScreenText(1, "");
        this.wizardScreenData.setScreenText(2, "");
        this.wizardScreenData.setScreenText(3, null);
        this.wizardScreenData.setScreenBoolean(0, false);
    }

    public WizardAudioTestScreen(String screenName, String pageText, String buttonLabel, String audioPath) {
        super(WizardScreenEnum.WizardAudioTestScreen, screenName, screenName, screenName);
        this.setScreenText(pageText);
        this.wizardScreenData.setScreenText(1, "");
        this.wizardScreenData.setScreenText(2, "");
        this.wizardScreenData.setScreenText(3, null);
        this.wizardScreenData.setScreenBoolean(0, false);
        this.setNextButton(buttonLabel);
        this.wizardScreenData.setScreenMediaPath(audioPath);
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        return new String[]{"Auto Play"}[index];
    }

    @Override
    public String getScreenTextInfo(int index) {
        return new String[]{"Audio Test Instructions", "Background Image", "AudioHotKey", "NextHotKey"}[index];
    }

    @Override
    public String getNextButtonInfo(int index) {
        return new String[]{"Audio Confirmation Button Label"}[index];
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        return new String[]{}[index];
    }
//    public String getAudioPath() {
//        return this.wizardScreenData
//    }

    public void setAudioPath(String audioPath) {
        this.wizardScreenData.setScreenMediaPath(audioPath);
    }

    private String getBackgroundImage(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(1);
    }

    public void setBackgroundImage(String backgroundImage) {
        this.wizardScreenData.setScreenText(1, backgroundImage);
    }

    private String getAudioHotKey(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(2);
    }

    public void setAudioHotKey(String hotKey) {
        this.wizardScreenData.setScreenText(2, hotKey);
    }

    private String getNextHotKey(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenText(3);
    }

    public void setNextHotKey(String hotKey) {
        this.wizardScreenData.setScreenText(3, hotKey);
    }

    private boolean getAutoPlay(WizardScreenData storedWizardScreenData) {
        return storedWizardScreenData.getScreenBoolean(0);
    }

    public void setAutoPlay(boolean autoPlay) {
        this.wizardScreenData.setScreenBoolean(0, autoPlay);
    }

//    String[] fieldNames = new String[]{"audioTestScreenText", "audioWorksButtonText", "testAudioPath"};
//    @Override
//    public String[] getFieldNames() {
//        return fieldNames;
//    }
//
//    @Override
//    public void setFieldValue(String fieldName, String fieldValue) {
//        namedFields.put(fieldName, fieldValue);
//    }
//
//    @Override
//    public String getFieldValue(String fieldName) {
//        return namedFields.get(fieldName);
//    }
    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, final Experiment experiment, final boolean obfuscateScreenNames, final long displayOrder) {
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
//        populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        final String backgroundImage = getBackgroundImage(storedWizardScreenData);
        if (backgroundImage != null && !backgroundImage.isEmpty()) {
            final PresenterFeature backgoundFeature = new PresenterFeature(FeatureType.backgroundImage, null);
            backgoundFeature.addFeatureAttributes(FeatureAttribute.msToNext, "1000");
            backgoundFeature.addFeatureAttributes(FeatureAttribute.styleName, "");
            backgoundFeature.addFeatureAttributes(FeatureAttribute.src, backgroundImage);
            storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(backgoundFeature);
        }
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(0)));
        final PresenterFeature presenterFeature;
        presenterFeature = new PresenterFeature(FeatureType.audioButton, null);
        presenterFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenMediaPath());
        presenterFeature.addFeatureAttributes(FeatureAttribute.src, storedWizardScreenData.getScreenMediaPath());
        presenterFeature.addFeatureAttributes(FeatureAttribute.poster, storedWizardScreenData.getScreenMediaPath() + ".jpg");
        presenterFeature.addFeatureAttributes(FeatureAttribute.autoPlay, Boolean.toString(getAutoPlay(storedWizardScreenData)));
        presenterFeature.addFeatureAttributes(FeatureAttribute.hotKey, getAudioHotKey(storedWizardScreenData));
        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, storedWizardScreenData.getNextButton()[0]);
        if (getNextHotKey(storedWizardScreenData) != null) {
            actionButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, getNextHotKey(storedWizardScreenData));
        }
        presenterFeature.getPresenterFeatureList().add(actionButtonFeature);
        actionButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        return storedWizardScreenData.getPresenterScreen();
    }
}
