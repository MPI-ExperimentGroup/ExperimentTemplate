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

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.Duration;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.media.client.Video;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.experiment.client.exception.AudioException;
import nl.mpi.tg.eg.experiment.client.listener.AudioEventListner;
import nl.mpi.tg.eg.experiment.client.listener.AudioExceptionListner;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.FrameTimeTrigger;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import nl.mpi.tg.eg.experiment.client.util.AudioPlayer;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;
import nl.mpi.tg.eg.experiment.client.util.AudioRecorder;
import nl.mpi.tg.eg.experiment.client.util.VideoPlayer;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jun 26, 2015 10:24:34 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedStimulusView extends ComplexView {

    private final Map<String, AudioPlayer> audioList = new HashMap<>();
    private StimulusGrid stimulusGrid = null;
    private String webRecorderMediaId = null;
    private final Map<String, Video> videoList = new HashMap<>();
    private final Map<String, MediaTriggerListener> mediaTriggerListenerList = new HashMap<>();
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
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("preloadImage");
        }
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
        for (MediaTriggerListener mediaTriggerListener : mediaTriggerListenerList.values()) {
            mediaTriggerListener.clearTriggers();
        }
        mediaTriggerListenerList.clear();
        webRecorderMediaId = null;
    }

    public ValueChangeListener<Double> addBarGraphElement(final int initialValue, final int range, final String styleName) {
        final HorizontalPanel bargraphOuter = new HorizontalPanel();
        final HorizontalPanel bargraphInner = new HorizontalPanel();
        if (styleName != null && !styleName.isEmpty()) {
            bargraphInner.setSize(((int) (100.0 / range * initialValue)) + "%", ((int) (100.0 / range * initialValue)) + "%");
        } else {
            bargraphOuter.setPixelSize(100, 10);
            bargraphInner.setPixelSize((int) (100.0 / range * initialValue), 10);
        }
        bargraphOuter.setStyleName("bargraphOuter");
        bargraphInner.setStyleName("bargraphInner");
        if (styleName != null && !styleName.isEmpty()) {
            bargraphOuter.addStyleName(styleName);
            bargraphInner.addStyleName(styleName);
        }
        bargraphOuter.add(bargraphInner);
        getActivePanel().add(bargraphOuter);
        return new ValueChangeListener<Double>() {
            @Override
            public void onValueChange(Double updateValue) {
                if (styleName != null && !styleName.isEmpty()) {
                    bargraphInner.setSize(((int) (100.0 / range * updateValue)) + "%", ((int) (100.0 / range * updateValue)) + "%");
                } else {
                    bargraphInner.setPixelSize((int) (100.0 / range * updateValue), (int) (100.0 / range * updateValue));
                }
            }
        };
    }

    public StimulusButton addTimedImage(final TimedEventMonitor timedEventMonitor, SafeUri imagePath, final String styleName, final int postLoadMs, final CancelableStimulusListener postLoadMsListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener clickedStimulusListener) {
        cancelableListnerList.add(postLoadMsListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(clickedStimulusListener);
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("addTimedImage");
        }
        final Image image = new Image(imagePath);
        if (styleName != null) {
            image.addStyleName(styleName);
        }
        image.setVisible(false);
        image.addErrorHandler(new ErrorHandler() {
            int retryCount = 10;

            @Override
            public void onError(ErrorEvent event) {
                if (retryCount > 0) {
                    if (timedEventMonitor != null) {
                        timedEventMonitor.registerEvent("imageRetry");
                    }
                    retryCount--;
                    image.setUrl(UriUtils.fromString(imagePath.asString() + "?retryCount=" + retryCount));
                } else {
                    if (timedEventMonitor != null) {
                        timedEventMonitor.registerEvent("imageOnError");
                    }
                    failedStimulusListener.postLoadTimerFired();
                }
            }
        });
        image.addLoadHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                if (timedEventMonitor != null) {
                    timedEventMonitor.registerEvent("imageOnLoad");
                }
                image.setVisible(true);
                if (postLoadMs > 0) {
                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            postLoadMsListener.postLoadTimerFired();
                        }
                    };
                    timerList.add(timer);
                    timer.schedule(postLoadMs); // todo: do we remove this?
                } else {
                    postLoadMsListener.postLoadTimerFired();
                }
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
            public boolean isChecked() {
                return false;
            }

            @Override
            public String getValue() {
                return null;
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
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("addTimedImage");
        }
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
                if (postLoadMs > 0) {
                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            loadedStimulusListener.postLoadTimerFired();
                        }
                    };
                    timerList.add(timer);
                    timer.schedule(postLoadMs); // todo: do we remove this?
                } else {
                    loadedStimulusListener.postLoadTimerFired();
                }
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

    public StimulusFreeText addStimulusValidation(final LocalStorage localStorage, final UserId userId, final Stimulus currentStimulus, final String postName, final String validationRegex, final String validationChallenge, final int dataChannel) {
        final Label errorLabel = new Label(validationChallenge);
        errorLabel.setStylePrimaryName("metadataErrorMessage");
        errorLabel.setVisible(false);
        getActivePanel().add(errorLabel);
        return new StimulusFreeText() {
            private boolean isVisible = true;
            private boolean isEnabled = true;

            @Override
            public Stimulus getStimulus() {
                return currentStimulus;
            }

            @Override
            public String getPostName() {
                return postName;
            }

            @Override
            public String getResponseTimes() {
                return null;
            }

            @Override
            public String getValue() {
                JSONObject storedStimulusJSONObject = localStorage.getStoredJSONObject(userId, currentStimulus);
                final JSONValue codeResponse = (storedStimulusJSONObject == null) ? null : storedStimulusJSONObject.get(postName);
                return (codeResponse != null) ? codeResponse.isString().stringValue() : null;
            }

            @Override
            public void setVisible(boolean isVisible) {
                this.isVisible = isVisible;
                if (!isEnabled()) {
                    errorLabel.setVisible(false);
                }
            }

            @Override
            public void setEnabled(boolean isEnabled) {
                this.isEnabled = isEnabled;
                if (!isEnabled()) {
                    errorLabel.setVisible(false);
                }
            }

            @Override
            public boolean isEnabled() {
                // while this has no editable components it must respond correctly to isEnabled for it to validate correctly, so we track the isEnabled and isVisible states
                return isEnabled && isVisible;
            }

            @Override
            public boolean isValid() {
                final String currentValue = getValue();
                final boolean isValid;
                if (currentValue != null) {
                    isValid = currentValue.matches(validationRegex);
                } else {
                    isValid = false;
                }
                if (isValid) {
                    errorLabel.setVisible(false);
                    return true;
                } else {
                    errorLabel.setText(validationChallenge);
                    errorLabel.setVisible(true);
                    return false;
                }
            }

            @Override
            public int getDataChannel() {
                return dataChannel;
            }

            @Override
            public void setFocus(boolean wantsFocus) {
                // todo: we could use a scroll to method to focus the message here
            }
        };
    }

    public StimulusFreeText addSlider(final Stimulus stimulus, final String postName, final PresenterEventListner presenterListener, final double initial, final int minimum, final int maximum, final int dataChannel) {
        TextBox slider = new TextBox();
        slider.getElement().setAttribute("type", "range");
        slider.getElement().setAttribute("min", Integer.toString(minimum));
        slider.getElement().setAttribute("max", Integer.toString(maximum));
        slider.getElement().setAttribute("value", Double.toString(initial));
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            slider.addStyleName(presenterListener.getStyleName());
        }
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (slider.isEnabled()) {
                    presenterListener.eventFired(null, this);
                }
                resetSingleShot();
            }
        };
        slider.addClickHandler(singleShotEventListner);
        slider.addTouchEndHandler(singleShotEventListner);
        StimulusFreeText stimulusFreeText = new StimulusFreeText() {
            @Override
            public Stimulus getStimulus() {
                return stimulus;
            }

            @Override
            public String getPostName() {
                return postName;
            }

            @Override
            public String getResponseTimes() {
                return "";
            }

            @Override
            public String getValue() {
                return slider.getValue();
            }

            @Override
            public boolean isValid() {
                return true;
            }

            @Override
            public void setVisible(boolean isVisible) {
                slider.setVisible(isVisible);
            }

            @Override
            public void setEnabled(boolean isEnabled) {
                slider.setEnabled(isEnabled);
            }

            @Override
            public boolean isEnabled() {
                return slider.isEnabled() && slider.isVisible();
            }

            @Override
            public int getDataChannel() {
                return dataChannel;
            }

            @Override
            public void setFocus(boolean wantsFocus) {
                slider.setFocus(wantsFocus);
            }
        };
        getActivePanel().add(slider);
        return stimulusFreeText;
    }

    public StimulusFreeText addStimulusFreeText(final Stimulus stimulus, final String postName, final String validationRegex, final String keyCodeChallenge, final String validationChallenge, final String allowedCharCodes, final SingleShotEventListner enterKeyListner, final int hotKey, final String styleName, final int dataChannel, final String textValue) {
        final int inputLengthLimit = 1000; // this coud be a parameter from the configuraiton file, however the validationRegex can also limit the input length.
        // perhaps consider removing allowedCharCodes and doing a regex test on each key?
        final Label errorLabel = new Label(validationChallenge);
        errorLabel.setStylePrimaryName("metadataErrorMessage");
        errorLabel.setVisible(false);
        getActivePanel().add(errorLabel);
        final Duration duration = new Duration();
        final StringBuilder responseTimes = new StringBuilder();
        final TextArea textBox = new TextArea();
        // the DB currently has a 1024 char limit, so we set the max length on the element
        textBox.getElement().setAttribute("maxlength", "1000");
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
            private boolean isVisible = true;
            private boolean isEnabled = true;

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
            public void setVisible(boolean isVisible) {
                this.isVisible = isVisible;
                textBox.setVisible(isVisible);
                if (!isEnabled()) {
                    errorLabel.setVisible(false);
                    textBox.setStylePrimaryName("metadataOK");
                }
            }

            @Override
            public void setEnabled(boolean isEnabled) {
                this.isEnabled = isEnabled;
                textBox.setEnabled(isEnabled);
                if (!isEnabled()) {
                    errorLabel.setVisible(false);
                    textBox.setStylePrimaryName("metadataOK");
                }
            }

            @Override
            public boolean isEnabled() {
                return isEnabled && isVisible;
            }

            @Override
            public boolean isValid() {
                if ((getValue().length() <= inputLengthLimit + 2) && (validationRegex == null || getValue().matches(validationRegex))) {
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

    public void addTimedAudio(final TimedEventMonitor timedEventMonitor, final SafeUri oggPath, final SafeUri mp3Path, final SafeUri wavPath, final boolean showPlaybackIndicator, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, final boolean autoPlay, final String mediaId) {
        cancelableListnerList.add(loadedStimulusListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(playbackStartedStimulusListener);
        cancelableListnerList.add(playedStimulusListener);
        try {
            if (timedEventMonitor != null) {
                timedEventMonitor.registerEvent("addTimedAudio");
            }
            final AudioPlayer audioPlayer = new AudioPlayer(timedEventMonitor, new AudioExceptionListner() {
                @Override
                public void audioExceptionFired(AudioException audioException) {
                    failedStimulusListener.postLoadTimerFired();
                }
            }, oggPath, mp3Path, wavPath, autoPlay, mediaId);
            audioList.put(mediaId, audioPlayer);
            final MediaTriggerListener existingTriggers = mediaTriggerListenerList.get(mediaId);
            if (existingTriggers != null) {
                existingTriggers.clearTriggers();
                mediaTriggerListenerList.remove(mediaId);
            }
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
                    loadedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioStarted() {
                    playbackStartedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioFailed() {
                    failedStimulusListener.postLoadTimerFired();
                }

                @Override
                public void audioEnded() {
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

    public Element addTimedVideo(final TimedEventMonitor timedEventMonitor, final SafeUri ogvPath, final SafeUri mp4Path, int percentOfPage, int maxHeight, int maxWidth, final String styleName, final boolean autoPlay, final boolean loop, final boolean showControls, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener, final CancelableStimulusListener playbackStartedStimulusListener, final CancelableStimulusListener playedStimulusListener, final String mediaId) {
        cancelableListnerList.add(loadedStimulusListener);
        cancelableListnerList.add(failedStimulusListener);
        cancelableListnerList.add(playbackStartedStimulusListener);
        cancelableListnerList.add(playedStimulusListener);
        final Element videoElement;
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("addTimedVideo");
        }
        final Video video = Video.createIfSupported();
        if (video == null) {
            failedStimulusListener.postLoadTimerFired();
            videoElement = null;
        } else {
            video.setAutoplay(autoPlay);
            videoList.put(mediaId, video);
            final MediaTriggerListener existingTriggers = mediaTriggerListenerList.get(mediaId);
            if (existingTriggers != null) {
                existingTriggers.clearTriggers();
                mediaTriggerListenerList.remove(mediaId);
            }
//            video.setPoster(poster);
            video.setControls(showControls);
            video.setPreload(MediaElement.PRELOAD_AUTO);
            if (styleName != null && !styleName.isEmpty()) {
                video.addStyleName(styleName);
            } else {
                addSizeAttributes(video.getElement(), percentOfPage, maxHeight, maxWidth);
            }
            getActivePanel().add(video);
            video.addCanPlayThroughHandler(new CanPlayThroughHandler() {
                boolean hasTriggeredOnLoaded = false;

                @Override
                public void onCanPlayThrough(CanPlayThroughEvent event) {
                    if (!hasTriggeredOnLoaded) {
                        if (timedEventMonitor != null) {
                            timedEventMonitor.registerEvent("videoCanPlayThrough");
                        }
                        hasTriggeredOnLoaded = true;
                        loadedStimulusListener.postLoadTimerFired();
                        if (autoPlay) {
                            video.play();
                        }
                        if (video.getError() != null) {
                            // todo: check that this method is functioning correctly and if not then use the method in audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onAudioFailed()();
                            failedStimulusListener.postLoadTimerFired();
                        }
                    }
                }
            });
            new VideoPlayer().addNativeCallbacks(timedEventMonitor, video.getVideoElement(), playbackStartedStimulusListener);
//            todo: move the video handling code from here into the VideoPlayer class, in such a way is it can be used like the AudioPlayer class of the same package
            video.addEndedHandler(new EndedHandler() {
                private boolean triggered = false;

                @Override
                public void onEnded(EndedEvent event) {
                    if (timedEventMonitor != null) {
                        timedEventMonitor.registerEvent("videoEnded");
                        timedEventMonitor.registerMediaLength(mediaId, (long) (video.getCurrentTime() * 1000));
                    }
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
//            if (oggPath != null) {
//                video.addSource(oggPath.asString(), "video/ogg");
//            }
            if (ogvPath != null) {
                video.addSource(ogvPath.asString(), "video/ogg");
            }
            if (mp4Path != null) {
                video.addSource(mp4Path.asString(), "video/mp4");
            }

            if (!autoPlay) {
                video.pause();
                video.setCurrentTime(0);
            }
            video.setLoop(loop);
            video.load();
            if (video.getError() != null) {
                // todo: check that this method is functioning correctly and if not then use the method in audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onAudioFailed()();
                failedStimulusListener.postLoadTimerFired();
            }
            videoElement = video.getElement();
        }
        return videoElement;
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
        if (webRecorderMediaId != null) {
            // nothing to do for stop all on a recorder
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
        if (webRecorderMediaId != null) {
            // pauseAudioRecorderWeb on a recorder has been added back in due to popular request although it will cause timing issues
            if (webRecorderMediaId.matches(mediaId)) {
                AudioRecorder.pauseRecorderWeb();
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
        if (webRecorderMediaId != null) {
            // resumeAudioRecorderWeb on a recorder has been added back in due to popular request although it will cause timing issues
            if (webRecorderMediaId.matches(mediaId)) {
                AudioRecorder.resumeRecorderWeb();
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
        if (webRecorderMediaId != null) {
            // nothing to do for rewind on a recorder
        }
    }

    public void addMediaTriggers(final String mediaId, final FrameTimeTrigger frameTimeTrigger) {
        final MediaTriggerListener mediaTriggerListener;
        if (mediaTriggerListenerList.containsKey(mediaId)) {
            mediaTriggerListener = mediaTriggerListenerList.get(mediaId);
        } else {
            mediaTriggerListener = new MediaTriggerListener();
            mediaTriggerListenerList.put(mediaId, mediaTriggerListener);
        }
        if (mediaTriggerListener.addMediaTriggerListener(frameTimeTrigger)) {
            final Video video = videoList.get(mediaId);
            if (video != null) {
                AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback() {
                    @Override
                    public void execute(double arg0) {
                        boolean hasMoreListeners = mediaTriggerListener.triggerWhenReady(video.getCurrentTime() * 1000);
                        if (hasMoreListeners) {
                            AnimationScheduler.get().requestAnimationFrame(this);
                        }
                    }
                });
            }
            final AudioPlayer audioPlayer = audioList.get(mediaId);
            if (audioPlayer != null) {
                AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback() {
                    @Override
                    public void execute(double arg0) {
                        boolean hasMoreListeners = mediaTriggerListener.triggerWhenReady(audioPlayer.getCurrentTime() * 1000);
                        if (hasMoreListeners) {
                            AnimationScheduler.get().requestAnimationFrame(this);
                        }
                    }
                });
            }
        }
    }

    public void logMediaTimeStamp(final String mediaId, final String eventTag, final TimedEventMonitor timedEventMonitor) {
        for (String key : videoList.keySet()) {
            if (key.matches(mediaId)) {
                Video video = videoList.get(key);
                if (video != null) {
                    timedEventMonitor.registerMediaLength(eventTag, (long) (video.getCurrentTime() * 1000));
                }
            }
        }
        for (String key : audioList.keySet()) {
            if (key.matches(mediaId)) {
                AudioPlayer audioPlayer = audioList.get(key);
                if (audioPlayer != null) {
                    timedEventMonitor.registerMediaLength(eventTag, (long) (audioPlayer.getCurrentTime() * 1000));
                }
            }
        }
        if (webRecorderMediaId != null) {
            if (webRecorderMediaId.matches(mediaId)) {
                AudioRecorder.logRecorderWebTimeStamp(eventTag, timedEventMonitor);
            }
        }
    }

    public boolean isWebRecorderMediaId(String webRecorderMediaId) {
        if (this.webRecorderMediaId == null || webRecorderMediaId == null) {
            return false;
        } else {
            return this.webRecorderMediaId.equals(webRecorderMediaId);
        }
    }

    public void setWebRecorderMediaId(String webRecorderMediaId) {
        this.webRecorderMediaId = webRecorderMediaId;
        stopMedia(webRecorderMediaId);
        videoList.remove(webRecorderMediaId);
        audioList.remove(webRecorderMediaId);
        final MediaTriggerListener existingTriggers = mediaTriggerListenerList.get(webRecorderMediaId);
        if (existingTriggers != null) {
            existingTriggers.clearTriggers();
            mediaTriggerListenerList.remove(webRecorderMediaId);
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
