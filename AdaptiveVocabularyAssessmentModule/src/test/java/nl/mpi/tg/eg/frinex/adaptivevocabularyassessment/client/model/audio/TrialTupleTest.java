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
import java.util.Map;
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
public class TrialTupleTest {
    
    public TrialTupleTest() {
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
     * Test of removeFirstAvailableStimulus method, of class TrialTuple.
     */
    @Ignore
    @Test
    public void testRemoveFirstAvailableStimulus() {
        System.out.println("removeFirstAvailableStimulus");
        TrialTuple instance = null;
        AudioAsStimulus expResult = null;
        AudioAsStimulus result = instance.removeFirstAvailableStimulus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorrectness method, of class TrialTuple.
     */
    @Ignore
    @Test
    public void testGetCorrectness() {
        System.out.println("getCorrectness");
        TrialTuple instance = null;
        Boolean expResult = null;
        Boolean result = instance.getCorrectness();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorrectness method, of class TrialTuple.
     */
    @Ignore
    @Test
    public void testSetCorrectness() {
        System.out.println("setCorrectness");
        boolean correct = false;
        TrialTuple instance = null;
        instance.setCorrectness(correct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNotEmpty method, of class TrialTuple.
     */
    @Ignore
    @Test
    public void testIsNotEmpty() {
        System.out.println("isNotEmpty");
        TrialTuple instance = null;
        boolean expResult = false;
        boolean result = instance.isNotEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTuple method, of class TrialTuple.
     */
    @Ignore
    @Test
    public void testCreateTuple() {
        System.out.println("createTuple");
        ArrayList<PermutationPair> availablePermutations = null;
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trialMatrix = null;
        int size = 0;
        int bandIndex = 0;
        TrialTuple expResult = null;
        TrialTuple result = TrialTuple.createTuple(availablePermutations, trialMatrix, size, bandIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
