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
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import nl.mpi.tg.eg.frinex.model.AudioData;
import nl.mpi.tg.eg.frinex.model.EventTime;
import nl.mpi.tg.eg.frinex.model.GroupData;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
import nl.mpi.tg.eg.frinex.model.TagData;
import nl.mpi.tg.eg.frinex.model.TagPairData;
import nl.mpi.tg.eg.frinex.model.TimeStamp;
import nl.mpi.tg.eg.frinex.util.CsvExportException;
import nl.mpi.tg.eg.frinex.util.ParticipantCsvExporter;
import nl.mpi.tg.eg.frinex.util.StimuliTagExpander;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private GroupDataRepository groupDataRepository;
    @Autowired
    private StimulusResponseRepository stimulusResponseRepository;
    @Autowired
    private AudioDataRepository audioDataRepository;

    @RequestMapping(value = "/csv.zip", method = RequestMethod.GET)
    @ResponseBody
    public void getCsvZip(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=\"results.zip\"");
        response.addHeader("Content-Transfer-Encoding", "binary");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            compressResults(outputStream);
            response.getOutputStream().write(outputStream.toByteArray());
            response.getOutputStream().flush();
        }
    }

    @RequestMapping(value = "/audio_{yearString}-{monthString}-{dayString}.zip", method = RequestMethod.GET)
    @ResponseBody
    public void getAudioZip(HttpServletResponse response,
            @PathVariable(value = "yearString", required = true) int selectedYear,
            @PathVariable(value = "monthString", required = true) int selectedMonth,
            @PathVariable(value = "dayString", required = true) int selectedDay) throws IOException {
        response.setContentType("application/zip");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, selectedMonth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date selectedDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, selectedDay + 1);
        Date selectedEndDate = calendar.getTime();
        String selectedDateString = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
        response.setHeader("Content-Disposition", "attachment; filename=\"audio_" + selectedDateString + ".zip\"");
        response.setHeader("Content-Transfer-Encoding", "binary");
        final ServletOutputStream outputStream = response.getOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
            for (AudioData audioData : audioDataRepository.findAllBySubmitDateBetween(selectedDate, selectedEndDate)) {
                InputStream audioStream = audioDataRepository.getMediaStream(audioData.getId());
                final String filename = audioData.getUserId() + "_" + audioData.getScreenName() + "_"
                        + audioData.getStimulusId() + "_" + audioData.getId() + "."
                        + audioData.getRecordingFormat().name();
                addToZipArchive(zipOutputStream, filename, audioStream);
                zipOutputStream.flush();
                outputStream.flush();
            }
            zipOutputStream.finish();
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
        for (Participant participant : participantRepository.findAllByOrderBySubmitDateDesc()) {
            if (!insertedUserIds.contains(participant.getUserId())) {
                // here we are relying on the last user data submission being the most complete because that data is only added to in the experiment GUI
                participantCsvExporter.appendAggregateCsvRow(printer, participant, tagRepository.findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(participant.getUserId()));
                insertedUserIds.add(participant.getUserId());
            }
        }
        printer.close();
