/*
 * Copyright (C) 2015 Pivotal Software, Inc.
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
package nl.mpi.tg.eg.experimentdesigner.model;

import nl.mpi.tg.eg.experimentdesigner.util.AbstractSchemaGenerator.AttributeType;

/**
 * this can be updated with the output of: grep select=
 * ~/Documents/ExperimentTemplate/gwt-cordova/src/main/xsl/config2java.xsl
 *
 * @since Aug 18, 2015 4:51:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum FeatureAttribute {

    menuLabel,
    closeButtonLabel,
    back,
    next,
    fieldName,
    parameterName,
    linkedFieldName,
    oneToMany,
    daysThresholds,
    code,
    //    tags,  // todo: consider updating some elements to take a tags attribute rather than a stimuli element
    codeFormat,
    validationRegex,
    allowedCharCodes(true),
    matchingRegex("Regular expression that will be used to determin a match.", false, AttributeType.xsString),
    visibleRegex, enabledRegex,
    visible,
    replacementRegex(true),
    replacement(true),
    src,
    link,
    type,
    headerKey(true), separator(true), // these attributes are used by the administration system to process the tabular data from custom stimuli modules eg advocas
    percentOfPage,
    maxHeight,
    maxWidth, 
    align,
    orientation(null, true, new String[]{"horizontal", "vertical", "flow"}),
    target(false), // this is probably not optional in some cases
    targetOptions(true),
    styleName("One or more CSS stylenames to apply to the feature. These can be the built in styles or custom styles defined in the SCSS section.", true, AttributeType.xsString),
    regionId,
    showOnBackButton("When true the component will be invisible until the browser back button is clicked. Repeated clicks will toggle the visibility. The feature cannot be used in conjunction with a presenter back attribute.", true, AttributeType.xsBoolean),
    eventTier,
    dataChannel(true),
    sendingRegex(false),
    receivingRegex(false),
    dataLogFormat(false),
    // TODO: add documentation for daysBetween|length|random|replaceAll etc.
    evaluateTokens(false), // TODO: this is one of only a few attributes can take a &quot;. For other string attributes the &quot; will cause the compile to fail ungracefully and should be prevented in the XSD
    evaluateMs(false), 
    filePerStimulus("When recording audio this boolean determins if a separate recording should be made for each stimulus or one recording for the set of stimuli"),
    eventTag("When present this tag will be logged in the timestamps table as close as possible to the moment the event occurs.", true, AttributeType.xsString),
    ratingLabels,
    ratingLabelLeft(true),
    ratingLabelRight(true),
    sendData,
    networkErrorMessage, // TODO: networkErrorMessage= could be replaced with onError which is a lot more flexible
    inputErrorMessage,
    randomise("When true the items will be randomised once before any repeats are calculated, when false the items will not be randomised. If the selected items were 1234, the result of randomise true might be 3214 for example.", true, AttributeType.xsBoolean),
    repeatCount("When greater than zero the list will be repeated the selected items by the number of times specified without changing the order of the items. For example if repeat count was 3 then the result might be 321432143214 for example.", true, AttributeType.xsInteger),
    repeatRandomWindow("When greater than 0 all items will be randomised again after the repeatCount has been applied. This randomisation is constrained to the designated window of items causing the randomisation to be done in a moving window across all items. When used in conjunction with repeatCount this allows randomisation without adjacency at the boundary of repeats. For example, if a repeatCount of 3 resulted in 321432143214 a repeatRandomWindow of 2 could produce 231342413124 but cannot produce 214331322144. Whereas a repeatRandomWindow of 12 would randomise all items to any location and could produce 214331322144.", true, AttributeType.xsInteger), // todo: document how this works, which currently is to compare in sequence, image, audio, video and label and use the first found one as the comparitor. This could be made more explicit by adding a comparitor attribute that would be default be set to "image audio video label" for example
    adjacencyThreshold("Sets a window of allowed proximity between stimuli that can be condisered the same. When greater than 0 the list of stimuli will be checked after randomisation for adjacency within the specified window and modified accordingly. If matching items are within the adjacency window, one of the items will be moved to a new position, in some cases a new position without an adjacent match will not be possible. The first attribute that has a value out of image, audio, video or label is used in the comparison. For example adjacencyThreshold 3 would disallow abcad and an attempt to rerandomise would follow. Defaults to 3 when not provided. ", true, AttributeType.xsInteger),
    repeatIncorrect,
    //    repeatMatching(true),
    hotKey(null, true, new String[]{
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z",
        "ZERO",
        "ONE",
        "TWO",
        "THREE",
        "FOUR",
        "FIVE",
        "SIX",
        "SEVEN",
        "EIGHT",
        "NINE",
        "NUM_ZERO",
        "NUM_ONE",
        "NUM_TWO",
        "NUM_THREE",
        "NUM_FOUR",
        "NUM_FIVE",
        "NUM_SIX",
        "NUM_SEVEN",
        "NUM_EIGHT",
        "NUM_NINE",
        "NUM_MULTIPLY",
        "NUM_PLUS",
        "NUM_MINUS",
        "NUM_PERIOD",
        "NUM_DIVISION",
        "ALT",
        "BACKSPACE",
        "CTRL",
        "DELETE",
        "DOWN",
        "END",
        "ENTER",
        "ESCAPE",
        "HOME",
        "LEFT",
        "PAGEDOWN",
        "PAGEUP",
        "RIGHT",
        "SHIFT",
        "TAB",
        "UP",
        "F1",
        "F2",
        "F3",
        "F4",
        "F5",
        "F6",
        "F7",
        "F8",
        "F9",
        "F10",
        "F11",
        "F12",
        "PAUSE",
        "SPACE" // Bluetooth remote R1 with mode M+A
        ,
         "R1_MA_A" //    ,"R1_MA_B = -1;
        //    ,"R1_MA_C = -1; // vol
        //    ,"R1_MA_D = -1; // vol
        ,
         "R1_MA_ENTER",
        "R1_MA_BACK" // back
        //    ,"R1_MA_UP = -1; // vol
        //    ,"R1_MA_DOWN = -1; // vol
        ,
         "R1_MA_LEFT",
        "R1_MA_RIGHT" // Bluetooth remote R1 with mode M+B
        ,
         "R1_MB_A",
        "R1_MB_B",
        "R1_MB_C",
        "R1_MB_D" // back
        ,
         "R1_MB_ENTER",
        "R1_MB_BACK" //    ,"R1_MB_UP = -1;
        //    ,"R1_MB_DOWN = -1;
        //    ,"R1_MB_LEFT = -1;
        //    ,"R1_MB_RIGHT = -1;
        // Bluetooth remote R1 with mode M+C
        ,
         "R1_MC_A" // back
        ,
         "R1_MC_B",
        "R1_MC_C",
        "R1_MC_D",
        "R1_MC_ENTER",
        "R1_MC_BACK" //    ,"R1_MC_UP = -1;
        //    ,"R1_MC_DOWN = -1;
        //    ,"R1_MC_LEFT = -1;
        //    ,"R1_MC_RIGHT = -1;
        // Bluetooth remote R1 with mode M+D
        ,
         "R1_MD_A",
        "R1_MD_B" // back
        //    ,"R1_MD_C = -1; // vol
        //    ,"R1_MD_D = -1; // vol
        //    ,"R1_MD_ENTER = -1; // mouse
        //    ,"R1_MD_BACK = -1; // mouse
        //    ,"R1_MD_UP = -1; // mouse
        //    ,"R1_MD_DOWN = -1; // mouse
        //    ,"R1_MD_LEFT = -1; // mouse
        //    ,"R1_MD_RIGHT = -1; // mouse
        // USB LP310 laser pointer remote
        ,
         "LP310_UP",
        "LP310_UP_LONG_A",
        "LP310_UP_LONG_B",
        "LP310_DOWN",
        "LP310_DOWN_LONG",
        "LP310_MIDDLE",
        "LP310_MIDDLE_LONG",
        "LP310_MIDDLE_DOUBLE",
        ""
    }), // todo: this could provide a list for the schema to know what are valid values --
    //    @Deprecated
    //    mp3,
    //    @Deprecated
    //    mp4,
    //    @Deprecated
    //    ogg,
    //    @Deprecated
    //    webm,
    recordingFormat("", true, new String[]{"wav", "ogg", "ogv"}),
    levelIndicatorStyle("The CSS style name to use for the audio recording level indicator. When not present the audio level indicator will not be shown.", true, AttributeType.xsString),
    echoCancellation("When present this value will be passed on to web browser when the recording starts. Implementation is browser and platform specific.", true, AttributeType.xsBoolean), 
    noiseSuppression("When present this value will be passed on to web browser when the recording starts. Implementation is browser and platform specific.", true, AttributeType.xsBoolean), 
    autoGainControl("When present this value will be passed on to web browser when the recording starts. Implementation is browser and platform specific.", true, AttributeType.xsBoolean),
    downloadPermittedWindowMs("A time window in milliseconds within which download from the server will be allowed. When set to a non zero value the recording will be downloaded from the server ready for playback. So if playback of the recorded audio is not needed then this should be set to 0 to save bandwidth.", false, AttributeType.xsInteger),
    deviceRegex(true),
    poster,
    autoPlay("When true media will be played as soon as it has loaded. Modern web browsers will prevent media from playing before the user interacts with the page after each time it has loaded. If this is an issue, it can be overcome by always having a begin button, or by having a replay button in the case of an initial failure.",
            false, new String[]{"true", "false"}),
    loop,
    columnCount,
    kintypestring,
    diagramName,
    imageWidth,
    alternativeChoice,
    offset,
    dtmf("Starts or stops the production of DTMF tones (dual-tone multi-frequency signaling) which can be used to encode some timing information in an audio output. The DTMF tone values can be decoded directly from the audio output or from subsequent audio recordings.", true, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "*", "#", "off"}),
    opto1("Sets the state of the on screen indicator for optical sensor one.", true, AttributeType.xsBooleanToggle),
    opto2("Sets the state of the on screen indicator for optical sensor two.", true, AttributeType.xsBooleanToggle),
    msToNext("The milliseconds to delay after completion. The resulting delay is approximate and variability should be tested in the intended environment.", false, AttributeType.xsInteger),
    groupId("This value is used in multiple ways depending on the context, to set the button group name used to enable and disable button groups, to assign the storage name for stimulus responses and the field name in the JSON data transmitted to the admin server so that multiple responses can be collected for a single stimulus.", true, AttributeType.xsString),
    mediaId(/*true*/), listenerId, threshold("Threshold value.", false, AttributeType.xsInteger), maximum("Maximum value.", false, AttributeType.xsInteger), minimum("Minimum value.", false, AttributeType.xsInteger), initial("Initial value.", false, AttributeType.xsInteger), average,
    msLabelFormat,
    animate(true), // animate currently has bounce stimuliCode or none
    //    minStimuliPerTag(true), // for each tag there should be at least N of each represented in the final list
    //    maxStimuliPerTag(true), // for each tag there should be no more than N of each represented in the final list
    maxStimuli(true),
    excludeRegex(true),
    //    alias, // alias is used to specify a tag or set of tags via GET parameters
    scoreThreshold(true), // integer to make active, when empty or not present is passed as null
    errorThreshold(true), // integer to make active, when empty or not present is passed as null
    potentialThreshold(true), // integer to make active, when empty or not present is passed as null
    correctStreak(true), // integer to make active, when empty or not present is passed as null
    errorStreak(true), // integer to make active, when empty or not present is passed as null
    gamesPlayed(true), // integer to make active, when empty or not present is passed as null
    showPlaybackIndicator,
    showControls(false),
    phaseMembers("List of members for each phase. Each phase is separated by : and in each phase the matching members are separated by , when no members match a - is given for that phase.", false, AttributeType.groupPhases),
    groupMembers("List of members separated by , with preferably only a-Z being used for the member names.", false, AttributeType.groupMembers),
    groupCommunicationChannels("List of communication channels separated by | for the group. The members are separated by , in each channel. Only members in the same channel will receive messages and these messages will only be from other members of the same channel.", false, AttributeType.groupChannels),
    groupCanvasChannels("List of canvas streaming channels separated by | for the group. The members are separated by , in each channel. Only members in the same channel see shared canvases and these messages will only be from other members of the same channel.", true, AttributeType.groupChannels),
    groupAudioChannels("List of audio streaming channels separated by | for the group. The members are separated by , in each channel. Only members in the same channel will hear the shared microphones from other members of the same channel.", true, AttributeType.groupChannels),
    groupCameraChannels("List of video streaming channels separated by | for the group. The members are separated by , in each channel. Only members in the same channel will see the shared cameras from other members of the same channel.", true, AttributeType.groupChannels),
