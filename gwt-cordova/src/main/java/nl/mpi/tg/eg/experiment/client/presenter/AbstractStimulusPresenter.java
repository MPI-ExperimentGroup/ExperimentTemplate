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

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.service.ServiceLocations;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.SimpleView;

/**
 * @since Jun 23, 2015 11:36:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractStimulusPresenter extends AbstractPresenter implements Presenter {

    private final StimulusProvider stimulusProvider = new StimulusProvider();
    protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    final Stimulus currentStimulus;

    protected interface PostLoadMsCallback {

        void postLoadMsEvent();
    }

    public AbstractStimulusPresenter(RootLayoutPanel widgetTag, SimpleView simpleView) {
        super(widgetTag, simpleView);
        currentStimulus = stimulusProvider.getNext();
    }

    protected void addStimulusImage(String image, int width, long postLoadMs, PostLoadMsCallback callback) {
        ((ComplexView) simpleView).addImage(UriUtils.fromString(serviceLocations.staticFilesUrl() + image), "", width);
    }

    protected void playStimulusAudio(String ogg, String mp3, long postLoadMs, PostLoadMsCallback callback) {
//         AudioElement getAudioElement()
    }
}
