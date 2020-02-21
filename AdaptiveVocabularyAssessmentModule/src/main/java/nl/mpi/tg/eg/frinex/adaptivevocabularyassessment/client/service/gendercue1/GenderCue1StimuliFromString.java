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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.gendercue1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class GenderCue1StimuliFromString {

//Trial;Item;Gender;Log_frequency;Syllables;Correct_response;Picture

    public String parseTrialsAudioPicturesStringIntoXml(String csvString, String stimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            
            String trialNumber = record.get("Trial");
            if (trialNumber == null) {
                throw new IOException("Trial is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }
            
            int number = Integer.parseInt(trialNumber);
            String round = (number <= 4) ? "practice" : "main";
            
            String item = record.get("Item");
            if (item == null) {
                throw new IOException("Item is undefined");
            } else {
                item = item.trim();
            }

            String gender = record.get("Gender");
            if (gender == null) {
                throw new IOException("Gender is undefined");
            } else {
                gender = gender.trim();
            }
            
             String logFrequency = record.get("Log_frequency");
            if (logFrequency == null) {
                throw new IOException("Log_frequency is undefined");
            } else {
                logFrequency = logFrequency.trim();
            }
            
            String syllables = record.get("Syllables");
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            } else {
                syllables = syllables.trim();
            }
           
           
            String correctResponse = record.get("Correct_response");
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            } else {
                correctResponse = correctResponse.trim();
            }

            String picture = record.get("Picture");
            if (picture == null) {
                throw new IOException("Picture is undefined");
            } else {
                picture =picture.trim().replace('_', '.');
            }
            
            
            String correctResponseButton;
           
            if (correctResponse.trim().equals("2")) {
                correctResponseButton = "Z";
            } else {
                if (correctResponse.trim().equals("1")) {
                    correctResponseButton = "M";
                } else {
                    throw new IOException("correct_response input is out of skope incorrectly, trial "+trialNumber);
                }
            }
            
            
         

            String uniqueId = "trial_" + trialNumber + "_" + item+"_"+gender;
            String label = uniqueId;
            

            String tags =  round + " " +
                    " log_Frequency_" + logFrequency + " " +
                    " Syllables_" +syllables;

            String imagePath = baseDir+stimuliDir+picture;
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponseButton, imagePath, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeAudioPictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String imagePath,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        
        retVal.append(" imagePath=\"").append(imagePath).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
