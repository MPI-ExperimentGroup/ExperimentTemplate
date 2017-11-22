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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client;

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;

/**
 *
 * @author olhshk
 */
public class AtomBookkeepingStimulus {

    private final AdVocAsAtomStimulus stimulus;

    private Boolean userReaction;
    private Boolean correctness;

    public AtomBookkeepingStimulus(AdVocAsAtomStimulus pureStimulus) {
        this.stimulus = pureStimulus;
        this.userReaction = null;
        this.correctness = null;
    }
   
   
    public AtomBookkeepingStimulus(AtomBookkeepingStimulus source) {
        this.stimulus = source.stimulus;
        this.userReaction = source.userReaction;
        this.correctness = source.correctness;
    }

    public AdVocAsAtomStimulus getPureStimulus(){
        return this.stimulus;
    }
    
    public String getSpelling() {
        return this.stimulus.getLabel();
    }

 

    public int getBandNumber() {
        return this.stimulus.getBandNumber();
    }

   
    public Boolean getReaction() {
        return this.userReaction;
    }

    public Boolean getCorrectness() {
        return this.correctness;
    }

    public void setReaction(boolean reaction) {
        this.userReaction = reaction;
    }

    public void setCorrectness(boolean eval) {
        this.correctness = eval;
    }

    public static ArrayList<AtomBookkeepingStimulus> copyAtomStimulae(ArrayList<AtomBookkeepingStimulus> source) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>(source.size());
        for (AtomBookkeepingStimulus stimulus : source) {
            AtomBookkeepingStimulus cloneStimulus = new AtomBookkeepingStimulus(stimulus);
            retVal.add(cloneStimulus);
        }
    return retVal;
    }

}
