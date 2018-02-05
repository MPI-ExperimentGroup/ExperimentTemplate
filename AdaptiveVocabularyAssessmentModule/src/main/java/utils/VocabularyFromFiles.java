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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author olhshk
 */
public class VocabularyFromFiles {
    
    private final int numberOfBands;
    private final int wordsPerBand;
    private final int wordsPerBandInSeries;
    private final AdVocAsStimulus[][] localWORDS;
    private final ArrayList<AdVocAsStimulus> localNONWORDS = new ArrayList<>(); // unknow length, cannot allocte in advance

    
     public VocabularyFromFiles(int numberOfBands, int wordsPerBand, int numberOfSeries){
        this.numberOfBands = numberOfBands;
        this.wordsPerBand  = wordsPerBand;
        this.wordsPerBandInSeries = this.wordsPerBand/numberOfSeries;
        this.localWORDS = new AdVocAsStimulus[this.numberOfBands][this.wordsPerBandInSeries];
    }
    public void parseWordInputCSV(String wordFileLocation) throws IOException {
        File inputFileWords = new File(wordFileLocation);
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        int[] counter = new int[this.numberOfBands];
        for (int i = 0; i < this.numberOfBands; i++) {
            counter[i] = 0;
        }
        for (CSVRecord record : records) {
            int bandNumber = Integer.parseInt(record.get("Band"));
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            AdVocAsStimulus stimulus = new AdVocAsStimulus(id, spelling, Vocabulary.WORD, bandNumber);
            localWORDS[bandNumber - 1][counter[bandNumber - 1]] = stimulus;
            counter[bandNumber - 1]++;
        }
      }

    public void parseNonwordInputCSV(String nonwordFileLocation) throws IOException {
        final File inputFileNonWords = new File(nonwordFileLocation);
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        System.out.println("public static AdVocAsStimulus[] NONWORDS_ARRAY = { ");
        for (CSVRecord record : records) {
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            System.out.println("new AdVocAsStimulus(\"" + id + "\", \"" + spelling + "\", \""+Vocabulary.NONWORD+"\" " + ",-1),");
            AdVocAsStimulus stimulus = new AdVocAsStimulus(id, spelling, Vocabulary.NONWORD, -1);
            localNONWORDS.add(stimulus);
        }
        System.out.println(" }; ");
         
    }

    public AdVocAsStimulus[][] getWords() {
        return localWORDS;
    }

    public ArrayList<AdVocAsStimulus> getNonwords() {
        return localNONWORDS;
    }

    public void initialiseVocabulary(String wordFileLocation, String nonwordFileLocation) throws IOException {
        this.parseWordInputCSV(wordFileLocation);
        this.parseNonwordInputCSV(nonwordFileLocation);
    }

}
