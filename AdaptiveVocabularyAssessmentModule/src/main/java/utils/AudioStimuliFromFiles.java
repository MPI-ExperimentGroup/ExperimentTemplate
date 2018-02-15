/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author olhshk
 */
public class AudioStimuliFromFiles {
    
    public ArrayList<String> bandIndex=new ArrayList<String>();
    
    public String mainClassDeclaration = "public static final String[] TRIAL_ROWS";
    public String indexDeclaration = "public static final String[] INDEX_ARRAY";

   
    public ArrayList<String> parseTrialsInputCSV(String wordFileLocation) throws IOException {
        
        
        File inputFileWords = new File(wordFileLocation);
        final Reader reader = new InputStreamReader(inputFileWords.toURL().openStream(), "UTF-8"); // todo: this might need to change to "ISO-8859-1" depending on the usage
        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader().parse(reader);
        ArrayList<String> retVal = new ArrayList<String>();
        
        for (CSVRecord record : records) {
           
            // making a trial  string
            StringBuilder strBuilder = new StringBuilder(0);
            
            String word = record.get("Word").trim();
            if (word==null || word.isEmpty()) {
                continue;
            }
            strBuilder.append("word:").append(word).append(',');
            
            String targetNonword = record.get("Target_nonword").trim();
            if (targetNonword==null || targetNonword.isEmpty()) {
                continue;
            }
            strBuilder.append("targetNonWord:").append(targetNonword).append(',');
           
            
            String nOfSyllables =record.get("Syllables").trim();
            if (nOfSyllables==null || nOfSyllables.isEmpty()) {
                continue;
            }
            strBuilder.append("nOfSyllables:").append(nOfSyllables).append(',');
            
            String condition = record.get("Condition").trim();
            if (condition==null || condition.isEmpty()) {
                continue;
            }
            strBuilder.append("condition:").append(condition).append(',');
            
            String length =record.get("Length_list").trim().substring(0, 1);
            if (length==null || length.isEmpty()) {
                continue;
            }
            strBuilder.append("length:").append(length).append(',');

            String currentWord1 =  record.get("Word1").trim();
            if (currentWord1==null || currentWord1.isEmpty()) {
                continue;
            }
            strBuilder.append("Word1:").append(currentWord1).append(',');
            
            String currentWord2 =  record.get("Word2").trim();
            if (currentWord2==null || currentWord2.isEmpty()) {
                continue;
            }
            strBuilder.append("Word2:").append(currentWord2).append(',');
            
            String currentWord3 =  record.get("Word3").trim();
            if (currentWord3==null || currentWord3.isEmpty()) {
                continue;
            }
            strBuilder.append("Word3:").append(currentWord3).append(',');
            
            String currentWord4 =  record.get("Word4").trim();
            strBuilder.append("Word4:").append(currentWord4).append(',');
            
            String currentWord5 =  record.get("Word5").trim();
            strBuilder.append("Word5:").append(currentWord5).append(',');
            
            String currentWord6 =  record.get("Word6").trim();
            strBuilder.append("Word6:").append(currentWord6).append(',');
            
            String foil = record.get("Foil").trim();
            strBuilder.append("foil:").append(foil).append(',');
            
            String bandLabel = record.get("Noise_level").trim();
            if (bandLabel==null || bandLabel.isEmpty()) {
                continue;
            }
            strBuilder.append("bandLabel:").append(bandLabel);
            
            if (!bandIndex.contains(bandLabel)) {
                bandIndex.add(bandLabel);
            }
            
            String row =  strBuilder.toString();
            retVal.add(row);

        }
        return retVal;
    }
    
    

    
    public String arrayListAsStringArray(ArrayList<String> rows, String declaration){
        
        StringBuilder builder = new StringBuilder(declaration+" = {\n") ;
        for(String row:rows){
           builder.append("\"").append(row).append("\"").append(",\n"); 
        }
        builder.append("};");
        return builder.toString();
    }
    
    
     
}
