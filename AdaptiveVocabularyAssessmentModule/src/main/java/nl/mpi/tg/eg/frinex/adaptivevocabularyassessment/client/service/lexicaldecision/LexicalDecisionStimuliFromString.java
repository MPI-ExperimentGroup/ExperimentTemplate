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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.lexicaldecision;

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
public class LexicalDecisionStimuliFromString {

    //trial_nr;word;length;freq_zipf;wav;expected
//Trial_Nr;condition;word;frequency;wav;expected;length\n
    public String parseTrialsStringIntoXml(String csvString, String sourceStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("trial_nr");
            if (trialNumber == null) {
                throw new IOException("trial_nr is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            /*String condition = record.get("condition");
            if (condition == null) {
                throw new IOException("condition is undefined");
            } else {
                condition = condition.trim();
            }*/
            String word = record.get("word");
            if (word == null) {
                throw new IOException("word is undefined");
            } else {
                word = word.trim();
            }

            String frequency = record.get("freq_zipf");
            if (frequency == null) {
                throw new IOException("freq_zipf is undefined");
            } else {
                frequency = frequency.trim().replace(".", "_");
            }

            String wav = record.get("wav");
            if (wav == null) {
                throw new IOException("wav is undefined");
            } else {
                wav = wav.trim();
            }

            if (!wav.equals(word + ".wav")) {
                throw new IOException("wav-name " + wav + " does not coincide with the word " + word);
            }

            String expected = record.get("expected");
            if (expected == null) {
                throw new IOException("expected is undefined");
            } else {
                expected = expected.trim();
            }

            String correctResponse = null;

            if (expected.equals("1")) {
                correctResponse = "M";
            } else {
                if (expected.equals("2")) {
                    correctResponse = "Z";
                } else {
                    throw new IOException("expected is erroneously defined: " + expected);
                }
            }

            //String uniqueId = "trial_" + trialNumber + "_" + condition + "_" + word + "_" + expected;
            String uniqueId = "trial_" + trialNumber + "_" + word + "_" + expected;

            //String tags = "condition_" + condition + "  frequency_" + frequency ;
            String tags = "main " + "  freq_zipf_" + frequency;

            // creating patternly-named copies of images 
            try {

                // end creating copies
                // sanity check
                BufferedReader br1ogg = new BufferedReader(new FileReader(baseDir + sourceStimuliDir + word + ".ogg"));
                br1ogg.close();
                BufferedReader br1mp3 = new BufferedReader(new FileReader(baseDir + sourceStimuliDir + word + ".mp3"));
                br1mp3.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String currentSt = this.makeStimulusString(uniqueId, uniqueId, correctResponse, "stimuli/" + word, tags, "0");
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
