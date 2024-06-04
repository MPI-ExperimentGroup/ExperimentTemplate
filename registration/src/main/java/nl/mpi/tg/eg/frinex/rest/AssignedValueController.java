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

import java.util.List;
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

    @RequestMapping(value = "/completeValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE){
        responseEntity = new ResponseEntity<>(new TagData(userId, screenName, "completedValue", tagValue, 0, tagDate), HttpStatus.OK);
        tagRepository.saveAll(experimentDataList);
    }
    
    @RequestMapping(value = "/assignValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssignedValue> assignValue(@RequestBody TagData tagData) {
        String[] valueOptions = tagData.getTagValue().split(",");
        final ResponseEntity responseEntity;
        //(valueOptions.length == 0 || 
        if (!"assignValue".equals(tagData.getEventTag()) && !"completedValue".equals(tagData.getEventTag())) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            AssignedValue // TODO: impliment this feature for serverAssignedValue          
                    //    serverAssignedValue targetOptions="list1,list2,list3" fieldName="storageField"
                    //    <serverAssignedValue isComplete="true" fieldName="storageField">
                    //    If I was to add a <serverAssignedValue valueOptions="list1,list2,list3" fieldName="storageField"> feature then there would also need to be a <serverAssignedValue isComplete="true" fieldName="storageField"> that would also be required in the experiment
                    //    any value in fieldName will be overwritten when valueOptions is supplied
                    //    if a value has already be assigned to the participant but not marked completed that value will be returned again which will overwrite any value in fieldName 
                    //    if preceding values returned by the server have been marked as completed then a new value will be returned which will overwrite any value in fieldName 
                    //            tagRepository.countByEventTagGroupBytagValue(userId, eventTag);
                    //            tagRepository.saveAll(experimentDataList);
                     // TODO: tagRepository.findAssignedValues
                                responseEntity = new ResponseEntity<>(new TagData(userId, screenName, "assignedValue", tagValue, 0, tagDate), HttpStatus.OK);
        }
        return responseEntity;
    }
}
