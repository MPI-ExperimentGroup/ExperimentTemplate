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
public class BookkeepingStimulus<A extends BandStimulus> {

    private final A stimulus;
    private String userReaction; // can be string, boolean, double, etc.
    private Boolean correctness;
    private long timeStamp; 
    

   
    public BookkeepingStimulus(A stimulus) {
        this.stimulus = stimulus;
        this.userReaction = null;
        this.correctness = null;
    }

    public A getStimulus() {
        return this.stimulus;
    }

    public String getReaction() {
        return this.userReaction;
    }
    
      public long getTimeStamp() {
        return this.timeStamp;
    }
   
    public Boolean getCorrectness() {
        return this.correctness;
    }

    public void setReaction(String reaction) {
        this.userReaction = reaction;
    }

    public void setCorrectness(boolean eval) {
        this.correctness = eval;
    }

      public void setTimeStamp(long timeStr) {
        this.timeStamp = timeStr;
    }
}
