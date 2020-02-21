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

    //Trial_Nr;Category;Target_Word;Syllables;Letters;ZipfF;Prev;PTAN;PTAF;Filename;Target_length;Expected_button
    public String parseTrialsStringIntoXml(String csvString, String sourceStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        int professionsLimit = 4 + 32;
        int counter = 0;
        for (LinkedHashMap<String, String> record : records) {

            counter++;

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

            String letters = record.get("Letters");
            if (letters == null) {
                throw new IOException("Letters is undefined");
            } else {
                letters = letters.trim();
            }

            String zipF = record.get("ZipfF");
            if (zipF == null) {
                throw new IOException("ZipfF is undefined");
            } else {
                zipF = zipF.trim();
                zipF = zipF.replace(".", "_period_");
            }

            String prev = record.get("Prev");
            if (prev == null) {
                throw new IOException("Prev is undefined");
            } else {
                prev = prev.trim();
                prev = prev.replace(".", "_point_");
            }

            String pTAN = record.get("PTAN");
            if (pTAN == null) {
                throw new IOException("PTAN is undefined");
            } else {
                pTAN = pTAN.trim();
                pTAN = pTAN.replace(".", "_point_");
            }

            String pTAF = record.get("PTAF");
            if (pTAF == null) {
                throw new IOException("PTAF is undefined");
            } else {
                pTAF = pTAF.trim();
                pTAF = pTAF.replace(".", "_point_");
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

            String expectedButton = record.get("Expected_button");
            if (expectedButton == null) {
                throw new IOException("Expected_button is undefined");
            } else {
                expectedButton = expectedButton.trim();
            }

            String correctResponse;
            if (expectedButton.equals("1")) {
                correctResponse = "M";
            } else {
                if (expectedButton.equals("2")) {
                    correctResponse = "Z";
                } else {
                    throw new IOException("Expected_button is defined icncorrectly, trial_nr " + trialNumber);
                }
            }

            String targetCategory ;
            if (counter <= professionsLimit) {
                targetCategory = "Professions";
                if (category.equals("Professions")) {
                    if (!correctResponse.equals("M")) {
                        throw new IOException("Correct Response is defined icncorrectly, trial_nr " + trialNumber);
                    }
                } else {
                    if (category.equals("Foil")) {
                        if (!correctResponse.equals("Z")) {
                            throw new IOException("Correct Response is defined icncorrectly, trial_nr " + trialNumber);
                        }
                    } else {
                        throw new IOException("Category is defined icncorrectly, trial_nr " + trialNumber);
                    }
                }
            } else {
                 targetCategory = "vehicles";
                 if (category.equals("vehicles")) {
                    if (!correctResponse.equals("M")) {
                        throw new IOException("Correct Response is defined icncorrectly, trial_nr " + trialNumber);
                    }
                } else {
                    if (category.equals("Foil")) {
                        if (!correctResponse.equals("Z")) {
                            throw new IOException("Correct Response is defined icncorrectly, trial_nr " + trialNumber);
                        }
                    } else {
                        throw new IOException("Category is defined icncorrectly, trial_nr " + trialNumber);
                    }
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
            String uniqueId = targetCategory + "_" + trialNumber + "_" + word+"_"+category;

            String  round = (trialNumber.startsWith("Practice")) ? "practice" : "main";
            String tags = targetCategory + " " + round + " Syllables_" + syllables + " Letters_" + letters + " ZipfF_" + zipF +" Prev_ " + prev + " PTAN_" + pTAN + " PTAF_" + pTAF + " Target_length_" + targetLength;

            String label = uniqueId;

            String currentSt = this.makeStimulusString(uniqueId, label, correctResponse, "stimuli/" + word, tags, "0");
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
