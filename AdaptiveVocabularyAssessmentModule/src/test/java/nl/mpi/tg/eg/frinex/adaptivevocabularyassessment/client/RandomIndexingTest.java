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
import java.util.HashSet;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
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
public class RandomIndexingTest {

    private final int numberOfBands = 54;
    private final int wordsPerBand = 40;

    public RandomIndexingTest() {
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
     * Test of updateAndGetIndices method, of class RandomIndexing.
     */
    @Test
    public void testUpdateAndGetIndices() {
        System.out.println("updateAndGetIndices");
        int startBand = 20;
        int nonWordsAvailable = 300;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator
        //public RandomIndexing(int startBand, int this.numberOfBands, int nonwordsPerBlock, int averageNonwordPosition, int nonwordsAvailable)
        RandomIndexing instance = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);
        ArrayList<Integer> result = instance.updateAndGetIndices();
        int allWords = (this.numberOfBands - startBand + 1) * 2; // 2 times on one band because of the second chance
        int expectedFastTrackSequenceLength = (allWords * 3) / 2;
        int expectedAmountOfNonWords = expectedFastTrackSequenceLength / 3;
        assertEquals(expectedAmountOfNonWords, result.size());
        HashSet<Integer> set = new HashSet(result);
        assertEquals(set.size(), result.size());
        for (Integer index : result) {
            assertTrue(index >= 0);
            assertTrue(index < expectedFastTrackSequenceLength);
        }
    }

    /**
     * Test of getIndices method, of class RandomIndexing.
     */
    @Test
    public void testGetIndices() {
        System.out.println("getIndices");
        int startBand = 20;
        int nonWordsAvailable = 100;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator
        RandomIndexing instance = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);
        ArrayList<Integer> result = instance.getIndices();
        assertEquals(null, result); // not initialised yet, look for testUpdateAndGetIndices for non empty index-list
    }

    /**
     * Test of updateFrequencesOfNonWordIndices method, of class RandomIndexing.
     */
    @Test
    public void testGetUpdateFrequencesOfNonWordIndices() {
        System.out.println("update and get FrequencesOfNonWordIndices");
        int startBand = 20;
        int nonWordsAvailable = 200;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator
        RandomIndexing instance = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);
        ArrayList<Integer> result = instance.updateAndGetIndices();
        instance.updateFrequencesOfNonWordIndices();
        double[] frequences = instance.getFrequencesOfNonWordindices();
        int allWords = ((this.numberOfBands - startBand) + 1) * 2;
        int expectedFastTrackSequenceLength = (allWords * 3) / 2;
        assertEquals(expectedFastTrackSequenceLength, frequences.length);
        double oneThird = 1.0 / 3.0;
        System.out.println(Math.abs(frequences[expectedFastTrackSequenceLength - 1] - oneThird));
        assertTrue(Math.abs(frequences[expectedFastTrackSequenceLength - 1] - oneThird) < 0.05);
    }

    /**
     * Test of getFastTrackSequenceLength method, of class RandomIndexing.
     */
    @Test
    public void testGetFastTrackSequenceLength() {
        System.out.println("getFastTrackSequenceLength");
        int startBand = 20;
        int nonWordsAvailable = 200;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator
        RandomIndexing instance = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);
        int allWords = ((this.numberOfBands - startBand) + 1) * 2;
        int expectedFastTrackSequenceLength = (allWords * 3) / 2;
        assertEquals(expectedFastTrackSequenceLength, instance.getFastTrackSequenceLength());
    }

    /**
     * Test of generateRandomArray method, of class RandomIndexing.
     */
    @Test
    public void testGenerateRandomArray1() {
        System.out.println("generateRandomArray");
        int numberOfSeries = 1;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        int n = wordsPerBandInSeries - 1;
        ArrayList<Integer> result = RandomIndexing.generateRandomArray(n);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(result.get(i));
        }
        HashSet<Integer> set = new HashSet(list);
        assertEquals(n, set.size()); // fails if the permutation is to short/long or gives repetitive values

        // checking if the Equality is implemented OK on Integers
        ArrayList<Integer> testEqualityList = new ArrayList<>(2);
        testEqualityList.add(1);
        testEqualityList.add(1);
        assertEquals(2, testEqualityList.size());
        HashSet<Integer> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());

    }

    /**
     * Test of generateRandomArray method, of class RandomIndexing.
     */
    @Test
    public void testGenerateRandomArray2() {
        System.out.println("generateRandomArray");
        int numberOfSeries = 2;
        int wordsPerBandInSeries = this.wordsPerBand / numberOfSeries;
        int n = wordsPerBandInSeries - 1;
        ArrayList<Integer> result = RandomIndexing.generateRandomArray(n);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(result.get(i));
        }
        HashSet<Integer> set = new HashSet(list);
        assertEquals(n, set.size()); // fails if the permutation is to short/long or gives repetitive values

        // checking if the Equality is implemented OK on Integers
        ArrayList<Integer> testEqualityList = new ArrayList<>(2);
        testEqualityList.add(1);
        testEqualityList.add(1);
        assertEquals(2, testEqualityList.size());
        HashSet<Integer> testEqualitySet = new HashSet(testEqualityList);
        assertEquals(1, testEqualitySet.size());

    }

    @Test
    public void testToString() throws Exception {
        System.out.println("toString");
        int startBand = 20;
        int nonWordsAvailable = 200;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator
        RandomIndexing instance = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);

