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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool;

import java.util.HashMap;

/**
 *
 * @author olhshk
 */
public class SourcenameIndices {

    public static final HashMap<String, CsvStringWrapper> STIMULI_FILES_INDEX;
    public static final HashMap<String, String> RESPONSES_INDEX;
    public static final HashMap<String, String> LANGUAGE_INDEX;
    public static final HashMap<String, CsvStringWrapper> SHABLON_INDEX;
    
    static {
        SHABLON_INDEX = new HashMap<String, CsvStringWrapper>();
        SHABLON_INDEX.put("FastTrackShablonOrigin_NL", new FastTrackShablonOrigin());
        SHABLON_INDEX.put("FastTrackShablonOrigin_HUN", new FastTrackShablonOrigin_HUN());
        SHABLON_INDEX.put("FineTuningShablonOrigin_NL", new FineTuningShablonOrigin());
        SHABLON_INDEX.put("FineTuningShablonOrigin_HUN", new FineTuningShablonOrigin());
    }
    
   
    static {
        STIMULI_FILES_INDEX = new HashMap<String, CsvStringWrapper>();
        STIMULI_FILES_INDEX.put("NonWords_EN_2rounds_1", new NonWords_EN_2rounds_1());
        STIMULI_FILES_INDEX.put("NonWords_EN_2rounds_2", new NonWords_EN_2rounds_2());
        STIMULI_FILES_INDEX.put("Words_EN_2rounds_1", new Words_EN_2rounds_1());
        STIMULI_FILES_INDEX.put("Words_EN_2rounds_2", new Words_EN_2rounds_2());
        STIMULI_FILES_INDEX.put("NonWords_NL_2rounds_1", new NonWords_NL_2rounds_1());
        STIMULI_FILES_INDEX.put("NonWords_NL_2rounds_2", new NonWords_NL_2rounds_2());
        STIMULI_FILES_INDEX.put("Words_NL_2rounds_1", new Words_NL_2rounds_1());
        STIMULI_FILES_INDEX.put("Words_NL_2rounds_2", new Words_NL_2rounds_2());
        STIMULI_FILES_INDEX.put("NonWords_NL_1round", new NonWords_NL_1round());
        STIMULI_FILES_INDEX.put("Words_NL_1round", new Words_NL_1round());
        
        STIMULI_FILES_INDEX.put("NonWords_HUN_round_1", new NonWords_HUN_round_1());
        STIMULI_FILES_INDEX.put("NonWords_HUN_round_2", new NonWords_HUN_round_2());
        STIMULI_FILES_INDEX.put("Words_HUN_round_1", new Words_HUN_round_1());
        STIMULI_FILES_INDEX.put("Words_HUN_round_2", new Words_HUN_round_2());
    }

    static {
        RESPONSES_INDEX = new HashMap<String, String>();
        RESPONSES_INDEX.put("NonWords_EN_2rounds_1", "NO&#44; I don’t know this word");
        RESPONSES_INDEX.put("NonWords_EN_2rounds_2", "NO&#44; I don’t know this word");
        RESPONSES_INDEX.put("Words_EN_2rounds_1", "YES&#44; I know this word");
        RESPONSES_INDEX.put("Words_EN_2rounds_2", "YES&#44; I know this word");
        RESPONSES_INDEX.put("NonWords_NL_2rounds_1", "NEE&#44; ik ken dit woord niet");
        RESPONSES_INDEX.put("NonWords_NL_2rounds_2", "NEE&#44; ik ken dit woord niet");
        RESPONSES_INDEX.put("Words_NL_2rounds_1", "JA&#44; ik ken dit woord");
        RESPONSES_INDEX.put("Words_NL_2rounds_2", "JA&#44; ik ken dit woord");
        RESPONSES_INDEX.put("NonWords_NL_1round", "NEE&#44; ik ken dit woord niet");
        RESPONSES_INDEX.put("Words_NL_1round", "JA&#44; ik ken dit woord");
        
        RESPONSES_INDEX.put("NonWords_HUN_round_1", "Nem ismerem a szót");
        RESPONSES_INDEX.put("NonWords_HUN_round_2", "Nem ismerem a szót");
        RESPONSES_INDEX.put("Words_HUN_round_1", "Igen&#44; ismerem a szót");
        RESPONSES_INDEX.put("Words_HUN_round_2", "Igen&#44; ismerem a szót");
    }

