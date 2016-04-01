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
package nl.mpi.tg.eg.experimentdesigner.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import nl.mpi.tg.eg.experimentdesigner.dao.ExperimentRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PublishEventRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static nl.mpi.tg.eg.experimentdesigner.util.DefaultExperiments.getDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @since Feb 22, 2016 4:23:36 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class WizardController {

    private static final Logger LOG = Logger.getLogger(StimulusController.class.getName());
    @Autowired
    PresenterScreenRepository presenterScreenRepository;
    @Autowired
    PublishEventRepository eventRepository;
    @Autowired
    PresenterFeatureRepository presenterFeatureRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    ExperimentRepository experimentRepository;

    @RequestMapping(value = "/experiments/wizard/create", method = RequestMethod.POST)
    public String create(final HttpServletRequest req, @ModelAttribute WizardData wizardData) {
        final Experiment experiment = getExperiment(wizardData);
        experimentRepository.save(experiment);
        return "redirect:/experiment/" + experiment.getId();
    }

    public Experiment getExperiment(WizardData wizardData) {
        final Experiment experiment = getExperiment(wizardData.getAppName().replaceAll("[^A-Za-z0-9]", "_"), wizardData.getAppName());
//        PresenterScreen previousScreen = null;
//        PresenterScreen nextScreen = null;
        PresenterScreen editUserScreen = null;
        PresenterScreen userSelectMenu = null;
        PresenterScreen agreementScreen = null;
        PresenterScreen audioTestScreen = null;
        PresenterScreen practiceStimulusScreen = null;
        PresenterScreen stimulusScreen = null;
        PresenterScreen completionScreen = null;
        PresenterScreen autoMenu = null;
//        PresenterScreen debugScreen = addDebugScreen(experiment, null, 10);
//    private String agreementScreenText = "";
//    private String disagreementScreenText = "";
        if (wizardData.isUserSelectScreen()) {
            userSelectMenu = addUserSelectMenu(experiment, null, null, 1);
        }
        if (wizardData.isAgreementScreen()) {
            agreementScreen = addAgreementScreen(experiment, null, "EditUser", 2, wizardData.getAgreementScreenText());
            if (userSelectMenu != null) {
                userSelectMenu.setNextPresenter(agreementScreen);
            }
        }
        if (wizardData.isMetadataScreen()) {
//            addMetadata(experiment, wizardData);
            editUserScreen = addEditUserScreen(experiment, null, null, 3, wizardData);
            if (agreementScreen != null) {
                agreementScreen.setNextPresenter(editUserScreen);
            }
        }
        if (wizardData.isAudioTestScreen()) {
//            addMetadata(experiment, wizardData);
            audioTestScreen = addAudioTestScreen(experiment, null, null, 4, wizardData.getTestAudioPath(), wizardData.getAudioTestScreenText());
            if (editUserScreen != null) {
                editUserScreen.setNextPresenter(audioTestScreen);
            }
        }
        if (wizardData.getPracticeStimuliSet() != null) {
//            addMetadata(experiment, wizardData);
            practiceStimulusScreen = addRandomTextScreen(experiment, null, 5, "PracticeStimulusScreen", wizardData.getPracticeStimuliSet(), wizardData.getPracticeStimuliRandomTags(), wizardData.getPracticeStimuliCount(), wizardData.getPracticeStimulusCodeMatch(), wizardData.getPracticeStimulusCodeMsDelay(), wizardData.getPracticeStimulusCodeFormat(), wizardData.getPracticeStimulusResponseOptions(), wizardData.getPracticeStimulusResponseLabelLeft(), wizardData.getPracticeStimulusResponseLabelRight());
            if (audioTestScreen != null) {
                audioTestScreen.setNextPresenter(practiceStimulusScreen);
            }
        }
        if (wizardData.getStimuliSet() != null) {
//            addMetadata(experiment, wizardData);
            stimulusScreen = addRandomTextScreen(experiment, null, 5, "StimulusScreen", wizardData.getStimuliSet(), wizardData.getStimuliRandomTags(), wizardData.getStimuliCount(), wizardData.getStimulusCodeMatch(), wizardData.getStimulusCodeMsDelay(), wizardData.getStimulusCodeFormat(), wizardData.getStimulusResponseOptions(), wizardData.getStimulusResponseLabelLeft(), wizardData.getStimulusResponseLabelRight());
            if (practiceStimulusScreen != null) {
                practiceStimulusScreen.setNextPresenter(stimulusScreen);
            }
        }
        if (wizardData.isCompletionScreen()) {
            completionScreen = addCompletionScreen(experiment, null, null, 6, wizardData.getCompletionText(), "Restart");
            if (stimulusScreen != null) {
                stimulusScreen.setNextPresenter(completionScreen);
            }
        }
//    private boolean practiceScreen = false;
//    private String practiceStimuliPath = "";
//    private boolean stimuliScreen = false;
        if (wizardData.isMenuScreen()) {
            autoMenu = addAutoMenu(experiment, 10);
            if (completionScreen != null) {
                completionScreen.setNextPresenter(autoMenu);
            }
        }
//    private String stimuliPath = "";
//    private boolean completionScreen = false;
//    private String completionText = "";
        return experiment;
    }

    public Experiment getExperiment(String appNameInternal, String appName) {
        Experiment experiment = getDefault();
        experiment.setAppNameDisplay(appName);
        experiment.setAppNameInternal(appNameInternal);
        return experiment;
    }

    public void addMetadata(Experiment experiment) {
        final Metadata metadata = new Metadata("workerId", "Speaker name *", ".'{'3,'}'", "Please enter at least three letters.", false, null);
        experiment.getMetadata().add(metadata);
    }

    public PresenterScreen addAutoMenu(final Experiment experiment, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Auto Menu", "Menu", null, "AutoMenu", null, PresenterType.menu, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addDebugScreen(final Experiment experiment, PresenterScreen autoMenuPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Debug Screen", "Debug Screen", autoMenuPresenter, "DebugScreen", null, PresenterType.debug, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.versionData, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseLocalStorageButton, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.localStorageData, null));
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addWelcomeScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Welcome", "Welcome", backPresenter, "Welcome", nextPresenter, PresenterType.menu, displayOrder);
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.menuItem, "Instructions");
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, "Instructions");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.menuItem, "Go directly to program");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, "Start");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addAgreementScreen(final Experiment experiment, final PresenterScreen backPresenter, final String nextPresenter, long displayOrder, String screenText) {
        final PresenterScreen presenterScreen = new PresenterScreen("Agreement", "Agreement", backPresenter, "Agreement", null, PresenterType.metadata, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, screenText));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.targetButton, "Agree");
        presenterFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter);
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addAudioTestScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder, String testAudioPath, final String audioTestScreenText) {
        final PresenterScreen presenterScreen = new PresenterScreen("AudioTest", "AudioTest", backPresenter, "AudioTest", nextPresenter, PresenterType.stimulus, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, audioTestScreenText));
        final PresenterFeature presenterFeature = new PresenterFeature(FeatureType.audioButton, null);
        presenterFeature.addFeatureAttributes(FeatureAttribute.eventTag, "AudioTest");
        presenterFeature.addFeatureAttributes(FeatureAttribute.mp3, testAudioPath + ".mp3");
        presenterFeature.addFeatureAttributes(FeatureAttribute.ogg, testAudioPath + ".ogg");
        presenterFeature.addFeatureAttributes(FeatureAttribute.poster, testAudioPath + ".jpg");
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, "Audio Works");
        presenterScreen.getPresenterFeatureList().add(actionButtonFeature);
        actionButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        return presenterScreen;
    }

    public PresenterScreen addUserSelectMenu(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Select User", "Select User", backPresenter, "SelectUser", nextPresenter, PresenterType.metadata, displayOrder);
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.selectUserMenu, null);
        presenterScreen.getPresenterFeatureList().add(selectUserFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addEditUserScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        return addEditUserScreen(experiment, backPresenter, nextPresenter, displayOrder, null);
    }

    public PresenterScreen addEditUserScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder, WizardData wizardData) {
        final PresenterScreen presenterScreen = new PresenterScreen("Edit User", "Edit User", backPresenter, "EditUser", nextPresenter, PresenterType.metadata, displayOrder);
        if (wizardData != null) {
            if (!wizardData.getMetadataScreenText().isEmpty()) {
                presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, wizardData.getMetadataScreenText()));
            }
            insertMetadataField(experiment, new Metadata("workerId", "Worker ID", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
            if (wizardData.isSpeakerNameField()) {
                insertMetadataField(experiment, new Metadata("speakerName", "Speaker name *", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
            }
            if (wizardData.isFirstNameField()) {
                insertMetadataField(experiment, new Metadata("firstName", "First name", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
            }
            if (wizardData.isLastNameField()) {
                insertMetadataField(experiment, new Metadata("lastName", "Last name", ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
            }
            if (wizardData.isAgeField()) {
                insertMetadataField(experiment, new Metadata("age", "Age", "[0-9]+", "Please enter a valid age.", false, null), presenterScreen);
            }
            if (wizardData.isGenderField()) {
                insertMetadataField(experiment, new Metadata("gender", "Gender", "|male|female|other", null, false, null), presenterScreen);
            }
            if (wizardData.isEmailAddressField()) {
                insertMetadataField(experiment, new Metadata("emailAddress", "Email address", "^[^@]+@[^@]+$", "Please enter a valid email address.", false, null), presenterScreen);
            }
            if (!wizardData.getCustomTextField().isEmpty()) {
                insertMetadataField(experiment, new Metadata("customTextField1", wizardData.getCustomTextField(), ".'{'3,'}'", "Please enter at least three letters.", false, null), presenterScreen);
            }
            if (!wizardData.getOptionCheckBox1().isEmpty()) {
                insertMetadataField(experiment, new Metadata("optionCheckBox1", wizardData.getOptionCheckBox1(), "true|false", "Please enter true or false.", false, null), presenterScreen);
            }
            if (!wizardData.getOptionCheckBox2().isEmpty()) {
                insertMetadataField(experiment, new Metadata("optionCheckBox2", wizardData.getOptionCheckBox2(), "true|false", "Please enter true or false.", false, null), presenterScreen);
            }
            if (!wizardData.getMandatoryCheckBox().isEmpty()) {
                insertMetadataField(experiment, new Metadata("mandatoryCheckBox", wizardData.getMandatoryCheckBox(), "true", "Please agree to continue.", false, null), presenterScreen);
            }
        } else {
            presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMetadataFields, null));
        }
        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, "Save Metadata");
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, "false");
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        onErrorFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "on Error Feature"));
        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.autoNextPresenter, null);
        onSuccessFeature.getPresenterFeatureList().add(menuButtonFeature);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        presenterScreen.getPresenterFeatureList().add(saveMetadataButton);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public void insertMetadataField(final Experiment experiment, final String label, final PresenterScreen presenterScreen) {
        insertMetadataField(experiment, new Metadata(label.replaceAll("[^A-Za-z0-9]", "_"), label, "true|false", "Please enter true or false.", false, null), presenterScreen);
    }

    public void insertMetadataField(final Experiment experiment, final Metadata metadata, final PresenterScreen presenterScreen) {
        experiment.getMetadata().add(metadata);
        final PresenterFeature metadataField = new PresenterFeature(FeatureType.metadataField, null);
        metadataField.addFeatureAttributes(FeatureAttribute.fieldName, metadata.getPostName());
        presenterScreen.getPresenterFeatureList().add(metadataField);
    }

    public PresenterScreen addRandomTextScreen(final Experiment experiment, final PresenterScreen backPresenter, long displayOrder, String screenName, String[] screenTextArray, int maxStimuli, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight) {
        return addRandomTextScreen(experiment, backPresenter, displayOrder, screenName, screenTextArray, null, maxStimuli, null, 0, null, responseOptions, responseOptionsLabelLeft, responseOptionsLabelRight);
    }

    public PresenterScreen addRandomTextScreen(final Experiment experiment, final PresenterScreen backPresenter, long displayOrder, String screenName, String[] screenTextArray, String[] randomStimuliTags, int maxStimuli, String stimulusCodeMatch, int codeStimulusDelay, String codeFormat, String responseOptions, String responseOptionsLabelLeft, String responseOptionsLabelRight) {
        final List<Stimulus> stimuliList = experiment.getStimuli();
        final Pattern stimulusCodePattern = (stimulusCodeMatch != null) ? Pattern.compile(stimulusCodeMatch) : null;
        for (String screenText : screenTextArray) {
            final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{screenName}));
            final Stimulus stimulus;
            if (stimulusCodePattern != null) {
                System.out.println("stimulusCodeMatch:" + stimulusCodeMatch);
                Matcher matcher = stimulusCodePattern.matcher(screenText);
                final String codeString = (matcher.find()) ? matcher.group(1) : null;
                System.out.println("codeString: " + codeString);
                final String baseFileName = screenText.replaceAll(BASE_FILE_REGEX, "");
                tagSet.addAll(Arrays.asList(baseFileName.split("/")));
                stimulus = new Stimulus(baseFileName, null, null, null, screenText, null, codeString, 0, tagSet);
            } else if (screenText.endsWith(".png")) {
                tagSet.addAll(Arrays.asList(screenText.split("/")));
                stimulus = new Stimulus(screenText.replace(".png", ""), null, null, null, screenText, null, screenText.replace(".png", ""), 0, tagSet);
            } else {
                final String[] splitScreenText = screenText.split(":", 2);
                tagSet.addAll(Arrays.asList(splitScreenText[0].split("/")));
                stimulus = new Stimulus(null, null, null, null, null, splitScreenText[1].replace("\n", "<br/>"), splitScreenText[0].replace(" ", "_"), 0, tagSet);
            }
            stimuliList.add(stimulus);
        }

        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName, null, PresenterType.stimulus, displayOrder);

        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadStimulus, null);
        loadStimuliFeature.addStimulusTag(screenName);
        if (randomStimuliTags != null) {
            for (String randomTag : randomStimuliTags) {
                loadStimuliFeature.addRandomTag(randomTag);
            }
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "true");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(maxStimuli));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);

        hasMoreStimulusFeature.getPresenterFeatureList().add(imageFeature);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, "80");
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(0));
        final PresenterFeature presenterFeature;
        if (codeFormat != null) {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionFooterButton, "spacebar");
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, "spacebar");
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
            nextButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            final PresenterFeature pauseFeature = new PresenterFeature(FeatureType.pause, null);
            pauseFeature.addFeatureAttributes(FeatureAttribute.msToNext, Integer.toString(codeStimulusDelay));
            nextButtonFeature.getPresenterFeatureList().add(pauseFeature);
            final PresenterFeature stimulusCodeAudio = new PresenterFeature(FeatureType.stimulusCodeAudio, null);
            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.codeFormat, codeFormat);
            stimulusCodeAudio.addFeatureAttributes(FeatureAttribute.msToNext, "0");
            pauseFeature.getPresenterFeatureList().add(stimulusCodeAudio);
            imageFeature.getPresenterFeatureList().add(nextButtonFeature);
            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "Het antwoord is:"));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
