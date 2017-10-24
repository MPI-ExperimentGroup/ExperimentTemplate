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

    private String username;

    private LexicalUnit[][] words;
    private ArrayList<LexicalUnit> nonwords;
    private ArrayList<LexicalUnit> stimulaeSequence;

    public FastTrack(String username) {
        this.username = username;
    }

    public void initialiseWordLists() throws IOException {

        Bands bands = new Bands();
        bands.parseWordInputCSV();
        bands.parseNonWordInputCSV();
        this.words = bands.getWords();
        this.nonwords = bands.getNonWords();

    }

    public void makeStimulaeSequence(int startBand, int frequenceStep, int sequenceLength) throws Exception {

        this.stimulaeSequence = new ArrayList<>(sequenceLength);

        // we need to choose the positions for wnonwords (from 0 to sequenceLength-1) 
        RandomNonWordIndeces posChooser = new RandomNonWordIndeces(sequenceLength, frequenceStep, Constants.NONWORD_PROBABILITY);
        ArrayList<Integer> nonWordInd = posChooser.updateAndGetIndices();

        int bandCounter = startBand-1;
        int nonwordCounter = 0;
        for (int i = 0; i < sequenceLength; i++) {
            if (nonWordInd.contains(i)) {
                try {
                    stimulaeSequence.set(i, this.nonwords.get(nonwordCounter));
                    nonwordCounter++;
                } catch (IndexOutOfBoundsException ex) {
                    throw new Exception("There is no non-used nonwords left to generate a stimulae sequence of length " + sequenceLength + " starting from band " + startBand);
                }
            } else {
                try {
                    stimulaeSequence.set(i, this.words[bandCounter][0]);
                    bandCounter++;
                } catch (IndexOutOfBoundsException ex) {
                    throw new Exception("There is no non-used words left to generate a stimulae sequence of length " + sequenceLength + " starting from band " + startBand);
                }
            }
        }

    }

    public ArrayList<LexicalUnit> getStimulaeSequence() {
        return this.stimulaeSequence;
    }

}
