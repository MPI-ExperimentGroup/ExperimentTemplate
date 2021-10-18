/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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

import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;

/**
 * @since 11 October 2019 10:40:23 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class VideoRecorder extends AbstractRecorder {

    public VideoRecorder() {
    }

    public native void startRecorderTriggersWeb(final MediaTriggerListener recorderMediaTriggerListenerL)/*-{
        // TODO: merge this video startRecorderTriggersWeb with the audio startRecorderTriggersWeb in an abstract class
        // console.log("startRecorderTriggersWeb");
        console.log("start updateRecorderTriggers");
        function updateRecorderTriggers() {
            if ($wnd.mediaRecorder) {
                var currentMediaTime = performance.now() - $wnd.videoStartOffset;
                var hasMoreListeners = recorderMediaTriggerListenerL.@nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener::triggerWhenReady(Ljava/lang/Double;)(currentMediaTime);
                if(hasMoreListeners === true) {
                    requestAnimationFrame(updateRecorderTriggers);
                } else {
                    console.log("videoStartOffset: "+ $wnd.videoStartOffset);
                    console.log("currentMediaTime: "+ currentMediaTime);
                    console.log("end updateRecorderTriggers no more listeners");
                    // if there are no more listeners then the animation requests will stop here.
                }
            } else {
                // if the recorder is not yet running then we let the animation requests continue
                // requestAnimationFrame(updateRecorderTriggers);
                console.log("end updateRecorderTriggers");
            }
        }
        requestAnimationFrame(updateRecorderTriggers);
    }-*/;

    public native void startRecorderWeb(final AbstractPresenter abstractPresenter, final DataSubmissionService dataSubmissionService, final String recordingVideoLabelString, final String deviceRegex, final boolean noiseSuppressionL, final boolean echoCancellationL, final boolean autoGainControlL, final String stimulusIdString, final String userIdString, final String screenName, final MediaSubmissionListener mediaSubmissionListener, final int downloadPermittedWindowMs, final String recordingFormat) /*-{
        if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            console.log("isVideoSupported");
            $wnd.recordingVideoLabelString = recordingVideoLabelString;
            if ($wnd.videoRecorder) {
                console.log("waiting for video recorder to finish");
                // if the recorder is non null then we must wait for the stop to complete before starting a new recording.
                mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderNotReady()();
                return;
            } else {
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::clearVideoRecorderPreview()();
                var videoPreviewElement = $doc.querySelector('#VideoRecorderPreview');
                if (videoPreviewElement) {
                    var videoElement = $doc.createElement("video");
                    videoElement.autoplay = 'true';
                    videoPreviewElement.appendChild(videoElement);
                }
                $wnd.requestPermissions(true, true,
                    function(recordingStream) {
                        // TODO: to prevent audio feedback we preview without audio and will record via this separate stream that has audio
                        $wnd.mediaRecorder = new MediaRecorder(recordingStream);
                        $wnd.videoRecorderChunks = [];
                        $wnd.mediaRecorder.ondataavailable = function(e) {
                          $wnd.videoRecorderChunks.push(e.data);
                        }
                        $wnd.mediaRecorder.onstart = function() {
                            // TODO: get the time from the recorded stream
                            $wnd.videoStartOffset = performance.now();
                            // TODO: set the target device with its label
                            var targetDeviceLabel = -1;
                            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderStarted(Ljava/lang/String;Ljava/lang/Double;)(targetDeviceLabel, 
                            //($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000
                            0
                            );
                        }
                        $wnd.mediaRecorder.onstop = function(e) {
                            console.log("stopVideoRecorderOk: " + e);
                            var videoRecorderBlob = new Blob($wnd.videoRecorderChunks, { 'type' : 'video/ogg; codecs=opus' });
                            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;)(@java.lang.Boolean::FALSE, e.typeArg);
                            dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::submitMediaData(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/typedarrays/shared/Uint8Array;Lnl/mpi/tg/eg/experiment/client/listener/MediaSubmissionListener;Ljava/lang/Integer;Ljava/lang/String;)(userIdString, screenName, stimulusIdString, videoRecorderBlob, mediaSubmissionListener, downloadPermittedWindowMs, recordingFormat);
                            $wnd.videoRecorderChunks = [];
                            if (videoElement) {
                                videoElement.srcObject = null;
                            }
                        }
                        if (videoElement) {
                            // TODO: investigate why this preview is not apparent
                            // to prevent audio feedback we preview without audio
                            // TODO try to use the video track of the recording stream for this preview
                            var previewStream = new MediaStream($wnd.mediaRecorder.stream.getVideoTracks());
                            videoElement.srcObject = previewStream;
                        }
                        $wnd.mediaRecorder.start();
                    }, function(error) {
                        console.log(error.message);
                        mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(error.message);
                        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
                        if($wnd.mediaRecorder){
                            if ($wnd.mediaRecorder.state !== "inactive") {$wnd.mediaRecorder.stop();}
                            $wnd.mediaRecorder.stream.getTracks().forEach(function (track) { track.stop(); });
                            $wnd.mediaRecorder = null;
                        }
                    }
                );
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(null);
        }
     }-*/;

    // TODO: impliment other video recorder features based on the audio recorder usage
    // TODO: add the XML features for startVideoRecorderWeb and other video recorder features based on the audio recorder features
    // TODO: send the recorded video to the admin system
    // TODO: update the admin system to store video data
    // TODO: update the admin system to display video records
    // TODO: expand on the aggressive_audio_test example to test and demonstrate the video recorder

    public native void getRecorderTime(final AbstractPresenter abstractPresenter) /*-{
     }-*/;

    public native void stopRecorder(final AbstractPresenter abstractPresenter) /*-{
        console.log("stopVideoRecorder");
        if($wnd.mediaRecorder){
            if ($wnd.mediaRecorder.state !== "inactive") {$wnd.mediaRecorder.stop();}
            $wnd.mediaRecorder.stream.getTracks().forEach(function (track) { track.stop(); });
            $wnd.mediaRecorder = null;
            // console.log("stopVideoRecorderError: " + tagvalue);
            // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::removeRecorderLevelIndicatorWeb()();
        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::clearRecorderTriggersWeb()();
     }-*/;

    @Override
    public void requestRecorderPermissions(AbstractPresenter abstractPresenter) {
        // TODO: implement this method
    }

    @Override
    public void startRecorderTag(AbstractPresenter abstractPresenter, int tier, TimedEventMonitor timedEventMonitor) {
        // TODO: implement this method
    }

    @Override
    public void startRecorderDtmfTriggersWeb(AbstractPresenter abstractPresenter, MediaTriggerListener recorderMediaTriggerListenerL) {
        // TODO: implement this method
    }

    @Override
    public void endRecorderTag(AbstractPresenter abstractPresenter, int tier, String stimulusId, String stimulusCode, String eventTag, TimedEventMonitor timedEventMonitor) {
        // TODO: implement this method
    }

    @Override
    public void requestFilePermissions(AbstractPresenter abstractPresenter) {
        // this method is not required for the video recorder
    }    
}
