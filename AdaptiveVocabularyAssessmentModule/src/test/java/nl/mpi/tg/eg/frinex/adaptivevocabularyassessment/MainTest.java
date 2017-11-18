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

import utils.Utils;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.AdVocAsStimuliProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class MainTest {

    final String OUTPUT_DIRECTORY = "../../Data/";

    public MainTest() {
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
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() throws Exception {
        for (int i = 1; i<10; i++) {
            double prob = 0.5+i*0.05;
            //System.out.println(prob);
            this.testRound(prob);
        }
    }
    
    private void testRound(double prob) throws Exception{
        Random rnd = new Random();

        AdVocAsStimuliProvider provider = new AdVocAsStimuliProvider();
        provider.initialiseStimuliState("");

        boolean hasNextStimulus = provider.hasNextStimulus(0);
        int currentExperimentCount = 0;
        while (hasNextStimulus) {
            provider.nextStimulus(0);
            currentExperimentCount = provider.getCurrentStimulusIndex();
            //System.out.println(currentExperimentCount);
            AdVocAsAtomStimulus stimulus = provider.getCurrentStimulus();
            String answer = this.probabilisticAnswerer(stimulus, prob, rnd);
            boolean isCorrect = provider.isCorrectResponse(stimulus, answer);
            hasNextStimulus = provider.hasNextStimulus(0);
        }

        boolean enoughFineTuningStimulae = provider.getEnoughFinetuningStimuli();
        boolean cycle2 = provider.getCycel2();
        boolean champion = provider.getChampion();
        boolean looser = provider.getLooser();
        
        int lastCorrectBandFastTrack = provider.getBestFastTrackBand();
        System.out.println("last correct band fast track: " + lastCorrectBandFastTrack);
        //Utils.writeCsvFileFastTrack(provider, lastCorrectBandFastTrack, OUTPUT_DIRECTORY);

        String message = "correct_"+prob+"__cycel2_" + cycle2
                + "__champion_" + champion + "__looser_" + looser + "__enough_" + enoughFineTuningStimulae;

        long millis = System.currentTimeMillis();
        //String fileNameCSV = "Fine_tuning_short_history_" + message + "_" + millis + ".csv";
        //Utils.writeCsvFileFineTuningHistoryShortened(provider, OUTPUT_DIRECTORY, fileNameCSV);
        
        String fileNameHTML = "Full_user_history_" + message + "_" + millis + ".html";
        Utils.writeHtmlFullUserResults(provider, OUTPUT_DIRECTORY, fileNameHTML);
        
        Utils.writeCsvMapAsOneCsv(provider, OUTPUT_DIRECTORY, "Full_user_history_" + message + "_" + millis + ".csv");
        System.out.println("Done with probability  "+prob);
    }

    private String probabilisticAnswerer(AdVocAsAtomStimulus stimulus, double correctnessUpperBound, Random rnd) throws Exception {
        String retVal = stimulus.getCorrectResponses();
        double rndDouble = rnd.nextDouble();
        //System.out.println("*****");
        //System.out.println(retVal);
        //System.out.println(rndDouble);
        if (rndDouble > correctnessUpperBound) { // spoil the answer
            if (retVal.equals("word")) {
                retVal = "nonword";
            } else {
                if (retVal.equals("nonword")) {
                    retVal ="word";
                } else {
                    throw new Exception("Wrong correct reaction in the stimulus, neither word, nor nonword: " + retVal);
                }
            }
        }
        //System.out.println(retVal);
        //System.out.println("*****");
        return retVal;
    }

 
}
