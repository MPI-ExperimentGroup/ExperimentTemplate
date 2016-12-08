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
    }

    public WizardMultiParticipantScreen(final String screenName,
            final String groupMembers,
            final String communicationChannels,
            final String textEntryPhaseRoles,
            final String textEntryPhaseText,
            final String textWaitPhaseRoles,
            final String textWaitPhaseText,
            final String gridWaitPhaseRoles,
            final String gridWaitPhaseText,
            final String responseGridPhaseRoles,
            final String responseGridPhaseText,
            final String mutualFeedbackPhaseRoles,
            final String mutualFeedbackPhaseText
    ) {
        super(WizardScreenEnum.WizardMultiParticipantScreen, screenName, screenName, screenName);
        this.wizardScreenData.setRandomiseStimuli(false);
        this.wizardScreenData.setStimuliCount(8);
        this.wizardScreenData.setStimulusMsDelay(0);
        this.wizardScreenData.setStimulusFreeText(false);
        this.wizardScreenData.setAllowHotkeyButtons(false);
//        this.wizardScreenData.setButtonLabelEventTag("");
        this.wizardScreenData.setCentreScreen(true);
        this.wizardScreenData.setGroupMembers(groupMembers);
        this.wizardScreenData.setGroupCommunicationChannels(communicationChannels);
        this.wizardScreenData.setGroupPhasesRoles(new String[]{textEntryPhaseRoles, textWaitPhaseRoles, gridWaitPhaseRoles, responseGridPhaseRoles, mutualFeedbackPhaseRoles});
        this.wizardScreenData.setScreenText(0, textEntryPhaseText);
        this.wizardScreenData.setScreenText(1, gridWaitPhaseText);
        this.wizardScreenData.setScreenText(2, textWaitPhaseText);
        this.wizardScreenData.setScreenText(3, responseGridPhaseText);
        this.wizardScreenData.setScreenText(4, mutualFeedbackPhaseText);
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
                tagSet.addAll(Arrays.asList(stimulusLine.split("\\.png:")));
                final Stimulus stimulus;
                if (stimulusLine.contains(".png")) {
//                    tagSet.addAll(Arrays.asList(stimulusLine.split("/")));
                    stimulus = new Stimulus(stimulusLine.replace(".png", ""), null, null, stimulusLine.split(":")[0], null, null, 0, tagSet, null);
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

    private PresenterFeature getScoreFeatures(final PresenterFeature aboveFeature, final PresenterFeature belowFeature) {
        final PresenterFeature scoreButtonFeature = new PresenterFeature(FeatureType.actionButton, "correct");
        scoreButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, "correct button");
        scoreButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "Z");
        scoreButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        final PresenterFeature scoreIncrement = new PresenterFeature(FeatureType.scoreIncrement, null);
        scoreIncrement.addFeatureAttributes(FeatureAttribute.scoreThreshold, "5");
        scoreIncrement.addFeatureAttributes(FeatureAttribute.scoreValue, "1");
        final PresenterFeature aboveThreshold = new PresenterFeature(FeatureType.aboveThreshold, null);
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "above threshold"));
        scoreIncrement.getPresenterFeatureList().add(aboveThreshold);
        final PresenterFeature belowThreshold = new PresenterFeature(FeatureType.belowThreshold, null);
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "below threshold"));
        scoreIncrement.getPresenterFeatureList().add(belowThreshold);
        scoreButtonFeature.getPresenterFeatureList().add(scoreIncrement);

        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));

        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        aboveThreshold.getPresenterFeatureList().add(aboveFeature);
        belowThreshold.getPresenterFeatureList().add(belowFeature);
        return scoreButtonFeature;
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
        groupNetwork.addFeatureAttributes(FeatureAttribute.groupMembers, storedWizardScreenData.getGroupMembers());
        //@todo: add groupRoleSequence
//        groupNetwork.addFeatureAttributes(FeatureAttribute.groupCommunicationChannels, "A,B,C,D,E,F,G,H"); // @todo: round 0
        groupNetwork.addFeatureAttributes(FeatureAttribute.groupCommunicationChannels, storedWizardScreenData.getGroupCommunicationChannels()); // @todo: round 1
//        groupNetwork.addFeatureAttributes(FeatureAttribute.groupCommunicationChannels, "B,C|D,E|F,G|H,A"); // @todo: round 2

        final PresenterFeature producerNetworkActivity0 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        producerNetworkActivity0.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "all"); // @ todo: this might not be needed if groupRoleSequence is added
