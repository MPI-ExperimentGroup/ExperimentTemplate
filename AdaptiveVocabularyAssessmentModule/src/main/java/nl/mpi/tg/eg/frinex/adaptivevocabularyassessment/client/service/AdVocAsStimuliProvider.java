package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

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
// /Users/olhshk/Documents/ExperimentTemplate/FieldKitRecorder/src/android/nl/mpi/tg/eg/frinex/FieldKitRecorder.java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.UtilsJSONdialect;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary.AdVocAsStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.AdVocAsStimuliFromString;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.FastTrackShablonElement;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.FineTuningShablonElement;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.SourcenameIndices;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends AbstractStimuliProvider {

    private final static String[] FLDS = {
        "wordsSource", "nonwordsSource", "fastTrackShablonSource", "fineTuningShablonSource",
        "numberOfBands", "fineTuningUpperBoundForCycles", "maxDurationMinutes", // constructor parameters
        "words", "nonwords", "startBand",
        "bandIndexScore", "isCorrectCurrentResponse", "currentBandIndex", "totalStimuli",
        "responseRecord", "tupleFT",
        "lastCorrectBandFastTrack", "isFastTrackIsStillOn", "secondChanceFastTrackIsFired", "timeTickEndFastTrack", "enoughFastTrackStimulae",
        "enoughFineTuningStimulae", "bandVisitCounter", "cycle2helper",
        "cycle2", "champion", "looser", "justVisitedLastBand", "justVisitedLowestBand", "errorMessage"};

    // constructr parameters, must be setters for them
    private String wordsSource = null;
    private String nonwordsSource = null;
    private String fastTrackShablonSource = null; // "FastTrackShablonOrigin_NL"
    private String fineTuningShablonSource = null; // "FineTuningShablonOrigin_NL"
    private int numberOfBands = 0;
    private int fineTuningUpperBoundForCycles = 0;
    private String maxDurationMinutes = null;

    // derived parameters
    private int startBand = 0;
    private int fineTuningTupleLength = 0;

    private int bandIndexScore = 0;
    private int totalStimuli = 0;

    private ArrayList<BookkeepingStimulus<AdVocAsStimulus>> responseRecord = new ArrayList<>();

    // fast track stuff
    private boolean isFastTrackIsStillOn = true;
    private boolean fastTrackCorrectorOn = false;
    private int timeTickEndFastTrack = 0;
    private int lastCorrectBandFastTrack;
    private boolean enoughFastTrackStimulae;

    // fine tuning stuff
    private ArrayList<BookkeepingStimulus<AdVocAsStimulus>> tupleFT = new ArrayList<>(this.fineTuningTupleLength);
    private int currentBandIndexFineTuning;

    // fine tuning stopping
    private boolean enoughFineTuningStimulae = true;
    private Integer[] bandVisitCounter = new Integer[0];
    private Integer[] cycle2helper = new Integer[0];
    private boolean cycle2 = false;
    private boolean champion = false;
    private boolean looser = false;
    private boolean justVisitedLastBand = false;
    private boolean justVisitedLowestBand = false;
    private String errorMessage = null;
    private boolean ftToBeContinued;

    //optional
    private long maxDurationMs = 0;
    private long startTimeMs = 0;
    private boolean timeOutExit = false;
    private long durationMs = 0;

    private String wordsResponse;
    private String nonwordsResponse;
    private int fastTrackShablonPosition;
    private int fineTuningShablonPosition;

    private ArrayList<ArrayList<AdVocAsStimulus>> words;
    private ArrayList<AdVocAsStimulus> nonwords;
    private ArrayList<FastTrackShablonElement> fastTrackSceletone;
    private ArrayList<FineTuningShablonElement> fineTuningSceletone;

    private LinkedHashMap<Long, Integer> percentageBandTable;

    public AdVocAsStimuliProvider(Stimulus[] stimulusArray) {
        super(stimulusArray);
    }

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        try {

            this.percentageBandTable = this.generatePercentageBandTable();

            if (stimuliStateSnapshot.trim().isEmpty()) {

                this.bandIndexScore = 0;
                this.bandVisitCounter = new Integer[this.numberOfBands];

                this.enoughFineTuningStimulae = true;
                for (int i = 0; i < this.numberOfBands; i++) {
                    this.bandVisitCounter[i] = 0;
                }

                this.cycle2helper = new Integer[this.fineTuningUpperBoundForCycles * 2 + 1];
                for (int i = 0; i < this.fineTuningUpperBoundForCycles * 2 + 1; i++) {
                    this.cycle2helper[i] = 0;
                }
                this.cycle2 = false;
                this.champion = false;
                this.looser = false;
                this.justVisitedLastBand = false;
                this.startTimeMs = 0;

                this.wordsResponse = SourcenameIndices.RESPONSES_INDEX.get(this.wordsSource);
                this.nonwordsResponse = SourcenameIndices.RESPONSES_INDEX.get(this.nonwordsSource);

                AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
                reader.parseWordsInputCSVString(this, this.wordsSource, this.nonwordsSource, this.numberOfBands);
                reader.parseNonWordsInputCSVString(this, this.nonwordsSource, this.wordsSource);
                ArrayList<ArrayList<AdVocAsStimulus>> rawWords = reader.getWords();
                ArrayList<AdVocAsStimulus> rawNonwords = reader.getNonwords();

                this.fastTrackSceletone = reader.parseFastTrackOriginString(this, this.fastTrackShablonSource, this.numberOfBands);
                this.startBand = this.fastTrackSceletone.get(0).getBandNumber();
                this.lastCorrectBandFastTrack = 0;
                this.fastTrackShablonPosition = 0;

                this.fineTuningSceletone = reader.parseFineTuningOriginString(this, this.fineTuningShablonSource, this.numberOfBands);
                this.fineTuningTupleLength = this.fineTuningSceletone.get(0).getArray().length;
                this.fineTuningShablonPosition = 0;

                this.words = rawWords;
                this.nonwords = rawNonwords;

                this.totalStimuli = this.nonwords.size();
                for (int i = 0; i < this.numberOfBands; i++) {
                    this.totalStimuli += this.words.get(i).size();
                }

            } else {
                this.deserialise(stimuliStateSnapshot);
            }
        } catch (Exception ex) {
            this.exceptionLogging(ex);
        }

    }

    private void exceptionLogging(Exception ex) {
        System.out.println();
        System.out.println(ex);
        for (StackTraceElement message : ex.getStackTrace()) {
            System.out.println(message);

        }
        System.out.println();
    }

    public void setmaxDurationMinutes(String maxDurationMinutes) {
        this.maxDurationMinutes = maxDurationMinutes;

        if (this.maxDurationMinutes != null) {
            
            this.maxDurationMs = Long.parseLong(maxDurationMinutes) * 60 * 1000;
        }

    }

    public void setnumberOfBands(String nBands) {
        this.numberOfBands = Integer.parseInt(nBands);
    }

    public void setfineTuningUpperBoundForCycles(String uBound) {
        this.fineTuningUpperBoundForCycles = Integer.parseInt(uBound);
    }

    public void setwordsSource(String wordsSource) {
        this.wordsSource = wordsSource;
    }

    public void setnonwordsSource(String nonwordsSource) {
        this.nonwordsSource = nonwordsSource;
    }

    public void setfastTrackShablonSource(String fastTrackShablonSource) {
        this.fastTrackShablonSource = fastTrackShablonSource;
    }

    public void setfineTuningShablonSource(String fineTuningShablonSource) {
        this.fineTuningShablonSource = fineTuningShablonSource;
    }

    // called when the button is pressed
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        int currentIndex = this.responseRecord.size() - 1;
        this.responseRecord.get(currentIndex).setReaction(stimulusResponse);
        this.responseRecord.get(currentIndex).setTimeStamp(System.currentTimeMillis());
        boolean correct = this.analyseCorrectness(stimulus, stimulusResponse);
        this.responseRecord.get(currentIndex).setCorrectness(correct);
        return correct;

    }

    @Override
    public String getCurrentStimulusUniqueId() {
        return this.getCurrentStimulus().getUniqueId();
    }

    @Override
    public String generateStimuliStateSnapshot() {
        return this.toString();
    }

    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
    }

    @Override
    public int getCurrentStimulusIndex() {
        return (this.responseRecord.size() - 1);
    }

    @Override
    public AdVocAsStimulus getCurrentStimulus() {
        if (this.responseRecord == null) {
            return null;
        }
        if (this.responseRecord.isEmpty()) {
            return null;
        }
        return this.responseRecord.get(this.getCurrentStimulusIndex()).getStimulus();
    }

    @Override
    public int getTotalStimuli() {
        return this.totalStimuli;
    }

    public Integer[] getBandVisitCounter() {
        return this.bandVisitCounter;
    }

    public Integer[] getCycleHelper() {
        return this.cycle2helper;
    }

    public int getStartBand() {
        return this.startBand;
    }

    public int getLastCorrrectFastTrackBand() {
        return this.lastCorrectBandFastTrack;
    }

    // actually defines if the series is to be continued or stopped
    // also set the band indices and current stimulus via fastTrackToBeContinued(), or switchToFineTuning(), or fineTuningToBeContinued()
    @Override
    public boolean hasNextStimulus(int increment) {

        // setting the very first stimulus
        if (this.responseRecord.isEmpty()) {
            this.startTimeMs = System.currentTimeMillis();
            AdVocAsStimulus newStimulus;
            boolean isWord = this.fastTrackSceletone.get(this.fastTrackShablonPosition).getIsWord();
            if (isWord) {
                newStimulus = this.words.get(this.startBand - 1).remove(0);
            } else { // should not happen with the given shablone
                newStimulus = this.nonwords.remove(0);

            }
            BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
            this.responseRecord.add(bStimulus);
            this.fastTrackCorrectorOn = false;
            return true;
        }

        // flow
        if (this.isFastTrackIsStillOn) {
            this.isFastTrackIsStillOn = this.fastTrackToBeContinued();
            if (this.isFastTrackIsStillOn) {
                return true;
            } else {
                return this.switchToFineTuning();
            }
        } else {
            return this.fineTuningToBeContinued();
        }

    }

    // all indices anc current stimulus are updated in the "hasNextStimulus"
    @Override
    public void nextStimulus(int increment) {

    }

    public boolean getIsFastTrackIsStillOn() {
        return this.isFastTrackIsStillOn;
    }

    public ArrayList<BookkeepingStimulus<AdVocAsStimulus>> getRecords() {
        return this.responseRecord;
    }

    public int getFastTrackShablonIndex() {
        return this.fastTrackShablonPosition;
    }

    public int getFineTuningShablonPosition() {
        return this.fineTuningShablonPosition;
    }

    private int nextShablonPosition(int pos, int arraySize) {
        if (pos < arraySize - 1) {
            return pos + 1;
        } else {
            return 0;
        }
    }

    // updates indices and set next (new current) stimulus
    public boolean fastTrackToBeContinued() {

        AdVocAsStimulus currentStimulus = this.getCurrentStimulus();
        int currentIndex = this.getCurrentStimulusIndex();
        boolean correctCurrentResponse = this.responseRecord.get(currentIndex).getCorrectness();

        // sanity check 1, cannot be done on correctors
        if (!this.fastTrackCorrectorOn) {
            if (currentStimulus.getBandNumber() != this.fastTrackSceletone.get(this.fastTrackShablonPosition).getBandNumber()) {
                System.out.println("FAST TRACK!: Discrepance between frinex current-stimulus band number " + currentStimulus.getBandNumber()
                        + " and the current shablon band number " + this.fastTrackSceletone.get(this.fastTrackShablonPosition).getBandNumber());
                System.out.println("Shablon position " + this.fastTrackShablonPosition);
                return false;
            }
        }

        if (currentStimulus.getBandNumber() > 0) { // current stimulus is word

            // sanity check 2 
            if (this.lastCorrectBandFastTrack > 0) {
                if (currentStimulus.getBandNumber() != this.lastCorrectBandFastTrack + 1) {
                    int bNumber = this.lastCorrectBandFastTrack + 1;
                    System.out.println("FAST TRACK!: Discrepance  between frinex calculated bandNumber " + bNumber + " and the current band number " + currentStimulus.getBandNumber());
                    System.out.println("Shablon position " + this.fastTrackShablonPosition);
                    return false;
                }
            }

            if (correctCurrentResponse) { // correct response on word 

                this.lastCorrectBandFastTrack = currentStimulus.getBandNumber();

                if (currentStimulus.getBandNumber() == this.numberOfBands) { // we are on the last band, there is nothing to improve
                    return false;
                }

                this.fastTrackCorrectorOn = false;
                this.fastTrackShablonPosition = this.nextShablonPosition(this.fastTrackShablonPosition, this.fastTrackSceletone.size());

                // set next stimulus
                AdVocAsStimulus newStimulus;

                if (this.fastTrackSceletone.get(this.fastTrackShablonPosition).getIsWord()) {
                    int bNumber = this.fastTrackSceletone.get(this.fastTrackShablonPosition).getBandNumber();
                    if (this.words.get(bNumber - 1).isEmpty()) {
                        this.enoughFastTrackStimulae = false;
                        return false;
                    }
                    newStimulus = this.words.get(bNumber - 1).remove(0);
                } else {
                    if (this.nonwords.isEmpty()) {
                        this.enoughFastTrackStimulae = false;
                        return false;
                    }
                    newStimulus = this.nonwords.remove(0);
                }
                BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
                this.responseRecord.add(bStimulus);
                return true;

            } else { // incorrect response on word
                if (this.fastTrackCorrectorOn) {
                    return false; // error on corrector
                } else {
                    this.fastTrackCorrectorOn = true;
                    int bNumber = currentStimulus.getBandNumber();
                    if (this.words.get(bNumber - 1).isEmpty()) {
                        this.enoughFastTrackStimulae = false;
                        return false;
                    }
                    AdVocAsStimulus newStimulus = this.words.get(bNumber - 1).remove(0);
                    BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
                    this.responseRecord.add(bStimulus);
                    return true;
                }

            }

        } else {   // current non-word

            if (correctCurrentResponse) { // correct on nonword
                this.fastTrackCorrectorOn = false;
                this.fastTrackShablonPosition = this.nextShablonPosition(this.fastTrackShablonPosition, this.fastTrackSceletone.size());
                // set next stimulus
                if (this.fastTrackSceletone.get(this.fastTrackShablonPosition).getIsWord()) {
                    int bNumber = this.fastTrackSceletone.get(this.fastTrackShablonPosition).getBandNumber();
                    if (this.words.get(bNumber - 1).isEmpty()) {
                        this.enoughFastTrackStimulae = false;
                        return false;
                    }
                    AdVocAsStimulus newStimulus = this.words.get(bNumber - 1).remove(0);
                    BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
                    this.responseRecord.add(bStimulus);
                    return true;

                } else {
                    System.out.println("The second non-word in a row on fast track shablon position " + this.fastTrackShablonPosition);
                    return false;
                }

            } else { // incorrect on nonword

                if (this.fastTrackCorrectorOn) {
                    System.out.println("Fast track!! corrector must not be a non-word!");
                    return false; // error on corrector
                } else {
                    this.fastTrackCorrectorOn = true;
                    this.fastTrackShablonPosition = this.nextShablonPosition(this.fastTrackShablonPosition, this.fastTrackSceletone.size());
                    if (this.fastTrackSceletone.get(this.fastTrackShablonPosition).getIsWord()) {
                        int bNumber = this.fastTrackSceletone.get(this.fastTrackShablonPosition).getBandNumber();
                        if (this.words.get(bNumber - 1).isEmpty()) {
                            this.enoughFastTrackStimulae = false;
                            return false;
                        }
                        AdVocAsStimulus newStimulus = this.words.get(bNumber - 1).remove(0);
                        BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
                        this.responseRecord.add(bStimulus);
                        return true;
                    } else {
                        System.out.println("The corrector of a  non-word iis a non-word! " + this.fastTrackShablonPosition);
                        return false;
                    }
                }
            }

        }
    }

    private boolean switchToFineTuning() {
        this.timeTickEndFastTrack = this.responseRecord.size() - 1; // the last time on fast track (if we start counting form zero)
        this.currentBandIndexFineTuning = this.lastCorrectBandFastTrack > 0 ? this.lastCorrectBandFastTrack - 1 : 19;
        this.fineTuningShablonPosition = 0;
        this.ftToBeContinued = true;
        return this.initialiseNextFineTuningTuple();
    }

    // we already at the right band. this.lastCorrectBAndFastTrack, the last band in the fast track with the correct answer
    // return false if there is not enough words and nonwords
    public boolean initialiseNextFineTuningTuple() {
        if (this.nonwords.size() < 1) {
            this.errorMessage = "There is no non-words left.";
            return false;
        }
        if (this.words.get(this.currentBandIndexFineTuning).size() < this.fineTuningTupleLength - 1) {
            this.errorMessage = "There is not enough stimuli for the band of index " + this.currentBandIndexFineTuning;
            return false;
        }

        boolean[] currentTupleShablon = this.fineTuningSceletone.get(this.fineTuningShablonPosition).getArray();
        this.tupleFT = new ArrayList<>(currentTupleShablon.length);

        for (int i = 0; i < currentTupleShablon.length; i++) {
            if (currentTupleShablon[i]) { // is word 
                if (this.words.get(this.currentBandIndexFineTuning).isEmpty()) {
                    this.enoughFineTuningStimulae = false;
                    return false;
                }
                BookkeepingStimulus<AdVocAsStimulus> bStimulus = new BookkeepingStimulus<>(this.words.get(this.currentBandIndexFineTuning).remove(0));
                this.tupleFT.add(bStimulus);
            } else {
                if (this.nonwords.isEmpty()) {
                    this.enoughFineTuningStimulae = false;
                    return false;
                }
                BookkeepingStimulus<AdVocAsStimulus> bStimulus = new BookkeepingStimulus<>(this.nonwords.remove(0));
                this.tupleFT.add(bStimulus);
            }

        }
        AdVocAsStimulus newStimulus = this.tupleFT.remove(0).getStimulus();
        BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
        this.responseRecord.add(bStimulus);
        return true;
    }

    public ArrayList<BookkeepingStimulus<AdVocAsStimulus>> getFTtuple() {
        return this.tupleFT;
    }

    public boolean getFTtoBeContinued() {
        return this.ftToBeContinued;
    }

    public int getCurrentBandFineTuning() {
        return (this.currentBandIndexFineTuning + 1);
    }

    public boolean fineTuningToBeContinued() {
        
        if (this.maxDurationMinutes != null) {
            this.isTimeOut();
            if (this.timeOutExit) {
                this.ftToBeContinued = false;
                return false;
            }
        } 
        

        boolean retVal;

        int currentIndex = this.getCurrentStimulusIndex();
        boolean correctCurrentResponse = this.responseRecord.get(currentIndex).getCorrectness();

        if (correctCurrentResponse) {
            if (!this.tupleFT.isEmpty()) { // tuple is not ended
                AdVocAsStimulus newStimulus = this.tupleFT.remove(0).getStimulus();
                BookkeepingStimulus bStimulus = new BookkeepingStimulus(newStimulus);
                this.responseRecord.add(bStimulus);
                return true;

            } else {  // the tuple is ended, all correct
                this.bandVisitCounter[this.currentBandIndexFineTuning]++;
                shiftFIFO(this.cycle2helper, this.currentBandIndexFineTuning);
                this.fineTuningShablonPosition = this.nextShablonPosition(this.fineTuningShablonPosition, this.fineTuningSceletone.size());

                if (this.currentBandIndexFineTuning == this.numberOfBands - 1) { // the last band is hit
                    if (this.justVisitedLastBand) {
                        this.champion = true;
                        this.bandIndexScore = this.numberOfBands - 1;
                        retVal = false; // stop interation, the last band visied twice in a row
                    } else {
                        this.justVisitedLastBand = true; // the second trial to be sure
                        // nextBand is the same
                        retVal = true;
                    }
                } else {
                    // ordinary next band step
                    this.justVisitedLastBand = false;
                    // go to the next band
                    this.currentBandIndexFineTuning++;
                    retVal = true;
                }
            }
        } else {
            // register the fact that the band is finished 
            this.bandVisitCounter[this.currentBandIndexFineTuning]++;
            this.fineTuningShablonPosition = this.nextShablonPosition(this.fineTuningShablonPosition, this.fineTuningSceletone.size());
            shiftFIFO(this.cycle2helper, this.currentBandIndexFineTuning);
            retVal = this.toBeContinuedLoopAndLooserChecker(); // loop checking only on incorrect
            this.recycleUnusedStimuli();
        }

        

        if (retVal) {
            // check if there are enough stimuli left
            this.enoughFineTuningStimulae = this.initialiseNextFineTuningTuple();
            if (!this.enoughFineTuningStimulae) {
                retVal = false;
                this.bandIndexScore = mostOftenVisitedBandIndex(this.bandVisitCounter, this.currentBandIndexFineTuning);
            }
        }

        this.ftToBeContinued = retVal;
        return retVal;
    }

    public boolean getChampion() {
        return this.champion;
    }

    public boolean getLooser() {
        return this.looser;
    }

    public boolean getCycle() {
        return this.cycle2;
    }

    public int getBandScore() {
        return (this.bandIndexScore + 1);
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public boolean getIsTimeOut() {
        return this.timeOutExit;
    }

    public void isTimeOut() {

        long currentTimeMs = System.currentTimeMillis();
        long duration = currentTimeMs - this.startTimeMs;
        this.timeOutExit = (duration >= this.maxDurationMs);

        if (this.timeOutExit) { // set exit values
            this.durationMs = duration;
            this.bandIndexScore = this.getBandIndexScoreByVisits(this.bandVisitCounter);
        }
        
    }

    @Override
    public Map<String, String> getStimuliReport(String reportType) {

        final LinkedHashMap<String, String> returnMap = new LinkedHashMap<>();

        switch (reportType) {
            case "user_summary": {
                String summary = this.getStringSummary("", "\n", "", ";");

                LinkedHashMap<String, String> summaryMap = this.makeMapFromCsvString(summary);
                for (String key : summaryMap.keySet()) {
                    returnMap.put(key, summaryMap.get(key));
                }
                break;
            }
            case "fast_track": {
                String inhoudFastTrack = this.getStringFastTrack("", "\n", "", ";");
                LinkedHashMap<String, String> fastTrackMap = this.makeMapFromCsvString(inhoudFastTrack);
                for (String key : fastTrackMap.keySet()) {
                    returnMap.put(key, fastTrackMap.get(key));
                }
                break;
            }
            case "fine_tuning": {

                String inhoudFineTuning = this.getStringFineTuningHistory("", "\n", "", ";", "csv");
                LinkedHashMap<String, String> fineTuningBriefMap = this.makeMapFromCsvString(inhoudFineTuning);
                for (String key : fineTuningBriefMap.keySet()) {
                    returnMap.put(key, fineTuningBriefMap.get(key));
                }
                break;
            }
            default:
                break;

        }

        return returnMap;
    }

    public String getStringSummary(String startRow, String endRow, String startColumn, String endColumn) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("Score").append(endColumn);
        stringBuilder.append(startColumn).append("BestFastTrack").append(endColumn);
        stringBuilder.append(startColumn).append("Cycle2oscillation").append(endColumn);
        stringBuilder.append(startColumn).append("EnoughFineTuningStimuli").append(endColumn);
        stringBuilder.append(startColumn).append("Champion").append(endColumn);
        stringBuilder.append(startColumn).append("Looser").append(endColumn);
        if (this.maxDurationMinutes != null) {
            stringBuilder.append(startColumn).append("Time-out exit").append(endColumn);
            stringBuilder.append(startColumn).append("Duration millisec").append(endColumn);
        }
        stringBuilder.append(endRow);
        stringBuilder.append(startRow);
        String bandNumberScore = String.valueOf(this.getBandScore());
        stringBuilder.append(startColumn).append(bandNumberScore).append(endColumn);
        String bandNumberFastTrack = String.valueOf(this.lastCorrectBandFastTrack);
        if (Integer.parseInt(bandNumberFastTrack) < this.startBand) {
            bandNumberFastTrack = "NA";
        }
        stringBuilder.append(startColumn).append(bandNumberFastTrack).append(endColumn);

        stringBuilder.append(startColumn).append(this.cycle2).append(endColumn);
        stringBuilder.append(startColumn).append(this.enoughFineTuningStimulae).append(endColumn);
        stringBuilder.append(startColumn).append(this.champion).append(endColumn);
        stringBuilder.append(startColumn).append(this.looser).append(endColumn);
        if (this.maxDurationMinutes != null) {
            stringBuilder.append(startColumn).append(this.timeOutExit).append(endColumn);
            stringBuilder.append(startColumn).append(this.durationMs).append(endColumn);
        }

        stringBuilder.append(endRow);
        return stringBuilder.toString();
    }

    private LinkedHashMap<String, String> makeMapFromCsvString(String csvTable) {
        String[] rows = csvTable.split("\n");
        LinkedHashMap<String, String> retVal = new LinkedHashMap();
        for (int i = 0; i < rows.length; i++) {
            String paddedInt = "" + i;
            while (paddedInt.length() < 6) {
                paddedInt = "0" + paddedInt;
            }
            retVal.put("row" + paddedInt, rows[i]);
        }
        return retVal;
    }

    public ArrayList<ArrayList<AdVocAsStimulus>> getWords() {
        return this.words;
    }

    public ArrayList<AdVocAsStimulus> getNonwords() {
        return this.nonwords;
    }

    public ArrayList<FastTrackShablonElement> getFastTrackSceletone() {
        return this.fastTrackSceletone;
    }

    public ArrayList<FineTuningShablonElement> getFineTuningSceletone() {
        return this.fineTuningSceletone;
    }

    public LinkedHashMap<Long, Integer> getPercentageBandTable() {
        return this.percentageBandTable;
    }

    private LinkedHashMap<Long, Integer> generatePercentageBandTable() {
        LinkedHashMap<Long, Integer> retVal = new LinkedHashMap<>();
        Integer value1 = this.percentageIntoBandNumber(1);
        retVal.put(new Long(1), value1);
        for (int p = 1; p <= 9; p++) {
            long percentage = p * 10;
            Integer value = this.percentageIntoBandNumber(percentage);
            retVal.put(percentage, value);

        }
        Integer value99 = this.percentageIntoBandNumber(99);
        retVal.put(new Long(99), value99);
        return retVal;
    }

    public long bandNumberIntoPercentage(int bandNumber) {
        double tmp = ((double) bandNumber * 100.0) / this.numberOfBands;
        long retVal = Math.round(tmp);
        return retVal;
    }

    public int percentageIntoBandNumber(long percentage) {
        float tmp = ((float) percentage * this.numberOfBands) / 100;
        int retVal = Math.round(tmp);
        return retVal;
    }

    public boolean analyseCorrectness(Stimulus stimulus, String stimulusResponse) {
        String stimulusResponseProcessed = (new StringBuilder()).append(stimulusResponse).toString();
        stimulusResponseProcessed = stimulusResponseProcessed.replaceAll(",", "&#44;");
        if (!(stimulusResponseProcessed.equals(this.wordsResponse)
                || (stimulusResponseProcessed.equals(this.nonwordsResponse)))) {
            System.out.println("Erroenous input: neither word nor nonword; something went terrible wrong.");
            return false;
        }
        String correctResponse = stimulus.getCorrectResponses();
        return stimulusResponseProcessed.equals(correctResponse);
    }

    public void recycleUnusedStimuli() {
        boolean ended = this.tupleFT.isEmpty();
        while (!ended) {
            BookkeepingStimulus<AdVocAsStimulus> bStimulus = this.tupleFT.remove(0); // picking up the first not-used element
            AdVocAsStimulus stimulus = bStimulus.getStimulus();
            int bandNumber = stimulus.getBandNumber();
            if (bandNumber > 0) { // a word
                int bandIndex = bandNumber - 1;
                this.words.get(bandIndex).add(0, stimulus);
            } else {
                this.nonwords.add(0, stimulus);
            }
            ended = this.tupleFT.isEmpty();
        }

    }

    public boolean toBeContinuedLoopAndLooserChecker() {
        boolean retVal;
        // detecting is should be stopped
        this.cycle2 = detectLoop(cycle2helper);
        if (this.cycle2) {
            System.out.println("Detected: " + this.fineTuningUpperBoundForCycles
                    + " times oscillation between two neighbouring bands, "
                    + this.cycle2helper[cycle2helper.length - 2] + " and " + this.cycle2helper[cycle2helper.length - 1]);
            this.bandIndexScore = (this.cycle2helper[cycle2helper.length - 1] < this.cycle2helper[cycle2helper.length - 2])
                    ? this.cycle2helper[cycle2helper.length - 1] : this.cycle2helper[cycle2helper.length - 2];

            retVal = false;
        } else {
            if (this.currentBandIndexFineTuning == 0) {
                if (this.justVisitedLowestBand) {
                    this.looser = true;// stop interation, the first band is visited twice in a row
                    this.bandIndexScore = 0;
                    retVal = false;
                } else {
                    this.justVisitedLowestBand = true; // give the second chance
                    // nextBand is the same
                    retVal = true;
                }
            } else {
                this.justVisitedLowestBand = false;
                this.currentBandIndexFineTuning--;
                retVal = true;
            }

        }
        return retVal;
    }

    public static boolean detectLoop(Integer[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == 0 || arr[i + 2] == 0) {
                return false; // we are at the very beginning, to early to count loops
            }
            if (arr[i + 2] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void shiftFIFO(Integer[] fifo, int newelement) {
        for (int i = 0; i < fifo.length - 1; i++) {
            fifo[i] = fifo[i + 1];
        }
        fifo[fifo.length - 1] = newelement;
    }

    public static int mostOftenVisitedBandIndex(Integer[] bandVisitCounter, int controlIndex) {

        int max = bandVisitCounter[0];
        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        for (int i = 1; i < bandVisitCounter.length; i++) {
            if (max < bandVisitCounter[i]) {
                max = bandVisitCounter[i];
                indices.clear();
                indices.add(i);
            } else {
                if (max == bandVisitCounter[i]) {
                    indices.add(i);
                }
            }
        }

        // choose the band from "indices" which is closest to currentIndex;
        int retVal = indices.get(0);
        int diff = Math.abs(retVal - controlIndex);
        for (int i = 1; i < indices.size(); i++) {
            if (Math.abs(indices.get(i) - controlIndex) <= diff) {
                retVal = indices.get(i);
                diff = Math.abs(retVal - controlIndex);
            }
        }

        return retVal;

    }

    public String getStringFastTrack(String startRow, String endRow, String startColumn, String endColumn) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("Spelling").append(endColumn);
        stringBuilder.append(startColumn).append("BandNumber").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("Timestamp").append(endColumn);
        stringBuilder.append(startColumn).append("NonwordsFrequencyAtThisPoint").append(endColumn);
        stringBuilder.append(endRow);
        int nonwordCounter = 0;
        int limit = (this.responseRecord.isEmpty()) ? -1 : this.timeTickEndFastTrack;
        for (int i = 0; i <= limit; i++) {
            BookkeepingStimulus<AdVocAsStimulus> stimulus = this.responseRecord.get(i);
            Integer bandNumber = stimulus.getStimulus().getBandNumber();
            if (bandNumber == 0) {
                nonwordCounter++;
            }
            double frequency = ((double) nonwordCounter) / ((double) (i + 1));
            StringBuilder row = new StringBuilder();

            String time = (new Date(stimulus.getTimeStamp())).toString();
            row.append(startColumn).append(stimulus.getStimulus().getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getStimulus().getBandNumber()).append(endColumn);
            String reaction = startRow.equals("<tr>") ? stimulus.getReaction() : stimulus.getReaction().replaceAll("&#44;", ",");

            row.append(startColumn).append(reaction).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(time).append(endColumn);
            row.append(startColumn).append(frequency).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
        }
        return stringBuilder.toString();

    }

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
        stringBuilder.append(startColumn).append("Spelling").append(endColumn);
        stringBuilder.append(startColumn).append("BandNumber").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("Timestamp").append(endColumn);
        stringBuilder.append(startColumn).append("Visiting Number").append(endColumn);
        stringBuilder.append(endRow);
        int modCounter = 0;
        ArrayList<String> spellingsCheck = new ArrayList<>();
        for (int i = this.timeTickEndFastTrack + 1; i < this.responseRecord.size(); i++) {
            BookkeepingStimulus<AdVocAsStimulus> stimulus = this.responseRecord.get(i);
            StringBuilder row = new StringBuilder();
            String time = (new Date(stimulus.getTimeStamp())).toString();

            row.append(startColumn).append(stimulus.getStimulus().getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getStimulus().getBandNumber()).append(endColumn);
            String reaction = startRow.equals("<tr>") ? stimulus.getReaction() : stimulus.getReaction().replaceAll("&#44;", ",");
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

        // check if there are repititions
        HashSet<String> set = new HashSet(spellingsCheck);
        if (set.size() < spellingsCheck.size()) {
            stringBuilder.append(startRow).append(startColumn)
                    .append("Repetitions of stimuli detected")
                    .append(endColumn).append(endRow);
        }
        return stringBuilder.toString();
    }

    @Override
    public String getHtmlStimuliReport() {
        StringBuilder htmlStringBuilder = new StringBuilder();
        String experimenteeReport = this.getHtmlExperimenteeReport();
        htmlStringBuilder.append(experimenteeReport);
        return htmlStringBuilder.toString();
    }

    public long getPercentageScore() {
        int bandNumber = this.bandIndexScore + 1;
        long percentageScore = this.bandNumberIntoPercentage(bandNumber);
        return percentageScore;
    }

    private String getHtmlExperimenteeReport() {
        StringBuilder htmlStringBuilder = new StringBuilder();
        LinkedHashMap<String, ArrayList<BookkeepingStimulus<AdVocAsStimulus>>> wordTables = this.generateWordNonWordSequences(this.responseRecord);

        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> woorden = wordTables.get("words");
        ArrayList<BookkeepingStimulus<AdVocAsStimulus>> nietWoorden = wordTables.get("nonwords");
        String experimenteePositionDiagram = this.getHtmlExperimenteePositionDiagram();

        String lang = SourcenameIndices.LANGUAGE_INDEX.get(this.wordsSource);

        String overview = SourcenameIndices.getOverview(this.getPercentageScore(), lang);
        htmlStringBuilder.append(overview);

        HashMap<String, String> wordListHeaders = SourcenameIndices.getWordListHeaders(lang);

        String experimenteeWordTable = this.getHtmlExperimenteeRecords(woorden, null, wordListHeaders.get("headerWords"));
        String experimenteeNonWordTable = this.getHtmlExperimenteeRecords(nietWoorden, null, wordListHeaders.get("headerNonWords"));
        String twoColumnTable = this.getHtmlTwoColumnTable(experimenteeWordTable, experimenteeNonWordTable);
        String twoColumnTableWitCapture = this.getHtmlElementWithCapture(twoColumnTable, wordListHeaders.get("capture"));

        htmlStringBuilder.append("<table><tr style=\"vertical-align: top;\"><td>");
        htmlStringBuilder.append(twoColumnTableWitCapture).append("</td>");
        htmlStringBuilder.append("<td style=\"padding-left: 200px;\">").append(experimenteePositionDiagram).append("</td></tr></table>");

        return htmlStringBuilder.toString();
    }

    private String getHtmlExperimenteeRecords(ArrayList<BookkeepingStimulus<AdVocAsStimulus>> atoms, String colour, String header) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<table>");
        htmlStringBuilder.append("<tr><td><big>").append(header).append("</big></td></tr>");
        String colorStyle = "";
        if (colour != null) {
            colorStyle = "style=\"color:" + colour + "\"";
        }
        for (BookkeepingStimulus<AdVocAsStimulus> atom : atoms) {
            if (colour == null) {
                if (atom.getCorrectness()) {
                    colorStyle = "style=\"color:green\"";
                }
                if (!atom.getCorrectness()) {
                    colorStyle = "style=\"color:red\"";
                }
            }
            htmlStringBuilder.append("<tr ").append(colorStyle).append(">");
            htmlStringBuilder.append("<td><big>").append(atom.getStimulus().getLabel()).append("</big></td>");
            htmlStringBuilder.append("</tr>");
        }
        htmlStringBuilder.append("</table>");

        return htmlStringBuilder.toString();
    }

    private String getHtmlTwoColumnTable(String leftColumn, String rightColumn) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<table>");
        htmlStringBuilder.append("<tr style=\"vertical-align: top;\"><td>").append(leftColumn).append("</td>");
        htmlStringBuilder.append("<td style=\"padding-left: 5px;\">").append(rightColumn).append("</td></tr></table>");
        return htmlStringBuilder.toString();
    }

    private String getHtmlElementWithCapture(String element, String capture) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<table>");
        htmlStringBuilder.append("<tr><td>").append(capture).append("</td></tr>");
        htmlStringBuilder.append("<tr><td>").append(element).append("</td></tr></table>");
        return htmlStringBuilder.toString();
    }

    private String getHtmlExperimenteePositionDiagram() {
        StringBuilder htmlStringBuilder = new StringBuilder();
        long perScore = this.getPercentageScore();
        LinkedHashMap<Long, String> content = this.generateDiagramSequence(this.responseRecord, this.percentageBandTable);
        //htmlStringBuilder.append("<table frame=\"box\">");
        htmlStringBuilder.append("<table>");
        String lang = SourcenameIndices.LANGUAGE_INDEX.get(this.wordsSource);
        String header = SourcenameIndices.diagramHelper(lang);
        htmlStringBuilder.append(header);
        for (Long key : content.keySet()) {
            htmlStringBuilder.append("<tr>");
            String percent = key.toString();
            String contentString = content.get(key);
            if (key.equals(perScore)) {
                percent = "<b><big><big><big>" + percent + "</big></big></big></b>";
                contentString = "<b><big><big>" + contentString + "</big></big></b>";
            }
            String bar = this.makeDiagramBar(key);
            htmlStringBuilder.append("<td>").append(percent).append("</td>");
            htmlStringBuilder.append("<td>").append(bar).append("</td>");
            htmlStringBuilder.append("<td>").append(contentString).append("</td>");
            htmlStringBuilder.append("</tr>");
        }
        htmlStringBuilder.append("</table>");

        return htmlStringBuilder.toString();
    }

    private String makeDiagramBar(Long percentage) {
        long width = percentage;
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<table cellspacing=\"0\" cellpadding=\"0\" class=\"bargraphOuter\" style=\"width: 100px; height: 10px;\">")
                .append("<tbody><tr><td align=\"left\" style=\"vertical-align: top;\">")
                .append("<table cellspacing=\"0\" cellpadding=\"0\" class=\"bargraphInner\" style=\"width: ")
                .append(width)
                .append("px; height: 10px;\">")
                .append("<tbody><tr></tr></tbody></table></td></tr></tbody></table>");
        return htmlStringBuilder.toString();
    }

    public LinkedHashMap<Long, String> generateDiagramSequence(ArrayList<BookkeepingStimulus<AdVocAsStimulus>> records, LinkedHashMap<Long, Integer> percentageBandTable) {
        LinkedHashMap<Long, String> retVal = new LinkedHashMap<Long, String>();

        LinkedHashMap<Integer, String> sampleWords = this.retrieveSampleWords(records, this.words);

        Long perScore = this.getPercentageScore();
        Integer bNumberScore = this.getBandScore();

        Set<Long> keysTmp = percentageBandTable.keySet();
        Set<Long> keys = new HashSet<Long>(keysTmp);

        Long one = new Long(1);
        Long nn = new Long(99);
        keys.remove(one);
        keys.remove(nn);

        if (perScore < 5) {
            retVal.put(perScore, sampleWords.get(bNumberScore));
        } else {
            Integer bandNumber = percentageBandTable.get(one);
            String value = sampleWords.get(bandNumber);
            retVal.put(one, value);
        }

        for (Long key : keys) {
            if (perScore >= key - 5 && perScore < key + 5) {
                // put the participant score here
                retVal.put(perScore, sampleWords.get(bNumberScore));
            } else {
                // put a predefined sample
                Integer bandNumber = percentageBandTable.get(key);
                String value = sampleWords.get(bandNumber);
                retVal.put(key, value);
            }
        }

        if (perScore >= 95) {
            retVal.put(perScore, sampleWords.get(bNumberScore));
        } else {
            Integer bandNumber = percentageBandTable.get(nn);
            String value = sampleWords.get(bandNumber);
            retVal.put(nn, value);
        }

        return retVal;
    }

    public LinkedHashMap<Integer, String> retrieveSampleWords(ArrayList<BookkeepingStimulus<AdVocAsStimulus>> records, ArrayList<ArrayList<AdVocAsStimulus>> nonusedWords) {
        LinkedHashMap<Integer, String> retVal = new LinkedHashMap<Integer, String>();
        for (BookkeepingStimulus<AdVocAsStimulus> bStimulus : records) {
            AdVocAsStimulus stimulus = bStimulus.getStimulus();
            if (stimulus.getCorrectResponses().equals(this.wordsResponse)) { // is a word
                Integer key = stimulus.getBandNumber();
                if (!retVal.containsKey(key)) {
                    retVal.put(key, stimulus.getLabel());
                }
            }
        }

        // fill in absent values
        for (int i = 1; i <= this.numberOfBands; i++) {
            Integer bandNumber = i;
            if (!retVal.containsKey(bandNumber)) {
                // unseen word in the band means that there must be for sure
                // non-used words for this band in the words-container
                // if there will be array out of boud exception
                String value = nonusedWords.get(i - 1).get(0).getLabel();
                retVal.put(bandNumber, value);
            }
        }

        return retVal;
    }

    private LinkedHashMap<String, ArrayList<BookkeepingStimulus<AdVocAsStimulus>>> generateWordNonWordSequences(ArrayList<BookkeepingStimulus<AdVocAsStimulus>> records) {
        LinkedHashMap<String, ArrayList<BookkeepingStimulus<AdVocAsStimulus>>> retVal = new LinkedHashMap<String, ArrayList<BookkeepingStimulus<AdVocAsStimulus>>>();
        retVal.put("words", new ArrayList<BookkeepingStimulus<AdVocAsStimulus>>());
        retVal.put("nonwords", new ArrayList<BookkeepingStimulus<AdVocAsStimulus>>());

        for (BookkeepingStimulus<AdVocAsStimulus> bStimulus : records) {
            if (this.wordsResponse.equals(bStimulus.getStimulus().getCorrectResponses())) {
                retVal.get("words").add(bStimulus);
            }
            if (this.nonwordsResponse.equals(bStimulus.getStimulus().getCorrectResponses())) {
                retVal.get("nonwords").add(bStimulus);
            }
        }
        return retVal;
    }

    /* "wordsSource", "nonwordsSource", "fastTrackShablonSource", "fineTuningShablonSource", 
        "numberOfBands", "fineTuningUpperBoundForCycles",  "maxDurationMinutes", 
        "words", "nonwords",  "startBand", 
        "bandIndexScore", "isCorrectCurrentResponse", "currentBandIndex", "totalStimuli",
        "responseRecord", "tupleFT",
        "lastCorrectFastTrack", "isFastTrackIsStillOn", "secondChanceFastTrackIsFired", "timeTickEndFastTrack", "enoughFastTrackStimulae",
        "enoughFineTuningStimulae", "bandVisitCounter", "cycle2helper",
        "cycle2", "champion", "looser", "justVisitedLastBand", "justVisitedLowestBand", "errorMessage" */
    @Override
    public String toString() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("wordsSource", this.wordsSource);
        map.put("nonwordsSource", this.nonwordsSource);
        map.put("fastTrackShablonSource", this.fastTrackShablonSource);
        map.put("fineTuningShablonSource", this.fineTuningShablonSource);
        map.put("numberOfBands", this.numberOfBands);
        map.put("fineTuningUpperBoundForCycles", this.fineTuningUpperBoundForCycles);
        map.put("maxDurationMinutes", this.maxDurationMinutes);

        // to do serialisation of these "words", "nonwords"
        map.put("words", this.words);
        map.put("nonwords", this.nonwords);
        map.put("startBand", this.startBand);

        map.put("bandIndexScore", this.bandIndexScore);
        map.put("currentBandIndex", this.lastCorrectBandFastTrack);
        map.put("totalStimuli", this.totalStimuli);

        // check serialisation of arrays!!! what happens if you put an array ....
        map.put("responseRecord", this.responseRecord);
        map.put("tupleFT", this.tupleFT);

        map.put("lastCorrectBandFastTrack", this.lastCorrectBandFastTrack);
        map.put("isFastTrackIsStillOn", this.isFastTrackIsStillOn);
        map.put("secondChanceFastTrackIsFired", this.fastTrackCorrectorOn);
        map.put("timeTickEndFastTrack", this.timeTickEndFastTrack);
        map.put("enoughFineTuningStimulae", this.enoughFineTuningStimulae);
        map.put("bandVisitCounter", Arrays.asList(this.bandVisitCounter));

        // serialisation of arrays!!!
        map.put("cycle2helper", Arrays.asList(this.cycle2helper));

        map.put("cycle2", this.cycle2);
        map.put("champion", this.champion);
        map.put("looser", this.looser);
        map.put("justVisitedLastBand", this.justVisitedLastBand);
        map.put("justVisitedLowestBand", this.justVisitedLowestBand);
        map.put("errorMessage", this.errorMessage);

        return map.toString();
    }

    /* "wordsSource", "nonwordsSource", "fastTrackShablonSource", "fineTuningShablonSource", 
        "numberOfBands", "fineTuningUpperBoundForCycles",  "maxDurationMinutes", 
        "words", "nonwords",  "startBand", 
        "bandIndexScore", "isCorrectCurrentResponse", "currentBandIndex", "totalStimuli",
        "responseRecord", "tupleFT",
        "bestBandFastTrack", "isFastTrackIsStillOn", "secondChanceFastTrackIsFired", "timeTickEndFastTrack", "enoughFastTrackStimulae",
        "enoughFineTuningStimulae", "bandVisitCounter", "cycle2helper",
        "cycle2", "champion", "looser", "justVisitedLastBand", "justVisitedLowestBand", "errorMessage" */
    public void deserialise(String str) throws Exception {
        Map<String, Object> map = UtilsJSONdialect.stringToObjectMap(str, FLDS);

        // constructor parameters
        this.wordsSource = map.get("wordsSource").toString();
        this.nonwordsSource = map.get("nonwordsSource").toString();
        this.fastTrackShablonSource = map.get("fastTrackShablonSource").toString();
        this.fineTuningShablonSource = map.get("fineTuningShablonSource").toString();
        this.numberOfBands = Integer.parseInt(map.get("numberOfBands").toString());
        this.fineTuningUpperBoundForCycles = Integer.parseInt(map.get("fineTuningUpperBoundForCycles").toString());
        this.maxDurationMinutes = map.get("maxDurationMinutes").toString();

        this.wordsResponse = SourcenameIndices.RESPONSES_INDEX.get(this.wordsSource);
        this.nonwordsResponse = SourcenameIndices.RESPONSES_INDEX.get(this.nonwordsSource);

        // state 
        AdVocAsStimuliFromString reader = new AdVocAsStimuliFromString();
        reader.parseWordsInputCSVString(this, this.wordsSource, this.nonwordsSource, this.numberOfBands);
        reader.parseNonWordsInputCSVString(this, this.nonwordsSource, this.wordsSource);

        LinkedHashMap<String, AdVocAsStimulus> hashedStimuli = reader.getHashedStimuli();

        List<Object> nonWordsObj = (List<Object>) map.get("nonwords");
        this.nonwords = new ArrayList<>(nonWordsObj.size());
        for (Object object : nonWordsObj) {
            AdVocAsStimulus current = hashedStimuli.get(object.toString());
            this.nonwords.add(current);
        }

        List<List<Object>> wordsObj = (List<List<Object>>) map.get("words");
        this.words = new ArrayList<>(wordsObj.size());
        for (List<Object> listObj : wordsObj) {
            if (listObj == null) {
                this.words.add(null);
                continue;
            }
            ArrayList<AdVocAsStimulus> currentarray = new ArrayList<>(listObj.size());
            this.words.add(currentarray);
            for (Object object : listObj) {
                AdVocAsStimulus current = hashedStimuli.get(object.toString());
                currentarray.add(current);
            }
        }

        this.startBand = Integer.parseInt(map.get("startBand").toString());
        this.bandIndexScore = Integer.parseInt(map.get("bandIndexScore").toString());
        this.lastCorrectBandFastTrack = Integer.parseInt(map.get("lastCorrectBandFastTrack").toString());
        this.totalStimuli = Integer.parseInt(map.get("totalStimuli").toString());

        BookkeepingStimulus<AdVocAsStimulus> ghost = new BookkeepingStimulus<>(null);
        Object recordObj = map.get("responseRecord");
        this.responseRecord = new ArrayList<>();
        if (recordObj != null) {
            List<Object> objs = (List<Object>) recordObj;
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i) != null) {
                    Map<String, Object> currentObj = (Map<String, Object>) objs.get(i);
                    BookkeepingStimulus<AdVocAsStimulus> bStimulus = ghost.toBookkeepingStimulusObject(currentObj, hashedStimuli);
                    this.responseRecord.add(i, bStimulus);
                } else {
                    throw new Exception("Serialised bookeeping stimuli object happen to be null :( ");
                }
            }
        }

        Object tupleFTObj = map.get("tupleFT");
        this.tupleFT = new ArrayList<>();
        if (tupleFTObj != null) {
            List<Object> objs = (List<Object>) tupleFTObj;
            for (int i = 0; i < objs.size(); i++) {
                Map<String, Object> currentObj = (Map<String, Object>) objs.get(i);
                BookkeepingStimulus<AdVocAsStimulus> bStimulus = ghost.toBookkeepingStimulusObject(currentObj, hashedStimuli);
                this.tupleFT.add(i, bStimulus);
            }
        }

        /*"lastCorrectBandFastTrack", "isFastTrackIsStillOn", "secondChanceFastTrackIsFired", "timeTickEndFastTrack", "enoughFastTrackStimulae",
        "enoughFineTuningStimulae", "bandVisitCounter", "cycle2helper",
        "cycle2", "champion", "looser", "justVisitedLastBand", "justVisitedLowestBand", "errorMessage" */
        this.isFastTrackIsStillOn = Boolean.parseBoolean(map.get("isFastTrackIsStillOn").toString());
        this.fastTrackCorrectorOn = Boolean.parseBoolean(map.get("secondChanceFastTrackIsFired").toString());
        this.timeTickEndFastTrack = Integer.parseInt(map.get("timeTickEndFastTrack").toString());
        this.enoughFastTrackStimulae = Boolean.parseBoolean(map.get("enoughFastTrackStimulae").toString());
        this.enoughFineTuningStimulae = Boolean.parseBoolean(map.get("enoughFineTuningStimulae").toString());

        this.cycle2 = Boolean.parseBoolean(map.get("cycle2").toString());
        this.champion = Boolean.parseBoolean(map.get("champion").toString());
        this.looser = Boolean.parseBoolean(map.get("looser").toString());
        this.justVisitedLastBand = Boolean.parseBoolean(map.get("justVisitedLastBand").toString());
        this.justVisitedLowestBand = Boolean.parseBoolean(map.get("justVisitedLowestBand").toString());
        this.errorMessage = map.get("errorMessage").toString();

        // TODO: deserialise shablon sources, sceletones, arays "bandVisitCounter", "cycle2helper"!!!
    }

    public int getBandIndexScoreByVisits(Integer[] visitCounter) {
        int retVal;
        int max = 0;

        for (Integer bandVisits : visitCounter) {
            if (bandVisits > max) {
                max = bandVisits;
            }
        }
        int sum = 0;
        int amount = 0;
        for (int i = 0; i < visitCounter.length; i++) {
            if (visitCounter[i] == max) {
                amount++;
                sum += i;
            }
        }
        float average = (float) sum / ((float) amount);
        retVal = Math.round(average);
        return retVal;
    }

}
