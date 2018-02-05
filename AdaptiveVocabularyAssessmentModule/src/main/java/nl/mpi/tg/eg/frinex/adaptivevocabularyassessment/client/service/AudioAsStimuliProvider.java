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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioPool2;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class AudioAsStimuliProvider extends BandStimuliProvider<AudioAsStimulus> {

  
    private Random rnd;
    private ArrayList<Trial> currentTrialTuple;

    // x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
    private Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials;
    
    // requirements
    // TODO: add validation check for requirements
    private final int[] requiredLengths = {3,4,5,6};
    private final TrialCondition[] requiredTrialTypes= {TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET};
    private final Map<TrialCondition,Integer> requiredWordTypeDistribution = new HashMap<TrialCondition, Integer>()
{
    {
        put(TrialCondition.TARGET_ONLY, 1);
        put(TrialCondition.TARGET_AND_FOIL, 1);
        put(TrialCondition.NO_TARGET, 2);
    }
};
    
  
    // in this experiment thr "null" in the list ArrayList<RecordStimulus> responseRecord is a marker used to separate tuples
    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);
        this.rnd = new Random();
        
        for (TrialCondition trialType: this.requiredTrialTypes) {
           ArrayList<ArrayList<ArrayList<Trial>>> typeTrials =  this.initialiseTrials(AudioPool2.TRIALS.get(trialType), this.rnd);
           trials.put(trialType, typeTrials);
        }
        
        /*
        if (this.numberOfSeries == 2) {

        } else {
        }
        
         */
        this.currentBandIndex = this.startBand - 1;
        this.responseRecord.add(null);

        boolean init = this.initialiseNextFineTuningTuple();
        if (!init) {
            System.out.println(this.errorMessage);
        }
    }

    // also permutes all trials within given class, band, length.
    private ArrayList<ArrayList<ArrayList<Trial>>> initialiseTrials(Trial[][][] sourceArray, Random rnd) {
        ArrayList<ArrayList<ArrayList<Trial>>> retVal = new ArrayList<ArrayList<ArrayList<Trial>>>(this.numberOfBands);
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<ArrayList<Trial>> listByLength = new ArrayList<ArrayList<Trial>>(sourceArray[i].length);
            retVal.add(i, listByLength);
            for (int j=0; j < sourceArray[i].length; j++) { 
                ArrayList<Trial> listOfCurrentLength = new ArrayList<Trial>(sourceArray[i][j].length);
                listByLength.add(j, listOfCurrentLength);
                int[] permutIndex = this.permutation(sourceArray[i][j].length, rnd);
                for (int k = 0; k < sourceArray[i][j].length; k++) {
                    sourceArray[i][j][permutIndex[k]].clearUsages(); // reinitialise static variable
                    listOfCurrentLength.add(k, sourceArray[i][j][permutIndex[k]]);
                }
            }
        }
        return retVal;

    }

    private int[] permutation(int length, Random rnd) {
        int[] retVal = new int[length];
        ArrayList<Integer> tmp = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            tmp.add(i);
        }
        int j = 0;
        while (tmp.size() > 0) {
            int rIndex = rnd.nextInt(tmp.size());
            retVal[j] = tmp.get(rIndex);
            tmp.remove(rIndex);
            j++;
        }
        return retVal;
    }

    
    // there must be 1 trial with target, 1 with target-and-foil, and 2 no-targets
    // there must be trials of length 3,4,5,6
    // with side effect: removing used stimuli from source lists targetOnlyTrials, targetAndFoilTrials, notargetTrials
    @Override
    public boolean initialiseNextFineTuningTuple() {
        
        this.currentTrialTuple = new ArrayList<Trial>(this.fineTuningTupleLength);
        // get permutation of types 
        int[] permutTrialTypes = this.permutation(this.requiredTrialTypes.length, this.rnd);
         // get permutation of 3,4,5,6
         
        // TODO find length leftovers for this type and band and chose from them !! 
        int[] permutLengthes =  this.permutation(this.requiredLengths.length, this.rnd);
        
        for (int i=0; i<this.fineTuningTupleLength; i++){
          TrialCondition trialType = this.requiredTrialTypes[permutTrialTypes[i]];
          int lngth = this.requiredLengths[permutLengthes[i]];
          ArrayList<Trial> candidateTrials = this.trials.get(trialType).get(this.currentBandIndex).get(lngth);
          if (candidateTrials.size()<0) {
              int bandNumber = this.currentBandIndex+1;
              this.errorMessage = "There is no trails left of type "+trialType+" for band "+bandNumber+ " of length "+lngth;
              return false;
          }
          this.currentTrialTuple.add(candidateTrials.get(0));
          candidateTrials.remove(0);
        }
       
        return true;
    }

    @Override
    public void nextStimulus(int increment) {
        // find first nonempty trial in the current trial tuple and pick up the first stimuli out of here
        // there must be at least one nonempty trial, ensured by "fineTuningToBecontinued"
        int i = 0;
        while (this.currentTrialTuple.get(i).getStimuliList().size() < 1) {
            i++;
        }
        AudioAsStimulus bStimulus = this.currentTrialTuple.get(i).getStimuliList().remove(0);
        this.responseRecord.add(bStimulus);
    }

    @Override
    protected boolean tupleIsNotEmpty() {
        int i = 0;
        while (this.currentTrialTuple.get(i).getStimuliList().size() < 1 && i < trials.size()) {
            i++;
        }
        if (i < trials.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean allTupleIsCorrect() {
        boolean allTupleCorrect = true;
        int i = this.responseRecord.size() - 1;
        // we go down till we hit the first separator between tuples (which is null)
        while (this.responseRecord.get(i) != null && allTupleCorrect) {
            AudioAsStimulus audioStimulus = this.responseRecord.get(i);
            WordType stimulusType = audioStimulus.getWordType();
            if (stimulusType.equals(WordType.EXAMPLE_TARGET_NON_WORD)) { // we do not check correctness for example stimuli
                i--;
            } else {
                if (!audioStimulus.getCorrectness()) {
                    allTupleCorrect = false;
                } else {
                    i--;
                }
            }
        }
        return allTupleCorrect;
    }

    // also evaluates and sets correctenss of the current stimulus
    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        int index = this.getCurrentStimulusIndex();
        AudioAsStimulus audioStimulus = this.responseRecord.get(index);
        audioStimulus.setTimeStamp(System.currentTimeMillis());
        WordType stimulusType = audioStimulus.getWordType();
        if (stimulusType.equals(WordType.EXAMPLE_TARGET_NON_WORD)) { // no reaction is expected
            audioStimulus.setCorrectness(true);
            audioStimulus.setReaction(null);
            return true;
        } else {
            audioStimulus.setReaction(stimulusResponse);
            boolean correctness;
            if (stimulusResponse == null) {
                if (stimulus.getCorrectResponses() == null) {
                    correctness = true;
                } else {
                    correctness = false;
                }
            } else {
                if (stimulus.getCorrectResponses() == null) {
                    correctness = false;
                } else {
                    correctness = stimulusResponse.equals(stimulus.getCorrectResponses());
                }
            }
            audioStimulus.setCorrectness(correctness);
            return correctness;
        }
    }
}
