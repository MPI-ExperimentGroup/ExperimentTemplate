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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;

/**
 *
 * @author olhshk
 */
public class Trial {

    private final ArrayList<AudioAsStimulus> wordList;  // the first one (zero index) is the cue, it is in the order it should appear for the participant
    private final String word;
    private final String targetNonWordFile;
    private final int numberOfSyllables;
    private final TrialCondition condition;
    private final int lgth;
    private final int bandIndex;
    private final String bandLabel;
    private final String dirName;

    public Trial(String word, String targetNonwordFile, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String, WordType> words, String bandLabel, int bandIndex, String dirName) {

        this.word = word;
        this.targetNonWordFile = targetNonwordFile;
        this.numberOfSyllables = nOfSyllables;
        this.lgth = length;
        this.bandLabel = bandLabel;
        this.bandIndex = bandIndex;
        this.dirName = dirName;
        this.condition = condition;
        this.wordList = new ArrayList<AudioAsStimulus>(length + 1);
        AudioAsStimulus example = this.makeExampleStimulus(targetNonwordFile);
        this.wordList.add(example);
        Set<String> keys = words.keySet();
        for (String label : keys) {
            WordType wtype = words.get(label);
            AudioAsStimulus stimulus = this.makeStimulus(label, wtype);
            this.wordList.add(stimulus);
        }
    }

    public Trial(String word, String targetNonwordFile, int nOfSyllables, TrialCondition condition, int length, ArrayList<AudioAsStimulus> wordList, String bandLabel, int bandIndex, String dirName) {

        this.word = word;
        this.targetNonWordFile = targetNonwordFile;
        this.numberOfSyllables = nOfSyllables;
        this.lgth = length;
        this.bandLabel = bandLabel;
        this.bandIndex = bandIndex;
        this.dirName = dirName;
        this.condition = condition;
        this.wordList = wordList;
    }

    public ArrayList<AudioAsStimulus> getStimuliList() {
        return this.wordList;
    }

    public String getWord() {
        return this.word;
    }

    public String getDirName() {
        return this.dirName;
    }

    public int getBandIndex() {
        return this.bandIndex;
    }

    public String getBandLabel() {
        return this.bandLabel;
    }

    public String getTargetNonWord() {
        return this.targetNonWordFile;
    }

    public int getNumberOfSyllables() {
        return this.numberOfSyllables;
    }

    public TrialCondition getCondition() {
        return this.condition;
    }

    public int getTrialLength() {
        return this.lgth;
    }

    private AudioAsStimulus makeExampleStimulus(String targetNonwordFile) {

        // public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype) 
        String uniqueId = targetNonwordFile + System.currentTimeMillis();
        String audioPath = this.dirName + "/" + targetNonwordFile;
        AudioAsStimulus retVal = new AudioAsStimulus(uniqueId, targetNonwordFile, AudioAsStimulus.PAUSE_EXAMPLE, audioPath, null, this.bandLabel, this.bandIndex, WordType.EXAMPLE_TARGET_NON_WORD, AudioAsStimulus.EXAMPLE_TARGET_LABEL);
        return retVal;
    }

    private AudioAsStimulus makeStimulus(String wordFile, WordType wordtype) {

        // public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype) 
        String uniqueId = wordFile + System.currentTimeMillis();
        String audioPath = this.dirName + "/" + wordFile;
        String correctResponses = (wordtype.equals(WordType.TARGET_NON_WORD)) ? AudioAsStimulus.AUDIO_RATING_LABEL : null;
        AudioAsStimulus retVal = new AudioAsStimulus(uniqueId, wordFile, AudioAsStimulus.PAUSE, audioPath, correctResponses, this.bandLabel, this.bandIndex, wordtype, AudioAsStimulus.AUDIO_RATING_LABEL);
        return retVal;
    }

