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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.corsipool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class CorsiStimuliFromCsvToXml {

//    <stimulus audioPath="COW" code="COW" identifier="COW" imagePath="COW" pauseMs="0" tags="COW"/>
//        <stimulus audioPath="COWmis" code="COWmis" identifier="COWmis" imagePath="COWmis" pauseMs="0" tags="COW"/>
//        <stimulus audioPath="COWmis-5" code="COWmis-5" identifier="COWmis-5" imagePath="COWmis-5" pauseMs="0" tags="COW"/>
//        <stimulus audioPath="FATHER" code="FATHER" identifier="FATHER" imagePath="FATHER" pauseMs="0" tags="FATHER"/>
//        <stimulus audioPath="FATHERmis" code="FATHERmis" identifier="FATHERmis" imagePath="FATHERmis" pauseMs="0" tags="FATHER"/>
//        <stimulus audioPath="GHOST-a" code="GHOST-a" identifier="GHOST-a" imagePath="GHOST-a" pauseMs="0" tags="GHOST_a"/>
//        <stimulus audioPath="GHOST-amis" code="GHOST-amis" identifier="GHOST-amis" imagePath="GHOST-amis" pauseMs="0" tags="GHOST_a"/>
//        <stimulus audioPath="GHOST-b" code="GHOST-b" identifier="GHOST-b" imagePath="GHOST-b" pauseMs="0" tags="GHOST_b"/>
//        <stimulus audioPath="GHOST-bmis" code="GHOST-bmis" identifier="GHOST-bmis" imagePath="GHOST-bmis" pauseMs="0" tags="GHOST_b"/>
//        <stimulus audioPath="PRAY" code="PRAY" identifier="PRAY" imagePath="PRAY" pauseMs="0" tags="PRAY"/>
//        <stimulus audioPath="PRAYmis" code="PRAYmis" identifier="PRAYmis" imagePath="PRAYmis" pauseMs="0" tags="PRAY"/>
//        <stimulus audioPath="SHY" code="SHY" identifier="SHY" imagePath="SHY" pauseMs="0" tags="SHY"/>
//        <stimulus audioPath="SHYmis" code="SHYmis" identifier="SHYmis" imagePath="SHYmis" pauseMs="0" tags="SHY"/>
//   
    public String parseWordsInputCSVStringToXml(String csvString, String appName, String dirName) throws Exception {

        StringBuilder retVal = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        // Trial_id;Blank_stimulis;Letter_stimulus;Stimulus_1;Stimulus_2;Stimulus_3;Stimulus_4;Stimulus_5;Stimulus_6;Stimulus_7;Stimulus_8;Correct_sequence;Voice_announcement;Capture
        int i = 0; // global stimuli counter
        int prefixSize = "Block_".length();

        for (LinkedHashMap<String, String> record : records) {

            String trialId = record.get("Trial_id").trim();
            if (trialId == null) {
                throw new IOException("Trial_id is undefined");
            }

            String blankStimulus = record.get("Blank_stimulis").trim();
            if (blankStimulus == null) {
                throw new IOException("Blank_stimulis string is undefined");
            }

            String letterStimulus = record.get("Letter_stimulus").trim();
            if (letterStimulus == null) {
                throw new IOException("Letter_stimulus is undefined");
            }

            String correctSequence = record.get("Correct_sequence");

            String voiceAnnouncement = record.get("Voice_announcement").trim();
            if (voiceAnnouncement == null) {
                throw new IOException("voiceAnnouncement is missing");
            }

            String label = record.get("Capture").trim();
            if (label == null) {
                throw new IOException("Capture is missing");
            }

            int j = 1;
            ArrayList<String> nonBlankStimuli = new ArrayList<String>();
            while (j <= 8) {
                String currentStrimulus = record.get("Stimulus_" + j).trim();
                if (currentStrimulus == null) {
                    break;
                }
                if (currentStrimulus.isEmpty()) {
                    break;
                }
                j++;
                nonBlankStimuli.add(currentStrimulus);

            }

            if (trialId.startsWith("Block_")) {
                String checkStr = trialId.substring(prefixSize, prefixSize + 1);
                int check = Integer.parseInt(checkStr);
                if (check != nonBlankStimuli.size()) {
                    throw new IOException("The trial's " + trialId + " block size " + check + " is not equal to amount of non-blank stimuli " + nonBlankStimuli.size());
                }
            }

            // voice stimulus which starts the trial 
            i++;
            String uniqueId = appName + "_" + i;
            String currentStimulus = makeStimulusString(appName, uniqueId, label, null, 1500, trialId, null, voiceAnnouncement, dirName, false);
            retVal.append(currentStimulus);

            for (int k = 0; k < nonBlankStimuli.size() - 1; k++) {
                i++; // global stimuli counter
                uniqueId = appName + "_" + i;
                currentStimulus = makeStimulusString(appName, uniqueId, label, null, 1000, trialId, nonBlankStimuli.get(k), null, dirName, false);
                retVal.append(currentStimulus);
                i++; // global stimuli counter
                uniqueId = appName + "_" + i;
                currentStimulus = makeStimulusString(appName, uniqueId, label, null, 1500, trialId, blankStimulus, null, dirName, false);
                retVal.append(currentStimulus);
            }

            i++; // last non blnk stimulus
            uniqueId = appName + "_" + i;
            currentStimulus = makeStimulusString(appName, uniqueId, label, null, 1000,
                    trialId, nonBlankStimuli.get(nonBlankStimuli.size() - 1), null, dirName, false);
            retVal.append(currentStimulus);
            i++; // last non blnk stimulus
            uniqueId = appName + "_" + i;
            currentStimulus = makeStimulusString(appName, uniqueId, label, correctSequence, 0, trialId, letterStimulus, null,
                    dirName, true);
            retVal.append(currentStimulus);

        }

        return retVal.toString();
    }

    private String makeStimulusString(String appName, String uniqueId, String label,
            String correctResponse, int pauseMs, String trialId,
            String imagePath, String audioPath, String dirName,
            boolean isLastStimulus) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");
        if (correctResponse != null) {
            retVal.append(" correctResponses=\"").append(correctResponse.trim()).append("\" ");
        }
        retVal.append(" pauseMs=\"").append(pauseMs).append("\" ");
        if (imagePath != null) {
            retVal.append(" imagePath=\"").append(dirName).append(imagePath).append("\" ");
        }
        if (audioPath != null) {
            retVal.append(" audioPath=\"").append(dirName).append(audioPath).append("\" ");
        }

        String tags = appName + " " + trialId;
        if (isLastStimulus) {
            tags = tags + " letters" + " active ";
        } else {
            tags = tags + " active ";
        }

        retVal.append(" tags=\"").append(tags).append(" \" ");

        retVal.append(" />\n");
        return retVal.toString();

    }
}
