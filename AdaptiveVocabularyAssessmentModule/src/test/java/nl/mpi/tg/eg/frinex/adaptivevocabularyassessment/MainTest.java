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

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Main;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import utils.Vocabulary;
import utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.FastTrack;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuning;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuningBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
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
        System.out.println("main");
        String[] input = new String[0];
        Main.main(input);
        try {
            AdVocAsAtomStimulus[][] words = Vocabulary.getWords();
            ArrayList<AdVocAsAtomStimulus> nonwords = Vocabulary.getNonwords();
            System.out.println("Fast track ");
            //FastTrack(String username, AtomBookkeepingStimulus[][] wrds, ArrayList<AtomStimulus> nonwrds, int nonWordsPerBlock, int startBand, int averageNonwordPosition)
            FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
            fastTrack.createStimulae();
            ArrayList<AtomBookkeepingStimulus> fastTrackSequence = fastTrack.getStimulae();

            int stopBand = 37;
            int lastCorrectBand = simulateFastTrackUpTo(fastTrack, stopBand);
            System.out.println("last correct band " + lastCorrectBand);
            Utils.writeCsvFileFastTrack(fastTrackSequence, stopBand, OUTPUT_DIRECTORY);

            /**/
            System.out.println("Fine tuning ");
            // FineTuning(String username, AtomBookkeepingStimulus[][] wrds, ArrayList<AtomStimulus> nonwrds)
            FineTuning fineTuning = new FineTuning(Constants.DEFAULT_USER, words, nonwords);
            fineTuning.createStimulae();
            ArrayList<ArrayList<FineTuningBookkeepingStimulus>> fineTuningStimulae = fineTuning.getStimulae();
            //writeCsvFileFineTuningPreset(fineTuningStimulae);
            //String message=simulateFineTuningAllCorrect(fineTuning, lastCorrectBand);
            //String message=simulateFineTuningAllWrong(fineTuning, lastCorrectBand);
            String message = simulateFineTuningHalfCorrect(fineTuning, lastCorrectBand);
            //ArrayList<ArrayList<FineTuningStimulus>> fineTuningStimulae = fineTuning.getStimulae();
            long millis = System.currentTimeMillis();
            String fileName = "Fine_tuning_history_" + message + "_" + millis + ".csv";
            Utils.writeCsvFileFineTuningHistoryShortened(fineTuningStimulae, OUTPUT_DIRECTORY, fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Done. ");

    }

    // return the number of the band where the last correct answer is produced
    private int simulateFastTrackUpTo(FastTrack fastTrack, int stopBand) {
        int i = 0;
        boolean correctness = true;
        ArrayList<AtomBookkeepingStimulus> stimulae = fastTrack.getStimulae();
        int actualStopBand = 0;
        while (i < stimulae.size() && correctness) {
            int currentBand = stimulae.get(i).getBandNumber();
            boolean answer = currentBand > 0; // correct answer, true if word, no if non-word
            if (currentBand > stopBand) { // make a mistake
                answer = !answer;
            } else {
                if (currentBand > 0) {
                    actualStopBand = currentBand;
                }
            }
            correctness = fastTrack.runStep(i, answer);
            i++;
        }
        System.out.println("actual stop band : " + actualStopBand);
        System.out.println("expected stop band : " + stopBand);
        return actualStopBand;
    }

    private String simulateFineTuningAllCorrect(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception {
        String retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, 1);
        System.out.println(retVal);
        return retVal;
    }

    private String simulateFineTuningAllWrong(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception {
        String retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, -1);
        System.out.println(retVal);
        return retVal;
    }

    private String simulateFineTuningHalfCorrect(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception {
        String retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, 0.6);
        System.out.println(retVal);
        return retVal;
    }

    private String simulateFineTuningProbabilistic(FineTuning fineTuning, int lastBandFromFastTrack, double correctnessUpperBound) throws Exception {
        ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae = fineTuning.getStimulae();
        String message = "";
        boolean enoughStimulae = true;
        boolean correctness;
        int bandNumber = lastBandFromFastTrack;
        int[] stimulusCounterInBand = new int[Constants.NUMBER_OF_BANDS];
        int[] bandTranscript = new int[Constants.FINE_TUNING_UPPER_BOUND_FOR_2CYCLES * 2 + 1];
        int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];
        boolean cycles2 = false;
        boolean justVisitedFirstBand = (bandNumber == 1);
        boolean justVisitedLastBand = (bandNumber == Constants.NUMBER_OF_BANDS);
        int bandChangeCounter = 0;
        boolean secondStoppingCriterion = false;
        boolean champion = false;
        boolean looser = false;
        while (enoughStimulae
                && !cycles2
                && !secondStoppingCriterion
                && bandNumber > 0
                && bandNumber <= Constants.NUMBER_OF_BANDS
                && !champion
                && !looser) {

            int bandIndex = bandNumber - 1;
            bandVisitCounter[bandIndex]++;

            enoughStimulae = stimulusCounterInBand[bandIndex] < stimulae.get(bandIndex).size();
            if (enoughStimulae) {
                boolean[] answers = fineTuning.testProbabilisticAnswerer(bandNumber, stimulusCounterInBand[bandIndex], correctnessUpperBound);
                correctness = fineTuning.runStep(bandNumber, stimulusCounterInBand[bandIndex], answers);
                stimulusCounterInBand[bandIndex]++;
                Utils.shiftFIFO(bandTranscript, bandNumber);
                cycles2 = Utils.detectLoop(bandTranscript);
                secondStoppingCriterion = Utils.timeToCountVisits(bandChangeCounter);
                if (correctness) {
                    if (bandNumber < Constants.NUMBER_OF_BANDS) { // usual on-success iteration
                        justVisitedLastBand = false;
                        bandNumber++;
                        bandChangeCounter++;
                    } else {
                        if (!justVisitedLastBand) {
                            justVisitedLastBand = true; // double check on the same band 
                            // on the next iteration if the user is indeed a champion
                        } else {
                            // chamipon, already visited the last band, on next iteration stops
                            champion = true;
                        }
                    }
                } else {
                    if (bandNumber > 1) {// usual on-mistake iteration
                        bandNumber--;
                        justVisitedFirstBand = false;
                        bandChangeCounter++;
                    } else {
                        if (!justVisitedFirstBand) {
                            justVisitedFirstBand = true; // recovery chance: 1 more attempt on the first band
                        } else {
                            // second time on the first band
                            looser = true;
                        }
                    }
                }
            } else {
                System.out.println(" out of stimuli");
                System.out.println(bandIndex);
            
            }
        }

        int score = -1;

        if (enoughStimulae) {
            if (cycles2) {
                System.out.println("Detected: three times looping between two neighbouring bands");
                message += "_oscillation_between_two_bands";
                score = bandTranscript[0];
                for (int i = 1; i < bandTranscript.length; i++) {
                    if (score > bandTranscript[i]) {
                        score = bandTranscript[i];
                    }
                }
            }
            if (secondStoppingCriterion) {
                score = Utils.mostOftenVisited(bandVisitCounter);
                System.out.println("Detected: second stopping criterion");
                message += "_second_stopping_criterion_" + bandVisitCounter[score - 1];

            }
            if (looser) {
                score = 1;
                message += "_looser_hit_the_first_band_twise";
            }
            if (champion) {
                score = Constants.NUMBER_OF_BANDS;
                message += "_champion_hit_the_last_band_twise";
            }

        } else {
            message += "_erroneous_situation_out_of_stimulae";// erroenous case, something is not taken into account
        }
        System.out.println(message + "_" + score);
        return message + "_" + score;
    }

}
