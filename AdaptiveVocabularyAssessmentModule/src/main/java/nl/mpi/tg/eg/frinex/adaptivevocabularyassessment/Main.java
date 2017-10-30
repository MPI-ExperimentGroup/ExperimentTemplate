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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.FastTrack;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning.FineTuning;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning.FineTuningStimulus;

/**
 * @since Oct 20, 2017 11:38:57 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Vocabulary vocab = new Vocabulary();
        try {

            vocab.initialiseVocabulary();
            AtomStimulus[][] words = vocab.getWords();
            ArrayList<AtomStimulus> nonwords = vocab.getNonwords();

            System.out.println("Fast track ");
            //FastTrack(String username, AtomStimulus[][] wrds, ArrayList<AtomStimulus> nonwrds, int nonWordsPerBlock, int startBand, int averageNonwordPosition)
            FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, words, nonwords, Constants.NONWORDS_PER_BLOCK, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
            fastTrack.createStimulae();
            ArrayList<AtomStimulus> fastTrackSequence = fastTrack.getStimulae();

            int stopBand = 37;
            int lastCorrectBand = simulateFastTrackUpTo(fastTrack, stopBand);
            System.out.println("last correct band " + lastCorrectBand);
            writeCsvFileFastTrack(fastTrackSequence, stopBand);

            /**/
            System.out.println("Fine tuning ");
            // FineTuning(String username, AtomStimulus[][] wrds, ArrayList<AtomStimulus> nonwrds)
            FineTuning fineTuning = new FineTuning(Constants.DEFAULT_USER, words, nonwords);
            fineTuning.createStimulae();
            ArrayList<ArrayList<FineTuningStimulus>> fineTuningStimulae = fineTuning.getStimulae();
            //writeCsvFileFineTuningPreset(fineTuningStimulae);
            //simulateFineTuningAllCorrect(fineTuning, lastCorrectBand);
            //simulateFineTuningAllWrong(fineTuning, lastCorrectBand);
            simulateFineTuningHalfCorrect(fineTuning, lastCorrectBand);
            //ArrayList<ArrayList<FineTuningStimulus>> fineTuningStimulae = fineTuning.getStimulae();
            writeCsvFileFineTuningHistoryShortened(fineTuningStimulae);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*Vocabulary bands = new Vocabulary();
        try {
           
            bands.parseWordInputCSV();
            bands.parseNonwordInputCSV();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        testPrint(bands);
         */
        System.out.println("Done. ");

    }

    private static void testPrint(Vocabulary bands) {
        AtomStimulus[][] tmpwords = bands.getWords();
        System.out.println("Words \n");

        for (int i = 0; i < tmpwords.length; i++) {
            System.out.println(i + 1);
            for (AtomStimulus unit : tmpwords[i]) {
                System.out.println(unit.getSpelling());
                System.out.println(unit.getIsUsed());
            }
        }
        ArrayList<AtomStimulus> tmpnonwords = bands.getNonwords();
        System.out.println("Non words \n");
        for (AtomStimulus nonword : tmpnonwords) {
            System.out.println(nonword.getSpelling());
            System.out.println(nonword.getIsUsed());
        }
    }

    public static void writeCsvFileFastTrack(ArrayList<AtomStimulus> stimulae, int stopBand) throws IOException {
        long millis = System.currentTimeMillis();
        int blockSize = Constants.NONWORDS_PER_BLOCK * Constants.AVRERAGE_NON_WORD_POSITION;
        String fileName = "Fast_track_test_stopped_at_band" + stopBand + "_" + blockSize + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + Constants.OUTPUT_DIRECTORY + fileName);
        final File csvFile = new File(Constants.OUTPUT_DIRECTORY, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("Spelling;BandNumber;UserAnswer;Correctness;isUsed;NonwordsFrequencyAtThisPoint\n");
        int counter = 0;
        int counterNonwords = 0;
        for (AtomStimulus stimulus : stimulae) {
            counter++;
            if (stimulus.getBandNumber() == -1) {
                counterNonwords++;
            }
            double frequency = ((double) counterNonwords) / ((double) counter);
            String row = stimulus.getSpelling() + ";" + stimulus.getBandNumber()
                    + ";" + stimulus.getReaction() + ";" + stimulus.getCorrectness()
                    + ";" + stimulus.getIsUsed() + ";" + frequency;
            //System.out.println(row);
            csvFileWriter.write(row + "\n");
        }
        csvFileWriter.close();
    }

    public static void writeCsvFileFineTuningPreset(ArrayList<ArrayList<FineTuningStimulus>> stimulae) throws IOException {
        long millis = System.currentTimeMillis();
        String fileName = "Fine_tuning_preset_" + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + Constants.OUTPUT_DIRECTORY + fileName);
        final File csvFile = new File(Constants.OUTPUT_DIRECTORY, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("QuadrupleNummer;Spelling;BandNumber;UserAnswer;Correctness;isUsed\n");
        int quadrupleCounter = 0;
        for (int bandCounter = 0; bandCounter < stimulae.size(); bandCounter++) {
            for (FineTuningStimulus stimulus : stimulae.get(bandCounter)) {
                for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
                    AtomStimulus aStimulus = stimulus.getAtomStimulusAt(i);
                    String row = quadrupleCounter + ";" + aStimulus.getSpelling()
                            + ";" + aStimulus.getBandNumber()
                            + ";" + aStimulus.getReaction() + ";" + aStimulus.getCorrectness()
                            + ";" + aStimulus.getIsUsed();
                    //System.out.println(row);
                    //System.out.println(row);
                    csvFileWriter.write(row + "\n");
                }
            }
        }
        csvFileWriter.close();
    }

    private static ArrayList<FineTuningStimulus> orderFineTuningHistory(ArrayList<ArrayList<FineTuningStimulus>> stimulae) {
        ArrayList<FineTuningStimulus> retVal = new ArrayList<>();
        for (int bandCounter = 0; bandCounter < stimulae.size(); bandCounter++) {
            for (FineTuningStimulus stimulus : stimulae.get(bandCounter)) {
                insertSortFineTuningHistory(retVal, stimulus);
            }
        }
        return retVal;
    }

    private static void insertSortFineTuningHistory(ArrayList<FineTuningStimulus> stimulae, FineTuningStimulus stimulus) {
        if (stimulus.getVisitingTime() > 0) {
            for (int i = 0; i < stimulae.size(); i++) {
                if (stimulus.getVisitingTime() < stimulae.get(i).getVisitingTime()) {
                    stimulae.add(i, stimulus);
                    return;
                }
            }
            stimulae.add(stimulus);
        }
    }

    public static void writeCsvFileFineTuningHistoryShortened(ArrayList<ArrayList<FineTuningStimulus>> stimulae) throws IOException {
        long millis = System.currentTimeMillis();
        ArrayList<FineTuningStimulus> history = orderFineTuningHistory(stimulae);
        String fileName = "Fine_tuning_history_shortened_" + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + Constants.OUTPUT_DIRECTORY + fileName);
        final File csvFile = new File(Constants.OUTPUT_DIRECTORY, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("BandNumber;overallCorrectness;visitingTime\n");
        for (FineTuningStimulus stimulus : history) {
            String row = stimulus.getBandNumber()
                    + ";" + stimulus.getOverallCorrectness()
                    + ";" + stimulus.getVisitingTime();
            csvFileWriter.write(row + "\n");
        }
        csvFileWriter.close();
    }

    // return the number of the band where the last correct answer is produced
    public static int simulateFastTrackUpTo(FastTrack fastTrack, int stopBand) {
        int i = 0;
        boolean correctness = true;
        ArrayList<AtomStimulus> stimulae = fastTrack.getStimulae();
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

    public static int simulateFineTuningAllCorrect(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception{
        int retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, 1);
        System.out.println(retVal);
        return retVal;
    }

    public static int simulateFineTuningAllWrong(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception{
        int retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, -1);
        System.out.println(retVal);
        return retVal;
    }

    public static int simulateFineTuningHalfCorrect(FineTuning fineTuning, int lastBandFromFastTrack) throws Exception{
        int retVal = simulateFineTuningProbabilistic(fineTuning, lastBandFromFastTrack, 0.5);
        System.out.println(retVal);
        return retVal;
    }

    private static int simulateFineTuningProbabilistic(FineTuning fineTuning, int lastBandFromFastTrack, double correctnessUpperBound) throws Exception {
        ArrayList<ArrayList<FineTuningStimulus>> stimulae = fineTuning.getStimulae();
        boolean enoughStimulae = true;
        boolean correctness;
        int bandNumber = lastBandFromFastTrack;
        int cycles2Counter = 0;
        int[] stimulusCounterInBand = new int[Constants.NUMBER_OF_BANDS];
        while (enoughStimulae && cycles2Counter < Constants.UPPER_BOUND_FOR_2CYCLES && bandNumber > 0 && enoughStimulae && bandNumber <= Constants.NUMBER_OF_BANDS) {
            int bandIndex=bandNumber-1;
            enoughStimulae = stimulusCounterInBand[bandNumber-1] < stimulae.get(bandIndex).size();
            if (enoughStimulae) {
                boolean[] answers = fineTuning.testProbabilisticAnswerer(bandNumber, stimulusCounterInBand[bandIndex], correctnessUpperBound);
                correctness = fineTuning.runStep(bandNumber, stimulusCounterInBand[bandIndex], answers);
                stimulusCounterInBand[bandIndex]++;
                if (correctness) {
                    bandNumber++;
                } else {
                    bandNumber--;
                }
            }
        }
        int retVal = bandNumber;
        if (bandNumber == 0) {
            retVal++;
        }
        if (bandNumber > Constants.NUMBER_OF_BANDS) {
            retVal--;
        }
        System.out.println(retVal);
        return retVal;
    }

}
