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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.werkwoordenpool;

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
public class WerkwoordenStimuliFromString {

//trial;condition;verb;inflected_verb;target; nchar_target;syllabes_target;Zipf_freq_target;prevalence_target;Picture_left;
//Picture_right;position_target;distractor; nchar_distractor;syllabes_distractor;Zipf_freq_target;prevalence_distractor;
//wav_file;verb_onset;target_onset;timer;correct_response

    public String parseTrialsAudioPicturesStringIntoXml(String csvString, String pictureStimuliDir, String audioStimuliDir, String codeStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            //"trial;condition;verb;inflected_verb;

            String trialNumber = record.get("trial");
            if (trialNumber == null) {
                throw new IOException("trial is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            String condition = record.get("condition");
            if (condition == null) {
                throw new IOException("condition is undefined");
            } else {
                condition = condition.trim();
            }
            condition =  condition.replaceAll("-", "_");

             String verb = record.get("verb");
            if (verb == null) {
                throw new IOException("verb is undefined");
            } else {
                verb = verb.trim();
            }
            
            String inflectedVerb = record.get("inflected_verb");
            if (inflectedVerb == null) {
                throw new IOException("inflected_verb is undefined");
            } else {
                inflectedVerb = inflectedVerb.trim();
            }
           
            //arget; nchar_target;syllabes_target;Zipf_freq_target;prevalence_target;Picture_left;
            
            String target = record.get("target");
            if (target == null) {
                throw new IOException("target is undefined");
            } else {
                target = target.trim();
            }

            String ncharTarget = record.get("nchar_target");
            if (ncharTarget == null) {
                throw new IOException("nchar_target is undefined");
            } else {
                ncharTarget = ncharTarget.trim();
            }
            
            String syllabesTarget = record.get("syllabes_target");
            if (syllabesTarget == null) {
                throw new IOException("syllabes_target is undefined");
            } else {
                syllabesTarget = syllabesTarget.trim();
            }
            
            String zipFTarget = record.get("Zipf_freq_target");
            if (zipFTarget == null) {
                throw new IOException("Zipf_freq_target is undefined");
            } else {
                zipFTarget = zipFTarget.trim();
            }
            
          
            
            String prevalenceTarget = record.get("prevalence_target");
            if (prevalenceTarget == null) {
                throw new IOException("prevalence_target is undefined");
            } else {
                prevalenceTarget = prevalenceTarget.trim();
            }
            
            
            String pictureLeft = record.get("Picture_left");
            if (pictureLeft == null) {
                throw new IOException("Picture_left is undefined");
            } else {
                pictureLeft = pictureLeft.trim();
            }
            
            //Picture_right;position_target;distractor; nchar_distractor;syllabes_distractor;Zipf_freq_distractor;prevalence_distractor;
            
            String pictureRight = record.get("Picture_right");
            if (pictureRight == null) {
                throw new IOException("Picture_right is undefined");
            } else {
                pictureRight = pictureRight.trim();
            }
            
            String positionTarget = record.get("position_target");
            if (positionTarget == null) {
                throw new IOException("position_target is undefined");
            } else {
                positionTarget = positionTarget.trim();
            }
            
           
            String distractor = record.get("distractor");
            if (distractor == null) {
                throw new IOException("distractor is undefined");
            } else {
                distractor = distractor.trim();
            }

            String ncharDistractor = record.get("nchar_distractor");
            if (ncharDistractor == null) {
                throw new IOException("nchar_distractor is undefined");
            } else {
                ncharDistractor = ncharDistractor.trim();
            }
            
            String syllabesDistractor = record.get("syllabes_distractor");
            if (syllabesDistractor == null) {
                throw new IOException("syllabes_distractor is undefined");
            } else {
                syllabesDistractor = syllabesDistractor.trim();
            }
            
            String zipFDistractor = record.get("Zipf_freq_distractor");
            if (zipFDistractor == null) {
                throw new IOException("Zipf_freq_distractor is undefined");
            } else {
                zipFDistractor = zipFDistractor.trim();
            }
            
            String prevalenceDistractor = record.get("prevalence_distractor");
            if (prevalenceDistractor == null) {
                throw new IOException("prevalence_distractor is undefined");
            } else {
                prevalenceDistractor = prevalenceDistractor.trim();
            }
            
            //wav_file;verb_onset;target_onset;timer;correct_response
 
            String audio = record.get("wav_file");
            if (audio == null) {
                throw new IOException("wav_file is undefined");
            } else {
                audio = audio.trim();
            }
            if (audio.endsWith(".wav")) {
                int length = audio.length();
                audio = audio.substring(0, length - 4);
            }
            
             String verbOnset = record.get("verb_onset");
            if (verbOnset == null) {
                throw new IOException("verb_onset is undefined");
            } else {
                verbOnset = verbOnset.trim();
            }

            

            String targetOnset = record.get("target_onset");
            if (targetOnset == null) {
                throw new IOException("target_onset is undefined");
            } else {
                targetOnset = targetOnset.trim();
            }

              
          

            String timer = record.get("timer");
            int timerMs;
            if (timer == null) {
                throw new IOException("timer is undefined");
            } else {
                timer = timer.trim();
                timer = timer.replace("_", ".");
                Double timerMsDb = Double.valueOf(timer);
                timerMs = (int)Math.round(timerMsDb);
            }
           

            String correctResponse = record.get("correct_response");
            String correctResponseButton;
            if (correctResponse == null) {
                throw new IOException("correct_response is undefined");
            }
            if (correctResponse.trim().equals("2")) {
                correctResponseButton = "Z";
            } else {
                if (correctResponse.trim().equals("1")) {
                    correctResponseButton = "M";
                } else {
                    throw new IOException("correct_response input is out of skope incorrectly, trial "+trialNumber);
                }
            }
            
            String pictureTarget=null;
            String pictureDistractor=null;
            if (positionTarget.equals("right")) {
                if (!correctResponseButton.equals("M")){
                   throw new IOException("correctResponseButton is defined incorrectly, trial "+trialNumber + ", position_traget is right but correctResponseButton is "+correctResponseButton); 
                }
                pictureTarget = pictureRight;
                pictureDistractor = pictureLeft;
            }
            if (positionTarget.equals("left")) {
                if (!correctResponseButton.equals("Z")){
                   throw new IOException("correctResponseButton is defined incorrectly, trial "+trialNumber + ", position_traget is left but correctResponseButton is "+correctResponseButton); 
                }
                pictureTarget = pictureLeft;
                pictureDistractor = pictureRight;
            }
            
            if (pictureTarget == null || pictureDistractor == null) {
              throw new IOException("Picture target or Picture distractor are define dincorrectly, trial "+trialNumber); 
            }
         

            String uniqueId = "trial_" + trialNumber + "_" + verb+"_"+positionTarget+"_"+condition;
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

            String label = target;
            
            String round = (condition.startsWith("practice")) ?  "practice" : "main";
           

            String tags =  round + " " +
                    positionTarget + " " +
                    " inflected_verb_" +inflectedVerb 
                    + " target_" + target + " nchar_target_" + ncharTarget 
                    + " syllables_target_" + syllabesTarget
                    + " Zipf_target_" + zipFTarget
                    + " prevalence_target_" + prevalenceTarget
                    + " picture_target_" + pictureTarget.substring(0, pictureTarget.length()-4)
                    + " position_target_" + positionTarget
                    
                    + " distractor_" + distractor + " nchar_distractor_" + ncharDistractor
                    + " syllables_distractor_" + syllabesDistractor
                    + " Zipf_distractor_" +  zipFDistractor
                    + " prevalence_distractor_" + prevalenceDistractor
                    + " picture_distractor_" + pictureDistractor.substring(0, pictureDistractor.length()-4)
                    + " verb_onset_" + verbOnset
                    + " target_onset_" + targetOnset
                    + " timer_" + timerMs
                    + " correctResponse_" + correctResponse.trim();

            String audioPath = audioStimuliDir + audio;
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponseButton, audioPath, uniqueId, tags, Integer.toString(timerMs));
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
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" audioPath=\"").append(audioPath).append("\" ");
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
