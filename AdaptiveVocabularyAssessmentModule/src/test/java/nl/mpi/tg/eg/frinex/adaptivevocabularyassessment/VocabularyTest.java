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
public class VocabularyTest {
    
    public VocabularyTest() {
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
     * Test of parseWordInputCSV method, of class Vocabulary.
     */
    @Test
    public void testParseWordInputCSV() throws Exception {
        System.out.println("parseWordInputCSV");
        Vocabulary instance = new Vocabulary();
        //instance.parseWordInputCSV("dummylocation");
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of parseNonwordInputCSV method, of class Vocabulary.
     */
    @Test
    public void testParseNonwordInputCSV() throws Exception {
        System.out.println("parseNonwordInputCSV");
        Vocabulary instance = new Vocabulary();
        //instance.parseNonwordInputCSV("dummylocation");
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getWords method, of class Vocabulary.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        Vocabulary instance = new Vocabulary();
        AtomStimulus[][] expResult = null;
        AtomStimulus[][] result = instance.getWords();
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getNonwords method, of class Vocabulary.
     */
    @Test
    public void testGetNonwords() {
        System.out.println("getNonwords");
        Vocabulary instance = new Vocabulary();
        ArrayList<AtomStimulus> expResult = null;
        ArrayList<AtomStimulus> result = instance.getNonwords();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of initialiseVocabulary method, of class Vocabulary.
     */
    @Test
    public void testInitialiseVocabulary() throws Exception {
        System.out.println("initialiseVocabulary");
        Vocabulary instance = new Vocabulary();
        //instance.initialiseVocabulary("dummylocation", "dummylocation");
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
    
}
