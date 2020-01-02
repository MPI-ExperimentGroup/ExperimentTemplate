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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.antisaccade;

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
public class AntisaccadeStimuliFromStringTest {
    
    public AntisaccadeStimuliFromStringTest() {
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
     * Test of parseTrialsStringIntoXml method, of class AntisaccadeStimuliFromString.
     */
    @Test
    public void testParseTrialsStringIntoXml() throws Exception {
        System.out.println("parseTrialsStringIntoXml");
        AntisaccadeStimuliFromString instance = new AntisaccadeStimuliFromString();
        String result2 = instance.parseTrialsStringIntoXml(Antisaccade.CSV_STRING);
        assertTrue(result2.startsWith("<stimulus "));
        assertTrue(result2.endsWith(" />\n"));
        System.out.println(result2);
    }
    
}
