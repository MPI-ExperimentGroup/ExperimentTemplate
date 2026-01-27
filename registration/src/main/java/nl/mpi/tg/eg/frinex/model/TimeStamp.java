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

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;

/**
 * @since Jul 8, 2015 5:36:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date tagDate;
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String experimentName;
    private String eventTag;
    private String userId;
    private int eventMs;

    public TimeStamp() {
    }

    public TimeStamp(String userId, String eventTag, int eventMs, Date tagDate) {
        this.tagDate = tagDate;
        this.eventTag = eventTag;
        this.userId = userId;
        this.eventMs = eventMs;
    }

    public long getId() {
        return id;
    }

    public Date getTagDate() {
        return tagDate;
    }

    public Date getSubmitDate() {
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

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}
