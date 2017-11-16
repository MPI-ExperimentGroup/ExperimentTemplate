/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.FastTrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class FastTrackTest {
    
    public FastTrackTest() {
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
     * Test of getStartBand method, of class FastTrack.
     */
    @Test
    public void testGetStartBand() {
        System.out.println("getStartBand");
        //FastTrack instance = null;
        int expResult = 0;
        //int result = instance.getStartBand();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of createStimulae method, of class FastTrack.
     */
    @Test
    public void testCreateStimulae() {
        System.out.println("createStimulae");
        // test fails if the repetitis are discovered
      
        Vocabulary vocab = new Vocabulary();
        AtomBookkeepingStimulus[][] words = vocab.initialiseWords(ConstantsWords.WORDS);
        ArrayList<AdVocAsAtomStimulus> nonwordstmp = new ArrayList<>();
        nonwordstmp.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));
        ArrayList<AtomBookkeepingStimulus> nonwords = vocab.initialiseNonwords(nonwordstmp);
        
       
        FastTrack fastTrack = new FastTrack(words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
        fastTrack.createStimulae();
        ArrayList<AtomBookkeepingStimulus> stimuli = fastTrack.getBookeepingStimuli();
        ArrayList<String> spellings = new ArrayList<>(stimuli.size());
        for (AtomBookkeepingStimulus stimulus : stimuli){
            spellings.add(stimulus.getSpelling());
        }
        HashSet<String> set = new HashSet(spellings);
        assertEquals(set.size(), spellings.size());
        
        // checking if the Equality is implemented OK on Strings
        ArrayList<String> testEqualityList = new ArrayList<>(2);
        testEqualityList.add("rhabarber");
        testEqualityList.add("rhabarber");
        assertEquals(2, testEqualityList.size());
        HashSet<String> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());
        
        
    }

    /**
     * Test of runStep method, of class FastTrack.
     */
    @Test
    public void testRunStep() {
        System.out.println("runStep");
        int stimulusNumber = 0;
        boolean answer = false;
        FastTrack instance = null;
        boolean expResult = false;
        //boolean result = instance.runStep(stimulusNumber, answer);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getStimulae method, of class FastTrack.
     */
    @Test
    public void testGetStimulae() {
        System.out.println("getStimulae");
        FastTrack instance = null;
        ArrayList<AtomBookkeepingStimulus> expResult = null;
        //ArrayList<AtomStimulus> result = instance.getStimulae();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
    
}
