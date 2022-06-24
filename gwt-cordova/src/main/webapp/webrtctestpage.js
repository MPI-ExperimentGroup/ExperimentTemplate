/* 
 * Copyright (C) 2022 Max Planck Institute for Psycholinguistics, Nijmegen
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
 * @since 24 June 2022 15:34 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */

// var socket = new WebSocket(@experiment.groupsSocketUrl@);
var socket = new SockJS('./gs-guide-websocket');
socket.onopen = function () {
    console.log("Connected");
    initialise();
};

function initialise() {
    var configuration = null;
    peerConnection = new RTCPeerConnection(configuration);
    peerConnection.onicecandidate = function (event) {
        if (event.candidate) {
            send({
                event: "candidate",
                data: event.candidate
            });
        }
    };

    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable: true
    });

    dataChannel.onerror = function (error) {
        console.log("onerror: " + error);
    };

    dataChannel.onmessage = function (event) {
        console.log("onmessage: " + event.data);
    };

    dataChannel.onclose = function () {
        console.log("onclose");
    };

    peerConnection.ondatachannel = function (event) {
        dataChannel = event.channel;
    };
}
