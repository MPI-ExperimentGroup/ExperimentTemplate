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
import java.util.ArrayList;
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
            @RequestParam Instant from,
            @RequestParam Instant to) {
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(Map.of(
                "target", "screenDataRepository",
                "datapoints", List.of(List.of(screenDataRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "timestampRepository",
                "datapoints", List.of(List.of(timestampRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "tagRepository",
                "datapoints", List.of(List.of(tagRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "tagPairRepository",
                "datapoints", List.of(List.of(tagPairRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "participantRepository",
                "datapoints", List.of(List.of(participantRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "stimulusResponseRepository",
                "datapoints", List.of(List.of(stimulusResponseRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "mediaDataRepository",
                "datapoints", List.of(List.of(mediaDataRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "screenDataRepository",
                "datapoints", List.of(List.of(screenDataRepository.countByTimestampBetween(from, to), to))
        ));
        result.add(Map.of(
                "target", "groupDataRepository",
                "datapoints", List.of(List.of(groupDataRepository.countByTimestampBetween(from, to), to))
        ));

        return result;
    }
}
