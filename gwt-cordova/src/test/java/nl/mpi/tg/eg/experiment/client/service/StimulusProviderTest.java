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
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus.Tag;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

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
        instance.getSubset(6, "", Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
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
        instance.getSubset(3, "", Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
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
        instance.getSubset(3, seenString, Arrays.asList(new Tag[]{Tag.tag_centipedes, Tag.tag_scorpions, Tag.tag_termites}), Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), 2);
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
            instance.getNextStimulus();
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
        instance.getSubset(Stimulus.Tag.tag_centipedes, 6, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), "");
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
        instance.getSubset(Stimulus.Tag.tag_centipedes, 3, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), "");
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
        instance.getSubset(Stimulus.Tag.tag_centipedes, 3, Arrays.asList(new Tag[]{Tag.tag_ประเพณีบุญบั้งไฟ, Tag.tag_Rocket, Tag.tag_Festival, Tag.tag_Lao, Tag.tag_Thai, Tag.tag_ບຸນບັ້ງໄຟ}), seenString);
        final int expectedStimuliCount = 13;
        final int expectedSpeakerCount = 13;
        final int expectedKijfCount = 1;
        checkStimulus(instance, expectedStimuliCount, expectedSpeakerCount, expectedKijfCount, seenString);
    }

    private String getSeenList(StimulusProvider instance) {
        String seenString = "";
        final String seenLabelString = "centipedes_Rocket_4," + "centipedes_Rocket_5," + "centipedes_Rocket_3," + "centipedes_Festival_4," + "centipedes_Festival_5";
        instance.getSubset(6, "", Arrays.asList(new Tag[]{Tag.tag_centipedes}), Arrays.asList(new Tag[]{Tag.tag_Rocket, Tag.tag_Festival}), 32);
        while (instance.hasNextStimulus()) {
            instance.getNextStimulus();
            Stimulus stimulus = instance.getCurrentStimulus();
            if (seenLabelString.contains(stimulus.getLabel())) {
                seenString = seenString + "," + stimulus.getUniqueId();
            }
        }
        return seenString;
    }
}
