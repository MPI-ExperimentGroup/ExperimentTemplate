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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author olhshk
 */
public class VocabularyFromFiles {
    
    private final int seriesN;
    

    
     public VocabularyFromFiles(int numberOfBands, int wordsPerBand, int seriesN){
        this.seriesN = seriesN;
    }
     
   
   
    //Example: <stimulus audioPath="Q6" correctResponses="[hH]" identifier="Q6:Q6.wav::[hH]" pauseMs="0" tags="Pretest_Screen Q6"/>
    public void parseWordInputCSVtoXMLfragment(String wordFileLocation) throws IOException {
        File inputFileWords = new File(wordFileLocation);
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        
        for (CSVRecord record : records) {
            int bandNumber = Integer.parseInt(record.get("Band"));
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            StringBuilder builder = new StringBuilder();
            builder.append("<stimulus ");
            builder.append("identifier=\"").append(id).append("\"");
            builder.append("tags=\"round").append(this.seriesN).append("\"");
            builder.append("label=\"").append(spelling).append("\"");
            builder.append("correctResponses=\"").append(Vocabulary.WORD).append("\"");
            builder.append("bandNumber=\"").append(bandNumber).append("\"");
            builder.append(" />");
            System.out.println(builder.toString());
        }
      }
    
    // obsolete
     public void parseNonwordInputCSVtoXMLfragment(String nonwordFileLocation) throws IOException {
        final File inputFileNonWords = new File(nonwordFileLocation);
        final Reader reader = new InputStreamReader(inputFileNonWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        System.out.println("public static AdVocAsStimulus[] NONWORDS_ARRAY = { ");
        for (CSVRecord record : records) {
            String spelling = record.get("spelling");
            long millis = System.currentTimeMillis();
            String id = spelling + "_" + millis;
            StringBuilder builder = new StringBuilder();
            builder.append("<stimulus ");
            builder.append("identifier=\"").append(id).append("\"");
            builder.append("tags=\"round").append(this.seriesN).append("\"");
            builder.append("label=\"").append(spelling).append("\"");
            builder.append("correctResponses=\"").append(Vocabulary.NONWORD).append("\"");
            builder.append("bandNumber=\"-1\"");
            builder.append(" />");
            System.out.println(builder.toString());
        }
        System.out.println(" }; ");
         
    }


    public void initialiseVocabulary(String wordFileLocation, String nonwordFileLocation) throws IOException {
        this.parseWordInputCSVtoXMLfragment(wordFileLocation);
        this.parseNonwordInputCSVtoXMLfragment(nonwordFileLocation);
    }
    
    
}
