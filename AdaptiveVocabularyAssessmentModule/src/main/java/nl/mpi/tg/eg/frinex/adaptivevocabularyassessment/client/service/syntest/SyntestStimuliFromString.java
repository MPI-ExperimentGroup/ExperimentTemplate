/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.syntest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.CsvRecords;

/**
 *
 * @author olhshk
 */
public class SyntestStimuliFromString {

    // Item_nr;Sentence;Wav_file;Picture_A;Picture_B;Picture_C;Picture_D;wav_A;wav_B;wav_C;wav_D;Correct_picture\n
    public String parseImageRoundStringIntoXml(String csvString, String pictureStimuliDir, String audioStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        int practiceCounter = 0;
        int syntestCounter = 0;

        for (LinkedHashMap<String, String> record : records) {

            String itemNumber = record.get("Item_nr");
            if (itemNumber == null) {
                throw new IOException("Item_nr is undefined");
            } else {
                itemNumber = itemNumber.trim();
            }

            String sentence = record.get("Sentence");
            if (sentence == null) {
                throw new IOException("Sentence is undefined");
            } else {
                sentence = sentence.trim();
            }

            String sentenceAudio = record.get("Wav_file");
            if (sentenceAudio == null) {
                throw new IOException("verb is undefined");
            } else {
                sentenceAudio = sentenceAudio.trim();
                int lgth = sentenceAudio.length();
                // length of the substring '.wav' is 4
                sentenceAudio = sentenceAudio.substring(0, lgth - 4);
            }

            String sentenceA = record.get("Picture_A");
            if (sentenceA == null) {
                throw new IOException("Picture_A is undefined");
            } else {
                sentenceA = sentenceA.trim();
            }

            String sentenceB = record.get("Picture_B");
            if (sentenceB == null) {
                throw new IOException("Picture_B is undefined");
            } else {
                sentenceB = sentenceB.trim();
            }

            String sentenceC = record.get("Picture_C");
            if (sentenceC == null) {
                throw new IOException("Picture_C is undefined");
            } else {
                sentenceC = sentenceC.trim();
            }

            String sentenceD = record.get("Picture_D");
            if (sentenceD == null) {
                throw new IOException("Picture_D is undefined");
            } else {
                sentenceD = sentenceD.trim();
            }

            String correctResponse = record.get("Correct_picture");
            if (correctResponse == null) {
                throw new IOException("Correct_picture is undefined");
            } else {
                correctResponse = correctResponse.trim();
            }

            String uniqueId = itemNumber;
            String tags = null;
            String code = null;
            if (itemNumber.equals("SynTest_Pract")) {
                practiceCounter++;
                itemNumber = itemNumber + practiceCounter;
                tags = "practice_syntest_" + practiceCounter;
                if (practiceCounter == 1) {
                    code = "Intro";
                }
                if (practiceCounter == 2) {
                    code = "VB_";
                }
            } else {
                tags = "syntest";
                syntestCounter++;

                // checkup audio
                if (!sentenceAudio.equals("SynTest_" + syntestCounter)) {
                    throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + sentenceAudio);
                }

                // checkup images
                if (!sentenceA.equals("Taaltest-" + syntestCounter + "A.png")) {
                    throw new IOException("Trial " + uniqueId + ", Error in picutre file name:  " + sentenceA);
                }
                if (!sentenceB.equals("Taaltest-" + syntestCounter + "B.png")) {
                    throw new IOException("Trial " + uniqueId + ", Error in picutre file name:  " + sentenceB);
                }
                if (!sentenceC.equals("Taaltest-" + syntestCounter + "C.png")) {
                    throw new IOException("Trial " + uniqueId + ", Error in picutre file name:  " + sentenceC);
                }
                if (!sentenceD.equals("Taaltest-" + syntestCounter + "D.png")) {
                    throw new IOException("Trial " + uniqueId + ", Error in picutre file name:  " + sentenceD);
                }

                code = "Taaltest-";
            }

            String label = sentence;

            String audioPath = audioStimuliDir + sentenceAudio;
            String currentSt = this.makeAudioPictureStimulusString(uniqueId, label, correctResponse, audioPath, code, tags, "0");
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeAudioPictureStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String audioPath,
            String code,
            String tags,
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" audioPath=\"").append(audioPath).append("\" ");
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

