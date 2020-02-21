/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.words2phrases;

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
public class Words2PhrasesStringToXml {
    
     // Block;Trial;Item;Verb;Intro\n
     public String parseTrialsStringIntoXml(String csvString, String stimuliDir, String type) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {
            
            String block = record.get("Block");
            if (block == null) {
                throw new IOException("Block is undefined");
            } else {
                block = block.trim();
            }
            
            
            String trial = record.get("Trial");
            if (trial == null) {
                throw new IOException("Trial is undefined");
            } else {
                trial = trial.trim();
            }

            
            String item = record.get("Item");
            if (item == null) {
                throw new IOException("Item is undefined");
            } else {
                item = item.trim();
            }

            
           
           
            String uniqueId = "Block_" + block + "Trial_" + trial + "_" + item;
            String image = item + ".png";
            String tags = type;

           
            String currentSt = this.makeStimulusString(uniqueId, uniqueId, image, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String image,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        if (label != null) {
            retVal.append(" label=\"").append(label).append("\" ");
        }

        retVal.append(" pauseMs=\"").append("0").append("\" ");

        retVal.append(" imagePath=\"").append(image).append("\" ");

        if (tags != null) {
            retVal.append(" tags=\"").append(tags).append("\" ");
        }

        retVal.append(" />\n");
        return retVal.toString();

    }
}
