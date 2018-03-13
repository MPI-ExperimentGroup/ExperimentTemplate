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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
import static nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect.removeFirstAndLast;

/**
 *
 * @author olhshk
 */
public enum TrialCondition {
    TARGET_ONLY, TARGET_AND_FOIL, NO_TARGET;

    public static TrialCondition stringToCondition(String conditionString) {
        TrialCondition retVal;
        switch (conditionString) {
            case "Target-only":
                retVal = TrialCondition.TARGET_ONLY;
                break;
            case "Target+Foil":
                retVal = TrialCondition.TARGET_AND_FOIL;
                break;
            case "NoTarget":
                retVal = TrialCondition.NO_TARGET;
                break;
            default:
                throw new IllegalArgumentException("No trial condition value for the string " + conditionString);
        }
        return retVal;
    }

    public static ArrayList<TrialCondition> toListObject(String str) {
        UtilsJSONdialect<TrialCondition> util = new UtilsJSONdialect<TrialCondition>();
        try {
            ArrayList<String> buffer = util.stringToArrayList(str);

            ArrayList<TrialCondition> retVal = new ArrayList<TrialCondition>(buffer.size());
            for (int i = 0; i < buffer.size(); i++) {
                String val = buffer.get(i);
                String tmp = removeFirstAndLast(val);
                retVal.add(i, TrialCondition.valueOf(tmp));
            }

            return retVal;
        } catch (Exception ex) {
            return null;
        }
    }
}
