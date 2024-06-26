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
package nl.mpi.tg.eg.experiment.client.service;

/**
 * @since Feb 17, 2017 11:27:51 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TestData {

    private void processMessage(GroupParticipantService instance, String userId, String screenId, String userLabel, String groupId, String allMemberCodes, String communicationChannels, String memberCode, String originMemberCode, String stimulusId, String stimulusIndex, String stimuliList, String requestedPhase, String messageString, boolean groupReady, String responseStimulusId, String expectedRespondents, String actualRespondents, String groupUUID) {
        instance.handleGroupMessage(userId, screenId, userLabel, groupId, allMemberCodes, memberCode, originMemberCode, expectedRespondents, actualRespondents, stimulusId, stimulusIndex, stimuliList, instance.getRequestedPhase().toString(), requestedPhase, messageString, groupReady, responseStimulusId, "groupScore", "channelScore", groupUUID);
    }

    public void processTestMessagesRound1(GroupParticipantService instance) {

        processMessage(instance, "mockuser-0", "Round_1", "A : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "A", "A", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "A", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-1", "Round_1", "B : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "B", "B", "4-7:large", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-4", "Round_1", "C : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "4-6:small", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-7", "Round_1", "D : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "D", "D", "1-5:large", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-6", "Round_1", "E : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "4-6:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-3", "Round_1", "F : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "F", "F", "3-7:small", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-5", "Round_1", "G : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "4-3:large", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", false, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-2", "Round_1", "H : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "H", "H", "3-4:large", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "null", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-1", "Round_1", "B : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "B", "B", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-4", "Round_1", "C : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-7", "Round_1", "D : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "D", "D", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-6", "Round_1", "E : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-3", "Round_1", "F : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "F", "F", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "B,D,F,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-2", "Round_1", "H : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "H", "H", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-5", "Round_1", "G : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "0", "null", true, "null", "A,C,E,G", "A,B,C,D,E,F,G,H", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-0", "Round_1", "A : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "A", "A", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-4", "Round_1", "C : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-5", "Round_1", "G : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C,G", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-6", "Round_1", "E : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C,E,G", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-3", "Round_1", "F : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "F", "F", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "2", "", true, "2-2:medium", "B,D,F,H", "F", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");

        processMessage(instance, "mockuser-0", "Round_1", "A : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "A", "A", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-4", "Round_1", "C : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "2-4:medium", "0", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-5", "Round_1", "G : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C,G", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
        processMessage(instance, "mockuser-6", "Round_1", "E : robot group at 11:51:31 AM", "robot group at 11:51:31 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "2-2:medium", "1", "2-4:medium-2-2:medium-4-6:medium-1-3:small-2-3:small-4-6:large-2-7:medium-1-1:medium", "1", "qqq", true, "null", "A,C,E,G", "A,C,E,G", "b42b3a02-905e-49d6-b84c-82e6da0a6cee");
    }

    public void processTestMessages(GroupParticipantService instance) {
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "4-3:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "2-7:medium", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "1-7:medium", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "3-7:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "3-2:medium", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "3-5:large", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", false, "null", "null", "A,B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "3-6:large", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", true, "null", "null", "null", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-2:small", "0", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "1", "qqq", true, "null", "A", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "1", "null", true, "null", "A", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C,D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C,D,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C,D,E,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "2", "", true, "null", "B,C,D,E,F,G,H", "B,C,D,E,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "3-6:small", "1", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "3", "qqq", true, "null", "B", "B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "3", "null", true, "null", "B", "B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C,D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C,D,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C,D,E,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "4", "", true, "null", "A,C,D,E,F,G,H", "A,C,D,E,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "2-1:small", "2", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "5", "qqq", true, "null", "C", "C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "5", "null", true, "null", "C", "C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B,D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B,D,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B,D,E,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B,D,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "6", "", true, "null", "B,A,D,E,F,G,H", "A,B,D,E,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "3-4:large", "3", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "7", "qqq", true, "null", "D", "D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "7", "null", true, "null", "D", "D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B,C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B,C,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B,C,E,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B,C,E,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "A,B,C,E,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "1-6:large", "4", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "9", "qqq", true, "null", "E", "E", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "9", "null", true, "null", "E", "E", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B,C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B,C,D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B,C,D,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B,C,D,F,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "10", "", true, "null", "B,C,D,A,F,G,H", "A,B,C,D,F,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "4-1:small", "5", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "11", "qqq", true, "null", "F", "F", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-6", "Round_0", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "F", "F", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "11", "null", true, "null", "F", "F", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-0", "Round_0", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-1", "Round_0", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-2", "Round_0", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "C", "C", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B,C", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-3", "Round_0", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "D", "D", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B,C,D", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B,C,D,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-5", "Round_0", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "E", "E", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B,C,D,E,G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-7", "Round_0", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "H", "H", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "12", "", true, "null", "B,C,D,E,A,G,H", "A,B,C,D,E,G,H", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "4-3:medium", "6", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "13", "qqq", true, "null", "G", "G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_0", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "G", "G", "2-4:medium", "7", "2-2:small-3-6:small-2-1:small-3-4:large-1-6:large-4-1:small-4-3:medium-2-4:medium", "13", "null", true, "null", "G", "G", "28d03a89-8adb-477c-b2dc-fc26ba05feb8");
        processMessage(instance, "mockuser-4", "Round_1", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "A", "A", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "A", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-2", "Round_1", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "B", "B", "2-1:large", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-1", "Round_1", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "2-6:large", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-3", "Round_1", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "D", "D", "3-5:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-0", "Round_1", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "2-5:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-5", "Round_1", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "F", "F", "4-7:large", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-6", "Round_1", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "4-3:small", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", false, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-7", "Round_1", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "H", "H", "4-3:large", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "null", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-2", "Round_1", "B : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "B", "B", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-1", "Round_1", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-3", "Round_1", "D : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "D", "D", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "B,D,F,H", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-0", "Round_1", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-5", "Round_1", "F : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "F", "F", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-6", "Round_1", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "A,C,E,G", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-7", "Round_1", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "H", "H", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "0", "null", true, "null", "null", "A,B,C,D,E,F,G,H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-0", "Round_1", "E : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "E", "E", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "1", "qqq", true, "null", "A,C,E,G", "E", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-1", "Round_1", "C : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "C", "C", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "1", "qqq", true, "null", "A,C,E,G", "C,E", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-4", "Round_1", "A : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "A", "A", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "1", "qqq", true, "null", "A,C,E,G", "A,C,E", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-6", "Round_1", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "3-3:medium", "0", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "1", "qqq", true, "null", "A,C,E,G", "A,C,E,G", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-6", "Round_1", "G : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "G", "G", "4-2:small", "1", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "1", "null", true, "null", "A,C,E,G", "A,C,E,G", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
        processMessage(instance, "mockuser-7", "Round_1", "H : robot group at 11:24:29 AM", "robot group at 11:24:29 AM", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", "H", "H", "4-2:small", "1", "3-3:medium-4-2:small-4-6:large-1-5:medium-3-5:large-2-2:small-4-2:medium-1-4:large", "2", "", true, "null", "B,D,F,H", "H", "f19643ac-06ec-4f2f-bae6-eb19f0103857");
    }

    public String[][] getExpectedData() {
        return new String[][]{
            {"mockuser-0", "mockuser-0\n"
                + "findingMembersListener\n"
                + "screenResetRequestListener\n"
                + "stimulusSyncListener\n"
                + "findingMembersListener\n"
                + "stimulusSyncListener\n"
                + "0-A-producer[A:-:B:-:C:-:D:-:E:-:F:-:G:-:H:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "1-A-producer wait[-:A:-:B:-:C:-:D:-:E:-:F:-:G:-:H]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "2-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "3-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "4-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "5-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "6-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "7-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "8-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "9-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "10-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "11-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "12-A-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "13-A-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
            },
            {"mockuser-7", "mockuser-7\n"
                + "screenResetRequestListener\n"
                + "stimulusSyncListener\n"
                + "stimulusSyncListener\n"
                + "0-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "1-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "2-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "3-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "4-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "5-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "6-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "7-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "8-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "9-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "10-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "11-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "12-H-guesser wait[B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "13-H-guesser[-:B,C,D,E,F,G,H:-:A,C,D,E,F,G,H:-:B,A,D,E,F,G,H:-:B,C,A,E,F,G,H:-:B,C,D,A,F,G,H:-:B,C,D,E,A,G,H:-:B,C,D,E,F,A,H:-:B,C,D,E,F,G,A]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
            },};
    }

    public String[][] getExpectedDataRound1() {
        return new String[][]{
            {"mockuser-1", "mockuser-1\n"
                + "findingMembersListener\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "0-B-guesser wait[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "stimulusSyncListener\n"
                + "1-B-guesser[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "synchroniseStreamingPhase\n"
                + "groupInfoChangeListener\n"
                + "groupInfoChangeListener\n"
            }};
    }
}
