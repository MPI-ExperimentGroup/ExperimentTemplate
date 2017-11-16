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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;

/**
 *
 * @author olhshk
 */
public class FastTrack {

    protected AtomBookkeepingStimulus[][] words;
    protected ArrayList<AtomBookkeepingStimulus> nonwords;

    private final int startBand;
    private final int averageNonwordPosition;
    private final int nonWordsPerBlock;
    private ArrayList<AtomBookkeepingStimulus> bookkeepingStimuli;

    public FastTrack(AtomBookkeepingStimulus[][] wrds, ArrayList<AtomBookkeepingStimulus> nonwrds, int nonWordsPerBlock, int startBand, int averageNonwordPosition) {
        this.words = wrds;
        this.nonwords = nonwrds;
        this.startBand = startBand;
        this.averageNonwordPosition = averageNonwordPosition;
        this.nonWordsPerBlock = nonWordsPerBlock;

    }

    public int getStartBand() {
        return this.startBand;
    }

    public void createStimulae() {

        this.bookkeepingStimuli = new ArrayList<>();

        //we need to choose the positions for nonwords (from 0 to sequenceLength-1) 
        //int upperBound, int nonwordsPerBlock, int n
        RandomIndexing posChooser = new RandomIndexing(this.startBand, this.nonWordsPerBlock, this.averageNonwordPosition, this.nonwords.size());
        ArrayList<Integer> nonWordInd = posChooser.updateAndGetIndices();

        int bandIndex = this.startBand - 1;
        int nonwordIndex = -1;
        int sequenceLength = posChooser.getFastTrackSequenceLength();
        for (int i = 0; i < sequenceLength; i++) {
            if (nonWordInd.contains(i)) { // i is a position for a non-word
                nonwordIndex++; // pick up the next nonword in the randmomized list of nonwords
                this.bookkeepingStimuli.add(this.nonwords.get(nonwordIndex));
                this.nonwords.get(nonwordIndex).setIsUsed(true);
            } else {
                //we pick up the first word in the randomized band as the stimulus for the fast tracks
                this.bookkeepingStimuli.add(this.words[bandIndex][0]);
                this.words[bandIndex][0].setIsUsed(true);
                bandIndex++;
            }
        }

    }

    public boolean runStep(int stimulusIndex, boolean answer) {
        AtomBookkeepingStimulus stimulus = this.bookkeepingStimuli.get(stimulusIndex);
        stimulus.setReaction(answer);
        boolean eval = true;
        if (stimulus.getBandNumber() < 0 && answer) {
            eval = false;// took a non-word as a word
        }
        if (stimulus.getBandNumber() > 0 && !answer) {
            eval = false;// tool a word as a nonword
        }
        stimulus.setCorrectness(eval);
        return eval;
    }

    public ArrayList<AtomBookkeepingStimulus> getBookeepingStimuli() {
        return this.bookkeepingStimuli;
    }

    public AtomBookkeepingStimulus[][] getBookkeepingWords() {
        return this.words;
    }

    public ArrayList<AtomBookkeepingStimulus> getBookkeepingNonwords() {
        return this.nonwords;
    }

    public ArrayList<AdVocAsAtomStimulus> getPureStimuli() {
        ArrayList<AdVocAsAtomStimulus> retVal = new ArrayList<>(this.bookkeepingStimuli.size());
        for (AtomBookkeepingStimulus bStimulus : bookkeepingStimuli) {
            retVal.add(bStimulus.getPureStimulus());
        }
        return retVal;
    }

   

}
