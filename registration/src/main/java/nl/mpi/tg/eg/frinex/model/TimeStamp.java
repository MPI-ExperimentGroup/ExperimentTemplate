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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * @since Jul 8, 2015 5:36:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@Table(
    name = "time_stamp",
    indexes = {
        @Index(
            name = "idx_ts_distinct",
            columnList = "user_id, event_tag, event_ms, tag_date, id"
        )
    }
)
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant tagDate;
    private Instant submitDate;
    private String experimentName;
    private String eventTag;
    private String userId;
    private int eventMs;

    public TimeStamp() {
    }

    public TimeStamp(Long id, String userId, String eventTag, int eventMs, Instant tagDate) {
        this.id = id;
        this.tagDate = tagDate;
        this.eventTag = eventTag;
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

    public String getEventTag() {
        return eventTag;
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
}
