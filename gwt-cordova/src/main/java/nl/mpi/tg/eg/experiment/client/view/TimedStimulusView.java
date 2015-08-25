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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import nl.mpi.tg.eg.experiment.client.listener.AudioEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;

/**
 * @since Jun 26, 2015 10:24:34 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedStimulusView extends ComplexView {

    private final AudioPlayer audioPlayer;
    private FlexTable flexTable = null;

    public TimedStimulusView(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public void startGrid() {
        flexTable = new FlexTable();
        flexTable.setStylePrimaryName("menuTable");
        outerPanel.setStylePrimaryName("menuOuter");
        outerPanel.add(flexTable);
    }

    public void endGrid() {
        flexTable = null;
    }

    public ButtonBase addStringItem(final PresenterEventListner menuItemListerner, final String labelString, final int rowIndex, final int columnIndex, final String widthString) {
        final Button pushButton = new Button(labelString);
        return addButton(menuItemListerner, pushButton, rowIndex, columnIndex, widthString);
    }

    public ButtonBase addImageItem(final PresenterEventListner menuItemListerner, final SafeUri imagePath, final int rowIndex, final int columnIndex, final String widthString) {
        final Image image = new Image(imagePath);
        image.setHeight(widthString);
        final Button imageButton = new Button();
        imageButton.getElement().appendChild(image.getElement());
        imageButton.addStyleName("stimulusImageButton");
        return addButton(menuItemListerner, imageButton, rowIndex, columnIndex, widthString);
    }

    private ButtonBase addButton(final PresenterEventListner menuItemListerner, final ButtonBase pushButton, final int rowIndex, final int columnIndex, final String widthString) {

        pushButton.addStyleName("stimulusButton");
        pushButton.setEnabled(true);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (pushButton.isEnabled()) {
                    menuItemListerner.eventFired(pushButton);
                }
            }
        };
        pushButton.addClickHandler(singleShotEventListner);
        pushButton.addTouchStartHandler(singleShotEventListner);
        pushButton.addTouchMoveHandler(singleShotEventListner);
        pushButton.addTouchEndHandler(singleShotEventListner);
        flexTable.setWidget(rowIndex, columnIndex, pushButton);
        flexTable.getCellFormatter().setHorizontalAlignment(rowIndex, columnIndex, HasHorizontalAlignment.ALIGN_CENTER);
        return pushButton;
    }

    public void preloadImage(SafeUri imagePath, final TimedStimulusListener timedStimulusListener) {
        final Image image = new Image(imagePath);
        image.setVisible(false);
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                timedStimulusListener.postLoadTimerFired();
            }
        });
        outerPanel.add(image);
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

    public void addSvgImage(String svgContent, int percentWidth) {
        final HTMLPanel htmlPanel = new HTMLPanel(svgContent);
        htmlPanel.setWidth(percentWidth + "%");
        outerPanel.add(htmlPanel);
    }

    public void addTimedAudio(SafeUri oggPath, SafeUri mp3Path, long postLoadMs, final TimedStimulusListener timedStimulusListener) {
        audioPlayer.stopAll();
        audioPlayer.setOnEndedListener(new AudioEventListner() {

            @Override
            public void audioEnded() {
                audioPlayer.setOnEndedListener(null); // prevent multiple triggering
                timedStimulusListener.postLoadTimerFired();
            }
        });
        audioPlayer.playSample(oggPath, mp3Path);
    }

    public void addAudioPlayerGui() {
        outerPanel.add(audioPlayer.getAudioPlayer());
    }

    public void stopAudio() {
        audioPlayer.stopAll();
    }
}
