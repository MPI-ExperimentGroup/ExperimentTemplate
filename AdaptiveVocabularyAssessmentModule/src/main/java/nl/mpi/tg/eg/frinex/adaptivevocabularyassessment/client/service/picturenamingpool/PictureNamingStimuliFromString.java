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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.picturenamingpool;

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audiopool.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
//trial_nr; target_word; Zipf_freq; Length; syllables; Prev; ND; ND_freq; Picture
public class PictureNamingStimuliFromString {

    public String parseTrialsInputCSVStringIntoXml(String csvString, String stimuliDir, String type) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {
            
            String trialNr = record.get("trial_nr").trim();
            if (trialNr == null) {
                throw new IOException("trial_nr is undefined");
            }

            String targetWord = record.get("target_word").trim();
            if (targetWord == null) {
                throw new IOException("targetWord is undefined");
            }

            String zipfFrequency = record.get("Zipf_freq").trim();
            if (zipfFrequency == null) {
                throw new IOException("Zipf_freq is undefined");
            }
            zipfFrequency=zipfFrequency.replaceAll("\\.", "_");

            String length = record.get("Length").trim();
            if (length == null) {
                throw new IOException("Length is undefined");
            }

            String syllables = record.get("syllables").trim();
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            }

           
            String prev = record.get("Prev").trim();
            if (prev == null) {
                throw new IOException("Prev is undefined");
            }
            prev=prev.replaceAll("\\.", "");

            String nd = record.get("ND").trim();
            if (nd == null) {
                throw new IOException("ND is undefined");
            }

            String ndFreq = record.get("ND_freq").trim();
            if (ndFreq == null) {
                throw new IOException("ND_freq is undefined");
            }
            ndFreq=ndFreq.replaceAll("\\.", "");

            String picture = record.get("Picture").trim();
            if (picture == null) {
                throw new IOException("Picture is undefined");
            }

           
            String imagePath = stimuliDir + picture;

            String uniqueId = "trial_"+trialNr + "_" +targetWord;
            String label = targetWord;

            String tags = type + "  zipf_frequency_" + zipfFrequency + " Length_" + length + " syllables_" + syllables
                    + " Prev_" + prev + " ND_" + nd + " ND_freq_" + ndFreq;

            String currentSt = this.makeStimulusString(uniqueId, label, imagePath, tags);
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeStimulusString(String uniqueId,
            String label,
            String audioPath,
            String tags) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"0\" ");
        retVal.append(" imagePath=\"").append(audioPath).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
