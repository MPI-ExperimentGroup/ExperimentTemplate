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
    public String parseTrialsPicturesStringIntoXml(String csvString, String baseDir, String pictureStimuliDir) throws Exception {

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

            String item = record.get("Item");
            if (item == null) {
                throw new IOException("Item is undefined");
            } else {
                item = item.trim();
            }

            String logFrequency = record.get("Log_frequency");
            if (logFrequency == null) {
                throw new IOException("Log_frequency is undefined");
            } else {
                logFrequency = logFrequency.trim();
            }
            logFrequency = logFrequency.replaceAll("\\,", "_comma_");

            String syllables = record.get("Syllables");
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            } else {
                syllables = syllables.trim();
            }

            String correctResponse = record.get("Correct_response");
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            }
            if (correctResponse.trim().equals("left")) {
                correctResponse = "Z";
            } else {
                if (correctResponse.trim().equals("right")) {
                    correctResponse = "M";
                } else {
                    throw new IOException("Correct_response is defined incorrectly");
                }
            }

            String picture = record.get("Picture").trim();
            if (picture == null) {
                throw new IOException("Picture is undefined");
            } else {
                picture = picture.trim();
            }

            try {
                //Path picturePath = Paths.get(originalDir + "pictures/" + picture);
                BufferedReader br1 = new BufferedReader(new FileReader(baseDir + pictureStimuliDir + picture));
                br1.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String imagePath = pictureStimuliDir + picture;
            String uniqueId = "part1_" + trialNumber + "_" + item;
            String label = item;
            String tags = "part1  target_frequency_" + logFrequency + " syllables_" + syllables;
            String currentSt = this.makePictureStimulusString(uniqueId, label, correctResponse, imagePath, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makePictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String imagePath,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" imagePath=\"").append(imagePath).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

    //"Trial;Condition;Item;Gender;Log_frequency_target;Log_frequency_distractor;Syllables_target;Syllables_distractor;Picture_target;Picture_distractor;Position_target;Wav;determiner_onset;target_onset;Timer;Correct_response\n"
    public String parseTrialsAudioPicturesStringIntoXml(String csvString, String pictureStimuliDir, String audioStimuliDir, String codeStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            //Trial,Block,Item,Gender,Log_frequency_target,Log_frequency_distractor,
            String trialNumber = record.get("Trial");
            if (trialNumber == null) {
                throw new IOException("Trial is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            String condition = record.get("Condition");
            if (condition == null) {
                throw new IOException("Condition is undefined");
            } else {
                condition = condition.trim();
            }
            condition =  condition.replaceAll("-", "_");


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

            String logFrequencyTarget = record.get("Log_frequency_target");
            if (logFrequencyTarget == null) {
                throw new IOException("Log_frequency_target is undefined");
            } else {
                logFrequencyTarget = logFrequencyTarget.trim();
            }
            logFrequencyTarget = logFrequencyTarget.replaceAll("\\,", "_comma_");

            String logFrequencyDistractor = record.get("Log_frequency_distractor");
            if (logFrequencyDistractor == null) {
                throw new IOException("Log_frequency_distractor is undefined");
            } else {
                logFrequencyDistractor = logFrequencyDistractor.trim();
            }
            logFrequencyDistractor = logFrequencyDistractor.replaceAll("\\,", "_comma_");

            //Syllables_target,Syllables_distractor,Picture_target,Picture_distractor,
            String syllablesTarget = record.get("Syllables_target");
            if (syllablesTarget == null) {
                throw new IOException("Syllables_target is undefined");
            } else {
                syllablesTarget = syllablesTarget.trim();
            }

            String syllablesDistractor = record.get("Syllables_distractor");
            if (syllablesDistractor == null) {
                throw new IOException("Syllables_distractor is undefined");
            } else {
                syllablesDistractor = syllablesDistractor.trim();
            }

            String pictureTarget = record.get("Picture_target");
            if (pictureTarget == null) {
                throw new IOException("Picture_target is undefined");
            } else {
                pictureTarget = pictureTarget.trim();
            }

            String pictureDistractor = record.get("Picture_distractor");
            if (pictureDistractor == null) {
                throw new IOException("Picture_distractor is undefined");
            } else {
                pictureDistractor = pictureDistractor.trim();
            }

            //Position_target,Wav,determiner_onset,target_onset,Timer,Correct_response
            String positionTarget = record.get("Position_target");
            if (positionTarget == null) {
                throw new IOException("Position_target is undefined");
            } else {
                positionTarget = positionTarget.trim();
            }

            String correctResponse = record.get("Correct_response");
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            }
            if (correctResponse.trim().equals("left")) {
                correctResponse = "Z";
            } else {
                if (correctResponse.trim().equals("right")) {
                    correctResponse = "M";
                } else {
                    throw new IOException("Correct_response is defined incorrectly");
                }
            }

            String audio = record.get("Wav");
            if (audio == null) {
                throw new IOException("Wav is undefined");
            } else {
                audio = audio.trim();
            }
            if (audio.endsWith(".wav")) {
                int length = audio.length();
                audio = audio.substring(0, length - 4);
            }

            String determinerOnset = record.get("determiner_onset");
            if (determinerOnset == null) {
                throw new IOException("determiner_onset is undefined");
            } else {
                determinerOnset = determinerOnset.trim();
            }

            determinerOnset = determinerOnset.replaceAll("\\,", "_comma_");

            String targetOnset = record.get("target_onset");
            if (targetOnset == null) {
                throw new IOException("target_onset is undefined");
            } else {
                targetOnset = targetOnset.trim();
            }

            targetOnset = targetOnset.replaceAll("\\,", "_comma_");

            String timer = record.get("Timer");
            if (timer == null) {
                throw new IOException("Timer is undefined");
            } else {
                timer = timer.trim();
            }
            timer = timer.replaceAll("\\,", "_comma_");

            String uniqueId = "part2_" + trialNumber + "_" + item;
            // creating patternly-named copies of images 
            try {
                Path sourceTarget = Paths.get(baseDir + pictureStimuliDir + pictureTarget);
                Path sourceDistractor = Paths.get(baseDir + pictureStimuliDir + pictureDistractor);
                Path destTarget = Paths.get(baseDir + codeStimuliDir + uniqueId + "_target.png");
                Path destDistractor = Paths.get(baseDir + codeStimuliDir + uniqueId + "_distractor.png");

                Files.copy(sourceTarget, destTarget, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(sourceDistractor, destDistractor, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1 = new BufferedReader(new FileReader(baseDir + codeStimuliDir + uniqueId + "_target.png"));
                br1.close();
                BufferedReader br2 = new BufferedReader(new FileReader(baseDir + codeStimuliDir + uniqueId + "_distractor.png"));
                br2.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String label = item;

            //Gender,Log_frequency_target,Log_frequency_distractor,Syllables_target,Syllables_distractor,Position_target,determiner_onset,target_onset,Timer
            String tags = "part2 " + positionTarget + 
                    " condition_" +condition +
                    " gender_" + gender + "  Log_frequency_target_" + logFrequencyTarget
                    + "  Log_frequency_distractor_" + logFrequencyDistractor
                    + " Syllables_target_" + syllablesTarget
                    + " Syllables_distractor_" + syllablesDistractor
                    + " Position_target_" + positionTarget
                    + " determiner_onset_" + determinerOnset
                    + " target_onset_" + targetOnset
                    + " timer_" + timer;

            String audioPath = audioStimuliDir + audio;
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponse, audioPath, uniqueId, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeAudioPictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String audioPath,
            String code,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");

        retVal.append(" audioPath=\"").append(audioPath).append("\" ");
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
