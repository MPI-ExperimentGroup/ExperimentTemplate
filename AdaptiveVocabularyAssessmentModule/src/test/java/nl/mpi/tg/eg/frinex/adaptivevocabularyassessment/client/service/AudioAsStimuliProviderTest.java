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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioIndexMap;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
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
public class AudioAsStimuliProviderTest {

    private AudioAsStimuliProvider instance;
    private final int startBand = 0;
    private final int tupleSize = 4;
    private final int numberOfBands = AudioIndexMap.INDEX_ARRAY.length;

    public AudioAsStimuliProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.instance = new AudioAsStimuliProvider();
        this.instance.setfastTrackPresent("False");
        this.instance.setfineTuningFirstWrongOut("False");
        this.instance.setfineTuningTupleLength(Integer.toString(this.tupleSize));
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfBands(Integer.toString(this.numberOfBands));
        this.instance.setnumberOfSeries("1");
        this.instance.setstartBand(Integer.toString(this.startBand));
        this.instance.settype("0");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initialiseStimuliState method, of class AudioAsStimuliProvider.
     */
    @Test
    public void testInitialiseStimuliState() { // also check initialisation of trial tuple which is called inside initialiseStimuliState
        System.out.println("initialiseStimuliState");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(AudioIndexMap.INDEX_ARRAY.length, this.instance.getNumberOfBands());
        assertEquals(this.tupleSize, this.instance.getTrialTuple().getTrials().size());
        ArrayList<Integer> leng = new ArrayList<Integer>(this.tupleSize);
        ArrayList<TrialCondition> types = new ArrayList<TrialCondition>(this.tupleSize);
        for (int i = 0; i < this.tupleSize; i++) {
            Trial currentTrial = this.instance.getTrialTuple().getTrials().get(i);
            leng.add(i, currentTrial.getTrialLength());
            types.add(i, currentTrial.getCondition());
            assertEquals(this.startBand, currentTrial.getBandIndex());
            ArrayList<AudioAsStimulus> stimuli = currentTrial.getStimuliList();
            assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, stimuli.get(0).getWordType()); // the first stimulus should always example
            assertEquals(currentTrial.getTrialLength() + 1, stimuli.size());
        }
        assertTrue(leng.indexOf(3) > -1);
        assertTrue(leng.indexOf(4) > -1);
        assertTrue(leng.indexOf(5) > -1);
        assertTrue(leng.indexOf(6) > -1);

        assertTrue(types.indexOf(TrialCondition.NO_TARGET) > -1);
        assertTrue(types.indexOf(TrialCondition.TARGET_ONLY) > -1);
        assertTrue(types.indexOf(TrialCondition.TARGET_AND_FOIL) > -1);
        types.remove(TrialCondition.NO_TARGET);
        assertEquals(3, types.size());
        assertTrue(types.indexOf(TrialCondition.NO_TARGET) > -1);

        assertEquals(this.startBand, this.instance.getCurrentBandIndex());
    }

    /**
     * Test of nextStimulus method, of class AudioAsStimuliProvider.
     */
    @Test
    public void testNextStimulus() {
        System.out.println("nextStimulus");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());
        assertTrue(this.instance.hasNextStimulus(0));
        this.instance.nextStimulus(0);
        assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, this.instance.getCurrentStimulus().getWordType());
        assertEquals(1, this.instance.getResponseRecord().size());
    }

    /**
     * Test of allTupleIsCorrect method, of class AudioAsStimuliProvider.
     */
    @Test
    public void testAllTupleIsCorrect() {
        System.out.println("allTupleIsCorrect");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertTrue(this.instance.allTupleIsCorrect()); // initially the tuple is set correct, it will change to false once it hist first wron answer
        this.instance.getTrialTuple().setCorrectness(false); 
        assertFalse(this.instance.allTupleIsCorrect());
    }

    /**
     * Test of isCorrectResponse method, of class AudioAsStimuliProvider.
     */
    @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        
        this.instance.hasNextStimulus(0);
        this.instance.nextStimulus(0);
        AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
        Stimulus stimulus = audioStimulus; // upcasting
        
        assertTrue(instance.isCorrectResponse(stimulus, null));
        assertTrue(instance.getTrialTuple().getCorrectness());
        assertTrue(instance.isCorrectResponse(stimulus, ""));
        assertTrue(instance.getTrialTuple().getCorrectness());
        
        this.instance.hasNextStimulus(0);
        this.instance.nextStimulus(0);
        AudioAsStimulus audioStimulus2 = this.instance.getCurrentStimulus();
        Stimulus stimulus2 = audioStimulus2; // upcasting
        
        String correctResponse = audioStimulus2.getCorrectResponses();
        assertTrue(instance.isCorrectResponse(stimulus2, correctResponse));
        assertTrue(instance.getTrialTuple().getCorrectness());
        String wrongResponse = "";
        if (correctResponse == null) {
            wrongResponse = AudioAsStimulus.AUDIO_RATING_LABEL;
        } else {
            if (correctResponse.length()==0) {
               wrongResponse = AudioAsStimulus.AUDIO_RATING_LABEL; 
            }
        }
        assertFalse(instance.isCorrectResponse(stimulus2, wrongResponse));
        assertFalse(instance.getTrialTuple().getCorrectness());
    }

}
