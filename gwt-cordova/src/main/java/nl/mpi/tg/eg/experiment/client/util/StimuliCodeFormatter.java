/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.util;

import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Sep 5, 2018 4:01:01 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Deprecated
// the use of StimuliCodeFormatter has been replaced with HtmlTokenFormatter
public class StimuliCodeFormatter {

    static final public String getFormattedStimulusCode(final Stimulus currentStimulus, final String codeFormat) {
        String formattedCodeTemp = codeFormat.replace("<code>", currentStimulus.getCode());
        if (currentStimulus.hasRatingLabels()) {
            int index = 0;
            for (final String ratingLabel : currentStimulus.getRatingLabels().split(",")) {
                formattedCodeTemp = formattedCodeTemp.replace("<rating_" + index + ">", ratingLabel);
                index++;
            }
        }
        return formattedCodeTemp;
    }
}
