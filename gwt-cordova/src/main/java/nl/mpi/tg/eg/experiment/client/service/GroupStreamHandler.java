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

    public void initialiseConnection(String stunServer) {
        // TODO: initialise the stream
    }

    public void updateStream(final StreamState streamState, final StreamTypes streamType) {
        // TODO: update the stream
        messageGroup(streamState.name(), streamType.name(), 0, "userId", "windowGroupId", "windowMemberCode", "screenId");
    }

    private native void messageGroup(String streamState, String messageString, int originPhase, String userId, String windowGroupId, String windowMemberCode, String screenId) /*-{
    var groupParticipantService = this;
    stompClient.send("/app/stream", {}, JSON.stringify({
        'userId': userId,
        'groupId': windowGroupId,
//        'groupUUID': groupUUID,
        'screenId': screenId,
        'memberCode': windowMemberCode,
//        'originMemberCode': null,
        'originPhase': originPhase,
        'streamState': streamState,
        'messageString': messageString
    }));
    }-*/;

//       private void offerStream(){}; private void answerStream(){}; private void  candidateStream(){}; private void  readyStream(){}; private void refreshStream(){}; private void  disconnectStream(){};
//        offer, answer, candidate, ready, refresh, disconnect
}
