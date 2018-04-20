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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;

/**
 *
 * @author olhshk
 */
public class Vocabulary {

    private final int numberOfBands;
    private final int wordsPerBandInSeries;
    private LinkedHashMap<String, AdVocAsStimulus> hashedStimuli;

    public Vocabulary(int numberOfBands, int wordsPerBandInSeries) {
        this.numberOfBands = numberOfBands;
        this.wordsPerBandInSeries = wordsPerBandInSeries;
        this.hashedStimuli = new LinkedHashMap<String, AdVocAsStimulus>();
    }

    // the sequence of words in each band should be randomly reshuffled any time we generate it
    // side effect: also adss stimuli to the hash map
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
                ArrayList<Integer> index = RandomIndexing.generateRandomArray(wrds[bandIndex].length); // permutations without repetitions

                retVal.add(new ArrayList<AdVocAsStimulus>(this.wordsPerBandInSeries));
                for (int i = 0; i < wrds[bandIndex].length; i++) {
                    int ind = index.get(i);
                    retVal.get(bandIndex).add(wrds[bandIndex][ind]);
                    this.hashedStimuli.put(wrds[bandIndex][ind].getUniqueId(), wrds[bandIndex][ind]);
                }
            }
        }
        return retVal;
    }

    // the sequence of nonwords should be randomly reshuffled any time we generate it
    // side effect: also adss stimuli to the hash map
    public ArrayList<AdVocAsStimulus> initialiseNonwords(AdVocAsStimulus[] nonwrds) {
        if (nonwrds == null || nonwrds.length==0) {
            System.out.println("Empty array of nonwords");
            return new ArrayList<>();
        }

        ArrayList<AdVocAsStimulus> retVal = new ArrayList<>(nonwrds.length);
        ArrayList<Integer> index = RandomIndexing.generateRandomArray(nonwrds.length);

        for (int i = 0; i < index.size(); i++) {
            int ind = index.get(i);
            retVal.add(nonwrds[ind]);
            this.hashedStimuli.put(nonwrds[ind].getUniqueId(), nonwrds[ind]);
        }
        return retVal;
    }

    public LinkedHashMap<String, AdVocAsStimulus> getHashedStimuli() {
        return this.hashedStimuli;
    }
    
    public LinkedHashMap<String, AdVocAsStimulus> hashStimuli(AdVocAsStimulus[][] wrds, AdVocAsStimulus[] nonwrds) {
        LinkedHashMap<String, AdVocAsStimulus> retVal = new LinkedHashMap<String, AdVocAsStimulus>();
        for (int i = 0; i < nonwrds.length; i++) {
            AdVocAsStimulus st = nonwrds[i];
            retVal.put(st.getUniqueId(), st);
        }
        for (int i = 0; i < wrds.length; i++) {
            for (int j = 0; j < wrds[i].length; j++) {
                AdVocAsStimulus st = wrds[i][j];
                retVal.put(st.getUniqueId(), st);
            }
        }
        return retVal;
    }

}
