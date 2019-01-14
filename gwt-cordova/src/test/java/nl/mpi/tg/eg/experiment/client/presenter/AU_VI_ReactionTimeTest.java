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
package nl.mpi.tg.eg.experiment.client.presenter;

import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.listener.CurrentStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.presenter.TestViAuStimulus.ViAuStimulus;
import nl.mpi.tg.eg.experiment.client.presenter.TestViAuStimulus.ViAuStimulusProvider;
import nl.mpi.tg.eg.frinex.common.StimuliProvider;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.frinex.common.model.StimulusSelector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @since January 4, 2019 8:24 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AU_VI_ReactionTimeTest {

    public AU_VI_ReactionTimeTest() {
    }

    /**
     * Test of loadStimulus method, of class AbstractStimulusPresenter.
     */
    @Test
    @Ignore
    public void testLoadStimulus() {
        System.out.println("loadStimulus");
        final StimuliProvider stimulusProvider = new nl.mpi.tg.eg.experiment.client.service.StimulusProvider(
                ViAuStimulusProvider.values);
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setrandomise("true");
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setrepeatRandomWindow("0");
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setmaxStimuli("1000");
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setrepeatCount("1");
        ((nl.mpi.tg.eg.experiment.client.service.StimulusProvider) stimulusProvider).setadjacencyThreshold("-1");

        AU_VI_ReactionTime instance = new AU_VI_ReactionTime();
        instance.loadStimulus("loadTargetPicture",
                new StimulusSelector[]{new StimulusSelector("PracticeChoice", ViAuStimulus.Tag.tag_PracticeChoice)},
                new StimulusSelector[]{}, null, null,
                stimulusProvider,
                new CurrentStimulusListener() {

            @Override
            public void postLoadTimerFired(final StimuliProvider stimulusProvider, final Stimulus currentStimulus) {
            }
        },
                new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
            }
        });
        assertEquals(16, stimulusProvider.getTotalStimuli());
        fail("The test case is a prototype.");
    }

//    public class AU_VI_ReactionTime extends AbstractStimulusPresenter {
//
//        public AU_VI_ReactionTime() {
//            super(null, null, null, null, null);
//        }
//
//        @Override
//        protected String getTitle() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        protected String getSelfTag() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        protected void setContent(AppEventListner appEventListner) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
    public class AU_VI_ReactionTime {

        protected void loadStimulus(String eventTag, StimulusSelector[] selectionTags, StimulusSelector[] randomTags, MetadataField stimulusAllocationField, String consumedTagsGroupName, StimuliProvider stimulusProvider, CurrentStimulusListener hasMoreStimulusListener, TimedStimulusListener endOfStimulusListener) {
            final String storedStimulusList = "";
            int seenStimulusIndex = 0;
            final List<Stimulus.Tag> allocatedTags = new ArrayList<>();
            for (StimulusSelector selector : selectionTags) {
                allocatedTags.add(selector.getTag());
            }
            stimulusProvider.getSubset(allocatedTags, storedStimulusList, seenStimulusIndex);
        }
    }
}
