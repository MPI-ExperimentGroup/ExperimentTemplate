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

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;
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
public class AdVocAsBookkeepingStimulusTest {

    public AdVocAsBookkeepingStimulusTest() {
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
     * Test of getLabel method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testGetSpelling() {
        System.out.println("getSpelling");
        //AdVocAsAtomStimulus(String uniqueId, String label, String correctResponses, int bandNumber)
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        String expResult = "test-spelling";
        String result = instance.getLabel();
        assertEquals(expResult, result);
    }

  
    /**
     * Test of getBandNumber method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        int result = instance.getBandNumber();
        assertEquals(37, result);
    }

  
    /**
     * Test of getReaction method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testGetReaction() {
        System.out.println("getReaction");
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        Boolean result = instance.getReaction();
        assertEquals(null, result);
    }

    /**
     * Test of getCorrectness method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testGetCorrectness() {
        System.out.println("getCorrectness");
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        Boolean result = instance.getCorrectness();
        assertEquals(null, result);
    }

    /**
     * Test of setReaction method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testSetReaction() {
        System.out.println("setReaction");
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        instance.setReaction(Vocabulary.NONWORD);
        Boolean result = instance.getReaction();
        assertEquals(false, result);
    }

    /**
     * Test of setCorrectness method, of class AdVocAsBookkeepingStimulus.
     */
    @Test
    public void testSetCorrectness() {
        System.out.println("setCorrectness");
        AdVocAsStimulus pureStimulus = new AdVocAsStimulus("test_"+System.currentTimeMillis(), "test-spelling", Vocabulary.WORD, 37);
        
        AdVocAsBookkeepingStimulus instance = new AdVocAsBookkeepingStimulus(pureStimulus);
        instance.setCorrectness(true);
        Boolean result = instance.getCorrectness();
        assertEquals(true, result);
    }

   
}
