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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olhshk
 */
public class UtilsJSONdialectMapTest {

    public UtilsJSONdialectMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of paramToString
     */
    @Test
    public void testParamToString() throws Exception {
        System.out.println("paramToString");

        Object param = new Integer(35);
        String result = UtilsJSONdialectMap.paramToString(param);
        assertEquals("{35}", result);

        ArrayList<String> list = new ArrayList<String>();
        list.add("rhabarber");
        list.add("compot");
        list.add("{rhabarber,compot}");
        String expResultList = "{0:{rhabarber},1:{compot},2:{rhabarber,compot}}";
        String resultList = UtilsJSONdialectMap.paramToString(list);
        System.out.println(resultList);
        assertEquals(expResultList, resultList);

        int n = 4;
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> current = new ArrayList<Integer>(i);
            list2.add(current); // should be {0,1,..,i-1} -> i:{0:{0},1:{1},..,i-1:{i-1}}
            for (int j = 0; j < i; j++) {
                current.add(j);
            }
        }
        String expResultList2 = "{0:{},1:{0:{0}},2:{0:{0},1:{1}},3:{0:{0},1:{1},2:{2}}}";
        String resultList2 = UtilsJSONdialectMap.paramToString(list2);
        System.out.println(resultList2);
        assertEquals(expResultList2, resultList2);

        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put(TrialCondition.NO_TARGET, new Integer(1));
        map.put(TrialCondition.TARGET_ONLY, new Integer(2));
        map.put(TrialCondition.TARGET_AND_FOIL, new Integer(3));
        String expResult3 = "{NO_TARGET:{1},TARGET_ONLY:{2},TARGET_AND_FOIL:{3}}" + UtilsJSONdialectMap.FIELD_SEPARATOR + "NO_TARGET,TARGET_ONLY,TARGET_AND_FOIL";
        String result3 = UtilsJSONdialectMap.paramToString(map);
        assertEquals(expResult3, result3);

        Map<Object, Object> map2 = new LinkedHashMap<Object, Object>();
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        listB.add(1);
        listB.add(2);
        List<Integer> listC = null;

        map2.put(TrialCondition.NO_TARGET, listA);
        map2.put(TrialCondition.TARGET_ONLY, listB);
        map2.put(TrialCondition.TARGET_AND_FOIL, listC);
        String expResult4 = "{NO_TARGET:{},TARGET_ONLY:{0:{1},1:{2}},TARGET_AND_FOIL:{null}}" + UtilsJSONdialectMap.FIELD_SEPARATOR + "NO_TARGET,TARGET_ONLY,TARGET_AND_FOIL";
        String result4 = UtilsJSONdialectMap.paramToString(map2);
        assertEquals(expResult4, result4);

    }

    /**
     * Test of stringToObject method, of class UtilsJSONdialectMap.
     */
    @Test
    public void testStringToObject() throws Exception {
        System.out.println("stringToObject");

        String str = "{NO_TARGET:{1},TARGET_ONLY:{2},TARGET_AND_FOIL:{3}}" + UtilsJSONdialectMap.FIELD_SEPARATOR + "NO_TARGET,TARGET_ONLY,TARGET_AND_FOIL";
        Map<Object, Object> expResult = new LinkedHashMap<Object, Object>();
        expResult.put(TrialCondition.NO_TARGET, new Integer(1));
        expResult.put(TrialCondition.TARGET_ONLY, new Integer(2));
        expResult.put(TrialCondition.TARGET_AND_FOIL, new Integer(3));
        Object result = UtilsJSONdialectMap.stringToObject(str);
        assertTrue(result instanceof Map<?, ?>);

        Map<Object, Object> mapResult = (Map<Object, Object>) result;
        Set<Object> keys = mapResult.keySet();
        assertEquals(3, keys.size());
        for (Object key : keys) {
            TrialCondition keyCast = TrialCondition.valueOf((String) key);
            Integer valCast = Integer.parseInt(mapResult.get(key).toString());
            assertEquals(expResult.get(keyCast), valCast);
        }

        // *** //
        String str2 = "{NO_TARGET:{},TARGET_ONLY:{0:{1},1:{2}},TARGET_AND_FOIL:{null}}" + UtilsJSONdialectMap.FIELD_SEPARATOR + "NO_TARGET,TARGET_ONLY,TARGET_AND_FOIL";
        Map<TrialCondition, List<Integer>> map2 = new LinkedHashMap<TrialCondition, List<Integer>>();
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        listB.add(1);
        listB.add(2);
        List<Integer> listC = null;
        map2.put(TrialCondition.NO_TARGET, listA);
        map2.put(TrialCondition.TARGET_ONLY, listB);
        map2.put(TrialCondition.TARGET_AND_FOIL, listC);

        Object result2 = UtilsJSONdialectMap.stringToObject(str2);
        assertTrue(result2 instanceof Map<?, ?>);
        Map<Object, Object> mapResult2 = (Map<Object, Object>) result2;
        Set<Object> keys2 = mapResult2.keySet();
        assertEquals(3, keys2.size());
        for (Object key : keys2) {
            TrialCondition keyCast = TrialCondition.valueOf((String) key);
            Object listObj = mapResult2.get(key);
            List<Object> list;
            if (listObj == null || listObj.equals("null")) {
                list = null;
            } else {
                if (listObj.toString().trim().equals("{}")) {
                    list = new ArrayList<Object>();
                } else {
                    list = (List<Object>) listObj;
                }
            }
            List<Integer> expectedList = map2.get(keyCast);
            if (expectedList == null) {
                assertNull(list);
            } else {
                assertEquals(expectedList.size(), list.size());
                for (int i = 0; i < expectedList.size(); i++) {
                    Integer valCast = Integer.parseInt(list.get(i).toString());
                    assertEquals(expectedList.get(i), valCast);
                }
            }
        }

    }

}
