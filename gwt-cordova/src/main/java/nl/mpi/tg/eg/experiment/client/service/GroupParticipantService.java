/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.user.client.Window;
import java.util.HashMap;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.sharedobjects.GroupMessageMatch;

/**
 * @since Nov 8, 2016 1:47:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupParticipantService {

//    private final HashMap<String, ArrayList<TimedStimulusListener>> selfActivityListeners = new HashMap<>();
//    private final HashMap<String, ArrayList<TimedStimulusListener>> othersActivityListeners = new HashMap<>();
    private final HashMap<String, TimedStimulusListener> selfActivityListeners = new HashMap<>();
    private final HashMap<String, TimedStimulusListener> othersActivityListeners = new HashMap<>();
    private final String allMemberCodes;
    private final String groupCommunicationChannels;
    private final TimedStimulusListener connectedListener;
    private final TimedStimulusListener groupNotReadyListener;
    private boolean isConnected = false;
    private TimedStimulusListener lastFiredListner = null;

    private final String userId;
    private final String screenId;
    private String userLabel = null;
//    private final String allMemberCodes = null;
    private String memberCode = null;
    private String groupId = null;
    private String stimulusId = null;
    private Integer stimulusIndex = null;
    private Integer requestedPhase = 0;
    private String messageSenderId = null;
    private String messageSenderMemberCode = null;
    private String messageString = null;
    private Boolean groupReady = false;
//    private Boolean userIdMatches = false;
    private String responseStimulusOptions = null;
    private String responseStimulusId = null;
    private String groupUUID = null;

    public GroupParticipantService(final String userId, String screenId, String groupMembers, String groupCommunicationChannels, TimedStimulusListener connectedListener, TimedStimulusListener groupNotReadyListener) {
        if (Window.Location.getParameter("testuser") != null) {
            this.userId = "testuser-" + Window.Location.getParameter("testuser");
        } else {
            this.userId = userId;
        }
        this.allMemberCodes = groupMembers;
        this.groupCommunicationChannels = groupCommunicationChannels;
        this.connectedListener = connectedListener;
        this.groupNotReadyListener = groupNotReadyListener;
        this.screenId = screenId;
    }

//    public void addGroupActivity(final String groupRole, final int requestedPhase, final GroupMessageMatch groupMessageMatch, final TimedStimulusListener activityListener) {
//
//        ArrayList<TimedStimulusListener> currentSelfRoles = selfActivityListeners.get(groupRole);
//        ArrayList<TimedStimulusListener> currentOthersRoles = othersActivityListeners.get(groupRole);
//        if (currentSelfRoles == null) {
//            currentSelfRoles = new ArrayList<>();
//            selfActivityListeners.put(groupRole, currentSelfRoles);
//        }
//        if (currentOthersRoles == null) {
//            currentOthersRoles = new ArrayList<>();
//            othersActivityListeners.put(groupRole, currentOthersRoles);
//        }
//        while (currentSelfRoles.size() < requestedPhase) {
//            currentSelfRoles.add(null);
//        }
//        while (currentOthersRoles.size() < requestedPhase) {
//            currentOthersRoles.add(null);
//        }
//        switch (groupMessageMatch) {
//            case self:
//                currentSelfRoles.add(requestedPhase, activityListener);
//                break;
//            case other:
//                currentOthersRoles.add(requestedPhase, activityListener);
//                break;
//            case all:
//                currentSelfRoles.add(requestedPhase, activityListener);
//                currentOthersRoles.add(requestedPhase, activityListener);
//                break;
//        }
//    }
    public void addGroupActivity(final String groupRole, final GroupMessageMatch groupMessageMatch, final TimedStimulusListener activityListener) {
        switch (groupMessageMatch) {
            case self:
                selfActivityListeners.put(groupRole, activityListener);
                break;
            case other:
                othersActivityListeners.put(groupRole, activityListener);
                break;
            case all:
                selfActivityListeners.put(groupRole, activityListener);
                othersActivityListeners.put(groupRole, activityListener);
                break;
        }
    }

    protected void clearLastFiredListner() {
        this.lastFiredListner = null;
    }

    protected void handleGroupMessage(String userId, String screenId, String userLabel, String groupId, String allMemberCodes, String memberCode, String stimulusId, String stimulusIndex, String requestedPhase, String messageString, Boolean groupReady, String responseStimulusId, String groupUUID) {
        final boolean userIdMatches = this.userId.equals(userId);
        final boolean screenIdMatches = this.screenId.equals(screenId);
        final boolean groupIdMatches;
        if (userIdMatches && screenIdMatches) {
            this.userLabel = userLabel;
            this.memberCode = memberCode;
            this.groupId = groupId;
            groupIdMatches = true;
        } else {
            groupIdMatches = this.groupId.equals(groupId);
        }

        if (groupIdMatches && screenIdMatches) {
            this.messageSenderMemberCode = memberCode;
            this.groupUUID = groupUUID;
            this.groupReady = groupReady;
            boolean messageIsRelevant = false;
            for (String channel : groupCommunicationChannels.split("\\|")) {
                // check communication channel before responding to the message
                if (channel.contains(this.memberCode) && channel.contains(this.messageSenderMemberCode)) {
                    messageIsRelevant = true;
                    break;
                }
            }
            if (messageIsRelevant) {
//            this.allMemberCodes = allMemberCodes;
                this.stimulusId = stimulusId;
                this.stimulusIndex = Integer.parseInt(stimulusIndex);
                this.requestedPhase = Integer.parseInt(requestedPhase);
                this.messageString = messageString;
                this.messageSenderId = userId;
                this.responseStimulusId = responseStimulusId;
//                if (groupReady) {
                for (String groupRole : ((userIdMatches) ? selfActivityListeners : othersActivityListeners).keySet()) {
                    final String[] splitRole = groupRole.split(":");
                    int roleIndex = this.requestedPhase % splitRole.length;
                    if (splitRole[roleIndex].contains(this.memberCode)) {
                        final TimedStimulusListener currentListner = ((userIdMatches) ? selfActivityListeners : othersActivityListeners).get(groupRole);
//                        ((userIdMatches) ? selfActivityListeners : othersActivityListeners).get(groupRole).get(this.requestedPhase).postLoadTimerFired();
                        if (lastFiredListner == null || !lastFiredListner.equals(currentListner)) {
                            currentListner.postLoadTimerFired();
                            lastFiredListner = currentListner;
                        }
                    }
                }
//                } else {
//                    groupNotReadyListener.postLoadTimerFired();
//                }
            }
        } else {
            groupNotReadyListener.postLoadTimerFired();
        }
    }

    protected void setConnected(Boolean isConnected) {
        this.isConnected = isConnected;
        connectedListener.postLoadTimerFired();
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public String getAllMemberCodes() {
        return allMemberCodes;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getStimulusId() {
        return stimulusId;
    }

    public String getGroupCommunicationChannels() {
        return groupCommunicationChannels;
    }

    public Integer getStimulusIndex() {
        return stimulusIndex;
    }

    public Integer getRequestedPhase() {
        return requestedPhase;
    }

    public String getMessageString() {
        return messageString;
    }

    public String getMessageSenderId() {
        return messageSenderId;
    }

    public String getResponseStimulusOptions() {
        return responseStimulusOptions;
    }

    public void setResponseStimulusOptions(String responseStimulusOptions) {
        this.responseStimulusOptions = responseStimulusOptions;
    }

    public String getResponseStimulusId() {
        return responseStimulusId;
    }

    public void setResponseStimulusId(String responseStimulusId) {
        this.responseStimulusId = responseStimulusId;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public boolean isGroupReady() {
        return groupReady;
    }

    public String getMessageSenderMemberCode() {
        return messageSenderMemberCode;
    }

    public native void joinGroupNetwork(String groupServerUrl) /*-{
        var groupParticipantService = this;
//        console.log("joinGroupNetwork: " + groupServerUrl + " : " + groupName);
        
        var socket = new $wnd.SockJS(groupServerUrl + 'gs-guide-websocket');
        stompClient = $wnd.Stomp.over(socket);
        stompClient.connect(
            {
//            withCredentials: false,
//            noCredentials : true
            },
            function (frame) {
            groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::setConnected(Ljava/lang/Boolean;)(@java.lang.Boolean::TRUE);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/shared/group', function (groupMessage) {
                var contentData = JSON.parse(groupMessage.body);
                console.log('contentData: ' + contentData);
                groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::handleGroupMessage(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)(contentData.userId, contentData.screenId,contentData.userLabel, contentData.groupId, contentData.allMemberCodes, contentData.memberCode, contentData.stimulusId, Number(contentData.stimulusIndex), Number(contentData.requestedPhase), contentData.messageString, (contentData.groupReady)?@java.lang.Boolean::TRUE : @java.lang.Boolean::FALSE, contentData.responseStimulusId, contentData.groupUUID);
            });
        });
     }-*/;

    public void messageGroup(int incrementPhase, String stimulusId, String stimulusIndex, String messageString, String responseStimulusOptions, String responseStimulusId) {
        String windowGroupId = Window.Location.getParameter("group");
        if (windowGroupId == null) {
            windowGroupId = groupId;
        }
        messageGroup(this.requestedPhase + incrementPhase, userId, windowGroupId, screenId, allMemberCodes, stimulusId, stimulusIndex, messageString, responseStimulusOptions, responseStimulusId);
    }

    private native void messageGroup(int requestedPhase, String userId, String groupId, String screenId, String allMemberCodes, String stimulusId, String stimulusIndex, String messageString, String responseStimulusOptions, String responseStimulusId) /*-{
    var groupParticipantService = this;
    stompClient.send("/app/group", {}, JSON.stringify({
        'userId': userId,
        'groupId': groupId,
        'screenId': screenId,
        'userLabel': null,
        'allMemberCodes': allMemberCodes,
        'memberCode': null,
        'stimulusId': stimulusId,
        'requestedPhase': requestedPhase,
        'stimulusIndex': stimulusIndex,
        'messageString': messageString,
        'responseStimulusOptions': responseStimulusOptions,
        'responseStimulusId': responseStimulusId,
        'groupReady': null
    }));
    }-*/;
}
