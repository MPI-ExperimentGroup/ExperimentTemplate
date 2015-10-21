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
package nl.mpi.tg.eg.experiment.client.view;

import com.google.gwt.user.client.ui.FlexTable;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.model.AnnotationData;
import nl.mpi.tg.eg.experiment.client.model.AnnotationSet;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataFactory;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;

/**
 * @since Oct 21, 2015 1:25:34 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AnnotationTimelineView extends TimedStimulusView {

    private AnnotationTimelinePanel annotationTimelinePanel;
    private VideoPanel videoPanel;
    final FlexTable flexTable = new FlexTable();

    public AnnotationTimelineView(AudioPlayer audioPlayer) {
        super(audioPlayer);
        flexTable.setStylePrimaryName("annotationTimelinePanel");
        outerPanel.add(flexTable);
    }

    public void setAnnotationTimelinePanel(AnnotationTimelinePanel annotationTimelinePanel) {
        this.annotationTimelinePanel = annotationTimelinePanel;
        flexTable.getFlexCellFormatter().setColSpan(1, 0, 2);
        flexTable.setWidget(1, 0, annotationTimelinePanel);
    }

    public void setVideoPanel(VideoPanel videoPanel) {
        this.videoPanel = videoPanel;
        flexTable.setWidget(0, 0, videoPanel);
    }

    public Set<AnnotationData> getAnnotations() {
        return annotationTimelinePanel.getAnnotations();
    }

    public void setStimuli(final DataFactory dataFactory, final AnnotationSet savedAnnotations, StimulusProvider stimulusProvider, int columnCount, String imageWidth, int maxButtons) {
        final Set<Stimulus> stimuliSet = annotationTimelinePanel.setAnnotations(savedAnnotations, videoPanel);
        while (stimuliSet.size() < maxButtons && stimulusProvider.hasNextStimulus()) {
            stimulusProvider.getNextStimulus();
            final Stimulus stimulus = stimulusProvider.getCurrentStimulus();
            stimuliSet.add(stimulus);
        }

        final StimulusGrid stimulusGrid = new StimulusGrid();
        int stimulusCounter = 0;
        annotationTimelinePanel.setTierCount(stimuliSet.size());
        for (Stimulus stimulus : stimuliSet) {
            annotationTimelinePanel.addStimulusButton(stimulus, stimulusGrid, videoPanel, dataFactory, stimulusCounter, columnCount, imageWidth);
            stimulusCounter++;
        }
        flexTable.setWidget(0, 1, stimulusGrid);
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        videoPanel.setWidth(width / 2 + "px");
        annotationTimelinePanel.resizeTimeline(videoPanel.getCurrentTime(), videoPanel.getDurationTime());
        super.parentResized(height, width, units);
    }
}
