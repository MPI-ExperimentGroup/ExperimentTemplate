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
    
    public native void connect(final String stunServer, final String userId, final String groupId /*, final TimedStimulusListener onError, final TimedStimulusListener onSuccess */) /*-{
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
            // TODO: check if isReady is needed - if (isReady) {
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
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::terminateConnection()();
                } else {
                    console.log('not connected, ignoring');
                }
            }
//            }
        });
    }-*/;
    
    private native void terminateConnection() /*-{
        // TODO: terminateConnection
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
                // TODO: pass in the target element for $("#streamContainer").append("<video id=\"remoteVideo\" style=\"width:40vw\" autoplay muted></video>");
                // TODO: pass in the target element for document.getElementById("remoteVideo").srcObject = event.streams[0];
                // $("#remoteVideo").attr('src', event.streams[0]);
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

            // TODO: localStream.getTracks().forEach(track => $wnd.peerConnection.addTrack(track, localStream));
        }
    }-*/;
        
    public void updateStream(final StreamState streamState, final StreamTypes streamType) {
        // TODO: update the stream
        messageGroup(streamState.name(), streamType.name(), 0, "userId", "windowGroupId", "windowMemberCode", "screenId");
    }

    private native void messageGroup(String streamState, String messageData, int originPhase, String userId, String windowGroupId, String windowMemberCode, String screenId) /*-{
    var groupParticipantService = this;
    $wnd.stompClient.send("/app/stream", {}, JSON.stringify({
        'userId': userId,
        'groupId': windowGroupId,
//        'groupUUID': groupUUID,
        'screenId': screenId,
        'memberCode': windowMemberCode,
        'originMemberCode': null,
        'originPhase': originPhase,
        'streamState': 'ready',
        'messageData': messageData + ' : ' + streamState
    }));
    }-*/;

//       private void offerStream(){}; private void answerStream(){}; private void  candidateStream(){}; private void  readyStream(){}; private void refreshStream(){}; private void  disconnectStream(){};
//        offer, answer, candidate, ready, refresh, disconnect
}
