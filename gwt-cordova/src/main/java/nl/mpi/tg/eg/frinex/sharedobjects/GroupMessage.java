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
package nl.mpi.tg.eg.frinex.sharedobjects;

import java.util.Objects;

/**
 * @since Oct 27, 2016 4:03:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupMessage {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.groupId);
        hash = 59 * hash + Objects.hashCode(this.screenId);
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
        final GroupMessage other = (GroupMessage) obj;
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        if (!Objects.equals(this.screenId, other.screenId)) {
            return false;
        }
        return true;
    }

    private String userId;
    private String groupId;
    private String groupUUID;
    private String screenId;
    private String userLabel;
    private String allMemberCodes;
    private String groupCommunicationChannels;
    private String memberCode;
    private String stimulusId;
    private String responseStimulusOptions;
    private String responseStimulusId;
    private String expectedRespondents;
    private String actualRespondents;
    private Integer stimulusIndex;
    private String stimuliList;
    private Integer requestedPhase;
    private String messageString;
    private boolean groupReady;
    private int eventMs;

    public GroupMessage() {
    }

    public GroupMessage(String groupId, String screenId, String userId) {
        this.groupId = groupId;
        this.screenId = screenId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
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

    public String getExpectedRespondents() {
        return expectedRespondents;
    }

    public void setExpectedRespondents(String expectedRespondents) {
        this.expectedRespondents = expectedRespondents;
    }

    public String getActualRespondents() {
        return actualRespondents;
    }

    public void setActualRespondents(String actualRespondents) {
        this.actualRespondents = actualRespondents;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    public String getAllMemberCodes() {
        return allMemberCodes;
    }

    public void setAllMemberCodes(String allMemberCodes) {
        this.allMemberCodes = allMemberCodes;
    }

    public String getGroupCommunicationChannels() {
        return groupCommunicationChannels;
    }

    public void setGroupCommunicationChannels(String groupCommunicationChannels) {
        this.groupCommunicationChannels = groupCommunicationChannels;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getStimulusId() {
        return stimulusId;
    }

    public void setStimulusId(String stimulusId) {
        this.stimulusId = stimulusId;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public boolean isGroupReady() {
        return groupReady;
    }

    public void setGroupReady(boolean groupReady) {
        this.groupReady = groupReady;
    }

    public Integer getStimulusIndex() {
        return stimulusIndex;
    }

    public void setStimulusIndex(Integer stimulusIndex) {
        this.stimulusIndex = stimulusIndex;
    }

    public String getStimuliList() {
        return stimuliList;
    }

    public void setStimuliList(String stimuliList) {
        this.stimuliList = stimuliList;
    }

    public Integer getRequestedPhase() {
        return requestedPhase;
    }

    public void setRequestedPhase(Integer requestedPhase) {
        this.requestedPhase = requestedPhase;
    }

    public int getEventMs() {
        return eventMs;
    }

    public void setEventMs(int eventMs) {
        this.eventMs = eventMs;
    }
}
