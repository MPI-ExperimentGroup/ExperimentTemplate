/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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

import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.SdCardStimulus;
import nl.mpi.tg.eg.experiment.client.model.UserId;

/**
 * @since Jun 29, 2016 2:49:37 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SdCardImageCapture {

    final private TimedStimulusListener timedStimulusListener;
    final private SdCardStimulus sdCardStimulus;
    final private UserId userId;

    public SdCardImageCapture(final TimedStimulusListener timedStimulusListener, SdCardStimulus sdCardStimulus, final UserId userId) {
        this.timedStimulusListener = timedStimulusListener;
        this.sdCardStimulus = sdCardStimulus;
        this.userId = userId;
    }

    public boolean hasBeenCaptured() {
        return false;
    }

    public void captureImage() {

        captureImageUI(userId.toString(), sdCardStimulus.getUniqueId());
    }

    public String getCapturedPath() {
        return sdCardStimulus.getImage(); // todo change this
    }

//    protected void captureStimulusImage() {
//        // todo: add capture / replace button
//        captureImageUI(userId.toString(), sdCardStimulus.getUniqueId());
////        throw new UnsupportedOperationException();
//    }
    protected void imageCaptured(String stimulusIdString, String fullPath) {
        timedStimulusListener.postLoadTimerFired();
    }

    protected void imageCapturedFailed(String stimulusIdString, String message) {
        throw new UnsupportedOperationException(); // todo: add error display
    }

    private native void captureImageUI(String userIdString, String stimulusIdString) /*-{
        var sdCardImageCapture = this;
        console.log("captureImageUI: " + userIdString + " : " + stimulusIdString);
        if($wnd.plugins){
            $wnd.navigator.device.capture.captureImage(function (mediaFiles) {
                console.log("captureImageOk: " + mediaFiles[0].fullPath);
                sdCardImageCapture.@nl.mpi.tg.eg.experiment.client.service.SdCardImageCapture::imageCaptured(Ljava/lang/String;Ljava/lang/String;)(stimulusIdString, mediaFiles[0].fullPath);
            }, function (error) {
                console.log("captureImageError: " + error.code);
                abstractPresenter.@nl.mpi.tg.eg.experiment.client.service.SdCardImageCapture::imageCapturedFailed(Ljava/lang/String;Ljava/lang/String;)(stimulusIdString, "Error code:" + error.code);
            }, {limit:1});
        } else {
            sdCardImageCapture.@nl.mpi.tg.eg.experiment.client.service.SdCardImageCapture::imageCapturedFailed(Ljava/lang/String;Ljava/lang/String;)(stimulusIdString, null);
        }
     }-*/;
}
