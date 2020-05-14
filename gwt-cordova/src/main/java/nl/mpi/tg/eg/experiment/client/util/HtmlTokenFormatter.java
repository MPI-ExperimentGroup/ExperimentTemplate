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
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.service.GroupScoreService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
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
    final MetadataField[] metadataFieldArray;
    final TimerService timerService;
    final Stimulus currentStimulus;

    public HtmlTokenFormatter(final Stimulus currentStimulus, final LocalStorage localStorage, final GroupScoreService groupParticipantService, final UserData userData, final TimerService timerService, final MetadataField[] metadataFieldArray) {
        this.localStorage = localStorage;
        this.groupParticipantService = groupParticipantService;
        this.userData = userData;
        this.timerService = timerService;
        this.currentStimulus = currentStimulus;
        this.metadataFieldArray = metadataFieldArray;
    }

    public String formatReplaceString(final String dataLogFormat, final String replacementRegex) {
        final String dataLogString = formatString(dataLogFormat);
        final String replacementRegexString = formatString(replacementRegex);
        System.out.println(replacementRegexString);
        System.out.println(dataLogString);
        String resultString = "";
        RegExp regExp = RegExp.compile(replacementRegexString);
        MatchResult matcher = regExp.exec(dataLogString);
        if (matcher != null) {
            for (int groupIndex = 1; groupIndex < matcher.getGroupCount(); groupIndex++) {
                resultString += matcher.getGroup(groupIndex);
            }
        }
        System.out.println(resultString);
        return resultString;
    }

    public String formatDDMMYYYCurrentDate() {
        // we cannot use com.google.gwt.i18n.client.DateTimeFormat.parseStrict(getValue()); and we are using a predefined date format
        final Date currentDate = new Date();
        final String returnString
                = ((currentDate.getDate() <= 9) ? "0" : "") + currentDate.getDate()
                + "/" + ((currentDate.getMonth() + 1 <= 9) ? "0" : "") + (currentDate.getMonth() + 1)
                + "/" + (1900 + currentDate.getYear());
        return returnString;
    }

    public Date parseDDMMYYYDate(String inputString) throws EvaluateTokensException {
        // we cannot use com.google.gwt.i18n.client.DateTimeFormat.parseStrict(getValue()); and we are using a predefined date format
        final String[] dateParts = inputString.replaceAll("[\"\\(\\)]", "").split("/");
        if (dateParts.length != 3) {
            throw new EvaluateTokensException("invalid date:" + inputString);
        }
        final Date currentDate = new Date();
        currentDate.setYear(Integer.parseInt(dateParts[2]));
        currentDate.setMonth(Integer.parseInt(dateParts[1]));
        currentDate.setDate(Integer.parseInt(dateParts[0]));
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        return currentDate;
    }

    public String evaluateTokensString(String inputString) throws EvaluateTokensException {
        final String formatedString = formatString(inputString);
        return evaluateResolve(formatedString.replaceAll("\\s", ""));
    }

    public Number evaluateTokensNumber(String inputString) throws EvaluateTokensException {
        final String formatedString = formatString(inputString);
        try {
            return Double.parseDouble(evaluateResolve(formatedString.replaceAll("\\s", "")));
        } catch (NumberFormatException exception) {
            throw new EvaluateTokensException(exception.getMessage() + " " + inputString);
        }
    }

    private String evaluateResolve(String inputString) throws EvaluateTokensException {
        System.out.println(inputString);
        RegExp regExpGroupM = RegExp.compile("(daysBetween|length|random)(\\([^\\)\\(]*\\))");
        MatchResult matcherGroupM = regExpGroupM.exec(inputString);
        while (matcherGroupM != null) {
            if (matcherGroupM.getGroupCount() == 3) {
                final String methodMatch = matcherGroupM.getGroup(1);
                final String parameterMatch = matcherGroupM.getGroup(2);
                String resultValue = "";
                switch (methodMatch) {
                    case "length":
                        resultValue = Integer.toString(parameterMatch.length() - 4);
                        break;
                    case "daysBetween":
                        String[] parameterParts = parameterMatch.split(",");
                        if (parameterParts.length == 2) {
                            final String dateStringA = parameterParts[0];
                            final String dateStringB = parameterParts[1];
                            final Date dateA = parseDDMMYYYDate(dateStringA);
                            final Date dateB = parseDDMMYYYDate(dateStringB);
                            long diffMs = dateB.getTime() - dateA.getTime();
                            resultValue = Long.toString(diffMs / (1000 * 60 * 60 * 24));
                        } else {
                            throw new EvaluateTokensException("unsupported match parameters:" + matcherGroupM.getGroup(0));
                        }
                        break;
                    case "random":
                        String[] parameterValues = parameterMatch.replaceAll("[\"\\(\\)]", "").split(",");
                        if (parameterValues.length == 1) {
//                            final int randomNumberOrigin = Integer.parseInt(parameterValues[0]);
                            final int randomNumberBound = Integer.parseInt(parameterValues[0]);
                            resultValue = Integer.toString(new Random().nextInt(randomNumberBound));
                        } else {
                            throw new EvaluateTokensException("unsupported match parameters:" + matcherGroupM.getGroup(0));
                        }
                        break;
                    default:
                        throw new EvaluateTokensException("unsupported match:" + matcherGroupM.getGroup(0));
                }
                inputString = inputString.replace(methodMatch + parameterMatch, resultValue);
            } else {
                throw new EvaluateTokensException(inputString);
            }
            matcherGroupM = regExpGroupM.exec(inputString);
        }
        RegExp regExpGroup = RegExp.compile("(\\([^\\)\\(]*\\))");
        MatchResult matcherGroup = regExpGroup.exec(inputString);
        while (matcherGroup != null) {
            for (int groupIndex = 1; groupIndex < matcherGroup.getGroupCount(); groupIndex++) {
                final String groupString = matcherGroup.getGroup(groupIndex);
                inputString = inputString.replace(groupString, "" + evaluateResolve(groupString.substring(1, groupString.length() - 1)));
            }
            matcherGroup = regExpGroup.exec(inputString);
        }
        for (String operator : new String[]{"/", "\\*", "%", "-", "\\+", "<=", ">=", "<", ">", "==", "\\!="}) {
            RegExp regExpOperator = RegExp.compile("(^-|[^0-9.]-|)([0-9\\.]+)(" + operator + ")(-?)([0-9\\.]+)");
            boolean foundMatch = true;
            while (foundMatch) {
                foundMatch = false;
                inputString = inputString.replaceAll(operator + "--", operator);
                inputString = inputString.replaceAll("^--", "");
                MatchResult matcherOperator = regExpOperator.exec(inputString);
                if (matcherOperator != null) {
                    if (matcherOperator.getGroupCount() >= 6) {
                        foundMatch = true;
                        final String groupSignLeftAll = matcherOperator.getGroup(1);
                        final String groupSignLeft = (groupSignLeftAll.length() <= 1) ? groupSignLeftAll : groupSignLeftAll.substring(groupSignLeftAll.length() - 1);
                        final String groupValueLeft = matcherOperator.getGroup(2);
                        final String groupOperator = matcherOperator.getGroup(3);
                        final String groupSignRight = matcherOperator.getGroup(4);
                        final String groupValueRight = matcherOperator.getGroup(5);
                        final String resultValue;
                        switch (operator) {
                            case "/":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) / Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "\\*":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) * Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "\\+":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) + Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "-":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) - Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "%":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) % Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "<":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) < Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case ">":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) > Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "<=":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) <= Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case ">=":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) >= Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "==":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) == Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            case "\\!=":
                                resultValue = String.valueOf(Double.parseDouble(groupSignLeft + groupValueLeft) != Double.parseDouble(groupSignRight + groupValueRight));
                                break;
                            default:
                                throw new EvaluateTokensException(operator);
                        }
                        String groupString = groupSignLeft + groupValueLeft + groupOperator + groupSignRight + groupValueRight;
                        inputString = inputString.replace(groupString, "" + resultValue);
                        System.out.print(groupString);
                        System.out.print(" = ");
                        System.out.println(resultValue);
                        System.out.println(inputString);
                    }
                }
            }
        }
        for (String operator : new String[]{"==", "\\!=", "&&", "\\|\\|"}) {
            RegExp regExpOperator = RegExp.compile("(true|false)(" + operator + ")(true|false)");
            boolean foundMatch = true;
            while (foundMatch) {
                foundMatch = false;
                MatchResult matcherOperator = regExpOperator.exec(inputString);
                if (matcherOperator != null) {
                    if (matcherOperator.getGroupCount() >= 4) {
                        foundMatch = true;
                        final String groupBooleanLeft = matcherOperator.getGroup(1);
                        final String groupOperator = matcherOperator.getGroup(2);
                        final String groupBooleanRight = matcherOperator.getGroup(3);
                        final String resultValue;
                        switch (operator) {
                            case "==":
                                resultValue = String.valueOf(Boolean.parseBoolean(groupBooleanLeft) == Boolean.parseBoolean(groupBooleanRight));
                                break;
                            case "\\!=":
                                resultValue = String.valueOf(Boolean.parseBoolean(groupBooleanLeft) != Boolean.parseBoolean(groupBooleanRight));
                                break;
                            case "&&":
                                resultValue = String.valueOf(Boolean.parseBoolean(groupBooleanLeft) && Boolean.parseBoolean(groupBooleanRight));
                                break;
                            case "\\|\\|":
                                resultValue = String.valueOf(Boolean.parseBoolean(groupBooleanLeft) || Boolean.parseBoolean(groupBooleanRight));
                                break;
                            default:
                                throw new EvaluateTokensException(operator);
                        }
                        String groupString = groupBooleanLeft + groupOperator + groupBooleanRight;
                        inputString = inputString.replace(groupString, "" + resultValue);
                        System.out.print(groupString);
                        System.out.print(" = ");
                        System.out.println(resultValue);
                        System.out.println(inputString);
                    }
                }
            }
        }
        return inputString;
    }

    public String formatString(String inputString) {
        if (inputString == null) {
            return null;
        }
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
            replacedTokensString = replacedTokensString.replace("<groupOtherMemberCodes>", (allMemberCodes != null && memberCode != null) ? allMemberCodes.replace(memberCode, "").replaceAll("[,]+", ",").replaceAll(",$", "").replaceAll("^,", "") : "---");
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
        replacedTokensString = replacedTokensString.replaceAll("<userId>", userData.getUserId().toString());
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
        replacedTokensString = replacedTokensString.replaceAll("<currentDateDDMMYYYY>", formatDDMMYYYCurrentDate());
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
        if (metadataFieldArray != null) {
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
                            for (MetadataField metadataField : metadataFieldArray) {
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
                for (String ratingLabel : currentStimulus.getRatingLabels().split(",")) {
                    // note that these chars are escaped in the config2stimuli.xsl which is why we do not do a general url decode which would overly affect the users input
                    ratingLabel = ratingLabel.replaceAll("&#x7C;", "|");
                    ratingLabel = ratingLabel.replaceAll("&#x21;", "!");
                    ratingLabel = ratingLabel.replaceAll("&#x2E;", ".");
                    ratingLabel = ratingLabel.replaceAll("&#x3F;", "?");
                    ratingLabel = ratingLabel.replaceAll("&#x2B;", "+");
                    ratingLabel = ratingLabel.replaceAll("&#x2A;", "*");
                    ratingLabel = ratingLabel.replaceAll("&#x24;", "\\$");
                    ratingLabel = ratingLabel.replaceAll("&#x5E;", "^");
                    ratingLabel = ratingLabel.replaceAll("&#x28;", "(");
                    ratingLabel = ratingLabel.replaceAll("&#x29;", ")");
                    ratingLabel = ratingLabel.replaceAll("&#x7D;", "}");
                    ratingLabel = ratingLabel.replaceAll("&#x7B;", "{");
                    ratingLabel = ratingLabel.replaceAll("&#x5D;", "]");
                    ratingLabel = ratingLabel.replaceAll("&#x5B;", "[");
                    ratingLabel = ratingLabel.replaceAll("&#x2C;", ",");
                    ratingLabel = ratingLabel.replaceAll("&#x5C;", "\\\\");
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
