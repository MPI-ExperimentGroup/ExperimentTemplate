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
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
    private final int wordsPerBand = 40;

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
    public void testInitialiseWords1() {
        System.out.println("initialiseWords-1");
        int numberOfSeries = 1;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);
        AdVocAsStimulus[][] wordsArray = Words_NL_1round.WORDS_SERIES[0];
        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(wordsArray);
        assertEquals(this.numberOfBands, words.size());
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<String> spellings = new ArrayList<>(words.get(i).size());
            for (AdVocAsStimulus stimulus : words.get(i)) {
                spellings.add(stimulus.getLabel());
                assertEquals(i + 1, stimulus.getBandNumber());
            }
            HashSet<String> set = new HashSet(spellings);
            assertEquals(wordsArray[i].length, set.size()); // fails if there are repititions or permutation was incorrect
        }
    }

    /**
     * Test of initialiseWords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseWords2() {
        System.out.println("initialiseWords-2");
        int numberOfSeries = 2;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);
        AdVocAsStimulus[][] wordsArray = Words_NL_2rounds_1.WORDS_SERIES[0];
        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(wordsArray);
        assertEquals(this.numberOfBands, words.size());
        for (int i = 0; i < this.numberOfBands; i++) {
            ArrayList<String> spellings = new ArrayList<>(words.get(i).size());
            for (AdVocAsStimulus stimulus : words.get(i)) {
                spellings.add(stimulus.getLabel());
                assertEquals(i + 1, stimulus.getBandNumber());
            }
            HashSet<String> set = new HashSet(spellings);
            assertEquals(wordsArray[i].length, set.size()); // fails if there are repititions or permutation was incorrect
        }
    }

    /**
     * Test of initialiseNonwords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseNonwords1() {
        System.out.println("initialiseNonwords-1");
        int numberOfSeries = 1;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);
        ArrayList<AdVocAsStimulus> nonwordstmp = new ArrayList<>();
        AdVocAsStimulus[] nonwordsArray = NonWords_NL_1round.NONWORDS_SERIES[0];

        nonwordstmp.addAll(Arrays.asList(nonwordsArray));
        ArrayList<AdVocAsStimulus> nonwords = instance.initialiseNonwords(nonwordstmp);
        ArrayList<String> spellings = new ArrayList<>(nonwords.size());
        for (AdVocAsStimulus stimulus : nonwords) {
            spellings.add(stimulus.getLabel());
        }
        HashSet<String> set = new HashSet(spellings);
        assertEquals(nonwordsArray.length, set.size());

        // checking if the Equality is implemented OK on Strings
        ArrayList<String> testEqualityList = new ArrayList<>(2);
        testEqualityList.add("ok");
        testEqualityList.add("ok");
        assertEquals(2, testEqualityList.size());
        HashSet<String> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());

    }

    /**
     * Test of initialiseNonwords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseNonwords2() {
        System.out.println("initialiseNonwords-2");
        int numberOfSeries = 2;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);
        ArrayList<AdVocAsStimulus> nonwordstmp = new ArrayList<>();
        AdVocAsStimulus[] nonwordsArray = NonWords_NL_2rounds_2.NONWORDS_SERIES[0];

        nonwordstmp.addAll(Arrays.asList(nonwordsArray));
        ArrayList<AdVocAsStimulus> nonwords = instance.initialiseNonwords(nonwordstmp);
        ArrayList<String> spellings = new ArrayList<>(nonwords.size());
        for (AdVocAsStimulus stimulus : nonwords) {
            spellings.add(stimulus.getLabel());
        }
        HashSet<String> set = new HashSet(spellings);
        assertEquals(nonwordsArray.length, set.size());

        // checking if the Equality is implemented OK on Strings
        ArrayList<String> testEqualityList = new ArrayList<>(2);
        testEqualityList.add("ok");
        testEqualityList.add("ok");
        assertEquals(2, testEqualityList.size());
        HashSet<String> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());

    }

    /**
     * Test of getHashedStimuli method, of class Vocabulary.
     */
    @Test
    public void testGetHashedStimuli() {
        System.out.println("getHashedStimuli");
        int numberOfSeries = 1;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);

        ArrayList<AdVocAsStimulus> nonwordstmp = new ArrayList<>();
        AdVocAsStimulus[] nonwordsArray = NonWords_NL_1round.NONWORDS_SERIES[0];
        nonwordstmp.addAll(Arrays.asList(nonwordsArray));
        ArrayList<AdVocAsStimulus> nonwords = instance.initialiseNonwords(nonwordstmp);

        AdVocAsStimulus[][] wordsArray = Words_NL_1round.WORDS_SERIES[0];
        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.initialiseWords(wordsArray);

        int size = this.numberOfBands * wordsArray[0].length + nonwordsArray.length;

        LinkedHashMap<String, AdVocAsStimulus> result = instance.getHashedStimuli();
        assertEquals(size, result.size());

        for (int i = 0; i < this.numberOfBands; i++) {
            for (AdVocAsStimulus stimulus : words.get(i)) {
                assertEquals(stimulus, result.get(stimulus.getUniqueId()));
            }
        }

        for (AdVocAsStimulus stimulus : nonwords) {
            assertEquals(stimulus, result.get(stimulus.getUniqueId()));
        }
    }
    
     /**
     * Test of getHashedStimuli method, of class Vocabulary.
     */
    @Test
    public void testHashStimuli() {
        System.out.println("hashStimuli");
        int numberOfSeries = 1;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        Vocabulary instance = new Vocabulary(this.wordsPerBand, wordsPerBandInSeries);

        AdVocAsStimulus[] nonwordsArray = NonWords_NL_1round.NONWORDS_SERIES[0];
        
        AdVocAsStimulus[][] wordsArray = Words_NL_1round.WORDS_SERIES[0];

        int size = this.numberOfBands * wordsArray[0].length + nonwordsArray.length;

        LinkedHashMap<String, AdVocAsStimulus> result = instance.hashStimuli(wordsArray, nonwordsArray);
        assertEquals(size, result.size());

        for (int i = 0; i < this.numberOfBands; i++) {
            for (int j=0; j< wordsArray[i].length; j++) {
                AdVocAsStimulus stimulus = wordsArray[i][j];
                assertEquals(stimulus, result.get(stimulus.getUniqueId()));
            }
        }

        for (int i = 0; i < nonwordsArray.length; i++) {
            assertEquals(nonwordsArray[i], result.get(nonwordsArray[i].getUniqueId()));
        }
    }
}
