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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;

/**
 *
 * @author olhshk
 */
public class TrialTuple {

    private final static String[] FLDS = {"trials", "correctness"};

    private final ArrayList<Trial> trials;
    private Boolean correctness;

    public TrialTuple(ArrayList<Trial> trials) {
        this.trials = trials;
        this.correctness = null;
    }

    public TrialTuple(ArrayList<Trial> trials, Boolean correctness) {
        this.trials = trials;
        this.correctness = correctness;
    }

    public Trial getFirstNonusedTrial() {
        int i = 0;
        while (this.trials.get(i).getStimuli().size() < 1) {
            i++;
        }
        return this.trials.get(i);
    }
    
//    public BookkeepingStimulus<AudioAsStimulus> removeFirstAvailableStimulus() {
//        int i= getFirstNonusedTrial();
//        BookkeepingStimulus<AudioAsStimulus> retVal = this.trials.get(i).getStimuli().remove(0);
//        return retVal;
//    }
    
    public ArrayList<Trial> getTrials() {
        return this.trials;
    }

    public int getNumberOfStimuli() {
        int retVal = 0;
        for (Trial trial : trials) {
            retVal += trial.getStimuli().size();
        }
        return retVal;
    }

    public Boolean getCorrectness() {
        return this.correctness;
    }

    public void setCorrectness(boolean correct) {
        this.correctness = correct;

    }

    public boolean isNotEmpty() {
        int i = 0;
        // try to find first non-empty trial
        while (i < this.trials.size()) {
            if (this.trials.get(i).getStimuli().size() > 0) {
                return true; // there are nonempty trials!
            } else {
                i++;
            }
        }
        return false; // all trials are fired!
    }

   

    @Override
    public String toString() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("fields", Arrays.asList(TrialTuple.FLDS));
        map.put("trials", this.trials);
        map.put("correctness", this.correctness);
        return map.toString();
    }

    public static TrialTuple mapToObject(Map<String, Object> map, LinkedHashMap<Integer, Trial> hashedTrials) {
        try {
            Object trialsObj = map.get("trials");
            if (trialsObj == null) {
                return null;
            } else {
               List<Object> ls = (List<Object>) trialsObj;
                ArrayList<Trial> trials = new ArrayList<Trial>(ls.size());
                for (int i = 0; i < ls.size(); i++) {
                    Integer id = Integer.parseInt(ls.get(i).toString());
                    Trial tr = hashedTrials.get(id);
                    trials.add(i, tr);
                }

                Object correctnessObj = map.get("correctness");
                Boolean correctness = null;
                if (correctnessObj != null) {
                    if (correctnessObj.toString().equals("true")) {
                        correctness = true;
                    } else {
                        if (correctnessObj.toString().equals("false")) {
                            correctness = false;
                        }
                    }
                }

                TrialTuple retVal = new TrialTuple(trials, correctness);
                return retVal;

            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }
}
