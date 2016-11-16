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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.groupMessageMatch;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;

/**
 * @since Nov 7, 2016 1:59:52 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardMultiParticipantScreen extends AbstractWizardScreen {

    public WizardMultiParticipantScreen() {
        super(WizardScreenEnum.WizardMultiParticipantScreen, "MultiParticipant", "MultiParticipant", "MultiParticipant");
        this.wizardScreenData.setRandomiseStimuli(false);
        this.wizardScreenData.setStimuliCount(8);
        this.wizardScreenData.setStimulusMsDelay(0);
        this.wizardScreenData.setStimulusFreeText(false);
        this.wizardScreenData.setAllowHotkeyButtons(false);
//        this.wizardScreenData.setButtonLabelEventTag("");
        this.wizardScreenData.setCentreScreen(true);
    }

//    public void setStimuliPath(String stimuliPath) {
//        this.stimuliPath = stimuliPath;
//    }
    public final void setStimuliSet(String[] stimuliSet) {
        if (this.wizardScreenData.getStimuli() == null) {
            this.wizardScreenData.setStimuli(new ArrayList<>());
        }
        final List<Stimulus> stimuliList = this.wizardScreenData.getStimuli();
        if (stimuliSet != null) {
            for (String stimulusLine : stimuliSet) {
                final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{this.wizardScreenData.getScreenTitle()}));
                final Stimulus stimulus;
                if (stimulusLine.endsWith(".png")) {
//                    tagSet.addAll(Arrays.asList(stimulusLine.split("/")));
                    stimulus = new Stimulus(stimulusLine.replace(".png", ""), null, null, stimulusLine, null, null, 0, tagSet, null);
                    stimuliList.add(stimulus);
                }
            }
        }
    }

    public void setStimulusFreeText(boolean stimulusFreeText, String freeTextValidationRegex, String freeTextValidationMessage) {
        this.wizardScreenData.setStimulusFreeText(stimulusFreeText);
        this.wizardScreenData.setFreeTextValidationRegex(freeTextValidationRegex);
        this.wizardScreenData.setFreeTextValidationMessage(freeTextValidationMessage);
    }

    final public void setButtonLabel(String buttonLabelEventTag) {
        this.wizardScreenData.setNextButton(new String[]{buttonLabelEventTag});
        this.wizardScreenData.setButtonLabelEventTag(buttonLabelEventTag);
    }

    @Override
    public PresenterScreen populatePresenterScreen(WizardScreenData storedWizardScreenData, Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        experiment.appendUniqueStimuli(storedWizardScreenData.getStimuli());
        super.populatePresenterScreen(storedWizardScreenData, experiment, obfuscateScreenNames, displayOrder);
        storedWizardScreenData.getPresenterScreen().setPresenterType(PresenterType.stimulus);
        List<PresenterFeature> presenterFeatureList = storedWizardScreenData.getPresenterScreen().getPresenterFeatureList();
        if (storedWizardScreenData.isCentreScreen()) {
            presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        }
        final PresenterFeature groupNetwork = new PresenterFeature(FeatureType.groupNetwork, null);
        groupNetwork.addFeatureAttributes(FeatureAttribute.groupMembers, "A,B,C,D,E,F,G,H");
        groupNetwork.addFeatureAttributes(FeatureAttribute.groupCommunicationChannels, "A,B|C,D|E,F,G,H");

        final PresenterFeature groupNetworkActivity1 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        groupNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupRole, "A,C,E:B,D,F:A,C,G:B,D,H");
        groupNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "self");
        final PresenterFeature groupNetworkActivity2 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        groupNetworkActivity2.addFeatureAttributes(FeatureAttribute.groupRole, "B,D,F,G,H:A,C,E,G,H:B,D,E,F,H:A,C,E,F,G");
        groupNetworkActivity2.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "other");

        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(storedWizardScreenData.getScreenTitle());

        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(storedWizardScreenData.isRandomiseStimuli()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, "0");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));

        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        groupNetworkActivity1.getPresenterFeatureList().add(imageFeature);
        // temporary testing features
        final PresenterFeature guesserRatingButtons = new PresenterFeature(FeatureType.ratingButton, null);
        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabels, "one,two,three");
        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, "");
        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabelRight, "");
        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        groupNetworkActivity2.getPresenterFeatureList().add(guesserRatingButtons);
        // temporary testing features
        final PresenterFeature nextStimulusFeature2 = new PresenterFeature(FeatureType.nextStimulusButton, "groupNetworkActivity2");
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.eventTag, "groupNetworkActivity2");
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.hotKey, "Q");
        groupNetworkActivity2.getPresenterFeatureList().add(nextStimulusFeature2);
        // temporary testing features
        final PresenterFeature nextStimulusFeature1 = new PresenterFeature(FeatureType.nextStimulusButton, "groupNetworkActivity1");
        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.eventTag, "groupNetworkActivity1");
        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.hotKey, "W");
        groupNetworkActivity1.getPresenterFeatureList().add(nextStimulusFeature1);
        // end testing features

        hasMoreStimulusFeature.getPresenterFeatureList().add(groupNetwork);
        presenterFeatureList.add(loadStimuliFeature);
        groupNetwork.getPresenterFeatureList().add(groupNetworkActivity1);
        groupNetwork.getPresenterFeatureList().add(groupNetworkActivity2);
//        hasMoreStimulusFeature.getPresenterFeatureList().add(groupNetworkActivity1);
//        hasMoreStimulusFeature.getPresenterFeatureList().add(groupNetworkActivity2);

        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "0");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(storedWizardScreenData.getStimulusMsDelay()));
        final PresenterFeature presenterFeature;
//        final String hotKeyString = "SPACE";
        presenterFeature = imageFeature;
        presenterFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        if (storedWizardScreenData.getStimulusFreeText()) {
            final PresenterFeature stimulusFreeTextFeature = new PresenterFeature(FeatureType.stimulusFreeText, storedWizardScreenData.getFreeTextValidationMessage());
            stimulusFreeTextFeature.addFeatureAttributes(FeatureAttribute.validationRegex, storedWizardScreenData.getFreeTextValidationRegex());
            presenterFeature.getPresenterFeatureList().add(stimulusFreeTextFeature);
        }
        if (storedWizardScreenData.getStimulusResponseOptions() != null) {
            final PresenterFeature ratingFooterButtonFeature = new PresenterFeature(FeatureType.ratingButton, null);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, storedWizardScreenData.getStimulusResponseOptions());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, storedWizardScreenData.getStimulusResponseLabelLeft());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, storedWizardScreenData.getStimulusResponseLabelRight());
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "NextStimulus" + storedWizardScreenData.getScreenTitle());
            ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
//        } else {
//            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionButton, storedWizardScreenData.getNextButton()[0]);
//            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getButtonLabelEventTag());
//            if (storedWizardScreenData.getAllowHotkeyButtons()) {
//                nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, hotKeyString);
//            }
//            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
//            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + storedWizardScreenData.getScreenTitle());
//            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
//            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }

//    showStimulusGrid( true, false, new FeatureAttribute[]{columnCount, imageWidth, eventTag
//    }
//     , true, false, FeatureType.Contitionals.hasCorrectIncorrect
}
