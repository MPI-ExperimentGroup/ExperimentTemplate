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

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * @since Jun 30, 2015 4:38:38 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;
    private String workerId;
    private String userId;
    private String otherLanguages;
    private String nativeLanguage;

    public Participant() {
    }

    public Participant(Date submitDate, String workerId, String userId, String otherLanguages, String nativeLanguage) {
        this.submitDate = submitDate;
        this.workerId = workerId;
        this.userId = userId;
        this.otherLanguages = otherLanguages;
        this.nativeLanguage = nativeLanguage;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getOtherLanguages() {
        return otherLanguages;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }
}
