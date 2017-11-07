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
    public void testGetAll() {
        System.out.println("getAll");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.getAll();
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus() {
        System.out.println("getCurrentStimulus");
        //AdVocAssStimuliProvider instance = new AdVocAsStimuliProvider();
        Stimulus expResult = null;
        //Stimulus result = instance.getCurrentStimulus();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStimulusIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusIndex() {
        System.out.println("getCurrentStimulusIndex");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        int expResult = 0;
        int result = instance.getCurrentStimulusIndex();
        //assertEquals(expResult, result);
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
     * Test of getCurrentStimulusUniqueId method, of class AdVocAsStimuliProvider.
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
    
}
