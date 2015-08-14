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

import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus.Speaker;
import static org.junit.Assert.assertEquals;
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
        System.out.println("getSubset");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(6, "");
        checkStimulus(instance, 12, 6);
    }

    private void checkStimulus(StimulusProvider instance, final int expectedSpeakerCount, final int expectedKijfCount) {
        int speakerCount = 0;
        int wordCount = 0;
        while (instance.hasNextStimulus()) {
            final Stimulus nextStimulus = instance.getNextStimulus();
            if (nextStimulus.getSpeaker().equals(Speaker.hielke)) {
                speakerCount++;
            }
            if (nextStimulus.getWord().equals("kijf")) {
                wordCount++;
            }
            System.out.println("nextStimulus: " + nextStimulus.getSpeaker() + ", " + nextStimulus.getWord() + ", " + nextStimulus.getSpeakerSimilarity());
        }
        assertEquals(expectedSpeakerCount, speakerCount);
        assertEquals(expectedKijfCount, wordCount);
    }

    /**
     * Test of getSubset method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset_StimulusSimilarity() {
        System.out.println("getSubset");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Stimulus.Similarity.diff, 6, "");
        checkStimulus(instance, 36, 6);
    }

    /**
     * Test of getSubset 3 method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset3_StimulusSimilarity() {
        System.out.println("getSubset 3");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Stimulus.Similarity.diff, 3, "");
        checkStimulus(instance, 18, 3);
    }

    /**
     * Test of getSubset 3 method, of class StimulusProvider.
     */
    @Test
    public void testGetSubset3Seen_StimulusSimilarity() {
        System.out.println("getSubset 3 seen");
        StimulusProvider instance = new StimulusProvider();
        instance.getSubset(Stimulus.Similarity.diff, 3, "hielke_grijp4,hielke_grijp5,hielke_grijp6,hielke_kijf4,hielke_kijf5,hielke_kijf6");
        checkStimulus(instance, 18, 3);
    }
}
