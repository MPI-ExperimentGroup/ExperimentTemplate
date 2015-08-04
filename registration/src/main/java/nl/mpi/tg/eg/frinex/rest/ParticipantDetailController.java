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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Aug 4, 2015 5:38:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class ParticipantDetailController {

    @Autowired
    private ParticipantRepository participantRepository;
    private ScreenDataRepository screenDataRepository;
    private TagPairRepository tagPairRepository;
    private TagRepository tagRepository;
    private TimeStampRepository timeStampRepository;

    @RequestMapping("participantdetail")
    public String greeting(@RequestParam(value = "id", required = true) String userId, Model model) {
        model.addAttribute("count", this.participantRepository.count());
        model.addAttribute("participantData", this.participantRepository.findByUserId(userId));
        model.addAttribute("screenData", this.screenDataRepository.findByUserId(userId));
        model.addAttribute("tagPairData", this.tagPairRepository.findByUserId(userId));
        model.addAttribute("tagData", this.tagRepository.findByUserId(userId));
        model.addAttribute("timeStampData", this.timeStampRepository.findByUserId(userId));
        return "participantdetail";
    }
}
