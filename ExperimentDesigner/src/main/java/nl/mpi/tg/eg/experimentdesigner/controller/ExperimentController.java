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
import nl.mpi.tg.eg.experimentdesigner.dao.PublishEventRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.util.DefaultExperiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @since Nov 4, 2015 1:59:50 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class ExperimentController {

    @Autowired
    ExperimentRepository experimentRepository;
    @Autowired
    PublishEventRepository eventRepository;

    @RequestMapping("/experiments")
    public String designView(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "experiments");
        model.addAttribute("allExperiments", experimentRepository.findAll());
        return "design";
    }

    @RequestMapping("/experiment/{experiment}")
    public String designView(Model model, HttpServletRequest request, @PathVariable Experiment experiment) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "configuration");
        model.addAttribute("experiment", experiment);
        return "design";
    }

    @RequestMapping("/experiments/add")
    public String addExperiment(Model model, HttpServletRequest request) {
        Experiment createdExperiment = DefaultExperiments.getDefault();
        experimentRepository.save(createdExperiment);
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "configuration");
        model.addAttribute("experiment", createdExperiment);
        return "design";
    }

    @RequestMapping(value = "/experiment/{experiment}/update", method = RequestMethod.POST)
    public String updateScreen(@ModelAttribute Experiment updatedExperiment, Model model, HttpServletRequest request, @PathVariable Experiment experiment) {
        experiment.setAppNameDisplay(updatedExperiment.getAppNameDisplay());
        experiment.setAppNameInternal(updatedExperiment.getAppNameInternal());
        experiment.setBackgroundColour(updatedExperiment.getBackgroundColour());
        experiment.setComplementColour0(updatedExperiment.getComplementColour0());
        experiment.setComplementColour1(updatedExperiment.getComplementColour1());
        experiment.setComplementColour2(updatedExperiment.getComplementColour2());
        experiment.setComplementColour3(updatedExperiment.getComplementColour3());
        experiment.setComplementColour4(updatedExperiment.getComplementColour4());
        experiment.setDataSubmitUrl(updatedExperiment.getDataSubmitUrl());
        experiment.setPrimaryColour0(updatedExperiment.getPrimaryColour0());
        experiment.setPrimaryColour1(updatedExperiment.getPrimaryColour1());
        experiment.setPrimaryColour2(updatedExperiment.getPrimaryColour2());
        experiment.setPrimaryColour3(updatedExperiment.getPrimaryColour3());
        experiment.setPrimaryColour4(updatedExperiment.getPrimaryColour4());
        experiment.setStaticFilesUrl(updatedExperiment.getStaticFilesUrl());
        experimentRepository.save(experiment);
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "configuration");
        model.addAttribute("experiment", experiment);
        return "design";
    }
}
