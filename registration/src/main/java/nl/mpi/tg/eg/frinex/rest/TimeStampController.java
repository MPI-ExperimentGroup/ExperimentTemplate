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
import java.util.Collections;
import java.util.List;
import nl.mpi.tg.eg.frinex.model.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Jul 23, 2015 3:27:13 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class TimeStampController {

    @Autowired
    private TimeStampRepository timeStampRepository;

    @RequestMapping("timestampviewer")
    public String tagPairViewer(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "sort", required = false, defaultValue = "tagDate") String sortColumn,
            @RequestParam(value = "size", defaultValue = "500", required = false) Integer size,
            @RequestParam(value = "dir", required = false, defaultValue = "a") String sortDirection) {
        model.addAttribute("count", this.timeStampRepository.count());
        final Page<TimeStamp> pageData = this.timeStampRepository.findAll(new PageRequest(page, size, ("a".equals(sortDirection)) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn));
        final List<TimeStamp> content = pageData.getContent();
        final List<TimeStamp> contentDistinct = new ArrayList<>();
        for (TimeStamp tagData : content) {
            if (!contentDistinct.contains(tagData)) {
                contentDistinct.add(tagData);
            }
        }
        model.addAttribute("allTimeStampData", contentDistinct);
        model.addAttribute("pageData", pageData);
        return "timestampviewer";
    }

    @RequestMapping("eventchart")
    public String eventChart(Model model) {
        model.addAttribute("count", this.timeStampRepository.count());
        model.addAttribute("allTimeStampData", this.timeStampRepository.findAllDistinctRecords());
        final List<String> cleanedDistinctEventTag = new ArrayList<>();
        for (String item : this.timeStampRepository.findDistinctEventTag()) {
            if (!item.startsWith("http")) {
                cleanedDistinctEventTag.add(item);
            }
        }
        Collections.sort(cleanedDistinctEventTag, String.CASE_INSENSITIVE_ORDER);
        model.addAttribute("timeStampLabels", cleanedDistinctEventTag);
        return "eventchart";
    }
}
