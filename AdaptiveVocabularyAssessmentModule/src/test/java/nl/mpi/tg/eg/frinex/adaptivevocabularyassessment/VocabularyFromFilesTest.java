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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class VocabularyFromFilesTest {

    //final String NONWORD_FILE_LOCATION = "2.selection_words_nonwords.csv";
    final String NONWORD_FILE_LOCATION = "nonwords_selection_2.csv";
    final String WORD_FILE_LOCATION = "words_selection_2.csv";
    final int numberOfBands = 54;
    final int wordsPerBand = 40;
    final int numberOfSeries = 2;

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
    @Ignore
    @Test
    public void testParseWordInputCSV() throws Exception {
        System.out.println("parseWordInputCSV");

        // VocabularyFromFiles(int numberOfBands, int wordsPerBand, int numberOfSeries)
        VocabularyFromFiles instance = new VocabularyFromFiles(this.numberOfBands, this.wordsPerBand, this.numberOfSeries);

        instance.parseWordInputCSV(WORD_FILE_LOCATION);
        AdVocAsStimulus[][] words = instance.getWords();
        StringBuilder stBuilder = new StringBuilder("{");
        for (AdVocAsStimulus[] wordband : words) {
            stBuilder.append("\n{\n");
            for (AdVocAsStimulus word : wordband) {
                String id = word.getUniqueId();
                String spelling = word.getLabel();
                int band = word.getBandNumber();
                String serialisedDescr = "new AdVocAsStimulus(\"" + id + "\", \"" + spelling + "\", \"" + Vocabulary.WORD + "\" " + "," + band + ")";
                stBuilder.append(serialisedDescr).append(",\n");
            }
            stBuilder.append("}\n,");
        }
        stBuilder.append("}");
        System.out.println(stBuilder);
    }

    /**
     * Test of parseNonwordInputCSV method, of class VocabularyFromFiles.
     */
    @Ignore
    @Test
    public void testParseNonwordInputCSV() throws Exception {
        System.out.println("parseNonwordInputCSV");
        VocabularyFromFiles instance = new VocabularyFromFiles(this.numberOfBands, this.wordsPerBand, this.numberOfSeries);
        instance.parseNonwordInputCSV(NONWORD_FILE_LOCATION);
        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        StringBuilder stBuilder = new StringBuilder("[");
        for (AdVocAsStimulus nonword : nonwords) {
            stBuilder.append("'").append(nonword.getLabel()).append("', ");
        }
        stBuilder.append("]");
        System.out.println(stBuilder);
    }

    /**
     * Test of getWords method, of class VocabularyFromFiles.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        VocabularyFromFiles instance = new VocabularyFromFiles(this.numberOfBands, this.wordsPerBand, this.numberOfSeries);
        AdVocAsStimulus[][] result = instance.getWords();
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
        VocabularyFromFiles instance = new VocabularyFromFiles(this.numberOfBands, this.wordsPerBand, this.numberOfSeries);
        ArrayList<AdVocAsStimulus> result = instance.getNonwords();
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
