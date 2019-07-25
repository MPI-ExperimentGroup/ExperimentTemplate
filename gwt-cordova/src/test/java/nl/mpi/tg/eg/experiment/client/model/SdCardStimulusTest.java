/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 24 July 2019 13:26 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimulusTest {

    public SdCardStimulusTest() {
    }

    /**
     * Test of getTags method, of class SdCardStimulus.
     */
    @Test
    public void testGetTags() {
        System.out.println("getTags");
        SdCardStimulus instance = new SdCardStimulus("uniqueId", "MPI_STIMULI/videos_21/first_x_v", "label", "code", 0, true, true, "stimulusImagePath");
        List<String> result = instance.getTags();
        assertEquals(7, result.size());
        assertEquals("MPI", result.get(0));
        assertEquals("STIMULI", result.get(1));
        assertEquals("videos", result.get(2));
        assertEquals("21", result.get(3));
        assertEquals("first", result.get(4));
        assertEquals("x", result.get(5));
        assertEquals("v", result.get(6));
//        assertEquals("mp4", result.get(7));
    }
}
