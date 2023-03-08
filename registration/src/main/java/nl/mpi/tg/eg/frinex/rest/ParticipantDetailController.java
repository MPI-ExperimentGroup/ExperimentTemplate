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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletRequest;
import javax.validation.constraints.NotNull;
import nl.mpi.tg.eg.frinex.model.DataDeletionLog;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.TagData;
import nl.mpi.tg.eg.frinex.model.TagPairData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since Aug 4, 2015 5:38:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class ParticipantDetailController {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ScreenDataRepository screenDataRepository;
    @Autowired
    private TagPairRepository tagPairRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TimeStampRepository timeStampRepository;
    @Autowired
    private StimulusResponseRepository stimulusResponseRepository;
    @Autowired
    private AudioDataRepository audioDataRepository;

    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.allowDelete}")
    protected boolean allowDelete;

    // the first ibdex is the label report, e.g. screen1 or screen2
    // the first index is the row number
    // the third index if the cell/ column  in the row
    private HashMap<String, String[][]> userSummary;
    private HashMap<String, String[][]> fastTrack;
    private HashMap<String, String[][]> fineTuning;

    private List<String> userSummarySortedKeys;
    private List<String> fastTrackSortedKeys;
    private List<String> fineTuningSortedKeys;

    @RequestMapping("participantdetail")
    public String participantDetail(ServletRequest request, @RequestParam(value = "id", required = true) String id, Model model,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {

        List<TagPairData> tagPairData = this.tagPairRepository.findByUserIdOrderByTagDateAsc(id);

        this.fetchCvsTables(tagPairData);

        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);

//        Map<String, String[]> paramMap = request.getParameterMap();
        boolean showStale = !simpleMode;
        final List<Participant> freshCopyUserData = this.participantRepository.findByStaleCopyAndUserId(false, id);
        if (showStale) {
            model.addAttribute("participantData", this.participantRepository.findByUserId(id));
        } else {
            model.addAttribute("participantData", freshCopyUserData);
        }
        if (freshCopyUserData.isEmpty()) {
            final Participant insertUserData = new Participant(id);
            model.addAttribute("insertUserData", insertUserData);
        } else {
            model.addAttribute("insertUserData", freshCopyUserData.get(0));
        }
        model.addAttribute("participantScreenData", this.screenDataRepository.findByUserIdOrderByViewDateAsc(id));
        model.addAttribute("countOfBrowserWindowClosed", this.screenDataRepository.findByUserIdAndScreenName(id, BROWSER_WINDOW_CLOSED).size());
        model.addAttribute("countOfApplicationStarted", this.screenDataRepository.findByUserIdAndScreenName(id, APPLICATION_STARTED).size());
        model.addAttribute("allowDelete", this.allowDelete);
        model.addAttribute("userSummary", this.userSummary);
        model.addAttribute("fastTrack", this.fastTrack);
        model.addAttribute("fineTuning", this.fineTuning);
        model.addAttribute("userSummarySortedKeys", this.userSummarySortedKeys);
        model.addAttribute("fastTrackSortedKeys", this.fastTrackSortedKeys);
        model.addAttribute("fineTuningSortedKeys", this.fineTuningSortedKeys);
        model.addAttribute("participantTagPairData", this.tagPairRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantSubsetStimulus", this.tagPairRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, SUBSET_STIMULUS));
        model.addAttribute("participantCompletionCode", this.tagPairRepository.findByUserIdAndEventTagAndTagValue1OrderByTagDateAsc(id, DATA_SUBMISSION, COMPLETION_CODE));
        model.addAttribute("participantAudioTestCount", this.tagRepository.countDistinctTagDateByUserIdAndTagValue(id, CARLY_BLUE_CHAIROGG)); // todo: this can go or be updated
        model.addAttribute("participantNextButtonMsData", this.timeStampRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, STIMULUS1_NEXT));
        model.addAttribute("participantTagData", this.tagRepository.findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantTimeStampData", this.timeStampRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantResponseData", this.stimulusResponseRepository.findByUserIdDistinctOrderByTagDateAsc(id));
        model.addAttribute("participantAudioData", this.audioDataRepository.findByUserIdOrderBySubmitDateAsc(id));
        return "participantdetail";
    }

    @Transactional
    @RequestMapping("participantdelete")
    /*
        This method will have no effect if the application.properties does not specify allowDelete true.
     */
    public String participantDataDelete(ServletRequest request, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "providedChecksum", required = false) String providedChecksum, @RequestParam(value = "deleteOption", defaultValue = "false", required = false) String deleteOption, Model model,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
        final boolean deleteAudio = "deleteAudio".equals(deleteOption);
        final boolean deleteAll = "deleteAll".equals(deleteOption);
        final String requiredChecksum = "Please delete " + ((deleteAudio) ? "audio" : "all") + " data for the participant " + id + ". I understand that this is permanent and cannot be reverted.";
        model.addAttribute("requiredChecksum", requiredChecksum);
        model.addAttribute("deleteAudio", deleteAudio);
        model.addAttribute("deleteAll", deleteAll);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        model.addAttribute("allowDelete", allowDelete);
        if (allowDelete) {
            if (requiredChecksum.equals(providedChecksum)) {
                final String screenName = "administration system";
                final String eventTag = (deleteAudio) ? "delete participant audio" : "delete participant data";
                final int eventMs = 0;
                final Date tagDate = new java.util.Date();
                final String remoteAddr = request.getRemoteAddr();
                final int lastIndexOf = remoteAddr.lastIndexOf(".");
                final String deletionAddr = (lastIndexOf > 0) ? remoteAddr.substring(0, lastIndexOf) + ".0" : "";
                // todo: IPv6 is not handled at this stage but it should be striped to 80 bits when added
                // log the ip address that requested this action
                final TagData deletionLogEntry = new TagData("participantdelete", screenName, eventTag, deletionAddr, eventMs, tagDate);
                this.tagRepository.save(deletionLogEntry);
                final DataDeletionLog dataDeletionLog = new DataDeletionLog();
                dataDeletionLog.setDeletionDate(tagDate);
                dataDeletionLog.setDeletionAddr(deletionAddr);
//                final PublicStatistics usageStats = new PublicStatistics();
//                final ScreenData screenData = screenDataRepository.findTop1ByOrderBySubmitDateAsc();
//                usageStats.firstDeploymentAccessed = (screenData != null) ? screenData.getSubmitDate() : null;
//                usageStats.totalParticipantsSeen = participantRepository.countDistinctUserId();
//                usageStats.totalDeploymentsAccessed = tagRepository.countDistinctTagValueByEventTag("compileDate");
//                usageStats.totalPageLoads = tagRepository.countDistinctDateByEventTag("compileDate");
//                usageStats.totalStimulusResponses = stimulusResponseRepository.countDistinctRecords();
//                final Participant participantFirst = participantRepository.findTop1ByOrderBySubmitDateAsc();
//                usageStats.firstParticipantSeen = (participantFirst != null) ? participantFirst.getSubmitDate() : null;
//                final Participant participantLast = participantRepository.findTop1ByOrderBySubmitDateDesc();
//                usageStats.lastParticipantSeen = (participantLast != null) ? participantLast.getSubmitDate() : null;
//                usageStats.participantsFirstAndLastSeen = participantRepository.findFirstAndLastUsersAccess();
//                usageStats.sessionFirstAndLastSeen = tagRepository.findFirstAndLastSessionAccess();
//                usageStats.totalMediaResponses = audioDataRepository.count();
                // TODO: save the deletion log and public usage stats
                 
                // delete the audio
                this.audioDataRepository.deleteByUserId(id);
                if (deleteAudio) {
                    model.addAttribute("deletionSuccess", true);
                } else if (deleteAll) {
                    participantRepository.deleteByUserId(id);
                    screenDataRepository.deleteByUserId(id);
                    tagPairRepository.deleteByUserId(id);
                    tagRepository.deleteByUserId(id);
                    timeStampRepository.deleteByUserId(id);
                    stimulusResponseRepository.deleteByUserId(id);
                    model.addAttribute("deletionSuccess", true);
                } else {
                    model.addAttribute("deletionSuccess", false);
                }
            } else {
                model.addAttribute("deletionSuccess", false);
            }
        }
        model.addAttribute("countOfMetadata", participantRepository.countByUserId(id));
        model.addAttribute("countOfScreenData", screenDataRepository.countByUserId(id));
        model.addAttribute("countOfTagPair", tagPairRepository.countByUserId(id));
        model.addAttribute("countOfTags", tagRepository.countByUserId(id));
        model.addAttribute("countOfTimestamps", timeStampRepository.countByUserId(id));
        model.addAttribute("countOfAudio", audioDataRepository.countByUserId(id));
        model.addAttribute("countOfStimulusResponse", stimulusResponseRepository.countByUserId(id));
        return "participantdelete";
    }

    private static final String DATA_SUBMISSION = "DataSubmission";
    private static final String APPLICATION_STARTED = "ApplicationStarted";
    private static final String CARLY_BLUE_CHAIROGG = "carly_blue_chair.ogg";
    private static final String COMPLETION_CODE = "CompletionCode";
    private static final String BROWSER_WINDOW_CLOSED = "BrowserWindowClosed";
    private static final String STIMULUS1_NEXT = "stimulus1Next";
    private static final String SUBSET_STIMULUS = "SubsetStimulus";

    private void fetchCvsTables(List<TagPairData> bulk) {

        HashMap<String, ArrayList<String[]>> userSummaryHelper = new HashMap<String, ArrayList<String[]>>();
        HashMap<String, ArrayList<String[]>> fastTrackHelper = new HashMap<String, ArrayList<String[]>>();
        HashMap<String, ArrayList<String[]>> fineTuningHelper = new HashMap<String, ArrayList<String[]>>();

        this.userSummarySortedKeys = new ArrayList<String>();
        this.fastTrackSortedKeys = new ArrayList<String>();
        this.fineTuningSortedKeys = new ArrayList<String>();

        for (TagPairData tagPairData : bulk) {

            String screen = tagPairData.getScreenName();

            if (!userSummaryHelper.containsKey(screen)) {
                userSummaryHelper.put(screen, new ArrayList<String[]>());
                this.userSummarySortedKeys.add(screen);
            }
            if (!fastTrackHelper.containsKey(screen)) {
                fastTrackHelper.put(screen, new ArrayList<String[]>());
                this.fastTrackSortedKeys.add(screen);
            }
            if (!fineTuningHelper.containsKey(screen)) {
                fineTuningHelper.put(screen, new ArrayList<String[]>());
                this.fineTuningSortedKeys.add(screen);

            }

            if (tagPairData.getEventTag().equals("user_summary")) {
                this.addCsvRow(userSummaryHelper.get(screen), tagPairData);
            } else {
                if (tagPairData.getEventTag().equals("fast_track")) {
                    this.addCsvRow(fastTrackHelper.get(screen), tagPairData);
                } else {
                    if (tagPairData.getEventTag().equals("fine_tuning")) {
                        this.addCsvRow(fineTuningHelper.get(screen), tagPairData);
                    }
                }
            }
        }

        this.userSummary = this.createOrderedTable(userSummaryHelper);
        this.fastTrack = this.createOrderedTable(fastTrackHelper);
        this.fineTuning = this.createOrderedTable(fineTuningHelper);

        Collections.sort(this.userSummarySortedKeys);
        Collections.sort(this.fastTrackSortedKeys);
        Collections.sort(this.fineTuningSortedKeys);
    }

    private void addCsvRow(ArrayList<String[]> table, TagPairData tagPairData) {
        String[] row = tagPairData.getTagValue2().split(";");
        String[] csvRow = new String[row.length + 1];
        csvRow[0] = tagPairData.getTagValue1(); // gets row label
        for (int i = 0; i < row.length; i++) {
            csvRow[i + 1] = row[i];
        }
        table.add(csvRow);
    }

    private String[][] orderByRowTag(ArrayList<String[]> buffer) {
        String[][] retVal = new String[buffer.size()][]; // amount of rows
        for (String[] csvRow : buffer) {
            String strIndex = csvRow[0].substring("row".length());
            int index = Integer.parseInt(strIndex);
            retVal[index] = Arrays.copyOfRange(csvRow, 1, csvRow.length);
        }
        return retVal;
    }

    private HashMap<String, String[][]> createOrderedTable(HashMap<String, ArrayList<String[]>> unorderedTable) {
        HashMap<String, String[][]> retVal = new HashMap<String, String[][]>();
        Set<String> summaryKeys = unorderedTable.keySet();
        for (String key : summaryKeys) {
            String[][] orderedRow = this.orderByRowTag(unorderedTable.get(key));
            retVal.put(key, orderedRow);

        }
        return retVal;
    }

}
