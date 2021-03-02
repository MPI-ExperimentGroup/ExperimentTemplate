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

import nl.mpi.tg.eg.frinex.model.PublicStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    TagRepository tagRepository;
    @Autowired
    ParticipantRepository participantRepository;

    @RequestMapping(value = "/public_usage_stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PublicStatistics> publicUsageStats() {
        final PublicStatistics usageStats = new PublicStatistics();
        usageStats.firstDeploymentAccessed = screenDataRepository.findTop1ByOrderBySubmitDateAsc().getSubmitDate();
        usageStats.totalParticipantsSeen = participantRepository.countDistinctUserId();
        usageStats.totalDeploymentsAccessed = tagRepository.countDistinctCompileDateByEventTag("compileDate");
        usageStats.firstParticipantSeen = participantRepository.findTop1ByOrderBySubmitDateAsc().getSubmitDate();
        usageStats.lastParticipantSeen = participantRepository.findTop1ByOrderBySubmitDateDesc().getSubmitDate();
        usageStats.participantsFirstAndLastSeen = participantRepository.findFirstAndLastUsersAccess();
        usageStats.sessionFirstAndLastSeen = tagRepository.findFirstAndLastSessionAccess();
        return new ResponseEntity<>(usageStats, HttpStatus.OK);
    }
}
