/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
import java.util.UUID;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @since Oct 26, 2016 2:03:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class SharedObjectController {

    private final HashMap<String, GroupMessage> allMembersList = new HashMap<>();
    private final HashMap<String, ArrayList<String>> unAllocatedMemberCodes = new HashMap<>();
    private final HashMap<String, String> allMemberCodes = new HashMap<>();
    private String currentGroupId = null;

    @MessageMapping("/shared")
    @SendTo("/shared/animation")
    public SharedData getSharedData(SharedData sharedData) throws Exception {
        return sharedData;
    }

    @MessageMapping("/group")
    @SendTo("/shared/group")
    public GroupMessage getGroupData(GroupMessage groupMessage) throws Exception {
        return updateGroupData(groupMessage);
    }

    private synchronized GroupMessage updateGroupData(GroupMessage groupMessage) {
        final GroupMessage storedMessage;
        if (allMembersList.containsKey(groupMessage.getUserId())) {
            storedMessage = allMembersList.get(groupMessage.getUserId());
            storedMessage.setStimulusId(groupMessage.getStimulusId());
            storedMessage.setMessageString(groupMessage.getMessageString());
        } else {
            allMembersList.put(groupMessage.getUserId(), groupMessage);
            storedMessage = groupMessage;
        }
        if (storedMessage.getGroupId() == null || storedMessage.getGroupId().isEmpty()) {
            if (currentGroupId == null) {
                currentGroupId = UUID.randomUUID().toString();
            }
            if (unAllocatedMemberCodes.containsKey(currentGroupId) && unAllocatedMemberCodes.get(currentGroupId).isEmpty()) {
                currentGroupId = UUID.randomUUID().toString();
            }
            storedMessage.setGroupId(currentGroupId);
        }
        if (allMemberCodes.get(storedMessage.getGroupId()) == null) {
            allMemberCodes.put(storedMessage.getGroupId(), storedMessage.getAllMemberCodes());
            unAllocatedMemberCodes.put(storedMessage.getGroupId(), new ArrayList<>(Arrays.asList(groupMessage.getAllMemberCodes().split(","))));
        }
        if (storedMessage.getMemberCode() == null || storedMessage.getMemberCode().isEmpty()) {
            storedMessage.setMemberCode(unAllocatedMemberCodes.get(storedMessage.getGroupId()).remove(0));
            storedMessage.setUserLabel(storedMessage.getMemberCode() + storedMessage.getUserId());
        }
        storedMessage.setGroupReady(unAllocatedMemberCodes.get(storedMessage.getGroupId()).isEmpty());
        storedMessage.setAllMemberCodes(allMemberCodes.get(storedMessage.getGroupId()));
        return storedMessage;
    }
}
