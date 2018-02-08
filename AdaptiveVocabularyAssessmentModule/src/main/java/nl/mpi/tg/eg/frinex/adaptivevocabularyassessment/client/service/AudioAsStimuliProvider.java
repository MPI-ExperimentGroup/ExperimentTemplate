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
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioUtils;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.PermutationPair;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialTuple;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioPool2;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class AudioAsStimuliProvider extends BandStimuliProvider<AudioAsStimulus> {

    private String dirName = "/";

    private Random rnd;
    private TrialTuple currentTrialTuple;

    // x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
    private Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials;

    // requirements
    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
    private final int maxLength = 6;
    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));

    private ArrayList<ArrayList<Integer>> trialLengtPermutations; // list of permutations of members requiredLengths
    private ArrayList<ArrayList<TrialCondition>> trialTypesPermutations; // list of permutations of members 
    private ArrayList<PermutationPair> availableCombinations; // are there available trials to form a tuple with length permutations like trialLengtPermutations[i] and type permutations trialTypesPermutations[j]

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);
        this.trials = AudioUtils.initialiseTrials(AudioPool2.TRIAL_ROWS, this.numberOfBands, this.maxLength, this.dirName);

        UtilsList<TrialCondition> utilTrials = new UtilsList<TrialCondition>();
        this.trialTypesPermutations = utilTrials.generatePermutations(this.requiredTrialTypes);
        UtilsList<Integer> utilSizes = new UtilsList<Integer>();
        this.trialLengtPermutations = utilSizes.generatePermutations(this.requiredLengths);
        this.currentBandIndex = this.startBand - 1;

        /*
        if (this.numberOfSeries == 2) {

        } else {
        }
        
         */
        this.currentBandIndex = this.startBand - 1;
        this.availableCombinations = AudioUtils.initialiseAvailabilityList(this.trials,
                this.trialLengtPermutations, this.trialTypesPermutations, this.currentBandIndex, this.fineTuningTupleLength);
        boolean init = this.initialiseNextFineTuningTuple();
        if (!init) {
            System.out.println(this.errorMessage);
        }
    }

    @Override
    public boolean initialiseNextFineTuningTuple() {

        this.currentTrialTuple = TrialTuple.createTuple(this.availableCombinations, this.trials, this.fineTuningTupleLength, this.currentBandIndex);
        if (this.currentTrialTuple == null) {
            this.errorMessage = "There is no trial combinations satisfying the specification!";
            return false;
        } else {
            this.currentTrialTuple.setCorrectness(true);
            return true;
        }
    }

    @Override
    public void nextStimulus(int increment) {
        AudioAsStimulus bStimulus = this.currentTrialTuple.removeFirstAvailableStimulus();
        this.responseRecord.add(bStimulus);
    }

    @Override
    protected boolean tupleIsNotEmpty() {
        return this.currentTrialTuple.isNotEmpty();
    }

    @Override
    protected Boolean allTupleIsCorrect() {
        return this.currentTrialTuple.getCorrectness();
    }

    // also evaluates and sets correctenss of the current stimulus
    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse
    ) {
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
            if (!correctness && this.currentTrialTuple.getCorrectness()) {
                this.currentTrialTuple.setCorrectness(false);
            }
            return correctness;
        }
    }
}
