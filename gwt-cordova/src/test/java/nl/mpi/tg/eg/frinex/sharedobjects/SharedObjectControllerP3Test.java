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
package nl.mpi.tg.eg.frinex.sharedobjects;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * @since Feb 3, 2017 3:36:50 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SharedObjectControllerP3Test {

    protected void processMessage(SharedObjectController instance,
            String InMessagegroupId, String InMessagescreenId, String InMessageallMemberCodes, String InMessagegroupCommunicationChannels, String InMessagememberCode, String InMessageoriginMemberCode,
            String InMessageexpectedRespondents, String InMessageactualRespondents, int InMessagestimulusIndex, int InMessageoriginPhase, int InMessagerequestedPhase, String InMessagemessageString,
            boolean InMessagegroupReady, int InMessagememberScore, int InMessagechannelScore, int InMessagegroupScore, int InMessageeventMs,
            String OutMessagegroupId, String OutMessagescreenId, String OutMessageallMemberCodes, String OutMessagegroupCommunicationChannels, String OutMessagememberCode, String OutMessageoriginMemberCode,
            String OutMessageexpectedRespondents, String OutMessageactualRespondents, int OutMessagestimulusIndex, int OutMessageoriginPhase, int OutMessagerequestedPhase, String OutMessagemessageString,
            boolean OutMessagegroupReady, int OutMessagememberScore, int OutMessagechannelScore, int OutMessagegroupScore, int OutMessageeventMs
    ) throws Exception {
        GroupMessage groupMessage = new GroupMessage(InMessagegroupId, InMessagescreenId, InMessagememberCode, InMessagememberCode);
        groupMessage.setAllMemberCodes(InMessageallMemberCodes);
        groupMessage.setGroupCommunicationChannels(InMessagegroupCommunicationChannels);
        groupMessage.setStimulusIndex(InMessagestimulusIndex);
        groupMessage.setStimuliList("StimuliList");
        groupMessage.setOriginPhase(InMessageoriginPhase);
        groupMessage.setRequestedPhase(InMessagerequestedPhase);
        groupMessage.setExpectedRespondents(InMessageexpectedRespondents);
        final GroupMessage result = instance.getGroupData(groupMessage);
        assertEquals(OutMessageoriginPhase, result.getOriginPhase().intValue());
        assertEquals(OutMessagerequestedPhase, result.getRequestedPhase().intValue());
        assertEquals(OutMessagestimulusIndex, result.getStimulusIndex().intValue());
//        if (result.isGroupReady()) {
//            assertTrue("ActualRespondents should contain InMessagememberCode", result.getActualRespondents().contains(InMessagememberCode));
//            if (lastScreenId.equals(result.getScreenId())) {
//                if (result.getOriginPhase() != null) {
//                    assertTrue(result.getOriginPhase() > 0);
//                }
//                lastPhase = result.getOriginPhase();
//            } else {
//                lastScreenId = result.getScreenId();
//                lastPhase = result.getOriginPhase();
//            }
//        }
    }

    /**
     * Test of getGroupData method, of class SharedObjectController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGroupData2() throws Exception {
        System.out.println("getGroupData2");
        SharedObjectController instance = new SharedObjectController();

        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 11, 35, 36, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 11, 35, 36, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 11, 35, 36, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 11, 35, 36, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 12, 36, 37, "etudfgt eae", true, 2, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 12, 36, 37, "etudfgt eae", true, 2, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 12, 37, 38, "etudfgt eae", true, 0, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 12, 37, 38, "etudfgt eae", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C,B,D", "C", 12, 38, 39, "", true, 2, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C,B,D", "C", 12, 38, 39, "", true, 2, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "C,D", 12, 38, 39, "", true, 0, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "C,D", 12, 38, 39, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A", 0, 0, 0, null, true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,B,C,D", 11, 35, 36, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 13, 39, 40, "etudfgt eae", true, 0, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 13, 39, 40, "etudfgt eae", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 12, 36, 37, "etudfgt eae", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 12, 36, 37, "etudfgt eae", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 13, 40, 41, "etudfgt eae", true, 2, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 13, 40, 41, "etudfgt eae", true, 2, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C,B,D", "C", 13, 41, 42, "", true, 2, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C,B,D", "C", 13, 41, 42, "", true, 2, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "C,D", 13, 41, 42, "", true, 0, 2, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "C,D", 13, 41, 42, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 12, 37, 38, "etudfgt eae", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 12, 37, 38, "etudfgt eae", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 12, 38, 39, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 12, 38, 39, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 12, 38, 39, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 12, 38, 39, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 13, 39, 40, "etudfgt eae", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 13, 39, 40, "etudfgt eae", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 13, 40, 41, "etudfgt eae", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 13, 40, 41, "etudfgt eae", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 13, 41, 42, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 13, 41, 42, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "D", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 14, 43, 44, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 14, 43, 44, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 14, 44, 45, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 14, 44, 45, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 14, 44, 45, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 14, 44, 45, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "D", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 15, 45, 46, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 15, 45, 46, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 15, 46, 47, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 15, 46, 47, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 15, 47, 48, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 15, 47, 48, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 15, 47, 48, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 15, 47, 48, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 14, 43, 44, "etudfgt eae", true, 1, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 14, 43, 44, "etudfgt eae", true, 1, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "D", 14, 44, 45, "", true, 1, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "D", 14, 44, 45, "", true, 1, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 14, 43, 44, null, true, 0, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 16, 48, 49, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 16, 48, 49, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 16, 49, 50, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 16, 49, 50, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "B", 16, 50, 51, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "B", 16, 50, 51, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "A,B,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "A,B,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 13, 40, 41, "etudfgt eae", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 16, 49, 50, "etudfgt eae", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 13, 41, 42, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A,C,D", 16, 49, 50, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 0, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B,C,D", 13, 41, 42, "", true, 0, 0, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "D", "A,C,B,D", "A,B,C,D", 14, 43, 44, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 14, 43, 44, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 14, 43, 44, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 14, 44, 45, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 14, 44, 45, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 14, 44, 45, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 14, 44, 45, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "D", "A,C,B,D", "A,B,C,D", 14, 43, 44, "", true, 0, 2, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 15, 45, 46, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 15, 45, 46, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,C", "C", 14, 42, 43, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 15, 46, 47, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 15, 46, 47, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 15, 47, 48, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 15, 47, 48, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 15, 47, 48, "", true, 0, 0, 0, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 15, 47, 48, "", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 14, 43, 44, "etudfgt eae", true, 1, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "B,D", "D", 14, 43, 44, "etudfgt eae", true, 1, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "D", 14, 44, 45, "", true, 1, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "D", "D", "A,C,B,D", "D", 14, 44, 45, "", true, 1, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 0, 0, 0, null, true, 0, 1, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "C", "C", "A,B,C,D", "C", 14, 43, 44, null, true, 0, 1, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 16, 48, 49, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 16, 48, 49, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 16, 49, 50, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 16, 49, 50, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "B", 16, 50, 51, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "B", 16, 50, 51, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "A,B,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "A,B,C", 16, 49, 50, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 0, 1, 2, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 0, 1, 2, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 0, 2, 3, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 0, 2, 3, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 0, 2, 3, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 0, 2, 3, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "B,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,B,C,D", "B,C", 0, 0, 0, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,B,C", 0, 0, 0, null, true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,B,C,D", "A,B,C", 0, 0, 0, null, true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 0, 1, 2, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 0, 1, 2, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 0, 2, 3, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 0, 2, 3, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 0, 2, 3, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 0, 2, 3, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 1, 3, 4, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 1, 3, 4, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 1, 4, 5, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 1, 4, 5, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 1, 5, 6, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 1, 5, 6, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 1, 5, 6, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 1, 5, 6, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 2, 6, 7, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 2, 6, 7, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 2, 7, 8, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 2, 7, 8, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 2, 8, 9, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 2, 8, 9, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 2, 8, 9, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 2, 8, 9, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 3, 9, 10, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 3, 9, 10, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 3, 10, 11, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 3, 10, 11, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 3, 11, 12, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 3, 11, 12, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 3, 11, 12, "", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 3, 11, 12, "", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 4, 12, 13, "etudfgt eae", true, 0, 0, 1, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 4, 12, 13, "etudfgt eae", true, 0, 0, 1, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 4, 13, 14, "etudfgt eae", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 4, 13, 14, "etudfgt eae", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 4, 14, 15, "", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 4, 14, 15, "", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 4, 14, 15, "", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 4, 14, 15, "", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 5, 15, 16, "etudfgt eae", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 5, 15, 16, "etudfgt eae", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 5, 16, 17, "etudfgt eae", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 5, 16, 17, "etudfgt eae", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 5, 17, 18, "", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 5, 17, 18, "", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 5, 17, 18, "", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 5, 17, 18, "", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 6, 18, 19, "etudfgt eae", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 6, 18, 19, "etudfgt eae", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 6, 19, 20, "etudfgt eae", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 6, 19, 20, "etudfgt eae", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 6, 20, 21, "", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 6, 20, 21, "", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 6, 20, 21, "", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 6, 20, 21, "", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 7, 21, 22, "etudfgt eae", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "B,D", "B", 7, 21, 22, "etudfgt eae", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 7, 22, 23, "etudfgt eae", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C", "A", 7, 22, 23, "etudfgt eae", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 7, 23, 24, "", true, 0, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "A", "A", "A,C,B,D", "A", 7, 23, 24, "", true, 0, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 7, 23, 24, "", true, 1, 1, 2, 0,
                "robot group at 4:11:52 PM", "Round_1___4", "A,B,C,D", "A,B|C,D", "B", "B", "A,C,B,D", "A,B", 7, 23, 24, "", true, 1, 1, 2, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A,B,C,D", "A", 0, 0, 0, "null", false, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A,B,C,D", "A", 0, 0, 0, "null", false, 0, 0, 0, 0);//processMessage
//processMessage(instance,"robot group at 2:27:38 PM","Round_0___4","A,B,C,D",
//        InMessage:GroupMessage{groupId=robot group at 2:27:38 PM, screenId=Round_0___4, allMemberCodes=A,B,C,D, groupCommunicationChannels=A,B,C,D, memberCode=C, 
//originMemberCode=null, expectedRespondents=A,B,C,D, actualRespondents=null, stimulusIndex=0, originPhase=0, requestedPhase=0, messageString=null, groupReady=false
//, memberScore=0, channelScore=0, groupScore=0, eventMs=0}
//processMessage(instance,"robot group at 2:27:38 PM","Round_0___4","A,B,C,D","A,B,C,D","C","C","A,B,C,D","A,B,C",0,0,0,"null",false,0,0,0,0,
//0,"null",false,"robot group at 2:27:38 PM","Round_0___4","A,B,C,D","A,B,C,D","B","B","A,B,C,D","A,B,C",0,0,0,"null",0,0,0,0);//processMessage
//false,0,0,0,0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 0, 0, "null", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "F", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0,
                //processMessage(instance,0,0,0);//processMessage
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "E", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A", "A", 0, 0, 1, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "G", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "G", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "H", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "H", "null", "A,B,C,D", "null", 0, 0, 0, "null", false, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "C", 0, 1, 2, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "C", 0, 1, 2, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
//processMessage(instance,"robot group at 2:27:38 PM","Round_0___4",
//        InMessage:GroupMessage{groupId=robot group at 2:27:38 PM, screenId=Round_0___4, allMemberCodes=A,B,C,D, groupCommunicationChannels=A,B,C,D, memberCode=B, 
//originMemberCode=null, expectedRespondents=A,B,C,D, actualRespondents=null, stimulusIndex=0, originPhase=1, requestedPhase=2, messageString=etudfgt eae, groupReady=false, 
//memberScore=0, channelScore=0, groupScore=0, eventMs=0}
//processMessage(instance,"robot group at 2:27:38 PM","Round_0___4","A,B,C,D","A,B,C,D","B","B","A,B,C,D","A,B,C",0,0,0,1,2,"etudfgt eae",true,0,0,0,0,
//"robot group at 2:27:38 PM","Round_0___4","A,B,C,D","A,B,C,D",0,0);//processMessage
//"B","B","A,B,C,D","A,B,C",0,1,2,"etudfgt eae",true,0,0,0,0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 1, 2, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "A,B,C,D", 0, 1, 2, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "B", "B", 1, 2, 3, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "B", "B", 1, 2, 3, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A,B,C,D", "A", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "B", "B", "B", 1, 2, 3, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "A,B,C,D", "B", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "B", "B", "A,B,C,D", "B", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "B,C", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "C", "C", "A,B,C,D", "B,C", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "B,C,D", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "D", "D", "A,B,C,D", "B,C,D", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0);//processMessage
        processMessage(instance, "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "A", "A,B,C,D", "A", 0, 0, 0, "null", true, 0, 0, 0, 0,
                "robot group at 2:27:38 PM", "Round_0___4", "A,B,C,D", "A,B,C,D", "A", "C", "A,B,C,D", "B,C,D", 1, 3, 4, "etudfgt eae", true, 0, 0, 0, 0);//processMessage

    }
}
