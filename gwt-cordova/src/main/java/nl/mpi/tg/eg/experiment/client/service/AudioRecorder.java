/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
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

/**
 * @since Aug 31, 2015 2:56:12 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AudioRecorder {

    public AudioRecorder() {
    }

    public native void playMedia(String fileName) /*-{
     var mediaObject = new Media(fileName, 
     function () {
     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onPlay()();
     }, function onError(error) {
     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onError(Ljava/lang/String;)('code: ' + error.code + '\n' + 'message: ' + error.message + '\n');
     });

     // start a callback to show the media position
     var mediaTimer = setInterval(function() {
     mediaObject.getCurrentPosition(
     function(position) {
     if (position > -1) {
     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::updateCurrentPosition(Ljava/lang/String;)((position) + " seconds");
     }
     },
     function(e) {
     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onError(Ljava/lang/String;)("Error getting pos=" + e);
     }
     );
     }, 1000);
     }-*/;

    protected native String getDuration() /*-{
     return media.getDuration();
     //     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onRecord(Ljava/lang/String;)(s);
     //     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onStop(Ljava/lang/String;)(s);
     //     this.@nl.mpi.tg.eg.experiment.client.service.AudioRecorder::onError(Ljava/lang/String;)(s);
     }-*/;

    protected abstract void onPlay();

    protected abstract void onRecord();

    protected abstract void onStop();

    protected abstract void onError(String s);

    protected abstract void updateCurrentPosition(String s);
}
