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

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Main;
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
     * Test of getAll method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testinItialiseStimuliState() {
        System.out.println("initialiseStimuliState");
        String[] input = new String[0];
        try {
            AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
            instance.initialiseStimuliState("");
            int totalStimuli = instance.getTotalStimuli();
            assertTrue(totalStimuli > 0);
            System.out.println(totalStimuli);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage(), true);
        }
    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus() {
        System.out.println("getCurrentStimulus");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        Stimulus stimulus = instance.getCurrentStimulus();
        assertTrue(stimulus != null);
        String label = stimulus.getLabel();
        assertTrue(label != null);
        System.out.println("Label: " +label);
    }

    /**
     * Test of getCurrentStimulusIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusIndex() {
        System.out.println("getCurrentStimulusIndex");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        
        int result = instance.getCurrentStimulusIndex();
        assertEquals(1, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetTotalStimuli() {
        System.out.println("getTotalStimuli");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        int expResult = 0;
        int result = instance.getTotalStimuli();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus() {
        System.out.println("hasNextStimulus");
        int increment = 0;
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        boolean expResult = false;
        //boolean result = instance.hasNextStimulus(increment);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of nextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testNextStimulus() {
        System.out.println("nextStimulus");
        int increment = 0;
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        //instance.nextStimulus(increment);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentStimuliIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testSetCurrentStimuliIndex() {
        System.out.println("setCurrentStimuliIndex");
        int currentStimuliIndex = 0;
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        //instance.setCurrentStimuliIndex(currentStimuliIndex);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStimulusUniqueId method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusUniqueId() {
        System.out.println("getCurrentStimulusUniqueId");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        String expResult = "";
        //String result = instance.getCurrentStimulusUniqueId();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLoadedStimulusString method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetLoadedStimulusString() {
        System.out.println("getLoadedStimulusString");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        String expResult = "";
        //String result = instance.getLoadedStimulusString();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
