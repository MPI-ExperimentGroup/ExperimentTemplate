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
package nl.mpi.tg.eg.experimentdesigner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @since 06 March 2023 16:11 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Administration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //    @XmlElementWrapper(name = "dataManagement")
    @XmlAttribute(name = "allowDataDeletion")
    private Boolean allowDataDeletion = false;
    @XmlElement(name = "dataChannel")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DataChannel> dataChannels = new ArrayList<>();

    public Administration() {
    }

    public List<DataChannel> getDataChannels() {
        return dataChannels;
    }

    public Boolean getAllowDataDeletion() {
        // returning null to prevent the attibutes being added unless it is true
        return (this.allowDataDeletion) ? this.allowDataDeletion : null;
    }

    public void setDataChannels(List<DataChannel> dataChannels) {
        this.dataChannels = dataChannels;
    }

    public void setAllowDataDeletion(Boolean allowDataDeletionL) {
        this.allowDataDeletion = allowDataDeletionL;
    }
}
