/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since October 1, 2018 15:38 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaDocumentationGenerator {

    private void getStart(Writer writer) throws IOException {
        writer.append("<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Frinex XML Usage</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n");
    }

    private void addExperiment(Writer writer) throws IOException {
        writer.append("<tr><td>\n");
//
//        writer.append("<xs:simpleType name=\"rgbHexValue\">\n");
//        writer.append("<xs:restriction base=\"xs:token\">\n");
//        writer.append("<xs:pattern value=\"#[\\dA-Fa-f]{6}\"/>\n");
//        writer.append("</xs:restriction>\n");
//        writer.append("</xs:simpleType>\n");
//        writer.append("<xs:simpleType name=\"lowercaseValue\">\n");
//        writer.append("<xs:restriction base=\"xs:string\">\n");
//        writer.append("<xs:pattern value=\"[a-z]([a-z_0-9]){3,}\"/>\n");
//        writer.append("</xs:restriction>\n");
//        writer.append("</xs:simpleType>\n");
//        writer.append("<xs:simpleType name=\"integerList\">\n");
//        writer.append("<xs:list itemType=\"xs:integer\"/>\n");
//        writer.append("</xs:simpleType>\n");
        writer.append("&lt;experiment&gt;<br/>\n");
//        writer.append("Root element of the experiment configuration file of which only one is permitted.\n");
        writer.append("</td><td>\n");
        for (String attributeStrings : new String[]{"appNameDisplay"}) {
            writer.append(attributeStrings);
            writer.append("<br/>\n");
        }
        for (String attributeLowercase : new String[]{"appNameInternal"}) {
            writer.append(attributeLowercase).append(" (lowercaseValue)\n");
            writer.append("<br/>\n");
        }
        for (String attributeRGBs : new String[]{"backgroundColour", "complementColour0", "complementColour1", "complementColour2", "complementColour3", "complementColour4", "primaryColour0", "primaryColour1", "primaryColour2", "primaryColour3", "primaryColour4"}) {
            writer.append(attributeRGBs).append(" (rgbHexValue)\n");
            writer.append("<br/>\n");
        }
        for (String attributeBooleans : new String[]{"isScalable", "preserveLastState", "rotatable", "showMenuBar"}) {
            writer.append(attributeBooleans).append(" (boolean)\n");
            writer.append("<br/>\n");
        }
        for (String attributeFloats : new String[]{"defaultScale"}) {
            writer.append(attributeFloats).append(" (decimal)\n");
            writer.append("<br/>\n");
        }
        for (String attributeIntegers : new String[]{"textFontSize"}) {
            writer.append(attributeIntegers).append(" (integer)\n");;
            writer.append("<br/>\n");
        }
        for (String attributeIntegerLists : new String[]{}) {
            writer.append(attributeIntegerLists);
            writer.append("<br/>\n");
        }
        writer.append("</td><td>\n");
        writer.append("&lt;preventWindowClose&gt;<br/>\n"); //featureText
        writer.append("&lt;administration&gt;<br/>\n");
        writer.append("&lt;scss&gt;<br/>\n");
        writer.append("&lt;metadata&gt;<br/>\n");
        writer.append("&lt;presenter&gt;<br/>\n");
        writer.append("&lt;stimuli&gt;<br/>\n");
        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void addPresenter(Writer writer, final PresenterType[] presenterTypes) throws IOException {
        writer.append("<tr><td>\n");
        writer.append("&lt;presenter&gt;<br/>\n");
        writer.append("</td><td>\n");

        writer.append("back<br/>\n"); // todo: constrain back to always refer to an presenter that exists
        writer.append("next<br/>\n"); // todo: constrain back to always refer to an presenter that exists
        writer.append("self<br/>\n");
        writer.append("title<br/>\n");
        writer.append("menuLabel<br/>\n");
        writer.append("type (");

        for (final PresenterType presenterType : presenterTypes) {
            writer.append(presenterType.name()).append(", \n");
        }
        writer.append(")<br/>\n");
//        writer.append("</td><td>\n");
//        writer.append("type (\n");
//        for (final FeatureType featureRef : FeatureType.values()) {
//            if (featureRef.getIsChildType() == FeatureType.Contitionals.none) {
//                writer.append(featureRef.name());
//                writer.append(", ");
//            }
//        }
//        writer.append(")<br/>\n");

        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void addAdministration(Writer writer) throws IOException {
        writer.append("<tr><td>\n");
        writer.append("&lt;administration&gt;<br/>\n");
        writer.append("</td><td>\n");
        writer.append("&lt;dataChannel&gt;<br/>\n");
        writer.append("</td><td>\n");
        writer.append("channel (decimal)<br/>\n");
        writer.append("label<br/>\n");
        writer.append("logToSdCard (boolean)<br/>\n");
        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void addMetadata(Writer writer) throws IOException {
        writer.append("<tr><td>\n");
        writer.append("&lt;metadata&gt;<br/>\n");
        writer.append("</td><td>\n");
        writer.append("controlledMessage<br/>\n");
        writer.append("controlledRegex<br/>\n");
        writer.append("postName<br/>\n");
        writer.append("preventServerDuplicates (boolean)<br/>\n");
        writer.append("duplicatesControlledMessage<br/>\n");
        writer.append("registrationField<br/>\n");
        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void addStimuli(Writer writer) throws IOException {
        writer.append("<tr><td>\n");
        writer.append("&lt;stimuli&gt;<br/>\n");
        writer.append("</td><td>\n");
        writer.append("&lt;stimulus&gt;<br/>\n");
        writer.append("</td><td>\n");
        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void addFeature(Writer writer, final FeatureType featureType) throws IOException {
        writer.append("<tr><td>\n");
        writer.append("<div class=\"complexType\">&lt;").append(featureType.name()).append("&gt;<br/>\n");
        writer.append("</td><td>\n");
        if (featureType.canHaveText()) {
            writer.append("featureText<br/>\n");
        }
        if (featureType.getFeatureAttributes() != null) {
            for (final FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                if (!featureAttribute.isOptional() && !featureType.allowsCustomImplementation()) {
                    writer.append("*");
                }
                writer.append(featureAttribute.name());
                writer.append("<br/>\n");
            }
        }
        if (featureType.canHaveStimulusTags() && !featureType.isCanHaveRandomGrouping()) {
            writer.append("tags<br/>\n");
        }
        if (featureType.allowsCustomImplementation()) {
            writer.append("class<br/>\n");
        }
        writer.append("</td><td>\n");
//        writer.append("&gt;<br/>\n");
        if (featureType.canHaveFeatures()) {
            writer.append("can have features<br/>\n");
//            for (final FeatureType featureRef : FeatureType.values()) {
//                if (featureRef.getIsChildType() == FeatureType.Contitionals.none || featureRef.getIsChildType() == featureType.getRequiresChildType()
//                        || featureRef.getIsChildType() == FeatureType.Contitionals.groupNetworkAction // currently allowing all groupNetworkAction in any element
//                        || featureRef.getIsChildType() == FeatureType.Contitionals.stimulusAction // currently allowing all stimulusAction in any element
//                        ) {
//                    writer.append("<div class=\"element\">\"").append(featureRef.name()).append(featureRef.name()).append("\"\n");
//                }
//            }
//            writer.append("</div>\n");
        } else {
            switch (featureType.getRequiresChildType()) {
                case hasTrueFalseCondition:
                    writer.append("&lt;conditionTrue&gt;<br/>\n");
                    writer.append("&lt;conditionFalse&gt;<br/>\n");
                    break;
                case hasCorrectIncorrect:
                    writer.append("&lt;responseCorrect&gt;<br/>\n");
                    writer.append("&lt;responseIncorrect&gt;<br/>\n");
                    break;
                case hasMoreStimulus:
                    writer.append("&lt;hasMoreStimulus&gt;<br/>\n");
                    writer.append("&lt;endOfStimulus&gt;<br/>\n");
                    writer.append("&lt;randomGrouping&gt;<br/>\n");
                    writer.append("&lt;stimuli&gt;<br/>\n");
                    break;
                case hasErrorSuccess:
                    writer.append("&lt;onError&gt;<br/>\n");
                    writer.append("&lt;onSuccess&gt;<br/>\n");
                    break;
                case hasUserCount:
                    writer.append("&lt;multipleUsers&gt;<br/>\n");
                    writer.append("&lt;singleUser&gt;<br/>\n");
                    break;
                case hasThreshold:
                    writer.append("&lt;aboveThreshold&gt;<br/>\n");
                    writer.append("&lt;withinThreshold&gt;<br/>\n");
                    break;
                case groupNetworkActivity:
                    writer.append("&lt;groupNetworkActivity&gt;<br/>\n");
                    writer.append("&lt;sendGroupEndOfStimuli&gt;<br/>\n");
                    break;
                case hasMediaPlayback:
                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
                    writer.append("&lt;mediaPlaybackComplete&gt;<br/>\n");
                    break;
                case hasMediaRecorderPlayback:
                    writer.append("&lt;onError&gt;<br/>\n");
                    writer.append("&lt;onSuccess&gt;<br/>\n");
                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
                    writer.append("&lt;mediaPlaybackComplete&gt;<br/>\n");
                    break;
                case hasMediaLoading:
                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
                    break;
//                case needsConditionalParent:
//                    break;
                case none:
                    break;
                default:
                    for (FeatureType featureType1 : FeatureType.values()) {
                        if (featureType1.getIsChildType() == featureType.getRequiresChildType()) {
                            writer.append("&lt;").append(featureType1.name()).append("&gt;<br/>\n");
                        }
                    }
                    if (featureType.canHaveStimulusTags() && featureType.isCanHaveRandomGrouping()) {
                        writer.append("&lt;randomGrouping&gt;<br/>\n");
                        writer.append("&lt;stimuli&gt;<br/>\n");
                    }
                    break;
            }
        }
        writer.append("</td>\n");
        writer.append("</tr>\n");
    }

    private void endState(Writer writer) throws IOException {
        writer.append("}\n");
    }

    private void getStateChange(Writer writer, final String state1, final String state2, final String... attributes) throws IOException {
        writer.append("<div class=\"element\">\"").append(state1).append("\">\n")
                .append("<div class=\"complexType\">\n")
                .append("<div class=\"sequence>\n");
        for (final String value : attributes) {
            writer.append("<div class=\"element ref=\"").append(value).append("\"/>\n");
        }
        writer.append("</div>\n").append("</div>\n").append("</div>\n");
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("    </body>\n"
                + "</html>\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        writer.append("<h3>Experiment</h3><table border=1>\n");
        addExperiment(writer);
        writer.append("</table>\n");
        writer.append("<h3>Administration</h3><table border=1>\n");
        addAdministration(writer);
        writer.append("</table>\n");
        writer.append("<h3>Metadata</h3><table border=1>\n");
        addMetadata(writer);
        writer.append("</table>\n");
        writer.append("<h3>Presenter</h3><table border=1>\n");
        addPresenter(writer, PresenterType.values());
        writer.append("</table>\n");
        writer.append("<h3>Stimuli</h3><table border=1>\n");
        addStimuli(writer);
        writer.append("</table>\n");
//        for (PresenterType presenterType : PresenterType.values()) {
//            addPresenter(writer, presenterType);
//            for (FeatureType featureType : presenterType.getFeatureTypes()) {
//                getStateChange(writer, presenterType.name(), featureType.name());
//            }
//            endState(writer);
//        }
        writer.append("<h3>Features</h3><table border=1>\n");
        for (FeatureType featureType : FeatureType.values()) {
            addFeature(writer, featureType);
        }
        writer.append("</table>\n");
        getEnd(writer);
        System.out.println(writer);
//        assertEquals(expResult, stringBuilder.toString());
    }

    public void createHtmlFile(final File schemaOutputFile) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
        FileWriter schemaOutputWriter = new FileWriter(schemaOutputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        appendContents(bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
