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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.AdVocAsStimuliProvider;
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
public class AdVocAsStimuliProviderTest {

    public AdVocAsStimuliProviderTest() {
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
     * Test of estinItialiseStimuliState method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testItialiseStimuliState() {
        System.out.println("initialiseStimuliState");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");

        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.getWords();
        assertEquals(Constants.NUMBER_OF_BANDS, words.size());
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            assertEquals(Constants.WORDS_PER_BAND, words.get(i).size());
        }

        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        assertEquals(ConstantsNonWords.NONWORDS_ARRAY.length, nonwords.size());

        int expectedTotalsStimuli = Constants.NUMBER_OF_BANDS * Constants.WORDS_PER_BAND + ConstantsNonWords.NONWORDS_ARRAY.length;
        assertEquals(expectedTotalsStimuli, instance.getTotalStimuli());

        ArrayList<Integer> nonWordIndices = instance.getNonWordsIndices();
        int expectedWords = (Constants.NUMBER_OF_BANDS - Constants.START_BAND + 1) * 2;
        int expectedLength = (expectedWords * Constants.AVRERAGE_NON_WORD_POSITION) / (Constants.AVRERAGE_NON_WORD_POSITION - 1);
        int expectedNonwords = expectedLength / Constants.AVRERAGE_NON_WORD_POSITION;
        assertEquals(expectedNonwords, nonWordIndices.size());

    }

