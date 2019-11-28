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

import java.util.Arrays;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
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
public class BookkeepingStimulusTest {

    public final BookkeepingStimulus<AdVocAsStimulus> instance;
    public final AdVocAsStimulus stimulus;

    public BookkeepingStimulusTest() {
        String uniqueId = "rabarber_w";
        String label = "rabarber";

        //AdVocAsStimulus(String uniqueId, String label, String ratingLabels, String correctResponses,  int bandNumber)
        this.stimulus = new AdVocAsStimulus(uniqueId, label, "yes,no", "no", 10);

        this.instance = new BookkeepingStimulus<>(stimulus);

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
     * Test of getStimulus method, of class BookkeepingStimulus.
     */
    @Test
    public void testGetStimulus() {
        System.out.println("getStimulus");
        AdVocAsStimulus result = instance.getStimulus();
        assertEquals(this.stimulus, result);
    }

    /**
     * Test of getReaction method, of class BookkeepingStimulus.
     */
    @Test
    public void testGetSetReaction() {
        System.out.println("getReaction, setReaction");
        String result = instance.getReaction();
        assertEquals(null, result);
        
        this.instance.setReaction("false");
        assertEquals("false", instance.getReaction());
        
        this.instance.setReaction("Disagree!");
        assertEquals("Disagree!", instance.getReaction());
    }

    /**
     * Test of getTimeStamp method, of class BookkeepingStimulus.
     */
    @Test
    public void testGetSetTimeStamp() {
        System.out.println("getTimeStamp");
        long result = instance.getTimeStamp();
        assertEquals(0, result);
        
        long now = System.currentTimeMillis();
        this.instance.setTimeStamp(now);
        assertEquals(now, this.instance.getTimeStamp());
    }

    /**
     * Test of getCorrectness method, of class BookkeepingStimulus.
     */
    @Test
    public void testGetSetCorrectness() {
        System.out.println("getCorrectness, setCorrectness");
        Boolean result = instance.getCorrectness();
        assertEquals(null, result);
        
        this.instance.setCorrectness(false);
        assertFalse(this.instance.getCorrectness());
        
        this.instance.setCorrectness(true);
        assertTrue(this.instance.getCorrectness());
    }

    /**
     * Test of toString method, of class BookkeepingStimulus.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "{fields=[stimulus, userReaction, correctness, timeStamp], stimulus=rabarber_w, userReaction=null, correctness=null, timeStamp=0}";
        String result = this.instance.toString();
        assertEquals(expResult, result);

        long now = System.currentTimeMillis();
        this.instance.setTimeStamp(now);
        this.instance.setReaction("yes");
        this.instance.setCorrectness(false);
        String expResult2 = "{fields=[stimulus, userReaction, correctness, timeStamp], stimulus=rabarber_w, userReaction=yes, correctness=false, timeStamp=" + now + "}";
        String result2 = this.instance.toString();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of toObject method, of class BookkeepingStimulus.
     */
    @Test
    public void testToBookkeepingStimulusObject() throws Exception {
        System.out.println("testToBookkeepingStimulusObject");
        long now = System.currentTimeMillis();
        LinkedHashMap<String, Object> inputMap = new LinkedHashMap<>();
        String[] flds = {"stimulus", "userReaction", "correctness", "timeStamp"};
        inputMap.put("fields", Arrays.asList(flds));
        inputMap.put("stimulus", "rabarber_w");
        inputMap.put("userReaction", "yes");
        inputMap.put("correctness", false);
        inputMap.put("timeStamp", now);

        LinkedHashMap<String, AdVocAsStimulus> map = new LinkedHashMap<>();
        map.put("rabarber_w", this.stimulus);

        BookkeepingStimulus<AdVocAsStimulus> result = this.instance.toBookkeepingStimulusObject(inputMap, map);
        assertEquals("yes", result.getReaction());
        assertFalse(result.getCorrectness());
        assertEquals(now, result.getTimeStamp());
        assertEquals(this.stimulus, result.getStimulus());
    }

  

   
    /**
     * Test of toObject method, of class BookkeepingStimulus.
     */
    // TODO
    @Ignore
    @Test
    public void testToObject() throws Exception {
        System.out.println("toObject");
        BookkeepingStimulus instance = null;
        BookkeepingStimulus expResult = null;
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
