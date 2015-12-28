/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.dao.ExperimentRepository;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PublishEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since Dec 23, 2015 1:12:25 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class PublishController {

    @Autowired
    ExperimentRepository experimentRepository;

    @RequestMapping(value = "/listing", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PublishEvents>> registerTimeStamp() {
        final ResponseEntity responseEntity;
        List<PublishEvents> experimentList = new ArrayList<>();
//        final Experiment experiment = new Experiment();
//        experiment.setAppNameInternal("DobesAnnotator");
        for (Experiment experiment : experimentRepository.findAll()) {
            experimentList.add(new PublishEvents(experiment, new Date(), new Date(), PublishEvents.PublishState.editing, true, true, true));
        }
//        experimentList.add(new PublishEvents(experiment, new Date(), new Date(), PublishEvents.PublishState.published, true, true, true));
//        experimentList.add(new PublishEvents(experiment, new Date(), new Date(), PublishEvents.PublishState.testing, true, true, true));
        responseEntity = new ResponseEntity<>(experimentList, HttpStatus.OK);
        return responseEntity;
    }
}
