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
import java.util.HashMap;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;

/**
 *
 * @author olhshk
 */
public class PermutationPair {

    private final ArrayList<TrialCondition> trialConditions;
    private final ArrayList<Integer> trialLengths;
    private final ArrayList<ArrayList<Trial>> trials;

    public PermutationPair(ArrayList<TrialCondition> trialConditions, ArrayList<Integer> trialLengths, ArrayList<ArrayList<Trial>> trials) {
        this.trialConditions = trialConditions;
        this.trialLengths = trialLengths;
        this.trials = trials;
    }

    public ArrayList<TrialCondition> getTrialConditions() {
        return trialConditions;
    }

    public ArrayList<Integer> getTrialLengths() {
        return trialLengths;
    }

    public ArrayList<ArrayList<Trial>> getTrials() {
        return trials;
    }

    public boolean isAvailable() {
        if (this.trials == null) {
            return false;
        }
        int tupleSize = this.trials.size();
        for (int i = 0; i < tupleSize; i++) {
            if (this.trials.get(i).size() < 1) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<PermutationPair>> initialiseAvailabilityList(ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> trials,
            ArrayList<ArrayList<Integer>> lengthPermuations, ArrayList<ArrayList<TrialCondition>> trialTypePermutations, int numberOfBands) {
        ArrayList<ArrayList<PermutationPair>> retVal = new ArrayList<ArrayList<PermutationPair>>(numberOfBands);

        for (int i = 0; i < numberOfBands; i++) {
            ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>> bandTrials = trials.get(i);
            ArrayList<PermutationPair> permCurrentBand = PermutationPair.initialiseAvailabilityListForBand(bandTrials, lengthPermuations, trialTypePermutations);
            retVal.add(i, permCurrentBand);
        }

        return retVal;
    }

    private static ArrayList<PermutationPair> initialiseAvailabilityListForBand(ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>> bandTrials,
            ArrayList<ArrayList<Integer>> lengthPermuations, ArrayList<ArrayList<TrialCondition>> trialTypePermutations) {

        ArrayList<PermutationPair> retVal = new ArrayList<PermutationPair>();
        for (int i = 0; i < trialTypePermutations.size(); i++) {
            ArrayList<TrialCondition> trialTypePermutation = trialTypePermutations.get(i);
            int tupleSize = trialTypePermutation.size();
            for (int j = 0; j < lengthPermuations.size(); j++) {
                ArrayList<Integer> sizePermutation = lengthPermuations.get(j);
                Boolean enough = true;
                int k = 0;
                ArrayList<ArrayList<Trial>> candidates = new ArrayList<ArrayList<Trial>>(tupleSize);
                while (enough && k < tupleSize) {
                    TrialCondition currentTrialCondition = trialTypePermutation.get(k);
                    Integer currentLength = sizePermutation.get(k);
                    //x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
                    ArrayList<Trial> possibleTrials = bandTrials.get(currentLength).get(currentTrialCondition);
                    if (possibleTrials.size() < 1) {
                        enough = false;
                    } else {
                        candidates.add(k, possibleTrials);
                        k++;
                    }
                }
                if (enough) {
                    PermutationPair permPair = new PermutationPair(trialTypePermutation, sizePermutation, candidates);
                    retVal.add(permPair);
                }
            }

        }
        return retVal;
    }

    // sanity checks must be added for testing
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        UtilsJSONdialect<TrialCondition> util1 = new UtilsJSONdialect<TrialCondition>();

        try {
            String trailTypesStr = util1.arrayListToString(this.trialConditions);
            if (trailTypesStr != null) {
                builder.append("trialConditions:").append(trailTypesStr).append(",");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        UtilsJSONdialect<Integer> util2 = new UtilsJSONdialect<Integer>();
        try {
            String trialLengthsStr = util2.arrayListToString(this.trialLengths);
            if (trialLengthsStr != null) {
                builder.append("trialLengths:").append(trialLengthsStr);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        UtilsJSONdialect<Trial> util3 = new UtilsJSONdialect<Trial>();
        try {
            String trialLengthsStr = util3.arrayList2String(this.trials);
            if (trialLengthsStr != null) {
                builder.append("trials:").append(trialLengthsStr);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        builder.append("}");
        return builder.toString();
    }

    public static PermutationPair toObject(String str, LinkedHashMap<String, AudioAsStimulus> hashedStimuli) {

        UtilsJSONdialect<Integer> util2 = new UtilsJSONdialect<Integer>();

        try {

            String triallTypesStr = UtilsJSONdialect.getKey(str, "trialConditions")[0];
            ArrayList<String> trialConditionsArrayStr = UtilsJSONdialect.stringToArrayList(triallTypesStr);
            ArrayList<TrialCondition> trialConds = new ArrayList<TrialCondition>(trialConditionsArrayStr.size());
            for (int i = 0; i < trialConditionsArrayStr.size(); i++) {
                trialConds.add(null);
            }
            for (int i = 0; i < trialConditionsArrayStr.size(); i++) {
                String withBrackets = trialConditionsArrayStr.get(i).trim();
                TrialCondition tc = TrialCondition.valueOf(UtilsJSONdialect.removeFirstAndLast(withBrackets));
                trialConds.set(i, tc);
            }

            String lengthStr = UtilsJSONdialect.getKey(str, "trialLengths")[0];
            ArrayList<Integer> lengths = util2.stringToArrayListInteger(lengthStr);

            String trialsStr = UtilsJSONdialect.getKey(str, "trials")[0];
            ArrayList<ArrayList<String>> trialsStrList = UtilsJSONdialect.stringToArray2List(trialsStr);

            ArrayList<ArrayList<Trial>> trials;

            if (trialsStrList != null) {
                trials = new ArrayList<ArrayList<Trial>>(trialsStrList.size());
                for (ArrayList<String> inner : trialsStrList) {
                    ArrayList<Trial> trialsInTuple = new ArrayList<Trial>(inner.size());
                    trials.add(trialsInTuple);
                    for (String trStr : inner) {
                        Trial trial = Trial.toObject(trStr, hashedStimuli);
                        if (trial != null) {
                            trialsInTuple.add(trial);
                        }
                    }
                }
            } else {
                trials = null;
            }

            PermutationPair retVal = new PermutationPair(trialConds, lengths, trials);
            return retVal;

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

}
