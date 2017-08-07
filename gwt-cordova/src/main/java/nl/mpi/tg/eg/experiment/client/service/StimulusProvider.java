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

import nl.mpi.tg.eg.frinex.common.StimuliProvider;
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
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus.Tag;

/**
 * @since Jun 23, 2015 11:07:47 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimulusProvider implements StimuliProvider {

    private final List<Stimulus> stimulusArray = new ArrayList<>();
    private final List<Stimulus> stimulusSelectionArray = new ArrayList<>();
    private final List<Stimulus> stimulusSubsetArray = new ArrayList<>();
//    private final List<String> noisyList = new ArrayList<>();
//    private final List<String> pictureList = new ArrayList<>();
//    private int totalStimuli;
    private int currentStimuliIndex = -1;
//    private Stimulus currentStimulus = null;

    public StimulusProvider() {
        GeneratedStimulus.fillStimulusList(stimulusArray);
//        noisyList.addAll(Arrays.asList(Stimulus.NOISE_AUDIO));
//        Stimulus.fillPictureList(pictureList);

        //stimulusSubsetArray.addAll(stimulusArray);
//        totalStimuli = stimulusSubsetArray.size();
    }

    @Override
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

    @Override
    public void getSubset(final List<Tag> selectionTags, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final int adjacencyThreshold, final String storedStimulusList, final int currentStimuliIndex) {
        getSubset(selectionTags, stimulusArray.size(), randomise, repeatCount, repeatRandomWindow, adjacencyThreshold, storedStimulusList, currentStimuliIndex);
    }

    @Override
    public void getSdCardSubset(final ArrayList<String> directoryTagArray, final List<String[]> directoryList, final TimedStimulusListener simulusLoadedListener, final TimedStimulusListener simulusErrorListener, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final int adjacencyThreshold, final String storedStimulusList, final int currentStimuliIndex) {
        final List<Stimulus> stimulusListCopy = new ArrayList<>();
        stimulusSelectionArray.clear();
        appendSdCardSubset(directoryTagArray, stimulusListCopy, directoryList, simulusLoadedListener, simulusErrorListener, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold, storedStimulusList, currentStimuliIndex);
    }

    private void appendSdCardSubset(final ArrayList<String> directoryTagArray, final List<Stimulus> stimulusListCopy, final List<String[]> directoryList, final TimedStimulusListener simulusLoadedListener, final TimedStimulusListener simulusErrorListener, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final int adjacencyThreshold, final String storedStimulusList, final int currentStimuliIndex) {
        if (directoryTagArray.isEmpty()) {
            final List<Stimulus> stimulusSubsetArrayTemp = new ArrayList<>();
            if (!directoryList.isEmpty()) {
                simulusLoadedListener.postLoadTimerFired();
            } else {
                this.currentStimuliIndex = currentStimuliIndex;
                if (!storedStimulusList.isEmpty()) {
//                    stimulusArray.clear();
//                    stimulusArray.addAll(stimulusListCopy);
                    // todo: also load the list for other getSubset related methods
                    loadStoredStimulusList(storedStimulusList, stimulusSubsetArrayTemp);
                } else {
                    while (!stimulusListCopy.isEmpty() && maxStimulusCount > stimulusSubsetArrayTemp.size()) {
                        final int nextIndex = (randomise) ? new Random().nextInt(stimulusListCopy.size()) : 0;
                        Stimulus stimulus = stimulusListCopy.remove(nextIndex);
                        stimulusSelectionArray.add(stimulus);
//                    if (!seenList.contains(stimulus.getUniqueId())) {
                        // this is not how seen list now works, it is now a list of stimuli allowing rewind
                        stimulusSubsetArrayTemp.add(stimulus);
//                    }
                    }
                    if (!randomise) {
                        Collections.sort(stimulusSubsetArrayTemp, new Comparator<Stimulus>() {
                            @Override
                            public int compare(Stimulus o1, Stimulus o2) {
                                return (o1.getUniqueId().compareTo(o2.getUniqueId()));
                            }
                        });
                    }
                    applyRepeatRandomWindow(stimulusSubsetArrayTemp, repeatCount, repeatRandomWindow, maxStimulusCount);
                    applyAdjacencyCheck(adjacencyThreshold);
                }
//                totalStimuli = stimulusSubsetArray.size();
                simulusLoadedListener.postLoadTimerFired();
            }
        } else {
            final String directoryTag = directoryTagArray.remove(0);
            final SdCardStimuli sdCardStimuli = new SdCardStimuli(stimulusListCopy, directoryList, ".*_question\\....$", new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    // todo: should this not take a single directory?
                    // todo: can this take a file limit per directory?
                    appendSdCardSubset(directoryTagArray, stimulusListCopy, directoryList, simulusLoadedListener, simulusErrorListener, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold, storedStimulusList, currentStimuliIndex);
                }
            }, simulusErrorListener);
            sdCardStimuli.fillStimulusList(directoryTag);
        }
    }

    @Override
    public void getSubset(final List<Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final int adjacencyThreshold, final String storedStimulusList, final int currentStimuliIndex) {
        List<Stimulus> stimulusListCopy = new ArrayList<>(stimulusArray);
        this.currentStimuliIndex = currentStimuliIndex;
        if (!storedStimulusList.isEmpty()) {
            // todo: also load the list for other getSubset related methods
            loadStoredStimulusList(storedStimulusList, stimulusArray);
        } else {
            getSubset(selectionTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold, storedStimulusList, stimulusListCopy);
        }
    }

    @Override
    public void loadStoredStimulusList(String storedStimulusList) {
        loadStoredStimulusList(storedStimulusList, stimulusArray);
    }

    protected void loadStoredStimulusList(String storedStimulusList, final List<Stimulus> stimulusArrayTemp) {
        stimulusSubsetArray.clear();
        while (!storedStimulusList.isEmpty()) {
            // stimuli ids can contain - so we cant split the string on -
            //storedStimulusList = storedStimulusList.replaceFirst("^-", storedStimulusList);
            for (Stimulus stimulus : stimulusArrayTemp) {
                if (storedStimulusList.startsWith("-" + stimulus.getUniqueId() + "-")) {
                    stimulusSubsetArray.add(stimulus);
                    storedStimulusList = storedStimulusList.substring(("-" + stimulus.getUniqueId()).length() - 1);
                }
            }
            if (!storedStimulusList.isEmpty()) {
                storedStimulusList = storedStimulusList.substring(1); // make sure the following - (or any stray chars) are removed  
            }
        }
    }

    @Override
    public void getSubset(final List<Tag> selectionTags, final int maxStimulusCount, final boolean randomise, final int repeatCount, final int repeatRandomWindow, final int adjacencyThreshold, final String storedStimulusList, List<Stimulus> stimulusListCopy) {
        final List<Stimulus> stimulusSubsetArrayTemp = new ArrayList<>();
        stimulusSelectionArray.clear();
        while (!stimulusListCopy.isEmpty() && maxStimulusCount > stimulusSubsetArrayTemp.size()) {
            final int nextIndex = (randomise) ? new Random().nextInt(stimulusListCopy.size()) : 0;
            Stimulus stimulus = stimulusListCopy.remove(nextIndex);
            if (stimulus.getTags().containsAll(selectionTags)) {
                stimulusSelectionArray.add(stimulus);
                if (!storedStimulusList.contains("-" + stimulus.getUniqueId() + "-")) {
                    stimulusSubsetArrayTemp.add(stimulus);
                }
            }
        }
        applyRepeatRandomWindow(stimulusSubsetArrayTemp, repeatCount, repeatRandomWindow, maxStimulusCount);
        applyAdjacencyCheck(adjacencyThreshold);
    }

    protected void applyAdjacencyCheck(final int adjacencyThreshold) {
        for (int attemptIndex = 0; attemptIndex < 3; attemptIndex++) {
//            System.out.println("attemptIndex: " + attemptIndex);
            boolean changeMade = false;
            for (int index = 0; index < stimulusSubsetArray.size(); index++) {
                final String currentImage;
                if (stimulusSubsetArray.get(index).hasImage()) {
                    currentImage = stimulusSubsetArray.get(index).getImage();
                } else if (stimulusSubsetArray.get(index).hasAudio()) {
                    currentImage = stimulusSubsetArray.get(index).getAudio();
                } else if (stimulusSubsetArray.get(index).hasVideo()) {
                    currentImage = stimulusSubsetArray.get(index).getVideo();
                } else {
                    currentImage = stimulusSubsetArray.get(index).getLabel();
                }
                boolean needsMoving = false;
                for (int adjacencyIndex = 1; adjacencyIndex < adjacencyThreshold + 1; adjacencyIndex++) {
                    if (index + adjacencyIndex < stimulusSubsetArray.size()) {
                        final String adjacentImage;
                        if (stimulusSubsetArray.get(index).hasImage()) {
                            adjacentImage = stimulusSubsetArray.get(index + adjacencyIndex).getImage();
                        } else if (stimulusSubsetArray.get(index).hasAudio()) {
                            adjacentImage = stimulusSubsetArray.get(index + adjacencyIndex).getAudio();
                        } else if (stimulusSubsetArray.get(index).hasVideo()) {
                            adjacentImage = stimulusSubsetArray.get(index + adjacencyIndex).getVideo();
                        } else {
                            adjacentImage = stimulusSubsetArray.get(index + adjacencyIndex).getLabel();
                        }
                        if (currentImage != null) {
                            needsMoving = currentImage.equals(adjacentImage);
                            if (needsMoving) {
                                break;
                            }
                        }
                    }
                }
                if (needsMoving) {
                    System.out.println("needs moving: " + currentImage);
                    for (int destinationindex = 0; destinationindex < stimulusSubsetArray.size(); destinationindex++) {
                        boolean isSuitable = true;
                        for (int adjacencyIndex = -adjacencyThreshold - 1; adjacencyIndex < adjacencyThreshold + 1; adjacencyIndex++) {
                            if (adjacencyIndex + destinationindex >= 0) {
//                                System.out.println("from " + index + " to " + destinationindex);
//                                System.out.println("adjacencyIndex: " + adjacencyIndex);
                                final String adjacentImage;
                                if (stimulusSubsetArray.get(index).hasImage()) {
                                    adjacentImage = stimulusSubsetArray.get(adjacencyIndex + destinationindex).getImage();
                                } else if (stimulusSubsetArray.get(index).hasAudio()) {
                                    adjacentImage = stimulusSubsetArray.get(adjacencyIndex + destinationindex).getAudio();
                                } else if (stimulusSubsetArray.get(index).hasVideo()) {
                                    adjacentImage = stimulusSubsetArray.get(adjacencyIndex + destinationindex).getVideo();
                                } else {
                                    adjacentImage = stimulusSubsetArray.get(adjacencyIndex + destinationindex).getLabel();
                                }
//                            System.out.println("adjacentImage: " + adjacentImage);
                                if (currentImage != null) {
                                    isSuitable = !currentImage.equals(adjacentImage);
                                    if (!isSuitable) {
//                                        System.out.println("not suitable");
                                        break;
                                    }
                                }
                            }
                        }
                        if (isSuitable) {
                            System.out.println("moving: " + currentImage + " from " + index + " to " + destinationindex);
                            if (destinationindex > index) {
                                stimulusSubsetArray.add(destinationindex - 1, stimulusSubsetArray.remove(index));
                            } else {
                                stimulusSubsetArray.add(destinationindex, stimulusSubsetArray.remove(index));
                            }
                            index--;
                            changeMade = true;
                            break;
                        }
                    }
                }
            }
            if (!changeMade) {
                break;
            }
        }
    }

    private void applyRepeatRandomWindow(final List<Stimulus> stimulusSubsetArrayTemp, final int repeatCount, final int repeatRandomWindow, final int maxStimulusCount) {
        stimulusSubsetArray.clear();
        for (int repeatIndex = 0; repeatIndex < repeatCount; repeatIndex++) {
            stimulusSubsetArray.addAll(stimulusSubsetArrayTemp);
        }
        while (stimulusSubsetArray.size() > maxStimulusCount) {
            // cap the number of stimuli by the max stimuli requested
            stimulusSubsetArray.remove(stimulusSubsetArray.size() - 1);
        }
        if (repeatCount > 1 && repeatRandomWindow > 0 && stimulusSubsetArray.size() > repeatRandomWindow) {
            // todo: perhaps also do this when the repeatRandomWindow is bigger than the stimulusSubsetArray but just reduce the repeatRandomWindow accordingly
            for (int shuffleIndex = repeatRandomWindow; shuffleIndex < stimulusSubsetArray.size(); shuffleIndex++) {
                // shuffle all stimuli in a moving window of 'repeatRandomWindow' so that the repeats are still sparated
                final int randomInt = new Random().nextInt(repeatRandomWindow);
                final int destinationIndex = shuffleIndex - randomInt;
                stimulusSubsetArray.add(destinationIndex, stimulusSubsetArray.remove(shuffleIndex));
//                System.out.println("length: " + stimulusSubsetArray.size());
//                System.out.println("unique count:" + new HashSet<Stimulus>(stimulusSubsetArray).size());
            }
        }
    }

    @Override
    public void getSubset(final int maxWordUse, final String storedStimulusList, final int currentStimuliIndex, final List<Tag> speakerTags, final List<Tag> wordTags, final int maxSpeakerWordCount) {
        this.currentStimuliIndex = currentStimuliIndex;
        // we now also handle subsetting with setCount and seenList
        HashMap<Tag, Integer> wordCounter = new HashMap<>();
        HashMap<String, Integer> similarityCounter = new HashMap<>();
        // preload counters
        for (Stimulus stimulus : new ArrayList<>(stimulusArray)) {
            if (storedStimulusList.contains("-" + stimulus.getUniqueId() + "-")) {
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
            if (!storedStimulusList.contains("-" + stimulus.getUniqueId() + "-")) {
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
//        totalStimuli = stimulusSubsetArray.size();
//        setCurrentTags(selectionTags); // todo: this tag list is inadequate and needs to take tow arrays in this case
    }

    @Override
    public void getSubset(final Tag similarity, final int maxWordUse, final List<Tag> wordTags, final String storedStimulusList, final int currentStimuliIndex) {
        this.currentStimuliIndex = currentStimuliIndex;
        // we now also handle subsetting with setCount and seenList
        HashMap<Tag, Integer> wordCounter = new HashMap<>();
        // preload counters
        for (Stimulus stimulus : new ArrayList<>(stimulusArray)) {
            if (storedStimulusList.contains("-" + stimulus.getUniqueId() + "-")) {
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
            if (stimulus.getTags().contains(similarity) && !storedStimulusList.contains("-" + stimulus.getUniqueId() + "-")) {
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
//        totalStimuli = stimulusSubsetArray.size();
    }

    // todo: audio and image evetns do not indicate phase learning or test
    // todo: next button could have its own timer to make reporting easier
    @Override
    public Stimulus getCurrentStimulus() {
        return this.stimulusSubsetArray.get(currentStimuliIndex);
    }

    @Override
    public int getCurrentStimulusIndex() {
        return currentStimuliIndex;
    }

    @Override
    public void setCurrentStimuliIndex(int currentStimuliIndex) {
        this.currentStimuliIndex = currentStimuliIndex;
    }

    @Override
    public String getLoadedStimulusString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-");
        for (Stimulus stimulus : this.stimulusSubsetArray) {
//            if (stringBuilder.length() > 0) {
//                stringBuilder.append("-");
//            }
            stringBuilder.append(stimulus.getUniqueId());
            stringBuilder.append("-"); // always keep a "-" after each id
        }
        return stringBuilder.toString();
    }

    /*public void setCurrentStimulus(Stimulus currentStimulus) {
        this.stimulusSubsetArray.get(currentStimuliIndex);
    }*/

 /*public void removeStimulus(Stimulus removeStimulus) {
        stimulusSubsetArray.remove(removeStimulus); // todo: is this valid in the current design?
        stimulusSubsetArray.add(currentStimuliIndex - 1, removeStimulus);
        currentStimuliIndex++;
    }*/
    @Override
    public void nextStimulus() {
        currentStimuliIndex++;
    }

    @Override
    public void pushCurrentStimulusToEnd() {
        stimulusSubsetArray.add(getCurrentStimulus());
    }

    @Override
    public boolean hasNextStimulus() {
        return currentStimuliIndex + 1 < stimulusSubsetArray.size();
    }

    @Override
    public int getTotalStimuli() {
        return stimulusSubsetArray.size();
    }

    @Override
    public Stimulus getStimuliFromString(final String stimuliString) {
        for (Stimulus stimulus : stimulusArray) {
            final String uniqueId = stimulus.getUniqueId();
            if (uniqueId.equals(stimuliString)) {
                return stimulus;
            }
        }
        return null;
    }

    @Override
    public List<Stimulus> getMatchingStimuli(final String matchingRegex, final int maxStimulusCount) {
        final List<Stimulus> matchingStimuli = new ArrayList<>();
        RegExp pattern = RegExp.compile(matchingRegex);
        MatchResult matcher = pattern.exec(getCurrentStimulus().getUniqueId());
        if (matcher != null) {
            String group = matcher.getGroup(0);
            // the stimulusSelectionArray should only contain stimuli relevant to this screen
            for (Stimulus stimulus : stimulusSelectionArray) {
                final String uniqueId = stimulus.getUniqueId();
                if (uniqueId.contains(group)) {
                    matchingStimuli.add(stimulus);
                }
            }
        }
        return matchingStimuli;
    }

    /*public int getRemainingStimuli() {
        return stimulusSubsetArray.size() - currentStimuliIndex;
    }*/
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
//    @Override
    public List<Stimulus> getPictureList(GroupParticipantService groupParticipantService, int maxStimuli) {
        final HashMap<String, Stimulus> uniqueList = new HashMap<>();
        uniqueList.put(getCurrentStimulus().getImage(), getCurrentStimulus());
        ArrayList<Stimulus> stimulusListCopy = new ArrayList<>(stimulusSubsetArray);
        while (!stimulusListCopy.isEmpty() && uniqueList.size() < maxStimuli) {
            final int nextIndex = new Random().nextInt(stimulusListCopy.size());
            Stimulus stimulus = stimulusListCopy.remove(nextIndex);
            if (stimulus.getImage() != null && !stimulus.getImage().isEmpty()) {
                if (!uniqueList.containsKey(stimulus.getImage())) {
                    uniqueList.put(stimulus.getImage(), stimulus);
                }
            }
        }
        final ArrayList<Stimulus> inputList = new ArrayList<>(uniqueList.values());
        final ArrayList<Stimulus> returnList = new ArrayList<>();
        String groupResponseOptions = null;
        while (!inputList.isEmpty()) {
            final int nextIndex = new Random().nextInt(inputList.size());
            Stimulus stimulus = inputList.remove(nextIndex);
            if (groupResponseOptions == null) {
                groupResponseOptions = "";
            } else {
                groupResponseOptions += ",";
            }
            groupResponseOptions += stimulus.getUniqueId();
            returnList.add(stimulus);
        }
        if (groupParticipantService != null) {
            groupParticipantService.setResponseStimulusOptions(groupResponseOptions);
        }
        return returnList;
    }
}
