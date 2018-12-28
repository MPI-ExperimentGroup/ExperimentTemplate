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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.monitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class MonitoringStimuliCodeImage {

    private String tmpDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/monitoring/stimuli/";

    public String parseTrialsCsvToXml(String csvString, String roundNr) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("Order");
            if (trialNumber == null) {
                throw new IOException("Order is undefined");
            } else {
                trialNumber = trialNumber.trim();
            }

            String list = record.get("List");
            if (list == null) {
                throw new IOException("List is undefined in trial " + trialNumber);
            } else {
                list = list.trim();
            }

            String round = record.get("Round");
            if (round == null) {
                throw new IOException("Round is undefined in trial " + trialNumber);
            } else {
                round = round.trim();
            }

            String snr = record.get("SNR");
            if (snr == null) {
                throw new IOException("Snr is undefined in trial " + trialNumber);
            } else {
                snr = snr.trim();
            }

            String trialCondition = record.get("Condition");
            if (trialCondition == null) {
                throw new IOException("Condition is undefined in trial " + trialNumber);
            } else {
                trialCondition = trialCondition.trim();
            }

            if (trialCondition.equals("Target-only") || trialCondition.equals("Target-Only")) {
                trialCondition = "TargetOnly";
            }
            if (trialCondition.equals("Target+Foil") || trialCondition.equals("Target+foil")) {
                trialCondition = "TargetAndFoil";
            }

            String trialLength = record.get("Length_list");
            if (trialLength == null) {
                throw new IOException("Length is undefined in trial " + trialNumber);
            } else {
                trialLength = trialLength.trim().substring(0, 1);
            }

            int trialLengthInt = Integer.parseInt(trialLength);

            Path sourceOgg = null;
            Path sourceMp3 = null;

            String specificLabelFragment = "";

            if (roundNr.equals("1")) {

                String word = record.get("Word");
                if (word == null) {
                    throw new IOException("Word is undefined in trial " + trialNumber);
                } else {
                    word = word.trim();
                }

                String cueNonWord = record.get("Cue_nonword");
                if (cueNonWord == null) {
                    throw new IOException("Cue_nonword nonword is undefined in trial " + trialNumber);
                } else {
                    cueNonWord = cueNonWord.trim();
                }

                sourceOgg = Paths.get(tmpDir + "mono_scaled/" + cueNonWord + ".ogg");
                sourceMp3 = Paths.get(tmpDir + "mono_scaled/" + cueNonWord + ".mp3");

                specificLabelFragment = "Word: " + word + "; Cue_nonword: " + cueNonWord + "; ";

            }

            if (roundNr.equals("2")) {

                String nonword = record.get("Non_word");
                if (nonword == null) {
                    throw new IOException("Non_word is undefined in trial " + trialNumber);
                } else {
                    nonword = nonword.trim();
                }

                String cueWord = record.get("Cue_word");
                if (cueWord == null) {
                    throw new IOException("Cue_word nonword is undefined in trial " + trialNumber);
                } else {
                    cueWord = cueWord.trim();
                }

                sourceOgg = Paths.get(tmpDir + "mono_scaled/" + cueWord + ".ogg");
                sourceMp3 = Paths.get(tmpDir + "mono_scaled/" + cueWord + ".mp3");

                specificLabelFragment = "Nonword: " + nonword + "; Cue_word: " + cueWord + "; ";
            }

            if (roundNr.equals("3")) {

                String cueWord = record.get("Cue_word");
                if (cueWord == null) {
                    throw new IOException("Cue_word nonword is undefined in trial " + trialNumber);
                } else {
                    cueWord = cueWord.trim();
                }
                sourceOgg = Paths.get(tmpDir + "mono_scaled/" + cueWord + ".ogg");
                sourceMp3 = Paths.get(tmpDir + "mono_scaled/" + cueWord + ".mp3");

                String targetSemantic = record.get("Target_semantisch");
                if (targetSemantic == null) {
                    throw new IOException("Target_semantisch is undefined in trial " + trialNumber);
                } else {
                    targetSemantic = targetSemantic.trim();
                }

                String fasF = record.get("fas_f");
                if (fasF == null) {
                    throw new IOException("fas_f is undefined in trial " + trialNumber);
                } else {
                    fasF = fasF.trim();
                }

                String fasA = record.get("fas_a");
                if (fasA == null) {
                    throw new IOException("fas_a is undefined in trial " + trialNumber);
                } else {
                    fasA = fasA.trim();
                }

                specificLabelFragment = "Cue_word: " + cueWord + "; Target_Semantisch:" + targetSemantic + "; fas_f" + fasF + "; fas_a" + fasA + "; ";
            }

            String uniqueId = "List" + list + "_Trial_" + trialNumber + "_roundNr_" + roundNr;

            // creating patternly-named copies 
            String bundles = tmpDir + "coded/";
            Path destOgg = Paths.get(bundles + uniqueId + "_cue.ogg");
            Path destMp3 = Paths.get(bundles + uniqueId + "_cue.mp3");
            try {
                Files.copy(sourceOgg, destOgg, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(sourceMp3, destMp3, StandardCopyOption.REPLACE_EXISTING);
                // end creating copies
                // sanity check
                BufferedReader br1 = new BufferedReader(new FileReader(bundles + uniqueId + "_cue.ogg"));
                br1.close();
                BufferedReader br2 = new BufferedReader(new FileReader(bundles + uniqueId + "_cue.mp3"));
                br2.close();
                // end sanity check 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String trialPositionTarget = record.get("Position_target");
            if (trialPositionTarget == null) {
                throw new IOException("Position target is undefined in trial " + trialNumber);
            }
            trialPositionTarget = trialPositionTarget.trim();
            int trialPositionTargetInt = Integer.parseInt(trialPositionTarget);

            if (trialCondition.equals("TargetOnly")) {
                if (trialPositionTargetInt < 1) {
                    throw new IOException("Inconsistent input data, TargetOnly with no position target.Trial number: " + trialNumber);
                }
            }

            if (roundNr.equals("1") || roundNr.equals("2")) {
                if (trialCondition.equals("TargetAndFoil")) {
                    if (trialPositionTargetInt < 1) {
                        throw new IOException("Inconsistent input data, TargetAndFoil with no position target.Trial number: " + trialNumber);
                    }

                }
            }

            if (trialCondition.equals("NoTarget")) {
                if (trialPositionTargetInt > 0) {
                    throw new IOException("Inconsistent input data, NoTarget with positioned target.Trial number: " + trialNumber);
                }
            }

            String words = "";
            String target = "";
            String subDir = Indices.SNR_TO_DIRNAME.get(snr);
            Integer snrInt = Integer.parseInt(snr);
            for (int i = 1; i <= trialLengthInt; i++) {
                String fieldName = "Word" + i;
                String currentWord = record.get(fieldName);
                if (currentWord == null) {
                    throw new IOException(fieldName + " is undefined in trial " + trialNumber);
                } else {
                    currentWord = currentWord.trim();
                }

                if (trialPositionTargetInt == i) {
                    target = currentWord;
                }

                words += currentWord;
                if (i < trialLengthInt) {
                    words += ", ";
                }

                try {
                    sourceOgg = Paths.get(tmpDir + subDir + "/" + currentWord + "_" + snrInt + ".ogg");
                    sourceMp3 = Paths.get(tmpDir + subDir + "/" + currentWord + "_" + snrInt + ".mp3");
                    destOgg = Paths.get(bundles + uniqueId + "_word_" + i + ".ogg");
                    destMp3 = Paths.get(bundles + uniqueId + "_word_" + i + ".mp3");
                    Files.copy(sourceOgg, destOgg, StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(sourceMp3, destMp3, StandardCopyOption.REPLACE_EXISTING);
                    // end creating copies
                    // sanity check
                    BufferedReader br1 = new BufferedReader(new FileReader(bundles + uniqueId + "_word_" + i + ".ogg"));
                    br1.close();
                    BufferedReader br2 = new BufferedReader(new FileReader(bundles + uniqueId + "_word_" + i + ".mp3"));
                    br2.close();
                    // end sanity check 
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String foilFragment = "";

            if (roundNr.equals("1") || roundNr.equals("2")) {

                String trialPositionFoil = record.get("Position_foil");
                if (trialPositionFoil == null) {
                    throw new IOException("Position foil is undefined in trial " + trialNumber);
                } else {
                    trialPositionFoil = trialPositionFoil.trim();
                }

                foilFragment = "; positionFoil:" + trialPositionFoil;

                int trialPositionFoilInt = Integer.parseInt(trialPositionFoil);

                if (trialCondition.equals("TargetOnly")) {
                    if (trialPositionFoilInt > 0) {
                        throw new IOException("Inconsistent input data, TargetOnly with positioned foil. Trial number: " + trialNumber);
                    }
                }

                if (trialCondition.equals("TargetAndFoil")) {
                    if (trialPositionFoilInt < 1) {
                        throw new IOException("Inconsistent input data, TargetAndFoil with no position foil. Trial number: " + trialNumber);
                    }
                    if (trialPositionTargetInt <= trialPositionFoilInt) {
                        throw new IOException("Inconsistent input data, Foil position is the same or bigger than the target position.Trial number: " + trialNumber);
                    }
                }

                if (trialCondition.equals("NoTarget")) {
                    if (trialPositionFoilInt > 0) {
                        throw new IOException("Inconsistent input data, NoTarget with positioned foil. Trial number: " + trialNumber);
                    }
                }
            }

            // making tags
            String mainOrLearning = (round.equals("0")) ? " learning " : " main ";

            String tags = "List" + list + mainOrLearning + " round" + roundNr;
            int lgth = Integer.parseInt(trialLength);
            if (lgth >= 4) {
                tags += (" length_at_least_4");
            }
            if (lgth >= 5) {
                tags += (" length_at_least_5");
            }
            if (lgth >= 6) {
                tags += (" length_at_least_6");
            }

            // end tags
            String label = specificLabelFragment + "; snr:" + snr + "; " + trialCondition + "; length:" + trialLength + "; positionTarget:" + trialPositionTarget + foilFragment + "; words: " + words;

            String currentSt = this.makeStimulusString(uniqueId, label, uniqueId, tags);
            builder.append(currentSt);

        }
        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String codeAudio,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" code=\"").append(codeAudio).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
