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

import utils.VocabularyFromFiles;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class VocabularyFromFilesTest {
    
    final String NONWORD_FILE_LOCATION = "2.selection_words_nonwords.csv";
    
    public VocabularyFromFilesTest() {
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
     * Test of parseWordInputCSV method, of class VocabularyFromFiles.
     */
    @Test
    public void testParseWordInputCSV() throws Exception {
        System.out.println("parseWordInputCSV");
        VocabularyFromFiles instance = new VocabularyFromFiles();
        //instance.parseWordInputCSV("dummylocation");
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of parseNonwordInputCSV method, of class VocabularyFromFiles.
     */
    @Test
    public void testParseNonwordInputCSV() throws Exception {
        System.out.println("parseNonwordInputCSV");
        VocabularyFromFiles.parseNonwordInputCSV(NONWORD_FILE_LOCATION);
        ArrayList<AdVocAsStimulus> nonwords = VocabularyFromFiles.getNonwords();
        StringBuilder stBuilder = new StringBuilder("[");
        for (AdVocAsStimulus nonword: nonwords) {
            stBuilder.append("'").append(nonword.getLabel()).append("', ");
        }
        stBuilder .append("]");
        System.out.println(stBuilder);
    }

    /**
     * Test of getWords method, of class VocabularyFromFiles.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        AdVocAsStimulus[][] result = VocabularyFromFiles.getWords();
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getNonwords method, of class VocabularyFromFiles.
     */
    @Test
    public void testGetNonwords() {
        System.out.println("getNonwords");
        ArrayList<AdVocAsStimulus> result = VocabularyFromFiles.getNonwords();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of initialiseVocabulary method, of class VocabularyFromFiles.
     */
    @Test
    public void testInitialiseVocabulary() throws Exception {
        System.out.println("initialiseVocabulary");
        //instance.initialiseVocabulary("dummylocation", "dummylocation");
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
    
}
