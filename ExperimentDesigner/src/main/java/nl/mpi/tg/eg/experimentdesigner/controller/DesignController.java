/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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

import javax.servlet.http.HttpServletRequest;
import nl.mpi.tg.eg.experimentdesigner.dao.ExperimentRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.MetadataRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.Metadata;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import nl.mpi.tg.eg.experimentdesigner.util.DefaultExperiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Aug 18, 2015 1:40:11 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class DesignController {

    @Autowired
    PresenterScreenRepository presenterScreenRepository;
    @Autowired
    PresenterFeatureRepository presenterFeatureRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    ExperimentRepository experimentRepository;

    private void populateModel(Model model, String appName) {
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        model.addAttribute("screencount", presenterScreenRepository.count());
        model.addAttribute("experimentcount", experimentRepository.count());
        model.addAttribute("experiment", experiment);
        model.addAttribute("featurecount", presenterFeatureRepository.count());
        model.addAttribute("metadatacount", metadataRepository.count());
        model.addAttribute("featureattributes", FeatureAttribute.values());
        model.addAttribute("featuretypes", FeatureType.values());
        model.addAttribute("presentertypes", PresenterType.values());

    }

    @RequestMapping("/deleteAll")
    public String deleteAll(Model model, HttpServletRequest request) {
        // todo: this is currently here to simplify the development process and should be removed in production
        experimentRepository.deleteAll();
//        if (experimentRepository.count() == 0) {
        new DefaultExperiments().insertDefaultExperiment(presenterScreenRepository, presenterFeatureRepository, metadataRepository, experimentRepository);
//        }
        return "redirect:experiments";
    }

    @RequestMapping(value = "/experiment/{appName}/screen/delete", method = RequestMethod.POST)
    public String deleteScreen(@ModelAttribute PresenterScreen prersenterScreen, Model model, HttpServletRequest request, @PathVariable String appName) {
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        final PresenterScreen presenterToDelete = presenterScreenRepository.findOne(prersenterScreen.getId());
        if (presenterToDelete.getUsageCount() > 0) {
            throw new IllegalArgumentException("Cannot delete because this screen is in use by " + presenterToDelete.getUsageCount() + " screens.");
        }
        experiment.getPresenterScreen().remove(presenterToDelete);
        experimentRepository.save(experiment);
//        model.addAttribute("contextPath", request.getContextPath());
//        model.addAttribute("updatedPresenterScreen", null);
//        model.addAttribute("detailType", "screens");
//        populateModel(model, appName);
//        return "screens :: screenRow";
        return "redirect:design";
    }

    @RequestMapping(value = "/experiment/{appName}/screen/add", method = RequestMethod.POST)
    public String addScreen(@ModelAttribute PresenterScreen prersenterScreen, Model model, HttpServletRequest request, @PathVariable String appName) {
        if (prersenterScreen.getSelfPresenterTag() == null || prersenterScreen.getSelfPresenterTag().length() < 3) {
            throw new IllegalArgumentException("Self (Action) must be longer than three characters.");
        }
        if (!prersenterScreen.getSelfPresenterTag().matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Self (Action) must only be letters or numbers.");
        }
        if (presenterScreenRepository.findBySelfPresenterTag(prersenterScreen.getSelfPresenterTag()) != null) {
            throw new IllegalArgumentException("Self (Action) must be unique.");
        }
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        experiment.getPresenterScreen().add(prersenterScreen);
        final PresenterScreen savedScreen = presenterScreenRepository.save(prersenterScreen);
        experimentRepository.save(experiment);
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("updatedPresenterScreen", savedScreen);
        model.addAttribute("detailType", "screens");
        populateModel(model, appName);
        return "screens :: screenRow";
    }

    @RequestMapping(value = "/experiment/{appName}/screen/update", method = RequestMethod.POST)
    public String updateScreen(@ModelAttribute PresenterScreen prersenterScreen, Model model, HttpServletRequest request, @PathVariable String appName) {
//        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
//        experiment.getPresenterScreen().add(prersenterScreen);
        final PresenterScreen updatedScreen = presenterScreenRepository.findOne(prersenterScreen.getId());
        updatedScreen.setTitle(prersenterScreen.getTitle());
        updatedScreen.setMenuLabel(prersenterScreen.getMenuLabel());
//        updatedScreen.setSelfPresenterTag(prersenterScreen.getSelfPresenterTag());
        updatedScreen.setBackPresenter(prersenterScreen.getBackPresenter());
        updatedScreen.setNextPresenter(prersenterScreen.getNextPresenter());
        presenterScreenRepository.save(updatedScreen);
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("updatedPresenterScreen", updatedScreen);
        model.addAttribute("detailType", "screens");
        populateModel(model, appName);
        return "screens :: screenRow";
    }

    @RequestMapping("/design")
    public String designView(Model model, HttpServletRequest request) {
        return "redirect:experiments";
    }
    
    @RequestMapping("/")
    public String designView1(Model model, HttpServletRequest request) {
        return "redirect:experiments";
    }

    @RequestMapping("/experiment/{appName}/screen/{presenterScreen}")
    public String editPresenterScreen(Model model, HttpServletRequest request, @PathVariable String appName, @PathVariable PresenterScreen presenterScreen) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "screen");
        model.addAttribute("presenterScreen", presenterScreen);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping("/experiment/{appName}/{detailType}")
    public String designView(Model model, HttpServletRequest request, @PathVariable String appName, @PathVariable String detailType) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", detailType);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/experiment/{appName}/metadata/add", method = RequestMethod.POST)
    public String addMetadata(final HttpServletRequest req, Model model, @ModelAttribute Metadata metadata, @PathVariable String appName) {
        final Metadata savedMetadata = metadataRepository.save(metadata);
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        experiment.getMetadata().add(metadata);
        experimentRepository.save(experiment);
        model.addAttribute("updatedMetadata", savedMetadata);
        populateModel(model, appName);
        return "metadata :: metadataRow";
    }

    @RequestMapping(value = "/experiment/{appName}/metadata/update", method = RequestMethod.POST)
    public String updateMetadata(final HttpServletRequest req, Model model, @ModelAttribute Metadata metadata, @PathVariable String appName) {
        final Metadata savedMetadata = metadataRepository.save(metadata);
        model.addAttribute("updatedMetadata", savedMetadata);
        populateModel(model, appName);
        return "metadata :: metadataRow";
    }

    @RequestMapping(value = "/experiment/{appName}/metadata/delete", method = RequestMethod.POST)
    public String deleteMetadata(final HttpServletRequest req, Model model, @ModelAttribute Metadata metadata, @PathVariable String appName) {
        final Metadata storedMetadata = metadataRepository.findOne(metadata.getId());
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        experiment.getMetadata().remove(storedMetadata);
        experimentRepository.save(experiment);
        metadataRepository.delete(storedMetadata);
        model.addAttribute("updatedMetadata", null);
        populateModel(model, appName);
        return "metadata :: metadataRow";
    }

    @RequestMapping(value = "/experiment/{appName}/feature/add", method = RequestMethod.POST)
    public String addFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature presenterFeature, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("screenId"));
        final PresenterScreen presenterScreen = presenterScreenRepository.findOne(rowId);
        presenterFeatureRepository.save(presenterFeature);
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        presenterScreenRepository.save(presenterScreen);
        populateModel(model, appName);
        model.addAttribute("features", presenterFeature);         
        model.addAttribute("presenterScreen", presenterScreen);         
        return "screens :: featuresrow";
    }

    @RequestMapping(value = "/experiment/{appName}/feature/add", params = {"featureId"}, method = RequestMethod.POST)
    public String addSubFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature childFeature, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("addSubFeature"));
        final PresenterFeature parentFeature = presenterFeatureRepository.findOne(rowId);
        presenterFeatureRepository.save(childFeature);
        parentFeature.getPresenterFeatureList().add(childFeature);
        presenterFeatureRepository.save(parentFeature);
        populateModel(model, appName);
        model.addAttribute("features", childFeature); 
        model.addAttribute("presenterScreen", parentFeature); 
        return "screens :: featuresrow";
    }

    @RequestMapping(value = "/experiment/{appName}", params = {"deleteFeature"}, method = RequestMethod.POST)
    public String deleteFeature(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("deleteFeature"));
        final Long presenterId = Long.valueOf(req.getParameter("parentId"));
        final PresenterFeature deletableFeature = presenterFeatureRepository.findOne(rowId);
        final PresenterScreen parentPresenter = presenterScreenRepository.findOne(presenterId);
        parentPresenter.getPresenterFeatureList().remove(deletableFeature);
        presenterScreenRepository.save(parentPresenter);
        presenterFeatureRepository.delete(deletableFeature);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/experiment/{appName}", params = {"deleteSubFeature"}, method = RequestMethod.POST)
    public String deleteSubFeature(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("deleteSubFeature"));
        final Long presenterId = Long.valueOf(req.getParameter("parentId"));
        final PresenterFeature deletableFeature = presenterFeatureRepository.findOne(rowId);
        final PresenterFeature parentFeature = presenterFeatureRepository.findOne(presenterId);
        parentFeature.getPresenterFeatureList().remove(deletableFeature);
        presenterFeatureRepository.save(parentFeature);
        presenterFeatureRepository.delete(deletableFeature);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/experiment/{appName}", params = {"saveFeature"}, method = RequestMethod.POST)
    public String saveFeature(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("saveFeature"));
        final PresenterFeature modifiedFeature = presenterFeatureRepository.findOne(rowId);
        final String featureText = req.getParameter("featureText");
        modifiedFeature.setFeatureText(featureText);
        presenterFeatureRepository.save(modifiedFeature);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/experiment/{appName}", params = {"removeScreen"})
    public String removeScreen(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("removeScreen"));
        presenterScreenRepository.delete(rowId);
        populateModel(model, appName);
        return "design";
    }
}
