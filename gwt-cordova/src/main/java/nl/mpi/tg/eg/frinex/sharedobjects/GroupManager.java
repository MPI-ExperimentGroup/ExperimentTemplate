/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @since Nov 18, 2016 2:53:20 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupManager {

    private final HashMap<String, GroupMessage> allMembersList = new HashMap<>();
    private final HashMap<String, String> allMemberCodes = new HashMap<>();
    private final HashMap<String, List<String>> unAllocatedMemberCodes = new HashMap<>();
    private final HashMap<String, HashSet<String>> groupsMembers = new HashMap();

    public boolean isGroupMember(final String groupId, final String memberId) {
        if (!groupsMembers.containsKey(groupId)) {
            groupsMembers.put(groupId, new HashSet<String>());
        }
        return groupsMembers.get(groupId).contains(memberId);
    }

    public boolean isGroupReady(final String groupId, final String memberId) {
        final List<String> membercodes = unAllocatedMemberCodes.get(groupId);
        if (membercodes == null) {
            return false;
        }
        return membercodes.isEmpty() && groupsMembers.get(groupId).contains(memberId);
    }

    public void addGroupMember(GroupMessage groupMessage) {
        if (allMemberCodes.get(groupMessage.getGroupId()) == null) {
            allMemberCodes.put(groupMessage.getGroupId(), groupMessage.getAllMemberCodes());
            unAllocatedMemberCodes.put(groupMessage.getGroupId(), new ArrayList<>(Arrays.asList(groupMessage.getAllMemberCodes().split(","))));
        }// else if (allMemberCodes.get(groupMessage.getGroupId()).equals(groupMessage.getAllMemberCodes())) {
        final List<String> availableMemberCodes = unAllocatedMemberCodes.get(groupMessage.getGroupId());
        if (groupMessage.getMemberCode() != null
                && !groupMessage.getMemberCode().isEmpty()
                && availableMemberCodes.contains(groupMessage.getMemberCode())) {
            // if the member code is provided and it is available then allocate it
            availableMemberCodes.remove(groupMessage.getMemberCode());
        } else if (!availableMemberCodes.isEmpty()) {
            groupMessage.setMemberCode(availableMemberCodes.remove(0));
        }
        groupMessage.setUserLabel(groupMessage.getMemberCode() + " : " + groupMessage.getGroupId());
        allMembersList.put(groupMessage.getUserId(), groupMessage);
        groupsMembers.get(groupMessage.getGroupId()).add(groupMessage.getUserId());
        //}
    }

    public GroupMessage getGroupMember(final String memberId) {
        return allMembersList.get(memberId);
    }
}
