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

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @since Oct 23, 2017 2:10:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class TouchInputCapture implements EventListener {

    private Set<String> releventStyleNames = new HashSet<>();

    public void addReleventStyleName(String releventStyleNames) {
        this.releventStyleNames.add(releventStyleNames);
    }

    public abstract void setDebugLabel(String debugLabel);

    @Override
    public void onBrowserEvent(Event event) {
        setDebugLabel(event.toString());
        if (event.equals(Event.ONTOUCHSTART)) {
            setDebugLabel("ONTOUCHSTART");
        }
        if (event.equals(Event.ONTOUCHMOVE)) {
            setDebugLabel("ONTOUCHMOVE");
        }
        if (event.equals(Event.ONTOUCHCANCEL)) {
            setDebugLabel("ONTOUCHCANCEL");
        }
        if (event.equals(Event.ONTOUCHEND)) {
            setDebugLabel("ONTOUCHEND");
        }
    }
}
