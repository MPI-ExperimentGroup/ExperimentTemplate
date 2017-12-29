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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client;

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;

/**
 *
 * @author olhshk
 */
public class Vocabulary {
    
    private final int numberOfBands;
    private final int wordsPerBandInSeries;
    
    public Vocabulary(int numberOfBands, int wordsPerBandInSeries){
        this.numberOfBands = numberOfBands;
        this.wordsPerBandInSeries  = wordsPerBandInSeries;
    }
    
     // the sequence of words in each band should be randomly reshuffled any time we generate it
    public ArrayList<ArrayList<AdVocAsStimulus>> initialiseWords(AdVocAsStimulus[][] wrds) {
        if (wrds == null || wrds.length == 0) {
            System.out.println("Empty array of words in bands");
            return new ArrayList<>();
        }
        ArrayList<ArrayList<AdVocAsStimulus>> retVal = new ArrayList<>(this.numberOfBands);
        for (int bandIndex = 0; bandIndex < wrds.length; bandIndex++) {
            if (wrds[bandIndex] == null && wrds[bandIndex].length == 0) {
                System.out.println("Empty array of words for band " + bandIndex + "is empty.");
                retVal.add(new ArrayList<AdVocAsStimulus>());
            } else {
                int[] index =RandomIndexing.generateRandomArray(wrds[bandIndex].length);
                
                retVal.add(new ArrayList<AdVocAsStimulus>(this.wordsPerBandInSeries));
                for (int i = 0;i < wrds[bandIndex].length; i ++) {
                    int ind = index[i];
                    retVal.get(bandIndex).add(wrds[bandIndex][ind]);
                }
            }
        }
        return retVal;
    }
    
    

    // the sequence of nonwords should be randomly reshuffled any time we generate it
    public ArrayList<AdVocAsStimulus> initialiseNonwords(ArrayList<AdVocAsStimulus> nonwrds) {
        if (nonwrds == null || nonwrds.isEmpty()) {
            System.out.println("Empty array of nonwords");
            return new ArrayList<>();
        }
        
        ArrayList<AdVocAsStimulus> retVal = new ArrayList<>(nonwrds.size());
        int[] index = RandomIndexing.generateRandomArray(nonwrds.size());
                
        for (int i=0; i<index.length; i++) {
            int ind=index[i];
            retVal.add(nonwrds.get(ind));
        }
        return retVal;
    }

    
    
}
