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

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.ArrayList;
import java.util.HashSet;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Jun 23, 2015 11:36:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractStimulusPresenter extends AbstractPresenter implements Presenter {

    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    private final DataSubmissionService submissionService;
    final UserResults userResults;
    final Stimulus currentStimulus;
    private final Duration duration;

    public AbstractStimulusPresenter(RootLayoutPanel widgetTag, AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults) {
        super(widgetTag, new TimedStimulusView(audioPlayer));
        currentStimulus = stimulusProvider.getNextStimulus();
        duration = new Duration();
        this.submissionService = submissionService;
        this.userResults = userResults;
    }

    protected void addStimulusImage(String image, int width, int postLoadMs, TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).addTimedImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + image), width, postLoadMs, timedStimulusListener);
        ((TimedStimulusView) simpleView).addText("addStimulusImage: " + duration.elapsedMillis() + "ms");
    }

    protected void playStimulusAudio(String ogg, String mp3, long postLoadMs, TimedStimulusListener timedStimulusListener) {
        ((TimedStimulusView) simpleView).addTimedAudio(UriUtils.fromString(serviceLocations.staticFilesUrl() + ogg), UriUtils.fromString(serviceLocations.staticFilesUrl() + mp3), postLoadMs, timedStimulusListener);
        ((TimedStimulusView) simpleView).addText("playStimulusAudio: " + duration.elapsedMillis() + "ms");
    }

    protected void showCurrentMs() {
        ((TimedStimulusView) simpleView).addText(duration.elapsedMillis() + "ms");
    }

    protected void logTimeStamp(String eventTag) {
        submissionService.submitTimeStamp(userResults.getUserData().getUserId(), eventTag, duration.elapsedMillis());
    }

    protected void showStimulusGrid(final TimedStimulusListener listener, final int columnCount, final String imageWidth, final String eventTag, final String alternativeChoice) {
        final ArrayList<ButtonBase> buttonList = new ArrayList<>();
        ((TimedStimulusView) simpleView).startGrid();
        int imageCounter = 0;        
        buttonList.add(((TimedStimulusView) simpleView).addStringItem(getEventListener(buttonList, eventTag, alternativeChoice, listener), alternativeChoice, 0, 0, imageWidth));
        HashSet<String> hashSet = new HashSet<>();
        while (stimulusProvider.hasNextStimulus()) {
            final String nextJpg = stimulusProvider.getNextStimulus().getJpg();
            if (!hashSet.contains(nextJpg)) {
                hashSet.add(nextJpg);
                buttonList.add(((TimedStimulusView) simpleView).addImageItem(getEventListener(buttonList, eventTag, nextJpg, listener), UriUtils.fromString(serviceLocations.staticFilesUrl() + nextJpg), imageCounter / columnCount, 1 + imageCounter++ % columnCount, imageWidth));
            }
        }
        ((TimedStimulusView) simpleView).endGrid();
    }

    private PresenterEventListner getEventListener(final ArrayList<ButtonBase> buttonList, final String eventTag, final String tagValue, final TimedStimulusListener listener) {
        return new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button) {
                for (ButtonBase currentButton : buttonList) {
                    currentButton.setEnabled(false);
                }
                button.addStyleName("stimulusButtonHighlight");
                submissionService.submitTagValue(userResults.getUserData().getUserId(), eventTag, tagValue, duration.elapsedMillis());
                listener.postLoadTimerFired();
            }
        };
    }
}
