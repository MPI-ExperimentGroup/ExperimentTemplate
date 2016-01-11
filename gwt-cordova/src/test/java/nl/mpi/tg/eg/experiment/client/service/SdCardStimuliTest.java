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
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
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
        String[] testData = new String[]{"/MPI_STIMULI/bodies/",
            "/MPI_STIMULI/bowped/",
            "/MPI_STIMULI/cutbreak/",
            "/MPI_STIMULI/grammar/",
            "/MPI_STIMULI/reciprocal/",
            "/MPI_STIMULI/bodies/01.png",
            "/MPI_STIMULI/bodies/02.png",
            "/MPI_STIMULI/bodies/03.png",
            "/MPI_STIMULI/bodies/04.png",
            "/MPI_STIMULI/bodies/05.png",
            "/MPI_STIMULI/bodies/06.png",
            "/MPI_STIMULI/bodies/07.png",
            "/MPI_STIMULI/bodies/08.png",
            "/MPI_STIMULI/bodies/09.png",
            "/MPI_STIMULI/bodies/10.png",
            "/MPI_STIMULI/bodies/11.png",
            "/MPI_STIMULI/bodies/12.png",
            "/MPI_STIMULI/bodies/13.png",
            "/MPI_STIMULI/bodies/14.png",
            "/MPI_STIMULI/bodies/15.png",
            "/MPI_STIMULI/grammar/01-boykicksballtogirl.mp4",
            "/MPI_STIMULI/grammar/02-boykicksballtogirls.mp4",
            "/MPI_STIMULI/grammar/03-girlkicksballtoboy.mp4",
            "/MPI_STIMULI/grammar/04-boykicksballstogirl.mp4",
            "/MPI_STIMULI/grammar/05-ballrolls.mp4",
            "/MPI_STIMULI/grammar/06-ballrollstodog.mp4",
            "/MPI_STIMULI/grammar/07-dogrunsaway.mp4",
            "/MPI_STIMULI/grammar/08-girlchasesball.mp4",
            "/MPI_STIMULI/grammar/09-girlfalls.mp4",
            "/MPI_STIMULI/grammar/10-ballrollstotree.mp4",
            "/MPI_STIMULI/grammar/11-treefallsondog.mp4",
            "/MPI_STIMULI/grammar/12-girlboylifttree.mp4",
            "/MPI_STIMULI/grammar/13-girlpushesboy.mp4",
            "/MPI_STIMULI/grammar/14-boysitsontree.mp4"};
        final ArrayList<Stimulus> stimuliList = new ArrayList<>();

        SdCardStimuli instance = new SdCardStimuli(stimuliList, new ArrayList<GeneratedStimulus.Tag>());
        for (String stimulusPath : testData) {
            instance.insertStimulus(stimulusPath);
        }
        for (Stimulus stimulus : stimuliList) {
            System.out.println(stimulus.getImage());
            System.out.println(stimulus.getLabel());
            System.out.println(stimulus.getMp3());
            System.out.println(stimulus.getMp4());
            System.out.println(stimulus.getOgg());
//            System.out.println(stimulus.getTags());
            System.out.println(stimulus.getUniqueId());
            System.out.println(stimulus.isImage());
            System.out.println(stimulus.isMp3());
            System.out.println(stimulus.isMp4());
            System.out.println(stimulus.isOgg());
        }
        // todo: verify that the generated stimulus are correct
    }
}
