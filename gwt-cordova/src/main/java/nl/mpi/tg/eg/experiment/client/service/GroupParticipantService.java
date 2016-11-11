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

import java.util.HashMap;
import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;

/**
 * @since Nov 8, 2016 1:47:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupParticipantService implements TimedStimulusListener {

    private final HashMap<String, TimedStimulusListener> groupActivityListeners = new HashMap<>();
    private final String groupMembers;
    private final String groupCommunicationChannels;
    private final TimedStimulusListener connectedListener;
    private final TimedStimulusListener messageListener;
    private boolean isConnected = false;

    private final String userId = null;
    private String userLabel = null;
    private String allMemberCodes = null;
    private String memberCode = null;
    private String groupId = null;
    private String stimulusId = null;
    private String messageString = null;
    private Boolean groupReady = false;
    private boolean userIdMatches = false;

    public GroupParticipantService(String groupMembers, String groupCommunicationChannels, TimedStimulusListener connectedListener, TimedStimulusListener messageListener) {
        this.groupMembers = groupMembers;
        this.groupCommunicationChannels = groupCommunicationChannels;
        this.connectedListener = connectedListener;
        this.messageListener = messageListener;
    }

    public void addGroupActivity(final String groupRole, final TimedStimulusListener activityListener) {
        groupActivityListeners.put(groupRole, activityListener);
    }

    @Override
    public void postLoadTimerFired() {
        connectedListener.postLoadTimerFired();
    }

    protected void handleGroupMessage(String userId, String userLabel, String groupId, String allMemberCodes, String memberCode, String stimulusId, String messageString, Boolean groupReady) {
        userIdMatches = this.userId.equals(userId);
        this.userLabel = userLabel;
        this.allMemberCodes = allMemberCodes;
        this.memberCode = memberCode;
        this.groupId = groupId;
        this.stimulusId = stimulusId;
        this.messageString = messageString;
        this.groupReady = groupReady;
        messageListener.postLoadTimerFired();
    }

    protected void setConnected(Boolean isConnected) {
        this.isConnected = isConnected;
        connectedListener.postLoadTimerFired();
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isUserIdMatches() {
        return userIdMatches;
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

    public String getMessageString() {
        return messageString;
    }

    public boolean isGroupReady() {
        return groupReady;
    }

    public native void joinGroupNetwork(String groupServerUrl, String groupName) /*-{
        var groupParticipantService = this;
        console.log("joinGroupNetwork: " + groupServerUrl + " : " + groupName);
        
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
                groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::handleGroupMessage(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)(contentData.userId, contentData.userLabel, contentData.groupId, contentData.allMemberCodes, contentData.memberCode, contentData.stimulusId, contentData.messageString, contentData.groupReady == true);
            });
        });
     }-*/;

    public native void messageGroup(String userId, String messageString) /*-{
    stompClient.send("/app/group", {}, JSON.stringify({
        'userId': userId,
        'userLabel': null,
        'allMemberCodes': 'A,B,C,D,E,F,G',
        'memberCode': null,
        'stimulusId': Math.floor((1 + Math.random()) * 0x10000),
        'messageString': messageString,
        'groupReady': null
    }));
    }-*/;
}
