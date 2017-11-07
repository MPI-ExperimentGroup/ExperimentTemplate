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

import java.util.ArrayList;
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
public class SeriesTest {
    
    public SeriesTest() {
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
     * Test of fetchUnusedAtoms method, of class Series.
     */
    @Test
    public void testFetchUnusedUnits_AtomStimulusArr() {
        System.out.println("fetchUnusedUnits");
        AtomBookkeepingStimulus[] units = null;
        Series instance = null;
        ArrayList<AtomBookkeepingStimulus> expResult = null;
        //ArrayList<AtomStimulus> result = instance.fetchUnusedAtoms(units);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fetchUnusedAtoms method, of class Series.
     */
    @Test
    public void testFetchUnusedUnits_ArrayList() {
        System.out.println("fetchUnusedUnits");
        ArrayList<AtomBookkeepingStimulus> units = null;
        Series instance = null;
        ArrayList<AtomBookkeepingStimulus> expResult = null;
        //ArrayList<AtomStimulus> result = instance.fetchUnusedAtomBookkeepingStimulus(units);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createStimulae method, of class Series.
     */
    @Test
    public void testCreateStimulae() throws Exception {
        System.out.println("createStimulae");
        Series instance = null;
        //instance.createStimulae();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class SeriesImpl extends Series {

        public SeriesImpl() {
            super("", null, null);
        }
    }
    
}
