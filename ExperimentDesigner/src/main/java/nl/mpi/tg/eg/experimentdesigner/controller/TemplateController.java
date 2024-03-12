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
package nl.mpi.tg.eg.experimentdesigner.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @since 11 March 2024 14:53 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class TemplateController {

    @GetMapping(value = "/template/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource getTemplateXml(@PathVariable final String filename) throws IOException, URISyntaxException {
        final String filenameCleaned = filename.replaceAll("[^0-9A-Za-z-_]*", "");
        Path path = Paths.get(getClass().getResource("/frinex-templates/" + filenameCleaned + ".xml").toURI());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

    @GetMapping(value = "/example/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource getExampleXml(@PathVariable final String filename) throws IOException, URISyntaxException {
        final String filenameCleaned = filename.replaceAll("[^0-9A-Za-z-_]*", "");
        Path path = Paths.get(getClass().getResource("/examples/" + filenameCleaned + ".xml").toURI());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

    @GetMapping(value = "/snippet/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource getExampleXml(@PathVariable final String filename) throws IOException, URISyntaxException {
        final String filenameCleaned = filename.replaceAll("[^0-9A-Za-z-_]*", "");
        Path path = Paths.get(getClass().getResource("/examples/" + filenameCleaned + ".xml").toURI());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }
}
