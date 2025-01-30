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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import nl.mpi.tg.eg.frinex.model.AssignedValue;
import nl.mpi.tg.eg.frinex.model.DataSubmissionResult;
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

    @RequestMapping(value = "/completeValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized ResponseEntity<DataSubmissionResult> completeValue(@RequestBody TagData completedTagData) {
        final ResponseEntity<DataSubmissionResult> responseEntity;
        List<TagData> usersValues = tagRepository.findFirstByUserIdAndEventTagInOrderByTagDateDesc(completedTagData.getUserId(), Set.copyOf(Arrays.asList(new String[]{"assignedValue", "completedValue"})));
        if (usersValues.isEmpty()) {
            // nothing has been assigned so fail here
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if ("completedValue".equals(usersValues.get(0).getEventTag())) {
            if (usersValues.get(0).getTagValue().equals(completedTagData.getTagValue())) {
                // the last assignment has been completed so do nothing but return ok
                responseEntity = new ResponseEntity<>(new DataSubmissionResult(completedTagData.getUserId(), completedTagData.getTagValue(), true), HttpStatus.OK);
            } else {
                // the last assignment has been completed but for a differnt value so fail here
                responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else if ("assignedValue".equals(usersValues.get(0).getEventTag())) {
            if (usersValues.get(0).getTagValue().equals(completedTagData.getTagValue())) {
                // the completed value matches the last assigned so we store the completion and return ok
                tagRepository.save(completedTagData);
                responseEntity = new ResponseEntity<>(new DataSubmissionResult(completedTagData.getUserId(), completedTagData.getTagValue(), true), HttpStatus.OK);
            } else {
                // a differnt value has been assigned so fail here
                responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            // neither completedValue nor assignedValue was found so fail here
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/assignValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized ResponseEntity<DataSubmissionResult> assignValue(@RequestBody TagData tagData) {
        String[] valueOptions = tagData.getTagValue().split(",");
        final ResponseEntity<DataSubmissionResult> responseEntity;
        if (!"assignValue".equals(tagData.getEventTag()) && !"completeValue".equals(tagData.getEventTag())) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            Random randomStream = new Random();
            final String selectedValue;
            // if we have gotten here then the metadata field on the client side must have been cleared, therefore we always assign a value
            List<AssignedValue> assignedValues = tagRepository.countByDistinctByEventTagAndScreenNameAndTagValueIn("assignedValue", tagData.getScreenName(), Set.copyOf(Arrays.asList(valueOptions)));
            List<AssignedValue> completedValues = tagRepository.countByDistinctByEventTagAndScreenNameAndTagValueIn("completedValue", tagData.getScreenName(), Set.copyOf(Arrays.asList(valueOptions)));
            final ArrayList<String> unassignedValues = new ArrayList<>(Arrays.asList(valueOptions));
            for (AssignedValue assignedTag : assignedValues) {
                unassignedValues.remove(assignedTag.getValue());
            }
            if (unassignedValues.isEmpty()) {
                final ArrayList<String> uncompletedValues = new ArrayList<>(Arrays.asList(valueOptions));
                for (AssignedValue completedTag : completedValues) {
                    uncompletedValues.remove(completedTag.getValue());
                }
                if (uncompletedValues.isEmpty()) {
                    // all values have been assigned and completed so we search for the the least completed ones
                    final ArrayList<AssignedValue> leastCompleted = new ArrayList<>();
                    Long minimumCount = null;
                    for (AssignedValue completedTag : completedValues) {
                        if (minimumCount == null) {
                            minimumCount = completedTag.getAssignedCount();
                            leastCompleted.add(completedTag);
                        } else if (minimumCount > completedTag.getAssignedCount()) {
                            minimumCount = completedTag.getAssignedCount();
                            leastCompleted.clear();
                            leastCompleted.add(completedTag);
                        } else if (minimumCount > completedTag.getAssignedCount()) {
                            leastCompleted.add(completedTag);
                        }

                    }
                    // if there is more than one which is the least completed then we chose from them randomly
                    selectedValue = leastCompleted.get(randomStream.nextInt(leastCompleted.size())).getValue();
                } else {
                    // there is a value that has not been completed so we choose a random uncompleted one
                    selectedValue = uncompletedValues.get(randomStream.nextInt(uncompletedValues.size()));
                }
            } else {
                // there is a value that has not been used so we choose a random unassigned one
                selectedValue = unassignedValues.get(randomStream.nextInt(unassignedValues.size()));
            }
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
            final TagData assignedTagData = new TagData(tagData.getUserId(), tagData.getScreenName(), "assignedValue", selectedValue, 0, new Date());
            tagRepository.save(assignedTagData);
            responseEntity = new ResponseEntity<>(new DataSubmissionResult(tagData.getUserId(), selectedValue, true), HttpStatus.OK);
        }
        return responseEntity;
    }
}