//        selfNetworkActivity1.addFeatureAttributes(FeatureAttribute.requestedPhase, "0");
        final PresenterFeature producerNetworkActivity1 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        producerNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "all");
//        selfNetworkActivity2.addFeatureAttributes(FeatureAttribute.requestedPhase, "1");
        final PresenterFeature guesserNetworkActivity0 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        guesserNetworkActivity0.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "all");
        final PresenterFeature guesserNetworkActivity1 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        guesserNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "all");
        final PresenterFeature allNetworkActivity2 = new PresenterFeature(FeatureType.groupNetworkActivity, null);
        allNetworkActivity2.addFeatureAttributes(FeatureAttribute.groupMessageMatch, "all");
//        groupNetworkActivity2.addFeatureAttributes(FeatureAttribute.requestedPhase, "1");
        producerNetworkActivity0.addFeatureAttributes(FeatureAttribute.groupRole, storedWizardScreenData.getGroupPhasesRoles()[0]);
        guesserNetworkActivity0.addFeatureAttributes(FeatureAttribute.groupRole, storedWizardScreenData.getGroupPhasesRoles()[1]);
        producerNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupRole, storedWizardScreenData.getGroupPhasesRoles()[2]);
        guesserNetworkActivity1.addFeatureAttributes(FeatureAttribute.groupRole, storedWizardScreenData.getGroupPhasesRoles()[3]);
        allNetworkActivity2.addFeatureAttributes(FeatureAttribute.groupRole, storedWizardScreenData.getGroupPhasesRoles()[4]);

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.scoreLabel, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupChannelScoreLabel, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupScoreLabel, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberLabel, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberLabel, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberLabel, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberLabel, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberLabel, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberCodeLabel, null));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberCodeLabel, null));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberCodeLabel, null));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberCodeLabel, null));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.groupMemberCodeLabel, null));

        producerNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(0)));
        producerNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(1)));
        guesserNetworkActivity0.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(2)));
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(3)));
        allNetworkActivity2.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, storedWizardScreenData.getScreenText(4)));

//        if (storedWizardScreenData.getStimulusFreeText()) {
//            final PresenterFeature stimulusFreeTextFeature = new PresenterFeature(FeatureType.stimulusFreeText, storedWizardScreenData.getFreeTextValidationMessage());
//            stimulusFreeTextFeature.addFeatureAttributes(FeatureAttribute.validationRegex, storedWizardScreenData.getFreeTextValidationRegex());
//            groupNetworkActivity1.getPresenterFeatureList().add(stimulusFreeTextFeature);
//        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(storedWizardScreenData.getScreenTitle());

        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, storedWizardScreenData.getScreenTitle());
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(storedWizardScreenData.isRandomiseStimuli()));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "1");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatRandomWindow, "0");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(storedWizardScreenData.getStimuliCount()));

        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        producerNetworkActivity0.getPresenterFeatureList().add(imageFeature);
        // temporary testing features
        final PresenterFeature stimulusGrid = addStimuliGrid("guesserNetworkActivity1Grid", new PresenterFeature(FeatureType.htmlText, "responseCorrect"), new PresenterFeature(FeatureType.htmlText, "responseIncorrect"));
        guesserNetworkActivity1.getPresenterFeatureList().add(stimulusGrid);
        final PresenterFeature resultsGrid = addStimuliGrid("resultsGrid", new PresenterFeature(FeatureType.htmlText, "response not relevant"), new PresenterFeature(FeatureType.htmlText, "response not relevant"));
        allNetworkActivity2.getPresenterFeatureList().add(resultsGrid);
//        (true, false, new FeatureAttribute[]{, , }, true, false, FeatureType.Contitionals.hasCorrectIncorrect),
        final PresenterFeature correctButton = getScoreFeatures(addGroupMessageButton("corectNetworkActivity2", "Z"), addGroupMessageButton("corectNetworkActivity2", "Z"));
        final PresenterFeature incorrectButton = addGroupMessageButton("incorrect", "NUM_PERIOD");
//        final PresenterFeature nextStimulusFeature2 = addGroupMessageButton("groupNetworkActivity2", "Q");
        guesserNetworkActivity1.getPresenterFeatureList().add(correctButton);
        guesserNetworkActivity1.getPresenterFeatureList().add(incorrectButton);
        // @todo: is this inserted into the correct location
        guesserNetworkActivity1.getPresenterFeatureList().add(new PresenterFeature(FeatureType.submitGroupEvent, null));
