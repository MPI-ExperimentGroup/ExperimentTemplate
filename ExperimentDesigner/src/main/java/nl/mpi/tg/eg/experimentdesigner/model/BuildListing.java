/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @since 04-03-2020 11:27 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@JsonPropertyOrder({"publishDate", "expiryDate", "isWebApp", "isDesktop", "isiOS", "isAndroid", "isUnity", "buildName", "registrationUrlStaging", "registrationUrlProduction", "state", "stagingServer", "productionServer", "frinexVersion", "defaultScale", "isScalable", "isRotatable", "allowDelete", "experimentInternalName", "experimentDisplayName", "defaultLocale", "availableLocales"})
public class BuildListing {

    final private Experiment experiment;
    final String frinexVersion;

    public BuildListing(Experiment experiment, final String frinexVersion) {
        this.experiment = experiment;
        this.frinexVersion = frinexVersion;
    }

    public String getExperimentInternalName() {
        return experiment.getAppNameInternal();
    }

    public String getExperimentDisplayName() {
        return experiment.getAppNameDisplay();
    }

    public String getRegistrationUrlStaging() {
        return (experiment.getValidationService() == null) ? null : experiment.getValidationService().getRegistrationUrlStaging();
    }

    public String getRegistrationUrlProduction() {
        return (experiment.getValidationService() == null) ? null : experiment.getValidationService().getRegistrationUrlProduction();
    }

    public PublishEvents.PublishState getState() {
        return (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getState();
    }

    public String getProductionServer() {
        return (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getProductionServer();
    }

    public String getStagingServer() {
        return (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getStagingServer();
    }

    public String getFrinexVersion() {
        return (frinexVersion != null) ? frinexVersion : (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getFrinexVersion();
    }

    public String getPublishDate() {
        return (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getPublishDate();
    }

    public String getExpiryDate() {
        return (experiment.getPublishEvents().isEmpty()) ? null : experiment.getPublishEvents().get(0).getExpiryDate();
    }

    public boolean isIsWebApp() {
        return (experiment.getPublishEvents().isEmpty()) ? false : experiment.getPublishEvents().get(0).isIsWebApp();
    }

    public boolean isIsiOS() {
        return (experiment.getPublishEvents().isEmpty()) ? false : experiment.getPublishEvents().get(0).isIsiOS();
    }

    public boolean isIsAndroid() {
        return (experiment.getPublishEvents().isEmpty()) ? false : experiment.getPublishEvents().get(0).isIsAndroid();
    }

    public boolean isIsUnity() {
        return (experiment.getPublishEvents().isEmpty()) ? false : experiment.getPublishEvents().get(0).isIsUnity();
    }

    public boolean isIsDesktop() {
        return (experiment.getPublishEvents().isEmpty()) ? false : experiment.getPublishEvents().get(0).isIsDesktop();
    }

    public String getAvailableLocales() {
        return experiment.getAvailableLocales();
    }

    public String getDefaultLocale() {
        return experiment.getDefaultLocale();
    }

    public float getDefaultScale() {
        return experiment.getDefaultScale();
    }

    public String getIsScalable() {
        return (experiment.isIsScalable()) ? "yes" : "no";
    }

    public boolean getIsRotatable() {
        return (experiment.isRotatable());
    }

    public boolean getAllowDelete() {
        if (experiment.getAdministration().getAllowDataDeletion() == null) {
            return false;
        } else {
            return experiment.getAdministration().getAllowDataDeletion();
        }
    }
}
