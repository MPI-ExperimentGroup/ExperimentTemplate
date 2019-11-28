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

   
    @Test
    public void testStringToObject3() throws Exception {
     String str3 = "[[11,12,13],[],[15,16],[29]]";
        Object result3 = UtilsJSONdialect.stringToObject(str3);
        List<Object> list3 = (List<Object>) result3;
        assertEquals(4, list3.size());
        
        List<Object> inner1 = (List<Object>) list3.get(0);
        assertEquals(3, inner1.size());
        assertEquals(11, Integer.parseInt(inner1.get(0).toString()));
        assertEquals(12, Integer.parseInt(inner1.get(1).toString()));
        assertEquals(13, Integer.parseInt(inner1.get(2).toString()));
        
        List<Object> inner2 = (List<Object>) list3.get(1);
        assertEquals(0, inner2.size());
        
        List<Object> inner3 = (List<Object>) list3.get(2);
        assertEquals(2, inner3.size());
        assertEquals(15, Integer.parseInt(inner3.get(0).toString()));
        assertEquals(16, Integer.parseInt(inner3.get(1).toString()));
        
        List<Object> inner4 = (List<Object>) list3.get(3);
        assertEquals(1, inner4.size());
        assertEquals(29, Integer.parseInt(inner4.get(0).toString()));
    }
    
    @Test
    public void testStringToObject4() throws Exception {
     String str3 = "{a={a=a, b=[x, y], c=null}, 1=2, d={1=2, 2=3}}";
        Object result3 = UtilsJSONdialect.stringToObject(str3);
        Map<String,Object> map3 = ( Map<String,Object>) result3;
        
        Map<String,Object> innerMap1 = ( Map<String,Object>) map3.get("a");
        assertEquals("a", innerMap1.get("a"));
        assertNull(innerMap1.get("c"));
        List<Object> l =  (List<Object>) innerMap1.get("b");
        assertEquals(2, l.size());
        assertEquals("x", l.get(0));
        assertEquals("y", l.get(1));
        
        assertEquals("2", map3.get("1"));
        
        Map<String,Object> innerMap3 = ( Map<String,Object>) map3.get("d");
        assertEquals("2", innerMap3.get("1"));
        assertEquals("3", innerMap3.get("2"));
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
