/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Jun 16, 2017 10:30:28 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardStimuliJsonMetadataScreen extends AbstractWizardScreen {

    public WizardStimuliJsonMetadataScreen(String screenTitle, String menuLabel, String screenTag) {
        super(WizardScreenEnum.WizardStimuliJsonMetadataScreen, screenTitle, menuLabel, screenTag);
    }

    public WizardStimuliJsonMetadataScreen() {
        super(WizardScreenEnum.WizardStimuliJsonMetadataScreen, "JsonMetadata", "JsonMetadata", "JsonMetadata");
    }

//    public WizardStimuliJsonMetadataScreen(final String screenTitle, final String screenTag, String dispalyText, final String saveButtonLabel, final String postText, final AbstractWizardScreen alternateNextScreen, final String alternateButtonLabel, final boolean sendData, final String on_Error_Text) {
//        super(WizardScreenEnum.WizardStimuliJsonMetadataScreen, screenTitle, screenTitle, screenTag);
//        this.wizardScreenData.setScreenText(0, dispalyText);
//        this.wizardScreenData.setNextButton(new String[]{saveButtonLabel, alternateButtonLabel});
//        this.wizardScreenData.setScreenText(1, postText);
//        this.wizardScreenData.setScreenBoolean(0, sendData);
//        this.wizardScreenData.setOn_Error_Text(on_Error_Text);
//        if (alternateNextScreen != null) {
//            this.wizardScreenData.getMenuWizardScreenData().add(0, alternateNextScreen.getWizardScreenData());
//        }
//    }
    @Override
    public String getScreenTextInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getScreenBooleanInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getScreenIntegerInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNextButtonInfo(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addStimuliMetadataField(String postName, String displayName) {
        wizardScreenData.getMetadataFields().add(new Metadata(postName, displayName, ".'{'3,'}'", "Please enter at least the characters.", false, null));
    }

    public void addStimuliBooleanMetadataField(String postName, String displayName) {
        wizardScreenData.getMetadataFields().add(new Metadata(postName, displayName, "true|false", "Please enter true or false.", false, null));
    }

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        if (storedWizardScreenData.getScreenText(0) != null) {
            storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(0)));
        }
        for (Metadata metadata : storedWizardScreenData.getMetadataFields()) {
            experiment.getMetadata().add(metadata);
            // todo: this metadataFieldConnection use needs to be replaced with wizard parameters
            final PresenterFeature metadataField = new PresenterFeature(("connectionString".equals(metadata.getPostName())) ? FeatureType.metadataFieldConnection : FeatureType.stimulusMetadataField, null);
            if ("connectionString".equals(metadata.getPostName())) {
                metadataField.addFeatureAttributes(FeatureAttribute.linkedFieldName, "workerId");
            }
            metadataField.addFeatureAttributes(FeatureAttribute.fieldName, metadata.getPostName());
            storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(metadataField);
        }
//        if (storedWizardScreenData.getMetadataFields().isEmpty()) {
//            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMetadataFields, null));
//        }
//        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, storedWizardScreenData.getNextButton()[0]);
//        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, Boolean.toString(storedWizardScreenData.getScreenBoolean(0)));
//        saveMetadataButton.addFeatureAttributes(FeatureAttribute.networkErrorMessage, storedWizardScreenData.getOn_Error_Text());
//        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
//        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
//        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
//        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.autoNextPresenter, null);
//        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
//        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
//        storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(saveMetadataButton);
        if (storedWizardScreenData.getScreenText(1) != null && storedWizardScreenData.getMenuWizardScreenData().size() > 0) {
            storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
            if (storedWizardScreenData.getScreenText(1) != null) {
                storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(1)));
            }
            if (storedWizardScreenData.getMenuWizardScreenData().size() > 0) {
                final PresenterFeature alternateNextFeature = new PresenterFeature(FeatureType.targetButton, storedWizardScreenData.getNextButton()[1]);
                alternateNextFeature.addFeatureAttributes(FeatureAttribute.target, storedWizardScreenData.getMenuWizardScreenData().get(0).getScreenTag());
                storedWizardScreenData.getPresenterScreen().getPresenterFeatureList().add(alternateNextFeature);
            }
        }
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }
}
