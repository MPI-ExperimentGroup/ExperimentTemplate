/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus.Similarity;
import nl.mpi.tg.eg.experiment.client.model.Stimulus.Speaker;

/**
 * @since Jun 23, 2015 11:07:47 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimulusProvider {

    private final List<Stimulus> stimulusArray = new ArrayList<>();
    private final List<Stimulus> stimulusSubsetArray = new ArrayList<>();
    private final List<String> noisyList = new ArrayList<>();
    private final List<String> pictureList = new ArrayList<>();
    private int totalStimuli;

    public StimulusProvider() {
        Stimulus.fillStimulusList(stimulusArray);
        noisyList.addAll(Arrays.asList(Stimulus.NOISE_AUDIO));
        Stimulus.fillPictureList(pictureList);

        //stimulusSubsetArray.addAll(stimulusArray);
        totalStimuli = stimulusSubsetArray.size();
    }

    public void getAll() {
        stimulusSubsetArray.addAll(stimulusArray);
    }

    public void getSubset(final int setCount, final String seenList) {
//        int targetMin = 6 * 6 * 3;
//        System.out.println("");
//        System.out.println("stimulusArray: " + stimulusArray);
//        System.out.println("targetMin: " + targetMin);
////        ArrayList<
//        stimulusSubsetArray.clear();
//        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
//        while (targetMin > 0) {
//            targetMin -= 6;
//            for (int wordCounter = 0; wordCounter < 4; wordCounter++) {
//                // remove four from each word from each speaker
//                stimulusListCopy.remove(targetMin + new Random().nextInt(6));
//            }
//        }

        List<Stimulus> stimulusListSubset = new ArrayList<>();
        for (Speaker speaker : Speaker.values()) {
            List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
            ArrayList<String> wordList1 = new ArrayList<>();
            ArrayList<String> wordList2 = new ArrayList<>();
            while (!stimulusListCopy.isEmpty()) {
                Stimulus currentStimulus = stimulusListCopy.remove(new Random().nextInt(stimulusListCopy.size()));
                if (currentStimulus.getSpeaker().equals(speaker)) {
                    final String word = currentStimulus.getWord();
                    if (!wordList1.contains(word)) {
                        stimulusListSubset.add(currentStimulus);
                        wordList1.add(word);
                    } else {
                        if (!wordList2.contains(word)) {
                            stimulusListSubset.add(currentStimulus);
                            wordList2.add(word);
                        } else {
                        }
                    }
                }
            }
        }
        stimulusSubsetArray.clear();
        while (!stimulusListSubset.isEmpty()) {
            // randomise the result
            Stimulus stimulus = stimulusListSubset.remove(new Random().nextInt(stimulusListSubset.size()));
            stimulusSubsetArray.add(stimulus);
        }
//        stimulusSubsetArray.addAll(stimulusListCopy);
        totalStimuli = stimulusSubsetArray.size();
    }

    public void getSubset(final Similarity similarity, final int setCount, final String seenList) {
        stimulusSubsetArray.clear();
        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
        while (!stimulusListCopy.isEmpty()) {
            Stimulus stimulus = stimulusListCopy.remove(new Random().nextInt(stimulusListCopy.size()));
            if (stimulus.getSpeakerSimilarity().equals(similarity)) {
                stimulusSubsetArray.add(stimulus);
            }
        }
        totalStimuli = stimulusSubsetArray.size();
    }

    // todo: audio and image evetns do not indicate phase learning or test
    // todo: next button could have its own timer to make reporting easier
    public Stimulus getNextStimulus() {
        return stimulusSubsetArray.remove(0);
    }

    public boolean hasNextStimulus() {
        return !stimulusSubsetArray.isEmpty();
    }

    public int getTotalStimuli() {
        return totalStimuli;
    }

    public int getRemainingStimuli() {
        return stimulusSubsetArray.size();
    }

    public void getNoisyStimuli() {
        // The first 2 trials should always be the filler words, Then there would be 12 real trials, each repeating twice in a complete random order (so 26 trials in all).
        stimulusSubsetArray.clear();
        // add fillers
        stimulusSubsetArray.add(new Stimulus(null, Stimulus.FILLER_AUDIO1, Stimulus.FILLER_AUDIO1, null, null));
        stimulusSubsetArray.add(new Stimulus(null, Stimulus.FILLER_AUDIO2, Stimulus.FILLER_AUDIO2, null, null));

        List<String> noisyListCopy1 = new ArrayList<>(noisyList);
        while (!noisyListCopy1.isEmpty()) {
            String noisyStimulus = noisyListCopy1.remove(new Random().nextInt(noisyListCopy1.size()));
            stimulusSubsetArray.add(new Stimulus(null, noisyStimulus, noisyStimulus, null, null));
        }
        List<String> noisyListCopy2 = new ArrayList<>(noisyList);
        while (!noisyListCopy2.isEmpty()) {
            String noisyStimulus = noisyListCopy2.remove(new Random().nextInt(noisyListCopy2.size()));
            stimulusSubsetArray.add(new Stimulus(null, noisyStimulus, noisyStimulus, null, null));
        }
        totalStimuli = stimulusSubsetArray.size();
    }

    public List<String> getPictureList() {
        final ArrayList<String> returnList = new ArrayList<>(pictureList);
//        Collections.shuffle(returnList);
        return returnList;
    }
}
