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
package nl.ru.languageininteraction.language.client.presenter;

import nl.mpi.tg.eg.experiment.client.presenter.Presenter;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
//import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.ru.languageininteraction.language.client.view.MapView;

/**
 * @since Nov 26, 2014 4:12:27 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MapPresenter extends AbstractPresenter implements Presenter {

    public MapPresenter(RootLayoutPanel widgetTag, UserResults userResults, final LocalStorage localStorage, final TimerService timerService) {
        super(widgetTag, new MapView(),userResults, localStorage, timerService);
    }

    @Override
    protected String getTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getSelfTag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void setTitle(PresenterEventListener titleBarListener) {
//        throw new UnsupportedOperationException();
////        simpleView.addTitle(messages.mapScreenTitle(), titleBarListener);
//    }
    @Override
    public void setContent(AppEventListener appEventListener) {
        ((MapView) simpleView).addMap();
        ((MapView) simpleView).addZoom();
    }

    @Override
    public void savePresenterState() {
        super.cleanUpPresenterState();
    }
}
