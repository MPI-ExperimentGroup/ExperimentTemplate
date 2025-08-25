/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import nl.mpi.tg.eg.frinex.model.MediaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 * @since Aug 13, 2018 5:16:42 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class MediaDataController {

    @Autowired
    private MediaDataRepository mediaDataRepository;
    @Autowired
    private MediaDataService mediaDataService;

    @RequestMapping(value = "audio/{userId}_{screenName}_{stimulusId}_{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public void getAudio(@PathVariable("userId") String userId, @PathVariable("screenName") String screenName, @PathVariable("stimulusId") String stimulusId, @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {
        getMedia(userId, screenName, stimulusId, id, response);
    }

    @RequestMapping(value = "media/{userId}_{screenName}_{stimulusId}_{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public void getMedia(@PathVariable("userId") String userId, @PathVariable("screenName") String screenName, @PathVariable("stimulusId") String stimulusId, @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {
        final MediaData mediaData = this.mediaDataRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Audio not found"));
        // TODO: video/ogv is not quite correct and should be video/ogg
        String extension = mediaData.getRecordingFormat().name().toLowerCase();
        String mediaType = mediaData.isVideo() ? "video" : "audio";
        String filename = mediaType + "/" + userId + "_" + screenName + "_" + stimulusId + "_" + id + "." + extension;
        response.setContentType(mediaType + "/" + extension);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        mediaDataService.streamToResponse(response.getOutputStream(), mediaData);
    }

    @RequestMapping("medialisting")
    public String participantListing(Model model,
            @RequestParam(value = "sort", required = false, defaultValue = "submitDate") String sortColumn,
            @RequestParam(value = "dir", required = false, defaultValue = "a") String sortDirection,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "100", required = false) Integer size,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        return "medialisting";
    }
}
