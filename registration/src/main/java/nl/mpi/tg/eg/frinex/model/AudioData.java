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
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
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
public class AudioData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String experimentName;
    private String screenName;
    private String userId;
    private String stimulusId;
    private AudioType recordingFormat = AudioType.ogg;
    private UUID shortLivedToken;
    private Long downloadPermittedWindowMs = null;
    @Lob
    private byte[] dataBlob;

    public AudioData() {
    }

    public AudioData(Date submitDate, String experimentName, String screenName, String userId, String stimulusId, AudioType recordingFormat, byte[] dataBlob, final UUID shortLivedToken, final long downloadPermittedWindowMs) {
        this.submitDate = submitDate;
        this.experimentName = experimentName;
        this.screenName = screenName;
        this.userId = userId;
        this.stimulusId = stimulusId;
        this.recordingFormat = recordingFormat;
        this.dataBlob = dataBlob;
        this.shortLivedToken = shortLivedToken;
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
    }

    public long getId() {
        return id;
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
        return recordingFormat == AudioType.ogv;
    }
    
    public AudioType getRecordingFormat() {
        return recordingFormat;
    }

    public void setRecordingFormat(AudioType recordingFormat) {
        this.recordingFormat = recordingFormat;
    }

    @JsonIgnore
    public byte[] getDataBlob() {
        return dataBlob;
    }

    public void setDataBlob(byte[] dataBlob) {
        this.dataBlob = dataBlob;
    }

    public UUID getShortLivedToken() {
        return shortLivedToken;
    }

    public void setShortLivedToken(UUID shortLivedToken) {
        this.shortLivedToken = shortLivedToken;
    }

    public long getDownloadPermittedWindowMs() {
        return (downloadPermittedWindowMs != null) ? downloadPermittedWindowMs : 0;
    }

    public void setDownloadPermittedWindowMs(long downloadPermittedWindowMs) {
        this.downloadPermittedWindowMs = downloadPermittedWindowMs;
    }
}
