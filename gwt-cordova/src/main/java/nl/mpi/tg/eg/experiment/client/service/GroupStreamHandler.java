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
    // private boolean isReady = false;
    private Map<String, Boolean> expectedConnections = new HashMap<>();
    private Map<String, TimedStimulusListener> connectionListeners = new HashMap<>();
    private Map<String, TimedStimulusListener> errorListeners = new HashMap<>();

    private boolean connectionIsExpected(final String connectionName) {
        return expectedConnections.containsKey(connectionName);
    }

    private void triggerErrorHanlder(final String connectionName) {
        expectedConnections.put(connectionName, false);
        if (errorListeners.containsKey(connectionName)) {
            errorListeners.get(connectionName).postLoadTimerFired();
        }
    }

    private void triggerSuccessHandler(final String connectionName) {
        expectedConnections.put(connectionName, true);
        boolean connectionResult = true;
        for (final Boolean currentConnection : expectedConnections.values()) {
            if (!currentConnection) {
                connectionResult = false;
            }
        }
        if (connectionResult) {
            for (TimedStimulusListener currentListener : connectionListeners.values()) {
                currentListener.postLoadTimerFired();
            }
        }
    }

    private native void handleOffer(final String sendingUserId, final String messageData, final String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String selfMemberCode, String remoteMemberCode, String streamType, String screenId) /*-{
        var groupStreamHandler = this;
        offer = JSON.parse(messageData);
        console.log(selfMemberCode + ": " + remoteMemberCode + " ==handleOffer==> " + selfMemberCode);
        if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode]) {
            // if (memberCode === originMemberCode) { // what is this comparison doing and why
            //     console.log('already connected, ignoring')
            // } else 
            // if (!$wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].localDescription) {
            if (!$wnd.readyConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode]) {
                // delaying setting the local description so that candidates do not get sent until both sides have seen the offer
                $wnd.readyConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode] = true;
                console.log(selfMemberCode + ": " + selfMemberCode + " <==setLocalDescription offer== " + remoteMemberCode);
                $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].setLocalDescription(offer);
            }
        } else {
            groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::initiateConnection(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(stunServer, originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, streamType, screenId);
            console.log(selfMemberCode + ": " + selfMemberCode + " <==setRemoteDescription offer== " + remoteMemberCode);
            console.log(selfMemberCode + ": " + selfMemberCode + " <==setLocalDescription answer== " + remoteMemberCode);
            $wnd.handleOffer($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode], offer, function (answer) {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("answer", streamType,
                    JSON.stringify({ type: 'answer', sdp: answer.sdp, 'memberCode': selfMemberCode, 'mediaType': offer.mediaType }), originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                }, function(error) {
                    console.log(error.message);
                    //groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, streamType, screenId);
                }
            );
        }
    }-*/;

    private native void handleAnswer(final String messageData, String selfMemberCode, String remoteMemberCode, String streamType) /*-{
        // console.log("handleAnswer");
        console.log(selfMemberCode + ": " + remoteMemberCode + " ==handleAnswer==> " + selfMemberCode);
        // console.log("answer: " + messageData);
        answer = JSON.parse(messageData);
        if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode]) {
            // $wnd.handleAnswer($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode], answer)
            console.log(selfMemberCode + ": " + remoteMemberCode + " <==setRemoteDescription answer== " + selfMemberCode);
            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].setRemoteDescription(answer);
            var queued = $wnd.pendingCandidates[memberCode + "-Camera>" + remoteMemberCode] || [];
            while (queued.length) {
                peerConnection.addIceCandidate(queued.shift()).then(function () {
                    console.log("addIceCandidateSuccess");
                }).catch(function (err) {
                    console.log("addIceCandidateFail:", err);
                    // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                    // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("failed", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                });
            }
        } else {
            console.log(remoteMemberCode + " <==no peer connection== " + selfMemberCode);
        }
    }-*/;

    private native void handleCandidate(final String messageData, final String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String selfMemberCode, String remoteMemberCode, String streamType, String screenId) /*-{
        var groupStreamHandler = this;
        // console.log("candidate: " + messageData);
        var key = selfMemberCode + "-" + streamType + '>' + remoteMemberCode;
        var candidate = JSON.parse(messageData);
        if (!$wnd.pendingCandidates) {
            $wnd.pendingCandidates = {};
        }
        if (candidate === "null" || !candidate.candidate) {
            console.log(selfMemberCode + ": " + remoteMemberCode + " <==doneCandidate== " + selfMemberCode);
            // Only send null when remoteDescription is set
            if ($wnd.groupConnections[key] && $wnd.groupConnections[key].remoteDescription) {
                $wnd.groupConnections[key].addIceCandidate(null);
            } else {
                // Save a marker if needed or ignore
            }
            groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(
                "refresh", streamType, "", originPhase,
                userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
        } else if ($wnd.groupConnections[key]) {
            var peer = $wnd.groupConnections[key];
            if (peer.remoteDescription && peer.remoteDescription.type) {
                console.log(selfMemberCode + ": " + remoteMemberCode + " <==addIceCandidate== " + selfMemberCode);
                peer.addIceCandidate(candidate).then(function () {
                    console.log("addIceCandidateSuccess");
                }).catch(function (err) {
                    console.log("addIceCandidateFail:", err);
                    // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                    // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("failed", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                });
            } else {
                console.log(selfMemberCode + ": " + remoteMemberCode + " <==queueingCandidate== " + selfMemberCode);
                if (!$wnd.pendingCandidates[key]) {
                    $wnd.pendingCandidates[key] = [];
                }
                $wnd.pendingCandidates[key].push(candidate);
            }
        } else {
            console.log(remoteMemberCode + " <==no peer connection== " + selfMemberCode);
        }
    }-*/;

    public native void connectStomp(final String stunServer, int originPhase, String userId, String groupId, String groupUUID, String memberCode, String screenId /*, final TimedStimulusListener onError, final TimedStimulusListener onSuccess */) /*-{
        var groupStreamHandler = this;
        if (!$wnd.groupConnections){
            $wnd.groupConnections = [];
            $wnd.readyConnections = [];
            $wnd.localStream = [];
        }
        // onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        // onSuccess.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
        $wnd.stompClient.subscribe('/shared/stream', function (streamMessage) {
            var contentData = JSON.parse(streamMessage.body);
            // console.log(contentData.userId);
            // console.log(contentData.groupId);
            // console.log(contentData.groupUUID);
            // console.log(contentData.screenId);
            // console.log(contentData.streamType);
            // console.log(contentData.originPhase);
            // console.log(contentData.messageData);
            if (contentData.streamState === "connected") {
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerSuccessHandler(Ljava/lang/String;)(contentData.originMemberCode + "-" + contentData.streamType + '>' + contentData.targetMemberCode);
            } else if (contentData.streamState === "permissions" || contentData.streamState === "failed") {
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(contentData.originMemberCode + "-" + contentData.streamType + '>' + contentData.targetMemberCode);
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(contentData.targetMemberCode + "-" + contentData.streamType + '>' + contentData.originMemberCode);
            }
            if (contentData.targetMemberCode !== null && memberCode === contentData.originMemberCode && contentData.streamState === "offer") {
                // this self message is needed in the offer stage to set up the stream in the correct timing by setting the local description
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleOffer(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.userId, contentData.messageData, stunServer, originPhase, userId, groupId, groupUUID, memberCode, contentData.targetMemberCode, contentData.streamType, screenId);
            } else if (contentData.targetMemberCode === null || memberCode === contentData.targetMemberCode) { // only responding to targeted messages or broadcast (blank targetMemberCode) messages
                console.log(memberCode + ": " + contentData.originMemberCode + " ==" + contentData.streamState + "==> " + contentData.targetMemberCode);
                if ($wnd.groupConnections[contentData.originMemberCode + "-" + contentData.streamType + '>' + contentData.targetMemberCode]) {
                    console.log(memberCode + ": connectionState==" + $wnd.groupConnections[contentData.originMemberCode + "-" + contentData.streamType + '>' + contentData.targetMemberCode].connectionState);
                }
                // console.log(memberCode + ": isReady==" + groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady);
                // if (groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady) {
                    if (groupId !== contentData.groupId) {
                        console.log("ignoring other group: " + contentData.groupId);
                    } else if (contentData.streamState === "offer") {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleOffer(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.userId, contentData.messageData, stunServer, originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, contentData.streamType, screenId);
                    } else if (contentData.userId === userId){
                        // the self message is needed in the offer stage to set up the stream but after that point we ignore these
                        console.log("ignoring self message: " + contentData.userId);
                    } else if (contentData.streamState === "answer") {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleAnswer(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.messageData, memberCode, contentData.originMemberCode, contentData.streamType);
                    } else if (contentData.streamState === "candidate") {
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::handleCandidate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(contentData.messageData, stunServer, originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, contentData.streamType, screenId);
                    } else if (contentData.streamState === "ready") {
                        // if the localStream is defined then we have something to send and we reply with an offer
                        if ($wnd.localStream[contentData.streamType + '_' + contentData.originMemberCode]) {
                            if ($wnd.groupConnections[memberCode + "-" + contentData.streamType + '>' + contentData.originMemberCode]) {
                                console.log(memberCode + ": already connected == ignoring");
                            } else {
                                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::initiateConnection(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(stunServer, originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, contentData.streamType, screenId);
                                $wnd.createOffer($wnd.groupConnections[memberCode + "-" + contentData.streamType + '>' + contentData.originMemberCode],
                                    function(offer) {
                                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("offer", contentData.streamType, JSON.stringify({ type: 'offer', sdp: offer.sdp, 'memberCode': memberCode, 'mediaType': contentData.messageData }), originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, screenId);
                                    }, function(error) {
                                        console.log(error.message);
                                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, contentData.streamType, screenId);
                                    }
                                );
                            }
                        } else {
                            // if there is nothing to stream resend a ready here to trigger the other member to resend
                            // TODO: perhaps add a delay before resending this ready
                            // TODO: investigate adding this 'ready' echo back in so that connections always start up
                            if (groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::connectionIsExpected(Ljava/lang/String;)(memberCode + "-" + contentData.streamType + '>' + contentData.originMemberCode)) {
                                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", contentData.streamType, "", originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, screenId);
                            }
                        }
                    } else if (contentData.streamState === "refresh") {
                        if ($wnd.localCanvasContext) {
                            // paint to the canvas so that some data is sent over the stream causing it to be visible to the receiving participant
                            $wnd.localCanvasContext.fillText("T", 0, 0);
                        }
                    } else if (contentData.userId !== userId && contentData.streamState === "disconnect") {
                        if ($wnd.groupConnections[contentData.originMemberCode + "-" + contentData.streamType + '>' + contentData.targetMemberCode]) {
                            groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, contentData.originMemberCode, contentData.streamType, screenId);
                        } else {
                            console.log(memberCode + ": not connected == ignoring");
                        }
                    }
                // }
            }
        }, function(error) {
            // display the error's message header:
            console.log('contentData: ' + contentData);
            console.log('error.headers.message: ' + error.headers.message);
            });
    }-*/;

    private native void initiateConnection(String stunServer, Integer originPhase, String userId, String groupId, String groupUUID, String selfMemberCode, String remoteMemberCode, String streamType, String screenId) /*-{
        var groupStreamHandler = this;    
        console.log("initialiseConnection: " + stunServer);
        if (!$wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode]) {
            console.log(selfMemberCode + ": " + remoteMemberCode + " ==initiateConnection==> " + selfMemberCode);
            var configuration = null; 
            if (stunServer) {
                    configuration = {
                    iceServers: [{
                        urls: "stun:" + stunServer
                    }]
                };
                console.log("configuration: " + configuration);
            }
            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode] = new RTCPeerConnection(configuration);
            $wnd.readyConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode] = false;
            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onicecandidateerror = function (event) {
                console.log("icecandidateerror: " + event);
                // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
            };
            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onicecandidate = function (event) {
                console.log(selfMemberCode + ": " + remoteMemberCode + " <==onicecandidate== " + selfMemberCode);
                if (event.candidate) {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("candidate", streamType, JSON.stringify({ type: "candidate", candidate: event.candidate.candidate, sdpMid: event.candidate.sdpMid, sdpMLineIndex: event.candidate.sdpMLineIndex }), originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                } else {
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("candidate", streamType, JSON.stringify({ type: "candidate", candidate: null }), originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                }
            };

            dataChannel = $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].createDataChannel("dataChannel", {
                reliable: true
            });

            dataChannel.onerror = function (error) {
                console.log(remoteMemberCode + " <==onerror== " + selfMemberCode + ": " + error);
            };

            dataChannel.onmessage = function (event) {
                console.log(remoteMemberCode + " <==onmessage== " + selfMemberCode + ": " + event.data);
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onconnectionstatechange = function (event) {
            // TODO: update the onSuccess and onError events here
                console.log(remoteMemberCode + " <==onconnectionstatechange== " + selfMemberCode);
                if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].iceGatheringState == "complete") {
                    if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].connectionState == "connected") {
                        // the onnError and onSuccess triggering is handled via STOMP so that it can respond to the groups collective connection events
                        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerSuccessHandler(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerSuccessHandler(Ljava/lang/String;)(remoteMemberCode + "-" + streamType + '>' + selfMemberCode);
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("connected", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                    } else {
                        // the onnError and onSuccess triggering is handled via STOMP so that it can respond to the groups collective connection events
                        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(remoteMemberCode + "-" + streamType + '>' + selfMemberCode);
                        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("failed", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                    }
                }
            };

            dataChannel.onclose = function () {
                console.log(remoteMemberCode + " <==onclose== " + selfMemberCode);
                // the onnError and onSuccess triggering is handled via STOMP so that it can respond to the groups collective connection events
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("failed", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                // TODO: perhaps this is premature but we destroy the local end of the connection so that a new one can resume
                if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode]) {
                    console.log(selfMemberCode + ": " + remoteMemberCode + " ==disconnecting==> " + selfMemberCode);
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].ontrack = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onremovetrack = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onremovestream = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onicecandidate = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onconnectionstatechange = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].oniceconnectionstatechange = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onsignalingstatechange = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onicegatheringstatechange = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onnegotiationneeded = null;
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].close();
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode] = null;
                    $wnd.readyConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode] = false;
                }
                // TODO: prepair for a reconnect eg set up  new RTCPeerConnection
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].ondatachannel = function (event) {
                console.log(remoteMemberCode + " <==ondatachannel== " + selfMemberCode);
                dataChannel = event.channel;
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onnegotiationneeded = function () {
                console.log(remoteMemberCode + " <==onnegotiationneeded== " + selfMemberCode);
            }

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].ontrack = function (event) {
                console.log(remoteMemberCode + " <==ontrack== " + selfMemberCode);
                if (event.streams.length > 0 && $wnd.$("#groupRemote" + streamType + "_" + remoteMemberCode).length > 0) {
                    $wnd.$("#groupRemote" + streamType + "_" + remoteMemberCode)[0].srcObject = event.streams[0];
                    // $wnd.$("#groupRemoteStream")[0].attr('src', event.streams[0]);
                }
                groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("refresh", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onremovetrack = function () {
                console.log(remoteMemberCode + " <==onremovetrack== " + selfMemberCode);
            };
            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onremovestream = function () {
                console.log(remoteMemberCode + " <==onremovestream== " + selfMemberCode);
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].oniceconnectionstatechange = function (event) {
                console.log(remoteMemberCode + " <==oniceconnectionstatechange== " + selfMemberCode);
                // console.log(event);
                // if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].connectionState == "complete") {
                //     if ($wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].iceConnectionState == "connected") {
                //         groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerSuccessHandler(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                //         groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("connected", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                //     } else {
                //         groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(selfMemberCode + "-" + streamType + '>' + remoteMemberCode);
                //         groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::triggerErrorHanlder(Ljava/lang/String;)(remoteMemberCode + "-" + streamType + '>' + selfMemberCode);
                //         groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("failed", streamType, "", originPhase, userId, groupId, groupUUID, selfMemberCode, remoteMemberCode, screenId);
                //     }
                // }
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onsignalingstatechange = function () {
                console.log(remoteMemberCode + " <==onsignalingstatechange== " + selfMemberCode);
            };

            $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].onicegatheringstatechange = function () {
                console.log(remoteMemberCode + " <==onicegatheringstatechange== " + selfMemberCode);
            };

            if ($wnd.localStream[streamType + '_' + remoteMemberCode]) {
                // localStream.getTracks().forEach(track => $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].addTrack(track, localStream));
                localTracks = $wnd.localStream[streamType + '_' + remoteMemberCode].getTracks();
                for (trackCount = 0; trackCount < localTracks.length; trackCount++) {
                    console.log(remoteMemberCode + " ==addTrack " + trackCount + " ==> " + selfMemberCode);
                    $wnd.groupConnections[selfMemberCode + "-" + streamType + '>' + remoteMemberCode].addTrack(localTracks[trackCount], $wnd.localStream[streamType + '_' + remoteMemberCode]);
                }
            }
        }
    }-*/;

    private native void sendReady(int originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String streamType, String screenId) /*-{
        var groupStreamHandler = this;
        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", streamType, "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
    }-*/;

    // TODO: Sound appears not to be streaming
    // TODO: Canvas ABBA reliably works but Camera ABBA connects for one but fails for the other and it is random which one fails
    // TODO: the on success is triggered a bit to easily and the on error is not triggered for both when the receiver fails to connect

    private native void offerVideo(int originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId, TimedStimulusListener onError) /*-{
        var groupStreamHandler = this;
        // TODO: add device filtering so a specified camera can be used
        if (!$wnd.localStream["Camera_" + remoteMemberCode]) {
            $wnd.requestPermissions(true, true, null,
                function(captureStream) {
                    $wnd.localStream['Camera_' + remoteMemberCode] = captureStream;
                    $wnd.$("#groupLocalCamera")[0].srcObject = $wnd.localStream['Camera_' + remoteMemberCode];
                    if (!$wnd.pendingCandidates) $wnd.pendingCandidates = {};
                    $wnd.pendingCandidates[memberCode + "-Camera>" + remoteMemberCode] = [];
                    // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "Camera", "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
                }, function(error) {
                    console.log(error.message);
                    groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::disconnectStreams(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, "Camera", screenId);
                    onError.@nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener::postLoadTimerFired()();
                }
            );
        }
    }-*/;

    private native void offerCanvas(int originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String screenId) /*-{
        var groupStreamHandler = this;
        if (!$wnd.localStream["Canvas_" + remoteMemberCode]) {
            // TODO: handle both video and canvas streams
            $wnd.localStream["Canvas_" + remoteMemberCode] = $wnd.$("#groupLocalCanvas")[0].captureStream(15); // 15 FPS
        }
        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = true;
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("ready", "Canvas", "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
        localCanvas = $wnd.$("#groupLocalCanvas")[0];
        $wnd.localCanvasContext = localCanvas.getContext("2d");

        // $wnd.localCanvasContext.clearRect(0, 0, localCanvas.width, localCanvas.height);
        $wnd.localCanvasContext.fillStyle = "lightgrey";
        $wnd.localCanvasContext.fillRect(0, 0, localCanvas.width, localCanvas.height);

        $wnd.localCanvasContext.fillStyle = "black";
        $wnd.localCanvasContext.font = "20px Arial";
//        $wnd.localCanvasContext.fillText(userId, 10, 50);
//        $wnd.localCanvasContext.fillText(groupId, 10, 100);
//        $wnd.localCanvasContext.fillText(memberCode, 10, 150);
        localCanvas.addEventListener("mousemove", function (event) {
            if (event.buttons > 0) {
                // console.log(event);
                // console.log(event.clientX);
                // console.log(localCanvas.offsetLeft);
                // console.log(window.pageXOffset);
                $wnd.localCanvasContext.beginPath();
                var bounds = localCanvas.getBoundingClientRect();
                var positionX = event.clientX - bounds.x;
                var positionY = event.clientY - bounds.y;
                $wnd.localCanvasContext.moveTo((positionX - event.movementX) / bounds.width * localCanvas.width, (positionY - event.movementY) / bounds.height * localCanvas.height);
                $wnd.localCanvasContext.lineTo(positionX / bounds.width * localCanvas.width, positionY / bounds.height * localCanvas.height);
                $wnd.localCanvasContext.strokeStyle = "blue";
                $wnd.localCanvasContext.lineWidth = 1;
                $wnd.localCanvasContext.stroke();
                $wnd.localCanvasContext.closePath();
            }
        }, false);
    }-*/;

    private native void disconnectStreams(Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String streamType, String screenId) /*-{
        var groupStreamHandler = this;
        console.log(memberCode + ": " + remoteMemberCode + " ==disconnectStreams==> " + memberCode);
        groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::messageGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)("disconnect", streamType, "", originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, screenId);
        if ($wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode]) {
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].ontrack = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onremovetrack = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onremovestream = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onicecandidate = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onconnectionstatechange = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].oniceconnectionstatechange = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onsignalingstatechange = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onicegatheringstatechange = null;
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].onnegotiationneeded = null;
            
            // In order to permit reconnects we don't modify the video elements in the DOM, they can be removed via the XML
            // iterate all member specific remote video elements
            // var remoteVideoArray = $wnd.$("video[id^=groupRemote" + streamType + "]");
            // if (remoteVideoArray) {
            //     for (remoteVideoIndex = 0; remoteVideoIndex < remoteVideoArray.length; remoteVideoIndex++) {
            //         var remoteVideo = remoteVideoArray[remoteVideoIndex];
            //         if (remoteVideo && remoteVideo.srcObject) {
            //             // remoteVideo.srcObject.getTracks().forEach(track => track.stop());
            //             for (trackCount = 0; trackCount < remoteVideo.srcObject.getTracks().length; trackCount++) {
            //                 remoteVideo.srcObject.getTracks()[trackCount].stop();
            //             }
            //         }
            //     }
            // }
            // In order to permit reconnects we don't modify the video elements in the DOM, they can be removed via the XML
            // var localVideoArray = $wnd.$("video[id^=groupLocalVideo");
            // if (localVideoArray) {
            //     for (localVideoIndex = 0; localVideoIndex < localVideoArray.length; localVideoIndex++) {
            //         var localVideo = localVideoArray[localVideoIndex];
            //         if (localVideo && localVideo.srcObject) {
            //             // localVideo.srcObject.getTracks().forEach(track => track.stop());
            //             for (trackCount = 0; trackCount < localVideo.srcObject.getTracks().length; trackCount++) {
            //                 localVideo.srcObject.getTracks()[trackCount].stop();
            //             }
            //         }
            //     }
            // }
            // TODO: should we be cleaning up the the local canvas srcObject.getTracks here also?
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode].close();
            $wnd.groupConnections[memberCode + "-" + streamType + '>' + remoteMemberCode] = null;
            $wnd.readyConnections[memberCode + "-" + streamType + '>' + remoteMemberCode] = false;
        }

        // remove all member remote video elements for each member
        $wnd.$("video[id^=groupRemote" + streamType + "]").remove();
        // remove local elements
        $wnd.$("#groupLocalCamera").remove();
        $wnd.$("#groupLocalCanvas").remove();
        $wnd.localStream[streamType + '_' + remoteMemberCode] = null;
        // groupStreamHandler.@nl.mpi.tg.eg.experiment.client.service.GroupStreamHandler::isReady = false;
    }-*/;

    public void synchronisePhase(int currentPhase) {

    }

