/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.model;

import java.util.Objects;

/**
 * @since Feb 4, 2019 2:01:07 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedEvent {

    private final String eventName;
    private final long eventMs;

    public TimedEvent(String eventName, long eventMs) {
        this.eventName = eventName;
        this.eventMs = eventMs;
    }

    public String getEventName() {
        return eventName;
    }

    public long getEventMs() {
        return eventMs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.eventName);
        hash = 79 * hash + (int) (this.eventMs ^ (this.eventMs >>> 32));
        return hash;
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
        final TimedEvent other = (TimedEvent) obj;
        if (this.eventMs != other.eventMs) {
            return false;
        }
        if (!Objects.equals(this.eventName, other.eventName)) {
            return false;
        }
        return true;
    }
}
