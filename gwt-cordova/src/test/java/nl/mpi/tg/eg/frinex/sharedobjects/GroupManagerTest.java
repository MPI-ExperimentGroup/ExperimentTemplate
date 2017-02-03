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

    /**
     * Test of isGroupMember method, of class GroupManager.
     */
    @Test
    public void testIsGroupMember() {
        System.out.println("isGroupMember");
        GroupMessage groupMessage = new GroupMessage("groupId", "screenId", "1");
        groupMessage.setAllMemberCodes("A,B,C,D,E,F");
        GroupManager instance = new GroupManager();

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
        GroupMessage groupMessage1 = new GroupMessage("groupId", "screenId", "1");
        groupMessage1.setAllMemberCodes("A,B,C,D,E,F,G,H");
        GroupManager instance = new GroupManager();

        assertEquals(false, instance.isGroupReady(groupMessage1));
        instance.isGroupMember(groupMessage1);
        instance.addGroupMember(groupMessage1);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage2 = new GroupMessage("groupId", "screenId", "2");
        instance.isGroupMember(groupMessage2);
        instance.addGroupMember(groupMessage2);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage3 = new GroupMessage("groupId", "screenId", "3");
        instance.isGroupMember(groupMessage3);
        instance.addGroupMember(groupMessage3);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage4 = new GroupMessage("groupId", "screenId", "4");
        instance.isGroupMember(groupMessage4);
        instance.addGroupMember(groupMessage4);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage9 = new GroupMessage("groupIdOther", "screenId", "9");
        groupMessage9.setAllMemberCodes("A,B,C,D,E,F");
        instance.isGroupMember(groupMessage9);
        instance.addGroupMember(groupMessage9);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage5 = new GroupMessage("groupId", "screenId", "5");
        instance.isGroupMember(groupMessage5);
        instance.addGroupMember(groupMessage5);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage6 = new GroupMessage("groupId", "screenId", "6");
        instance.isGroupMember(groupMessage6);
        instance.addGroupMember(groupMessage6);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage7 = new GroupMessage("groupId", "screenId", "7");
        instance.isGroupMember(groupMessage7);
        instance.addGroupMember(groupMessage7);
        assertEquals(false, instance.isGroupReady(groupMessage1));
        final GroupMessage groupMessage8 = new GroupMessage("groupId", "screenId", "8");
        instance.isGroupMember(groupMessage8);
        instance.addGroupMember(groupMessage8);
        assertEquals(true, instance.isGroupReady(groupMessage1));
    }

    /**
     * Test of updateChannelMessageIfOutOfDate method, of class GroupManager.
     */
    @Test
    public void testUpdateChannelMessageIfOutOfDate() {
        System.out.println("updateChannelMessageIfOutOfDate");
        GroupMessage incomingMessage = null;
        GroupMessage storedMessage = null;
        GroupManager instance = new GroupManager();
        GroupMessage expResult = null;
        GroupMessage result = instance.updateChannelMessageIfOutOfDate(incomingMessage, storedMessage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroupMember method, of class GroupManager.
     */
    @Test
    public void testAddGroupMember() {
        System.out.println("addGroupMember");
        GroupMessage groupMessage = null;
        GroupManager instance = new GroupManager();
        instance.addGroupMember(groupMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupMember method, of class GroupManager.
     */
    @Test
    public void testGetGroupMember() {
        System.out.println("getGroupMember");
        String memberId = "";
        GroupManager instance = new GroupManager();
        GroupMessage expResult = null;
        GroupMessage result = instance.getGroupMember(memberId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
