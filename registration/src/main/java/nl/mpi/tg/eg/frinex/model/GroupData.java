/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.frinex.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * @since Nov 25, 2016 2:54:02 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class GroupData implements Comparable<GroupData> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date eventDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String experimentName;
    private String groupUUID;
    private String groupName;
    private String screenName;
    private String messageRespondentId;
    private String allMemberCodes;
    private String groupCommunicationChannels;
    private String senderMemberCode;
    private String respondentMemberCode;
    private String stimulusId;
    private String stimulusIndex;
    private String responseStimulusId;
    @Column(length = 1024)
    private String stimulusOptionIds;
    private String messageSenderId = null;
    private String messageString;
    private int eventMs;

    public GroupData() {
    }

    public GroupData(Date eventDate, Date submitDate, String experimentName, String groupUUID, String groupName, String screenName, String messageRespondentId, String allMemberCodes, String groupCommunicationChannels, String senderMemberCode, String respondentMemberCode, String stimulusId, String stimulusIndex, String responseStimulusId, String stimulusOptionIds, String messageString, int eventMs) {
        this.eventDate = eventDate;
        this.submitDate = submitDate;
        this.experimentName = experimentName;
        this.groupUUID = groupUUID;
        this.groupName = groupName;
        this.screenName = screenName;
        this.messageRespondentId = messageRespondentId;
        this.allMemberCodes = allMemberCodes;
        this.groupCommunicationChannels = groupCommunicationChannels;
        this.senderMemberCode = senderMemberCode;
        this.respondentMemberCode = respondentMemberCode;
        this.stimulusId = stimulusId;
        this.stimulusIndex = stimulusIndex;
        this.responseStimulusId = responseStimulusId;
        this.stimulusOptionIds = stimulusOptionIds;
        this.messageString = messageString;
        this.eventMs = eventMs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    public String getResponseStimulusId() {
        return responseStimulusId;
    }

    public void setResponseStimulusId(String responseStimulusId) {
        this.responseStimulusId = responseStimulusId;
    }

    public String getStimulusIndex() {
        return stimulusIndex;
    }

    public void setStimulusIndex(String stimulusIndex) {
        this.stimulusIndex = stimulusIndex;
    }

    public String getMessageSenderId() {
        return messageSenderId;
    }

    public void setMessageSenderId(String messageSenderId) {
        this.messageSenderId = messageSenderId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getMessageRespondentId() {
        return messageRespondentId;
    }

    public void setMessageRespondentId(String messageRespondentId) {
        this.messageRespondentId = messageRespondentId;
    }

    public String getAllMemberCodes() {
        return allMemberCodes;
    }

    public void setAllMemberCodes(String allMemberCodes) {
        this.allMemberCodes = allMemberCodes;
    }

    public String getRespondentMemberCode() {
        return respondentMemberCode;
    }

    public void setRespondentMemberCode(String respondentMemberCode) {
        this.respondentMemberCode = respondentMemberCode;
    }

    public String getSenderMemberCode() {
        return senderMemberCode;
    }

    public void setSenderMemberCode(String senderMemberCode) {
        this.senderMemberCode = senderMemberCode;
    }

    public String getStimulusId() {
        return stimulusId;
    }

    public void setStimulusId(String stimulusId) {
        this.stimulusId = stimulusId;
    }

    public String getStimulusOptionIds() {
        return stimulusOptionIds;
    }

    public void setStimulusOptionIds(String stimulusOptionIds) {
        this.stimulusOptionIds = stimulusOptionIds;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public String getGroupCommunicationChannels() {
        return groupCommunicationChannels;
    }

    public void setGroupCommunicationChannels(String groupCommunicationChannels) {
        this.groupCommunicationChannels = groupCommunicationChannels;
    }

    public int getEventMs() {
        return eventMs;
    }

    public void setEventMs(int eventMs) {
        this.eventMs = eventMs;
    }

    @Override
    public int compareTo(GroupData o) {
        return eventDate.compareTo(o.getEventDate());
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
        final GroupData other = (GroupData) obj;
        if (this.eventMs != other.eventMs) {
            return false;
        }
        if (!Objects.equals(this.experimentName, other.experimentName)) {
            return false;
        }
        if (!Objects.equals(this.groupUUID, other.groupUUID)) {
            return false;
        }
        if (!Objects.equals(this.groupName, other.groupName)) {
            return false;
        }
        if (!Objects.equals(this.screenName, other.screenName)) {
            return false;
        }
        if (!Objects.equals(this.messageRespondentId, other.messageRespondentId)) {
            return false;
        }
        if (!Objects.equals(this.allMemberCodes, other.allMemberCodes)) {
            return false;
        }
        if (!Objects.equals(this.groupCommunicationChannels, other.groupCommunicationChannels)) {
            return false;
        }
        if (!Objects.equals(this.senderMemberCode, other.senderMemberCode)) {
            return false;
        }
        if (!Objects.equals(this.respondentMemberCode, other.respondentMemberCode)) {
            return false;
        }
        if (!Objects.equals(this.stimulusId, other.stimulusId)) {
            return false;
        }
        if (!Objects.equals(this.stimulusIndex, other.stimulusIndex)) {
            return false;
        }
        if (!Objects.equals(this.responseStimulusId, other.responseStimulusId)) {
            return false;
        }
        if (!Objects.equals(this.stimulusOptionIds, other.stimulusOptionIds)) {
            return false;
        }
        if (!Objects.equals(this.messageSenderId, other.messageSenderId)) {
            return false;
        }
        if (!Objects.equals(this.messageString, other.messageString)) {
            return false;
        }
        if (!Objects.equals(this.eventDate, other.eventDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.eventDate);
        hash = 89 * hash + Objects.hashCode(this.experimentName);
        hash = 89 * hash + Objects.hashCode(this.groupUUID);
        hash = 89 * hash + Objects.hashCode(this.groupName);
        hash = 89 * hash + Objects.hashCode(this.screenName);
        hash = 89 * hash + Objects.hashCode(this.messageRespondentId);
        hash = 89 * hash + Objects.hashCode(this.allMemberCodes);
        hash = 89 * hash + Objects.hashCode(this.groupCommunicationChannels);
        hash = 89 * hash + Objects.hashCode(this.senderMemberCode);
        hash = 89 * hash + Objects.hashCode(this.respondentMemberCode);
        hash = 89 * hash + Objects.hashCode(this.stimulusId);
        hash = 89 * hash + Objects.hashCode(this.stimulusIndex);
        hash = 89 * hash + Objects.hashCode(this.responseStimulusId);
        hash = 89 * hash + Objects.hashCode(this.stimulusOptionIds);
        hash = 89 * hash + Objects.hashCode(this.messageSenderId);
        hash = 89 * hash + Objects.hashCode(this.messageString);
        hash = 89 * hash + this.eventMs;
        return hash;
    }
}