//            stimulusCodeAudio.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, "<style=\"text-align: left;\">zeer waarschijnlijk negatief</style><style=\"text-align: right;\">zeer waarschijnlijk positief</style>"));
            presenterFeature = stimulusCodeAudio;
        } else {
            presenterFeature = imageFeature;
        }

        if (responseOptions != null) {
            final PresenterFeature ratingFooterButtonFeature = new PresenterFeature(FeatureType.ratingFooterButton, null);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, responseOptions);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelLeft, responseOptionsLabelLeft);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.ratingLabelRight, responseOptionsLabelRight);
            ratingFooterButtonFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + screenName);
            ratingFooterButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(ratingFooterButtonFeature);
        } else {
            final PresenterFeature nextButtonFeature = new PresenterFeature(FeatureType.actionFooterButton, "spacebar");
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.eventTag, "spacebar");
            nextButtonFeature.addFeatureAttributes(FeatureAttribute.hotKey, "SPACE");
            final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
            nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulus" + screenName);
            nextButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
            presenterFeature.getPresenterFeatureList().add(nextButtonFeature);
        }
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
    private static final String BASE_FILE_REGEX = "\\.[a-zA-Z]+$";

    public PresenterScreen createMetadataScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, final String[] metadataStrings, long displayOrder) {
        //    Metadata is collected in the spoken form (audio recording) with screen prompts for each item in metadataStrings:
        final List<Stimulus> stimuliList = experiment.getStimuli();
        final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{"metadata"}));
        for (String metadataString : metadataStrings) {
            final Stimulus stimulus = new Stimulus(null, null, null, null, null, metadataString, metadataString.replace(" ", "_"), 0, tagSet);
            stimuliList.add(stimulus);
        }
