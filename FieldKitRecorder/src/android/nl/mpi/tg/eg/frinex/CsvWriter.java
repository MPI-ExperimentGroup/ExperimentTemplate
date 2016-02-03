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
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @since Dec 10, 2015 4:34:49 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class CsvWriter {

    private final File outputDirectory;
    private final String baseName;
    private final ArrayList<CSVRow> rows = new ArrayList<CSVRow>();
    private final HashMap<Integer, Long> startTimes = new HashMap<Integer, Long>();
    private static final String CSV_SUFFIX = ".csv";

    public CsvWriter(final File outputDirectory, String baseName) {
        this.outputDirectory = outputDirectory;
        this.baseName = baseName;
    }

    public void writeCsvFile() throws IOException {
//        System.out.println("filePath: " + filePath);
        final FileWriter csvFileWriter = new FileWriter(new File(outputDirectory, baseName + CSV_SUFFIX), true);
        csvFileWriter.write("BeginTime,EndTime,BeginTime2,EndTime2,Tier,Stimulus,Tag\n");
        Collections.sort(rows);
        for (CSVRow row : rows) {
            csvFileWriter.write(makeTimeString(row.getBeginTime()) + ","
                    + makeTimeString(row.getEndTime())
                    + "," + makeTimeString2(row.getBeginTime())
                    + "," + makeTimeString2(row.getEndTime())
                    + "," + row.getTier() + ","
                    + row.getStimulusString() + ","
                    + row.getTagString() + "\n");
        }
        csvFileWriter.close();
    }

    public void startTag(int tier, long startTime) {
        System.out.println("tier: " + tier);
        System.out.println("startTime: " + startTime);
        startTimes.put(tier, startTime);
    }

    public void endTag(int tier, long endTime, String stimulusString, String tagString) {
        System.out.println("tier: " + tier);
        System.out.println("endTime: " + endTime);
        System.out.println("stimulusString: " + stimulusString);
        System.out.println("tagString: " + tagString);
        final Long startTime = startTimes.get(tier);
        rows.add(new CSVRow((startTime != null) ? startTime : endTime, endTime, tier, stimulusString, tagString));
    }

    public static String makeTimeString(long milli) {//System.out.println("MILLI: " + milli);
        final String hours = padZeros(milli / (60 * 60 * 1000), 2);//System.out.println("hh: " + hours);
        milli = milli % (60 * 60 * 1000);
        final String minutes = padZeros(milli / (60 * 1000), 2);
        milli = milli % (60 * 1000);
        final String seconds = padZeros(milli / 1000, 2);
        final String millis = padZeros(milli % 1000, 3);//System.out.println("mmm: " + millis);
        return hours + ":" + minutes + ":" + seconds + "." + millis;
    }

    public static String makeShortTimeString(long milli) {//System.out.println("MILLI: " + milli);
        final String hours = padZeros(milli / (60 * 60 * 1000), 2);//System.out.println("hh: " + hours);
        milli = milli % (60 * 60 * 1000);
        final String minutes = padZeros(milli / (60 * 1000), 2);
        milli = milli % (60 * 1000);
        final String seconds = padZeros(milli / 1000, 2);
        return hours + ":" + minutes + ":" + seconds;
    }

    private static String makeTimeString2(long milli) {
        final String seconds = "" + milli / 1000;
        final String millis = padZeros(milli % 1000, 3);
        return seconds + "." + millis;
    }

    private static String padZeros(long number, int nZeros) {
        String result = "" + number;
        while (result.length() < nZeros) {
            result = "0" + result;
        }
        return result;
    }
}