    @Override
    public String toString() {
//        
//    private final ArrayList<AudioAsStimulus> wordList;  // the first one (zero index) is the cue, it is in the order it should appear for the participant
//    private final String word;
//    private final String targetNonWordFile;
//    private final int numberOfSyllables;
//    private final TrialCondition condition;
//    private final int lgth;
//    private final int bandIndex;
//    private final String bandLabel;
//    private final String dirName;
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("word:{").append(this.word).append("},");
        builder.append("targetNonWordFile:{").append(this.targetNonWordFile).append("},");
        builder.append("numberOfSyllables:{").append(this.numberOfSyllables).append("},");
        builder.append("lgth:{").append(this.lgth).append("},");
        builder.append("bandIndex:{").append(this.bandIndex).append("},");
        builder.append("bandLabel:{").append(this.bandLabel).append("},");
        builder.append("dirName:{").append(this.dirName).append("},");
        builder.append("condition:{").append(this.condition).append("},");
        UtilsJSONdialect<AudioAsStimulus> util = new UtilsJSONdialect<AudioAsStimulus>();
        String wordListStr = "";
        try {
            wordListStr = util.arrayListToString(this.wordList);
        } catch (Exception ex) {

        }
        builder.append("wordList:").append(wordListStr);
        builder.append("}");
        return builder.toString();

    }

    public static Trial toObject(String str) {
        try {
            String word = UtilsJSONdialect.getKeyWithoutBrackets(str, "word");
            String targetNonWordFile = UtilsJSONdialect.getKeyWithoutBrackets(str, "targetNonWordFile");
            String numberOfSyllablesStr = UtilsJSONdialect.getKeyWithoutBrackets(str, "numberOfSyllables");
            String lgthStr = UtilsJSONdialect.getKeyWithoutBrackets(str, "lgth");
            String bandIndexStr = UtilsJSONdialect.getKeyWithoutBrackets(str, "bandIndex");
            String bandLabel = UtilsJSONdialect.getKeyWithoutBrackets(str, "bandLabel");
            String dirName = UtilsJSONdialect.getKeyWithoutBrackets(str, "dirName");
            String conditionStr = UtilsJSONdialect.getKeyWithoutBrackets(str, "condition");
            int numberOfSyllables = Integer.parseInt(numberOfSyllablesStr);
            int lgth = Integer.parseInt(lgthStr);
            int bandIndex = Integer.parseInt(bandIndexStr);
            TrialCondition condition = TrialCondition.stringToCondition(conditionStr);

            ArrayList<AudioAsStimulus> stimuli = new ArrayList<AudioAsStimulus>();
            String stimuliListStr = UtilsJSONdialect.getKey(str, "wordList");
            UtilsJSONdialect<AudioAsStimulus> util = new UtilsJSONdialect<AudioAsStimulus>();
            ArrayList<String> stimuliStrList = util.stringToArrayList(stimuliListStr);
            for (int i = 0; i < stimuliStrList.size(); i++) {
                AudioAsStimulus stimulus = AudioAsStimulus.toObject(stimuliStrList.get(i));
                stimuli.add(i, stimulus);
            }
            //Trial(String word, String targetNonwordFile, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String, WordType> words, String bandLabel, int bandIndex, String dirName)
            Trial retVal = new Trial(word, targetNonWordFile, numberOfSyllables, condition, lgth, stimuli, bandLabel, bandIndex, dirName);
            return retVal;
        } catch (Exception ex) {
            return null;
        }

    }

    public static String map3ToString(Map<TrialCondition, ArrayList<ArrayList<ArrayList<Trial>>>> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        UtilsJSONdialect<Trial> utils = new UtilsJSONdialect<Trial>();
        TrialCondition[] conditions = TrialCondition.values();
        int i=0;
        for (TrialCondition condition : conditions) {
            builder.append(condition).append(":");
            ArrayList<ArrayList<ArrayList<Trial>>> list = map.get(condition);
            try {
                String listStr = utils.arrayList3String(list);
                builder.append(listStr);
                if (i < conditions.length-1) {
                  builder.append(",");  
                }
            } catch (Exception ex) {
                return null;
            }
            i++;
        }
        builder.append("}");
        return builder.toString();
    }
    
   

}
