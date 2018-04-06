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
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;

/**
 *
 * @author olhshk
 */
public class PermutationPair {

    private final static String FLDS = "[trialConditions, trialLengths, trials]";
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
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("fields", PermutationPair.FLDS);
        map.put("trials", this.trials);
        map.put("trialConditions", this.trialConditions);
        map.put("trialLengthss", this.trialLengths);
        return map.toString();
    }

    public static PermutationPair toObject(Map<String, Object> map) {
        try {

            List<Object> triallTypesObj = (List<Object>) map.get("trialConditions");
            ArrayList<TrialCondition> trialConds = null;
            if (triallTypesObj != null) {
                trialConds = new ArrayList<TrialCondition>(triallTypesObj.size());
                for (int i = 0; i < triallTypesObj.size(); i++) {
                    String cond = triallTypesObj.get(i).toString();
                    TrialCondition tc = TrialCondition.valueOf(cond);
                    trialConds.add(i, tc);
                }
            }
            List<Object> triallLengthObj = (List<Object>) map.get("trialLengths");
            ArrayList<Integer> trialLengths = null;
            if (triallLengthObj != null) {
                trialLengths = new ArrayList<Integer>(triallLengthObj.size());
                for (int i = 0; i < triallLengthObj.size(); i++) {
                    String lengthStr = triallLengthObj.get(i).toString();
                    Integer tc = Integer.parseInt(lengthStr);
                    trialLengths.add(i, tc);
                }
            }

          
            
            ArrayList<ArrayList<Object>> trialsObj=  (ArrayList<ArrayList<Object>>) map.get("trials");
            ArrayList<ArrayList<Trial>> trials;

            if (trialsObj != null) {
                trials = new ArrayList<ArrayList<Trial>>(trialsObj.size());
                for (ArrayList<Object> inner : trialsObj) {
                    ArrayList<Trial> trialsInTuple = new ArrayList<Trial>(inner.size());
                    trials.add(trialsInTuple);
                    for (Object trialObj : inner) {
                        Map<String,Object> mapTrial = (Map<String,Object>) trialObj;
                        Trial trial = Trial.mapToObject(mapTrial);
                        if (trial != null) {
                            trialsInTuple.add(trial);
                        }
                    }
                }
            } else {
                trials = null;
            }

            PermutationPair retVal = new PermutationPair(trialConds, trialLengths, trials);
            return retVal;

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

}
