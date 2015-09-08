/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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
package nl.mpi.tg.eg.experimentdesigner.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @since Sep 7, 2015 3:40:28 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String postName;
    private String registrationField;
    private String controlledRegex;
    private String controlledMessage;
    private boolean preventServerDuplicates;
    private String duplicatesControlledMessage;

    public Metadata() {
    }

    public long getId() {
        return id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getRegistrationField() {
        return registrationField;
    }

    public void setRegistrationField(String registrationField) {
        this.registrationField = registrationField;
    }

    public String getControlledRegex() {
        return controlledRegex;
    }

    public void setControlledRegex(String controlledRegex) {
        this.controlledRegex = controlledRegex;
    }

    public String getControlledMessage() {
        return controlledMessage;
    }

    public void setControlledMessage(String controlledMessage) {
        this.controlledMessage = controlledMessage;
    }

    public boolean isPreventServerDuplicates() {
        return preventServerDuplicates;
    }

    public void setPreventServerDuplicates(boolean preventServerDuplicates) {
        this.preventServerDuplicates = preventServerDuplicates;
    }

    public String getDuplicatesControlledMessage() {
        return duplicatesControlledMessage;
    }

    public void setDuplicatesControlledMessage(String duplicatesControlledMessage) {
        this.duplicatesControlledMessage = duplicatesControlledMessage;
    }

}
