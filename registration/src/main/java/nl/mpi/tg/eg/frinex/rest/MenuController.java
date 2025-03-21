/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Mar 14, 2019 2:49:44 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class MenuController {

//    @Autowired
//    private AudioDataRepository audioDataRepository;

    @RequestMapping({"/", "/menu"})
    public String menu(Model model,
            HttpServletRequest request,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
        // model.addAttribute("audioDates", this.audioDataRepository.findSubmitDateDistinctByOrderBySubmitDateAsc());
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        model.addAttribute("requestUrl", request.getRequestURL().toString().replaceFirst("^http:", "https:"));
        return "menu";
    }
}
