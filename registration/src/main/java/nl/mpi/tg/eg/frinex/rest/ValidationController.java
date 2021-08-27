/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
import java.util.Date;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since Mar 19, 2019 4:52:37 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class ValidationController {

    @Autowired
    ScreenDataRepository screenDataRepository;
    @Autowired
    TimeStampRepository timeStampRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    StimulusResponseRepository stimulusResponseRepository;

    @RequestMapping(value = "/mock_validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> mockValidate(
            @RequestParam("uuid") String uuid,
            @RequestParam("session_steps") String session_steps,
            @RequestParam("session_next_step") String session_next_step,
            @RequestParam("session_id") String session_id,
            @RequestParam("validation_error") String validation_error,
            @RequestParam("session_step") String session_step,
            @RequestParam("applicationversion") String applicationversion,
            @RequestParam("datalog") String datalog) throws IOException {
        final Date tagDate = new java.util.Date();
        tagRepository.save(new TagData(uuid, "mock_validate", "session_steps", (session_steps.length() > 254) ? session_steps.substring(0, 254) : session_steps, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "session_next_step", (session_next_step.length() > 254) ? session_next_step.substring(0, 254) : session_next_step, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "session_id", (session_id.length() > 254) ? session_id.substring(0, 254) : session_id, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "validation_error", (validation_error.length() > 254) ? validation_error.substring(0, 254) : validation_error, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "session_step", (session_step.length() > 254) ? session_step.substring(0, 254) : session_step, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "applicationversion", (applicationversion.length() > 254) ? applicationversion.substring(0, 254) : applicationversion, 0, tagDate));
        tagRepository.save(new TagData(uuid, "mock_validate", "datalog", (datalog.length() > 254) ? datalog.substring(0, 254) : datalog, 0, tagDate));
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"scoredata\": {");
        for (StimulusResponse stimulusResponse : stimulusResponseRepository.findTop1ByUserIdOrderByTotalPotentialScoreDesc(uuid)) {
            stringBuilder.append("\"gamesPlayed\": \"").append(stimulusResponse.getGamesPlayed()).append("\",");
            stringBuilder.append("\"totalScore\": \"").append(stimulusResponse.getTotalScore()).append("\",");
            stringBuilder.append("\"totalPotentialScore\": \"").append(stimulusResponse.getTotalPotentialScore()).append("\",");
            stringBuilder.append("\"currentScoreGroup\": \"").append(stimulusResponse.getScoreGroup()).append("\",");
            stringBuilder.append("\"currentScore\": \"").append(stimulusResponse.getCurrentScore()).append("\",");
            stringBuilder.append("\"correctStreak\": \"").append(stimulusResponse.getCorrectStreak()).append("\",");
            stringBuilder.append("\"errorStreak\": \"").append(stimulusResponse.getErrorStreak()).append("\",");
            stringBuilder.append("\"potentialScore\": \"").append(stimulusResponse.getPotentialScore()).append("\",");
            stringBuilder.append("\"maxScore\": \"").append(stimulusResponse.getMaxScore()).append("\",");
            stringBuilder.append("\"maxErrors\": \"").append(stimulusResponse.getMaxErrors()).append("\",");
            stringBuilder.append("\"maxCorrectStreak\": \"").append(stimulusResponse.getMaxCorrectStreak()).append("\",");
            stringBuilder.append("\"maxErrorStreak\": \"").append(stimulusResponse.getMaxErrorStreak()).append("\",");
            stringBuilder.append("\"maxPotentialScore\": \"").append(stimulusResponse.getMaxPotentialScore()).append("\",");
        }
        stringBuilder.append("},");
        stringBuilder.append("{\"metadata\": ");
        stringBuilder.append("\"validated_uuid\":\"");
        stringBuilder.append(uuid);
        stringBuilder.append("\",\"error_message\":\"this is a mock response\"}}");
        return new ResponseEntity(stringBuilder.toString(), HttpStatus.OK);
    }
}
