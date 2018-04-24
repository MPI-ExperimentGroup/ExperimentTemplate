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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool;

import java.util.ArrayList;
import java.util.HashSet;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
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

    private final int numberOfBands = 54;
    private final String wordsSource1="Words_NL_1round";
    private final String nonwordsSource1="NonWords_NL_1round";
    private final String wordsSource21="Words_NL_2rounds_1";
    private final String nonwordsSource21="NonWords_NL_2rounds_1";
    private final String wordsSource22="Words_NL_2rounds_2";
    private final String nonwordsSource22="NonWords_NL_2rounds_2";
    private final String wordsResponse = "JA&#44; ik ken dit woord" ;
    private final String nonwordsResponse ="NEE&#44; ik ken dit woord niet";

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
     * Test of initialiseWords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseWords1() throws Exception{
        System.out.println("initialiseWords-1");
        Vocabulary instance = new Vocabulary(this.numberOfBands, 40);
        AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
        reader.parseWordsInputCSVString(this.wordsSource1, this.numberOfBands, this.nonwordsResponse, this.wordsResponse);
        ArrayList<ArrayList<AdVocAsStimulus>> rawWords = reader.getWords();

        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(rawWords);
        assertEquals(this.numberOfBands, words.size());
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<String> spellings = new ArrayList<>(words.get(i).size());
            for (AdVocAsStimulus stimulus : words.get(i)) {
                spellings.add(stimulus.getLabel());
                assertEquals(i + 1, stimulus.getBandNumber());
            }
            assertEquals(rawWords.get(i).size(), words.get(i).size());
            HashSet<String> set = new HashSet(spellings);
            assertEquals(set.size(), spellings.size()); // fails if there are repititions or permutation was incorrect
        }
    }

    /**
     * Test of initialiseWords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseWords21() throws Exception{
        System.out.println("initialiseWords-21");
        Vocabulary instance = new Vocabulary(this.numberOfBands, 20);
        AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
        reader.parseWordsInputCSVString(this.wordsSource21, this.numberOfBands, this.nonwordsResponse, this.wordsResponse);
        ArrayList<ArrayList<AdVocAsStimulus>> rawWords = reader.getWords();

        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(rawWords);
        assertEquals(this.numberOfBands, words.size());
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<String> spellings = new ArrayList<>(words.get(i).size());
            for (AdVocAsStimulus stimulus : words.get(i)) {
                spellings.add(stimulus.getLabel());
                assertEquals(i + 1, stimulus.getBandNumber());
            }
            assertEquals(rawWords.get(i).size(), words.get(i).size());
            HashSet<String> set = new HashSet(spellings);
            assertEquals(set.size(), spellings.size()); // fails if there are repititions or permutation was incorrect
        }
    }

     /**
     * Test of initialiseWords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseWords22() throws Exception{
        System.out.println("initialiseWords-22");
        Vocabulary instance = new Vocabulary(this.numberOfBands, 20);
        AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
        reader.parseWordsInputCSVString(this.wordsSource22, this.numberOfBands, this.nonwordsResponse, this.wordsResponse);
        ArrayList<ArrayList<AdVocAsStimulus>> rawWords = reader.getWords();

        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(rawWords);
        assertEquals(this.numberOfBands, words.size());
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<String> spellings = new ArrayList<>(words.get(i).size());
            for (AdVocAsStimulus stimulus : words.get(i)) {
                spellings.add(stimulus.getLabel());
                assertEquals(i + 1, stimulus.getBandNumber());
            }
            assertEquals(rawWords.get(i).size(), words.get(i).size());
            HashSet<String> set = new HashSet(spellings);
            assertEquals(set.size(), spellings.size()); // fails if there are repititions or permutation was incorrect
        }
    }

    /**
     * Test of initialiseNonwords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseNonwords2() throws Exception{
        System.out.println("initialiseNonwords-2");
        Vocabulary instance = new Vocabulary(this.numberOfBands, 20);
        AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
        reader.parseNonWordsInputCSVString(this.nonwordsSource21, this.nonwordsResponse, this.wordsResponse);
        ArrayList<AdVocAsStimulus> rawNonWords = reader.getNonwords();
        
        ArrayList<AdVocAsStimulus> nonwords = instance.initialiseNonwords(rawNonWords);
        ArrayList<String> spellings = new ArrayList<>(nonwords.size());
        for (AdVocAsStimulus stimulus : nonwords) {
            spellings.add(stimulus.getLabel());
        }
        HashSet<String> set = new HashSet(spellings);
        assertEquals(rawNonWords.size(), set.size());

        // checking if the Equality is implemented OK on Strings
        ArrayList<String> testEqualityList = new ArrayList<>(2);
        testEqualityList.add("ok");
        testEqualityList.add("ok");
        assertEquals(2, testEqualityList.size());
        HashSet<String> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());

    }

   
   
}
