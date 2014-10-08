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
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AppController implements AppEventListner {

    protected enum ApplicationState {

        intro, stimulus, feedback, end
    }
    private final ScreenPresenter presenter;

    public AppController(RootPanel widgetTag) {
        this.presenter = new ScreenPresenter(widgetTag, this);
        presenter.setState(ApplicationState.intro);
    }

    public void eventFired(ApplicationState state) {
        switch (state) {
            case intro:
                presenter.setState(ApplicationState.stimulus);
                break;
            case stimulus:
                presenter.setState(ApplicationState.feedback);
                break;
            case feedback:
                presenter.setState(ApplicationState.end);
                break;
        }
    }
}
