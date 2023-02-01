/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.listener.TimerListener;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since Jul 10, 2018 4:21:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimerService {

    final HashMap<String, List<TimerListener>> timerListeners = new HashMap<>();

    public void clearAllTimers() {
        for (List<TimerListener> listenerList : timerListeners.values()) {
            while (!listenerList.isEmpty()) {
                final TimerListener timerEntry = listenerList.remove(0);
                if (timerEntry != null) {
                    timerEntry.clearTimer();
                }
            }
        }
    }

    public void startTimer(final long initialTimerStartMs, final int msToNext, final String listenerId, final TimedStimulusListener timeoutListener) {
        // this must first check for a saved timer start value and reload it, for cases where a user has refreshed the browser
        final TimerListener timerListener = new TimerListener() {
            @Override
            public void timerTriggered() {
                timeoutListener.postLoadTimerFired();
            }

            @Override
            public long getInitialTimerStartMs() {
                return initialTimerStartMs;
            }

        };
        final List<TimerListener> listenerList;
        if (timerListeners.containsKey(listenerId)) {
            listenerList = timerListeners.get(listenerId);
        } else {
            listenerList = new ArrayList<>();
            timerListeners.put(listenerId, listenerList);
        }
        listenerList.add(timerListener);
        timerListener.startTimer(msToNext);
    }

    public int getTimerValue(final String listenerId) {
        final List<TimerListener> listenerList = timerListeners.get(listenerId);
        final TimerListener timerEntry = (listenerList == null || listenerList.isEmpty()) ? null : listenerList.get(0);
        if (timerEntry == null) {
            return -1;
        } else {
            return (int) timerEntry.getTimerValue();
        }
    }

    public void clearTimer(final String listenerId) {
        final List<TimerListener> listenerList = timerListeners.get(listenerId);
        if (listenerList != null) {
            while (!listenerList.isEmpty()) {
                final TimerListener timerEntry = listenerList.remove(0);
                if (timerEntry != null) {
                    timerEntry.clearTimer();
                }
            }
        }
    }

    public Set<String> getTimerIds() {
        return timerListeners.keySet();
    }
}
