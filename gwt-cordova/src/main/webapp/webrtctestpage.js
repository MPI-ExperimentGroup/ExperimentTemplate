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

function initiateConnection() {
    initialiseConnection();
    peerConnection.createOffer().then(function (offer) {
        sendToGroup("offer", { type: 'offer', sdp: offer.sdp });
        // peerConnection.setLocalDescription(offer).then(function () {
        // sendToGroup("offer", peerConnection.localDescription);
        // $("#connectionInfo").val(JSON.stringify(peerConnection.localDescription));
        //});
    }).catch(handleDisconnectError);
}

function changeStream(streamOption) {
    peerConnection.getSenders().forEach(sender => sender.track.enabled = (streamOption.hasOwnProperty(sender.track.kind)) ? streamOption[sender.track.kind] : sender.track.enabled);
    sendToGroup("change", streamOption);
}

function offerCanvas() {
    $("#offerVideoButton").prop("disabled", true);
    $("#offerCanvasButton").prop("disabled", true);
    $("#streamContainer").append("<canvas id=\"localCanvas\" style=\"width:80vw max-width:400px\" width=\"400\" height=\"300\"></canvas>");
    localStream = $("#localCanvas")[0].captureStream(15); // 15 FPS
    isReady = true;
    sendToGroup("ready", "");
    localCanvas = $("#localCanvas")[0];
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
}

function offerVideo() {
    $("#offerVideoButton").prop("disabled", true);
    $("#offerCanvasButton").prop("disabled", true);
    $("#streamContainer").append("<video id=\"localVideo\" style=\"width:80vw\" autoplay muted></video>");
    navigator.mediaDevices.getUserMedia({ audio: true, video: true }).then(function (captureStream) {
        localStream = captureStream;
        $("#localVideo").srcObject = localStream;
        // $("#localVideo").attr('src', localStream);
        isReady = true;
        // localStream.getTracks().forEach(track => peerConnection.addTrack(track, localStream));
        // peerConnection.addStream(localStream);
        sendToGroup("ready", "");
    }).catch(handleDisconnectError);
}

// function acceptVideo() {
// navigator.mediaDevices.getUserMedia({ audio: true, video: true }).then(function (stream) {
//     localStream = stream;
//     $("#localVideo").srcObject = localStream;
//     localStream.getTracks().forEach(track => peerConnection.addTrack(track, localStream));
// }).catch(handleDisconnectError);
// }

function handleOffer(sendingUserId, offer) {
    if (peerConnection) {
        if (sendingUserId !== userId) {
            console.log('already connected, ignoring');
        } else if (!peerConnection.localDescription) {
            // delaying setting the local description so that candidates do not get sent until both sides have seen the offer
            peerConnection.setLocalDescription(offer);
        }
    } else {
        initialiseConnection();
        // $("#acceptButton").prop("disabled", false);
        // var sessionDesc = new RTCSessionDescription(JSON.parse($("#connectionInfo").val()));
        peerConnection.setRemoteDescription(offer).then(function () {
            return peerConnection.createAnswer();
        }).then(function (answer) {
            sendToGroup("answer", { type: 'answer', sdp: answer.sdp });
            peerConnection.setLocalDescription(answer);
            // }).then(function () {
            // sendToGroup("answer", peerConnection.localDescription);
        });
    }
}

function handleAnswer(answer) {
    if (peerConnection) {
        peerConnection.setRemoteDescription(answer);
        // peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    } else {
        console.log("No peer connection");
    }
}

function handleCandidate(candidate) {
    // var candidate = new RTCIceCandidate(candidate);
    if (peerConnection) {
        // if (peerConnection.remoteDescription) {
        if (candidate === "null" || !candidate.candidate) {
            // the terminal null is sent inside the candidate object
            peerConnection.addIceCandidate(null).catch(reportError);
            sendToGroup("refresh", "");
        } else {
            peerConnection.addIceCandidate(candidate).catch(reportError);
        }
        // peerConnection.addIceCandidate(candidate).catch(reportError);
        // } else {
        // TODO: this seems to be a problem
        // console.log("No remote description");
        // }
    } else {
        console.log("No peer connection");
    }
}

