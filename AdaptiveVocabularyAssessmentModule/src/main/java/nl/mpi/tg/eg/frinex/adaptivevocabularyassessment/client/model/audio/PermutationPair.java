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

/**
 *
 * @author olhshk
 */
public class PermutationPair {

    public final ArrayList<TrialCondition> trialConditions;
    public final ArrayList<Integer> trialLengths;

    public PermutationPair(ArrayList<TrialCondition> trialConditions, ArrayList<Integer> trialLengths) {
        this.trialConditions = trialConditions;
        this.trialLengths = trialLengths;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        UtilsJSONdialect<TrialCondition> util1 = new UtilsJSONdialect<TrialCondition>();

        try {
            String trailTypesStr = util1.arrayListToString(this.trialConditions);
            if (trailTypesStr != null) {
                builder.append("trialConditions:").append(trailTypesStr).append(",");
            }
        } catch (Exception ex) {
        }

        UtilsJSONdialect<Integer> util2 = new UtilsJSONdialect<Integer>();
        try {
            String trialLengthsStr = util2.arrayListToString(this.trialLengths);
            if (trialLengthsStr != null) {
                builder.append("trialLengths:").append(trialLengthsStr);
            }
        } catch (Exception ex) {
        }

        builder.append("}");
        return builder.toString();
    }

    public static PermutationPair toObject(String str) {

        UtilsJSONdialect<Integer> util2 = new UtilsJSONdialect<Integer>();

        try {
            
            String triallTypesStr = UtilsJSONdialect.getKey(str, "trialConditions");
            ArrayList<String> trialConditionsArrayStr = UtilsJSONdialect.stringToArrayList(triallTypesStr);
            ArrayList<TrialCondition> trialConds = new ArrayList<TrialCondition>(trialConditionsArrayStr.size());
            for (int i=0; i<trialConditionsArrayStr.size(); i++) {
                trialConds.add(null);
            }
            for (int i=0; i<trialConditionsArrayStr.size(); i++) {
                String withBrackets = trialConditionsArrayStr.get(i).trim();
                TrialCondition tc = TrialCondition.valueOf(UtilsJSONdialect.removeFirstAndLast(withBrackets));
                trialConds.set(i, tc);
            }
            
            String lengthStr = UtilsJSONdialect.getKey(str, "trialLengths");
            ArrayList<Integer> lengths = util2.stringToArrayListInteger(lengthStr);
           
            PermutationPair retVal = new PermutationPair(trialConds, lengths);
            return retVal;
            
        } catch (Exception ex) {
            return null;
        }
  
    }

}
