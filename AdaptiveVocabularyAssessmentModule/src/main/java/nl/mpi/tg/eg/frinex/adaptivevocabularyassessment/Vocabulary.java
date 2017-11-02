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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
/**
 *
 * @author olhshk
 */
public class Vocabulary {
    
    private final AtomStimulus[][] words = new AtomStimulus[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
    ArrayList<AtomStimulus> nonwords = new ArrayList<>(); // unknow length, cannot allocte in advance
    

    
    public void parseWordInputCSV(String wordFileLocation) throws IOException {
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
            AtomStimulus stimulus = new AtomStimulus(record.get("spelling"), bandNumber);
            this.words[bandNumber-1][counter[bandNumber-1]]=stimulus;
            counter[bandNumber-1]++;
        }
    }
    
    public void parseNonwordInputCSV(String nonwordFileLocation) throws IOException {
        final File inputFileNonWords = new File(nonwordFileLocation);
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        for (CSVRecord record : records) {
            AtomStimulus unit = new AtomStimulus(record.get("spelling"), -1);
            nonwords.add(unit);
        }
    }
    
    public AtomStimulus[][] getWords(){
        return words;
    }
    
    public ArrayList<AtomStimulus> getNonwords(){
        return nonwords;
    }
    
    
    public void initialiseVocabulary(String wordFileLocation, String nonwordFileLocation) throws IOException {
        this.parseWordInputCSV(wordFileLocation);
        this.parseNonwordInputCSV(nonwordFileLocation);
    }
    
}
