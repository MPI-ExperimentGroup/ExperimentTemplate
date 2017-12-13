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
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BandStimuliProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsBookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Constants;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsNonWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.ConstantsWords;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.RandomIndexing;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.Vocabulary;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic.BookkeepingStimulus;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.AdVocAsStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 27, 2017 2:01:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsStimuliProvider extends BandStimuliProvider<AdVocAsBookkeepingStimulus> {

    // experiment specific stuff here
    private RandomIndexing rndIndexing;
    private ArrayList<Integer> nonWordsIndexes;

    private ArrayList<ArrayList<AdVocAsStimulus>> words;
    private ArrayList<AdVocAsStimulus> nonwords;

    // fine tuning stuff
    private Random rnd;

    @Override
    public void initialiseStimuliState(String stimuliStateSnapshot) {

        super.initialiseStimuliState(stimuliStateSnapshot);

        Vocabulary vocab = new Vocabulary();
        this.words = vocab.initialiseWords(ConstantsWords.WORDS);
        ArrayList<AdVocAsStimulus> nonwordstmp = new ArrayList<>();
        nonwordstmp.addAll(Arrays.asList(ConstantsNonWords.NONWORDS_ARRAY));
        this.nonwords = vocab.initialiseNonwords(nonwordstmp);

        this.totalStimuli = this.nonwords.size();
        for (int i = 0; i < Constants.NUMBER_OF_BANDS; i++) {
            this.totalStimuli += this.words.get(i).size();
        }

        // int startBand, int nonwordsPerBlock, int averageNonwordPosition, int nonwordsAvailable
        this.rndIndexing = new RandomIndexing(Constants.START_BAND, Constants.NONWORDS_PER_BLOCK, Constants.AVRERAGE_NON_WORD_POSITION, nonwords.size());
        this.nonWordsIndexes = this.rndIndexing.updateAndGetIndices();

        this.rnd = new Random();
    }

    public ArrayList<ArrayList<AdVocAsStimulus>> getWords() {
        return this.words;
    }

    public ArrayList<AdVocAsStimulus> getNonwords() {
        return this.nonwords;
    }

    public ArrayList<Integer> getNonWordsIndices() {
        return this.nonWordsIndexes;
    }

    // experiment-specific, must be overridden
    // current band index is prepared by hasNextStimulus method
    @Override
    public AdVocAsBookkeepingStimulus deriveNextFastTrackStimulus() {
        int experimentNumber = this.responseRecord.size();
        AdVocAsStimulus stimulus;
        if (this.nonWordsIndexes.contains(experimentNumber)) {
            stimulus = this.nonwords.remove(0);
        } else {
            stimulus = this.words.get(this.currentBandIndex).remove(0);
        }

        AdVocAsBookkeepingStimulus retVal = new AdVocAsBookkeepingStimulus(stimulus); // injection constructor:  bans stimulus into a bookkeping stimulus with null-reaction and correctness
        return retVal;
    }

    @Override
    protected boolean analyseCorrectness(Stimulus stimulus, String stimulusResponse) {
        boolean retVal;
        String stimulusResponseProcessed = new String(stimulusResponse);
        stimulusResponseProcessed = stimulusResponseProcessed.replaceAll(",", "&#44;");
        if (!(stimulusResponseProcessed.equals(Constants.WORD) || (stimulusResponseProcessed.equals(Constants.NONWORD)))) {
            System.out.println("Erroenous input: neither word nor nonword; something went terrible wrong.");
            return false;
        }
        int index = this.getCurrentStimulusIndex();
        if (this.responseRecord.get(index).getBandNumber() > -1) {
            retVal = stimulusResponseProcessed.equals(Constants.WORD);
        } else {
            retVal = stimulusResponseProcessed.equals(Constants.NONWORD);
        }
        return retVal;
    }

    @Override
    protected boolean enoughStimuliForFastTrack() {
        boolean retVal = true;
        // check if we still have data for the next experiment
        int nextExperimentIndex = this.responseRecord.size();

        if (this.nonWordsIndexes.contains(nextExperimentIndex) && nonwords.size() < 1) {
            System.out.println("We do not have nonwords left for the Fast Track!");
            retVal = false;
        }

        if (!this.nonWordsIndexes.contains(nextExperimentIndex)
                && this.words.get(this.currentBandIndex).size() < 1) {
            System.out.println("We do not have words in band " + this.currentBandIndex + "  left for the Fast Track!");
            retVal = false;
        }
        return retVal;
    }

// we already at the right band, the last band in the fast track with the correct answer
    // return false if there is not enough words and nonwords
    @Override
    public boolean initialiseNextFineTuningTuple() {
        if (this.nonwords.size() < 1) {
            return false;
        }
        if (this.words.get(this.currentBandIndex).size() < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE - 1) {
            return false;
        }
        int nonWordPos = rnd.nextInt(Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE);
        AdVocAsBookkeepingStimulus bStimulus;
        for (int i = 0; i < nonWordPos; i++) {
            bStimulus = new AdVocAsBookkeepingStimulus(this.words.get(this.currentBandIndex).remove(0)); //injection constructor
            this.tupleFT.add(bStimulus);
        }
        bStimulus = new AdVocAsBookkeepingStimulus(this.nonwords.remove(0)); // injection constructor
        this.tupleFT.add(bStimulus);
        for (int i = nonWordPos + 1; i < Constants.FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE; i++) {
            bStimulus = new AdVocAsBookkeepingStimulus(this.words.get(this.currentBandIndex).remove(0));//injection constructor
            this.tupleFT.add(bStimulus);
        }
        return true;
    }

    @Override
    protected void recycleUnusedStimuli() {
        boolean ended = this.tupleFT.isEmpty();
        while (!ended) {
            AdVocAsBookkeepingStimulus bStimulus = this.tupleFT.remove(0);
            //(String uniqueId, String label, String correctResponses, int bandNumber)
            AdVocAsStimulus stimulus = new AdVocAsStimulus(bStimulus.getUniqueId(), bStimulus.getLabel(), bStimulus.getCorrectResponses(), bStimulus.getBandNumber());
            int bandNumber = stimulus.getBandNumber();
            if (bandNumber > -1) { // a word
                // bandIndex is 1 less then band number 
                this.words.get(bandNumber - 1).add(stimulus);
            } else {
                this.nonwords.add(stimulus);
            }
            ended = this.tupleFT.isEmpty();
        }

    }

    @Override
    public String getStringFastTrack(String startRow, String endRow, String startColumn, String endColumn) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startRow);
        stringBuilder.append(startColumn).append("Label").append(endColumn);
        stringBuilder.append(startColumn).append("BandNumber").append(endColumn);
        stringBuilder.append(startColumn).append("UserAnswer").append(endColumn);
        stringBuilder.append(startColumn).append("IsAnswerCorrect").append(endColumn);
        stringBuilder.append(startColumn).append("NonwordsFrequencyAtThisPoint").append(endColumn);
        stringBuilder.append(endRow);
        int counterNonwords = 0;
        for (int i = 0; i <= this.timeTickEndFastTrack; i++) {
            BookkeepingStimulus stimulus = this.responseRecord.get(i);
            if (stimulus.getBandNumber() == -1) {
                counterNonwords++;
            }
            double frequency = ((double) counterNonwords) / ((double) (i + 1));

            StringBuilder row = new StringBuilder();
            row.append(startColumn).append(stimulus.getLabel()).append(endColumn);
            row.append(startColumn).append(stimulus.getBandNumber()).append(endColumn);
            row.append(startColumn).append(stimulus.getReaction()).append(endColumn);
            row.append(startColumn).append(stimulus.getCorrectness()).append(endColumn);
            row.append(startColumn).append(frequency).append(endColumn);
            stringBuilder.append(startRow).append(row).append(endRow);
        }
        return stringBuilder.toString();

    }

    @Override
    public String getHtmlStimuliReport() {

        StringBuilder htmlStringBuilder = new StringBuilder();

        String experimenteeReport = this.getHtmlExperimenteeReport();
        htmlStringBuilder.append(experimenteeReport);

        htmlStringBuilder.append("<br><br><p>Detailed report for researcher</p>");

        String summary = this.getStringSummary("<tr>", "</tr>", "<td>", "</td>");
        String inhoudFastTrack = this.getStringFastTrack("<tr>", "</tr>", "<td>", "</td>");
        String inhoudFineTuning = this.getStringFineTuningHistory("<tr>", "</tr>", "<td>", "</td>", "html");

        htmlStringBuilder.append("<p>User summary</p><table border=1>").append(summary).append("</table><br><br>");
        htmlStringBuilder.append("<p>Fast Track History</p><table border=1>").append(inhoudFastTrack).append("</table><br><b>");
        htmlStringBuilder.append("<p>Fine tuning History</p><table border=1>").append(inhoudFineTuning).append("</table>");
        return htmlStringBuilder.toString();
        //return "<table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td></tr><tr><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>0</td></tr></table>";
    }

    private String getHtmlExperimenteeReport() {
        StringBuilder htmlStringBuilder = new StringBuilder();
        HashMap<String, ArrayList<AdVocAsBookkeepingStimulus>> wordTables = this.generateWordNonWordSequences(this.responseRecord);

        String experimenteeWordTable = this.getHtmlExperimenteeRecords(wordTables.get("words"));
        String experimenteeNonwordTable = this.getHtmlExperimenteeRecords(wordTables.get("nonwords"));
        String experimenteePositionDiagram = this.getHtmlExperimenteePositionDiagram();

        htmlStringBuilder.append("<table><tr><td>Uw resultaten: woorden</td><td></td><td>Uw resultaten: niet-woorden</td></tr>");
        htmlStringBuilder.append("<tr style=\"vertical-align: top;\"><td>").append(experimenteeWordTable).append("</td><td></td><td>").append(experimenteeNonwordTable).append("</td></tr></table>");

        htmlStringBuilder.append("<p>Uw score: </p>").append(this.getPercentageScore()).append("<br><br>");
        htmlStringBuilder.append("<p>Uw kennis wan de Nederlandse woordenschat</p>").append(experimenteePositionDiagram).append("<br><br>");

        return htmlStringBuilder.toString();
    }

    private String getHtmlExperimenteeRecords(ArrayList<AdVocAsBookkeepingStimulus> atoms) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<table>");
        String color;
        for (AdVocAsBookkeepingStimulus atom : atoms) {
            if (atom.getCorrectness()) {
                color = "style=\"color:green\"";
            } else {
                color = "style=\"color:red\"";
            }
            htmlStringBuilder.append("<tr ").append(color).append(">");
            htmlStringBuilder.append("<td>").append(atom.getLabel()).append("</td>");
            htmlStringBuilder.append("</tr>");
        }
        htmlStringBuilder.append("</table>");

        return htmlStringBuilder.toString();
    }

    private String getHtmlExperimenteePositionDiagram() {
        StringBuilder htmlStringBuilder = new StringBuilder();
        long perScore = this.getPercentageScore();
        HashMap<Long, String> content = this.generateDiagramSequence(this.responseRecord);
        htmlStringBuilder.append("<table>");
        for (Long key : content.keySet()) {
            if (key.equals(perScore)) {
                htmlStringBuilder.append("<tr style=\"font-weight:bold;\">");
            } else {
                htmlStringBuilder.append("<tr>");
            }
            htmlStringBuilder.append("<td>").append(key).append("</td>");
            htmlStringBuilder.append("<td>").append(content.get(key)).append("</td>");
            htmlStringBuilder.append("</tr>");
        }
        htmlStringBuilder.append("</table>");

        return htmlStringBuilder.toString();
    }
    
    
    public HashMap<Long, String> generateDiagramSequence(ArrayList<AdVocAsBookkeepingStimulus> records) {
        HashMap<Long, String> retVal = new HashMap<Long, String>();

        HashMap<Integer, String> sampleWords = this.retrieveSampleWords(records);

        Long perScore = this.getPercentageScore();
        boolean experimenteeResultAdded=false;
        
        if (perScore<Constants.START_PERCENTAGE_FOR_GRAPH) {
               Integer bScore = this.getBandScore();
               retVal.put(perScore, sampleWords.get(bScore)); 
               experimenteeResultAdded = true;
            }
        
        for (long percentage = Constants.START_PERCENTAGE_FOR_GRAPH; percentage <= 100; percentage = percentage + 10) {
            if (!experimenteeResultAdded && perScore<percentage) {
               Integer bScore = this.getBandScore();
               retVal.put(perScore, sampleWords.get(bScore)); 
               experimenteeResultAdded = true;
            }
            Integer bandNumber = this.getPercentageBandTable().get(percentage);
            String value = sampleWords.get(bandNumber);
            retVal.put(percentage, value);
        }

        
        return retVal;
    }

    public HashMap<Integer, String> retrieveSampleWords(ArrayList<AdVocAsBookkeepingStimulus> records) {
        HashMap<Integer, String> retVal = new HashMap<Integer, String>();
        for (AdVocAsBookkeepingStimulus bStimulus : records) {
            if (bStimulus.getCorrectResponses().equals(Constants.WORD)) { // is a word
                Integer key = bStimulus.getBandNumber();
                if (!retVal.containsKey(key)) {
                    retVal.put(key, bStimulus.getLabel());
                }
            }
        }

        // fill in absent values
        for (int i = 1; i <= Constants.NUMBER_OF_BANDS; i++) {
            Integer bandNumber = i;
            if (!retVal.containsKey(bandNumber)) {
                // unseen word in the band means that there must be for sure
                // non-used words for this band in the words-container
                // if there will be array out of boud exception
                String value = this.words.get(i-1).get(0).getLabel();
                retVal.put(bandNumber, value);
            }
        }

        return retVal;
    }


    private HashMap<String, ArrayList<AdVocAsBookkeepingStimulus>> generateWordNonWordSequences(ArrayList<AdVocAsBookkeepingStimulus> records) {
        HashMap<String, ArrayList<AdVocAsBookkeepingStimulus>> retVal = new HashMap<String, ArrayList<AdVocAsBookkeepingStimulus>>();
        retVal.put("words", new ArrayList<AdVocAsBookkeepingStimulus>());
        retVal.put("nonwords", new ArrayList<AdVocAsBookkeepingStimulus>());

        for (AdVocAsBookkeepingStimulus bStimulus : records) {
            if (Constants.WORD.equals(bStimulus.getCorrectResponses())) {
                retVal.get("words").add(bStimulus);
            }
            if (Constants.NONWORD.equals(bStimulus.getCorrectResponses())) {
                retVal.get("nonwords").add(bStimulus);
            }
        }
        return retVal;
    }
}
