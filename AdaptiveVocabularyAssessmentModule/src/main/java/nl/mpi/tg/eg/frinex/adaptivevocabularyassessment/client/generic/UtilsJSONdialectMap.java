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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author olhshk
 */
public class UtilsJSONdialectMap {

    public static final int MAX_LIST_LENGTH = 20000;
    public static final String FIELD_SEPARATOR = "\nFIELDS\n";

    //*** Object to String ***/
    public static String paramToString(Object param) throws Exception {

        if (param == null) {
            return "{null}";
        }

        if (param instanceof List<?>) {
            return UtilsJSONdialectMap.listToString((List<Object>) param);
        }

        if (param instanceof Map<?, ?>) {
            return UtilsJSONdialectMap.mapToString((Map<Object, Object>) param);
        }
        String tmp = param.toString(); // wrap into "{ } when necessary
        if (tmp.trim().startsWith("{")) {
            return tmp;
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("{").append(tmp).append("}");
            return builder.toString();
        }
    }

    private static String listToString(List<Object> list) throws Exception {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "{}";
        }
        StringBuilder retVal = new StringBuilder();
        retVal.append("{");
        for (int i = 0; i < list.size() - 1; i++) {

            Object obj = list.get(i);
            retVal.append(i).append(":");

            String objString = paramToString(obj);

            if (objString.startsWith("{")) {
                retVal.append(objString);
            } else {
                retVal.append("{").append(objString).append("}");
            }
            retVal.append(",");
        }

        int lastIndex = list.size() - 1;
        Object obj = list.get(lastIndex);
        retVal.append(lastIndex).append(":");
        String objString = paramToString(obj);
        if (objString.startsWith("{")) {
            retVal.append(objString);
        } else {
            retVal.append("{").append(objString).append("}");
        }
        retVal.append("}");
        return retVal.toString();
    }

    private static String mapToString(Map<Object, Object> map) throws Exception {
        StringBuilder builder = new StringBuilder();
        StringBuilder fieldBuilder = new StringBuilder();
        builder.append("{");
        Set<Object> keys = map.keySet();
        int size = keys.size();
        int i = 0;
        for (Object key : keys) {
            String keyStr = key.toString();
            fieldBuilder.append(keyStr);
            builder.append(keyStr).append(":");
            Object value = map.get(key);
            String valStr = paramToString(value);
            builder.append(valStr);
            if (i < size - 1) {
                fieldBuilder.append(",");
                builder.append(",");
            }
            i++;
        }
        builder.append("}");
        builder.append(FIELD_SEPARATOR);
        builder.append(fieldBuilder.toString());

        return builder.toString();
    }

    //*** String to Object ***//
    public static Object stringToObject(String str) throws Exception {
        if (str.startsWith("{0:")) { //is a list
            return stringToList(str);
        }
        if (str.contains(":{")) { //is a map
            String[] parts = str.split(FIELD_SEPARATOR);
            String[] fields = parts[1].split(",");
            return stringToMap(parts[0], fields, false);
        }
        if (str.startsWith("{") && !str.trim().equals("{}")) {
            return removeFirstAndLast(str);
        } else {
            return str;
        }
    }

    private static String getKey(String jsonStringInit, String key) throws Exception {
       
        if (jsonStringInit == null) {
            return null;
        }
        if (key == null) {
            return jsonStringInit;
        }

        String jsonString = (new StringBuilder()).append(jsonStringInit).toString();
        if (jsonStringInit.startsWith("{") && jsonStringInit.endsWith("}")) {
            jsonString = removeFirstAndLast(jsonStringInit);
        }

        String regExp = "(^" + key + ":)|(," + key + ":)|( " + key + ":)";

        String[] parts = jsonString.split(regExp, 2);
        if (parts.length < 2) {
            return null;
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
                    return val.toString();
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

    private static String removeFirstAndLast(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() < 2) {
            return "";
        }
        String retVal = str.substring(1, str.length() - 1);
        return retVal;
    }

    private static List<Object> stringToList(String listStr) throws Exception {
        String[] fields = new String[MAX_LIST_LENGTH];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = String.valueOf(i);
        }
        Map<Object, Object> map = stringToMap(listStr, fields, true);
        List<Object> retVal = new ArrayList<Object>();
        Set<Object> keys = map.keySet();
        for (Object key : keys) {
            retVal.add(map.get(key));
        }
        return retVal;
    }

    private static Map<Object, Object> stringToMap(String mapStr, String[] fields, boolean cutOnNull) throws Exception {
        if (mapStr == null) {
            return null;
        }

        if (mapStr.trim().equals("null") || mapStr.trim().equals("{null}")) {
            return null;
        }

        if (mapStr.trim().isEmpty() || mapStr.trim().equals("{}")) {
            return new LinkedHashMap<Object, Object>();
        }

        Map<Object, Object> retVal = new LinkedHashMap<Object, Object>();
        for (int i = 0; i < fields.length; i++) {
            String current = getKey(mapStr, fields[i]);
            if (current == null && cutOnNull) {
                break;
            }
            Object object = stringToObject(current);
            retVal.put(fields[i], object);
        }

        return retVal;
    }

}
