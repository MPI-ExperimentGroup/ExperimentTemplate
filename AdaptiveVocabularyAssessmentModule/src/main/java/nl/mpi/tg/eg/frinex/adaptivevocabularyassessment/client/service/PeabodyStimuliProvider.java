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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import java.util.Date;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.peabody.PeabodyStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.peabodypool.Indices;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.peabodypool.PeabodyStimuliFromString;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 *
 * @author olhshk
 */
public class PeabodyStimuliProvider extends BandStimuliProvider<PeabodyStimulus> {

    int baseSetIndex;
    int maxMistakesForOkBase = 4;
    int maxMistakesForOk = 8;
    int finalScore = 0;

    ArrayList<ArrayList<PeabodyStimulus>> stimuliPool;

    public PeabodyStimuliProvider(Stimulus[] stimulusArray) {
        super(stimulusArray);
    }

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        if (stimuliStateSnapshot.trim().isEmpty()) {
            this.bandIndexScore = 0;
            this.isCorrectCurrentResponse = null;
            this.bandVisitCounter = new Integer[this.numberOfBands];

            this.enoughFineTuningStimulae = true;
            this.champion = false;
            this.looser = false;

            this.baseSetIndex = this.startBand - 1;
            this.currentBandIndex = this.baseSetIndex;

            PeabodyStimuliFromString reader = new PeabodyStimuliFromString();
            try {
                reader.parseWordsInputCSVString(this.numberOfBands);
                this.stimuliPool = reader.getStimuliByBands();
            } catch (Exception exReading) {
                this.exceptionLogging(exReading);
            }

        } else {
            try {
                this.deserialiseToThis(stimuliStateSnapshot);
            } catch (Exception ex) {
                this.exceptionLogging(ex);
            }
        }

    }

    // in peabody experiment tupel contains 12 units (a unit/stimulus is picure + audio)
    @Override
    protected boolean fineTuningToBeContinuedWholeTuple() {

        boolean retVal;

        if (this.tupleIsNotEmpty()) {
            // we have not hit the last stimuli in the tuple yet
            // continue
            return true;
        } else {
            // tuple is empty: register that the band is finished 

            // analyse correctness of the current tuple as a whole
            boolean enoughCorrect = this.isEnoughCorrectResponses();

            if (enoughCorrect) {
                if (this.currentBandIndex == this.numberOfBands - 1) { // the last band is hit
                    this.champion = true;
                    this.bandIndexScore = this.numberOfBands - 1;
                    retVal = false;
                } else {
                    retVal = true;
                    if (this.currentBandIndex == this.baseSetIndex) {  // if we were climbing with bad results
                        // start moving down
                        this.currentBandIndex = (this.startBand - 1) + 1; // 1 band below the start band 
                    } else {
                        this.currentBandIndex++;
                    }
                }
            } else {
                if (this.currentBandIndex == 0) {
                    this.looser = false;
                    this.bandIndexScore = 0;
                    retVal = false;
                } else {
                    if (this.currentBandIndex == this.baseSetIndex) { // if we were climbing with bad results
                        this.currentBandIndex--;
                        this.baseSetIndex--;
                        retVal = true;
                    } else {
                        retVal = false;
                        this.bandIndexScore = this.currentBandIndex;
                    }
                }

            }
        }

        if (retVal) {
            // check if there are enough stimuli left
            this.enoughFineTuningStimulae = this.initialiseNextFineTuningTuple();
            if (!this.enoughFineTuningStimulae) {
                System.out.println(this.errorMessage);
                retVal = false;
            }
        } else {
            this.finalScore = this.getFinalScore(this.responseRecord);
        }

        return retVal;
    }

    @Override
    protected Boolean isEnoughCorrectResponses() {
        int wrongAnswers = 0;
        int lastIndex = this.responseRecord.size() - 1;
        for (int i = 0; i < this.fineTuningTupleLength; i++) {
            if (!this.responseRecord.get(lastIndex - i).getCorrectness()) {
                wrongAnswers++;
            }
        }
        if (this.currentBandIndex == this.baseSetIndex) {
            return wrongAnswers <= this.maxMistakesForOkBase;
        } else {
            return wrongAnswers <= this.maxMistakesForOk;
        }

    }

    public int getFinalScore(ArrayList<BookkeepingStimulus<PeabodyStimulus>> recordi) {
        int lastIndex = recordi.size() - 1;
        BookkeepingStimulus<PeabodyStimulus> lastBStimulus = recordi.get(lastIndex);
        String audioPath = lastBStimulus.getStimulus().getAudio();
        String[] bits = audioPath.split("_");
        int lastAudioIndex = Integer.parseInt(bits[0]);
        return lastAudioIndex;
    }

    @Override
    public boolean initialiseNextFineTuningTuple() {
        ArrayList<PeabodyStimulus> stimuli = this.stimuliPool.get(this.currentBandIndex);
        this.tupleFT = new ArrayList<BookkeepingStimulus<PeabodyStimulus>>();
        for (PeabodyStimulus stimulus : stimuli) {
            BookkeepingStimulus<PeabodyStimulus> bStimulus = new BookkeepingStimulus<PeabodyStimulus>(stimulus);
            this.tupleFT.add(bStimulus);
        }
        return true;
    }

    @Override
    public boolean analyseCorrectness(Stimulus stimulus, String stimulusResponse) {
        return stimulusResponse.equals(stimulus.getCorrectResponses());
    }

    @Override
    public String getStringFineTuningHistory(String startRow, String endRow, String startColumn, String endColumn, String format) {
        StringBuilder empty = new StringBuilder();
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);
        empty.append(startColumn).append(" ").append(endColumn);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("ImageFile").append(endColumn);
        stringBuilder.append(startColumn).append("AudioFile").append(endColumn);
        stringBuilder.append(startColumn).append("SetNumber").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("Timestamp").append(endColumn);
        stringBuilder.append(startColumn).append("Visiting Number").append(endColumn);
        stringBuilder.append(endRow);
        int modCounter = 0;
        ArrayList<String> spellingsCheck = new ArrayList<>();
        for (int i = this.timeTickEndFastTrack + 1; i < this.responseRecord.size(); i++) {
            BookkeepingStimulus<PeabodyStimulus> stimulus = this.responseRecord.get(i);
            StringBuilder row = new StringBuilder();
            String time = (new Date(stimulus.getTimeStamp())).toString();

            row.append(startColumn).append(stimulus.getStimulus().getImage()).append(endColumn);
            row.append(startColumn).append(stimulus.getStimulus().getAudio()).append(endColumn);
            row.append(startColumn).append(stimulus.getStimulus().getSetNumber()).append(endColumn);
            String reaction = stimulus.getReaction();
            row.append(startColumn).append(reaction).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(time).append(endColumn);
            row.append(startColumn).append(i).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
            modCounter++;
            if (!stimulus.getCorrectness() || modCounter == this.fineTuningTupleLength) {
                stringBuilder.append(startRow).append(empty).append(endRow); // skip between tuples
                stringBuilder.append(startRow).append(empty).append(endRow);
                modCounter = 0;
            }
            spellingsCheck.add(stimulus.getStimulus().getLabel());
        }

        return stringBuilder.toString();
    }

    @Override
    protected String bandIndexToLabel(int index) {
        return Indices.SET_INDEX[index];
    }

    @Override
    public String getStringFastTrack(String p1, String p2, String p3, String p4) {
        return "";
    }

    @Override
    protected void recycleUnusedStimuli() {
    }

    @Override
    protected void checkTimeOut() {
    }

    @Override
    protected void deserialiseSpecific(String str) {
    }

    @Override
    public boolean enoughStimuliForFastTrack() {
        return true;
    }

    @Override
    public BookkeepingStimulus<PeabodyStimulus> deriveNextFastTrackStimulus() {
        return null;
    }

    @Override
    public long getPercentageScore() {
        return -1;
    }
}
