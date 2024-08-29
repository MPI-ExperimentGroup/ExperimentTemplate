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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/repository")
    public String repositoryPage(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("detailType", "repository");
        Principal principal = request.getUserPrincipal();
        model.addAttribute("username", (principal != null) ? principal.getName() : "");
        model.addAttribute("repository", (principal != null) ? "/git/" + principal.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".git" : "");
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
//        File buildhistory = new File("/FrinexBuildService/artifacts/buildhistory.json");
//        return new String(Files.readAllBytes(buildhistory.toPath()));
        return WebClient.create("http://frinexbuild.mpi.nl/buildhistory.json")
                .get()
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON)
                .header("user-agent", "FrinexWizard")
                .retrieve()
                .bodyToFlux(String.class);
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

    @RequestMapping("/repositoryClone/{repositoryName}")
    @ResponseBody
    public ResponseEntity<String> repositoryClone(@PathVariable String repositoryName) {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.]", "");
        StringBuilder stringBuilder = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder(
                "/bin/bash", "-c", "git clone http://WizardUser:$WizardUserPass@frinexbuild.mpi.nl/wizardgit/" + repositoryNameCleaned + ".git");
        builder.redirectErrorStream(true);
        builder.directory(new File("/FrinexExperiments"));
        try {
            Process process = builder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    stringBuilder.append(line);
                }
            } while (line != null);
        } catch (IOException exception) {
            stringBuilder.append(exception.getMessage());
        }
            return ResponseEntity.ok()
                    .contentLength(stringBuilder.length())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(stringBuilder.toString());
        }
    }
}
