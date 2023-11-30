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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @since Sep 4, 2015 2:42:21 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
@XmlRootElement
@XmlType(propOrder = {"publishEvents", "validationService", "administration", "scss", "metadata", "presenterScreen", "stimuli"})
public class Experiment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private long id;
    @XmlAttribute
    private String appNameDisplay;
    @XmlAttribute
    private boolean showMenuBar = true;
    @Column(unique = true)
    @XmlAttribute
    private String appNameInternal;
    @XmlAttribute
    private String resourceNetworkPath; // path to MPI_Scratch that contains any resource files needed for the experiment
//    private String nextPresenterTag;
    @XmlAttribute
    private String primaryColour0;
    @XmlAttribute
    private String primaryColour1;
    @XmlAttribute
    private String primaryColour2;
    @XmlAttribute
    private String primaryColour3;
    @XmlAttribute
    private String primaryColour4;
    @XmlAttribute
    private String complementColour0;
    @XmlAttribute
    private String complementColour1;
    @XmlAttribute
    private String complementColour2;
    @XmlAttribute
    private String complementColour3;
    @XmlAttribute
    private String complementColour4;
    @XmlAttribute
    private String backgroundColour;
    @XmlAttribute
    private int textFontSize = 17;
    @XmlAttribute
    private boolean isScalable = true;
    @XmlAttribute
    private boolean isRotatable = true;
    @XmlAttribute
    private boolean preserveLastState = true;
    private boolean preventWindowClose = true;
    @XmlAttribute
    private String defaultLocale;
    @XmlAttribute
    private String availableLocales;
    @XmlAttribute
    private float defaultScale = 1;
    @XmlElement(name = "scss")
    private String scss;
    @XmlElement(name = "validationService")
    private ValidationService validationService;
    @XmlElement(name = "administration")
    private Administration administration = new Administration();

    @XmlElement(name = "deployment")
    @OneToMany(mappedBy = "experiment")
    private List<PublishEvents> publishEvents = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlElement(name = "presenter")
    @OrderBy("displayOrder ASC")
    private List<PresenterScreen> presenterScreen = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlElementWrapper(name = "metadata")
    @XmlElement(name = "field")
    private List<Metadata> metadata = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlElementWrapper(name = "stimuli")
    @XmlElement(name = "stimulus")
    private List<Stimulus> stimuli = new ArrayList<>();

    public Experiment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        this.appNameInternal = appNameInternal.toLowerCase();
    }

    public boolean isShowMenuBar() {
        return showMenuBar;
    }

    public void setShowMenuBar(boolean showMenuBar) {
        this.showMenuBar = showMenuBar;
    }

    public int getTextFontSize() {
        return textFontSize;
    }

    public void setTextFontSize(int textFontSize) {
        this.textFontSize = textFontSize;
    }

    public List<PublishEvents> getPublishEvents() {
        return publishEvents;
    }

    public Administration getAdministration() {
        return administration;
    }

    public void setAdministration(Administration administration) {
        this.administration = administration;
    }

    public String getScss() {
        return scss;
    }

    public void setScss(String scss) {
        this.scss = scss;
    }

    public ValidationService getValidationService() {
        return validationService;
    }

    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public String getDefaultLocale() {
        return (defaultLocale == null || defaultLocale.isEmpty()) ? "en" : defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public String getAvailableLocales() {
        return (availableLocales == null || availableLocales.isEmpty()) ? "en" : availableLocales;
    }

    public void setAvailableLocales(String availableLocales) {
        this.availableLocales = availableLocales;
    }

    public boolean isIsScalable() {
        return isScalable;
    }

    public void setIsScalable(boolean isScalable) {
        this.isScalable = isScalable;
    }

    public boolean isRotatable() {
        return isRotatable;
    }

    public void setRotatable(boolean isRotatable) {
        this.isRotatable = isRotatable;
    }

    public boolean isPreserveLastState() {
        return preserveLastState;
    }

    public void setPreserveLastState(boolean preserveLastState) {
        this.preserveLastState = preserveLastState;
    }

    public boolean preventWindowClose() {
        return preventWindowClose;
    }

    public void setPreventWindowClose(boolean preventWindowClose) {
        this.preventWindowClose = preventWindowClose;
    }

    public float getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(float defaultScale) {
        this.defaultScale = defaultScale;
    }

    public String getResourceNetworkPath() {
        return resourceNetworkPath;
    }

    public void setResourceNetworkPath(String resourceNetworkPath) {
        this.resourceNetworkPath = resourceNetworkPath;
    }

    public void setPrimaryColour1(String primaryColour1) {
        this.primaryColour1 = primaryColour1;
    }

    public String getPrimaryColour1() {
        return primaryColour1;
    }

    public void setPrimaryColour2(String primaryColour2) {
        this.primaryColour2 = primaryColour2;
    }

    public String getPrimaryColour2() {
        return primaryColour2;
    }

    public void setPrimaryColour3(String primaryColour3) {
        this.primaryColour3 = primaryColour3;
    }

    public String getPrimaryColour3() {
        return primaryColour3;
    }

    public String getComplementColour1() {
        return complementColour1;
    }

    public void setComplementColour1(String complementColour1) {
        this.complementColour1 = complementColour1;
    }

    public String getComplementColour2() {
        return complementColour2;
    }

    public void setComplementColour2(String complementColour2) {
        this.complementColour2 = complementColour2;
    }

    public String getComplementColour3() {
        return complementColour3;
    }

    public void setComplementColour3(String complementColour3) {
        this.complementColour3 = complementColour3;
    }

    public String getPrimaryColour0() {
        return primaryColour0;
    }

    public void setPrimaryColour0(String primaryColour0) {
        this.primaryColour0 = primaryColour0;
    }

    public String getPrimaryColour4() {
        return primaryColour4;
    }

    public void setPrimaryColour4(String primaryColour4) {
        this.primaryColour4 = primaryColour4;
    }

    public String getComplementColour0() {
        return complementColour0;
    }

    public void setComplementColour0(String complementColour0) {
        this.complementColour0 = complementColour0;
    }

    public String getComplementColour4() {
        return complementColour4;
    }

    public void setComplementColour4(String complementColour4) {
        this.complementColour4 = complementColour4;
    }

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(String backgroundColour) {
        this.backgroundColour = backgroundColour;
    }

//    public String getNextPresenterTag() {
//        return nextPresenterTag;
//    }
//
//    public void setNextPresenterTag(String nextPresenterTag) {
//        this.nextPresenterTag = nextPresenterTag;
//    }
    public List<PresenterScreen> getPresenterScreen() {
        return presenterScreen;
    }

    public void setPresenterScreen(List<PresenterScreen> PresenterScreen) {
        this.presenterScreen = PresenterScreen;
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

//    @XmlAttribute
//    public int getMetadataCount() {
//        return metadata.size();
//    }
//    @Transient
    public void addMetadataOnce(Metadata metadataField) {
        if (!metadata.contains(metadataField)) {
            metadata.add(metadataField);
        }
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    public List<Stimulus> getStimuli() {
        return stimuli;
    }

    public void setStimuli(List<Stimulus> stimuli) {
        this.stimuli = stimuli;
    }

//    @Transient
    public void appendUniqueStimuli(List<Stimulus> stimuliList) {
        if (stimuliList != null) {
            for (Stimulus stimulus : stimuliList) {
                if (!this.stimuli.contains(stimulus)) {
                    this.stimuli.add(stimulus);
                } else {
                    final int indexExisting = this.stimuli.indexOf(stimulus);
                    this.stimuli.get(indexExisting).getStimulusTags().addAll(stimulus.getStimulusTags());
                }
            }
        }
    }
}
