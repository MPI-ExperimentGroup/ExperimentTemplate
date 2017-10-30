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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.AtomStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;

/**
 *
 * @author olhshk
 */
public class FineTuningStimulus {

    private final AtomStimulus[] atomStimulae;
    private Boolean overallCorrectness;
    private long visitingTime;
    private int bandNumber;

    public FineTuningStimulus(AtomStimulus[] arrStimulae) throws Exception {
        this.atomStimulae = arrStimulae;// will be changed "in place", setting isUsed, userReaction, and evaluation
        this.visitingTime = -1;
        this.overallCorrectness = null;
        int nonwordCounter = 0;
        int currentBandNumber = -2;
        // correctenss checks, move to unit tests?? 
        for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            if (arrStimulae[i].getBandNumber() > 0) {
                if (currentBandNumber > -2) {
                    if (currentBandNumber != arrStimulae[i].getBandNumber()) {
                        throw new Exception("Ill-formed stimulus in fine tuning, there are words from different bands, " + arrStimulae[i].getBandNumber() + " and " + currentBandNumber);
                    }
                } else {
                    currentBandNumber = arrStimulae[i].getBandNumber();
                }
            } else {
                nonwordCounter++;
                if (nonwordCounter > 1) {
                    throw new Exception("Ill-formed stimulus in fine tuning, there is more than 1 nonword. ");
                }
            }
        }
        if (nonwordCounter > 0) {
            this.bandNumber = currentBandNumber;
        } else {
          throw new Exception("Ill-formed stimulus in fine tuning, there are no nonwords. "); 
        }
    }

    public AtomStimulus getAtomStimulusAt(int i) {
        return (this.atomStimulae)[i];
    }
    
    public Boolean getOverallCorrectness(){
        return this.overallCorrectness; 
    }
    
    public int getBandNumber(){
        return this.bandNumber; 
    }
    
    public long getVisitingTime(){
        return this.visitingTime; 
    }

    public void setUsedAt(int i) {
        (this.atomStimulae)[i].setIsUsed(true);
    }

    public void setVisitTime(long currentMillis) throws Exception {
        if (this.visitingTime > 0) {
            throw new Exception("this stimulae is vsisted more than 1 time. Setting of the test sequence has a flaw!!");
        }
        this.visitingTime = currentMillis;
    }

    public void setOverallCorrectness(boolean eval) {
        this.overallCorrectness = eval;
    }

}
