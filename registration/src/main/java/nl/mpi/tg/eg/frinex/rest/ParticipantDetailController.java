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
    @Autowired
    private ScreenDataRepository screenDataRepository;
    @Autowired
    private TagPairRepository tagPairRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TimeStampRepository timeStampRepository;

    @RequestMapping("participantdetail")
    public String participantDetail(@RequestParam(value = "id", required = true) String id, Model model) {
        model.addAttribute("participantData", this.participantRepository.findByUserId(id));
        model.addAttribute("participantScreenData", this.screenDataRepository.findByUserIdOrderByViewDateAsc(id));
        model.addAttribute("countOfBrowserWindowClosed", this.screenDataRepository.countByUserIdAndScreenName(id, BROWSER_WINDOW_CLOSED));
        model.addAttribute("participantTagPairData", this.tagPairRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantSubsetStimulus", this.tagPairRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, SUBSET_STIMULUS));
        model.addAttribute("participantCompletionCode", this.tagRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, COMPLETION_CODE));
        model.addAttribute("participantAudioTestCount", this.tagRepository.countByUserIdAndTagValue(id, CARLY_BLUE_CHAIROGG));
        model.addAttribute("participantNextButtonMsData", this.timeStampRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, STIMULUS1_NEXT));
        model.addAttribute("participantTagData", this.tagRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantTimeStampData", this.timeStampRepository.findByUserIdOrderByTagDateAsc(id));
        return "participantdetail";
    }
    private static final String CARLY_BLUE_CHAIROGG = "carly_blue_chair.ogg";
    private static final String COMPLETION_CODE = "CompletionCode";
    private static final String BROWSER_WINDOW_CLOSED = "BrowserWindowClosed";
    private static final String STIMULUS1_NEXT = "stimulus1Next";
    private static final String SUBSET_STIMULUS = "SubsetStimulus";
}
