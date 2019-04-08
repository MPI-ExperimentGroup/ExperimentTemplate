/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.core.client.Duration;
import com.google.gwt.dom.client.Element;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.model.TimedEvent;

/**
 * @since Feb 4, 2019 11:45:37 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TimedEventMonitor {

    private final Duration duration;
    final List<TimedEvent> eventList = new ArrayList<>();

    public TimedEventMonitor(Duration duration) {
        this.duration = duration;
    }

    public void registerEvent(String eventName) {
        eventList.add(new TimedEvent(eventName, duration.elapsedMillis()));
    }

    public void registerMediaLength(String mediaName, Long mediaLength) {
        eventList.add(new TimedEvent(mediaName, mediaLength));
    }

    public void clearEvents(final List<TimedEvent> completedList) {
        eventList.removeAll(completedList);
    }

    public List<TimedEvent> getEventList() {
        return Collections.unmodifiableList(eventList);
    }

    public native void addVisibilityListener(final Element widgetTag, final Element element, final String regionId) /*-{
        var timedEventMonitor = this;
        var regionIn = regionId + "_in";
        var regionOut = regionId + "_out";
        var regionDisplay = regionId + "_display";
        var regionNone = regionId + "_none";
        new IntersectionObserver(function(entries, observer) {
            entries.forEach(function(entry) {
                if(entry.intersectionRatio > 0){
                    timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionIn);
                } else {
                    timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionOut);
                }
            });
//            if(element.style.display === "none" ) {
//                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionNone);
//            } else {
//                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionDisplay);
//            }
        }, {
            root: widgetTag
        }).observe(element);
//        new MutationObserver(function(mutationsList, observer){
//            mutationsList.forEach(function(mutation) {
//                console.log(mutation.type);
//                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionId + "_" + mutation.type);
//            });
//            if(element.style.display === "none" ) {
//                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionNone);
//            } else {
//                timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)(regionDisplay);
//            }
//        }).observe(element, {attributes: true, childList: true, subtree: true});
    }-*/;
}
