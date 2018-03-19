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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioUtils;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.PermutationPair;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialTuple;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class AudioAsStimuliProvider extends BandStimuliProvider<AudioAsStimulus> {

    private String dirName;

    private TrialTuple currentTrialTuple;

    // x[contdition][i][j] is the list of all trials (id-s)  satisfying "condition" for the band i of the length j
    private LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials;

    // requirements
    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
    private final int maxLength = 6;
    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));

    private ArrayList<ArrayList<Integer>> trialLengtPermutations; // list of permutations of members requiredLengths
    private ArrayList<ArrayList<TrialCondition>> trialTypesPermutations; // list of permutations of members 
    private ArrayList<ArrayList<PermutationPair>> availableCombinations; // x[i] is the list of permutations with non-empty possibilities to instantiate them using trials matrix of unused trials

    private int nStimuliInTuple = 0;

    public AudioAsStimuliProvider(AudioAsStimulus[] stimulusArray) {
        super(stimulusArray);
    }

    
    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);

        this.initialiseTrials(this.stimuli, this.numberOfBands, this.maxLength);

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
        this.nStimuliInTuple = this.currentTrialTuple.getNumberOfStimuli();
        if (this.currentTrialTuple == null) {
            this.errorMessage = "There is no trial combinations satisfying the specification!";
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void nextStimulus(int increment) {
        AudioAsStimulus stimulus  = this.currentTrialTuple.removeFirstAvailableStimulus();
        BookkeepingStimulus<AudioAsStimulus> bStimulus  = new BookkeepingStimulus<AudioAsStimulus>(stimulus);
        this.responseRecord.add(bStimulus);
    }

    @Override
    protected boolean tupleIsNotEmpty() {
        return this.currentTrialTuple.isNotEmpty();
    }

    @Override
    protected Boolean allTupleIsCorrect() {
        boolean allTupleCorrect = true;
        int lastIndex = this.responseRecord.size() - 1;
        for (int i = 0; i < this.nStimuliInTuple; i++) {
            BookkeepingStimulus<AudioAsStimulus> bStimulus = this.responseRecord.get(lastIndex - i);
            AudioAsStimulus audioStimulus = bStimulus.getStimulus();
            WordType stimulusType = audioStimulus.getWordTypeWT();
            if (!stimulusType.equals(WordType.EXAMPLE_TARGET_NON_WORD)) {
                boolean correctness = (stimulusType.equals(WordType.TARGET_NON_WORD) && bStimulus.getReaction() != null) || ((!stimulusType.equals(WordType.TARGET_NON_WORD)) && bStimulus.getReaction() == null);
                bStimulus.setCorrectness(correctness);
                if (!correctness && allTupleCorrect) {
                    allTupleCorrect = false;
                }
            }
        }
        this.currentTrialTuple.setCorrectness(true);
        return allTupleCorrect;
    }

    // this method is called only when stimulusButton is pressed
    // 
    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        int index = this.getCurrentStimulusIndex();
        BookkeepingStimulus<AudioAsStimulus> bStimulus = this.responseRecord.get(index);
        bStimulus.setTimeStamp(System.currentTimeMillis());
         
        AudioAsStimulus audioStimulus = bStimulus.getStimulus();
        WordType stimulusType = audioStimulus.getWordTypeWT();
        
        bStimulus.setReaction(stimulusResponse);
        boolean correctness = stimulusType.equals(WordType.TARGET_NON_WORD); // button shoulb be pressed only on the target nonword
        return correctness;

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
            BookkeepingStimulus<AudioAsStimulus> bStimulus = this.responseRecord.get(i);
            AudioAsStimulus stimulus = bStimulus.getStimulus();
            StringBuilder row = new StringBuilder();
            String time = (new Date(bStimulus.getTimeStamp())).toString();
            row.append(startColumn).append(stimulus.getBandIndexInt()).append(endColumn);
            row.append(startColumn).append(stimulus.getBandLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getWordTypeWT()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectResponses()).append(endColumn);
            row.append(startColumn).append(bStimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(bStimulus.getCorrectness()).append(endColumn);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        String superString = super.toString();
        builder.append(superString).append(",");

//    private String dirName;
        builder.append("dirName:{").append(this.dirName).append("},");
//
//    private TrialTuple currentTrialTuple;

        if (this.currentTrialTuple != null) {
            builder.append("this.currentTrialTuple:{").append(this.currentTrialTuple.toString()).append("},");
        }

//    // x[contdition][i][j] is the list of all trials satisfying "condition" for the band i of the length j
//    private Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials;
        String trialsStr = Trial.map3ToString(this.trials);
        if (trialsStr != null) {
            builder.append("trials:").append(this.trials).append(",");
        }
//
//
//    // requirements
//    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
        UtilsJSONdialect<Integer> utils = new UtilsJSONdialect<Integer>();
        try {
            String requiredLengthsStr = utils.arrayListToString(this.requiredLengths);
            if (trialsStr != null) {
                builder.append("requiredLengths:").append(requiredLengthsStr).append(",");
            }
        } catch (Exception ex) {
            return null;
        }

//    private final int maxLength = 6;
        builder.append("maxLength:{").append(this.maxLength).append("},");

//    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));
//
//    private ArrayList<ArrayList<Trial>> trialLengtPermutations; // list of permutations of members requiredLengths
        try {
            String trialLengtPermutationsStr = utils.arrayList2String(this.trialLengtPermutations);
            if (trialsStr != null) {
                builder.append("trialLengtPermutations:").append(trialLengtPermutationsStr).append(",");
            }
        } catch (Exception ex) {
            return null;
        }

//    private ArrayList<ArrayList<TrialCondition>> trialTypesPermutations; // list of permutations of members 
        UtilsJSONdialect<TrialCondition> utils2 = new UtilsJSONdialect<TrialCondition>();
        try {
            String trialTypesPermutationsStr = utils2.arrayList2String(this.trialTypesPermutations);
            if (trialsStr != null) {
                builder.append("trialTypesPermutations:").append(trialTypesPermutationsStr).append(",");
            }
        } catch (Exception ex) {
            return null;
        }

//    private ArrayList<ArrayList<PermutationPair>> availableCombinations;
        UtilsJSONdialect<PermutationPair> utils3 = new UtilsJSONdialect<PermutationPair>();
        try {
            String availableCombinationsStr = utils3.arrayList2String(this.availableCombinations);
            if (trialsStr != null) {
                builder.append("availableCombinations:").append(availableCombinationsStr).append(",");
            }
        } catch (Exception ex) {
            return null;
        }

        builder.append("}");
        return builder.toString();
    }

    private LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> initMatrix(int numbOfBands, int maxLength) {
        LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> retVal = new LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>>();

        // initialisation: creating empty matrices
        for (TrialCondition trialType : TrialCondition.values()) {
            ArrayList<ArrayList<ArrayList<Trial>>> trialsForCondition = new ArrayList<ArrayList<ArrayList<Trial>>>(numbOfBands);
            for (int i = 0; i < numbOfBands; i++) {
                ArrayList<ArrayList<Trial>> trialsByLength = new ArrayList<ArrayList<Trial>>(maxLength + 1); // maxLength is included
                for (int j = 0; j <= maxLength; j++) {
                    ArrayList<Trial> listOfCurrentLength = new ArrayList<Trial>();
                    trialsByLength.add(j, listOfCurrentLength);
                }
                trialsForCondition.add(i, trialsByLength);
            }
            retVal.put(trialType, trialsForCondition);
        }
        return retVal;
    }
    
    
    private  void initialiseTrials(AudioAsStimulus[] stimuli, int numberOfBands, int maxLength) {
       this.trials = this.initMatrix(numberOfBands, maxLength);
       int[] permuteIndex = RandomIndexing.generateRandomArray(stimuli.length);
       
        for (int i=0; i<stimuli.length; i++) {
            AudioAsStimulus stimulus = stimuli[permuteIndex[i]];
            ArrayList<ArrayList<ArrayList<Trial>>> trialGroupByCondition  = this.trials.get(stimulus. getTrialConditionTC());
            ArrayList<ArrayList<Trial>> trialGroupByBand = trialGroupByCondition.get(stimulus.getBandIndexInt());
            ArrayList<Trial> trialGroupByLength = trialGroupByBand.get(stimulus.getTrialLengthInt());
            int index=this.findIndexOfTrialById(trialGroupByLength, stimulus.getTrialNumberInt());
            if (index<0) {
                //(int id, String word, String cueFile, int nOfSyllables, TrialCondition condition, int length, String bandLabel, int bandIndex)
               Trial freshTrial = new Trial(stimulus.getTrialNumberInt(), stimulus.gettrialWord(), 
                       stimulus.gettrialCueFile(), stimulus.getTrialSyllablesInt(), stimulus.getTrialConditionTC(), stimulus.getTrialLengthInt(),
                       stimulus.getBandLabel(), stimulus.getBandIndexInt()); 
               trialGroupByLength.add(freshTrial);
               index=this.findIndexOfTrialById(trialGroupByLength, stimulus.getTrialNumberInt());
            }
            trialGroupByLength.get(index).addStimulus(stimulus, stimulus.getPositionInTrialInt());
            this.hashedStimuli.put(stimulus.getUniqueId(), stimulus);
        }
        
    }
    
    private int findIndexOfTrialById(ArrayList<Trial> trials, int number){
         for (int i=0; i<trials.size(); i++) {
            if (trials.get(i).getId() == number) {
                return i;
            }
        }
         return -1;
    }

}
