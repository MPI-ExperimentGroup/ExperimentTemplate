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
public class SchemaDocumentationGenerator extends AbstractSchemaGenerator {

    private void getStart(Writer writer) throws IOException {
        writer.append("<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Frinex XML Usage</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "<style>\n"
                + "td{\n"
                + "    vertical-align: top;\n"
                + "}\n"
                + "</style>\n"
                + "<script src=\"webjars/jquery/jquery.min.js\"></script>\n"
                + "<script>\n"
                + "    function getExample(tagName, targetId) {\n"
                + "      console.log(\"getExample\");\n"
                + "      \n"
                + "    $.get( \"with_stimulus_example.xml\", function( data ) {\n"
                + "    console.log(\"gotData\");\n"
                + "    console.log(tagName);\n"
                + "      console.log(targetId);\n"
                + "/*      \n"
                + "$(data).find(tagName).each(function() {\n"
                + "  console.log(this.nodeValue);\n"
                + "  console.log($);\n"
                + "  $.each(function() {\n"
                + "      console.log(this.name);\n"
                + "      console.log(this.value);\n"
                + "  }      );\n"
                + "$.each(this.attributes, function(i, attrib){\n"
                + "console.log(attrib);\n"
                + "var name = attrib.name;\n"
                + "var value = attrib.value;\n"
                + "console.log(name);\n"
                + "      console.log(value);\n"
                + "      \n"
                + "    $( \"#\" + targetId).append('<div>' + name + '</div>');      \n"
                + "});\n"
                + "});\n"
                + "*/\n"
                + "\n"
                + "$(data).find(tagName).each(function(currentIndex, currentElement) {\n"
                + "$(\"#\" + targetId).first().append('<span>&lt;' + currentElement.nodeName + '&gt;</span><br/>');\n"
                + "var lastElementName = '';\n"
                + "$(currentElement).children().each(function() {\n"
                + "var $childElement = this;\n"
                + "if(lastElementName !== $childElement.nodeName) {\n"
                + "$(\"#\" + targetId).first().append('&nbsp;&nbsp;&nbsp;<span>&lt;' + $childElement.nodeName + '&gt;</span><br/>');\n"
                + "lastElementName = $childElement.nodeName;\n"
                + "} else {\n"
                + "}});\n"
                + "$(\"#\" + targetId).first().append('<span>&lt;' + currentElement.nodeName + '&gt;</span>');\n"
                + "});\n"
                + "\n"
                + "/*\n"
                + "$(this).find(tagName).children().each(function() {\n"
                + "alert((this).nodeName);\n"
                + "//$(\"#\" + targetId).append((this).nodeName));\n"
                + "$(\"#\" + targetId).append((this).text().replace(/&/g, \"&amp;\").replace(/</g, \"&lt;\")\n"
                + ".replace(/>/g, \"&gt;\")\n"
                + ".replace(/\"/g, \"&quot;\")\n"
                + "+ \"<br />\");\n"
                + "\n"
                + "});\n"
                + "\n"
                + "$(data).find(tagName).each(function () {\n"
                + "  console.log($(this).text());\n"
                + "$(\"#\" + targetId).append($(this).text().replace(/&/g, \"&amp;\")\n"
                + ".replace(/</g, \"&lt;\")\n"
                + ".replace(/>/g, \"&gt;\")\n"
                + ".replace(/\"/g, \"&quot;\")\n"
                + "+ \"<br />\");\n"
                + "});\n"
                + "\n"
                + "\n"
                + "      $xml = $( data );\n"
                + "    $tagUseage = $xml.find(tagName);\n"
                + "    \n"
                + "  console.log($tagUseage);    \n"
                + "    \n"
                + "    $.each($tagUseage.attributes, function(i, attrib){\n"
                + "console.log(this.name);\n"
                + "console.log(this.value);\n"
                + "\n"
                + "  //  var name = attrib.name;\n"
                + "    //var value = attrib.value;\n"
                + "//    $( \"#\" + targetId).append('<div>' + name + '</div>');      \n"
                + "  \n"
                + "    });\n"
                + "    */\n"
                + "//    $tagUseage.attr().each(function () {\n"
                + "//   $( \"#\" + targetId).append('<div>' + this.name() + '</div>');      \n"
                + "   // })\n"
                + "\n"
                + "    \n"
                + "    \n"
                + "    // document.getElementById('xmlResult').innerHTML =\n"
                + "    //$xml.childNodes[0].attr(\"appNameDisplay\");\n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    // $presenter = $xml.find( \"presenter\" );\n"
                + "    \n"
                + "    // document.getElementById('xmlResult').innerHTML =\n"
                + "    //   $presenter.attr(\"self\");\n"
                + "    //xml.childNodes[0].attr(\"\");\n"
                + "    // data.getElementsByTagName(\"Experiment\")[0].childNodes[0].nodeValue;\n"
                + "    });\n"
                + "    }\n"
                + "    \n"
                + "  </script>"
                + "    </head>\n"
                + "    <body>\n");
    }

