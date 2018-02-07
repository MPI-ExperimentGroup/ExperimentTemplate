/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.listener;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.HandlesAllTouchEvents;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since Oct 23, 2017 2:10:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class TouchInputCapture extends HandlesAllTouchEvents implements MouseMoveHandler, Event.NativePreviewHandler {

    private final StringBuilder recordedTouches = new StringBuilder();
    private final List<TouchInputZone> touchZones = new ArrayList<>();
    private final TimedStimulusListener endOfTouchEventListner;
    private final int msAfterEndOfTouchToNext;
    private final Timer endOfTouchTimer;
    private final Duration duration = new Duration();
    boolean disableMouseEvents = false;

    public TouchInputCapture(final TimedStimulusListener endOfTouchEventListner, final int msAfterEndOfTouchToNext) {
        this.endOfTouchEventListner = endOfTouchEventListner;
        this.msAfterEndOfTouchToNext = msAfterEndOfTouchToNext;
        endOfTouchTimer = new Timer() {
            public void run() {
                setDebugLabel("Triggered:msAfterLastTouchEvent");
                triggerZones(new ArrayList<TouchInputZone>());
            }
        };
    }

    public abstract void setDebugLabel(String debugLabel);

    private void appendTouch(int xPos, int yPos, final List<TouchInputZone> triggeredZones) {
        recordedTouches.append(duration.elapsedMillis());
        recordedTouches.append(",");
        recordedTouches.append(xPos);
        recordedTouches.append(",");
        recordedTouches.append(yPos);
        recordedTouches.append(",");
        for (TouchInputZone zone : touchZones) {
            if (zone.intersects(xPos, yPos)) {
                recordedTouches.append(zone.getEventTag());
                triggeredZones.add(zone);
            }
            recordedTouches.append(",");
        }
        recordedTouches.append("\n");
    }

    public void addTouchZone(TouchInputZone touchZone) {
        touchZones.add(touchZone);
    }

    private void triggerZones(final List<TouchInputZone> triggeredZones) {
        if (triggeredZones.isEmpty()) {
            for (TouchInputZone zone : touchZones) {
                zone.clearEvent();
            }
            endOfTouchTimer.cancel();
            endOfTouchEventListner.postLoadTimerFired();
        } else {
            for (TouchInputZone zone : touchZones) {
//            if (triggeredZones.contains(zone)) {
                zone.triggerEvent();
//                break;
//            } else {
//                zone.clearEvent();
//            }
            }
            if (msAfterEndOfTouchToNext > 0) {
                endOfTouchTimer.schedule(msAfterEndOfTouchToNext);
            }
        }
    }

    private void recordTouches(TouchEvent event) {
        final JsArray<Touch> targetTouches = event.getTouches();
        final List<TouchInputZone> triggeredZones = new ArrayList<>();
        for (int index = 0; index < targetTouches.length(); index++) {
            appendTouch(targetTouches.get(index).getScreenX(), targetTouches.get(index).getScreenY(), triggeredZones);
        }
        triggerZones(triggeredZones);
    }

    private void touchesToDebugLabel(TouchEvent event) {
        StringBuilder builder = new StringBuilder();
        final JsArray<Touch> targetTouches = event.getTouches();
        for (int index = 0; index < targetTouches.length(); index++) {
            builder.append(targetTouches.get(index).getScreenX());
            builder.append(", ");
            builder.append(targetTouches.get(index).getScreenY());
            builder.append(" ");
        }
        setDebugLabel(builder.toString());
    }

    @Override
    public void onTouchStart(TouchStartEvent event) {
        disableMouseEvents = true;
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchMove(TouchMoveEvent event) {
        disableMouseEvents = true;
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        disableMouseEvents = true;
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchCancel(TouchCancelEvent event) {
        disableMouseEvents = true;
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event) {
//        if (disableMouseEvents) {
//            return;
//        }
        final List<TouchInputZone> triggeredZones = new ArrayList<>();
        appendTouch(event.getClientX(), event.getClientY(), triggeredZones);
        setDebugLabel(event.toDebugString() + " " + event.getClientX() + "," + event.getClientY());
        triggerZones(triggeredZones);
    }

    @Override
    public void onPreviewNativeEvent(Event.NativePreviewEvent event) {
//        if (disableMouseEvents) {
//            return;
//        }
        final int eventType = event.getTypeInt();
        final List<TouchInputZone> triggeredZones = new ArrayList<>();
        switch (eventType) {
            case Event.ONMOUSEOUT:
            case Event.ONMOUSEMOVE:
                appendTouch(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY(), triggeredZones);
                setDebugLabel(event.toDebugString() + " " + event.getNativeEvent().getClientX() + "," + event.getNativeEvent().getClientY());
                triggerZones(triggeredZones);
                break;
            default:
        }
    }

    public String getTouchReport(int screenWidth, int screenHeight) {
        recordedTouches.insert(0, "\n");
        recordedTouches.insert(0, screenHeight);
        recordedTouches.insert(0, ",");
        recordedTouches.insert(0, screenWidth);
        recordedTouches.insert(0, ",");
        recordedTouches.insert(0, duration.elapsedMillis());
        return recordedTouches.toString();
    }
}
