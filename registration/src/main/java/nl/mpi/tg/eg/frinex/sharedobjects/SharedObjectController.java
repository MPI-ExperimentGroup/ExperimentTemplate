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
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @since Oct 26, 2016 2:03:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class SharedObjectController {

    private final HashMap<String, GroupMessage> groupMemberList = new HashMap<>();
    private ArrayList<String> unAllocatedMemberCodes = null;
    private String allMemberCodes;

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
        if (groupMemberList.containsKey(groupMessage.getUserId())) {
            storedMessage = groupMemberList.get(groupMessage.getUserId());
            storedMessage.setStimulusId(groupMessage.getStimulusId());
            storedMessage.setMessageString(groupMessage.getMessageString());
        } else {
            if (unAllocatedMemberCodes == null) {
                unAllocatedMemberCodes = new ArrayList<>(Arrays.asList(groupMessage.getAllMemberCodes().split(",")));
                allMemberCodes = groupMessage.getAllMemberCodes();
            }
            if (!unAllocatedMemberCodes.isEmpty()) {
                groupMessage.setMemberCode(unAllocatedMemberCodes.remove(0));
                groupMessage.setUserLabel(groupMessage.getMemberCode() + groupMessage.getUserId());
                groupMemberList.put(groupMessage.getUserId(), groupMessage);
            }
            storedMessage = groupMessage;
        }
        groupMessage.setGroupReady(unAllocatedMemberCodes.isEmpty());
        groupMessage.setAllMemberCodes(allMemberCodes);
        return storedMessage;
    }
}
