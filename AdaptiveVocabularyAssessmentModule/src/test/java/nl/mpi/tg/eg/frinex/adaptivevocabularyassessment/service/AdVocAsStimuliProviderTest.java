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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.AdVocAsStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
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
public class AdVocAsStimuliProviderTest {

    public AdVocAsStimuliProviderTest() {
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
     * Test of estinItialiseStimuliState method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testItialiseStimuliState() {
        System.out.println("initialiseStimuliState");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        int totalStimuli = instance.getTotalStimuli();
        int minPreparedFineTuning = (Constants.FINE_TUNING_MAX_BAND_CHANGE+1)*Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE;
        int minPreparedFastTrack = Constants.NUMBER_OF_BANDS - Constants.START_BAND+1;
        int minAmountOfPreparedExperiments = minPreparedFastTrack + minPreparedFineTuning;
        assertTrue(totalStimuli >  minAmountOfPreparedExperiments);
        System.out.println(totalStimuli);
    }
    
    
   
    /**
     * Test of getCurrentStimulusIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusIndex() {
        System.out.println("getCurrentStimulusIndex");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        int result = instance.getCurrentStimulusIndex();
        assertEquals(0, result);
    }
    
      /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus() {
        System.out.println("getCurrentStimulus");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        Stimulus stimulus = instance.getCurrentStimulus();
        assertTrue(stimulus != null);
        String label = stimulus.getLabel();
        assertTrue(label != null);
        System.out.println("Label: " + label);
    }

    @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        Stimulus stimulus = instance.getCurrentStimulus();
        boolean result = instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        assertTrue(result);
    }

  
    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetTotalStimuli() {
        System.out.println("getTotalStimuli");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        int totalStimuli = instance.getTotalStimuli();
        assertTrue(totalStimuli > 0);
        System.out.println(totalStimuli);
    }


    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void getStimuliReport() {
        System.out.println("getStimuliReport");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        Map<String,String> result= instance.getStimuliReport();
        Set<String>  keys = result.keySet();
        // 4 is the amount of headers
        assertTrue(keys.size()>4);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index>-1);
        }
    }

    /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus() throws Exception{
        System.out.println("hasNextStimulus");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        boolean result = instance.hasNextStimulus(0);// does not depend on increment
        // but on internal state of the process
        assertTrue(result);

        // 1 step, correct
        
        int ind1 = instance.getCurrentStimulusIndex();
        assertEquals(0, ind1);
        AdVocAsAtomStimulus stimulus = instance.getCurrentStimulus();
        instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        boolean result1 = instance.hasNextStimulus(0);
        assertTrue(result1);

        // 2nd step, incorrect
        // anyway go the the fine tuning
        int ind2 = instance.getCurrentStimulusIndex();
        assertEquals(1, ind2);
        
        AdVocAsAtomStimulus stimulus2 = instance.getCurrentStimulus();
        String correctResponse = stimulus2.getCorrectResponses();
        String response = null;
        if (correctResponse.equals("word")) {
            response = "nonword";
        }
        if (correctResponse.equals("nonword")) {
            response = "word";
        }
        if (response == null) {
            throw new Exception("Wrong reaction");
        }
        instance.isCorrectResponse(stimulus2, response);
        
        boolean result12 = instance.hasNextStimulus(0); // trasnfer to fine tuning
        
        int ind3 = instance.getCurrentStimulusIndex();
        assertEquals(2, ind3);
        
        assertTrue(result12);
    }

 
    /**
     * Test of setCurrentStimuliIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testSetCurrentStimuliIndex() {
        System.out.println("setCurrentStimuliIndex");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.setCurrentStimuliIndex(0);// actually does nothing
    }

    /**
     * Test of getCurrentStimulusUniqueId method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusUniqueId() {
        System.out.println("getCurrentStimulusUniqueId");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        String result = instance.getCurrentStimulusUniqueId();
        assertTrue(result != null);
    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testDetectLoop() {
        System.out.println("detectLoop");
        int[] arr1 = {42, 43, 42, 43, 42, 43, 42};
        boolean result1 = AdVocAsStimuliProvider.detectLoop(arr1);
        assertEquals(true, result1);
        int[] arr2 = {42, 43, 42, 43, 42, 43, 45};
        boolean result2 = AdVocAsStimuliProvider.detectLoop(arr2);
        assertEquals(false, result2);
        int[] arr3 = {43, 42, 43, 42, 43, 42, 45, 42};
        boolean result3 = AdVocAsStimuliProvider.detectLoop(arr3);
        assertEquals(false, result3);
    }

    /**
     * Test of shiftFIFO method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testShiftFIFO() {
        System.out.println("shiftFIFO");
        int[] fifo = {0, 1, 2, 3, 4, 5, 6};
        int newelement = 7;
        AdVocAsStimuliProvider.shiftFIFO(fifo, newelement);
        for (int i = 0; i < 7; i++) {
            assertEquals(i + 1, fifo[i]);
        }
    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testTimeToCountVisits() {
        System.out.println("timeToCountVisits");
        boolean result1 = AdVocAsStimuliProvider.timeToCountVisits(34);
        assertEquals(true, result1);
        boolean result2 = AdVocAsStimuliProvider.timeToCountVisits(32);
        assertEquals(false, result2);

    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testMostOftenVisited() {
        System.out.println("mostOftenVisited");
        int[] arr1 = {1, 1, 2, 2, 2, 2, 1};
        int result1 = AdVocAsStimuliProvider.mostOftenVisited(arr1);
        assertEquals(3 + 1, result1); // output is the band nummer
        // and bund nemmer is band index+1
        int[] arr2 = {0, 1, 2, 2, 2, 1, 1};
        // 2 or 3 ?
        // 0+1+2 vs 2+1+1
        // 3
        // 3 or 4
        //0+1+2+2 vs 1+1
        //3
        int result2 = AdVocAsStimuliProvider.mostOftenVisited(arr2);
        assertEquals(3 + 1, result2);
    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testChooseBand() {
        System.out.println("mostChooseBAnd");
        int[] arr1 = {1, 1, 2, 2, 2, 2, 1};
        int result1 = AdVocAsStimuliProvider.chooseBand(2, 3, arr1);
        // 1+1+2 vs 2+2+1
        assertEquals(3, result1);
        int result2 = AdVocAsStimuliProvider.chooseBand(3, 4, arr1);
        // 1+1+2+2 vs 2+1
        assertEquals(3, result2);
    }

}
