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

    @Override
    public String getMenuLabel() {
        return this.wizardScreenData.getMenuLabel();
    }

    public final void setMenuLabel(String menuLabel) {
        this.wizardScreenData.setMenuLabel(menuLabel);
    }

    @Override
    public String getScreenTag() {
        return this.wizardScreenData.getScreenTag();
    }

    @Override
    public final void setScreenTag(String screenTag) {
        this.wizardScreenData.setScreenTag(screenTag);
    }

    @Override
    public WizardScreenData getWizardScreenData() {
        return wizardScreenData;
    }

    @Override
    public WizardScreenData getBackWizardScreenData() {
        return this.wizardScreenData.getBackWizardScreenData();
    }

    @Override
    public void setBackWizardScreenData(WizardScreenData backWizardScreenData) {
        this.wizardScreenData.setBackWizardScreenData(backWizardScreenData);
    }

    @Override
    public WizardScreenData getNextWizardScreenData() {
        return this.wizardScreenData.getNextWizardScreenData();
    }

    @Override
    public void setNextWizardScreenData(WizardScreenData nextWizardScreenData) {
        this.wizardScreenData.setNextWizardScreenData(nextWizardScreenData);
    }

    @Override
    public void setBackWizardScreen(WizardScreen backWizardScreen) {
        this.wizardScreenData.setBackWizardScreenData(backWizardScreen.getWizardScreenData());
    }

    @Override
    public void setNextWizardScreen(WizardScreen nextWizardScreen) {
        this.wizardScreenData.setNextWizardScreenData(nextWizardScreen.getWizardScreenData());
    }

    @Override
    public String getScreenText() {
        return this.wizardScreenData.getScreenText1();
    }

    @Override
    public final void setScreenText(String screenText) {
        this.wizardScreenData.setScreenText1(screenText);
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
        return this.wizardScreenData.getPresenterScreen();
    }

    @Override
    public PresenterScreen populatePresenterScreen(final Experiment experiment, boolean obfuscateScreenNames, long displayOrder) {
        this.wizardScreenData.getPresenterScreen().setTitle((obfuscateScreenNames) ? experiment.getAppNameDisplay() + " " + displayOrder : getScreenTitle());
        this.wizardScreenData.getPresenterScreen().setMenuLabel((getMenuLabel() != null) ? getMenuLabel() : getScreenTitle());
        final String currentTagString = (getScreenTag() != null) ? getScreenTag() : getScreenTitle();
        this.wizardScreenData.getPresenterScreen().setSelfPresenterTag(currentTagString.replaceAll("[^A-Za-z0-9]", "_"));
        if (getBackWizardScreenData() != null) {
            this.wizardScreenData.getPresenterScreen().setBackPresenter(getBackWizardScreenData().getPresenterScreen());
        }
        if (getNextWizardScreenData() != null) {
            this.wizardScreenData.getPresenterScreen().setNextPresenter(getNextWizardScreenData().getPresenterScreen());
        }
        this.wizardScreenData.getPresenterScreen().setDisplayOrder(displayOrder);
        return this.wizardScreenData.getPresenterScreen();
    }
}