//    public abstract void updateDebugRegion(String message);

    public abstract void addCanvasElement(String elementId, String groupId, String groupUUID, String memberCode, String remoteMemberCode);

    public abstract void addVideoElement(String elementId, String groupId, String groupUUID, String memberCode, String remoteMemberCode);

    public void notifyDetatchedElement(String elementId, Integer originPhase, String userId, String groupId, String groupUUID, String memberCode, String remoteMemberCode, String streamType, String screenId) {
        disconnectStreams(originPhase, userId, groupId, groupUUID, memberCode, remoteMemberCode, streamType, screenId);
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

    public void negotiateCanvas(final String streamChannels, Integer originPhase, final UserId userId, final String groupId, final String groupUUID, final String memberCode, final String screenId, TimedStimulusListener onError, TimedStimulusListener onSuccess) {
        // TODO: utilise TimedStimulusListener onError and TimedStimulusListener onSuccess
        for (String channel : streamChannels.split("\\|")) {
            final boolean isRelevant = channel.matches("(.*,)?" + memberCode + "(,.*)?");
            final boolean isFirst = channel.matches(memberCode + "(,.*)?");
            if (isRelevant) {
                if (isFirst) {
                    for (final String member : channel.split(",")) {
                        // set up the elements and connection based on communication channels
                        if (!member.equals(memberCode)) {
                            addCanvasElement("groupLocalCanvas", groupId, groupUUID, memberCode, member);
                            offerCanvas(originPhase, userId.toString(), groupId, groupUUID, memberCode, member, screenId);
                        }
                    }
                } else {
                    final String member = channel.split(",")[0];
                    final String connectionName = "groupRemoteCanvas_" + member;
                    final String connectionKey = memberCode + "-Canvas>" + member;
                    if (!expectedConnections.containsKey(connectionKey)) {
                        expectedConnections.put(connectionKey, false);
                        addVideoElement(connectionName, groupId, groupUUID, memberCode, member);
                        connectionListeners.put(connectionKey, onSuccess);
                        errorListeners.put(connectionKey, onError);
                    }
                    sendReady(originPhase, userId.toString(), groupId, groupUUID, memberCode, member, "Canvas", screenId);
                }
            }
        }
        // TODO: on canvas and video removed from parent disconnectStreams
        // disconnectStreams(originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
    }

    public void negotiateCamera(final String streamChannels, Integer originPhase, final UserId userId, final String groupId, final String groupUUID, final String memberCode, final String screenId, TimedStimulusListener onError, TimedStimulusListener onSuccess) {
        // TODO: utilise TimedStimulusListener onError and TimedStimulusListener onSuccess
        for (String channel : streamChannels.split("\\|")) {
            final boolean isRelevant = channel.matches("(.*,)?" + memberCode + "(,.*)?");
            final boolean isFirst = channel.matches(memberCode + "(,.*)?");
            if (isRelevant) {
                if (isFirst) {
                    for (final String member : channel.split(",")) {
                        // set up the elements and connection based on communication channels
                        if (!member.equals(memberCode)) {
                            addVideoElement("groupLocalCamera", groupId, groupUUID, memberCode, member);
                            offerVideo(originPhase, userId.toString(), groupId, groupUUID, memberCode, member, screenId, onError);
                        }
                    }
                } else {
                    final String member = channel.split(",")[0];
                    final String connectionName = "groupRemoteCamera_" + member;
                    final String connectionKey = memberCode + "-Camera>" + member;
                    if (!expectedConnections.containsKey(connectionKey)) {
                        expectedConnections.put(connectionKey, false);
                        addVideoElement(connectionName, groupId, groupUUID, memberCode, member);
                        connectionListeners.put(connectionKey, onSuccess);
                        errorListeners.put(connectionKey, onError);
                    }
                    sendReady(originPhase, userId.toString(), groupId, groupUUID, memberCode, member, "Camera", screenId);
                }
            }
        }
        // TODO: on canvas and video removed from parent disconnectStreams
        // disconnectStreams(originPhase, userId.toString(), groupId, groupUUID.toString(), memberCode, screenId);
    }

    private native void messageGroup(String streamState, String streamType, String messageData, Integer originPhase, String userId, String groupId, String groupUUID, String originMemberCode, String targetMemberCode, String screenId) /*-{
    var groupParticipantService = this;
    $wnd.stompClient.send("/app/stream", {}, JSON.stringify({
        'userId': userId,
        'groupId': groupId,
        'screenId': screenId,
        'targetMemberCode': targetMemberCode,
        'originMemberCode': originMemberCode,
        'originPhase': originPhase,
        'streamState': streamState,
        'streamType': streamType,
        'messageData': messageData
    }));
    }-*/;
}
