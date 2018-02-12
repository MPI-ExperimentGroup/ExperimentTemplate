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
package utils;

import java.util.ArrayList;
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
public class AudioStimuliFromFilesTest {
    
    public AudioStimuliFromFilesTest() {
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
     * Test of parseTrialsInputCSV method, of class AudioStimuliFromFiles.
     */
    @Test
    public void testParseTrialsInputCSV() throws Exception {
        System.out.println("parseTrialsInputCSV");
        ///Users/olhshk/Documents/ExperimentTemplate/AdaptiveVocabularyAssessmentModule/src/test/java/utils/Stimuli_NonwordMonitoring_26jan18.csv
        String wordFileLocation = "src/test/java/utils/Stimuli_NonwordMonitoring_26jan18.csv";
        AudioStimuliFromFiles instance = new AudioStimuliFromFiles();
        ArrayList<String> result = instance.parseTrialsInputCSV(wordFileLocation);
        assertEquals(168,result.size());
        //Nr;Word;Target_nonword;Syllables;Condition;Length_list;Word1;Word2;Word3;Word4;Word5;Word6;Foil;
        //1;vloer;smoer;1;Target-only;3 words;deebral;smoer;wijp;;;;;;;
        assertEquals("word:vloer,targetNonWord:smoer,nOfSyllables:1,condition:Target-only,length:3,Word1:deebral,Word2:smoer,Word3:wijp,Word4:,Word5:,Word6:,foil:",result.get(0)); 
        //110;stuur;hies;1;Target+Foil;5 words;vaatlal;nos;hiemrief;hies;fots;;hiemrief;;;
        assertEquals("word:stuur,targetNonWord:hies,nOfSyllables:1,condition:Target+Foil,length:5,Word1:vaatlal,Word2:nos,Word3:hiemrief,Word4:hies,Word5:fots,Word6:,foil:hiemrief",result.get(109)); 
        
    }

    /**
     * Test of arrayListAsStringArray method, of class AudioStimuliFromFiles.
     */
    
    @Test
    public void testArrayListAsStringArray() throws Exception{
        System.out.println("arrayListAsStringArray");
        String wordFileLocation = "src/test/java/utils/Stimuli_NonwordMonitoring_26jan18.csv";
        
        AudioStimuliFromFiles instance = new AudioStimuliFromFiles();
        ArrayList<String> rows = instance.parseTrialsInputCSV(wordFileLocation);
        String result = instance.arrayListAsStringArray(rows);
        assertNotNull(result);
        assertTrue(result.length()>168*10+20);
        System.out.println("OK");
        System.out.println(result);
    }
    
}