    /**
     * Test of getCurrentStimulusIndex method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusIndex() {
        System.out.println("getCurrentStimulusIndex");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        int result = instance.getCurrentStimulusIndex();
        assertEquals(0, result);
    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus1() {
        System.out.println("getCurrentStimulus1");
        this.testGetCurrentStimulus();

    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus2() {
        System.out.println("getCurrentStimulus2");
        this.testGetCurrentStimulus();

    }

    /**
     * Test of getCurrentStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus3() {
        System.out.println("getCurrentStimulus3");
        this.testGetCurrentStimulus();

    }

    private void testGetCurrentStimulus() {
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        assertEquals(1, instance.getResponseRecord().size());
        Stimulus stimulus = instance.getCurrentStimulus();
        assertTrue(stimulus != null);
        String label = stimulus.getLabel();
        assertTrue(label != null);
        System.out.println("Label: " + label);
        AdVocAsBookkeepingStimulus bStimulus = instance.getResponseRecord().get(instance.getCurrentStimulusIndex());
        int expectedBand = stimulus.getCorrectResponses().equals(Constants.WORD) ? Constants.START_BAND : -1;
        assertEquals(expectedBand, bStimulus.getBandNumber());
    }

    @Test
    public void testIsCorrectResponse() throws Exception {
        System.out.println("isCorrectResponse");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");

        //stimulus 1
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        Stimulus stimulus = instance.getCurrentStimulus();
        boolean result = instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());
        assertTrue(result);

        AdVocAsBookkeepingStimulus bStimulus = instance.getResponseRecord().get(0);
        assertTrue(bStimulus.getCorrectness());

        boolean expectedReaction = stimulus.getCorrectResponses().equals(Constants.WORD);
        assertEquals(expectedReaction, bStimulus.getReaction());

        // stimulus 2
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        Stimulus stimulus2 = instance.getCurrentStimulus();
        // making worng response
        String response2 = Constants.NONWORD;
        if (stimulus2.getCorrectResponses().equals(Constants.NONWORD)) {
            response2 = Constants.WORD;
        } else {
            if (!stimulus2.getCorrectResponses().equals(Constants.WORD)) {
                throw new Exception("The reaction is neither nonword nor word, something went terribly worng.");
            }
        }
        boolean result2 = instance.isCorrectResponse(stimulus2, response2);
        assertFalse(result2);

        AdVocAsBookkeepingStimulus bStimulus2 = instance.getResponseRecord().get(1);
        assertFalse(bStimulus2.getCorrectness());

        boolean expectedCorrectReaction2 = stimulus2.getCorrectResponses().equals(Constants.WORD);
        assertEquals(!expectedCorrectReaction2, bStimulus2.getReaction());

    }

    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testGetTotalStimuli() {
        System.out.println("getTotalStimuli");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        int totalStimuli = instance.getTotalStimuli();
        assertEquals(ConstantsNonWords.NONWORDS_ARRAY.length + Constants.NUMBER_OF_BANDS * Constants.WORDS_PER_BAND, totalStimuli);
    }

    /**
     * Test of getTotalStimuli method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void getStimuliReport() throws Exception {
        System.out.println("getStimuliReport");
        AdVocAsStimuliProvider instance = this.testHasNextStimulus();

        Map<String, String> result = instance.getStimuliReport("user_summary");
        Set<String> keys = result.keySet();
        // header + data
        assertTrue(keys.size() == 2);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index > -1);
        }

        result = instance.getStimuliReport("fast_track");
        keys = result.keySet();
        // header + data
        assertTrue(keys.size() > 2);
        for (String key : keys) {
            String row = result.get(key);
            int index = row.indexOf(";");
            assertTrue(index > -1);
        }

        result = instance.getStimuliReport("fine_tuning");
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
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus1() throws Exception {
        System.out.println("hasNextStimulus-1");
        this.testHasNextStimulus();
    }

    /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus2() throws Exception {
        System.out.println("hasNextStimulus-2");
        this.testHasNextStimulus();
    }

    /**
     * Test of hasNextStimulus method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testHasNextStimulus3() throws Exception {
        System.out.println("hasNextStimulus-3");
        this.testHasNextStimulus();
    }

   
    // also tests nextStimulus
    private AdVocAsStimuliProvider testHasNextStimulus() throws Exception {
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        boolean result = instance.hasNextStimulus(0);// does not depend on increment
        int invariant = instance.getResponseRecord().size() + instance.getNonwords().size() + this.getListOfListLength(instance.getWords());

        // but on internal state of the process
        assertTrue(result);

        instance.nextStimulus(0);
        assertEquals(1, instance.getResponseRecord().size());
        int invariant1 = instance.getResponseRecord().size() + instance.getNonwords().size() + this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant1);

        //experiment 0, correct answer
        int ind1 = instance.getCurrentStimulusIndex();
        assertEquals(0, ind1);
        BookkeepingStimulus stimulus = instance.getCurrentStimulus();

        instance.isCorrectResponse(stimulus, stimulus.getCorrectResponses());

        boolean result1 = instance.hasNextStimulus(0);
        assertTrue(result1);
        int expectedBand = stimulus.getCorrectResponses().equals(Constants.WORD) ? (Constants.START_BAND + 1) : Constants.START_BAND;
        assertEquals(expectedBand, instance.getCurrentBandNumber());

        instance.nextStimulus(0);
        assertEquals(2, instance.getResponseRecord().size());
        int invariant2 = instance.getResponseRecord().size() + instance.getNonwords().size() + this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant2);

        //experiment 1, wrong answer, second chance must be given
        int ind2 = instance.getCurrentStimulusIndex();
        assertEquals(1, ind2);

        BookkeepingStimulus stimulus2 = instance.getCurrentStimulus();
        String correctResponse = stimulus2.getCorrectResponses();
        String response = null;
        if (correctResponse.equals(Constants.WORD)) {
            response = Constants.NONWORD;
        }
        if (correctResponse.equals(Constants.NONWORD)) {
            response = Constants.WORD;
        }
        if (response == null) {
            throw new Exception("Wrong reaction");
        }

        instance.isCorrectResponse(stimulus2, response);
        boolean result12 = instance.hasNextStimulus(0);
        assertTrue(result12);
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        assertEquals(-1, instance.getBestFastTrackBand()); // stil on fast track, expecting the secind chance

        instance.nextStimulus(0); // give the second chance
        assertEquals(3, instance.getResponseRecord().size());
        int invariant3 = instance.getResponseRecord().size() + instance.getNonwords().size() + this.getListOfListLength(instance.getWords());
        assertEquals(invariant, invariant3);

        int ind3 = instance.getCurrentStimulusIndex();
        assertEquals(2, ind3);
        BookkeepingStimulus stimulus3 = instance.getCurrentStimulus();
        String correctResponse3 = stimulus3.getCorrectResponses();
        String response3 = null;
        if (correctResponse3.equals(Constants.WORD)) {
            response3 = Constants.NONWORD;
        }
        if (correctResponse3.equals(Constants.NONWORD)) {
            response3 = Constants.WORD;
        }
        if (response3 == null) {
            throw new Exception("Wrong reaction");
        }

        boolean result3 = instance.hasNextStimulus(0);
        assertTrue(result3);
        // now current band represents the last cirrect band on the fast track
        assertEquals(expectedBand, instance.getCurrentBandNumber());
        assertEquals(instance.getCurrentBandNumber(), instance.getBestFastTrackBand()); // stil on fast track, expecting the secind chance

        return instance;

    }

    /**
     * Test of getCurrentStimulusUniqueId method, of class
     * AdVocAsStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusUniqueId() {
        System.out.println("getCurrentStimulusUniqueId");
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        instance.initialiseStimuliState("");
        instance.hasNextStimulus(0);
        instance.nextStimulus(0);
        String result = instance.getCurrentStimulusUniqueId();
        assertTrue(result != null);
    }

    /**
     * Test of detectLoop method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testDetectLoop() {
        System.out.println("detectLoop");
        int[] arr1 = {42, 43, 42, 43, 42, 43, 42};
        boolean result1 = AdVocAsStimuliProvider.detectLoop(arr1);
        assertEquals(true, result1);
        int[] arr2 = {42, 43, 42, 43, 42, 43, 45};
        boolean result2 = AdVocAsStimuliProvider.detectLoop(arr2);
        assertEquals(false, result2);
        int[] arr3 = {43, 42, 43, 42, 43, 42, 45, 42};
        boolean result3 = AdVocAsStimuliProvider.detectLoop(arr3);
        assertEquals(false, result3);
    }

    /**
     * Test of shiftFIFO method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testShiftFIFO() {
        System.out.println("shiftFIFO");
        int[] fifo = {0, 1, 2, 3, 4, 5, 6};
        int newelement = 7;
        AdVocAsStimuliProvider.shiftFIFO(fifo, newelement);
        for (int i = 0; i < 7; i++) {
            assertEquals(i + 1, fifo[i]);
        }
    }

    /**
     * Test of shiftFIFO method, of class AdVocAsStimuliProvider.
     */
    @Test
    public void testMostOftenVisitedBandNumber() {
        System.out.println(" MostOftenVisitedBandMumber");
        int[] visitCounter = {1, 3, 2, 3, 3, 3, 1};
        // indices {1,3,4,5}
        // ind = 1, indSym = 2
        AdVocAsStimuliProvider instance = new AdVocAsStimuliProvider();
        int currentIndex1 = 2; // at 2
        int bandNumber1 = instance.mostOftenVisitedBandNumber(visitCounter, currentIndex1);
        assertEquals(4, bandNumber1);

        int currentIndex2 = 3; // at 3
        int bandNumber2 = instance.mostOftenVisitedBandNumber(visitCounter, currentIndex2);
        assertEquals(4, bandNumber2);
    }

