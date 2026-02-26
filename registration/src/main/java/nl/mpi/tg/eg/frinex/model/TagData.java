/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * @since Jun 30, 2015 12:13:58 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@Table(
    name = "tag_data",
    indexes = {
        @Index(
            name = "idx_tagdata_distinct",
            columnList = "user_id, screen_name, event_tag, tag_value, event_ms, tag_date, id"
        )
    }
)
public class TagData implements Comparable<TagData> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant tagDate;
    private Instant submitDate;
    private String experimentName;
    private String screenName;
    private String eventTag;
    private String tagValue;
    private String userId;
    private int eventMs;

    public TagData() {
    }

    public TagData(String userId, String screenName, String eventTag, String tagValue, int eventMs, Instant tagDate) {
        this.tagDate = tagDate;
        this.screenName = screenName;
        this.eventTag = eventTag;
        this.tagValue = tagValue;
        this.userId = userId;
        this.eventMs = eventMs;
    }
    
    public TagData(Long id, String userId, String screenName, String eventTag, String tagValue, int eventMs, Instant tagDate) {
        this.id = id;
        this.tagDate = tagDate;
        this.screenName = screenName;
        this.eventTag = eventTag;
        this.tagValue = tagValue;
        this.userId = userId;
        this.eventMs = eventMs;
    }

    public Long getId() {
        return id;
    }

    public Instant getTagDate() {
        return tagDate;
    }

    public Instant getSubmitDate() {
        return submitDate;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getEventTag() {
        return eventTag;
    }

    public String getTagValue() {
        return tagValue;
    }

    public String getUserId() {
        return userId;
    }

    public int getEventMs() {
        return eventMs;
    }

    public void setSubmitDate(Instant submitDate) {
        this.submitDate = submitDate;
    }

    @Override
    public int compareTo(TagData o) {
        return tagDate.compareTo(o.getTagDate());
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
        final TagData other = (TagData) obj;
        if (this.eventMs != other.eventMs) {
            return false;
        }
        if (!Objects.equals(this.experimentName, other.experimentName)) {
            return false;
        }
        if (!Objects.equals(this.eventTag, other.eventTag)) {
            return false;
        }
        if (!Objects.equals(this.tagValue, other.tagValue)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.tagDate, other.tagDate)) {
            return false;
        }
        if (!Objects.equals(this.screenName, other.screenName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tagDate);
        hash = 97 * hash + Objects.hashCode(this.experimentName);
        hash = 97 * hash + Objects.hashCode(this.screenName);
        hash = 97 * hash + Objects.hashCode(this.eventTag);
        hash = 97 * hash + Objects.hashCode(this.tagValue);
        hash = 97 * hash + Objects.hashCode(this.userId);
        hash = 97 * hash + this.eventMs;
        return hash;
    }
}
