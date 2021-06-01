/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.TreeMap;

/**
 * @since 1 Jun 2021 11:26:02 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class MediaTriggerListener {

    private long currentKey = -1;
    // keep all listeners in a sorted map so that only tha smallest msToNext need be compared. When triggered the the listeners are removed from the map.
    private final TreeMap<Long, SingleStimulusListener> listenerMap = new TreeMap<>();

    public boolean addMediaTriggerListener(Long triggerMs, SingleStimulusListener singleStimulusListener) {
        boolean isFirst = listenerMap.isEmpty();
        listenerMap.put(triggerMs, singleStimulusListener);
        currentKey = listenerMap.firstKey();
        return isFirst;
    }

    public boolean triggerWhenReady(Long currentTime) {
        while (currentTime >= currentKey) {
            listenerMap.remove(currentKey).postLoadTimerFired(null);
            if (listenerMap.isEmpty()) {
                return false;
            } else {
                currentKey = listenerMap.firstKey();
            }
        }
        return true;
    }

    public void clearTriggers() {
        currentKey = -1;
        listenerMap.clear();
    }
}
