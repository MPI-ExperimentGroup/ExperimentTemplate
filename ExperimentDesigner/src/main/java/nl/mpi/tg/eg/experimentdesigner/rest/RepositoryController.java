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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
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
    Runnable cloneRunnable = null;

    @RequestMapping("/repository/clone/{repositoryName}")
    @ResponseBody
    public ResponseEntity<Resource> repositoryClone(@PathVariable String repositoryName) throws MalformedURLException, IOException {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.]", "");
        File log = new File("/FrinexExperiments/" + repositoryNameCleaned + ".log");
        if (cloneRunnable == null) {
            cloneRunnable = new Runnable() {
                @Override
                public void run() {
                    ProcessBuilder builder = new ProcessBuilder(
                            "/bin/bash", "-c", "git clone http://WizardUser:$WizardUserPass@frinexbuild.mpi.nl/wizardgit/" + repositoryNameCleaned + ".git");
                    builder.redirectErrorStream(true);
                    builder.directory(new File("/FrinexExperiments"));
                    builder.redirectOutput(log);
//                    try {
                    Process process = builder.start();
//                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                        // TODO: while the data here is limited to the container and behind a password we might want to filter what returned here from bash
//                        String line;
//                        do {
//                            line = bufferedReader.readLine();
//                            if (line != null) {
//                                response.getWriter().println(line + "<br>");
//                                response.getWriter().flush();
//                            }
//                        } while (line != null);
//                        response.getWriter().flush();
//                    } catch (IOException exception) {
//                        LOG.log(Level.SEVERE, "clone failed", exception);
//                    }

                }
            };
            cloneRunnable.run();
        }
//        try {
        Path path = Paths.get(log.toURI());
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
//        } catch (MalformedURLException exception) {
//            LOG.log(Level.SEVERE, "reading log failed", exception);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND);
//        }
    }

    @RequestMapping("/repository/list/{repositoryName}")
    @ResponseBody
    public ResponseEntity<String> repositoryList(@PathVariable String repositoryName) {
        String repositoryNameCleaned = repositoryName.replaceAll("[^A-z0-9_\\.]", "");
        StringBuilder stringBuilder = new StringBuilder();
        File workingDirectory = new File("/FrinexExperiments/" + repositoryNameCleaned);
        if (workingDirectory.exists()) {
            if (workingDirectory.isDirectory()) {
                for (File listingFile : workingDirectory.listFiles((File pathname) -> pathname.getName().matches("[A-z0-9_-]*\\.[Xx][Mm][Ll]$"))) {
                    stringBuilder.append(listingFile.getName()).append("<br>");
                }
            } else {
                stringBuilder.append("Not a directory: ").append(repositoryNameCleaned);
            }
        } else {
            stringBuilder.append("Directory not found: ").append(repositoryNameCleaned);
        }
        return ResponseEntity.ok().body(stringBuilder.toString());
    }

}
