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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.categorisationpool;

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
public class CategorisationStimuliFromString {

    //Nr;Category;Word;Syllables;Letters;SubtlexWF;Prev;PTAN;PTAF;Filename;Target_length
    //Trial_Nr;Category;Target_Word;Syllables;Letters;SubtlexWF;Prev;PTAN;PTAF;Filename;Target_length\n"
    public String parseTrialsStringIntoXml(String csvString, String sourceStimuliDir, String baseDir, String rightCategory, String wrongCategory) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("Trial_Nr");
            if (trialNumber == null) {
                throw new IOException("Trial_Nr is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            String category = record.get("Category");
            if (category == null) {
                throw new IOException("Category is undefined");
            } else {
                category = category.trim();
            }

            String word = record.get("Target_Word");
            if (word == null) {
                throw new IOException("Target_Word is undefined");
            } else {
                word = word.trim();
            }

            String syllables = record.get("Syllables");
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            } else {
                syllables = syllables.trim();
            }

         

            //SubtlexWF;Prev;PTAN;PTAF;Filename
            String subtlexWF = record.get("SubtlexWF");
            if (subtlexWF == null) {
                throw new IOException("SubtlexWF is undefined");
            } else {
                subtlexWF = subtlexWF.trim();
                subtlexWF = subtlexWF.replace(",", "_comma_");
            }

            String prev = "";
            String prevTag = "";
            if (rightCategory.equals("Professions")) {
                prev = record.get("Prev");
                prevTag = " Prev";
            } else {
                prev = record.get("Irt_Prev");
                prevTag = " Irt_Prev";
            }
            if (prev == null) {
                throw new IOException("Prev/Irt_Prev is undefined");
            } else {
                prev = prev.trim();
                prev = prev.replace(",", "_comma_");
            }

            String pTAN = record.get("PTAN");
            if (pTAN == null) {
                throw new IOException("PTAN is undefined");
            } else {
                pTAN = pTAN.trim();
                pTAN = pTAN.replace(",", "_comma_");
            }

            String pTAF = record.get("PTAF");
            if (pTAF == null) {
                throw new IOException("PTAF is undefined");
            } else {
                pTAF = pTAF.trim();
                pTAF = pTAF.replace(",", "_comma_");
            }

            String filename = record.get("Filename");
            if (filename == null) {
                throw new IOException("Filename is undefined");
            } else {
                filename = filename.trim();
                if (!filename.equals(word + ".wav")) {
                    throw new IOException("The name of the file " + filename + " does not coinside with the word " + word);
                }
            }

            String correctResponse;
            if (category.equals(rightCategory)) {
                correctResponse = "M";
            } else {
                if (category.equals(wrongCategory)) {
                    correctResponse = "Z";
                } else {
                    throw new IOException("Category is out of controlled vocabulary");
                }
            }

            String targetLength = record.get("Target_length");
            if (targetLength == null) {
                throw new IOException("Target_length is undefined");
            } else {
                targetLength = targetLength.trim();
            }
            
           

            try {

                BufferedReader br1ogg = new BufferedReader(new FileReader(baseDir + sourceStimuliDir + word + ".ogg"));
                br1ogg.close();
                BufferedReader br1mp3 = new BufferedReader(new FileReader(baseDir + sourceStimuliDir + word + ".mp3"));
                br1mp3.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            String uniqueId = rightCategory + "_" + trialNumber + "_" + word;

            //Syllables;Length;SubtlexWF;Prev;PTAN;PTAF;Filename
            String tags = rightCategory + " Syllables_" + syllables +  " SubtlexWF_" + subtlexWF + prevTag + prev + " PTAN_" + pTAN + " PTAF_" + pTAF;
            
            String label = uniqueId + "_Target_length_" + targetLength;
            
            String currentSt = this.makeStimulusString(uniqueId, label, correctResponse, "stimuli/audiofiles/" + word, tags, "0");
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String audioPath,
            String tags,
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        if (label != null) {
            retVal.append(" label=\"").append(label).append("\" ");
        }

        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" audioPath=\"").append(audioPath).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        if (tags != null) {
            retVal.append(" tags=\"").append(tags).append("\" ");
        }

        retVal.append(" />\n");
        return retVal.toString();

    }
}
