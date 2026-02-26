/*
 * Copyright (C) 2023 Max Planck Institute for Psycholinguistics, Nijmegen
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
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import java.time.Instant;

/**
 * @since 08 March 2023 15:47 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class DataDeletionLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant deletionDate;
    private String deletionAddr;
    public Instant firstDeploymentAccessed;
    public long totalDeploymentsAccessed;
    public long totalPageLoads;
    public long totalStimulusResponses;
    public long totalMediaResponses;
    public Instant participantsFirstSeen;
    public Instant participantsLastSeen;
    public Instant sessionFirstSeen;
    public Instant sessionLastSeen;

    public Instant getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Instant deletionDate) {
        this.deletionDate = deletionDate;
    }

    public String getDeletionAddr() {
        return deletionAddr;
    }

    public void setDeletionAddr(String deletionAddr) {
        this.deletionAddr = deletionAddr;
    }

    public Instant getFirstDeploymentAccessed() {
        return firstDeploymentAccessed;
    }

    public void setFirstDeploymentAccessed(Instant firstDeploymentAccessed) {
        this.firstDeploymentAccessed = firstDeploymentAccessed;
    }

    public long getTotalDeploymentsAccessed() {
        return totalDeploymentsAccessed;
    }

    public void setTotalDeploymentsAccessed(long totalDeploymentsAccessed) {
        this.totalDeploymentsAccessed = totalDeploymentsAccessed;
    }

    public long getTotalPageLoads() {
        return totalPageLoads;
    }

    public void setTotalPageLoads(long totalPageLoads) {
        this.totalPageLoads = totalPageLoads;
    }

    public long getTotalStimulusResponses() {
        return totalStimulusResponses;
    }

    public void setTotalStimulusResponses(long totalStimulusResponses) {
        this.totalStimulusResponses = totalStimulusResponses;
    }

    public long getTotalMediaResponses() {
        return totalMediaResponses;
    }

    public void setTotalMediaResponses(long totalMediaResponses) {
        this.totalMediaResponses = totalMediaResponses;
    }

    public Instant getParticipantsFirstSeen() {
        return participantsFirstSeen;
    }

    public void setParticipantsFirstSeen(Instant participantsFirstSeen) {
        this.participantsFirstSeen = participantsFirstSeen;
    }

    public Instant getParticipantsLastSeen() {
        return participantsLastSeen;
    }

    public void setParticipantsLastSeen(Instant participantsLastSeen) {
        this.participantsLastSeen = participantsLastSeen;
    }

    public Instant getSessionFirstSeen() {
        return sessionFirstSeen;
    }

    public void setSessionFirstSeen(Instant sessionFirstSeen) {
        this.sessionFirstSeen = sessionFirstSeen;
    }

    public Instant getSessionLastSeen() {
        return sessionLastSeen;
    }

    public void setSessionLastSeen(Instant sessionLastSeen) {
        this.sessionLastSeen = sessionLastSeen;
    }
}
