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
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.validation.constraints.NotNull;
import nl.mpi.tg.eg.frinex.model.DataDeletionLog;
import nl.mpi.tg.eg.frinex.model.Participant;
import nl.mpi.tg.eg.frinex.model.ScreenData;
import nl.mpi.tg.eg.frinex.model.StimulusResponse;
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
    @Autowired
    private DataDeletionLogRepository dataDeletionLogRepository;

    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.allowDelete}")
    protected boolean allowDelete;

    @RequestMapping("participantdetail")
    public String participantDetail(ServletRequest request, @RequestParam(value = "id", required = true) String id, Model model,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
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
        model.addAttribute("participantTagPairData", this.tagPairRepository.findByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantSubsetStimulus", this.tagPairRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, SUBSET_STIMULUS));
        model.addAttribute("participantCompletionCode", this.tagPairRepository.findByUserIdAndEventTagAndTagValue1OrderByTagDateAsc(id, DATA_SUBMISSION, COMPLETION_CODE));
        model.addAttribute("participantAudioTestCount", this.tagRepository.countDistinctTagDateByUserIdAndTagValue(id, CARLY_BLUE_CHAIROGG)); // todo: this can go or be updated
        model.addAttribute("participantNextButtonMsData", this.timeStampRepository.findByUserIdAndEventTagOrderByTagDateAsc(id, STIMULUS1_NEXT));
        model.addAttribute("participantTagData", this.tagRepository.findDistinctUserIdEventTagTagValueEventMsTageDateByUserIdOrderByTagDateAsc(id));
        model.addAttribute("participantTimeStampData", this.timeStampRepository.findByUserIdOrderByTagDateAsc(id));
//        model.addAttribute("participantResponseData", this.stimulusResponseRepository.findByUserIdDistinctOrderByTagDateAsc(id));
        final List<StimulusResponse> contentDistinct = new ArrayList<>();
        for (StimulusResponse stimulusResponse : this.stimulusResponseRepository.findByUserIdOrderByTagDateAsc(id)) {
            if (!contentDistinct.contains(stimulusResponse)) {
                contentDistinct.add(stimulusResponse);
            }
        }
        model.addAttribute("participantResponseData", contentDistinct);
        model.addAttribute("participantAudioData", this.audioDataRepository.findByUserIdOrderBySubmitDateAsc(id));
        return "participantdetail";
    }

    @Transactional
    @RequestMapping("participantdelete")
    /*
        This method will have no effect if the application.properties does not specify allowDelete true.
     */
    public String participantDataDelete(ServletRequest request, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "providedCheckbox1", required = false) String providedCheckbox1, @RequestParam(value = "providedCheckbox2", required = false) String providedCheckbox2, @RequestParam(value = "deleteOption", defaultValue = "false", required = false) String deleteOption, Model model,
            @RequestParam(value = "simple", required = false, defaultValue = "true") boolean simpleMode,
            @RequestParam(value = "id", required = false) String paramId) {
        final boolean deleteAudio = "deleteAudio".equals(deleteOption);
        final boolean deleteAll = "deleteAll".equals(deleteOption);
        final String requiredCheckbox1 = "Delete data for the participant " + id;
        final String requiredCheckbox2 = "I understand that this is permanent and cannot be reverted.";
        model.addAttribute("requiredCheckbox1", requiredCheckbox1);
        model.addAttribute("requiredCheckbox2", requiredCheckbox2);
        model.addAttribute("deleteAudio", deleteAudio);
        model.addAttribute("deleteAll", deleteAll);
        model.addAttribute("simpleMode", simpleMode);
        model.addAttribute("paramId", paramId);
        model.addAttribute("allowDelete", allowDelete);

        final DataDeletionLog pendingDeleteInfo = new DataDeletionLog();
        final ScreenData screenData = screenDataRepository.findTop1ByUserIdOrderBySubmitDateAsc(id);
        pendingDeleteInfo.firstDeploymentAccessed = (screenData != null) ? screenData.getSubmitDate() : null;
        pendingDeleteInfo.totalDeploymentsAccessed = tagRepository.countDistinctUserIdAndTagValueByEventTag(id, "compileDate");
        pendingDeleteInfo.totalPageLoads = tagRepository.countDistinctUserIdAndDateByEventTag(id, "compileDate");
        pendingDeleteInfo.totalStimulusResponses = stimulusResponseRepository.countDistinctUserIdRecords(id);
        final Participant participantFirst = participantRepository.findTop1ByUserIdOrderBySubmitDateAsc(id);
        pendingDeleteInfo.participantsFirstSeen = (participantFirst != null) ? participantFirst.getSubmitDate() : null;
        final Participant participantLast = participantRepository.findTop1ByUserIdOrderBySubmitDateDesc(id);
        pendingDeleteInfo.participantsLastSeen = (participantLast != null) ? participantLast.getSubmitDate() : null;
        pendingDeleteInfo.sessionFirstSeen = tagRepository.findFirstSessionAccess(id);
        pendingDeleteInfo.sessionLastSeen = tagRepository.findLastSessionAccess(id);
        pendingDeleteInfo.totalMediaResponses = audioDataRepository.countByUserId(id);
        model.addAttribute("pendingDeleteInfo", pendingDeleteInfo);

        if (allowDelete) {
            if (requiredCheckbox1.equals(providedCheckbox1) && requiredCheckbox2.equals(providedCheckbox2)) {
//                final String screenName = "administration system";
//                final String eventTag = (deleteAudio) ? "delete participant audio" : "delete participant data";
//                final int eventMs = 0;
                final Date deletionDate = new java.util.Date();
                final String remoteAddr = request.getRemoteAddr();
                final int lastIndexOf = remoteAddr.lastIndexOf(".");
                final String deletionAddr = (lastIndexOf > 0) ? remoteAddr.substring(0, lastIndexOf) + ".0" : "";
                // todo: IPv6 is not handled at this stage but it should be striped to 80 bits when added
                // log the ip address that requested this action
//                final TagData deletionLogEntry = new TagData("participantdelete", screenName, eventTag, deletionAddr, eventMs, tagDate);
//                this.tagRepository.save(deletionLogEntry);
                final DataDeletionLog dataDeletionLog = new DataDeletionLog();
                dataDeletionLog.setDeletionDate(deletionDate);
                dataDeletionLog.setDeletionAddr(deletionAddr);

                // delete the audio
                this.audioDataRepository.deleteByUserId(id);
                dataDeletionLog.totalMediaResponses = pendingDeleteInfo.totalMediaResponses;
                if (deleteAudio) {
                    model.addAttribute("deletionSuccess", true);
                    model.addAttribute("dataDeletionLog", dataDeletionLog);
                } else if (deleteAll) {
                    participantRepository.deleteByUserId(id);
                    screenDataRepository.deleteByUserId(id);
                    tagPairRepository.deleteByUserId(id);
                    tagRepository.deleteByUserId(id);
                    timeStampRepository.deleteByUserId(id);
                    stimulusResponseRepository.deleteByUserId(id);
                    dataDeletionLog.firstDeploymentAccessed = pendingDeleteInfo.firstDeploymentAccessed;
                    dataDeletionLog.totalDeploymentsAccessed = pendingDeleteInfo.totalDeploymentsAccessed;
                    dataDeletionLog.totalPageLoads = pendingDeleteInfo.totalPageLoads;
                    dataDeletionLog.totalStimulusResponses = pendingDeleteInfo.totalStimulusResponses;
                    dataDeletionLog.participantsFirstSeen = pendingDeleteInfo.participantsFirstSeen;
                    dataDeletionLog.participantsLastSeen = pendingDeleteInfo.participantsLastSeen;
                    dataDeletionLog.sessionFirstSeen = pendingDeleteInfo.sessionFirstSeen;
                    dataDeletionLog.sessionLastSeen = pendingDeleteInfo.sessionLastSeen;
                    model.addAttribute("dataDeletionLog", dataDeletionLog);
                    model.addAttribute("deletionSuccess", true);
                } else {
                    model.addAttribute("deletionSuccess", false);
                }
                dataDeletionLogRepository.save(dataDeletionLog);
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
}
