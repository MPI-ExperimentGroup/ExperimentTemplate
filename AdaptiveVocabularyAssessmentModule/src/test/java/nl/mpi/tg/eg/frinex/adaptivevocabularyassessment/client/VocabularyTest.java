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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
     * Test of initialiseWords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseWords() {
        System.out.println("initialiseWords");
        Vocabulary instance = new Vocabulary();
        AtomBookkeepingStimulus[][] words = instance.initialiseWords(ConstantsWords.WORDS);
        assertEquals(Constants.NUMBER_OF_BANDS, words.length);
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            ArrayList<String> spellings = new ArrayList<>(words[i].length);
            for (AtomBookkeepingStimulus stimulus : words[i]) {
                spellings.add(stimulus.getSpelling());
            }
            HashSet<String> set = new HashSet(spellings);
            assertEquals(ConstantsWords.WORDS[i].length, set.size()); // fails if there are repititions or permutation was incorrect
        }
    }

    /**
     * Test of initialiseNonwords method, of class Vocabulary.
     */
    @Test
    public void testInitialiseNonwords() {
        System.out.println("initialiseNonwords");
        Vocabulary instance = new Vocabulary();
        ArrayList<AdVocAsAtomStimulus> nonwordstmp = new ArrayList<>();
        nonwordstmp.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));
        ArrayList<AtomBookkeepingStimulus> nonwords = instance.initialiseNonwords(nonwordstmp);
        ArrayList<String> spellings = new ArrayList<>(nonwords.size());
        for (AtomBookkeepingStimulus stimulus : nonwords) {
            spellings.add(stimulus.getSpelling());
        }
        HashSet<String> set = new HashSet(spellings);
        assertEquals(ConstantsNonWords.NONWORDS_ARRAY.length, set.size()); // fails if there are repititions or permutation was incorrect
    }

}
