/*
 * Copyright (C) 2022 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * @since Feb 27, 2022 11:56 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class BuildController {

    @RequestMapping("/builds")
    public String buildListing(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "builds");
        return "design";
    }

    @RequestMapping(
            value = "/buildhistory.json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    String buildHistory() throws IOException {
//        File buildhistory = new File("/FrinexBuildService/artifacts/buildhistory.json");
//        return new String(Files.readAllBytes(buildhistory.toPath()));
        return WebClient.create("http://frinexbuild.mpi.nl/buildhistory.json")
                .get()
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON)
                .header("user-agent", "FrinexWizard")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @RequestMapping(
            value = "/buildlisting.js",
            produces = "application/javascript"
    )
    public @ResponseBody
    ResponseEntity<StreamingResponseBody> buildHistoryJS() throws IOException {
//        File buildhistory = new File("/FrinexBuildService/artifacts/buildlisting.js");
//        return new String(Files.readAllBytes(buildhistory.toPath()));
        return WebClient.create("http://frinexbuild.mpi.nl/buildlisting.js")
                .get()
                .header("user-agent", "FrinexWizard")
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }
}
