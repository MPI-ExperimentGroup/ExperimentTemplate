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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
/**
 *
 * @author olhshk
 */
public class Vocabulary {
    
    private static final AdVocAsAtomStimulus[][] WORDS = new AdVocAsAtomStimulus[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
    private static final ArrayList<AdVocAsAtomStimulus> NONWORDS = new ArrayList<>(); // unknow length, cannot allocte in advance
    

    
    private static void parseWordInputCSV(String wordFileLocation) throws IOException {
        File inputFileWords = new File(wordFileLocation);
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        int[] counter = new int[Constants.NUMBER_OF_BANDS];
        for (int i=0; i<Constants.NUMBER_OF_BANDS; i++){
            counter[i] =0;
        }
        for (CSVRecord record : records) {
            //String number = record.get("nr");
            int bandNumber = Integer.parseInt(record.get("Band"));
            //String uniqueId, String label, String correctResponses, int bandNumber)
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            AdVocAsAtomStimulus stimulus = new AdVocAsAtomStimulus(id, spelling, "word", bandNumber);
            WORDS[bandNumber-1][counter[bandNumber-1]]=stimulus;
            counter[bandNumber-1]++;
        }
    }
    
    public static void parseNonwordInputCSV(String nonwordFileLocation) throws IOException {
        final File inputFileNonWords = new File(nonwordFileLocation);
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        for (CSVRecord record : records) {
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            AdVocAsAtomStimulus stimulus = new AdVocAsAtomStimulus(id, spelling, "nonword", -1);
            NONWORDS.add(stimulus);
        }
    }
    
    public static AdVocAsAtomStimulus[][] getWords(){
        return WORDS;
    }
    
    public static ArrayList<AdVocAsAtomStimulus> getNonwords(){
        return NONWORDS;
    }
    
    
    public static void initialiseVocabulary(String wordFileLocation, String nonwordFileLocation) throws IOException {
        parseWordInputCSV(wordFileLocation);
        parseNonwordInputCSV(nonwordFileLocation);
    }
    
}
