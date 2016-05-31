/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.frinex.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import nl.mpi.tg.eg.frinex.model.TagData;
import nl.mpi.tg.eg.frinex.model.TagPairData;
import nl.mpi.tg.eg.frinex.model.TimeStamp;
import nl.mpi.tg.eg.frinex.util.CsvExportException;
import nl.mpi.tg.eg.frinex.util.ParticipantCsvExporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since Aug 10, 2015 3:21:52 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class CsvController {

    @Autowired
    ScreenDataRepository screenDataRepository;
    @Autowired
    TimeStampRepository timeStampRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagPairRepository tagPairRepository;

    @RequestMapping(value = "/zip", method = RequestMethod.GET)
    @ResponseBody
    public void getZip(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=\"results.zip\"");
        response.addHeader("Content-Transfer-Encoding", "binary");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            compressResults(outputStream);
            response.getOutputStream().write(outputStream.toByteArray());
            response.getOutputStream().flush();
        }
    }

    @RequestMapping(value = "/aggregate", method = RequestMethod.GET)
    @ResponseBody
    public void getAggregate(HttpServletResponse response) throws IOException, CsvExportException {
        response.setContentType("application/text");
        response.addHeader("Content-Disposition", "attachment; filename=\"aggregate.csv\"");
        response.addHeader("Content-Transfer-Encoding", "text");
        CSVPrinter printer = new CSVPrinter(
                response.getWriter(),
                CSVFormat.DEFAULT
        );
        final ParticipantCsvExporter participantCsvExporter = new ParticipantCsvExporter();
        participantCsvExporter.appendAggregateCsvHeader(printer);
        ArrayList<String> insertedUserIds = new ArrayList<>();
            for (Participant participant : participantRepository.findByOrderBySubmitDateDesc()) {
                if (!insertedUserIds.contains(participant.getUserId())) {
                    // here we are relying on the last user data submission being the most complete because that data is only added to in the experiment GUI
                    participantCsvExporter.appendAggregateCsvRow(printer, participant, tagRepository.findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(participant.getUserId()));
                    insertedUserIds.add(participant.getUserId());
                }
            }
        printer.close();
//        response.getOutputStream().flush();
    }

    private void addToZipArchive(final ZipOutputStream zipStream, String fileName, byte[] content) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipStream.putNextEntry(zipEntry);
        zipStream.write(content);
        zipStream.closeEntry();
    }

    void compressResults(final OutputStream out) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(out)) {
            zipOutputStream.setLevel(ZipOutputStream.STORED);
            addToZipArchive(zipOutputStream, "participants.csv", getParticipantsCsv());
            addToZipArchive(zipOutputStream, "screenviews.csv", getScreenDataCsv());
            addToZipArchive(zipOutputStream, "tagdata.csv", getTagDataCsv());
            addToZipArchive(zipOutputStream, "tagpairdata.csv", getTagPairDataCsv());
            addToZipArchive(zipOutputStream, "timestampdata.csv", getTimeStampDataCsv());
        }
    }

    private byte[] getParticipantsCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        final ParticipantCsvExporter participantCsvExporter = new ParticipantCsvExporter();
        participantCsvExporter.appendCsvHeader(printer);
        ArrayList<String> insertedUserIds = new ArrayList<>();
        for (Participant participant : participantRepository.findByOrderBySubmitDateDesc()) {
            if (!insertedUserIds.contains(participant.getUserId())) {
                // here we are relying on the last user data submission being the most complete because that data is only added to in the experiment GUI
                participantCsvExporter.appendCsvRow(printer, participant);
                insertedUserIds.add(participant.getUserId());
            }
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }

    private byte[] getScreenDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("UserId", "ScreenName", "ViewDate");
        for (ScreenData screenData : screenDataRepository.findAllDistinctRecords()) {
            printer.printRecord(screenData.getUserId(), screenData.getScreenName(), screenData.getViewDate());
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }

    private byte[] getTimeStampDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("UserId", "EventTag", "EventMs", "TagDate");
        for (TimeStamp timeStamp : timeStampRepository.findAllDistinctRecords()) {
            printer.printRecord(timeStamp.getUserId(), timeStamp.getEventTag(), timeStamp.getEventMs(), timeStamp.getTagDate());
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }

    private byte[] getTagDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("UserId", "EventTag", "TagValue", "EventMs", "TagDate");
        for (TagData tagData : tagRepository.findAllDistinctRecords()) {
            printer.printRecord(tagData.getUserId(), tagData.getEventTag(), tagData.getTagValue(), tagData.getEventMs(), tagData.getTagDate());
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }

    private byte[] getTagPairDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("UserId", "EventTag", "TagValue1", "TagValue2", "EventMs", "TagDate");
        for (TagPairData tagPairData : tagPairRepository.findAllDistinctRecords()) {
            printer.printRecord(tagPairData.getUserId(), tagPairData.getEventTag(), tagPairData.getTagValue1(), tagPairData.getTagValue2(), tagPairData.getEventMs(), tagPairData.getTagDate());
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }
}
