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
import static java.util.Collections.list;
import java.util.Map;
import java.util.Random;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;

/**
 *
 * @author olhshk
 */
public class TrialTuple {

    private final ArrayList<Trial> trials;
    private Boolean correctness;
    private static final Random RND = new Random();

    public TrialTuple(ArrayList<Trial> trials) {
        this.trials = trials;
        this.correctness = null;
    }
    
    public TrialTuple(ArrayList<Trial> trials, Boolean correctness) {
        this.trials = trials;
        this.correctness = correctness;
    }

    public AudioAsStimulus removeFirstAvailableStimulus() {
        int i = 0;
        while (this.trials.get(i).getStimuliList().size() < 1) {
            i++;
        }
        AudioAsStimulus retVal = this.trials.get(i).getStimuliList().remove(0);
        return retVal;
    }

    public ArrayList<Trial> getTrials() {
        return this.trials;
    }

    public Boolean getCorrectness() {
        return this.correctness;
    }

    public void setCorrectness(boolean correct) {
        this.correctness = correct;

    }

    public boolean isNotEmpty() {
        int i = 0;
        // try to find first non-empty trial
        while (i < this.trials.size()) {
            if (this.trials.get(i).getStimuliList().size() > 0) {
                return true; // there are nonempty trials!
            } else {
                i++;
            }
        }
        return false; // all trials are fired!
    }

    public static TrialTuple createTupleForBand(ArrayList<PermutationPair> availablePermutations, Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trialMatrix, int size, int bandIndex) {
        if (availablePermutations.size() < 1) {
            return null;
        }

        ArrayList<Trial> trials = new ArrayList<Trial>(size);
        int combinationIndex = RND.nextInt(availablePermutations.size());
        PermutationPair permPair = availablePermutations.get(combinationIndex);
        for (int i = 0; i < size; i++) {
            TrialCondition trialType = permPair.trialTypes.get(i);
            Integer length = permPair.trialLengths.get(i);
            ArrayList<Trial> possibilities = trialMatrix.get(trialType).get(bandIndex).get(length);
            int trialIndex = RND.nextInt(possibilities.size());
            Trial currentTrial = possibilities.remove(trialIndex);
            trials.add(currentTrial);
        }

        ArrayList<PermutationPair> toBeRemoved = AudioUtils.emptiedPossibilities(availablePermutations, trialMatrix, bandIndex, size);
        availablePermutations.removeAll(toBeRemoved);
        TrialTuple retVal = new TrialTuple(trials);
        return retVal;
    }

    @Override
    public String toString() {
        //private final ArrayList<Trial> trials;
        //private Boolean correctness;

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        UtilsJSONdialect<Trial> util = new UtilsJSONdialect<Trial>();
        try {
            String trialsStr = util.arrayListToString(this.trials);
            builder.append("trials:").append(trialsStr).append(",");
            builder.append("correcteness:{");
            if (correctness == null) {
                builder.append(" }}");
            } else {
                if (correctness) {
                    builder.append("true}}");
                } else {
                    builder.append("false}}");
                }
            }
            return builder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static TrialTuple toObject(String str) {
        try {
            String trialsStr = UtilsJSONdialect.getKey(str, "trials");
            UtilsJSONdialect<Trial> util = new UtilsJSONdialect<Trial>();
            ArrayList<String> trialsStrArray = util.stringToArrayList(trialsStr);
            ArrayList<Trial> trials = new ArrayList<Trial>(trialsStrArray.size());
            for (int i = 0; i < trialsStrArray.size(); i++) {
                Trial tr = Trial.toObject(trialsStrArray.get(i));
                trials.add(i, tr);
            }
            String correctnessStr = UtilsJSONdialect.getKeyWithoutBrackets(str, "correctness");
            Boolean correctness = null;
            if (correctnessStr.trim().equals("true")){
               correctness = true; 
            } else {
               if (correctnessStr.trim().equals("false")) {
                   correctness = false;
               }
            }
            
            TrialTuple retVal = new TrialTuple(trials, correctness);
            return retVal;
        } catch (Exception e) {
            return null;
        }

    }
}
