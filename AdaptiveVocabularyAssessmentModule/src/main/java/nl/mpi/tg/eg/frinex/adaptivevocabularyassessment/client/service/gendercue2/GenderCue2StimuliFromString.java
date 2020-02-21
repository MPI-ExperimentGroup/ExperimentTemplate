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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.gendercue2;

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
public class GenderCue2StimuliFromString {

//Trial;Condition;Item;Gender;Log_frequency_target;Log_frequency_distractor;Syllables_target;Syllables_distractor;Picture_left;Picture_right;
//Position_target;Wav;determiner_onset;determiner_offset;determiner_duration;target_onset;target_offset;
//target_duration;Timer;Correct_response
    public String parseTrialsAudioPicturesStringIntoXml(String csvString, String pictureStimuliDir, String audioStimuliDir, String codeStimuliDir, String baseDir) throws Exception {

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

            String condition = record.get("Condition");
            if (condition == null) {
                throw new IOException("Condition is undefined");
            } else {
                condition = condition.trim();
            }

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

            String logFrequencyDistractor = record.get("Log_frequency_distractor");
            if (logFrequencyDistractor == null) {
                throw new IOException("Log_frequency_distractor is undefined");
            } else {
                logFrequencyDistractor = logFrequencyDistractor.trim();
            }

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

            String pictureLeft = record.get("Picture_left");
            if (pictureLeft == null) {
                throw new IOException("Picture_left is undefined");
            } else {
                int l = pictureLeft.length();
                pictureLeft = pictureLeft.trim().substring(0, l-4);
            }

            String pictureRight = record.get("Picture_right");
            if (pictureRight == null) {
                throw new IOException("Picture_right is undefined");
            } else {
                int l = pictureRight.length();
                pictureRight = pictureRight.trim().substring(0, l-4);
            }

            //Position_target;Wav;determiner_onset;determiner_offset;determiner_duration;target_onset;target_offset;
            String positionTarget = record.get("Position_target");
            if (positionTarget == null) {
                throw new IOException("Position_target is undefined");
            } else {
                positionTarget = positionTarget.trim();
            }

            String audio = record.get("Wav");
            if (audio == null) {
                throw new IOException("Wav is undefined");
            } else {
                positionTarget = positionTarget.trim();
            }
            if (audio.endsWith("wav")) {
                int length = audio.length();
                audio = audio.substring(0, length - 4);
            }

            String determinerOnset = record.get("determiner_onset");
            if (determinerOnset == null) {
                throw new IOException("determiner_onset is undefined");
            } else {
                determinerOnset = determinerOnset.trim();
            }

            String determinerOffset = record.get("determiner_offset");
            if (determinerOffset == null) {
                throw new IOException("determiner_offset is undefined");
            } else {
                determinerOffset = determinerOffset.trim();
            }

            String determinerDuration = record.get("determiner_duration");
            if (determinerDuration == null) {
                throw new IOException("determiner_duration is undefined");
            } else {
                determinerDuration = determinerDuration.trim();
            }

            String targetOnset = record.get("target_onset");
            if (targetOnset == null) {
                throw new IOException("target_onset is undefined");
            } else {
                targetOnset = targetOnset.trim();
            }

            String targetOffset = record.get("target_offset");
            if (targetOffset == null) {
                throw new IOException("target_offset is undefined");
            } else {
                targetOffset = targetOffset.trim();
            }

            //target_duration;Timer;Correct_response
            String targetDuration = record.get("target_duration");
            if (targetDuration == null) {
                throw new IOException("target_duration is undefined");
            } else {
                targetDuration = targetDuration.trim();
            }

            String timer = record.get("Timer");
            int timerMs;
            if (timer == null) {
                throw new IOException("Timer is undefined");
            } else {
                timer = timer.replace("_", ".");
                Double timerMsDb = Double.valueOf(timer);
                timerMs = (int)Math.round(timerMsDb);
            }

            String correctResponse = record.get("Correct_response");
            String correctResponseButton;
            if (correctResponse == null) {
                throw new IOException("Correct_response is undefined");
            }
            if (correctResponse.trim().equals("2")) {
                correctResponseButton = "Z";
            } else {
                if (correctResponse.trim().equals("1")) {
                    correctResponseButton = "M";
                } else {
                    throw new IOException("correct_response input is out of skope incorrectly, trial " + trialNumber);
                }
            }

            String pictureTarget = null;
            String pictureDistractor = null;
            if (positionTarget.equals("right")) {
                if (!correctResponseButton.equals("M")) {
                    throw new IOException("correctResponseButton is defined incorrectly, trial " + trialNumber + ", position_traget is right but correctResponseButton is " + correctResponseButton);
                }
                pictureTarget = pictureRight+".png";
                pictureDistractor = pictureLeft+".png";
            }
            if (positionTarget.equals("left")) {
                if (!correctResponseButton.equals("Z")) {
                    throw new IOException("correctResponseButton is defined incorrectly, trial " + trialNumber + ", position_traget is left but correctResponseButton is " + correctResponseButton);
                }
                pictureTarget = pictureLeft+".png";
                pictureDistractor = pictureRight+".png";
            }

            if (pictureTarget == null || pictureDistractor == null) {
                throw new IOException("Picture target or Picture distractor are define dincorrectly, trial " + trialNumber);
            }

            String uniqueId = "trial_" + trialNumber + "_" + item + "_" + positionTarget;
            // creating patternly-named copies of images 
            try {
                Path sourceTarget = Paths.get(baseDir + pictureStimuliDir + pictureTarget);
                Path sourceDistractor = Paths.get(baseDir + pictureStimuliDir + pictureDistractor);
                Path destTarget = Paths.get(baseDir + codeStimuliDir + uniqueId + "_target.jpg");
                Path destDistractor = Paths.get(baseDir + codeStimuliDir + uniqueId + "_distractor.jpg");

                Files.copy(sourceTarget, destTarget, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(sourceDistractor, destDistractor, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1 = new BufferedReader(new FileReader(baseDir + codeStimuliDir + uniqueId + "_target.jpg"));
                br1.close();
                BufferedReader br2 = new BufferedReader(new FileReader(baseDir + codeStimuliDir + uniqueId + "_distractor.jpg"));
                br2.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e);
            }

            String label = uniqueId;

            String round = (condition.startsWith("practice")) ? "practice" : "main";

            //Gender;Log_frequency_target;Log_frequency_distractor;Syllables_target;Syllables_distractor;Picture_left;Picture_right;
            //Position_target;Wav;determiner_onset;determiner_offset;determiner_duration;target_onset;target_offset;
            //target_duration;
            String tags = round + " "
                    + positionTarget + " "
                    + " Gender_" + gender
                    + " Log_frequency_target_" + logFrequencyTarget + " Log_frequency_distractor_" + logFrequencyDistractor
                    + " Syllables_target_" + syllablesTarget + " Syllables_distractor_" + syllablesDistractor
                    + " Picture_left_" + pictureLeft + " Pictures_right_" + pictureRight
                    + " position_target_" + positionTarget
                    + " determiner_onset_" + determinerOnset
                    + " determiner_offset_" + determinerOffset
                    + " determiner_duration_" + determinerDuration
                    + " target_onset_" + targetOnset
                    + " target_offset_" + targetOffset
                    + " target_duration_" + targetDuration
                    + " correctResponse_" + correctResponse.trim();

            String audioPath = audioStimuliDir + audio;
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponseButton, audioPath, uniqueId, tags, timerMs);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeAudioPictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String audioPath,
            String code,
            String tags,
            int timerMs) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"").append(timerMs).append("\" ");

        retVal.append(" audioPath=\"").append(audioPath).append("\" ");
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
