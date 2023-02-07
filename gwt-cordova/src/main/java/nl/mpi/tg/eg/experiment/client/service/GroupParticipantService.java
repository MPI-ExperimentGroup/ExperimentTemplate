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

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.listener.GroupActivityListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Nov 8, 2016 1:47:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class GroupParticipantService implements GroupScoreService {

//    private final HashMap<String, ArrayList<CancelableStimulusListener>> selfActivityListeners = new HashMap<>();
//    private final HashMap<String, ArrayList<CancelableStimulusListener>> othersActivityListeners = new HashMap<>();
    private final HashMap<String, GroupActivityListener> activityListeners = new HashMap<>();
    private final String allMemberCodes;
    private final String groupCommunicationChannels;
//    private final CancelableStimulusListener endOfStimulusListener;
    private boolean isConnected = false;
    private List<GroupActivityListener> lastFiredListenerList = null;

    private final String userId;
    private final String screenId;
    private String userLabel = null;
//    private final String allMemberCodes = null;
    private String memberCode = null;
    private String activeChannel = null;
    private String groupId = null;
    private int phasesPerStimulus = 0;
    private String stimulusId = null;
    private String stimuliListLoaded;
    private String stimuliListGroup;
    private Integer requestedPhase = 0;
    private String messageSenderId = null;
    private String messageSenderMemberCode = null;
    private String messageString = null;
    private Boolean groupReady = false;
    private Boolean endOfStimuli = false;
//    private Boolean userIdMatches = false;
    private String responseStimulusOptions = null;
    private String responseStimulusId = null;
    private String groupScore = null;
    private final HashMap<String, String> channelScores = new HashMap<>();
    private String groupUUID = null;
    private String asignedMembers = null;
    private Timer groupKickTimer = null;

    public GroupParticipantService(final String userId, String screenId, String groupMembers, String groupCommunicationChannels, final int phasesPerStimulus, String stimuliListLoaded
    //            , CancelableStimulusListener endOfStimulusListener
    ) {
        this.userId = userId;
        this.allMemberCodes = groupMembers;
        this.groupCommunicationChannels = groupCommunicationChannels;
        this.phasesPerStimulus = phasesPerStimulus;
        this.screenId = screenId;
        this.stimuliListLoaded = stimuliListLoaded;
//        this.endOfStimulusListener = endOfStimulusListener;
    }

//    public void addGroupActivity(final String phaseMembers, final int requestedPhase, final CancelableStimulusListener activityListener) {
//
//        ArrayList<CancelableStimulusListener> currentSelfRoles = selfActivityListeners.get(phaseMembers);
//        ArrayList<CancelableStimulusListener> currentOthersRoles = othersActivityListeners.get(phaseMembers);
//        if (currentSelfRoles == null) {
//            currentSelfRoles = new ArrayList<>();
//            selfActivityListeners.put(phaseMembers, currentSelfRoles);
//        }
//        if (currentOthersRoles == null) {
//            currentOthersRoles = new ArrayList<>();
//            othersActivityListeners.put(phaseMembers, currentOthersRoles);
//        }
//        while (currentSelfRoles.size() < requestedPhase) {
//            currentSelfRoles.add(null);
//        }
//        while (currentOthersRoles.size() < requestedPhase) {
//            currentOthersRoles.add(null);
//        }
//        switch (groupMessageMatch) {
//            case self:
//                currentSelfRoles.add(requestedPhase, activityListener);
//                break;
//            case other:
//                currentOthersRoles.add(requestedPhase, activityListener);
//                break;
//            case all:
//                currentSelfRoles.add(requestedPhase, activityListener);
//                currentOthersRoles.add(requestedPhase, activityListener);
//                break;
//        }
//    }
    public void addGroupActivity(final GroupActivityListener activityListener) {
//        int phaseCount = activityListener.getGroupRole().split(":").length;
        // todo: phasesPerStimulus being based on the maximum phaseCount is a bit abitary and could perhaps be an explicit parameter in the experiment configuration
//        phasesPerStimulus = (phasesPerStimulus >= phaseCount) ? phasesPerStimulus : phaseCount;
        activityListeners.put(activityListener.getGroupRole(), activityListener);
    }

    protected void clearLastFiredListener() {
        this.lastFiredListenerList = null;
    }

    protected synchronized void handleGroupMessage(String userId, String screenId, String userLabel, String groupId, String allMemberCodes,
            String memberCode,
            String originMemberCode,
            String expectedRespondents,
            String actualRespondents,
            String stimulusId, String stimulusIndex, String stimuliListGroup, String originPhase, String requestedPhase,
            String messageString, Boolean groupReady, String responseStimulusId, String groupScore, String channelScore, String groupUUID) {
        final boolean originPhaseMatches = true; //this.requestedPhase.equals(Integer.parseInt(originPhase));
//        System.out.println("originPhaseMatches: " + originPhaseMatches + " requestedPhase: '" + this.requestedPhase + "' originPhase: '" + originPhase + "'");
        final boolean userIdMatches = this.userId.equals(userId);
        final boolean screenIdMatches = this.screenId.equals(screenId);
        boolean userGroupLabelUpdateNeeded = (userIdMatches && screenIdMatches) && (this.memberCode == null && memberCode != null);
        if (userIdMatches && screenIdMatches) {
            // handle direct messages
            this.userLabel = userLabel;
            this.memberCode = (groupUUID != null) ? memberCode : null; // only show the member code if the UUID has been assigned
            this.groupId = (groupUUID != null) ? groupId : null; // only show the friendly name if the UUID has been assigned
            this.asignedMembers = (groupUUID != null) ? actualRespondents : null; // only show the respondents if the UUID has been assigned
            if (groupUUID != null) {
                this.stimuliListGroup = stimuliListGroup;
            }
            if (!groupReady) {
                if (originMemberCode != null) {
                    groupFindingMembers();
                } else {
                    groupInitialisationError();
                }
            }
            if (this.stimuliListGroup != null && !this.stimuliListLoaded.equals(this.stimuliListGroup)) {
                // if the stimuli list does not match then reset the page after storing the received stimuli list
                this.stimuliListLoaded = synchroniseStimulusList(this.stimuliListGroup);
                synchroniseCurrentStimulus(0);
                return;
            }
            this.groupUUID = groupUUID;
        }
        final boolean groupIdMatches = (this.groupUUID != null) ? this.groupUUID.equals(groupUUID) : false; // if a group id was provided then ignore anyother groups???

        if (groupReady && groupIdMatches && screenIdMatches && originPhaseMatches) {
            // handle group messages
            this.groupReady = groupReady;
            if (this.stimuliListLoaded.equals(this.stimuliListGroup)) {
                // only if the group is ready do we try to process the group message
                boolean messageIsRelevant = false;
                for (String channel : groupCommunicationChannels.split("\\|")) {
                    final List<String> channelList = Arrays.asList(channel.split(","));
                    if (channelList.contains(originMemberCode)) {
                        this.channelScores.put(channel, channelScore);
                    }
                    this.groupScore = groupScore;
                    // check communication channel before responding to the message
                    if (channelList.contains(this.memberCode) && channelList.contains(originMemberCode)) {
                        activeChannel = channel;
                        final List<String> expectedRespondentsList = Arrays.asList(expectedRespondents.split(","));
                        final List<String> requiredRespondentsList = new ArrayList<>(expectedRespondentsList);
                        requiredRespondentsList.retainAll(channelList);
                        final List<String> actualRespondentsList = Arrays.asList(actualRespondents.split(","));
                        if (actualRespondentsList.containsAll(requiredRespondentsList)) {
                            messageIsRelevant = true;
                            userGroupLabelUpdateNeeded = true;
                        }
                    }
                }
                if (messageIsRelevant) {
                    this.messageSenderMemberCode = originMemberCode;
                    // make sure that all relevent members have responded before moving to the next phase
                    final int currentRequestedPhase = Integer.parseInt(requestedPhase);
                    final List<GroupActivityListener> currentFiredListenerList = new ArrayList();
                    for (String phaseMembers : activityListeners.keySet()) {
                        final String[] splitRole = phaseMembers.split(":");
                        int roleIndex = currentRequestedPhase % splitRole.length;
                        if (splitRole[roleIndex].contains(this.memberCode)) {
                            final GroupActivityListener currentListener = activityListeners.get(phaseMembers);
//                        ((userIdMatches) ? selfActivityListeners : othersActivityListeners).get(phaseMembers).get(this.requestedPhase).postLoadTimerFired();
                            if (splitRole.length == 1 /* if there is only one role to this screen then it is ok to refire the last */
                                    || (lastFiredListenerList == null || !lastFiredListenerList.contains(currentListener))) {
                                this.stimulusId = stimulusId;
//                                this.stimulusIndex = Integer.parseInt(stimulusIndex); // todo check for double adding of stimulus index and or something like that
                                int stimulusPhaseIndex = currentRequestedPhase / phasesPerStimulus;
                                this.requestedPhase = currentRequestedPhase;
                                this.messageString = messageString;
                                this.messageSenderId = userId;
                                this.responseStimulusId = responseStimulusId;
                                if (!endOfStimuli) {
                                    // if we are already at the end of the stimuli list then do not sync again
                                    final Stimulus currentStimulus = synchroniseCurrentStimulus(stimulusPhaseIndex);
                                    // if the stimulusSyncListener has put us at the end of the stimuli list then trigger any phases
                                    currentListener.triggerActivityListener(currentRequestedPhase, splitRole[roleIndex], currentStimulus);
                                    currentFiredListenerList.add(currentListener);
//                                    if (endOfStimuli) {
//                                        // if endOfStimuli has changed state here then we must trigger the endOfStimulusListener
//                                        endOfStimulusListener.postLoadTimerFired();
//                                    }
                                }
                            }
                        }
                    }
                    if (!currentFiredListenerList.isEmpty()) {
                        lastFiredListenerList = currentFiredListenerList;
                        synchroniseStreamingPhase(this.requestedPhase);
                    }
                }
            }
        }
        if (userGroupLabelUpdateNeeded) {
            groupInfoChanged();
        }
    }

    protected void setConnected(Boolean isConnected) {
        this.isConnected = isConnected;
        if (isConnected) {
            groupFindingMembers();
        } else {
            groupNetworkConnecting();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String getUserLabel() {
        return userLabel;
    }

    @Override
    public String getAllMemberCodes() {
        return allMemberCodes;
    }

    @Override
    public String getMemberCode() {
        return memberCode;
    }

    @Override
    public String getActiveChannel() {
        return activeChannel;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    public String getStimulusId() {
        return stimulusId;
    }

    @Override
    public String getGroupCommunicationChannels() {
        return groupCommunicationChannels;
    }

    @Override
    public Integer getRequestedPhase() {
        return requestedPhase;
    }

    @Override
    public String getMessageString() {
        return messageString;
    }

    public String getMessageSenderId() {
        return messageSenderId;
    }

    public String getResponseStimulusOptions() {
        return responseStimulusOptions;
    }

    public void setResponseStimulusOptions(String responseStimulusOptions) {
        this.responseStimulusOptions = responseStimulusOptions;
    }

    public String getResponseStimulusId() {
        return responseStimulusId;
    }

    public void setResponseStimulusId(String responseStimulusId) {
        this.responseStimulusId = responseStimulusId;
    }

    @Override
    public String getGroupScore() {
        return groupScore;
    }

    @Override
    public String getChannelScore() {
        return channelScores.get(activeChannel);
    }

    @Override
    public String getChannelScore(String channel) {
        return channelScores.get(channel);
    }

    @Override
    public Set<String> getChannelScoreKeys() {
        return channelScores.keySet();
    }

    @Override
    public String getGroupUUID() {
        return groupUUID;
    }

    @Override
    public String getAsignedMemberCodes() {
        return asignedMembers;
    }

    public boolean isGroupReady() {
        return groupReady;
    }

    public void setEndOfStimuli(Boolean endofStimuli) {
        this.endOfStimuli = endofStimuli;
    }

    public boolean isEndOfStimuli() {
        return this.endOfStimuli;
    }

    public String getMessageSenderMemberCode() {
        return messageSenderMemberCode;
    }

    public void stopListeners() {
        groupKickTimer.cancel();
    }

    public native void joinGroupNetwork(String groupServerUrl) /*-{
        var groupParticipantService = this;
//        console.log("joinGroupNetwork: " + groupServerUrl + " : " + groupName);
        groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::groupNetworkConnecting()();
        var socket = new $wnd.SockJS(groupServerUrl + 'gs-guide-websocket');
        $wnd.stompClient = $wnd.Stomp.over(socket);
        $wnd.stompClient.connect(
            {
//            withCredentials: false,
//            noCredentials : true
//            }, "","",
            },
            function (frame) {
            groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::setConnected(Ljava/lang/Boolean;)(@java.lang.Boolean::TRUE);
            console.log('Connected: ' + frame);
            $wnd.stompClient.subscribe('/shared/group', function (groupMessage) {
                var contentData = JSON.parse(groupMessage.body);
                //console.log('contentData: ' + contentData);
                groupParticipantService.@nl.mpi.tg.eg.experiment.client.service.GroupParticipantService::handleGroupMessage(
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/Boolean;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    Ljava/lang/String;
                    )(
                    contentData.userId, 
                    contentData.screenId,
                    contentData.userLabel,
                    contentData.groupId,
                    contentData.allMemberCodes, 
                    contentData.memberCode, 
                    contentData.originMemberCode, 
                    contentData.expectedRespondents,
                    contentData.actualRespondents,
                    contentData.stimulusId,
                    Number(contentData.stimulusIndex),
                    contentData.stimuliList, 
                    Number(contentData.originPhase), 
                    Number(contentData.requestedPhase), 
                    contentData.messageString,
                    (contentData.groupReady)?@java.lang.Boolean::TRUE : @java.lang.Boolean::FALSE, 
                    contentData.responseStimulusId,
                    String(contentData.groupScore),
                    String(contentData.channelScore),
                    contentData.groupUUID
                    );
            }, function(error) {
                // display the error's message header:
                console.log('contentData: ' + contentData);
                console.log('error.headers.message: ' + error.headers.message);
            });
//        }, function(error) {
//                console.log('error: ' + error);
//                console.log('error.headers.message: ' + error.headers.message);
            });
     }-*/;

    public void messageGroup(final int originPhase, final int incrementPhase, final String stimulusId, final String stimulusIndex, final String messageString, final String responseStimulusOptions, final String responseStimulusId, final int memberScore, final String phaseMembers) {
        String windowGroupId = Window.Location.getParameter("group");
        final String windowMemberCode = Window.Location.getParameter("member");
        final String currentGroupId = (windowGroupId != null) ? windowGroupId : groupId;
        if (groupKickTimer != null) {
            groupKickTimer.cancel();
        }
        groupKickTimer = new Timer() {
            @Override
            public void run() {
                GroupParticipantService.this.messageGroup(originPhase, originPhase + incrementPhase, userId, currentGroupId, windowMemberCode, screenId, allMemberCodes, groupCommunicationChannels, phaseMembers, stimulusId, stimulusIndex, stimuliListLoaded, messageString, responseStimulusOptions, responseStimulusId, memberScore);
            }
        };
//        groupKickTimer.run();
        groupKickTimer.scheduleRepeating(1000);
    }

    private native void messageGroup(int originPhase, int requestedPhase, String userId, String windowGroupId, String windowMemberCode, String screenId, String allMemberCodes, String groupCommunicationChannels, String expectedRespondents, String stimulusId, String stimulusIndex, String stimuliList, String messageString, String responseStimulusOptions, String responseStimulusId, int memberScore) /*-{
    var groupParticipantService = this;
    $wnd.stompClient.send("/app/group", {}, JSON.stringify({
        'userId': userId,
        'groupId': windowGroupId,
        'screenId': screenId,
        'userLabel': null,
        'allMemberCodes': allMemberCodes,
        'groupCommunicationChannels': groupCommunicationChannels,
        'expectedRespondents': expectedRespondents,
        'memberCode': windowMemberCode,
        'originMemberCode': null,
        'stimulusId': stimulusId,
        'originPhase': originPhase,
        'requestedPhase': requestedPhase,
        'stimulusIndex': stimulusIndex,
        'stimuliList': stimuliList,
        'messageString': messageString,
        'responseStimulusOptions': responseStimulusOptions,
        'responseStimulusId': responseStimulusId,
        'memberScore': memberScore,
        'groupReady': null
    }));
    }-*/;

    public abstract String synchroniseStimulusList(final String stimuliListGroup);

    public abstract Stimulus synchroniseCurrentStimulus(final int currentIndex);
    
    public abstract void synchroniseStreamingPhase(final int currentPhase);

    public abstract void groupInfoChanged();

    public abstract void groupNetworkConnecting();

    public abstract void groupFindingMembers();

    public abstract void groupInitialisationError();
}
