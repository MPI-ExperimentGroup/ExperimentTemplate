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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author olhshk
 */
public class AudioUtils {
    
     // returns list of satisfying the requirements permutation pairs ((type-1, ..., type-tupleSize), (length-1, ..., length-tupleSize)) for a given bandIndex, 
    // such that there ARE trials of type-i of length-i for tuples of tupleSize,  for bandIndex
    public static ArrayList<PermutationPair> initialiseAvailabilityList(Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials,
            ArrayList<ArrayList<Integer>> lengthPermuations, ArrayList<ArrayList<TrialCondition>> trialTypePermutations, int bandIndex, int tupleSize) {

        ArrayList<PermutationPair> retVal = new ArrayList<PermutationPair>();
        for (int i = 0; i < trialTypePermutations.size(); i++) {
            ArrayList<TrialCondition> trialPermutation = trialTypePermutations.get(i);
            for (int j = 0; j < lengthPermuations.size(); j++) {
                ArrayList<Integer> sizePermutation = lengthPermuations.get(j);
                Boolean enough = true;
                int k = 0;
                while (enough && k < tupleSize) {
                    TrialCondition currentTrialCondition = trialPermutation.get(k);
                    Integer currentLength = sizePermutation.get(k);
                    //x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
                    ArrayList<Trial> possibleTrials = trials.get(currentTrialCondition).get(bandIndex).get(currentLength);
                    if (possibleTrials.size() < 1) {
                        enough = false;
                    } else {
                        k++;
                    }
                }
                if (enough) {
                    PermutationPair permPair = new PermutationPair(trialPermutation, sizePermutation);
                    retVal.add(permPair);
                }
            }

        }
        return retVal;
    }

    public static LinkedHashMap<String, WordType> makeClassifiedWordList(String[] wrds, String word, String targetNonWord, String foil) {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        String[] parts1 = word.split("_");
        String wordClean = parts1[0];
        String[] parts2 = targetNonWord.split("_");
        String targetNonWordClean = parts2[0];
        String foilClean = null;
        if (foil != null) {
            String[] parts3 = foil.split("_");
            foilClean = parts3[0];
        }
        for (int i = 0; i < wrds.length; i++) {
            if (wrds[i] != null) {

                String[] parts = wrds[i].split("_");
                WordType wtype = WordType.NON_WORD;
                if (parts[0].equals(wordClean)) {
                    wtype = WordType.WORD;
                }
                if (parts[0].equals(targetNonWordClean)) {
                    wtype = WordType.TARGET_NON_WORD;
                }
                if (foilClean != null) {
                    if (parts[0].equals(foilClean)) {
                        wtype = WordType.FOIL;
                    }
                }
                retVal.put(wrds[i], wtype);
            }

        }
        return retVal;
    }

    public static ArrayList<PermutationPair> emptiedPossibilities(ArrayList<PermutationPair> oldList, Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trialMatrix, int bandIndex, int tupleSize) {
        ArrayList<PermutationPair> toRemove = new ArrayList<PermutationPair>();
        listLoop:
        for (PermutationPair permPair : oldList) {
            for (int i = 0; i < tupleSize; i++) {
                TrialCondition trialType = permPair.trialTypes.get(i);
                Integer length = permPair.trialLengths.get(i);
                ArrayList<Trial> possibilities = trialMatrix.get(trialType).get(bandIndex).get(length);
                if (possibilities.size() < 1) {
                    toRemove.add(permPair);
                    continue listLoop;
                }
            }
        }
        return toRemove;
    }

    private static boolean thereIsSecondPart(String[] parts) {
        if (parts.length < 2) {
            return false;
        }
        if (parts[0] == null) {
            return false;
        }
        if (parts[0].isEmpty()) {
            return false;
        }
        return true;
    }

}