//        response.getOutputStream().flush();
    }

    @RequestMapping(value = "/groupdatacsv", method = RequestMethod.GET)
    @ResponseBody
    public void getGroupDataLimor(HttpServletResponse response) throws IOException, CsvExportException {
//        response.setContentType("application/text");
//        response.addHeader("Content-Disposition", "attachment; filename=\"groupdata.csv\"");
//        response.addHeader("Content-Transfer-Encoding", "text");
        CSVPrinter printer = new CSVPrinter(
                response.getWriter(),
                CSVFormat.DEFAULT
        );
        List<String> headerList = new ArrayList();
        headerList.add("Event Date");
        headerList.add("Screen Name");
        headerList.add("Group Name");
        headerList.add("Member Codes");
        headerList.add("Communication Channels");
        headerList.add("Sender Code");
        headerList.add("Respondent Code");
        headerList.add("Index");
        final StimuliTagExpander stimuliTagExpander = new StimuliTagExpander();
        for (String columnTag : stimuliTagExpander.getTagColumns()) {
            headerList.add("Target-" + columnTag);
        }
        headerList.add("Target");
        for (String columnTag : stimuliTagExpander.getTagColumns()) {
            headerList.add("Response-" + columnTag);
        }
        headerList.add("Response");
        for (int distractorIndex : stimuliTagExpander.getDistractorColumns()) {
            for (String columnTag : stimuliTagExpander.getTagColumns()) {
                headerList.add("Distractor-" + (distractorIndex + 1) + "-" + columnTag);
            }
            headerList.add("Distractor-" + (distractorIndex + 1));
        }
        headerList.add("Message");
        headerList.add("ms");
        printer.printRecord(headerList);
        for (GroupData groupData : groupDataRepository.findAll()) {
            List<Object> rowList = new ArrayList();
            rowList.add(groupData.getEventDate());
            rowList.add(groupData.getScreenName());
            rowList.add(groupData.getGroupName());
            rowList.add(groupData.getAllMemberCodes());
            rowList.add(groupData.getGroupCommunicationChannels());
            rowList.add(groupData.getSenderMemberCode());
            rowList.add(groupData.getRespondentMemberCode());
            rowList.add(groupData.getStimulusIndex());
            for (String tagColumn : stimuliTagExpander.getTagColumns(groupData.getStimulusId(), ":")) {
                rowList.add(tagColumn);
            }
            rowList.add(groupData.getStimulusId());
            for (String tagColumn : stimuliTagExpander.getTagColumns(groupData.getResponseStimulusId(), ":")) {
                rowList.add(tagColumn);
            }
            rowList.add(groupData.getResponseStimulusId());
            for (String tagColumn : stimuliTagExpander.getDistractorTagColumns(groupData.getStimulusOptionIds(), ":")) {
                rowList.add(tagColumn);
            }
            rowList.add(groupData.getMessageString());
            rowList.add(groupData.getEventMs());
            printer.printRecord(rowList);
        }
        printer.close();
    }

    private void addToZipArchive(final ZipOutputStream zipStream, String fileName, byte[] content) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipStream.putNextEntry(zipEntry);
        zipStream.write(content);
        zipStream.closeEntry();
    }

    private void addToZipArchive(final ZipOutputStream zipStream, final String fileName, final InputStream dataStream) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipStream.putNextEntry(zipEntry);
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = dataStream.read(buffer)) != -1) {
            zipStream.write(buffer, 0, bytesRead);
        }
        dataStream.close();
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
            addToZipArchive(zipOutputStream, "groupdata.csv", getGroupDataCsv());
            addToZipArchive(zipOutputStream, "stimulusresponse.csv", getStimulusResponseDataCsv());
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
        for (Participant participant : participantRepository.findAllByOrderBySubmitDateDesc()) {
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

    private byte[] getGroupDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("EventDate", "SubmitDate", "ExperimentName", "GroupUUID", "GroupName", "ScreenName", "MessageRespondentId", "AllMemberCodes", "GroupCommunicationChannels", "SenderMemberCode", "RespondentMemberCode", "StimulusId", "StimulusIndex", "ResponseStimulusId", "StimulusOptionIds", "MessageSenderId", "MessageString", "EventMs");
        for (GroupData groupData : groupDataRepository.findAllDistinctRecords()) {
            printer.printRecord(
                    groupData.getEventDate(),
                    groupData.getSubmitDate(),
                    groupData.getExperimentName(),
                    groupData.getGroupUUID(),
                    groupData.getGroupName(),
                    groupData.getScreenName(),
                    groupData.getMessageRespondentId(),
                    groupData.getAllMemberCodes(),
                    groupData.getGroupCommunicationChannels(),
                    groupData.getSenderMemberCode(),
                    groupData.getRespondentMemberCode(),
                    groupData.getStimulusId(),
                    groupData.getStimulusIndex(),
                    groupData.getResponseStimulusId(),
                    groupData.getStimulusOptionIds(),
                    groupData.getMessageSenderId(),
                    groupData.getMessageString(),
                    groupData.getEventMs());
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }

    private String serialiseEventTimes(List<EventTime> eventTimes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (EventTime event : eventTimes) {
            stringBuilder.append(event.getEvent());
            stringBuilder.append(":");
            stringBuilder.append(event.getMs());
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    private byte[] getStimulusResponseDataCsv() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(
                stringBuilder,
                CSVFormat.DEFAULT
        );
        printer.printRecord("TagDate", "ExperimentName", "ScreenName", "DataChannel", "ResponseGroup", "ScoreGroup", "StimulusId", "Response", "IsCorrect", "UserId", "Events", "EventMs", "GamesPlayed", "TotalScore", "TotalPotentialScore", "CurrentScore", "CorrectStreak", "ErrorStreak", "PotentialScore", "MaxScore", "MaxErrors", "MaxCorrectStreak", "MaxErrorStreak", "MaxPotentialScore");
//        for (StimulusResponse stimulusResponse : stimulusResponseRepository.findAllDistinctRecords()) {
        final List<StimulusResponse> contentDistinct = new ArrayList<>();
        for (StimulusResponse stimulusResponse : stimulusResponseRepository.findAllByOrderByTagDateAsc()) {
            if (!contentDistinct.contains(stimulusResponse)) {
                contentDistinct.add(stimulusResponse);
                printer.printRecord(stimulusResponse.getTagDate(),
                        stimulusResponse.getExperimentName(),
                        stimulusResponse.getScreenName(),
                        stimulusResponse.getDataChannel(),
                        stimulusResponse.getResponseGroup(),
                        stimulusResponse.getScoreGroup(),
                        stimulusResponse.getStimulusId(),
                        stimulusResponse.getResponse(),
                        stimulusResponse.getIsCorrect(),
                        stimulusResponse.getUserId(),
                        serialiseEventTimes(stimulusResponse.getEventTimes()),
                        stimulusResponse.getEventMs(),
                        stimulusResponse.getGamesPlayed(),
                        stimulusResponse.getTotalScore(),
                        stimulusResponse.getTotalPotentialScore(),
                        stimulusResponse.getCurrentScore(),
                        stimulusResponse.getCorrectStreak(),
                        stimulusResponse.getErrorStreak(),
                        stimulusResponse.getPotentialScore(),
                        stimulusResponse.getMaxScore(),
                        stimulusResponse.getMaxErrors(),
                        stimulusResponse.getMaxCorrectStreak(),
                        stimulusResponse.getMaxErrorStreak(),
                        stimulusResponse.getMaxPotentialScore());
            }
        }
        printer.close();
        return stringBuilder.toString().getBytes();
    }
}
