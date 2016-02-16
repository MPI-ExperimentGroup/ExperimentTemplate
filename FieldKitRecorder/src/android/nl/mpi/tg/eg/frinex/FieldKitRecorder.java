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

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @since Dec 9, 2015 4:41:18 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FieldKitRecorder extends CordovaPlugin {

    private AudioRecorder audioRecorder = new WavRecorder();
    String externalStoragePath = Environment.getExternalStorageDirectory().getPath();
    private static final String AUDIO_RECORDER_FOLDER = "MPI_Recorder";
//  private   String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    private CsvWriter csvWriter = null;
    private String currentRecoringDirectory = null;
    final static int PAUSE_TIER = 0;

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("record")) {
            System.out.println("action: record");
            final String userId = args.getString(0);
            final String stimulusSet = args.getString(1);
            final String stimulusId = args.getString(2);

            System.out.println("record: " + userId);
            System.out.println("record: " + stimulusSet);
            System.out.println("record: " + stimulusId);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        Date date = new Date();
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd");
//                        String dirName = "MPI_Recorder_" + dateFormat.format(date);
                        final File outputDirectory = new File(externalStoragePath, AUDIO_RECORDER_FOLDER
                                //                                + File.separator + dirName
                                + File.separator + userId
                                + File.separator + stimulusSet
                                + ((stimulusId != null && !stimulusId.isEmpty()) ? File.separator + stimulusId + File.separator : ""));
                        if (currentRecoringDirectory == null || !currentRecoringDirectory.equals(outputDirectory.getAbsolutePath())) {
                            if (currentRecoringDirectory != null) {
                                if (csvWriter != null) {
                                    csvWriter.writeCsvFile(FieldKitRecorder.this.cordova.getActivity().getApplicationContext());
                                    csvWriter = null;
                                }
                                //audioRecorder.stopRecording(callbackContext); // todo: this might not finish in time and therefore might not start a new recording
                            }
                            currentRecoringDirectory = outputDirectory.getAbsolutePath();
                            final String baseName = audioRecorder.startRecording(outputDirectory, FieldKitRecorder.this.cordova.getActivity().getApplicationContext());
                            csvWriter = new CsvWriter(outputDirectory, baseName);
                            callbackContext.success();
                        }
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
                        if (csvWriter != null) {
                            csvWriter.writeCsvFile(FieldKitRecorder.this.cordova.getActivity().getApplicationContext());
                            csvWriter = null;
                        }
                        audioRecorder.stopRecording();
                        callbackContext.success();
                    } catch (final IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        }
        if (action.equals("startTag")) {
            System.out.println("action: startTag");
            final String tier = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (csvWriter != null) {
                        csvWriter.startTag(Integer.parseInt(tier), audioRecorder.getTime());
                    } else {
                        callbackContext.error("not recording");
                    }
                }
            });
            return true;
        }
        if (action.equals("endTag")) {
            System.out.println("action: endTag");
            final String tier = args.getString(0);
            final String stimulusId = args.getString(1);
            final String stimulusCode = args.getString(2);
            final String tagString = args.getString(3);
            System.out.println("endTag: " + tier);
            System.out.println("endTag: " + stimulusId);
            System.out.println("endTag: " + stimulusCode);
            System.out.println("endTag: " + tagString);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (csvWriter != null) {
                        csvWriter.endTag(Integer.parseInt(tier), audioRecorder.getTime(), stimulusId, stimulusCode, tagString);
                    } else {
                        callbackContext.error("not recording");
                    }
                }
            });
            return true;
        }
        if (action.equals("getTime")) {
//            System.out.println("action: getTime");
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (audioRecorder != null) {
                        callbackContext.success(CsvWriter.makeShortTimeString(audioRecorder.getTime()));
                    } else {
                        callbackContext.error("not recording");
                    }
                }
            });
            return true;
        }
        if (action.equals("isRecording")) {
//            System.out.println("action: isRecording");
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (audioRecorder != null) {
                        callbackContext.success();
                    } else {
                        callbackContext.error("not recording");
                    }
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        if (csvWriter != null) {
            try {
                csvWriter.writeCsvFile(this.cordova.getActivity().getApplicationContext());
                csvWriter = null;
            } catch (final IOException e) {
                System.out.println("IOException closing csvWriter: " + e.getMessage());
            }
        }
        audioRecorder.terminateRecorder();
    }

    private String getFormattedSystemTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onPause(boolean multitasking) {
        if (csvWriter != null) {
            try {
                csvWriter.startTag(PAUSE_TIER, audioRecorder.getTime());
                csvWriter.writeCsvFile(this.cordova.getActivity().getApplicationContext());
            } catch (final IOException e) {
                System.out.println("IOException from csvWriter: " + e.getMessage());
            }
        }
        audioRecorder.pauseRecorder();
    }

    @Override
    public void onResume(boolean multitasking) {
        audioRecorder.resumeRecorder();
        if (csvWriter != null) {
            csvWriter.endTag(PAUSE_TIER, audioRecorder.getTime(), "ResumedAfterPause", getFormattedSystemTime(), "");
        }
    }
}
