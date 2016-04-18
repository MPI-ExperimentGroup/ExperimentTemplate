/*
 * Copyright (C) 2014 Language In Interaction
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
package nl.ru.languageininteraction.synaesthesia.client.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.model.colour.ColourData;
import nl.mpi.tg.eg.experiment.client.model.colour.StimulusResponse;
import nl.mpi.tg.eg.experiment.client.model.colour.StimulusResponseGroup;

/**
 * @since Oct 31, 2014 3:48:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class ResultsSerialiser {

//    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    public String serialise(UserResults userResults, MetadataField postName_email) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StimulusResponseGroup responseGroup : userResults.getStimulusResponseGroups()) {
            final ArrayList<Stimulus> stimuliList = new ArrayList(responseGroup.getStimuli());
            stimuliList.sort(new Comparator<Stimulus>() {
                @Override
                public int compare(Stimulus o1, Stimulus o2) {
                    return o1.getLabel().compareTo(o2.getLabel());
                }
            });
            for (Stimulus stimulus : stimuliList) {
                for (StimulusResponse response : responseGroup.getResults(stimulus)) {
                    stringBuilder.append(userResults.getUserData().getMetadataValue(postName_email));
                    stringBuilder.append("\t");
                    stringBuilder.append(responseGroup.getPostName());
                    stringBuilder.append("\t");
                    stringBuilder.append(stimulus.getLabel());
                    stringBuilder.append("\t");
                    stringBuilder.append(formatDate(response.getTime()));
                    stringBuilder.append("\t");
                    stringBuilder.append(response.getDurationMs());
                    stringBuilder.append("\t");
                    final ColourData colour = response.getColour();
                    if (colour != null) {
                        stringBuilder.append(colour.getHexValue());
                        stringBuilder.append("\t");
                        stringBuilder.append(colour.getRed());
                        stringBuilder.append("\t");
                        stringBuilder.append(colour.getGreen());
                        stringBuilder.append("\t");
                        stringBuilder.append(colour.getBlue());
                    } else {
                        stringBuilder.append("\t\t\t");
                    }
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    protected abstract String formatDate(Date date);
}
