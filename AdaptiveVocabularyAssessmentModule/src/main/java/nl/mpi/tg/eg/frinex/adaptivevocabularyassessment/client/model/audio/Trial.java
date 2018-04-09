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
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;

/**
 *
 * @author olhshk
 */
public class Trial {

    private final int trialId;
    private final ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli;  // the first one (zero index) is the cue, it is in the order it should appear for the participant
    private final String word;
    private final String targetNonWord;
    private final int numberOfSyllables;
    private final TrialCondition condition;
    private final int lgth;
    private final int bandIndex;
    private final String bandLabel;
    private final int positionTarget;
    private final int positionFoil;
    
    public Trial(int id, String word, String targetNonWord, int nOfSyllables, TrialCondition condition, int length, String bandLabel, int bandIndex, int positionTarget, int positionFoil, ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli) throws Exception {

        if (stimuli.size() != length + 1) {
            throw new Exception("Amount of stimuli " + stimuli.size() + " does not correspond to the specification " + "1 (cue) + " + length + " stimuli.");
        }

        this.trialId = id;
        this.word = word;
        this.targetNonWord = targetNonWord;
        this.numberOfSyllables = nOfSyllables;
        this.lgth = length;
        this.bandLabel = bandLabel;
        this.bandIndex = bandIndex;
        this.condition = condition;
        this.positionTarget = positionTarget;
        this.positionFoil = positionFoil;
        this.stimuli = stimuli;

    }

    public String getWord() {
        return this.word;
    }

    public int getId() {
        return this.trialId;
    }

    public int getBandIndex() {
        return this.bandIndex;
    }

    public String getBandLabel() {
        return this.bandLabel;
    }

    public String getTargetNonWord() {
        return this.targetNonWord;
    }

    public int getNumberOfSyllables() {
        return this.numberOfSyllables;
    }

    public TrialCondition getCondition() {
        return this.condition;
    }

    public int getTrialLength() {
        return this.lgth;
    }

    public int getPositionTarget() {
        return this.positionTarget;
    }

    public int getPositionFoil() {
        return this.positionFoil;
    }

    public ArrayList<BookkeepingStimulus<AudioAsStimulus>> getStimuli() {
        return this.stimuli;
    }

    public static ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> prepareTrialMatrix(LinkedHashMap<Integer, Trial> csvTrials, int numberOfBands, int maxTrialLength) {
        ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> retVal = initMatrix(numberOfBands, maxTrialLength);
        Set<Integer> keys = csvTrials.keySet();
        for (Integer i:keys) {
            Trial trial = csvTrials.get(i);
            TrialCondition condition = trial.getCondition();
            int bandIndex = trial.getBandIndex();
            int trialLength = trial.getTrialLength();
            ArrayList<Trial> listToInsert = retVal.get(bandIndex).get(trialLength).get(condition);
            listToInsert.add(trial);            
        }
        return retVal;
    }

    private static ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> initMatrix(int numbOfBands, int maxLength) {
        ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> retVal = new ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>>(numbOfBands);

        for (int i = 0; i < numbOfBands; i++) {
            ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>> trialslForBand = new ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>(maxLength + 1);
            retVal.add(i, trialslForBand);

            for (int j = 0; j <= maxLength; j++) {

                LinkedHashMap<TrialCondition, ArrayList<Trial>> trialslForLength = new LinkedHashMap<TrialCondition, ArrayList<Trial>>();
                trialslForBand.add(j, trialslForLength);

                for (TrialCondition trialType : TrialCondition.values()) {
                    trialslForLength.put(trialType, new ArrayList<Trial>());
                }
            }

        }
        return retVal;
    }

    @Override
    public String toString() {
         return String.valueOf(this.getId());
    }

    public static Trial mapToObject(Object obj, LinkedHashMap<Integer, Trial> hashedTrials){
          return hashedTrials.get(Integer.parseInt(obj.toString()));
    }

//        String idStr = map.get("trialId").toString();
//
//        String word = map.get("word").toString();
//        String targetNonWord = map.get("targetNonWord").toString();
//
//        String numberOfSyllablesStr = map.get("numberOfSyllables").toString();
//        String lgthStr = map.get("lgth").toString();
//        String bandIndexStr = map.get("bandIndex").toString();
//        String bandLabel = map.get("bandLabel").toString();
//        String conditionStr = map.get("condition").toString();
//        String positionTargetStr = map.get("positionTarget").toString();
//        String positionFoilStr = map.get("positionFoil").toString();
//
//        int numberOfSyllables = Integer.parseInt(numberOfSyllablesStr);
//        int lgth = Integer.parseInt(lgthStr);
//        int bandIndex = Integer.parseInt(bandIndexStr);
//        int id = Integer.parseInt(idStr);
//        int positionTarget = Integer.parseInt(positionTargetStr);
//        int positionFoil = Integer.parseInt(positionFoilStr);
//
//        TrialCondition condition = TrialCondition.valueOf(conditionStr);
//
//        Object stimuliObj = map.get("stimuli");
//        ArrayList<BookkeepingStimulus<AudioAsStimulus>> bStimuli;
//        if (stimuliObj == null) {
//            bStimuli = null;
//        } else {
//            List<Object> stimuliObjList = (List<Object>) stimuliObj;
//            bStimuli = new ArrayList<BookkeepingStimulus<AudioAsStimulus>>(stimuliObjList.size());
//            for (int i = 0; i < stimuliObjList.size(); i++) {
//                Map<String, Object> currentMap = (Map<String, Object>) stimuliObjList.get(i);
//                BookkeepingStimulus<AudioAsStimulus> ghost = new BookkeepingStimulus<AudioAsStimulus>(null);
//                BookkeepingStimulus<AudioAsStimulus> bStimulus = ghost.toBookkeepingStimulusObject(currentMap);
//                int position = bStimulus.getStimulus().getpositionInTrial();
//                bStimuli.set(position, bStimulus);
//            }
//        }
//
//        //Trial(int id, String word, String targetNonWord, int nOfSyllables, TrialCondition condition, int length, String bandLabel, int bandIndex)
//        Trial retVal = new Trial(id, word, targetNonWord, numberOfSyllables, condition, lgth, bandLabel, bandIndex, positionTarget, positionFoil, bStimuli);
//
//        return retVal;
//
//    }
//    

}
