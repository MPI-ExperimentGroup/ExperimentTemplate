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
    private int totalStimuli =0;

    private boolean isCurrentCorrect = true;
    private int fastTrackStimuliIndex = 0;
    private int bestBandFastTrack = -1;

    private int currtentBandInFineTuning = -1;
    private int fineTuningBandChangeCounter = 0;
    private int[] currentFineTuningStimulusInBand = new int[Constants.NUMBER_OF_BANDS];
    private int counterInTuple = 0;
    private String[] answersInTuple = new String[Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE];

    private boolean enoughFineTuningStimulae = true;
    private int[] cycle2detector = new int[Constants.FINE_TUNING_UPPER_BOUND_FOR_2CYCLES * 2 + 1];
    private boolean cycle2 = false;
    private boolean secondStoppingCriterion = false;

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
            this.totalStimuli = this.fastTrackSequence.size() + fineTuning.getTotalStimuli();
        } catch (FineTuningException ex) {
            System.out.println("Error when creating a fine-tuning stimulus: " + ex.getMessage());
        }
    }

    
    @Override
    public Stimulus getCurrentStimulus() {
        if (this.bestBandFastTrack < 0) { // fast track is still on
            return fastTrackSequence.get(fastTrackStimuliIndex).getPureStimulus();
        } else {
            AtomBookkeepingStimulus bStimulus = this.getCurrentAtomBookkeepingStimulusInFineTuning();
            return bStimulus.getPureStimulus();
        }
    }
    
    private FineTuningBookkeepingStimulus getCurrentBookkeepingStimulusInFineTuning() {
        ArrayList<FineTuningBookkeepingStimulus> band = this.fineTuningSequence.get(this.currtentBandInFineTuning);
        int bandIndex = this.currtentBandInFineTuning - 1;
        FineTuningBookkeepingStimulus retVal = band.get(bandIndex);
        return retVal;
    }

    private AtomBookkeepingStimulus getCurrentAtomBookkeepingStimulusInFineTuning() {
        FineTuningBookkeepingStimulus bfStimulus = this.getCurrentBookkeepingStimulusInFineTuning();
        AtomBookkeepingStimulus retVal = bfStimulus.getAtomStimulusAt(this.counterInTuple);
        return retVal;
    }

    @Override
    public int getCurrentStimulusIndex() {
        return 0; // not relevant for now
    }

    @Override
    public int getTotalStimuli() {
        return this.totalStimuli; 
    }

    @Override
    public boolean hasNextStimulus(int increment) {
        if (this.bestBandFastTrack < 0) { // fast track is still on
            return this.fastTrackToBeContinued(increment);
        } else {
            return this.fineTuningToBeContinued(increment);
        }
    }

    @Override
    public void nextStimulus(int increment) { // updating indices
        if (this.bestBandFastTrack < 0) {
            fastTrackStimuliIndex++;
        } else {
            if ((this.counterInTuple % Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE) == 0) {
                this.fineTuningBandChangeCounter++;
                // we need to go to the next band
                // all anwers in the tuple must be correct
                boolean allCorrect = true;
                for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
                    allCorrect &= this.getCurrentAtomBookkeepingStimulusInFineTuning().getCorrectness();
                }
                this.getCurrentBookkeepingStimulusInFineTuning().setOverallCorrectness(allCorrect);
                if (allCorrect) { // one band higher 
                    this.currtentBandInFineTuning++;
                } else {
                    this.currtentBandInFineTuning--; // one band lower
                }
                this.counterInTuple = 0;
            } else {
                this.counterInTuple++;
            }

        }

    }

    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
        this.fastTrackStimuliIndex = currentStimuliIndex;
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

    private boolean fastTrackToBeContinued(int increment) {
        if (this.isCurrentCorrect) {
            if (this.fastTrackStimuliIndex + increment < this.fastTrackSequence.size()) {
                return true;
            } else {
                this.bestBandFastTrack = this.fastTrackSequence.get(fastTrackStimuliIndex).getBandNumber();
                this.currtentBandInFineTuning = this.bestBandFastTrack;
                return false;
            }
        } else {
            int previousIndex = (this.fastTrackStimuliIndex - increment) >= 0 ? (fastTrackStimuliIndex - increment) : 0;
            this.bestBandFastTrack = this.fastTrackSequence.get(previousIndex).getBandNumber();
            this.currtentBandInFineTuning = this.bestBandFastTrack;
            return false;
        }
    }

    private boolean fineTuningToBeContinued(int increment) {
        this.enoughFineTuningStimulae = true; // TODO!!!
        if (!this.enoughFineTuningStimulae) {
            System.out.println("Erroneous situation: not enough stimulae");
            return false;
        }
        this.cycle2 = detectLoop(cycle2detector);
        if (this.cycle2) {
            return false;
        }
        this.secondStoppingCriterion = timeToCountVisits(this.fineTuningBandChangeCounter);
        if (this.secondStoppingCriterion) {
            return false;
        }
        return true;
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
