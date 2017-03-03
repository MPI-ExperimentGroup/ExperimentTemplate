/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;

/**
 * @since Oct 6, 2015 1:30:16 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StimulusGrid extends FlexTable {

    public StimulusGrid() {
    }

    private ButtonBase addButton(final PresenterEventListner menuItemListerner, final ButtonBase pushButton, final int rowIndex, final int columnIndex, final String widthString) {
        this.setStylePrimaryName("gridTable");
        pushButton.addStyleName("stimulusButton");
        pushButton.setEnabled(true);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (pushButton.isEnabled()) {
                    menuItemListerner.eventFired(pushButton, this);
                }
            }
        };
        pushButton.addClickHandler(singleShotEventListner);
        pushButton.addTouchStartHandler(singleShotEventListner);
        pushButton.addTouchMoveHandler(singleShotEventListner);
        pushButton.addTouchEndHandler(singleShotEventListner);
        this.setWidget(rowIndex, columnIndex, pushButton);
        this.getCellFormatter().setHorizontalAlignment(rowIndex, columnIndex, HasHorizontalAlignment.ALIGN_CENTER);
        this.getCellFormatter().setStylePrimaryName(rowIndex, columnIndex, "gridCell");
        return pushButton;
    }

    public ButtonBase addStringItem(final PresenterEventListner menuItemListerner, final String labelString, final int rowIndex, final int columnIndex, final String widthString) {
        final Button pushButton = new Button(labelString);
        return addButton(menuItemListerner, pushButton, rowIndex, columnIndex, widthString);
    }

    public ButtonBase addImageItem(final PresenterEventListner menuItemListerner, final SafeUri imagePath, final int rowIndex, final int columnIndex, final String widthString, final String styleName) {
        final Image image = new Image(imagePath);
        image.setWidth(widthString);
        if (styleName != null) {
            image.addStyleName(styleName);
        }
        final Button imageButton = new Button();
        imageButton.getElement().appendChild(image.getElement());
        imageButton.addStyleName("stimulusImageButton");
        return addButton(menuItemListerner, imageButton, rowIndex, columnIndex, widthString);
    }
}
