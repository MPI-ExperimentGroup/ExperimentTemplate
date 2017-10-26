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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment;

/**
 *
 * @author olhshk
 */
public class AtomStimulus {

    private final String spelling;
    private final int bandNumber;
    private boolean isUsed;

    private Boolean userReaction;
    private Boolean correctness;

    public AtomStimulus(String spelling, int bandNumber) {
        this.spelling = spelling;
        this.bandNumber = bandNumber;
        this.isUsed = false;
        this.userReaction = null;
        this.correctness = null;
    }

    public String getSpelling() {
        return this.spelling;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public int getBandNumber() {
        return this.bandNumber;
    }

    public void setIsUsed(boolean value) {
        this.isUsed = value;
    }
    public Boolean getReaction(){
        return this.userReaction;
    }
    
    public Boolean getCorrectness(){
        return this.correctness;
    }
    
    public void setReaction(boolean  reaction){
        this.userReaction = reaction;
    }
    
    public void setCorrectness(boolean eval){
        this.correctness=eval;
    }


}
