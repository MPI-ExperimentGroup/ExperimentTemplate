/*
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;

/**
 * @since 29 February 2024 16:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardStimulusPresenter extends AbstractStimulusPresenter implements Presenter {

    private String titleString = "WizardStimulusPresenter";

    public WizardStimulusPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, submissionService, userResults, localStorage, timerService);
    }

    @Override
    protected String getTitle() {
        return titleString;
    }

    @Override
    protected String getSelfTag() {
        return "WizardStimulusPresenter";
    }

    @Override
    protected void setContent(AppEventListener appEventListener) {
    }

    @Override
    protected String[] getStopwatchValues() {
        return new String[]{};
    }

    public void parseJsonData(final JSONObject jsonBlocksData, final String selectedBlockId) {
        // TODO: process the JSON data and focus on the highlighed block ID
    }
}
