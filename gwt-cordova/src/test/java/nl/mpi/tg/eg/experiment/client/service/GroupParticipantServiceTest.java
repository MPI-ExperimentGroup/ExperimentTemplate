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
package nl.mpi.tg.eg.experiment.client.service;

import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;
import nl.mpi.tg.eg.experiment.client.sharedobjects.GroupMessageMatch;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Nov 21, 2016 4:48:36 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class GroupParticipantServiceTest {

    public GroupParticipantServiceTest() {
    }

    private void groupNetworkActivity(final StringBuilder stringBuilder, final GroupParticipantService instance, final String groupRole, final GroupMessageMatch groupMessageMatch) {
        instance.addGroupActivity(groupRole, groupMessageMatch, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
//                fail("The test case is a prototype."+groupRole);
                System.out.print(instance.getRequestedPhase());
                System.out.print("-");
                System.out.print(instance.getMemberCode());
                System.out.print("[");
                System.out.print(groupRole);
                System.out.println("]");
                stringBuilder.append(instance.getRequestedPhase());
                stringBuilder.append("-");
                stringBuilder.append(instance.getMemberCode());
                stringBuilder.append("[");
                stringBuilder.append(groupRole);
                stringBuilder.append("]\n");
            }
        });
    }

    /**
     * Test of addGroupActivity method, of class GroupParticipantService.
     */
    @Test
    public void testAddGroupActivity() {
        System.out.println("addGroupActivity");
        GroupParticipantService instance = new GroupParticipantService("userId", "screenId", "A,B,C,D,E,F,G,H", "A,B|C,D|E,F|G,H", new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
            }
        }, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
            }
        });
        final StringBuilder stringBuilder = new StringBuilder();
        groupNetworkActivity(stringBuilder, instance, "A,C,E,G:-:-:B,D,F,H:-:-", GroupMessageMatch.all);
        groupNetworkActivity(stringBuilder, instance, "-:A,C,E,G:-:-:B,D,F,H:-", GroupMessageMatch.all);
        groupNetworkActivity(stringBuilder, instance, "B,D,F,H:-:-:A,C,E,G:-:-", GroupMessageMatch.all);
        groupNetworkActivity(stringBuilder, instance, "-:B,D,F,H:-:-:A,C,E,G:-", GroupMessageMatch.all);
        groupNetworkActivity(stringBuilder, instance, "-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H", GroupMessageMatch.all);
        for (int phaseCounter = 0; phaseCounter < 10; phaseCounter++) {
            for (int memberIndex = 0; memberIndex < 8; memberIndex++) {
                instance.clearLastFiredListner();
                instance.handleGroupMessage("userId", "screenId", "userLabel", "groupId", "A,B,C,D,E,F,G,H", "A,B,C,D,E,F,G,H".split(",")[memberIndex], "stimulusId", "0", Integer.toString(phaseCounter), "messageString", Boolean.TRUE, "responseStimulusOptions", "responseStimulusId");
            }
        }
        assertEquals("0-A[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "0-B[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "0-C[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "0-D[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "0-E[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "0-F[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "0-G[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "0-H[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "1-A[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "1-B[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "1-C[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "1-D[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "1-E[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "1-F[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "1-G[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "1-H[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "2-A[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-B[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-C[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-D[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-E[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-F[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-G[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "2-H[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "3-A[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "3-B[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "3-C[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "3-D[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "3-E[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "3-F[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "3-G[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "3-H[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "4-A[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "4-B[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "4-C[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "4-D[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "4-E[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "4-F[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "4-G[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "4-H[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "5-A[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-B[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-C[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-D[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-E[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-F[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-G[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "5-H[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "6-A[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "6-B[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "6-C[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "6-D[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "6-E[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "6-F[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "6-G[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "6-H[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "7-A[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "7-B[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "7-C[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "7-D[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "7-E[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "7-F[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "7-G[-:A,C,E,G:-:-:B,D,F,H:-]\n"
                + "7-H[-:B,D,F,H:-:-:A,C,E,G:-]\n"
                + "8-A[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-B[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-C[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-D[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-E[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-F[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-G[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "8-H[-:-:A,B,C,D,E,F,G,H:-:-:A,B,C,D,E,F,G,H]\n"
                + "9-A[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "9-B[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "9-C[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "9-D[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "9-E[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "9-F[A,C,E,G:-:-:B,D,F,H:-:-]\n"
                + "9-G[B,D,F,H:-:-:A,C,E,G:-:-]\n"
                + "9-H[A,C,E,G:-:-:B,D,F,H:-:-]\n", stringBuilder.toString());
    }

    /**
     * Test of handleGroupMessage method, of class GroupParticipantService.
     */
    @Test
    public void testHandleGroupMessage() {
//        System.out.println("handleGroupMessage");
//        String userId = "";
//        String screenId = "";
//        String userLabel = "";
//        String groupId = "";
//        String allMemberCodes = "";
//        String memberCode = "";
//        String stimulusId = "";
//        String stimulusIndex = "";
//        String requestedPhase = "";
//        String messageString = "";
//        Boolean groupReady = null;
//        GroupParticipantService instance = null;
//        instance.handleGroupMessage(userId, screenId, userLabel, groupId, allMemberCodes, memberCode, stimulusId, stimulusIndex, requestedPhase, messageString, groupReady);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
