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
    
    @RequestMapping("/repository/clone/{repositoryName}")
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
        return ResponseEntity.ok().body(stringBuilder.toString());
    }

}
