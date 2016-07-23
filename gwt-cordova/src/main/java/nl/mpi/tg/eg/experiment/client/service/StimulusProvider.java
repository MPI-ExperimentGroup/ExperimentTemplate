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

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus.Tag;

/**
 * @since Jun 23, 2015 11:07:47 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimulusProvider {

    private final List<Stimulus> stimulusArray = new ArrayList<>();
    private final List<Stimulus> stimulusSubsetArray = new ArrayList<>();
//    private final List<String> noisyList = new ArrayList<>();
//    private final List<String> pictureList = new ArrayList<>();
    private int totalStimuli;
    private Stimulus currentStimulus = null;

    public StimulusProvider() {
        GeneratedStimulus.fillStimulusList(stimulusArray);
//        noisyList.addAll(Arrays.asList(Stimulus.NOISE_AUDIO));
//        Stimulus.fillPictureList(pictureList);

        //stimulusSubsetArray.addAll(stimulusArray);
        totalStimuli = stimulusSubsetArray.size();
    }

    public void getAll() {
        stimulusSubsetArray.addAll(stimulusArray);
    }

    private Integer getDefaultInt(Integer foundValue) {
        return (foundValue == null) ? 0 : foundValue;
    }

    private Tag getFirstTagMatch(final List<Tag> wordTags, Stimulus stimulus) {
        Tag wordTag = null;
        for (Tag currentTag : wordTags) {
            if (stimulus.getTags().contains(currentTag)) {
                wordTag = currentTag;
                break;
            }
        }
        return wordTag;
    }

    public void getSubset(final List<Tag> selectionTags, final boolean randomise, final int repeatCount, final String seenList) {
        getSubset(selectionTags, stimulusArray.size(), randomise, repeatCount, seenList);
    }

    public void getSdCardSubset(final ArrayList<String> directoryTagArray, final List<String[]> directoryList, final TimedStimulusListener simulusLoadedListener, final TimedStimulusListener simulusErrorListener, final int maxStimulusCount, final boolean randomise, final int repeatCount, final String seenList) {
        final List<Stimulus> stimulusListCopy = new ArrayList<>();
        stimulusArray.clear();
        appendSdCardSubset(directoryTagArray, stimulusListCopy, directoryList, simulusLoadedListener, simulusErrorListener, maxStimulusCount, randomise, repeatCount, seenList);
    }

    private void appendSdCardSubset(final ArrayList<String> directoryTagArray, final List<Stimulus> stimulusListCopy, final List<String[]> directoryList, final TimedStimulusListener simulusLoadedListener, final TimedStimulusListener simulusErrorListener, final int maxStimulusCount, final boolean randomise, final int repeatCount, final String seenList) {
        if (directoryTagArray.isEmpty()) {
            final List<Stimulus> stimulusSubsetArrayTemp = new ArrayList<>();
            if (!directoryList.isEmpty()) {
                simulusLoadedListener.postLoadTimerFired();
            } else {
                while (!stimulusListCopy.isEmpty() && maxStimulusCount > stimulusSubsetArrayTemp.size()) {
                    final int nextIndex = (randomise) ? new Random().nextInt(stimulusListCopy.size()) : 0;
                    Stimulus stimulus = stimulusListCopy.remove(nextIndex);
                    stimulusArray.add(stimulus);
                    if (!seenList.contains(stimulus.getUniqueId())) {
                        stimulusSubsetArrayTemp.add(stimulus);
                    }
                }
                if (!randomise) {
                    Collections.sort(stimulusSubsetArrayTemp, new Comparator<Stimulus>() {
                        @Override
                        public int compare(Stimulus o1, Stimulus o2) {
                            return (o1.getUniqueId().compareTo(o2.getUniqueId()));
                        }
                    });
                }
                stimulusSubsetArray.clear();
                for (int repeatIndex = 0; repeatIndex < repeatCount; repeatIndex++) {
                    stimulusSubsetArray.addAll(stimulusSubsetArrayTemp);
                }
                totalStimuli = stimulusSubsetArray.size();
                simulusLoadedListener.postLoadTimerFired();
            }
        } else {
            final String directoryTag = directoryTagArray.remove(0);
            final SdCardStimuli sdCardStimuli = new SdCardStimuli(stimulusListCopy, directoryList, new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    appendSdCardSubset(directoryTagArray, stimulusListCopy, directoryList, simulusLoadedListener, simulusErrorListener, maxStimulusCount, randomise, repeatCount, seenList);
                }
            }, simulusErrorListener);
            sdCardStimuli.fillStimulusList(directoryTag);
        }
    }

    public void getSubset(final List<Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final String seenList) {
        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
        getSubset(selectionTags, maxStimulusCount, randomise, repeatCount, seenList, stimulusListCopy);
    }

    public void getSubset(final List<Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final String seenList, List<Stimulus> stimulusListCopy) {
        final List<Stimulus> stimulusSubsetArrayTemp = new ArrayList<>();
        while (!stimulusListCopy.isEmpty() && maxStimulusCount > stimulusSubsetArrayTemp.size()) {
            final int nextIndex = (randomise) ? new Random().nextInt(stimulusListCopy.size()) : 0;
            Stimulus stimulus = stimulusListCopy.remove(nextIndex);
            if (stimulus.getTags().containsAll(selectionTags)) {
                if (!seenList.contains(stimulus.getUniqueId())) {
                    stimulusSubsetArrayTemp.add(stimulus);
                }
            }
        }
        stimulusSubsetArray.clear();
        for (int repeatIndex = 0; repeatIndex < repeatCount; repeatIndex++) {
            stimulusSubsetArray.addAll(stimulusSubsetArrayTemp);
        }
        totalStimuli = stimulusSubsetArray.size();
    }

    public void getSubset(final int maxWordUse, final String seenList, final List<Tag> speakerTags, final List<Tag> wordTags, final int maxSpeakerWordCount) {
        // we now also handle subsetting with setCount and seenList
        HashMap<Tag, Integer> wordCounter = new HashMap<>();
        HashMap<String, Integer> similarityCounter = new HashMap<>();
        // preload counters
        for (Stimulus stimulus : new ArrayList<>(stimulusArray)) {
            if (seenList.contains(stimulus.getUniqueId())) {
                Tag wordTag = getFirstTagMatch(wordTags, stimulus);
                Tag speakerTag = getFirstTagMatch(speakerTags, stimulus);
                if (wordTag != null && speakerTag != null) {
                    final String wordAndSpeaker = wordTag.name() + speakerTag.name();
                    Integer wordCount = getDefaultInt(wordCounter.get(wordTag));
                    Integer speakerWordCount = getDefaultInt(similarityCounter.get(wordAndSpeaker));
                    speakerWordCount++;
                    wordCount++;
                    similarityCounter.put(wordAndSpeaker, speakerWordCount);
                    wordCounter.put(wordTag, wordCount);
                }
            }
        }
        stimulusSubsetArray.clear();
        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
        while (!stimulusListCopy.isEmpty()) {
            Stimulus stimulus = stimulusListCopy.remove(new Random().nextInt(stimulusListCopy.size()));
            if (!seenList.contains(stimulus.getUniqueId())) {
                Tag wordTag = getFirstTagMatch(wordTags, stimulus);
                Tag speakerTag = getFirstTagMatch(speakerTags, stimulus);
                if (wordTag != null && speakerTag != null) {
                    final String wordAndSpeaker = wordTag.name() + speakerTag.name();
                    Integer wordCount = getDefaultInt(wordCounter.get(wordTag));
                    Integer speakerWordCount = getDefaultInt(similarityCounter.get(wordAndSpeaker));
                    if (wordCount < maxWordUse && speakerWordCount < maxSpeakerWordCount) {
                        System.out.println("adding based on: " + wordTag + " " + wordCount + " " + wordAndSpeaker + " " + speakerWordCount);
                        speakerWordCount++;
                        wordCount++;
                        similarityCounter.put(wordAndSpeaker, speakerWordCount);
                        wordCounter.put(wordTag, wordCount);
                        stimulusSubsetArray.add(stimulus);
                    } else {
                        System.out.println("rejecting based on: " + wordTag + " " + wordCount + " " + wordAndSpeaker + " " + speakerWordCount);
                    }
                }
            }
        }
        totalStimuli = stimulusSubsetArray.size();
//        setCurrentTags(selectionTags); // todo: this tag list is inadequate and needs to take tow arrays in this case
    }

    public void getSubset(final Tag similarity, final int maxWordUse, final List<Tag> wordTags, final String seenList) {
        // we now also handle subsetting with setCount and seenList
        HashMap<Tag, Integer> wordCounter = new HashMap<>();
        // preload counters
        for (Stimulus stimulus : new ArrayList<>(stimulusArray)) {
            if (seenList.contains(stimulus.getUniqueId())) {
                Set<Tag> commonTags = new HashSet<>(wordTags);
                commonTags.retainAll(stimulus.getTags());
                for (Tag wordTag : commonTags) {
                    Integer value = getDefaultInt(wordCounter.get(wordTag));
                    value++;
                    wordCounter.put(wordTag, value);
                }
            }
        }
        stimulusSubsetArray.clear();
        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
        while (!stimulusListCopy.isEmpty()) {
            Stimulus stimulus = stimulusListCopy.remove(new Random().nextInt(stimulusListCopy.size()));
            if (stimulus.getTags().contains(similarity) && !seenList.contains(stimulus.getUniqueId())) {
                List<Tag> commonTags = new ArrayList<>(wordTags);
                commonTags.retainAll(stimulus.getTags());
                for (Tag wordTag : commonTags) {
                    Integer value = getDefaultInt(wordCounter.get(wordTag));
                    if (value < maxWordUse) {
                        value++;
                        wordCounter.put(wordTag, value);
                        stimulusSubsetArray.add(stimulus);
//                        System.out.println("adding based on: " + wordTag + " " + value);
                        break;
                    } else {
//                        System.out.println("rejecting based on: " + wordTag + " " + value);
                    }
                }
            }
        }
        totalStimuli = stimulusSubsetArray.size();
    }

    // todo: audio and image evetns do not indicate phase learning or test
    // todo: next button could have its own timer to make reporting easier
    public Stimulus getCurrentStimulus() {
        return currentStimulus;
    }

    public void setCurrentStimulus(Stimulus currentStimulus) {
        this.currentStimulus = currentStimulus;
    }

    public void getNextStimulus() {
        currentStimulus = stimulusSubsetArray.remove(0);
    }

    public void pushCurrentStimulusToEnd() {
        stimulusSubsetArray.add(currentStimulus);
    }

    public boolean hasNextStimulus() {
        return !stimulusSubsetArray.isEmpty();
    }

    public int getTotalStimuli() {
        return totalStimuli;
    }

    public List<Stimulus> getMatchingStimuli(final String matchingRegex, final int maxStimulusCount) {
        final List<Stimulus> matchingStimuli = new ArrayList<>();
        RegExp pattern = RegExp.compile(matchingRegex);
        MatchResult matcher = pattern.exec(currentStimulus.getUniqueId());
        if (matcher != null) {
            String group = matcher.getGroup(0);
            for (Stimulus stimulus : stimulusArray) {
                final String uniqueId = stimulus.getUniqueId();
                if (uniqueId.contains(group)) {
                    matchingStimuli.add(stimulus);
                }
            }
        }
        return matchingStimuli;
    }

    public int getRemainingStimuli() {
        return stimulusSubsetArray.size();
    }

//    public void getNoisyStimuli() {
//        // The first 2 trials should always be the filler words, Then there would be 12 real trials, each repeating twice in a complete random order (so 26 trials in all).
//        stimulusSubsetArray.clear();
//        // add fillers
////        stimulusSubsetArray.add(new Stimulus(null, Stimulus.FILLER_AUDIO1, Stimulus.FILLER_AUDIO1, null, null));
////        stimulusSubsetArray.add(new Stimulus(null, Stimulus.FILLER_AUDIO2, Stimulus.FILLER_AUDIO2, null, null));
//
////        List<String> noisyListCopy1 = new ArrayList<>(noisyList);
////        while (!noisyListCopy1.isEmpty()) {
////            String noisyStimulus = noisyListCopy1.remove(new Random().nextInt(noisyListCopy1.size()));
////            stimulusSubsetArray.add(new Stimulus(null, noisyStimulus, noisyStimulus, null, null));
////        }
////        List<String> noisyListCopy2 = new ArrayList<>(noisyList);
////        while (!noisyListCopy2.isEmpty()) {
////            String noisyStimulus = noisyListCopy2.remove(new Random().nextInt(noisyListCopy2.size()));
////            stimulusSubsetArray.add(new Stimulus(null, noisyStimulus, noisyStimulus, null, null));
////        }
//        totalStimuli = stimulusSubsetArray.size();
//    }
    @Deprecated // todo: perhaps this would be better done in the respective presenters
    public List<String> getPictureList() {
        final ArrayList<String> returnList = new ArrayList<>();
        for (Stimulus stimulus : stimulusSubsetArray) {
            if (stimulus.getImage() != null && !stimulus.getImage().isEmpty()) {
                returnList.add(stimulus.getImage());
            }
        }
        return returnList;
    }
}
