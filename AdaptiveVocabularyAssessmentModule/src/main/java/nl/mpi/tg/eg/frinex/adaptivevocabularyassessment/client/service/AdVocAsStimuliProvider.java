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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.FastTrack;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuning;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuningBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuningException;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends AbstractStimuliProvider {

    FastTrack fastTrack = null;
    FineTuning fineTuning = null;

    private int totalStimuli;
    private int score = -1;
    private Boolean isCorrectCurrentResponse;
    private int experimentCount = 1;

    // fast track stuff
    private int fastTrackStimuliIndex = 0;
    private int bestBandFastTrack = -1;

    // fine tuning stuff
    private int counterInTupleFTuning = 0;
    private int bandIndexFineTuning = -1;
    private int bandChangeCounterFTuning = 0;
    private final boolean[] answersFTuning = new boolean[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
    private final int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];

    // fine tuning stopping
    private boolean enoughFineTuningStimulae = true;
    private final int[] cycle2helper = new int[Constants.FINE_TUNING_UPPER_BOUND_FOR_2CYCLES * 2 + 1];
    private boolean cycle2 = false;
    private boolean secondStoppingCriterion = false;
    private boolean champion = false;
    private boolean looser = false;
    private boolean justVisitedLastBand = false;
    private boolean justVisitedFirstBand = false;

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        AdVocAsAtomStimulus[][] words = ConstantsWords.WORDS;
        ArrayList<AdVocAsAtomStimulus> nonwords = new ArrayList<>();
        nonwords.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));

        this.fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
        this.fastTrack.createStimulae();

        AtomBookkeepingStimulus[][] bookkeepingWords = this.fastTrack.getBookkeepingWords();
        ArrayList<AtomBookkeepingStimulus> bookkeepingNonwords = this.fastTrack.getBookkeepingNonwords();
        this.fineTuning = new FineTuning(Constants.DEFAULT_USER, bookkeepingWords, bookkeepingNonwords);
        try {
            fineTuning.createStimulae();
            int totalFTuning = fineTuning.getTotalStimuli();
            this.totalStimuli = this.getFastTrackStimuli().size() + totalFTuning;
        } catch (FineTuningException ex) {
            System.out.println("Error when creating a fine-tuning stimulus: " + ex.getMessage());
        }
    }

    public ArrayList<AtomBookkeepingStimulus> getFastTrackStimuli() {
        return this.fastTrack.getBookeepingStimuli();
    }
    
    public int getFastTrackIndex(){
        return this.fastTrackStimuliIndex;
    }

    // thinking that the indices geot update by "next stimulus" 
    @Override
    public AdVocAsAtomStimulus getCurrentStimulus() {
        if (this.bestBandFastTrack < 0) { // fast track is still on
            return this.getFastTrackStimuli().get(fastTrackStimuliIndex).getPureStimulus();
        } else {
            AtomBookkeepingStimulus bStimulus = this.getCurrentAtomBookkeepingStimulusInFineTuning();
            return bStimulus.getPureStimulus();
        }
    }

    private AtomBookkeepingStimulus getCurrentAtomBookkeepingStimulusInFineTuning() {
        FineTuningBookkeepingStimulus bfStimulus = this.getCurrentBookkeepingStimulusInFineTuning();
        AtomBookkeepingStimulus retVal = bfStimulus.getAtomStimulusAt(this.counterInTupleFTuning);
        return retVal;
    }

    private FineTuningBookkeepingStimulus getCurrentBookkeepingStimulusInFineTuning() {
        ArrayList<FineTuningBookkeepingStimulus> band = this.fineTuning.getStimuli().get(this.bandIndexFineTuning);
        int stimIndex = this.bandVisitCounter[this.bandIndexFineTuning];
        FineTuningBookkeepingStimulus retVal = band.get(stimIndex);
        return retVal;
    }

    // aka get current experiment 
    @Override
    public int getCurrentStimulusIndex() {
        return this.experimentCount;
    }

    @Override
    public int getTotalStimuli() {
        return this.totalStimuli;
    }

    // actually defines if the is to be continued or stopped
    @Override
    public boolean hasNextStimulus(int increment) {
        if (this.bestBandFastTrack < 0) { // fast track is still on, update it
            boolean fastrackOn = this.fastTrackToBeContinued();
            if (!fastrackOn) {
                // prepare indices for the fine tuning
                this.switchToFineTuning();
            }
            return true;
        } else {
            return this.fineTuningToBeContinued();
        }

    }

    // all indices are updated in the "hasNextStimulus"
    @Override
    public void nextStimulus(int increment) {
        return;
    }

    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
        //  not relevant for now
        //this.fastTrackStimuliIndex = currentStimuliIndex;
    }

    @Override
    public String getCurrentStimulusUniqueId() {
        return getCurrentStimulus().getUniqueId();
    }

    @Override
    public String generateStimuliStateSnapshot() {
        return ""; //todo: serialise your current stimuli list here
    }

    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        this.experimentCount++; // in any case, count experiment as done
        boolean isResponseWord = true;
        if (!stimulusResponse.equals("word")) {
            isResponseWord = false;
        }
        if (this.bestBandFastTrack < 0) { //we are in fine tuning
            this.isCorrectCurrentResponse = this.fastTrack.runStep(this.fastTrackStimuliIndex, isResponseWord);
        } else { // fine tuning
            this.answersFTuning[this.counterInTupleFTuning] = isResponseWord;
            if (this.counterInTupleFTuning == Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1) {
                // we have filled-in the tuple
                int currentBand = this.bandIndexFineTuning + 1;
                int currentFTStimulus = this.bandVisitCounter[this.bandIndexFineTuning];
                boolean correctness = fineTuning.runStep(currentBand, currentFTStimulus, this.answersFTuning);
                this.isCorrectCurrentResponse = correctness;
            } else {
                this.isCorrectCurrentResponse = false;
            }
        }
        return this.isCorrectCurrentResponse;
    }

    // also updates indices 
    private boolean fastTrackToBeContinued() {
        if (this.experimentCount == 1) {// just started the first experiment..
            return true;
        }
        if (this.isCorrectCurrentResponse) {
            if (this.fastTrackStimuliIndex + 1 < this.getFastTrackStimuli().size()) {
                this.fastTrackStimuliIndex++;
                return true;
            }
        } else {
            // hit incorrect? go back one band and transfer to fine tuning!
            this.fastTrackStimuliIndex = this.fastTrackStimuliIndex > 0 ? (fastTrackStimuliIndex--) : 0;
            
        }
        return false;
    }

    private void switchToFineTuning() {
        int index = this.findMaxWordIndex();
        this.bestBandFastTrack = this.getFastTrackStimuli().get(index).getBandNumber();

        // to initialise for fine tuning :     
        //private int counterInTupleFTuning = 0;
        //private int bandIndexFineTuning = -1;
        //private int bandChangeCounterFTuning = 0;
        //private final boolean[] answersFTuning = new boolean[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
        //private final int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];
        this.counterInTupleFTuning = 0;
        this.bandIndexFineTuning = this.bestBandFastTrack - 1;
        this.bandChangeCounterFTuning = 0;
        for (int i = 0; i < this.answersFTuning.length; i++) {
            this.answersFTuning[i] = false;
        }
        for (int i = 0; i < this.bandVisitCounter.length; i++) {
            this.bandVisitCounter[i] = 0;
        }

    }
    
    private int findMaxWordIndex(){
        for (int i=this.fastTrackStimuliIndex; i>=0; i--){
            if (this.getFastTrackStimuli().get(i).getBandNumber()>0) {
                return i;
            }
        }
        return -1;
    }

    //also updates the indices
    private boolean fineTuningToBeContinued() {

        if (this.counterInTupleFTuning < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1) {
            // we did not hit the last atom in the tuple
            this.counterInTupleFTuning++;
            return true;
        }

        // to update for the next step :     
        //upd 1: private int counterInTupleFTuning = 0; 
        //upd 2: private int bandIndexFineTuning = -1;
        //upd 3: private int bandChangeCounterFTuning = 0;
        //upd 4: private final boolean[] answersFTuning = new boolean[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];
        //upd 5: private final int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];
        // fix the fact the current-band visit is finished
        this.bandVisitCounter[this.bandIndexFineTuning]++; // upd 5

        // detecting if this step gives a loop, and if such detected, computing the score
        // the decription: "after three oscillations"
        // the correctness/non correctenss  of the last step, how it influences ???
        int currentBand = this.bandIndexFineTuning + 1;
        shiftFIFO(cycle2helper, currentBand);
        this.cycle2 = detectLoop(cycle2helper);
        if (this.cycle2) {
            System.out.println("Detected: three times oscillation between two neighbouring bands");
            this.score = this.cycle2helper[0];
            for (int i = 1; i < this.cycle2helper.length; i++) {
                if (this.score > this.cycle2helper[i]) {
                    this.score = this.cycle2helper[i];
                }
            }
            return false;
        }

        this.secondStoppingCriterion = timeToCountVisits(this.bandChangeCounterFTuning);
        if (this.secondStoppingCriterion) {
            this.score = AdVocAsStimuliProvider.mostOftenVisited(this.bandVisitCounter);
            return false;
        }

        // correctness actually influences only the transition to th next band
        int nextBand = this.bandIndexFineTuning;
        if (this.isCorrectCurrentResponse) {
            if (currentBand == Constants.NUMBER_OF_BANDS) { // the last band is hit
                if (this.justVisitedLastBand) {
                    this.champion = true;
                    return false; // stop interation, the last band visiedt twice in a row
                } else {
                    this.justVisitedLastBand = true; // the second trial to be sure
                    // nextBand is the same
                }
            } else {
                // ordinary next band interation
                this.justVisitedLastBand = false;
                nextBand++;
                this.bandChangeCounterFTuning++; // upd 3
            }
        } else {
            if (currentBand == 1) {
                if (this.justVisitedFirstBand) {
                    this.looser = true;// stop interation, the first band is visited twice in a row
                    return false;
                } else {
                    this.justVisitedFirstBand = true; // the second chance
                    // nextBand is the same
                }
            } else {
                this.justVisitedFirstBand = false;
                nextBand--;
                this.bandChangeCounterFTuning++; // upd 3
            }
        }

        // now if we have to check if there are enough stimuli for the following experiment
        // checking the erronenous situation
        int indexNextStimulus = this.bandVisitCounter[nextBand];
        this.enoughFineTuningStimulae = (indexNextStimulus < this.fineTuning.getStimuli().get(nextBand).size());
        if (this.enoughFineTuningStimulae) {
            // initialise next tuple analysis
            this.bandIndexFineTuning = nextBand; // upd 2
            this.counterInTupleFTuning = 0; // upd 1
            for (int i = 0; i < this.answersFTuning.length; i++) { // upd 4
                this.answersFTuning[i] = false;
            }
            return true;
        }
        System.out.println("Not enough fine-tuning compound stimula left for the next band " + nextBand + ". Index: " + indexNextStimulus + "; size: " + this.fineTuning.getStimuli().get(nextBand).size());
        return false;
    }

    public static boolean detectLoop(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i + 2] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void shiftFIFO(int[] fifo, int newelement) {
        for (int i = 0; i < fifo.length - 1; i++) {
            fifo[i] = fifo[i + 1];
        }
        fifo[fifo.length - 1] = newelement;
    }

    public static boolean timeToCountVisits(int changeCounter) {
        return (changeCounter > Constants.FINE_TUNING_MAX_BAND_CHANGE);
    }

    public static int mostOftenVisited(int[] bandVisitCounter) {
        int candidate_min = 0;
        int candidate_max = 0;
        for (int i = 1; i < bandVisitCounter.length; i++) {
            if (bandVisitCounter[candidate_min] < bandVisitCounter[i]) {
                candidate_min = i;
                candidate_max = i;
            }
            if (bandVisitCounter[candidate_min] == bandVisitCounter[i]) {
                candidate_max = i;
            }
        }

        if (candidate_max > candidate_min) {
            int visits = bandVisitCounter[candidate_min];
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < bandVisitCounter.length; i++) {
                if (bandVisitCounter[i] == visits) {
                    candidates.add(i);
                }
            }
            int i = 0;
            boolean found = false;
            int current = -2;
            while (i < candidates.size() - 1 && !found) {
                int index1 = candidates.get(i);
                int index2 = candidates.get(i + 1);
                current = chooseBand(index1, index2, bandVisitCounter);
                if (current == index1) {
                    found = true;
                } else {
                    i++;
                }
            }
            return current + 1;
        } else {
            return candidate_min + 1;
            // band nummer is its index plus 1
        }

    }

    public static int chooseBand(int index1, int index2, int[] bandVisitCounter) {
        if (index2 > index1) {
            // count visist below index2
            int visitsBelow = 0;
            for (int i = 0; i < index2; i++) {
                visitsBelow += bandVisitCounter[i];
            }

            // count visist below candidate_max
            int visitsAbove = 0;
            for (int i = index2 + 1; i < bandVisitCounter.length; i++) {
                visitsAbove += bandVisitCounter[i];
            }
            if (visitsBelow > visitsAbove) {
                return index1;
            } else {
                return index2;
            }
        } else {
            return -1; // erroneous input
        }
    }

}
