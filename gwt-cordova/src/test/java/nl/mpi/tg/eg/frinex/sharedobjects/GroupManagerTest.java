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

/**
 * @since Feb 3, 2017 3:37:11 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupManagerTest {

    public GroupManagerTest() {
    }

    private GroupMessage[] getGroupMembersArray() {
        final GroupMessage groupMessage1 = new GroupMessage("groupId", "screenId", "1");
        groupMessage1.setAllMemberCodes("A,B,C,D,E,F,G,H");
        final GroupMessage groupMessage2 = new GroupMessage("groupId", "screenId", "2");
        final GroupMessage groupMessage3 = new GroupMessage("groupId", "screenId", "3");
        final GroupMessage groupMessage4 = new GroupMessage("groupId", "screenId", "4");
        final GroupMessage groupMessage5 = new GroupMessage("groupId", "screenId", "5");
        final GroupMessage groupMessage6 = new GroupMessage("groupId", "screenId", "6");
        final GroupMessage groupMessage7 = new GroupMessage("groupId", "screenId", "7");
        final GroupMessage groupMessage8 = new GroupMessage("groupId", "screenId", "8");
        final GroupMessage groupMessage9 = new GroupMessage("groupIdOther", "screenId", "9");
        groupMessage9.setAllMemberCodes("A,B,C,D,E,F");
        return new GroupMessage[]{groupMessage1, groupMessage2, groupMessage3, groupMessage4, groupMessage5, groupMessage6, groupMessage7, groupMessage8, groupMessage9};
    }

    /**
     * Test of isGroupMember method, of class GroupManager.
     */
    @Test
    public void testIsGroupMember() {
        System.out.println("isGroupMember");
        GroupMessage groupMessage = new GroupMessage("groupId", "screenId", "1");
        groupMessage.setAllMemberCodes("A,B,C,D,E,F");
        GroupManager instance = new GroupManager();

        groupMessage.setStimuliList("4-7:medium-2-5:small-2-3:small-1-2:medium-1-4:small-2-1:large-1-6:small-1-7:small");
        assertEquals(false, instance.isGroupMember(groupMessage));
        instance.addGroupMember(groupMessage);
        assertEquals(true, instance.isGroupMember(groupMessage));
    }

    /**
     * Test of isGroupReady method, of class GroupManager.
     */
    @Test
    public void testIsGroupReady() {
        System.out.println("isGroupReady");
        GroupMessage[] groupMembers = getGroupMembersArray();
        GroupManager instance = new GroupManager();
        for (GroupMessage groupMessage : groupMembers) {
            groupMessage.setStimuliList("4-7:medium-2-5:small-2-3:small-1-2:medium-1-4:small-2-1:large-1-6:small-1-7:small");
        }
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[0]);
        instance.addGroupMember(groupMembers[0]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[1]);
        instance.addGroupMember(groupMembers[1]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[2]);
        instance.addGroupMember(groupMembers[2]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[3]);
        instance.addGroupMember(groupMembers[3]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[8]);
        instance.addGroupMember(groupMembers[8]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[4]);
        instance.addGroupMember(groupMembers[4]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[5]);
        instance.addGroupMember(groupMembers[5]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[6]);
        instance.addGroupMember(groupMembers[6]);
        assertEquals(false, instance.isGroupReady(groupMembers[0]));
        instance.isGroupMember(groupMembers[7]);
        instance.addGroupMember(groupMembers[7]);
        assertEquals(true, instance.isGroupReady(groupMembers[0]));
    }

    /**
     * Test of updateChannelMessageIfOutOfDate method, of class GroupManager.
     */
    @Test
    public void testUpdateChannelMessageIfOutOfDate() {
        System.out.println("updateChannelMessageIfOutOfDate");
        GroupManager instance = new GroupManager();
        final String groupCommunicationChannels = "A,B|C,D|E,F|G,H";
        final String[] meberCodes = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "X"};
        final GroupMessage storedMessage = new GroupMessage("groupId", "screenId", "5");
        storedMessage.setGroupCommunicationChannels(groupCommunicationChannels);
        storedMessage.setMemberCode("E");
        storedMessage.setRequestedPhase(12);
        int requestPhase = 0;
        for (GroupMessage groupMessage : getGroupMembersArray()) {
            groupMessage.setGroupCommunicationChannels(groupCommunicationChannels);
            groupMessage.setMemberCode(meberCodes[requestPhase]);
            groupMessage.setRequestedPhase(0);
            groupMessage.setMemberCode("E");
            final GroupMessage updateChannelMessageIfOutOfDate = instance.updateChannelMessageIfOutOfDate(groupMessage, groupMessage);
            instance.setUsersLastMessage(updateChannelMessageIfOutOfDate);
            assertEquals(0, updateChannelMessageIfOutOfDate.getRequestedPhase().intValue());
        }
        requestPhase = 0;
        for (GroupMessage groupMessage : getGroupMembersArray()) {
            groupMessage.setRequestedPhase(requestPhase + 3);
            groupMessage.setGroupCommunicationChannels(groupCommunicationChannels);
            groupMessage.setMemberCode(meberCodes[requestPhase]);
            final GroupMessage updateChannelMessageIfOutOfDate = instance.updateChannelMessageIfOutOfDate(groupMessage, groupMessage);
            instance.setUsersLastMessage(updateChannelMessageIfOutOfDate);
            assertEquals(requestPhase + 3, updateChannelMessageIfOutOfDate.getRequestedPhase().intValue());
            requestPhase++;
        }
        requestPhase = 0;
        for (GroupMessage groupMessage : getGroupMembersArray()) {
            groupMessage.setRequestedPhase(requestPhase);
            groupMessage.setGroupCommunicationChannels(groupCommunicationChannels);
            groupMessage.setMemberCode(meberCodes[requestPhase]);
            final GroupMessage updateChannelMessageIfOutOfDate = instance.updateChannelMessageIfOutOfDate(groupMessage, groupMessage);
            instance.setUsersLastMessage(updateChannelMessageIfOutOfDate);
            final int[] expectedValues = new int[]{4, 4, 6, 6, 8, 8, 10, 10, 8};
            assertEquals(expectedValues[requestPhase], updateChannelMessageIfOutOfDate.getRequestedPhase().intValue());
            requestPhase++;
        }
    }
}
