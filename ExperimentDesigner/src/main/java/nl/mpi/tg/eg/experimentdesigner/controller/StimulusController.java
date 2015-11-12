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
import nl.mpi.tg.eg.experimentdesigner.dao.StimuliRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.Stimulus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @since Nov 12, 2015 12:02:29 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class StimulusController {

    @Autowired
    StimuliRepository stimuliRepository;
    @Autowired
    ExperimentRepository experimentRepository;

    @RequestMapping(value = "/experiment/{appName}/stimulus/add", method = RequestMethod.POST)
    public String addStimulus(final HttpServletRequest req, Model model, @ModelAttribute Stimulus stimulus, @PathVariable String appName) {
        final Stimulus savedStimulus = stimuliRepository.save(stimulus);
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        experiment.getStimuli().add(stimulus);
        experimentRepository.save(experiment);
        model.addAttribute("updatedStimulus", savedStimulus);
//        populateModel(model, appName);
        return "stimuli :: stimulusRow";
    }

    @RequestMapping(value = "/experiment/{appName}/stimulus/update", method = RequestMethod.POST)
    public String updateStimulus(final HttpServletRequest req, Model model, @ModelAttribute Stimulus stimulus, @PathVariable String appName) {
        final Stimulus savedStimulus = stimuliRepository.save(stimulus);
        model.addAttribute("updatedStimulus", savedStimulus);
//        populateModel(model, appName);
        return "stimuli :: stimulusRow";
    }

    @RequestMapping(value = "/experiment/{appName}/stimulus/delete", method = RequestMethod.POST)
    public String deleteStimulus(final HttpServletRequest req, Model model, @ModelAttribute Stimulus stimulus, @PathVariable String appName) {
        final Stimulus storedStimulus = stimuliRepository.findOne(stimulus.getId());
        final Experiment experiment = experimentRepository.findByAppNameInternal(appName);
        experiment.getStimuli().remove(storedStimulus);
        experimentRepository.save(experiment);
        stimuliRepository.delete(storedStimulus);
        model.addAttribute("updatedStimulus", storedStimulus);
//        populateModel(model, appName);
        return "stimuli :: stimulusRow";
    }
}
