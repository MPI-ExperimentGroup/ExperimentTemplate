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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.AudioAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.Trial;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.TrialCondition;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio.WordType;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author olhshk
 */
public class AudioAsStimuliProviderTest {

    private AudioAsStimuliProvider instance;
    private final int startBand = 0;
    private final int tupleSize = 4;
    private final int numberOfBands = 20;

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
        this.instance = new AudioAsStimuliProvider(null);
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
    
    @Test
    public void testInitialiseTrials(){
        AudioAsStimuliProvider instance = new AudioAsStimuliProvider(null);
        ArrayList<Trial> trials = instance.initialiseTrials();
        assertEquals(2156, trials.size());
        for (int i=0; i<trials.size(); i++) {
            Trial trial = trials.get(i);
            assertEquals(i+1, trial.getId());
            assertEquals(trial.getTrialLength()+1, trial.getStimuli().size());
            
            AudioAsStimulus cue = trial.getStimuli().get(0).getStimulus();
            assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, cue.getwordType());
            
            if(trial.getPositionTarget()>0) {
              AudioAsStimulus target = trial.getStimuli().get(trial.getPositionTarget()).getStimulus();  
              String[] bufExp = cue.getLabel().split("_");
              String expectedLabel = bufExp[0];
              String[] bufLabel = target.getLabel().split("_");
              String label = bufLabel[0];
              assertEquals("Trial number "+(new Integer(i+1)).toString(), expectedLabel, label);
            }
                    
            if (trial.getCondition() == TrialCondition.TARGET_AND_FOIL) {
                assertTrue(trial.getPositionFoil()>0);
                assertTrue(trial.getPositionTarget()>0);
            }
            if (trial.getPositionFoil()>0 && trial.getPositionTarget()>0) {
               assertEquals(TrialCondition.TARGET_AND_FOIL,trial.getCondition()); 
            }
            
            if (trial.getCondition() == TrialCondition.NO_TARGET) {
                assertTrue(trial.getPositionTarget()==0);
                assertTrue(trial.getPositionFoil()==0);
            }
            if (trial.getPositionTarget()==0) {
               assertEquals(trial.getCondition(),TrialCondition.NO_TARGET); 
            }
            
            if (trial.getCondition() == TrialCondition.TARGET_ONLY) {
                assertTrue(trial.getPositionTarget()>0);
                assertTrue(trial.getPositionFoil()==0);
            }
            if (trial.getPositionTarget()>0 && trial.getPositionFoil()==0) {
               assertEquals(trial.getCondition(),TrialCondition.TARGET_ONLY); 
            }
            
            for (int j=0; j<trial.getStimuli().size(); j++){
                AudioAsStimulus stimulus = trial.getStimuli().get(j).getStimulus();
                assertEquals(trial.getBandIndex(),stimulus.getbandIndex());
                assertEquals(trial.getBandLabel(),stimulus.getbandLabel());
            }
            
            
            
        }
        
        // "1;vloer;smoer_1.wav;1;Target-only;3 words;deebral.wav;smoer_2.wav;wijp.wav;;;;2;plus10db;0;";
        Trial trial1= trials.get(0);
        assertEquals("vloer", trial1.getWord());
        assertEquals("smoer_1.wav", trial1.getTargetNonWord());
        assertEquals(1, trial1.getNumberOfSyllables());
        assertEquals(TrialCondition.TARGET_ONLY, trial1.getCondition());
        assertEquals(3, trial1.getTrialLength());
        assertEquals("smoer_1", trial1.getStimuli().get(0).getStimulus().getLabel());
        assertEquals("deebral", trial1.getStimuli().get(1).getStimulus().getLabel());
        assertEquals("smoer_2", trial1.getStimuli().get(2).getStimulus().getLabel());
        assertEquals("wijp", trial1.getStimuli().get(3).getStimulus().getLabel());
        assertEquals(2, trial1.getPositionTarget());
        assertEquals("plus10db", trial1.getBandLabel());
        assertEquals(10, trial1.getBandIndex());
        assertEquals(0, trial1.getPositionFoil());
        
