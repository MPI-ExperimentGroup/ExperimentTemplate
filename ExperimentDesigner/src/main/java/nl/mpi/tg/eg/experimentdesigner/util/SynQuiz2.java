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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.link;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.maxHeight;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.maxWidth;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.percentOfPage;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.src;
import static nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute.target;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.AbstractWizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScreen;

/**
 * @since Jan 18, 2016 11:20:47 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SynQuiz2 {

    private final WizardController wizardController = new WizardController();
    private final String imageSize = "80";

    public Experiment getExperiment() {
        Experiment experiment = wizardController.getExperiment("SynQuiz2", "SynQuiz2", true);
        experiment.setStimuli(new SynQuiz2().createStimuli());
        new SynQuiz2().create(experiment, experiment.getPresenterScreen());
        return experiment;
    }

//    done         The "tell us about your synaesthesia" text should be at the top of that page as an introduction
//done·         Individual synaesthesia experiences (Touch/Sound/Colour/Taste/etc.) should be on their own pages
//done         Resurrecting color picker instructions from SynQuiz
//done         On the color picker, the progress indicator should be a % complete, rather than 7/12, 3/24, etc.
//done but more elegance needed·         The menu page should have a "Finish" button that submits the data and clears it out for the next user
//done·         Clicking Finish from the menu page should take people to the 'What's Next' screen (text for that is in document I sent)
//done    make sure the data is sent to the old web service
    //done make sure the new metadata is understood by the old webservice
    public void create(Experiment experiment, final List<PresenterScreen> presenterScreenList) {
        final PresenterScreen introductionScreen = createIntroductionScreen("Introduction", 1);
        presenterScreenList.add(introductionScreen);
        final WizardCompletionScreen completionScreen = new WizardCompletionScreen(
                "Thank you for participating! You may hear from us in the next few weeks to ask if you would like to participate in the genetics part of the study. Your data has been saved, and you can now close your browser. <br><br>"
                + "If you have any questions about the study, you can email them to us at "
                + formatLink("mailto:synaesthesia@mpi.nl")
                + ". It will be a year or more before there are results, but when we publish our study it will be posted on our "
                + formatLink("website", "http://www.mpi.nl/departments/language-and-genetics/projects/decoding-the-genetics-of-synaesthesia/publications")
                + ".<br/><br/>",
                true, null,
                "Finish this expriment and start from the begining",
                "Completion",
                "Could not contact the server, please check your internet connection and try again.", "Retry");
//        final PresenterScreen registrationScreen = createRegistrationScreen("Registration", 2);
//        presenterScreenList.add(registrationScreen);
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setMetadataScreenText("Please read the " + formatLink("Participant Information Sheet", "static/synaesthesia_info_sheet_ENGLISH_webversion.pdf") + " carefully!");
        wizardEditUserScreen.setBackWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return introductionScreen;
            }

        });
        wizardEditUserScreen.setFirstNameField(true);
        wizardEditUserScreen.setLastNameField(true);
        wizardEditUserScreen.setEmailAddressField(true);
        wizardEditUserScreen.setOptionCheckBox1("I would like to be contacted about participating in other synaesthesia research studies (optional)");
        wizardEditUserScreen.setMandatoryCheckBox("By checking this box I confirm that I have read and understood the Volunteer's Information Sheet and I agree to take part in this study");

        wizardEditUserScreen.setScreenTitle("Participant");
        wizardEditUserScreen.setScreenTag("Participant");
        wizardEditUserScreen.setMenuLabel("Participant");
//        wizardEditUserScreen.setScreenText(dispalyText);
//        wizardEditUserScreen.setCustomFields(customFields);
//        wizardEditUserScreen.setNextButton("Continue");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setSaveButtonLabel("Continue");
//        wizardEditUserScreen.setPostText(postText);
//        wizardEditUserScreen.setAlternateButtonLabel(alternateButtonLabel);
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Could not contact the server, please check your internet connection and try again.");

        final WizardScreen menuScreen = createMenuScreen("Menu", null, completionScreen.getPresenterScreen(), 15);
        presenterScreenList.add(menuScreen.getPresenterScreen());
        completionScreen.setBackWizardScreen(menuScreen);
        completionScreen.setNextWizardScreen(new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return introductionScreen;
            }
        });
//        final PresenterScreen demographicsScreen1 = createDemographicsScreen1(experiment, "Demographics1", 4);
//        presenterScreenList.add(demographicsScreen1);
//wizardController.addEditUserScreen(experiment, introductionScreen, "Participant", "Participant", null, 3, wizardData, null, null, "Continue", null, null, null, true, "Could not contact the server, please check your internet connection and try again.", false);
        final WizardEditUserScreen demographicsScreen1 = new WizardEditUserScreen("Details", "Details", null, null, demographicsFields1, "Continue", null, null, null, true, "Could not contact the server, please check your internet connection and try again.");
        wizardController.addMetadata(experiment);
        wizardEditUserScreen.setNextWizardScreen(demographicsScreen1);
        demographicsScreen1.setBackWizardScreen(wizardEditUserScreen);

        PresenterScreen previousDemographicsScreen = demographicsScreen1.getPresenterScreen();
        for (DemographicScreenType demographicScreenType : DemographicScreenType.values()) {
            final PresenterScreen demographicsScreen = createDemographicsScreen(experiment, previousDemographicsScreen, demographicScreenType, 6);
            presenterScreenList.add(demographicsScreen);
            previousDemographicsScreen.setNextPresenter(demographicsScreen);
            previousDemographicsScreen = demographicsScreen;
        }

//        final PresenterScreen demographicsScreen2 = createDemographicsScreen2(experiment, "Demographics2", 4);
//        presenterScreenList.add(demographicsScreen2);
//        demographicsScreen1.setNextPresenter(demographicsScreen2);
//        demographicsScreen2.setBackPresenter(demographicsScreen1);
        previousDemographicsScreen.setNextPresenter(menuScreen.getPresenterScreen());
        final PresenterScreen menuBackPresenter = previousDemographicsScreen;
        menuScreen.getPresenterScreen().setBackPresenter(menuBackPresenter);
        final PresenterScreen reportScreen = createReportScreen("Report", menuScreen.getPresenterScreen(), menuScreen.getPresenterScreen(), 20);
        presenterScreenList.add(reportScreen);
        final PresenterScreen weekdaysScreen = createStimulusScreen("Weekdays", menuScreen.getPresenterScreen(), reportScreen, 16);
        presenterScreenList.add(weekdaysScreen);
        final PresenterScreen numbersScreen = createStimulusScreen("Numbers", menuScreen.getPresenterScreen(), reportScreen, 17);
        presenterScreenList.add(numbersScreen);
        final PresenterScreen lettersScreen = createStimulusScreen("Letters", menuScreen.getPresenterScreen(), reportScreen, 18);
        presenterScreenList.add(lettersScreen);
        presenterScreenList.add(createStimulusScreen("Months", menuScreen.getPresenterScreen(), reportScreen, 19));
        completionScreen.populatePresenterScreen(experiment, false, 21);
        wizardEditUserScreen.populatePresenterScreen(experiment, false, 3);
        demographicsScreen1.populatePresenterScreen(experiment, false, 5);
    }

    private PresenterScreen createIntroductionScreen(String screenName, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen("Decoding the Genetics of Synaesthesia", screenName, null, screenName, null, PresenterType.text, displayOrder);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "We are studying the genetic basis of synaesthesia, a neurological phenomenon described as a \"mixing of the senses\". To find out how our genes shape how we see the world, "
                + "we are looking for people who connect letters, numbers, days of the week, or months with specific colours. This is called \"grapheme-colour\" synaesthesia. "));
        presenterFeatureList.add(new PresenterFeature(FeatureType.addPadding, null));
        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "How our study works:"));
        presenterFeatureList.add(new PresenterFeature(FeatureType.addPadding, null));
        final PresenterFeature studyDiagramFeature = new PresenterFeature(FeatureType.image, null);
        studyDiagramFeature.addFeatureAttributes(percentOfPage, imageSize);
        studyDiagramFeature.addFeatureAttributes(maxHeight, imageSize);
        studyDiagramFeature.addFeatureAttributes(maxWidth, imageSize);
        studyDiagramFeature.addFeatureAttributes(src, "static/study_diagram.svg");
        studyDiagramFeature.addFeatureAttributes(link, "");
        presenterFeatureList.add(studyDiagramFeature);
        presenterFeatureList.add(new PresenterFeature(FeatureType.addPadding, null));
        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "The synaesthesia tests take about 20 minutes to complete, and you can choose the ones that apply to you. "
                + "Depending on your scores, we may send you an email inviting you to participate in the genetics part of the study. There is no cost to participate, and you can do everything from home."));
        presenterFeatureList.add(new PresenterFeature(FeatureType.addPadding, null));
        final PresenterFeature targetButtonFeature = new PresenterFeature(FeatureType.targetButton, "Participate!");
        targetButtonFeature.addFeatureAttributes(target, "Participant");
        presenterFeatureList.add(targetButtonFeature);
        presenterFeatureList.add(new PresenterFeature(FeatureType.addPadding, null));
        presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, "For more information about synaesthesia, please see our 'About synaesthesia' page. "
                + "If you are not sure if you have synaesthesia, and want to find out, try our SynQuiz app or take a quick test at synesthete.org."));
        presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, "This project is organised and funded by the Language & Genetics Department at the Max Planck Institute for Psycholinguistics in Nijmegen in the Netherlands, directed by Prof. Dr. Simon E. Fisher. "
                + "The synaesthesia studies are coordinated by Dr. Amanda Tilot and Dr. Sarah Graham. "
                + "If you have any questions about our research, please contact us at " + formatLink("mailto:synaesthesia@mpi.nl") + "."));
        return presenterScreen;
    }

    private String formatLink(String linkUrl) {
        return formatLink(linkUrl, linkUrl);
    }

    private String formatLink(String linkText, String linkUrl) {
        return "<a href=\"#\" onclick=\"window.open('" + linkUrl + "','_system'); return false;\">" + linkText + "</a>";
    }

//    private PresenterScreen createRegistrationScreen(String screenName, long displayOrder) {
//        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, null, screenName + "Screen", null, PresenterType.metadata, displayOrder);
//        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
////        presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, ));
////        presenterFeatureList.add(new PresenterFeature(FeatureType.text, ""));
////        presenterFeatureList.add(new PresenterFeature(FeatureType.text, ""));
////        final PresenterFeature targetButtonFeature = new PresenterFeature(FeatureType.targetButton, "Participant Information Sheet");
////        targetButtonFeature.addFeatureAttributes(target, "InformationScreen");
////        presenterFeatureList.add(targetButtonFeature);
////        insertMetadataInput("First Name", ".'{'3,'}'");
////        insertMetadataInput("Last Name", ".'{'3,'}'");
////        insertMetadataInput("Email address", "^[^@]+@[^@]+$");
////        insertMetadataInput("\"I would like to be contacted about participating in other synaesthesia research studies\" (optional)", "true|false");
////        insertMetadataInput("\"By checking this box I confirm that I have read and understood the Volunteer's Information Sheet and I agree to take part in this study\"", "true|false");
//        presenterFeatureList.add(new PresenterFeature(FeatureType.allMetadataFields, null));
//        final PresenterFeature submitButtonFeature = new PresenterFeature(FeatureType.targetButton, "Submit");
//        submitButtonFeature.addFeatureAttributes(target, "DemographicsScreen");
//        presenterFeatureList.add(submitButtonFeature);
//        return presenterScreen;
//    }
// todo: show complete on test that have been done like in SynQuiz1
// todo: add finish button on the test menu screen which submits all data and leads to a restart(erase) all
    private WizardScreen createMenuScreen(String screenName, final PresenterScreen backScreen, final PresenterScreen completionScreen, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backScreen, screenName, completionScreen, PresenterType.menu, displayOrder);
        final PresenterFeature presenterFeature1 = new PresenterFeature(FeatureType.menuItem, "Weekdays");
        presenterFeature1.addFeatureAttributes(FeatureAttribute.target, "Weekdays");
        presenterScreen.getPresenterFeatureList().add(presenterFeature1);
        final PresenterFeature presenterFeature2 = new PresenterFeature(FeatureType.menuItem, "Numbers");
        presenterFeature2.addFeatureAttributes(FeatureAttribute.target, "Numbers");
        presenterScreen.getPresenterFeatureList().add(presenterFeature2);
        final PresenterFeature presenterFeature3 = new PresenterFeature(FeatureType.menuItem, "Letters");
        presenterFeature3.addFeatureAttributes(FeatureAttribute.target, "Letters");
        presenterScreen.getPresenterFeatureList().add(presenterFeature3);
        final PresenterFeature presenterFeature4 = new PresenterFeature(FeatureType.menuItem, "Months");
        presenterFeature4.addFeatureAttributes(FeatureAttribute.target, "Months");
        presenterScreen.getPresenterFeatureList().add(presenterFeature4);
//        completionScreen
        return new AbstractWizardScreen() {
            @Override
            public PresenterScreen getPresenterScreen() {
                return presenterScreen;
            }
        };
    }

    final String[] demographicsFields1 = new String[]{
        "DateOfBirth:Date of Birth:[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]:Please enter in the standard format DD/MM/YYYY.",
//        "Age:Age:[0-9]+:Please enter in number format.",
        "Gender:Gender:|male|female|other:.",
        "AbsolutePitch:Absolute pitch:true|false:Please enter true or false.",
        "TraumaticBlowToTheHead:Traumatic blow to the head:true|false:Please enter true or false.",
        "Migraines:Migraines:true|false:Please enter true or false.",
        "Headaches:Headaches:true|false:Please enter true or false.",
        "Seizures:Seizures:true|false:Please enter true or false.",
        "Dyslexia:Dyslexia:true|false:Please enter true or false.",
        "BrainSurgery:Brain surgery:true|false:Please enter true or false.",
        "AnyOtherConditions:Are there any other conditions that you would like us to know about?:.*:."
    };
//    private PresenterScreen createDemographicsScreen1(Experiment experiment, String screenName, long displayOrder) {
//        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, null, screenName + "Screen", null, PresenterType.metadata, displayOrder);
//        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        
//        final PresenterFeature targetButtonFeature = new PresenterFeature(FeatureType.saveMetadataButton, "Take the tests!");
//        presenterFeatureList.add(targetButtonFeature);
//        return presenterScreen;
//    }

    enum DemographicScreenType {
        Study, Colour, Smell, Sound, Spatial, Taste, Touch, Other
    }

    private PresenterScreen createDemographicsScreen(Experiment experiment, final PresenterScreen backPresenter, DemographicScreenType screenName, long displayOrder) {
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
//        wizardEditUserScreen.setScreenTitle();
        final PresenterScreen presenterScreen = new PresenterScreen(
                "Tell us about your synaesthesia: " + screenName.name() + "(" + (screenName.ordinal() + 1) + "/" + DemographicScreenType.values().length + ")",
                screenName.name(),
                backPresenter,
                screenName.name(),
                null,
                PresenterType.metadata,
                displayOrder + screenName.ordinal());
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
//        presenterFeatureList.add(new PresenterFeature(FeatureType.centrePage, null));
//        presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "Tell Us About Your Synaesthesia"));
        switch (screenName) {
            case Study:
                presenterFeatureList.add(new PresenterFeature(FeatureType.plainText, "Our study at the Max Planck Institute focuses on synaesthesia where numbers, letters, weekdays, or months cause people to have a colour experience. To someone with synaesthesia, the letter A might \"mean\" red to them, or the number \"5\" might make them experience the colour green. Please let us know if you experience any other types of synaesthesia by checking the boxes in the following screens. We may contact you in the future about studies related to these other types."));
                break;
            case Colour:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Colour<br/>" +
                        "Do any of the items below cause you to have a color experience?<br/><br/>"
                        + "Examples: Does the letter M \"mean\" orange to you? Or does hearing a piano being played make you perceive red?<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign -> Colour", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign -> Colour", presenterScreen);
                break;
            case Smell:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Smell<br/>"
                        "Do any of the items below cause you to experience smells?<br/><br/>"
                        + "Example: Does Tuesday smell like bananas?<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign->Smell", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign->Smell", presenterScreen);
                break;
            case Sound:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Sound<br/>"
                        "Do any of the items below cause you to experience sound?<br/><br/>"
                        + "Example: Do you hear a particular sound when you experience cold temperatures?<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign->Sound", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign->Sound", presenterScreen);
                break;
            case Spatial:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Spatial<br/>"
                        "Do you experience any of the items below in a particular spatial location?<br/><br/>"
                        + "Example: Do you \"see\" sequences like the days of the month or numbers in physical space?<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments>Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign->Spatial", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign->Spatial", presenterScreen);
                break;
            case Taste:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Taste<br/>"
                        "Do any of the items below cause you to experience tastes?<br/><br/>"
                        + "Examples: \"Philip tastes of bitter oranges, while April tastes of apricots.\" \"The word 'safety' tastes of lightly buttered toast\"<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments>Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign->Taste", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign->Taste", presenterScreen);
                break;
            case Touch:
                presenterFeatureList.add(new PresenterFeature(FeatureType.htmlText, //"Touch<br/>"
                        "Do any of the items below cause you to experience a sense of touch?<br/><br/>"
                        + "Example: You feel a touch on your arm when you see someone else being touched on their arm.<br/><br/>"));
                wizardEditUserScreen.insertMetadataField(experiment, "Numbers->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Letters->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Weekdays->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Months->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sequences->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Pitch->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Chord->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Musical Instruments->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Taste->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Smell->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Pain->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Personalities->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Touch->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Temperature->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Vision->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "Sound->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "American Sign->Touch", presenterScreen);
                wizardEditUserScreen.insertMetadataField(experiment, "British Sign->Touch", presenterScreen);
                break;
            case Other:
                wizardEditUserScreen.insertMetadataField(experiment, new Metadata("AnyOtherTypes", "If you experience any other types, please explain below.", "", "", false, null), presenterScreen);
        }
        final PresenterFeature saveMetadataButton = new PresenterFeature(FeatureType.saveMetadataButton, "Continue");
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.sendData, "true");
        saveMetadataButton.addFeatureAttributes(FeatureAttribute.networkErrorMessage, "Could not contact the server, please check your internet connection and try again.");
        final PresenterFeature onErrorFeature = new PresenterFeature(FeatureType.onError, null);
        saveMetadataButton.getPresenterFeatureList().add(onErrorFeature);
        final PresenterFeature onSuccessFeature = new PresenterFeature(FeatureType.onSuccess, null);
        final PresenterFeature autoNextPresenter = new PresenterFeature(FeatureType.autoNextPresenter, null);
        onSuccessFeature.getPresenterFeatureList().add(autoNextPresenter);
        saveMetadataButton.getPresenterFeatureList().add(onSuccessFeature);
        presenterScreen.getPresenterFeatureList().add(saveMetadataButton);
        return presenterScreen;
    }

    private PresenterScreen createStimulusScreen(String screenName, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName, nextPresenter, PresenterType.colourPicker, displayOrder);
        final PresenterFeature helpDialogue = new PresenterFeature(FeatureType.helpDialogue, "<b>Instructions</b>\\n<p>Select the colour that you associate with the presented character or word \\n<ol>\\n<li>Select the hue by tapping on the colour bar on the right </li><li>Select the shade by tapping on the square on the left </li>\\n<li>When the colour of the preview rectangle matches your association, tap \"Submit\"</li>\\n<li>If you have no colour association tap \"No colour\"</li>\\n</ol>\\n</p>");
        helpDialogue.addFeatureAttributes(FeatureAttribute.closeButtonLabel, "OK, go to test!");
        presenterScreen.getPresenterFeatureList().add(helpDialogue);
        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature loadStimuliFeature = new PresenterFeature(FeatureType.loadAllStimulus, null);
        loadStimuliFeature.addStimulusTag(screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.eventTag, screenName);
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "true");
        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.repeatCount, "3");
        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature hasMoreStimulusFeature = new PresenterFeature(FeatureType.hasMoreStimulus, null);
//        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
//        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        loadStimuliFeature.getPresenterFeatureList().add(hasMoreStimulusFeature);
        final PresenterFeature endOfStimulusFeature = new PresenterFeature(FeatureType.endOfStimulus, null);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.text, "end of stimuli"));
//        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, "Menu");
//        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, "AutoMenu");
//        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        loadStimuliFeature.getPresenterFeatureList().add(endOfStimulusFeature);
        return presenterScreen;
    }

    private PresenterScreen createReportScreen(String screenName, final PresenterScreen backPresenter, final PresenterScreen nextPresenter, long displayOrder) {
        final PresenterScreen presenterScreen = new PresenterScreen(screenName, screenName, backPresenter, screenName, nextPresenter, PresenterType.colourReport, displayOrder);
//        List<PresenterFeature> presenterFeatureList = presenterScreen.getPresenterFeatureList();
        final PresenterFeature showColourReport = new PresenterFeature(FeatureType.showColourReport, null);
//        showColourReport.addStimulusTag(screenName);
        showColourReport.addFeatureAttributes(FeatureAttribute.scoreThreshold, "1");
//        loadStimuliFeature.addFeatureAttributes(FeatureAttribute.randomise, "true");
//        presenterFeatureList.add(loadStimuliFeature);
        final PresenterFeature aboveThreshold = new PresenterFeature(FeatureType.aboveThreshold, null);
        aboveThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "above threshold"));
//        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.stimulusLabel, null));
//        hasMoreStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.showStimulusProgress, null));
        showColourReport.getPresenterFeatureList().add(aboveThreshold);
        final PresenterFeature belowThreshold = new PresenterFeature(FeatureType.belowThreshold, null);
        belowThreshold.getPresenterFeatureList().add(new PresenterFeature(FeatureType.plainText, "below threshold"));
//        final PresenterFeature menuButtonFeature = new PresenterFeature(FeatureType.targetButton, "Menu");
//        menuButtonFeature.addFeatureAttributes(FeatureAttribute.target, "AutoMenu");
//        endOfStimulusFeature.getPresenterFeatureList().add(menuButtonFeature);
//        endOfStimulusFeature.getPresenterFeatureList().add(new PresenterFeature(FeatureType.autoNextPresenter, null));
        showColourReport.getPresenterFeatureList().add(belowThreshold);
        presenterScreen.getPresenterFeatureList().add(showColourReport);
        return presenterScreen;
    }

    private void insertStimulusGroup(final ArrayList<Stimulus> stimuliList, String groupName, String groupItems) {
        final HashSet<String> tagSet = new HashSet<>(Arrays.asList(new String[]{groupName}));
        final String[] itemArray = groupItems.split(",");
        for (String itemString : itemArray) {
            final Stimulus stimulus = new Stimulus(itemString, null, null, null, itemString, null, 0, tagSet, null);
            stimuliList.add(stimulus);
        }
    }

    public ArrayList<Stimulus> createStimuli() {
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        insertStimulusGroup(stimuliList, "Weekdays", "Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday");
//        insertStimulusGroup(stimuliList, "Numbers", "0,1,2,3,4,5,6,7,8,9");
//        insertStimulusGroup(stimuliList, "Letters", "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        insertStimulusGroup(stimuliList, "Letters and Numbers", "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9");
        insertStimulusGroup(stimuliList, "Months", "January,February,March,April,May,June,July,August,September,October,November,December");
        return stimuliList;
    }
}
