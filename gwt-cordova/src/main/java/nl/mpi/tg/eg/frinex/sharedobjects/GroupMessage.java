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

/**
 * @since Oct 27, 2016 4:03:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupMessage {

    private String userId;
    private String groupId;
    private String userLabel;
    private String allMemberCodes;
    private String memberCode;
    private String stimulusId;
    private Integer stimulusIndex;
    private String messageString;
    private boolean groupReady;

    public GroupMessage() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
}
