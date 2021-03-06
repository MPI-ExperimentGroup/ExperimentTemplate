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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.*;

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
public class AdVocAsStimuliProviderTest_HUN {

    private AdVocAsStimuliProvider provider;
    String answerWord = SourcenameIndices.RESPONSES_INDEX.get("Words_HUN_round_1");
    String answerNonWord = SourcenameIndices.RESPONSES_INDEX.get("NonWords_HUN_round_1");

    public AdVocAsStimuliProviderTest_HUN() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        AdVocAsStimulus[] stimului = {null};
        this.provider = new AdVocAsStimuliProvider(stimului);
        this.provider.setfastTrackShablonSource("FastTrackShablonOrigin_HUN");
        this.provider.setfineTuningShablonSource("FineTuningShablonOrigin_HUN");
        this.provider.setfineTuningUpperBoundForCycles("2");
        this.provider.setwordsSource("Words_HUN_round_1");
        this.provider.setnonwordsSource("NonWords_HUN_round_1");
        this.provider.setmaxDurationMinutes(null);
        this.provider.setnumberOfBands("50");
    }

    @After
    public void tearDown() {
        this.provider = null;
    }

    /**
     * Test of initialiseStimuliState method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testInitialiseStimuliState() {
        System.out.println("initialiseStimuliState");
        this.provider.initialiseStimuliState("");

        assertNull(this.provider.getCurrentStimulus());

        ArrayList<FastTrackShablonElement> fastTrackSceletone = this.provider.getFastTrackSceletone();

        assertNotNull(fastTrackSceletone);
        assertEquals(48, fastTrackSceletone.size());
        assertTrue(fastTrackSceletone.get(0).getIsWord());
        assertEquals(16, fastTrackSceletone.get(0).getBandNumber());
        assertFalse(fastTrackSceletone.get(45).getIsWord());
        assertEquals(0, fastTrackSceletone.get(45).getBandNumber());

        ArrayList<FineTuningShablonElement> fineTuningSceletone = this.provider.getFineTuningSceletone();

        assertNotNull(fineTuningSceletone);
        assertEquals(100, fineTuningSceletone.size());

        for (int i = 0; i < fineTuningSceletone.size(); i++) {
            boolean[] isWord = fineTuningSceletone.get(i).getArray();
            assertEquals(4, isWord.length);
            if ((i % 4) == 0) {
                assertTrue(isWord[0]);
                assertTrue(isWord[1]);
                assertTrue(isWord[2]);
                assertFalse(isWord[3]);
            }
            if ((i % 4) == 1) {
                assertTrue(isWord[0]);
                assertTrue(isWord[1]);
                assertFalse(isWord[2]);
                assertTrue(isWord[3]);
            }
            if ((i % 4) == 2) {
                assertTrue(isWord[0]);
                assertFalse(isWord[1]);
                assertTrue(isWord[2]);
                assertTrue(isWord[3]);
            }
            if ((i % 4) == 3) {
                assertFalse(isWord[0]);
                assertTrue(isWord[1]);
                assertTrue(isWord[2]);
                assertTrue(isWord[3]);
            }
        }

        ArrayList<ArrayList<AdVocAsStimulus>> words = this.provider.getWords();
        assertNotNull(words);
        assertEquals(50, words.size());
        for (int i = 0; i < words.size(); i++) {
            ArrayList<AdVocAsStimulus> wordsInBand = words.get(i);
            assertEquals(15, wordsInBand.size());
            for (int j = 0; j < wordsInBand.size(); j++) {
                AdVocAsStimulus stimulus = wordsInBand.get(j);
                assertEquals(i + 1, stimulus.getBandNumber());
            }
        }
        assertEquals("remény", words.get(0).get(0).getUniqueId());
        assertEquals("remény", words.get(0).get(0).getLabel());
        assertEquals(1, words.get(0).get(0).getBandNumber());
        assertEquals(this.answerWord, words.get(0).get(0).getCorrectResponses());
        assertEquals(this.answerNonWord + "," + this.answerWord, words.get(0).get(0).getRatingLabels());

        assertEquals("anzágol", words.get(49).get(14).getUniqueId());
        assertEquals("anzágol", words.get(49).get(14).getLabel());
        assertEquals(50, words.get(49).get(14).getBandNumber());
        assertEquals(this.answerWord, words.get(49).get(14).getCorrectResponses());
        assertEquals(this.answerNonWord + "," + this.answerWord, words.get(49).get(14).getRatingLabels());

        ArrayList<AdVocAsStimulus> nonwords = this.provider.getNonwords();
        assertNotNull(nonwords);

        assertEquals(125, nonwords.size());
        for (int i = 0; i < nonwords.size(); i++) {
            AdVocAsStimulus stimulus = nonwords.get(i);
            assertEquals(0, stimulus.getBandNumber());
        }

        assertEquals("szeudor", nonwords.get(0).getUniqueId());
        assertEquals("szeudor", nonwords.get(0).getLabel());
        assertEquals(0, nonwords.get(0).getBandNumber());
        assertEquals(this.answerNonWord, nonwords.get(0).getCorrectResponses());
        assertEquals(this.answerNonWord + "," + this.answerWord, nonwords.get(0).getRatingLabels());

        int n = nonwords.size() - 1;
        assertEquals("kilukát", nonwords.get(n).getUniqueId());
        assertEquals("kilukát", nonwords.get(n).getLabel());
        assertEquals(0, nonwords.get(n).getBandNumber());
        assertEquals(this.answerNonWord, nonwords.get(n).getCorrectResponses());
        assertEquals(this.answerNonWord + "," + this.answerWord, nonwords.get(n).getRatingLabels());

        assertEquals(-1, this.provider.getCurrentStimulusIndex());

        Integer[] bvCounter = this.provider.getBandVisitCounter();
        assertEquals(50, bvCounter.length);
        for (int i = 0; i < bvCounter.length; i++) {
            assertEquals(0, bvCounter[i].intValue());
        }
        Integer[] cycleHelper = this.provider.getCycleHelper();
        assertEquals(5, cycleHelper.length);
        for (int i = 0; i < cycleHelper.length; i++) {
            assertEquals(0, cycleHelper[i].intValue());
        }

        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());
        assertEquals(16, this.provider.getStartBand());
    }

    /**
     * Test of setmaxDurationMinutes method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetmaxDurationMinutes() {
        System.out.println("setmaxDurationMinutes: tested in testInitialiseStimuliState via setUp");
    }

    /**
     * Test of setnumberOfBands method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetnumberOfBands() {
        System.out.println("setnumberOfBands: tested in testInitialiseStimuliState via setUp");
    }

    /**
     * Test of setfineTuningUpperBoundForCycles method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetfineTuningUpperBoundForCycles() {
        System.out.println("setfineTuningUpperBoundForCycles: tested in testInitialiseStimuliState via setUp");

    }

    /**
     * Test of setwordsSource method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetwordsSource() {
        System.out.println("setwordsSource: tested in testInitialiseStimuliState via setUp");

    }

    /**
     * Test of setnonwordsSource method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetnonwordsSource() {
        System.out.println("setnonwordsSource : tested in testInitialiseStimuliState via setUp");
    }

    /**
     * Test of setfastTrackShablonSource method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetfastTrackShablonSource() {
        System.out.println("setfastTrackShablonSource: tested in testInitialiseStimuliState via setUp");
    }

    /**
     * Test of setnfineTuningShablonSource method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testSetnfineTuningShablonSource() {
        System.out.println("setnfineTuningShablonSource: tested in testInitialiseStimuliState via setUp");
    }

    /**
     * Test of isCorrectResponse method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        this.provider.initialiseStimuliState("");
        this.provider.hasNextStimulus(0);
        this.provider.nextStimulus(0);

        AdVocAsStimulus stimulus = this.provider.getWords().get(14).get(0);
        assertTrue(this.provider.isCorrectResponse(stimulus, this.answerWord));
        assertFalse(this.provider.isCorrectResponse(stimulus, this.answerNonWord));

        // a bit of moc because the first sent to the front-end stimulus is a word 
        AdVocAsStimulus stimulusNW = this.provider.getNonwords().get(0);
        assertFalse(this.provider.isCorrectResponse(stimulusNW, this.answerWord));
        assertTrue(this.provider.isCorrectResponse(stimulusNW, this.answerNonWord));
    }

    /**
     * Test of getCurrentStimulusUniqueId method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusUniqueId() {
        System.out.println("getCurrentStimulusUniqueId");
        this.provider.initialiseStimuliState("");
        this.provider.hasNextStimulus(0);
        this.provider.nextStimulus(0);
        assertEquals("megrohad", this.provider.getCurrentStimulusUniqueId());
    }

    /**
     * Test of generateStimuliStateSnapshot method, of class
     * AdVocAsStimuliProvider.
     */
    // TODO: serialisation left to the future
    @Ignore
    @Test
    public void testGenerateStimuliStateSnapshot() {
        System.out.println("generateStimuliStateSnapshot");
        AdVocAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.generateStimuliStateSnapshot();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentStimuliIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetSetCurrentStimuliIndex() {
        System.out.println("getCurrentStimulusIndex, setCurrentStimuliIndex");
        this.provider.initialiseStimuliState("");
        this.provider.hasNextStimulus(0);
        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getCurrentStimulusIndex());
        this.provider.setCurrentStimuliIndex(-10);
        assertEquals(0, this.provider.getCurrentStimulusIndex());
    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus() {
        System.out.println("getCurrentStimulus");
        this.provider.initialiseStimuliState("");
        this.provider.hasNextStimulus(0);
        this.provider.nextStimulus(0);
        AdVocAsStimulus result = this.provider.getCurrentStimulus();
        assertEquals(16, result.getBandNumber());
        assertEquals("megrohad", result.getUniqueId());
        assertEquals("megrohad", result.getLabel());
        assertEquals(this.answerWord, result.getCorrectResponses());
        assertEquals(this.answerNonWord + "," + this.answerWord, result.getRatingLabels());
    }

    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetTotalStimuli() {
        System.out.println("getTotalStimuli");
        this.provider.initialiseStimuliState("");
        assertEquals(125 + 15 * 50, this.provider.getTotalStimuli());
    }

    /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus() {
        System.out.println("hasNextStimulus");
        this.provider.initialiseStimuliState("");
        assertTrue(this.provider.hasNextStimulus(0));
    }

    /**
     * Test of nextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testNextStimulus() {
        System.out.println("nextStimulus: tested via testGetCurrentStimulus");

    }

    // HELPER
    private void checkingInitialisedFineTuningTuple(int bandNumber) {
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> tuple = this.provider.getFTtuple();
        //the first stimulus is already put on the records;
        int nonwordCounter = 0;
        if (record.get(record.size() - 1).getStimulus().getBandNumber() > 0) {
            assertEquals(bandNumber, record.get(record.size() - 1).getStimulus().getBandNumber());
        } else {
            nonwordCounter++;
        }

        assertEquals(3, tuple.size());

        for (int i = 0; i <= 2; i++) {
            if (tuple.get(i).getStimulus().getBandNumber() > 0) {
                assertEquals(bandNumber, tuple.get(0).getStimulus().getBandNumber());
            } else {
                nonwordCounter++;
            }
        }

        assertEquals(1, nonwordCounter);
    }

    // HELPER 
    private void checkFineTuningAllCorrect(int startBandNumber) {

        System.out.println("fine tuning all correct");

        boolean fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        assertTrue(fineTuningToBecontinued);

        int fTuningShablonCounter = 0;
        ArrayList<FineTuningShablonElement> sceletoneFR = this.provider.getFineTuningSceletone();
        boolean[] currentSjablon = sceletoneFR.get(0).getArray();

        int bandNumber = startBandNumber;
        while (fineTuningToBecontinued) {
            this.correctFTtuple(bandNumber, fTuningShablonCounter, currentSjablon);
            if (bandNumber < 50) {
                bandNumber++;
            }
            fTuningShablonCounter++;
            currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
            fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        }

        assertEquals(50, this.provider.getBandScore());
        assertTrue(this.provider.getChampion());
        assertFalse(this.provider.getLooser());
        assertFalse(this.provider.getCycle());
        assertFalse(this.provider.getIsTimeOut());
    }

    // HELPER 
    private void checkFineTuningAllWrong(int startBandNumber, int wrongPosition) {

        System.out.println("fine tuning all wrong");

        boolean fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        assertTrue(fineTuningToBecontinued);

        int fTuningShablonCounter = 0;
        ArrayList<FineTuningShablonElement> sceletoneFR = this.provider.getFineTuningSceletone();
        boolean[] currentSjablon = sceletoneFR.get(0).getArray();

        int bandNumber = startBandNumber;

        while (fineTuningToBecontinued) {

            this.wrongFTtuple(bandNumber, wrongPosition, fTuningShablonCounter, currentSjablon);
            if (bandNumber > 1) {
                bandNumber--;
            }
            fTuningShablonCounter++;
            currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
            fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        }

        assertEquals(1, this.provider.getBandScore());
        assertFalse(this.provider.getChampion());
        assertTrue(this.provider.getLooser());
        assertFalse(this.provider.getCycle());
        assertFalse(this.provider.getIsTimeOut());
    }

    private void wrongFTtuple(int bandNumber, int wrongPosition, int fTuningShablonCounter, boolean[] currentSjablon) {
        AdVocAsStimulus stimulus;

        int oldSizeNonwords = this.provider.getNonwords().size();
        int oldSizeWords = this.provider.getWords().get(bandNumber - 1).size();

        ArrayList<AdVocAsStimulus> prevNonwords = this.freshCopy(this.provider.getNonwords());
        ArrayList<AdVocAsStimulus> prevWords = this.freshCopy(this.provider.getWords().get(bandNumber - 1));

        boolean nonWordPresented = false;

        for (int tC = 0; tC < wrongPosition; tC++) {
            assertEquals(fTuningShablonCounter, this.provider.getFineTuningShablonPosition());

            stimulus = this.provider.getCurrentStimulus();
            assertEquals(currentSjablon[tC], stimulus.getBandNumber() > 0); // both must be either words or non words simultaneously
            if (stimulus.getBandNumber() > 0) {
                assertEquals(bandNumber, stimulus.getBandNumber());
            } else {
                nonWordPresented = true;
            }

            //this.provider.isCorrectResponse(stimulus, stimulus.getCorrectResponses()); // pressing button
            stimulus.isCorrect(stimulus.getCorrectResponses());
            this.provider.hasNextStimulus(0); // here we analyse the correctenss of the step and update 
            this.provider.nextStimulus(0);
        }

        assertEquals(fTuningShablonCounter, this.provider.getFineTuningShablonPosition());
        stimulus = this.provider.getCurrentStimulus();

        if (!currentSjablon[wrongPosition] && stimulus.getBandNumber() > 0) {
            System.out.println("!!!-2");
        }

        assertEquals(currentSjablon[wrongPosition], stimulus.getBandNumber() > 0); // both must be either words or non words simultaneously
        if (stimulus.getBandNumber() > 0) {
            assertEquals(bandNumber, stimulus.getBandNumber());
        } else {
            nonWordPresented = true;
        }
        String answer = stimulus.getBandNumber() > 0 ? this.answerNonWord : this.answerWord;
        //boolean correctness = this.provider.isCorrectResponse(stimulus, answer); // "pressing button" wrong
        boolean correctness = stimulus.isCorrect(answer);

        assertFalse(correctness);

        //checking recycling
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> tuple = this.provider.getFTtuple();
        ArrayList<AdVocAsStimulus> unused = new ArrayList<AdVocAsStimulus>();

        int currentBandNumber = this.provider.getCurrentBandFineTuning();
        AdVocAsStimulus unusedNonword = null;
        for (BookkeepingStimulus<AdVocAsStimulus> currentStimulus : tuple) {
            if (currentStimulus.getStimulus().getBandNumber() > 0) {
                unused.add(currentStimulus.getStimulus());
            } else {
                unusedNonword = currentStimulus.getStimulus();
            }
        }

        this.provider.hasNextStimulus(0);

        ArrayList<AdVocAsStimulus> nowNonwords = this.provider.getNonwords();
        ArrayList<AdVocAsStimulus> nowWords = this.provider.getWords().get(bandNumber - 1);

        boolean toBeContinued = this.provider.getFTtoBeContinued();

        if (toBeContinued) {

            if ((currentBandNumber > 1 && currentBandNumber < 50) || (currentBandNumber == 50 && !correctness)) { // when recycled and not used immediately
                if (oldSizeWords + unused.size() != nowWords.size()) {
                    System.out.println(currentBandNumber);
                }

                assertEquals(oldSizeWords + unused.size(), nowWords.size());
                for (int sC = 0; sC < unused.size(); sC++) {
                    assertEquals(unused.get(sC), nowWords.get(sC));
                }
                for (int sC = unused.size(); sC < nowWords.size(); sC++) {
                    assertEquals(prevWords.get(sC - unused.size()), nowWords.get(sC));
                }
            } else { //we are on the same band twice, put back to recycle and then use 3 stimuli for the next tuple 
                int diff = 3 - unused.size();
                assertEquals(oldSizeWords + unused.size() - 3, nowWords.size());
                for (int sC = 0; sC < nowWords.size(); sC++) {
                    assertEquals(prevWords.get(sC + diff), nowWords.get(sC));
                }
            }

        }

        if (nonWordPresented) {
            if (toBeContinued) {
                assertEquals(oldSizeNonwords - 1, this.provider.getNonwords().size()); // gets next nonword on initialisation 
                for (int sC = 0; sC < nowNonwords.size(); sC++) {
                    assertEquals(prevNonwords.get(sC + 1), nowNonwords.get(sC));
                }
            } else {
                assertEquals(oldSizeNonwords, this.provider.getNonwords().size());  // nonword list is not changed because initialisation of the next is not called
                for (int sC = 0; sC < nowNonwords.size(); sC++) {
                    assertEquals(prevNonwords.get(sC), nowNonwords.get(sC));
                }
            }
        } else {
            if (toBeContinued) {
                assertEquals(oldSizeNonwords, nowNonwords.size()); // recycles but uses again for the next tuple 
                for (int sC = 0; sC < nowNonwords.size(); sC++) {
                    assertEquals(prevNonwords.get(sC), nowNonwords.get(sC));
                }
            } else {
                assertEquals(oldSizeNonwords + 1, nowNonwords.size()); // recycled is not used
                for (int sC = 0; sC < nowNonwords.size() - 1; sC++) {
                    assertEquals(prevNonwords.get(sC), nowNonwords.get(sC + 1));
                }
            }
        }

        if (toBeContinued) {
            ArrayList<BookkeepingStimulus<AdVocAsStimulus>> newTuple = this.provider.getFTtuple();
            if (unusedNonword != null) {
                for (BookkeepingStimulus<AdVocAsStimulus> st : newTuple) {
                    if (st.getStimulus().getBandNumber() <= 0) {
                        assertEquals(unusedNonword, st.getStimulus());
                    }
                }
            }

            if (this.provider.getCurrentBandFineTuning() == currentBandNumber) {

                if (unused.size() > 0) {

                    ArrayList<AdVocAsStimulus> newWords = new ArrayList<AdVocAsStimulus>();
                    for (BookkeepingStimulus<AdVocAsStimulus> st : newTuple) {
                        if (st.getStimulus().getBandNumber() > 0) {
                            newWords.add(st.getStimulus());
                        }
                    }

                    if (this.provider.getCurrentStimulus().getBandNumber() > 0) {
                        assertEquals(unused.get(0), this.provider.getCurrentStimulus());
                        // the first unused timulus went to the show again
                        for (int sC = 1; sC < unused.size(); sC++) {
                            assertEquals(unused.get(sC), newWords.get(sC - 1));
                            sC++;
                        }
                    } else {
                       for (int sC = 0; sC < unused.size(); sC++) {
                            assertEquals(unused.get(sC), newWords.get(sC));
                            sC++;
                        } 
                    }

                }

            }

        }

        this.provider.nextStimulus(0);
    }

    private void correctFTtuple(int bandNumber, int fTuningShablonCounter, boolean[] currentSjablon) {
        AdVocAsStimulus stimulus;
        for (int tC = 0; tC < 4; tC++) {
            assertEquals(fTuningShablonCounter, this.provider.getFineTuningShablonPosition());

            stimulus = this.provider.getCurrentStimulus();
            assertEquals(currentSjablon[tC], stimulus.getBandNumber() > 0); // both must be either words or non words simultaneously
            if (stimulus.getBandNumber() > 0) {
                assertEquals(bandNumber, stimulus.getBandNumber());
            }

            //this.provider.isCorrectResponse(stimulus, stimulus.getCorrectResponses()); // pressing button
            stimulus.isCorrect(stimulus.getCorrectResponses());
            this.provider.hasNextStimulus(0); // here we analyse the correctenss of the previous step a
            this.provider.nextStimulus(0);
        }
    }

    // HELPER 
    // run after FastTrack helpers 
    private void checkFineTuningLoop(int startBandNumber, int wrongPosition, int loopStartBand) {

        System.out.println("fine tuning cycle");

        boolean fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        assertTrue(fineTuningToBecontinued);

        int fTuningShablonCounter = 0;
        ArrayList<FineTuningShablonElement> sceletoneFR = this.provider.getFineTuningSceletone();
        boolean[] currentSjablon = sceletoneFR.get(0).getArray();
        ArrayList<Integer> bNumbers = new ArrayList<Integer>();
        // here everything is correct
        //loopStartBand = X
        for (int bN = startBandNumber; bN <= loopStartBand; bN++) {
            bNumbers.add(bN);
            this.correctFTtuple(bN, fTuningShablonCounter, currentSjablon);
            fTuningShablonCounter++;
            currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        }

        // now we make oscillations
        // band X+1; make error on tc = wrongPosition, part 2 of the loop; must be an error
        assertEquals(loopStartBand + 1, this.provider.getCurrentBandFineTuning());
        this.wrongFTtuple(loopStartBand + 1, wrongPosition, fTuningShablonCounter, currentSjablon);
        bNumbers.add(loopStartBand + 1);

        // back to band X; // part 3 of the loop; must be correct
        fTuningShablonCounter++;
        currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        assertEquals(loopStartBand, this.provider.getCurrentBandFineTuning());
        this.correctFTtuple(loopStartBand, fTuningShablonCounter, currentSjablon);
        bNumbers.add(loopStartBand);

        // back to band X+1; part 4 of the loop; must be an error
        fTuningShablonCounter++;
        currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        assertEquals(loopStartBand + 1, this.provider.getCurrentBandFineTuning());
        this.wrongFTtuple(loopStartBand + 1, wrongPosition, fTuningShablonCounter, currentSjablon);
        bNumbers.add(loopStartBand + 1);

        // band X again, correct but we do not check loops on correct answers
        fTuningShablonCounter++;
        currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        assertEquals(loopStartBand, this.provider.getCurrentBandFineTuning());
        this.correctFTtuple(loopStartBand, fTuningShablonCounter, currentSjablon);
        bNumbers.add(loopStartBand);

        // X+1
        fTuningShablonCounter++;
        currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        assertEquals(loopStartBand + 1, this.provider.getCurrentBandFineTuning());
        this.wrongFTtuple(loopStartBand + 1, wrongPosition, fTuningShablonCounter, currentSjablon);
        bNumbers.add(loopStartBand + 1);

        assertFalse(this.provider.getFTtoBeContinued());
        assertEquals(loopStartBand, this.provider.getBandScore()); //lowest in the loop
        assertFalse(this.provider.getChampion());
        assertFalse(this.provider.getLooser());
        assertTrue(this.provider.getCycle());
        assertFalse(this.provider.getIsTimeOut());
    }

    /**
     * Test of fastTrackToBeContinued method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testFastTrackToBeContinuedAllCorrect_FTuningAllCorrect() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus, all correct responses, also switch to fine tuning ");
        this.provider.initialiseStimuliState("");
        this.provider.hasNextStimulus(0);
        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());
        boolean fastTrackOn = this.provider.getIsFastTrackIsStillOn();
        int stimulusIndex = 0;
        int fastTrackShablonPosition = 0;
        while (fastTrackOn) {
            assertEquals(fastTrackShablonPosition, this.provider.getFastTrackShablonIndex());
            assertEquals(stimulusIndex, this.provider.getCurrentStimulusIndex());
            AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
            String corrAnswer = stimulus.getCorrectResponses();
            //this.provider.isCorrectResponse(stimulus, corrAnswer); // like pressing button
            stimulus.isCorrect(corrAnswer);

            // memoise previous correct band
            int previousLastCorrect = this.provider.getLastCorrrectFastTrackBand();
            this.provider.hasNextStimulus(0); // here we analyse the correctenss of the previous step and update last correct fast track 
            if (stimulus.getBandNumber() > 0) {
                assertEquals(stimulus.getBandNumber(), this.provider.getLastCorrrectFastTrackBand());
                if (previousLastCorrect > 0) {
                    assertEquals(previousLastCorrect + 1, this.provider.getLastCorrrectFastTrackBand());
                }

            } else {
                assertEquals(previousLastCorrect, this.provider.getLastCorrrectFastTrackBand());
            }

            this.provider.nextStimulus(0); // does nothing
            stimulusIndex++;
            fastTrackShablonPosition++;
            fastTrackOn = this.provider.getIsFastTrackIsStillOn();
        }

        assertEquals(50, this.provider.getLastCorrrectFastTrackBand());

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();

        int fastTrackLength = record.size() - 1; // the last stimulus in the record is the first one in fine tuning because of "hasNextStimulus" structure
        assertEquals(48, fastTrackLength);

        // testing first fine tuning tuple
        this.checkingInitialisedFineTuningTuple(50);

        // all answers are correct 
        this.checkFineTuningAllCorrect(50);
    }

    /**
     * Test of fastTrackToBeContinued method, of class AdVocAsStimuliProvider.
     */
    private void checkFastTrackToBeContinuedWrongOnWordButCorrected() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus,  incorrect word corrected");
        this.provider.initialiseStimuliState("");
        boolean eval;

        // shablon position, 1 is a word of band 16//
        // stimulusIndex 0
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());
        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());

        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord);
        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(this.answerWord);
        assertTrue(eval);

        // shablon position, 2 is a word of band 17//
        // stimulusIndex 1
        this.provider.hasNextStimulus(0);
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand()); // here we evaluate the previous step to see where to go next

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // mistake !
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(this.answerNonWord);
        assertFalse(eval);

        // corrector,  must be a word of band 21 //
        // stimulusIndex 2
        this.provider.hasNextStimulus(0); // here we evaluate the previous step to see where to go next
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // correcting
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(this.answerWord);
        assertTrue(eval);

        // shablon position 3 (normal flow) which is a non-word
        // stimulusIndex 3
        this.provider.hasNextStimulus(0); // here we evaluate the previous step to see where to go next
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(2, this.provider.getFastTrackShablonIndex());
        assertEquals(0, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // correct answer normal flow
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(this.answerNonWord);
        assertTrue(eval);

        this.provider.hasNextStimulus(0);
        int stimulusIndex = 4;
        int shablonPosition = 3;

        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());  // there was a non-word previosu step, so we do not increase last correct band 
        this.provider.nextStimulus(0);
        boolean fastTrackOn = this.provider.getIsFastTrackIsStillOn();

        // the rest of the answers are all correct
        while (fastTrackOn) {
            assertEquals(stimulusIndex, this.provider.getCurrentStimulusIndex());
            assertEquals(shablonPosition, this.provider.getFastTrackShablonIndex());

            stimulus = this.provider.getCurrentStimulus();
            String corrAnswer = stimulus.getCorrectResponses();
            //this.provider.isCorrectResponse(stimulus, corrAnswer); // "pressing button" 
            stimulus.isCorrect(corrAnswer);

            // memoise previous last correct band
            int previousCorrectBand = this.provider.getLastCorrrectFastTrackBand();
            this.provider.hasNextStimulus(0); // here we evaluate the correctness and prepare the next stimulus
            if (stimulus.getBandNumber() > 0) {
                assertEquals(stimulus.getBandNumber(), this.provider.getLastCorrrectFastTrackBand());
                assertEquals(previousCorrectBand + 1, this.provider.getLastCorrrectFastTrackBand());
            } else {
                assertEquals(previousCorrectBand, this.provider.getLastCorrrectFastTrackBand());
            }

            this.provider.nextStimulus(0);
            fastTrackOn = this.provider.getIsFastTrackIsStillOn();
            stimulusIndex++;
            shablonPosition++;
        }

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        ArrayList<FastTrackShablonElement> sceletone = this.provider.getFastTrackSceletone();
        assertEquals(48, sceletone.size());

        int fastTrackLength = record.size() - 1; // the last stimulus in the record is the first one in fine tuning because of "hasNextStimulus" structure
        assertEquals(48, fastTrackLength - 1); // there was 1 extra erroneous step in records
        // reached the last band
        assertEquals(50, this.provider.getLastCorrrectFastTrackBand());

        for (int i = 0; i < fastTrackLength; i++) {
            if (i + 1 != 2) {
                assertTrue(record.get(i).getCorrectness());
            } else {
                assertFalse(record.get(i).getCorrectness());  // mistake on the second stimulus
            }

        }

        for (int i = 2; i < fastTrackLength; i++) {
            if (sceletone.get(i - 1).getIsWord()) { // after mistake the record is one stimulus ahead of the sceletone
                assertEquals(record.get(i).getStimulus().getBandNumber(), sceletone.get(i - 1).getBandNumber());
            } else {
                assertEquals(0, record.get(i).getStimulus().getBandNumber());
            }
        }

        this.checkingInitialisedFineTuningTuple(50);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordButCorrected_FTallWrong_onPos0() {
        this.checkFastTrackToBeContinuedWrongOnWordButCorrected();
        this.checkFineTuningAllWrong(50, 0);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordButCorrected_FTallWrong_onPos1() {
        this.checkFastTrackToBeContinuedWrongOnWordButCorrected();
        this.checkFineTuningAllWrong(50, 1);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordButCorrected_FTallWrong_onPos2() {
        this.checkFastTrackToBeContinuedWrongOnWordButCorrected();
        this.checkFineTuningAllWrong(50, 2);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordButCorrected_FTallWrong_onPos3() {
        this.checkFastTrackToBeContinuedWrongOnWordButCorrected();
        this.checkFineTuningAllWrong(50, 3);
    }

    /**
     * Test of fastTrackToBeContinued method, of class AdVocAsStimuliProvider.
     */
    public void checkFastTrackToBeContinuedWrongOnWordNotCorrected() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus, 1 incorrect word not corrected");
        this.provider.initialiseStimuliState("");
        boolean eval;

        // shablon position, 1 is a word of band 16//
        // stimulusIndex 0
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());
        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord);
        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon position, 2 is a word of band 17//
        // stimulusIndex 1
        this.provider.hasNextStimulus(0);
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // mistake !
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerNonWord);
        assertFalse(eval);

        // corrector,  must be again a word of band 17 //
        // stimulusIndex 2
        this.provider.hasNextStimulus(0);
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // again wrong!
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerNonWord);
        assertFalse(eval);

        this.provider.hasNextStimulus(0); // has next stimulus analyses the previous response to provide a switch; it also put the firs fine-tuning stimulus on records.
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertFalse(this.provider.getIsFastTrackIsStillOn());

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        assertEquals(4, record.size());
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.checkingInitialisedFineTuningTuple(16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_AllFineTuningCorrect() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected();
        this.checkFineTuningAllCorrect(16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTallWrong_onPos0() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected();
        this.checkFineTuningAllWrong(16, 0);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTallWrong_onPos1() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected();
        this.checkFineTuningAllWrong(16, 1);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTallWrong_onPos2() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected();
        this.checkFineTuningAllWrong(16, 2);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTallWrong_onPos3() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected();
        this.checkFineTuningAllWrong(16, 3);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos0_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 0, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos1_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 1, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos2_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 2, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos3_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 3, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos0_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 0, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos1_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 1, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos2_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 2, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos3_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 3, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos0_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 0, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos1_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 1, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos2_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 2, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnWordNotCorrected_FTCycle2_wrongOnPos3_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnWordNotCorrected(); // last correct fast track band is 20
        this.checkFineTuningLoop(16, 3, 49);
    }

    /**
     * HELPER
     */
    public void checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus,  error on the first word not corrected");
        this.provider.initialiseStimuliState("");
        boolean eval;

        // shablon position, 1 is a word of band 16//
        // stimulusIndex 0
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // mistake
        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerNonWord);
        assertFalse(eval);

        // shablon position, 2 is a word of band 17//
        // stimulusIndex 1
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // again mistake !
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerNonWord);
        assertFalse(eval);

        this.provider.hasNextStimulus(0); // has next stimulus analyses the previous response to provide a switch; it also put the firs fine-tuning stimulus on records.
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertFalse(this.provider.getIsFastTrackIsStillOn());

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        assertEquals(3, record.size());
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());

        this.checkingInitialisedFineTuningTuple(16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnFirstWordNotCorrected_FineTuningAllCorrect() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningAllCorrect(16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos0_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 0, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos1_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 1, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos2_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 2, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos3_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 3, 16);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos0_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 0, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos1_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 1, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos2_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 2, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos3_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 3, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos0_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 0, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos1_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 1, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos2_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 2, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongWrongOnFirstWordNotCorrected_FTCycle2_wrongOnPos3_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnFirstWordNotCorrected();
        this.checkFineTuningLoop(16, 3, 49);
    }

    /**
     * Test of fastTrackToBeContinued method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordButCorrected() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus, error on a nonword, and corrected");
        this.provider.initialiseStimuliState("");
        boolean eval;

        // shablon index 0 is a word of band 16//
        // stimulusIndex 0
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());
        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord);  // correct
        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon index 1 is a word of band 17//
        // stimulusIndex 1
        this.provider.hasNextStimulus(0);
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // correct
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon index 2 is a nonword  //
        // stimulusIndex 2
        this.provider.hasNextStimulus(0);
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(2, this.provider.getFastTrackShablonIndex());
        assertEquals(0, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // mistake on a nonword
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertFalse(eval);

        // shablon index 3 is a word
        // stimulusIndex 3
        this.provider.hasNextStimulus(0);
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(3, this.provider.getFastTrackShablonIndex()); // shablon index move forward 
        assertEquals(18, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // correct
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon index 4, is a word of 19
        // stimulusIndex 4
        this.provider.hasNextStimulus(0);
        assertEquals(18, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(4, this.provider.getFastTrackShablonIndex());
        assertEquals(19, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // correct
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);
        assertTrue(this.provider.getIsFastTrackIsStillOn());

        // shablon index 5, is a word of 24
        // stimulusIndex 5
        this.provider.hasNextStimulus(0);
        assertEquals(19, this.provider.getLastCorrrectFastTrackBand());
        int stimulusIndex = 5;
        int shablonPosition = 5;

        this.provider.nextStimulus(0);
        boolean fastTrackOn = this.provider.getIsFastTrackIsStillOn();

        while (fastTrackOn) {
            assertEquals(stimulusIndex, this.provider.getCurrentStimulusIndex());
            assertEquals(shablonPosition, this.provider.getFastTrackShablonIndex());

            stimulus = this.provider.getCurrentStimulus();
            String corrAnswer = stimulus.getCorrectResponses();
            //this.provider.isCorrectResponse(stimulus, corrAnswer); // "pressing button"
            stimulus.isCorrect(corrAnswer);

            // memoise previous last correct band
            int previousCorrectBand = this.provider.getLastCorrrectFastTrackBand();
            this.provider.hasNextStimulus(0); // here we evaluate the correctness and prepare the next stimulus
            if (stimulus.getBandNumber() > 0) {
                assertEquals(stimulus.getBandNumber(), this.provider.getLastCorrrectFastTrackBand());
                assertEquals(previousCorrectBand + 1, this.provider.getLastCorrrectFastTrackBand());
            } else {
                assertEquals(previousCorrectBand, this.provider.getLastCorrrectFastTrackBand());
            }

            this.provider.nextStimulus(0);
            fastTrackOn = this.provider.getIsFastTrackIsStillOn();
            stimulusIndex++;
            shablonPosition++;
        }

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        ArrayList<FastTrackShablonElement> sceletone = this.provider.getFastTrackSceletone();
        assertEquals(48, sceletone.size());

        int fastTrackLength = record.size();
        assertEquals(48, fastTrackLength - 1); // the last step is already a fine tuning stimulus
        // reached the last band
        assertEquals(50, this.provider.getLastCorrrectFastTrackBand());

        for (int i = 1; i <= fastTrackLength - 1; i++) { // exclude fine tuning stimulus
            if (i != 3) {
                assertTrue(record.get(i - 1).getCorrectness());
            } else {
                assertFalse(record.get(i - 1).getCorrectness());  // mistake on the second stimulus
            }
            if (sceletone.get(i - 1).getIsWord()) {
                assertEquals(record.get(i - 1).getStimulus().getBandNumber(), sceletone.get(i - 1).getBandNumber());
            } else {
                assertEquals(0, record.get(i - 1).getStimulus().getBandNumber());
            }
        }

        this.checkingInitialisedFineTuningTuple(50);

    }

    // HELPER
    private void checkFastTrackToBeContinuedWrongOnNonWordNotCorrected() {
        System.out.println("fastTrackToBeContinued via hasNextStimulus, error on non-word, not corrected");
        this.provider.initialiseStimuliState("");
        boolean eval;

        // shablon index 0 is a word of band 16//
        // stimulusIndex 0
        this.provider.hasNextStimulus(0);
        assertEquals(0, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(0, this.provider.getFastTrackShablonIndex());
        assertEquals(16, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord);  // correct
        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon index 1 is a word of band 17//
        // stimulusIndex 1
        this.provider.hasNextStimulus(0);
        assertEquals(16, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(1, this.provider.getFastTrackShablonIndex());
        assertEquals(17, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // correct
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertTrue(eval);

        // shablon index 2 is a nonword  //
        // stimulusIndex 2
        this.provider.hasNextStimulus(0);
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(2, this.provider.getFastTrackShablonIndex());
        assertEquals(0, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerWord); // mistake on a nonword
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerWord);
        assertFalse(eval);

        // corrector a word of band 18
        // stimulusIndex 3
        this.provider.hasNextStimulus(0);
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertEquals(3, this.provider.getFastTrackShablonIndex()); // shablon index move forward 
        assertEquals(18, this.provider.getCurrentStimulus().getBandNumber());
        //eval = this.provider.isCorrectResponse(this.provider.getCurrentStimulus(), this.answerNonWord); // again mistake
        stimulus = this.provider.getCurrentStimulus();
        eval = stimulus.isCorrect(answerNonWord);
        assertFalse(eval);

        this.provider.hasNextStimulus(0); // has next stimulus analyses the previous response to provide a switch; it also put the firs fine-tuning stimulus on records.
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.provider.nextStimulus(0);
        assertFalse(this.provider.getIsFastTrackIsStillOn());

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> record = this.provider.getRecords();
        assertEquals(5, record.size()); // every hasNextStimulus puts a stimulus on top of the records
        assertEquals(17, this.provider.getLastCorrrectFastTrackBand());

        this.checkingInitialisedFineTuningTuple(17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_AllFineTuningCorrect() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningAllCorrect(17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTallWrong_onPos0() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningAllWrong(17, 0);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTallWrong_onPos1() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningAllWrong(17, 1);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTallWrong_onPos2() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningAllWrong(17, 2);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTallWrong_onPos3() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningAllWrong(17, 3);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos0_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 0, 17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos1_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 1, 17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos2_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 2, 17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos3_loopStart16() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 3, 17);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos0_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 0, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos1_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 1, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos2_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 2, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos3_loopStart26() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 3, 26);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos0_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 0, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos1_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 1, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos2_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 2, 49);
    }

    @Test
    public void testFastTrackToBeContinuedWrongOnNonWordNotCorrected_FTCycle2_wrongOnPos3_loopStart49() {
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 3, 49);
    }

    /**
     * Test of isTimeOut method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testIsTimeOut_FineTuning_everythingIsCorrect() throws InterruptedException {
        System.out.println("isTimeOut");
        this.provider.setmaxDurationMinutes("2");
        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected(); // stops fast track at band 21

        boolean fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        assertTrue(fineTuningToBecontinued);

        int fTuningShablonCounter = 0;
        ArrayList<FineTuningShablonElement> sceletoneFR = this.provider.getFineTuningSceletone();
        boolean[] currentSjablon = sceletoneFR.get(0).getArray();

        // everything is correct
        for (int bN = 16; bN <= 26; bN++) {
            this.correctFTtuple(bN, fTuningShablonCounter, currentSjablon);
            fTuningShablonCounter++;
            currentSjablon = sceletoneFR.get(fTuningShablonCounter).getArray();
        }

        Thread.sleep(120000);
        assertEquals(fTuningShablonCounter, this.provider.getFineTuningShablonPosition());

        AdVocAsStimulus stimulus = this.provider.getCurrentStimulus();
        assertEquals(currentSjablon[0], stimulus.getBandNumber() > 0); // both must be either words or non words simultaneously
        if (stimulus.getBandNumber() > 0) {
            assertEquals(27, stimulus.getBandNumber());
        }

        //this.provider.isCorrectResponse(stimulus, stimulus.getCorrectResponses()); // pressing button
        stimulus.isCorrect(stimulus.getCorrectResponses());
        this.provider.hasNextStimulus(0); // here we analyse the correctenss of the previous step or define timeOuts
        this.provider.nextStimulus(0);

        fineTuningToBecontinued = this.provider.getFTtoBeContinued();
        assertFalse(fineTuningToBecontinued);
        assertTrue(this.provider.getIsTimeOut());
        assertFalse(this.provider.getChampion());
        assertFalse(this.provider.getLooser());
        assertFalse(this.provider.getCycle());
        System.out.println("Band score: " + this.provider.getBandScore());

    }

    /**
     * Test of getStimuliReport method, of class AdVocAsStimuliProvider.
     *
     */
    @Ignore
    @Test
    public void testGetStimuliReport() {
        System.out.println("getStimuliReport");

        this.checkFastTrackToBeContinuedWrongOnNonWordNotCorrected();
        this.checkFineTuningLoop(17, 1, 26);

        Map<String, String> result = this.provider.getStimuliReport("user_summary");
        Set<String> keys = result.keySet();
        // header (1 row) + data (1 row)
        assertTrue(keys.size() == 2);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index > -1);
        }
        String[] header = result.get("row000000").split(";");
        assertEquals("Score", header[0]);
        assertEquals("BestFastTrack", header[1]);
        assertEquals("Cycle2oscillation", header[2]);
        String[] data = result.get("row000001").split(";");
        assertEquals("26", data[0]);
        assertEquals("17", data[1]);
        assertEquals("true", data[2]);

        result = this.provider.getStimuliReport("fast_track");
        keys = result.keySet();
        // header (1 row) + data (4 rows, correct, correct, imcorrect, incorrect)
        assertTrue(keys.size() == 5);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index > -1);
        }

        header = result.get("row000000").split(";");
        assertEquals("Spelling", header[0]);
        assertEquals("BandNumber", header[1]);
        assertEquals("UserAnswer", header[2]);

        data = result.get("row000001").split(";");
        assertEquals("megrohad", data[0]);
        assertEquals("16", data[1]);
        assertEquals("JA, ik ken dit woord", data[2]);

        data = result.get("row000002").split(";");
        assertEquals("evenveel", data[0]);
        assertEquals("17", data[1]);
        assertEquals("JA, ik ken dit woord", data[2]);

        data = result.get("row000003").split(";");
        assertEquals("kruffen", data[0]);
        assertEquals("0", data[1]);
        assertEquals("JA, ik ken dit woord", data[2]);

        data = result.get("row000004").split(";");
        assertEquals("wederzien", data[0]);
        assertEquals("18", data[1]);
        assertEquals("NEE, ik ken dit woord niet", data[2]);

        result = this.provider.getStimuliReport("fine_tuning");
        keys = result.keySet();
        // header + data
        assertTrue(keys.size() >= 1);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index > -1);
        }
    }

    /**
     * Test of getPercentageBandTable method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetPercentageBandTable() {
        System.out.println("getPercentageBandTable");
        this.provider.initialiseStimuliState("");
        LinkedHashMap<Long, Integer> table = provider.getPercentageBandTable();
        assertEquals(11, table.size());

        Long[] keys = table.keySet().toArray(new Long[0]);
        assertTrue(1 == keys[0]);
        assertTrue(10 == keys[1]);
        assertTrue(20 == keys[2]);
        assertTrue(30 == keys[3]);
        assertTrue(40 == keys[4]);
        assertTrue(50 == keys[5]);
        assertTrue(60 == keys[6]);
        assertTrue(70 == keys[7]);
        assertTrue(80 == keys[8]);
        assertTrue(90 == keys[9]);
        assertTrue(99 == keys[10]);

        assertEquals(1, table.get(keys[0]).intValue());
        assertEquals(5, table.get(keys[1]).intValue());
        assertEquals(10, table.get(keys[2]).intValue());
        assertEquals(15, table.get(keys[3]).intValue());
        assertEquals(20, table.get(keys[4]).intValue());
        assertEquals(25, table.get(keys[5]).intValue());
        assertEquals(30, table.get(keys[6]).intValue());
        assertEquals(35, table.get(keys[7]).intValue());
        assertEquals(40, table.get(keys[8]).intValue());
        assertEquals(45, table.get(keys[9]).intValue());
        assertEquals(50, table.get(keys[10]).intValue());
    }

    /**
     * Test of bandNumberIntoPercentage method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testBandNumberIntoPercentage() {
        this.provider.initialiseStimuliState("");
        assertEquals(0L, provider.bandNumberIntoPercentage(0));

        assertEquals(100L, provider.bandNumberIntoPercentage(50));
        assertEquals(54L, provider.bandNumberIntoPercentage(27));
        assertEquals(36L, provider.bandNumberIntoPercentage(18));
    }

    /**
     * Test of percentageIntoBandNumber method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testPercentageIntoBandNumber() {
        System.out.println("percentageIntoBandNumber");
        assertEquals(0, provider.percentageIntoBandNumber(0));
        assertEquals(50, provider.percentageIntoBandNumber(100));
        assertEquals(49, provider.percentageIntoBandNumber(98));
        assertEquals(25, provider.percentageIntoBandNumber(50));
        assertEquals(25, provider.percentageIntoBandNumber(49));
        assertEquals(5, provider.percentageIntoBandNumber(10));
        assertEquals(1, provider.percentageIntoBandNumber(1));
        assertEquals(1, provider.percentageIntoBandNumber(2));
    }

    /**
     * Test of toBeContinuedLoopAndLooserChecker method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testToBeContinuedLoopAndLooserChecker() {
        System.out.println("toBeContinuedLoopAndLooserChecker");
        this.provider.initialiseStimuliState("");
        assertTrue(provider.toBeContinuedLoopAndLooserChecker());
    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testDetectLoop() {
        this.provider.initialiseStimuliState("");
        System.out.println("detectLoop");
        Integer[] arr1 = {42, 43, 42, 43, 42, 43, 42};
        boolean result1 = AdVocAsStimuliProvider.detectLoop(arr1);
        assertEquals(true, result1);
        Integer[] arr2 = {42, 43, 42, 43, 42, 43, 45};
        boolean result2 = AdVocAsStimuliProvider.detectLoop(arr2);
        assertEquals(false, result2);
        Integer[] arr3 = {43, 42, 43, 42, 43, 42, 45, 42};
        boolean result3 = AdVocAsStimuliProvider.detectLoop(arr3);
        assertEquals(false, result3);

        Integer[] arr4 = {8, 6, 8, 6, 8};
        boolean result4 = AdVocAsStimuliProvider.detectLoop(arr4);
        assertTrue(result4);

        Integer[] arr5 = {9, 8, 9, 8, 9};
        boolean result5 = AdVocAsStimuliProvider.detectLoop(arr5);
        assertTrue(result5);
    }

    /**
     * Test of shiftFIFO method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testShiftFIFO() {
        System.out.println("shiftFIFO");
        this.provider.initialiseStimuliState("");
        Integer[] fifo = {0, 1, 2, 3, 4, 5, 6};
        int newelement = 7;
        AdVocAsStimuliProvider.shiftFIFO(fifo, newelement);
        for (Integer i = 0; i < 7; i++) {
            assertEquals(new Integer(i + 1), fifo[i]);
        }
    }

    /**
     * Test of mostOftenVisitedBandIndex method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testMostOftenVisitedBandIndex() {
        System.out.println("mostOftenVisitedBandIndex");
        this.provider.initialiseStimuliState("");
        Integer[] visitCounter = {1, 3, 2, 3, 3, 3, 1};
        // indices {1,3,4,5}
        // ind = 1, indSym = 2
        int currentIndex1 = 2; // at 2
        int bandIndex1 = this.provider.mostOftenVisitedBandIndex(visitCounter, currentIndex1);
        assertEquals(3, bandIndex1);

        int currentIndex2 = 3; // at 3
        int bandIndex2 = this.provider.mostOftenVisitedBandIndex(visitCounter, currentIndex2);
        assertEquals(3, bandIndex2);
    }

    @Test
    public void testGetStringSummaryMethod() {
        this.provider.initialiseStimuliState("");

        String startRow = "";
        String endRow = "\n";
        String startColumn = "";
        String endColumn = ";";
        String result = this.provider.getStringSummary(startRow, endRow, startColumn, endColumn);
        String expResult = "Score;BestFastTrack;Cycle2oscillation;EnoughFineTuningStimuli;Champion;Looser;\n1;NA;false;true;false;false;\n";
        assertEquals(expResult, result);

    }

    /**
     * Test of getStringFastTrack method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGetStringFastTrack() {
        System.out.println("getStringFastTrack");
        String startRow = "";
        String endRow = "";
        String startColumn = "";
        String endColumn = "";
        AdVocAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.getStringFastTrack(startRow, endRow, startColumn, endColumn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringFineTuningHistory method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGetStringFineTuningHistory() {
        System.out.println("getStringFineTuningHistory");
        String startRow = "";
        String endRow = "";
        String startColumn = "";
        String endColumn = "";
        String format = "";
        AdVocAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.getStringFineTuningHistory(startRow, endRow, startColumn, endColumn, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHtmlStimuliReport method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGetHtmlStimuliReport() {
        System.out.println("getHtmlStimuliReport");
        AdVocAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.getHtmlStimuliReport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPercentageScore method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGetPercentageScore() {
        System.out.println("getPercentageScore");
        AdVocAsStimuliProvider instance = null;
        long expResult = 0L;
        long result = instance.getPercentageScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateDiagramSequence method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGenerateDiagramSequence() {
        System.out.println("generateDiagramSequence");
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> records = null;
        LinkedHashMap<Long, Integer> percentageBandTable = null;
        AdVocAsStimuliProvider instance = null;
        LinkedHashMap<Long, String> expResult = null;
        LinkedHashMap<Long, String> result = instance.generateDiagramSequence(records, percentageBandTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveSampleWords method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testRetrieveSampleWords() {
        System.out.println("retrieveSampleWords");
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> records = null;
        ArrayList<ArrayList<AdVocAsStimulus>> nonusedWords = null;
        AdVocAsStimuliProvider instance = null;
        LinkedHashMap<Integer, String> expResult = null;
        LinkedHashMap<Integer, String> result = instance.retrieveSampleWords(records, nonusedWords);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testToString() {
        System.out.println("toString");
        AdVocAsStimuliProvider instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deserialise method, of class AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testDeserialise() throws Exception {
        System.out.println("deserialise");
        String str = "";
        AdVocAsStimuliProvider instance = null;
        instance.deserialise(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBandIndexScoreByVisits method, of class
     * AdVocAsStimuliProvider.
     */
    @Ignore
    @Test
    public void testGetBandIndexScoreByVisits() {
        System.out.println("getBandIndexScoreByVisits");
        Integer[] visitCounter = null;
        AdVocAsStimuliProvider instance = null;
        int expResult = 0;
        int result = instance.getBandIndexScoreByVisits(visitCounter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private ArrayList<AdVocAsStimulus> freshCopy(ArrayList<AdVocAsStimulus> list) {
        ArrayList<AdVocAsStimulus> retVal = new ArrayList<AdVocAsStimulus>();
        for (AdVocAsStimulus stimulus : list) {
            retVal.add(stimulus);
        }
        return retVal;
    }
}
