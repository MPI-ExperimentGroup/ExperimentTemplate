/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.util;

import com.google.gwt.json.client.JSONObject;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.service.GroupScoreService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.MetadataFieldProvider;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jul 19, 2017 3:34:18 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HtmlTokenFormatter {

    final GroupScoreService groupParticipantService;
    final LocalStorage localStorage;
    final UserData userData;
    final MetadataFieldProvider metadataFieldProvider;
    final TimerService timerService;
    final Stimulus currentStimulus;

    public HtmlTokenFormatter(final Stimulus currentStimulus, final LocalStorage localStorage, final GroupScoreService groupParticipantService, final UserData userData, final TimerService timerService, final MetadataFieldProvider metadataFieldProvider) {
        this.localStorage = localStorage;
        this.groupParticipantService = groupParticipantService;
        this.userData = userData;
        this.timerService = timerService;
        this.currentStimulus = currentStimulus;
        this.metadataFieldProvider = metadataFieldProvider;
    }

    public String formatString(String inputString) {
        String replacedTokensString = inputString;
        // todo: add a stimuli loop tag
        if (groupParticipantService != null) {
            while (replacedTokensString.contains("</channelLoop>")) {
                final int channelLoopStart = replacedTokensString.indexOf("<channelLoop>");
                final int channelLoopEnd = replacedTokensString.indexOf("</channelLoop>");
                String channelLoopString = replacedTokensString.substring(channelLoopStart, channelLoopEnd + "</channelLoop>".length());
                System.out.println("channelLoopString:" + channelLoopString);
                String channelLoopStringOutput = "";
                for (String channel : groupParticipantService.getChannelScoreKeys()) {
                    channelLoopStringOutput += channelLoopString.replaceAll("<channelLabel>", channel).replaceAll("<channelScore>", groupParticipantService.getChannelScore(channel)).replaceAll("<channelLoop>", "").replaceAll("</channelLoop>", "");
                }
                replacedTokensString = replacedTokensString.replace(channelLoopString, channelLoopStringOutput);
            }
            final String groupScore = groupParticipantService.getGroupScore();
            replacedTokensString = replacedTokensString.replace("<groupScore>", (groupScore != null) ? groupScore : "---");
            final String memberCode = groupParticipantService.getMemberCode();
            replacedTokensString = replacedTokensString.replace("<groupMemberCode>", (memberCode != null) ? memberCode : "---");
            final String allMemberCodes = groupParticipantService.getAllMemberCodes();
            final String activeChannel = groupParticipantService.getActiveChannel();
            replacedTokensString = replacedTokensString.replace("<groupAllMemberCodes>", (allMemberCodes != null) ? allMemberCodes : "---");
            replacedTokensString = replacedTokensString.replace("<groupOtherMemberCodes>", (allMemberCodes != null) ? allMemberCodes.replace(memberCode, "").replaceAll("[,]+", ",").replaceAll(",$", "").replaceAll("^,", "") : "---");
            replacedTokensString = replacedTokensString.replace("<channelOtherMemberCodes>", (activeChannel != null) ? activeChannel.replace(memberCode, "").replaceAll("[,]+", ",").replaceAll(",$", "").replaceAll("^,", "") : "---");
            replacedTokensString = replacedTokensString.replace("<groupActiveChannel>", (activeChannel != null) ? activeChannel : "---");
            final String groupCommunicationChannels = groupParticipantService.getGroupCommunicationChannels();
            replacedTokensString = replacedTokensString.replace("<groupCommunicationChannels>", (groupCommunicationChannels != null) ? groupCommunicationChannels : "---");
            final String messageString = groupParticipantService.getMessageString();
            replacedTokensString = replacedTokensString.replace("<groupMessageString>", (messageString != null) ? messageString : "---");
            final String groupId = groupParticipantService.getGroupId();
            replacedTokensString = replacedTokensString.replace("<groupId>", (groupId != null) ? groupId : "---");
            final String userLabel = groupParticipantService.getUserLabel();
            replacedTokensString = replacedTokensString.replace("<groupUserLabel>", (userLabel != null) ? userLabel : "---");
            final String channelScore = groupParticipantService.getChannelScore();
            replacedTokensString = replacedTokensString.replaceAll("<channelScore>", (channelScore != null) ? channelScore : "---");
        }
        replacedTokensString = replacedTokensString.replaceAll("<playerScore>", Integer.toString(userData.getCurrentScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerErrors>", Integer.toString(userData.getPotentialScore() - userData.getCurrentScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerPotentialScore>", Integer.toString(userData.getPotentialScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerErrorStreak>", Integer.toString(userData.getErrorStreak()));
        replacedTokensString = replacedTokensString.replaceAll("<playerCorrectStreak>", Integer.toString(userData.getCorrectStreak()));
        replacedTokensString = replacedTokensString.replaceAll("<playerMaxScore>", Double.toString(userData.getMaxScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerMaxErrors>", Integer.toString(userData.getMaxErrors()));
        replacedTokensString = replacedTokensString.replaceAll("<playerMaxPotentialScore>", Integer.toString(userData.getMaxPotentialScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerMaxErrorStreak>", Integer.toString(userData.getMaxErrorStreak()));
        replacedTokensString = replacedTokensString.replaceAll("<playerMaxCorrectStreak>", Integer.toString(userData.getMaxCorrectStreak()));
        replacedTokensString = replacedTokensString.replaceAll("<playerTotalScore>", Integer.toString(userData.getTotalScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerTotalErrors>", Integer.toString(userData.getTotalPotentialScore() - userData.getTotalScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerTotalPotentialScore>", Integer.toString(userData.getTotalPotentialScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerGamesPlayed>", Integer.toString(userData.getGamesPlayed()));
        for (String timerId : timerService.getTimerIds()) {
            replacedTokensString = replacedTokensString.replaceAll("<" + timerId + ">", Integer.toString(timerService.getTimerValue(timerId)));
        }
        if (localStorage != null) {
            final String[] splitOnTokens = replacedTokensString.split("<stimulusResponse");
            if (splitOnTokens.length > 1) {
                String resultString = null;
                for (String splitPart : splitOnTokens) {
                    if (resultString == null) {
                        resultString = splitPart;
                    } else {
                        final String[] subPart = splitPart.split(">", 2);
                        final String uniqueId;
                        if (subPart[0].length() == 0) {
                            uniqueId = currentStimulus.getUniqueId(); // show the current stimulus response
                        } else {
                            uniqueId = subPart[0].substring(1); // extracted XXX from "<stimulusResponse_XXX"
                        }
                        final JSONObject storedJSONObject = localStorage.getStoredJSONObject(userData.getUserId(), uniqueId);
                        if (storedJSONObject != null) {
                            for (String key : storedJSONObject.keySet()) {
                                resultString += storedJSONObject.get(key).toString().replaceAll("(^\")|(\"$)", "");
                            }
                        }
                        resultString += subPart[1];
                    }
                }
                replacedTokensString = (resultString != null) ? resultString : replacedTokensString;
            }
            final String completionCode = localStorage.getCompletionCode(userData.getUserId());
            replacedTokensString = replacedTokensString.replaceAll("<completionCode>", (completionCode != null) ? completionCode : "");
        }
        // insert MetadataField tags
        if (metadataFieldProvider != null) {
            final String[] splitOnTokens = replacedTokensString.split("<metadataField");
            if (splitOnTokens.length > 1) {
                String resultString = null;
                for (String splitPart : splitOnTokens) {
                    if (resultString == null) {
                        resultString = splitPart;
                    } else {
                        final String[] subPart = splitPart.split(">", 2);
                        if (subPart[0].length() != 0) {
                            final String postName = subPart[0].substring(1); // extracted XXX from "<metadataField_XXX"
                            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                                if (metadataField.getPostName().equals(postName)) {
                                    resultString += userData.getMetadataValue(metadataField);
                                }
                            }
                        }
                        resultString += subPart[1];
                    }
                }
                replacedTokensString = (resultString != null) ? resultString : replacedTokensString;
            }
        }
        if (currentStimulus != null) {
            replacedTokensString = replacedTokensString.replaceAll("<stimulusId>", currentStimulus.getUniqueId());
            replacedTokensString = replacedTokensString.replaceAll("<stimulusLabel>", currentStimulus.getLabel());
            replacedTokensString = replacedTokensString.replaceAll("<stimulusCode>", currentStimulus.getCode());
            replacedTokensString = replacedTokensString.replaceAll("<code>", currentStimulus.getCode()); // migrated <code> from StimuliCodeFormatter and <code> should be deprecated
            if (currentStimulus.hasCorrectResponses()) {
                replacedTokensString = replacedTokensString.replaceAll("<stimulusCorrectResponses>", currentStimulus.getCorrectResponses());
            }
            if (currentStimulus.hasRatingLabels()) {
                replacedTokensString = replacedTokensString.replaceAll("<stimulusRatingLabels>", currentStimulus.getRatingLabels());
                int index = 0;
                for (final String ratingLabel : currentStimulus.getRatingLabels().split(",")) {
                    replacedTokensString = replacedTokensString.replace("<rating_" + index + ">", ratingLabel); // migrated <rating_XXX> from StimuliCodeFormatter <rating_XXX> and should be deprecated
                    replacedTokensString = replacedTokensString.replace("<stimulusRatingLabel_" + index + ">", ratingLabel);
                    index++;
                }
            }
            if (currentStimulus.hasAudio()) {
                replacedTokensString = replacedTokensString.replaceAll("<stimulusAudio>", currentStimulus.getAudio());
            }
            if (currentStimulus.hasVideo()) {
                replacedTokensString = replacedTokensString.replaceAll("<stimulusVideo>", currentStimulus.getVideo());
            }
            if (currentStimulus.hasImage()) {
                replacedTokensString = replacedTokensString.replaceAll("<stimulusImage>", currentStimulus.getImage());
            }
            replacedTokensString = replacedTokensString.replaceAll("<stimulusTags>", serialiseTags(currentStimulus.getTags()));
            replacedTokensString = replacedTokensString.replaceAll("<stimulusPauseMs>", Integer.toString(currentStimulus.getPauseMs()));
        }
        return replacedTokensString;
    }

    private String serialiseTags(List<?> tags) {
        String result = "";
        for (Object tag : tags) {
            if (!result.isEmpty()) {
                result += ",";
            }
            result += tag.toString();
        }
        return result;
    }
}
