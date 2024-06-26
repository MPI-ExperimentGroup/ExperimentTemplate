/*
 * Copyright (C) 2022 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.model;

/**
 * @since 26 October 2022 16:40 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum TokenText {

    /* TODO: document the stimulusResponse usage and underscores
    &lt;stimulusResponse__stimulusFreeText1&gt;
    <stimulusResponse__GroupID> note that there are two _ because you can use the stimulusID like <stimulusResponse_StimulusID_GroupID>
    this <stimulusResponse__GroupID> will give you the current stimulus
    */

    // TODO: fill out the all of the tokens with examples and expected result 
    groupScore("Outputs the current score of the group in a group experiment.", "::groupScore::", "8"),
    channelScore("Outputs the current score of the current channel in a group experiment.", "::channelLoop::::channelScore::, ::/channelLoop::", "6, 2, "),
    channelLabel("Outputs the label of the current channel in a group experiment.", "::channelLoop::::channelLabel::\n::/channelLoop::", "A-B\nC-D\n"),
    channelLoop("Loops over the channels in a group experiment.", "::channelLoop::channel ::channelLabel:: has ::channelScore:: points\n::/channelLoop::", "channel A-B has 6 points\nchannel C-D has 2 points\n"),
    groupMemberCode("Outputs the member code of the local member in a group experiment.", "::groupMemberCode::", "A"),
    groupRequestedPhase("Outputs the phase that should currently be displayed in a group experiment.", "::groupRequestedPhase::", "1"),
    formatDateTime("Formats the date and time according to the string provided.", "::formatDateTime_yyyy MM dd, HH:mm::", "2023 12 25, 08:30"),
    mediaLength("Outputs the last known length in seconds of the media associated with the mediaId. This value may be less accurate until the media has finished playing or recording.", "::mediaLength_recording01::", "12.5"),
    stimulusId("Outputs the current stimulus Id.", "::stimulusId::", "d1e286"),
    stimulusLabel("Outputs the current stimulus label.", "::stimulusLabel::", "One"),
    stimulusCode("Outputs the current stimulus code.", "::stimulusCode::", "code"),
    stimulusCorrectResponses("Outputs the current stimulus correct responses.", "::stimulusCorrectResponses::", "Correct|Responses"),
    stimulusRatingLabels("Outputs the current stimulus rating labels.", "::stimulusRatingLabels::", "Rating,Labels"),
    stimulusRatingLabel_("Outputs the current stimulus rating label N.", "::stimulusRatingLabel_1::", "Labels"),
    stimulusAudio("Outputs the current stimulus audio.", "::stimulusAudio::", "Audio"),
    stimulusVideo("Outputs the current stimulus video.", "::stimulusVideo::", "Video"),
    stimulusImage("Outputs the current stimulus image.", "::stimulusImage::", "Image"),
    stimulusTags("Outputs the current stimulus tags.", "::stimulusTags::", "tag_number,tag_interesting"),
    stimulusPauseMs("Outputs the current stimulus pause ms.", "::stimulusPauseMs::", "0"),
    stimulusResponse("Outputs the stored responses of a specified stimulus Id of the current stimulus when the Id is blank. If a response group is provided that response value will be shown. For example a stimulus with the Id of D123 that has the response of ImageViewed and a group response of ButtonThreeClicked would output the following.", "::stimulusResponse_D123:: and ::stimulusResponse_D123_Step01::", "ImageViewed and ButtonThreeClicked");
    
//::groupAllMemberCodes::
//
//::groupAssignedMemberCodes::
//
//::groupOtherMemberCodes::
//
//::channelOtherMemberCodes::
//
//::groupActiveChannel::
//
//::groupCommunicationChannels::
//
//::groupMessageString::
//
//::groupId::
//
//::groupUUID::
//
//::groupUserLabel::
//
//::channelScore::
//
//::userId::
//
//::playerScore::
//
//::playerErrors::
//
//::playerPotentialScore::
//
//::playerErrorStreak::
//
//::playerCorrectStreak::
//
//::playerMaxScore::
//
//::playerMaxErrors::
//
//::playerMaxPotentialScore::
//
//::playerMaxErrorStreak::
//
//::playerMaxCorrectStreak::
//
//::playerTotalScore::
//
//::playerTotalErrors::
//
//::playerTotalPotentialScore::
//
//::playerGamesPlayed::
//
//::currentDateDDMMYYYY has options for "D" "M" "Y"
//
//::formatDateTime_
//
//::" + timerId + "::
//
//::stimulusResponse
//
//::completionCode::
//
//::metadataField
//

// rating_
// stimulusRatingLabel_
    public final String usageDescription;
    public final String exampleUsage;
    public final String exampleResult;

    // public static final String tokenStartChar = '<';
    // public static final String tokenStopChar = '>';

    private TokenText(String usageDescription, String exampleUsage, String exampleResult) {
        this.usageDescription = usageDescription;
        this.exampleUsage = exampleUsage;
        this.exampleResult = exampleResult;
    }
}
