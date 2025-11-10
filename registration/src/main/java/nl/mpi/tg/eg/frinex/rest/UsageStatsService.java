/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.PublicStatistics;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @since 26 Feb 2021 15:02:08 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class UsageStatsService {

    @Autowired
    ScreenDataRepository screenDataRepository;
    @Autowired
    TimeStampRepository timestampRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagPairRepository tagPairRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    GroupDataRepository groupDataRepository;
    @Autowired
    StimulusResponseRepository stimulusResponseRepository;
    @Autowired
    private MediaDataRepository mediaDataRepository;
    @Autowired
    private DataDeletionLogRepository dataDeletionLogRepository;

    @RequestMapping(value = "/public_usage_stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PublicStatistics> publicUsageStats() {
        final PublicStatistics usageStats = new PublicStatistics();
        final ScreenData screenData = screenDataRepository.findTop1ByOrderBySubmitDateAsc();
        usageStats.firstDeploymentAccessed = (screenData != null) ? screenData.getSubmitDate() : null;
        usageStats.totalParticipantsSeen = participantRepository.countDistinctUserId();
        usageStats.totalDeploymentsAccessed = tagRepository.countDistinctTagValueByEventTag("compileDate");
        usageStats.totalPageLoads = tagRepository.countDistinctDateByEventTag("compileDate");
        usageStats.totalStimulusResponses = stimulusResponseRepository.countDistinctRecords();
        final Participant participantFirst = participantRepository.findTop1ByOrderBySubmitDateAsc();
        usageStats.firstParticipantSeen = (participantFirst != null) ? participantFirst.getSubmitDate() : null;
        final Participant participantLast = participantRepository.findTop1ByOrderBySubmitDateDesc();
        usageStats.lastParticipantSeen = (participantLast != null) ? participantLast.getSubmitDate() : null;
        usageStats.participantsFirstAndLastSeen = participantRepository.findFirstAndLastUsersAccess();
        usageStats.sessionFirstAndLastSeen = tagRepository.findFirstAndLastSessionAccess();
        usageStats.totalMediaResponses = mediaDataRepository.count();
        usageStats.totalDeletionEvents = dataDeletionLogRepository.count();
        return new ResponseEntity<>(usageStats, HttpStatus.OK);
    }

    @JsonInclude(Include.NON_NULL)
    @RequestMapping(value = "/public_quick_stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PublicStatistics> publicQuickStats() {
        final PublicStatistics usageStats = new PublicStatistics();
        usageStats.firstDeploymentAccessed = null;
        usageStats.totalParticipantsSeen = participantRepository.countDistinctUserId();
        usageStats.totalDeploymentsAccessed = tagRepository.countDistinctTagValueByEventTag("compileDate");
        usageStats.totalPageLoads = tagRepository.countDistinctDateByEventTag("compileDate");
        usageStats.totalStimulusResponses = stimulusResponseRepository.countDistinctRecords();
        usageStats.firstParticipantSeen = null;
        usageStats.lastParticipantSeen = null;
        usageStats.participantsFirstAndLastSeen = null;
        usageStats.sessionFirstAndLastSeen = null;
        usageStats.totalMediaResponses = mediaDataRepository.count();
        return new ResponseEntity<>(usageStats, HttpStatus.OK);
    }

    @GetMapping("/public_count_stats")
    public List<Map<String, Object>> getCountsForRepos(
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {

        if (to == null) {
            to = Instant.now();
        }
        if (from == null) {
            from = to.minus(1, ChronoUnit.DAYS);
        }

        long durationMinutes = ChronoUnit.MINUTES.between(from, to);

        long stepMinutes;
        if (durationMinutes <= 120) {
            stepMinutes = 5;
        } else if (durationMinutes <= 24 * 60) {
            stepMinutes = 60;
        } else if (durationMinutes <= 7 * 24 * 60) {
            stepMinutes = 360;
        } else {
            stepMinutes = 1440;
        }

        List<Map<String, Object>> result = new ArrayList<>();

        List<List<Long>> screenDataList = new ArrayList<>();
        List<List<Long>> timestampList = new ArrayList<>();
        List<List<Long>> tagList = new ArrayList<>();
        List<List<Long>> tagPairList = new ArrayList<>();
        List<List<Long>> participantList = new ArrayList<>();
        List<List<Long>> stimulusResponseList = new ArrayList<>();
        List<List<Long>> mediaDataList = new ArrayList<>();
        List<List<Long>> groupDataList = new ArrayList<>();

        Instant current = from;

        while (!current.isAfter(to)) {
            Instant next = current.plus(stepMinutes, ChronoUnit.MINUTES);
            screenDataList.add(List.of(screenDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            timestampList.add(List.of(timestampRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            tagList.add(List.of(tagRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            tagPairList.add(List.of(tagPairRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            participantList.add(List.of(participantRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            stimulusResponseList.add(List.of(stimulusResponseRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            mediaDataList.add(List.of(mediaDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            groupDataList.add(List.of(groupDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)), current.toEpochMilli()));
            current = next;
        }

        result.add(Map.of("target", "screenDataRepository", "datapoints", screenDataList));
        result.add(Map.of("target", "timestampRepository", "datapoints", timestampList));
        result.add(Map.of("target", "tagRepository", "datapoints", tagList));
        result.add(Map.of("target", "tagPairRepository", "datapoints", tagPairList));
        result.add(Map.of("target", "participantRepository", "datapoints", participantList));
        result.add(Map.of("target", "stimulusResponseRepository", "datapoints", stimulusResponseList));
        result.add(Map.of("target", "mediaDataRepository", "datapoints", mediaDataList));
        result.add(Map.of("target", "groupDataRepository", "datapoints", groupDataList));

        return result;
    }
    
    @GetMapping(value = "/public_count_csv", produces = "text/csv")
    public StreamingResponseBody getCountsCsv(
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {

        final Instant toF = (to == null)? Instant.now() : to;
        final Instant fromF = (from == null)? to.minus(1, ChronoUnit.DAYS) : from;

        long durationMinutes = ChronoUnit.MINUTES.between(from, to);

        final long stepMinutes;
        if (durationMinutes <= 120) {
            stepMinutes = 5;
        } else if (durationMinutes <= 24 * 60) {
            stepMinutes = 60;
        } else if (durationMinutes <= 7 * 24 * 60) {
            stepMinutes = 360;
        } else {
            stepMinutes = 1440;
        }
        return outputStream -> {
            outputStream.write("time,target,value".getBytes(StandardCharsets.UTF_8));
            Instant current = fromF;
            while (!current.isAfter(toF)) {
                Instant next = current.plus(stepMinutes, ChronoUnit.MINUTES);
                outputStream.write((current.toEpochMilli() + ",ScreenData," + screenDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",Timestamp," + timestampRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",Tag," + tagRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",TagPair," + tagPairRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",Participant," + participantRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",StimulusResponse," + stimulusResponseRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",MediaData," + mediaDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write((current.toEpochMilli() + ",GroupData," + groupDataRepository.countBySubmitDateBetween(Date.from(current), Date.from(next)) + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                current = next;
            }
        };
    }
}
