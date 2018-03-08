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
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
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

    private String dirName;

    private TrialTuple currentTrialTuple;

    // x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
    private Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials;

    // requirements
    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
    private final int maxLength = 6;
    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));

    private ArrayList<ArrayList<Integer>> trialLengtPermutations; // list of permutations of members requiredLengths
    private ArrayList<ArrayList<TrialCondition>> trialTypesPermutations; // list of permutations of members 
    private ArrayList<ArrayList<PermutationPair>> availableCombinations; // x[i] is the list of permutations with non-empty possibilities to instantiate them using trials matrix of unused trials

    public void setdirName(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);
        this.trials = AudioUtils.initialiseTrials(AudioPool2.TRIAL_ROWS, this.numberOfBands, this.maxLength, this.dirName);

        UtilsList<TrialCondition> utilTrials = new UtilsList<TrialCondition>();
        this.trialTypesPermutations = utilTrials.generatePermutations(this.requiredTrialTypes);
        UtilsList<Integer> utilSizes = new UtilsList<Integer>();
        this.trialLengtPermutations = utilSizes.generatePermutations(this.requiredLengths);
        this.currentBandIndex = this.startBand;

        /*
        if (this.numberOfSeries == 2) {

        } else {
        }
        
         */
        this.availableCombinations = new ArrayList<ArrayList<PermutationPair>>(this.numberOfBands);
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<PermutationPair> permCurrentBand = AudioUtils.initialiseAvailabilityList(this.trials,
                    this.trialLengtPermutations, this.trialTypesPermutations, i, this.fineTuningTupleLength);
            this.availableCombinations.add(i, permCurrentBand);
        }

        boolean init = this.initialiseNextFineTuningTuple();
        if (!init) {
            System.out.println(this.errorMessage);
        }
    }

    @Override
    public boolean initialiseNextFineTuningTuple() {

        this.currentTrialTuple = TrialTuple.createTupleForBand(this.availableCombinations.get(this.currentBandIndex), this.trials, this.fineTuningTupleLength, this.currentBandIndex);
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
            if (!correctness && this.currentTrialTuple.getCorrectness()) {
                this.currentTrialTuple.setCorrectness(false);
            }
            return correctness;
        }
    }

    public TrialTuple getTrialTuple() {
        return this.currentTrialTuple;
    }

    @Override
    public String getStringFineTuningHistory(String startRow, String endRow, String startColumn, String endColumn, String format) {
        StringBuilder empty = new StringBuilder();
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("BandIndex").append(endColumn);
        stringBuilder.append(startColumn).append("BandLabel").append(endColumn);
        stringBuilder.append(startColumn).append("Label").append(endColumn);
        stringBuilder.append(startColumn).append("StimulusType").append(endColumn);
        stringBuilder.append(startColumn).append("CorrectResponse").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("Timestamp").append(endColumn);
        stringBuilder.append(startColumn).append("Visiting Number").append(endColumn);
        stringBuilder.append(endRow);
        ArrayList<String> spellingsCheck = new ArrayList<>();
        for (int i = 0; i < this.responseRecord.size(); i++) {
            AudioAsStimulus stimulus = this.responseRecord.get(i);
            StringBuilder row = new StringBuilder();
            String time = (new Date(stimulus.getTimeStamp())).toString();
            row.append(startColumn).append(stimulus.getBandIndex()).append(endColumn);
            row.append(startColumn).append(stimulus.getBandLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getWordType()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectResponses()).append(endColumn);
            row.append(startColumn).append(stimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(time).append(endColumn);
            row.append(startColumn).append(i).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
            spellingsCheck.add(stimulus.getLabel());
        }

        // check if there are repititions
        HashSet<String> set = new HashSet(spellingsCheck);
        if (set.size() < spellingsCheck.size()) {
            stringBuilder.append(startRow).append(startColumn)
                    .append("Repetitions of stimuli detected")
                    .append(endColumn).append(endRow);
        }
        return stringBuilder.toString();
    }
}