        // "1683;hand;kem_1.wav;1;Target+Foil;5 words;guil.wav;kedlim.wav;sorbuin.wav;kem_2.wav;vep.wav;;4;min6db;2;";
        Trial trial2= trials.get(1682);
        assertEquals("hand", trial2.getWord());
        assertEquals("kem_1.wav", trial2.getTargetNonWord());
        assertEquals(1, trial2.getNumberOfSyllables());
        assertEquals(TrialCondition.TARGET_AND_FOIL, trial2.getCondition());
        assertEquals(5, trial2.getTrialLength());
        assertEquals("kem_1", trial2.getStimuli().get(0).getStimulus().getLabel());
        assertEquals("guil", trial2.getStimuli().get(1).getStimulus().getLabel());
        assertEquals("kedlim", trial2.getStimuli().get(2).getStimulus().getLabel());
        assertEquals("sorbuin", trial2.getStimuli().get(3).getStimulus().getLabel());
        assertEquals("kem_2", trial2.getStimuli().get(4).getStimulus().getLabel());
        assertEquals("vep", trial2.getStimuli().get(5).getStimulus().getLabel());
        assertEquals(4, trial2.getPositionTarget());
        assertEquals("min6db", trial2.getBandLabel());
        assertEquals(2, trial2.getBandIndex());
        assertEquals(2, trial2.getPositionFoil());
        
