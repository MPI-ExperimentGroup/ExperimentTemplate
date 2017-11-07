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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning;

import java.util.ArrayList;
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
public class FineTuningTest {
    
    public FineTuningTest() {
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
     * Test of createStimulae method, of class FineTuning.
     */
    @Test
    public void testCreateStimulae() throws Exception {
        System.out.println("createStimulae");
        //FineTuning instance = null;
        //instance.createStimulae();
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of runStep method, of class FineTuning.
     */
    @Test
    public void testRunStep() throws Exception {
        System.out.println("runStep");
        int bandNumber = 0;
        int stimulusIndex = 0;
        boolean[] answers = null;
        //FineTuning instance = null;
        boolean expResult = false;
        //boolean result = instance.runStep(bandNumber, stimulusIndex, answers);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getStimulus method, of class FineTuning.
     */
    @Test
    public void testGetStimulus() {
        System.out.println("getStimulus");
        int bandNumber = 0;
        int stimulusIndex = 0;
        //FineTuning instance = null;
        //FineTuningStimulus expResult = null;
        //FineTuningStimulus result = instance.getStimulus(bandNumber, stimulusIndex);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getStimulae method, of class FineTuning.
     */
    @Test
    public void testGetStimulae() {
        System.out.println("getStimulae");
        //FineTuning instance = null;
        ArrayList<ArrayList<FineTuningBookkeepingStimulus>> expResult = null;
        //ArrayList<ArrayList<FineTuningStimulus>> result = instance.getStimulae();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of testProbabilisticAnswerer method, of class FineTuning.
     */
    @Test
    public void testTestProbabilisticAnswerer() {
        System.out.println("testProbabilisticAnswerer");
        int bandNumber = 0;
        int stimulusInBandNummer = 0;
        double correctnessUpperBound = 0.0;
        //FineTuning instance = null;
        boolean[] expResult = null;
        //boolean[] result = instance.testProbabilisticAnswerer(bandNumber, stimulusInBandNummer, correctnessUpperBound);
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
    
}
