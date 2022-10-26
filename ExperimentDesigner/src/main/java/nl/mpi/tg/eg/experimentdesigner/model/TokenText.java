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
 * @since 26 Orctober 2022 16:40 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum TokenText {
    // TODO: fill out the all of the tokens with examples and expected result 
    channelLoop("loops over the channels in a group experiment", "<channelLoop><channelLabel></channelLoop>", "channel1, channel2, channel3"),
    channelLabel("ouputs the label of the current channel in a group experiment", "<channelLabel>", "channel1"),
    channelScore("ouputs the current score of the current channel in a group experiment", "<channelScore>", "5"),
    groupScore("ouputs the current score of the group in a group experiment", "<groupScore>", "25"),
    groupMemberCode("ouputs the member code of the local member in a group experiment", "<groupMemberCode>", "A"),
    groupRequestedPhase("ouputs the phase that should currently be displayed in a group experiment", "<groupRequestedPhase>", "1");

//<groupAllMemberCodes>
//
//<groupAsignedMemberCodes>
//
//<groupOtherMemberCodes>
//
//<channelOtherMemberCodes>
//
//<groupActiveChannel>
//
//<groupCommunicationChannels>
//
//<groupMessageString>
//
//<groupId>
//
//<groupUUID>
//
//<groupUserLabel>
//
//<channelScore>
//
//<userId>
//
//<playerScore>
//
//<playerErrors>
//
//<playerPotentialScore>
//
//<playerErrorStreak>
//
//<playerCorrectStreak>
//
//<playerMaxScore>
//
//<playerMaxErrors>
//
//<playerMaxPotentialScore>
//
//<playerMaxErrorStreak>
//
//<playerMaxCorrectStreak>
//
//<playerTotalScore>
//
//<playerTotalErrors>
//
//<playerTotalPotentialScore>
//
//<playerGamesPlayed>
//
//<currentDateDDMMYYYY has options for "D" "M" "Y"
//
//<formatDateTime_
//
//<" + timerId + ">
//
//<stimulusResponse
//
//<completionCode>
//
//<metadataField
//
//<stimulusId>
//
//<stimulusLabel>
//
//<stimulusCode>
//
//<stimulusCorrectResponses>
//
//<stimulusRatingLabels>
//
//<stimulusRatingLabel_" + index + ">
//
//<stimulusAudio>
//
//<stimulusVideo>
//
//<stimulusImage>
//
//<stimulusTags>
//
//<stimulusPauseMs>


    public final String usageDescription;
    public final String exampleUsage;
    public final String exampleResult;

    private TokenText(String usageDescription, String exampleUsage, String exampleResult) {
        this.usageDescription = usageDescription;
        this.exampleUsage = exampleUsage;
        this.exampleResult = exampleResult;
    }
}
