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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
/**
 *
 * @author olhshk
 */
public class Bands {
    
    private final String[][] words = new String[Constants.NUMBER_OF_BANDS][Constants.WORDS_PER_BAND];
    ArrayList<String> nonwords = new ArrayList<>(); // unknow length, cannot allocte in advance
    final File inputFileWords = new File(Constants.WORD_FILE_LOCATION);
    final File inputFileNonWords = new File(Constants.NONWORD_FILE_LOCATION);

    
    public void parseWordInputCSV() throws IOException {
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        int[] counter = new int[Constants.NUMBER_OF_BANDS];
        for (int i=0; i<Constants.NUMBER_OF_BANDS; i++){
            counter[i] =0;
        }
        for (CSVRecord record : records) {
            //String number = record.get("nr");
            int bandNumber = Integer.parseInt(record.get("Band"));
            String spelling = record.get("spelling");
            this.words[bandNumber-1][counter[bandNumber-1]]=spelling;
            counter[bandNumber-1]++;
            //System.out.println(bandNumber);
            //System.out.println(spelling);
           
        }
    }
    
    public void parseNonWordInputCSV() throws IOException {
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        for (CSVRecord record : records) {
            String spelling = record.get("spelling");
            nonwords.add(spelling);
        }
    }
    
    public String[][] getWords(){
        return words;
    }
    
    public ArrayList<String> getNonWords(){
        return nonwords;
    }
    
}
