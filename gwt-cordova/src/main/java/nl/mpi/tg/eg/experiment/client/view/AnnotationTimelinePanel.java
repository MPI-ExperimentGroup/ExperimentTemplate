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

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;

/**
 * @since Sep 21, 2015 11:56:46 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AnnotationTimelinePanel extends VerticalPanel {

    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);

    public AnnotationTimelinePanel(String width, String poster, String mp4, String ogg, String webm, Stimulus.Tag tag, int maxStimuli, int columnCount, String imageWidth) {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        this.setStylePrimaryName("annotationTimelinePanel");
        final VideoPanel videoPanel = new VideoPanel(width, poster, mp4, ogg, webm);
        horizontalPanel.add(videoPanel);
        final VerticalPanel verticalPanel = new VerticalPanel();
        final StimulusGrid stimulusGrid = new StimulusGrid();
        stimulusProvider.getSubset(tag, maxStimuli, "");
        int stimulusCounter = 0;
        while (stimulusProvider.hasNextStimulus()) {
            stimulusProvider.getNextStimulus();
            Stimulus stimulus = stimulusProvider.getCurrentStimulus();
            stimulusGrid.addImageItem(new PresenterEventListner() {

                @Override
                public String getLabel() {
                    return null;
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void eventFired(ButtonBase button) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }, UriUtils.fromString(serviceLocations.staticFilesUrl() + stimulus.getImage()), stimulusCounter / columnCount, 1 + stimulusCounter++ % columnCount, imageWidth);
        }
        horizontalPanel.add(stimulusGrid);
        horizontalPanel.add(verticalPanel);
        this.add(horizontalPanel);
        final Label timelineCursor = new Label();
        final AbsolutePanel absolutePanel = new AbsolutePanel();

        absolutePanel.setStylePrimaryName("annotationTimelineTier");
        timelineCursor.setStylePrimaryName("annotationTimelineCursor");
        absolutePanel.add(timelineCursor);
        final Label label1 = new Label("two");
        label1.setWidth("10%");
        absolutePanel.add(label1);
        final Label label2 = new Label("three");
        label2.setWidth("70%");
        absolutePanel.add(label2);
        this.add(absolutePanel);
        final Label labelticker = new Label("test output");
        horizontalPanel.add(labelticker);
        Timer timer = new Timer() {
            private int couter = 0;

            @Override
            public void run() {
                labelticker.setText("" + videoPanel.getCurrentTime());
                absolutePanel.setWidgetPosition(timelineCursor, (int) ((absolutePanel.getOffsetWidth() - 1) * (videoPanel.getCurrentTime() / videoPanel.getDurationTime())), absolutePanel.getOffsetHeight() - timelineCursor.getOffsetHeight());
            }
        };
        timer.scheduleRepeating(10);
    }
}
