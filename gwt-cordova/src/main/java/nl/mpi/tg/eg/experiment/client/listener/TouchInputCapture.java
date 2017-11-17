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

/**
 * @since Oct 23, 2017 2:10:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class TouchInputCapture extends HandlesAllTouchEvents implements MouseMoveHandler {

    private final StringBuilder recordedTouches = new StringBuilder();

    public abstract void setDebugLabel(String debugLabel);

    private void appendTouch(int xPos, int yPos) {
        recordedTouches.append(xPos);
        recordedTouches.append(",");
        recordedTouches.append(yPos);
        recordedTouches.append(" ");
    }

    private void recordTouches(TouchEvent event) {
        final JsArray<Touch> targetTouches = event.getTouches();
        for (int index = 0; index < targetTouches.length(); index++) {
            appendTouch(targetTouches.get(index).getScreenX(), targetTouches.get(index).getScreenY());
        }
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
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchMove(TouchMoveEvent event) {
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onTouchCancel(TouchCancelEvent event) {
        touchesToDebugLabel(event);
        recordTouches(event);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event) {
        appendTouch(event.getClientX(), event.getClientY());
        setDebugLabel(event.toDebugString() + " " + event.getClientX() + "," + event.getClientY());
    }

    public String getTouchReport() {
        return recordedTouches.toString();
    }
}
