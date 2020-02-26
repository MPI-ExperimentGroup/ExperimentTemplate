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
import java.util.Arrays;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import org.codehaus.groovy.runtime.AbstractComparator;

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
                // todo: add invitation_validation_example.xml
                + "    $.get( \"with_stimulus_example.xml\", function( data ) {\n"
                //                + "    console.log(\"gotData\");\n"
                //                + "    console.log(tagName);\n"
                //                + "      console.log(targetId);\n"
                //+ "/*      \n"
                //+ "$(data).find(tagName).each(function() {\n"
                //+ "  console.log(this.nodeValue);\n"
                //+ "  console.log($);\n"
                //+ "  $.each(function() {\n"
                //+ "      console.log(this.name);\n"
                //+ "      console.log(this.value);\n"
                //+ "  }      );\n"
                //+ "$.each(this.attributes, function(i, attrib){\n"
                //+ "console.log(attrib);\n"
                //+ "var name = attrib.name;\n"
                //+ "var value = attrib.value;\n"
                //+ "console.log(name);\n"
                //+ "      console.log(value);\n"
                //+ "      \n"
                //+ "    $( \"#\" + targetId).append('<div>' + name + '</div>');      \n"
                //+ "});\n"
                //+ "});\n"
                //+ "*/\n"
                + "\n"
                + "$(data).find(tagName).each(function(currentIndex, currentElement) {\n"
                + "$(\"#\" + targetId).empty();\n"
                + "$(\"#\" + targetId).css({background: \"lightgray\"});\n"
                + "$(\"#\" + targetId).first().append('<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">' + currentElement.nodeName);\n"
                + "$(currentElement.attributes).each(function(attributeIndex, childAttribute) {\n"
                + "if(attributeIndex > 0){\n"
                + "$(\"#\" + targetId).first().append('<br/>&nbsp;&nbsp;&nbsp;');\n"
                + "}\n"
                + "$(\"#\" + targetId).first().append('&nbsp;<span style=\"color:green\">' + childAttribute.name);\n"
                + "$(\"#\" + targetId).first().append('</span><span style=\"color:grey\">=&quot;' + childAttribute.value + '&quot;</span>');\n"
                + "});\n"
                + "$(\"#\" + targetId).first().append('</span><span style=\"color:purple\">&gt;</span><br/>');\n"
                + "var lastElementName = '';\n"
                + "var lastElementNameCount = 0;\n"
                + "$(currentElement).children().each(function(childIndex, childElement) {\n"
                + "if(lastElementName !== childElement.nodeName) {\n"
                + "$(\"#\" + targetId).first().append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">' + childElement.nodeName + '</span><span style=\"color:purple\">&gt;</span><br/>');\n"
                + "lastElementName = childElement.nodeName;\n"
                + "lastElementNameCount = 0;\n"
                + "} else {"
                + "lastElementNameCount++;"
                + "if(lastElementNameCount === 1){\n"
                + "$(\"#\" + targetId).first().append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"' + lastElementName + '.' + targetId + '\" onclick=\"getExample(\\'' + lastElementName + '\\', \\'' + lastElementName + '.' + targetId + '\\');\">&lt;...&gt;</span><br/>');\n"
                + "}}});\n"
                + "if($(currentElement).children().length === 0){\n"
                + "var elementText = $(currentElement).text().trim();\n"
                + "if(elementText.length > 0){\n"
                + "$(\"#\" + targetId).first().append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<pre style=\"color:grey\">' + elementText + '</pre><br/>');\n"
                + "}\n"
                + "}\n"
                + "$(\"#\" + targetId).first().append('<span style=\"color:purple\">&lt;</span><span style=\"color:red\">/</span><span style=\"color:blue\">' + currentElement.nodeName + '</span><span style=\"color:purple\">&gt;</span>');\n"
                + "});\n"
                + "\n"
                //+ "/*\n"
                //+ "$(this).find(tagName).children().each(function() {\n"
                //+ "alert((this).nodeName);\n"
                //+ "//$(\"#\" + targetId).append((this).nodeName));\n"
                //+ "$(\"#\" + targetId).append((this).text().replace(/&/g, \"&amp;\").replace(/</g, \"&lt;\")\n"
                //+ ".replace(/>/g, \"&gt;\")\n"
                //+ ".replace(/\"/g, \"&quot;\")\n"
                //+ "+ \"<br />\");\n"
                //+ "\n"
                //+ "});\n"
                //+ "\n"
                //+ "$(data).find(tagName).each(function () {\n"
                //+ "  console.log($(this).text());\n"
                //+ "$(\"#\" + targetId).append($(this).text().replace(/&/g, \"&amp;\")\n"
                //+ ".replace(/</g, \"&lt;\")\n"
                //+ ".replace(/>/g, \"&gt;\")\n"
                //+ ".replace(/\"/g, \"&quot;\")\n"
                //+ "+ \"<br />\");\n"
                //+ "});\n"
                //+ "\n"
                //+ "\n"
                //+ "      $xml = $( data );\n"
                //+ "    $tagUseage = $xml.find(tagName);\n"
                //+ "    \n"
                //+ "  console.log($tagUseage);    \n"
                //+ "    \n"
                //+ "    $.each($tagUseage.attributes, function(i, attrib){\n"
                //+ "console.log(this.name);\n"
                //+ "console.log(this.value);\n"
                //+ "\n"
                //+ "  //  var name = attrib.name;\n"
                //+ "    //var value = attrib.value;\n"
                //+ "//    $( \"#\" + targetId).append('<div>' + name + '</div>');      \n"
                //+ "  \n"
                //+ "    });\n"
                //+ "    */\n"
                //+ "//    $tagUseage.attr().each(function () {\n"
                //+ "//   $( \"#\" + targetId).append('<div>' + this.name() + '</div>');      \n"
                //+ "   // })\n"
                //+ "\n"
                //+ "    \n"
                //+ "    \n"
                //+ "    // document.getElementById('xmlResult').innerHTML =\n"
                //+ "    //$xml.childNodes[0].attr(\"appNameDisplay\");\n"
                //+ "    \n"
                //+ "    \n"
                //+ "    \n"
                //+ "    \n"
                //+ "    \n"
                //+ "    // $presenter = $xml.find( \"presenter\" );\n"
                //+ "    \n"
                //+ "    // document.getElementById('xmlResult').innerHTML =\n"
                //+ "    //   $presenter.attr(\"self\");\n"
                //+ "    //xml.childNodes[0].attr(\"\");\n"
                //+ "    // data.getElementsByTagName(\"Experiment\")[0].childNodes[0].nodeValue;\n"
                + "    });\n"
                + "    }\n"
                + "    \n"
                + "  </script>"
                + "    </head>\n"
                + "    <body>\n");
    }

    private void addAttributes(Writer writer, DocumentationElement currentElement) throws IOException {
        // sort the attributeTypes alphabetically
        currentElement.attributeTypes.sort(new AbstractComparator<DocumentationAttribute>() {
            @Override
            public int compare(DocumentationAttribute o1, DocumentationAttribute o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        for (DocumentationAttribute attributeTypes : currentElement.attributeTypes) {
            writer.append("<span style=\"color:green\">");
            writer.append(attributeTypes.name);
            writer.append("</span><span style=\"color:grey\">=&quot;");
            if (attributeTypes.typeLabel != null) {
                writer.append(attributeTypes.typeLabel);
            }
            if (attributeTypes.typeLabel != null && attributeTypes.restriction != null) {
                writer.append(" ");
            }
            if (attributeTypes.restriction != null) {
                writer.append("[");
                boolean isFirst = true;
                for (String value : attributeTypes.restriction) {
                    if (!isFirst) {
                        writer.append(", ");
                    }
                    isFirst = false;
                    writer.append(value);
                }
                writer.append("]");
            }
            if (attributeTypes.optional) {
                writer.append(" (optional)");
            }
            writer.append("&quot;</span><span>\n");
            writer.append(attributeTypes.documentation);
            writer.append("</span><br/>\n");
        }
        if (currentElement.allowsCustomImplementation) {
            writer.append("<span style=\"color:green\">");
            writer.append("*");
            writer.append("</span><span style=\"color:grey\">=&quot;");
            writer.append("Custom attributes can be assigned in the plugin associated with this feature");
            writer.append("&quot;</span><br/>\n");
        }
    }

    private void addElement(Writer writer, DocumentationElement currentElement) throws IOException {
        writer.append("<h3 id=\"" + currentElement.typeName + "\" style=\"text-transform: uppercase;\">\n");
        writer.append(currentElement.elementName);
        writer.append("\n</h3>\n");
        if (currentElement.documentationText != null) {
            writer.append(currentElement.documentationText);
        }
        writer.append("\n<br/><br/><table>\n");
        writer.append("<tr><td>\n");
        writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">");
        writer.append(currentElement.elementName);
        writer.append("</span></td><td>");
        addAttributes(writer, currentElement);
//        writer.append("</td><td>");
        if (currentElement.childElements.length == 0 && currentElement.childTypeNames.length == 0 && !currentElement.hasStringContents) {
            writer.append("</td><td><span style=\"color:red\">/</span>");
        }
        writer.append("<span style=\"color:purple\">&gt;</span></td><td>\n");
        if (currentElement.childElements.length > 0 || currentElement.childTypeNames.length > 0) {
            writer.append("</td></tr><tr><td></td><td>\n");
        }
//        List<String> childTypeNames = new ArrayList<>(Arrays.asList(currentElement.childTypeNames));
//        childTypeNames.sort(new AbstractComparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        if (currentElement.childTypeNames.length + currentElement.childElements.length > 0) {
            if (currentElement.childTypeNames.length + currentElement.childElements.length > 10) {
                writer.append("<button");
                writer.append(" id=\"");
                writer.append(currentElement.elementName);
                writer.append("ChildShow\" style=\"display: block;\" onclick=\"$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildDocumentation').show();$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildHide').show();$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildShow').hide();\">show ");
                writer.append(Integer.toString(currentElement.childTypeNames.length + currentElement.childElements.length));
                writer.append(" truncated items</button>\n");
                writer.append("<button");
                writer.append(" id=\"");
                writer.append(currentElement.elementName);
                writer.append("ChildHide\" style=\"display: none;\" onclick=\"$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildDocumentation').hide();$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildHide').hide();$('#");
                writer.append(currentElement.elementName);
                writer.append("ChildShow').show();\">hide ");
                writer.append(Integer.toString(currentElement.childTypeNames.length + currentElement.childElements.length));
                writer.append(" items</button>\n");
            }
            writer.append("<table");
            if (currentElement.childTypeNames.length > 10) {
                writer.append(" id=\"");
                writer.append(currentElement.elementName);
                writer.append("ChildDocumentation\" style=\"display: none;\"");
            }
            writer.append(">\n");
            for (String childElement : currentElement.childTypeNames) {
                writer.append("<tr><td>\n");

                writer.append("<a href=\"#" + childElement + "Type\">");
                writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">");
                writer.append(childElement);
                writer.append("</span><span style=\"color:purple\">&gt;</span>\n");
                writer.append("</a>");
                writer.append("</td></tr>\n");
            }
            for (DocumentationElement childElement : currentElement.childElements) {
                writer.append("<tr><td>\n");
                writer.append("<a href=\"#" + childElement.typeName + "\">");
                writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:blue\">");
                writer.append(childElement.elementName);
                writer.append("</span>");
                writer.append("</a>");
                writer.append("</td><td>");
                addAttributes(writer, childElement);
                writer.append("</td><td><span style=\"color:purple\">&gt;</span>\n");
                writer.append("</td></tr>\n");
            }
            writer.append("</table>\n");
        }
        writer.append("</td>\n");
        writer.append("</tr>\n");
        if (currentElement.hasStringContents) {
            writer.append("<tr>\n");
            writer.append("<td></td><td></td><td>\n");
            writer.append("<span style=\"color:grey\">String</span></td>");
            writer.append("</tr>\n");
        }
        if (currentElement.childElements.length > 0 || currentElement.childTypeNames.length > 0 || currentElement.hasStringContents) {
            writer.append("<tr>\n");
            writer.append("<td>\n");
            writer.append("<span style=\"color:purple\">&lt;</span><span style=\"color:red\">/</span><span style=\"color:blue\">");
            writer.append(currentElement.elementName);
            writer.append("</span><span style=\"color:purple\">&gt;</span></td>");
            writer.append("</tr>\n");
        }
        writer.append("</table>\n");
        writer.append("<table style=\"width: 100%;\">\n");
        writer.append("<tr>\n");
        writer.append("<td colspan='2' id='" + currentElement.elementName + "Target'>\n");
        writer.append("<button onclick=\"getExample('" + currentElement.elementName + "', '" + currentElement.elementName + "Target');\">search for examples</button>\n");
        writer.append("</td>");
        writer.append("</tr>\n");
        writer.append("</table>\n");
        for (DocumentationElement childElement : currentElement.childElements) {
            // omitting the translation elements here because they are already defined in their parent elements
            if (!childElement.elementName.equals("translation")) {
                addElement(writer, childElement);
            }
        }
    }

