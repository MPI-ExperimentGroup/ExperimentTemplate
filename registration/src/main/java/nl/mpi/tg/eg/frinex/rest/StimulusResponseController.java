/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.rest;

import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Sep 26, 2018 5:00:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class StimulusResponseController {

    @Autowired
    private StimulusResponseRepository stimulusResponseRepository;

    @RequestMapping("stimulusresponseviewer")
    public String tagPairViewer(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "sort", required = false, defaultValue = "tagDate") String sortColumn,
            @RequestParam(value = "size", defaultValue = "500", required = false) Integer size,
            @RequestParam(value = "dataChannel", required = false) Integer dataChannel,
            @RequestParam(value = "dir", required = false, defaultValue = "a") String sortDirection,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {//, Pageable pageable
        // final long count = this.stimulusResponseRepository.count();
        // model.addAttribute("count", count);
        // final Page<StimulusResponse> pageData = (dataChannel == null)
        //         ? this.stimulusResponseRepository.findAll(PageRequest.of(page, size, ("a".equals(sortDirection)) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn))
        //         : this.stimulusResponseRepository.findBydataChannel(PageRequest.of(page, size, ("a".equals(sortDirection)) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn), dataChannel);
        // final List<StimulusResponse> content = pageData.getContent();
        // final List<StimulusResponse> contentDistinct = new ArrayList<>();
        // for (StimulusResponse stimulusResponse : content) {
        //     if (!contentDistinct.contains(stimulusResponse)) {
        //         contentDistinct.add(stimulusResponse);
        //     }
        // }
        // model.addAttribute("allStimulusResponses", contentDistinct);
        // model.addAttribute("pageData", pageData);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("dataChannel", dataChannel);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        return "stimulusresponseviewer";
    }
}
