/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.model;

import nl.mpi.tg.eg.frinex.common.model.AbstractStimulus;

/**
 * @since Oct 27, 2017 2:13:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AdVocAsAtomStimulus extends AbstractStimulus {

    public enum Tag implements nl.mpi.tg.eg.frinex.common.model.Stimulus.Tag {

        tag_set1, tag_set2, tag_set3
    }
   
    public AdVocAsAtomStimulus(String uniqueId, Tag[] tags, String label, String ratingLabels, String correctResponses) {
        super(uniqueId, tags, label, ratingLabels, correctResponses);
    }
}
