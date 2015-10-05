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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @since Sep 21, 2015 11:56:46 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AnnotationTimelinePanel extends VerticalPanel {

    public AnnotationTimelinePanel() { //String width, String poster, String mp4, String ogg, String webm
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        this.setStylePrimaryName("annotationTimelinePanel");
//        horizontalPanel.add(new VideoPanel(width, poster, mp4, ogg, webm));
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new Button("one", new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        verticalPanel.add(new Button("two", new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        horizontalPanel.add(verticalPanel);
        this.add(horizontalPanel);
        configureTimeline();
    }

    private void configureTimeline() {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setStylePrimaryName("annotationTimelineTier");
        final Label label = new Label("one");
        label.setWidth("20%");
        horizontalPanel.add(label);
        final Label label1 = new Label("two");
        label1.setWidth("10%");
        horizontalPanel.add(label1);
        final Label label2 = new Label("three");
        label2.setWidth("70%");
        horizontalPanel.add(label2);
        this.add(horizontalPanel);
    }
}
