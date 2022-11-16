/*
 * Copyright (C) 2022 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.frinex.sharedobjects;

import nl.mpi.tg.eg.experiment.client.model.UserId;

/**
 * @since 16 November 2022 14:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StreamMessage {

    public enum StreamState {
        offer, answer, candidate, ready, refresh, disconnect
    };

    private UserId userId;
    private GroupId groupId;
    private GroupUUID groupUUID;
    private String screenId;
    private MemberCode memberCode;
    private MemberCode originMemberCode;
    private Integer originPhase;
    private StreamState streamState;
    private String messageString;
    private String messageStatus;

    public StreamMessage(UserId userId, GroupId groupId, GroupUUID groupUUID, String screenId, MemberCode memberCode, MemberCode originMemberCode, Integer originPhase, StreamState streamState, String messageString, String messageStatus) {
        this.userId = userId;
        this.groupId = groupId;
        this.groupUUID = groupUUID;
        this.screenId = screenId;
        this.memberCode = memberCode;
        this.originMemberCode = originMemberCode;
        this.originPhase = originPhase;
        this.streamState = streamState;
        this.messageString = messageString;
        this.messageStatus = messageStatus;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupId groupId) {
        this.groupId = groupId;
    }

    public GroupUUID getGroupUUID() {
        return groupUUID;
    }

    public void setGroupUUID(GroupUUID groupUUID) {
        this.groupUUID = groupUUID;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public MemberCode getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(MemberCode memberCode) {
        this.memberCode = memberCode;
    }

    public MemberCode getOriginMemberCode() {
        return originMemberCode;
    }

    public void setOriginMemberCode(MemberCode originMemberCode) {
        this.originMemberCode = originMemberCode;
    }

    public Integer getOriginPhase() {
        return originPhase;
    }

    public void setOriginPhase(Integer originPhase) {
        this.originPhase = originPhase;
    }

    public StreamState getStreamState() {
        return streamState;
    }

    public void setStreamState(StreamState streamState) {
        this.streamState = streamState;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
}
