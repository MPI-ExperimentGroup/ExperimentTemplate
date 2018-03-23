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
import java.util.Map;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.AudioStimuliFromString;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import utils.TrialsCsv;

/**
 *
 * @author olhshk
 */
public class TrialTest {

   private ArrayList<Trial> instance;
   
   private String[] labelling = {"min10db", "min8db", "min6db", "min4db", "min2db", "zerodb", "plus2db", "plus4db", "plus6db", "plus8db", "plus10db"};
    
    
    public TrialTest(){
        AudioStimuliFromString util = new AudioStimuliFromString();
        try {
            this.instance = this.parseTrialsInputCSVStringIntoTrialsArray(util, TrialsCsv.CSV_CONTENT);
        } catch (Exception ex) {
            
        } 
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
     * Test of getStimuliList method, of class Trial.
     */
    @Test
    public void testGetStimuliList() {
        System.out.println("getStimuliList");
       
        Trial   trial1= this.instance.get(0);
        ArrayList<BookkeepingStimulus<AudioAsStimulus>>  stimuli = trial1.getStimuli();
        
        //"1;vloer;smoer_1.wav;1;Target-only;3 words;deebral.wav;smoer_2.wav;wijp.wav;;;;2;plus10db;0;\n" 
        
        assertEquals(4, stimuli.size());
        assertEquals("smoer_1", stimuli.get(0).getStimulus().getLabel());
        assertEquals("deebral", stimuli.get(1).getStimulus().getLabel());
        assertEquals("smoer_2", stimuli.get(2).getStimulus().getLabel());
        assertEquals("wijp", stimuli.get(3).getStimulus().getLabel());
        
        assertEquals(10, trial1.getBandIndex());
        assertEquals("plus10db", trial1.getBandLabel());
        assertEquals(TrialCondition.TARGET_ONLY, trial1.getCondition());
        assertEquals(1, trial1.getId());
        assertEquals(1, trial1.getNumberOfSyllables());
        assertEquals(0, trial1.getPositionFoil());
        assertEquals(2, trial1.getPositionTarget());
    }

    /**
     * Test of getWord method, of class Trial.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        int testLength = 4;
        String[] words = new String[testLength];
        for (int i = 0; i < testLength; i++) {
            words[i] = this.instance.get(i).getWord();
        }
//        "1;vloer;smoer_1.wav;1;Target-only;3 words;deebral.wav;smoer_2.wav;wijp.wav;;;;2;plus10db;0;\n" +
//"2;pauw;paud_1.wav;1;Target-only;3 words;rolscheegt.wav;paud_2.wav;staap.wav;;;;2;plus10db;0;\n" +
//"3;krik;grik_1.wav;1;Target-only;3 words;spank.wav;grik_2.wav;pintein.wav;;;;2;plus10db;0;\n" +
//"4;schelp;schess_1.wav;1;Target-only;3 words;rim.wav;schess_2.wav;werelglal.wav;;;;2;plus10db;0;\n" +
        assertEquals("vloer", words[0]);
        assertEquals("pauw", words[1]);
        assertEquals("krik", words[2]);
        assertEquals("schelp", words[3]);
    }


    /**
     * Test of getBandNumber method, of class Trial.
     */
    @Test
    public void testGetBandIndex() {
        System.out.println("getBandNumber");
        int testLength = 300;
        int[] bands = new int[testLength];
        for (int i = 0; i < testLength; i++) {
            bands[i] = this.instance.get(i).getBandIndex();
        }
        assertEquals(10, bands[0]);
        assertEquals(10, bands[1]);
        assertEquals(10, bands[2]);
        assertEquals(10, bands[3]);
    }
     @Test
    public void testGetBandLabel() {
        System.out.println("getBandLabel");
        int testLength = 4;
        String[] bands = new String[testLength];
        for (int i = 0; i < testLength; i++) {
            bands[i] = this.instance.get(i).getBandLabel();
        }
        assertEquals("plus10db", bands[0]);
        assertEquals("plus10db", bands[1]);
        assertEquals("plus10db", bands[2]);
        assertEquals("plus10db", bands[3]);
        
    }

    /**
     * Test of getTargetNonWord method, of class Trial.
     */
    @Ignore
    @Test
    public void testGetTargetNonWord() {
        System.out.println("getTargetNonWord");
        int testLength = 4;
        String[] targetNonWords = new String[testLength];
        for (int i = 0; i < testLength; i++) {
            targetNonWords[i] = this.instance.get(i).getTargetNonWord();
        }
        assertEquals("smoer", targetNonWords[0]);
        assertEquals("hers", targetNonWords[1]);
        assertEquals("fjon", targetNonWords[2]);
        assertEquals("lop", targetNonWords[3]);
    }

    /**
     * Test of getNumberOfSyllables method, of class Trial.
     */
     @Ignore
    @Test
    public void testGetNumberOfSyllables() {
        System.out.println("getNumberOfSyllables");
        int testLength=4;
        int[] syllabN = new int[testLength];
        for (int i = 0; i < testLength; i++) {
            syllabN[i] = this.instance.get(i).getNumberOfSyllables();
        }
        assertEquals(1, syllabN[0]);
        assertEquals(1, syllabN[1]);
        assertEquals(1, syllabN[2]);
        assertEquals(1, syllabN[3]);
    }

    /**
     * Test of getCondition method, of class Trial.
     */
     @Ignore
    @Test
    public void testGetCondition() {
        System.out.println("getCondition");
        int testLength=4;
        TrialCondition[] consitions = new TrialCondition[testLength];
        for (int i = 0; i < testLength; i++) {
            consitions[i] = this.instance.get(i).getCondition();
        }
        assertEquals(TrialCondition.TARGET_ONLY, consitions[0]);
        assertEquals(TrialCondition.TARGET_ONLY, consitions[1]);
        assertEquals(TrialCondition.TARGET_AND_FOIL, consitions[2]);
        assertEquals(TrialCondition.NO_TARGET, consitions[3]);
    }

    /**
     * Test of getTrialLength method, of class Trial.
     */
     @Ignore
    @Test
    public void testGetTrialLength() {
        System.out.println("getTrialLength");
        
        int testLength = 4;
        int[] trailL = new int[testLength];
        ArrayList<BookkeepingStimulus<AudioAsStimulus>>[] stimuli = new ArrayList[testLength];
     
        for (int i = 0; i < testLength; i++) {
            trailL[i] = this.instance.get(i).getTrialLength();
            stimuli[i] = this.instance.get(i).getStimuli();
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

   
    /**
     * Test of getStimuli method, of class Trial.
     */
     @Ignore
    @Test
    public void testGetStimuli() {
        System.out.println("getStimuli");
        Trial instance = null;
        ArrayList<BookkeepingStimulus<AudioAsStimulus>> expResult = null;
        ArrayList<BookkeepingStimulus<AudioAsStimulus>> result = instance.getStimuli();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Trial.
     */
     @Ignore
    @Test
    public void testGetId() {
        System.out.println("getId");
        Trial instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Trial.
     */
     @Ignore
    @Test
    public void testToString() {
        System.out.println("toString");
        Trial instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toObject method, of class Trial.
     */
     @Ignore
    @Test
    public void testToObject() throws Exception{
        System.out.println("toObject");
        String str = "";
        LinkedHashMap<String, AudioAsStimulus> hashedStimuli = null;
        Trial expResult = null;
        Trial result = Trial.toObject(str, hashedStimuli);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of map3ToString method, of class Trial.
     */
     @Ignore
    @Test
    public void testMap3ToString() {
        System.out.println("map3ToString");
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> map = null;
        String expResult = "";
        String result = Trial.map3ToString(map);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

     public ArrayList<Trial> parseTrialsInputCSVStringIntoTrialsArray(AudioStimuliFromString util, String string) throws Exception {
        System.out.println("parseTrialsInputCSVStringIntoTrialsArray");
        ArrayList<String> fileNameExtensions = new ArrayList<String>(1);
        fileNameExtensions.add("wav");
        HashMap<String, String> bandIndexing = new HashMap<String, String>();
        for (int i = 0; i < labelling.length; i++) {
            bandIndexing.put(labelling[i], (new Integer(i)).toString());
        }
        ArrayList<Trial> retVal = util.parseTrialsInputCSVStringIntoTrialsArray(string, fileNameExtensions, bandIndexing);
        return retVal;
    }
}
