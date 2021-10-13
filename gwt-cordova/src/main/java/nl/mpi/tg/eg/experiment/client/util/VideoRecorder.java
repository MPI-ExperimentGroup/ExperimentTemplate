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
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;

/**
 * @since 11 October 2019 10:40:23 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class VideoRecorder {

    public VideoRecorder() {
    }

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
                var recordingConstraints = {
                    video: true,
                    audio: true
                };
                var previewConstraints = {
                    video: true
                };
                var videoElement = $doc.createElement("video");
                videoElement.autoplay = 'true';
                $doc.body.appendChild(videoElement);
                try {
                    navigator.mediaDevices.getUserMedia(recordingConstraints).then(function (recordingStream) {
                        // TODO: to prevent audio feedback we preview without audio and will record via this separate stream that has audio
                        $wnd.mediaRecorder = new MediaRecorder(recordingStream);
                        $wnd.videoRecorderChunks = [];
                        $wnd.mediaRecorder.ondataavailable = function(e) {
                          $wnd.videoRecorderChunks.push(e.data);
                        }
                    });
                    navigator.mediaDevices.getUserMedia(previewConstraints).then(function (previewStream) {
                        // to prevent audio feedback we preview without audio
                        videoElement.srcObject = previewStream;
                    });
                } catch(e) {
                    console.log(e.message);
                    mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(e.message);
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
                }
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
}
