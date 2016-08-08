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

import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;

/**
 * @since May 3, 2016 1:34:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractWizardScreen implements WizardScreen {

    final PresenterScreen presenterScreen = new PresenterScreen();
    protected final WizardScreenData wizardScreenData;

    public AbstractWizardScreen() {
        this.wizardScreenData = new WizardScreenData();
    }

    public AbstractWizardScreen(WizardScreenData wizardScreenData) {
        this.wizardScreenData = wizardScreenData;
    }

    public AbstractWizardScreen(String screenTitle, String menuLabel, String screenTag) {
        this.wizardScreenData = new WizardScreenData();
        this.wizardScreenData.setScreenTitle(screenTitle);
        this.wizardScreenData.setMenuLabel(menuLabel);
        this.wizardScreenData.setScreenTag(screenTag.replaceAll("[^A-Za-z0-9]", "_"));
    }

    public String getScreenTitle() {
        return this.wizardScreenData.getScreenTitle();
    }

    public final void setScreenTitle(String screenTitle) {
        this.wizardScreenData.setScreenTitle(screenTitle);
    }

    public String getMenuLabel() {
        return this.wizardScreenData.getMenuLabel();
    }

    public final void setMenuLabel(String menuLabel) {
        this.wizardScreenData.setMenuLabel(menuLabel);
    }

    public String getScreenTag() {
        return this.wizardScreenData.getScreenTag();
    }

    public final void setScreenTag(String screenTag) {
        this.wizardScreenData.setScreenTag(screenTag);
    }

    @Override
    public WizardScreen getBackWizardScreen() {
        return this.wizardScreenData.getBackWizardScreen();
    }

    @Override
    public void setBackWizardScreen(WizardScreen backWizardScreen) {
        this.wizardScreenData.setBackWizardScreen(backWizardScreen);
    }

    @Override
    public WizardScreen getNextWizardScreen() {
        return this.wizardScreenData.getNextWizardScreen();
    }

    @Override
    public void setNextWizardScreen(WizardScreen nextWizardScreen) {
        this.wizardScreenData.setNextWizardScreen(nextWizardScreen);
    }

    @Override
    public String getScreenText() {
        return this.wizardScreenData.getScreenText();
    }

    @Override
    public final void setScreenText(String screenText) {
        this.wizardScreenData.setScreenText(screenText);
    }

    public boolean isCentreScreen() {
        return this.wizardScreenData.isCentreScreen();
    }

    public final void setCentreScreen(boolean centreScreen) {
        this.wizardScreenData.setCentreScreen(centreScreen);
    }

    @Override
    public String getNextButton() {
        return this.wizardScreenData.getNextButton();
    }

    @Override
    public final void setNextButton(String nextButton) {
        this.wizardScreenData.setNextButton(nextButton);
    }

    @Override
    public PresenterScreen getPresenterScreen() {
        return presenterScreen;
    }

    @Override
    public PresenterScreen populatePresenterScreen(final Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        presenterScreen.setTitle((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : getScreenTitle());
        presenterScreen.setMenuLabel((getMenuLabel() != null) ? getMenuLabel() : getScreenTitle());
        final String currentTagString = (getScreenTag() != null) ? getScreenTag() : getScreenTitle();
        presenterScreen.setSelfPresenterTag(currentTagString.replaceAll("[^A-Za-z0-9]", "_"));
        if (getBackWizardScreen() != null) {
            presenterScreen.setBackPresenter(getBackWizardScreen().getPresenterScreen());
        }
        if (getNextWizardScreen() != null) {
            presenterScreen.setNextPresenter(getNextWizardScreen().getPresenterScreen());
        }
        presenterScreen.setDisplayOrder(displayOrder);
        return presenterScreen;
    }
}
