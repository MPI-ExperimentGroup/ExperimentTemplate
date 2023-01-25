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
package nl.mpi.tg.eg.experiment.client.service;

import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since 11 Jan 2023 11:12 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupStreamHandler {

    public enum StreamState {
        start, stop, pause, hide, show, mute, unmute
    }

    public enum StreamTypes {
        microphone, camera, canvas
    }

    private boolean isReady = false;

    private native void handleOffer(final String messageData) /*-{
        console.log("offer: " + contentData.messageData);
        // TODO: handle offer with JSON.parse(contentData.messageData));
    }-*/;

    private native void handleAnswer(final String messageData) /*-{
        console.log("answer: " + contentData.messageData);
        // TODO: handle answer with JSON.parse(contentData.messageData));
    }-*/;

    private native void handleCandidate(final String messageData) /*-{
        console.log("candidate: " + contentData.messageData);
        // TODO: handle candidate with JSON.parse(contentData.messageData));
    }-*/;

    public native void connect(final String stunServer, final String streamContainer, int originPhase, String userId, String windowGroupId, String groupUUID, String windowMemberCode, String screenId /*, final TimedStimulusListener onError, final TimedStimulusListener onSuccess */) /*-{
            var groupStreamHandler = this;
        // onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        // onSuccess.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        $wnd.stompClient.subscribe('/shared/stream', function (streamMessage) {
            var contentData = JSON.parse(streamMessage.body);
            console.log("contentData.userId");
            console.log("contentData.groupId");
            console.log("contentData.groupUUID");
            console.log("contentData.screenId");
            console.log("contentData.memberCode");
            console.log("contentData.originMemberCode");
            console.log("contentData.streamState");
            console.log("contentData.originPhase");
            console.log("contentData.messageData");
            if (groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady) {
                if (groupId !== contentData.groupId) {
                    console.log("ignoring other group: " + contentData.groupId);
                } else if (contentData.userId === userId){
                    console.log("ignoring self message: " + contentData.userId);
                } else if (contentData.streamState === "offer") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleOffer(Ljava/lang/String;)(contentData.messageData);
                } else if (contentData.streamState === "answer") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleAnswer(Ljava/lang/String;)(contentData.messageData);
                } if (contentData.streamState === "candidate") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleCandidate(Ljava/lang/String;)(contentData.messageData);
                } else if (contentData.streamState === "ready") {
                    if ($wnd.peerConnection) {
                        console.log('already connected, ignoring');
                    } else {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::initiateConnection(Ljava/lang/String;)(stunServer);
                    }
                } else if (contentData.streamState === "refresh") {
                    if (localContext) {
                        // paint to the canvas so that some data is sent over the stream causing it to be visible to the receiving participant
                        localContext.fillText("T", 0, 0);
                    }
                }
                if (contentData.userId !== userId && contentData.streamState === "disconnect") {
                    if ($wnd.peerConnection) {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, windowGroupId, groupUUID, windowMemberCode, screenId);
                    } else {
                        console.log('not connected, ignoring');
                    }
                }
            }
        });
    }-*/;

    private native void initiateConnection(String stunServer) /*-{
        // TODO: initialise the stream     
        console.log("initialiseConnection: " + stunServer);
        if (!$wnd.peerConnection) {
            var configuration = null; 
            if (stunServer) {
                    configuration = {
                    iceServers: [{
                        urls: "stun:" + stunServer
                    }]
                };
                console.log("configuration: " + configuration);
            }
            
            $wnd.peerConnection = new RTCPeerConnection(configuration);
            $wnd.peerConnection.onicecandidate = function (event) {
                console.log("onicecandidate");
                if (event.candidate) {
                    // TODO: sendToGroup("candidate", {
                    //    type: "candidate", candidate: event.candidate.candidate,
                    //    sdpMid: event.candidate.sdpMid,
                    //    sdpMLineIndex: event.candidate.sdpMLineIndex
                    //});
                } else {
                    // TODO: sendToGroup("candidate", { type: "candidate", candidate: null });
                }
            };

            dataChannel = $wnd.peerConnection.createDataChannel("dataChannel", {
                reliable: true
            });

            dataChannel.onerror = function (error) {
                console.log("onerror: " + error);
            };

            dataChannel.onmessage = function (event) {
                console.log("onmessage: " + event.data);
            };

            dataChannel.onconnectionstatechange = function (event) {
                console.log("onconnectionstatechange");
            };

            dataChannel.onclose = function () {
                console.log("onclose");
            };

            $wnd.peerConnection.ondatachannel = function (event) {
                dataChannel = event.channel;
            };

            $wnd.peerConnection.onnegotiationneeded = function () {
            }

            $wnd.peerConnection.ontrack = function (event) {
                console.log("ontrack");
                // TODO: pass in the target element for $wnd.$("#streamContainer").append("<video id=\"groupRemoteVideo\" style=\"width:40vw\" autoplay muted></video>");
                // TODO: pass in the target element for $wnd.$("#groupRemoteVideo").srcObject = event.streams[0];
                // $wnd.$("#groupRemoteVideo").attr('src', event.streams[0]);
                // TODO: sendToGroup("refresh", "");
            };

            $wnd.peerConnection.onremovetrack = function () {
                console.log("onremovetrack");
            };
            $wnd.peerConnection.onremovestream = function () {
                console.log("onremovestream");
            };

            $wnd.peerConnection.oniceconnectionstatechange = function () {
                console.log("oniceconnectionstatechange");
            };

            $wnd.peerConnection.onsignalingstatechange = function () {
                console.log("onsignalingstatechange");
            };

            $wnd.peerConnection.onicegatheringstatechange = function () {
                console.log("onicegatheringstatechange");
            };

            // localStream.getTracks().forEach(track => $wnd.peerConnection.addTrack(track, localStream));
            for (trackCount = 0; trackCount < $wnd.localStream.getTracks().length; trackCount++) {
                $wnd.peerConnection.addTrack(localVideo.srcObject.getTracks()[trackCount], $wnd.localStream);
            }
        }
    }-*/;

    private native void offerVideo(final String streamContainer, int originPhase, String userId, String windowGroupId, String groupUUID, String windowMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        $wnd.$("#" + streamContainer).append("<video id=\"groupLocalVideo\" style=\"width:80vw\" autoplay muted></video>");
        $wnd.requestPermissions(true, true,
            function(captureStream) {
                $wnd.localStream = captureStream;
                $wnd.$("#groupLocalVideo").srcObject = $wnd.localStream;
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "", originPhase, userId, windowGroupId, groupUUID, windowMemberCode, screenId);
            }, function(error) {
                console.log(error.message);
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, windowGroupId, groupUUID, windowMemberCode, screenId);
            }
        );
    }-*/;

    private native void offerCanvas(final String streamContainer, int originPhase, String userId, String windowGroupId, String groupUUID, String windowMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        $wnd.$("#" + streamContainer).append("<canvas id=\"groupLocalCanvas\" style=\"width:80vw max-width:400px\" width=\"400\" height=\"300\"></canvas>");
        $wnd.localStream = $wnd.$("#groupLocalCanvas")[0].captureStream(15); // 15 FPS
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "", originPhase, userId, windowGroupId, groupUUID, windowMemberCode, screenId);
        localCanvas = $wnd.$("#groupLocalCanvas");
        localContext = localCanvas.getContext("2d");

        // localContext.clearRect(0, 0, localCanvas.width, localCanvas.height);
        localContext.fillStyle = "lightgrey";
        localContext.fillRect(0, 0, localCanvas.width, localCanvas.height);

        localContext.fillStyle = "black";
        localContext.font = "20px Arial";
        localContext.fillText(userId, 10, 50);
        localContext.fillText(groupId, 10, 100);
        localContext.fillText(memberCode, 10, 150);
        localCanvas.addEventListener("mousemove", function (event) {
            if (event.buttons > 0) {
                // console.log(event);
                // console.log(event.clientX);
                // console.log(localCanvas.offsetLeft);
                // console.log(window.pageXOffset);
                localContext.beginPath();
                var bounds = localCanvas.getBoundingClientRect();
                var positionX = event.clientX - bounds.x;
                var positionY = event.clientY - bounds.y;
                localContext.moveTo((positionX - event.movementX) / bounds.width * localCanvas.width, (positionY - event.movementY) / bounds.height * localCanvas.height);
                localContext.lineTo(positionX / bounds.width * localCanvas.width, positionY / bounds.height * localCanvas.height);
                localContext.strokeStyle = "blue";
                localContext.lineWidth = 1;
                localContext.stroke();
                localContext.closePath();
            }
        }, false);
    }-*/;

    private native void disconnectStreams(Integer originPhase, String userId, String windowGroupId, String groupUUID, String windowMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("disconnect", "", originPhase, userId, windowGroupId, groupUUID, windowMemberCode, screenId);
        var remoteVideo = document.getElementById("groupRemoteVideo");
        var localVideo = document.getElementById("groupLocalVideo");
        if ($wnd.peerConnection) {
            $wnd.peerConnection.ontrack = null;
            $wnd.peerConnection.onremovetrack = null;
            $wnd.peerConnection.onremovestream = null;
            $wnd.peerConnection.onicecandidate = null;
            $wnd.peerConnection.oniceconnectionstatechange = null;
            $wnd.peerConnection.onsignalingstatechange = null;
            $wnd.peerConnection.onicegatheringstatechange = null;
            $wnd.peerConnection.onnegotiationneeded = null;
            if (remoteVideo && remoteVideo.srcObject) {
                // remoteVideo.srcObject.getTracks().forEach(track => track.stop());
                for (trackCount = 0; trackCount < remoteVideo.srcObject.getTracks().length; trackCount++) {
                    remoteVideo.srcObject.getTracks()[trackCount].stop();
                }
            }
            if (localVideo && localVideo.srcObject) {
                // localVideo.srcObject.getTracks().forEach(track => track.stop());
                for (trackCount = 0; trackCount < localVideo.srcObject.getTracks().length; trackCount++) {
                    localVideo.srcObject.getTracks()[trackCount].stop();
                }
            }
            $wnd.peerConnection.close();
            $wnd.peerConnection = null;
        }

        $wnd.$("groupRemoteVideo").remove();
        $wnd.$("groupLocalVideo").remove();
        $wnd.$("groupLocalCanvas").remove();
        $wnd.localStream = null;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = false;
    }-*/;

    public void updateStream(final StreamState streamState, final StreamTypes streamType, final String streamContainer, Integer originPhase, final UserId userId, final String groupId, final String groupUUID, final String memberCode, final String screenId) {
        // TODO: update the stream
        switch (streamState) {
            case start:
                switch (streamType) {
                    case microphone:
                        break;
                    case camera:
                        offerVideo(streamContainer, originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
                        break;
                    case canvas:
                        offerCanvas(streamContainer, originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
                        break;
                }
                break;
            case stop:
                disconnectStreams(originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
                break;
            case pause:
                break;
            case hide:
                break;
            case show:
                break;
            case mute:
                break;
            case unmute:
                break;
        }
        //messageGroup(streamState.name(), streamType.name(), 0, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
    }

    private native void messageGroup(String streamState, String messageData, Integer originPhase, String userId, String windowGroupId, String groupUUID, String windowMemberCode, String screenId) /*-{
    var groupParticipantService = this;
    $wnd.stompClient.send("/app/stream", {}, JSON.stringify({
        'userId': userId,
        'groupId': windowGroupId,
        'groupUUID': groupUUID,
        'screenId': screenId,
        'memberCode': windowMemberCode,
        'originMemberCode': null,
        'originPhase': originPhase,
        'streamState': streamState,
        'messageData': messageData
    }));
    }-*/;
}
