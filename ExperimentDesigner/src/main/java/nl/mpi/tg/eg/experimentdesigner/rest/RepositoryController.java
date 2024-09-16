/*
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import nl.mpi.tg.eg.experimentdesigner.controller.StimulusController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 02 September 2024 11:228 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class RepositoryController {

    private static final Logger LOG = Logger.getLogger(StimulusController.class.getName());
    HashMap<String, Runnable> cloneRunnables = new HashMap<>();

    @RequestMapping("/git/clone/{repositoryName}")
    @ResponseBody
    public ResponseEntity<Resource> repositoryClone(@PathVariable String repositoryName) throws MalformedURLException {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.-]", "");
        File log = new File("/FrinexExperiments/" + repositoryNameCleaned + ".log");
        if (!cloneRunnables.containsKey(repositoryNameCleaned)) {
            cloneRunnables.put(repositoryNameCleaned, new Runnable() {
                @Override
                public void run() {
                    ProcessBuilder builder = new ProcessBuilder(
                            "/bin/bash", "-c",
                            "git clone http://WizardUser:$WizardUserPass@frinexbuild.mpi.nl/wizardgit/"
                            + repositoryNameCleaned + ".git &>> /FrinexExperiments/" + repositoryNameCleaned
                            + ".log; sleep 5;");
                    builder.redirectErrorStream(true);
                    builder.directory(new File("/FrinexExperiments"));
                    // builder.redirectOutput(log);
                    // builder.redirectError(log);
                    try {
                        Process process = builder.start();
                        // BufferedReader bufferedReader = new BufferedReader(new
                        // InputStreamReader(process.getInputStream()));
                        // // TODO: while the data here is limited to the container and behind a
                        // password we might want to filter what returned here from bash
                        // String line;
                        // do {
                        // line = bufferedReader.readLine();
                        // if (line != null) {
                        // response.getWriter().println(line + "<br>");
                        // response.getWriter().flush();
                        // }
                        // } while (line != null);
                        // response.getWriter().flush();
                        process.waitFor();
                    } catch (IOException | InterruptedException exception) {
                        LOG.log(Level.SEVERE, "clone failed", exception);
                    }

                }
            });
            cloneRunnables.get(repositoryNameCleaned).run();
        }
        // try {
        Path path = Paths.get(log.toURI());
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
        // } catch (MalformedURLException exception) {
        // LOG.log(Level.SEVERE, "reading log failed", exception);
        // return ResponseEntity.status(HttpStatus.NOT_FOUND);
        // }
    }

    @RequestMapping("/git/status/{repositoryName}")
    @ResponseBody
    public void repositoryStatus(@PathVariable String repositoryName, HttpServletResponse response) throws MalformedURLException, IOException {
        repositoryCommand(repositoryName, response, "status");
    }

    @RequestMapping("/git/diff/{repositoryName}")
    @ResponseBody
    public void repositoryDiff(@PathVariable String repositoryName, HttpServletResponse response) throws MalformedURLException, IOException {
        repositoryCommand(repositoryName, response, "diff");
    }

    private void repositoryCommand(String repositoryName, HttpServletResponse response, String command) throws MalformedURLException, IOException {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.-]", "");
        ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "git " + command);
        builder.redirectErrorStream(true);
        builder.directory(new File("/FrinexExperiments/" + repositoryNameCleaned));
        Process process = builder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        do {
            line = bufferedReader.readLine();
            if (line != null) {
                response.getWriter().println(line + "<br>");
                response.getWriter().flush();
            }
        } while (line != null);
        response.getWriter().flush();
    }

    private boolean recurseDirectories(File directory, String pathString, StringBuilder stringBuilder, boolean isFirst) {
        // for (File listingFile : workingDirectory.listFiles((File pathname) -> pathname.getName().matches("[A-z0-9_-]*\\.[PpJjOoMmWwXx][NnPpGgPpAaMm][Gg34VvLl]$"))) {
        for (File listingFile : directory.listFiles()) {
            if (listingFile.isDirectory()) {
                isFirst = recurseDirectories(listingFile, pathString + listingFile.getName() + "/", stringBuilder, isFirst);
            } else {
                if (isFirst) {
                    isFirst = false;
                } else {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("\"").append(pathString).append(listingFile.getName()).append("\"");
            }
        }
        return isFirst;
    }

    @RequestMapping("/repository/list/{repositoryName}/{experimentName}")
    @ResponseBody
    public ResponseEntity<String> repositoryList(@PathVariable String repositoryName, @PathVariable String experimentName) {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.-]", "");
        String experimentNameCleaned = experimentName.replaceAll("[^A-z0-9_\\.-]", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        final File repositoryDirectory = new File("/FrinexExperiments/" + repositoryNameCleaned);
        if (repositoryDirectory.isDirectory()) {
            stringBuilder.append("\n\"listing\":[\n");
            boolean isFirst = true;
            if ("*".equals(experimentName)) {
                // recurseDirectories(repositoryDirectory, "/", stringBuilder, isFirst);
                for (File listingFile : repositoryDirectory.listFiles()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",\n");
                    }
                    stringBuilder.append("\"").append(listingFile.getName());
                }
            } else {
                for (File workingDirectory : repositoryDirectory.listFiles((File dir, String name) -> experimentNameCleaned.toLowerCase().equals(name.toLowerCase()))) {
                    if (workingDirectory.isDirectory()) {
                        isFirst = recurseDirectories(workingDirectory, "/" + workingDirectory.getName() + "/", stringBuilder, isFirst);
                    }
                }
            }
            stringBuilder.append("]\n");
        } else {
            stringBuilder.append("\"error\": \"Checkout not found: ").append(repositoryNameCleaned).append("/").append(experimentNameCleaned).append("\"");
        }
        stringBuilder.append("}");
        return ResponseEntity.ok().body(stringBuilder.toString());
    }

}
