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

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("record")) {
                new AudioRecorder().startRecording(cordova, callbackContext);
                return true;
            }
            if (action.equals("stop")) {
                new AudioRecorder().stopRecording(callbackContext);
                return true;
            }
            if (action.equals("tag")) {
                String tagValue = args.getString(0);
                writeTag(tagValue, callbackContext);
                return true;
            }
        } catch (final IOException e) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    callbackContext.error(e.getMessage());
                }
            });
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
