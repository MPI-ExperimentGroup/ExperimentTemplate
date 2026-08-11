/* 
 * Copyright (C) 2023 Max Planck Institute for Psycholinguistics, Nijmegen
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


/*
 * @since 24 Jan 2023 14:40 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */

function startRecorder(successHandler, errorHandler) {
    recorder.onstart = successHandler;

    recorder.start().catch(function (e) {
        console.log("startRecorder: " + e.message);
        errorHandler(e.message);
    });
}

// Safari 16.4+ blocks HTMLMediaElement.play() called asynchronously after page load,
// even when the user clicked through earlier screens. This unlocks the audio context
// on first gesture so subsequent autoplay calls are permitted.
var frinexAudioUnlocked = false;
function frinexUnlockAudio() {
    if (frinexAudioUnlocked) return;
    frinexAudioUnlocked = true;
    var Ctx = window.AudioContext || window.webkitAudioContext;
    if (Ctx) {
        var ctx = new Ctx();
        var buf = ctx.createBuffer(1, 1, 22050);
        var src = ctx.createBufferSource();
        src.buffer = buf;
        src.connect(ctx.destination);
        src.start(0);
        ctx.close();
    }
}
['click', 'touchstart', 'touchend', 'keydown'].forEach(function (evt) {
    document.addEventListener(evt, frinexUnlockAudio, { once: true, capture: true });
});

function playMedia(mediaElement, successHandler, errorHandler) {
    var promise = mediaElement.play();
    if (promise !== undefined) {
        promise.then(_ => {
            successHandler();
        }).catch(e => {
            if (e.name === 'NotAllowedError') {
                // Safari autoplay was blocked. Register a one-shot gesture listener and
                // retry, so the experiment can continue once the participant interacts
                // rather than falling through to the "Failed to load audio" error state.
                console.log('playMedia: autoplay blocked by browser policy, awaiting user gesture to retry');
                var retryOnGesture = function () {
                    ['click', 'touchstart', 'keydown'].forEach(function (evt) {
                        document.removeEventListener(evt, retryOnGesture, true);
                    });
                    frinexUnlockAudio();
                    var retryPromise = mediaElement.play();
                    if (retryPromise !== undefined) {
                        retryPromise.then(function () {
                            successHandler();
                        }).catch(function (retryError) {
                            console.log('playMedia retry failed: ' + retryError.message);
                            errorHandler();
                        });
                    } else {
                        successHandler();
                    }
                };
                ['click', 'touchstart', 'keydown'].forEach(function (evt) {
                    document.addEventListener(evt, retryOnGesture, { once: true, capture: true });
                });
            } else {
                errorHandler();
            }
        });
    }
}

function createOffer(connection, successHandler, errorHandler) {
    connection.createOffer().then(function (offer) {
        successHandler(offer);
    }).catch(function (e) {
        console.log("createOffer " + e.message);
        errorHandler(e);
    });
}

function handleOffer(connection, offer, successHandler, errorHandler) {
    connection.setRemoteDescription(offer).then(
        connection.createAnswer().then(function (answer) {
            successHandler(answer);
            connection.setLocalDescription(answer);
        }).catch(function (e) {
            console.log("handleOffer " + e.message);
            errorHandler(e);
        })
    );
}

// function handleAnswer(connection, answer) {
//     await connection.setRemoteDescription(answer);
// }

function requestPermissions(wantsVideo, wantsAudio, videoDeviceId, audioDeviceId, videoWidth, videoHeight, successHandler, errorHandler) {
    const constraints = {};
    if (audioDeviceId) {
        constraints.audio = { deviceId: { exact: audioDeviceId } };
    } else {
        constraints.audio = wantsAudio;
    }
    if (videoDeviceId) {
        constraints.video = { deviceId: { exact: videoDeviceId } };
    } else {
        constraints.video = wantsVideo;
    }
    if (wantsVideo) {
        if (videoWidth) {
            constraints.video.width = { ideal: videoWidth };
        }
        if (videoHeight) {
            constraints.video.height = { ideal: videoHeight };
        }
    }
    navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        successHandler(stream);
    }).catch(function (e) {
        console.log("requestPermissions " + e.message);
        errorHandler(e);
    });
}
