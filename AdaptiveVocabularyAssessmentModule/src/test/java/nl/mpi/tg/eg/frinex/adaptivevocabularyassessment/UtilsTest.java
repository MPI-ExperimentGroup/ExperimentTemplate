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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import utils.VocabularyFromFiles;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuningBookkeepingStimulus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class UtilsTest {
    
    public UtilsTest() {
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
     * Test of testPrint method, of class Utils.
     */
    @Test
    public void testTestPrint() {
        System.out.println("testPrint");
        VocabularyFromFiles bands = null;
        //Utils.testPrint(bands);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of writeCsvFileFastTrack method, of class Utils.
     */
    @Test
    public void testWriteCsvFileFastTrack() throws Exception {
        System.out.println("writeCsvFileFastTrack");
        ArrayList<AtomBookkeepingStimulus> stimulae = null;
        int stopBand = 0;
        //Utils.writeCsvFileFastTrack(stimulae, stopBand);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of writeCsvFileFineTuningPreset method, of class Utils.
     */
    @Test
    public void testWriteCsvFileFineTuningPreset() throws Exception {
        System.out.println("writeCsvFileFineTuningPreset");
        ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae = null;
        //Utils.writeCsvFileFineTuningPreset(stimulae);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of orderFineTuningHistory method, of class Utils.
     */
    @Test
    public void testOrderFineTuningHistory() {
        System.out.println("orderFineTuningHistory");
        //ArrayList<ArrayList<FineTuningStimulus>> stimulae = null;
        ArrayList<FineTuningBookkeepingStimulus> expResult = null;
        //ArrayList<FineTuningStimulus> result = Utils.orderFineTuningHistory(stimulae);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of insertSortFineTuningHistory method, of class Utils.
     */
    @Test
    public void testInsertSortFineTuningHistory() {
        System.out.println("insertSortFineTuningHistory");
        ArrayList<FineTuningBookkeepingStimulus> stimulae = null;
        FineTuningBookkeepingStimulus stimulus = null;
        //Utils.insertSortFineTuningHistory(stimulae, stimulus);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of writeCsvFileFineTuningHistoryShortened method, of class Utils.
     */
    @Test
    public void testWriteCsvFileFineTuningHistoryShortened() throws Exception {
        System.out.println("writeCsvFileFineTuningHistoryShortened");
        ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae = null;
        //Utils.writeCsvFileFineTuningHistoryShortened(stimulae);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
