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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.rhymespool;

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
public class RhymesStimuliFromString {

//"TrialNr;Condition;Cue;Cue_audiofile;Target;Target_audiofile;Target_length\n
    public String parseTrialsStringIntoXml(String csvString, String sourceStimuliDir, String codedStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("TrialNr");
            if (trialNumber == null) {
                throw new IOException("TrialNr is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            String condition = record.get("Condition");
            if (condition == null) {
                throw new IOException("Condition is undefined");
            } else {
                condition = condition.trim();
            }

            String cue = record.get("Cue");
            if (cue == null) {
                throw new IOException("Cue is undefined");
            } else {
                cue = cue.trim();
            }

            String target = record.get("Target");
            if (target == null) {
                throw new IOException("Target is undefined");
            } else {
                target = target.trim();
            }

            String correctResponse;
            if (condition.equals("rhyme")) {
                correctResponse = "M";
            } else {
                if (condition.equals("foil")) {
                    correctResponse = "Z";
                } else {
                    if (condition.equals("unrelated")) {
                        correctResponse = "Z";
                    } else {
                        throw new IOException("Correct_response is defined incorrectly");
                    }
                }
            }

            String targetLength = record.get("Target_length");
            if (targetLength == null) {
                throw new IOException("Target_length is undefined");
            } else {
                targetLength = targetLength.trim();
            }

            String uniqueId = "trial_" + trialNumber + "_" + condition + "_cue_" + cue + "_target_" + target;

            String cueAudiofile = record.get("Cue_audiofile");
            if (cueAudiofile == null) {
                throw new IOException("Cue_audiofile; is undefined");
            } else {
                cueAudiofile = cueAudiofile.trim();
            }

            if (!cueAudiofile.equals(cue + ".wav")) {
                throw new IOException("Cue wav-name " + cueAudiofile + " does not coincide with the word " + cue);
            }

            String targetAudiofile = record.get("Target_audiofile");
            if (targetAudiofile == null) {
                throw new IOException("Target_audiofile; is undefined");
            } else {
                targetAudiofile = targetAudiofile.trim();
            }

            if (!targetAudiofile.equals(target + ".wav")) {
                throw new IOException("Target wav-name " + target + " does not coincide with the word " + target);
            }

            // creating patternly-named copies of images 
            try {
                Path source1ogg = Paths.get(baseDir + sourceStimuliDir + cue + ".ogg");
                Path source1mp3 = Paths.get(baseDir + sourceStimuliDir + cue + ".mp3");
                Path source2ogg = Paths.get(baseDir + sourceStimuliDir + target + ".ogg");
                Path source2mp3 = Paths.get(baseDir + sourceStimuliDir + target + ".mp3");
                Path dest1ogg = Paths.get(baseDir + codedStimuliDir + uniqueId + "_cue.ogg");
                Path dest1mp3 = Paths.get(baseDir + codedStimuliDir + uniqueId + "_cue.mp3");
                Path dest2ogg = Paths.get(baseDir + codedStimuliDir + uniqueId + "_target.ogg");
                Path dest2mp3 = Paths.get(baseDir + codedStimuliDir + uniqueId + "_target.mp3");

                Files.copy(source1ogg, dest1ogg, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(source1mp3, dest1mp3, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(source2ogg, dest2ogg, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(source2mp3, dest2mp3, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1ogg = new BufferedReader(new FileReader(baseDir + codedStimuliDir + uniqueId + "_cue.ogg"));
                br1ogg.close();
                BufferedReader br1mp3 = new BufferedReader(new FileReader(baseDir + codedStimuliDir + uniqueId + "_cue.mp3"));
                br1mp3.close();
                BufferedReader br2ogg = new BufferedReader(new FileReader(baseDir + codedStimuliDir + uniqueId + "_target.ogg"));
                br2ogg.close();
                BufferedReader br2mp3 = new BufferedReader(new FileReader(baseDir + codedStimuliDir + uniqueId + "_target.mp3"));
                br2mp3.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String label = uniqueId + "_Target_length_" + targetLength;

            String currentSt = this.makeStimulusString(uniqueId, label, correctResponse, uniqueId, null, "0");
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String code,
            String tags,
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        if (label != null) {
            retVal.append(" label=\"").append(label).append("\" ");
        }

        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        if (tags != null) {
            retVal.append(" tags=\"").append(tags).append("\" ");
        }

        retVal.append(" />\n");
        return retVal.toString();

    }

}
