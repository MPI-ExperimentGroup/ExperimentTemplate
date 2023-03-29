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

import com.google.gwt.user.client.ui.Panel;
import java.util.HashMap;
import java.util.Map;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since 29 March 2023 10:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class DragDropHandler {

    boolean initComplete = false;
    private Map<String, Panel> regionPanels = new HashMap<>();
    private Map<String, String> regionCodeResponses = new HashMap<>();
    private String currentDraggedRegion = null;

    public void addDragDrop(final Panel dragDropRegion, final Stimulus currentStimulus, final boolean draggable, final boolean droptarget, final String regionId, final String formattedCode, final TimedStimulusListener ondragstart, final TimedStimulusListener ondragover, final TimedStimulusListener ondrop) {
        if (!initComplete) {
            initDropMethods();
            initComplete = true;
        }
        regionPanels.put(regionId, dragDropRegion);
        regionCodeResponses.put(regionId, formattedCode);
        if (dragDropRegion != null) {
            if (draggable) {
                dragDropRegion.getElement().setAttribute("draggable", Boolean.toString(draggable));
                dragDropRegion.getElement().setAttribute("ondragstart", "frinexDragStart(event.target.id)");
            }
            if (droptarget) {
                dragDropRegion.getElement().setAttribute("ondragover", "event.preventDefault();frinexDragOver(event.target.id)");
                dragDropRegion.getElement().setAttribute("ondrop", "event.preventDefault();frinexDrop(event.target.id)");
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
        currentDraggedRegion = regionId;
        setResponse("onDragStart", regionCodeResponses.get(currentDraggedRegion), null);
    }

    public void onDragOver(final String regionId) {
        setResponse("onDragOver", regionCodeResponses.get(currentDraggedRegion), regionCodeResponses.get(regionId));
    }

    public void onDrop(final String regionId) {
        setResponse("onDrop", regionCodeResponses.get(currentDraggedRegion), regionCodeResponses.get(regionId));
        final Panel draggedPanel = regionPanels.get(currentDraggedRegion);
        final Panel droppedPanel = regionPanels.get(regionId);
        if (draggedPanel != null && droppedPanel != null) {
            draggedPanel.add(droppedPanel);
        }
    }

    public void clearAll() {
        regionPanels.clear();
        currentDraggedRegion = null;
    }

    public abstract void setResponse(final String dragDropStatus, final String draggedCodeResponse, final String targetCodeResponse);
}
