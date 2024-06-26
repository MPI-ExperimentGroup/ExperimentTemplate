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
package nl.mpi.tg.eg.frinex.rest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import nl.mpi.tg.eg.frinex.model.AssignedValue;
import nl.mpi.tg.eg.frinex.model.TagData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 03 June 2024 16:25 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class AssignedValueController {

    @Autowired
    TagRepository tagRepository;

//    @RequestMapping(value = "/completeValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE){
//        responseEntity = new ResponseEntity<>(new TagData(userId, screenName, "completedValue", tagValue, 0, tagDate), HttpStatus.OK);
//        tagRepository.saveAll(experimentDataList);
//    }
    @RequestMapping(value = "/assignValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssignedValue> assignValue(@RequestBody TagData tagData) {
        String[] valueOptions = tagData.getTagValue().split(",");
        final ResponseEntity responseEntity;
        if (!"assignValue".equals(tagData.getEventTag()) && !"serverValueComplete".equals(tagData.getEventTag())) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
//            List<AssignedValue> assignedValues = tagRepository.countAssignedValues("assignedValue", Set.copyOf(Arrays.asList(valueOptions)));
//            List<AssignedValue> completedValues = tagRepository.countAssignedValues("completedValue", Set.copyOf(Arrays.asList(valueOptions)));
//            AssignedValue assignedRecord = null;
//            for (String currentValue : valueOptions) {
//                int assignedIndex = assignedValues.indexOf(currentValue);
//                int completedIndex = completedValues.indexOf(currentValue);
//                if (assignedIndex == -1) {
//                    if (assignedRecord == null) {
//                        // never been assigned so start with the first option
//                        assignedRecord = new AssignedValue(0, new Date(), currentValue);
//                    } else if (assignedRecord.getAssignedCount() > 0) {
//                        // the previous choice has been assigned more so we replace it
//                        assignedRecord = new AssignedValue(0, new Date(), currentValue);
//                    } else if (new Random().nextInt(0, valueOptions.length) == 0) {
//                        // randomly choose another that has never been assigned
//                        assignedRecord = new AssignedValue(0, new Date(), currentValue);
//                    }
//                } else {
//                    final AssignedValue comparisonAssigned = assignedValues.get(assignedIndex);
//                    if (assignedRecord == null) {
//                        // nothing chosen so start with the first option
//                        assignedRecord = comparisonAssigned;
//                    } else if (assignedRecord.getAssignedCount() > comparisonAssigned.getAssignedCount()) {
//                        // the previous choice has been assigned more so we replace it
//                        assignedRecord = comparisonAssigned;
//                    } else if (assignedRecord.getAssignedCount() == comparisonAssigned.getAssignedCount()) {
//                        // this option has been assigned just as often
//                        int comparisonCompletedIndex = completedValues.indexOf(currentValue);
//                        if (completedIndex == -1 && comparisonCompletedIndex == -1) {
//                            // neither have been completed
//                            if (new Random().nextInt(0, valueOptions.length) == 0) {
//                                // randomly choose the another option
//                                assignedRecord = comparisonAssigned;
//                            }
//                        } else if (comparisonCompletedIndex == -1) {
//                            // the other has not been completed so we choose it
//                            assignedRecord = comparisonAssigned;
//                        } else if (completedIndex == -1) {
//                            // the current choice has not been completed so we keep it
//                        } else {
//                            AssignedValue chosenCompletedRecord = completedValues.get(completedIndex);
//                            AssignedValue otherCompletedRecord = completedValues.get(comparisonCompletedIndex);
//                            if (chosenCompletedRecord.getAssignedCount() == otherCompletedRecord.getAssignedCount()) {
//                                // both have been completed just as often
//                                if (new Random().nextInt(0, valueOptions.length) == 0) {
//                                    // randomly choose the another option
//                                    assignedRecord = comparisonAssigned;
//                                }
//                            } else if (chosenCompletedRecord.getAssignedCount() > otherCompletedRecord.getAssignedCount()) {
//                                // the other has been completed less often so we choose it
//                                assignedRecord = comparisonAssigned;
//                            }
//                        }
//                    }
//                }
//            }
//            if (assignedRecord != null) {
//                final TagData assignedTagData = new TagData(tagData.getUserId(), tagData.getScreenName(), "assignedValue", assignedRecord.getValue(), 0, new Date());
//                tagRepository.save(assignedTagData);
//                responseEntity = new ResponseEntity<>(assignedTagData, HttpStatus.OK);
//            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
//            }
        }
        return responseEntity;
    }
}
