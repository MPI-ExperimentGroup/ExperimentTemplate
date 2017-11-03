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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.fintetuning.FineTuningStimulus;

/**
 *
 * @author olhshk
 */
public class Utils {

    public static void shiftFIFO(int[] fifo, int newelement) {
        for (int i = 0; i < fifo.length - 1; i++) {
            fifo[i] = fifo[i + 1];
        }
        fifo[fifo.length - 1] = newelement;
    }

    public static boolean detectLoop(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i + 2] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean timeToCountVisits(int changeCounter) {
        return (changeCounter > Constants.FINE_TUNING_MAX_BAND_CHANGE);
    }

    public static int mostOftenVisited(int[] bandVisitCounter) {
        int candidate_min = 0;
        int candidate_max = 0;
        for (int i = 1; i < bandVisitCounter.length; i++) {
            if (bandVisitCounter[candidate_min] < bandVisitCounter[i]) {
                candidate_min = i;
                candidate_max = i;
            }
            if (bandVisitCounter[candidate_min] == bandVisitCounter[i]) {
                candidate_max = i;
            }
        }

        if (candidate_max > candidate_min) {
            int visits = bandVisitCounter[candidate_min];
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < bandVisitCounter.length; i++) {
               if (bandVisitCounter[i]==visits){
                   candidates.add(i);
               }
            }
            int i=0; 
            boolean found = false;
            int current = -2;
            while (i<candidates.size()-1 && !found){
                int index1 = candidates.get(i);
                int index2 = candidates.get(i+1);
                current = chooseBand(index1, index2, bandVisitCounter);
                if (current == index1){
                    found = true;
                } else {
                    i++;
                }
            }
            return current+1;
        } else {
            return candidate_min + 1;
            // band nummer is its index plus 1
        }

    }
    
    public static int chooseBand(int index1, int index2, int[] bandVisitCounter){
        if (index2 > index1) {
            // count visist below index2
            int visitsBelow = 0;
            for (int i = 0; i < index2; i++) {
                visitsBelow += bandVisitCounter[i];
            }

            // count visist below candidate_max
            int visitsAbove = 0;
            for (int i = index2 + 1; i < bandVisitCounter.length; i++) {
                visitsAbove += bandVisitCounter[i];
            }
            if (visitsBelow > visitsAbove) {
                return index1;
            } else {
                return index2;
            }
        } else {
            return -1; // erroneous input
        }
    }

    public static void testPrint(Vocabulary bands) {
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

    public static void writeCsvFileFastTrack(ArrayList<AtomStimulus> stimulae, int stopBand, String outputDir) throws IOException {
        long millis = System.currentTimeMillis();
        int blockSize = Constants.NONWORDS_PER_BLOCK * Constants.AVRERAGE_NON_WORD_POSITION;
        String fileName = "Fast_track_test_stopped_at_band" + stopBand + "_" + blockSize + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
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

    public static void writeCsvFileFineTuningPreset(ArrayList<ArrayList<FineTuningStimulus>> stimulae, String outputDir) throws IOException {
        long millis = System.currentTimeMillis();
        String fileName = "Fine_tuning_preset_" + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
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

    public static ArrayList<FineTuningStimulus> orderFineTuningHistory(ArrayList<ArrayList<FineTuningStimulus>> stimulae) {
        ArrayList<FineTuningStimulus> retVal = new ArrayList<>();
        for (int bandCounter = 0; bandCounter < stimulae.size(); bandCounter++) {
            for (FineTuningStimulus stimulus : stimulae.get(bandCounter)) {
                insertSortFineTuningHistory(retVal, stimulus);
            }
        }
        return retVal;
    }

    public static void insertSortFineTuningHistory(ArrayList<FineTuningStimulus> stimulae, FineTuningStimulus stimulus) {
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

    public static void writeCsvFileFineTuningHistoryShortened(ArrayList<ArrayList<FineTuningStimulus>> stimulae, String outputDir, String fileName) throws IOException {
        ArrayList<FineTuningStimulus> history = orderFineTuningHistory(stimulae);
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
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

}
