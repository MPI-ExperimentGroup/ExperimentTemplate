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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
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
        String result = UtilsJSONdialect.getKey(jsonString, key);
        assertEquals(expResult, result);

        String jsonString1 = "{key11: {val11} , key2:{val2}}";
        String key1 = "key11";
        String expResult1 = "{val11}";
        String result1 = UtilsJSONdialect.getKey(jsonString1, key1);
        assertEquals(expResult1, result1);
        String result11 = UtilsJSONdialect.getKey(jsonString1, "key2");
        assertEquals("{val2}", result11);

        String jsonString2 = "{key21: {val21} , key22:{val22}, key23:{val23}  }";
        String key2 = "key22";
        String expResult2 = "{val22}";
        String result2 = UtilsJSONdialect.getKey(jsonString2, key2);
        assertEquals(expResult2, result2);

        String jsonString3 = "{key21: {val21} , key22:{val22,{},{{val221},{}}}, key23:{val23}  }";
        String key3 = "key22";
        String expResult3 = "{val22,{},{{val221},{}}}";
        String result3 = UtilsJSONdialect.getKey(jsonString3, key3);
        assertEquals(expResult3, result3);

    }
    
   

    /**
     * Test of arrayListToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayListToString() throws Exception {
        System.out.println("arrayListToString");
        UtilsJSONdialect instance = new UtilsJSONdialect<String>();
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
        UtilsJSONdialect instance = new UtilsJSONdialect<String>();
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(100);
        input.add(200);
        input.add(350);
        String expResult = "{0:{100},1:{200},2:{350}}";
        String result = instance.arrayListToString(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of arrayListToString method, of class UtilsJSONdialect.
     */
    @Test
    public void testArrayListToString3() throws Exception {
        System.out.println("arrayListToString");
        UtilsJSONdialect instance = new UtilsJSONdialect<String>();
        ArrayList<AudioAsStimulus> input = new ArrayList<AudioAsStimulus>();

        /*
    public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel)
         */
        AudioAsStimulus stimulus1 = new AudioAsStimulus("xxx", "test", 100, "/here/test.wav", null, "2dB", 0, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        AudioAsStimulus stimulus2 = new AudioAsStimulus("xxx1", "test1", 200, "/here/test1.wav", "YES", "6dB", 1, WordType.TARGET_NON_WORD, AudioAsStimulus.AUDIO_RATING_LABEL);
        AudioAsStimulus stimulus3 = new AudioAsStimulus("xxx2", "test2", 300, "/here/test2.wav", null, "10dB", 2, WordType.NON_WORD, AudioAsStimulus.AUDIO_RATING_LABEL);
        long millisec1 = System.currentTimeMillis();
        stimulus1.setTimeStamp(millisec1);
        input.add(stimulus1);
        long millisec2 = System.currentTimeMillis();
        stimulus2.setTimeStamp(millisec2);
        input.add(stimulus2);
        long millisec3 = System.currentTimeMillis();
        stimulus3.setTimeStamp(millisec3);
        input.add(stimulus3);
        
        String expResult1 = "{audioPath:{/here/test.wav},label:{test},pauseMs:{100},ratingLabels:{"+AudioAsStimulus.EXAMPLE_TARGET_LABEL+"},uniqueId:{xxx},bandLabel:{2dB},bandIndex:{0},timeStamp:{"+millisec1+"},wordType:{"+WordType.EXAMPLE_TARGET_NON_WORD+"}}";
        String expResult2 = "{audioPath:{/here/test1.wav},correctResponses:{YES},label:{test1},pauseMs:{200},ratingLabels:{"+AudioAsStimulus.AUDIO_RATING_LABEL+"},uniqueId:{xxx1},bandLabel:{6dB},bandIndex:{1},timeStamp:{"+millisec2+"},wordType:{"+WordType.TARGET_NON_WORD+"}}";
        String expResult3 = "{audioPath:{/here/test2.wav},label:{test2},pauseMs:{300},ratingLabels:{"+AudioAsStimulus.AUDIO_RATING_LABEL+"},uniqueId:{xxx2},bandLabel:{10dB},bandIndex:{2},timeStamp:{"+millisec3+"},wordType:{"+WordType.NON_WORD+"}}";
        
        
        String expResult = "{0:"+expResult1+",1:"+expResult2+",2:"+expResult3+"}";
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
        UtilsJSONdialect instance = new UtilsJSONdialect();
        ArrayList<String> result = instance.stringToArrayList(listStr);
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
        UtilsJSONdialect instance = new UtilsJSONdialect();
        ArrayList<String> result = instance.stringToArrayList(listStr);
        assertEquals(expResult, result);
    }

}