    static {
        LANGUAGE_INDEX = new HashMap<String, String>();
        LANGUAGE_INDEX.put("NonWords_EN_2rounds_1", "EN");
        LANGUAGE_INDEX.put("NonWords_EN_2rounds_2", "EN");
        LANGUAGE_INDEX.put("Words_EN_2rounds_1", "EN");
        LANGUAGE_INDEX.put("Words_EN_2rounds_2", "EN");
        LANGUAGE_INDEX.put("NonWords_NL_2rounds_1", "NL");
        LANGUAGE_INDEX.put("NonWords_NL_2rounds_2", "NL");
        LANGUAGE_INDEX.put("Words_NL_2rounds_1", "NL");
        LANGUAGE_INDEX.put("Words_NL_2rounds_2", "NL");
        LANGUAGE_INDEX.put("NonWords_NL_1round", "NL");
        LANGUAGE_INDEX.put("Words_NL_1round", "NL");
        
        LANGUAGE_INDEX.put("NonWords_HUN_round_1", "HUN");
        LANGUAGE_INDEX.put("NonWords_HUN_round_2", "HUN");
        LANGUAGE_INDEX.put("Words_HUN_round_1", "HUN");
        LANGUAGE_INDEX.put("Words_HUN_round_2", "HUN");
    }

    public static String getOverview(long percentage, String lang) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        switch (lang) {
            case "NL": {
                //htmlStringBuilder.append("<p><small>(Scroll om volledig resultaten te bekijken als dat nodig is.)</small></p>");
                htmlStringBuilder.append("<p>Overzicht van uw resultaten: U kent ongeveer <big><big><b>").append(percentage).append("</b></big></big> &#37; van alle Nederlandse woorden.</p>");
                break;
            }
            case "HUN": {
                htmlStringBuilder.append("<p>Overview of your results</p>");
                htmlStringBuilder.append("<p>You know about <big><big><b>").append(percentage).append("</b></big></big> &#37; of all Hungarian words</p>");
                break;
            }
            default: {
                htmlStringBuilder.append("<p>Overview of your results.</p>");
                htmlStringBuilder.append("<p>You know about <big><big><b>").append(percentage).append("</b></big></big> &#37; of all English words</p>");
                break;
            }
        }
        return htmlStringBuilder.toString();
    }

    public static HashMap<String, String> getWordListHeaders(String lang) {
        HashMap<String, String> retVal = new HashMap<String, String>();

        switch (lang) {
            case "NL": {
                retVal.put("capture", "Groen=Correct herkend, Rood=Niet correct herkend");
                retVal.put("headerWords", "Woorden");
                retVal.put("headerNonWords", "Nep-woorden");
                break;
            }
            case "HUN": {
                retVal.put("capture", "Green=Correctly recognised, Red=Wrongly recognised");
                retVal.put("headerWords", "Words");
                retVal.put("headerNonWords", "Non-words");
                break;
            }
            default: {
                retVal.put("capture", "Green=Correctly recognised, Red=Wrongly recognised");
                retVal.put("headerWords", "Words");
                retVal.put("headerNonWords", "Non-words");
                break;
            }
        }
        return retVal;
    }

    public static String diagramHelper(String lang) {

        switch (lang) {
            case "NL": {
                return "<tr><td>PERCENTAGE</td><td></td><td>VOORBEELD woord</td></tr>";
            }
            case "HUN": {
                return "<tr><td>PERCENTAGE</td><td></td><td>EXAMPLE word</td></tr>";
            }
            default: {
                return "<tr><td>PERCENTAGE</td><td></td><td>EXAMPLE word</td></tr>";
            }
        }
    }

}
