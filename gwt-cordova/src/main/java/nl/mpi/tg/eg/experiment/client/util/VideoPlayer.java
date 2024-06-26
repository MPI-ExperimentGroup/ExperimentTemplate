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
package nl.mpi.tg.eg.experiment.client.util;

import com.google.gwt.dom.client.VideoElement;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;

/**
 * @since Jan 22, 2019 11:47:23 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class VideoPlayer {

    public native void addNativeCallbacks(final TimedEventMonitor timedEventMonitor, final VideoElement videoElement, final CancelableStimulusListener playbackStartedStimulusListener) /*-{
     videoElement.addEventListener("play", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoPlay");
     playbackStartedStimulusListener.@nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener::postLoadTimerFired()();
     }, false);            
     videoElement.addEventListener("loadstart", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoLoadStart");
     }, false);
     videoElement.addEventListener("durationchange", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoDurationChange");
     }, false);
     videoElement.addEventListener("loadedmetadata", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoLoadedMetadata");
     }, false);
     videoElement.addEventListener("loadeddata", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoLoadedData");
     }, false);
     videoElement.addEventListener("progress", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("VideoProgress");
     }, false);
     videoElement.addEventListener("canplay", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoCanPlay");
     }, false);
     videoElement.addEventListener("canplaythrough", function(){
     timedEventMonitor.@nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor::registerEvent(Ljava/lang/String;)("videoCanPlayThrough");
     }, false);
     }-*/;
}
