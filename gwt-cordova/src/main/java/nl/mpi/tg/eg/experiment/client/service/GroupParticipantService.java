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

import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;

/**
 * @since Nov 8, 2016 1:47:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupParticipantService implements TimedStimulusListener {

    final String groupMembers;
    final String groupCommunicationChannels;
    final TimedStimulusListener connectedListener;

    public GroupParticipantService(String groupMembers, String groupCommunicationChannels, TimedStimulusListener connectedListener) {
        this.groupMembers = groupMembers;
        this.groupCommunicationChannels = groupCommunicationChannels;
        this.connectedListener = connectedListener;
    }

    @Override
    public void postLoadTimerFired() {
        connectedListener.postLoadTimerFired();
    }

    protected void handleGroupMessage(String userId, String userLabel, String allMemberCodes, String memberCode, String stimulusId, String messageString, Boolean groupReady) {
        connectedListener.postLoadTimerFired();
    }

    public native void joinGroupNetwork(String groupServerUrl, String groupName) /*-{
        var groupParticipantService = this;
        console.log("joinGroupNetwork: " + groupServerUrl + " : " + groupName);
        
        var socket = new $wnd.SockJS(groupServerUrl + 'gs-guide-websocket');
        stompClient = $wnd.Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/shared/group', function (groupMessage) {
                var contentData = JSON.parse(groupMessage.body);
                console.log('contentData: ' + contentData);
                groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::handleGroupMessage(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)(contentData.userId, contentData.userLabel, contentData.groupId, contentData.allMemberCodes, contentData.memberCode, contentData.stimulusId, contentData.messageString, contentData.groupReady);
            });
        });
     }-*/;
}
