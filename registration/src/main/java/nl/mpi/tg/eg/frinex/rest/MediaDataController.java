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

import jakarta.servlet.ServletRequest;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import nl.mpi.tg.eg.frinex.model.DataDeletionLog;
import nl.mpi.tg.eg.frinex.model.MediaData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private DataDeletionLogRepository dataDeletionLogRepository;

    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.allowDelete}")
    protected boolean allowDelete;

    @RequestMapping(value = "audio/{userId}_{screenName}_{stimulusId}_{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public void getAudio(@PathVariable("userId") String userId, @PathVariable("screenName") String screenName, @PathVariable("stimulusId") String stimulusId, @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {
        getMedia(userId, screenName, stimulusId, id, response);
    }

    @RequestMapping(value = "media/{userId}_{screenName}_{stimulusId}_{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public void getMedia(@PathVariable("userId") String userId, @PathVariable("screenName") String screenName, @PathVariable("stimulusId") String stimulusId, @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {
        final MediaData mediaData = this.mediaDataRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));
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
    
    @Transactional
    @PostMapping("mediadatadelete")
    /*
        This method will have no effect if the application.properties does not specify allowDelete true.
     */
    public String mediaDataDelete(
            ServletRequest request,
            @RequestParam(value = "providedCheckbox1", required = false) String providedCheckbox1,
            @RequestParam(value = "providedCheckbox2", required = false) String providedCheckbox2,
            @RequestParam(value = "deleteOption", defaultValue = "false", required = false) String deleteOption,
            @RequestParam(value = "deleteBeforeDate", required = false) String deleteBeforeDate,
            Model model,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode) {
        final boolean deleteAudio = "deleteAudio".equals(deleteOption);
        final boolean deleteAll = "deleteAll".equals(deleteOption);
        Instant cutoffDate = null;
        if (deleteBeforeDate != null && !deleteBeforeDate.isEmpty()) {
            LocalDate localDate = LocalDate.parse(deleteBeforeDate);
            cutoffDate = localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        final String requiredCheckbox1 = "Delete media recordings before " + cutoffLocalDate.format(formatter);
        final String requiredCheckbox2 = "I understand that this is permanent and cannot be reverted.";
        model.addAttribute("requiredCheckbox1", requiredCheckbox1);
        model.addAttribute("requiredCheckbox2", requiredCheckbox2);
        model.addAttribute("deleteAudio", deleteAudio);
        model.addAttribute("deleteAll", deleteAll);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("allowDelete", allowDelete);

        final DataDeletionLog pendingDeleteInfo = new DataDeletionLog();
        model.addAttribute("pendingDeleteInfo", pendingDeleteInfo);

        if (allowDelete) {
            if (requiredCheckbox1.equals(providedCheckbox1) && requiredCheckbox2.equals(providedCheckbox2)) {
                final Instant deletionDate = Instant.now();
                final String remoteAddr = request.getRemoteAddr();
                final int lastIndexOf = remoteAddr.lastIndexOf(".");
                final String deletionAddr = (lastIndexOf > 0) ? remoteAddr.substring(0, lastIndexOf) + ".0" : "";
                 // todo: IPv6 is not handled at this stage but it should be striped to 80 bits when added
                // log the ip address that requested this action
//                final TagData deletionLogEntry = new TagData("participantdelete", screenName, eventTag, deletionAddr, eventMs, tagDate);
//                this.tagRepository.save(deletionLogEntry);
                final DataDeletionLog dataDeletionLog = new DataDeletionLog();
                dataDeletionLog.setDeletionDate(deletionDate);
                dataDeletionLog.setDeletionAddr(deletionAddr);

                if (cutoffDate != null) {
                    pendingDeleteInfo.totalMediaResponses = this.mediaDataRepository.deleteBySubmitDateBefore(cutoffDate);
                }
                dataDeletionLog.totalMediaResponses = pendingDeleteInfo.totalMediaResponses;
                if (deleteAudio) {
                    model.addAttribute("deletionSuccess", true);
                    model.addAttribute("dataDeletionLog", dataDeletionLog);
                } else if (deleteAll) {
                    dataDeletionLog.firstDeploymentAccessed = pendingDeleteInfo.firstDeploymentAccessed;
                    dataDeletionLog.totalDeploymentsAccessed = pendingDeleteInfo.totalDeploymentsAccessed;
                    dataDeletionLog.totalPageLoads = pendingDeleteInfo.totalPageLoads;
                    dataDeletionLog.totalStimulusResponses = pendingDeleteInfo.totalStimulusResponses;
                    dataDeletionLog.participantsFirstSeen = pendingDeleteInfo.participantsFirstSeen;
                    dataDeletionLog.participantsLastSeen = pendingDeleteInfo.participantsLastSeen;
                    dataDeletionLog.sessionFirstSeen = pendingDeleteInfo.sessionFirstSeen;
                    dataDeletionLog.sessionLastSeen = pendingDeleteInfo.sessionLastSeen;
                    model.addAttribute("dataDeletionLog", dataDeletionLog);
                    model.addAttribute("deletionSuccess", true);
                } else {
                    model.addAttribute("deletionSuccess", false);
                }
                dataDeletionLogRepository.save(dataDeletionLog);
            } else {
                model.addAttribute("deletionSuccess", false);
            }
        }
        model.addAttribute("countOfAudio", mediaDataRepository.count());
        return "mediadatadelete";
    }
}
