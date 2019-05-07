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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since Jul 23, 2015 3:27:13 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class TimeStampController {

    @Autowired
    private TimeStampRepository timeStampRepository;

    @RequestMapping("timestampviewer")
    public String tagPairViewer(Model model) {
        model.addAttribute("count", this.timeStampRepository.count());
        model.addAttribute("allTimeStampData", this.timeStampRepository.findAllDistinctRecords());
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
        Collections.sort(cleanedDistinctEventTag);
        model.addAttribute("timeStampLabels", cleanedDistinctEventTag);
        return "eventchart";
    }
}
