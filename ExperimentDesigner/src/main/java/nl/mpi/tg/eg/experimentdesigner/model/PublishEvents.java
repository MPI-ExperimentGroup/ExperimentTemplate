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

import java.util.Date;
import javax.persistence.Temporal;

/**
 * @since Dec 1, 2015 1:32:47 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class PublishEvents {

    public enum ExperimentState {

        editing,
        testing,
        published
    };

    @Temporal(javax.persistence.TemporalType.DATE)
    private final Date compileDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Date expiryDate;

    private final ExperimentState experimentState;
    private final boolean isWebApp;
    private final boolean isiOS;
    private final boolean isAndroid;
    private final Experiment experiment;

    public PublishEvents(Experiment experiment, Date compileDate, Date expiryDate, ExperimentState experimentState, boolean isWebApp, boolean isiOS, boolean isAndroid) {
        this.compileDate = compileDate;
        this.expiryDate = expiryDate;
        this.experimentState = experimentState;
        this.isWebApp = isWebApp;
        this.isiOS = isiOS;
        this.isAndroid = isAndroid;
        this.experiment = experiment;
    }

    public String getExperimentName() {
        return experiment.getAppNameInternal();
    }

    public ExperimentState getExperimentState() {
        return experimentState;
    }

    public Date getCompileDate() {
        return compileDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean isIsWebApp() {
        return isWebApp;
    }

    public boolean isIsiOS() {
        return isiOS;
    }

    public boolean isIsAndroid() {
        return isAndroid;
    }
}