    private int getListOfListLength(ArrayList<ArrayList<AdVocAsStimulus>> ll) {
        int retVal = 0;
        for (ArrayList<AdVocAsStimulus> l : ll) {
            retVal += l.size();
        }
        return retVal;
    }

    @Test
    public void testPercentageBAndTable() {
        System.out.println(" Test getBandNumberFromPercentage");

        AdVocAsStimuliProvider provider = new AdVocAsStimuliProvider();
        provider.initialiseStimuliState("");
        
        HashMap<Long, Integer> percentageTable = provider.getPercentageBandTable();
        int result1 = percentageTable.get(new Long(1));
        assertEquals(1, result1);
        long result2 = percentageTable.get(new Long(100));
        assertEquals(Constants.NUMBER_OF_BANDS, result2);
        if (Constants.NUMBER_OF_BANDS == 54) {
            long result3 = percentageTable.get(new Long(37));
            assertEquals(20, result3);
            long result4 = percentageTable.get(new Long(30));
            assertEquals(16, result4);
            long result5 = percentageTable.get(new Long(80));
            assertEquals(43, result5);
        } else {
            // add tests for other values of the number of bands
            assertTrue(false);
        }
        
    }

    @Test
    public void generalRandomTest() throws Exception {
        for (int i = 1; i < 11; i++) {
            double prob = 0.5 + i * 0.05;
            //System.out.println(prob);
            this.testRound(prob);
        }
    }

    @Test
    public void notEnoughStimuliTest() throws Exception {
        this.longFineTuningTest();
    }

