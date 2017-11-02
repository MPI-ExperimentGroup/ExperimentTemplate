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
public class AtomStimulusTest {

    public AtomStimulusTest() {
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
     * Test of getSpelling method, of class AtomStimulus.
     */
    @Test
    public void testGetSpelling() {
        System.out.println("getSpelling");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        String expResult = "test-spelling";
        String result = instance.getSpelling();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsUsed method, of class AtomStimulus.
     */
    @Test
    public void testGetIsUsed() {
        System.out.println("getIsUsed");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        boolean result = instance.getIsUsed();
        assertEquals(false, result);
    }

    /**
     * Test of getBandNumber method, of class AtomStimulus.
     */
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        int result = instance.getBandNumber();
        assertEquals(37, result);
    }

    /**
     * Test of setIsUsed method, of class AtomStimulus.
     */
    @Test
    public void testSetIsUsed() {
        System.out.println("setIsUsed");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        instance.setIsUsed(true);
        boolean result = instance.getIsUsed();
        assertEquals(true, result);
    }

    /**
     * Test of getReaction method, of class AtomStimulus.
     */
    @Test
    public void testGetReaction() {
        System.out.println("getReaction");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        Boolean result = instance.getReaction();
        assertEquals(null, result);
    }

    /**
     * Test of getCorrectness method, of class AtomStimulus.
     */
    @Test
    public void testGetCorrectness() {
        System.out.println("getCorrectness");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        Boolean result = instance.getCorrectness();
        assertEquals(null, result);
    }

    /**
     * Test of setReaction method, of class AtomStimulus.
     */
    @Test
    public void testSetReaction() {
        System.out.println("setReaction");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        instance.setReaction(false);
        Boolean result = instance.getReaction();
        assertEquals(false, result);
    }

    /**
     * Test of setCorrectness method, of class AtomStimulus.
     */
    @Test
    public void testSetCorrectness() {
        System.out.println("setCorrectness");
        AtomStimulus instance = new AtomStimulus("test-spelling", 37);
        instance.setCorrectness(true);
        Boolean result = instance.getCorrectness();
        assertEquals(true, result);
    }

    /**
     * Test of copyAtomStimulae method, of class AtomStimulus.
     */
    @Test
    public void testCopyAtomStimulae() {
        System.out.println("copyAtomStimulae");
        ArrayList<AtomStimulus> source = new ArrayList<>();
        AtomStimulus st1 = new AtomStimulus("test-spelling-1", 37);
        AtomStimulus st2 = new AtomStimulus("test-spelling-2", 39);
        source.add(st1);
        source.add(st2);
        ArrayList<AtomStimulus> expResult = new ArrayList<>();
        AtomStimulus st3 = new AtomStimulus(st1);
        AtomStimulus st4 = new AtomStimulus(st2);
        expResult.add(st3);
        expResult.add(st4);
        ArrayList<AtomStimulus> result = AtomStimulus.copyAtomStimulae(source);
        for (int i = 0; i < 2; i++) {
            assertNotEquals(expResult.get(i), result.get(i));
            assertEquals(expResult.get(i).getSpelling(), result.get(i).getSpelling());
            assertEquals(expResult.get(i).getBandNumber(), result.get(i).getBandNumber());
        }

    }

}
