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

import java.util.Date;
import java.util.Objects;
import nl.mpi.tg.eg.experiment.client.model.UserId;

/**
 * @since 16 November 2022 14:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StreamMessage {

    public enum StreamMessageState {
        offer, answer, candidate, ready, refresh, permissions, failed, connected, disconnect
    };

    public enum StreamMessageType {
        Canvas, Camera, Receive
    };

    private UserId userId;
    private GroupId groupId;
    private GroupUUID groupUUID;
    private String screenId;
    private MemberCode targetMemberCode;
    private MemberCode originMemberCode;
    private Integer originPhase;
    private StreamMessageState streamState;
    private StreamMessageType streamType;
    private String messageData;
    private Date arrivalDate;

    public StreamMessage() {
        arrivalDate = new Date();
    }

//    public StreamMessage(UserId userId, GroupId groupId, GroupUUID groupUUID, String screenId, MemberCode memberCode, MemberCode originMemberCode, Integer originPhase, StreamMessageState streamState, String messageData) {
//        this.userId = userId;
//        this.groupId = groupId;
//        this.groupUUID = groupUUID;
//        this.screenId = screenId;
//        this.memberCode = memberCode;
//        this.originMemberCode = originMemberCode;
//        this.originPhase = originPhase;
//        this.streamState = streamState;
//        this.StreamMessageType = StreamMessageType;
//        this.messageData = messageData;
//    }
    
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

    public MemberCode getTargetMemberCode() {
        return targetMemberCode;
    }

    public void setTargetMemberCode(MemberCode targetMemberCode) {
        this.targetMemberCode = targetMemberCode;
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

    public StreamMessageType getStreamType() {
        return streamType;
    }

    public StreamMessageState getStreamState() {
        return streamState;
    }

    public void setStreamState(StreamMessageState streamState) {
        this.streamState = streamState;
    }

    public void setStreamType(StreamMessageType streamType) {
        this.streamType = streamType;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.userId);
        hash = 19 * hash + Objects.hashCode(this.groupId);
        hash = 19 * hash + Objects.hashCode(this.groupUUID);
        hash = 19 * hash + Objects.hashCode(this.screenId);
        hash = 19 * hash + Objects.hashCode(this.targetMemberCode);
        hash = 19 * hash + Objects.hashCode(this.originMemberCode);
        hash = 19 * hash + Objects.hashCode(this.originPhase);
        hash = 19 * hash + Objects.hashCode(this.streamState);
        hash = 19 * hash + Objects.hashCode(this.streamType);
        hash = 19 * hash + Objects.hashCode(this.messageData);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StreamMessage other = (StreamMessage) obj;
        if (!Objects.equals(this.screenId, other.screenId)) {
            return false;
        }
        if (!Objects.equals(this.messageData, other.messageData)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        if (!Objects.equals(this.groupUUID, other.groupUUID)) {
            return false;
        }
        if (!Objects.equals(this.targetMemberCode, other.targetMemberCode)) {
            return false;
        }
        if (!Objects.equals(this.originMemberCode, other.originMemberCode)) {
            return false;
        }
        if (!Objects.equals(this.originPhase, other.originPhase)) {
            return false;
        }
        if (this.streamState != other.streamState) {
            return false;
        }
        return this.streamType == other.streamType;
    }
}
