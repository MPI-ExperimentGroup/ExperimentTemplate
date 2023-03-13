/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

/**
 * @since 26 Feb 2021 15:06:33 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicStatistics {

    public Date firstDeploymentAccessed;
    public long totalDeploymentsAccessed;
    public long totalPageLoads;
    public long totalParticipantsSeen;
    public long totalStimulusResponses;
    public long totalMediaResponses;
    public long totalDeletionEvents;
    public Date firstParticipantSeen;
    public Date lastParticipantSeen;
    public Date[][] participantsFirstAndLastSeen;
    public Date[][] sessionFirstAndLastSeen;

    public PublicStatistics() {
    }
}
