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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @since Dec 1, 2015 1:32:47 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class PublishEvents implements Serializable {
 // TODO: tie this into the XSD
    public enum PublishState {
        validate,
        debug,
        draft,
        staging,
        production,
        transfer,
        undeploy
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publishDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiryDate;
    private PublishState publishState;
    private boolean isWebApp = true;
    private boolean isDesktop;
    private boolean isiOS;
    private boolean isAndroid;
    private boolean isUnity;
    private String productionServer;
    private String stagingServer;
    private String frinexVersion;
    @ManyToOne
    private Experiment experiment;
//    private String buildName;

    public PublishEvents() {
    }

    public PublishEvents(Experiment experiment, Date publishDate, Date expiryDate, PublishState publishState, boolean isWebApp, boolean isiOS, boolean isAndroid, boolean isUnity, boolean isDesktop, String stagingServer, String productionServer, String frinexVersion) {
        this.publishDate = publishDate;
        this.expiryDate = expiryDate;
        this.publishState = publishState;
        this.isWebApp = isWebApp;
        this.isiOS = isiOS;
        this.isAndroid = isAndroid;
        this.isUnity = isUnity;
        this.isDesktop = isDesktop;
        this.experiment = experiment;
        this.frinexVersion = frinexVersion;
//        this.buildName = experiment.getAppNameInternal();
    }
//
//    public String getBuildName() {
//        return buildName;
//    }

//    public String getExperimentInternalName() {
//        return experiment.getAppNameInternal();
//    }
//    public String getExperimentDisplayName() {
//        return experiment.getAppNameDisplay();
//    }
    @XmlAttribute
    public PublishState getState() {
        return publishState;
    }

    @XmlAttribute
    public String getPublishDate() {
        return (publishDate != null) ? new SimpleDateFormat("yyyy-MM-dd").format(publishDate) : "";
    }

    @XmlAttribute
    public String getExpiryDate() {
        return (expiryDate != null) ? new SimpleDateFormat("yyyy-MM-dd").format(expiryDate) : "";
    }

    @XmlAttribute
    public boolean isIsWebApp() {
        return isWebApp;
    }

    @XmlAttribute
    public boolean isIsiOS() {
        return isiOS;
    }

    @XmlAttribute
    public boolean isIsAndroid() {
        return isAndroid;
    }

    @XmlAttribute
    public boolean isIsUnity() {
        return isUnity;
    }

    @XmlAttribute
    public boolean isIsDesktop() {
        return isDesktop;
    }

    @XmlAttribute
    public String getStagingServer() {
        return stagingServer;
    }

    @XmlAttribute
    public String getProductionServer() {
        return productionServer;
    }

    @XmlAttribute
    public String getFrinexVersion() {
        return frinexVersion;
    }
    
    public void setPublishDate(String publishDate) {
        try {
            this.publishDate = new SimpleDateFormat("yyyy-MM-dd").parse(publishDate);
        } catch (ParseException exception) {
            System.out.println("publishDate: " + exception.getMessage());
        }
    }

    public void setExpiryDate(String expiryDate) {
        try {
            this.expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(expiryDate);
        } catch (ParseException exception) {
            System.out.println("expiryDate: " + exception.getMessage());
        }
    }

    public void setState(PublishState publishState) {
        this.publishState = publishState;
    }

    public void setIsWebApp(boolean isWebApp) {
        this.isWebApp = isWebApp;
    }

    public void setIsDesktop(boolean isDesktop) {
        this.isDesktop = isDesktop;
    }

    public void setIsiOS(boolean isiOS) {
        this.isiOS = isiOS;
    }

    public void setIsAndroid(boolean isAndroid) {
        this.isAndroid = isAndroid;
    }

    public void setIsUnity(boolean isUnity) {
        this.isUnity = isUnity;
    }
    
    public void setStagingServer(String stagingServer) {
        this.stagingServer = stagingServer;
    }
    
    public void setProductionServer(String productionServer) {
        this.productionServer = productionServer;
    }

    public void setFrinexVersion(String frinexVersion) {
        this.frinexVersion = frinexVersion;
    }
}
