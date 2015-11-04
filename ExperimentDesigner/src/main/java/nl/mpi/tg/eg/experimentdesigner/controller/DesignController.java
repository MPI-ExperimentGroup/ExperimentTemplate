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
        model.addAttribute("screens", experiment.getPresenterScreen());
        model.addAttribute("metadata", experiment.getMetadata());
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

    @RequestMapping("/design")
    public String designView(Model model, HttpServletRequest request) {
        return "redirect:experiments";
    }

    @RequestMapping("/design/{appName}")
    public String designView(Model model, HttpServletRequest request, @PathVariable String appName) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "configuration");
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping("/design/{appName}/{detailType}")
    public String designView(Model model, HttpServletRequest request, @PathVariable String appName, @PathVariable String detailType,
            @RequestParam(value = "screen", required = false, defaultValue = "") String screenTag) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", detailType);
        model.addAttribute("screenTag", screenTag);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"addMetadata"}, method = RequestMethod.POST)
    public String addMetadata(final HttpServletRequest req, Model model, @ModelAttribute Metadata metadata, @PathVariable String appName) {
//        final Long experimentId = Long.valueOf(req.getParameter("experimentId"));
//        final PresenterScreen presenterScreen = presenterScreenRepository.findOne(rowId);
        metadataRepository.save(metadata);
//        presenterScreen.getPresenterFeatureList().add(presenterFeature);
//        presenterScreenRepository.save(presenterScreen);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"deleteMetadata"}, method = RequestMethod.POST)
    public String deleteMetadata(final HttpServletRequest req, Model model, @ModelAttribute Metadata metadata, @PathVariable String appName) {
        final Long metadataId = Long.valueOf(req.getParameter("deleteMetadata"));
        metadataRepository.delete(metadataId);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"addFeature"}, method = RequestMethod.POST)
    public String addFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature presenterFeature, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("addFeature"));
        final PresenterScreen presenterScreen = presenterScreenRepository.findOne(rowId);
        presenterFeatureRepository.save(presenterFeature);
        presenterScreen.getPresenterFeatureList().add(presenterFeature);
        presenterScreenRepository.save(presenterScreen);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"addSubFeature"}, method = RequestMethod.POST)
    public String addSubFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature childFeature, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("addSubFeature"));
        final PresenterFeature parentFeature = presenterFeatureRepository.findOne(rowId);
        presenterFeatureRepository.save(childFeature);
        parentFeature.getPresenterFeatureList().add(childFeature);
        presenterFeatureRepository.save(parentFeature);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"deleteFeature"}, method = RequestMethod.POST)
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

    @RequestMapping(value = "/design/{appName}", params = {"deleteSubFeature"}, method = RequestMethod.POST)
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

    @RequestMapping(value = "/design/{appName}", params = {"saveFeature"}, method = RequestMethod.POST)
    public String saveFeature(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("saveFeature"));
        final PresenterFeature modifiedFeature = presenterFeatureRepository.findOne(rowId);
        final String featureText = req.getParameter("featureText");
        modifiedFeature.setFeatureText(featureText);
        presenterFeatureRepository.save(modifiedFeature);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"addScreen"}, method = RequestMethod.POST)
    public String addScreen(@ModelAttribute PresenterScreen prersenterScreen, Model model, @PathVariable String appName) {
        presenterScreenRepository.save(prersenterScreen);
        populateModel(model, appName);
        return "design";
    }

    @RequestMapping(value = "/design/{appName}", params = {"removeScreen"})
    public String removeScreen(final HttpServletRequest req, Model model, @PathVariable String appName) {
        final Long rowId = Long.valueOf(req.getParameter("removeScreen"));
        presenterScreenRepository.delete(rowId);
        populateModel(model, appName);
        return "design";
    }
}
