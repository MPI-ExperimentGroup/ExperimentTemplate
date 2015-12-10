/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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

/**
 * @since Dec 10, 2015 3:24:50 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AudioRecorder {

    private static final int RECORDER_BPP = 16;
    private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
    private static final String AUDIO_RECORDER_FOLDER = "AudioData";
    private static final int RECORDER_SAMPLERATE = 44100;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private RandomAccessFile randomAccessFile = null;
    private final int bufferSize;
    private AudioRecord recorder = null;
    private int recordedLength = 0;

    public AudioRecorder() {

        bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
    }

    public boolean startRecording(final CallbackContext callbackContext) {
        String externalStoragePath = Environment.getExternalStorageDirectory().getPath();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy_MM_dd");
        Date date = new Date();
        String dirName = "MPI_Recorder_" + dateFormat.format(date) + "/";
        dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String timeString = dateFormat.format(date);
        File outputFile = new File(externalStoragePath, AUDIO_RECORDER_FOLDER + "/" + dirName + timeString + UUID.randomUUID().toString() + AUDIO_RECORDER_FILE_EXT_WAV);

        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }

        try {
            randomAccessFile = new RandomAccessFile(outputFile, "rw");

            // write a temporary wav header
            writeWaveFileHeader(randomAccessFile, 0, 36, RECORDER_SAMPLERATE, 1, 1000);

            // start the audio recording
            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, bufferSize);

            recorder.setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener() {
                public void onPeriodicNotification(AudioRecord recorder) {
                    try {
                        byte buffer[] = new byte[bufferSize];
                        recorder.read(buffer, 0, buffer.length);
                        randomAccessFile.write(buffer);
                        recordedLength += buffer.length;
                    } catch (final Exception e) {
                        cordova.getThreadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                callbackContext.error(e.getMessage());
                            }
                        });
                    }
                }

                public void onMarkerReached(AudioRecord recorder) {
                }
            });
            recorder.setPositionNotificationPeriod(bufferSize);
            recorder.startRecording();
        } catch (final Exception e) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    callbackContext.error(e.getMessage());
                }
            });
            return false;
        }
        return true;
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
