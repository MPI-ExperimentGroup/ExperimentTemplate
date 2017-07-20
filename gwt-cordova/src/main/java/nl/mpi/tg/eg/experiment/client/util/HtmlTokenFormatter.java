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
package nl.mpi.tg.eg.experiment.client.util;

import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.service.GroupScoreService;

/**
 * @since Jul 19, 2017 3:34:18 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HtmlTokenFormatter {

    final GroupScoreService groupParticipantService;
    final UserData userData;

    public HtmlTokenFormatter(GroupScoreService groupParticipantService, UserData userData) {
        this.groupParticipantService = groupParticipantService;
        this.userData = userData;
    }

    public String formatString(String inputString) {
        String replacedTokensString = inputString;
        while (replacedTokensString.contains("</channelLoop>")) {
            final int channelLoopStart = replacedTokensString.indexOf("<channelLoop>");
            final int channelLoopEnd = replacedTokensString.indexOf("</channelLoop>");
            String channelLoopString = replacedTokensString.substring(channelLoopStart, channelLoopEnd + "</channelLoop>".length());
            System.out.println("channelLoopString:" + channelLoopString);
            String channelLoopStringOutput = "";
            for (String channel : groupParticipantService.getChannelScoreKeys()) {
                channelLoopStringOutput += channelLoopString.replaceAll("<channelLabel>", channel).replaceAll("<channelScore>", groupParticipantService.getChannelScore(channel)).replaceAll("<channelLoop>", "").replaceAll("</channelLoop>", "");
            }
            replacedTokensString = replacedTokensString.replace(channelLoopString, channelLoopStringOutput);
        }
        replacedTokensString = replacedTokensString.replace("<groupScore>", groupParticipantService.getGroupScore());
        replacedTokensString = replacedTokensString.replaceAll("<channelScore>", groupParticipantService.getChannelScore());
        replacedTokensString = replacedTokensString.replaceAll("<playerScore>", Integer.toString(userData.getCurrentScore()));
        replacedTokensString = replacedTokensString.replaceAll("<playerBestScore>", Double.toString(userData.getBestScore()));
        return replacedTokensString;
    }
}
