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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @since Sep 4, 2015 2:42:21 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public class Experiment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String appNameDisplay;
    private String appNameInternal;
    private String dataSubmitUrl;
    private String staticFilesUrl;
//    private String nextPresenterTag;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresenterScreen> PresenterScreen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Metadata> metadata;

    public Experiment() {
    }

    public long getId() {
        return id;
    }

    public String getAppNameDisplay() {
        return appNameDisplay;
    }

    public void setAppNameDisplay(String appNameDisplay) {
        this.appNameDisplay = appNameDisplay;
    }

    public String getAppNameInternal() {
        return appNameInternal;
    }

    public void setAppNameInternal(String appNameInternal) {
        this.appNameInternal = appNameInternal;
    }

    public String getDataSubmitUrl() {
        return dataSubmitUrl;
    }

    public void setDataSubmitUrl(String dataSubmitUrl) {
        this.dataSubmitUrl = dataSubmitUrl;
    }

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
    public List<PresenterScreen> getPresenterScreen() {
        return PresenterScreen;
    }

    public void setPresenterScreen(List<PresenterScreen> PresenterScreen) {
        this.PresenterScreen = PresenterScreen;
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }
}
