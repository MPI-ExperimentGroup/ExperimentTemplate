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
package nl.mpi.tg.eg.experimentdesigner.model;

import java.util.List;

/**
 * @since Aug 24, 2018 10:25:32 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class CanHaveFeatures {

    public PresenterFeature[] addFeatures(FeatureType... featureTypes) {
        PresenterFeature returnPresenterFeatures[] = new PresenterFeature[featureTypes.length];
        int index = 0;
        for (FeatureType feature : featureTypes) {
            returnPresenterFeatures[index] = addFeature(feature, null);
            index++;
        }
        return returnPresenterFeatures;
    }

    public PresenterFeature addFeature(FeatureType featureType, String text, String... attributes) {
        if (featureType.canHaveText() == (text != null && !text.isEmpty())) {
            final PresenterFeature presenterFeature = new PresenterFeature(featureType, text);
            getPresenterFeatureList().add(presenterFeature);
            if (featureType.getFeatureAttributes() != null) {
                for (int index = 0; index < featureType.getFeatureAttributes().length; index++) {
                    presenterFeature.addFeatureAttributes(featureType.getFeatureAttributes()[index], attributes[index]);
                }
            }
            return presenterFeature;
        } else {
            throw new UnsupportedOperationException(featureType.name() + ((featureType.canHaveText()) ? " requires feature text." : " cannot have text"));
        }
    }

    abstract public List<PresenterFeature> getPresenterFeatureList();
}
