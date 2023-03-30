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
    private final Map<String, Panel> regionPanels = new HashMap<>();
    private final Map<String, String> regionCodeResponses = new HashMap<>();
    private final Map<String, TimedStimulusListener> regionOnSragStart = new HashMap<>();
    private final Map<String, TimedStimulusListener> regionOnDragOver = new HashMap<>();
    private final Map<String, TimedStimulusListener> regionOnDrop = new HashMap<>();
    private final Map<String, Stimulus> regionStimulus = new HashMap<>();
    private String currentDraggedRegion = null;

    public void addDragDrop(final Panel dragDropRegion, final Stimulus currentStimulus, final boolean draggable, final boolean droptarget, final String regionId, final String formattedCode, final TimedStimulusListener ondragstart, final TimedStimulusListener ondragover, final TimedStimulusListener ondrop) {
        if (!initComplete) {
            initDropMethods();
            initComplete = true;
        }
        regionPanels.put(regionId, dragDropRegion);
        regionCodeResponses.put(regionId, formattedCode);
        regionOnSragStart.put(regionId, ondragstart);
        regionOnDragOver.put(regionId, ondragover);
        regionOnDrop.put(regionId, ondrop);
        regionStimulus.put(regionId, currentStimulus);
        if (dragDropRegion != null) {
            if (draggable) {
                dragDropRegion.getElement().setAttribute("draggable", Boolean.toString(draggable));
                dragDropRegion.getElement().setAttribute("ondragstart", "frinexDragStart(event.target.id);");
            }
            if (droptarget) {
                dragDropRegion.getElement().setAttribute("ondragover", "event.preventDefault();frinexDragOver($(event.target).closest('[id]').attr('id'));");
                dragDropRegion.getElement().setAttribute("ondrop", "event.preventDefault();frinexDrop($(event.target).closest('[id]').attr('id'));");
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
        final TimedStimulusListener listener = regionOnSragStart.get(regionId);
        if (listener != null) {
            listener.postLoadTimerFired();
        }
    }

    public void onDragOver(final String regionId) {
        setResponse("onDragOver", regionCodeResponses.get(currentDraggedRegion), regionCodeResponses.get(regionId));
        final TimedStimulusListener listener = regionOnDragOver.get(regionId);
        if (listener != null) {
            listener.postLoadTimerFired();
        }
        final TimedStimulusListener draggedListener = regionOnDragOver.get(currentDraggedRegion);
        if (draggedListener != null) {
            draggedListener.postLoadTimerFired();
        }
    }

    public void onDrop(final String regionId) {
        setResponse("onDrop", regionCodeResponses.get(currentDraggedRegion), regionCodeResponses.get(regionId));
        final Panel draggedPanel = regionPanels.get(currentDraggedRegion);
        final Panel targetPanel = regionPanels.get(regionId);
        if (draggedPanel != null && targetPanel != null) {
            targetPanel.add(draggedPanel);
        }
        final TimedStimulusListener listener = regionOnDrop.get(regionId);
        if (listener != null) {
            listener.postLoadTimerFired();
        }
        final TimedStimulusListener draggedListener = regionOnDrop.get(currentDraggedRegion);
        if (draggedListener != null) {
            draggedListener.postLoadTimerFired();
        }
        final Stimulus stimulus = regionStimulus.get(regionId);
        if (stimulus != null) {
            setResponse(stimulus, regionCodeResponses.get(currentDraggedRegion));
        }
        final Stimulus draggedStimulus = regionStimulus.get(currentDraggedRegion);
        if (draggedStimulus != null) {
            setResponse(draggedStimulus, regionCodeResponses.get(regionId));
        }
    }

    public void clearAll() {
        regionPanels.clear();
        regionCodeResponses.clear();
        regionOnSragStart.clear();
        regionOnDragOver.clear();
        regionOnDrop.clear();
        currentDraggedRegion = null;
    }

    public abstract void setResponse(final String dragDropStatus, final String draggedCodeResponse, final String targetCodeResponse);

    public abstract void setResponse(final Stimulus stimulus, final String codeResponse);
}
