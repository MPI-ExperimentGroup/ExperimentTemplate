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

import com.google.gwt.core.client.Duration;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.CanPlayThroughEvent;
import com.google.gwt.event.dom.client.CanPlayThroughHandler;
import com.google.gwt.event.dom.client.EndedEvent;
import com.google.gwt.event.dom.client.EndedHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.media.client.Video;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.experiment.client.exception.AudioException;
import nl.mpi.tg.eg.experiment.client.listener.AudioEventListner;
import nl.mpi.tg.eg.experiment.client.listener.AudioExceptionListner;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;
import nl.mpi.tg.eg.experiment.client.service.VideoPlayer;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jun 26, 2015 10:24:34 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedStimulusView extends ComplexView {

    private final Map<String, AudioPlayer> audioList = new HashMap<>();
    private StimulusGrid stimulusGrid = null;
    private final Map<String, Video> videoList = new HashMap<>();
    private final List<Timer> timerList = new ArrayList<>();
    private final List<CancelableStimulusListener> cancelableListnerList = new ArrayList<>();

    public TimedStimulusView() {
        super();
    }

    public void startGrid(final String menuOuter) {
        outerPanel.setStylePrimaryName(menuOuter);
        stimulusGrid = new StimulusGrid(domHandlerArray);
        outerPanel.add(stimulusGrid);
    }

    public void endGrid() {
        stimulusGrid = null;
    }

    public StimulusButton addStringItem(final PresenterEventListner menuItemListerner, final String labelString, final int rowIndex, final int columnIndex, final int hotKeyIndex) {
        return stimulusGrid.addStringItem(menuItemListerner, labelString, rowIndex, columnIndex, hotKeyIndex);
    }

    public StimulusButton addImageItem(final PresenterEventListner menuItemListerner, final SafeUri imagePath, final int rowIndex, final int columnIndex, final String widthString, final String styleName, final int hotKeyIndex) {
        return stimulusGrid.addImageItem(menuItemListerner, imagePath, rowIndex, columnIndex, widthString, styleName, hotKeyIndex);
    }

    public void preloadImage(final TimedEventMonitor timedEventMonitor, SafeUri imagePath, final TimedStimulusListener timedStimulusListener) {
        timedEventMonitor.registerEvent("preloadImage");
        final Image image = new Image(imagePath);
        image.setVisible(false);
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("onPreloadImage");
                }
                timedStimulusListener.postLoadTimerFired();
            }
        });
        getActivePanel().add(image);
    }

    public void addBackgroundImage(final SafeUri imagePath, final String styleName, final int postLoadMs, final TimedStimulusListener timedStimulusListener) {
//        final Image image = new Image(imagePath);
//            this.getElement().getStyle().setBackgroundColor("green");
        if (imagePath == null) {
            this.getElement().getStyle().clearBackgroundImage();
        } else {
            this.getElement().getStyle().setBackgroundImage("url(" + imagePath.asString() + ")");
        }
        this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
//            this.getElement().getStyle().setProperty("backgroundSize", "100% 100%");
        this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
        this.getElement().getStyle().setProperty("backgroundPosition", "50% 50%");
        // remove the custom styles but keep the page width style
        this.setStyleName(this.getStyleName().contains("normalWidth") ? "normalWidth" : "narrowWidth");
        if (styleName != null && !styleName.isEmpty()) {
            this.addStyleName(styleName);
        } else {
            this.getElement().getStyle().setProperty("backgroundSize", "cover");
//            resizeView(); // this is to put back the screen size styles
        }
//        image.addLoadHandler(new LoadHandler() {
//
//            @Override
//            public void onLoad(LoadEvent event) {
        Timer timer = new Timer() {
            @Override
            public void run() {
                timedStimulusListener.postLoadTimerFired();
            }
        };
        timerList.add(timer);
        timer.schedule(postLoadMs);
//            }
//        });
//        outerPanel.add(image);
    }

    @Override
    public void clearPageAndTimers(String styleName) {
        stopListeners();
        stopTimers();
        stopAudio(); // note that stop audio here is probably redundant 
        stopAllMedia();
        this.getElement().getStyle().setBackgroundImage(null);
        super.clearPageAndTimers(styleName);
        videoList.clear();
        audioList.clear();
    }

    public StimulusButton addTimedImage(final TimedEventMonitor timedEventMonitor, SafeUri imagePath, final String styleName, final int postLoadMs, final CancelableStimulusListener onLoadStimulusListener, final CancelableStimulusListener postLoadMsListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener clickedStimulusListener) {
        cancelableListnerList.add(onLoadStimulusListener);
        cancelableListnerList.add(postLoadMsListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(clickedStimulusListener);
        timedEventMonitor.registerEvent("addTimedImage");
        final Image image = new Image(imagePath);
        if (styleName != null) {
            image.addStyleName(styleName);
        }
        image.setVisible(false);
        image.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("onError");
                }
                failedStimulusListener.postLoadTimerFired();
            }
        });
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("onLoad");
                }
                image.setVisible(true);
                if (onLoadStimulusListener != null) {
                    onLoadStimulusListener.postLoadTimerFired();
                }
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        postLoadMsListener.postLoadTimerFired();
                    }
                };
                timerList.add(timer);
                timer.schedule(postLoadMs);
            }
        });
        final SingleShotEventListner singleShotEventListner;
        if (clickedStimulusListener != null) {
            singleShotEventListner = new SingleShotEventListner() {

                @Override
                protected void singleShotFired() {
                    clickedStimulusListener.postLoadTimerFired();
                    resetSingleShot();
                }
            };
            image.addClickHandler(singleShotEventListner);
            image.addTouchStartHandler(singleShotEventListner);
            image.addTouchMoveHandler(singleShotEventListner);
            image.addTouchEndHandler(singleShotEventListner);
        } else {
            singleShotEventListner = null;
        }
        getActivePanel().add(image);
        return new StimulusButton() {
            @Override
            public Widget getWidget() {
                return image;
            }

            @Override
            public void addStyleName(String styleName) {
                image.addStyleName(styleName);
            }

            @Override
            public void removeStyleName(String styleName) {
                image.removeStyleName(styleName);
            }

            @Override
            public void setEnabled(boolean enabled) {
                if (singleShotEventListner != null) {
                    singleShotEventListner.setEnabled(enabled);
                }
            }

            @Override
            public boolean isEnabled() {
                if (singleShotEventListner != null) {
                    return singleShotEventListner.isEnabled();
                } else {
                    return false;
                }
            }

            @Override
            public void setVisible(boolean visible) {
                image.setVisible(visible);
            }

            @Override
            public void triggerSingleShotEventListner() {
                clickedStimulusListener.postLoadTimerFired();
            }
        };
    }

    @Deprecated
    public void addTimedImage(final TimedEventMonitor timedEventMonitor, SafeUri imagePath, int percentOfPage, int maxHeight, int maxWidth, final String animateStyle, final Integer fixedPositionY, final int postLoadMs, final CancelableStimulusListener shownStimulusListener, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener clickedStimulusListener) {
        cancelableListnerList.add(shownStimulusListener);
        cancelableListnerList.add(loadedStimulusListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(clickedStimulusListener);
        timedEventMonitor.registerEvent("addTimedImage");
        final Image image = new Image(imagePath);
        if (animateStyle != null && !animateStyle.isEmpty()) {
            image.addStyleName(animateStyle);
            if (fixedPositionY != null) {
                image.getElement().getStyle().setLeft(fixedPositionY, Style.Unit.PCT);
            }
        }
        addSizeAttributes(image.getElement(), percentOfPage, maxHeight, maxWidth);
        image.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("onError");
                }
                if (failedStimulusListener != null) {
                    failedStimulusListener.postLoadTimerFired();
                }
            }
        });
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("onLoad");
                }
                if (shownStimulusListener != null) {
                    shownStimulusListener.postLoadTimerFired();
                }
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        loadedStimulusListener.postLoadTimerFired();
                    }
                };
                timerList.add(timer);
                timer.schedule(postLoadMs);
            }
        });
        if (clickedStimulusListener != null) {
            final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

                @Override
                protected void singleShotFired() {
                    clickedStimulusListener.postLoadTimerFired();
                    resetSingleShot();
                }
            };
            image.addClickHandler(singleShotEventListner);
            image.addTouchStartHandler(singleShotEventListner);
            image.addTouchMoveHandler(singleShotEventListner);
            image.addTouchEndHandler(singleShotEventListner);
        }
        final HTMLPanel htmlPanel = new HTMLPanel("");
        htmlPanel.setStylePrimaryName("gridCell");
        htmlPanel.add(image);
        getActivePanel().add(htmlPanel);
    }

    public void addSvgImage(String svgContent, int percentWidth) {
        final HTMLPanel htmlPanel = new HTMLPanel(svgContent);
        htmlPanel.setWidth(percentWidth + "%");
        getActivePanel().add(htmlPanel);
    }

    public StimulusFreeText addStimulusFreeText(final Stimulus stimulus, final String postName, final String validationRegex, final String keyCodeChallenge, final String validationChallenge, final String allowedCharCodes, final SingleShotEventListner enterKeyListner, final int hotKey, final String styleName, final int dataChannel, final String textValue) {
        final int inputLengthLimit = 28; // todo: make this a parameter from the configuraiton file, remove allowedCharCodes and do a regex test on each key?
        final Label errorLabel = new Label(validationChallenge);
        errorLabel.setStylePrimaryName("metadataErrorMessage");
        errorLabel.setVisible(false);
        getActivePanel().add(errorLabel);
        final Duration duration = new Duration();
        final StringBuilder responseTimes = new StringBuilder();
        final TextArea textBox = new TextArea();
        if (textValue != null) {
            textBox.setText(textValue);
        }
        if (hotKey == KeyCodes.KEY_ENTER) {
            textBox.setVisibleLines(1);
            textBox.getElement().getStyle().setProperty("minHeight", "26px");
        }
        if (styleName != null) {
            textBox.addStyleName(styleName);
        }
        textBox.setStylePrimaryName("metadataOK");
        getActivePanel().add(textBox);
        textBox.setFocus(true);
        textBox.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final char charCode = event.getCharCode();
                if (charCode > -1 && charCode == hotKey) {
                    event.getNativeEvent().preventDefault();
                    enterKeyListner.eventFired();
                    errorLabel.setVisible(false);
                } else if (charCode == 0) {
                    // firefox needs these events to be handled by allowing the event to pass
                    return;
                } else if (textBox.getText().length() > inputLengthLimit) {
                    event.getNativeEvent().preventDefault();
                    // todo: update this to give a sensible message
                    errorLabel.setText(keyCodeChallenge.replace("<keycode>", "" + inputLengthLimit) + validationChallenge);
                    errorLabel.setVisible(true);
                } else if (allowedCharCodes != null) {
                    if (0 > allowedCharCodes.indexOf(charCode)) {
                        event.getNativeEvent().preventDefault();
                        final char invertedCaseCode = (Character.isLowerCase(charCode)) ? Character.toUpperCase(charCode) : Character.toLowerCase(charCode);
                        if (0 > allowedCharCodes.indexOf(invertedCaseCode)) {
                            // if the key is not allowed, then show a message
//                            final String messageString = "The key '<keycode>' is not allowed. " + validationChallenge;
                            errorLabel.setText(keyCodeChallenge.replace("<keycode>", "" + charCode) + validationChallenge);
                            errorLabel.setVisible(true);
                        } else {
                            responseTimes.append(duration.elapsedMillis());
                            responseTimes.append(",");
                            // if the case is not allowed, then modify the case to what is
                            final int cursorPos = textBox.getCursorPos();
                            String pretext = textBox.getText().substring(0, cursorPos);
                            String posttext = textBox.getText().substring(textBox.getCursorPos());
                            textBox.setText(pretext + invertedCaseCode + posttext);
                            textBox.setCursorPos(cursorPos + 1);
                            errorLabel.setVisible(false);
                        }
                    } else {
                        responseTimes.append(duration.elapsedMillis());
                        responseTimes.append(",");
                        errorLabel.setVisible(false);
                    }
                } else {
                    responseTimes.append(duration.elapsedMillis());
                    responseTimes.append(",");
                    errorLabel.setVisible(false);
                }
            }
        });
        final StimulusFreeText stimulusFreeText = new StimulusFreeText() {
            @Override
            public Stimulus getStimulus() {
                return stimulus;
            }

            @Override
            public String getValue() {
                return textBox.getValue();
            }

            @Override
            public String getResponseTimes() {
                return responseTimes.substring(0, responseTimes.length() - 1);
            }

            @Override
            public boolean isValid() {
                if (validationRegex == null || getValue().matches(validationRegex)) {
                    textBox.setStylePrimaryName("metadataOK");
                    errorLabel.setVisible(false);
                    return true;
                } else {
                    textBox.setStylePrimaryName("metadataError");
                    errorLabel.setText(validationChallenge);
                    errorLabel.setVisible(true);
                    textBox.setFocus(true);
                    return false;
                }
            }

            @Override
            public String getPostName() {
                return postName;
            }

            @Override
            public int getDataChannel() {
                return dataChannel;
            }

            @Override
            public void setFocus(boolean wantsFocus) {
                textBox.setFocus(wantsFocus);
            }

        };

        return stimulusFreeText;
    }

    public void addTimedAudio(final TimedEventMonitor timedEventMonitor, final SafeUri oggPath, final SafeUri mp3Path, boolean showPlaybackIndicator, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, final boolean autoPlay, final String mediaId) {
        cancelableListnerList.add(loadedStimulusListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(playbackStartedStimulusListener);
        cancelableListnerList.add(playedStimulusListener);
        try {
            timedEventMonitor.registerEvent("addTimedAudio");
            final AudioPlayer audioPlayer = new AudioPlayer(new AudioExceptionListner() {
                @Override
                public void audioExceptionFired(AudioException audioException) {
                    timedEventMonitor.registerEvent("audioExceptionFired");
                    failedStimulusListener.postLoadTimerFired();
                }
            }, oggPath, mp3Path, autoPlay);
            audioList.put(mediaId, audioPlayer);
//        audioPlayer.stopAll(); // Note that this stop all change will be a change in default behaviour, however there shouldn't be any instances where this is depended on, but that should be checked
            final Label playbackIndicator = new Label();
            final Timer playbackIndicatorTimer = new Timer() {
                public void run() {
                    playbackIndicator.setText("CurrentTime: " + audioPlayer.getCurrentTime());
//                    playbackIndicator.setWidth();
                    this.schedule(100);
                }
            };
            timerList.add(playbackIndicatorTimer);
            if (showPlaybackIndicator) {
                playbackIndicator.setStylePrimaryName("playbackIndicator");
                getActivePanel().add(playbackIndicator);
                playbackIndicatorTimer.schedule(500);
            }
            audioPlayer.setEventListner(new AudioEventListner() {
                @Override
                public void audioLoaded() {
                    timedEventMonitor.registerEvent("audioLoaded");
                    loadedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioStarted() {
                    timedEventMonitor.registerEvent("audioStarted");
                    playbackStartedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioFailed() {
                    timedEventMonitor.registerEvent("audioFailed");
                    failedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioEnded() {
                    timedEventMonitor.registerEvent("audioEnded");
                    timedEventMonitor.registerMediaLength(mediaId, (long) (audioPlayer.getCurrentTime() * 1000));
//                    playbackIndicatorTimer.cancel();
//                    playbackIndicator.removeFromParent();
//                    audioPlayer.setEventListner(null); // prevent multiple triggering
                    playedStimulusListener.postLoadTimerFired();
                }
            });
        } catch (AudioException audioException) {
            failedStimulusListener.postLoadTimerFired();
        }
    }

    public void addTimedVideo(final TimedEventMonitor timedEventMonitor, final SafeUri oggPath, final SafeUri ogvPath, final SafeUri mp4Path, int percentOfPage, int maxHeight, int maxWidth, final String styleName, final boolean autoPlay, final boolean loop, final boolean showControls, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, final String mediaId) {
        cancelableListnerList.add(loadedStimulusListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(playbackStartedStimulusListener);
        cancelableListnerList.add(playedStimulusListener);
        timedEventMonitor.registerEvent("addTimedVideo");
        final Video video = Video.createIfSupported();
        if (video == null) {
            failedStimulusListener.postLoadTimerFired();
        } else {
            video.setAutoplay(autoPlay);
            videoList.put(mediaId, video);
//            video.setPoster(poster);
            video.setControls(showControls);
            video.setPreload(MediaElement.PRELOAD_AUTO);
            if (styleName != null && !styleName.isEmpty()) {
                video.addStyleName(styleName);
            } else {
                addSizeAttributes(video.getElement(), percentOfPage, maxHeight, maxWidth);
            }
            getActivePanel().add(video);
            if (oggPath != null) {
                video.addSource(oggPath.asString(), "video/ogg");
            }
            if (ogvPath != null) {
                video.addSource(ogvPath.asString(), "video/ogg");
            }
            if (mp4Path != null) {
                video.addSource(mp4Path.asString(), "video/mp4");
            }
            video.addCanPlayThroughHandler(new CanPlayThroughHandler() {
                boolean hasTriggeredOnLoaded = false;

                @Override
                public void onCanPlayThrough(CanPlayThroughEvent event) {
                    if (!hasTriggeredOnLoaded) {
                        timedEventMonitor.registerEvent("onCanPlayThrough");
                        hasTriggeredOnLoaded = true;
                        loadedStimulusListener.postLoadTimerFired();
                        if (autoPlay) {
                            video.play();
                        }
                        if (video.getError() != null) {
                            // todo: check that this method is functioning correctly and if not then use the method in audioPlayer.@nl.mpi.tg.eg.experiment.client.service.AudioPlayer::onAudioFailed()();
                            failedStimulusListener.postLoadTimerFired();
                        }
                    }
                }
            });
            new VideoPlayer().addNativeCallbacks(video.getVideoElement(), playbackStartedStimulusListener);
//            todo: move the video handling code from here into the VideoPlayer class, in such a way is it can be used like the AudioPlayer class of the same package
            video.addEndedHandler(new EndedHandler() {
                private boolean triggered = false;

                @Override
                public void onEnded(EndedEvent event) {
                    timedEventMonitor.registerEvent("onEnded");
                    timedEventMonitor.registerMediaLength(mediaId, (long) (video.getCurrentTime() * 1000));
                    // prevent multiple triggering
                    if (!triggered) {
                        triggered = true;
//                        Timer timer = new Timer() {
//                            public void run() {
                        playedStimulusListener.postLoadTimerFired();
//                            }
//                        };
//                        timerList.add(timer);
//                        timer.schedule(postLoadMs);
                    }
                }
            });
            if (!autoPlay) {
                video.pause();
                video.setCurrentTime(0);
            }
            video.setLoop(loop);
            video.load();
            if (video.getError() != null) {
                // todo: check that this method is functioning correctly and if not then use the method in audioPlayer.@nl.mpi.tg.eg.experiment.client.service.AudioPlayer::onAudioFailed()();
                failedStimulusListener.postLoadTimerFired();
            }
        }
    }

    public void addAudioPlayerGui(final String mediaId) {
        getActivePanel().add(audioList.get(mediaId).getAudioPlayer());
    }

    // should this be deprecated/removed?
    public void stopAudio() {
        for (AudioPlayer audioPlayer : audioList.values()) {
            if (audioPlayer != null) {
                audioPlayer.stopAll();
            }
        }
    }

    public void stopAllMedia() {
        for (Video video : videoList.values()) {
            if (video != null) {
                video.pause();
            }
        }
        for (AudioPlayer audioPlayer : audioList.values()) {
            if (audioPlayer != null) {
                audioPlayer.stopAll();
            }
        }
    }

    public void stopMedia(final String mediaId) {
        for (String key : videoList.keySet()) {
            if (key.matches(mediaId)) {
                Video video = videoList.get(key);
                if (video != null) {
                    video.pause();
                }
            }
        }
        for (String key : audioList.keySet()) {
            if (key.matches(mediaId)) {
                AudioPlayer audioPlayer = audioList.get(key);
                if (audioPlayer != null) {
                    audioPlayer.stop();
                }
            }
        }
    }

    public void startMedia(final String mediaId) {
        for (String key : videoList.keySet()) {
            if (key.matches(mediaId)) {
                Video video = videoList.get(key);
                if (video != null) {
                    video.play();
                }
            }
        }
        for (String key : audioList.keySet()) {
            if (key.matches(mediaId)) {
                AudioPlayer audioPlayer = audioList.get(key);
                if (audioPlayer != null) {
                    audioPlayer.play();
                }
            }
        }
    }

    public void rewindMedia(final String mediaId) {
        for (String key : videoList.keySet()) {
            if (key.matches(mediaId)) {
                Video video = videoList.get(key);
                if (video != null) {
                    video.setCurrentTime(0);
                }
            }
        }
        for (String key : audioList.keySet()) {
            if (key.matches(mediaId)) {
                AudioPlayer audioPlayer = audioList.get(key);
                if (audioPlayer != null) {
                    audioPlayer.rewind();
                }
            }
        }
    }

    public void stopTimers() {
        for (Timer timer : timerList) {
            if (timer != null) {
                timer.cancel();
            }
        }
        timerList.clear();
    }

    public void stopListeners() {
        for (CancelableStimulusListener listener : cancelableListnerList) {
            if (listener != null) {
                listener.cancel();
            }
        }
        timerList.clear();
    }
}
