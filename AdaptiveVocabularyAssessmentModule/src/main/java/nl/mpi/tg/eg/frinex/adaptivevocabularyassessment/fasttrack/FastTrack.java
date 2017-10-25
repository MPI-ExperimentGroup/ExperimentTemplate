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

import java.io.IOException;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.Bands;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.LexicalUnit;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;

/**
 *
 * @author olhshk
 */
public class FastTrack {

    private final String username;
    private final int startBand;
    private final int averageNonwordPosition;
    private LexicalUnit[][] words;
    private ArrayList<LexicalUnit> nonwords;
    private ArrayList<LexicalUnit> stimulaeSequence;

    public FastTrack(String username, int startBand, int averageNonwordPosition) {
        this.username = username;
        this.startBand = startBand;
        this.averageNonwordPosition = averageNonwordPosition;
    }
    
    
    public void initialiseWordLists() throws IOException {
       Bands bands = new Bands();
        bands.parseWordInputCSV();
        bands.parseNonWordInputCSV();
        this.words = bands.getWords();
        this.nonwords = bands.getNonWords();

    }

    public void makeStimulaeSequence(int nonWordsPerBlock) throws Exception {

        this.stimulaeSequence = new ArrayList<>();

        //we need to choose the positions for nonwords (from 0 to sequenceLength-1) 
        //int upperBound, int nonwordsPerBlock, int n
        RandomNonWordIndeces posChooser = new RandomNonWordIndeces(this.startBand, nonWordsPerBlock, this.averageNonwordPosition);
        ArrayList<Integer> nonWordInd = posChooser.updateAndGetIndices();
        
        int bandCounter = this.startBand - 1;
        int nonwordCounter = 0;
        int sequenceLength = posChooser.getSequenceLength();
        for (int i = 0; i < sequenceLength; i++) {
            if (nonWordInd.contains(i)) {
                try {
                    this.stimulaeSequence.add(this.nonwords.get(nonwordCounter));
                    nonwordCounter++;
                } catch (IndexOutOfBoundsException ex) {
                    throw new Exception("There is no non-used nonwords left to generate a stimulae sequence of length " + sequenceLength + " starting from band " + this.startBand);
                }
            } else {
                try {
                    this.stimulaeSequence.add(this.words[bandCounter][0]);
                    bandCounter++;
                } catch (IndexOutOfBoundsException ex) {
                    throw new Exception("There is no non-used words left to generate a stimulae sequence of length " + sequenceLength + " starting from band " + this.startBand);
                }
            }
        }

    }

    public ArrayList<LexicalUnit> getStimulaeSequence() {
        return this.stimulaeSequence;
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
