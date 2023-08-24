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

import com.google.gwt.animation.client.AnimationScheduler;
import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;

/**
 * @since 11 October 2019 10:40:23 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AudioRecorder extends AbstractRecorder {

    public AudioRecorder() {
    }

    public void startRecorderTriggersWeb(final MediaTriggerListener recorderMediaTriggerListenerL) {
        AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback() {
            @Override
            public void execute(double timestamp) {
                boolean shouldContinue = runRecorderTriggersWeb(recorderMediaTriggerListenerL);
                if (shouldContinue) {
                    AnimationScheduler.get().requestAnimationFrame(this);
                }
            }
        });
    }
    
    public native boolean runRecorderTriggersWeb(final MediaTriggerListener recorderMediaTriggerListenerL)/*-{
        // because firefox requestAnimationFrame can be slow, this has been migrated from native requestAnimationFrame to GWT AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback(){});
        if ($wnd.recorder) {
            // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
            var currentMediaTime = ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000;
            var hasMoreListeners = recorderMediaTriggerListenerL.@nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener::triggerWhenReady(Ljava/lang/Double;)(currentMediaTime);
            if(hasMoreListeners === true) {
                return true;
            } else {
                console.log("recorderStartOffset: "+ ($wnd.recorderStartOffset * 1000));
                console.log("currentMediaTime: "+ currentMediaTime);
                console.log("end updateRecorderTriggers no more listeners");
                // if there are no more listeners then the animation requests will stop here.
                return false;
            }
        } else {
            // if the recorder is not yet running then we let the animation requests continue
            // requestAnimationFrame(updateRecorderTriggers);
            console.log("end updateRecorderTriggers");
            return false;
            }
    }-*/;

    public native void startRecorderDtmfTriggersWeb(final AbstractPresenter abstractPresenter, final MediaTriggerListener recorderMediaTriggerListenerL)/*-{
        // we don't use a Goertzel algorithm in this case since we already have the ByteFrequencyData from the audioContext
        if (!$wnd.audioAnalyser && $wnd.recorder.sourceNode) {
            $wnd.audioAnalyser = $wnd.recorder.audioContext.createAnalyser();
            $wnd.audioAnalyser.fftSize = 2048;
            $wnd.recorder.sourceNode.connect($wnd.audioAnalyser);
        }
        // console.log("startRecorderDtmfTriggersWeb");
        //              1209 Hz	1336 Hz	1477 Hz	1633 Hz
        //    697 Hz	1	2	3	A
        //    770 Hz	4	5	6	B
        //    852 Hz	7	8	9	C
        //    941 Hz	*	0	#	D
        var sampleRate = $wnd.recorder.audioContext.sampleRate;
        var index697 = Math.round(697 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index770 = Math.round(770 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index852 = Math.round(852 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index941 = Math.round(941 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index1209 = Math.round(1209 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index1336 = Math.round(1336 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index1477 = Math.round(1477 / sampleRate * $wnd.audioAnalyser.fftSize);
        var index1633 = Math.round(1633 / sampleRate * $wnd.audioAnalyser.fftSize);

        var frequencyCanvas = $doc.querySelector('#frequencyCanvas');
        if (frequencyCanvas) {
            var frequencyCanvasContext = frequencyCanvas.getContext('2d');
            var frequencyCanvasHeight = 256;
            // we are only visualising the lower half of the spectrum
            var frequencyCanvasWidth = 1024;
            frequencyCanvasContext.clearRect(0, 0, frequencyCanvasWidth, frequencyCanvasHeight);
        }
        var bufferLength = $wnd.audioAnalyser.frequencyBinCount;
        var dataArray = new Uint8Array(bufferLength);
        var initialMs = performance.now();
        console.log("start updateRecorderDtmfTriggers");
        function updateRecorderDtmfTriggers() {
            var frameMs = performance.now() - initialMs;
            initialMs = performance.now();
            if ($wnd.audioAnalyser && $wnd.recorder) {
                var nextAnimationRequest = requestAnimationFrame(updateRecorderDtmfTriggers);
                //console.log(bufferLength);
                $wnd.audioAnalyser.getByteFrequencyData(dataArray);
                var index697Level = dataArray[index697];
                var index770Level = dataArray[index770];
                var index852Level = dataArray[index852];
                var index941Level = dataArray[index941];
                var index1209Level = dataArray[index1209];
                var index1336Level = dataArray[index1336];
                var index1477Level = dataArray[index1477];
                var index1633Level = dataArray[index1633];
                var peekLevel = 0;
                for(var bufferIndex = 0; bufferIndex < bufferLength; bufferIndex++) {
                    var currentLevel = dataArray[bufferIndex];
                    peekLevel = (peekLevel < currentLevel)? currentLevel : peekLevel;
                }
                var dtmfAverage = (index697Level + index770Level + index852Level + index941Level + index1209Level + index1336Level + index1477Level + index1633Level) / 8;
                var triggerThreshold = dtmfAverage + ((peekLevel - dtmfAverage) / 3);
                var isAmplitudeOk = (peekLevel - dtmfAverage) > 100;
                var isFrameRateOk = frameMs < 100;
                //console.log(peekLevel - dtmfAverage);
                if (frequencyCanvas) {
                    // draw graph
                    frequencyCanvasContext.fillStyle = 'rgb(255, 255, 255)';
                    frequencyCanvasContext.fillRect(0, 0, frequencyCanvasWidth, frequencyCanvasHeight);
                    var barWidth = 1;//(frequencyCanvasWidth / bufferLength) / 4;
                    var barHeight;
                    var positionX = 0;
                    // we are only visualising the lower half of the spectrum
                    for(var bufferIndex = 0; bufferIndex < bufferLength && bufferIndex < frequencyCanvasWidth; bufferIndex++) {
                        barHeight = dataArray[bufferIndex] / 4;
                        if (bufferIndex === index697 || bufferIndex === index770 || bufferIndex === index852 || bufferIndex === index941 || bufferIndex === index1209 || bufferIndex === index1336 || bufferIndex === index1477 || bufferIndex === index1633) {
                            frequencyCanvasContext.fillStyle = 'rgb(255, 0, 0)';
                        } else {
                            frequencyCanvasContext.fillStyle = 'rgb(50, 50, 50)';
                        }
                        frequencyCanvasContext.fillRect(positionX, frequencyCanvasHeight - barHeight, barWidth, frequencyCanvasHeight);
                        positionX += barWidth + 1;
                    }
                    var triggerThresholdHeight = triggerThreshold / 4;
                    var peekLevelHeight = peekLevel / 4;
                    var dtmfAverageHeight = dtmfAverage / 4;
                    if (isAmplitudeOk) {
                        frequencyCanvasContext.fillStyle = 'rgb(0, 0, 255)';
                    } else {
                        frequencyCanvasContext.fillStyle = 'rgb(0, 255, 255)';
                    }
                    frequencyCanvasContext.fillRect(0, frequencyCanvasHeight - triggerThresholdHeight, frequencyCanvasWidth, 1);
                    frequencyCanvasContext.fillStyle = 'rgb(0, 255, 0)';
                    frequencyCanvasContext.fillRect(0, frequencyCanvasHeight - peekLevelHeight, frequencyCanvasWidth, 1);
                    frequencyCanvasContext.fillRect(0, frequencyCanvasHeight - dtmfAverageHeight, frequencyCanvasWidth, 1);
                    if (isFrameRateOk) {
                        frequencyCanvasContext.fillStyle = 'rgb(0, 0, 255)';
                    } else {
                        frequencyCanvasContext.fillStyle = 'rgb(255, 0, 0)';
                    }
                    frequencyCanvasContext.fillText(Math.floor(frameMs), 10, 10);
                }
                var row = -1;
                var column = -1;
                if (isAmplitudeOk && isFrameRateOk) {
                    if (index697Level > triggerThreshold && index770Level < dtmfAverage && index852Level < dtmfAverage && index941Level < dtmfAverage) {
                        // 697 Hz	1	2	3	A
                        var row = 0;
                    } else if (index697Level < dtmfAverage && index770Level > triggerThreshold && index852Level < dtmfAverage && index941Level < dtmfAverage) {
                        // 770 Hz	4	5	6	B
                        var row = 1;
                    } else if (index697Level < dtmfAverage && index770Level < dtmfAverage && index852Level > triggerThreshold && index941Level < dtmfAverage) {
                        // 852 Hz	7	8	9	C    
                        var row = 2
                    } else if (index697Level < dtmfAverage && index770Level < dtmfAverage && index852Level < dtmfAverage && index941Level > triggerThreshold) {
                        // 941 Hz	*	0	#	D
                        var row = 3;
                    }
                    if (index1209Level > triggerThreshold && index1336Level < dtmfAverage && index1477Level < dtmfAverage && index1633Level < dtmfAverage) {
                        // 1209 Hz
                        column = 0;
                    } else if (index1209Level < dtmfAverage && index1336Level > triggerThreshold && index1477Level < dtmfAverage && index1633Level < dtmfAverage) {
                        // 1336 Hz
                        column = 1;
                    } else if (index1209Level < dtmfAverage && index1336Level < dtmfAverage && index1477Level > triggerThreshold && index1633Level < dtmfAverage) {
                        // 1477 Hz
                        column = 2;
                    } else if (index1209Level < dtmfAverage && index1336Level < dtmfAverage && index1477Level < dtmfAverage && index1633Level > triggerThreshold) {
                        // 1633 Hz
                        column = 3;
                    }
                }
                //console.log(row + ", " + column + " - " + dtmfAverage + ", " + peekLevel);
                var hasDtmfListeners = (dtmfAverage === peekLevel)? true : abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::updateDtmfListener(Ljava/lang/Double;Ljava/lang/Double;)(row, column);
                //console.log(hasDtmfListeners);
                if(hasDtmfListeners !== true) {
                    // if there are no more listeners then the animation requests will stop here.
                    $win.cancelAnimationFrame(nextAnimationRequest);
                    console.log("end updateRecorderDtmfTriggers no more listeners");
                }
            } else {
                // if the recorder is not yet running then we let the animation requests continue
                // requestAnimationFrame(updateRecorderDtmfTriggers);
                // if the audioAnalyser is null then end the animation requests
                console.log("end updateRecorderDtmfTriggers");
            }
            //var finalMs = performance.now() - initialMs;
            //console.log(frameMs + "ms" + "," + finalMs + "ms");
        }
        requestAnimationFrame(updateRecorderDtmfTriggers);
    }-*/;

    public native void startRecorderWeb(final AbstractPresenter abstractPresenter, final DataSubmissionService dataSubmissionService, final String recordingLabelString, final String deviceRegex, final boolean noiseSuppressionL, final boolean echoCancellationL, final boolean autoGainControlL, final String stimulusIdString, final String userIdString, final String screenName, final MediaSubmissionListener mediaSubmissionListener, final int downloadPermittedWindowMs, final String recordingFormat) /*-{
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            console.log("isRecordingSupported");
            $wnd.recordingLabelString = recordingLabelString;
            //if ($wnd.recorder && $wnd.recorder.state !== 'inactive') {
            if ($wnd.recorder) {
                console.log("waiting for recorder to finish");
                // console.log($wnd.recorder.state);
                // if the recorder is non null then we must wait for the stop to complete before starting a new recording.
                mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderNotReady()();
                return;
                // recorderTemp = $wnd.recorder;
                // $wnd.recorder.onstop = function(){
                    // console.log("startAudioRecorderWeb: onstop");
                    // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::removeRecorderLevelIndicatorWeb()();
                    // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::clearRecorderTriggersWeb()();
                    // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::FALSE, null, null);
                    // recorderTemp.close();
                    // posting done has not been shown to terminate the worker: recorderTemp.encoder.postMessage("done");
                    // calling terminate has not been shown to terminate the worker: recorderTemp.encoder.terminate();
                // };
                // $wnd.recorder.stop();
                // $wnd.recorder = null;
                // $wnd.audioAnalyser = null;
            } else {
                    navigator.mediaDevices.enumerateDevices().then(function (deviceInfos) {
                    var firstDeviceId = -1;
                    var firstDeviceLabel = -1;
                    var targetDeviceId = -1;
                    var targetDeviceLabel = -1;
                    // look for the device only if the recorder has not already been constructed
                    if (!$wnd.recorderInstance) {
                        // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::addText(Ljava/lang/String;)("(debug) enumerateDevices");
                        console.log("enumerateDevices: ");
                        // it is likely that the first item in the list is the default input, so we stop on the first matching device
                        for (var index = 0; (index < deviceInfos.length && targetDeviceId === -1); index++) {
                            var deviceInfo = deviceInfos[index];
    //                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::addText(Ljava/lang/String;)("(debug) " + deviceInfo.label.search(deviceRegex));    
                            console.log("deviceInfo: " + deviceInfo.label + " : " + deviceInfo.kind + " match: " + deviceInfo.label.search(deviceRegex));
                            if(deviceInfo.kind === 'audioinput' && firstDeviceId === -1){
                                firstDeviceId = deviceInfo.deviceId;
                                firstDeviceLabel = deviceInfo.label;
                            }
                            if(deviceInfo.kind === 'audioinput' && deviceInfo.label.search(deviceRegex) >= 0){
                                console.log(deviceInfo.kind);    
                                console.log(deviceInfo.label);            
                                console.log(deviceInfo.deviceId);
                                targetDeviceId = deviceInfo.deviceId;
                                targetDeviceLabel = deviceInfo.label;
                            }
                        }
                        //abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, "isRecordingSupported", null);
                        if(targetDeviceId === -1) {
                            console.log("Device not found, defaulting to first device seen.");
                            targetDeviceId = firstDeviceId;
                            targetDeviceLabel = firstDeviceLabel;
                        }
                    }
                    if(targetDeviceId === -1 && !$wnd.recorderInstance) {
                        console.log("Device not found: " + deviceRegex);
                        mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)("Device not found: " + deviceRegex);
                    } else {
                        // $wnd.audioAnalyser = null;
                        if (!$wnd.recorderInstance){
                            if (recordingFormat === 'wav') {
                                $wnd.recorderInstance = new $wnd.Recorder({numberOfChannels: 1, encoderPath: "opus-recorder/waveWorker.min.js", monitorGain: 0, recordingGain: 1, encoderSampleRate: 48000, wavBitDepth: 16, mediaTrackConstraints: {deviceId: targetDeviceId, echoCancellation: echoCancellationL, noiseSuppression: noiseSuppressionL, autoGainControl: autoGainControlL}, bufferLength: 1024});
                            } else {
                                $wnd.recorderInstance = new $wnd.Recorder({numberOfChannels: 1, encoderPath: "opus-recorder/encoderWorker.min.js", monitorGain: 0, recordingGain: 1, encoderSampleRate: 48000, mediaTrackConstraints: {deviceId: targetDeviceId, echoCancellation: echoCancellationL, noiseSuppression: noiseSuppressionL, autoGainControl: autoGainControlL}, bufferLength: 1024});
                            }
                        }
                        $wnd.recorder = $wnd.recorderInstance;
                        $wnd.recorder.ondataavailable = function( typedArray ){
                            console.log("ondataavailable: " + typedArray.length);
                            var dataBlob = new Blob([typedArray], {type: 'audio/' + recordingFormat});
                            dataSubmissionService.@nl.mpi.tg.eg.experiment.client.service.DataSubmissionService::submitMediaData(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/typedarrays/shared/Uint8Array;Lnl/mpi/tg/eg/experiment/client/listener/MediaSubmissionListener;Ljava/lang/Integer;Ljava/lang/String;)(userIdString, screenName, stimulusIdString, dataBlob, mediaSubmissionListener, downloadPermittedWindowMs, recordingFormat);
                        };
                        try {
                            $wnd.startRecorder(function(){$wnd.recorderStartOffset = $wnd.recorder.audioContext.currentTime; mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderStarted(Ljava/lang/String;Ljava/lang/Double;)(targetDeviceLabel, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000)}, function(errorMessage){mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(errorMessage)});
                            // abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, $wnd.recorder.state, null);
                            //$wnd.recorder.start();
                        } catch(e) {
                            console.log(e.message);
                            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(e.message);
                            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
                        };
                    }
                });
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            mediaSubmissionListener.@nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener::recorderFailed(Ljava/lang/String;)(null);
        }
     }-*/;

    protected native void isRecording(final AbstractPresenter abstractPresenter) /*-{
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.isRecording(function () {
//                console.log("isAudioRecording");
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, null, null);
            }, function (tagvalue) {
//                console.log("isAudioRecording: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::FALSE, null, null);
            });
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    public native void getRecorderTime(final AbstractPresenter abstractPresenter) /*-{
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.getTime(function (currentTime) {
//                console.log("isAudioRecording: " + " : " + currentTime);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, currentTime, null);
            }, function (tagvalue) {
//                console.log("isAudioRecording: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::FALSE, null, null);
            });
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            if ($wnd.recorder.state === 'recording') {
                if ($wnd.recordingLabelString == 'ms') {
                    // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
                    var recordingMilliSeconds = Math.floor(($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000);
                    var recordingMsString = (recordingMilliSeconds) + 'ms';
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, recordingMsString, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset));
                } else if ($wnd.recordingLabelString == '00:00:00') {
                    // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
                    var recordingMilliSeconds = Math.floor(($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000);
                    var recordingTimeDate = new Date(recordingMilliSeconds);
                    var recordingTimeString = recordingTimeDate.toISOString().substr(11, 8);
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, recordingTimeString, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset));
                } else {
                    abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, $wnd.recordingLabelString, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset));
                }
            } else {
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    public native void stopRecorder(final AbstractPresenter abstractPresenter) /*-{
        // TODO: prevent the level indicator from having a non zero display after the recording stops
        console.log("stopAudioRecorder");
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.stop(function (tagvalue) {
                console.log("stopAudioRecorderOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::FALSE, tagvalue, null);
            }, function (tagvalue) {
                console.log("stopAudioRecorderError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            });
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                recorderTemp = $wnd.recorder;
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::FALSE, null, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset));
                $wnd.recorder.onstop = function(){
                    console.log("stopAudioRecorder: onstop");
                    // closing the recorder is known to leave the worker running and the documented methods to terminate the worker have not helped, so we keep the recorder open for new recordings.
                    // recorderTemp.close();
                    // recorderTemp.encoder.postMessage("done");
                    // recorderTemp.encoder.terminate();
                    // OggOpusEncoder.prototype.destroy
                    $wnd.recorder = null;
                }
                if ($wnd.recorder.sourceNode) {
                    $wnd.recorder.sourceNode.disconnect($wnd.audioAnalyser);
                }
                $wnd.audioAnalyser = null;
                $wnd.injectOscillator1 = null;
                $wnd.injectOscillator2 = null;
                $wnd.recorder.stop();
            }
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::removeRecorderLevelIndicatorWeb()();
        abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::clearRecorderTriggersWeb()();
     }-*/;

    /* pausing the recorder causes problems with the display of time code for the recording however it has been added back in due to popular request */
    static public native void pauseRecorderWeb() /*-{
        console.log("pauseAudioRecorderWeb");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                $wnd.recorder.pause();
            }
        }
     }-*/;

    /* pausing the recorder causes problems with the display of time code for the recording however it has been added back in due to popular request */
    static public native void resumeRecorderWeb() /*-{
        console.log("resumeAudioRecorderWeb");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                $wnd.recorder.resume();
            }
        }
     }-*/;

    static public native void logRecorderWebTimeStamp(String eventTag, final TimedEventMonitor timedEventMonitor) /*-{
        console.log("logAudioRecorderWebTimeStamp");
        if($wnd.Recorder && $wnd.Recorder.isRecordingSupported()) {
            if ($wnd.recorder) {
                // note that this assumes the bit rate of 48000 which is expected with this encoder
                // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)(eventTag, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000);
            }
        }
     }-*/;

    public native void startRecorderTag(final AbstractPresenter abstractPresenter, int tier, final TimedEventMonitor timedEventMonitor) /*-{
        console.log("startAudioRecorderTag: " + tier);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.startTag(function (tagvalue) {
                console.log("startAudioRecorderTagOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, tagvalue, null);
            }, function (tagvalue) {
                console.log("startAudioRecorderTagError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            }, tier);
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            // note that this assumes the bit rate of 48000 which is expected with this encoder
            // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)("startAudioRecorderTag", ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000);
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    public native void endRecorderTag(final AbstractPresenter abstractPresenter, int tier, String stimulusId, String stimulusCode, String eventTag, final TimedEventMonitor timedEventMonitor) /*-{
        console.log("endAudioRecorderTag: " + tier + " : " + stimulusId + " : " + stimulusCode + " : " + eventTag);
        if($wnd.plugins && $wnd.plugins.fieldKitRecorder){
            $wnd.plugins.fieldKitRecorder.endTag(function (tagvalue) {
                console.log("endAudioRecorderTagOk: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioOk(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Double;)(@java.lang.Boolean::TRUE, tagvalue, null);
            }, function (tagvalue) {
                console.log("endAudioRecorderTagError: " + tagvalue);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(tagvalue);
            }, tier, stimulusId, stimulusCode, eventTag);
        } else if($wnd.Recorder && $wnd.Recorder.isRecordingSupported() && $wnd.recorder) {
            // note that this assumes the bit rate of 48000 which is expected with this encoder
            // using $wnd.recorder.audioContext.currentTime * 1000 instead of $wnd.recorder.encodedSamplePosition / 48 partly because encodedSamplePosition is not useful when recording WAV.
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerMediaLength(Ljava/lang/String;Ljava/lang/Double;)(eventTag, ($wnd.recorder.audioContext.currentTime - $wnd.recorderStartOffset) * 1000);
        } else {
            abstractPresenter.@nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter::audioError(Ljava/lang/String;)(null);
        }
     }-*/;

    public void injectTone(final HardwareTimeStamp.DTMF dtmf, final TimedEventMonitor timedEventMonitor) {
        // inject DTMF tones into the recorded audio stream
        startDtmfInjection(dtmf.tone1, dtmf.tone2, dtmf.name(), timedEventMonitor);
    }

    final protected native boolean startDtmfInjection(final int tone1, final int tone2, final String eventTag, final TimedEventMonitor timedEventMonitor) /*-{
        if ($wnd.recorder) {
            if (!$wnd.injectOscillator1 || !$wnd.injectOscillator2) {
                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("starting tone injection");
                //var audioContext = new ($wnd.AudioContext || $wnd.webkitAudioContext)();
                $wnd.injectOscillator1 = $wnd.recorder.audioContext.createOscillator();
                $wnd.injectOscillator2 = $wnd.recorder.audioContext.createOscillator();
                $wnd.injectOscillator1.type = 'sine';
                $wnd.injectOscillator2.type = 'sine';
                $wnd.gainNode = $wnd.recorder.audioContext.createGain();
                $wnd.gainNode.gain.value = 0.0;
                $wnd.injectOscillator1.connect($wnd.gainNode);
                $wnd.injectOscillator2.connect($wnd.gainNode);
                //var merger = $wnd.recorder.audioContext.createChannelMerger(2);
                // gainNode.connect($wnd.recorder.audioContext.destination);
                $wnd.gainNode.connect($wnd.recorder.recordingGainNode);
                //gainNode.connect(merger);
                //merger.connect($wnd.recorder.recordingGainNode);
                $wnd.injectOscillator1.frequency.value = 0;
                $wnd.injectOscillator2.frequency.value = 0;
                $wnd.injectOscillator1.start();
                $wnd.injectOscillator2.start();
            }
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(eventTag);
            $wnd.injectOscillator1.frequency.value = tone1;
            $wnd.injectOscillator2.frequency.value = tone2;
            $wnd.gainNode.gain.value = (tone1 == 0 && tone2 == 0)? 0 : 0.2;
        } else {
            timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("cannot inject tone");
        }
    }-*/;
    // TODO: https://developer.mozilla.org/en-US/docs/Web/API/BaseAudioContext/createChannelMerger
}
