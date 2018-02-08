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
public class AudioUtilsTest {
    
    public AudioUtilsTest() {
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
     * Test of initialiseAvailabilityList method, of class AudioUtils.
     */
    @Ignore
    @Test
    public void testInitialiseAvailabilityList() {
        System.out.println("initialiseAvailabilityList");
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trials = null;
        ArrayList<ArrayList<Integer>> lengthPermuations = null;
        ArrayList<ArrayList<TrialCondition>> trialTypePermutations = null;
        int bandNumber = 0;
        int tupleSize = 0;
        ArrayList<PermutationPair> expResult = null;
        ArrayList<PermutationPair> result = AudioUtils.initialiseAvailabilityList(trials, lengthPermuations, trialTypePermutations, bandNumber, tupleSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialiseTrials method, of class AudioUtils.
     */
    @Ignore
    @Test
    public void testInitialiseTrials() {
        System.out.println("initialiseTrials");
        String[] rows = null;
        int numberOfBands = 0;
        int maxLength = 0;
        String dirName = "";
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> expResult = null;
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> result = AudioUtils.initialiseTrials(rows, numberOfBands, maxLength, dirName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emptiedPossibilities method, of class AudioUtils.
     */
    @Ignore
    @Test
    public void testEmptiedPossibilities() {
        System.out.println("emptiedPossibilities");
        ArrayList<PermutationPair> oldList = null;
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trialMatrix = null;
        int bandIndex = 0;
        int tupleSize = 0;
        ArrayList<PermutationPair> expResult = null;
        ArrayList<PermutationPair> result = AudioUtils.emptiedPossibilities(oldList, trialMatrix, bandIndex, tupleSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
