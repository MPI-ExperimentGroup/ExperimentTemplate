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
package nl.mpi.tg.eg.experiment.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;

/**
 * @since Feb 24, 2015 2:30:02 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuButton extends HorizontalPanel {

    public MenuButton(final PresenterEventListener presenterListerner) {
        final Label headerIcon = new Label();
        headerIcon.addStyleName("headerIcon");
        if (presenterListerner != null) {
            final Label headerArrow = new Label();
            headerArrow.addStyleName("headerArrow");
            this.add(headerArrow);

            final Label headerButton = new Label(presenterListerner.getLabel());
            headerButton.addStyleName("headerButton");
            this.add(headerButton);
            SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

                @Override
                protected void singleShotFired() {
                    presenterListerner.eventFired(null, this);
                }
            };
            headerArrow.addClickHandler(singleShotEventListener);
            headerButton.addClickHandler(singleShotEventListener);
            headerIcon.addClickHandler(singleShotEventListener);

            headerArrow.addTouchStartHandler(singleShotEventListener);
            headerButton.addTouchStartHandler(singleShotEventListener);
            headerIcon.addTouchStartHandler(singleShotEventListener);
            headerArrow.addTouchMoveHandler(singleShotEventListener);
            headerButton.addTouchMoveHandler(singleShotEventListener);
            headerIcon.addTouchMoveHandler(singleShotEventListener);
            headerArrow.addTouchEndHandler(singleShotEventListener);
            headerButton.addTouchEndHandler(singleShotEventListener);
            headerIcon.addTouchEndHandler(singleShotEventListener);
        }
        this.add(headerIcon);
    }
}
