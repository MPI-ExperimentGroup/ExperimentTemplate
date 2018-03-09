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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

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
public class AudioAsStimulusTest {

    public AudioAsStimulusTest() {
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
     * Test of getWordType method, of class AudioAsStimulus.
     */
    @Test
    public void testGetWordType() {
        System.out.println("getWordType");
        //public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel)
        AudioAsStimulus instance = new AudioAsStimulus("xxx", "test", 100, "/here/test.wav", null, "10bD", 2, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        WordType result = instance.getWordType();
        assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, result);
    }

    /**
     * Test of setReaction method, of class AudioAsStimulus.
     */
    @Test
    public void testSetReaction() {
        System.out.println("setReaction");
        //public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype, String ratingLabel)
        AudioAsStimulus instance = new AudioAsStimulus("xxx", "test", 100, "/here", null, "6bD", 1, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        instance.setReaction("YES");
        assertEquals(true, instance.getReaction());
        instance.setReaction("");
        assertEquals(false, instance.getReaction());
        instance.setReaction(null);
        assertEquals(false, instance.getReaction());
        instance.setReaction("rubbish");
        assertEquals(null, instance.getReaction());
    }

    /**
     * Test of hasCorrectResponses method, of class AudioAsStimulus.
     */
    @Test
    public void testHasCorrectResponses() {
        System.out.println("hasCorrectResponses");
        AudioAsStimulus instance = new AudioAsStimulus("xxx", "test", 100, "/here", null, "6dB", 1, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        boolean result = instance.hasCorrectResponses();
        assertEquals(true, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        //public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel)
        AudioAsStimulus instance = new AudioAsStimulus("xxx", "test", 100, "/here", null, "6dB", 1, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        long millisec = System.currentTimeMillis();
        instance.setTimeStamp(millisec);
        String expResult = "{audioPath:{/here},label:{test},pauseMs:{100},ratingLabels:{"+AudioAsStimulus.EXAMPLE_TARGET_LABEL+"},uniqueId:{xxx},bandLabel:{6dB},bandIndex:{1},timeStamp:{"+millisec+"},wordType:{"+WordType.EXAMPLE_TARGET_NON_WORD+"}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testToString2() {
        System.out.println("toString 2");
        //public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String bandLabel, int bandIndex, WordType wordtype, String ratingLabel)
        AudioAsStimulus instance = new AudioAsStimulus("xxx", "test", 100, "/here", null, "6dB", 1, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        long millisec = System.currentTimeMillis();
        instance.setTimeStamp(millisec);
        String expResult = "{audioPath:{/here},label:{test},pauseMs:{100},ratingLabels:{"+AudioAsStimulus.EXAMPLE_TARGET_LABEL+"},uniqueId:{xxx},bandLabel:{6dB},bandIndex:{1},timeStamp:{"+millisec+"},wordType:{"+WordType.EXAMPLE_TARGET_NON_WORD+"}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toObject method, of class AudioAsStimulus.
     */
    @Test
    public void testToObject() {
        System.out.println("toObject");
        long millisec = System.currentTimeMillis();
        String str = "{audioPath:{/here/test1.wav},correctResponses:{YES},label:{test1},pauseMs:{200},ratingLabels:{"+AudioAsStimulus.AUDIO_RATING_LABEL+"},uniqueId:{xxx1},bandLabel:{6dB},bandIndex:{1},timeStamp:{"+millisec+"},wordType:{"+WordType.TARGET_NON_WORD+"}}";
        AudioAsStimulus expResult = new AudioAsStimulus("xxx1", "test1", 200, "/here/test1.wav", "YES", "6dB", 1, WordType.TARGET_NON_WORD, AudioAsStimulus.AUDIO_RATING_LABEL);
        expResult.setTimeStamp(millisec);
        AudioAsStimulus result = AudioAsStimulus.toObject(str);
        assertEquals(expResult, result);
    }

}
