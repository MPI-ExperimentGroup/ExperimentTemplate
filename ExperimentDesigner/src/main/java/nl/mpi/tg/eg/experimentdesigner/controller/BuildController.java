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
import java.nio.charset.Charset;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @since Feb 27, 2022 11:56 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class BuildController {

    @RequestMapping("/list")
    public String listing(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "listing");
        Principal principal = request.getUserPrincipal();
        model.addAttribute("username", (principal != null) ? principal.getName() : "");
        model.addAttribute("repository", (principal != null) ? "/git/" + principal.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".git" : "");
        return "design";
    }

    @RequestMapping("/repository/{repository}")
    public String repository(Model model, HttpServletRequest request, @PathVariable(required = false) String repository) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "repository");
        model.addAttribute("experimentPath", "*");
        Principal principal = request.getUserPrincipal();
        model.addAttribute("username", (principal != null) ? principal.getName() : "");
        model.addAttribute("repository", (principal != null) ? "/git/" + ((repository == null) ? principal.getName() : repository).replaceAll("[^a-zA-Z0-9]", "_") + ".git" : "");
        return "design";
    }

    @RequestMapping("/repository/{repository}/{experimentPath}")
    public String repositoryExperiment(Model model, HttpServletRequest request, @PathVariable String repository, @PathVariable String experimentPath) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "repository");
        model.addAttribute("experimentPath", experimentPath);
        Principal principal = request.getUserPrincipal();
        model.addAttribute("username", (principal != null) ? principal.getName() : "");
        model.addAttribute("repository", (principal != null) ? "/git/" + repository.replaceAll("[^a-zA-Z0-9]", "_") + ".git" : "");
        return "design";
    }

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
    Flux<String> buildHistory() throws IOException {
        return WebClient.create("http://frinexbuild.mpi.nl/buildhistory.json")
                .get()
                .header("user-agent", "FrinexWizard").header("Accept-Encoding", "gzip")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .map(buffer -> {
                    String string = buffer.toString(Charset.forName("UTF-8"));
                    DataBufferUtils.release(buffer);
                    return string;
                });
    }

    @RequestMapping(
            value = "/buildlisting.js",
            produces = "application/javascript"
    )
    public @ResponseBody
    Mono<byte[]> buildHistoryJS() throws IOException {
//        File buildhistory = new File("/FrinexBuildService/artifacts/buildlisting.js");
//        return new String(Files.readAllBytes(buildhistory.toPath()));
        return WebClient.create("http://frinexbuild.mpi.nl/buildlisting.js")
                .get()
                .header("user-agent", "FrinexWizard")
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(byte[].class);
    }

    @RequestMapping("/repositoryXml/{experimentName}")
    public @ResponseBody
    Mono<byte[]> loadXml(@PathVariable String experimentName) throws IOException {
        return WebClient.create("http://frinexbuild.mpi.nl/" + experimentName + "/" + experimentName + ".xml")
                .get()
                .header("user-agent", "FrinexWizard")
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(byte[].class);
    }
}