//        experiment.setStimuli(stimuliList);
        final String screenName = "Metadata";
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, "MetadataScreen", null, PresenterType.stimulus, displayOrder);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag("metadata");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "false");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);

        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, "false");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startTagFeature);
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, "next");
        final PresenterFeature endAudioRecorderTagFeature = new PresenterFeature(FeatureType.endAudioRecorderTag, null);
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTag, "");
        actionButtonFeature.getPresenterFeatureList().add(endAudioRecorderTagFeature);
        final PresenterFeature nextStimulusFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        nextStimulusFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextStimulusMetadata");
        actionButtonFeature.getPresenterFeatureList().add(nextStimulusFeature);
        hasMoreStimulusFeature.getPresenterFeatureList().add(actionButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);

        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
//        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stopAudioRecorder, null));
//        endOfStimulusFeature.getPresenterFeatureList().add(autoNextPresenter);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "end of stimuli"));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, nextPresenter.getSelfPresenterTag());
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterFeature addImageFeature(PresenterFeature parentFeature, StimuliSubAction imageFeatureValues) {
        final PresenterFeature startTagFeature = new PresenterFeature(FeatureType.startAudioRecorderTag, null);
        startTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        parentFeature.getPresenterFeatureList().add(startTagFeature);
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.centrePage, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
        parentFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        final PresenterFeature imageFeature = new PresenterFeature(FeatureType.stimulusImage, null);
        imageFeature.addFeatureAttributes(FeatureAttribute.maxHeight, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.maxWidth, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.percentOfPage, imageFeatureValues.getPercentOfPage());
        imageFeature.addFeatureAttributes(FeatureAttribute.msToNext, "0");
        parentFeature.getPresenterFeatureList().add(imageFeature);
        final PresenterFeature actionFeature;
        if (imageFeatureValues.getButtons().length == 1) {
            imageFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, imageFeatureValues.getLabel()));
            actionFeature = new PresenterFeature(FeatureType.actionButton, imageFeatureValues.getButtons()[0]);
            final PresenterFeature endAudioRecorderTagFeature = new PresenterFeature(FeatureType.endAudioRecorderTag, null);
            endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
            endAudioRecorderTagFeature.addFeatureAttributes(FeatureAttribute.eventTag, imageFeatureValues.getLabel());
            actionFeature.getPresenterFeatureList().add(endAudioRecorderTagFeature);
        } else {
            actionFeature = new PresenterFeature(FeatureType.ratingFooterButton, null);
            actionFeature.addFeatureAttributes(FeatureAttribute.ratingLabels, String.join(",", imageFeatureValues.getButtons()));
            actionFeature.addFeatureAttributes(FeatureAttribute.eventTier, "1");
        }
        actionFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.clearPage, null));
        imageFeature.getPresenterFeatureList().add(actionFeature);
        return actionFeature;
    }

    public PresenterScreen addWelcomeMenu(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder, final String startNewText, final String resumeoldText) {
        final PresenterScreen presenterScreen = new PresenterScreen("Start", "Start", backPresenter, "Start", nextPresenter, PresenterType.metadata, displayOrder);
        final PresenterFeature userCheckFeature = new PresenterFeature(FeatureType.existingUserCheck, null);
        final PresenterFeature multipleUsersFeature = new PresenterFeature(FeatureType.multipleUsers, null);
        userCheckFeature.getPresenterFeatureList().add(multipleUsersFeature);
        final PresenterFeature singleUserFeature = new PresenterFeature(FeatureType.singleUser, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        singleUserFeature.getPresenterFeatureList().add(autoNextPresenter);
        userCheckFeature.getPresenterFeatureList().add(singleUserFeature);
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, startNewText));
        final PresenterFeature createUserFeature = new PresenterFeature(FeatureType.createUserButton, "New Interview.");
        createUserFeature.addFeatureAttributes(FeatureAttribute.target, "EditUser");
        multipleUsersFeature.getPresenterFeatureList().add(createUserFeature);
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        multipleUsersFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, resumeoldText));
        final PresenterFeature selectUserFeature = new PresenterFeature(FeatureType.targetButton, "Resume Interview");
        selectUserFeature.addFeatureAttributes(FeatureAttribute.target, "SelectUser");
        multipleUsersFeature.getPresenterFeatureList().add(selectUserFeature);
        presenterScreen.getPresenterFeatureList().add(userCheckFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addInstructionsScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder, String instructionsText) {
        final PresenterScreen presenterScreen = new PresenterScreen("Instructions", "Instructions", backPresenter, "Instructions", null, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, instructionsText));
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.targetButton, "Go directly to program");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addTextScreen(final Experiment experiment, final PresenterScreen backPresenter, final String screenName, final PresenterScreen nextPresenter, long displayOrder, String instructionsText) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName, nextPresenter, PresenterType.text, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, instructionsText));
//        final PresenterFeature actionButtonFeature = new PresenterFeature(FeatureType.actionButton, "Continue");
//        presenterScreen.getPresenterFeatureList().add(actionButtonFeature);
//        actionButtonFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addCompletionScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder, String completedText, String eraseUsersDataButtonlabel) {
        final PresenterScreen presenterScreen = new PresenterScreen("Completion", "Completion", backPresenter, "Completion", nextPresenter, PresenterType.transmission, displayOrder);
        final PresenterFeature sendAllDataFeature = new PresenterFeature(FeatureType.sendAllData, null);
        presenterScreen.getPresenterFeatureList().add(sendAllDataFeature);

        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        sendAllDataFeature.getPresenterFeatureList().add(onSuccessFeature);
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.htmlText, completedText));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.addPadding, null));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.generateCompletionCode, null));
        onSuccessFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseUsersDataButton, eraseUsersDataButtonlabel));

        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        sendAllDataFeature.getPresenterFeatureList().add(onErrorFeature);
        onErrorFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "Could not contact the server, please check your internet connection and try again."));
        final PresenterFeature retryFeature = new PresenterFeature(FeatureType.targetButton, "Retry");
        onErrorFeature.getPresenterFeatureList().add(retryFeature);
        retryFeature.addFeatureAttributes(FeatureAttribute.target, "Completion");

        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen createStimulusScreen(final Experiment experiment, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, final String stimulusTagArray[], final StimuliSubAction[] featureValuesArray, final boolean randomiseStimuli, final int maxStimuli, boolean filePerStimulus, long displayOrder) {
        String screenName = "";
        final List<Stimulus> stimuliList = experiment.getStimuli();
        for (final String stimulusTag : stimulusTagArray) {
            stimuliList.add(new Stimulus(stimulusTag, null, null, null, stimulusTag, stimulusTag, stimulusTag, 0, new HashSet<>(Arrays.asList(new String[]{stimulusTag}))));
            screenName += stimulusTag;
        }
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName + "Screen", null, PresenterType.stimulus, displayOrder);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "This screen will show " + maxStimuli + " stimuli in random order from the directories:"));
//        for (final String stimulusTag : stimulusTagArray) {
//            presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "MPI_STIMULI/" + stimulusTag));
//        }
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadSdCardStimulus, null);
        for (final String stimulusTag : stimulusTagArray) {
            loadStimuliFeature.addStimulusTag(stimulusTag);
        }
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.maxStimuli, Integer.toString(maxStimuli));
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, Boolean.toString(randomiseStimuli));
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
        // todo: add more reverter tags as required
        final PresenterFeature startRecorderFeature = new PresenterFeature(FeatureType.startAudioRecorder, null);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.wavFormat, "true");
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        startRecorderFeature.addFeatureAttributes(FeatureAttribute.filePerStimulus, (filePerStimulus) ? "true" : "false");
        hasMoreStimulusFeature.getPresenterFeatureList().add(startRecorderFeature);

        PresenterFeature previousPresenterFeature = hasMoreStimulusFeature;
        for (StimuliSubAction imageFeatureValues : featureValuesArray) {
            final PresenterFeature lanwisImage = addImageFeature(previousPresenterFeature, imageFeatureValues);
            previousPresenterFeature = lanwisImage;
        }
        final PresenterFeature autoNextFeature = new PresenterFeature(FeatureType.nextStimulus, null);
        autoNextFeature.addFeatureAttributes(FeatureAttribute.eventTag, "nextImage");
        autoNextFeature.addFeatureAttributes(FeatureAttribute.norepeat, "true");
        previousPresenterFeature.getPresenterFeatureList().add(autoNextFeature);
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "end of stimuli"));
        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, nextPresenter.getSelfPresenterTag());
        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, nextPresenter.getSelfPresenterTag());
        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
