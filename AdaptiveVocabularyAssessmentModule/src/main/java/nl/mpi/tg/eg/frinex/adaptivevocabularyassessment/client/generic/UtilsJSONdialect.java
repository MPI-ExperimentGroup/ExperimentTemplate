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

    public static String[] getKey(String jsonStringInit, String key) throws Exception {
        
        String[] retVal = new String[2];
        
        if (jsonStringInit == null) {
            retVal[0]=null;
            retVal[1]=null;
            return retVal;
        }
        if (key == null) {
            retVal[0]=jsonStringInit;
            retVal[1]="";
            return retVal;
        }

        String jsonString = (new StringBuilder()).append(jsonStringInit).toString();
        if (jsonStringInit.startsWith("{") && jsonStringInit.endsWith("}")) {
            jsonString = removeFirstAndLast(jsonStringInit);
        } 

        String regExp = "(^" + key + ":)|(," + key + ":)|( " + key + ":)";

        String[] parts = jsonString.split(regExp,2);
        if (parts.length < 2) {
            retVal[0]=null;
            retVal[1]=null;
            return retVal;
        }
        String buffer = parts[1].trim();
        StringBuilder val = new StringBuilder();
        char current = buffer.charAt(0);
        if (current != '{') {
            throw new Exception("Json dialect parsing error. Got key from string parsing error, no { at the beginning of the corresponding value");
        }
        int openBrackets = 1;
        val.append("{");
        for (int i = 1; i < buffer.length(); i++) {
            current = buffer.charAt(i);
            val.append(current);
            if (current == '}') {
                if (openBrackets == 1) {
                    retVal[0] = val.toString();
                    retVal[1] = buffer.substring(i+1);
                    return retVal;
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

    public static String removeFirstAndLast(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() < 2) {
            return "";
        }
        String retVal = str.substring(1, str.length() - 1);
        return retVal;
    }

    public static String getKeyWithoutBrackets(String jsonString, String key) throws Exception {
        String buffer = getKey(jsonString, key)[0];
        String retVal = removeFirstAndLast(buffer);
        return retVal;
        
    }

    public String arrayListToString(ArrayList<S> list) throws Exception {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "{}";
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

    public String arrayList2String(ArrayList<ArrayList<S>> list) throws Exception {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "{}";
        }
        UtilsJSONdialect<S> util = new UtilsJSONdialect<S>();
        ArrayList<String> intermediate = new ArrayList<String>();
        for (ArrayList<S> innerList : list) {
            String innerListStr = util.arrayListToString(innerList);
            intermediate.add(innerListStr);
        }
        UtilsJSONdialect<String> util2 = new UtilsJSONdialect<String>();
        String retVal = util2.arrayListToString(intermediate);
        return retVal;
    }

    public String arrayList3String(ArrayList<ArrayList<ArrayList<S>>> list) throws Exception {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "{}";
        }
        UtilsJSONdialect<S> util = new UtilsJSONdialect<S>();
        ArrayList<String> intermediate = new ArrayList<String>();
        for (ArrayList<ArrayList<S>> innerList : list) {
            String innerListStr = util.arrayList2String(innerList);
            intermediate.add(innerListStr);
        }
        UtilsJSONdialect<String> util2 = new UtilsJSONdialect<String>();
        String retVal = util2.arrayListToString(intermediate);
        return retVal;
    }

    public static String intArrayListToString(int[] arr) throws Exception {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return "{}";
        }
        StringBuilder retVal = new StringBuilder();
        retVal.append("{");
        for (int i = 0; i < arr.length - 1; i++) {
            retVal.append(i).append(":");
            retVal.append("{").append(arr[i]).append("}");
            retVal.append(",");
        }
        int lastIndex = arr.length - 1;
        retVal.append(lastIndex).append(":");
        retVal.append("{").append(arr[lastIndex]).append("}");
        retVal.append("}");
        return retVal.toString();
    }

    public static String doubleArrayListToString(double[] arr) throws Exception {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return "{}";
        }
        StringBuilder retVal = new StringBuilder();
        retVal.append("{");
        for (int i = 0; i < arr.length - 1; i++) {
            retVal.append(i).append(":");
            retVal.append("{").append(arr[i]).append("}");
            retVal.append(",");
        }
        int lastIndex = arr.length - 1;
        retVal.append(lastIndex).append(":");
        retVal.append("{").append(arr[lastIndex]).append("}");
        retVal.append("}");
        return retVal.toString();
    }

    public static ArrayList<String> stringToArrayList(String listStr) throws Exception {
        if (listStr == null) {
            return null;
        }

        if (listStr.trim().equals("null") || listStr.trim().equals("{null}")) {
            return null;
        }

        if (listStr.trim().isEmpty() || listStr.trim().equals("{}")) {
            return new ArrayList<String>();
        }

        ArrayList<String> retVal = new ArrayList<String>();
        String[] current = getKey(listStr, "0");
        int i = 0;
        String index = "0";
        while (current[0] != null) {
            retVal.add(i, current[0]);
            i++;
            index = String.valueOf(i);
            current = getKey(current[1], index);
        }
        return retVal;
    }

    public static ArrayList<ArrayList<String>> stringToArray2List(String listStr) throws Exception {
        ArrayList<String> outer = stringToArrayList(listStr);
        if (outer == null) {
            return null;
        }
        ArrayList<ArrayList<String>> retVal = new ArrayList<ArrayList<String>>(outer.size());
        for (String innerStr : outer) {
            ArrayList<String> inner = stringToArrayList(innerStr);
            retVal.add(inner);
        }
        return retVal;
    }

    public static ArrayList<Integer> stringToArrayListInteger(String listStr) throws Exception {

        ArrayList<String> buffer = stringToArrayList(listStr);
        if (buffer == null) {
            return null;
        }
        if (buffer.isEmpty()) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> retVal = new ArrayList<Integer>(buffer.size());
        for (int i = 0; i < buffer.size(); i++) {
            String val = buffer.get(i);
            String tmp = removeFirstAndLast(val);
            Integer valInt = Integer.parseInt(tmp);
            retVal.add(i, valInt);
        }
        return retVal;
    }

    public static double[] stringToArrayDouble(String listStr) throws Exception {
        ArrayList<String> buffer = stringToArrayList(listStr);
        if (buffer == null) {
            return null;
        }
        if (buffer.isEmpty()) {
            return new double[0];
        }
        double[] retVal = new double[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            String val = buffer.get(i);
            String tmp = removeFirstAndLast(val);
            double valInt = Double.parseDouble(tmp);
            retVal[i] = valInt;
        }
        return retVal;
    }

    public static int[] stringToArrayInt(String listStr) throws Exception {
        ArrayList<String> buffer = stringToArrayList(listStr);
        if (buffer == null) {
            return null;
        }
        if (buffer.isEmpty()) {
            return new int[0];
        }
        int[] retVal = new int[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            String val = buffer.get(i);
            String tmp = removeFirstAndLast(val);
            int valInt = Integer.parseInt(tmp);
            retVal[i] = valInt;
        }
        return retVal;
    }

}
