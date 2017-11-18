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

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
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
        
        ArrayList<ArrayList<AtomBookkeepingStimulus>> words = instance.getWords();
        assertEquals(Constants.NUMBER_OF_BANDS, words.size());
        for (int i=0; i<Constants.NUMBER_OF_BANDS; i++){
            assertEquals(Constants.WORDS_PER_BAND, words.get(i).size());
        }
        
        ArrayList<AtomBookkeepingStimulus> nonwords = instance.getNonwords();
        assertEquals(ConstantsNonWords.NONWORDS_ARRAY.length, nonwords.size());
        
        int expectedTotalsStimuli = Constants.NUMBER_OF_BANDS * Constants.WORDS_PER_BAND + ConstantsNonWords.NONWORDS_ARRAY.length;
        assertEquals(expectedTotalsStimuli, instance.getTotalStimuli());
        
        ArrayList<Integer> nonWordIndices = instance.getNonWordsIndices();
        int expectedWords = (Constants.NUMBER_OF_BANDS - Constants.START_BAND+1)*2;
        int expectedLength = (expectedWords * Constants.AVRERAGE_NON_WORD_POSITION)/(Constants.AVRERAGE_NON_WORD_POSITION -1);
        int expectedNonwords = expectedLength / Constants.AVRERAGE_NON_WORD_POSITION;
        assertEquals(expectedNonwords,nonWordIndices.size());
        
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
        instance.nextStimulus(0);
        int result = instance.getCurrentStimulusIndex();
        assertEquals(0, result);
    }
    
      /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus1() {
        System.out.println("getCurrentStimulus1");
        this.testGetCurrentStimulus();
        
    }
    
       /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus2() {
        System.out.println("getCurrentStimulus2");
        this.testGetCurrentStimulus();
        
    }
    
        /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus3() {
        System.out.println("getCurrentStimulus3");
        this.testGetCurrentStimulus();
        
    }
    
    private void testGetCurrentStimulus() {
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        assertEquals(1, instance.getResponseRecord().size());
        Stimulus stimulus = instance.getCurrentStimulus();
        assertTrue(stimulus != null);
        String label = stimulus.getLabel();
        assertTrue(label != null);
        System.out.println("Label: " + label);
        AtomBookkeepingStimulus bStimulus = instance.getResponseRecord().get(instance.getCurrentStimulusIndex());
        assertTrue(bStimulus.getIsUsed());
        int expectedBand = stimulus.getCorrectResponses().equals("word") ? Constants.START_BAND : -1;
        assertEquals(expectedBand, bStimulus.getBandNumber());
    }

    @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        Stimulus stimulus = instance.getCurrentStimulus();
        boolean result = instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        assertTrue(result);
        
        AtomBookkeepingStimulus bStimulus = instance.getResponseRecord().get(instance.getCurrentStimulusIndex());
        assertTrue(bStimulus.getCorrectness());
        
        boolean expectedResult = stimulus.getCorrectResponses().equals("word");
        assertEquals(expectedResult, bStimulus.getReaction());
        
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
        assertEquals(ConstantsNonWords.NONWORDS_ARRAY.length+Constants.NUMBER_OF_BANDS*Constants.WORDS_PER_BAND, totalStimuli);
    }


    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void getStimuliReport() throws Exception{
        System.out.println("getStimuliReport");
        AdVocAsStimuliProvider instance = this.testHasNextStimulus();
        Map<String,String> result= instance.getStimuliReport();
        Set<String>  keys = result.keySet();
        // 3 is the amount of headers (tables)
        assertTrue(keys.size()>3);
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
     public void testHasNextStimulus1() throws Exception{
         System.out.println("hasNextStimulus-1");
         this.testHasNextStimulus();
     }
     
      /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
     public void testHasNextStimulus2() throws Exception{
         System.out.println("hasNextStimulus-2");
         this.testHasNextStimulus();
     }
    
         /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
     public void testHasNextStimulus3() throws Exception{
         System.out.println("hasNextStimulus-3");
         this.testHasNextStimulus();
     }
    
    
    private AdVocAsStimuliProvider testHasNextStimulus() throws Exception{
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        boolean result = instance.hasNextStimulus(0);// does not depend on increment
        // but on internal state of the process
        assertTrue(result);
        instance.nextStimulus(0);
        
        //experiment 0, correct answer
        int ind1 = instance.getCurrentStimulusIndex();
        assertEquals(0, ind1);
        AdVocAsAtomStimulus stimulus = instance.getCurrentStimulus();
        
        instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        
        boolean result1 = instance.hasNextStimulus(0);
        assertTrue(result1);
        int expectedBand = stimulus.getCorrectResponses().equals("word") ? (Constants.START_BAND+1) : Constants.START_BAND;
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        
        instance.nextStimulus(0);
        
         //experiment 1, wrong answer, second chance must be given
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
        boolean result12 = instance.hasNextStimulus(0); 
        assertTrue(result12);
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        assertEquals(-1, instance.getBestFastTrackBand()); // stil on fast track, expecting the secind chance
        
        
        instance.nextStimulus(0); // give the second chance
        
        
        int ind3 = instance.getCurrentStimulusIndex();
        assertEquals(2, ind3);
        AdVocAsAtomStimulus stimulus3 = instance.getCurrentStimulus();
        String correctResponse3 = stimulus3.getCorrectResponses();
        String response3 = null;
        if (correctResponse3.equals("word")) {
            response3 = "nonword";
        }
        if (correctResponse3.equals("nonword")) {
            response3 = "word";
        }
        if (response3 == null) {
            throw new Exception("Wrong reaction");
        }
        
        boolean result3 = instance.hasNextStimulus(0); 
        assertTrue(result3); 
        // now current band represents the last cirrect band on the fast track
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        assertEquals(instance.getCurrentBandNumber(), instance.getBestFastTrackBand()); // stil on fast track, expecting the secind chance
        
        return instance;
        
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
        instance.nextStimulus(0);
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
     * Test of shiftFIFO method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testMostOftenVisitedBandBumberHelp() {
        System.out.println(" MostOftenVisitedBandBumberHelp");
        int[] visitCounter = {1, 3, 2, 3, 3, 3, 1};
        // indices {1,3,4,5}
        // ind = 1, indSym = 2
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        int currentIndex1=2; // at 2
        int bandNumber1 = instance.mostOftenVisitedBandBumberHelp(visitCounter, currentIndex1);
        assertEquals(4, bandNumber1);
        
        int currentIndex2=3; // at 3
        int bandNumber2 = instance.mostOftenVisitedBandBumberHelp(visitCounter, currentIndex2);
        assertEquals(4, bandNumber2); 
    }
   

}
