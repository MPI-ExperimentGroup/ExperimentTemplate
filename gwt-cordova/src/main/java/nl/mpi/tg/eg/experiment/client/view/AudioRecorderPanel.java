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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.service.AudioRecorder;

/**
 * @since Aug 31, 2015 3:45:48 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AudioRecorderPanel extends VerticalPanel {

    private final AudioRecorder audioRecorder;

    public AudioRecorderPanel(final String sourceFile) {
        audioRecorder = new AudioRecorder() {

            @Override
            protected void onPlay() {
                AudioRecorderPanel.this.add(new Label("onPlay"));
            }

            @Override
            protected void onRecord() {
                AudioRecorderPanel.this.add(new Label("onPlay"));
            }

            @Override
            protected void onStop() {
                AudioRecorderPanel.this.add(new Label("onPlay"));
            }

            @Override
            protected void onError(String s) {
                AudioRecorderPanel.this.add(new Label("onPlay"));
            }

            @Override
            protected void updateCurrentPosition(String s) {
                AudioRecorderPanel.this.add(new Label("onPlay"));
            }
        };
        add(new Button("record", new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
            }
        }));
        add(new Button("play", new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                audioRecorder.playMedia(sourceFile);
            }
        }));
    }
}
