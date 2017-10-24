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
import org.apache.commons.lang3.ArrayUtils;

/**
 * produces the collection of [p * N] indices(for non-word) from 0 to N-1, where
 * p is the probability for a non word to appear.
 *
 * @author olhshk
 */
public class RandomNonWordIndeces {

    private int upperBound; //  N
    
    private int step;

    private int amountOfIndeces; // [p * N]

    private ArrayList<Integer> randomIndices;

    private double[] frequences;

    private int[] buffer;

    public RandomNonWordIndeces(int upperBound, int step, double probability) {
        this.upperBound = upperBound;
        this.step = step;
        this.amountOfIndeces = this.calculateNumberOfIndices(probability, upperBound);
    }

    private int calculateNumberOfIndices(double probability, int n) {
        double tmp = probability * n;
        double floor = Math.floor(tmp);
        int retVal = (int) tmp;
        if (tmp - floor >= 0.5) {
            retVal++;
        }
        return retVal;
    }

    private void initIndexBuffer() {
        this.buffer = new int[this.upperBound];
        for (int i = 0; i < this.upperBound; i++) {
            this.buffer[i] = i;
        }

    }

    private ArrayList<Integer> calculateRandomIndices() {
        
        this.initIndexBuffer();
        ArrayList<Integer> retVal = new ArrayList<>();
        int n = this.upperBound;
        int j;
        for (int i = 0; i < this.amountOfIndeces; i++) {
            j = ThreadLocalRandom.current().nextInt(0, n);
            retVal.add(this.buffer[j]);
            this.buffer = (int[]) ArrayUtils.remove(this.buffer, j);
            n--;
        }

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
        double[] retVal = new double[this.upperBound];
        int nonwordsCounter;
        for (int i = 0; i < this.upperBound; i++) {
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
