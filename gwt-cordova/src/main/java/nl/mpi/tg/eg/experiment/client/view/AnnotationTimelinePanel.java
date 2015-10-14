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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.model.AnnotationData;
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
    private final VideoPanel videoPanel;
    private final AbsolutePanel absolutePanel;
    private final HashMap<Label, AnnotationData> stimulusAnnotations = new HashMap<>();
    private final HashMap<Stimulus, ButtonBase> stimulusButtons = new HashMap<>();

    public AnnotationTimelinePanel(String width, String poster, String mp4, String ogg, String webm, List<Stimulus.Tag> tags, int maxStimuli, int columnCount, String imageWidth) {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        this.setStylePrimaryName("annotationTimelinePanel");
        videoPanel = new VideoPanel(width, poster, mp4, ogg, webm);
        horizontalPanel.add(videoPanel);
        final VerticalPanel verticalPanel = new VerticalPanel();
        final StimulusGrid stimulusGrid = new StimulusGrid();
        stimulusProvider.getSubset(tags, maxStimuli);
        int stimulusCounter = 0;
        absolutePanel = new AbsolutePanel();
        final int tierHeight = 30;
        absolutePanel.setHeight(tierHeight * stimulusProvider.getTotalStimuli() + "px");
        while (stimulusProvider.hasNextStimulus()) {
            stimulusProvider.getNextStimulus();
            final Stimulus stimulus = stimulusProvider.getCurrentStimulus();
            final int topPosition = tierHeight * stimulusCounter; // absolutePanel.getOffsetHeight() / stimulusProvider.getTotalStimuli() * stimulusCounter;
            stimulusButtons.put(stimulus, stimulusGrid.addImageItem(new PresenterEventListner() {

                @Override
                public String getLabel() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                    final double clickedTime = videoPanel.getCurrentTime();
                    final AnnotationData annotationData = new AnnotationData(clickedTime, clickedTime + 5, "" + videoPanel.getCurrentTime(), stimulus);
                    final Label label1 = new Label(annotationData.getAnnotationHtml());
                    label1.setStylePrimaryName("annotationTimelineTierSegment");
                    final SingleShotEventListner tierSegmentListner = new SingleShotEventListner() {

                        @Override
                        protected void singleShotFired() {
                            videoPanel.playSegment(annotationData);
                            resetSingleShot();
                        }
                    };
                    label1.addClickHandler(tierSegmentListner);
                    label1.addTouchStartHandler(tierSegmentListner);
                    label1.addTouchMoveHandler(tierSegmentListner);
                    label1.addTouchEndHandler(tierSegmentListner);
                    label1.setWidth(getWidth(annotationData) + "px");
                    absolutePanel.add(label1, getLeftPosition(annotationData), topPosition);
                    stimulusAnnotations.put(label1, annotationData);
                    singleShotEventListner.resetSingleShot();
                }
            }, UriUtils.fromString(serviceLocations.staticFilesUrl() + stimulus.getImage()), stimulusCounter / columnCount, 1 + stimulusCounter++ % columnCount, imageWidth));
        }
        horizontalPanel.add(stimulusGrid);
        horizontalPanel.add(verticalPanel);
        this.add(horizontalPanel);
        final Label timelineCursor = new Label();

        absolutePanel.setStylePrimaryName("annotationTimelineTier");
        timelineCursor.setStylePrimaryName("annotationTimelineCursor");
        absolutePanel.add(timelineCursor);
        this.add(absolutePanel);
        final Label labelticker = new Label("test output");
        horizontalPanel.add(labelticker);
        Timer timer = new Timer() {
            private int couter = 0;

            @Override
            public void run() {
                final double currentTime = videoPanel.getCurrentTime();
                labelticker.setText("" + currentTime);
                absolutePanel.setWidgetPosition(timelineCursor, getLeftPosition(), absolutePanel.getOffsetHeight() - timelineCursor.getOffsetHeight());
                // to folling section is going to be a bit time critical, so might need some attention in the future
                ArrayList<Stimulus> intersectedStimuli = new ArrayList<>();
                for (AnnotationData annotationData : stimulusAnnotations.values()) {
                    if (annotationData.intersectsTime(currentTime)) {
                        intersectedStimuli.add(annotationData.getStimulus());
                    }
                }
                // since we dont have an included and excluded list, we instead clear all highlights then set the known highlights after that
                for (ButtonBase button : stimulusButtons.values()) {
                    button.removeStyleName("stimulusButtonHighlight");
                }
                for (Stimulus intersectedStimulus : intersectedStimuli) {
                    stimulusButtons.get(intersectedStimulus).addStyleName("stimulusButtonHighlight");
                }
            }
        };
        timer.scheduleRepeating(10);
    }

    private int getLeftPosition() {
        return getLeftPosition(videoPanel.getCurrentTime());
    }

    private int getLeftPosition(final AnnotationData annotationData) {
        return getLeftPosition(annotationData.getInTime());
    }

    private int getWidth(final AnnotationData annotationData) {
        return getLeftPosition(annotationData.getOutTime() - annotationData.getInTime());
    }

    private int getLeftPosition(final double currentTime) {
        return (int) ((absolutePanel.getOffsetWidth() - 1) * (currentTime / videoPanel.getDurationTime()));
    }
}
