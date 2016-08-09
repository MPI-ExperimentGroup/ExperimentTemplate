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
        super("AudioTest", "AudioTest", "AudioTest");
    }

    public WizardAudioTestScreen(String screenName, String pageText, String buttonLabel, String audioPath) {
        super(screenName, screenName, screenName);
        this.setScreenText(pageText);
        this.setNextButton(buttonLabel);
        this.wizardScreenData.setStimuliSet(new String[]{audioPath});
    }

    public String getAudioPath() {
        return this.wizardScreenData.getStimuliSet()[0];
    }

    public void setAudioPath(String audioPath) {
        this.wizardScreenData.setStimuliSet(new String[]{audioPath});
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
    public PresenterScreen populatePresenterScreen(final Experiment experiment, final boolean obfuscateScreenNames, final long displayOrder) {
        super.populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
//        populatePresenterScreen(experiment, obfuscateScreenNames, displayOrder);
        presenterScreen.setPresenterType(PresenterType.stimulus);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, getScreenText()));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.audioButton, null);
        presenterFeature.addFeatureAttributes(FeatureAttribute.eventTag, "AudioTest");
        presenterFeature.addFeatureAttributes(FeatureAttribute.mp3, getAudioPath() + ".mp3");
        presenterFeature.addFeatureAttributes(FeatureAttribute.ogg, getAudioPath() + ".ogg");
        presenterFeature.addFeatureAttributes(FeatureAttribute.poster, getAudioPath() + ".jpg");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, getNextButton());
        presenterScreen.getPresenterFeatureList().add(actionButtonFeature);
        actionButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        return presenterScreen;
    }
}
