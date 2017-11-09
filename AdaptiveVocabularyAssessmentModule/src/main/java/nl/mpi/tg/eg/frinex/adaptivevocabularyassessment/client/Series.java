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
public abstract class Series {

    protected AtomBookkeepingStimulus[][] words;
    protected ArrayList<AtomBookkeepingStimulus> nonwords;
    protected String userName;

    public Series(String username, AdVocAsAtomStimulus[][] wrds, ArrayList<AdVocAsAtomStimulus> nonwrds) {
        this.words = this.initialiseWords(wrds);
        this.nonwords = this.initialiseNonwords(nonwrds);
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

    protected ArrayList<AtomBookkeepingStimulus> fetchUnusedAtomBookkeepingStimulus(ArrayList<AtomBookkeepingStimulus> stimuli) {
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>();
        for (int i = 0; i < stimuli.size(); i++) {
            if (!stimuli.get(i).getIsUsed()) {
                retVal.add(stimuli.get(i));
            }
        }
        return retVal;
    }

    private AtomBookkeepingStimulus[][] initialiseWords(AdVocAsAtomStimulus[][] wrds) {
        if (wrds == null || wrds.length == 0) {
            System.out.println("Empty array of words in bands");
            return new AtomBookkeepingStimulus[0][0];
        }
        AtomBookkeepingStimulus[][] retVal = new AtomBookkeepingStimulus[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
        for (int bandIndex = 0; bandIndex < wrds.length; bandIndex++) {
            if (wrds[bandIndex] == null && wrds[bandIndex].length == 0) {
                System.out.println("Empty array of words for band "+bandIndex);
                 retVal[bandIndex] = new AtomBookkeepingStimulus[0];
            } else {
                for (int wordCounter = 0; wordCounter < wrds[bandIndex].length; wordCounter++) {
                    AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(wrds[bandIndex][wordCounter]);
                    retVal[bandIndex][wordCounter] = stimulus;
                }
            }
        }
        return retVal;
    }

    private ArrayList<AtomBookkeepingStimulus> initialiseNonwords(ArrayList<AdVocAsAtomStimulus> nonwrds) {
        if (nonwrds == null || nonwrds.isEmpty()) {
            System.out.println("Empty array of nonwords");
            return new ArrayList<>();
        }
        ArrayList<AtomBookkeepingStimulus> retVal = new ArrayList<>(nonwrds.size());
        for (AdVocAsAtomStimulus nonword : nonwrds) {
            AtomBookkeepingStimulus stimulus = new AtomBookkeepingStimulus(nonword);
            retVal.add(stimulus);
        }
        return retVal;
    }

    public void createStimulae() throws Exception {
    }

}