//    private void addFeature(Writer writer, final FeatureType featureType, FeatureType[] sortedFeatureTypes) throws IOException {
//        writer.append("<tr><td>\n");
//        writer.append("<div class=\"complexType\">&lt;").append(featureType.name()).append("&gt;<br/>\n");
//        writer.append("</td><td>\n");
//        if (featureType.canHaveText()) {
//            writer.append("featureText<br/>\n");
//        }
//        if (featureType.getFeatureAttributes() != null) {
//            for (final FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
//                if (!featureAttribute.isOptional() && !featureType.allowsCustomImplementation()) {
//                    writer.append("*");
//                }
//                writer.append(featureAttribute.name());
//                writer.append("<br/>\n");
//            }
//        }
//        if (featureType.canHaveStimulusTags() && !featureType.isCanHaveRandomGrouping()) {
//            writer.append("tags<br/>\n");
//        }
//        if (featureType.allowsCustomImplementation()) {
//            writer.append("class<br/>\n");
//        }
//        writer.append("</td><td>\n");
////        writer.append("&gt;<br/>\n");
//        if (featureType.canHaveFeatures()) {
//            writer.append("can have features<br/>\n");
////            for (final FeatureType featureRef : sortedFeatureTypes) {
////                if (featureRef.isChildType(FeatureType.Contitionals.none) || featureRef.isChildType(featureType.getRequiresChildType())
////                        || featureRef.isChildType(FeatureType.Contitionals.groupNetworkAction) // currently allowing all groupNetworkAction in any element
////                        || featureRef.isChildType(FeatureType.Contitionals.stimulusAction) // currently allowing all stimulusAction in any element
////                        ) {
////                    writer.append("<div class=\"element\">\"").append(featureRef.name()).append(featureRef.name()).append("\"\n");
////                }
////            }
////            writer.append("</div>\n");
//        } else {
//            switch (featureType.getRequiresChildType()) {
//                case hasTrueFalseCondition:
//                    writer.append("&lt;conditionTrue&gt;<br/>\n");
//                    writer.append("&lt;conditionFalse&gt;<br/>\n");
//                    break;
//                case hasCorrectIncorrect:
//                    writer.append("&lt;responseCorrect&gt;<br/>\n");
//                    writer.append("&lt;responseIncorrect&gt;<br/>\n");
//                    break;
//                case hasMoreStimulus:
//                    writer.append("&lt;hasMoreStimulus&gt;<br/>\n");
//                    writer.append("&lt;endOfStimulus&gt;<br/>\n");
//                    writer.append("&lt;randomGrouping&gt;<br/>\n");
//                    writer.append("&lt;stimuli&gt;<br/>\n");
//                    break;
//                case hasErrorSuccess:
//                    writer.append("&lt;onError&gt;<br/>\n");
//                    writer.append("&lt;onSuccess&gt;<br/>\n");
//                    break;
//                case hasUserCount:
//                    writer.append("&lt;multipleUsers&gt;<br/>\n");
//                    writer.append("&lt;singleUser&gt;<br/>\n");
//                    break;
//                case hasThreshold:
//                    writer.append("&lt;aboveThreshold&gt;<br/>\n");
//                    writer.append("&lt;withinThreshold&gt;<br/>\n");
//                    break;
//                case groupNetworkActivity:
//                    writer.append("&lt;groupNetworkActivity&gt;<br/>\n");
//                    writer.append("&lt;sendGroupEndOfStimuli&gt;<br/>\n");
//                    break;
//                case hasMediaPlayback:
//                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
//                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
//                    writer.append("&lt;mediaPlaybackComplete&gt;<br/>\n");
//                    break;
//                case hasMediaRecorderPlayback:
//                    writer.append("&lt;onError&gt;<br/>\n");
//                    writer.append("&lt;onSuccess&gt;<br/>\n");
//                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
//                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
//                    writer.append("&lt;mediaPlaybackComplete&gt;<br/>\n");
//                    break;
//                case hasMediaLoading:
//                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
//                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
//                    break;
//                case hasMediaLoadingButton:
//                    writer.append("&lt;mediaLoaded&gt;<br/>\n");
//                    writer.append("&lt;mediaLoadFailed&gt;<br/>\n");
//                    writer.append("&lt;onActivate&gt;<br/>\n");
//                    break;
////                case needsConditionalParent:
////                    break;
//                case none:
//                    break;
//                default:
//                    for (FeatureType featureType1 : sortedFeatureTypes) {
//                        if (featureType1.isChildType(featureType.getRequiresChildType())) {
//                            writer.append("&lt;").append(featureType1.name()).append("&gt;<br/>\n");
//                        }
//                    }
//                    if (featureType.canHaveStimulusTags() && featureType.isCanHaveRandomGrouping()) {
//                        writer.append("&lt;randomGrouping&gt;<br/>\n");
//                        writer.append("&lt;stimuli&gt;<br/>\n");
//                    }
//                    break;
//            }
//        }
//        writer.append("</td>\n");
//        writer.append("</tr>\n");
//    }
    private void getEnd(Writer writer) throws IOException {
        writer.append("    </body>\n"
                + "</html>\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        addElement(writer, rootElement);
        FeatureType[] sortedFeatureTypes = FeatureType.values();
        Arrays.sort(sortedFeatureTypes, new AbstractComparator<FeatureType>() {
            @Override
            public int compare(FeatureType o1, FeatureType o2) {
                return o1.name().compareTo(o2.name());
            }
        });
//        addElement(writer, rootElement.childElements[1]);
        writer.append("<div style=\"background-color:#add8e630;border: 1px solid black;\">\n");
        writer.append("<h2 id=\"...General Features...Type\">General Features</h2><table border=1>\n");
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.none)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        writer.append("</div><br/><br/>\n");
        writer.append("<div style=\"background-color:#adc3e630;border: 1px solid black;\">\n");
        writer.append("<h2 id=\"...Stimulus Features...Type\">Stimulus Features</h2><table border=1>\n");
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.stimulusAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        writer.append("</div><br/><br/>\n");
        writer.append("<div style=\"background-color:#c3ade630;border: 1px solid black;\">\n");
        writer.append("<h2 id=\"...Group Features...Type\">Group Features</h2><table border=1>\n");
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        writer.append("</div><br/><br/>\n");
        writer.append("<div style=\"background-color:#ade6d930;border: 1px solid black;\">\n");
        writer.append("<h2 id=\"...Other Specialised Features...Type\">Other Specialised Features</h2><table border=1>\n");
        for (FeatureType featureType : sortedFeatureTypes) {
            if (!featureType.isChildType(FeatureType.Contitionals.none)
                    && !featureType.isChildType(FeatureType.Contitionals.stimulusAction)
                    && !featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        writer.append("</div>\n");
//        for (final FeatureType featureRef : sortedFeatureTypes) {
//                if (featureRef.isChildType(FeatureType.Contitionals.none)) {
//                    childList.add(new DocumentationElement(featureRef));
//                }
//            }
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
