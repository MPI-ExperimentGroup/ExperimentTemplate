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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

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

    
    public String removeFileNameExtensions(String fileName, ArrayList<String> nameExtensions) {

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
    public ArrayList<Trial> parseTrialsInputCSVStringIntoTrialsArray(String csvString, ArrayList<String> fileNameExtensions, HashMap<String, String> bandIndexing) throws Exception {

        ArrayList<Trial> retVal = new ArrayList<Trial>();

        CsvRecords csvWrapper= new  CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String,String>> records = csvWrapper.getRecords();
        for (LinkedHashMap<String,String> record:records) {

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
                String wrd = this.removeFileNameExtensions(words.get(i), fileNameExtensions);
                String audioPath = "stimuli/" + bandLabel + "/" + wrd;
                String uniqueId = wrd+"_"+bandLabel;
                int pauseMs = 900;
                WordType wordType;
                if (i == 0) {
                    pauseMs = 500;
                    wordType = WordType.EXAMPLE_TARGET_NON_WORD;
                } else {
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

                AudioAsStimulus stimulus = new AudioAsStimulus(uniqueId, new Tag[0], wrd, "", pauseMs, audioPath, null, null, "", "", bandLabel, bandIndex, wordType, i);
                BookkeepingStimulus<AudioAsStimulus> bStimulus = new BookkeepingStimulus<AudioAsStimulus>(stimulus);
                stimuli.add(bStimulus);
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
            Trial nextTrial = new Trial(Integer.parseInt(trialNumber), trialWord, trialTargetNonword, Integer.parseInt(trialSyllables), tc,
                    Integer.parseInt(trialLength), bandLabel, bandIndex,
                    Integer.parseInt(trialPositionTarget), Integer.parseInt(trialPositionFoil), stimuli);
            retVal.add(nextTrial);
        }
        return retVal;
    }

}
