/*
 * Copyright (C) 2015 Language In Interaction
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
package nl.mpi.tg.eg.experiment.client.util;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.SourceElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.safehtml.shared.SafeUri;
import nl.mpi.tg.eg.experiment.client.exception.AudioException;
import nl.mpi.tg.eg.experiment.client.listener.AudioEventListener;
import nl.mpi.tg.eg.experiment.client.listener.AudioExceptionListener;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;

/**
 * @since Jan 6, 2015 10:27:57 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AudioPlayer {

    private Audio audioPlayer;
    private AudioEventListener audioEventListener;
    final private AudioExceptionListener audioExceptionListener;
    final private boolean autoPlay;
    private boolean hasTriggeredOnLoaded = false;
    private int sourceLoadingCounter = 0;
    final TimedEventMonitor timedEventMonitor;
    final String mediaId;

    public AudioPlayer(final TimedEventMonitor timedEventMonitor, final AudioExceptionListener audioExceptionListener, final SafeUri ogg, final SafeUri mp3, final SafeUri wav, final boolean autoPlay, final String mediaId) throws AudioException {
        this.timedEventMonitor = timedEventMonitor;
        this.audioExceptionListener = audioExceptionListener;
        this.autoPlay = autoPlay;
        this.mediaId = mediaId;
        try {
            createPlayer();
            if (ogg != null) {
                final SourceElement sourceElement = audioPlayer.addSource(ogg.asString(), AudioElement.TYPE_OGG);
                onNoFoundSetup(sourceElement);
            }
            if (mp3 != null) {
                final SourceElement sourceElement = audioPlayer.addSource(mp3.asString(), AudioElement.TYPE_MP3);
                onNoFoundSetup(sourceElement);
            }
            if (wav != null) {
                final SourceElement sourceElement = audioPlayer.addSource(wav.asString(), AudioElement.TYPE_WAV);
                onNoFoundSetup(sourceElement);
            }
            //audioPlayer.setCurrentTime(0); // on android the if the ready state is not correct then this will fail and audio will not play
            audioPlayer.load();
            if (!autoPlay) {
                audioPlayer.pause();
            }
        } catch (AudioException audioException) {
            if (timedEventMonitor != null) {
                timedEventMonitor.registerEvent("audioExceptionFired");
            }
            audioExceptionListener.audioExceptionFired(audioException);
        }
    }

    public Audio getAudioPlayer() {
        audioPlayer.setVisible(true);
        return audioPlayer;
    }

    private void createPlayer() throws AudioException {
        audioPlayer = Audio.createIfSupported();
        if (audioPlayer == null) {
            throw new AudioException("audio not supportered");
        }
        final AudioElement audioElement = audioPlayer.getAudioElement();
        onEndedSetup(audioElement);
    }

    private void incrementSourceLoadingCounter() {
        sourceLoadingCounter++;
    }

    private void registerSourceLoadingError() {
        sourceLoadingCounter--;
        if (sourceLoadingCounter <= 0) {
            onAudioFailed("audioSourceLoadingError");
        }
    }

    private native void onNoFoundSetup(final SourceElement sourceElement) /*-{
        var audioPlayer = this;
        audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::incrementSourceLoadingCounter()();
        sourceElement.addEventListener("error", function(){
            // todo: check to second instance of onerror
            audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::registerSourceLoadingError()();
        }, false);
    }-*/;

    private native void onEndedSetup(final AudioElement audioElement) /*-{
        var audioPlayer = this;
        audioElement.addEventListener("play", function(){
            audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onStartedAction()();
        }, false);
        audioElement.addEventListener("ended", function(){
            audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onEndedAction()();
        }, false);
        audioElement.addEventListener("canplaythrough", function(){
            audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onLoadedAction()();
        }, false);
        audioElement.addEventListener("error", function(){
            // todo: check to second instance of onerror
            audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onAudioFailed(Ljava/lang/String;)("audioElement EventListener");
        }, false);
     }-*/;

    private native void play(final AudioElement audioElement) /*-{
        var audioPlayer = this;
        $wnd.playMedia(audioElement, function(){}, function(){audioPlayer.@nl.mpi.tg.eg.experiment.client.util.AudioPlayer::onAudioFailed(Ljava/lang/String;)("audio wnd.playMedia")});
     }-*/;

    public void onStartedAction() {
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("audioStarted");
        }
        if (audioEventListener != null) {
            audioEventListener.audioStarted(audioPlayer.getDuration());
        }
    }

    public void onEndedAction() {
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("audioEnded");
            timedEventMonitor.registerMediaLength(mediaId, (long) (audioPlayer.getCurrentTime() * 1000));
        }
        if (audioEventListener != null) {
            audioEventListener.audioEnded(audioPlayer.getDuration());
        }
    }

    public void onAudioFailed(String reason) {
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("audioFailed");
            timedEventMonitor.registerEvent(reason);
        }
        if (audioEventListener != null) {
            audioEventListener.audioFailed();
        }
    }

    public void onLoadedAction() {
        if (timedEventMonitor != null) {
            timedEventMonitor.registerEvent("audioLoaded");
        }
        if (audioEventListener != null && !hasTriggeredOnLoaded) {
            hasTriggeredOnLoaded = true;
            audioEventListener.audioLoaded(audioPlayer.getDuration());
            if (autoPlay) {
                play(audioPlayer.getAudioElement());
            } else {
                audioPlayer.pause();
            }
        }
    }

    public void setEventListener(AudioEventListener audioEventListener) {
        this.audioEventListener = audioEventListener;
    }

//    public void playSampleAudio(RoundSample roundSample) {
//        final String[] soundFiles = roundSample.getLanguageSample().getSoundFiles();
//        playSample(soundFiles[roundSample.getSampleIndex()]);
//    }
//    @Deprecated
//    private void playSample(String sample) {
//        if (audioPlayer == null) {
//            try {
//                createPlayer();
//            } catch (AudioException audioException) {
//                audioExceptionListener.audioExceptionFired(audioException);
//                return;
//            }
//        }
//        audioPlayer.setSrc(sample);
//        //audioPlayer.setCurrentTime(0); // on android the if the ready state is not correct then this will fail and audio will not play
//        audioPlayer.play();
//    }
    public double getCurrentTime() {
        return (audioPlayer != null) ? audioPlayer.getCurrentTime() : -1;
//        return audioPlayer.getCurrentTime();
    }

    public void play() {
        if (audioPlayer != null) {
            play(audioPlayer.getAudioElement());
        } else {
            onAudioFailed("audioPlayer is null");
        }
    }

    public void rewind() {
        if (audioPlayer != null) {
            audioPlayer.setCurrentTime(0);
        };
    }

    public void stopAll() {
        if (audioPlayer != null) {
            audioPlayer.pause();
//            audioPlayer.setSrc("");
            audioPlayer.removeFromParent();
            audioPlayer = null;
        }
        //onEndedAction();
        audioEventListener = null;
    }

    public void stop() {
        if (audioPlayer != null) {
            audioPlayer.pause();
        }
        //onEndedAction();
    }
}
