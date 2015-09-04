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

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterFeatureRepository;
import nl.mpi.tg.eg.experimentdesigner.dao.PresenterScreenRepository;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterFeature;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    private void populateModel(Model model) {
        model.addAttribute("screens", presenterScreenRepository.findAll());
//        model.addAttribute("features", presenterFeatureRepository.findAll());
        model.addAttribute("featureattributes", FeatureAttribute.values());
        model.addAttribute("featuretypes", FeatureType.values());
        model.addAttribute("presentertypes", PresenterType.values());

    }

    @RequestMapping("/design")
    public String designView(Model model) {
        populateModel(model);
        return "design";
    }

    @RequestMapping(value = "/design", params = {"addFeature"}, method = RequestMethod.POST)
    public String addFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature presenterFeature) {
        final Long rowId = Long.valueOf(req.getParameter("addFeature"));
        final PresenterScreen presenterScreen = presenterScreenRepository.findOne(rowId);
        presenterFeatureRepository.save(presenterFeature);
        presenterScreen.getPresenterFeatures().add(presenterFeature);
        presenterScreenRepository.save(presenterScreen);
        populateModel(model);
        return "design";
    }

    @RequestMapping(value = "/design", params = {"addSubFeature"}, method = RequestMethod.POST)
    public String addSubFeature(final HttpServletRequest req, Model model, @ModelAttribute PresenterFeature childFeature) {
        final Long rowId = Long.valueOf(req.getParameter("addSubFeature"));
        final PresenterFeature parentFeature = presenterFeatureRepository.findOne(rowId);
        presenterFeatureRepository.save(childFeature);
        parentFeature.getPresenterFeatures().add(childFeature);
        presenterFeatureRepository.save(parentFeature);
        populateModel(model);
        return "design";
    }

    @RequestMapping(value = "/design", params = {"addScreen"}, method = RequestMethod.POST)
    public String addScreen(@ModelAttribute PresenterScreen prersenterScreen, Model model) {
        presenterScreenRepository.save(prersenterScreen);
        populateModel(model);
        return "design";
    }

    @RequestMapping(value = "/design", params = {"removeScreen"})
    public String removeScreen(final HttpServletRequest req, Model model) {
        final Long rowId = Long.valueOf(req.getParameter("removeScreen"));
        presenterScreenRepository.delete(rowId);
        populateModel(model);
        return "design";
    }
}
