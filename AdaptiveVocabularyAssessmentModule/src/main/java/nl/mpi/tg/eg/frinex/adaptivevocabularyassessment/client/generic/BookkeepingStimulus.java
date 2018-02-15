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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic;

/**
 *
 * @author olhshk
 */
/**
 * Generic BookkeepingStimulus class.
 *
 * @param <T> userRecation, can be string, boolean, double, etc.
 */
public abstract class BookkeepingStimulus<T,S> extends BandStimulus<S> {

    protected T userReaction; // can be string, boolean, double, etc.
    protected Boolean correctness;

    /*
    public BandStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, String bandLabel){
     */
    public BookkeepingStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses, S bandLabel, int bandIndex) {
        super(uniqueId, tags, label, code, pauseMs, audioPath, videoPath, imagePath, ratingLabels, correctResponses, bandLabel, bandIndex);
        this.userReaction = null;
        this.correctness = null;
    }

    // injection
    public BookkeepingStimulus(BandStimulus<S> stimulus) {
       super(stimulus.getUniqueId(), stimulus.getTags().toArray(new Tag[0]), stimulus.getLabel(), 
                stimulus.getCode(), stimulus.getPauseMs(), stimulus.getAudio(), stimulus.getVideo(), stimulus.getImage(), 
                stimulus.getRatingLabels(), stimulus.getCorrectResponses(), stimulus.getBandLabel(),  stimulus.getBandIndex());
        this.userReaction = null;
        this.correctness = null;
    }

    public T getReaction() {
        return this.userReaction;
    }

    public Boolean getCorrectness() {
        return this.correctness;
    }

    // experiment specific
    public void setReaction(String reaction) {
        this.userReaction = (T) reaction;
    }

    public void setCorrectness(boolean eval) {
        this.correctness = eval;
    }

}
