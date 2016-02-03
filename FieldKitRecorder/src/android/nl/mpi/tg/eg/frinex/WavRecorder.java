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

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Environment;
import android.media.MediaRecorder;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;

/**
 * @since Dec 10, 2015 3:24:50 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WavRecorder implements AudioRecorder {

    private static final int RECORDER_BPP = 16;
    private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
    private static final int RECORDER_SAMPLERATE = 44100;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
//    private final int bufferSize;
    public AudioRecord recorder = null;
    private long recordedLength = 0;
    private Thread recordingThread = null;
    private boolean isRecording = false;
    private static final int TIMER_INTERVAL = 120;
    private static final int FRAME_PERIOD = RECORDER_SAMPLERATE * TIMER_INTERVAL / 1000;
    private static final int BUFFER_SIZE = FRAME_PERIOD * 2 * 16 * 1 / 8;
    private File outputFile = null;

    public WavRecorder() {

//        bufferSize = 100 * AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
    }

    public long getTime() {
        return recordedLength / ((RECORDER_BPP / 8 /* recordedLength is in bytes */) * RECORDER_SAMPLERATE / 1000);
    }

    public boolean isRecording() {
        return isRecording;
    }

    public String startRecording(final CordovaInterface cordova, final CallbackContext callbackContext, final File outputDirectory) throws IOException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        String timeString = dateFormat.format(date);
        final String baseName = timeString; // + UUID.randomUUID().toString();

        // setting outputFile allows for new file names to be passed and for the recording to contine but into the new file after cleanly closing the previous file
        outputFile = new File(outputDirectory, baseName + AUDIO_RECORDER_FILE_EXT_WAV);

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        if (recorder == null) {
            recordedLength = 0;
            // start the audio recording
            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, BUFFER_SIZE);
            recordingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("recording started");
                    try {
                        while (outputFile != null) {
                            System.out.println("outputFile: " + outputFile.getAbsolutePath());
                            final RandomAccessFile randomAccessFile = new RandomAccessFile(outputFile, "rw");
                            outputFile = null;
                            randomAccessFile.seek(randomAccessFile.length());
                            // write a temporary wav header
                            writeWaveFileHeader(randomAccessFile, 0, 36, RECORDER_SAMPLERATE, 1, 1000);
                            isRecording = true;
                            callbackContext.success();
                            byte buffer[] = new byte[BUFFER_SIZE];
                            // if a new file is specified in outputFile then close the current file and start a new file but with the recorder running the entire time
                            while (outputFile == null || recorder != null && isRecording) {
//                            System.out.println("recorder.read");
                                final int bytesRead = recorder.read(buffer, 0, buffer.length);
//                            System.out.println("bytesRead: " + bytesRead);
                                if (bytesRead > 0) {
                                    randomAccessFile.write(buffer, 0, bytesRead);
                                    recordedLength = randomAccessFile.length() - 36;
                                }
                                isRecording = recorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING;
//                            System.out.println("recordedLength: " + recordedLength);
//                            System.out.println("bytesRead: " + bytesRead);
//                            System.out.println("bufferSize: " + BUFFER_SIZE);
                            }
                            System.out.println("recording ended");
                            recorder.release();
                            recorder = null;
                            // rewrite the wav header
                            long totalAudioLen = randomAccessFile.length() - 36;
                            long totalDataLen = totalAudioLen + 36;
                            long longSampleRate = RECORDER_SAMPLERATE;
                            int channels = 2;
                            if (RECORDER_CHANNELS == AudioFormat.CHANNEL_IN_MONO) {
                                channels = 1;
                            }
                            long byteRate = RECORDER_BPP * RECORDER_SAMPLERATE * channels / 8;

                            randomAccessFile.seek(
                                    0);
                            writeWaveFileHeader(randomAccessFile, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);

                            randomAccessFile.close();
                        }
                    } catch (final IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                    isRecording = false;
                }
            }, "recordingThread");
            recordingThread.start();
            recorder.startRecording();
        }
        System.out.println("outputFile: " + outputFile.getPath());
        return baseName;
    }

    public void stopRecording(final CallbackContext callbackContext) throws IOException {
        System.out.println("stopRecording");
        if (recorder != null) {
            recorder.stop();
        }
        callbackContext.success();
    }

    private void writeWaveFileHeader(RandomAccessFile out, long totalAudioLen, long totalDataLen, long longSampleRate, int channels, long byteRate) throws IOException {

        byte[] header = new byte[44];

        header[0] = 'R';  // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f';  // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16;  // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1;  // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (channels * 16 / 8);  // block align
        header[33] = 0;
        header[34] = RECORDER_BPP;  // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

        out.write(header, 0, 44);
    }
}
