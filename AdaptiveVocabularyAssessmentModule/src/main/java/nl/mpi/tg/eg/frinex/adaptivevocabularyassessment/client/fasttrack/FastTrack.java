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

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;

/**
 *
 * @author olhshk
 */
public class FastTrack {

    protected AtomBookkeepingStimulus[][] words;
    protected ArrayList<AtomBookkeepingStimulus> nonwords;
    protected String userName;

    
    private final int startBand;
    private final int averageNonwordPosition;
    private final int nonWordsPerBlock;
    private ArrayList<AtomBookkeepingStimulus> bookkeepingStimuli;

    public FastTrack(String username, AdVocAsAtomStimulus[][] wrds, ArrayList<AdVocAsAtomStimulus> nonwrds, int nonWordsPerBlock, int startBand, int averageNonwordPosition) {
        this.userName=username;
        this.words = this.initialiseWords(wrds);
        this.nonwords = this.initialiseNonwords(nonwrds);
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
        RandomNonWordIndeces posChooser = new RandomNonWordIndeces(this.startBand, this.nonWordsPerBlock, this.averageNonwordPosition, this.nonwords.size());
        ArrayList<Integer> nonWordInd = posChooser.updateAndGetIndices();

        int bandIndex = this.startBand - 1;
        int nonwordIndex = -1;
        ArrayList<AtomBookkeepingStimulus> nonwordsCopy = AtomBookkeepingStimulus.copyAtomStimulae(this.nonwords);
        int sequenceLength = posChooser.getSequenceLength();
        for (int i = 0; i < sequenceLength; i++) {
            if (nonWordInd.contains(i)) { // i is a position for a non-word
                //int nonWordCounter = nonwordsCopy.size();
                //int nonwordIndex = rnd.nextInt(nonWordCounter);
                nonwordIndex++; // temporary we pick up the next nonword as the stimulus for the fast tracks
                this.bookkeepingStimuli.add(this.nonwords.get(nonwordIndex));
                this.nonwords.get(nonwordIndex).setIsUsed(true);
                nonwordsCopy.remove(nonwordIndex);
            } else {
                //int wordNumber = rnd.nextInt(Constants.WORDS_PER_BAND);
                int wordNumber = 0; // temporary we pick up the first word in the band as the stimulus for the fast tracks
                this.bookkeepingStimuli.add(this.words[bandIndex][wordNumber]);
                this.words[bandIndex][wordNumber].setIsUsed(true);
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
    
     public ArrayList<AtomBookkeepingStimulus> getBookeepingStimuli(){
        return this.bookkeepingStimuli;
    }
    
    
    public AtomBookkeepingStimulus[][] getBookkeepingWords(){
        return this.words;
    }
    
    public ArrayList<AtomBookkeepingStimulus> getBookkeepingNonwords(){
        return this.nonwords;
    }
    
  

    public ArrayList<AdVocAsAtomStimulus> getPureStimuli() {
        ArrayList<AdVocAsAtomStimulus> retVal = new ArrayList<>(this.bookkeepingStimuli.size());
        for (AtomBookkeepingStimulus bStimulus : bookkeepingStimuli) {
            retVal.add(bStimulus.getPureStimulus());
        }
        return retVal;
    }

    private AtomBookkeepingStimulus[][] initialiseWords(AdVocAsAtomStimulus[][] wrds) {
        if (wrds == null || wrds.length == 0) {
            System.out.println("Empty array of words in bands");
            return new AtomBookkeepingStimulus[0][0];
        }
        AtomBookkeepingStimulus[][] retVal = new AtomBookkeepingStimulus[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
        for (int bandIndex = 0; bandIndex < wrds.length; bandIndex++) {
            if (wrds[bandIndex] == null && wrds[bandIndex].length == 0) {
                System.out.println("Empty array of words for band " + bandIndex);
                retVal[bandIndex] = new AtomBookkeepingStimulus[0];
            } else {
                for (int wordCounter = 0; wordCounter < wrds[bandIndex].length; wordCounter++) {
                    AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(wrds[bandIndex][wordCounter]);
                    retVal[bandIndex][wordCounter] = stimulus;
                }
            }
        }
        return retVal;
    }

    private ArrayList<AtomBookkeepingStimulus> initialiseNonwords(ArrayList<AdVocAsAtomStimulus> nonwrds) {
        if (nonwrds == null || nonwrds.isEmpty()) {
            System.out.println("Empty array of nonwords");
            return new ArrayList<>();
        }
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>(nonwrds.size());
        for (AdVocAsAtomStimulus nonword : nonwrds) {
            AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(nonword);
            retVal.add(stimulus);
        }
        return retVal;
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