//    private int fastTrackSequenceLength; //  N
//    private final int averageNonwordPosition; // n
//    private final int nonwordsPerBlock;
//    private final int numberOfNonwords; // [1/n * N]
//    private final int numberOfWords; // [(n-1)/n * N]
//    private ArrayList<Integer> randomIndices;
//    private double[] frequences;
        String fastTrackSequenceLengthStr = (new Integer(instance.getFastTrackSequenceLength())).toString();
        String numberOfNonwordsStr = (new Integer(instance.getNumberOfNonWords())).toString();
        String numberOfWordsStr = (new Integer(instance.getNumberOfWords())).toString();
        String expResult = "{fastTrackSequenceLength:{" + fastTrackSequenceLengthStr + "},averageNonwordPosition:{3},nonwordsPerBlock:{4},numberOfNonwords:{"
                + numberOfNonwordsStr + "},numberOfWords:{" + numberOfWordsStr + "}}";
        assertEquals(expResult, instance.toString());

        ArrayList<Integer> indx = instance.updateAndGetIndices();
        instance.updateFrequencesOfNonWordIndices();
        double[] fr = instance.getFrequencesOfNonWordindices();

        String frStr = UtilsJSONdialect.doubleArrayListToString(fr);
        String indxStr = (new UtilsJSONdialect<Integer>()).arrayListToString(indx);

        String expResult2 = "{fastTrackSequenceLength:{" + fastTrackSequenceLengthStr + "},averageNonwordPosition:{3},nonwordsPerBlock:{4},numberOfNonwords:{"
                + numberOfNonwordsStr + "},numberOfWords:{" + numberOfWordsStr + "},randomIndices:" + indxStr + ",frequences:" + frStr + "}";

        assertEquals(expResult2, instance.toString());
    }

    @Test
    public void testToObject() throws Exception {
        System.out.println("toObject");
        int startBand = 20;
        int nonWordsAvailable = 200;
        int averageNonwordPosition = 3;
        int nonWordsPerBlock = 4; // smotheness regulator

        RandomIndexing instanceHelper = new RandomIndexing(startBand, this.numberOfBands, nonWordsPerBlock, averageNonwordPosition, nonWordsAvailable);

        String fastTrackSequenceLengthStr = (new Integer(instanceHelper.getFastTrackSequenceLength())).toString();
        String numberOfNonwordsStr = (new Integer(instanceHelper.getNumberOfNonWords())).toString();
        String numberOfWordsStr = (new Integer(instanceHelper.getNumberOfWords())).toString();

        ArrayList<Integer> indx = instanceHelper.updateAndGetIndices();
        instanceHelper.updateFrequencesOfNonWordIndices();
        double[] fr = instanceHelper.getFrequencesOfNonWordindices();

        String frStr = UtilsJSONdialect.doubleArrayListToString(fr);
        String indxStr = (new UtilsJSONdialect<Integer>()).arrayListToString(indx);

        String input = "{fastTrackSequenceLength:{" + fastTrackSequenceLengthStr + "},averageNonwordPosition:{3},nonwordsPerBlock:{4},numberOfNonwords:{"
                + numberOfNonwordsStr + "},numberOfWords:{" + numberOfWordsStr + "},randomIndices:" + indxStr + ",frequences:" + frStr + "}";

        RandomIndexing generatedInstance = RandomIndexing.toObject(input);

        assertEquals(instanceHelper.getFastTrackSequenceLength(), generatedInstance.getFastTrackSequenceLength());

        for (int i = 0; i < fr.length; i++) {
            assertEquals(fr[i], (generatedInstance.getFrequencesOfNonWordindices())[i],0);
        }

        for (int i = 0; i < indx.size(); i++) {
            assertEquals(indx.get(i), (generatedInstance.getIndices()).get(i));
        }

        assertEquals(instanceHelper.getNumberOfNonWords(), generatedInstance.getNumberOfNonWords());
        assertEquals(instanceHelper.getNumberOfWords(), generatedInstance.getNumberOfWords());
    }

}