    public AdVocAsStimuliProvider testRound(double prob) throws Exception {
        Random rnd = new Random();

        AdVocAsStimuliProvider provider = new AdVocAsStimuliProvider();
        provider.initialiseStimuliState("");

        boolean hasNextStimulus = provider.hasNextStimulus(0);
        int currentExperimentCount = 0;
        while (hasNextStimulus) {
            ArrayList<AdVocAsBookkeepingStimulus> ft = provider.getFTtuple();
            if (ft.size() == 4) {
                this.checkFreshFineTuningTuple(ft);
            }
            provider.nextStimulus(0);
            currentExperimentCount = provider.getCurrentStimulusIndex();
            //System.out.println(currentExperimentCount);
            BookkeepingStimulus stimulus = provider.getCurrentStimulus();
            String answer = this.probabilisticAnswerer(stimulus, prob, rnd);
            boolean isCorrect = provider.isCorrectResponse(stimulus, answer);
            hasNextStimulus = provider.hasNextStimulus(0);
        }

        this.checkAllWordsAreDifferent(provider.getResponseRecord());
        this.checkNonWordFrequenceFastTrack(provider.getResponseRecord(), provider.getEndFastTrackTimeTick());

        boolean enoughFineTuningStimulae = provider.getEnoughFinetuningStimuli();
        boolean cycle2 = provider.getCycel2();
        boolean champion = provider.getChampion();
        boolean looser = provider.getLooser();

        if (enoughFineTuningStimulae && !champion) { // the fine tunig has been stoppped because of false reaction and further checks (cycle-2 or looser)
            int recordSize = provider.getResponseRecord().size();
            AdVocAsBookkeepingStimulus lastStimulus = provider.getResponseRecord().get(recordSize - 1);
            assertFalse(lastStimulus.getCorrectness());
            assertTrue(cycle2 || looser);
            if (looser) {
                assertEquals(1, provider.getBandScore());
                assertEquals(2, provider.getPercentageScore()); // 100/54 = 1.85... rounded to two
            } else {
                assertTrue(provider.getPercentageScore() > 1);
            }

        }

        if (champion) {
            assertEquals(Constants.NUMBER_OF_BANDS, provider.getBandScore());
            assertEquals(100, provider.getPercentageScore());
        }

        this.checkFastTrack(provider.getResponseRecord(), provider.getEndFastTrackTimeTick(), provider.getBestFastTrackBand());
        this.checkFineTuning(provider.getResponseRecord(), provider.getEndFastTrackTimeTick(), provider.getBestFastTrackBand(), cycle2, provider.getBandScore());

        // checking generating graph
        // first check if the sample set is generated ok
        HashMap<Integer, String> samples = provider.retrieveSampleWords(provider.getResponseRecord());
        
        Vocabulary vocab = new Vocabulary();
        ArrayList<ArrayList<AdVocAsStimulus>> wordsInBands = vocab.initialiseWords(ConstantsWords.WORDS);
        
        assertEquals(Constants.NUMBER_OF_BANDS, samples.keySet().size());
        for (int bandNumber = 1; bandNumber <= Constants.NUMBER_OF_BANDS; bandNumber++) {
            assertTrue(samples.containsKey(bandNumber));
            String sample = samples.get(bandNumber);
            assertNotNull(sample);
            ArrayList<String> words = this.getSpellings(wordsInBands.get(bandNumber - 1));
            assertTrue(words.contains(sample));
        }

        // now check if the graph sequence for percentage is ok 
        HashMap<Long, String> graph = provider.generateDiagramSequence(provider.getResponseRecord());
        for (long p = Constants.START_PERCENTAGE_FOR_GRAPH; p <= 100; p = p+10) {
            assertTrue(graph.containsKey(p));
            assertNotNull(graph.get(p));
            int bandNumber = provider.getPercentageBandTable().get(p);
            ArrayList<String> words = this.getSpellings(wordsInBands.get(bandNumber - 1));
            assertTrue(words.contains(graph.get(p)));
        }
        long percentageScore = provider.getPercentageScore();
        assertTrue(graph.containsKey(percentageScore));

        return provider;
    }

