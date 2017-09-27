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
package nl.mpi.tg.eg.experiment.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.frinex.common.model.StimulusSelector;

/**
 * @since Sep 27, 2017 1:42:59 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SimuliValidationRunner {

    public static void main(String[] args) {
        Set<String> calculatedStimuliSet = new HashSet<>();
        Set<String> stimuliSet = new HashSet<>();
        HashMap<String, Integer> transionTable = new HashMap<>();
        String eventTag = "R0-4";
        final StimulusSelector[] selectionTags = new StimulusSelector[]{new StimulusSelector("v1", GeneratedStimulus.Tag.tag_version1zero)};
        final StimulusSelector[] randomTags = new StimulusSelector[]{};

//final MetadataField stimulusAllocationField= metadataFieldProvider.stimuliMetadataField;
        final int maxStimulusCount = 23;
        final Integer minStimuliPerTag = 1;
        final Integer maxStimuliPerTag = 100;
        final boolean randomise = true;
        int repeatCount = 1;
        final int repeatRandomWindow = 6;
        final int adjacencyThreshold = 3;
        final String storedStimulusList = "";
        final List<Stimulus.Tag> allocatedTags = new ArrayList<>();
//        final List<StimulusSelector> allocatedTags = new ArrayList<>(Arrays.asList(selectionTags));
        for (StimulusSelector selector : selectionTags) {
            allocatedTags.add(selector.getTag());
        }
        final int cyclesToRun = 1000000;
        for (int sampleCount = 0; sampleCount < cyclesToRun; sampleCount++) {
            final StimulusProvider stimulusProvider = new StimulusProvider();
            stimulusProvider.getSubset(allocatedTags, maxStimulusCount, randomise, repeatCount, repeatRandomWindow, adjacencyThreshold, storedStimulusList, 0);
            final String loadedStimulusString = stimulusProvider.getLoadedStimulusString();
//            System.out.println(loadedStimulusString);
            calculatedStimuliSet.add(loadedStimulusString);
            System.out.println("sampleCount: " + (sampleCount + 1) + " uniqueCount: " + calculatedStimuliSet.size());
            String currentStimulus = null;
            String nextStimulus = stimulusProvider.getCurrentStimulus().getImage() + "_" + stimulusProvider.getCurrentStimulus().getCode();
            stimuliSet.add(nextStimulus);
            String currentPair = currentStimulus + ":" + nextStimulus;
            transionTable.put(currentPair, (transionTable.containsKey(currentPair)) ? transionTable.get(currentPair) + 1 : 1);
//            System.out.println(currentPair);
            while (stimulusProvider.hasNextStimulus(1)) {
                currentStimulus = nextStimulus;
                stimulusProvider.nextStimulus(1);
                nextStimulus = stimulusProvider.getCurrentStimulus().getImage() + "_" + stimulusProvider.getCurrentStimulus().getCode();
                stimuliSet.add(nextStimulus);
                currentPair = currentStimulus + ":" + nextStimulus;
                transionTable.put(currentPair, (transionTable.containsKey(currentPair)) ? transionTable.get(currentPair) + 1 : 1);
//                System.out.println(currentPair);
            }
            currentPair = nextStimulus + ":" + null;
            transionTable.put(currentPair, (transionTable.containsKey(currentPair)) ? transionTable.get(currentPair) + 1 : 1);
//            System.out.println(currentPair);
        }
        int minTransition = cyclesToRun;
        int maxTransition = 0;
        int totalTransition = 0;
        for (String currentPair : transionTable.keySet()) {
            final Integer transionCount = transionTable.get(currentPair);
            totalTransition += transionCount;
            minTransition = (minTransition > transionCount) ? transionCount : minTransition;
            maxTransition = (maxTransition < transionCount) ? transionCount : maxTransition;
            System.out.println(currentPair + " : " + transionCount);
        }
        for (String uniqueStimulus : stimuliSet) {
            System.out.println(uniqueStimulus);
        }
        System.out.println("transionTableSize: " + transionTable.size());
        System.out.println("minTransition: " + minTransition);
        System.out.println("maxTransition: " + maxTransition);
        System.out.println("totalTransition: " + totalTransition);
        System.out.println("expectedTransition: " + (totalTransition / transionTable.size()));
        System.out.println("uniqueStimuliCount: " + stimuliSet.size());
        System.out.println("cyclesRun: " + (cyclesToRun) + " uniqueCount: " + calculatedStimuliSet.size());
    }
}
