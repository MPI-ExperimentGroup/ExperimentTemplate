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
package utils;

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
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

    //final String NONWORD_FILE_LOCATION_NL = "2.selection_words_nonwords.csv";
    final String NONWORD_FILE_LOCATION_NL = "nonwords_selection_2.csv";
    final String WORD_FILE_LOCATION_NL = "words_selection_2.csv";
    final String NONWORD_FILE_LOCATION_EN = "english_nonwords.csv";
    final String WORD_FILE_LOCATION_EN = "english_words.csv";
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
    private void testParseWordInputCSV(String fileLocation, String bandColumn, String wordColumn, String correctResponse, int numberOfBands) throws Exception {

        // VocabularyFromFiles(int numberOfBands, int wordsPerBand, int numberOfSeries)
        VocabularyFromFiles instance = new VocabularyFromFiles(numberOfBands, this.wordsPerBand, this.numberOfSeries);

        instance.parseWordInputCSV(fileLocation, bandColumn, wordColumn, correctResponse);
        ArrayList<ArrayList<AdVocAsStimulus>> words= instance.getWords();
        StringBuilder stBuilder = new StringBuilder("{");
        for (ArrayList<AdVocAsStimulus> wordband : words) {
            this.printStimuli(wordband, 0, wordband.size());
        }
        stBuilder.append("},\n");
        System.out.println(stBuilder);
    }


    private void printStimuli(ArrayList<AdVocAsStimulus> stimuli, int from, int uptoExcl) {
        System.out.println("\n{");
        
        for (int i=from; i<uptoExcl; i++) {
            AdVocAsStimulus stimulus =stimuli.get(i);
            String id = stimulus.getUniqueId();
            String spelling = stimulus.getLabel();
            String correctResponse = stimulus.getCorrectResponses();
            String bandNumber = stimulus.getbandLabel();
            System.out.println("new AdVocAsStimulus(\"" + id + "\", \"" + spelling + "\", \"" + correctResponse + "\" " + ","+bandNumber+"),");
        }
        
        System.out.println("},\n");
    }

    @Ignore
    @Test
    public void testParseWordInputCSV_NL() throws Exception {
        System.out.println("parseWordInputCSV NL");
        this.testParseWordInputCSV(WORD_FILE_LOCATION_NL, "Band", "spelling", Vocabulary.WORD_NL, 54);
    }

    @Ignore
    @Test
    public void testParseNonWordInputCSV_NL() throws Exception {
        System.out.println("parseNonWordInputCSV NL");
        VocabularyFromFiles instance = new VocabularyFromFiles(54, this.wordsPerBand, this.numberOfSeries);
        instance.parseNonwordInputCSV(NONWORD_FILE_LOCATION_NL, "spelling", Vocabulary.NONWORD_NL);
        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        this.printStimuli(nonwords, 0, nonwords.size());
    }

   @Ignore
    @Test
    public void testParseWordInputCSV_EN_A() throws Exception {
        System.out.println("parseWordInputCSV EN");
        this.testParseWordInputCSV(WORD_FILE_LOCATION_EN, "Band", "List A", Vocabulary.WORD_EN, 62);
    }

    @Test
    public void testParseWordInputCSV_EN_B() throws Exception {
        System.out.println("parseWordInputCSV EN");
        this.testParseWordInputCSV(WORD_FILE_LOCATION_EN, "Band", "List B",  Vocabulary.WORD_EN, 62);
    }

    @Ignore
    @Test
    public void testParseNonWordInputCSV_EN_1() throws Exception {
        System.out.println("parseNonWordInputCSV EN");
        VocabularyFromFiles instance = new VocabularyFromFiles(62, this.wordsPerBand, this.numberOfSeries);
        instance.parseNonwordInputCSV(NONWORD_FILE_LOCATION_EN, "spelling",  Vocabulary.NONWORD_EN);
        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        int limit = nonwords.size()/2;
        System.out.println("*******************");
        System.out.println(" Engl nonwords 1");
        System.out.println("*******************");
        this.printStimuli(nonwords, 0, limit);
        
    }
    
    @Ignore
   @Test
    public void testParseNonWordInputCSV_EN_2() throws Exception {
        System.out.println("parseNonWordInputCSV EN");
        VocabularyFromFiles instance = new VocabularyFromFiles(62, this.wordsPerBand, this.numberOfSeries);
        instance.parseNonwordInputCSV(NONWORD_FILE_LOCATION_EN, "spelling",  Vocabulary.NONWORD_EN);
        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        int limit = nonwords.size()/2;
        System.out.println("*******************");
        System.out.println(" Engl nonwords 2");
        System.out.println("*******************");
        this.printStimuli(nonwords, limit, nonwords.size());
        
    }
    
   

}
