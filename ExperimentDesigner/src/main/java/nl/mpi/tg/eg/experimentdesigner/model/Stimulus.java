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
package nl.mpi.tg.eg.experimentdesigner.model;

import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @since Sep 16, 2015 11:15:06 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Stimulus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String mp3;
    private String mp4;
    private String ogg;
    private String webm;
    private String image;
    private String label;

    HashSet<String> stimulusTags;

    public Stimulus() {
    }

    public Stimulus(String mp3, String mp4, String ogg, String image, String label, HashSet<String> stimulusTags) {
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
        this.label = label;
        this.stimulusTags = new HashSet<>();
        for (String tag : stimulusTags) {
            addStimulusTag(tag);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    @XmlAttribute
    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public void setOgg(String ogg) {
        this.ogg = ogg;
    }

    public void setWebm(String webm) {
        this.webm = webm;
    }

    @XmlAttribute
    public String getOgg() {
        return ogg;
    }

    @XmlAttribute
    public String getWebm() {
        return webm;
    }

    @XmlAttribute
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlAttribute
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElement(name = "tag")
    public HashSet<String> getStimulusTags() {
        return stimulusTags;
    }

    final public void addStimulusTag(String stimulusTag) {
        stimulusTag = stimulusTag.replaceAll("[ \\t\\n\\x0B\\f\\r\\(\\)\\{\\};\\?\\/\\\\\\]\\[,'\"\\.=]+", "_");
        this.stimulusTags.add("tag_" + stimulusTag);
    }
}
