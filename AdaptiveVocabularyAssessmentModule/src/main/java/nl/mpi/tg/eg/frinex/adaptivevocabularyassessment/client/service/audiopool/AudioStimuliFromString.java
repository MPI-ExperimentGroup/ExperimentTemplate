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
import java.util.HashMap;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.common.model.Stimulus.Tag;

/**
 *
 * @author olhshk
 */
public class AudioStimuliFromString {

    private LinkedHashMap<String, AudioAsStimulus> hashedStimuli = new LinkedHashMap<String, AudioAsStimulus>();
    private LinkedHashMap<Integer, Trial> trials = new LinkedHashMap<Integer, Trial>();

    private String audiPathDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/audioas2/stimuli/";

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

    //Nr;Word;Target_nonword;Syllables;Condition;Length_list;Word1;Word2;Word3;Word4;Word5;Word6;Position_target;Noise_level;Position_foil;
    private LinkedHashMap<Integer, Trial> parseTrialsInputCSVStringIntoTrialsArray(String csvString, ArrayList<String> fileNameExtensions, HashMap<String, String> bandIndexing) throws Exception {

        LinkedHashMap<Integer, Trial> retVal = new LinkedHashMap<Integer, Trial>();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        int countNonFoundStimuli = 0;

        for (LinkedHashMap<String, String> record : records) {

            String trialNumber = record.get("Nr").trim();
            if (trialNumber == null) {
                throw new IOException(trialNumber + "is undefined");
            }

            String trialWord = record.get("Word").trim();
            if (trialWord == null) {
                throw new IOException(trialWord + "is undefined");
            }

            String trialTargetNonword = record.get("Target_nonword").trim();
            if (trialTargetNonword == null) {
                throw new IOException(trialTargetNonword + "is undefined");
            }

            String trialSyllables = record.get("Syllables").trim();
            if (trialSyllables == null) {
                throw new IOException(trialSyllables + "is undefined");
            }

            String trialCondition = record.get("Condition").trim();
            if (trialCondition == null) {
                throw new IOException(trialCondition + "is undefined");
            }
            String trialLength = record.get("Length_list").trim().substring(0, 1);
            if (trialLength == null) {
                throw new IOException(trialLength + "is undefined");
            }
            int trialLengthInt = Integer.parseInt(trialLength);

            ArrayList<String> words = new ArrayList<String>(trialLengthInt + 1);
            for (int i = 0; i < trialLengthInt + 1; i++) {
                words.add("");
            }

            words.set(0, trialTargetNonword);

            for (int i = 1; i <= trialLengthInt; i++) {
                String filedName = "Word" + i;
                String currentWord = record.get(filedName).trim();
                if (currentWord == null) {
                    throw new IOException(currentWord + "is undefined");
                }
                words.set(i, currentWord);
            }

            String trialPositionTarget = record.get("Position_target").trim();
            if (trialTargetNonword == null) {
                throw new IOException(trialTargetNonword + "is undefined");
            }
            int trialPositionTargetInt = Integer.parseInt(trialPositionTarget);
            if (trialPositionTarget == null) {
                throw new IOException(trialPositionTarget + "is undefined");
            }

            String trialPositionFoil = record.get("Position_foil").trim();
            if (trialPositionFoil == null) {
                throw new IOException(trialPositionFoil + "is undefined");
            }
            int trialPositionFoilInt = Integer.parseInt(trialPositionFoil);

            String bandLabel = record.get("Noise_level").trim();
            if (bandLabel == null) {
                throw new IOException(bandLabel + "is undefined");
            }

            int bandIndex = Integer.parseInt(bandIndexing.get(bandLabel));

            ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli = new ArrayList<BookkeepingStimulus<AudioAsStimulus>>();

            for (int i = 0; i < words.size(); i++) {

                //AudioAsStimulus(String uniqueId, Stimulus.Tag[] tags, String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath,
                //             String ratingLabels, String correctResponses, String bandLabel, int bandIndex, WordType wordType, int posInTrial)
                String wrd = removeFileNameExtensions(words.get(i), fileNameExtensions);
                String suffix;
                int pauseMs = 900;
                WordType wordType;
                String ratingLabels = "";
                String locationInDir;
                if (i == 0) {
                    suffix = "_in_"+trialCondition;
                    wordType = WordType.EXAMPLE_TARGET_NON_WORD;
                    ratingLabels = null;
                    locationInDir = "clear_mono/" + wrd;
                } else {
                    suffix = "_in_"+trialCondition + "_"+bandLabel;
                    locationInDir =  bandLabel + "/" + wrd;
                    if (trialPositionTargetInt == i) {
                        wordType = WordType.TARGET_NON_WORD;
                    } else {
                        if (trialPositionFoilInt == i) {
                            wordType = WordType.FOIL;
                        } else {
                            wordType = WordType.NON_WORD;
                        }
                    }
                }
                
                
                String audioPath = "static/stimuli/"+locationInDir;
                String uniqueId = wrd + "_" +wordType + "_" + suffix;

                AudioAsStimulus stimulus = new AudioAsStimulus(uniqueId, new Tag[0], wrd, "", pauseMs, audioPath, null, null, ratingLabels, "", bandLabel, bandIndex, wordType, i);
                stimulus.hasRatingLabels();
                BookkeepingStimulus<AudioAsStimulus> bStimulus = new BookkeepingStimulus<AudioAsStimulus>(stimulus);
                stimuli.add(bStimulus);
                hashedStimuli.put(uniqueId, stimulus);

                //sanity check if the files exist
                String wav = locationInDir+".wav";
                String mp3 = locationInDir+".mp3";
                String ogg = locationInDir+".ogg";
                
                try {
                    
                    BufferedReader br = new BufferedReader(new FileReader(this.audiPathDir + wav));
                    //System.out.println(audioPath);
                    br.close();
                    BufferedReader br1 = new BufferedReader(new FileReader(this.audiPathDir + mp3));
                    br1.close();
                    BufferedReader br2 = new BufferedReader(new FileReader(this.audiPathDir + ogg));
                    br2.close();

                } catch (FileNotFoundException ex) {
                    countNonFoundStimuli++;
                    System.out.println();
                    System.out.println("Not found file number " + countNonFoundStimuli);
                    System.out.println("Trial " + Integer.parseInt(trialNumber));
                    System.out.println(ex);

                }
            }

            TrialCondition tc = null;
            switch (trialCondition) {
                case "Target-only":
                    tc = TrialCondition.TARGET_ONLY;
                    break;
                case "Target+Foil":
                    tc = TrialCondition.TARGET_AND_FOIL;
                    break;
                case "NoTarget":
                    tc = TrialCondition.NO_TARGET;
                    break;
                default:
                    break;
            }

            //public Trial(int id, String word, String cueFile, int nOfSyllables, TrialCondition condition, int length, String bandLabel, int bandIndex, ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli) throws Exception{
            Trial nextTrial = new Trial(Integer.parseInt(trialNumber), trialWord, removeFileNameExtensions(trialTargetNonword, fileNameExtensions), Integer.parseInt(trialSyllables), tc,
                    Integer.parseInt(trialLength), bandLabel, bandIndex,
                    Integer.parseInt(trialPositionTarget), Integer.parseInt(trialPositionFoil), stimuli);
            retVal.put(nextTrial.getId(), nextTrial);

        }
        return retVal;
    }

