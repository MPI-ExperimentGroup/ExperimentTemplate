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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.sentencecomprehensionpool;

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
public class SentenceComprehensionPicturesStimuliFromString {

    //Trial,Item,Gender,Log_frequency,Syllables,Correct_response,Picture
    public String parseTrialsPicturesStringIntoXml(String csvString, String stimuliDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ",", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("Trial").trim();
            if (trialNumber == null) {
                throw new IOException("Trial is undefined");
            }

            String item = record.get("Item").trim();
            if (item == null) {
                throw new IOException("Item is undefined");
            }

            String logFrequency = record.get("Log_frequency").trim();
            if (logFrequency == null) {
                throw new IOException("Log_frequency is undefined");
            }
            logFrequency = logFrequency.replaceAll("\\.", "");

            String syllables = record.get("Syllables").trim();
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            }

            String correctResponce = record.get("Correct_response").trim();
            if (correctResponce == null) {
                throw new IOException("Correct_response is undefined");
            }

            String picture = record.get("Picture").trim();
            if (picture == null) {
                throw new IOException("Picture is undefined");
            }
            
            String imagePath = stimuliDir + picture;
            String uniqueId = "part1_"+ trialNumber+"_"+item;
            String label = item;
            String tags = "part1  target_frequency_" + logFrequency + " syllables_" + syllables;
            String currentSt = this.makePictureStimulusString(uniqueId, label, imagePath, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makePictureStimulusString(String uniqueId,
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

    public String parseTrialsAudioPicturesStringIntoXml(String csvString, String originalDir, String stimuliDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ",", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            //Trial,Block,Item,Gender,Log_frequency_target,Log_frequency_distractor,
            String trialNumber = record.get("Trial").trim();
            if (trialNumber == null) {
                throw new IOException("Trial is undefined");
            }

            String block = record.get("Block").trim();
            if (block == null) {
                throw new IOException("Block is undefined");
            }

            String item = record.get("Item").trim();
            if (item == null) {
                throw new IOException("Item is undefined");
            }

            String gender = record.get("Gender").trim();
            if (gender == null) {
                throw new IOException("Item is undefined");
            }

            String logFrequencyTarget = record.get("Log_frequency_target").trim();
            if (logFrequencyTarget == null) {
                throw new IOException("Log_frequency_target is undefined");
            }
            logFrequencyTarget = logFrequencyTarget.replaceAll("\\.", "");

            String logFrequencyDistractor = record.get("Log_frequency_distractor").trim();
            if (logFrequencyDistractor == null) {
                throw new IOException("Log_frequency_distractor is undefined");
            }
            logFrequencyDistractor = logFrequencyDistractor.replaceAll("\\.", "");

            //Syllables_target,Syllables_distractor,Picture_target,Picture_distractor,
            String syllablesTarget = record.get("Syllables_target").trim();
            if (syllablesTarget == null) {
                throw new IOException("Syllables_target is undefined");
            }
            String syllablesDistractor = record.get("Syllables_distractor").trim();
            if (syllablesDistractor == null) {
                throw new IOException("Syllables_distractor is undefined");
            }

            String pictureTarget = record.get("Picture_target").trim();
            if (pictureTarget == null) {
                throw new IOException("Picture_target is undefined");
            }

            String pictureDistractor = record.get("Picture_distractor").trim();
            if (pictureDistractor == null) {
                throw new IOException("Picture_distractor is undefined");
            }

            //Position_target,Wav,determiner_onset,target_onset,Timer,Correct_response
            String positionTarget = record.get("Position_target").trim();
            if (positionTarget == null) {
                throw new IOException("Position_target is undefined");
            }

            String correctResponse = record.get("Correct_response").trim();
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            }

            String audio = record.get("Wav").trim();
            if (audio == null) {
                throw new IOException("Wav is undefined");
            }
            if (audio.endsWith(".wav")) {
                int length = audio.length();
                audio = audio.substring(0, length-4);
            }

            String determinerOnset = record.get("determiner_onset").trim();
            if (determinerOnset == null) {
                throw new IOException("determiner_onset is undefined");
            }

            determinerOnset = determinerOnset.replaceAll("\\.", "");

            String targetOnset = record.get("target_onset").trim();
            if (targetOnset == null) {
                throw new IOException("target_onset is undefined");
            }

            targetOnset = targetOnset.replaceAll("\\.", "");

            String timer = record.get("Timer").trim();
            if (timer == null) {
                throw new IOException("Timer is undefined");
            }
            timer = timer.replaceAll("\\.", "");

            String uniqueId = "part2_" + trialNumber+"_"+item;
            // creating patternly-named copies of images 
            Path sourceTarget = Paths.get(originalDir + "pictures/" + pictureTarget);
            Path sourceDistractor = Paths.get(originalDir + "pictures/" + pictureDistractor);
            Path destTarget = Paths.get(stimuliDir + uniqueId + "_target.png");
            Path destDistractor = Paths.get(stimuliDir + uniqueId + "_distractor.png");
            try {
                Files.copy(sourceTarget, destTarget, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(sourceDistractor, destDistractor, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1 = new BufferedReader(new FileReader(stimuliDir + uniqueId + "_target.png"));
                br1.close();
                BufferedReader br2 = new BufferedReader(new FileReader(stimuliDir + uniqueId + "_distractor.png"));
                br2.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e);
                return e.getMessage();
            }
            
            // creating patternly-named copies of audio 
            Path sourceTargetOgg = Paths.get(originalDir + "audiofiles/" + audio+".ogg");
            Path sourceTargetMp3 = Paths.get(originalDir + "audiofiles/" + audio+".mp3");
            Path destTargetOgg = Paths.get(stimuliDir + uniqueId + "_target.ogg");
            Path destTargetMp3 = Paths.get(stimuliDir + uniqueId + "_target.mp3");
            try {
                Files.copy(sourceTargetOgg, destTargetOgg, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(sourceTargetMp3, destTargetMp3, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1 = new BufferedReader(new FileReader(stimuliDir + uniqueId + "_target.ogg"));
                br1.close();
                BufferedReader br2 = new BufferedReader(new FileReader(stimuliDir + uniqueId + "_target.mp3"));
                br2.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e);
                return e.getMessage();
            }

            
            
            String label = item;
            
            //Gender,Log_frequency_target,Log_frequency_distractor,Syllables_target,Syllables_distractor,Position_target,determiner_onset,target_onset,Timer
            String tags = "oart1 gender_"+gender + "  Log_frequency_target_" + logFrequencyTarget + 
                    "  Log_frequency_distractor_" + logFrequencyDistractor +
                    " Syllables_target_" + syllablesTarget + 
                    " Syllables_distractor_" + syllablesDistractor +
                    " Position_target_" + positionTarget + 
                    " determiner_onset_" + determinerOnset + 
                    " target_onset_" + targetOnset + 
                     " timer_" + timer;
            
            
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponse, uniqueId,tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeAudioPictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String code,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");
        
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
