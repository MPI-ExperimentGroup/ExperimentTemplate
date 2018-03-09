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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic;

import java.util.ArrayList;

/**
 *
 * @author olhshk
 */
public class UtilsJSONdialect<S> {

    public static String getKey(String jsonString, String key) throws Exception {
        if (jsonString == null) {
            return null;
        }
        if (key == null) {
            return jsonString;
        }
        String[] parts = jsonString.split(key + ":");
        if (parts.length < 2) {
            return null;
        }
        String buffer = parts[1].trim();
        StringBuilder retVal = new StringBuilder();
        char current = buffer.charAt(0);
        if (current != '{') {
            throw new Exception("Get key from string parsing error, no { at the beginning of the object");
        }
        int openBrackets = 1;
        retVal.append("{");
        for (int i = 1; i < buffer.length(); i++) {
            current = buffer.charAt(i);
            retVal.append(current);
            if (current == '}') {
                if (openBrackets == 1) {
                    return retVal.toString();
                }
                openBrackets--;
            } else {
                if (current == '{') {
                    openBrackets++;
                }
            }

        }
        throw new Exception("Get key from string parsing error, no matching } ");
    }
    
    public static String removeFirstAndLast(String str){
       if (str == null) {
           return null;
       } 
       if (str.length()<2) {
           return "";
       }
       String retVal =  str.substring(1, str.length()-1);
       return retVal;
    }
    
    
     public static String getKeyWithoutBrackets(String jsonString, String key) throws Exception {
         String buffer = getKey(jsonString, key);
         String retVal= removeFirstAndLast(buffer);
         return retVal;
     }
    

    public String arrayListToString(ArrayList<S> list) throws Exception {
        if (list == null) {
            return null;
        }
        StringBuilder retVal = new StringBuilder();
        retVal.append("{");
        for (int i = 0; i < list.size() - 1; i++) {
            S obj = list.get(i);
            retVal.append(i).append(":");
            String objString = obj.toString();
            if (objString.startsWith("{")) {
                retVal.append(objString);
            } else {
                retVal.append("{").append(objString).append("}");
            }
            retVal.append(",");
        }
        int lastIndex = list.size() - 1;
        S obj = list.get(lastIndex);
        retVal.append(lastIndex).append(":");
        String objString = obj.toString();
        if (objString.startsWith("{")) {
            retVal.append(objString);
        } else {
            retVal.append("{").append(objString).append("}");
        }
        retVal.append("}");
        return retVal.toString();
    }

    public ArrayList<String> stringToArrayList(String listStr) throws Exception {
        if (listStr == null) {
            return null;
        }
        ArrayList<String> retVal = new ArrayList<String>();
        String current = getKey(listStr, "0");
        if (current == null) {
            return null;
        }
        int i = 0;
        while (current != null) {
            retVal.add(i, current);
            i++;
            String index = String.valueOf(i);
            current = getKey(listStr, index);
        }
        return retVal;
    }

}
