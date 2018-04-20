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
    private final ArrayList<ArrayList<AdVocAsStimulus>> localWORDS;
    private final ArrayList<AdVocAsStimulus> localNONWORDS = new ArrayList<AdVocAsStimulus>(); // unknow length, cannot allocte in advance

    
     public VocabularyFromFiles(int numberOfBands, int wordsPerBand, int numberOfSeries){
        this.numberOfBands = numberOfBands;
        this.wordsPerBand  = wordsPerBand;
        this.wordsPerBandInSeries = this.wordsPerBand/numberOfSeries;
        this.localWORDS = new ArrayList<ArrayList<AdVocAsStimulus>>(this.numberOfBands);
    }
    public void parseWordInputCSV(String wordFileLocation, String bandColumnName, String wordColumnName, String ratingLabels, String correctResponse) throws IOException {
        File inputFileWords = new File(wordFileLocation);
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        for (int i = 0; i < this.numberOfBands; i++) {
            this.localWORDS.add(new ArrayList<AdVocAsStimulus>(this.wordsPerBandInSeries));
        }
        for (CSVRecord record : records) {
            int bandNumber = Integer.parseInt(record.get(bandColumnName));
            String spelling = record.get(wordColumnName);
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            AdVocAsStimulus stimulus = new AdVocAsStimulus(id, spelling, ratingLabels, correctResponse, bandNumber);
            localWORDS.get(bandNumber - 1).add(stimulus);
        }
      }

    public void parseNonwordInputCSV(String nonwordFileLocation, String nonwordColumnName, String ratingLabels, String correctResponse) throws IOException {
        final File inputFileNonWords = new File(nonwordFileLocation);
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        for (CSVRecord record : records) {
            String spelling = record.get(nonwordColumnName);
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            AdVocAsStimulus stimulus = new AdVocAsStimulus(id, spelling, ratingLabels, correctResponse, 0);
            localNONWORDS.add(stimulus);
        }
        System.out.println(" }; ");
         
    }

    public ArrayList<ArrayList<AdVocAsStimulus>> getWords() {
        return localWORDS;
    }

    public ArrayList<AdVocAsStimulus> getNonwords() {
        return localNONWORDS;
    }

    public void initialiseVocabulary(String wordFileLocation, String nonwordFileLocation, String bandColumnName, String wordColumnName, String ratingLables, String nonwordColumnName, String correctResponseWords, String correctResponseNonWords) throws IOException {
        this.parseWordInputCSV(wordFileLocation, bandColumnName, wordColumnName, ratingLables, correctResponseWords);
        this.parseNonwordInputCSV(nonwordFileLocation, nonwordColumnName, ratingLables, correctResponseNonWords);
    }

}
