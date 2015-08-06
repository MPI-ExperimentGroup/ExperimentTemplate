/*
 * Copyright (C) 2015 
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

import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Jun 29, 2015 11:51:54 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractPreloadStimulusPresenter extends AbstractStimulusPresenter implements Presenter {

    private final StimulusProvider stimulusProvider = new StimulusProvider();

    public AbstractPreloadStimulusPresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults) {
        super(widgetTag, audioPlayer, submissionService, userResults);
    }

    private void preloadAllStimuli(final AppEventListner appEventListner, final HorizontalPanel progressBar, final TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).updateProgressBar(progressBar, 0, stimulusProvider.getTotalStimuli() - stimulusProvider.getRemainingStimuli(), stimulusProvider.getTotalStimuli());
        if (stimulusProvider.hasNextStimulus()) {
            ((TimedStimulusView) simpleView).preloadImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + stimulusProvider.getNextStimulus().getJpg()), new TimedStimulusListener() {

                @Override
                public void postLoadTimerFired() {
                    preloadAllStimuli(appEventListner, progressBar, timedStimulusListener);
                }
            });
        } else {
            timedStimulusListener.postLoadTimerFired();
        }
    }

    protected void preloadAllStimuli(final AppEventListner appEventListner, final TimedStimulusListener timedStimulusListener) {
        stimulusProvider.getSubset();
        final HorizontalPanel progressBar = ((TimedStimulusView) simpleView).addProgressBar(0, 0, stimulusProvider.getTotalStimuli());
        preloadAllStimuli(appEventListner, progressBar, timedStimulusListener);
    }
}
