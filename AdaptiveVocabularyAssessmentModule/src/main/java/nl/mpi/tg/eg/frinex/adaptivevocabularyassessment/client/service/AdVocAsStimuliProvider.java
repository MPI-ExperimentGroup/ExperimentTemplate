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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.FastTrack;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends AbstractStimuliProvider {
    
    private ArrayList<AdVocAsAtomStimulus> stimuliList = new ArrayList();
    private boolean isCurrentCorrect = true;
    private int stimuliIndex = 0;
    final String WORD_FILE_LOCATION = "2.selection_words_nonwords_w.csv";
    final String NONWORD_FILE_LOCATION = "2.selection_words_nonwords.csv";
    
    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {
        stimuliList.clear();
        
        AdVocAsAtomStimulus[][] words = this.makeWords();
        ArrayList<AdVocAsAtomStimulus> nonwords = this.makeNonwords();
        FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
        fastTrack.createStimulae();
        stimuliList = fastTrack.getPureStimuli();
        System.out.println(stimuliList.size());
    }
    
    private AdVocAsAtomStimulus[][] makeWords() {
        if (Constants.WORDS == null || Constants.WORDS.length == 0) {
            AdVocAsAtomStimulus[][] retVal = new AdVocAsAtomStimulus[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
            for (int bandIndex = 0; bandIndex < Constants.NUMBER_OF_BANDS; bandIndex++) {
                //String uniqueId, String label, String correctResponses, int bandNumber
                for (int wordCounter = 0; wordCounter < Constants.WORDS_PER_BAND; wordCounter++) {
                    String testId = "rhabarber_" + bandIndex + "_" + wordCounter;
                    AdVocAsAtomStimulus stimulus = new AdVocAsAtomStimulus(testId, testId, "word", bandIndex + 1);
                    retVal[bandIndex][wordCounter] = stimulus;
                    
                }
            }
            return retVal;
        } else {
            return Constants.WORDS;
        }
    }
    
    private ArrayList<AdVocAsAtomStimulus> makeNonwords() {
        if (Constants.NONWORDS == null || Constants.NONWORDS.isEmpty()) {
            ArrayList<AdVocAsAtomStimulus> retVal = new ArrayList<>(100);
            for (int wordCounter = 0; wordCounter < 100; wordCounter++) {
                String testId = "rabbish_" + wordCounter;
                AdVocAsAtomStimulus stimulus = new AdVocAsAtomStimulus(testId, testId, "nonword", -1);
                retVal.add(stimulus);
            }
            return retVal;
        } else {
            return Constants.NONWORDS;
        }
    }
    
    @Override
    public Stimulus getCurrentStimulus() {
        return stimuliList.get(stimuliIndex);
    }
    
    @Override
    public int getCurrentStimulusIndex() {
        return stimuliIndex;
    }
    
    @Override
    public int getTotalStimuli() {
        return stimuliList.size();
    }
    
    @Override
    public boolean hasNextStimulus(int increment) {
        if (this.isCurrentCorrect) {
            return stimuliIndex + increment < stimuliList.size() && stimuliIndex + increment >= 0;
        } else {
            return false;
        }
    }
    
    @Override
    public void nextStimulus(int increment) {
        stimuliIndex += increment;
        stimuliIndex = (stimuliIndex >= 0) ? stimuliIndex : 0;
    }
    
    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
        this.stimuliIndex = currentStimuliIndex;
    }
    
    @Override
    public String getCurrentStimulusUniqueId() {
        return getCurrentStimulus().getUniqueId();
    }
    
    @Override
    public String generateStimuliStateSnapshot() {
        return ""; //todo: serialise your current stimuli list here
    }
    
    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        this.isCurrentCorrect = super.isCorrectResponse(stimulus, stimulusResponse); // @todo: incorporate your response validation here
        return this.isCurrentCorrect;
    }
}
