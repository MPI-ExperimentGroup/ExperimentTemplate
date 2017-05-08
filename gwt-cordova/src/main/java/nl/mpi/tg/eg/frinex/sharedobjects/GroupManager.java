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

    private final HashMap<String, HashMap<GroupMessage, GroupMessage>> allMembersList = new HashMap<>();
    private final HashMap<GroupMessage, String> allMemberCodes = new HashMap<>();
    private final HashMap<GroupMessage, HashMap<String, Integer>> groupScores = new HashMap<>();
    private final HashMap<GroupMessage, String> stimuliLists = new HashMap<>();
    private final HashMap<GroupMessage, String> groupUUIDs = new HashMap<>();
    private final HashMap<GroupMessage, List<String>> unAllocatedMemberCodes = new HashMap<>();
    private final HashMap<GroupMessage, HashSet<String>> groupsMembers = new HashMap();
    GroupMessage lastGroupId = null;

    public boolean isGroupMember(GroupMessage groupMessage) {
        if (groupMessage.getGroupId() == null) {
            final GroupMessage lastMessage;
            final HashMap<GroupMessage, GroupMessage> userGroups = allMembersList.get(groupMessage.getUserId());
            if (userGroups != null) {
                lastMessage = userGroups.get(groupMessage);
            } else {
                lastMessage = null;
            }
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

    public void updateGroupScore(GroupMessage groupMessage) {
        if (!groupScores.containsKey(groupMessage)) {
            groupScores.put(groupMessage, new HashMap<String, Integer>());
        }
        final HashMap<String, Integer> groupScoresMap = groupScores.get(groupMessage);
        if (groupMessage.getMemberCode() != null && !groupMessage.getMemberCode().isEmpty()) {
            groupScoresMap.put(groupMessage.getMemberCode(), groupMessage.getMemberScore());
        }
        int groupScore = 0;
        int groupChannelScore = 0;
        String channelMembers = "";
        for (String channel : groupMessage.getGroupCommunicationChannels().split("\\|")) // check if the communication channel applies to this group member
        {
            if (channel.contains(groupMessage.getMemberCode())) {
                channelMembers += channel;
            }
        }
        for (String currentMember : groupScoresMap.keySet()) {
            Integer currentScore = groupScoresMap.get(currentMember);
            groupScore += currentScore;
            if (channelMembers.contains(currentMember)) {
                groupChannelScore += currentScore;
            }
        }
        groupMessage.setGroupScore(groupScore);
        groupMessage.setChannelScore(groupChannelScore);
    }

    public boolean updateChannelMessageIfOutOfDate(GroupMessage incomingMessage, final GroupMessage storedMessage) {
        GroupMessage mostRecentChannelMessage = incomingMessage;
        boolean resendingOldMessage = false;
        // keep the member id and the channel data before updating the message to the most recent seen by the server, if found
        final String memberCode = storedMessage.getMemberCode();
        final String groupCommunicationChannels = incomingMessage.getGroupCommunicationChannels();
        System.out.println("groupCommunicationChannels: " + groupCommunicationChannels);
        System.out.println("memberCode: " + memberCode);
        for (String channel : groupCommunicationChannels.split("\\|")) // check if the communication channel applies to this group member
        {
            if (channel.contains(memberCode)) {
                for (final HashMap<GroupMessage, GroupMessage> userGroups : allMembersList.values()) {
                    GroupMessage membersLastMessage = userGroups.get(incomingMessage);
                    if (membersLastMessage != null) {
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
                                    if (membersLastMessage.getExpectedRespondents() != null && membersLastMessage.getActualRespondents() != null
                                            && membersLastMessage.getExpectedRespondents().equals(membersLastMessage.getActualRespondents())) {
                                        System.out.println("all ExpectedRespondents replied");
                                        // only resend a message if all expected respondants have replied                                        
                                        mostRecentChannelMessage = membersLastMessage;
                                        resendingOldMessage = true;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        // preserve the user id and member code of the requesting participant, even if the message is a resend from a different participant
        incomingMessage.setOriginMemberCode(mostRecentChannelMessage.getMemberCode());
        incomingMessage.setStimulusId(mostRecentChannelMessage.getStimulusId());
        incomingMessage.setStimulusIndex(mostRecentChannelMessage.getStimulusIndex());
        incomingMessage.setRequestedPhase(mostRecentChannelMessage.getRequestedPhase());
        incomingMessage.setMessageString(mostRecentChannelMessage.getMessageString());
        incomingMessage.setResponseStimulusId(mostRecentChannelMessage.getResponseStimulusId());
        incomingMessage.setExpectedRespondents(mostRecentChannelMessage.getExpectedRespondents());
        return resendingOldMessage;
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
        // even if the message has the wrong stimuli list, the participant will still be added to the group, but the correct stimili list will be returned so as to trigger the client to reload its stimili.
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
        groupMessage.setStimuliList(stimuliLists.get(groupMessage));
        System.out.println("groupMessage: ");
        System.out.println(groupMessage.getAllMemberCodes());
        System.out.println(groupMessage.getGroupCommunicationChannels());
        System.out.println(groupMessage.getGroupId());
        System.out.println(groupMessage.getGroupUUID());
        System.out.println(groupMessage.getMemberCode());
        System.out.println(groupMessage.getOriginMemberCode());
        System.out.println(groupMessage.getRequestedPhase());
        System.out.println(groupMessage.getExpectedRespondents());
        System.out.println(groupMessage.getStimuliList());
        System.out.println(groupMessage.getStimulusId());
        System.out.println(groupMessage.getStimulusIndex());
        System.out.println(groupMessage.getUserId());
        System.out.println(groupMessage.getScreenId());
    }

    public void updateResponderListForMessagePhase(GroupMessage storedMessage) {
        final Set<String> respondingMemberCodes = new HashSet<>();
        if (storedMessage.getOriginMemberCode() != null) {
            respondingMemberCodes.add(storedMessage.getOriginMemberCode());
        }
        for (final HashMap<GroupMessage, GroupMessage> userGroups : allMembersList.values()) {
            GroupMessage lastGroupMessage = userGroups.get(storedMessage);
            if (lastGroupMessage != null && lastGroupMessage.getOriginMemberCode() != null) {
//          if the group matches
                if (storedMessage.equals(lastGroupMessage)) {
                    // this is the same phase
                    if (storedMessage.getRequestedPhase().equals(lastGroupMessage.getRequestedPhase())) {
                        respondingMemberCodes.add(lastGroupMessage.getOriginMemberCode());
                    }
                }
            }
        }
        List sortedRespondingMemberCodes = new ArrayList(respondingMemberCodes);
        Collections.sort(sortedRespondingMemberCodes);
        String respondingMemberCodesString = String.join(",", sortedRespondingMemberCodes);
        storedMessage.setActualRespondents(respondingMemberCodesString);
        for (final HashMap<GroupMessage, GroupMessage> userGroups : allMembersList.values()) {
            GroupMessage lastGroupMessage = userGroups.get(storedMessage);
//          if the group matches
            if (storedMessage.equals(lastGroupMessage)) {
                // this is the same phase
                if (storedMessage.getRequestedPhase().equals(lastGroupMessage.getRequestedPhase())) {
                    lastGroupMessage.setActualRespondents(respondingMemberCodesString);
                }
            }
        }
    }

    public void setUsersLastMessage(GroupMessage storedMessage) {
        if (!allMembersList.containsKey(storedMessage.getUserId())) {
            allMembersList.put(storedMessage.getUserId(), new HashMap<GroupMessage, GroupMessage>());
        }
        allMembersList.get(storedMessage.getUserId()).put(storedMessage, storedMessage);
    }

    public GroupMessage getGroupMember(GroupMessage incomingMessage) {
        HashMap<GroupMessage, GroupMessage> userGroups = allMembersList.get(incomingMessage.getUserId());
        if (userGroups != null) {
            return userGroups.get(incomingMessage);
        } else {
            return null;
        }
    }
}