    private ArrayList<String> getSpellings(ArrayList<AdVocAsStimulus> stimuli) {
        ArrayList<String> retVal = new ArrayList<>(stimuli.size());
        for (AdVocAsStimulus stimulus : stimuli) {
            retVal.add(stimulus.getLabel());
        }
        return retVal;
    }

    ; 

    public AdVocAsStimuliProvider longFineTuningTest() throws Exception {

        AdVocAsStimuliProvider provider = new AdVocAsStimuliProvider();
        provider.initialiseStimuliState("");

        // make to wrong answers to start fine tuning immediately
        provider.hasNextStimulus(0);
        provider.nextStimulus(0);
        BookkeepingStimulus stimulus = provider.getCurrentStimulus();
        String answer = this.makeResponseWrong(stimulus);
        boolean isCorrect = provider.isCorrectResponse(stimulus, answer);

        provider.hasNextStimulus(0);
        provider.nextStimulus(0);
        stimulus = provider.getCurrentStimulus();
        answer = this.makeResponseWrong(stimulus);
        isCorrect = provider.isCorrectResponse(stimulus, answer);

        boolean hasNextStimulus = provider.hasNextStimulus(0);
        // fine tuning correct till the band is 54 then back till the band is 20
        boolean runHigher = true;
        int tupleCounter = 0;
        while (hasNextStimulus) {

            ArrayList<AdVocAsBookkeepingStimulus> ft = provider.getFTtuple();
            if (ft.size() == 4) {
                this.checkFreshFineTuningTuple(ft);
                tupleCounter++;
            }

            if (runHigher && provider.getCurrentBandNumber() == 54) {
                runHigher = false; // start climbing backwards
            }
            if (!runHigher && provider.getCurrentBandNumber() == 20) {
                runHigher = true; // start climbing forward
            }
            provider.nextStimulus(0);
            stimulus = provider.getCurrentStimulus();
            if (runHigher) {
                answer = stimulus.getCorrectResponses();
            } else {
                answer = this.makeResponseWrong(stimulus);
            }
            isCorrect = provider.isCorrectResponse(stimulus, answer);
            hasNextStimulus = provider.hasNextStimulus(0);

        }

        this.checkAllWordsAreDifferent(provider.getResponseRecord());

        int minTuples = (9 / (Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1)) * (Constants.NUMBER_OF_BANDS - Constants.START_BAND);
        assertTrue(tupleCounter > minTuples);

        boolean enoughFineTuningStimulae = provider.getEnoughFinetuningStimuli();
        boolean cycle2 = provider.getCycel2();
        boolean champion = provider.getChampion();
        boolean looser = provider.getLooser();

        assertFalse(enoughFineTuningStimulae);
        assertFalse(cycle2);
        assertFalse(looser);
        assertFalse(champion);
        assertEquals(20, provider.getBestFastTrackBand());

        this.checkFastTrack(provider.getResponseRecord(), provider.getEndFastTrackTimeTick(), provider.getBestFastTrackBand());
        this.checkFineTuning(provider.getResponseRecord(), provider.getEndFastTrackTimeTick(), provider.getBestFastTrackBand(), cycle2, provider.getBandScore());

        return provider;

    }

    private String probabilisticAnswerer(BookkeepingStimulus stimulus, double correctnessUpperBound, Random rnd) throws Exception {
        String retVal = stimulus.getCorrectResponses();
        double rndDouble = rnd.nextDouble();
        //System.out.println("*****");
        //System.out.println(retVal);
        //System.out.println(rndDouble);
        if (rndDouble > correctnessUpperBound) { // spoil the answer
            if (retVal.equals(Constants.WORD)) {
                retVal = Constants.NONWORD;
            } else {
                if (retVal.equals(Constants.NONWORD)) {
                    retVal = Constants.WORD;
                } else {
                    throw new Exception("Wrong correct reaction in the stimulus, neither word, nor nonword: " + retVal);
                }
            }
        }
        //System.out.println(retVal);
        //System.out.println("*****");
        return retVal;
    }

    private String makeResponseWrong(BookkeepingStimulus stimulus) {
        String answer = Constants.NONWORD;
        if (stimulus.getCorrectResponses().equals(Constants.NONWORD)) {
            answer = Constants.WORD;
        };
        return answer;
    }