function sendToGroup(status, messageObject) {
    const contentData = {
        'groupId': groupId,
        'screenId': null,
        'originMemberCode': null,
        'originPhase': null,
        'streamState': status,
        'userId': userId,
        'memberCode': memberCode,
        'messageData': JSON.stringify(messageObject),
    };
    stompClient.send("/app/stream", {}, JSON.stringify(contentData));
    $("#selfdata").append(
        "<tr style=\"background: #0d6efd1a;\">" +
        "<td>sent: " + contentData.userId +
        "</td><td>" + contentData.groupId +
        "</td><td>" + // contentData.groupUUID +
        "</td><td>" + contentData.screenId +
        "</td><td>" + contentData.memberCode +
        "</td><td>" + contentData.originMemberCode +
        "</td><td>" + contentData.stimulusId +
        "</td><td>" + contentData.streamState +
        "</td><td>" + contentData.originPhase +
        "</td><td>" + contentData.messageData +
        "</td></tr>");
}

function disconnectStreams() {
    sendToGroup("disconnect", "");
    var remoteVideo = $("#remoteVideo");
    var localVideo = $("#localVideo");
    if (peerConnection) {
        peerConnection.ontrack = null;
        peerConnection.onremovetrack = null;
        peerConnection.onremovestream = null;
        peerConnection.onicecandidate = null;
        peerConnection.oniceconnectionstatechange = null;
        peerConnection.onsignalingstatechange = null;
        peerConnection.onicegatheringstatechange = null;
        peerConnection.onnegotiationneeded = null;
        if (remoteVideo && remoteVideo.srcObject) {
            remoteVideo.srcObject.getTracks().forEach(track => track.stop());
        }
        if (localVideo && localVideo.srcObject) {
            localVideo.srcObject.getTracks().forEach(track => track.stop());
        }
        peerConnection.close();
        peerConnection = null;
    }

    $("#streamContainer").empty();
    // remoteVideo.removeAttribute("src");
    // remoteVideo.removeAttribute("srcObject");
    // localVideo.removeAttribute("src");
    // remoteVideo.removeAttribute("srcObject");

    // $("#initialiseButton").prop("disabled", peerConnection);
    $("#offerVideoButton").prop("disabled", !peerConnection);
    $("#offerCanvasButton").prop("disabled", !peerConnection);
    // $("#acceptButton").prop("disabled", !peerConnection);
    $("#changeVideoAudioButton").prop("disabled", !peerConnection);
    $("#changeAudioButton").prop("disabled", !peerConnection);
    $("#changeVideoButton").prop("disabled", !peerConnection);
    $("#changeNoneButton").prop("disabled", !peerConnection);
    $("#disconnectButton").prop("disabled", !peerConnection);
    localStream = null;
    isReady = false;
}

function reportError(e) {
    console.log(e.name, e.message);
}

function handleDisconnectError(e) {
    console.log(e.name, e.message);
    disconnectStreams();
}

