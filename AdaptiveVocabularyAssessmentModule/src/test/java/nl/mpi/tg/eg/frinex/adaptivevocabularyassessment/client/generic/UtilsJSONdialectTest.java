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
import org.junit.Ignore;

/**
 *
 * @author olhshk
 */
public class UtilsJSONdialectTest {

    public UtilsJSONdialectTest() {
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
     * Test of stringToObject method, of class UtilsJSONdialect.
     */
    
    @Test
    public void testStringToObject() throws Exception {
        System.out.println("stringToObject");

        String str = "{NO_TARGET=1, TARGET_ONLY=2, TARGET_AND_FOIL=3, fields=[NO_TARGET, TARGET_ONLY, TARGET_AND_FOIL]}";
        Map<String, Object> expResult = new LinkedHashMap<String, Object>();
        expResult.put(TrialCondition.NO_TARGET.toString(), new Integer(1));
        expResult.put(TrialCondition.TARGET_ONLY.toString(), new Integer(2));
        expResult.put(TrialCondition.TARGET_AND_FOIL.toString(), new Integer(3));

        Object result = UtilsJSONdialect.stringToObject(str);
        assertTrue(result instanceof Map<?, ?>);

        Map<String, Object> mapResult = (Map<String, Object>) result;
        Set<String> keys = mapResult.keySet();
        assertEquals(3, keys.size());
        for (String key : keys) {
            Integer valCast = Integer.parseInt(mapResult.get(key).toString());
            assertEquals(expResult.get(key), valCast);
        }

        // *** //
        String str2 = "{NO_TARGET=[], TARGET_ONLY=[1,2], TARGET_AND_FOIL=null, fields=[NO_TARGET, TARGET_ONLY, TARGET_AND_FOIL]}";
        Map<TrialCondition, List<Integer>> map2 = new LinkedHashMap<TrialCondition, List<Integer>>();
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        listB.add(1);
        listB.add(2);
        List<Integer> listC = null;
        map2.put(TrialCondition.NO_TARGET, listA);
        map2.put(TrialCondition.TARGET_ONLY, listB);
        map2.put(TrialCondition.TARGET_AND_FOIL, listC);

        Object result2 = UtilsJSONdialect.stringToObject(str2);
        assertTrue(result2 instanceof Map<?, ?>);
        Map<String, Object> mapResult2 = (Map<String, Object>) result2;
        Set<String> keys2 = mapResult2.keySet();
        assertEquals(3, keys2.size());
        for (String key : keys2) {
            TrialCondition keyCast = TrialCondition.valueOf(key);
            List<Integer> expectedList = map2.get(keyCast);
            if (expectedList == null) {
                assertNull(mapResult2.get(key));
                continue;
            }
            List<Object> listObj = (List<Object>) mapResult2.get(key);
            assertEquals(expectedList.size(), listObj.size());
            for (int i = 0; i < expectedList.size(); i++) {
                Integer valCast = Integer.parseInt(listObj.get(i).toString());
                assertEquals(expectedList.get(i), valCast);

            }
        }

    }

   


    /**
     * Test of positionsNotWithinParentheses method, of class UtilsJSONdialect.
     */
    @Test
    public void testPositionsWithinParentheses() {
        System.out.println("positionsWithinParentheses");
        String str = "[,],{,[,]},";
        List<Integer> result = UtilsJSONdialect.positionsNotWithinParentheses(str);
        assertEquals(2, result.size());
        assertEquals(3, result.get(0).intValue());
        assertEquals(10, result.get(1).intValue());
        
    }

}
