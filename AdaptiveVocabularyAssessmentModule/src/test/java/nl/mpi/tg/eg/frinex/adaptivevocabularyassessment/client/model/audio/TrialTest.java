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
import java.util.Arrays;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioIndexMap;
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
public class TrialTest {

    //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
    private static final LinkedHashMap<String, WordType> map1 = createMap1();

    private static LinkedHashMap<String, WordType> createMap1() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("deerbal", WordType.NON_WORD);
        retVal.put("smoer", WordType.TARGET_NON_WORD);
        retVal.put("wijp", WordType.NON_WORD);
        return retVal;
    }

    //19	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
    private static final LinkedHashMap<String, WordType> map2 = createMap2();

    private static LinkedHashMap<String, WordType> createMap2() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("geider", WordType.NON_WORD);
        retVal.put("hers", WordType.TARGET_NON_WORD);
        retVal.put("atgraus", WordType.NON_WORD);
        retVal.put("hamp", WordType.NON_WORD);
        return retVal;
    }

    //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
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

    //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
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
    
    private final ArrayList<String> indexMap;

    public TrialTest() {
        
        this.indexMap = new ArrayList<String>(Arrays.asList(AudioIndexMap.INDEX_ARRAY));

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //Trial(String word, String targetNonword, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String,WordType> targetNonWords, int bandNumber, String dirName)
        //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
        this.instance[0] = new Trial("vloer", "smoer", 1, TrialCondition.TARGET_ONLY, 3, map1, "6dB", 1, "/1");
        //19	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
        this.instance[1] = new Trial("kers", "hers", 1, TrialCondition.TARGET_ONLY, 4, map2, "10dB", 2, "/2");
        //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
        this.instance[2] = new Trial("vuur", "fjon", 1, TrialCondition.TARGET_AND_FOIL, 5, map3, "10dB", 2, "/3");
        //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
        this.instance[3] = new Trial("pop", "lop", 1, TrialCondition.NO_TARGET, 4, map4, "2dB", 0, "/2");
        
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
        for (int i = 0; i < this.instance.length; i++) {
            stimuli[i] = this.instance[i].getStimuliList();
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
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        String[] words = new String[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            words[i] = this.instance[i].getWord();
        }
        assertEquals("vloer", words[0]);
        assertEquals("kers", words[1]);
        assertEquals("vuur", words[2]);
        assertEquals("pop", words[3]);
    }

    /**
     * Test of getDirName method, of class Trial.
     */
    @Test
    public void testGetDirName() {
        System.out.println("getDirName");
        String[] dirNames = new String[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            dirNames[i] = this.instance[i].getDirName();
        }
        assertEquals("/1", dirNames[0]);
        assertEquals("/2", dirNames[1]);
        assertEquals("/3", dirNames[2]);
        assertEquals("/2", dirNames[3]);
    }

    /**
     * Test of getBandNumber method, of class Trial.
     */
    @Test
    public void testGetBandIndex() {
        System.out.println("getBandNumber");
        int[] bands = new int[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            bands[i] = this.instance[i].getBandIndex();
        }
        assertEquals(1, bands[0]);
        assertEquals(2, bands[1]);
        assertEquals(2, bands[2]);
        assertEquals(0, bands[3]);
    }
    
      @Test
    public void testGetBandLabel() {
        System.out.println("getBandLabel");
        String[] bands = new String[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            bands[i] = this.instance[i].getBandLabel();
        }
        assertEquals("6dB", bands[0]);
        assertEquals("10dB", bands[1]);
        assertEquals("10dB", bands[2]);
        assertEquals("2dB", bands[3]);
        assertEquals(1, this.indexMap.indexOf(bands[0]));
        assertEquals(2, this.indexMap.indexOf(bands[1]));
        assertEquals(2, this.indexMap.indexOf(bands[2]));
        assertEquals(0, this.indexMap.indexOf(bands[3]));
        
    }

    /**
     * Test of getTargetNonWord method, of class Trial.
     */
    @Test
    public void testGetTargetNonWord() {
        System.out.println("getTargetNonWord");
        String[] targetNonWords = new String[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            targetNonWords[i] = this.instance[i].getTargetNonWord();
        }
        assertEquals("smoer", targetNonWords[0]);
        assertEquals("hers", targetNonWords[1]);
        assertEquals("fjon", targetNonWords[2]);
        assertEquals("lop", targetNonWords[3]);
    }

    /**
     * Test of getNumberOfSyllables method, of class Trial.
     */
    @Test
    public void testGetNumberOfSyllables() {
        System.out.println("getNumberOfSyllables");
        int[] syllabN = new int[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            syllabN[i] = this.instance[i].getNumberOfSyllables();
        }
        assertEquals(1, syllabN[0]);
        assertEquals(1, syllabN[1]);
        assertEquals(1, syllabN[2]);
        assertEquals(1, syllabN[3]);
    }

    /**
     * Test of getCondition method, of class Trial.
     */
    @Test
    public void testGetCondition() {
        System.out.println("getCondition");
        TrialCondition[] consitions = new TrialCondition[this.instance.length];
        for (int i = 0; i < this.instance.length; i++) {
            consitions[i] = this.instance[i].getCondition();
        }
        assertEquals(TrialCondition.TARGET_ONLY, consitions[0]);
        assertEquals(TrialCondition.TARGET_ONLY, consitions[1]);
        assertEquals(TrialCondition.TARGET_AND_FOIL, consitions[2]);
        assertEquals(TrialCondition.NO_TARGET, consitions[3]);
    }

    /**
     * Test of getTrialLength method, of class Trial.
     */
    @Test
    public void testGetTrialLength() {
        System.out.println("getTrialLength");
        int[] trailL = new int[this.instance.length];
        ArrayList<AudioAsStimulus>[] stimuli = new ArrayList[this.instance.length];

        for (int i = 0; i < this.instance.length; i++) {
            trailL[i] = this.instance[i].getTrialLength();
            stimuli[i] = this.instance[i].getStimuliList();
        }
        assertEquals(3, trailL[0]);
        assertEquals(4, trailL[1]);
        assertEquals(5, trailL[2]);
        assertEquals(4, trailL[3]);

        assertEquals(trailL[0] + 1, stimuli[0].size());
        assertEquals(trailL[1] + 1, stimuli[1].size());
        assertEquals(trailL[2] + 1, stimuli[2].size());
        assertEquals(trailL[3] + 1, stimuli[3].size());
    }

}
