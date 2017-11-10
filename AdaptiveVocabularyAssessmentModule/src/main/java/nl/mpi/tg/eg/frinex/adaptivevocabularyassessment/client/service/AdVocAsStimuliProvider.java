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

    private ArrayList<AtomBookkeepingStimulus> fastTrackSequence = new ArrayList();
    private ArrayList<ArrayList<FineTuningBookkeepingStimulus>> fineTuningSequence = new ArrayList();
    private int totalStimuli;
    private int experimentCounter = 1;
    private int score = -1;

    private boolean isCurrentCorrect = true;
    private int fastTrackStimuliIndex = 0;
    private int bestBandFastTrack = -1;

    // fine tuning stuff
    private boolean justVisitedLastBand = false;
    private boolean justVisitedFirstBand = false;
    private int bandIndexFineTuning = -1;
    private int bandChangeCounterFTuning = 0;
    private int counterInTupleFTuning = 0;
    private final int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];
    private boolean enoughFineTuningStimulae = true;
    private final int[] cycle2helper = new int[Constants.FINE_TUNING_UPPER_BOUND_FOR_2CYCLES * 2 + 1];
    private boolean cycle2 = false;
    private boolean secondStoppingCriterion = false;
    private boolean champion = false;
    private boolean looser = false;

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {
        fastTrackSequence.clear();
        fineTuningSequence.clear();

        AdVocAsAtomStimulus[][] words = ConstantsWords.WORDS;
        ArrayList<AdVocAsAtomStimulus> nonwords = new ArrayList<>();
        nonwords.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));

        FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
        fastTrack.createStimulae();
        this.fastTrackSequence = fastTrack.getBookeepingStimuli();

        AtomBookkeepingStimulus[][] bookkeepingWords = fastTrack.getBookkeepingWords();
        ArrayList<AtomBookkeepingStimulus> bookkeepingNonwords = fastTrack.getBookkeepingNonwords();
        FineTuning fineTuning = new FineTuning(Constants.DEFAULT_USER, bookkeepingWords, bookkeepingNonwords);
        try {
            fineTuning.createStimulae();
            this.fineTuningSequence = fineTuning.getStimuli();
            int totalFTuning = fineTuning.getTotalStimuli();
            this.totalStimuli = this.fastTrackSequence.size() + totalFTuning;
        } catch (FineTuningException ex) {
            System.out.println("Error when creating a fine-tuning stimulus: " + ex.getMessage());
        }
    }

    // thinking that the indices geot update by "next stimulus" 
    @Override
    public Stimulus getCurrentStimulus() {
        if (this.bestBandFastTrack < 0) { // fast track is still on
            return fastTrackSequence.get(fastTrackStimuliIndex).getPureStimulus();
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
        ArrayList<FineTuningBookkeepingStimulus> band = this.fineTuningSequence.get(this.bandIndexFineTuning);
        int stimIndex = this.bandVisitCounter[this.bandIndexFineTuning];
        FineTuningBookkeepingStimulus retVal = band.get(stimIndex);
        return retVal;
    }

    // aka get current experiment 
    @Override
    public int getCurrentStimulusIndex() {
        return this.experimentCounter;
    }

    @Override
    public int getTotalStimuli() {
        return this.totalStimuli;
    }

    // actually defines if the is to be continued or stopped
    @Override
    public boolean hasNextStimulus(int increment) {
        boolean fastTrackOn = this.bestBandFastTrack < 0;
        if (fastTrackOn) { // fast track is still on, update it
            fastTrackOn = this.fastTrackToBeContinued();
        }
        if (fastTrackOn) {
            return true;
        }
        return this.fineTuningToBeContinued();

    }

    // check "hasNextStimulus" aka "toBeContinued" is passed
    // this method is aka update indices
    @Override
    public void nextStimulus(int increment) { // updating indices
        if (this.bestBandFastTrack < 0) {
            this.fastTrackStimuliIndex++;
        } else {
            
            // the last element in the tuple
            if (this.counterInTupleFTuning == Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE-1) {
                boolean allCorrect = this.getCurrentBookkeepingStimulusInFineTuning().getOverallCorrectness();
                
                this.bandVisitCounter[this.bandIndexFineTuning]++;
                
                // bookeep the visit the current band and go to the next one
                
                if (allCorrect) {
                    if (!this.justVisitedLastBand) {  // one band higher 
                        this.bandIndexFineTuning++;
                        this.bandChangeCounterFTuning++;
                    }
                } else {
                    if (!this.justVisitedFirstBand) { // one band lower
                        this.bandIndexFineTuning--;
                        this.bandChangeCounterFTuning++;
                    }
                }
                this.counterInTupleFTuning = 0;

            } else { // we have not finished the tuple
                this.counterInTupleFTuning++;
            }

        }

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
        this.isCurrentCorrect = super.isCorrectResponse(stimulus, stimulusResponse);

        // bookkeeping part
        if (this.bestBandFastTrack < 0) {
            AtomBookkeepingStimulus currentBStimulus = this.fastTrackSequence.get(this.fastTrackStimuliIndex);
            currentBStimulus.setCorrectness(this.isCurrentCorrect);
        } else {
            AtomBookkeepingStimulus bStimulus = this.getCurrentAtomBookkeepingStimulusInFineTuning();
            bStimulus.setCorrectness(this.isCurrentCorrect);
        }

        return this.isCurrentCorrect;
    }

    private boolean fastTrackToBeContinued() {
        if (this.isCurrentCorrect) {
            if (this.fastTrackStimuliIndex + 1 < this.fastTrackSequence.size()) {
                return true;
            } else {
                this.bestBandFastTrack = this.fastTrackSequence.get(fastTrackStimuliIndex).getBandNumber();
                this.bandIndexFineTuning = this.bestBandFastTrack - 1;
                return false;
            }
        } else {
            int previousIndex = (this.fastTrackStimuliIndex - 1) >= 0 ? (fastTrackStimuliIndex - 1) : 0;
            this.bestBandFastTrack = this.fastTrackSequence.get(previousIndex).getBandNumber();
            this.bandIndexFineTuning = this.bestBandFastTrack - 1;
            return false;
        }
    }

    private boolean fineTuningToBeContinued() {

        if (this.counterInTupleFTuning < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE-1) {
           // the tuple is not finished
            return true;
        }

        // we are in the situation when the tuple is finished
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

        // we need to go to the next/previous band, possibly detecting a champion/looser
        boolean allCorrect = true;
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            allCorrect &= this.getCurrentBookkeepingStimulusInFineTuning().getAtomStimulusAt(i).getCorrectness();
        }
        this.getCurrentBookkeepingStimulusInFineTuning().setOverallCorrectness(allCorrect);

        int nextBand = this.bandIndexFineTuning;
        if (allCorrect) {
            if (this.bandIndexFineTuning + 1 == Constants.NUMBER_OF_BANDS) {
                if (this.justVisitedLastBand) {
                    this.champion = true;
                    return false;
                } else {
                    this.justVisitedLastBand = true; // the second trial to be sure
                    // nextBand is the same
                }
            } else {
                this.justVisitedLastBand = false;
                nextBand++;
            }
        } else {
            if (this.bandIndexFineTuning == 0) {
                if (this.justVisitedFirstBand) {
                    this.looser = true;
                    return false;
                } else {
                    this.justVisitedFirstBand = true; // the second chance
                    // nextBand is the same
                }
            } else {
                this.justVisitedFirstBand = false;
                nextBand--;
            }
        }

        // now if we have to check if there are enough stimuli for the following experiment
        // checking the erronenous tituation
        int indexNextStimulus = this.bandVisitCounter[nextBand];
        this.enoughFineTuningStimulae = (indexNextStimulus < this.fineTuningSequence.get(nextBand).size());
        if (this.enoughFineTuningStimulae) {
            return true;
        }
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