    private void addAttributes(Writer writer, DocumentationElement currentElement) throws IOException {
        for (String attributeStrings : currentElement.attributeStrings) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeStrings);
            writer.append("</span><span style=\"color:grey\">=&quot;String&quot;</span><br/>\n");
        }
        for (String attributeLowercase : currentElement.attributeLowercase) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeLowercase);
            writer.append("</span><span style=\"color:grey\">=&quot;String Lowercase&quot;</span><br/>\n");
        }
        for (String attributeRGBs : currentElement.attributeRGBs) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeRGBs);
            writer.append("</span><span style=\"color:grey\">=&quot;RGB Hex Value&quot;</span><br/>\n");
        }
        for (String attributeBooleans : currentElement.attributeBooleans) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeBooleans);
            writer.append("</span><span style=\"color:grey\">=&quot;Boolean&quot;</span><br/>\n");
        }
        for (String attributeIntegerLists : currentElement.attributeBooleansOptional) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeIntegerLists);
            writer.append("</span><span style=\"color:grey\">=&quot;Boolean (optional)&quot;</span><br/>\n");
        }
        for (String attributeFloats : currentElement.attributeFloats) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeFloats);
            writer.append("</span><span style=\"color:grey\">=&quot;Float&quot;</span><br/>\n");
        }
        for (String attributeIntegers : currentElement.attributeIntegers) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeIntegers);
            writer.append("</span><span style=\"color:grey\">=&quot;Integer&quot;</span><br/>\n");
        }
        for (String attributeIntegerLists : currentElement.attributeIntegersOptional) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeIntegerLists);
            writer.append("</span><span style=\"color:grey\">=&quot;Integer (optional)&quot;</span><br/>\n");
        }
        for (String attributeIntegerLists : currentElement.attributeIntegerLists) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeIntegerLists);
            writer.append("</span><span style=\"color:grey\">=&quot;Integer List&quot;</span><br/>\n");
        }
    }

    private void addElement(Writer writer, DocumentationElement currentElement) throws IOException {
        writer.append("<h3 style=\"text-transform: uppercase;\">\n");
        writer.append(currentElement.elementName);
        writer.append("\n</h3>\n");
        writer.append(currentElement.documentationText);
        writer.append("\n<br/><br/><table>\n");

//        writer.append("<tr><td colspan=3>\n");
//        writer.append(ROOT_ELEMENT_DOCUMENTATION);
//        writer.append("</td></tr>\n");
        writer.append("<tr><td>\n");
        writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">");
        writer.append(currentElement.elementName);
        writer.append("</span></td><td>");
//        writer.append("Root element of the experiment configuration file of which only one is permitted.\n");
//        writer.append("</td><td>\n");
        addAttributes(writer, currentElement);
        writer.append("</td><td>");
        if (currentElement.childElements.length == 0 && !currentElement.hasStringContents) {
            writer.append("<span style=\"color:red\">/</span>");
        }
        writer.append("<span style=\"color:purple\">&gt;</span></td><td>\n");
        for (DocumentationElement chileElement : currentElement.childElements) {
            writer.append("<table>\n");
            writer.append("<tr><td>\n");
            writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">");
            writer.append(chileElement.elementName);
            writer.append("</span></td><td>");
            addAttributes(writer, chileElement);
            writer.append("</td><td><span style=\"color:purple\">&gt;</span>\n");
            writer.append("</td></tr>\n");
            writer.append("</td></tr></table>\n");
        }
//        writer.append("&lt;preventWindowClose&gt;<br/>\n"); //featureText
//        writer.append("&lt;administration&gt;<br/>\n");
//        writer.append("&lt;scss&gt;<br/>\n");
//        writer.append("&lt;metadata&gt;<br/>\n");
//        writer.append("&lt;presenter&gt;<br/>\n");
//        writer.append("&lt;stimuli&gt;<br/>\n");
        writer.append("</td>\n");
        writer.append("</tr>\n");
        if (currentElement.hasStringContents) {
            writer.append("<tr>\n");
            writer.append("<td></td><td></td><td>\n");
            writer.append("<span style=\"color:grey\">String</span></td>");
            writer.append("</tr>\n");
        }
        if (currentElement.childElements.length > 0 || currentElement.hasStringContents) {
            writer.append("<tr>\n");
            writer.append("<td>\n");
            writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:red\">/</span><span style=\"color:blue\">");
            writer.append(currentElement.elementName);
            writer.append("</span><span style=\"color:purple\">&gt;</span></td>");
            writer.append("</tr>\n");
        }
        writer.append("<tr>\n");
        writer.append("<td colspan='2' style=\"background: lightgray;\" id='" + currentElement.elementName + "Target'>\n");
        writer.append("</td>");
        writer.append("<td>\n");
        writer.append("<button onclick=\"getExample('" + currentElement.elementName + "', '" + currentElement.elementName + "Target');\">show example</button>\n");
        writer.append("</td>");
        writer.append("</tr>\n");
        writer.append("</table>\n");
        for (DocumentationElement chileElement : currentElement.childElements) {
            addElement(writer, chileElement);
        }
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
        writer.append("<tr>\n");
        writer.append("<td id='presenter1'>\n");
        writer.append("</td>");
        writer.append("<td>\n");
        writer.append("<button onclick=\"getExample('presenter', 'presenter1');\">show example</button>\n");
        writer.append("</td>");
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
                case hasMediaLoadingButton:
                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
                    writer.append("&lt;onActivate&gt;<br/>\n");
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
        addElement(writer, rootElement);
//        addElement(writer, rootElement.childElements[1]);
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