    private void checkFreshFineTuningTuple(ArrayList<AdVocAsBookkeepingStimulus> tuple) {
        int nNonwords = 0;
        for (AdVocAsBookkeepingStimulus stimulus : tuple) {
            assertEquals(null, stimulus.getReaction());
            assertEquals(null, stimulus.getCorrectness());
            assertNotEquals(0, stimulus.getBandNumber());
            if (stimulus.getBandNumber() < 0) {
                nNonwords++;
            }
        }
        assertEquals(1, nNonwords);
    }

    private void checkNonWordFrequenceFastTrack(ArrayList<AdVocAsBookkeepingStimulus> records, int timeTick) {
        int counterNonwords = 0;
        double frequency = 0;

        for (int i = 0; i <= timeTick; i++) {
            BookkeepingStimulus stimulus = records.get(i);
            if (stimulus.getBandNumber() == -1) {
                counterNonwords++;
            }
            frequency = ((double) counterNonwords) / ((double) (i + 1));
        }
        if (timeTick >= 3) {
            assertTrue(frequency > 0);
        }
        double idealFrequency = 1.0 / (double) (Constants.AVRERAGE_NON_WORD_POSITION);
        double diff = Math.abs(frequency - idealFrequency);
        System.out.println(frequency);
        System.out.println(idealFrequency);
        if (timeTick >= 12) {
            assertTrue(diff <= 0.2);
        }
    }

    private void checkAllWordsAreDifferent(ArrayList<AdVocAsBookkeepingStimulus> records) {
        int sz = records.size();
        HashSet<AdVocAsBookkeepingStimulus> testEqualitySet = new HashSet(records);
        assertEquals(testEqualitySet.size(), sz);
        assertEquals(sz, records.size());
    }

    private void checkFastTrack(ArrayList<AdVocAsBookkeepingStimulus> records, int lastTimeTickFastTrack, int bestFastTrackBand) {
        AdVocAsBookkeepingStimulus stimulus = records.get(0);
        AdVocAsBookkeepingStimulus previousStimulus;
        if (stimulus.getBandNumber() > 0) {
            assertEquals(Constants.START_BAND, stimulus.getBandNumber());
        }
        for (int i = 1; i <= lastTimeTickFastTrack; i++) {
            previousStimulus = stimulus;
            stimulus = records.get(i);
            if (previousStimulus.getCorrectness()) { // correcr reaction
                if (previousStimulus.getBandNumber() > 0 && stimulus.getBandNumber() > 0 && previousStimulus.getBandNumber() < Constants.NUMBER_OF_BANDS) {
                    assertEquals(previousStimulus.getBandNumber() + 1, stimulus.getBandNumber());
                }
            } else {
                if (i >= 2) { // check pre-previous answer
                    boolean prepreCorrectness = records.get(i - 2).getCorrectness();
                    if (prepreCorrectness) {
                        // we had the first incorrect answer in a row coming to this band
                        // this is the second chance stimulus
                        if (previousStimulus.getBandNumber() > 0 && stimulus.getBandNumber() > 0) {
                            // second chance
                            assertEquals(previousStimulus.getBandNumber(), stimulus.getBandNumber());
                        }
                    } else {
                        // preprevious and previous reaction were wrong!!!
                        // we have proceeded after the second wrong answer in a row, it should not happen!!
                        assertTrue(false);
                    }
                }
            }
        }

        stimulus = records.get(lastTimeTickFastTrack);
        if (stimulus.getCorrectness()) {
            // we stopped fast track because we have reached the end of the bands
            if (stimulus.getBandNumber() > 0) {
                assertEquals(Constants.NUMBER_OF_BANDS, stimulus.getBandNumber());
            }
        } else {
            // we stopped because there were two incorrect answers in a row
            previousStimulus = records.get(lastTimeTickFastTrack - 1);
            assertFalse(previousStimulus.getCorrectness());
            if (lastTimeTickFastTrack >= 2) {
                assertTrue(records.get(lastTimeTickFastTrack - 2).getCorrectness());
            }
        }
        if (stimulus.getBandNumber() > 0) {
            assertEquals(bestFastTrackBand, stimulus.getBandNumber());
        }

    }

