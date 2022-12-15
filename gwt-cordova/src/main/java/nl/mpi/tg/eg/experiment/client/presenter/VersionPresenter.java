/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.Version;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;

/**
 * @since Jul 30, 2018 11:31:58 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class VersionPresenter extends AbstractPresenter {

    private final Version version = GWT.create(Version.class);

    public VersionPresenter(RootLayoutPanel widgetTag, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new ComplexView(), userResults, localStorage, timerService);
    }

    @Override
    protected String getTitle() {
        return "Version";
    }

    @Override
    protected String getSelfTag() {
        return "Version";
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
        ((ComplexView) simpleView).addText("Framework For Interactive Experiments\n"
                + "DOI 10.5281/zenodo.3522910" + "\n"
                + "FRINEX Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "\n"
                + "Project Version: "
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Commit Date: " + version.lastCommitDate());
        // TODO: add the audio recorder version / branch 
        // TODO: add all the jar version numbers
        // TODO: add the webjars version numbers
    }

    @Override
    public void savePresenterState() {
        super.cleanUpPresenterState();
    }
}
