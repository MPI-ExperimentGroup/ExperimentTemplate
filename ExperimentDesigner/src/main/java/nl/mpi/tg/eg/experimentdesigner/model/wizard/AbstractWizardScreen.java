/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner.model.wizard;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;

/**
 * @since May 3, 2016 1:34:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Entity
public abstract class AbstractWizardScreen implements WizardScreen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Transient
    final PresenterScreen presenterScreen = new PresenterScreen();
    @OneToOne(targetEntity = AbstractWizardScreen.class, cascade = CascadeType.ALL)
    WizardScreen backWizardScreen = null;
    @OneToOne(targetEntity = AbstractWizardScreen.class, cascade = CascadeType.ALL)
    WizardScreen nextWizardScreen = null;
    @Size(max = 3500)
    String screenText = null;
    String nextButton = null;
    String screenTitle = null;
    String menuLabel = null;
    String screenTag = null;
    boolean centreScreen = true;

    public AbstractWizardScreen() {
    }

    public AbstractWizardScreen(String screenTitle, String menuLabel, String screenTag) {
        this.screenTitle = screenTitle;
        this.menuLabel = menuLabel;
        this.screenTag = screenTag.replaceAll("[^A-Za-z0-9]", "_");
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getScreenTag() {
        return screenTag;
    }

    public void setScreenTag(String screenTag) {
        this.screenTag = screenTag;
    }

    @Override
    public WizardScreen getBackWizardScreen() {
        return backWizardScreen;
    }

    @Override
    public void setBackWizardScreen(WizardScreen backWizardScreen) {
        this.backWizardScreen = backWizardScreen;
    }

    @Override
    public WizardScreen getNextWizardScreen() {
        return nextWizardScreen;
    }

    @Override
    public void setNextWizardScreen(WizardScreen nextWizardScreen) {
        this.nextWizardScreen = nextWizardScreen;
    }

    @Override
    public String getScreenText() {
        return screenText;
    }

    @Override
    public void setScreenText(String screenText) {
        this.screenText = screenText;
    }

    public boolean isCentreScreen() {
        return centreScreen;
    }

    public void setCentreScreen(boolean centreScreen) {
        this.centreScreen = centreScreen;
    }

    @Override
    public String getNextButton() {
        return nextButton;
    }

    @Override
    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }

    @Override
    public PresenterScreen getPresenterScreen() {
        return presenterScreen;
    }

    @Override
    public PresenterScreen populatePresenterScreen(final Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        presenterScreen.setTitle((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : screenTitle);
        presenterScreen.setMenuLabel((menuLabel != null) ? menuLabel : screenTitle);
        final String currentTagString = (getScreenTag() != null) ? getScreenTag() : screenTitle;
        presenterScreen.setSelfPresenterTag(currentTagString.replaceAll("[^A-Za-z0-9]", "_"));
        if (backWizardScreen != null) {
            presenterScreen.setBackPresenter(backWizardScreen.getPresenterScreen());
        }
        if (nextWizardScreen != null) {
            presenterScreen.setNextPresenter(nextWizardScreen.getPresenterScreen());
        }
        presenterScreen.setDisplayOrder(displayOrder);
        return presenterScreen;
    }
}