    private void checkFineTuning(ArrayList<AdVocAsBookkeepingStimulus> records, int lastTimeTickFastTrack, int bestFastTrackBand, boolean cycle2, int score) {
        int counterInTuple = 0;
        AdVocAsBookkeepingStimulus stimulus;
        ArrayList<Integer> bandSequence = new ArrayList<>();
        ArrayList<Boolean> bandSwitchReason = new ArrayList<>();
        int currentBandNumber = -1;

        for (int i = lastTimeTickFastTrack + 1; i < records.size(); i++) {
            stimulus = records.get(i);
            if (stimulus.getBandNumber() > 0) {
                currentBandNumber = stimulus.getBandNumber();
            }
            if (stimulus.getCorrectness()) {
                counterInTuple++;
                if (counterInTuple == Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE) {
                    // all  4 ccorrect answers in a row
                    // they all must be in 1 band (except the nonword)
                    bandSequence.add(currentBandNumber);
                    bandSwitchReason.add(true);
                    int nonWordCounter = 0;

                    // check the tuple
                    for (int j = 0; j < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; j++) {
                        if (records.get(i - j).getBandNumber() > 0) {
                            // all words in the tuple must be in one band
                            assertEquals(currentBandNumber, records.get(i - j).getBandNumber());
                        } else {
                            nonWordCounter++;
                        }
                    }
                    // there must be exactly one nonword per tuple
                    assertEquals(1, nonWordCounter);

                    // initialise next step
                    currentBandNumber = -1;
                    counterInTuple = 0;
                }
            } else { // wrong answer -- switch band to the lower one
                bandSequence.add(currentBandNumber);
                bandSwitchReason.add(false);
                // initialise next step
                counterInTuple = 0;
                currentBandNumber = -1;
            }
        }

        // correction for the cases where the first answer in the tuple was wrong
        // and it was a non-word, so no band is detected
        for (int i = 0; i < bandSequence.size(); i++) {
            if (bandSequence.get(i) < 0) { // have not been defined
                if (i > 0) {
                    if (bandSwitchReason.get(i - 1)) { // the reason for changing for the current band from the previous one 
                        //was all 4 atoms correct
                        if (bandSequence.get(i - 1) < Constants.NUMBER_OF_BANDS) {
                            bandSequence.set(i, bandSequence.get(i - 1) + 1);
                        } else {
                            bandSequence.set(i, Constants.NUMBER_OF_BANDS);
                        }
                    } else {
                        // the reason for changing for the current band from the previous one 
                        //was an error of the previous band
                        if (bandSequence.get(i - 1) > 1) {
                            bandSequence.set(i, bandSequence.get(i - 1) - 1);
                        } else {
                            bandSequence.set(i, 1);
                        }
                    }
                } else { // we are on the first band and it is undefined
                    bandSequence.set(0, bestFastTrackBand);
                }
            }
        }

        // fine tuning starting check
        assertEquals(bestFastTrackBand, bandSequence.get(0).intValue());

        // if the bands where changed correctly
        for (int i = 1; i < bandSequence.size(); i++) {

            if (bandSwitchReason.get(i - 1)) { // the reason for changing for the current band from the previous one 
                //was all 4 atoms correct
                if (bandSequence.get(i - 1) != Constants.NUMBER_OF_BANDS) {
                    assertEquals(bandSequence.get(i - 1) + 1, bandSequence.get(i).intValue());
                }
            } else {
                // the reason for changing for the current band from the previous one 
                //was an error of the previous band
                if (bandSequence.get(i - 1) != 1) {
                    assertEquals(bandSequence.get(i - 1) - 1, bandSequence.get(i).intValue());
                }
            }
        }

        if (cycle2) {
            int lastIndex = bandSequence.size() - 1;
            // ignore the last erronenous reaction
            assertEquals(bandSequence.get(lastIndex - 1).intValue(), bandSequence.get(lastIndex - 3).intValue());
            assertEquals(bandSequence.get(lastIndex - 1).intValue(), bandSequence.get(lastIndex - 5).intValue());
            assertEquals(bandSequence.get(lastIndex - 2).intValue(), bandSequence.get(lastIndex - 4).intValue());
            assertNotEquals(bandSequence.get(lastIndex - 1).intValue(), bandSequence.get(lastIndex - 2).intValue());
            int expectedScore = bandSequence.get(lastIndex - 1) < bandSequence.get(lastIndex - 2) ? bandSequence.get(lastIndex - 1) : bandSequence.get(lastIndex - 2);
            assertEquals(expectedScore, score);
        }

    }
}
