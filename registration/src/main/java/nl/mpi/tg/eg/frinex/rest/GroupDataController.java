/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Nov 30, 2016 1:58:25 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class GroupDataController {

    // @Autowired
    // private GroupDataRepository groupDataRepository;
    @RequestMapping("groupdataviewer")
    public String groupDataViewer(Model model,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "500", required = false) Integer size,
            @RequestParam(value = "sort", required = false, defaultValue = "submitDate") String sortColumn,
            @RequestParam(value = "dir", required = false, defaultValue = "a") String sortDirection,
            @RequestParam(value = "groupUUID", required = false) String groupUUID,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
        // model.addAttribute("count", this.groupDataRepository.count());
        // final Page<GroupData> pageData;
        // if (groupUUID != null) {
        //     pageData = this.groupDataRepository.findByGroupUUID(groupUUID, PageRequest.of(page, size, Sort.Direction.ASC, sortColumn));
        // } else {
        //     pageData = this.groupDataRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, sortColumn));
        // }
        // final List<GroupData> content = pageData.getContent();
        // final List<GroupData> contentDistinct = new ArrayList<>();
        // for (GroupData groupData : content) {
        //     if (!contentDistinct.contains(groupData)) {
        //         contentDistinct.add(groupData);
        //     }
        // }
//        model.addAttribute("allGroupData", contentDistinct);
        // model.addAttribute("pageData", pageData);
//        model.addAttribute("stimuliTagExpander", new StimuliTagExpander());
        if (groupUUID != null) {
            model.addAttribute("groupUUID", groupUUID);
        }
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        return "groupdataviewer";
    }
}
