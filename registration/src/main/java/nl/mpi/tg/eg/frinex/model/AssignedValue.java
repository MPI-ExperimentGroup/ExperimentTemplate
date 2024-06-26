/*
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
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
import java.util.Objects;

/**
 * @since 04 June 2024 11:29 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AssignedValue {

    private final long assignedCount;
    private final Date lastChange;
    private final String value;

    public AssignedValue(long assignedCount, Date lastChange, String value) {
        this.assignedCount = assignedCount;
        this.lastChange = lastChange;
        this.value = value;
    }

    public long getAssignedCount() {
        return assignedCount;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.value);
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
        final AssignedValue other = (AssignedValue) obj;
        return Objects.equals(this.value, other.value);
    }

}
