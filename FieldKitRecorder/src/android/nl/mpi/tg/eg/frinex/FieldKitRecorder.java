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
package nl.mpi.tg.eg.frinex;

import java.io.IOException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @since Dec 9, 2015 4:41:18 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FieldKitRecorder extends CordovaPlugin {

    AudioRecorder audioRecorder = new WavRecorder();

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("record")) {
            System.out.println("action: record");
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        audioRecorder.startRecording(cordova, callbackContext);
                    } catch (final IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        }
        if (action.equals("stop")) {
            System.out.println("action: stop");
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        audioRecorder.stopRecording(callbackContext);
                    } catch (final IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        }
        if (action.equals("tag")) {
            System.out.println("action: tag");
            final String tagValue = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    writeTag(tagValue, callbackContext);
                }
            });
            return true;
        }
        return false;
    }

    private void writeTag(String tagValue, CallbackContext callbackContext) {
        if (tagValue != null && tagValue.length() > 0) {
            callbackContext.success(tagValue);
        } else {
            callbackContext.error("Tag not supplied");
        }
    }
}
