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
     * Test of parseTrialsInputCSV_V1 method, of class AudioStimuliFromFiles.
     */
    @Test
    public void testParseTrialsInputCSV() throws Exception {
        System.out.println("parseTrialsInputCSV");
        ///Users/olhshk/Documents/ExperimentTemplate/AdaptiveVocabularyAssessmentModule/src/test/java/utils/Stimuli_NonwordMonitoring_26jan18.csv
        String wordFileLocation = "src/test/java/utils/Stimuli_NonwordMonitoring_4Olha.csv";
        AudioStimuliFromFiles instance = new AudioStimuliFromFiles();
        ArrayList<String> result = instance.parseTrialsInputCSV_V1(wordFileLocation);
        assertEquals(96,result.size());
        //Nr;Word;Target_nonword;Syllables;Condition;Length_list;Word1;Word2;Word3;Word4;Word5;Word6;Noise_level;Foil;
        
        //1;kof;kof_filler.wav;1;NoTarget;3 words;lan_filler_2dB.wav;lep_filler_2dB.wav;lif_filler_2dB.wav;;;;2dB;
        assertEquals("word:kof,targetNonWord:kof_filler.wav,nOfSyllables:1,condition:NoTarget,length:3,Word1:lan_filler_2dB.wav,Word2:lep_filler_2dB.wav,Word3:lif_filler_2dB.wav,Word4:,Word5:,Word6:,foil:,bandLabel:2dB",result.get(0)); 
        
        //19;pag;pag_filler.wav;1;NoTarget;6 words;neg_filler_2dB.wav;par_filler_2dB.wav;lup_filler_2dB.wav;nom_filler_2dB.wav;rin_filler_2dB.wav;sog_filler_2dB.wav;2dB;
        assertEquals("word:pag,targetNonWord:pag_filler.wav,nOfSyllables:1,condition:NoTarget,length:6,Word1:neg_filler_2dB.wav,Word2:par_filler_2dB.wav,Word3:lup_filler_2dB.wav,Word4:nom_filler_2dB.wav,Word5:rin_filler_2dB.wav,Word6:sog_filler_2dB.wav,foil:,bandLabel:2dB",result.get(18)); 
        
    }

    /**
     * Test of arrayListAsStringArray method, of class AudioStimuliFromFiles.
     */
    
    @Test
    public void testArrayListAsStringArray() throws Exception{
        System.out.println("arrayListAsStringArray");
        String wordFileLocation = "src/test/java/utils/Stimuli_NonwordMonitoring_4Olha.csv";
        
        AudioStimuliFromFiles instance = new AudioStimuliFromFiles();
        ArrayList<String> rows = instance.parseTrialsInputCSV_V1(wordFileLocation);
        String result = instance.arrayListAsStringArray(rows, instance.mainClassDeclaration);
        assertNotNull(result);
        assertTrue(result.length()>168*10+20);
        System.out.println("OK");
        System.out.println(result);
        System.out.println("**");
        String bandIndexMap = instance.arrayListAsStringArray(instance.bandIndex, instance.indexDeclaration);
        System.out.println(bandIndexMap);
    }
    
}
