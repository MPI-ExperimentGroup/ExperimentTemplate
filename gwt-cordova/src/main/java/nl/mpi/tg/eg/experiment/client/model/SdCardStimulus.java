/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.List;
import java.util.Objects;

/**
 * @since Jan 11, 2016 4:07:26 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimulus implements Stimulus {

    final private String uniqueId;
    final private String stimulusBasePath;
    final private String label;
    final private String code;
    final private int pauseMs;
    final private boolean isAudio;
    final private boolean isVideo;
    final private boolean isImage;

    public SdCardStimulus(String uniqueId, String stimulusBasePath, String label, String code, int pauseMs, boolean isAudio, boolean isVideo, boolean image) {
        this.uniqueId = uniqueId;
        this.stimulusBasePath = stimulusBasePath;
        this.label = label;
        this.code = code;
        this.pauseMs = pauseMs;
        this.isAudio = isAudio;
        this.isVideo = isVideo;
        this.isImage = image;
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getPauseMs() {
        return pauseMs;
    }

    @Override
    public String getAudio() {
        return isAudio ? stimulusBasePath : null;
    }

    @Override
    public String getImage() {
        return isImage ? stimulusBasePath : null;
    }

    @Override
    public String getVideo() {
        return isVideo ? stimulusBasePath : null;
    }

    @Override
    public boolean hasImage() {
        return isImage;
    }

    @Override
    public boolean hasAudio() {
        return isAudio;
    }

    @Override
    public boolean hasVideo() {
        return isVideo;
    }

    @Override
    public boolean hasRatingLabels() {
        return false;
    }

    @Override
    public String getRatingLabels() {
        return null;
    }

    @Override
    public List<?> getTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Stimulus o) {
        return this.uniqueId.compareTo(o.getUniqueId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.uniqueId);
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
        final Stimulus other = (Stimulus) obj;
        if (!Objects.equals(this.uniqueId, other.getUniqueId())) {
            return false;
        }
        return true;
    }
}
