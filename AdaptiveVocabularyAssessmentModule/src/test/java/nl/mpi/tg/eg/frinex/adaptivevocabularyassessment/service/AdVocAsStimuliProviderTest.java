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
        int expectedBand = stimulus.getCorrectResponses().equals(Constants.WORD) ? Constants.START_BAND : -1;
        assertEquals(expectedBand, bStimulus.getBandNumber());
    }

    @Test
    public void testIsCorrectResponse() throws Exception{
        System.out.println("isCorrectResponse");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        
        //stimulus 1
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        Stimulus stimulus = instance.getCurrentStimulus();
        boolean result = instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        assertTrue(result);
        
        AtomBookkeepingStimulus bStimulus = instance.getResponseRecord().get(0);
        assertTrue(bStimulus.getCorrectness());
        
        boolean expectedReaction = stimulus.getCorrectResponses().equals(Constants.WORD);
        assertEquals(expectedReaction, bStimulus.getReaction());
        
        // stimulus 2
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        Stimulus stimulus2 = instance.getCurrentStimulus();
        // making worng response
        String response2 = Constants.NONWORD;
        if (stimulus2.getCorrectResponses().equals(Constants.NONWORD)){
            response2 = Constants.WORD;
        } else {
            if (!stimulus2.getCorrectResponses().equals(Constants.WORD)){
               throw new Exception("The reaction is neither nonword nor word, something went terribly worng.") ;
            }
        }
        boolean result2 = instance.isCorrectResponse(stimulus2, response2);
        assertFalse(result2);
        
        AtomBookkeepingStimulus bStimulus2 = instance.getResponseRecord().get(1);
        assertFalse(bStimulus2.getCorrectness());
        
        boolean expectedCorrectReaction2 = stimulus2.getCorrectResponses().equals(Constants.WORD);
        assertEquals(!expectedCorrectReaction2, bStimulus2.getReaction());
        
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
        
        Map<String,String> result= instance.getStimuliReport("user_summary");
        Set<String>  keys = result.keySet();
        // header + data
        assertTrue(keys.size() == 2);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index>-1);
        }
        
        result= instance.getStimuliReport("fast_track");
        keys = result.keySet();
        // header + data
        assertTrue(keys.size()>2);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index>-1);
        }
        
        result= instance.getStimuliReport("fine_tuning");
        keys = result.keySet();
        // header + data
        assertTrue(keys.size()>=1);
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
    
     // also tests nextStimulus
    
    private AdVocAsStimuliProvider testHasNextStimulus() throws Exception{
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        boolean result = instance.hasNextStimulus(0);// does not depend on increment
        int invariant = instance.getResponseRecord().size() + instance.getNonwords().size()+this.getListOfListLength(instance.getWords());
        
        // but on internal state of the process
        assertTrue(result);
         
        instance.nextStimulus(0);
        assertEquals(1,instance.getResponseRecord().size());
        int invariant1 = instance.getResponseRecord().size() + instance.getNonwords().size()+this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant1);
        
        //experiment 0, correct answer
        int ind1 = instance.getCurrentStimulusIndex();
        assertEquals(0, ind1);
        AdVocAsAtomStimulus stimulus = instance.getCurrentStimulus();
        
        instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        
        boolean result1 = instance.hasNextStimulus(0);
        assertTrue(result1);
        int expectedBand = stimulus.getCorrectResponses().equals(Constants.WORD) ? (Constants.START_BAND+1) : Constants.START_BAND;
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        
        instance.nextStimulus(0);
        assertEquals(2,instance.getResponseRecord().size());
        int invariant2 = instance.getResponseRecord().size() + instance.getNonwords().size()+this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant2);
        
         //experiment 1, wrong answer, second chance must be given
        int ind2 = instance.getCurrentStimulusIndex();
        assertEquals(1, ind2);
        
        AdVocAsAtomStimulus stimulus2 = instance.getCurrentStimulus();
        String correctResponse = stimulus2.getCorrectResponses();
        String response = null;
        if (correctResponse.equals(Constants.WORD)) {
            response = Constants.NONWORD;
        }
        if (correctResponse.equals(Constants.NONWORD)) {
            response = Constants.WORD;
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
        assertEquals(3,instance.getResponseRecord().size());
        int invariant3 = instance.getResponseRecord().size() + instance.getNonwords().size()+this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant3);
        
        int ind3 = instance.getCurrentStimulusIndex();
        assertEquals(2, ind3);
        AdVocAsAtomStimulus stimulus3 = instance.getCurrentStimulus();
        String correctResponse3 = stimulus3.getCorrectResponses();
        String response3 = null;
        if (correctResponse3.equals(Constants.WORD)) {
            response3 = Constants.NONWORD;
        }
        if (correctResponse3.equals(Constants.NONWORD)) {
            response3 = Constants.WORD;
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
        int bandNumber1 = instance.mostOftenVisitedBandNumber(visitCounter, currentIndex1);
        assertEquals(4, bandNumber1);
        
        int currentIndex2=3; // at 3
        int bandNumber2 = instance.mostOftenVisitedBandNumber(visitCounter, currentIndex2);
        assertEquals(4, bandNumber2); 
    }
   
    private int getListOfListLength(ArrayList<ArrayList<AtomBookkeepingStimulus>> ll) {
        int retVal = 0;
        for (ArrayList<AtomBookkeepingStimulus> l : ll) {
            retVal += l.size();
        }
        return retVal;
    }

}
