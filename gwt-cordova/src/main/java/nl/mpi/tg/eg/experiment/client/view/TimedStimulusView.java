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

import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.CanPlayThroughEvent;
import com.google.gwt.event.dom.client.CanPlayThroughHandler;
import com.google.gwt.event.dom.client.EndedEvent;
import com.google.gwt.event.dom.client.EndedHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.media.client.Video;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import nl.mpi.tg.eg.experiment.client.listener.AudioEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;

/**
 * @since Jun 26, 2015 10:24:34 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedStimulusView extends ComplexView {

    private final AudioPlayer audioPlayer;
    private StimulusGrid stimulusGrid = null;
    private HorizontalPanel horizontalPanel = null;

    public TimedStimulusView(AudioPlayer audioPlayer) {
        super();
        this.audioPlayer = audioPlayer;
    }

    public void startGrid() {
        outerPanel.setStylePrimaryName("menuOuter");
        stimulusGrid = new StimulusGrid();
        outerPanel.add(stimulusGrid);
    }

    public void startHorizontalPanel() {
        horizontalPanel = new HorizontalPanel();
        outerPanel.add(horizontalPanel);
    }

    public void endHorizontalPanel() {
        horizontalPanel = null;
    }

    public void endGrid() {
        stimulusGrid = null;
    }

    public ButtonBase addStringItem(final PresenterEventListner menuItemListerner, final String labelString, final int rowIndex, final int columnIndex, final String widthString) {
        return stimulusGrid.addStringItem(menuItemListerner, labelString, rowIndex, columnIndex, widthString);
    }

    public ButtonBase addImageItem(final PresenterEventListner menuItemListerner, final SafeUri imagePath, final int rowIndex, final int columnIndex, final String widthString) {
        return stimulusGrid.addImageItem(menuItemListerner, imagePath, rowIndex, columnIndex, widthString);
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
        ((horizontalPanel != null) ? horizontalPanel : outerPanel).add(image);
    }

    public void addBackgroundImage(final SafeUri imagePath, final int postLoadMs, final TimedStimulusListener timedStimulusListener) {
//        final Image image = new Image(imagePath);
//            this.getElement().getStyle().setBackgroundColor("green");
        this.getElement().getStyle().setBackgroundImage("url(" + imagePath.asString() + ")");
        this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
//            this.getElement().getStyle().setProperty("backgroundSize", "100% 100%");
        this.getElement().getStyle().setProperty("backgroundSize", "cover");
        this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
        this.getElement().getStyle().setProperty("backgroundPosition", "50% 50%");
//        image.addLoadHandler(new LoadHandler() {
//
//            @Override
//            public void onLoad(LoadEvent event) {
//                Timer timer = new Timer() {
//                    @Override
//                    public void run() {
        timedStimulusListener.postLoadTimerFired();
//                    }
//                };
//                timer.schedule(postLoadMs);
//            }
//        });
//        outerPanel.add(image);
    }

    @Override
    public void clearPage() {
        stopAudio();
        this.getElement().getStyle().setBackgroundImage(null);
        super.clearPage();
    }

    public void addTimedImage(SafeUri imagePath, int percentOfPage, int maxHeight, int maxWidth, final String animateStyle, final Integer fixedPositionY, final int postLoadMs, final TimedStimulusListener shownStimulusListener, final TimedStimulusListener timedStimulusListener) {
        final Image image = new Image(imagePath);
        if (animateStyle != null) {
            image.addStyleName(animateStyle);
            image.getElement().getStyle().setLeft(fixedPositionY, Style.Unit.PCT);
        }
        addSizeAttributes(image.getElement(), percentOfPage, maxHeight, maxWidth);
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                shownStimulusListener.postLoadTimerFired();
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        timedStimulusListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadMs);
            }
        });
        ((horizontalPanel != null) ? horizontalPanel : outerPanel).add(image);
    }

    public void addSvgImage(String svgContent, int percentWidth) {
        final HTMLPanel htmlPanel = new HTMLPanel(svgContent);
        htmlPanel.setWidth(percentWidth + "%");
        ((horizontalPanel != null) ? horizontalPanel : outerPanel).add(htmlPanel);
    }

    public StimulusFreeText addStimulusFreeText(final String validationRegex, final String validationChallenge) {
        final Label errorLabel = new Label(validationChallenge);
        errorLabel.setStylePrimaryName("metadataErrorMessage");
        errorLabel.setVisible(false);
        outerPanel.add(errorLabel);
        final TextBox textBox = new TextBox();
        outerPanel.add(textBox);
        textBox.setFocus(true);
        return new StimulusFreeText() {
            @Override
            public String getValue() {
                return textBox.getValue();
            }

            @Override
            public boolean isValid() {
                if (getValue().matches(validationRegex)) {
                    textBox.setStylePrimaryName("metadataOK");
                    errorLabel.setVisible(false);
                    return true;
                } else {
                    textBox.setStylePrimaryName("metadataError");
                    errorLabel.setVisible(true);
                    textBox.setFocus(true);
                    return false;
                }
            }
        };
    }

    public void addTimedAudio(SafeUri oggPath, SafeUri mp3Path, final int postLoadMs, final TimedStimulusListener shownStimulusListener, final TimedStimulusListener timedStimulusListener) {
        audioPlayer.stopAll();
        audioPlayer.setOnEndedListener(new AudioEventListner() {
            @Override
            public void audioLoaded() {
                shownStimulusListener.postLoadTimerFired();
            }

            @Override
            public void audioEnded() {
                audioPlayer.setOnEndedListener(null); // prevent multiple triggering
                Timer timer = new Timer() {
                    public void run() {
                        timedStimulusListener.postLoadTimerFired();
                    }
                };
                timer.schedule(postLoadMs);
            }
        });
        audioPlayer.playSample(oggPath, mp3Path);
    }

    public void addTimedVideo(SafeUri oggPath, SafeUri mp4Path, int percentOfPage, int maxHeight, int maxWidth, final int postLoadMs, final TimedStimulusListener shownStimulusListener, final TimedStimulusListener timedStimulusListener) {
        final Video video = Video.createIfSupported();
        if (video != null) {
//            video.setPoster(poster);
            video.setControls(true);
            video.setPreload(MediaElement.PRELOAD_AUTO);
            addSizeAttributes(video.getElement(), percentOfPage, maxHeight, maxWidth);
            ((horizontalPanel != null) ? horizontalPanel : outerPanel).add(video);
            if (oggPath != null) {
                video.addSource(oggPath.asString(), "video/ogg");
            }
            if (mp4Path != null) {
                video.addSource(mp4Path.asString(), "video/mp4");
            }
            video.addCanPlayThroughHandler(new CanPlayThroughHandler() {
                @Override
                public void onCanPlayThrough(CanPlayThroughEvent event) {
                    shownStimulusListener.postLoadTimerFired();
                    video.play();
                }
            });
            video.addEndedHandler(new EndedHandler() {
                private boolean triggered = false;

                @Override
                public void onEnded(EndedEvent event) {
                    // prevent multiple triggering
                    if (!triggered) {
                        triggered = true;
                        Timer timer = new Timer() {
                            public void run() {
                                timedStimulusListener.postLoadTimerFired();
                            }
                        };
                        timer.schedule(postLoadMs);
                    }
                }
            });
            video.setAutoplay(false);
            video.load();
        }
    }

    public void addAudioPlayerGui() {
        ((horizontalPanel != null) ? horizontalPanel : outerPanel).add(audioPlayer.getAudioPlayer());
    }

    public void stopAudio() {
        audioPlayer.stopAll();
    }
}