        // "2156;wol;pra.wav;1;NoTarget;6 words;reuwel.wav;wog.wav;consmilp.wav;leskert.wav;mels.wav;dwaat.wav;0;min10db;0;";
        Trial trial3= trials.get(2155);
        assertEquals("wol", trial3.getWord());
        assertEquals("pra.wav", trial3.getTargetNonWord());
        assertEquals(1, trial3.getNumberOfSyllables());
        assertEquals(TrialCondition.NO_TARGET, trial3.getCondition());
        assertEquals(6, trial3.getTrialLength());
        assertEquals("pra", trial3.getStimuli().get(0).getStimulus().getLabel());
        assertEquals("reuwel", trial3.getStimuli().get(1).getStimulus().getLabel());
        assertEquals("wog", trial3.getStimuli().get(2).getStimulus().getLabel());
        assertEquals("consmilp", trial3.getStimuli().get(3).getStimulus().getLabel());
        assertEquals("leskert", trial3.getStimuli().get(4).getStimulus().getLabel());
        assertEquals("mels", trial3.getStimuli().get(5).getStimulus().getLabel());
        assertEquals("dwaat", trial3.getStimuli().get(6).getStimulus().getLabel());
        assertEquals(0, trial3.getPositionTarget());
        assertEquals("min10db", trial3.getBandLabel());
        assertEquals(0, trial3.getBandIndex());
        assertEquals(0, trial3.getPositionFoil());
        
    }

    /**
     * Test of initialiseStimuliState method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testInitialiseStimuliState() { // also check initialisation of trial tuple which is called inside initialiseStimuliState
        System.out.println("initialiseStimuliState");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(20, this.instance.getnumberOfBands());
        assertEquals(this.tupleSize, this.instance.getTrialTuple().getTrials().size());
        ArrayList<Integer> leng = new ArrayList<Integer>(this.tupleSize);
        ArrayList<TrialCondition> types = new ArrayList<TrialCondition>(this.tupleSize);
        for (int i = 0; i < this.tupleSize; i++) {
            Trial currentTrial = this.instance.getTrialTuple().getTrials().get(i);
            leng.add(i, currentTrial.getTrialLength());
            types.add(i, currentTrial.getCondition());
            assertEquals(this.startBand, currentTrial.getBandIndex());
            ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli = currentTrial.getStimuli();
            assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, stimuli.get(0).getStimulus().getwordType()); // the first stimulus should always example
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
    @Ignore @Test
    public void testNextStimulus() {
        System.out.println("nextStimulus");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());
        assertTrue(this.instance.hasNextStimulus(0));
        this.instance.nextStimulus(0);
        assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, this.instance.getCurrentStimulus().getwordType());
        assertEquals(1, this.instance.getResponseRecord().size());
    }

    /**
     * Test of allTupleIsCorrect method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testAllTupleIsCorrect1() {
        System.out.println("allTupleIsCorrect");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        int n = this.instance.getTrialTuple().getNumberOfStimuli();
        for (int i = 0; i < n; i++) {
            this.instance.hasNextStimulus(0);
            this.instance.nextStimulus(0);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus; // upcasting
            if (audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD)) {
                instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
            }
        }
        assertTrue(this.instance.allTupleIsCorrect());
    }

    /**
     * Test of allTupleIsCorrect method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testAllTupleIsCorrect2() {
        System.out.println("allTupleIsCorrect 2");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        int n = this.instance.getTrialTuple().getNumberOfStimuli();
        boolean mistaken = false;
        for (int i = 0; i < n; i++) {
            this.instance.hasNextStimulus(0);
            this.instance.nextStimulus(0);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus; // upcasting
            if (!audioStimulus.getwordType().equals(WordType.EXAMPLE_TARGET_NON_WORD)) { //when evaluated
                if (audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD)) {
                    instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
                }
                if (!audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD) && !mistaken) { // hitting once worngly
                    instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
                    mistaken = true;
                }
            }
        }
        assertFalse(this.instance.allTupleIsCorrect());
    }

    /**
     * Test of allTupleIsCorrect method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testAllTupleIsCorrect3() {
        System.out.println("allTupleIsCorrect 3");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        int n = this.instance.getTrialTuple().getNumberOfStimuli();
        boolean mistaken = false;
        for (int i = 0; i < n; i++) {
            this.instance.hasNextStimulus(0);
            this.instance.nextStimulus(0);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus; // upcasting
            if (audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD)) { // missing a hit
                if (mistaken) {
                    instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
                } else {
                    mistaken = true;  // missing a hit
                }
            }
        }
        assertFalse(this.instance.allTupleIsCorrect());
    }

    /**
     * Test of isCorrectResponse method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        int n = this.instance.getTrialTuple().getNumberOfStimuli();
        for (int i = 0; i < n; i++) {
            this.instance.hasNextStimulus(0);
            this.instance.nextStimulus(0);
            int lastIndex = this.instance.getResponseRecord().size()-1;
            BookkeepingStimulus<AudioAsStimulus> bStimulus = this.instance.getResponseRecord().get(lastIndex);
            Stimulus stimulus = bStimulus.getStimulus(); // upcasting
            if (bStimulus.getStimulus().getwordType().equals(WordType.TARGET_NON_WORD)) {
                assertTrue(instance.isCorrectResponse(stimulus, ""));
                assertEquals("", bStimulus.getReaction());

                assertTrue(instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL));
                assertEquals(AudioAsStimulus.AUDIO_RATING_LABEL, bStimulus.getReaction());
            } else {
                if (!bStimulus.getStimulus().getwordType().equals(WordType.EXAMPLE_TARGET_NON_WORD)) {
                    assertFalse(instance.isCorrectResponse(stimulus, ""));
                    assertEquals("", bStimulus.getReaction());

                    assertFalse(instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL));
                    assertEquals(AudioAsStimulus.AUDIO_RATING_LABEL, bStimulus.getReaction());
                }
            }
        }
    }

    /**
     * Test of initialiseNextFineTuningTuple method, of class
     * AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testInitialiseNextFineTuningTuple() {
        System.out.println("initialiseNextFineTuningTuple");

        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        while (this.instance.getTrialTuple().isNotEmpty()) {
            this.instance.getTrialTuple().removeFirstAvailableStimulus();
        }

        boolean result = this.instance.initialiseNextFineTuningTuple();
        assertEquals(this.tupleSize, this.instance.getTrialTuple().getTrials().size());
        assertEquals(null, this.instance.getTrialTuple().getCorrectness());
    }

    /**
     * Test of tupleIsNotEmpty method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testTupleIsNotEmpty() {
        System.out.println("tupleIsNotEmpty");

        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        for (int i = 0; i < this.tupleSize; i++) {
            assertTrue(this.instance.getTrialTuple().getTrials().get(i).getStimuli().size() > 1);
        }

        while (this.instance.getTrialTuple().isNotEmpty()) {
            this.instance.getTrialTuple().removeFirstAvailableStimulus();
        }

        for (int i = 0; i < this.tupleSize; i++) {
            assertEquals(0, this.instance.getTrialTuple().getTrials().get(i).getStimuli().size());
        }

    }

    /**
     * Test of getTrialTuple method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testGetTrialTuple() {
        System.out.println("getTrialTuple");
        System.out.println("tupleIsNotEmpty");

        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        for (int i = 0; i < this.tupleSize; i++) {
            Trial trial = this.instance.getTrialTuple().getTrials().get(i);
            TrialCondition cond = trial.getCondition();
            ArrayList<BookkeepingStimulus<AudioAsStimulus>> stimuli = trial.getStimuli();
            assertTrue(stimuli.size() > 1);
            int countFoil = 0;
            int countTarget = 0;
            int countNonWord = 0;
            for (BookkeepingStimulus<AudioAsStimulus> bStimulus : stimuli) {
                AudioAsStimulus stimulus = bStimulus.getStimulus();
                if (stimulus.getwordType().equals(WordType.FOIL)) {
                    countFoil++;
                }
                if (stimulus.getwordType().equals(WordType.TARGET_NON_WORD)) {
                    countTarget++;
                }
                if (stimulus.getwordType().equals(WordType.NON_WORD)) {
                    countNonWord++;
                }
            }
            if (cond.equals(TrialCondition.NO_TARGET)) {
                assertEquals(0, countFoil);
                assertEquals(0, countTarget);
                assertEquals(stimuli.size() - 1, countNonWord);
            }
            if (cond.equals(TrialCondition.TARGET_ONLY)) {
                assertEquals(0, countFoil);
                assertEquals(1, countTarget);
                assertEquals(stimuli.size() - 2, countNonWord);
            }
            if (cond.equals(TrialCondition.TARGET_AND_FOIL)) {
                assertEquals(1, countFoil);
                assertEquals(1, countTarget);
                assertEquals(stimuli.size() - 3, countNonWord);
            }
        }

    }

    /**
     * Test of hasNextStimulus method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testHasNextStimulus() {
        System.out.println("hasNextStimulus");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());
        assertTrue(this.instance.hasNextStimulus(0));
        this.instance.nextStimulus(0);
    }

    /**
     * Test of hasNextStimulus method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testHasNextStimulusChampion() {
        System.out.println("hasNextStimulus Champion");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());

        int i = 0;
        while (this.instance.hasNextStimulus(i)) {
            this.instance.nextStimulus(i);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus;
            String correctResponce = audioStimulus.getCorrectResponses();
            this.instance.isCorrectResponse(stimulus, correctResponce);
            i++;
        }

        assertTrue(this.instance.getChampion());
        assertFalse(this.instance.getCycel2());
        assertFalse(this.instance.getLooser());
        assertEquals(this.numberOfBands, this.instance.getBandScore());

        ArrayList<BookkeepingStimulus<AudioAsStimulus>> record = this.instance.getResponseRecord();
        this.printRecord(record);

    }

    /**
     * Test of hasNextStimulus method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testHasNextStimulusLooser() {
        System.out.println("hasNextStimulus Looser");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());

        int i = 0;
        boolean disturt = false;
        while (this.instance.hasNextStimulus(i)) {
            this.instance.nextStimulus(i);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus;
            String correctResponce = audioStimulus.getCorrectResponses();
            i++;
            if (disturt) {
                if (correctResponce == null) {
                    this.instance.isCorrectResponse(stimulus, "YES");
                } else {
                    if (correctResponce.equals("YES")) {
                        this.instance.isCorrectResponse(stimulus, "");
                    } else {
                        this.instance.isCorrectResponse(stimulus, "YES");
                    }
                }
            } else {
                this.instance.isCorrectResponse(stimulus, correctResponce);
            }
            if (audioStimulus.getwordType().equals(WordType.EXAMPLE_TARGET_NON_WORD)) {
                disturt = true;
            } else {
                disturt = false;
            }
        }

        assertFalse(this.instance.getChampion());
        assertFalse(this.instance.getCycel2());
        assertTrue(this.instance.getLooser());
        assertEquals(1, this.instance.getBandScore());

        ArrayList<BookkeepingStimulus<AudioAsStimulus>> record = this.instance.getResponseRecord();
        this.printRecord(record);
    }

    /**
     * Test of hasNextStimulus method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testHasNextStimulusLoop() {
        System.out.println("hasNextStimulus Loop");
        String stimuliStateSnapshot = "";
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        assertEquals(this.startBand, this.instance.getCurrentBandIndex());

        int i = 0;
        boolean distort = false;
        int previousBandIndex = 0;
        while (this.instance.hasNextStimulus(i)) {
            this.instance.nextStimulus(i);
            AudioAsStimulus audioStimulus = this.instance.getCurrentStimulus();
            Stimulus stimulus = audioStimulus;
            String correctResponce = audioStimulus.getCorrectResponses();
            i++;

            if (this.instance.getCurrentBandIndex() > previousBandIndex) {
                distort = true; // we jumped to the higher band, need to make a mistake to force looping
            } else {
                distort = false;
            }

            if (!audioStimulus.getwordType().equals(WordType.EXAMPLE_TARGET_NON_WORD)) {
                previousBandIndex = this.instance.getCurrentBandIndex();
                if (distort) { // cal isCorrect (the button is hited) only on non-target
                    if (!audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD)) {
                        this.instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
                    }
                } else { // non-distort, call isCorrect only on target
                    if (audioStimulus.getwordType().equals(WordType.TARGET_NON_WORD)) {
                        this.instance.isCorrectResponse(stimulus, AudioAsStimulus.AUDIO_RATING_LABEL);
                    }
                }
            }
        }

        assertFalse(this.instance.getChampion());
        assertTrue(this.instance.getCycel2());
        assertFalse(this.instance.getLooser());
        assertEquals(2, this.instance.getBandScore());

        ArrayList<BookkeepingStimulus<AudioAsStimulus>> record = this.instance.getResponseRecord();
        this.printRecord(record);
    }

    private void printRecord(ArrayList<BookkeepingStimulus<AudioAsStimulus>> record) {
        for (BookkeepingStimulus<AudioAsStimulus> bStimulus : record) {
            AudioAsStimulus stimulus =  bStimulus.getStimulus();
            System.out.print(stimulus.getbandLabel());
            System.out.print("  ");
            System.out.print(stimulus.getLabel());
            System.out.print("  ");
            System.out.print(stimulus.getwordType());
            System.out.print("  ");
            System.out.print(stimulus.getCorrectResponses());
            System.out.print("  ");
            System.out.print(bStimulus.getReaction());
            System.out.println();
        }
    }

    /**
     * Test of allTupleIsCorrect method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testAllTupleIsCorrect() {
        System.out.println("allTupleIsCorrect");
        AudioAsStimuliProvider instance = null;
        Boolean expResult = null;
        Boolean result = instance.allTupleIsCorrect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringFineTuningHistory method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testGetStringFineTuningHistory() {
        System.out.println("getStringFineTuningHistory");
        String startRow = "";
        String endRow = "";
        String startColumn = "";
        String endColumn = "";
        String format = "";
        AudioAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.getStringFineTuningHistory(startRow, endRow, startColumn, endColumn, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AudioAsStimuliProvider.
     */
    @Ignore @Test
    public void testToString() {
        System.out.println("toString");
        AudioAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}
