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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioIndexMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool.AudioPool2;
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
public class AudioUtilsTest {

    //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
    private final LinkedHashMap<String, WordType> map1 = createMap1();

    private LinkedHashMap<String, WordType> createMap1() {
        LinkedHashMap<String, WordType> retVal = new LinkedHashMap<String, WordType>();
        retVal.put("deerbal", WordType.NON_WORD);
        retVal.put("smoer", WordType.TARGET_NON_WORD);
        retVal.put("wijp", WordType.NON_WORD);
        return retVal;
    }

    //19	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
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

    private TrialTuple tuple;
    
    private ArrayList<String> indexMap;
    
    private int numberOfBands;

    public AudioUtilsTest() {
        
        
        //Trial(String word, String targetNonword, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String,WordType> targetNonWords, String BandLabel, int bandIndex, String dirName)
        //1	vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
        this.trials.add(0, new Trial("vloer", "smoer_10.wav", 1, TrialCondition.TARGET_ONLY, 3, map1, "10bD", 2,  "/1"));
        //19	kers	hers	1	Target-only	4 targetNonWords	geider	hers	atgraus	hamp
        this.trials.add(1, new Trial("kers", "hers_10.wav", 1, TrialCondition.TARGET_ONLY, 4, map2, "10bD", 2,  "/2"));
        //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
        this.trials.add(2, new Trial("vuur", "fjon_10.wav", 1, TrialCondition.TARGET_AND_FOIL, 5, map3, "10bD", 2,  "/3"));
        //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
        this.trials.add(3, new Trial("pop", "lop_10.wav", 1, TrialCondition.NO_TARGET, 4, map4, "10bD", 2,  "/2"));

       this.tuple = new TrialTuple(this.trials);
        
       this.indexMap = new ArrayList<String>(Arrays.asList(AudioIndexMap.INDEX_ARRAY));
       
       this.numberOfBands = this.indexMap.size();
        

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
     * Test of initMatrix method, of class AudioUtils.
     */
    @Test
    public void testInitMatrix() {
        System.out.println("initMatrix");
        int maxLength = 6;
        Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> result = AudioUtils.initMatrix(this.numberOfBands, maxLength);
        assertEquals(3, result.keySet().size());
        assertEquals(this.numberOfBands, result.get(TrialCondition.TARGET_ONLY).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(2).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(2).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(2).size());
        assertEquals(0, result.get(TrialCondition.TARGET_ONLY).get(0).get(0).size());
        assertEquals(0, result.get(TrialCondition.TARGET_ONLY).get(1).get(3).size());
        assertEquals(0, result.get(TrialCondition.TARGET_ONLY).get(2).get(4).size());
        assertEquals(0, result.get(TrialCondition.TARGET_ONLY).get(2).get(5).size());
        assertEquals(0, result.get(TrialCondition.TARGET_ONLY).get(2).get(6).size());

        assertEquals(this.numberOfBands, result.get(TrialCondition.NO_TARGET).size());
        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(0).size());
        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(1).size());
        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(2).size());
        assertEquals(0, result.get(TrialCondition.NO_TARGET).get(1).get(0).size());
        assertEquals(0, result.get(TrialCondition.NO_TARGET).get(1).get(3).size());
        assertEquals(0, result.get(TrialCondition.NO_TARGET).get(1).get(4).size());
        assertEquals(0, result.get(TrialCondition.NO_TARGET).get(1).get(5).size());
        assertEquals(0, result.get(TrialCondition.NO_TARGET).get(1).get(6).size());

        assertEquals(this.numberOfBands, result.get(TrialCondition.TARGET_AND_FOIL).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(0).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(1).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(2).size());
        assertEquals(0, result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(0).size());
        assertEquals(0, result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(3).size());
        assertEquals(0, result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(4).size());
        assertEquals(0, result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(5).size());
        assertEquals(0, result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(6).size());

    }

    /**
     * Test of makeClassifiedWordList method, of class AudioUtils.
     */
    @Test
    public void testMakeClassifiedWordList() {
        System.out.println("makeClassifiedWordList");

//vloer	smoer	1	Target-only	3 targetNonWords	deebral	smoer	wijp
        String[] wrds = {"deerbal", "smoer", "wijp"};
        String word = "vloer";
        String targetNonWord = "smoer";
        String foil = null;
        LinkedHashMap<String, WordType> result = AudioUtils.makeClassifiedWordList(wrds, word, targetNonWord, foil);
        assertEquals(WordType.NON_WORD, result.get(wrds[0]));
        assertEquals(WordType.TARGET_NON_WORD, result.get(wrds[1]));
        assertEquals(WordType.NON_WORD, result.get(wrds[2]));

        //107	vuur	fjon	1	Target+Foil	5 targetNonWords	fjodschelg	fjon	wisdaag	tuik	poks		fjodschelg
        String[] wrds5 = {"fjodschelg", "fjon", "wisdaag", "tuik", "poks"};
        String word5 = "vuur";
        String targetNonWord5 = "fjon";
        String foil5 = "fjodschelg";
        LinkedHashMap<String, WordType> result5 = AudioUtils.makeClassifiedWordList(wrds5, word5, targetNonWord5, foil5);
        assertEquals(WordType.FOIL, result5.get(wrds5[0]));
        assertEquals(WordType.TARGET_NON_WORD, result5.get(wrds5[1]));
        assertEquals(WordType.NON_WORD, result5.get(wrds5[2]));
        assertEquals(WordType.NON_WORD, result5.get(wrds5[3]));
        assertEquals(WordType.NON_WORD, result5.get(wrds5[4]));

        //156	pop	lop	1	NoTarget	4 targetNonWords	voorserm	muiland	fraal	kijn	
        String[] wrds4 = {"voorserm", "muiland", "fraal", "kijn"};
        String word4 = "pop";
        String targetNonWord4 = "lop";
        String foil4 = null;
        LinkedHashMap<String, WordType> result4 = AudioUtils.makeClassifiedWordList(wrds4, word4, targetNonWord4, foil4);
        assertEquals(WordType.NON_WORD, result4.get(wrds4[0]));
        assertEquals(WordType.NON_WORD, result4.get(wrds4[1]));
        assertEquals(WordType.NON_WORD, result4.get(wrds4[2]));
        assertEquals(WordType.NON_WORD, result4.get(wrds4[3]));
    }

    /**
     * Test of initialiseTrials method, of class AudioUtils.
     */
    @Test
    public void testInitialiseTrials() {
        System.out.println("initialiseTrials");
        String[] rows = AudioPool2.TRIAL_ROWS;
        int maxLength = 6;
        String dirName = "/audiosources";
        LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> result = AudioUtils.initialiseTrials(rows, this.numberOfBands, maxLength, dirName);
        assertEquals(3, result.keySet().size());

        assertEquals(this.numberOfBands, result.get(TrialCondition.NO_TARGET).size());
        assertEquals(this.numberOfBands, result.get(TrialCondition.TARGET_ONLY).size());
        assertEquals(this.numberOfBands, result.get(TrialCondition.TARGET_AND_FOIL).size());

        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(0).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(0).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(0).size());

        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(1).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(1).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(1).size());

        assertEquals(7, result.get(TrialCondition.NO_TARGET).get(2).size());
        assertEquals(7, result.get(TrialCondition.TARGET_ONLY).get(2).size());
        assertEquals(7, result.get(TrialCondition.TARGET_AND_FOIL).get(2).size());

        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(1).get(0).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(1).get(1).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(1).get(2).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(2).get(3).size()>0);
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(2).get(4).size()>0);
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(2).get(5).size()>0);
        assertTrue(result.get(TrialCondition.TARGET_ONLY).get(2).get(6).size()>0);

        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(0).get(3).size()>0);
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(0).get(4).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(1).get(1).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(1).get(2).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(3).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(4).isEmpty());
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(5).size()> 0);
        assertTrue(result.get(TrialCondition.TARGET_AND_FOIL).get(2).get(6).size()>0);

        assertTrue(result.get(TrialCondition.NO_TARGET).get(0).get(0).isEmpty());
        assertTrue(result.get(TrialCondition.NO_TARGET).get(0).get(1).isEmpty());
        assertTrue(result.get(TrialCondition.NO_TARGET).get(0).get(2).isEmpty());
        assertTrue(result.get(TrialCondition.NO_TARGET).get(2).get(3).size()>0);
        assertTrue(result.get(TrialCondition.NO_TARGET).get(2).get(4).size()> 0);
        assertTrue(result.get(TrialCondition.NO_TARGET).get(2).get(5).size()>0);
        assertTrue(result.get(TrialCondition.NO_TARGET).get(2).get(6).size()>0);

        //52;baf;baf_filler.wav;1;Target-only;3 words;bag_filler_10dB.wav;baf_filler_10dB.wav;beg_filler_10dB.wav;;;;10dB;
        Trial firstTrial = result.get(TrialCondition.TARGET_ONLY).get(2).get(3).get(0);
        assertEquals("baf", firstTrial.getWord());
        assertEquals("baf_filler.wav", firstTrial.getTargetNonWord());
        assertEquals(2, firstTrial.getBandIndex());
        assertEquals(TrialCondition.TARGET_ONLY, firstTrial.getCondition());
        assertEquals("/audiosources", firstTrial.getDirName());
        assertEquals(1, firstTrial.getNumberOfSyllables());
        assertEquals(3, firstTrial.getTrialLength());
        assertEquals(4, firstTrial.getStimuliList().size());
        assertEquals("baf_filler.wav", firstTrial.getStimuliList().get(0).getLabel());
        assertEquals("bag_filler_10dB.wav", firstTrial.getStimuliList().get(1).getLabel());
        assertEquals("baf_filler_10dB.wav", firstTrial.getStimuliList().get(2).getLabel());
        assertEquals("beg_filler_10dB.wav", firstTrial.getStimuliList().get(3).getLabel());
    
        for (TrialCondition cond : TrialCondition.values()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <= 6; j++) {
                    for (Trial trial : result.get(cond).get(i).get(j)) {
                        assertEquals(j, trial.getTrialLength());
                        assertEquals(i, trial.getBandIndex());
                        int bandIndex = this.indexMap.indexOf(trial.getBandLabel());
                        assertEquals(i, bandIndex);
                        assertEquals(cond, trial.getCondition());
                        assertEquals(trial.getTargetNonWord(), trial.getStimuliList().get(0).getLabel());
                        assertEquals(j + 1, trial.getStimuliList().size());
                        assertEquals("/audiosources", trial.getDirName());
                        assertEquals(1, trial.getNumberOfSyllables());
                    }
                }
            }
        }
    }

    /**
     * Test of initialiseAvailabilityList method, of class AudioUtils.
     */
    @Test
    public void testInitialiseAvailabilityList() {
        System.out.println("initialiseAvailabilityList");
        String[] rows = AudioPool2.TRIAL_ROWS;
        int maxLength = 6;
        String dirName = "/audiosources";
        int tupleSize = 4;

        UtilsList<Integer> utilInt = new UtilsList<Integer>();
        ArrayList<Integer> generatorSet = new ArrayList<Integer>(4);
        generatorSet.add(0, 3);
        generatorSet.add(1, 4);
        generatorSet.add(2, 5);
        generatorSet.add(3, 6);
        ArrayList<ArrayList<Integer>> lengthPermuations = utilInt.generatePermutations(generatorSet);

        UtilsList<TrialCondition> utilTrial = new UtilsList<TrialCondition>();
        ArrayList<TrialCondition> generatorSet2 = new ArrayList<TrialCondition>(4);
        generatorSet2.add(0, TrialCondition.TARGET_ONLY);
        generatorSet2.add(1, TrialCondition.TARGET_ONLY);
        generatorSet2.add(2, TrialCondition.NO_TARGET);
        generatorSet2.add(3, TrialCondition.TARGET_AND_FOIL);
        ArrayList<ArrayList<TrialCondition>> trialTypePermutations = utilTrial.generatePermutations(generatorSet2);

        for (int bandIndex = 0; bandIndex < this.numberOfBands; bandIndex++) {
            LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> matrix = AudioUtils.initialiseTrials(rows, this.numberOfBands, maxLength, dirName);
            ArrayList<PermutationPair> result = AudioUtils.initialiseAvailabilityList(matrix, lengthPermuations, trialTypePermutations, bandIndex, tupleSize);
            for (PermutationPair permPair : result) {
                for (int i = 0; i < tupleSize; i++) {
                    TrialCondition trialType = permPair.trialTypes.get(i);
                    Integer length = permPair.trialLengths.get(i);
                    ArrayList<Trial> possibilities = matrix.get(trialType).get(bandIndex).get(length);
                    assertTrue(possibilities.size() > 0);
                }
            }
        }
    }

    /**
     * Test of emptiedPossibilities method, of class AudioUtils.
     */
    @Test
    public void testEmptiedPossibilities() {
        System.out.println("emptiedPossibilities");
        String[] rows = AudioPool2.TRIAL_ROWS;
        int maxLength = 6;
        String dirName = "/audiosources";
        int tupleSize = 4;

        UtilsList<Integer> utilInt = new UtilsList<Integer>();
        ArrayList<Integer> generatorSet = new ArrayList<Integer>(4);
        generatorSet.add(0, 3);
        generatorSet.add(1, 4);
        generatorSet.add(2, 5);
        generatorSet.add(3, 6);
        ArrayList<ArrayList<Integer>> lengthPermuations = utilInt.generatePermutations(generatorSet);

        UtilsList<TrialCondition> utilTrial = new UtilsList<TrialCondition>();
        ArrayList<TrialCondition> generatorSet2 = new ArrayList<TrialCondition>(4);
        generatorSet2.add(0, TrialCondition.TARGET_ONLY);
        generatorSet2.add(1, TrialCondition.TARGET_ONLY);
        generatorSet2.add(2, TrialCondition.NO_TARGET);
        generatorSet2.add(3, TrialCondition.TARGET_AND_FOIL);
        ArrayList<ArrayList<TrialCondition>> trialTypePermutations = utilTrial.generatePermutations(generatorSet2);
        
        LinkedHashMap<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> matrix = AudioUtils.initialiseTrials(rows, this.numberOfBands, maxLength, dirName);
            
        for (int bandIndex = 0; bandIndex < this.numberOfBands; bandIndex++) {
            ArrayList<PermutationPair> available = AudioUtils.initialiseAvailabilityList(matrix, lengthPermuations, trialTypePermutations, bandIndex, tupleSize);
            ArrayList<PermutationPair> result = AudioUtils.emptiedPossibilities(available, matrix, bandIndex, tupleSize);
            assertTrue(result.isEmpty()); // nothing has been removed

            while (!available.isEmpty()) {
                PermutationPair permPair = available.get(0);
                boolean flag = false; // will be ste to true if one of the possibility lists is emptied 
                for (int i = 0; i < tupleSize; i++) {
                    TrialCondition trialType = permPair.trialTypes.get(i);
                    Integer length = permPair.trialLengths.get(i);
                    int lngth = matrix.get(trialType).get(bandIndex).get(length).size();
                    matrix.get(trialType).get(bandIndex).get(length).remove(lngth - 1);
                    if (lngth == 1) {
                        flag = true;
                    }
                }
                ArrayList<PermutationPair> currentResult = AudioUtils.emptiedPossibilities(available, matrix, bandIndex, tupleSize);
                
                if (flag) { // one of the used lists has been emptied
                    assertTrue(currentResult.size() > 0);
                }
                
                // soundness: all found possibilities are empty
                
                for (PermutationPair perm : currentResult) {
                    boolean notEmpty = true;
                    
                    for (int i = 0; i < tupleSize; i++) {
                        TrialCondition trialType = perm.trialTypes.get(i);
                        Integer length = perm.trialLengths.get(i);
                        notEmpty = notEmpty && (!matrix.get(trialType).get(bandIndex).get(length).isEmpty());
                    }
                    assertFalse(notEmpty); // there is at least one empty possibilities list
                }

                available.removeAll(currentResult);
                
                //completeness: no empty lists possibilities are missed
                for (PermutationPair perm : available) {
                    boolean notEmpty = true;
                    for (int i = 0; i < tupleSize; i++) {
                        TrialCondition trialType = perm.trialTypes.get(i);
                        Integer length = perm.trialLengths.get(i);
                        notEmpty = notEmpty && (!matrix.get(trialType).get(bandIndex).get(length).isEmpty());
                    }
                    assertTrue(notEmpty); // all possibilities can be instantiated
                }
                
                //System.out.println(available.size());

            }
        }

    }

}
