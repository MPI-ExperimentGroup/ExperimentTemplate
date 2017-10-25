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

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.baseName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.Bands;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.LexicalUnit;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.FastTrack;

/**
 * @since Oct 20, 2017 11:38:57 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("starting work ... ");
        FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, Constants.START_BAND, Constants.AVRERAGE_NON_WORD_POSITION);
        try {
            fastTrack.initialiseWordLists();
            fastTrack.makeStimulaeSequence(Constants.NONWORDS_PER_BLOCK);
            ArrayList<LexicalUnit> fastTrackSequence = fastTrack.getStimulaeSequence();
            writeCsvFileFastTrack(fastTrackSequence);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        /*Bands bands = new Bands();
        try {
           
            bands.parseWordInputCSV();
            bands.parseNonWordInputCSV();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        testPrint(bands);
         */
        System.out.println("Done. ");

    }

    private static void testPrint(Bands bands) {
        LexicalUnit[][] tmpwords = bands.getWords();
        System.out.println("Words \n");

        for (int i = 0; i < tmpwords.length; i++) {
            System.out.println(i + 1);
            for (LexicalUnit unit : tmpwords[i]) {
                System.out.println(unit.getSpelling());
                System.out.println(unit.getIsUsed());
            }
        }
        ArrayList<LexicalUnit> tmpnonwords = bands.getNonWords();
        System.out.println("Non words \n");
        for (LexicalUnit nonword : tmpnonwords) {
            System.out.println(nonword.getSpelling());
            System.out.println(nonword.getIsUsed());
        }
    }

    public static void writeCsvFileFastTrack(ArrayList<LexicalUnit> stimulae) throws IOException {
        long millis = System.currentTimeMillis();
        int blockSize = Constants.NONWORDS_PER_BLOCK*Constants.AVRERAGE_NON_WORD_POSITION;
        String fileName = "Fast_track_test_"+blockSize+"_"+millis+".csv";
        System.out.println("writeCsvFile: " + Constants.OUTPUT_DIRECTORY + fileName);
        final File csvFile = new File(Constants.OUTPUT_DIRECTORY, fileName);
        final FileWriter csvFileWriter = new FileWriter(csvFile, false);
        csvFileWriter.write("Spelling;BandNumber;NonwordsFrequencyAtThisPoint\n");
        int counter = 0;
        int counterNonwords = 0;
        for (LexicalUnit stimulus : stimulae) {
            counter++;
            if (stimulus.getBandNumber() == -1) {
                    counterNonwords++;
            }
            double frequency = ((double) counterNonwords) / ((double) counter);
            String row = stimulus.getSpelling() + ";" +  stimulus.getBandNumber() +
                    ";"+frequency;
            System.out.println(row);
            csvFileWriter.write(row + "\n");
        }
        csvFileWriter.close();
    }

}
