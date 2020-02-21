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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.antisaccade;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class AntisaccadeStimuliFromString {

//Phase;Item;Cue;Stimulus;Position_cue;Position_stimulus;Correct_response;Fixation_point_interval_ms
    // &#8592;&#160; &#8593;&#160; &#8594;
    public String parseTrialsStringIntoXml(String csvString) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String phase = record.get("Phase");
            if (phase == null) {
                throw new IOException("Phase is undefined");
            } else {
                phase = phase.trim();
            }
           
            String item = record.get("Item");
            if (item == null) {
                throw new IOException("Item is undefined");
            } else {
                item = item.trim();
            }

            String stimulus = record.get("Stimulus");
            if ( stimulus == null) {
                throw new IOException("Stimulus is undefined");
            } else {
                stimulus = stimulus.trim();
            }
            
            String label=null;
            if (stimulus.equals("arrowleft")) {
                label = "&#8592;";
            } else {
                if (stimulus.equals("arrowup")){
                  label= "&#8593;";  
                } else {
                    if (stimulus.equals("arrowright")) {
                        label= "&#8594;"; 
                    }  else {
                        throw new IOException("Stimulus arrow is ill-defined");
                    }
                }
                 
                
            }

            String positionCue = record.get("Position_cue");
            if (positionCue == null) {
                throw new IOException("Position_cue is undefined");
            } else {
                positionCue = positionCue.trim();
            }
            
            String positionStimulus = record.get("Position_stimulus");
            
            if (positionStimulus == null) {
                throw new IOException("Position_stimulus is undefined");
            } else {
                positionStimulus = positionStimulus.trim();
            }
            
            String correctResponse = record.get("Correct_response");
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            } else {
                correctResponse = correctResponse.trim().toUpperCase();
            }
            
            String pauseMs = record.get("Fixation_point_interval_ms");
            if (pauseMs == null) {
                throw new IOException("Fixation_point_interval_ms is undefined");
            } else {
                pauseMs = pauseMs.trim();
            }
            
            String uniqueId = phase + "_"+item+"_"+stimulus;
            String code = positionStimulus;
            String tags = phase+ " " + stimulus +" " +"cue_" + positionCue + "  pos_stimulus_" + positionStimulus;
            
           

            String currentSt = this.makeStimulusString(uniqueId, label, code, correctResponse, tags, pauseMs);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String code,
            String correctResponse,
            String tags,
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");
         retVal.append(" code=\"").append(code).append("\" ");
         
        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" ratingLabels=\"").append("LEFT,UP,RIGHT").append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        if (tags != null) {
            retVal.append(" tags=\"").append(tags).append("\" ");
        }

        retVal.append(" />\n");
        return retVal.toString();

    }

}
