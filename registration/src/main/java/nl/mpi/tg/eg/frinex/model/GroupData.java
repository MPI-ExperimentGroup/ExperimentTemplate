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

import java.io.Serializable;
import java.util.Date;
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
public class GroupData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date viewDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String experimentName;
    private String groupName;
    private String screenName;
    private String userId;
    private String userLabel;
    private String allMemberCodes;
    private String groupCommunicationChannels;
    private String memberCode;
    private String stimulusId;
    private String stimulusOptionIds;
    private String messageString;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
