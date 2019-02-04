/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experiment.client.util.HtmlTokenFormatter;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jan 31, 2019 5:07:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractTimedPresenter extends AbstractPresenter implements Presenter {

    final protected TimedStimulusView timedStimulusView;

    public AbstractTimedPresenter(RootLayoutPanel widgetTag, final TimedStimulusView timedStimulusView, UserResults userResults, LocalStorage localStorage, TimerService timerService) {
        super(widgetTag, timedStimulusView, userResults, localStorage, timerService);
        this.timedStimulusView = timedStimulusView;
    }

    public void htmlTokenText(final Stimulus currentStimulus, final String textString, final String styleName) {
        timedStimulusView.addHtmlText(new HtmlTokenFormatter(currentStimulus, localStorage, groupParticipantService, userResults.getUserData(), timerService, metadataFieldProvider.metadataFieldArray).formatString(textString), styleName);
        // the submitTagValue previously used here by the multiparticipant configuration has been migrated to logTokenText which should function the sames for the multiparticipant experiment except that it now uses submitTagPairValue
    }

    public void htmlTokenText(final Stimulus currentStimulus, String textString) {
        htmlTokenText(currentStimulus, textString, null);
    }

    public void htmlText(String textString, final String styleName) {
        timedStimulusView.addHtmlText(textString, styleName);
    }

    protected void image(final String imageString, int postLoadMs, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        image(imageString, null, postLoadMs, loadedStimulusListener, failedStimulusListener);
    }

    protected void image(final String imageString, final String styleName, int postLoadMs, final CancelableStimulusListener loadedStimulusListener, final CancelableStimulusListener failedStimulusListener) {
        // consider fromTrustedString if there are issues with fromString when sdcard stimuli are used
        timedStimulusView.addTimedImage(null, UriUtils.fromString((imageString.startsWith("file")) ? imageString : serviceLocations.staticFilesUrl() + imageString), styleName, postLoadMs, null, loadedStimulusListener, failedStimulusListener, null);
    }

    protected void addPadding() {
        timedStimulusView.addPadding();
    }

    protected void centrePage() {
        timedStimulusView.centrePage();
    }

    public void ratingButtons(final List<PresenterEventListner> presenterListeners, final String ratingLabelLeft, final String ratingLabelRight, boolean footerButtons, String styleName, final String buttonGroupName, final String savedValue, final String buttonGroup, final HorizontalPanel buttonsPanel) {
        final List<StimulusButton> ratingButtons = timedStimulusView.addRatingButtons(presenterListeners, ratingLabelLeft, ratingLabelRight, footerButtons, styleName, buttonGroupName, savedValue, buttonsPanel);
        addButtonToGroup(buttonGroup, ratingButtons);
//        addButtonToGroup(buttonGroupName, ratingButtons);
    }

    public StimulusButton imageButton(final PresenterEventListner presenterListerner, final SafeUri imagePath, final String styleName, final boolean isTouchZone, final String buttonGroup) {
        return addButtonToGroup(buttonGroup, timedStimulusView.addImageButton(presenterListerner, imagePath, styleName, isTouchZone));
    }

    public void actionFooterButton(final PresenterEventListner presenterListerner, final String styleName, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addFooterButton(presenterListerner, styleName));
    }

    public void targetFooterButton(final PresenterEventListner presenterListerner, final String styleName, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addFooterButton(presenterListerner, styleName));
    }

    public void actionButton(final PresenterEventListner presenterListerner, final String styleName, final String buttonGroup) {
        addButtonToGroup(buttonGroup, timedStimulusView.addOptionButton(presenterListerner, styleName));
    }

    public StimulusButton optionButton(final PresenterEventListner presenterListerner, final String styleName, final String buttonGroup) {
        final StimulusButton optionButton = timedStimulusView.addOptionButton(presenterListerner, styleName);
        addButtonToGroup(buttonGroup, optionButton);
        return optionButton;
    }

    protected void table(final TimedStimulusListener timedStimulusListener) {
        table(null, timedStimulusListener);
    }

    protected void table(final String styleName, final TimedStimulusListener timedStimulusListener) {
        table(styleName, false, timedStimulusListener);
    }

    protected void table(final String styleName, boolean showOnBackButton, final TimedStimulusListener timedStimulusListener) {
        final Widget tableWidget = timedStimulusView.startTable(styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endTable();
        if (showOnBackButton) {
            tableWidget.setVisible(false);
            // todo: backEventListners list should be emptied on screen clear etc
            backEventListners.add(new TimedStimulusListener() {
                @Override
                public void postLoadTimerFired() {
                    tableWidget.setVisible(!tableWidget.isVisible());
                }
            });
        }
    }

    protected void row(final TimedStimulusListener timedStimulusListener) {
        timedStimulusView.startRow();
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRow();
    }

    protected void column(final TimedStimulusListener timedStimulusListener) {
        column(null, timedStimulusListener);
    }

    protected void column(final String styleName, final TimedStimulusListener timedStimulusListener) {
        timedStimulusView.startCell(styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endCell();
    }

    protected void region(final String regionId, final String styleName, final TimedStimulusListener timedStimulusListener) {
        timedStimulusView.startRegion(regionId, styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRegion();
    }

    protected void regionStyle(final String regionId, final String styleName) {
        timedStimulusView.setRegionStyle(regionId, styleName);
    }

    protected void regionReplace(final String regionId, final String styleName, final TimedStimulusListener timedStimulusListener) {
        timedStimulusView.clearRegion(regionId);
        timedStimulusView.startRegion(regionId, styleName);
        timedStimulusListener.postLoadTimerFired();
        timedStimulusView.endRegion();
    }

    protected void regionClear(final String regionId) {
        timedStimulusView.clearRegion(regionId);
    }
}
