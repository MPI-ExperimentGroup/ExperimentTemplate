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
package nl.mpi.tg.eg.frinex.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.mpi.tg.eg.frinex.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Jul 23, 2015 3:18:50 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class ParticipantListingController {

    @RequestMapping("participantlisting")
    public String participantListing(Model model,
            @RequestParam(value = "sort", required = false, defaultValue = "submitDate") String sortColumn,
            @RequestParam(value = "dir", required = false, defaultValue = "a") String sortDirection,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "500", required = false) Integer size,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
//        model.addAttribute("count", this.participantRepository.countDistinctUserId());
//        final Page<Participant> pageData = this.participantRepository.findByStaleCopy(false, PageRequest.of(page, size, ("a".equals(sortDirection)) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn));
//        final List<Participant> content = pageData.getContent();
//        final List<Participant> contentDistinct = new ArrayList<>();
//        for (Participant participant : content) {
//            if (!contentDistinct.contains(participant)) {
//                contentDistinct.add(participant);
//            }
//        }
//        model.addAttribute("allParticipantData", contentDistinct);
        final Participant insertUserData = new Participant(UUID.randomUUID().toString());
        model.addAttribute("insertUserData", insertUserData);
//        model.addAttribute("pageData", pageData);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        return "participantlisting";
    }
}
