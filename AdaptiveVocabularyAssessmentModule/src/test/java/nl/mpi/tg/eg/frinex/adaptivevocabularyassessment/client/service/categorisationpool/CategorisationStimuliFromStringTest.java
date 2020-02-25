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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.categorisationpool;

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
public class CategorisationStimuliFromStringTest {
    
    public CategorisationStimuliFromStringTest() {
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
     * Test of parseTrialsStringIntoXml method, of class CategorisationStimuliFromString.
     */
    @Ignore
    @Test
    public void testParseTrialsStringIntoXml() throws Exception {
        System.out.println("parseTrialsStringIntoXml");
        String baseDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/categorisation/";
        String sourcesStimuliDir = "stimuli/";
        CategorisationStimuliFromString instance = new CategorisationStimuliFromString();
        String result = instance.parseTrialsStringIntoXml(CategorisationCsv.CSV, sourcesStimuliDir, baseDir);
        System.out.println(result);
        assertTrue(result.startsWith("<stimulus "));
        assertTrue(result.endsWith(" />\n"));
    }
    
}
