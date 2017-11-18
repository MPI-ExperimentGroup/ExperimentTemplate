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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.AtomBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsAtomStimulus;
import nl.mpi.tg.eg.frinex.common.AbstractStimuliProvider;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends AbstractStimuliProvider {

    private int score = -1;
    private Boolean isCorrectCurrentResponse;
    private int currentBandIndex = 0;
    private int totalStimuli;
    private RandomIndexing rndIndexing;
    private ArrayList<Integer> nonWordsIndexes;

    private ArrayList<ArrayList<AtomBookkeepingStimulus>> words;
    private ArrayList<AtomBookkeepingStimulus> nonwords;
    private ArrayList<AtomBookkeepingStimulus> responseRecord;

    // fast track stuff
    private int bestBandFastTrack = -1;
    private boolean isFastTrackIsStillOn = true;
    private boolean secondChanceFastTrackIsFired = false;
    private int timeTickEndFastTrack = -1;

    // fine tuning stuff
    private final ArrayList<AtomBookkeepingStimulus> tupleFT = new ArrayList<>(Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
    private Random rnd;

    // fine tuning stopping
    private boolean enoughFineTuningStimulae = true;
    private final int[] cycle2helper = new int[Constants.FINE_TUNING_UPPER_BOUND_FOR_2CYCLES * 2 + 1];
    private boolean cycle2 = false;
    private boolean champion = false;
    private boolean looser = false;
    private boolean justVisitedLastBand = false;
    private boolean justVisitedFirstBand = false;

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        for (String splitValue : stimuliStateSnapshot.split(",")) {
            switch (splitValue) {
                case "bestFastrTrackRecord:":
                    break;
            }

        }
        Vocabulary vocab = new Vocabulary();
        this.words = vocab.initialiseWords(ConstantsWords.WORDS);
        ArrayList<AdVocAsAtomStimulus> nonwordstmp = new ArrayList<>();
        nonwordstmp.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));
        this.nonwords = vocab.initialiseNonwords(nonwordstmp);

        this.totalStimuli = this.nonwords.size();
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            this.totalStimuli += this.words.get(i).size();
        }

        this.responseRecord = new ArrayList<>();

        // int startBand, int nonwordsPerBlock, int averageNonwordPosition, int nonwordsAvailable
        this.rndIndexing = new RandomIndexing(Constants.START_BAND, Constants.NONWORDS_PER_BLOCK, Constants.AVRERAGE_NON_WORD_POSITION, nonwords.size());
        this.nonWordsIndexes = this.rndIndexing.updateAndGetIndices();

        this.rnd = new Random();

        this.currentBandIndex = Constants.START_BAND - 1;
    }

    public ArrayList<ArrayList<AtomBookkeepingStimulus>> getWords() {
        return this.words;
    }

    public ArrayList<AtomBookkeepingStimulus> getNonwords() {
        return this.nonwords;
    }

    public ArrayList<AtomBookkeepingStimulus> getResponseRecord() {
        return this.responseRecord;
    }

    public ArrayList<Integer> getNonWordsIndices() {
        return this.nonWordsIndexes;
    }

    public boolean getEnoughFinetuningStimuli() {
        return this.enoughFineTuningStimulae;
    }

    public boolean getCycel2() {
        return this.cycle2;
    }

    public boolean getChampion() {
        return this.champion;
    }

    public boolean getLooser() {
        return this.looser;
    }

    public int getBestFastTrackBand() {
        return this.bestBandFastTrack;
    }

    public int getScore() {
        return this.score;
    }

    public int getCurrentBandNumber() {
        return (this.currentBandIndex + 1);
    }

    // prepared by next stimulus
    @Override
    public AdVocAsAtomStimulus getCurrentStimulus() {
        AtomBookkeepingStimulus bStimulus = this.responseRecord.get(this.getCurrentStimulusIndex());
        return bStimulus.getPureStimulus();
    }

    // aka get current experiment 
    @Override
    public int getCurrentStimulusIndex() {
        return (this.responseRecord.size() - 1);
    }

    @Override
    public int getTotalStimuli() {
        return this.totalStimuli;
    }

    // actually defines if the series is to be continued or stopped
    @Override
    public boolean hasNextStimulus(int increment) {
        if (this.isFastTrackIsStillOn) { // fast track is still on, update it
            this.isFastTrackIsStillOn = this.fastTrackToBeContinued();
            if (this.isFastTrackIsStillOn) {
                return true;
            } else {
                // return false if not enough stimuli left
                return this.switchToFineTuning();
            }
        } else {
            return this.fineTuningToBeContinued();
        }

    }

    // all indices are updated in the "hasNextStimulus"
    @Override
    public void nextStimulus(int increment) {
        AtomBookkeepingStimulus bStimulus;
        if (this.isFastTrackIsStillOn) {
            int nextExperimentIndex = this.responseRecord.size();
            if (this.nonWordsIndexes.contains(nextExperimentIndex)) {
                bStimulus = this.nonwords.remove(0);
            } else {
                bStimulus = this.words.get(this.currentBandIndex).remove(0);
            }
        } else {
            bStimulus = this.tupleFT.remove(0);
        }
        bStimulus.setIsUsed(true);
        this.responseRecord.add(bStimulus);

    }

    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
        //  not relevant for now
        //this.fastTrackStimuliIndex = currentStimuliIndex;
    }

    @Override
    public String getCurrentStimulusUniqueId() {
        return getCurrentStimulus().getUniqueId();
    }

    @Override
    public String generateStimuliStateSnapshot() {
        return "";
    }

    @Override
    public String getHtmlStimuliReport() {
        String summary = this.getStringSummary("<tr>", "</tr>", "<td>", "</td>");
        String inhoudFastTrack = this.getStringFastTrack("<tr>", "</tr>", "<td>", "</td>");
        String inhoudFineTuning = this.getStringFineTuningHistory("<tr>", "</tr>", "<td>", "</td>");
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<p>User summary</p><table border=1>").append(summary).append("</table><br><br>");
        htmlStringBuilder.append("<p>Fast Track History</p><table border=1>").append(inhoudFastTrack).append("</table><br><b>");
        htmlStringBuilder.append("<p>Fine tuning History</p><table border=1>").append(inhoudFineTuning).append("</table>");
        return htmlStringBuilder.toString();
        //return "<table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td></tr><tr><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>0</td></tr></table>";
    }

    @Override
    public Map<String, String> getStimuliReport() {
        final HashMap<String, String> returnMap = new HashMap<>();

        String summary = this.getStringSummary("", "\n", "", ";");
        HashMap<String, String> summaryMap = this.makeMapFromCsvString(summary, "Summary");
        for (String key : summaryMap.keySet()) {
            returnMap.put(key, summaryMap.get(key));
        }

        String inhoudFastTrack = this.getStringFastTrack("", "\n", "", ";");
        HashMap<String, String> fastTrackMap = this.makeMapFromCsvString(inhoudFastTrack, "Fast_Track");
        for (String key : fastTrackMap.keySet()) {
            returnMap.put(key, fastTrackMap.get(key));
        }

        String inhoudFineTuning = this.getStringFineTuningHistory("", "\n", "", ";");
        HashMap<String, String> fineTuningBriefMap = this.makeMapFromCsvString(inhoudFineTuning, "Fine_Tuning");
        for (String key : fineTuningBriefMap.keySet()) {
            returnMap.put(key, fineTuningBriefMap.get(key));
        }

        //returnMap.put("example_1", "1;2;3;4;5;6;7;8;9");
        //returnMap.put("example_2", "A;B;C;D;E;F;G;H;I");
        //returnMap.put("example_3", "X;X;X;X;X;X");
        //returnMap.put("number", "1");
        //returnMap.put("example", "1,2,3,4,5,6,7,8,9 \n 2,3,4,5,6,7,8,9,0");
        //returnMap.put("number", "1");
        return returnMap;
    }

    @Override
    public boolean isCorrectResponse(Stimulus stimulus, String stimulusResponse) {
        if (!(stimulusResponse.equals("word") || (stimulusResponse.equals("nonword")))) {
            System.out.println("Erroenous input neither word nor nonword; something went terrible wrong.");
            return false;
        }
        int index = this.getCurrentStimulusIndex();
        if (this.responseRecord.get(index).getBandNumber() > -1) {
            this.isCorrectCurrentResponse = stimulusResponse.equals("word");
        } else {
            this.isCorrectCurrentResponse = stimulusResponse.equals("nonword");
        }
        this.responseRecord.get(index).setCorrectness(this.isCorrectCurrentResponse);

        if (stimulusResponse.equals("word")) {
            this.responseRecord.get(index).setReaction(true);
        } else {
            this.responseRecord.get(index).setReaction(false);
        }

        return this.isCorrectCurrentResponse;
    }

    // updates indices 
    private boolean fastTrackToBeContinued() {
        if (this.responseRecord.isEmpty()) {// just started the first experiment...
            return true;
        }
        boolean retVal;
        int index = this.responseRecord.size() - 1;
        boolean isWord = this.responseRecord.get(index).getBandNumber() > -1;
        if (this.isCorrectCurrentResponse) {
            this.secondChanceFastTrackIsFired = false;
            if (isWord) {
                if (this.currentBandIndex == (Constants.NUMBER_OF_BANDS - 1)) {
                    retVal = false;
                } else {
                    this.currentBandIndex++;
                    retVal = true;
                }
            } else {
                retVal = true;
            }
        } else {
            // hit incorrect? 
            if (this.secondChanceFastTrackIsFired) {
                retVal = false;
            } else {
                // giving the second chanse
                this.secondChanceFastTrackIsFired = true;
                retVal = true;
            }
        }

        if (retVal) {
            // check if we still have data for the next experiment
            int nextExperimentIndex = this.responseRecord.size();

            if (this.nonWordsIndexes.contains(nextExperimentIndex) && nonwords.size() < 1) { // we have at least 1 nonword left
                System.out.println("We do not have nonwords left for the Fast Track!");
                retVal = false;
            }

            if (!this.nonWordsIndexes.contains(nextExperimentIndex)
                    && this.words.get(this.currentBandIndex).size() < 1) { // we have at least 1 word left
                System.out.println("We do not have words in band " + this.currentBandIndex + "  left for the Fast Track!");
                retVal = false;
            }
        }

        return retVal;
    }

    private boolean switchToFineTuning() {
        this.timeTickEndFastTrack = this.responseRecord.size() - 1; // the last time on fast track (if we start counting form zero)
        this.bestBandFastTrack = this.currentBandIndex + 1; // band number is 1 more w.r.t bandIndex, with indexing offsets start woth zero
        return this.initialiseNextFineTuningTuple();
    }

    // we already at the right band, the last band in the fast track with the correct answer
    // return false if there is not enough words and nonwords
    private boolean initialiseNextFineTuningTuple() {
        if (this.nonwords.size() < 1) {
            return false;
        }
        if (this.words.get(this.currentBandIndex).size() < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1) {
            return false;
        }
        int nonWordPos = rnd.nextInt(Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
        AtomBookkeepingStimulus bStimulus;
        for (int i = 0; i < nonWordPos; i++) {
            bStimulus = this.words.get(this.currentBandIndex).remove(0);
            this.tupleFT.add(bStimulus);
        }
        bStimulus = this.nonwords.remove(0);
        this.tupleFT.add(bStimulus);
        for (int i = nonWordPos + 1; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            bStimulus = this.words.get(this.currentBandIndex).remove(0);
            this.tupleFT.add(bStimulus);
        }
        return true;
    }

    //(also) updates the indices
    private boolean fineTuningToBeContinued() {

        boolean retVal;
        int currentBandNumber = this.currentBandIndex + 1; // memoise currentBandNumber == index +1

        if (this.isCorrectCurrentResponse) {
            if (this.tupleFT.size() > 0) {
                // we have not hit the last atom in the tuple yet
                // continue
                return true;
            } else {
                // tranistion to the higher band ?
                if (this.currentBandIndex == Constants.NUMBER_OF_BANDS - 1) { // the last band is hit
                    if (this.justVisitedLastBand) {
                        this.champion = true;
                        this.score = Constants.NUMBER_OF_BANDS;
                        retVal = false; // stop interation, the last band visied twice in a row
                    } else {
                        this.justVisitedLastBand = true; // the second trial to be sure
                        // nextBand is the same
                        retVal = true;
                    }
                } else {
                    // ordinary next band iteration
                    this.justVisitedLastBand = false;
                    // go to the next band
                    this.currentBandIndex++;
                    retVal = true;
                }
            }
        } else {
            // put back unused element of the tuple
            // recycling
            boolean ended = this.tupleFT.isEmpty();
            while (!ended) {
                AtomBookkeepingStimulus bStimulus = this.tupleFT.remove(0);
                int bandNumber = bStimulus.getBandNumber();
                if (bandNumber > -1) { // a word
                    // bandIndex is 1 less then band number 
                    this.words.get(bandNumber - 1).add(bStimulus);
                } else {
                    this.nonwords.add(bStimulus);
                }
                ended = this.tupleFT.isEmpty();
            }

            // detecting is should be stopped
            this.cycle2 = detectLoop(cycle2helper);
            if (this.cycle2) {
                System.out.println("Detected: three times oscillation between two neighbouring bands");
                this.score = this.cycle2helper[0];
                for (int i = 1; i < this.cycle2helper.length; i++) {
                    if (this.score > this.cycle2helper[i]) {
                        this.score = this.cycle2helper[i];
                    }
                }
                retVal = false;
            } else {
                if (this.currentBandIndex == 0) {
                    if (this.justVisitedFirstBand) {
                        this.looser = true;// stop interation, the first band is visited twice in a row
                        this.score = 1;
                        retVal = false;
                    } else {
                        this.justVisitedFirstBand = true; // give the second chance
                        // nextBand is the same
                        retVal = true;
                    }
                } else {
                    this.justVisitedFirstBand = false;
                    this.currentBandIndex--;
                    retVal = true;
                }

            }
        }

        if (retVal) {
            shiftFIFO(cycle2helper, currentBandNumber);
            // check if there are enough stimuli left
            this.enoughFineTuningStimulae = this.initialiseNextFineTuningTuple();
            if (!this.enoughFineTuningStimulae) {
                System.out.println("Not enough fine-tuning compound stimula left for the band " + this.currentBandIndex);
                retVal = false;
                this.score = this.mostOftenVisitedBandBumber();
            }

        }
        return retVal;
    }

    public static boolean detectLoop(int[] arr) {
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

    public static void shiftFIFO(int[] fifo, int newelement) {
        for (int i = 0; i < fifo.length - 1; i++) {
            fifo[i] = fifo[i + 1];
        }
        fifo[fifo.length - 1] = newelement;
    }

    public int mostOftenVisitedBandBumber() {
        int[] bandVisitCounter = new int[Constants.NUMBER_OF_BANDS];
        for (int i = this.timeTickEndFastTrack + 1; i < this.responseRecord.size(); i++) {
            AtomBookkeepingStimulus bStimulus = this.responseRecord.get(i);
            int bandIndex = bStimulus.getBandNumber() - 1;
            bandVisitCounter[bandIndex]++;
        }

        int retVal = this.mostOftenVisitedBandBumberHelp(bandVisitCounter, this.currentBandIndex);
        return retVal;

    }

    public int mostOftenVisitedBandBumberHelp(int[] bandVisitCounter, int controlIndex) {

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
        int ind = 0;
        for (int i = 1; i < indices.size(); i++) {
            if (Math.abs(indices.get(i) - controlIndex) < diff) {
                retVal = indices.get(i);
                diff = Math.abs(retVal - controlIndex);
                ind = i;
            }
        }

        int retValSym = indices.get(indices.size() - 1);
        diff = Math.abs(retValSym - controlIndex);
        int indexSym = indices.size() - 1;
        for (int i = indices.size() - 2; i >= 0; i--) {
            if (Math.abs(indices.get(i) - controlIndex) < diff) {
                retValSym = indices.get(i);
                diff = Math.abs(retValSym - controlIndex);
                indexSym = i;
            }
        }

        if (indices.size() - indexSym >= ind + 1) {
            retVal = retValSym;
        }
        return retVal + 1;

    }

    public String getStringFastTrack(String startRow, String endRow, String startColumn, String endColumn) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("Spelling").append(endColumn);
        stringBuilder.append(startColumn).append("BandNumber").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("NonwordsFrequencyAtThisPoint").append(endColumn);
        stringBuilder.append(endRow);
        int counterNonwords = 0;
        for (int i = 0; i <= this.timeTickEndFastTrack; i++) {
            AtomBookkeepingStimulus stimulus = this.responseRecord.get(i);
            if (stimulus.getBandNumber() == -1) {
                counterNonwords++;
            }
            double frequency = ((double) counterNonwords) / ((double) (i + 1));

            StringBuilder row = new StringBuilder();
            row.append(startColumn).append(stimulus.getSpelling()).append(endColumn);
            row.append(startColumn).append(stimulus.getBandNumber()).append(endColumn);
            row.append(startColumn).append(stimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(frequency).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
        }
        return stringBuilder.toString();

    }

    public String getStringFineTuningHistory(String startRow, String endRow, String startColumn, String endColumn) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("BandNumber").append(endColumn);
        stringBuilder.append(startColumn).append("Spelling").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("VisitingTime").append(endColumn);
        stringBuilder.append(endRow);
        int modCounter = 0;
        ArrayList<String> spellingsCheck = new ArrayList<>();
        for (int i = this.timeTickEndFastTrack + 1; i < this.responseRecord.size(); i++) {
            AtomBookkeepingStimulus stimulus = this.responseRecord.get(i);
            StringBuilder row = new StringBuilder();
            row.append(startColumn).append(stimulus.getBandNumber()).append(endColumn);
            row.append(startColumn).append(stimulus.getSpelling()).append(endColumn);
            row.append(startColumn).append(stimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(i).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
            modCounter++;
            if (!stimulus.getCorrectness() || modCounter == 4) {
                stringBuilder.append(startRow).append(endRow); // skeep between tuples
                stringBuilder.append(startRow).append(endRow);
                stringBuilder.append(startRow).append(endRow);
                modCounter = 0;
            }
            spellingsCheck.add(stimulus.getSpelling());
        }
        
        // check if there are repititions
        HashSet<String> set = new HashSet(spellingsCheck);
        if (set.size() < spellingsCheck.size()){
            stringBuilder.append(startRow).append(startColumn)
                    .append("Repetitions of stimuli detected")
                    .append(endColumn).append(endRow);
        }
        return stringBuilder.toString();
    }

    public String getStringSummary(String startRow, String endRow, String startColumn, String endColumn) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("Score").append(endColumn);
        stringBuilder.append(startColumn).append("BestFastTrack").append(endColumn);
        stringBuilder.append(startColumn).append("Cycel2oscillation").append(endColumn);
        stringBuilder.append(startColumn).append("EnoughFineTuningStimuli").append(endColumn);
        stringBuilder.append(startColumn).append("Champion").append(endColumn);
        stringBuilder.append(startColumn).append("Looser").append(endColumn);
        stringBuilder.append(endRow);
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append(this.score).append(endColumn);
        stringBuilder.append(startColumn).append(this.bestBandFastTrack).append(endColumn);
        stringBuilder.append(startColumn).append(this.cycle2).append(endColumn);
        stringBuilder.append(startColumn).append(this.enoughFineTuningStimulae).append(endColumn);
        stringBuilder.append(startColumn).append(this.champion).append(endColumn);
        stringBuilder.append(startColumn).append(this.looser).append(endColumn);
        stringBuilder.append(endRow);
        return stringBuilder.toString();
    }

    private HashMap<String, String> makeMapFromCsvString(String csvTable, String tableName) {
        String[] rows = csvTable.split("\n");
        HashMap<String, String> retVal = new HashMap();
        retVal.put("Stimuli_report_" + tableName + "_" + "head", rows[0]);
        for (int i = 1; i < rows.length; i++) {
            retVal.put("Stimuli_report_" + tableName + "_row_" + i, rows[i]);
        }
        return retVal;
    }

}
