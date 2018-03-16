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

import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
abstract public class AudioAsStimulus extends BandStimulus {

    private final WordType wordType;
    private final int positionInTrial;
    
    
    private final int trialNumber;
    private final String trialWord;
    private final String trialCueFile;
    private final int trialSyllables;
    private final TrialCondition trialCondition;
    private final int trialLength;
    private final int trialPositionTarget;
    private final int trialPositionFoil;      
    
    
    public static final String AUDIO_RATING_LABEL = "&#160;";
    public static final String EXAMPLE_TARGET_LABEL = null;
    public static final int PAUSE_EXAMPLE = 60000;
    public static final int PAUSE = 900;
    
 
    public AudioAsStimulus(String uniqueId, String label, int pauseMs, String audioPath, String correctResponses, String ratingLabel, 
            String wordtype, String positionInTrial,
            String bandLabel, String bandIndex, 
            String trialNumber, String  trialWord, String cueFile, String trialSyllables, String trialCondition, String trialLength,  String trialPositionTarget, String trialPositionFoil) {
        super(uniqueId, new Stimulus.Tag[0], label, null, pauseMs, audioPath, null, null, ratingLabel, correctResponses, bandLabel, bandIndex);
        
        this.wordType = WordType.valueOf(wordtype);
        this.positionInTrial = Integer.parseInt(positionInTrial);
        
        this.trialNumber = Integer.parseInt(trialNumber);
        this.trialWord = trialWord;
        this.trialCueFile = cueFile;
        this.trialSyllables = Integer.parseInt(trialSyllables);
        this.trialCondition = TrialCondition.valueOf(trialCondition);
        this.trialLength = Integer.parseInt(trialLength);
        this.trialPositionTarget = Integer.parseInt(trialPositionTarget);
        this.trialPositionFoil = Integer.parseInt(trialPositionFoil );
    }

 
    abstract public String getwordType();
    abstract public String getpositionInTrial();
    
    
    abstract public String gettrialNumber();
    abstract public String gettrialWord();
    abstract public String gettrialCueFile();
    abstract public String gettrialSyllables();
    abstract public String gettrialCondition();
    abstract public String gettrialLength();
    abstract public String gettrialPositionTarget();
    abstract public String gettrialPositionFoil();
   
    public WordType getWordType() {
        return this.wordType;
    }
    
    public int getPositionInTrial() {
        return this.positionInTrial;
    }
    
    public int getTrialNumber() {
        return this.trialNumber;
    }
    
    public String getTrialWord() {
        return this.trialWord;
    }
    
    public String getTrialCueFile() {
        return this.trialCueFile;
    }
    
    public int getTrialSyllables() {
        return this.trialSyllables;
    }
    
    public TrialCondition getTrialCondition() {
        return this.trialCondition;
    }
    
    public int getTrialLength() {
        return this.trialLength;
    }
    
    public int getTrialPositionTarget() {
        return this.trialPositionTarget;
    }
    
     public int getTrialPositionFoil() {
        return this.trialPositionFoil;
    }

    @Override
    public boolean hasCorrectResponses() {
        return true;
    }

  

   
}
