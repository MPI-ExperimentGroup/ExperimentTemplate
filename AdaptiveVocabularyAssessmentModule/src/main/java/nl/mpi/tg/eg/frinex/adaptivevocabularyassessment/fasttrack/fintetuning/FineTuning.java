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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Series;

/**
 *
 * @author olhshk
 */
public class FineTuning extends Series {

    ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae = new ArrayList<>(Constants.NUMBER_OF_BANDS);
    
    private int stepCounter;

    public FineTuning(String username, AtomBookkeepingStimulus[][] wrds, ArrayList<AtomBookkeepingStimulus> nonwrds) {
        super(username, wrds, nonwrds);
        stepCounter = 0;
    }

    @Override
    public void createStimulae() throws Exception{
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            ArrayList<FineTuningBookkeepingStimulus> currentBand = this.createStimulaeBand(this.words[i], this.nonwords);
            this.stimulae.add(currentBand);
        }

    }

    // return true if the answer is correct, and false otherwise
    public boolean runStep(int bandNumber, int stimulusIndex, boolean[] answers) throws Exception{
        int bandIndex = bandNumber-1;
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

            atomStimulus.setIsUsed(true);
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

    public ArrayList<ArrayList<FineTuningBookkeepingStimulus>> getStimulae() {
        return this.stimulae;
    }

    private ArrayList<FineTuningBookkeepingStimulus> createStimulaeBand(AtomBookkeepingStimulus[] words, ArrayList<AtomBookkeepingStimulus> nonwords) throws Exception{
        ArrayList<AtomBookkeepingStimulus> unusedWords = this.fetchUnusedAtoms(words);
        ArrayList<AtomBookkeepingStimulus> unusedNonwords = this.fetchUnusedAtomBookkeepingStimulus(nonwords);
        int length = unusedWords.size() / Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE;
        if (length > unusedNonwords.size()) {
            length = unusedNonwords.size();
        }
        ArrayList<FineTuningBookkeepingStimulus> retVal = new ArrayList<>(length);

        int wordsCounter = 0;
        int nonwordsCounter = 0;
        for (int i = 0; i < length; i++) {
            AtomBookkeepingStimulus[] tuple = new AtomBookkeepingStimulus[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
            int nonwordPosition = ThreadLocalRandom.current().nextInt(0, Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
            for (int j = 0; j < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; j++) {
                if (j != nonwordPosition) {
                    tuple[j] = unusedWords.get(wordsCounter);
                    wordsCounter++;
                } else {
                    tuple[j] = unusedNonwords.get(nonwordsCounter);
                    nonwordsCounter++;
                }
            }
            FineTuningBookkeepingStimulus stimulus = new FineTuningBookkeepingStimulus(tuple);
            retVal.add(stimulus);
        }
        return retVal;
    }

    public boolean[] testProbabilisticAnswerer(int bandNumber, int stimulusInBandNummer, double correctnessUpperBound) {
        FineTuningBookkeepingStimulus stimulus = this.stimulae.get(bandNumber-1).get(stimulusInBandNummer);
        boolean[] retVal = new boolean[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            retVal[i] = (stimulus.getAtomStimulusAt(i).getBandNumber() > 0);
        }
        double rnd = ThreadLocalRandom.current().nextDouble();
        if (rnd > correctnessUpperBound) { // spoil the answer
            int j = ThreadLocalRandom.current().nextInt(0, Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
            retVal[j] = !retVal[j];
        }
        return retVal;
    }

}
