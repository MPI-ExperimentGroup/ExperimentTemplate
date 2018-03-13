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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class AudioAsStimulus extends BookkeepingStimulus<Boolean, String> {

    private final WordType wordtype;
    public static final String AUDIO_RATING_LABEL = "&#160;";
    public static final String EXAMPLE_TARGET_LABEL = null;
    public static final int PAUSE_EXAMPLE = 60000;
    public static final int PAUSE = 900;

    /*
    public BookkeepingStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, S bandLabel) {
     */
    public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel) {
        super(uniqueId, new Stimulus.Tag[0], label, null, pauseMs, audioPath, null, null, ratingLabel, correctResponses, bandLabel, bandIndex);
        this.wordtype = wordtype;
        this.userReaction = null;
        this.correctness = null;
    }

    public WordType getWordType() {
        return this.wordtype;
    }

    @Override
    public boolean hasCorrectResponses() {
        return true;
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

    public static AudioAsStimulus toObject(String str) {
        try {

            // inerited fields
            String label = UtilsJSONdialect.getKeyWithoutBrackets(str, "label");
            String audioPath = UtilsJSONdialect.getKeyWithoutBrackets(str, "audioPath");
            String videoPath = UtilsJSONdialect.getKeyWithoutBrackets(str, "videoPath");
            String code = UtilsJSONdialect.getKeyWithoutBrackets(str, "code");
            String correctResponses = UtilsJSONdialect.getKeyWithoutBrackets(str, "correctResponses");
            String pauseMs = UtilsJSONdialect.getKeyWithoutBrackets(str, "pauseMs");
            String ratingLabels = UtilsJSONdialect.getKeyWithoutBrackets(str, "ratingLabels");
            String uniqueId = UtilsJSONdialect.getKeyWithoutBrackets(str, "uniqueId");
            
            // specific fields
            String bandLabel = UtilsJSONdialect.getKeyWithoutBrackets(str, "bandLabel");
            String bandIndex = UtilsJSONdialect.getKeyWithoutBrackets(str, "bandIndex");
            String timeStamp = UtilsJSONdialect.getKeyWithoutBrackets(str, "timeStamp");
            String wordType = UtilsJSONdialect.getKeyWithoutBrackets(str, "wordType");

          
            // (String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel)
            AudioAsStimulus retVal = new AudioAsStimulus(uniqueId, label, Integer.parseInt(pauseMs), audioPath, correctResponses, bandLabel, Integer.parseInt(bandIndex), WordType.stringToWordType(wordType), ratingLabels);
            retVal.setTimeStamp(Long.parseLong(timeStamp));
            return retVal;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(super.toString());
        builder.append(",");
        builder.append("wordType:{").append(this.wordtype).append("}");
        builder.append("}");
        return builder.toString();
    }

}
