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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.konopkapool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
// Nr;Item;Condition;(Patient_)inanimate_animal_human;Location_agent;Picture
//Nr;Item;Condition;Selection_criterion;(Patient_)inanimate_animal_human;Location_agent;Picture
public class KonopkaStimuliFromString {

    public String parseTrialsInputCSVStringIntoXml(String csvString, String stimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String nr = record.get("Nr").trim();
            if (nr == null) {
                throw new IOException("Nr is undefined");
            }
            String type = nr.startsWith("Practice") ? "practice" : "main";

            String item = record.get("Item").trim();
            if (item == null) {
                throw new IOException("Item is undefined");
            }
            item = item.replace(' ', '_');

            String selectionCriterion = record.get("Selection_criterion").trim();
            if (selectionCriterion == null) {
                throw new IOException("Selection_criterion is undefined");
            }

            String condition = record.get("Condition").trim();
            if (condition == null) {
                throw new IOException("Condition is undefined");
            }

            String subdir="";
            /*
            if (selectionCriterion.equals("practice")) {
                if (!type.equals("practice")) {
                    throw new IOException("Mismatch between Nr for practice round and the Selection criterion");
                } else {
                    subdir = "practice/";
                }
            } else {
                if (condition.equals("Filler")) {
                    subdir = "fillers/";
                } else {
                    if (condition.equals("Active")) {
                        subdir = "targets/";
                    } else {
                        if (condition.equals("Passive")) {
                            subdir = "targets/";
                        } else {
                            throw new IOException("Condition in the main round is nether active, nor passive, nor filler in trial"+ nr);
                        }
                    }
                }
            }
*/

            String patient_inanimate_animal_human = record.get("(Patient_)inanimate_animal_human").trim();
            if (patient_inanimate_animal_human == null) {
                throw new IOException("(Patient_)inanimate_animal_human is undefined");
            }

            String location_agent = record.get("Location_agent").trim();
            if (location_agent == null) {
                throw new IOException("Location_agent is undefined");
            }

            String picture = record.get("Picture").trim();
            if (picture == null) {
                throw new IOException("Picture is undefined");
            }

            // corrector
            if (!picture.endsWith(".png")) {
              picture = picture + ".png" ;
            }
            
            
            String imagePath = stimuliDir +subdir+picture;
            
            
            //testing consistencu of stimuli collection
            try {
               
                BufferedReader br1 = new BufferedReader(new FileReader(baseDir + stimuliDir +subdir+picture));
                br1.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println("trial "+nr);
                System.out.println(e);
            }
            
            
            String label = item.replace(" ", "_");
            String uniqueId = "Nr_".concat(nr).concat("__").concat(label);

            String tags = type + " Condition_" + condition + " Selection_criterion_" + selectionCriterion + " Patient_inanimate_animal_human_" + patient_inanimate_animal_human + " Location_agent_" + location_agent;

            String currentSt = this.makeStimulusString(uniqueId, label, imagePath, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String imagePath,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" imagePath=\"").append(imagePath).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
