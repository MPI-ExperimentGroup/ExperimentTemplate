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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @since Nov 18, 2016 2:53:20 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupManager {

    private final HashMap<String, GroupMessage> allMembersList = new HashMap<>();
    private final HashMap<GroupMessage, String> allMemberCodes = new HashMap<>();
    private final HashMap<GroupMessage, String> stimuliLists = new HashMap<>();
    private final HashMap<GroupMessage, String> groupUUIDs = new HashMap<>();
    private final HashMap<GroupMessage, List<String>> unAllocatedMemberCodes = new HashMap<>();
    private final HashMap<GroupMessage, HashSet<String>> groupsMembers = new HashMap();
    GroupMessage lastGroupId = null;

    public boolean isGroupMember(GroupMessage groupMessage) {
        if (groupMessage.getGroupId() == null) {
            final GroupMessage lastMessage = allMembersList.get(groupMessage.getUserId());
            if (lastMessage != null /*&& lastMessage.getScreenId().equals(groupMessage.getScreenId())*/) {
                // preserve the group id when the browser window is refreshed without get parameters
                groupMessage.setGroupId(lastMessage.getGroupId());
            } else {
                final List<String> lastGroupMemberCodes = unAllocatedMemberCodes.get(lastGroupId);
                if (lastGroupMemberCodes == null || lastGroupMemberCodes.isEmpty()) {
                    lastGroupId = null;
                }
                if (lastGroupId == null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    lastGroupId = new GroupMessage("Auto group started:" + dateFormat.format(date), groupMessage.getScreenId(), null);
                }
                groupMessage.setGroupId(lastGroupId.getGroupId());
            }
        }
        return groupsMembers.containsKey(groupMessage) && groupsMembers.get(groupMessage).contains(groupMessage.getUserId());
    }

    public boolean isGroupReady(GroupMessage groupMessage) {
        final List<String> membercodes = unAllocatedMemberCodes.get(groupMessage);
        if (membercodes == null) {
            return false;
        }
        return membercodes.isEmpty() && groupsMembers.get(groupMessage).contains(groupMessage.getUserId());
    }

    public GroupMessage updateChannelMessageIfOutOfDate(GroupMessage incomingMessage, final GroupMessage storedMessage) {
        GroupMessage mostRecentChannelMessage = incomingMessage;
        // keep the member id and the channel data before updating the message to the most recent seen by the server, if found
        final String memberCode = storedMessage.getMemberCode();
        final String groupCommunicationChannels = incomingMessage.getGroupCommunicationChannels();
        System.out.println("groupCommunicationChannels: " + groupCommunicationChannels);
        System.out.println("memberCode: " + memberCode);
        for (String channel : groupCommunicationChannels.split("\\|")) // check if the communication channel applies to this group member
        {
            if (channel.contains(memberCode)) {
                for (GroupMessage membersLastMessage : allMembersList.values()) {
                    final String currentMemberCode = membersLastMessage.getMemberCode();
                    if (currentMemberCode != null) {
                        System.out.println("currentMemberCode: " + currentMemberCode);
                        if (channel.contains(currentMemberCode)) {
                            System.out.println("is common member");
                            System.out.println("mostRecentChannelMessage.getRequestedPhase():" + mostRecentChannelMessage.getRequestedPhase());
                            System.out.println("membersLastMessage.getRequestedPhase():" + membersLastMessage.getRequestedPhase());
                            if (mostRecentChannelMessage.getRequestedPhase() < membersLastMessage.getRequestedPhase()) {
                                System.out.println("other is more advanced than sent");
                                // select only the most recent message for any user in this channel
                                mostRecentChannelMessage = membersLastMessage;
                            }
                        }
                    }
                }

            }
        }
        return mostRecentChannelMessage;
    }

    public void addGroupMember(GroupMessage groupMessage) {
        if (!unAllocatedMemberCodes.containsKey(groupMessage)) {
            groupsMembers.put(groupMessage, new HashSet<String>());
            allMemberCodes.put(groupMessage, groupMessage.getAllMemberCodes());
            // keep the first stimuli list sent so that it can be set it into each subsequent message
            stimuliLists.put(groupMessage, groupMessage.getStimuliList());
            unAllocatedMemberCodes.put(groupMessage, new ArrayList<>(Arrays.asList(groupMessage.getAllMemberCodes().split(","))));
            // keeping a UUID for each group could help disambiguate when the server is restarted and the same group name reused
            groupUUIDs.put(groupMessage, UUID.randomUUID().toString());
        }
        final List<String> availableMemberCodes = unAllocatedMemberCodes.get(groupMessage);
        if (groupMessage.getStimuliList().equals(stimuliLists.get(groupMessage))) {
            // if the message has the wrong stimuli list then do not add them yet (this will keep the group in a not ready state until everyone has the same stimili list)
            if (groupMessage.getMemberCode() != null
                    && !groupMessage.getMemberCode().isEmpty()
                    && availableMemberCodes.contains(groupMessage.getMemberCode())) {
                // if the member code is provided and it is available then allocate it
                availableMemberCodes.remove(groupMessage.getMemberCode());
            } else if (!availableMemberCodes.isEmpty()) {
                groupMessage.setMemberCode(availableMemberCodes.remove(0));
            }
            groupMessage.setUserLabel(groupMessage.getMemberCode() + " : " + groupMessage.getGroupId());
            groupMessage.setGroupUUID(groupUUIDs.get(groupMessage));
            groupsMembers.get(groupMessage).add(groupMessage.getUserId());
        }
        groupMessage.setStimuliList(stimuliLists.get(groupMessage));
        System.out.println("groupMessage: ");
        System.out.println(groupMessage.getAllMemberCodes());
        System.out.println(groupMessage.getGroupCommunicationChannels());
        System.out.println(groupMessage.getGroupId());
        System.out.println(groupMessage.getGroupUUID());
        System.out.println(groupMessage.getMemberCode());
        System.out.println(groupMessage.getRequestedPhase());
        System.out.println(groupMessage.getUserId());
    }

    public void updateResponderListForMessagePhase(GroupMessage storedMessage) {
        final Set<String> respondingMemberCodes = new HashSet<>();
        if (storedMessage.getMemberCode() != null) {
            respondingMemberCodes.add(storedMessage.getMemberCode());
        }
        for (GroupMessage lastMessage : allMembersList.values()) {
            if (lastMessage.getMemberCode() != null) {
//          if the group matches
                if (storedMessage.equals(lastMessage)) {
                    // this is the same phase
                    if (storedMessage.getRequestedPhase().equals(lastMessage.getRequestedPhase())) {
                        respondingMemberCodes.add(lastMessage.getMemberCode());
                    }
                }
            }
        }
        List sortedRespondingMemberCodes = new ArrayList(respondingMemberCodes);
        Collections.sort(sortedRespondingMemberCodes);
        String respondingMemberCodesString = String.join(",", sortedRespondingMemberCodes);
        storedMessage.setActualRespondents(respondingMemberCodesString);
        for (GroupMessage lastMessage : allMembersList.values()) {
//          if the group matches
            if (storedMessage.equals(lastMessage)) {
                // this is the same phase
                if (storedMessage.getRequestedPhase().equals(lastMessage.getRequestedPhase())) {
                    lastMessage.setActualRespondents(respondingMemberCodesString);
                }
            }
        }
    }

    public void setUsersLastMessage(GroupMessage storedMessage) {
        allMembersList.put(storedMessage.getUserId(), storedMessage);
    }

    public GroupMessage getGroupMember(final String memberId) {
        return allMembersList.get(memberId);
    }
}
