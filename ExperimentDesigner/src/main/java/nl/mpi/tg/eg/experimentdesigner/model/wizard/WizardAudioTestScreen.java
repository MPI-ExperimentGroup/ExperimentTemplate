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

    String audioPath;

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
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
    public PresenterScreen getPresenterScreen(final Experiment experiment, final boolean obfuscateScreenNames, final long displayOrder) {
        presenterScreen.setTitle((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : "AudioTest");
        presenterScreen.setMenuLabel("AudioTest");
        if (backWizardScreen != null) {
            presenterScreen.setBackPresenter(backWizardScreen.getPresenterScreen());
        }
        presenterScreen.setSelfPresenterTag("AudioTest");
        if (nextWizardScreen != null) {
            presenterScreen.setNextPresenter(nextWizardScreen.getPresenterScreen());
        }
        presenterScreen.setPresenterType(PresenterType.stimulus);
        presenterScreen.setDisplayOrder(displayOrder);

        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, screenText));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.audioButton, null);
        presenterFeature.addFeatureAttributes(FeatureAttribute.eventTag, "AudioTest");
        presenterFeature.addFeatureAttributes(FeatureAttribute.mp3, audioPath + ".mp3");
        presenterFeature.addFeatureAttributes(FeatureAttribute.ogg, audioPath + ".ogg");
        presenterFeature.addFeatureAttributes(FeatureAttribute.poster, audioPath + ".jpg");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, nextButton);
        presenterScreen.getPresenterFeatureList().add(actionButtonFeature);
        actionButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        return presenterScreen;
    }
}
