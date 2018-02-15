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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;

/**
 *
 * @author olhshk
 */
public class AdVocAsBookkeepingStimulus extends BookkeepingStimulus<Boolean,Integer> {

    /*
    public BookkeepingStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, String bandNumber) {
    */
    
    public AdVocAsBookkeepingStimulus(String uniqueId, Tag tags[], String label, String ratingLabels, String correctResponses, Integer bandNumber) {
        super(uniqueId, tags, label, null, -1, null, null, null, ratingLabels, correctResponses, bandNumber, bandNumber-1);
    }

    // injection
    public AdVocAsBookkeepingStimulus(AdVocAsStimulus stimulus) {
        super(stimulus);
    }

    @Override
    public void setReaction(String reaction) {
        String stimulusResponseProcessed = new String(reaction);
        stimulusResponseProcessed = stimulusResponseProcessed.replaceAll(",", "&#44;");
        if (!(stimulusResponseProcessed.equals(Vocabulary.WORD) || (stimulusResponseProcessed.equals(Vocabulary.NONWORD)))) {
            System.out.println("Erroenous input: neither word nor nonword; something went terrible wrong.");
        }
        if (stimulusResponseProcessed.equals(Vocabulary.WORD)) {
            this.userReaction = true;
        }
        if (stimulusResponseProcessed.equals(Vocabulary.NONWORD)) {
            this.userReaction = false;
        }
    }

}
