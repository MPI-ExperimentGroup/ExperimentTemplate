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
import java.util.Arrays;
import java.util.List;
import nl.mpi.tg.eg.frinex.model.TagPairData;
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

    private ArrayList<String[]> userSummary;
    private ArrayList<String[]> fastTrack;
    private ArrayList<String[]> fineTuning;

    @RequestMapping("participantdetail")
    public String participantDetail(@RequestParam(value = "id", required = true) String id, Model model) {

        List<TagPairData> tagPairData = this.tagPairRepository.findByUserIdOrderByTagDateAsc(id);

        this.fetchCvsTables(tagPairData);

        model.addAttribute("participantData", this.participantRepository.findByUserId(id));
        model.addAttribute("participantScreenData", this.screenDataRepository.findByUserIdOrderByViewDateAsc(id));
        model.addAttribute("countOfBrowserWindowClosed", this.screenDataRepository.countDistinctViewDateByUserIdAndScreenName(id, BROWSER_WINDOW_CLOSED));
        model.addAttribute("userSummary", this.userSummary);
        model.addAttribute("fastTrack", this.fastTrack);
        model.addAttribute("fineTuning", this.fineTuning);
        model.addAttribute("participantTagPairData", this.tagPairRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantSubsetStimulus", this.tagPairRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, SUBSET_STIMULUS));
        model.addAttribute("participantCompletionCode", this.tagRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, COMPLETION_CODE));
        model.addAttribute("participantAudioTestCount", this.tagRepository.countDistinctTagDateByUserIdAndTagValue(id, CARLY_BLUE_CHAIROGG));
        model.addAttribute("participantNextButtonMsData", this.timeStampRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, STIMULUS1_NEXT));
        model.addAttribute("participantTagData", this.tagRepository.findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantTimeStampData", this.timeStampRepository.findByUserIdOrderByTagDateAsc(id));
        return "participantdetail";
    }
    private static final String CARLY_BLUE_CHAIROGG = "carly_blue_chair.ogg";
    private static final String COMPLETION_CODE = "CompletionCode";
    private static final String BROWSER_WINDOW_CLOSED = "BrowserWindowClosed";
    private static final String STIMULUS1_NEXT = "stimulus1Next";
    private static final String SUBSET_STIMULUS = "SubsetStimulus";

    private void fetchCvsTables(List<TagPairData> bulk) {
        this.userSummary = new ArrayList<String[]>();
        this.fineTuning = new ArrayList<String[]>();
        this.fastTrack = new ArrayList<String[]>();
        for (TagPairData tagPairData : bulk) {
            if (tagPairData.getEventTag().equals("user_summary")) {
                this.addCsvRow(this.userSummary, tagPairData);
            } else {
                if (tagPairData.getEventTag().equals("fast_track")) {
                    this.addCsvRow(this.fastTrack, tagPairData);
                } else {
                    if (tagPairData.getEventTag().equals("fine_tuning")) {
                        this.addCsvRow(this.fineTuning, tagPairData);
                    }
                }
            }

        }
    }

    private void addCsvRow(ArrayList<String[]> table, TagPairData tagPairData) {
        String[] row = tagPairData.getTagValue2().split(";");
        String[] csvRow = new String[row.length + 1];
        for (int i = 0; i < row.length; i++) {
            csvRow[i] = row[i];
        }
        String tag1 = tagPairData.getTagValue1();
        if (tag1.endsWith("000000")) {
            String[] empty1 = new String[row.length + 1];
            String[] empty2 = new String[row.length + 1];
            String[] empty3 = new String[row.length + 1];
            table.add(empty1);
            table.add(empty2);
            table.add(empty3);
            csvRow[row.length] = "Screen name";
        } else {
            csvRow[row.length] = tagPairData.getScreenName();
        }
        table.add(csvRow);
    }

}
