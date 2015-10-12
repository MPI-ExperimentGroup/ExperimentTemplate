/*
 * Copyright (C) 2015 Language In Interaction
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
package nl.ru.languageininteraction.language.client.presenter;

import nl.mpi.tg.eg.experiment.client.presenter.Presenter;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.ru.languageininteraction.language.client.StartScreenBuilder;
import nl.mpi.tg.eg.experiment.client.exception.AudioException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.view.StartScreenView;

/**
 * @since Feb 4, 2015 11:26:10 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class StartScreenPresenter extends AbstractSvgPresenter implements Presenter {

    protected final StartScreenBuilder svgBuilder = new StartScreenBuilder();

    public StartScreenPresenter(RootLayoutPanel widgetTag, UserResults userResults, AudioPlayer audioPlayer, final AppEventListner appEventListner) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new StartScreenView(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                appEventListner.requestApplicationState(ApplicationState.version);
            }
        }, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                appEventListner.requestApplicationState(ApplicationState.chooseplayer);
            }
        }, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                appEventListner.requestApplicationState(ApplicationState.locale);
            }
        }, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
                appEventListner.requestApplicationState(ApplicationState.tutorial);
            }
        },
                audioPlayer));
    }

    @Override
    void configureSvg() {
    }

    @Override
    boolean nextEventFired() {
        return true;
    }
}
