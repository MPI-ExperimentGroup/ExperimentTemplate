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
package nl.mpi.tg.eg.frinex.common.model;

import java.util.List;

/**
 * @since Jan 11, 2016 4:04:44 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public interface Stimulus extends Comparable<Stimulus> {

    interface Tag {

        String name();
    };

    String getCode();

    String getLabel();

    int getPauseMs();

    String getUniqueId();

    boolean hasImage();

    boolean hasAudio();

    boolean hasVideo();

    boolean hasRatingLabels();

    boolean hasCorrectResponses();

    boolean isCorrect(String value);

    String getAudio();

    String getImage();

    String getVideo();

    String getRatingLabels();

    String getCorrectResponses();

    List<?> getTags();
}
