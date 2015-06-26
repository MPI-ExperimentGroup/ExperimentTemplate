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
package nl.mpi.tg.eg.experiment.client.view;

import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;

/**
 * @since Jun 26, 2015 10:24:34 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedStimulusView extends ComplexView {

    private final AudioPlayer audioPlayer;

    public TimedStimulusView(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public void addTimedImage(SafeUri imagePath, int percentWidth, final int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        final Image image = new Image(imagePath);
        image.setWidth(percentWidth + "%");
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                Timer timer = new Timer() {
                    public void run() {
                        timedStimulusListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadMs);
            }
        });
        outerPanel.add(image);
    }

    public void addTimedAudio(SafeUri oggPath, SafeUri mp3Path, long postLoadMs, TimedStimulusListener timedStimulusListener) {
        audioPlayer.playSample(oggPath, mp3Path);
    }
}
