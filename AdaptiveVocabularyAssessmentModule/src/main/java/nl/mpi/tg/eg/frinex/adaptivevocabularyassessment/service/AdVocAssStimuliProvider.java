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

import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.model.AdVocAsAtomStimulus.Tag;
import static nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.model.AdVocAsAtomStimulus.Tag.tag_set1;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAssStimuliProvider extends AbstractStimuliProvider {

    final private List<Stimulus> stimuliList = new ArrayList();
    private int stimuliIndex = 0;

    @Override
    public void getAll() {
        stimuliList.clear();
        //
        //AdVocAssStimulus(String uniqueId, Tag[] tags, String label, String ratingLabels, String correctResponses)
        // tag referred from the  corresponding presenter part of the xml
        // label = a word, a content of a stimulus
        // rating labels == correct/incorrect ?

        stimuliList.add(new AdVocAsAtomStimulus("AdVocAssStimulus_1", new Tag[]{tag_set1}, "rhabarber", "word,nonword", "word"));
        stimuliList.add(new AdVocAsAtomStimulus("AdVocAssStimulus_2", new Tag[]{tag_set1}, "rabarba", "word,nonword", "nonword"));
        stimuliList.add(new AdVocAsAtomStimulus("AdVocAssStimulus_3", new Tag[]{tag_set1}, "lion", "word,nonword", "word"));
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
        return stimuliIndex + increment < stimuliList.size() && stimuliIndex + increment >= 0;
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
        return super.isCorrectResponse(stimulus, stimulusResponse); // @todo: incorporate your response validation here
    }
}
