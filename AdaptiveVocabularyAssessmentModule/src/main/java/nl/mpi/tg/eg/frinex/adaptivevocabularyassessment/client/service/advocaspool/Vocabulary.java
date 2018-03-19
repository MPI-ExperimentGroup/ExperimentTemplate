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
import java.util.HashMap;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;

/**
 *
 * @author olhshk
 */
public class Vocabulary {

    public static final String NONWORD = "NEE&#44; ik ken dit woord niet";

    public static final String WORD = "JA&#44; ik ken dit woord";

    private final int numberOfBands;
    private final int wordsPerBandInSeries;

    public Vocabulary(int numberOfBands, int wordsPerBandInSeries) {
        this.numberOfBands = numberOfBands;
        this.wordsPerBandInSeries = wordsPerBandInSeries;
    }

    private WordsAndNonWords initialiseWordsAndNonWordsNonRandom(HashMap<String, AdVocAsStimulus> stimuli) {
        if (stimuli == null || stimuli.isEmpty()) {
            System.out.println("Empty array of words in bands");
            return null;
        }
        ArrayList<ArrayList<AdVocAsStimulus>> wrd = new ArrayList<>(this.numberOfBands);
        ArrayList<AdVocAsStimulus> nonwrd = new ArrayList<>();
        for (int bandIndex = 0; bandIndex < this.numberOfBands; bandIndex++) {
            wrd.add(new ArrayList<AdVocAsStimulus>(this.wordsPerBandInSeries));
        }

        Set<String> identifiers = stimuli.keySet();
        for (String id : identifiers) {
            AdVocAsStimulus stimulus = stimuli.get(id);
            String correctResponse = stimulus.getCorrectResponses();
            if (correctResponse.equals(NONWORD)) {
                nonwrd.add(stimulus);
            } else {
                if (correctResponse.equals(WORD)) {
                    int index = stimulus.getBandIndex();
                    ArrayList<AdVocAsStimulus> bandStimuli = wrd.get(index);
                    bandStimuli.add(stimulus);
                }
            }

        }
        
         WordsAndNonWords retVal = new WordsAndNonWords();
        retVal.setWordsInBands(wrd);
        retVal.setNonwords(nonwrd);
        return retVal;

    }

    private ArrayList<AdVocAsStimulus> randomiseStimuliList(ArrayList<AdVocAsStimulus> stimuli) {
        ArrayList<AdVocAsStimulus> retVal = new ArrayList<>(stimuli.size());
        int[] index = RandomIndexing.generateRandomArray(stimuli.size());
        for (int i = 0; i < stimuli.size(); i++) {
            int ind = index[i];
            retVal.add(stimuli.get(ind));
        }
        return retVal;
    }

    public WordsAndNonWords initialiseWordsAndNonWords(HashMap<String, AdVocAsStimulus> stimuli) {

        ArrayList<ArrayList<AdVocAsStimulus>> wrdRandom = new ArrayList<ArrayList<AdVocAsStimulus>>(this.numberOfBands);
        WordsAndNonWords tmp = this.initialiseWordsAndNonWordsNonRandom(stimuli);

        for (int i = 0; i < tmp.getWordsInBands().size(); i++) {
            ArrayList<AdVocAsStimulus> stimuliInBand = tmp.getWordsInBands().get(i);
            ArrayList<AdVocAsStimulus> stimuliInBandRandom = this.randomiseStimuliList(stimuliInBand);
            wrdRandom.add(stimuliInBandRandom);
        }

        ArrayList<AdVocAsStimulus> nonWrdsRandom = this.randomiseStimuliList(tmp.getNonwords());

        WordsAndNonWords retVal = new WordsAndNonWords();
        retVal.setWordsInBands(wrdRandom);
        retVal.setNonwords(nonWrdsRandom);
        return retVal;
    }

}
