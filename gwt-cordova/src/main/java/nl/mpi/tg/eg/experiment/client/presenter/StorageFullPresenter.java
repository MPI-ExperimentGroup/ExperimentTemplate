/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;

/**
 * @since Dec 8, 2016 2:36:10 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StorageFullPresenter extends LocalStoragePresenter implements Presenter {

    private final Version version = GWT.create(Version.class);
    private final String errorMessage;

    public StorageFullPresenter(RootLayoutPanel widgetTag, String errorMessage) {
        super(widgetTag);
        this.errorMessage = errorMessage;
    }

    @Override
    protected String getTitle() {
        return "StorageFullPresenter";
    }

    @Override
    protected String getSelfTag() {
        return "StorageFullPresenter";
    }

    @Override
    protected void setContent(final AppEventListener appEventListener) {
        ((ComplexView) simpleView).addHtmlText(messages.errorScreenText(errorMessage), "highlightedText");
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText("Framework For Interactive Experiments\n"
        + "DOI 10.5281/zenodo.3522910" + "\n"
        + "FRINEX Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "\n"
                + "Project Version: "
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Commit Date: " + version.lastCommitDate());
        ((ComplexView) simpleView).addPadding();
        eraseLocalStorageButton(null, "eraseLocalStorageButton");
        ((ComplexView) simpleView).addPadding();
        localStorageData();
    }
}