    public void readTrialsAsCsv(String[] labelling) {
        this.trials = new LinkedHashMap<Integer, Trial>();
        HashMap<String, String> bandIndexing = new HashMap<String, String>();
        for (int i = 0; i < labelling.length; i++) {
            bandIndexing.put(labelling[i], (new Integer(i)).toString());
        }
        try {
            ArrayList<String> fileNameExtensions = new ArrayList<String>(1);
            fileNameExtensions.add("wav");
            System.out.println("Portion 1");
            this.trials = this.parseTrialsInputCSVStringIntoTrialsArray(TrialsCsv1.CSV_CONTENT, fileNameExtensions, bandIndexing);
            System.out.println("Portion 2");
            LinkedHashMap<Integer, Trial> trials2 = this.parseTrialsInputCSVStringIntoTrialsArray(TrialsCsv2.CSV_CONTENT, fileNameExtensions, bandIndexing);
            System.out.println("Portion 3");
            LinkedHashMap<Integer, Trial> trials3 = this.parseTrialsInputCSVStringIntoTrialsArray(TrialsCsv3.CSV_CONTENT, fileNameExtensions, bandIndexing);
            System.out.println("Portion 4");
            LinkedHashMap<Integer, Trial> trials4 = this.parseTrialsInputCSVStringIntoTrialsArray(TrialsCsv4.CSV_CONTENT, fileNameExtensions, bandIndexing);
            System.out.println("Portion 5");
            LinkedHashMap<Integer, Trial> trials5 = this.parseTrialsInputCSVStringIntoTrialsArray(TrialsCsv5.CSV_CONTENT, fileNameExtensions, bandIndexing);

            this.trials.putAll(trials2);
            this.trials.putAll(trials3);
            this.trials.putAll(trials4);
            this.trials.putAll(trials5);
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    public LinkedHashMap<String, AudioAsStimulus> getHashedStimuli() {
        return this.hashedStimuli;
    }

    public LinkedHashMap<Integer, Trial> getHashedTrials() {
        return this.trials;
    }
}
