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
//target_word	target_frequency	Length(Letters)	syllables	SUBTLEX_log	Prev	ND	ND_freq	Picture	Random
public class PictureNamingStimuliFromString {

    public String parseTrialsInputCSVStringIntoXml(String csvString, String stimuliDir, String type) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        for (LinkedHashMap<String, String> record : records) {

            String targetWord = record.get("target_word").trim();
            if (targetWord == null) {
                throw new IOException("targetWord is undefined");
            }

            String targetFrequency = record.get("target_frequency").trim();
            if (targetFrequency == null) {
                throw new IOException("target_frequency is undefined");
            }

            String lengthInLetters = record.get("Length(Letters)").trim();
            if (lengthInLetters == null) {
                throw new IOException("Length(Letters) is undefined");
            }

            String syllables = record.get("syllables").trim();
            if (syllables == null) {
                throw new IOException("Syllables is undefined");
            }

            String subtlextLog = record.get("SUBTLEX_log").trim();
            if (subtlextLog == null) {
                throw new IOException("SUBTLEX_log is undefined");
            }
            subtlextLog=subtlextLog.replaceAll("\\.", "");

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

            String random = "";
            if (!type.equals("practice")) {
                random = record.get("Random").trim();
                if (random == null) {
                    throw new IOException("Random is undefined");
                }
               random=random.replaceAll("\\.", "");
            }
            

            String imagePath = stimuliDir + picture;

            String uniqueId = targetWord;
            String label = targetWord;

            String tags = type + "  target_frequency_" + targetFrequency + " Length_letters_" + lengthInLetters + " syllables_" + syllables
                    + " SUBTLEX_log_" + subtlextLog + " Prev_" + prev + " ND_" + nd + " ND_freq_" + ndFreq + " Random_" + random;

            //target_word	target_frequency	Length(Letters)	syllables	SUBTLEX_log	Prev	ND	ND_freq	Picture	Random
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
