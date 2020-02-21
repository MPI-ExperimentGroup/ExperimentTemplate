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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audiopool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class AudioNonWordMonitoringStimuliFromString {

    //private String audiPathDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/audiononwordmonitoring/stimuli/";
    private String removeFileNameExtensions(String fileName, ArrayList<String> nameExtensions) {

        for (String nameExtension : nameExtensions) {
            String suffix = "." + nameExtension;
            if (fileName.endsWith(suffix)) {
                int nameLength = fileName.length();
                return fileName.substring(0, nameLength - suffix.length());
            }
        }

        return fileName;
    }

     //Order;Round;SNR;Condition;Length_list;Word;Foil_word;Location_foil;Location_target;Cue_nonword;Word1;Word2;Word3;Word4;Word5;Word6
   
    public String parseTrialsInputCSVStringIntoXml(String csvString, ArrayList<String> fileNameExtensions, String stimuliDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        int countNonFoundStimuli = 0;

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("Order").trim();
            if (trialNumber == null) {
                throw new IOException("Order is undefined");
            }

            String round = record.get("Round").trim();
            if (round == null) {
                throw new IOException("Round is undefined");
            }

            String snr = record.get("SNR").trim();
            if (snr == null) {
                throw new IOException("Snr is undefined");
            }

            String trialCondition = record.get("Condition").trim();
            if (trialCondition == null) {
                throw new IOException("Condition is undefined");
            }
            if (trialCondition.equals("Target-only") || trialCondition.equals("Target-Only")) {
                trialCondition = "TargetOnly";
            }
            if (trialCondition.equals("Target+Foil") || trialCondition.equals("Target+foil")) {
                trialCondition = "TargetAndFoil";
            }

            String trialLength = record.get("Length_list").trim().substring(0, 1);
            if (trialLength == null) {
                throw new IOException("Length is undefined");
            }
            int trialLengthInt = Integer.parseInt(trialLength);

            String trialWord = record.get("Word").trim();
            if (trialWord == null) {
                throw new IOException("Word is undefined");
            }

            String foilWord = record.get("Foil_word").trim();
            if (foilWord == null) {
                throw new IOException("Foil_word nonword is undefined");
            }

            ArrayList<String> words = new ArrayList<String>(trialLengthInt + 1);

            if (!trialCondition.equals("NoTarget")) {
                foilWord += "_1";
            }
            words.add(foilWord);

            String locationTarget = record.get("Location_target").trim();
            if (locationTarget == null) {
                throw new IOException("Location_target is undefined");
            }
            int locationTargetInt = Integer.parseInt(locationTarget);

            for (int i = 1; i <= trialLengthInt; i++) {
                String fieldName = "Word" + i;
                String currentWord = record.get(fieldName).trim();
                if (currentWord == null) {
                    throw new IOException(fieldName + " is undefined");
                }

                if (i == locationTargetInt) {
                    currentWord = currentWord + "_2";
                }
                words.add(currentWord);
            }

            String locationFoil = record.get("Location_foil").trim();
            if (locationFoil == null) {
                throw new IOException("Location_foil is undefined");
            }
            int locationFoilInt = Integer.parseInt(locationFoil);

            for (int i = 0; i < words.size(); i++) {

                String wrd = this.removeFileNameExtensions(words.get(i), fileNameExtensions);
                String pathEnd;
                String tags = "trial_" + trialNumber + " round_" + round + " condition_" + trialCondition + " length_" + trialCondition + " word_" + trialWord;
                if (i == 0) {
                    pathEnd  = "mono_scaled/" + wrd;
                    tags = tags + " type_cue";
                } else {
                    pathEnd =  Indices.SNR_TO_DIRNAME.get(snr) + "/" + wrd + "_" + snr;
                    if (locationTargetInt == i) {
                        tags = tags + " type_target_word";
                    } else {
                        if (locationFoilInt == i) {
                            tags = tags + " type_foil";
                        } else {
                            tags = tags + " type_nonword";
                        }
                    }
                }
                
                
                String audioPath = stimuliDir +pathEnd;

                String uniqueId = pathEnd;
                String label = wrd;

                String currentSt = this.makeStimulusString(uniqueId, label, audioPath, tags);
                builder.append(currentSt);

//                 // sanity check if the files exist
//                String wav = pathEnd + ".wav";
//                String mp3 = pathEnd + ".mp3";
//                String ogg = pathEnd + ".ogg";
//                String path = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/monitoring/stimuli/"; // must be the same as in the configuration file
//                try {
//
//                    BufferedReader br = new BufferedReader(new FileReader(path + wav));
//                    //System.out.println(audioPath);
//                    br.close();
//                    BufferedReader br1 = new BufferedReader(new FileReader(path + mp3));
//                    br1.close();
//                    BufferedReader br2 = new BufferedReader(new FileReader(path + ogg));
//                    br2.close();
//
//                } catch (FileNotFoundException ex) {
//                    countNonFoundStimuli++;
//                    System.out.println();
//                    System.out.println("Not found file number " + countNonFoundStimuli);
//                    System.out.println("Trial " + trialNumber);
//                    System.out.println(ex);
//
//                }
           }

        }
        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String audioPath,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");
        
        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" audioPath=\"").append(audioPath).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
