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

import java.util.ArrayList;

/**
 *
 * @author olhshk
 */
public abstract class Series {
    
    protected AtomBookkeepingStimulus[][] words;
    protected ArrayList<AtomBookkeepingStimulus> nonwords;
    protected String userName;
  
    public Series(String username, AtomBookkeepingStimulus[][] wrds, ArrayList<AtomBookkeepingStimulus> nonwrds){
        this.words = wrds;
        this.nonwords=nonwrds;
        this.userName = username;
    }

    protected ArrayList<AtomBookkeepingStimulus> fetchUnusedAtoms(AtomBookkeepingStimulus[] units) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>();
        for (int i = 0; i < units.length; i++) {
            if (!units[i].getIsUsed()) {
                retVal.add(units[i]);
            }
        }
        return retVal;
    }

    protected ArrayList<AtomBookkeepingStimulus> fetchUnusedAtomBookkeepingStimulus(ArrayList<AtomBookkeepingStimulus> units) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            if (!units.get(i).getIsUsed()) {
                retVal.add(units.get(i));
            }
        }
        return retVal;
    }
    
    public void createStimulae() throws Exception{
    }
    
   
}
