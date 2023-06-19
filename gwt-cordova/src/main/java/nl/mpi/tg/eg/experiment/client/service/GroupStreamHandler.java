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

import java.util.HashMap;
import java.util.Map;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since 11 Jan 2023 11:12 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class GroupStreamHandler {

//    public enum StreamState {
//        start, stop, pause, hide, show, mute, unmute
//    }
//
//    public enum StreamTypes {
//        microphone, camera, canvas
//    }
//    String groupCameraChannels;
//    String groupAudioChannels;
//    String groupCanvasChannels;
    private boolean isReady = false;
    private Map<String, Boolean> expectedConnections = new HashMap<String, Boolean>();

    private native void handleOffer(final String sendingUserId, final String messageData, final String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        offer = JSON.parse(messageData);
        if ($wnd.groupConnections[originCode]) {
            if (sendingUserId !== userId) {
                console.log('already connected, ignoring')
            } else if (!$wnd.groupConnections[originCode].localDescription) {
                // delaying setting the local description so that candidates do not get sent until both sides have seen the offer
                $wnd.groupConnections[originCode].setLocalDescription(offer);
            }
        } else {
            groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::initiateConnection(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(stunServer, originPhase, userId, groupId, groupUUID, memberCode, screenId);
            $wnd.handleOffer($wnd.groupConnections[originCode], offer, function (answer) {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("answer", 
                    JSON.stringify({ type: 'answer', sdp: answer.sdp, 'memberCode': memberCode, 'mediaType': offer.mediaType }), originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
                }, function(error) {
                    console.log(error.message);
                    //groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, screenId);
                }
            );
        }
    }-*/;

    private native void handleAnswer(final String messageData, String remoteMemberCode) /*-{
        // console.log("answer: " + messageData);
        answer = JSON.parse(messageData);
        if ($wnd.groupConnections[originCode]) {
            $wnd.groupConnections[originCode].setRemoteDescription(answer);
        } else {
            console.log("No peer connection");
        }
    }-*/;

    private native void handleCandidate(final String messageData, final String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        // console.log("candidate: " + messageData);
        candidate = JSON.parse(messageData);
        if ($wnd.groupConnections[originCode]) {
            if (candidate === "null" || !candidate.candidate) {
                // the terminal null is sent inside the candidate object
                $wnd.groupConnections[originCode].addIceCandidate(null);; //.catch(reportError);
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("refresh", "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
            } else {
                $wnd.groupConnections[originCode].addIceCandidate(candidate); //.catch(reportError);
            }
        } else {
            console.log("No peer connection");
        }
    }-*/;

    public native void connectStomp(final String stunServer, int originPhase, String userId, String groupId, String groupUUID, String memberCode, String screenId /*, final TimedStimulusListener onError, final TimedStimulusListener onSuccess */) /*-{
            var groupStreamHandler = this;
        // onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        // onSuccess.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        $wnd.stompClient.subscribe('/shared/stream', function (streamMessage) {
            var contentData = JSON.parse(streamMessage.body);
            // console.log(contentData.userId);
            // console.log(contentData.groupId);
            // console.log(contentData.groupUUID);
            // console.log(contentData.screenId);
            // console.log(contentData.memberCode);
            // console.log(contentData.originMemberCode);
            // console.log(contentData.streamState);
            // console.log(contentData.originPhase);
            // console.log(contentData.messageData);
            if (groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady) {
                if (groupId !== contentData.groupId) {
                    console.log("ignoring other group: " + contentData.groupId);
                } else if (contentData.streamState === "offer") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleOffer(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.userId, contentData.messageData, stunServer, originPhase, userId, groupId, groupUUID, memberCode, screenId);
                } else if (contentData.userId === userId){
                    // the self message is needed in the offer stage to set up the stream but after that point we ignore these
                    console.log("ignoring self message: " + contentData.userId);
                } else if (contentData.streamState === "answer") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleAnswer(Ljava/lang/String;)(contentData.messageData);
                } else if (contentData.streamState === "candidate") {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleCandidate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.messageData, stunServer, originPhase, userId, groupId, groupUUID, memberCode, screenId);
                } else if (contentData.streamState === "ready") {
                    if ($wnd.groupConnections[contentData.originMemberCode]) {
                        console.log('already connected, ignoring');
                    } else {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::initiateConnection(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(stunServer, originPhase, userId, groupId, groupUUID, memberCode, screenId);
                        $wnd.createOffer($wnd.groupConnections[contentData.originMemberCode],
                            function(offer) {
                                // at this point memberCode is the origin of the offer and contentData.memberCode is the origin of the request
                                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("offer", JSON.stringify({ type: 'offer', sdp: offer.sdp, 'memberCode': memberCode, 'mediaType': contentData.messageData }), originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, screenId);
                            }, function(error) {
                                console.log(error.message);
                                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, screenId);
                            }
                        );
                    }
                } else if (contentData.streamState === "refresh") {
                    if (localContext) {
                        // paint to the canvas so that some data is sent over the stream causing it to be visible to the receiving participant
                        localContext.fillText("T", 0, 0);
                    }
                } else if (contentData.userId !== userId && contentData.streamState === "disconnect") {
                    if ($wnd.groupConnections[contentData.originMemberCode]) {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, screenId);
                    } else {
                        console.log('not connected, ignoring');
                    }
                }
            }
            groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::setConnected(Ljava/lang/String;)("todo: connectionString");
        }, function(error) {
            // display the error's message header:
            console.log('contentData: ' + contentData);
            console.log('error.headers.message: ' + error.headers.message);
            });
    }-*/;

    private native void initiateConnection(String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;    
        console.log("initialiseConnection: " + stunServer);
        if (!$wnd.groupConnections){
            $wnd.groupConnections = [];
        }
        if (!$wnd.groupConnections[remoteMemberCode]) {
            var configuration = null; 
            if (stunServer) {
                    configuration = {
                    iceServers: [{
                        urls: "stun:" + stunServer
                    }]
                };
                console.log("configuration: " + configuration);
            }
            $wnd.groupConnections[remoteMemberCode] = new RTCPeerConnection(configuration);
            $wnd.$wnd.groupConnections[remoteMemberCode].onicecandidate = function (event) {
                console.log("onicecandidate");
                if (event.candidate) {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("candidate", JSON.stringify({ type: "candidate", candidate: event.candidate.candidate, sdpMid: event.candidate.sdpMid, sdpMLineIndex: event.candidate.sdpMLineIndex }), originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
                } else {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("candidate", JSON.stringify({ type: "candidate", candidate: null }), originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
                }
            };

            dataChannel = $wnd.groupConnections[remoteMemberCode].createDataChannel("dataChannel", {
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

            $wnd.groupConnections[remoteMemberCode].ondatachannel = function (event) {
                dataChannel = event.channel;
            };

            $wnd.groupConnections[originCode].onnegotiationneeded = function () {
            }

            $wnd.groupConnections[originCode].ontrack = function (event) {
                console.log("ontrack");
                // TODO: are there cases with multiple tracks to be expected?
                // it would be better to have separate groupRemoteCanvas_ and groupRemoteVideo_ instead of groupRemoteStream_
                $wnd.$("#groupRemoteStream_" + memberCode)[0].srcObject = event.streams[0];
                // $wnd.$("#groupRemoteStream")[0].attr('src', event.streams[0]);
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("refresh", "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
            };

            $wnd.groupConnections[originCode].onremovetrack = function () {
                console.log("onremovetrack");
            };
            $wnd.groupConnections[originCode].onremovestream = function () {
                console.log("onremovestream");
            };

            $wnd.groupConnections[originCode].oniceconnectionstatechange = function () {
                console.log("oniceconnectionstatechange");
            };

            $wnd.groupConnections[originCode].onsignalingstatechange = function () {
                console.log("onsignalingstatechange");
            };

            $wnd.groupConnections[originCode].onicegatheringstatechange = function () {
                console.log("onicegatheringstatechange");
            };

            // localStream.getTracks().forEach(track => $wnd.groupConnections[originCode].addTrack(track, localStream));
            for (trackCount = 0; trackCount < $wnd.localStream.getTracks().length; trackCount++) {
                $wnd.groupConnections[originCode].addTrack($wnd.localStream.getTracks()[trackCount], $wnd.localStream);
            }
        }
    }-*/;

    private native void offerVideo(int originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        $wnd.requestPermissions(true, true,
            function(captureStream) {
                $wnd.localStream = captureStream;
                $wnd.$("#groupLocalVideo")[0].srcObject = $wnd.localStream;
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "video", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
            }, function(error) {
                console.log(error.message);
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, screenId);
            }
        );
    }-*/;

    private native void offerCanvas(int originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        $wnd.localStream = $wnd.$("#groupLocalCanvas")[0].captureStream(15); // 15 FPS
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "canvas", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
        localCanvas = $wnd.$("#groupLocalCanvas")[0];
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

    private native void disconnectStreams(Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("disconnect", "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
        if ($wnd.groupConnections[remoteMemberCode]) {
            $wnd.groupConnections[remoteMemberCode].ontrack = null;
            $wnd.groupConnections[remoteMemberCode].onremovetrack = null;
            $wnd.groupConnections[remoteMemberCode].onremovestream = null;
            $wnd.groupConnections[remoteMemberCode].onicecandidate = null;
            $wnd.groupConnections[remoteMemberCode].oniceconnectionstatechange = null;
            $wnd.groupConnections[remoteMemberCode].onsignalingstatechange = null;
            $wnd.groupConnections[remoteMemberCode].onicegatheringstatechange = null;
            $wnd.groupConnections[remoteMemberCode].onnegotiationneeded = null;
            
            // iterate all member specific remote video elements
            var remoteVideoArray = $wnd.$("video[id^=groupRemoteStream]");
            if (remoteVideoArray) {
                for (remoteVideoIndex = 0; remoteVideoIndex < remoteVideoArray.length; remoteVideoIndex++) {
                    var remoteVideo = remoteVideoArray[remoteVideoIndex];
                    if (remoteVideo && remoteVideo.srcObject) {
                        // remoteVideo.srcObject.getTracks().forEach(track => track.stop());
                        for (trackCount = 0; trackCount < remoteVideo.srcObject.getTracks().length; trackCount++) {
                            remoteVideo.srcObject.getTracks()[trackCount].stop();
                        }
                    }
                }
            }
            
            var localVideoArray = $wnd.$("video[id^=groupLocalVideo");
            if (localVideoArray) {
                for (localVideoIndex = 0; localVideoIndex < localVideoArray.length; localVideoIndex++) {
                    var localVideo = localVideoArray[localVideoIndex];
                    if (localVideo && localVideo.srcObject) {
                        // localVideo.srcObject.getTracks().forEach(track => track.stop());
                        for (trackCount = 0; trackCount < localVideo.srcObject.getTracks().length; trackCount++) {
                            localVideo.srcObject.getTracks()[trackCount].stop();
                        }
                    }
                }
            }
            // TODO: should we be cleaning up the the local canvas srcObject.getTracks here also?
            $wnd.groupConnections[remoteMemberCode].close();
            $wnd.groupConnections[remoteMemberCode] = null;
        }

        // remove all member remote video elements for each member
        $wnd.$("video[id^=groupRemoteStream]").remove();
        // remove local elements
        $wnd.$("#groupLocalVideo").remove();
        $wnd.$("#groupLocalCanvas").remove();
        $wnd.localStream = null;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = false;
    }-*/;
    
    public void setConnected(final String connectionString) {
        expectedConnections.put(connectionString, true);
    }

public boolean isConnected() {
        for (Boolean isConnected : expectedConnections.values()) {
            if (!isConnected) {
                return false;
            }
        }
        return true;
    }

    public void synchronisePhase(int currentPhase) {

    }

    public abstract void updateDebugRegion(String message);
    
    public abstract void addCanvasElement(String elementId, String groupId, String groupUUID, String memberCode, String remoteMemberCode);

    public abstract void addVideoElement(String elementId, String groupId, String groupUUID, String memberCode, String remoteMemberCode);

    public void notifyDetatchedElement(String elementId, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) {
        // TODO: handle disconnect here
//        messageGroup("disconnect", elementId, originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
        // TODO: narrow down the disconnects to the specified channels and medium
        disconnectStreams(originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
    }

    public String getDebugText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String expectedConnection : expectedConnections.keySet()) {
            stringBuilder.append(expectedConnection);
            stringBuilder.append(": ");
            stringBuilder.append(expectedConnections.get(expectedConnection));
            stringBuilder.append("<br>");
        }
        return stringBuilder.toString();
    }

    public void negotiateCanvas(final String streamChannels, Integer originPhase, final UserId userId, final String groupId, final String groupUUID, final String memberCode, final String screenId) {
        for (String channel : streamChannels.split("\\|")) {
            boolean isRelevant = channel.matches("(.*,)?" + memberCode + "(,.*)?");
            if (isRelevant) {
                for (String member : channel.split(",")) {
                    // set up the elements and connection based on communication channels
                    if (member.equals(memberCode)) {
                        addCanvasElement("groupLocalCanvas", groupId, groupUUID, memberCode);
                        offerCanvas(originPhase, userId.toString(), groupId, groupUUID, null, memberCode, screenId);
                    } else {
                        final String connectionName = "groupRemoteCanvas_" + member;
                        if (!expectedConnections.containsKey(connectionName)) {
                            expectedConnections.put(connectionName, false);
                            addCanvasElement(connectionName, groupId, groupUUID, memberCode);
                        }
                    }
                }
            }
        }
        // TODO: on canvas and video removed from parent disconnectStreams
        // disconnectStreams(originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
    }

    public void negotiateCamera(final String streamChannels, Integer originPhase, final UserId userId, final String groupId, final String groupUUID, final String memberCode, final String screenId) {
        for (String channel : streamChannels.split("\\|")) {
            boolean isRelevant = channel.matches("(.*,)?" + memberCode + "(,.*)?");
            if (isRelevant) {
                for (String member : channel.split(",")) {
                    // set up the elements and connection based on communication channels
                    if (member.equals(memberCode)) {
                        addVideoElement("groupLocalVideo", groupId, groupUUID, memberCode);
                        offerVideo(originPhase, userId.toString(), groupId, groupUUID, null, memberCode, screenId);
                    } else {
                        final String connectionName = "groupRemoteCamera_" + member;
                        if (!expectedConnections.containsKey(connectionName)) {
                            expectedConnections.put(connectionName, false);
                            addVideoElement(connectionName, groupId, groupUUID, memberCode);
                        }
                    }
                }
            }
        }
        // TODO: set up the communication channels
        // addVideoElement("groupLocalVideo", groupId, groupUUID, memberCode);
        // todo: we need to originating member code to create the elemet id correctly and this should be based on streamChannels eg negotiateCanvas
        // addVideoElement("groupRemoteStream_" + member, groupId, groupUUID, memberCode);
        // offerVideo(originPhase, userId.toString(), groupId, groupUUID, memberCode, screenId);
        // TODO: on canvas and video removed from parent disconnectStreams
        // disconnectStreams(originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
    }

    private native void messageGroup(String streamState, String messageData, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String originMemberCode, String screenId) /*-{
    var groupParticipantService = this;
    $wnd.stompClient.send("/app/stream", {}, JSON.stringify({
        'userId': userId,
        'groupId': groupId,
        'screenId': screenId,
        'memberCode': memberCode,
        'originMemberCode': originMemberCode,
        'originPhase': originPhase,
        'streamState': streamState,
        'messageData': messageData
    }));
    }-*/;
}
