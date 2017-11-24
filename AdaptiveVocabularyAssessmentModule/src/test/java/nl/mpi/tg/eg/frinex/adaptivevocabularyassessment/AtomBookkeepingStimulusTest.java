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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
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
public class AtomBookkeepingStimulusTest {

    public AtomBookkeepingStimulusTest() {
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
     * Test of getSpelling method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testGetSpelling() {
        System.out.println("getSpelling");
        //AdVocAsAtomStimulus(String uniqueId, String label, String correctResponses, int bandNumber)
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        String expResult = "test-spelling";
        String result = instance.getSpelling();
        assertEquals(expResult, result);
    }

  
    /**
     * Test of getBandNumber method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        int result = instance.getBandNumber();
        assertEquals(37, result);
    }

  
    /**
     * Test of getReaction method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testGetReaction() {
        System.out.println("getReaction");
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        Boolean result = instance.getReaction();
        assertEquals(null, result);
    }

    /**
     * Test of getCorrectness method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testGetCorrectness() {
        System.out.println("getCorrectness");
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        Boolean result = instance.getCorrectness();
        assertEquals(null, result);
    }

    /**
     * Test of setReaction method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testSetReaction() {
        System.out.println("setReaction");
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        instance.setReaction(false);
        Boolean result = instance.getReaction();
        assertEquals(false, result);
    }

    /**
     * Test of setCorrectness method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testSetCorrectness() {
        System.out.println("setCorrectness");
        AdVocAsAtomStimulus pureStimulus = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling", Constants.WORD, 37);
        
        AtomBookkeepingStimulus instance = new AtomBookkeepingStimulus(pureStimulus);
        instance.setCorrectness(true);
        Boolean result = instance.getCorrectness();
        assertEquals(true, result);
    }

    /**
     * Test of copyAtomStimulae method, of class AtomBookkeepingStimulus.
     */
    @Test
    public void testCopyAtomStimulae() {
        System.out.println("copyAtomStimulae");
        ArrayList<AtomBookkeepingStimulus> source = new ArrayList<>();
        AdVocAsAtomStimulus pureStimulus1 = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling-1", Constants.WORD, 37);
        AtomBookkeepingStimulus st1 = new AtomBookkeepingStimulus(pureStimulus1);
        AdVocAsAtomStimulus pureStimulus2 = new AdVocAsAtomStimulus("test_"+System.currentTimeMillis(), "test-spelling-2", Constants.WORD, 39);
        AtomBookkeepingStimulus st2 = new AtomBookkeepingStimulus(pureStimulus2);
        source.add(st1);
        source.add(st2);
        ArrayList<AtomBookkeepingStimulus> expResult = new ArrayList<>();
        AtomBookkeepingStimulus st3 = new AtomBookkeepingStimulus(st1);
        AtomBookkeepingStimulus st4 = new AtomBookkeepingStimulus(st2);
        expResult.add(st3);
        expResult.add(st4);
        ArrayList<AtomBookkeepingStimulus> result = AtomBookkeepingStimulus.copyAtomStimulae(source);
        for (int i = 0; i < 2; i++) {
            assertNotEquals(expResult.get(i), result.get(i));
            assertEquals(expResult.get(i).getSpelling(), result.get(i).getSpelling());
            assertEquals(expResult.get(i).getBandNumber(), result.get(i).getBandNumber());
        }

    }

}