//        incorrectButton.getPresenterFeatureList().add(new PresenterFeature(FeatureType.submitGroupEvent, null));

        final PresenterFeature nextStimulusFeature3 = addGroupMessageButton("nextStimulusFeature3", "Q");
        allNetworkActivity2.getPresenterFeatureList().add(nextStimulusFeature3);
        // temporary testing features
        final PresenterFeature groupNetworkActivitySelf1 = addGroupMessageButton("groupNetworkActivitySelf1", "W");
        // end testing features

        hasMoreStimulusFeature.getPresenterFeatureList().add(groupNetwork);
        presenterFeatureList.add(loadStimuliFeature);
        groupNetwork.getPresenterFeatureList().add(producerNetworkActivity0);
        groupNetwork.getPresenterFeatureList().add(producerNetworkActivity1);
        groupNetwork.getPresenterFeatureList().add(guesserNetworkActivity0);
        groupNetwork.getPresenterFeatureList().add(guesserNetworkActivity1);
        groupNetwork.getPresenterFeatureList().add(allNetworkActivity2);
//        hasMoreStimulusFeature.getPresenterFeatureList().add(allNetworkActivity2);
//        hasMoreStimulusFeature.getPresenterFeatureList().add(guesserNetworkActivity1);

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
        presenterFeature.getPresenterFeatureList().add(groupNetworkActivitySelf1);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(storedWizardScreenData.getPresenterScreen());
        return storedWizardScreenData.getPresenterScreen();
    }

    protected PresenterFeature addStimuliGrid(String eventTagString, final PresenterFeature responseCorrectFeatures, final PresenterFeature responseIncorrectFeatures) {
        //        final PresenterFeature guesserRatingButtons = new PresenterFeature(FeatureType.ratingButton, null);
//        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabels, "one,two,three");
//        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, "");
//        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.ratingLabelRight, "");
//        guesserRatingButtons.addFeatureAttributes(FeatureAttribute.eventTier, "1");
//        groupNetworkActivity2.getPresenterFeatureList().add(guesserRatingButtons);
// temporary testing features
//        final PresenterFeature nextStimulusFeature1 = new PresenterFeature(FeatureType.sendGroupMessageButton, "groupNetworkActivity1");
//        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.norepeat, "true");
//        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.eventTag, "groupNetworkActivity1");
//        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.hotKey, "Q");
//        nextStimulusFeature1.addFeatureAttributes(FeatureAttribute.requestedPhase, "2");
//        guesserNetworkActivity0.getPresenterFeatureList().add(nextStimulusFeature1);
        final PresenterFeature resultsGrid = new PresenterFeature(FeatureType.showStimulusGrid, null);
        resultsGrid.addFeatureAttributes(FeatureAttribute.columnCount, "5");
        resultsGrid.addFeatureAttributes(FeatureAttribute.imageWidth, "10%");
        resultsGrid.addFeatureAttributes(FeatureAttribute.eventTag, eventTagString);
        final PresenterFeature responseCorrect = new PresenterFeature(FeatureType.responseCorrect, null);
        responseCorrect.getPresenterFeatureList().add(responseCorrectFeatures);
        responseCorrect.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        resultsGrid.getPresenterFeatureList().add(responseCorrect);
        final PresenterFeature resultsIncorrect = new PresenterFeature(FeatureType.responseIncorrect, null);
        resultsIncorrect.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        resultsIncorrect.getPresenterFeatureList().add(responseIncorrectFeatures);
        resultsGrid.getPresenterFeatureList().add(resultsIncorrect);
        return resultsGrid;
    }

//    showStimulusGrid( true, false, new FeatureAttribute[]{columnCount, imageWidth, eventTag
//    }
//     , true, false, FeatureType.Contitionals.hasCorrectIncorrect
    protected PresenterFeature addGroupMessageButton(final String labelString, final String hotKey) {
        final PresenterFeature nextStimulusFeature2 = new PresenterFeature(FeatureType.sendGroupMessageButton, labelString);
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.eventTag, labelString);
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.hotKey, hotKey);
        nextStimulusFeature2.addFeatureAttributes(FeatureAttribute.incrementPhase, "1");
        return nextStimulusFeature2;
    }
}
