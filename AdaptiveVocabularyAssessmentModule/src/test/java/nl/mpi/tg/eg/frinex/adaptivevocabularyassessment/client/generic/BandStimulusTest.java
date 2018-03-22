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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic;

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
public class BandStimulusTest {
    
    public BandStimulusTest() {
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
     * Test of getbandLabel method, of class BandStimulus.
     */
    @Ignore
    @Test
    public void testGetbandLabel() {
        System.out.println("getbandLabel");
        BandStimulus instance = null;
        String expResult = "bandLabel_20";
        String result = instance.getbandLabel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getbandIndex method, of class BandStimulus.
     */
    @Ignore
    @Test
    public void testGetbandIndex() {
        System.out.println("getbandIndex");
        BandStimulus instance = null;
        String expResult = "19";
        int result = instance.getbandIndex();
        assertEquals(expResult, result);
    }

  
    /**
     * Test of toString method, of class BandStimulus.
     */
    @Ignore
    @Test
    public void testToString() {
        System.out.println("toString");
        BandStimulus instance = null;
        String expResult = "label_0000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

   
    
}