//    streamState(null, false, new String[]{"start", "stop", "pause", "hide", "show", "mute", "unmute"}),
//    streamType(null, false, new String[]{"microphone", "camera", "canvas"}),
    streamChannels("List of directional communication channels separated by | for the given streaming media. The members are separated by , in each channel. Only the first member in each channel will transmit into the stream while the remaining members in each channel will receive the stream.", false, AttributeType.groupChannels),
    incrementPhase("Increments the current group phase and triggers the relevant group activities for all members of the group.", false, AttributeType.xsInteger),
    //    incrementStimulus,
    phasesPerStimulus("The number of phases per round in the group. When current phase reaches this value the next stimulus will be triggered.", false, AttributeType.xsInteger),
    applyScore("If set to true then the stimulus response is compared to the stimulus correctResponses and a score is given accordingly.", false, AttributeType.xsBoolean),
    // TODO: (1 of 3) participant allocation service with attribute allocationServiceName, to preserve the allocation across browsers and devices
    scoreValue("A positive number for achievements or a negative number for failures or zero for neither.", false, AttributeType.xsInteger);
    final boolean isOptional;
    final String[] typeValues;
    final AttributeType attributeType;
    final String documentation;

    private FeatureAttribute() {
        this.isOptional = false;
        this.typeValues = null;
        this.attributeType = null;
        this.documentation = null;
    }

    private FeatureAttribute(final String documentation) {
        this.isOptional = false;
        this.typeValues = null;
        this.attributeType = null;
        this.documentation = documentation;
    }

    private FeatureAttribute(boolean isOptional) {
        this.isOptional = isOptional;
        this.typeValues = null;
        this.attributeType = null;
        this.documentation = null;
    }

    private FeatureAttribute(final String documentation, boolean isOptional, final String[] typeValues) {
        this.isOptional = isOptional;
        this.typeValues = typeValues;
        this.attributeType = null;
        this.documentation = documentation;
    }

    private FeatureAttribute(final String documentation, boolean isOptional, final AttributeType attributeType) {
        this.isOptional = isOptional;
        this.typeValues = null;
        this.attributeType = attributeType;
        this.documentation = documentation;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public String[] getTypeValues() {
        return typeValues;
    }

    public AttributeType getType() {
        return attributeType;
    }

    public String getDocumentation() {
        return documentation;
    }
}
