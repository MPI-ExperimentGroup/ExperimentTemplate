/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning;

import java.util.ArrayList;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;

/**
 *
 * @author olhshk
 */
public class FineTuning {

    protected AtomBookkeepingStimulus[][] words;
    protected ArrayList<AtomBookkeepingStimulus> nonwords;
    protected String userName;

    ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae = new ArrayList<>(Constants.NUMBER_OF_BANDS);

    private int stepCounter;

    private int totalStimuli=0;
    private int currentlyAddedStimIndex;

    public FineTuning(String username, AtomBookkeepingStimulus[][] words, ArrayList<AtomBookkeepingStimulus> nonwords) {
        this.userName = username;
        this.words = words;
        this.nonwords = nonwords;
        this.stepCounter = 0;
        this.currentlyAddedStimIndex = 0;
    }

    public void createStimulae() throws FineTuningException {
        for (int bandIndex = 0; bandIndex < Constants.NUMBER_OF_BANDS; bandIndex++) {
            ArrayList<FineTuningBookkeepingStimulus> currentBand = this.createStimulaeBand(this.words[bandIndex], this.nonwords);
            this.totalStimuli += currentBand.size() * Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE;
            this.stimulae.add(currentBand);
        }

    }
    
    public int getTotalStimuli(){
        return this.totalStimuli;
    }

    // return true if the answer is correct, and false otherwise
    public boolean runStep(int bandNumber, int stimulusIndex, boolean[] answers){
        int bandIndex = bandNumber - 1;
        FineTuningBookkeepingStimulus stimulus = this.stimulae.get(bandIndex).get(stimulusIndex);
        // checking for mistakes
        boolean retVal = true;
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            AtomBookkeepingStimulus atomStimulus = stimulus.getAtomStimulusAt(i);
            atomStimulus.setReaction(answers[i]);

            boolean eval = true;
            if (atomStimulus.getBandNumber() < 0 && answers[i]) {
                eval = false;// took a non-word as a word
            }
            if (atomStimulus.getBandNumber() > 0 && !answers[i]) {
                eval = false;// tool a word as a nonword
            }
            atomStimulus.setCorrectness(eval);
            retVal = retVal && eval;
        }
        stimulus.setOverallCorrectness(retVal);
        this.stepCounter++;
        stimulus.setVisitTime(this.stepCounter);
        return retVal;
    }

    public FineTuningBookkeepingStimulus getStimulus(int bandNumber, int stimulusIndex) {
        return this.stimulae.get(bandNumber).get(stimulusIndex);
    }

    public ArrayList<ArrayList<FineTuningBookkeepingStimulus>> getStimuli() { // matrix form
        return this.stimulae;
    }

  
    private ArrayList<FineTuningBookkeepingStimulus> createStimulaeBand(AtomBookkeepingStimulus[] words, ArrayList<AtomBookkeepingStimulus> nonwords) throws FineTuningException{
        ArrayList<AtomBookkeepingStimulus> unusedWords = this.fetchUnusedAtomBookkeepingStimulus(words);
        ArrayList<AtomBookkeepingStimulus> unusedNonwords = this.fetchUnusedAtomBookkeepingStimulus(nonwords);
        int length = unusedWords.size() / Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE;
        if (length > unusedNonwords.size()) {
            length = unusedNonwords.size();
        }
        ArrayList<FineTuningBookkeepingStimulus> retVal = new ArrayList<>(length);

        int wordsCounter = 0;
        int nonwordsCounter = 0;
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            AtomBookkeepingStimulus[] tuple = new AtomBookkeepingStimulus[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
            int nonwordPosition = rnd.nextInt(Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
            for (int j = 0; j < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; j++) {
                if (j != nonwordPosition) {
                    tuple[j] = unusedWords.get(wordsCounter);
                    wordsCounter++;
                } else {
                    tuple[j] = unusedNonwords.get(nonwordsCounter);
                    nonwordsCounter++;
                }
                tuple[j].setIsUsed(true);
                this.currentlyAddedStimIndex++;
            }
            FineTuningBookkeepingStimulus stimulus = new FineTuningBookkeepingStimulus(tuple);
            retVal.add(stimulus);
        }
        return retVal;
    }

    private ArrayList<AtomBookkeepingStimulus> fetchUnusedAtomBookkeepingStimulus(AtomBookkeepingStimulus[] stimuli) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>();
        for (int i = 0; i < stimuli.length; i++) {
            if (!stimuli[i].getIsUsed()) {
                retVal.add(stimuli[i]);
            }
        }
        return retVal;
    }

    private ArrayList<AtomBookkeepingStimulus> fetchUnusedAtomBookkeepingStimulus(ArrayList<AtomBookkeepingStimulus> stimuli) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>();
        for (int i = 0; i < stimuli.size(); i++) {
            if (!stimuli.get(i).getIsUsed()) {
                retVal.add(stimuli.get(i));
            }
        }
        return retVal;
    }

    public boolean[] testProbabilisticAnswerer(int bandNumber, int stimulusInBandNummer, double correctnessUpperBound) {
        FineTuningBookkeepingStimulus stimulus = this.stimulae.get(bandNumber - 1).get(stimulusInBandNummer);
        boolean[] retVal = new boolean[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            retVal[i] = (stimulus.getAtomStimulusAt(i).getBandNumber() > 0);
        }
        Random rnd = new Random();
        double rndDouble = rnd.nextDouble();
        System.out.println(rndDouble);
        if (rndDouble > correctnessUpperBound) { // spoil the answer
            int j = rnd.nextInt(Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
            retVal[j] = !retVal[j];
        }
        return retVal;
    }

}
