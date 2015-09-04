/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @since Aug 18, 2015 4:39:26 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class PresenterFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private FeatureType featureType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresenterFeature> presenterFeatures;
//    private Map<FeatureAttribute, String> featureAttributes;
    private String featureText;

    public PresenterFeature() {
    }

    public long getId() {
        return id;
    }

    public List<PresenterFeature> getPresenterFeatures() {
        return presenterFeatures;
    }

    public void setPresenterFeatures(List<PresenterFeature> presenterFeatures) {
        this.presenterFeatures = presenterFeatures;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    public String getFeatureText() {
        return featureText;
    }

    public void setFeatureText(String featureText) {
        this.featureText = featureText;
    }
}