    //Item_nr;Sentence;Wav_file;Picture_A;Picture_B;Picture_C;Picture_D;wav_A;wav_B;wav_C;wav_D;Correct_picture\n
    public String parseIdiomsStringIntoXml(String csvString, String pictureStimuliDir, String audioStimuliDir, String baseDir) throws Exception {

        StringBuilder builder = new StringBuilder();

        CsvRecords csvWrapper = new CsvRecords(null, ";", "\n");
        csvWrapper.readRecords(csvString);
        ArrayList<LinkedHashMap<String, String>> records = csvWrapper.getRecords();

        int idiomsCounter = 0;

        for (LinkedHashMap<String, String> record : records) {

            idiomsCounter++;

            String itemNumber = record.get("Item_nr");
            if (itemNumber == null) {
                throw new IOException("Item_nr is undefined");
            } else {
                itemNumber = itemNumber.trim();
            }

            String sentence = record.get("Sentence");
            if (sentence == null) {
                throw new IOException("Sentence is undefined");
            } else {
                sentence = sentence.trim();
            }

            String sentenceAudio = record.get("Wav_file");
            if (sentenceAudio == null) {
                throw new IOException("verb is undefined");
            } else {
                sentenceAudio = sentenceAudio.trim();
                int lgth = sentenceAudio.length();
                // length of the substring '.wav' is 4
                sentenceAudio = sentenceAudio.substring(0, lgth - 4);
            }

            String sentenceA = record.get("Picture_A");
            if (sentenceA == null) {
                throw new IOException("Picture_A is undefined");
            } else {
                sentenceA = sentenceA.trim();
            }

            String sentenceB = record.get("Picture_B");
            if (sentenceB == null) {
                throw new IOException("Picture_B is undefined");
            } else {
                sentenceB = sentenceB.trim();
            }

            String sentenceC = record.get("Picture_C");
            if (sentenceC == null) {
                throw new IOException("Picture_C is undefined");
            } else {
                sentenceC = sentenceC.trim();
            }

            String sentenceD = record.get("Picture_D");
            if (sentenceD == null) {
                throw new IOException("Picture_D is undefined");
            } else {
                sentenceD = sentenceD.trim();
            }

            String wavA = record.get("wav_A");
            if (wavA == null) {
                throw new IOException("wav_A is undefined");
            } else {
                wavA = wavA.trim();
                int lgth = wavA.length()-4;
                wavA = wavA.substring(0, lgth);
            }

            String wavB = record.get("wav_B");
            if (wavB == null) {
                throw new IOException("wav_B is undefined");
            } else {
                wavB = wavB.trim();
                int lgth = wavB.length()-4;
                wavB = wavB.substring(0, lgth);
            }

            String wavC = record.get("wav_C");
            if (wavC == null) {
                throw new IOException("wav_C is undefined");
            } else {
                wavC = wavC.trim();
                int lgth = wavC.length()-4;
                wavC = wavC.substring(0, lgth);
            }

            String wavD = record.get("wav_D");
            if (wavD == null) {
                throw new IOException("wav_D is undefined");
            } else {
                wavD = wavD.trim();
                int lgth = wavD.length()-4;
                wavD = wavD.substring(0, lgth);
            }

            String correctResponse = record.get("Correct_picture");
            if (correctResponse == null) {
                throw new IOException("Correct_picture is undefined");
            } else {
                correctResponse = correctResponse.trim();
            }

            String uniqueId = itemNumber;
            if (!uniqueId.equals("Idiom_" + idiomsCounter)) {
                throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + uniqueId);
            }

            String tags = "idiom";
            String code = uniqueId;

            // checkup audio
            if (!sentenceAudio.equals("Idiom_" + idiomsCounter)) {
                throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + sentenceAudio);
            }

            if (!wavA.equals("Idiom_" + idiomsCounter + "_A")) {
                throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + wavA);
            }
            if (!wavB.equals("Idiom_" + idiomsCounter + "_B")) {
                throw new IOException("Idiom_" + idiomsCounter + ", Error in audio file name:  " + wavB);
            }
            if (!wavC.equals("Idiom_" + idiomsCounter + "_C")) {
                throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + wavC);
            }
            if (!wavD.equals("Idiom_" + idiomsCounter + "_D")) {
                throw new IOException("Trial " + uniqueId + ", Error in audio file name:  " + wavD);
            }

            String label = sentence;
            String ratingLabels = sentenceA+","+sentenceB+","+sentenceC+","+sentenceD;

            String currentSt = this.makeIdiomsStimulusString(uniqueId, label, correctResponse, ratingLabels, code, tags, "0");
            builder.append(currentSt);

        }

        return builder.toString();
    }

    private String makeIdiomsStimulusString(String uniqueId,
            String label,
            String correctResponse,
            String ratingLabels,
            String code,
            String tags,
            String pause) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<stimulus ");
        retVal.append(" identifier=\"").append(uniqueId).append("\" ");
        retVal.append(" label=\"").append(label).append("\" ");

        retVal.append(" pauseMs=\"").append(pause).append("\" ");

        retVal.append(" ratingLabels=\"").append(ratingLabels).append("\" ");
        retVal.append(" code=\"").append(code).append("\" ");
        retVal.append(" correctResponses=\"").append(correctResponse).append("\" ");

        retVal.append(" tags=\"").append(tags).append("\" ");

        retVal.append(" />\n");
        return retVal.toString();

    }

}
