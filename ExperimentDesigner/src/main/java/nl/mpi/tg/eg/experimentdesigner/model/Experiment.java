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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @since Sep 4, 2015 2:42:21 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@XmlRootElement
public class Experiment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String appNameDisplay;
    private String appNameInternal;
    private String dataSubmitUrl;
    private String staticFilesUrl;
//    private String nextPresenterTag;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PresenterScreen> PresenterScreen = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Metadata> metadata = new ArrayList<>();

    public Experiment() {
    }

    public long getId() {
        return id;
    }

    @XmlAttribute
    public String getAppNameDisplay() {
        return appNameDisplay;
    }

    public void setAppNameDisplay(String appNameDisplay) {
        this.appNameDisplay = appNameDisplay;
    }

    @XmlAttribute
    public String getAppNameInternal() {
        return appNameInternal;
    }

    public void setAppNameInternal(String appNameInternal) {
        this.appNameInternal = appNameInternal;
    }

    @XmlAttribute
    public String getDataSubmitUrl() {
        return dataSubmitUrl;
    }

    public void setDataSubmitUrl(String dataSubmitUrl) {
        this.dataSubmitUrl = dataSubmitUrl;
    }

    @XmlAttribute
    public String getStaticFilesUrl() {
        return staticFilesUrl;
    }

    public void setStaticFilesUrl(String staticFilesUrl) {
        this.staticFilesUrl = staticFilesUrl;
    }

//    public String getNextPresenterTag() {
//        return nextPresenterTag;
//    }
//
//    public void setNextPresenterTag(String nextPresenterTag) {
//        this.nextPresenterTag = nextPresenterTag;
//    }
    @XmlElement
    public List<PresenterScreen> getPresenterScreen() {
        return PresenterScreen;
    }

    public void setPresenterScreen(List<PresenterScreen> PresenterScreen) {
        this.PresenterScreen = PresenterScreen;
    }

    @XmlElementWrapper(name = "metadata")
    @XmlElement(name = "field")
    public List<Metadata> getMetadata() {
        return metadata;
    }

//    @XmlAttribute
//    public int getMetadataCount() {
//        return metadata.size();
//    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }
}
