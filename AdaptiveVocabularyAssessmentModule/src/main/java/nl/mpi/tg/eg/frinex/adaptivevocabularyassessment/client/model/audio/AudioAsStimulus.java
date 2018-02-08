/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class AudioAsStimulus extends BookkeepingStimulus<Boolean> {

    private final WordType wordtype;
    public static String AUDIO_RATING_LABEL = "YES";

    /*
    public BookkeepingStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, int bandNumber) {
     */
    public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype) {
        super(uniqueId, new Stimulus.Tag[0], label, null, pauseMs, audioPath, null, null, AUDIO_RATING_LABEL, correctResponses, bandNumber);
        this.wordtype = wordtype;
        this.userReaction = null;
        this.correctness = null;
    }

    public WordType getWordType() {
        return this.wordtype;
    }

    @Override
    public void setReaction(String reaction) {
        if (reaction == null) { // button is not pressed
            this.userReaction = false;
        } else {
            if (reaction.equals(AUDIO_RATING_LABEL)) {
                this.userReaction = true;
            } else {
                if (reaction.equals("")) {
                    this.userReaction = false;
                } else {
                    // something went terribly wrong 
                    this.userReaction = null;
                }
            }
        }
    }


}
