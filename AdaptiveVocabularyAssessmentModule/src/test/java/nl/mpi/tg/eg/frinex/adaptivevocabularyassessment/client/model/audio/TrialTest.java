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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class TrialTest {

    //1	vloer	smoer	1	Target-only	3 words	deebral	smoer	wijp
    private static final LinkedHashMap<String, WordType> map1 = createMap1();

    private static LinkedHashMap<String, WordType> createMap1() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("deerbal", WordType.NON_WORD);
        retVal.put("smoer", WordType.TARGET_NON_WORD);
        retVal.put("wijp", WordType.NON_WORD);
        return retVal;
    }

    //19	kers	hers	1	Target-only	4 words	geider	hers	atgraus	hamp
    private static final LinkedHashMap<String, WordType> map2 = createMap2();

    private static LinkedHashMap<String, WordType> createMap2() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("geider", WordType.NON_WORD);
        retVal.put("hers", WordType.TARGET_NON_WORD);
        retVal.put("atgraus", WordType.NON_WORD);
        retVal.put("hamp", WordType.NON_WORD);
        return retVal;
    }

    //107	vuur	fjon	1	Target+Foil	5 words	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
    private static final LinkedHashMap<String, WordType> map3 = createMap3();

    private static LinkedHashMap<String, WordType> createMap3() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("fjodschelg", WordType.FOIL);
        retVal.put("fjon", WordType.TARGET_NON_WORD);
        retVal.put("wisdaag", WordType.NON_WORD);
        retVal.put("tuik", WordType.NON_WORD);
        retVal.put("poks", WordType.NON_WORD);
        return retVal;
    }

    //156	pop	lop	1	NoTarget	4 words	voorserm	muiland	fraal	kijn	
    private static final LinkedHashMap<String, WordType> map4 = createMap4();

    private static LinkedHashMap<String, WordType> createMap4() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("voorserm", WordType.NON_WORD);
        retVal.put("muiland", WordType.NON_WORD);
        retVal.put("fraal", WordType.NON_WORD);
        retVal.put("kijn", WordType.NON_WORD);
        return retVal;
    }
    
    private final Trial[] instance = new Trial[4];

    public TrialTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //Trial(String word, String targetNonword, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String,WordType> words, int bandNumber, String dirName)
        //1	vloer	smoer	1	Target-only	3 words	deebral	smoer	wijp
        this.instance[0] = new Trial("vloer", "smoer", 1, TrialCondition.TARGET_ONLY, 3, map1, 20, "/1");
        //19	kers	hers	1	Target-only	4 words	geider	hers	atgraus	hamp
        this.instance[1] = new Trial("kers", "hers", 1, TrialCondition.TARGET_ONLY, 4, map2, 21, "/2");
        //107	vuur	fjon	1	Target+Foil	5 words	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
        this.instance[2] = new Trial("vuur", "fjon", 1, TrialCondition.TARGET_AND_FOIL, 5, map3, 22, "/3");
        //156	pop	lop	1	NoTarget	4 words	voorserm	muiland	fraal	kijn	
        this.instance[3]= new Trial("pop", "lop", 1, TrialCondition.NO_TARGET, 4, map4, 22, "/2");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getStimuliList method, of class Trial.
     */
    @Test
    public void testGetStimuliList() {
        System.out.println("getStimuliList");
        ArrayList<AudioAsStimulus>[] stimuli = new ArrayList[this.instance.length];
        for (int i=0; i<this.instance.length; i++){
            stimuli[i]=this.instance[i].getStimuliList();
        }
        assertEquals(4, stimuli[0].size());
        assertEquals("smoer", stimuli[0].get(0).getLabel());
        assertEquals("deerbal", stimuli[0].get(1).getLabel());
        assertEquals("smoer", stimuli[0].get(2).getLabel());
        assertEquals("wijp", stimuli[0].get(3).getLabel());
        
        assertEquals(5, stimuli[1].size());
        assertEquals("hers", stimuli[1].get(0).getLabel());
        assertEquals("geider", stimuli[1].get(1).getLabel());
        assertEquals("hers", stimuli[1].get(2).getLabel());
        assertEquals("atgraus", stimuli[1].get(3).getLabel());
        assertEquals("hamp", stimuli[1].get(4).getLabel());
        
        assertEquals(6, stimuli[2].size());
        assertEquals("fjon", stimuli[2].get(0).getLabel());
        assertEquals("fjodschelg", stimuli[2].get(1).getLabel());
        assertEquals("fjon", stimuli[2].get(2).getLabel());
        assertEquals("wisdaag", stimuli[2].get(3).getLabel());
        assertEquals("tuik", stimuli[2].get(4).getLabel());
        assertEquals("poks", stimuli[2].get(5).getLabel());
        
        assertEquals(5, stimuli[3].size());
        assertEquals("lop", stimuli[3].get(0).getLabel());
        assertEquals("voorserm", stimuli[3].get(1).getLabel());
        assertEquals("muiland", stimuli[3].get(2).getLabel());
        assertEquals("fraal", stimuli[3].get(3).getLabel());
        assertEquals("kijn", stimuli[3].get(4).getLabel());
    }

    /**
     * Test of getWord method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        Trial instance = null;
        String expResult = "";
        String result = instance.getWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirName method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetDirName() {
        System.out.println("getDirName");
        Trial instance = null;
        String expResult = "";
        String result = instance.getDirName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBandNumber method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        Trial instance = null;
        int expResult = 0;
        int result = instance.getBandNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTargetNonWord method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetTargetNonWord() {
        System.out.println("getTargetNonWord");
        Trial instance = null;
        String expResult = "";
        String result = instance.getTargetNonWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfSyllables method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetNumberOfSyllables() {
        System.out.println("getNumberOfSyllables");
        Trial instance = null;
        int expResult = 0;
        int result = instance.getNumberOfSyllables();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCondition method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetCondition() {
        System.out.println("getCondition");
        Trial instance = null;
        TrialCondition expResult = null;
        TrialCondition result = instance.getCondition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrialLength method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetTrialLength() {
        System.out.println("getTrialLength");
        Trial instance = null;
        int expResult = 0;
        int result = instance.getTrialLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToCondition method, of class Trial.
     */
    @Ignore
    @Test
    public void testStringToCondition() {
        System.out.println("stringToCondition");
        String conditionString = "";
        TrialCondition expResult = null;
        TrialCondition result = Trial.stringToCondition(conditionString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearUsages method, of class Trial.
     */
    @Ignore
    @Test
    public void testClearUsages() {
        System.out.println("clearUsages");
        Trial instance = null;
        instance.clearUsages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
