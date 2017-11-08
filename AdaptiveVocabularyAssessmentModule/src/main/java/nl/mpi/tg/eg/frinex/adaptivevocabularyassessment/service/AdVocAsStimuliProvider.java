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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.service;

import java.io.IOException;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Utils;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.FastTrack;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends AbstractStimuliProvider {

    private final String WORD_FILE_LOCATION = "../../Data/2.selection_words_nonwords_w.csv";

    private final String NONWORD_FILE_LOCATION = "../../Data/2.selection_words_nonwords.csv";

    private final String OUTPUT_DIRECTORY = "../../Data/";

    private ArrayList<AdVocAsAtomStimulus> stimuliList = new ArrayList();
    private boolean isCurrentCorrect = true;
    private int stimuliIndex = 0;

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {
        stimuliList.clear();
        Vocabulary vocab = new Vocabulary();
        try {
            vocab.initialiseVocabulary(WORD_FILE_LOCATION, NONWORD_FILE_LOCATION);
            AtomBookkeepingStimulus[][] words = vocab.getWords();
            ArrayList<AtomBookkeepingStimulus> nonwords = vocab.getNonwords();
            FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
            fastTrack.createStimulae();
            ArrayList<AtomBookkeepingStimulus> fastTrackSequence = fastTrack.getStimulae();
            stimuliList = Utils.getPureStimuli(fastTrackSequence);
        } catch (IOException ex) {
            stimuliList.add(new AdVocAsAtomStimulus("Failure", "inform the researcher that the test generation has failed", "word,nonword", 1));
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
