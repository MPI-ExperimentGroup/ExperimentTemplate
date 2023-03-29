/*
 * Copyright (C) 2023 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since 29 March 2023 10:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DragDropHandler {

    boolean initComplete = false;

    public void addDragDrop(final Panel dragDropRegion, final Stimulus currentStimulus, final boolean draggable, final boolean droptarget, final String regionId, final String codeFormat, final TimedStimulusListener ondragstart, final TimedStimulusListener ondragover, final TimedStimulusListener ondrop) {
        if (!initComplete) {
            initDropMethods();
            initComplete = true;
            GWT.log("DragDropHandler.initComplete");
        }
        if (dragDropRegion != null) {
            if (draggable) {
                dragDropRegion.getElement().setAttribute("draggable", Boolean.toString(draggable));
                dragDropRegion.getElement().setAttribute("ondragstart", "frinexDragStart(event.target.id)");
            }
            if (droptarget) {
                dragDropRegion.getElement().setAttribute("ondragover", "frinexDragOver(event.target.id)");
                dragDropRegion.getElement().setAttribute("ondrop", "frinexDrop(event.target.id)");
            }
        }
    }

    public native void initDropMethods() /*-{
        var dragDropHandler = this;
        $wnd.frinexDragStart = $entry(function(regionId) {
          dragDropHandler.@nl.mpi.tg.eg.experiment.client.util.DragDropHandler::onDragStart(Ljava/lang/String;)(regionId);
        });
        $wnd.frinexDragOver = $entry(function(regionId) {
          dragDropHandler.@nl.mpi.tg.eg.experiment.client.util.DragDropHandler::onDragOver(Ljava/lang/String;)(regionId);
        });
        $wnd.frinexDrop = $entry(function(regionId) {
          dragDropHandler.@nl.mpi.tg.eg.experiment.client.util.DragDropHandler::onDrop(Ljava/lang/String;)(regionId);
        });
    }-*/;

    public void onDragStart(final String regionId) {
        GWT.log("onDragStart: " + regionId);
    }

    public void onDragOver(final String regionId) {
        GWT.log("onDragStart: " + regionId);
    }

    public void onDrop(final String regionId) {
        GWT.log("onDragStart: " + regionId);
    }

    public void clearAll() {
        GWT.log("DragDropHandler.clearAll");
    }
}
