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

/**
 * @since Jan 11, 2016 4:07:26 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardStimulus implements Stimulus {

    final private String uniqueId;
    final private String stimulusPath;
    final private String label;
    final private String code;
    final private int pauseMs;
    final private boolean mp3;
    final private boolean mp4;
    final private boolean ogg;
    final private boolean image;

    public SdCardStimulus(String uniqueId, String stimulusPath, String label, String code, int pauseMs, boolean mp3, boolean mp4, boolean ogg, boolean image) {
        this.uniqueId = uniqueId;
        this.stimulusPath = stimulusPath;
        this.label = label;
        this.code = code;
        this.pauseMs = pauseMs;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
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
    public String getMp3() {
        return mp3 ? stimulusPath : null;
    }

    @Override
    public String getImage() {
        return image ? stimulusPath : null;
    }

    @Override
    public String getMp4() {
        return mp4 ? stimulusPath : null;
    }

    @Override
    public String getOgg() {
        return ogg ? stimulusPath : null;
    }

    @Override
    public boolean isMp3() {
        return mp3;
    }

    @Override
    public boolean isMp4() {
        return mp4;
    }

    @Override
    public boolean isOgg() {
        return ogg;
    }

    @Override
    public boolean isImage() {
        return image;
    }

    @Override
    public List<?> getTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
