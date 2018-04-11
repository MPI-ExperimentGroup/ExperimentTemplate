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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
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

    private final static String[] SPECIFIC_FLDS = {"availableCombinations", "currentTrialTuple", "responseRecord"};

    // requirements, possible moving to config
    // do not need serialisation, as it stays the same in any state
    private final String[] labelling = {"min10db", "min8db", "min6db", "min4db", "min2db", "zerodb", "plus2db", "plus4db", "plus6db", "plus8db", "plus10db"};
    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
    private final int maxTrialLength = 6;
    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));
    private final Random rnd = new Random();
    
    // do not need serialisation
   // x[i][j][contdition] is the list of all trials (id-s) of the band-index i  of the length j satisfying "condition"
    private ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> trials; // shared between various permutation-pairs, reduced while it is used
    
    
    AudioStimuliFromString reader = new AudioStimuliFromString();
    // to be serialised
    private ArrayList<ArrayList<PermutationPair>> availableCombinations; // x[i] is the list of permutations with non-empty possibilities to instantiate them using trials matrix of unused trials
    private TrialTuple currentTrialTuple;
    
    public AudioAsStimuliProvider(Stimulus[] stimulusArray) {
        super(stimulusArray);
    }

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);
        if (stimuliStateSnapshot.isEmpty()) {
            

            UtilsList<TrialCondition> utilTrials = new UtilsList<TrialCondition>();
            ArrayList<ArrayList<TrialCondition>> trialTypesPermutations = utilTrials.generatePermutations(this.requiredTrialTypes);

            UtilsList<Integer> utilSizes = new UtilsList<Integer>();
            ArrayList<ArrayList<Integer>> trialLengtPermutations = utilSizes.generatePermutations(this.requiredLengths);

            this.reader = new AudioStimuliFromString();
            this.reader.readTrialsAsCsv(this.labelling);
            this.trials = Trial.prepareTrialMatrix(this.reader.getHashedTrials(), this.numberOfBands, this.maxTrialLength);

            this.availableCombinations = PermutationPair.initialiseAvailabilityList(this.trials, trialLengtPermutations, trialTypesPermutations, this.numberOfBands);
            
            this.currentBandIndex = this.startBand;
            boolean init = this.initialiseNextFineTuningTuple();
            if (!init) {
                System.out.println(this.errorMessage);
            }
        } else {
            try {
                this.deserialiseSpecific(stimuliStateSnapshot);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    

    @Override
    public boolean initialiseNextFineTuningTuple() {

        ArrayList<PermutationPair> combinations = this.availableCombinations.get(this.currentBandIndex);

        this.currentTrialTuple = this.createTupleForBand(combinations);
        if (this.currentTrialTuple == null) {
            this.errorMessage = "There is no trial tuples left satisfying the specification, for the band " + this.labelling[this.currentBandIndex];
            return false;
        } else {
            // now remove permutation-pairs which have emptied list of trials 
            ArrayList<PermutationPair> toBeRemoved = new ArrayList<PermutationPair>();
            for (PermutationPair permut : combinations) {
                if (!permut.isAvailable()) {
                    toBeRemoved.add(permut);
                }
            }
            combinations.removeAll(toBeRemoved);
            return true;
        }
    }

    @Override
    public void nextStimulus(int increment) {
        BookkeepingStimulus<AudioAsStimulus> bStimulus = this.currentTrialTuple.removeFirstAvailableStimulus();
        this.responseRecord.add(bStimulus);
    }

    @Override
    protected boolean tupleIsNotEmpty() {
        return this.currentTrialTuple.isNotEmpty();
    }

    @Override
    protected Boolean isWholeTupleCorrect() {
        boolean allTupleCorrect = true;
        int i = this.responseRecord.size() - 1;
        while (i > -1 && this.responseRecord.get(i) != null) {
            BookkeepingStimulus<AudioAsStimulus> bStimulus = this.responseRecord.get(i);
            AudioAsStimulus audioStimulus = bStimulus.getStimulus();
            WordType stimulusType = audioStimulus.getwordType();
            if (!stimulusType.equals(WordType.EXAMPLE_TARGET_NON_WORD)) {
                boolean correctness = (stimulusType.equals(WordType.TARGET_NON_WORD) && bStimulus.getReaction() != null) || ((!stimulusType.equals(WordType.TARGET_NON_WORD)) && bStimulus.getReaction() == null);
                bStimulus.setCorrectness(correctness);
                if (!correctness && allTupleCorrect) {
                    allTupleCorrect = false;
                }
            }
            i--;
        }
        this.currentTrialTuple.setCorrectness(allTupleCorrect);
        this.responseRecord.add(null); // marking the end of the trial tuple
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
        WordType stimulusType = audioStimulus.getwordType();

        bStimulus.setReaction(AudioAsStimulus.USER_REACTION);
        boolean correctness = stimulusType.equals(WordType.TARGET_NON_WORD); // button shoulb be pressed only on the target nonword
        return correctness;

    }

    public TrialTuple getCurrentTrialTuple() {
        return this.currentTrialTuple;
    }

    @Override
    public String getStringFineTuningHistory(String startRow, String endRow, String startColumn, String endColumn, String format) {
        int bandOffset = 5;
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
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("Timestamp").append(endColumn);
        stringBuilder.append(startColumn).append("Visiting Number").append(endColumn);
        stringBuilder.append(endRow);
        ArrayList<String> spellingsCheck = new ArrayList<>();
        for (int i = 0; i < this.responseRecord.size(); i++) {
            BookkeepingStimulus<AudioAsStimulus> bStimulus = this.responseRecord.get(i);
            if (bStimulus == null) { // trial tuple border
                stringBuilder.append(startRow).append(endRow);
                stringBuilder.append(startRow).append(endRow);
                stringBuilder.append(startRow).append(endRow);
                continue;
            }
            AudioAsStimulus stimulus = bStimulus.getStimulus();
            StringBuilder row = new StringBuilder();
            String time = (new Date(bStimulus.getTimeStamp())).toString();
            row.append(startColumn).append(stimulus.getbandIndex()-bandOffset).append(endColumn);
            row.append(startColumn).append(stimulus.getbandLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getwordType()).append(endColumn);
            row.append(startColumn).append(bStimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(bStimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(time).append(endColumn);
            row.append(startColumn).append(i).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
            spellingsCheck.add(stimulus.getUniqueId());
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

    public TrialTuple createTupleForBand(ArrayList<PermutationPair> availablePermutations) {

        if (availablePermutations.size() < 1) {
            return null;
        }

        int combinationIndex = this.rnd.nextInt(availablePermutations.size());
        PermutationPair permPair = availablePermutations.get(combinationIndex);
        int tupleSize = permPair.getTrialConditions().size();
        ArrayList<Trial> trs = new ArrayList<Trial>(tupleSize);

        for (int i = 0; i < tupleSize; i++) {
            ArrayList<Trial> possibilities = permPair.getTrials().get(i);// shared part
            int trialIndex = this.rnd.nextInt(possibilities.size());
            Trial currentTrial = possibilities.remove(trialIndex);
            trs.add(currentTrial);
        }

        TrialTuple retVal = new TrialTuple(trs);
        return retVal;
    }

    @Override
    public String toString() {
        Map<String, Object> map = super.toMap();
        map.put("availableCombinations", this.availableCombinations);
        map.put("currentTrialTuple", this.currentTrialTuple);
        return map.toString();
    }

    @Override
    protected void deserialiseSpecific(String str) throws Exception {
        
        this.reader.readTrialsAsCsv(this.labelling);
        
        this.trials = Trial.prepareTrialMatrix(reader.getHashedTrials(), this.numberOfBands, this.maxTrialLength);
        
        Map<String, Object> map = UtilsJSONdialect.stringToObjectMap(str, SPECIFIC_FLDS);
        
        Object recordObj = map.get("responseRecord");
        this.responseRecord = new ArrayList<BookkeepingStimulus<AudioAsStimulus>>();
        BookkeepingStimulus<AudioAsStimulus> factory = new BookkeepingStimulus<AudioAsStimulus>(null);
        if (recordObj != null) {
            List<Object> objs = (List<Object>) recordObj;
            for (int i = 0; i < objs.size(); i++) {
                Map<String,Object> mapBStimulus= (Map<String,Object>) objs.get(i);
                BookkeepingStimulus<AudioAsStimulus> bStimulus = factory.toBookkeepingStimulusObject(mapBStimulus, this.reader.getHashedStimuli());
                this.responseRecord.add(i, bStimulus);
            }
        }
        
        ArrayList<ArrayList<Object>> permutObjects = (ArrayList<ArrayList<Object>>) map.get("availableCombinations");
        this.availableCombinations = new ArrayList<ArrayList<PermutationPair>>(permutObjects.size());
        for (int i=0; i<permutObjects.size(); i++) {
            if (permutObjects.get(i) == null) {
                this.availableCombinations.add(null);
                continue;
            }
            ArrayList<PermutationPair> permForBand = new ArrayList<PermutationPair>(permutObjects.get(i).size());
            this.availableCombinations.add(permForBand);
            for (Object object:permutObjects.get(i)) {
                LinkedHashMap<String, Object> objMap = (LinkedHashMap<String, Object>) object;
                PermutationPair perm = PermutationPair.toObject(objMap, this.reader.getHashedTrials());
                permForBand.add(perm);
            }
        }
        
        
        
        LinkedHashMap<String, Object> tupleMap = (LinkedHashMap<String, Object>) map.get("currentTrialTuple");
        this.currentTrialTuple = TrialTuple.mapToObject(tupleMap, this.reader.getHashedTrials());
        
    }
    
  

    // not relevant for this experiment
    @Override
    public boolean analyseCorrectness(Stimulus stimulus, String stimulusResponse) {
        return true;
    }

    @Override
    public boolean fastTrackToBeContinuedWithSecondChance() {
        return true;
    }

    @Override
    public boolean enoughStimuliForFastTrack() {
        return true;
    }

    @Override
    public void recycleUnusedStimuli() {
    }

    @Override
    public String getStringFastTrack(String startRow, String endRow, String startColumn, String endColumn) {
        return "";
    }

    @Override
    public BookkeepingStimulus<AudioAsStimulus> deriveNextFastTrackStimulus() {
        return null;
    }

}
