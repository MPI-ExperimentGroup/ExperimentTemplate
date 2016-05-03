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

import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;

/**
 * @since May 3, 2016 1:34:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractWizardScreen implements WizardScreen {

    final PresenterScreen presenterScreen = new PresenterScreen();
    WizardScreen backWizardScreen;
    WizardScreen nextWizardScreen;
    String screenText;
    String nextButton;
    String screenTitle;
    String screenTag;

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
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
}
