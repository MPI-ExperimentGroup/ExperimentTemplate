/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @since Jan 11, 2016 2:11:44 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimuliTest {

    /**
     * Test of insertStimulus method, of class SdCardStimuli.
     */
    @Test
    public void testInsertStimulus() {
        System.out.println("insertStimulus");
        String[] testData = new String[]{//"file:///storage/emulated/0/MPI_STIMULI/bodies/",
            //            "file:///storage/emulated/0/MPI_STIMULI/bowped/",
            //            "file:///storage/emulated/0/MPI_STIMULI/cutbreak/",
            //            "file:///storage/emulated/0/MPI_STIMULI/grammar/",
            //            "file:///storage/emulated/0/MPI_STIMULI/reciprocal/",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/01.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/01.jpg",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/02.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/03.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/04.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/05.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/06.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/07.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/08.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/09.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/10.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/11.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/12.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/13.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/14.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/15.png",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/15.mp3",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/15.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/bodies/15.txt",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/01-boykicksballtogirl.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/02-boykicksballtogirls.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/03-girlkicksballtoboy.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/04-boykicksballstogirl.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/05-ballrolls.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/06-ballrollstodog.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/07-dogrunsaway.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/08-girlchasesball.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/09-girlfalls.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/10-ballrollstotree.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/11-treefallsondog.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/12-girlboylifttree.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/13-girlpushesboy.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/grammar/14-boysitsontree.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/1_pig.mp3",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/1_pig.png",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/1_rat.mp3",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/1_rat.png",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_bat.mp3",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_bat.png",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_fish.mp3",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_fish.png",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_fish_question.png",
            "file:///storage/emulated/0/MPI_STIMULI/SDCardPictureTask/2_fish%20copy.mp3"
        };
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();

        SdCardStimuli instance = new SdCardStimuli(stimuliList, new ArrayList<String[]>(), ".*_question\\....$", null, null, null, null);
        for (String stimulusPath : testData) {
            instance.insertStimulus(stimulusPath, stimulusPath.substring(stimulusPath.lastIndexOf("/") + 1));
        }
        assertEquals(42, stimuliList.size());
        for (Stimulus stimulus : stimuliList) {
            System.out.println("getImage " + stimulus.getImage());
            System.out.println("getLabel " + stimulus.getLabel());
            System.out.println("getAudio " + stimulus.getAudio());
            System.out.println("getVideo " + stimulus.getVideo());
            System.out.println("getCode " + stimulus.getCode());
//            System.out.println(stimulus.getTags());
            System.out.println("getUniqueId " + stimulus.getUniqueId());
            System.out.println("hasImage " + stimulus.hasImage());
            System.out.println("hasAudio " + stimulus.hasAudio());
            System.out.println("hasVideo " + stimulus.hasVideo());

            assertEquals(stimulus.hasAudio(), stimulus.getAudio() != null);
            assertEquals(stimulus.hasVideo(), stimulus.getVideo() != null);
            assertEquals(stimulus.hasImage(), stimulus.getImage() != null);

            assertEquals(true, (stimulus.hasAudio()) ? stimulus.getAudio().startsWith("file:") : true);
            assertEquals(false, (stimulus.hasAudio()) ? stimulus.getAudio().contains(".") : false);
            assertEquals(true, (stimulus.hasImage()) ? stimulus.getImage().startsWith("file:") : true);
            assertEquals(true, (stimulus.hasImage()) ? stimulus.getImage().contains(".") : true);
            assertEquals(true, (stimulus.hasVideo()) ? stimulus.getVideo().startsWith("file:") : true);
            assertEquals(false, (stimulus.hasVideo()) ? stimulus.getVideo().contains(".") : false);
        }
//        assertEquals(30, stimuliList.size());
    }

    /**
     * Test of insertStimulus method with the code regex, of class
     * SdCardStimuli.
     */
    @Test
    public void testInsertCodeStimulus() {
        System.out.println("insertCodeStimulus");
        String[] testData = new String[]{
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_AT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_BT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_AF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_CF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_AF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_AT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_BF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_CT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_CF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_BT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_CT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_BF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_AF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2_AT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_BF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_BT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_AF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_CF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_CT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_BT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V2.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V4_AT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_BF.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3_CT.ogg",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V3.mp4",
            "file:///storage/emulated/0/MPI_STIMULI/NADL/V1_CF.ogg"
        };
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();
        final String matchingRegex = ".*F.ogg$";
        final String excludeRegex = ".*.mp4$";
        final String replacementRegex = "^(.*)F.ogg$";
        SdCardStimuli instance = new SdCardStimuli(stimuliList, new ArrayList<String[]>(), excludeRegex, matchingRegex, replacementRegex, null, null);
        for (String stimulusPath : testData) {
            instance.insertStimulus(stimulusPath, stimulusPath.substring(stimulusPath.lastIndexOf("/") + 1));
        }
        assertEquals(12, stimuliList.size());
        for (Stimulus stimulus : stimuliList) {
            System.out.println("getImage " + stimulus.getImage());
            System.out.println("getLabel " + stimulus.getLabel());
            System.out.println("getAudio " + stimulus.getAudio());
            System.out.println("getVideo " + stimulus.getVideo());
            System.out.println("getCode " + stimulus.getCode());
            assertEquals(stimulus.getCode(), 48, stimulus.getCode().length());
//            System.out.println(stimulus.getTags());
            System.out.println("getUniqueId " + stimulus.getUniqueId());
            System.out.println("is image " + stimulus.hasImage());
            System.out.println("hasImage " + stimulus.hasAudio());
            System.out.println("hasVideo " + stimulus.hasVideo());

            assertEquals(stimulus.hasAudio(), stimulus.getAudio() != null);
            assertEquals(stimulus.hasVideo(), stimulus.getVideo() != null);
            assertEquals(stimulus.hasImage(), stimulus.getImage() != null);

            assertEquals(true, (stimulus.hasAudio()) ? stimulus.getAudio().startsWith("file:") : true);
            assertEquals(false, (stimulus.hasAudio()) ? stimulus.getAudio().contains(".") : false);
            assertEquals(true, (stimulus.hasImage()) ? stimulus.getImage().startsWith("file:") : true);
            assertEquals(true, (stimulus.hasImage()) ? stimulus.getImage().contains(".") : true);
            assertEquals(true, (stimulus.hasVideo()) ? stimulus.getVideo().startsWith("file:") : true);
            assertEquals(false, (stimulus.hasVideo()) ? stimulus.getVideo().contains(".") : false);
        }
//        assertEquals(30, stimuliList.size());
    }
}
