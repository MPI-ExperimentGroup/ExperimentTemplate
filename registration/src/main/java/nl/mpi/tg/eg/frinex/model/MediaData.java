/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * @since Aug 13, 2018 4:01:36 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@Table(name = "audioData")
public class MediaData implements Serializable {

    private long id;
    private Date submitDate;
    private String experimentName;
    private String screenName;
    private String userId;
    private String stimulusId;
    private MediaDataType recordingFormat = null;
    @Column(name = "short_lived_token")
    private UUID mediaUUID;
    private Integer partNumber = 0;
//    private UUID mediaUUID = null;
    private Long downloadPermittedWindowMs = null;
    private byte[] dataBlob;

    public MediaData() {
    }

    public MediaData(Date submitDate) {
        // This constructor is only needed for caching the list of days with audio recordings
        this.submitDate = submitDate;
    }

//    public MediaData(long id, Date submitDate, String experimentName, String screenName, String userId, String stimulusId, MediaDataType recordingFormat, Long downloadPermittedWindowMs, Integer partNumber) {
//        this.id = id;
//        this.submitDate = submitDate;
//        this.experimentName = experimentName;
//        this.screenName = screenName;
//        this.userId = userId;
//        this.stimulusId = stimulusId;
//        this.recordingFormat = recordingFormat;
//        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
//        this.partNumber = partNumber;
//    }
    public MediaData(Date submitDate, String experimentName, String screenName, String userId, String stimulusId, MediaDataType recordingFormat, byte[] dataBlob, final UUID shortLivedToken, final Long downloadPermittedWindowMs, final UUID mediaUUID, final Integer partNumber) {
        this.submitDate = submitDate;
        this.experimentName = experimentName;
        this.screenName = screenName;
        this.userId = userId;
        this.stimulusId = stimulusId;
        this.recordingFormat = recordingFormat;
        this.dataBlob = dataBlob;
        this.mediaUUID = shortLivedToken;
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
        this.mediaUUID = mediaUUID;
        this.partNumber = partNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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

    public String getStimulusId() {
        return stimulusId;
    }

    public void setStimulusId(String stimulusId) {
        this.stimulusId = stimulusId;
    }

    @Transient
    public boolean isVideo() {
        return recordingFormat == MediaDataType.ogv || recordingFormat == MediaDataType.webm;
    }

    public MediaDataType getRecordingFormat() {
        return (recordingFormat != null) ? recordingFormat : MediaDataType.ogg;
    }

    public void setRecordingFormat(MediaDataType recordingFormat) {
        this.recordingFormat = recordingFormat;
    }

    @Lob
    // migrating from OID to BYTEA because of the need to stream up and down
    //    @Column(columnDefinition = "BYTEA")
    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    public byte[] getDataBlob() {
        return dataBlob;
    }

    public void setDataBlob(byte[] dataBlob) {
        this.dataBlob = dataBlob;
    }

    public UUID getMediaUUID() {
        return mediaUUID;
    }

    public void setMediaUUID(UUID shortLivedToken) {
        this.mediaUUID = shortLivedToken;
    }

    public long getDownloadPermittedWindowMs() {
        return (downloadPermittedWindowMs != null) ? downloadPermittedWindowMs : 0;
    }

    public void setDownloadPermittedWindowMs(Long downloadPermittedWindowMs) {
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
    }

    @JsonIgnore
    public Integer getPartNumber() {
        return partNumber;
    }

    @Transient
    public String getMediaPath() {
        return "media/" + userId + "_" + screenName + "_" + stimulusId + "_" + id;
    }
}
