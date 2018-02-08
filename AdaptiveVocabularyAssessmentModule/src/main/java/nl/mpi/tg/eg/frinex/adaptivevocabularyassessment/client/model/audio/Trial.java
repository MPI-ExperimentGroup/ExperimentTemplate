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
import java.util.Set;

/**
 *
 * @author olhshk
 */
public class Trial {

    private final ArrayList<AudioAsStimulus> wordList;  // the first one (zero index) is the cue, it is in the order it should appear for the participant
    private final String word;
    private final String targetNonWord;
    private final int numberOfSyllables;
    private final TrialCondition condition;
    private final int lgth;
    private final int PAUSE_EXAMPLE = 900;
    private final int PAUSE  = 500;
    private final int bandNumber;
    private final String dirName;

    public Trial(String word, String targetNonword, int nOfSyllables, TrialCondition condition, int length, LinkedHashMap<String,WordType> words, int bandNumber, String dirName) {

        this.word = word;
        this.targetNonWord = targetNonword;
        this.numberOfSyllables = nOfSyllables;
        this.lgth = length;
        this.bandNumber = bandNumber;
        this.dirName = dirName;
        this.condition = condition;
        this.wordList = new ArrayList<AudioAsStimulus>(length+1);
        AudioAsStimulus example = this.makeExampleStimulus(targetNonword);
        this.wordList.add(example);
        Set<String> keys = words.keySet();
        for (String label:keys){
            WordType wtype = words.get(label);
            AudioAsStimulus stimulus = this.makeStimulus(label, wtype);
            this.wordList.add(stimulus);
        }
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
    
    public int getBandNumber() {
        return this.bandNumber;
    }

    public String getTargetNonWord() {
        return this.targetNonWord;
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
    
    public static TrialCondition stringToCondition(String conditionString){
       TrialCondition retVal;
       switch (conditionString) {
            case "Target-only":
                retVal = TrialCondition.TARGET_ONLY;
                break;
            case "Target+Foil":
                retVal = TrialCondition.TARGET_AND_FOIL;
                break;
            case "NoTarget":
                retVal = TrialCondition.NO_TARGET;
                break;
            default:
                retVal = null;
                break;
        } 
       return retVal;
    }
    
    private AudioAsStimulus makeExampleStimulus(String targetNonword){
        
        // public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype) 
        String uniqueId = targetNonword+System.currentTimeMillis();
        String audioPath = this.dirName+"/"+targetNonword+"_"+this.bandNumber; // Florian is it so??
        AudioAsStimulus retVal = new AudioAsStimulus(uniqueId, targetNonword, PAUSE_EXAMPLE, audioPath, null, this.bandNumber, WordType.EXAMPLE_TARGET_NON_WORD);
        return retVal;
    }
    
    private AudioAsStimulus makeStimulus(String word, WordType wordtype){
        
        // public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, int bandNumber, WordType wordtype) 
        String uniqueId = word+System.currentTimeMillis();
        String audioPath = this.dirName+"/"+word+"_"+this.bandNumber; // Florian is it so??
        String correctResponses = (wordtype.equals(WordType.TARGET_NON_WORD)) ? AudioAsStimulus.AUDIO_RATING_LABEL : null;
        AudioAsStimulus retVal = new AudioAsStimulus(uniqueId, word, PAUSE, audioPath, correctResponses, this.bandNumber, wordtype);
        return retVal;
    }
    
    public void clearUsages(){
        for (AudioAsStimulus stimulus:this.wordList) {
            stimulus.setCorrectness(false);
            stimulus.setReaction(null);
        }
    }

}
