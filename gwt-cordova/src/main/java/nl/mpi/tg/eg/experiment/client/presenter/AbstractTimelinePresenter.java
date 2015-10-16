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
package nl.mpi.tg.eg.experiment.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import java.util.List;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.model.AnnotationData;
import nl.mpi.tg.eg.experiment.client.model.AnnotationSet;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataFactory;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.AnnotationTimelinePanel;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;
import nl.mpi.tg.eg.experiment.client.view.VideoPanel;

/**
 * @since Oct 2, 2015 4:22:12 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractTimelinePresenter extends AbstractPresenter implements Presenter {

    DataFactory dataFactory = GWT.create(DataFactory.class);
    private final StimulusProvider stimulusProvider = new StimulusProvider();
    private AnnotationTimelinePanel annotationTimelinePanel;
    private VideoPanel videoPanel;
    private final LocalStorage localStorage;
    private final UserResults userResults;
//    private String storageTag = "temp_tag";

    public AbstractTimelinePresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults, LocalStorage localStorage) {
        super(widgetTag, new TimedStimulusView(audioPlayer));
        this.localStorage = localStorage;
        this.userResults = userResults;
    }

    @Override 
    public void setAnnotationTimelinePanel(final String storageTag, String width, String poster, String mp4, String ogg, String webm, List<Stimulus.Tag> tags, int maxStimuli, int columnCount, String imageWidth) {
        videoPanel = new VideoPanel(width, poster, mp4, ogg, webm);
        stimulusProvider.getSubset(tags, maxStimuli);
        this.annotationTimelinePanel = new AnnotationTimelinePanel(dataFactory, videoPanel, stimulusProvider, columnCount, imageWidth);
        ((ComplexView) simpleView).addWidget(annotationTimelinePanel);
        ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "save annotations";
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                final Set<AnnotationData> annotations = annotationTimelinePanel.getAnnotations();
                final AutoBean<AnnotationSet> annotationSetBean = dataFactory.annotationSet();
                final AnnotationSet annotationSet = annotationSetBean.as();
                annotationSet.setAnnotations(annotations);
                AutoBean<AnnotationSet> bean = AutoBeanUtils.getAutoBean(annotationSet);
                final String payload = AutoBeanCodex.encode(bean).getPayload();

                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), storageTag, payload);
            }
        });
        final AnnotationSet savedAnnotationsString = loadAnnotations(storageTag);
        annotationTimelinePanel.setAnnotations(savedAnnotationsString);
    }

    protected AnnotationSet loadAnnotations(String storageTag) {
        final String storedDataValue = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), storageTag);
        if (storedDataValue != null && !storedDataValue.isEmpty()) {
            AutoBean<AnnotationSet> annotationSetBean = AutoBeanCodex.decode(dataFactory, AnnotationSet.class, storedDataValue);
            return annotationSetBean.as();
        } else {
            return dataFactory.annotationSet().as();
        }
    }

    @Override
    public void fireResizeEvent() {
        super.fireResizeEvent();
        annotationTimelinePanel.ResizeTimeline();
    }

}
