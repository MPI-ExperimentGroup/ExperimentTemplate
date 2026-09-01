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
                // Safari blocks autoplay when the call is not in a direct user-gesture
                // stack.  The gesture-retry approach (registering a listener and calling
                // play() on the next button press) races with the experiment's clearPage/
                // stopAll(), which pauses the same element in the bubble phase of that
                // same event — the user never hears sound.
                //
                // Safari does permit autoplay of MUTED elements without a gesture.
                // Strategy: set muted=true, call play() (succeeds), then unmute inside
                // the .then() callback once playback has actually started.  Safari reads
                // the muted flag from the running pipeline at that point and delivers
                // audible output for the remainder of the clip.
                console.log('playMedia: autoplay blocked, attempting muted play');
                mediaElement.muted = true;
                var mutedPromise = mediaElement.play();
                if (mutedPromise !== undefined) {
                    mutedPromise.then(function () {
                        // Unmute after play() has actually started — Safari reads the muted
                        // flag from the running pipeline, so this produces audible output.
                        mediaElement.muted = false;
                        successHandler();
                    }).catch(function (mutedError) {
                        mediaElement.muted = false;
                        // Muted play also failed (e.g. data not yet buffered).
                        // Fall back to a one-shot gesture listener as last resort.
                        console.log('playMedia: muted play failed (' + mutedError.message + '), awaiting gesture');
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
                                    console.log('playMedia gesture retry failed: ' + retryError.message);
                                    errorHandler();
                                });
                            } else {
                                successHandler();
                            }
                        };
                        ['click', 'touchstart', 'keydown'].forEach(function (evt) {
                            document.addEventListener(evt, retryOnGesture, { once: true, capture: true });
                        });
                    });
                } else {
                    mediaElement.muted = false;
                    successHandler();
                }
            } else if (e.name === 'AbortError') {
                // play() was interrupted by a pause() call before it could start.
                // This is not an audio load failure — retry once now that the abort is over.
                console.log('playMedia: play aborted by pause, retrying');
                var abortRetryPromise = mediaElement.play();
                if (abortRetryPromise !== undefined) {
                    abortRetryPromise.then(function () { successHandler(); })
                                     .catch(function (abortRetryError) {
                                         console.log('playMedia abort retry failed: ' + abortRetryError.message);
                                         errorHandler();
                                     });
                } else {
                    successHandler();
                }
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
