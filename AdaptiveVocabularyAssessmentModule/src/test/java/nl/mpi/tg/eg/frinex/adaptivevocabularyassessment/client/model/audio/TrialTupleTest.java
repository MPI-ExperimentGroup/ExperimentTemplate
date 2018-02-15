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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioIndexMap;
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
public class TrialTupleTest {

    //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
    private final LinkedHashMap<String, WordType> map1 = createMap1();

    private LinkedHashMap<String, WordType> createMap1() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("deerbal", WordType.NON_WORD);
        retVal.put("smoer", WordType.TARGET_NON_WORD);
        retVal.put("wijp", WordType.NON_WORD);
        return retVal;
    }

    //2	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
    private final LinkedHashMap<String, WordType> map2 = createMap2();

    private LinkedHashMap<String, WordType> createMap2() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("geider", WordType.NON_WORD);
        retVal.put("hers", WordType.TARGET_NON_WORD);
        retVal.put("atgraus", WordType.NON_WORD);
        retVal.put("hamp", WordType.NON_WORD);
        return retVal;
    }

    //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
    private final LinkedHashMap<String, WordType> map3 = createMap3();

    private LinkedHashMap<String, WordType> createMap3() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("fjodschelg", WordType.FOIL);
        retVal.put("fjon", WordType.TARGET_NON_WORD);
        retVal.put("wisdaag", WordType.NON_WORD);
        retVal.put("tuik", WordType.NON_WORD);
        retVal.put("poks", WordType.NON_WORD);
        return retVal;
    }

    //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
    private final LinkedHashMap<String, WordType> map4 = createMap4();

    private LinkedHashMap<String, WordType> createMap4() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("voorserm", WordType.NON_WORD);
        retVal.put("muiland", WordType.NON_WORD);
        retVal.put("fraal", WordType.NON_WORD);
        retVal.put("kijn", WordType.NON_WORD);
        return retVal;
    }

    private final ArrayList<Trial> trials = new ArrayList<Trial>(4);

    private TrialTuple instance;
    
     private final ArrayList<String> indexMap;

    public TrialTupleTest() {
        
        this.indexMap = new ArrayList<String>(Arrays.asList(AudioIndexMap.INDEX_ARRAY));
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        //Trial(String word, String targetNonword, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String,WordType> targetNonWords, int bandNumber, String dirName)
        //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
        this.trials.add(0, new Trial("vloer", "smoer", 1, TrialCondition.TARGET_ONLY, 3, map1, "10dB", 2,  "/1"));
        //19	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
        this.trials.add(1, new Trial("kers", "hers", 1, TrialCondition.TARGET_ONLY, 4, map2, "10dB", 2, "/2"));
        //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
        this.trials.add(2, new Trial("vuur", "fjon", 1, TrialCondition.TARGET_AND_FOIL, 5, map3,"10dB", 2, "/3"));
        //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
        this.trials.add(3, new Trial("pop", "lop", 1, TrialCondition.NO_TARGET, 4, map4, "10dB", 2, "/2"));

        this.instance = new TrialTuple(this.trials);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of removeFirstAvailableStimulus method, of class TrialTuple.
     */
    @Test
    public void testRemoveFirstAvailableStimulus() {
        System.out.println("removeFirstAvailableStimulus");

        AudioAsStimulus result1 = this.instance.removeFirstAvailableStimulus();
        assertEquals("smoer", result1.getLabel());
        assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, result1.getWordType());
        assertEquals(null, result1.getCorrectResponses());
        assertEquals(null, result1.getReaction());
        assertEquals(null, result1.getCorrectness());

        AudioAsStimulus result2 = this.instance.removeFirstAvailableStimulus();
        assertEquals("deerbal", result2.getLabel());
        assertEquals(WordType.NON_WORD, result2.getWordType());
        assertEquals(null, result2.getCorrectResponses());
        assertEquals(null, result2.getReaction());
        assertEquals(null, result2.getCorrectness());
        result2.setReaction("YES");
        assertEquals(true, result2.getReaction());
        result2.setCorrectness(false);
        assertFalse(result2.getCorrectness());
        assertEquals("10dB", result2.getBandLabel());

        AudioAsStimulus result3 = this.instance.removeFirstAvailableStimulus();
        assertEquals("smoer", result3.getLabel());
        assertEquals(WordType.TARGET_NON_WORD, result3.getWordType());
        assertEquals("YES", result3.getCorrectResponses());
        assertEquals(null, result3.getReaction());
        assertEquals(null, result3.getCorrectness());

        AudioAsStimulus result4 = this.instance.removeFirstAvailableStimulus();
        assertEquals("wijp", result4.getLabel());
        assertEquals(WordType.NON_WORD, result4.getWordType());
        assertEquals(null, result4.getCorrectResponses());

        AudioAsStimulus result5 = this.instance.removeFirstAvailableStimulus();
        assertEquals("hers", result5.getLabel());
        assertEquals(WordType.EXAMPLE_TARGET_NON_WORD, result5.getWordType());
        assertEquals(null, result5.getCorrectResponses());

        AudioAsStimulus result6 = this.instance.removeFirstAvailableStimulus();
        assertEquals("geider", result6.getLabel());
        assertEquals(WordType.NON_WORD, result6.getWordType());
        assertEquals(null, result6.getCorrectResponses());
    }

 
    /**
     * Test of getCorrectness and setCorrectness method, of class TrialTuple.
     */
    @Test
    public void testGetSetCorrectness() {
        System.out.println("setCorrectness");
        assertNull(this.instance.getCorrectness());  // correctness is not set yet
        this.instance.setCorrectness(true);
        assertTrue(this.instance.getCorrectness());
        this.instance.setCorrectness(false);
        assertFalse(this.instance.getCorrectness());
    }

    /**
     * Test of isNotEmpty method, of class TrialTuple.
     */
    @Test
    public void testIsNotEmpty() {
        System.out.println("isNotEmpty");
        int allTogetherStimuli = 4 + 5 + 6 + 5;
        for (int count = 1; count <= allTogetherStimuli; count++) {
            assertTrue(this.instance.isNotEmpty());
            this.instance.removeFirstAvailableStimulus();
        }
        assertFalse(this.instance.isNotEmpty());
    }

    /**
     * Test of createTupleForBand method, of class TrialTuple.
     */
    @Test
    public void testCreateTuple() {
        System.out.println("createTuple");

        //PermutationPair(ArrayList<TrialCondition> trialTypes, ArrayList<Integer> trialLengths)
        ArrayList<TrialCondition> trialTypes = new ArrayList<TrialCondition>(4);
        trialTypes.add(0, TrialCondition.TARGET_ONLY);
        trialTypes.add(1, TrialCondition.NO_TARGET);
        trialTypes.add(2, TrialCondition.TARGET_AND_FOIL);
        trialTypes.add(3, TrialCondition.TARGET_ONLY);

        ArrayList<Integer> trailLength = new ArrayList<Integer>(4);
        trailLength.add(0, 4); // TARGET_ONLY
        trailLength.add(1, 4); // NO_TARGET
        trailLength.add(2, 5);
        trailLength.add(3, 3);

        PermutationPair permPair = new PermutationPair(trialTypes, trailLength);
        ArrayList<PermutationPair> availablePermutations = new ArrayList<PermutationPair>(1);
        availablePermutations.add(permPair);

        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> trialMatrix = new LinkedHashMap();
        // initialisation
        for (TrialCondition trialType : TrialCondition.values()) {
            ArrayList<ArrayList<ArrayList<Trial>>> matrix3 = new ArrayList<ArrayList<ArrayList<Trial>>>(3);
            trialMatrix.put(trialType, matrix3);
            for (int i = 0; i < 3; i++) {
                ArrayList<ArrayList<Trial>> matrix2 = new ArrayList<ArrayList<Trial>>();
                matrix3.add(i, matrix2); // band
                for (int j=0; j<=6; j++) {
                    ArrayList<Trial> matrix1 = new ArrayList<Trial>(); // length
                    matrix2.add(j, matrix1);
                }
            }
        }
        trialMatrix.get(TrialCondition.TARGET_ONLY).get(2).get(3).add(this.trials.get(0));
        trialMatrix.get(TrialCondition.TARGET_ONLY).get(2).get(4).add(this.trials.get(1));
        trialMatrix.get(TrialCondition.TARGET_AND_FOIL).get(2).get(5).add(this.trials.get(2));
        trialMatrix.get(TrialCondition.NO_TARGET).get(2).get(4).add(this.trials.get(3));

        int size = 4;
        int bandIndex = 2;
        TrialTuple result = TrialTuple.createTupleForBand(availablePermutations, trialMatrix, size, bandIndex);
        ArrayList<Trial> trialsTest = result.getTrials();
        
        assertEquals(TrialCondition.TARGET_ONLY, trialsTest.get(0).getCondition());
        assertEquals(4, trialsTest.get(0).getTrialLength());
        assertEquals("kers", trialsTest.get(0).getWord());
        assertEquals("hers", trialsTest.get(0).getTargetNonWord());
        
        assertEquals(TrialCondition.NO_TARGET, trialsTest.get(1).getCondition());
        assertEquals(4, trialsTest.get(1).getTrialLength());
        assertEquals("pop", trialsTest.get(1).getWord());
        assertEquals("lop", trialsTest.get(1).getTargetNonWord());
        
        assertEquals(TrialCondition.TARGET_AND_FOIL, trialsTest.get(2).getCondition());
        assertEquals(5, trialsTest.get(2).getTrialLength());
        assertEquals("vuur", trialsTest.get(2).getWord());
        assertEquals("fjon", trialsTest.get(2).getTargetNonWord());
        
        assertEquals(TrialCondition.TARGET_ONLY, trialsTest.get(3).getCondition());
        assertEquals(3, trialsTest.get(3).getTrialLength());
        assertEquals("vloer", trialsTest.get(3).getWord());
        assertEquals("smoer", trialsTest.get(3).getTargetNonWord());
    }

}
