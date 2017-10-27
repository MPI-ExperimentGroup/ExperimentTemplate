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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.AtomStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Series;

/**
 *
 * @author olhshk
 */
public class FineTuning extends Series {

    ArrayList<ArrayList<FineTuningStimulus>> stimulae = new ArrayList<>(Constants.NUMBER_OF_BANDS);

    public FineTuning(String username, AtomStimulus[][] wrds, ArrayList<AtomStimulus> nonwrds) {
        super(username, wrds, nonwrds);
    }

    @Override
    public void createStimulae() {
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            ArrayList<FineTuningStimulus> currentBand = this.createStimulaeBand(this.words[i], this.nonwords);
            this.stimulae.add(currentBand);
        }

    }

    // return true if the answer is correct, and false otherwise
    public boolean runStep(int bandNumber, int stimulusNumber, boolean[] answers) {
        FineTuningStimulus stimulus = this.getStimulus(bandNumber, stimulusNumber);
        // checking for mistakes
        boolean retVal = true;
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            AtomStimulus atomStimulus = stimulus.getAtomStimulusAt(i);
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
        return retVal;
    }
    
     public FineTuningStimulus getStimulus(int bandNumber, int stimulusNumber) {
        return this.stimulae.get(bandNumber).get(stimulusNumber);
    }


    private ArrayList<FineTuningStimulus> createStimulaeBand(AtomStimulus[] words, ArrayList<AtomStimulus> nonwords) {
        ArrayList<AtomStimulus> unusedWords = this.fetchUnusedUnits(words);
        ArrayList<AtomStimulus> unusedNonwords = this.fetchUnusedUnits(nonwords);
        int length = unusedWords.size() / Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE;
        if (length > unusedNonwords.size()) {
            length = unusedNonwords.size();
        }
        ArrayList<FineTuningStimulus> retVal = new ArrayList<>(length);

        int wordsCounter = 0;
        int nonwordsCounter = 0;
        for (int i = 0; i < length; i++) {
            AtomStimulus[] tuple = new AtomStimulus[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
            int nonwordPosition = ThreadLocalRandom.current().nextInt(0, Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1);
            for (int j = 0; j < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; j++) {
                if (j != nonwordPosition) {
                    tuple[j] = unusedWords.get(wordsCounter);
                    wordsCounter++;
                } else {
                    tuple[j] = unusedNonwords.get(nonwordsCounter);
                    nonwordsCounter++;
                }
                tuple[j].setIsUsed(true);
            }
            FineTuningStimulus stimulus = new FineTuningStimulus(tuple);
            retVal.add(stimulus);
        }
        return retVal;
    }

   
}
