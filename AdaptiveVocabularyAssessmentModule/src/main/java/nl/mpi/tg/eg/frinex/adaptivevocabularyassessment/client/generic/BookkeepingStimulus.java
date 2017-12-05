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
 * @param <T> userRecation, can be string, boolean, double, etc.
 */
public abstract class BookkeepingStimulus<T> extends BandStimulus{

   
    protected T userReaction; // can be string, boolean, double, etc.
    private Boolean correctness;

    public BookkeepingStimulus(String uniqueId, Tag tags[], String label, String ratingLabels, String correctResponses, int bandNumber) {
        //BandStimulus(String uniqueId, Tag tags[], String label, String ratingLabels, String correctResponses, int bandNumber)
        super(uniqueId, tags, label, ratingLabels, correctResponses, bandNumber);
        this.userReaction = null;
        this.correctness = null;
    }
    
    // injection
    public BookkeepingStimulus(BandStimulus stimulus) {
        //BandStimulus(String uniqueId, Tag tags[], String label, String ratingLabels, String correctResponses, int bandNumber)
        super(stimulus.getUniqueId(), stimulus.getTags().toArray(new Tag[0]), stimulus.getLabel(), stimulus.getRatingLabels(), stimulus.getCorrectResponses(), stimulus.getBandNumber());
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
