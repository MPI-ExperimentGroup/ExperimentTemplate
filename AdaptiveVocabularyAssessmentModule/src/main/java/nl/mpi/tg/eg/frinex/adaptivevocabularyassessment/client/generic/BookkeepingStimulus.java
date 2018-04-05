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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    @Override
    public String toString() {
        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("stimulus", this.stimulus);
        map.put("userReaction", this.userReaction);
        map.put("correctness", this.correctness);
        map.put("timeStamp", this.timeStamp);
        try {
            String retVal = UtilsJSONdialectMap.paramToString(map);
            return retVal;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
//        StringBuilder builder = new StringBuilder();
//        builder.append("{");
//        builder.append("stimulus:{").append(this.stimulus.toString()).append("},");
//        builder.append("userReaction:{").append(this.userReaction).append("},");
//        builder.append("correctness:{").append(this.correctness).append("},");
//        builder.append("timeStamp:{").append(this.timeStamp).append("}}");
//        return builder.toString();
    }

    public BookkeepingStimulus<A> toObject(String serialisation, HashMap<String, A> hashMap) throws Exception {
        Object object = UtilsJSONdialectMap.stringToObject(serialisation);
        if (!(object instanceof Map<?, ?>)) {
            throw new Exception("Failed to deserialise input string into the intermediate map of type object to object");
        }
        Map<Object, Object> map = (Map<Object, Object>) object;
        Set<Object> keys = map.keySet();
        String id = null;
        String corr = null;
        String reaction = null;
        String time = null;
        for (Object key : keys) {
            String keyStr = key.toString();
            switch (keyStr) {
                case "stimulus":
                    id = map.get(key).toString();
                    break;
                case "correctness":
                    corr = map.get(key).toString();
                    break;
                case "userReaction":
                    reaction = map.get(key).toString();
                    break;
                case "timeStamp":
                    time = map.get(key).toString();
                    break;
                default:
                    break;

            }
        }
        A localStimulus = hashMap.get(id);
        BookkeepingStimulus<A> retVal = new BookkeepingStimulus<A>(localStimulus);
        if (corr != null) {
            if (corr.equals("true")) {
                retVal.setCorrectness(true);
            } else {
                if (corr.equals("false")) {
                    retVal.setCorrectness(false);
                } else {
                    return null;
                }
            }
        }
        retVal.setReaction(reaction);
        retVal.setTimeStamp(Long.parseLong(time));
        return retVal;
    }
//        try {
//            String id = UtilsJSONdialect.getKeyWithoutBrackets(serialisation, "stimulus");
//            A localStimulus = map.get(id);
//            BookkeepingStimulus<A> retVal = new BookkeepingStimulus<A>(localStimulus);
//            String corr = UtilsJSONdialect.getKeyWithoutBrackets(serialisation, "correctness");
//            if (corr != null) {
//                if (corr.equals("true")) {
//                    retVal.setCorrectness(true);
//                } else {
//                    if (corr.equals("false")) {
//                        retVal.setCorrectness(false);
//                    } else {
//                        return null;
//                    }
//                }
//            }
//            String ur = UtilsJSONdialect.getKeyWithoutBrackets(serialisation, "userReaction");
//            if (ur != null) {
//                retVal.setReaction(ur);
//            }
//            String ts = UtilsJSONdialect.getKeyWithoutBrackets(serialisation, "timeStamp");
//            retVal.setTimeStamp(Long.parseLong(ts));
//            return retVal;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
