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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;

/**
 * produces the collection of [p * N] indices(for non-word) from 0 to N-1, where
 * p = 1/n is the probability for a non word to appear.
 *
 * @author olhshk
 */
public class RandomNonWordIndeces {

    private int sequenceLength; //  N

    private final int averageNonwordPosition; // n

    private final int nonwordsPerBlock;

    private final int numberOfNonwords; // [1/n * N]

    private final int numberOfWords; // [(n-1)/n * N]

    private ArrayList<Integer> randomIndices;

    private double[] frequences;

    public RandomNonWordIndeces(int startBand, int nonwordsPerBlock, int averageNonwordPosition, int nonwordsAvailable) {
        this.averageNonwordPosition = averageNonwordPosition;
        this.nonwordsPerBlock = nonwordsPerBlock;
        int wordsAvailable = Constants.NUMBER_OF_BANDS - (startBand - 1);// one from each band
        // number of nonwords = 1/n * sequenceLength
        // number of words = (n-1)/n * sequenceLength
        this.sequenceLength = (averageNonwordPosition * wordsAvailable) / (averageNonwordPosition - 1);
        if (this.sequenceLength > nonwordsAvailable){
            this.sequenceLength = nonwordsAvailable;
            this.numberOfWords = (this.sequenceLength * (averageNonwordPosition - 1))/averageNonwordPosition; 
        } else {
            this.numberOfWords = wordsAvailable;
        }
        
        this.numberOfNonwords = this.sequenceLength - this.numberOfWords;
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
            ArrayList<Integer> offsetBuffer = new ArrayList<>(blockSize-1); // the last element in the block should be always a word to avoid 2 words in a row

            for (int j = 0; j < blockSize-1; j++) {
                offsetBuffer.add(j);
            }
            for (int k = 0; k < this.nonwordsPerBlock; k++) {
                int n=offsetBuffer.size();
                int indOffset = ThreadLocalRandom.current().nextInt(0, n); // excl.n
                int offset = offsetBuffer.get(indOffset);
                retVal.add(i * blockSize + offset);
                offsetBuffer.remove(new Integer(offset));
                offsetBuffer.remove(new Integer(offset-1)); // avoiding neigbouring nonwords
                offsetBuffer.remove(new Integer(offset+1));
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
        for (int k = 0; k < nonwordsRemainder; k++) {
            randOffset = ThreadLocalRandom.current().nextInt(0, n);
            retVal.add(blockSize * numberOfBlocks + offsetBuffer.get(randOffset));
            offsetBuffer.remove(randOffset);
            n--;
        }
        ////

        Collections.sort(retVal);
        this.correctLastPosition(retVal);

        return retVal;
    }

    // the last positin in any sequence must be always a word (from a band 54)
    private void correctLastPosition(ArrayList<Integer> sortedIndices) {
        int lastNonwordPosition = sortedIndices.get(sortedIndices.size() - 1);
        if (lastNonwordPosition == this.sequenceLength - 1) {
            int wordAt = this.findLastWordPosition(sortedIndices, sortedIndices.size() - 1);
            sortedIndices.set(sortedIndices.size() - 1, wordAt);
        }
    }

    private int findLastWordPosition(ArrayList<Integer> sortedIndices, int n) {
        // look for the first gap
        while (sortedIndices.get(n) - sortedIndices.get(n - 1) == 1) {
            n--;
        }
        return sortedIndices.get(n) - 1;
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

    public int getSequenceLength() {
        return this.sequenceLength;
    }

}
