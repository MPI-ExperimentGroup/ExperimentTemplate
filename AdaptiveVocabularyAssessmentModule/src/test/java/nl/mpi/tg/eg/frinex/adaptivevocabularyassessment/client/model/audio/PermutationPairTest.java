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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.AudioStimuliFromString;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class PermutationPairTest {

    private final String[] labelling = {"min10db", "min8db", "min6db", "min4db", "min2db", "zerodb", "plus2db", "plus4db", "plus6db", "plus8db", "plus10db"};
    private ArrayList<ArrayList<LinkedHashMap<TrialCondition, ArrayList<Trial>>>> trials; // shared between various permutation-pairs, reduced while it is used
    private final ArrayList<Integer> requiredLengths = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
    private final int maxTrialLength = 6;
    private final ArrayList<TrialCondition> requiredTrialTypes = new ArrayList<TrialCondition>(Arrays.asList(TrialCondition.TARGET_ONLY, TrialCondition.TARGET_AND_FOIL, TrialCondition.NO_TARGET, TrialCondition.NO_TARGET));
    private final int numberOfBands = 11;
    ArrayList<ArrayList<TrialCondition>> trialTypesPermutations;
    ArrayList<ArrayList<Integer>> trialLengtPermutations;

    public PermutationPairTest() {
        UtilsList<TrialCondition> utilTrials = new UtilsList<TrialCondition>();
        this.trialTypesPermutations = utilTrials.generatePermutations(this.requiredTrialTypes);

        UtilsList<Integer> utilSizes = new UtilsList<Integer>();
        this.trialLengtPermutations = utilSizes.generatePermutations(this.requiredLengths);

        ArrayList<Trial> trialsAsInCsv = AudioStimuliFromString.readTrialsAsCsv(labelling);
        this.trials = Trial.prepareTrialMatrix(trialsAsInCsv, this.numberOfBands, this.maxTrialLength);
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
     * Test of toString method, of class PermutationPair.
     */
    @Test
    public void testToString() {
        System.out.println("toString with empty trial List");
        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair instance = new PermutationPair(conditions, lengths, null);
        String expResult = "{trialConditions:{0:{NO_TARGET},1:{TARGET_AND_FOIL},2:{NO_TARGET},3:{TARGET_ONLY}},trialLengths:{0:{4},1:{3},2:{5},3:{6}}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toObject method, of class PermutationPair.
     */
    @Test
    public void testToObject() {
        System.out.println("toObject with empty trialList ");
        String str = "{trialConditions:{0:{NO_TARGET},1:{TARGET_AND_FOIL},2:{NO_TARGET},3:{TARGET_ONLY}},trialLengths:{0:{4},1:{3},2:{5},3:{6}}}";
        PermutationPair result = PermutationPair.toObject(str, null);

        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair expResult = new PermutationPair(conditions, lengths, null);
        assertEquals(expResult.getTrialConditions(), result.getTrialConditions());
    }

    /**
     * Test of getTrialConditions method, of class PermutationPair.
     */
    @Test
    public void testGetTrialConditions() {
        System.out.println("getTrialConditions");
        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair instance = new PermutationPair(conditions, lengths, null);
        ArrayList<TrialCondition> result = instance.getTrialConditions();
        for (int i = 0; i < conditions.size(); i++) {
            assertEquals(conditions.get(i), result.get(i));
        }
    }

    /**
     * Test of getTrialLengths method, of class PermutationPair.
     */
    @Test
    public void testGetTrialLengths() {
        System.out.println("getTrialLengths");
        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair instance = new PermutationPair(conditions, lengths, null);
        ArrayList<Integer> result = instance.getTrialLengths();
        for (int i = 0; i < lengths.size(); i++) {
            assertEquals(lengths.get(i), result.get(i));
        }
    }

    /**
     * Test of getTrials method, of class PermutationPair.
     */
    @Test
    public void testGetTrials() {
        System.out.println("getTrials");
        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair instance = new PermutationPair(conditions, lengths, null);
        ArrayList<ArrayList<Trial>> result = instance.getTrials();
        assertEquals(null, result);
        
        // add more
    }

    /**
     * Test of isAvailable method, of class PermutationPair.
     */
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        ArrayList<Integer> lengths = new ArrayList<Integer>(4);
        lengths.add(4);
        lengths.add(3);
        lengths.add(5);
        lengths.add(6);

        ArrayList<TrialCondition> conditions = new ArrayList<TrialCondition>(4);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_AND_FOIL);
        conditions.add(TrialCondition.NO_TARGET);
        conditions.add(TrialCondition.TARGET_ONLY);

        PermutationPair instance = new PermutationPair(conditions, lengths, null);
        assertFalse(instance.isAvailable());
    }

    /**
     * Test of initialiseAvailabilityList method, of class PermutationPair.
     */
    @Test
    public void testInitialiseAvailabilityList() {
        System.out.println("initialiseAvailabilityList");
        ArrayList<ArrayList<PermutationPair>> result = PermutationPair.initialiseAvailabilityList(this.trials, 
                this.trialLengtPermutations, 
                this.trialTypesPermutations, 
                this.numberOfBands);
        
        
    }

}
