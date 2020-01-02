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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.lettercomparison;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class LettercomparisonStimuliFromString {
    
    // Block;Item;Picture;Condition;Letters_top;Letters_bottom;Expected_button
    public String parseTrialsInputCSVStringIntoXml(String csvString, String round) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String block = record.get("Block").trim();
            if (block == null) {
                throw new IOException("Block is undefined");
            }
            
            String item = record.get("Item").trim();
            if (item == null) {
                throw new IOException("item is undefined");
            }
            
            String condition = record.get("Condition").trim();
            if (condition == null) {
                throw new IOException("condition is undefined");
            } else {
                condition = condition.trim();
            }

            String letters_top = record.get("Letters_top").trim();
            if (letters_top == null) {
                throw new IOException("Letters_top is undefined");
            }
            
            String letters_bottom = record.get("Letters_bottom").trim();
            if (letters_bottom == null) {
                throw new IOException("Letters_bottom is undefined");
            }
            
            String expected_button = record.get("Expected_button").trim();
            if (expected_button == null) {
                throw new IOException("Expected_button is undefined");
            } else {
                expected_button = expected_button.trim(); 
            }
            
            if (letters_top.equals(letters_bottom)) {
                if (!expected_button.equals("1")) {
                   throw new IOException("Expected_button is defined incorrectly for equal lettergroups: "+expected_button+" "+letters_top+ " "+letters_bottom);  
                }
                 if (!condition.equals("same")) {
                   throw new IOException("Condition is defined incorrectly for equal lettergroups: "+condition+" "+letters_top+ " "+letters_bottom);  
                }
            } else {
                if (!expected_button.equals("2")) {
                   throw new IOException("Expected_button is defined incorrectly for differentl lettergroups: "+expected_button+" "+letters_top+ " "+letters_bottom);  
                }
                 if (!condition.equals("diff")) {
                   throw new IOException("Condition is defined incorrectly for different lettergroups: "+condition+" "+letters_top+ " "+letters_bottom);  
                } 
            }
            
            

           
            String label = "&lt;span &gt;"+letters_top+"&lt;br&gt;"+letters_bottom+"&lt;/span&gt;";
            String uniqueId = "Item_"+item+"_"+condition+"_"+letters_top+"_"+letters_bottom;
            String tags = round+" "+block;
            String correctResponse = null;
            if (expected_button.equals("1")){
             correctResponse = "M";   
            }
            if (expected_button.equals("2")){
             correctResponse = "Z";   
            }
            if (correctResponse == null) {
                throw new IOException("CorrectResponse is defined incorrectly: "+correctResponse); 
            }

            String currentSt = this.makeStimulusString(uniqueId, label, tags, correctResponse);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String tags,
            String correctResponse) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");
        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }
    
    
}
