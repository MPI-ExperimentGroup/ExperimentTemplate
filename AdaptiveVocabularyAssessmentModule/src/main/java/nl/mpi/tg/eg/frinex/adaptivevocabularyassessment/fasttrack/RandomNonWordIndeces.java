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
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * produces the collection of [p * N] indices(for non-word) from 0 to N-1, where
 * p = 1/n is the probability for a non word to appear.
 *
 * @author olhshk
 */
public class RandomNonWordIndeces {

    private final int sequenceLength; //  N

    private final int averageNonwordPosition; // n

    private final int nonwordsPerBlock;

    private final int numberOfNonwords; // [1/n * N]

    private ArrayList<Integer> randomIndices;

    private double[] frequences;

    public RandomNonWordIndeces(int sequenceLength, int nonwordsPerBlock, int averageNonwordPosition) {
        this.sequenceLength = sequenceLength;
        this.averageNonwordPosition = averageNonwordPosition;
        this.nonwordsPerBlock = nonwordsPerBlock;
        this.numberOfNonwords = this.calculateNumberOfNonwords(averageNonwordPosition, sequenceLength);
    }

    private int calculateNumberOfNonwords(int averageNonwordPosition, int sequenceLength) {
        int retVal = sequenceLength / averageNonwordPosition;
        int remainder = sequenceLength - averageNonwordPosition * retVal;
        // if remainer block is "big enough" we may place yet another non-word there
        // "big enough" means that it is "almost averageNonwordPosition"
        double check = ((double) remainder) / ((double) averageNonwordPosition);
        if (check >= 0.5) {
            retVal++;
        }
        return retVal;
    }

    // we divide all the indices from 0 to sequenceLength-1 on blocks,
    // each block has size nonwordsPerBlock*averageNonwordPosition positions
    // and we pick nonwordsPerBlock positions for non-words in each block
    private ArrayList<Integer> calculateRandomIndices() {

        ArrayList<Integer> retVal = new ArrayList<>();
        int randOffset;
        int blockSize = nonwordsPerBlock * this.averageNonwordPosition;
        int numberOfBlocks = this.sequenceLength / blockSize;
        for (int i = 0; i < numberOfBlocks; i++) {
            ArrayList<Integer> offsetBuffer = new ArrayList<>(blockSize);
            
            for (int j = 0; j < blockSize; j++) {
                offsetBuffer.add(j);
            }
            
            int n=blockSize;
            for (int k=0; k<this.nonwordsPerBlock; k++) {
                randOffset = ThreadLocalRandom.current().nextInt(0, n);
                retVal.add(i*blockSize + offsetBuffer.get(randOffset));
                offsetBuffer.remove(randOffset);
                n--;
            }
        }

        // if there are positions to pick up left
        int remainderBlock = this.sequenceLength - blockSize * numberOfBlocks;
        ArrayList<Integer> offsetBuffer = new ArrayList<>(remainderBlock);
        for (int j = 0; j < remainderBlock; j++) {
            offsetBuffer.add(j);
        }
        int n = remainderBlock;
        int nonwordsRemainder = this.numberOfNonwords - retVal.size();
        for (int k=0; k< nonwordsRemainder; k++) {
            randOffset = ThreadLocalRandom.current().nextInt(0, n);
            retVal.add(blockSize * numberOfBlocks + offsetBuffer.get(randOffset));
            offsetBuffer.remove(randOffset);
            n--;
        }
        ////

        Collections.sort(retVal);
        return retVal;
    }

    private int amountOfSelectedIndecesBetween(int a, int b) {
        int retVal = 0;
        for (int i = a; i <= b; i++) {
            if (this.randomIndices.contains(i)) {
                retVal++;
            }
        }
        return retVal;
    }

    private double[] calculateFrequencesOfNonWordIndices() {
        double[] retVal = new double[this.sequenceLength];
        int nonwordsCounter;
        for (int i = 0; i < this.sequenceLength; i++) {
            nonwordsCounter = amountOfSelectedIndecesBetween(0, i);
            retVal[i] = ((double) nonwordsCounter) / ((double) (i + 1));
        }
        return retVal;
    }

    public ArrayList<Integer> updateAndGetIndices() {
        this.randomIndices = this.calculateRandomIndices();
        return this.randomIndices;
    }

    public ArrayList<Integer> getIndices() {
        return this.randomIndices;
    }

    public void updateFrequencesOfNonWordIndices() {
        this.frequences = this.calculateFrequencesOfNonWordIndices();
    }

    public double[] getFrequencesOfNonWordindices() {
        return this.frequences;
    }

}
