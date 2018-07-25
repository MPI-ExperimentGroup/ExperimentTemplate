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

    //Order;Round;SNR;Condition;Length_list;Word;Target_nonword;Word1;Word2;Word3;Word4;Word5;Word6;Position_target;Position_foil;\n
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

            String trialLength = record.get("Length_list").trim().substring(0, 1);
            if (trialLength == null) {
                throw new IOException("Length is undefined");
            }
            int trialLengthInt = Integer.parseInt(trialLength);

            String trialWord = record.get("Word").trim();
            if (trialWord == null) {
                throw new IOException("Word is undefined");
            }

            String trialTargetNonword = record.get("Target_nonword").trim();
            if (trialTargetNonword == null) {
                throw new IOException("Target nonword is undefined");
            }

            ArrayList<String> words = new ArrayList<String>(trialLengthInt + 1);
            for (int i = 0; i < trialLengthInt + 1; i++) {
                words.add("");
            }

            words.set(0, trialTargetNonword + "_1");

            String trialPositionTarget = record.get("Position_target").trim();
            if (trialPositionTarget == null) {
                throw new IOException("Position target is undefined");
            }
            int trialPositionTargetInt = Integer.parseInt(trialPositionTarget);

            for (int i = 1; i <= trialLengthInt; i++) {
                String fieldName = "Word" + i;
                String currentWord = record.get(fieldName).trim();
                if (currentWord == null) {
                    throw new IOException(fieldName + " is undefined");
                }
                if (i == trialPositionTargetInt) {
                       currentWord = currentWord + "_2";
                }
                words.set(i, currentWord);
            }

            String trialPositionFoil = record.get("Position_foil").trim();
            if (trialPositionFoil == null) {
                throw new IOException("Position foil is undefined");
            }
            int trialPositionFoilInt = Integer.parseInt(trialPositionFoil);

            for (int i = 0; i < words.size(); i++) {

                String wrd = this.removeFileNameExtensions(words.get(i), fileNameExtensions);
                String suffix = "_in_" + trialCondition;
                String ratingLabels = "JA,NEE";
                String correctResponse = "NEE";
                String locationInDir;
                String tags = "trial_" + trialNumber + " round_" + round + " condition_" + trialCondition + " length_" + trialCondition + " word_" + trialWord;
                if (i == 0) {
                    suffix = suffix + "_clear_mono_for_" + snr;
                    ratingLabels = null;
                    correctResponse = null;
                    locationInDir = "clear_mono/" + wrd;
                    tags = tags + " type_cue";
                } else {
                    suffix = suffix + "_" + snr;
                    locationInDir = Indices.SNR_TO_DIRNAME.get(snr) + "/" + wrd + "_" + snr;
                    if (trialPositionTargetInt == i) {
                        tags = tags + " type_target_nonword";
                        correctResponse = "YES";
                    } else {
                        if (trialPositionFoilInt == i) {
                            tags = tags + " type_foil";
                        } else {
                            tags = tags + " type_nonword";
                        }
                    }
                }

                String audioPath = stimuliDir + locationInDir;
                String uniqueId = wrd + "_" + suffix;
                String label = wrd;

                String currentSt = this.makeStimulusString(uniqueId, label, ratingLabels, correctResponse, audioPath, locationInDir, tags);
                builder.append(currentSt);

                //sanity check if the files exist
                String wav = locationInDir + ".wav";
                String mp3 = locationInDir + ".mp3";
                String ogg = locationInDir + ".ogg";
                String audiPathDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/audiononwordmonitoring/stimuli/"; // must be the same as in the configuration file
                try {

                    BufferedReader br = new BufferedReader(new FileReader(audiPathDir + wav));
                    //System.out.println(audioPath);
                    br.close();
                    BufferedReader br1 = new BufferedReader(new FileReader(audiPathDir + mp3));
                    br1.close();
                    BufferedReader br2 = new BufferedReader(new FileReader(audiPathDir + ogg));
                    br2.close();

                } catch (FileNotFoundException ex) {
                    countNonFoundStimuli++;
                    System.out.println();
                    System.out.println("Not found file number " + countNonFoundStimuli);
                    System.out.println("Trial " + trialNumber);
                    System.out.println(ex);

                }
            }

        }
        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String ratingLabels,
            String correctResponse,
            String audioPath,
            String dirName,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");
        if (ratingLabels != null) {
            retVal.append(" raingLabels=\"").append(ratingLabels.trim()).append("\" ");
        }
        if (correctResponse != null) {
            retVal.append(" correctResponses=\"").append(correctResponse.trim()).append("\" ");
        }
        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" audioPath=\"").append(dirName).append(audioPath).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
