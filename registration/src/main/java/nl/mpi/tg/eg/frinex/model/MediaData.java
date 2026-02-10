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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.Transient;

/**
 * @since Aug 13, 2018 4:01:36 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@Table(name = "audioData")
public class MediaData implements Serializable {

    private Long id;
    private Date submitDate;
    private String experimentName;
    private String screenName;
    private String userId;
    private String stimulusId;
    private MediaDataType recordingFormat = null;
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

    public MediaData(Long id, Date submitDate, String experimentName, String screenName, String userId, String stimulusId, MediaDataType recordingFormat, final UUID mediaUUID, Long downloadPermittedWindowMs, Integer partNumber) {
        this.id = id;
        this.submitDate = submitDate;
        this.experimentName = experimentName;
        this.screenName = screenName;
        this.userId = userId;
        this.stimulusId = stimulusId;
        this.recordingFormat = recordingFormat;
        this.mediaUUID = mediaUUID;
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
        this.partNumber = partNumber;
    }

    public MediaData(Date submitDate, String experimentName, String screenName, String userId, String stimulusId, MediaDataType recordingFormat, byte[] dataBlob, final UUID mediaUUID, final Long downloadPermittedWindowMs, final Integer partNumber) {
        this.submitDate = submitDate;
        this.experimentName = experimentName;
        this.screenName = screenName;
        this.userId = userId;
        this.stimulusId = stimulusId;
        this.recordingFormat = recordingFormat;
        this.dataBlob = dataBlob;
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
        this.mediaUUID = mediaUUID;
        this.partNumber = partNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
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

    @Column(name = "short_lived_token")
    public UUID getMediaUUID() {
        return mediaUUID;
    }

    public void setMediaUUID(UUID mediaUUID) {
        this.mediaUUID = mediaUUID;
    }

    @JsonIgnore
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

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    @Transient
    public String getMediaPath() {
        return "media/" + userId + "_" + screenName + "_" + stimulusId + "_" + id;
    }
}
