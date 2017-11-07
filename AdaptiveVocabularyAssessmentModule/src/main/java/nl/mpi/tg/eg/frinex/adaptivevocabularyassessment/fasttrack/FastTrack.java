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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Series;

/**
 *
 * @author olhshk
 */
public class FastTrack extends Series {

    private final int startBand;
    private final int averageNonwordPosition;
    private final int nonWordsPerBlock;
    private ArrayList<AtomBookkeepingStimulus> bookkeepingStimuli;

    public FastTrack(String username, AtomBookkeepingStimulus[][] wrds, ArrayList<AtomBookkeepingStimulus> nonwrds, int nonWordsPerBlock, int startBand, int averageNonwordPosition) {
        super(username, wrds, nonwrds);
        this.startBand = startBand;
        this.averageNonwordPosition = averageNonwordPosition;
        this.nonWordsPerBlock = nonWordsPerBlock;

    }
    
    public int getStartBand(){
       return this.startBand; 
    }

    @Override
    public void createStimulae() {

        this.bookkeepingStimuli = new ArrayList<>();

        //we need to choose the positions for nonwords (from 0 to sequenceLength-1) 
        //int upperBound, int nonwordsPerBlock, int n
        RandomNonWordIndeces posChooser = new RandomNonWordIndeces(this.startBand, this.nonWordsPerBlock, this.averageNonwordPosition, this.nonwords.size());
        ArrayList<Integer> nonWordInd = posChooser.updateAndGetIndices();

        int bandIndex = this.startBand - 1;
        int nonwordIndex = -1; 
        ArrayList<AtomBookkeepingStimulus> nonwordsCopy = AtomBookkeepingStimulus.copyAtomStimulae(this.nonwords);
        int sequenceLength = posChooser.getSequenceLength();
        for (int i = 0; i < sequenceLength; i++) {
            if (nonWordInd.contains(i)) { // i is a position for a non-word
                //int nonWordCounter = nonwordsCopy.size();
                //int nonwordIndex = ThreadLocalRandom.current().nextInt(0, nonWordCounter);
                nonwordIndex++; // temporary we pick up the next nonword as the stimulus for the fast tracks
                this.bookkeepingStimuli.add(this.nonwords.get(nonwordIndex));
                this.nonwords.get(nonwordIndex).setIsUsed(true);
                nonwordsCopy.remove(nonwordIndex);
            } else {
                //int wordNumber = ThreadLocalRandom.current().nextInt(0, Constants.WORDS_PER_BAND);
                int wordNumber = 0; // temporary we pick up the first word in the band as the stimulus for the fast tracks
                this.bookkeepingStimuli.add(this.words[bandIndex][wordNumber]);
                
                this.words[bandIndex][wordNumber].setIsUsed(true);
                bandIndex++;
            }
        }

    }

    public boolean runStep(int stimulusNumber, boolean answer) {
        AtomBookkeepingStimulus stimulus = this.bookkeepingStimuli.get(stimulusNumber);
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

    public ArrayList<AtomBookkeepingStimulus> getStimulae() {
        return this.bookkeepingStimuli;
    }

    private void debugTestNonwordFrequences(RandomNonWordIndeces posChooser) {
        posChooser.updateFrequencesOfNonWordIndices();
        double[] freq = posChooser.getFrequencesOfNonWordindices();
        System.out.println("Array of frequences is of length " + freq.length);
        for (int i = 0; i < freq.length; i++) {
            System.out.println(freq[i]);
        }

    }
    
  

}
