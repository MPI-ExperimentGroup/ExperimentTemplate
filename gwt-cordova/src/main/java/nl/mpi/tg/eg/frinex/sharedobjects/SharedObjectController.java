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

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @since Oct 26, 2016 2:03:15 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Controller
public class SharedObjectController {

    private final static GroupManager GROUP_MANAGER = new GroupManager();
//    private String currentGroupId = null;

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
        if (GROUP_MANAGER.isGroupMember(groupMessage.getGroupId(), groupMessage.getUserId())) {
            storedMessage = GROUP_MANAGER.getGroupMember(groupMessage.getUserId());
            storedMessage.setStimulusId(groupMessage.getStimulusId());
            storedMessage.setScreenId(groupMessage.getScreenId());
            storedMessage.setStimulusIndex(groupMessage.getStimulusIndex());
            storedMessage.setRequestedPhase(groupMessage.getRequestedPhase());
            storedMessage.setMessageString(groupMessage.getMessageString());
            storedMessage.setAllMemberCodes(groupMessage.getAllMemberCodes());
//            storedMessage.setGroupId(groupMessage.getGroupId());
//            storedMessage.setRequestedPhase(groupMessage.getRequestedPhase());
        } else {
            GROUP_MANAGER.addGroupMember(groupMessage);
            storedMessage = groupMessage;
        }
//        if (storedMessage.getGroupId() == null || storedMessage.getGroupId().isEmpty()) {
//            if (currentGroupId == null) {
//                currentGroupId = UUID.randomUUID().toString();
//                storedMessage.setGroupId(currentGroupId);
//            }
//        }
//        if (unAllocatedMemberCodes.containsKey(currentGroupId) && unAllocatedMemberCodes.get(currentGroupId).isEmpty()) {
////                currentGroupId = UUID.randomUUID().toString();
//        }
        storedMessage.setGroupReady(GROUP_MANAGER.isGroupReady(storedMessage.getGroupId(), storedMessage.getUserId()));        
        return storedMessage;
    }
}
