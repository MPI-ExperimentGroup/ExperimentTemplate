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
package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.fasttrack.fintetuning.FineTuningBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;

/**
 *
 * @author olhshk
 */
public class Utils {

   
    public static void testPrint() {
        AdVocAsAtomStimulus[][] tmpwords = Vocabulary.getWords();
        System.out.println("Words \n");

        for (int i = 0; i < tmpwords.length; i++) {
            System.out.println(i + 1);
            for (AdVocAsAtomStimulus unit : tmpwords[i]) {
                System.out.println(unit.getLabel());
            }
        }
        ArrayList<AdVocAsAtomStimulus> tmpnonwords =  Vocabulary.getNonwords();
        System.out.println("Non words \n");
        for (AdVocAsAtomStimulus nonword : tmpnonwords) {
            System.out.println(nonword.getLabel());
        }
    }

    public static void writeCsvFileFastTrack(ArrayList<AtomBookkeepingStimulus> stimulae, int stopBand, String outputDir) throws IOException {
        long millis = System.currentTimeMillis();
        int blockSize = Constants.NONWORDS_PER_BLOCK * Constants.AVRERAGE_NON_WORD_POSITION;
        String fileName = "Fast_track_test_stopped_at_band" + stopBand + "_" + blockSize + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("Spelling;BandNumber;UserAnswer;Correctness;isUsed;NonwordsFrequencyAtThisPoint\n");
        int counter = 0;
        int counterNonwords = 0;
        for (AtomBookkeepingStimulus stimulus : stimulae) {
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

    public static void writeCsvFileFineTuningPreset(ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae, String outputDir) throws IOException {
        long millis = System.currentTimeMillis();
        String fileName = "Fine_tuning_preset_" + "_" + millis + ".csv";
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("QuadrupleNummer;Spelling;BandNumber;UserAnswer;Correctness;isUsed\n");
        int quadrupleCounter = 0;
        for (int bandCounter = 0; bandCounter < stimulae.size(); bandCounter++) {
            for (FineTuningBookkeepingStimulus stimulus : stimulae.get(bandCounter)) {
                for (int i = 0; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
                    AtomBookkeepingStimulus aStimulus = stimulus.getAtomStimulusAt(i);
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

    public static ArrayList<FineTuningBookkeepingStimulus> orderFineTuningHistory(ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae) {
        ArrayList<FineTuningBookkeepingStimulus> retVal = new ArrayList<>();
        for (int bandCounter = 0; bandCounter < stimulae.size(); bandCounter++) {
            for (FineTuningBookkeepingStimulus stimulus : stimulae.get(bandCounter)) {
                insertSortFineTuningHistory(retVal, stimulus);
            }
        }
        return retVal;
    }

    public static void insertSortFineTuningHistory(ArrayList<FineTuningBookkeepingStimulus> stimulae, FineTuningBookkeepingStimulus stimulus) {
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

    public static void writeCsvFileFineTuningHistoryShortened(ArrayList<ArrayList<FineTuningBookkeepingStimulus>> stimulae, String outputDir, String fileName) throws IOException {
        ArrayList<FineTuningBookkeepingStimulus> history = orderFineTuningHistory(stimulae);
        System.out.println("writeCsvFile: " + outputDir + fileName);
        final File csvFile = new File(outputDir, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("BandNumber;overallCorrectness;visitingTime\n");
        for (FineTuningBookkeepingStimulus stimulus : history) {
            String row = stimulus.getBandNumber()
                    + ";" + stimulus.getOverallCorrectness()
                    + ";" + stimulus.getVisitingTime();
            csvFileWriter.write(row + "\n");
        }
        csvFileWriter.close();
    }

    public static ArrayList<AdVocAsAtomStimulus> getPureStimuli(ArrayList<AtomBookkeepingStimulus> bookkeepingStimuli){
        ArrayList<AdVocAsAtomStimulus> retVal = new ArrayList<>(bookkeepingStimuli.size());
        for (AtomBookkeepingStimulus bStimulus : bookkeepingStimuli) {
            retVal.add(bStimulus.getPureStimulus());
        }
        return retVal;
    }
}
