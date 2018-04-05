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

    /**
     * Test of getKey method, of class UtilsJSONdialect.
     */
    @Test
    public void testGetKey() throws Exception {
        System.out.println("getKey");

        String jsonString = "{key1:{val1}}";
        String key = "key1";
        String expResult = "{val1}";
        String result = UtilsJSONdialect.getKey(jsonString, key)[0];
        assertEquals(expResult, result);

        String jsonString1 = "{key11: {val11} , key2:{val2}}";
        String key1 = "key11";
        String expResult1 = "{val11}";
        String result1 = UtilsJSONdialect.getKey(jsonString1, key1)[0];
        assertEquals(expResult1, result1);
        String result11 = UtilsJSONdialect.getKey(jsonString1, "key2")[0];
        assertEquals("{val2}", result11);

        String jsonString2 = "{key21: {val21} , key22:{val22}, key23:{val23}  }";
        String key2 = "key22";
        String expResult2 = "{val22}";
        String result2 = UtilsJSONdialect.getKey(jsonString2, key2)[0];
        assertEquals(expResult2, result2);

        String jsonString3 = "{key21: {val21} , key22:{val22,{},{{val221},{}}}, key23:{val23}  }";
        String key3 = "key22";
        String expResult3 = "{val22,{},{{val221},{}}}";
        String result3 = UtilsJSONdialect.getKey(jsonString3, key3)[0];
        assertEquals(expResult3, result3);

    }

    /**
     * Test of arrayListToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayListToString() throws Exception {
        System.out.println("arrayListToString");
        UtilsJSONdialect<String> instance = new UtilsJSONdialect<String>();
        ArrayList<String> input = new ArrayList<String>();
        input.add("rhabarber");
        input.add("compot");
        input.add("{rhabarber,compot}");
        String expResult = "{0:{rhabarber},1:{compot},2:{rhabarber,compot}}";
        String result = instance.arrayListToString(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of arrayListToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayListToString2() throws Exception {
        System.out.println("arrayListToString");
        UtilsJSONdialect<Integer> instance = new UtilsJSONdialect<Integer>();
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(100);
        input.add(200);
        input.add(350);
        String expResult = "{0:{100},1:{200},2:{350}}";
        String result = instance.arrayListToString(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToArrayList method, of class UtilsJSONdialect.
     */
    @Test
    public void testStringToArrayList() throws Exception {
        System.out.println("stringToArrayList");
        String listStr = "{0:{rhabarber},1:{compot},2:{rhabarber,compot}}";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("{rhabarber}");
        expResult.add("{compot}");
        expResult.add("{rhabarber,compot}");
        ArrayList<String> result = UtilsJSONdialect.stringToArrayList(listStr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToArrayList method, of class UtilsJSONdialect.
     */
    @Test
    public void testStringToArrayList2() throws Exception {
        System.out.println("stringToArrayList");
        String listStr = "{0:{1},1:{2},2:{3,4,5}}";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("{1}");
        expResult.add("{2}");
        expResult.add("{3,4,5}");
        ArrayList<String> result = UtilsJSONdialect.stringToArrayList(listStr);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeFirstAndLast method, of class UtilsJSONdialect.
     */
    @Test
    public void testRemoveFirstAndLast() {
        System.out.println("removeFirstAndLast");
        String str = "{abc}";
        String expResult = "abc";
        String result = UtilsJSONdialect.removeFirstAndLast(str);
        assertEquals(expResult, result);
    }

    /**
     * Test of getKeyWithoutBrackets method, of class UtilsJSONdialect.
     */
    @Test
    public void testGetKeyWithoutBrackets() throws Exception {
        System.out.println("getKeyWithoutBrackets");
        String jsonString = "{x:{y},param:{value},param1:{value1,value2}}";
        String key = "param";
        String expResult = "value";
        String result = UtilsJSONdialect.getKeyWithoutBrackets(jsonString, key);
        assertEquals(expResult, result);
    }

    /**
     * Test of arrayList2String method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayList2String() throws Exception {
        System.out.println("arrayList2String");
        int n=4;
        UtilsJSONdialect<Integer> instance = new UtilsJSONdialect<Integer>();
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> current = new ArrayList<Integer>(i);
            input.add(current); // should be {0,1,..,i-1} -> i:{0:{0},1:{1},..,i-1:{i-1}}
            for (int j = 0; j < i; j++) {
                 current.add(j);
            }
        }
        String expResult = "{0:{},1:{0:{0}},2:{0:{0},1:{1}},3:{0:{0},1:{1},2:{2}}}";
        String result = instance.arrayList2String(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of arrayList3String method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayList3String() throws Exception {
        System.out.println("arrayList3String");
        int n=3;
        UtilsJSONdialect<String> instance = new UtilsJSONdialect<String>();
        ArrayList<ArrayList<ArrayList<String>>> input = new  ArrayList<ArrayList<ArrayList<String>>>(n);
        ArrayList<ArrayList<String>> l1 = new  ArrayList<ArrayList<String>>(2);
        ArrayList<ArrayList<String>> l2 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> l3 = new  ArrayList<ArrayList<String>>(3);
        input.add(l1);
        input.add(l2);
        input.add(l3);
        
        // {{a,b,c},{x,y}}
        ArrayList<String> l11 = new ArrayList<String>(3);
        ArrayList<String> l12 = new ArrayList<String>(2);
        
        l11.add("a");
        l11.add("b");
        l11.add("c");
        
        l12.add("x");
        l12.add("y");
        
        l1.add(l11);
        l1.add(l12);
        
        // {}
        
        
        // {{1} {1,2}, {1,2,3}}
        ArrayList<String> l31 = new ArrayList<String>(1);
        ArrayList<String> l32 = new ArrayList<String>(2);
        ArrayList<String> l33 = new ArrayList<String>(3);
        l3.add(l31);
        l3.add(l32);
        l3.add(l33);
        l31.add("1");
        l32.add("1");
        l32.add("2");
        l33.add("1");
        l33.add("2");
        l33.add("3");
        
       
        String expResult = "{0:{0:{0:{a},1:{b},2:{c}},1:{0:{x},1:{y}}},1:{},2:{0:{0:{1}},1:{0:{1},1:{2}},2:{0:{1},1:{2},2:{3}}}}";
        String result = instance.arrayList3String(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of intArrayToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testIntArrayListToString() throws Exception {
        System.out.println("intArrayListToString");
        int[] arr = {1,2,3};
        String expResult = "{0:{1},1:{2},2:{3}}";
        String result = UtilsJSONdialect.intArrayToString(arr);
        assertEquals(expResult, result);
        assertEquals(null, UtilsJSONdialect.intArrayToString(null));
        assertEquals("{}", UtilsJSONdialect.intArrayToString(new int[0]));
    }

    /**
     * Test of doubleArrayToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testDoubleArrayListToString() throws Exception {
        System.out.println("doubleArrayListToString");
        double[] arr = {0.5, 0.8, 100.356785};
        String expResult = "{0:{0.5},1:{0.8},2:{100.356785}}";
        String result = UtilsJSONdialect.doubleArrayToString(arr);
        assertEquals(expResult, result);
        assertEquals(null, UtilsJSONdialect.doubleArrayToString(null));
        assertEquals("{}", UtilsJSONdialect.doubleArrayToString(new double[0]));
    }

    /**
     * Test of stringToArrayListInteger method, of class UtilsJSONdialect.
     */
    @Test
    public void testStringToArrayListInteger() throws Exception {
        System.out.println("stringToArrayListInteger");
        String listStr = "{0:{100},1:{90},2:{110}}";
        ArrayList<Integer> expResult = new ArrayList<Integer>(3);
        expResult.add(100);
        expResult.add(90);
        expResult.add(110);
        ArrayList<Integer> result = UtilsJSONdialect.stringToArrayListInteger(listStr);
        assertEquals(expResult, result);
        assertEquals(null, UtilsJSONdialect.stringToArrayListInteger(null));
        assertEquals(new ArrayList<Integer>(), UtilsJSONdialect.stringToArrayListInteger(" "));
        assertEquals(new ArrayList<Integer>(), UtilsJSONdialect.stringToArrayListInteger("{}"));
    }

    /**
     * Test of stringToArrayDouble method, of class UtilsJSONdialect.
     */
    @Test
    public void testStringToArrayDouble() throws Exception {
        System.out.println("stringToArrayDouble");
        String listStr = "{0:{0.5},1:{1.4},2:{2.3},3:{3.2}}";
        double[] expResult = {0.5,1.4,2.3,3.2};
        
        double[] result = UtilsJSONdialect.stringToArrayDouble(listStr);
        for (int i = 0; i < result.length; i++) {
            assertTrue(expResult[i] == result[i]);
        }
    }

}
