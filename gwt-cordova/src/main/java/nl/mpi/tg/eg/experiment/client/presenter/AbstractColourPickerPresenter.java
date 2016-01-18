/*
 * Copyright (C) 2014 Language In Interaction
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
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.Date;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.StimulusProvider;
import nl.mpi.tg.eg.experiment.client.view.ColourPickerCanvasView;
import nl.ru.languageininteraction.synaesthesia.client.exception.CanvasError;
import nl.ru.languageininteraction.language.client.model.StimulusResponse;
import nl.ru.languageininteraction.language.client.model.StimulusResponseGroup;

/**
 * @since Oct 10, 2014 9:52:25 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AbstractColourPickerPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootLayoutPanel widgetTag;
//    private final ArrayList<Stimulus> stimuli;
//    private final int maxStimuli;
    private final UserResults userResults;
    private final ColourPickerCanvasView colourPickerCanvasView;
    private final StimulusProvider stimulusProvider = new StimulusProvider();
//    private Stimulus currentStimulus = null;
    final List<GeneratedStimulus.Tag> selectionTags;
    private long startMs;
    private final int repeatCount;
    private int shownSetCount;
    private int shownCount = 0;

    public AbstractColourPickerPresenter(RootLayoutPanel widgetTag, UserResults userResults, int repeatCount, final List<GeneratedStimulus.Tag> selectionTags) throws CanvasError {
        this.widgetTag = widgetTag;
//        this.stimuliGroup = userResults.getPendingStimuliGroup();
//        userResults.setPendingStimuliGroup(null);
//        this.stimuli = new ArrayList<>(stimuliGroup.getStimuli());

        this.userResults = userResults;
        this.repeatCount = repeatCount;
//        maxStimuli = this.stimuli.size();
        this.selectionTags = selectionTags;
        colourPickerCanvasView = new ColourPickerCanvasView();
    }

    private void triggerEvent(final AppEventListner appEventListner, final ColourPickerCanvasView colourPickerCanvasView, final ApplicationController.ApplicationState nextState) {
        if (!stimulusProvider.hasNextStimulus()) {
            shownSetCount++;
            if (repeatCount > shownSetCount) {
                stimulusProvider.getSubset(selectionTags, false, "");
            }
        }
        if (!stimulusProvider.hasNextStimulus()) {
            appEventListner.requestApplicationState(nextState);
        } else {
            colourPickerCanvasView.setRandomColour();
            stimulusProvider.getNextStimulus();
            startMs = System.currentTimeMillis();
            shownCount++;
            colourPickerCanvasView.setStimulus(stimulusProvider.getCurrentStimulus(), messages.stimulusscreenprogresstext(Integer.toString(shownCount), Integer.toString(stimulusProvider.getTotalStimuli() * repeatCount)));
        }
    }

    @Override
    public void setState(final AppEventListner appEventListner, final ApplicationController.ApplicationState prevState, final ApplicationController.ApplicationState nextState) {
        widgetTag.clear();
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
//        userResults.addStimulusResponseGroup(stimuliGroup, stimulusResponseGroup);
        colourPickerCanvasView.setAcceptButton(new PresenterEventListner() {

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                stimulusResponseGroup.addResponse(stimulusProvider.getCurrentStimulus(), new StimulusResponse(colourPickerCanvasView.getColour(), new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }

            @Override
            public String getLabel() {
                return messages.stimulusscreenselectbutton();
            }
        });
        colourPickerCanvasView.setRejectButton(new PresenterEventListner() {

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                stimulusResponseGroup.addResponse(stimulusProvider.getCurrentStimulus(), new StimulusResponse(null, new Date(), System.currentTimeMillis() - startMs));
                triggerEvent(appEventListner, colourPickerCanvasView, nextState);
            }

            @Override
            public String getLabel() {
                return messages.stimulusscreenrejectbutton();
            }
        });
//        colourPickerCanvasView.setInstructions(messages.instructionscreentext(), messages.helpButtonChar(),messages.instructionscreenbutton());
        colourPickerCanvasView.setQuitButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return messages.stimulusscreenQuitButton();
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner shotEventListner) {
                // delete the incomplete test results
//                userResults.deleteStimuliGroupResults(stimuliGroup);
                appEventListner.requestApplicationState(prevState);
            }
        });
        triggerEvent(appEventListner, colourPickerCanvasView, nextState);
        colourPickerCanvasView.resizeView();
        widgetTag.add(colourPickerCanvasView);
    }

    @Override
    public void fireBackEvent() {
    }

    @Override
    public void fireResizeEvent() {
    }

    @Override
    public void fireWindowClosing() {
    }

    @Override
    public void savePresenterState() {
    }
}
