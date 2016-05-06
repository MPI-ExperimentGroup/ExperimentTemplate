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

import java.io.Serializable;
import java.util.HashSet;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @since Sep 16, 2015 11:15:06 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Stimulus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int pauseMs;

    private String mp3;
    private String mp4;
    private String ogg;
    private String webm;
    private String image;
    private String baseFilePath;
    @Size(max = 5500)
    private String label;
    private String code;
    private byte[] imageData = null;
    private byte[] audioData = null;
    private byte[] videoData = null;

    TreeSet<String> stimulusTags;

    public Stimulus() {
    }

    public Stimulus(String baseFilePath, String mp3, String mp4, String ogg, String image, String label, String code, int pauseMs, HashSet<String> stimulusTags) {
        this.baseFilePath = baseFilePath;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
        this.label = label;
        this.code = code;
        this.pauseMs = pauseMs;
        this.stimulusTags = new TreeSet<>();
        for (String tag : stimulusTags) {
            addStimulusTag(tag);
        }
    }

    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlAttribute
    public int getPauseMs() {
        return pauseMs;
    }

    public void setPauseMs(int pauseMs) {
        this.pauseMs = pauseMs;
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

    @XmlAttribute
    public String getBaseFilePath() {
        return baseFilePath;
    }

    public void setBaseFilePath(String baseFileName) {
        this.baseFilePath = baseFileName;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlTransient
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @XmlTransient
    public byte[] getAudioData() {
        return audioData;
    }

    public void setAudioData(byte[] audioData) {
        this.audioData = audioData;
    }

    @XmlTransient
    public byte[] getVideoData() {
        return videoData;
    }

    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }

    @XmlElement(name = "tag")
    public TreeSet<String> getStimulusTags() {
        return stimulusTags;
    }

    final public void addStimulusTag(String stimulusTag) {
        this.stimulusTags.add(cleanTagString(stimulusTag));
    }

    public static String cleanTagString(String stimulusTag) {
        return stimulusTag.replaceAll("[ \\t\\n\\x0B\\f\\r\\(\\)\\{\\};\\?\\/\\\\\\]\\[,'\"\\.=]+", "_");
    }
}
