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

    private synchronized GroupMessage updateGroupData(GroupMessage incomingMessage) {
        if (incomingMessage == null) {
            System.out.println("incomingMessage == null");
            return null;
        }
        System.out.println("InMessage:" + incomingMessage.toString());
//        final String incomingStimuliList = incomingMessage.getStimuliList();
//        System.out.println("incomingMessage: ");
//        System.out.println(incomingMessage.getAllMemberCodes());
//        System.out.println(incomingMessage.getGroupCommunicationChannels());
//        System.out.println(incomingMessage.getGroupId());
//        System.out.println(incomingMessage.getGroupUUID());
//        System.out.println(incomingMessage.getMemberCode());
//        System.out.println(incomingMessage.getOriginMemberCode());
//        System.out.println(incomingMessage.getRequestedPhase());
//        System.out.println(incomingMessage.getActualRespondents());
//        System.out.println(incomingMessage.getExpectedRespondents());
//        System.out.println(incomingStimuliList);
//        System.out.println(incomingMessage.getStimulusId());
//        System.out.println(incomingMessage.getStimulusIndex());
//        System.out.println(incomingMessage.getUserId());
//        System.out.println(incomingMessage.getScreenId());
        if (!GROUP_MANAGER.isGroupMember(incomingMessage)) {
            if (!GROUP_MANAGER.addGroupMember(incomingMessage)) {
                return incomingMessage; // reject the request
            }
        }
        incomingMessage.setOriginMemberCode(incomingMessage.getMemberCode());
        GROUP_MANAGER.updateGroupData(incomingMessage);
        GROUP_MANAGER.updateResponderListForMessagePhase(incomingMessage);
        GROUP_MANAGER.updateGroupScore(incomingMessage);
        incomingMessage.setGroupReady(GROUP_MANAGER.isGroupReady(incomingMessage));
        // if the message is a reconnect request then send the last message for that chanel
        GroupMessage resendMessage = GROUP_MANAGER.updateChannelMessageIfOutOfDate(incomingMessage);
        if (resendMessage == null) {
            GROUP_MANAGER.setUsersLastMessage(incomingMessage);
            System.out.println("OutMessage:" + incomingMessage.toString());
            return incomingMessage;
        } else {
            // if the message is a reconnect but the stimuli index is greater and the stimulus id == null then trigger an end of stimulus screen change
            if (incomingMessage.getStimulusIndex() > resendMessage.getStimulusIndex() && incomingMessage.getStimulusId() == null && incomingMessage.getExpectedRespondents() == "") {
                System.out.println("trigger an end of stimulus screen change:" + incomingMessage.toString());
//                incomingMessage.setExpectedRespondents(incomingMessage.getMemberCode().toString());
                return incomingMessage;
            } else {
                System.out.println("ResendMessage:" + resendMessage.toString());
                return resendMessage;
            }
        }
    }
}
