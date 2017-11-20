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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;

/**
 *
 * @author olhshk
 */
public class Vocabulary {
    
     // the sequence of words in each band should be randomly reshuffled any time we generate it
    public ArrayList<ArrayList<AtomBookkeepingStimulus>> initialiseWords(AdVocAsAtomStimulus[][] wrds) {
        if (wrds == null || wrds.length == 0) {
            System.out.println("Empty array of words in bands");
            return new ArrayList<>();
        }
        ArrayList<ArrayList<AtomBookkeepingStimulus>> retVal = new ArrayList<>(Constants.NUMBER_OF_BANDS);
        for (int bandIndex = 0; bandIndex < wrds.length; bandIndex++) {
            if (wrds[bandIndex] == null && wrds[bandIndex].length == 0) {
                System.out.println("Empty array of words for band " + bandIndex + "is empty.");
                retVal.add(new ArrayList<AtomBookkeepingStimulus>());
            } else {
                int[] index =RandomIndexing.generateRandomArray(wrds[bandIndex].length);
                
                retVal.add(new ArrayList<AtomBookkeepingStimulus>(Constants.WORDS_PER_BAND));
                for (int i = 0;i < wrds[bandIndex].length; i ++) {
                    int ind = index[i];
                    AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(wrds[bandIndex][ind]);
                    retVal.get(bandIndex).add(stimulus);
                }
            }
        }
        return retVal;
    }

    // the sequence of nonwords should be randomly reshuffled any time we generate it
    public ArrayList<AtomBookkeepingStimulus> initialiseNonwords(ArrayList<AdVocAsAtomStimulus> nonwrds) {
        if (nonwrds == null || nonwrds.isEmpty()) {
            System.out.println("Empty array of nonwords");
            return new ArrayList<>();
        }
        
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>(nonwrds.size());
        int[] index = RandomIndexing.generateRandomArray(nonwrds.size());
                
        for (int i=0; i<index.length; i++) {
            int ind=index[i];
            AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(nonwrds.get(ind));
            retVal.add(stimulus);
        }
        return retVal;
    }

    
    
}
