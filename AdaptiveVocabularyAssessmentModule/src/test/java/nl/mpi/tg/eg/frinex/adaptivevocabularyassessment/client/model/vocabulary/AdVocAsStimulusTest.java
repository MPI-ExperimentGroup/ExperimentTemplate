/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary;

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
public class AdVocAsStimulusTest {
    
    public AdVocAsStimulusTest() {
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
     * Test of getBandNumber method, of class AdVocAsStimulus.
     */
    
    // AdVocAsStimulus(String uniqueId, String label, String ratingLabels, String correctResponses,  int bandNumber)
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        AdVocAsStimulus instance = new AdVocAsStimulus("rhabarber_word", "rhabarber", "JA&#44; ik ken dit woord,NEE&#44; ik ken dit woord niet", "JA&#44; ik ken dit woord", 10);
        int expResult = 10;
        int result = instance.getBandNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AdVocAsStimulus.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AdVocAsStimulus instance = new AdVocAsStimulus("rhabarber_word", "rhabarber", "JA&#44; ik ken dit woord,NEE&#44; ik ken dit woord niet", "JA&#44; ik ken dit woord", 10);
        String expResult = "rhabarber_word";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCorrect method, of class AdVocAsStimulus.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");
        String value = "JA&#44; ik ken dit woord";
        AdVocAsStimulus instance = new AdVocAsStimulus("rhabarber_word", "rhabarber", "JA&#44; ik ken dit woord,NEE&#44; ik ken dit woord niet", "JA&#44; ik ken dit woord", 10);
        boolean result = instance.isCorrect(value);
        assertTrue(result);
        
        assertFalse(instance.isCorrect("NEE&#44; ik ken dit woord niet"));
    }
    
}
