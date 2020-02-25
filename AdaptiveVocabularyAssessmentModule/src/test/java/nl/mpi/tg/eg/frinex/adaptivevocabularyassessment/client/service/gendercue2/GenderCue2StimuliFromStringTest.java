/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.gendercue2;

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
public class GenderCue2StimuliFromStringTest {
    
    public GenderCue2StimuliFromStringTest() {
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
     * Test of parseTrialsAudioPicturesStringIntoXml method, of class GenderCue2StimuliFromString.
     */
    @Ignore
    @Test
    public void testParseTrialsAudioPicturesStringIntoXml() throws Exception {
        System.out.println("parseTrialsAudioPicturesStringIntoXml");
        String baseDir = "/Users/olhshk/Documents/ExperimentTemplate/gwt-cordova/src/main/static/grammatical_gender_cues_2/";
        String audioStimuliDir = "stimuli/";
        String pictureStimuliDir = "stimuli/";
        String codeStimuliDir = "stimuli/coded/";
        GenderCue2StimuliFromString instance = new GenderCue2StimuliFromString();
        String result = instance.parseTrialsAudioPicturesStringIntoXml(GenderCueCsv2.CSV, pictureStimuliDir, audioStimuliDir, codeStimuliDir, baseDir);
        System.out.println(result);
        assertTrue(result.startsWith("<stimulus "));
        assertTrue(result.endsWith(" />\n"));
    }
    
}
