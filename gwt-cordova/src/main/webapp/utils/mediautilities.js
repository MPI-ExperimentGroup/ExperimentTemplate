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

function playMedia(mediaElement, successHandler, errorHandler) {
    var promise = mediaElement.play();
    if (promise !== undefined) {
        promise.then(_ => {
            successHandler();
        }).catch(e => {
            errorHandler();
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

function requestPermissions(wantsVideo, wantsAudio, successHandler, errorHandler) {
    navigator.mediaDevices.getUserMedia({video: wantsVideo, audio: wantsAudio}).then(function (stream) {
        successHandler(stream);
    }).catch(function (e) {
        console.log("requestPermissions " + e.message);
        errorHandler(e);
    });
}
