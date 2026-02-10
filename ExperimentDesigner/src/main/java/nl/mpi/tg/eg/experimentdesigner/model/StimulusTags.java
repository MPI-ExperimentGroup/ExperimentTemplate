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
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * @since 09 Feb 2023 17:01 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class StimulusTags implements Serializable {
    // TODO: create a demonstrator that utilises the list field to load stimuli based on the stimuli IDs stored in the named metadata field

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String idListField;
    @ElementCollection(targetClass = String.class)
    private List<String> stimulusTags;

    public StimulusTags() {
        idListField = null;
        stimulusTags = new ArrayList<>();
    }

    public StimulusTags(String idListField, List<String> stimulusTags) {
        this.idListField = idListField;
        this.stimulusTags = stimulusTags;
    }

    @XmlAttribute
    public String getIdListField() {
        return idListField;
    }

    public void setIdListField(String idListField) {
        this.idListField = idListField;
    }

    @XmlElement(name = "tag")
    public List<String> getStimulusTags() {
        return stimulusTags;
    }

    public void setStimulusTags(List<String> stimulusTags) {
        this.stimulusTags = stimulusTags;
    }
}