function initialiseConnection() {
    if (!peerConnection) {
        var configuration = null; /*{
            iceServers: [{
                urls: "stun:stun.stunprotocol.org"
            }]
        };*/
        peerConnection = new RTCPeerConnection(configuration);
        peerConnection.onicecandidate = function (event) {
            console.log("onicecandidate");
            if (event.candidate) {
                sendToGroup("candidate", {
                    type: "candidate", candidate: event.candidate.candidate,
                    sdpMid: event.candidate.sdpMid,
                    sdpMLineIndex: event.candidate.sdpMLineIndex
                });
            } else {
                sendToGroup("candidate", { type: "candidate", candidate: null });
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

        dataChannel.onconnectionstatechange = function (event) {
            console.log("onconnectionstatechange");
        };

        dataChannel.onclose = function () {
            console.log("onclose");
        };

        peerConnection.ondatachannel = function (event) {
            dataChannel = event.channel;
        };

        peerConnection.onnegotiationneeded = function () {
        }

        peerConnection.ontrack = function (event) {
            console.log("ontrack");
            $("#streamContainer").append("<video id=\"remoteVideo\" style=\"width:40vw\" autoplay muted></video>");
            $("#remoteVideo")[0].srcObject = event.streams[0];
            // $("#remoteVideo").attr('src', event.streams[0]);
            sendToGroup("refresh", "");
        };

        peerConnection.onremovetrack = function () {
            console.log("onremovetrack");
        };
        peerConnection.onremovestream = function () {
            console.log("onremovestream");
        };

        peerConnection.oniceconnectionstatechange = function () {
            console.log("oniceconnectionstatechange");
        };

        peerConnection.onsignalingstatechange = function () {
            console.log("onsignalingstatechange");
        };

        peerConnection.onicegatheringstatechange = function () {
            console.log("onicegatheringstatechange");
        };

        // peerConnection.onaddstream = function (event) {
        //     console.log("onaddstream");
        //     $("#remoteVideo").srcObject = event.stream;
        // };

        localStream.getTracks().forEach(track => peerConnection.addTrack(track, localStream));

        // $("#acceptButton").prop("disabled", true);
    }
    // $("#initialiseButton").prop("disabled", peerConnection);
    $("#offerVideoButton").prop("disabled", isReady);
    $("#offerCanvasButton").prop("disabled", isReady);
    $("#changeVideoAudioButton").prop("disabled", !peerConnection);
    $("#changeAudioButton").prop("disabled", !peerConnection);
    $("#changeVideoButton").prop("disabled", !peerConnection);
    $("#changeNoneButton").prop("disabled", !peerConnection);
    $("#disconnectButton").prop("disabled", !peerConnection);
}

function setConnected(connected) {
    $("#offerVideoButton").prop("disabled", isReady);
    $("#offerCanvasButton").prop("disabled", isReady);
    $("#changeVideoAudioButton").prop("disabled", !peerConnection);
    $("#changeAudioButton").prop("disabled", !peerConnection);
    $("#changeVideoButton").prop("disabled", !peerConnection);
    $("#changeNoneButton").prop("disabled", !peerConnection);
    $("#disconnectButton").prop("disabled", !peerConnection);

    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#greetings").html("");
    $("#groupTarget").html("");
    $("#animateTarget").html("");
    if (connected && !isCompact) {
        $("#conversation").show();
        $("#streamTarget").append("<tr>" +
            "<td>userId</td>" +
            "<td>Group</td>" +
            "<td>groupUUID</td>" +
            "<td>screenId</td>" +
            "<td>TargetCode</td>" +
            "<td>OriginCode</td>" +
            "<td>streamState</td>" +
            "<td>originPhase</td>" +
            "<td>message</td>" +
            "</tr>");
        $("#groupTarget").append("<tr>" +
            "<td>UserId</td>" +
            "<td>Group</td>" +
            "<td>GroupUUID</td>" +
            "<td>ScreenId</td>" +
            "<td>Member Code</td>" +
            "<td>Origin Code</td>" +
            "<td>Channels</td>" +
            "<td>Expected Respondents</td>" +
            "<td>Actual Respondents</td>" +
            "<td>All Members</td>" +
            "<td>Origin Phase</td>" +
            "<td>Requested Phase</td>" +
            "<td>Message</td>" +
            "<td>Group Ready</td>" +
            "</tr>");
        $("#unittestdata").append(
            "<tr><td>userId</td>" +
            "<td>screenId</td>" +
            "<td>groupId</td>" +
            "<td>memberCode</td>" +
            "<td>originMemberCode</td>" +
            "<td>originPhase</td>" +
            "<td>messageString</td>" +
            "<td>groupReady</td>" +
            "<td>groupUUID</td></tr>");
        $("#selfdata").append(
            "<tr><td>userId</td>" +
            "<td>groupId</td>" +
            "<td>groupUUID</td>" +
            "<td>screenId</td>" +
            "<td>Channels</td>" +
            "<td>memberCode</td>" +
            "<td>originMemberCode</td>" +
            "<td>stimulusId</td>" +
            "<td>streamState</td>" +
            "<td>originPhase</td>" +
            "<td>messageString</td>" +
            "<td>groupReady</td>" +
            "</tr>");
    } else {
        $("#conversation").hide();
    }
}

function connect() {
    var socket = new SockJS('./gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        var progressDivBar = $("<div style='background: grey;' class='progressDivBar'>&nbsp;</div>");
        $("#animateTarget").append(progressDivBar);
        stompClient.subscribe('/shared/animation', function (greeting) {
            $("#startBar").prop("disabled", true);
            var contentData = JSON.parse(greeting.body);
            showData(contentData);
            progressDivBar.css("width", contentData.width + "px");
            //            $("#send").hide();
        });
        stompClient.subscribe('/shared/stream', function (streamMessage) {
            var contentData = JSON.parse(streamMessage.body);
            if (!isCompact) {
                var streamTableRow = $("#streamUserId" + contentData.userId);
                if (!streamTableRow.length) {
                    $("#streamTarget").append("<tr id=\"streamUserId" + contentData.userId + "\"></tr>");
                    streamTableRow = $("#streamUserId" + contentData.userId);
                }
                streamTableRow.html("<td>" + contentData.userId +
                    "</td><td>" + contentData.groupId +
                    "</td><td>" + contentData.groupUUID +
                    "</td><td>" + contentData.screenId +
                    "</td><td>" + contentData.targetMemberCode +
                    "</td><td>" + contentData.originMemberCode +
                    "</td><td>" + contentData.streamState +
                    "</td><td>" + contentData.originPhase +
                    "</td><td>" + contentData.messageData +
                    "</td>");
                    streamTableRow.css("outline-style", "solid");
                    streamTableRow.css("outline-width", "5px");
                    streamTableRow.css("outline-color", "green");
                    streamTableRow.animate({ outlineWidth: 0 }, "slow");
            }
            if (isReady) {
                if (!groupId || groupId === contentData.groupId) {
                    if (/*contentData.userId !== userId &&*/ contentData.streamState === "offer") {
                        console.log("offer: " + contentData.messageData);
                        handleOffer(contentData.userId, JSON.parse(contentData.messageData));
                    }
                    if (contentData.userId !== userId && contentData.streamState === "answer") {
                        console.log("answer: " + contentData.messageData);
                        handleAnswer(JSON.parse(contentData.messageData));
                    }
                    if (contentData.userId !== userId && contentData.streamState === "candidate") {
                        console.log("candidate: " + contentData.messageData);
                        handleCandidate(JSON.parse(contentData.messageData));
                    }
                    if (contentData.userId !== userId && contentData.streamState === "ready") {
                        if (peerConnection) {
                            console.log('already connected, ignoring');
                        } else {
                            initiateConnection();
                        }
                    }
                    if (contentData.userId !== userId && contentData.streamState === "refresh") {
                        if (localContext) {
                            // paint to the canvas so that some data is sent over the stream causing it to be visible to the receiving participant
                            localContext.fillText("T", 0, 0);
                        }
                    }
                    if (contentData.userId !== userId && contentData.streamState === "disconnect") {
                        if (peerConnection) {
                            disconnectStreams();
                        } else {
                            console.log('not connected, ignoring');
                        }
                    }
                }
            }
        });
        stompClient.subscribe('/shared/group', function (greeting) {
            var contentData = JSON.parse(greeting.body);
            if (!isCompact) {
                //            console.log('greeting.body: ' + greeting.body);
                //            console.log('contentData: ' + contentData);
                var usersTableRow = $("#userId" + contentData.userId);
                if (!usersTableRow.length) {
                    $("#groupTarget").append("<tr id=\"userId" + contentData.userId + "\"></tr>");
                    usersTableRow = $("#userId" + contentData.userId);
                }
                var usersTableCells =
                    "<td>" + contentData.userId +
                    "</td><td>" + contentData.groupId +
                    "</td><td>" + contentData.groupUUID +
                    "</td><td>" + contentData.screenId +
                    "</td><td>" + contentData.memberCode +
                    "</td><td>" + contentData.originMemberCode +
                    "</td><td>" + contentData.groupCommunicationChannels +
                    "</td><td>" + contentData.expectedRespondents +
                    "</td><td>" + contentData.actualRespondents +
                    "</td><td>" + contentData.allMemberCodes +
                    "</td><td>" + contentData.originPhase +
                    "</td><td>" + contentData.requestedPhase +
                    "</td><td>" + contentData.messageString +
                    "</td><td>" + contentData.groupReady +
                    "</td>";
                // var messageButtonCell = "<td><button class='btn btn-default' type='submit' onClick=\"messageGroup('" + contentData.userId + "','" + contentData.screenId + "','" + contentData.memberCode + "')\">message</button></td>";
                // var addButtonCell = "<td><button class='btn btn-default' type='submit' onClick=\"messageGroup(Math.floor((1 + Math.random()) * 0x10000),'" + contentData.screenId + "',null,'" + contentData.groupId + "',null,null)\">add member</button></td>";
                usersTableRow.html(usersTableCells /* + messageButtonCell + addButtonCell*/);
                usersTableRow.css("outline-style", "solid");
                usersTableRow.css("outline-width", "5px");
                usersTableRow.css("outline-color", "green");
                usersTableRow.animate({ outlineWidth: 0 }, "slow");
                //            var groupMemberDiv = $("<div style='background: grey;' class='progressDivBar'>&nbsp;</div>");
                //            $("#groupTarget").append(groupMemberDiv);
                if ($("#logTestData").checked) {
                    $("#unittestdata").append(
                        "<tr><td>\"" +
                        contentData.userId + "\", </td><td>\"" +
                        contentData.screenId + "\", </td><td>\"" +
                        contentData.groupId + "\", </td><td>\"" +
                        contentData.memberCode + "\", </td><td>\"" +
                        contentData.originMemberCode + "\", </td><td>\"" +
                        contentData.originPhase + "\", </td><td>\"" +
                        contentData.messageString + "\", </td><td>" +
                        contentData.groupReady + ", </td><td>\"" +
                        contentData.groupUUID + "\"</td></tr>");
                    }
                if (contentData.userId === userId) {
                    $("#selfdata").append(
                        "<tr style=\"background: #fd690d1a;\">" +
                        "<td> received: " + contentData.userId +
                        "</td><td>" + contentData.groupId +
                        "</td><td>" + contentData.groupUUID +
                        "</td><td>" + contentData.screenId +
                        "</td><td>" + contentData.memberCode +
                        "</td><td>" + contentData.originMemberCode +
                        "</td><td>" + contentData.streamState +
                        "</td><td>" + contentData.originPhase +
                        "</td><td>" + contentData.messageString +
                        "</td><td>" + contentData.groupReady +
                        "</td></tr>");
                }
            }
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    $("#groupTarget").append("<tr><td>Disconnected</td></tr>");
}

var stepCounter = 0;
function startBar() {
    stompClient.send("/app/shared", {}, JSON.stringify({
        'text': '',
        'fill': '',
        'cx': '',
        'width': stepCounter,
        'height': '',
        'left': '',
        'top': ''
    }));
    stepCounter++;
    if (stepCounter > 300)
        stepCounter = 0;
    setTimeout(startBar, 10);
}
function updateGroup() {
    stompClient.send("/app/group", {}, JSON.stringify({
        'userId': userId,
        'memberCode': null,
        'messageString': $("#messageString").val()
    }));
}
function messageGroup(currentUserId, screenId, groupIdL, memberCode) {
    stompClient.send("/app/group", {}, JSON.stringify({
        'userId': currentUserId,
        'groupId': groupIdL,
        'screenId': screenId,
        'memberCode': memberCode,
        'messageString': $("#messageString").val()
    }));
}

function showData(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

var isReady = false;
var isCompact = false;
var localStream = null;
var localCanvas = null;
var localContext = null;
var peerConnection = null;
var stompClient = null;

const urlParams = new URLSearchParams(window.location.search);
const userId = (urlParams.has("mockuser")) ? urlParams.get("mockuser") : "webrtctestpage-" + Math.floor((1 + Math.random()) * 0x10000);
const groupId = (urlParams.has("group")) ? urlParams.get("group") : null;
const memberCode = (urlParams.has("member")) ? urlParams.get("member") : null;

$(function () {
    if (urlParams.has("compact")) {
        isCompact = true;
        $("#infoDiv").hide();
        $("#animateDiv").hide();
        $("#messageDiv").hide();
        $("#groupTarget").hide();
        $("#streamTarget").hide();
        $("#unittestdata").hide();
        $("#selfdata").hide();
        $("#initialiseButton").hide();
    }
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#startBar").click(function () {
        startBar();
    });
    $("#join").click(function () {
        updateGroup();
    });
    $("#messageString").change(function () {
        updateGroup();
    });
    connect();
});
