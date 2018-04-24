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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool;

import java.util.ArrayList;
import java.util.HashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
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
public class AdVocAsStimuliFromStringTest {
    
    private final String answerWordEn="YES&#44; I know the word.";
    private final String answerNonWordEn="NO&#44; I do not know the word.";
    private final int numberOfBandsEn = 62;
    private final int wordsPerBandEn =20;
    private final int nNonwordsEN= 1240 /2;
    
    private final int wordsPerBandNL = 20;
    private final int numberOfBandsNL = 54;
    private String answerWordNL = "JA&#44; ik ken dit woord" ;
    private String answerNonWordNL ="NEE&#44; ik ken dit woord niet";
    private final int nNonwordsNL = 676;
    
    public AdVocAsStimuliFromStringTest() {
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

   
    private void testParseWordsInputCSVString(String className, String firstWord, String lastWord, String answerNonWord, String answerWord, int numberOfBands, int wordsPerBand) throws Exception {
        System.out.println("parseWordsInputCSVString: "+className);
        AdVocAsStimuliFromString instance = new AdVocAsStimuliFromString();
        instance.parseWordsInputCSVString(className, numberOfBands, answerNonWord, answerWord);
        ArrayList<ArrayList<AdVocAsStimulus>> words = instance.getWords();
        ArrayList<String> uniqueIds = new  ArrayList<String>();
        for (int i=0; i<numberOfBands; i++) {
            for (int j=0; j<wordsPerBand; j++) {
                AdVocAsStimulus word = words.get(i).get(j);
                uniqueIds.add(word.getUniqueId());
                assertEquals(i+1, word.getBandNumber());
                assertEquals(answerWord, word.getCorrectResponses());
                assertEquals(answerNonWord+","+answerWord, word.getRatingLabels());
                assertEquals(word.getUniqueId(), word.getLabel());
            }
        }
        HashMap<String, AdVocAsStimulus> map = instance.getHashedStimuli();
        assertEquals(uniqueIds.size(), map.keySet().size());
       
        assertEquals(firstWord, words.get(0).get(0).getLabel());
        assertEquals(lastWord, words.get(numberOfBands-1).get(wordsPerBand-1).getLabel());
    }
    
    @Test
    public void testParseWordsInputCSVString_EN_21() throws Exception{
       this.testParseWordsInputCSVString("Words_EN_2rounds_1", "alternative", "scorbutic", this.answerNonWordEn, this.answerWordEn, this.numberOfBandsEn, this.wordsPerBandEn);
    }
    
     @Test
    public void testParseWordsInputCSVString_EN_22() throws Exception{
       this.testParseWordsInputCSVString("Words_EN_2rounds_2", "annoying", "whitleather", this.answerNonWordEn, this.answerWordEn, this.numberOfBandsEn, this.wordsPerBandEn);
    }
    
     @Test
    public void testParseWordsInputCSVString_NL_21() throws Exception{
       this.testParseWordsInputCSVString("Words_NL_2rounds_1", "vaak", "fijfel", this.answerNonWordNL, this.answerWordNL, this.numberOfBandsNL, this.wordsPerBandNL);
    }
    
    @Test
    public void testParseWordsInputCSVString_NL_22() throws Exception{
       this.testParseWordsInputCSVString("Words_NL_2rounds_2", "schuilnaam", "kebon", this.answerNonWordNL, this.answerWordNL, this.numberOfBandsNL, this.wordsPerBandNL);
    }
    
    
    @Test
    public void testParseWordsInputCSVString_NL_1() throws Exception{
       this.testParseWordsInputCSVString("Words_NL_1round", "vaak", "kebon", this.answerNonWordNL, this.answerWordNL, this.numberOfBandsNL, this.wordsPerBandNL*2);
    }
    

    /**
     * Test of parseNonWordsInputCSVString method, of class AdVocAsStimuliFromString.
     */
    private void testParseNonWordsInputCSVString(String className, String firstNonword, String lastNonword, String answerNonWord, String answerWord, int nNonwords) throws Exception {
        System.out.println("parseNonWordsInputCSVString: "+className);
        AdVocAsStimuliFromString instance = new AdVocAsStimuliFromString();
        instance.parseNonWordsInputCSVString(className, answerNonWord, answerWord);
        ArrayList<AdVocAsStimulus> nonwords = instance.getNonwords();
        ArrayList<String> uniqueIds = new  ArrayList<String>();
        for (int i=0; i<nNonwords; i++) {
            
                AdVocAsStimulus word = nonwords.get(i);
                uniqueIds.add(word.getUniqueId());
                assertEquals(0, word.getBandNumber());
                assertEquals(answerNonWord, word.getCorrectResponses());
                assertEquals(answerNonWord+","+answerWord, word.getRatingLabels());
                assertEquals(word.getUniqueId(), word.getLabel());
            
        }
        HashMap<String, AdVocAsStimulus> map = instance.getHashedStimuli();
        assertEquals(uniqueIds.size(), map.keySet().size());
       
        assertEquals(firstNonword, nonwords.get(0).getLabel());
        assertEquals(lastNonword, nonwords.get(nNonwords-1).getLabel());
    }

   @Test
    public void testParseNonWordsInputCSVString_EN_21() throws Exception{
       this.testParseNonWordsInputCSVString("NonWords_EN_2rounds_1", "abhothness", "ivomprofication", this.answerNonWordEn, this.answerWordEn, this.nNonwordsEN);
    }
    
    @Test
    public void testParseNonWordsInputCSVString_EN_22() throws Exception{
       this.testParseNonWordsInputCSVString("NonWords_EN_2rounds_2", "jalmer", "zomel", this.answerNonWordEn, this.answerWordEn, this.nNonwordsEN);
    }
    
    @Test
    public void testParseNonWordsInputCSVString_NL_21() throws Exception{
       this.testParseNonWordsInputCSVString("NonWords_NL_2rounds_1", "kruffen", "ankentement", this.answerNonWordEn, this.answerWordEn, this.nNonwordsNL);
    }
    
    @Test
    public void testParseNonWordsInputCSVString_NL_22() throws Exception{
       this.testParseNonWordsInputCSVString("NonWords_NL_2rounds_2", "pretebentie", "berrillelijk", this.answerNonWordEn, this.answerWordEn, this.nNonwordsNL);
    }
    
    @Test
    public void testParseNonWordsInputCSVString_NL_1() throws Exception{
       this.testParseNonWordsInputCSVString("NonWords_NL_1round", "kruffen", "berrillelijk", this.answerNonWordEn, this.answerWordEn, this.nNonwordsNL*2);
    }
}
