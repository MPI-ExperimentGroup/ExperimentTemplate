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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.AbstractWizardScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardScreen;
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
        final Experiment experiment = getExperiment(wizardData.getAppName().replaceAll("[^A-Za-z0-9]", "_"), wizardData.getAppName(), wizardData.isShowMenuBar());
//        PresenterScreen previousScreen = null;
//        PresenterScreen nextScreen = null;
//        PresenterScreen editUserScreen = null;
        PresenterScreen userSelectMenu = null;
        PresenterScreen agreementScreen = null;
        PresenterScreen informationScreen = null;
//        PresenterScreen audioTestScreen = null;
        PresenterScreen practiceStimulusScreen = null;
        PresenterScreen stimulusScreen = null;
        PresenterScreen completionScreen = null;
        PresenterScreen autoMenu = null;
//        PresenterScreen debugScreen = addDebugScreen(experiment, null, 10);
//    private String agreementScreenText = "";
//    private String disagreementScreenText = "";
//        if (wizardData.isAgreementScreen()) {
//            agreementScreen = addAgreementScreen(experiment, null, "InformationScreen", 2, wizardData.getAgreementScreenText(), "Akkoord", wizardData.isObfuscateScreenNames());
//            if (userSelectMenu != null) {
//                userSelectMenu.setNextPresenter(agreementScreen);
//            }
//        }
//        if (wizardData.getInformationScreenText() != null) {
//            informationScreen = addTextScreen(experiment, null, "InformationScreen", null, 3, wizardData.getInformationScreenText(), "volgende [ spatiebalk ]", wizardData.isObfuscateScreenNames());
//            if (agreementScreen != null) {
//                agreementScreen.setNextPresenter(informationScreen);
//            }
//        }
//        if (wizardData.isMetadataScreen()) {
////            addMetadata(experiment, wizardData);
//            editUserScreen = addEditUserScreen(experiment, null, "Edit User", "Edit User", null, 4, wizardData, null, null, "Save Details", null, null, null, true, "Could not contact the server, please check your internet connection and try again.", wizardData.isObfuscateScreenNames());
//            if (informationScreen != null) {
//                informationScreen.setNextPresenter(editUserScreen);
//            }
//        }
        int currentDisplaySequence = 4;
        PresenterScreen previousScreen = informationScreen;
        for (WizardScreen wizardScreen : wizardData.getWizardScreens()) {
            PresenterScreen currentScreen = wizardScreen.populatePresenterScreen(experiment, wizardData.isObfuscateScreenNames(), currentDisplaySequence++);
            if (previousScreen != null) {
                previousScreen.setNextPresenter(currentScreen);
                previousScreen = currentScreen;
            }
        }
//    private boolean practiceScreen = false;
//    private String practiceStimuliPath = "";
//    private boolean stimuliScreen = false;
        if (wizardData.isMenuScreen()) {
            autoMenu = addAutoMenu(experiment, 10, wizardData.isObfuscateScreenNames());
            if (completionScreen != null) {
                completionScreen.setNextPresenter(autoMenu);
            }
        }
//    private String stimuliPath = "";
//    private boolean completionScreen = false;
//    private String completionText = "";
        return experiment;
    }

    public Experiment getExperiment(String appNameInternal, String appName, boolean showMenuBar) {
        Experiment experiment = getDefault();
        experiment.setAppNameDisplay(appName);
        experiment.setAppNameInternal(appNameInternal);
        experiment.setShowMenuBar(showMenuBar);
        return experiment;
    }

    public void addMetadata(Experiment experiment) {
        final Metadata metadata = new Metadata("workerId", "Worker ID", ".'{'3,'}'", "Please enter at least three letters.", false, null);
        experiment.getMetadata().add(metadata);
    }

    public PresenterScreen addAutoMenu(final Experiment experiment, long displayOrder, boolean obfuscateScreenNames) {
        final PresenterScreen presenterScreen = new PresenterScreen((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : "Auto Menu", "Menu", null, "AutoMenu", null, PresenterType.menu, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.allMenuItems, null));
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }

    public PresenterScreen addDebugScreen(final Experiment experiment, PresenterScreen autoMenuPresenter, long displayOrder, boolean obfuscateScreenNames) {
        final PresenterScreen presenterScreen = new PresenterScreen((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : "Debug Screen", "Debug Screen", autoMenuPresenter, "DebugScreen", null, PresenterType.debug, displayOrder);
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.versionData, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.eraseLocalStorageButton, null));
        presenterScreen.getPresenterFeatureList().add(new PresenterFeature(FeatureType.localStorageData, null));
        experiment.getPresenterScreen().add(presenterScreen);
        return presenterScreen;
    }
}
