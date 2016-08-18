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

import java.util.Arrays;
import java.util.HashSet;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus.Tag;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimulusProviderTest {

    /**
     * Test of getSubset method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_0args() {
        System.out.println("getSubset_0args");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(6, "", -1, Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
        final String seenString = "";
        final int expectedStimuliCount = 36;
        final int expectedSpeakerCount = 12;
        final int expectedKijfCount = 6;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    /**
     * Test of getSubset method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_0args_3() {
        System.out.println("getSubset_0args_3");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(3, "", -1, Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
        final String seenString = "";
        final int expectedStimuliCount = 18;
        final Integer expectedSpeakerCount = null;
        final int expectedKijfCount = 3;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    /**
     * Test of getSubset method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_0args_3_seen() {
        System.out.println("getSubset_0args_3_seen");
        StimulusProvider instance = new StimulusProvider();
        final String seenString = getSeenList(instance);
        instance.getSubset(3, seenString, -1, Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
        final int expectedStimuliCount = 13;
        final Integer expectedSpeakerCount = null;
        final int expectedKijfCount = 1;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    private void checkStimulus(StimulusProvider instance, final int expectedStimuliCount, final Integer expectedSpeakerCount, final int expectedKijfCount, final String seenString) {
        int speakerCount = 0;
        int wordCount = 0;
        assertEquals(expectedStimuliCount, instance.getTotalStimuli());
        while (instance.hasNextStimulus()) {
            instance.nextStimulus();
            final Stimulus nextStimulus = instance.getCurrentStimulus();
            assertFalse(seenString.contains(nextStimulus.getUniqueId()));
            if (nextStimulus.getTags().contains(Tag.tag_centipedes)) {
                speakerCount++;
            }
            if (nextStimulus.getTags().contains(Tag.tag_Festival)) {
                wordCount++;
            }
//            System.out.println("nextStimulus: " + nextStimulus.getSpeaker() + ", " + nextStimulus.getWord() + ", " + nextStimulus.getSpeakerSimilarity());
        }
        if (expectedSpeakerCount != null) {
            assertEquals(expectedSpeakerCount.intValue(), speakerCount);
        }
        assertEquals(expectedKijfCount, wordCount);
    }

    /**
     * Test of getSubset method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_StimulusSimilarity() {
        System.out.println("getSubset");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(GeneratedStimulus.Tag.tag_centipedes, 6, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), "", -1);
        final String seenString = "";
        final int expectedStimuliCount = 36;
        final int expectedSpeakerCount = 36;
        final int expectedKijfCount = 6;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    /**
     * Test of getSubset 3 method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset3_StimulusSimilarity() {
        System.out.println("getSubset 3");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(GeneratedStimulus.Tag.tag_centipedes, 3, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), "", -1);
        final String seenString = "";
        final int expectedStimuliCount = 18;
        final int expectedSpeakerCount = 18;
        final int expectedKijfCount = 3;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    /**
     * Test of getSubset 3 method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset3Seen_StimulusSimilarity() {
        System.out.println("getSubset 3 seen");
        StimulusProvider instance = new StimulusProvider();
        String seenString = getSeenList(instance);
        instance.getSubset(GeneratedStimulus.Tag.tag_centipedes, 3, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), seenString, -1);
        final int expectedStimuliCount = 13;
        final int expectedSpeakerCount = 13;
        final int expectedKijfCount = 1;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    private String getSeenList(StimulusProvider instance) {
        String seenString = "";
        final String seenLabelString = "centipedes_Rocket_4," + "centipedes_Rocket_5," + "centipedes_Rocket_3," + "centipedes_Festival_4," + "centipedes_Festival_5";
        instance.getSubset(6, "", -1, Arrays.asList(new Tag[]{Tag.tag_centipedes}), Arrays.asList(new Tag[]{Tag.tag_Rocket, Tag.tag_Festival}), 32);
        while (instance.hasNextStimulus()) {
            instance.nextStimulus();
            Stimulus stimulus = instance.getCurrentStimulus();
            if (seenLabelString.contains(stimulus.getLabel())) {
                seenString = seenString + "," + stimulus.getUniqueId();
            }
        }
        return seenString;
    }

    /**
     * Test of getSubset for HR stimuli, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_HRStimuli01() {
        // unit test for HR stimuli to check that each stimuli is shown 3 times and all stimuli are represented
        System.out.println("getSubset HR stimuli");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Arrays.asList(new Tag[]{Tag.tag_HRPretest01}), 1000, true, 3, 20, "", -1);
        final int expectedStimuliCount = 196 * 3;
        assertEquals(expectedStimuliCount, instance.getTotalStimuli());
    }

    /**
     * Test of uniqueStimuli in getSubset for HR stimuli, of class
     * StimulusProvider.
     */
    @Test
    public void testGetSubset_UniqueStimuli() {
        // unit test for HR stimuli to check that each stimuli is shown 3 times and all stimuli are represented
        System.out.println("getSubset uniqueStimuli");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Arrays.asList(new Tag[]{Tag.tag_HRPretest01}), 1000, true, 2, 20, "", -1);
        final int expectedStimuliCount = 196;
        HashSet<Stimulus> stimulusSet = new HashSet();
        while (instance.hasNextStimulus()) {
            instance.nextStimulus();
            stimulusSet.add(instance.getCurrentStimulus());
        }
        assertEquals(expectedStimuliCount, stimulusSet.size());
    }

    /**
     * Test of getSubset for HR stimuli, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_HRStimuli02() {
        // unit test for HR stimuli to check that each stimuli is shown 3 times and all stimuli are represented
        System.out.println("getSubset HR stimuli");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Arrays.asList(new Tag[]{Tag.tag_HRPretest02}), 1000, true, 3, 20, "", -1);
        final int expectedStimuliCount = 256 * 3;
        assertEquals(expectedStimuliCount, instance.getTotalStimuli());
    }

    /**
     * Test of getSubset for reload events.
     */
    @Test
    public void testGetSubset_Reload() {
        System.out.println("getSubset Reload");
        StimulusProvider instance1 = new StimulusProvider();
        instance1.getSubset(Arrays.asList(new Tag[]{Tag.tag_HRPretest02}), 1000, true, 3, 20, "", -1);
        int seenStimuliCounter = 0;
        for (int counter = 0; counter < 25; counter++) {
            instance1.nextStimulus();
            seenStimuliCounter++;
        }
        final int currentStimulusIndex = instance1.getCurrentStimulusIndex();
        final String loadedStimulusString = instance1.getLoadedStimulusString();
        System.out.println("loadedStimulusString: " + loadedStimulusString);
        System.out.println("currentStimulusIndex: " + currentStimulusIndex);
        StimulusProvider instance2 = new StimulusProvider();
        instance2.getSubset(Arrays.asList(new Tag[]{Tag.tag_HRPretest02}), 1000, true, 3, 20, loadedStimulusString, currentStimulusIndex);
        while (instance2.hasNextStimulus()) {
            instance2.nextStimulus();
            seenStimuliCounter++;
        }
        final int expectedStimuliCount = 256 * 3;
        assertEquals(expectedStimuliCount, seenStimuliCounter);
        assertEquals(expectedStimuliCount, instance2.getTotalStimuli());
    }
}
