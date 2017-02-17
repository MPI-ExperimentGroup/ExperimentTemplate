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
public class SharedObjectControllerTest {

    private GroupMessage processMessage(SharedObjectController instance,
            String userId,
            String screenId,
            String userLabel,
            String groupId,
            String allMemberCodes,
            String communicationChannels,
            String memberCode,
            String originMemberCode,
            String stimulusId,
            String stimulusIndex,
            String stimuliList,
            String requestedPhase,
            String messageString,
            boolean groupReady,
            String responseStimulusId,
            String expectedRespondents,
            String actualRespondents,
            String groupUUID) throws Exception {
        GroupMessage groupMessage = new GroupMessage(groupId, screenId, userId);
        groupMessage.setAllMemberCodes(allMemberCodes);
        groupMessage.setGroupCommunicationChannels(communicationChannels);
        groupMessage.setRequestedPhase(Integer.parseInt(requestedPhase));
        return instance.getGroupData(groupMessage);
    }

    /**
     * Test of getGroupData method, of class SharedObjectController.
     */
    @Test
    public void testGetGroupData() throws Exception {
        System.out.println("getGroupData");
        SharedObjectController instance = new SharedObjectController();

        GroupMessage result = processMessage(instance, "testuser-0", "Round_0", "A : robot group at 5:36:59 PM", "robot group at 5:36:59 PM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "4-1:medium", "7", "3-2:large-2-7:medium-3-1:medium-1-5:medium-1-2:small-3-7:large-3-2:medium-4-1:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "G", "155557d6-83a5-4fb6-af3c-67fd40635b75");
        assertEquals("robot group at 5:36:59 PM", result.getGroupId());
        result = processMessage(instance, "testuser-0", "Round_0", "A : robot group at 5:36:59 PM", "robot group at 5:36:59 PM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "4-1:medium", "7", "3-2:large-2-7:medium-3-1:medium-1-5:medium-1-2:small-3-7:large-3-2:medium-4-1:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "G", "155557d6-83a5-4fb6-af3c-67fd40635b75");
        assertEquals("robot group at 5:36:59 PM", result.getGroupId());
        result = processMessage(instance, "testuser-0", "Round_0", "A : robot group at 10:23:42 AM", "robot group at 10:23:42 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-7:small", "0", "2-7:small-4-5:small-3-1:large-1-5:medium-3-6:medium-3-1:medium-3-4:large-1-7:medium", "0", "null", false, "null", "null", "A", "0502002b-84d6-4e9c-86e9-1b81a7be81ca");
        assertEquals("robot group at 10:23:42 AM", result.getGroupId());
        result = processMessage(instance, "testuser-1", "Round_0", "B : robot group at 10:23:42 AM", "robot group at 10:23:42 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "B", "B", "2-1:medium", "0", "2-7:small-4-5:small-3-1:large-1-5:medium-3-6:medium-3-1:medium-3-4:large-1-7:medium", "0", "null", false, "null", "null", "null", "0502002b-84d6-4e9c-86e9-1b81a7be81ca");
        assertEquals("robot group at 10:23:42 AM", result.getGroupId());
        result = processMessage(instance, "testuser-0", "Round_0", "A : robot group at 5:36:59 PM", "robot group at 5:36:59 PM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "4-1:medium", "7", "3-2:large-2-7:medium-3-1:medium-1-5:medium-1-2:small-3-7:large-3-2:medium-4-1:medium", "8", "", true, "null", "B,C,A,E,F,G,H", "G", "155557d6-83a5-4fb6-af3c-67fd40635b75");
        assertEquals("robot group at 5:36:59 PM", result.getGroupId());
        result = processMessage(instance, "testuser-0", "Round_0", "A : robot group at 10:23:42 AM", "robot group at 10:23:42 AM", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H", "A", "A", "2-7:small", "0", "2-7:small-4-5:small-3-1:large-1-5:medium-3-6:medium-3-1:medium-3-4:large-1-7:medium", "0", "null", false, "null", "null", "A", "0502002b-84d6-4e9c-86e9-1b81a7be81ca");
        assertEquals("robot group at 10:23:42 AM", result.getGroupId());
    }

}
