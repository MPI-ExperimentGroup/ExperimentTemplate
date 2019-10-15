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

/**
 * @since May 9, 2018 5:41:05 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaGenerator extends AbstractSchemaGenerator {

    private void getStart(Writer writer) throws IOException {
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n");
    }

    private void addExperiment(Writer writer, FeatureAttribute[] featureAttributes) throws IOException {
        writer.append("<xs:simpleType name=\"rgbHexValue\">\n");
        writer.append("<xs:restriction base=\"xs:token\">\n");
        writer.append("<xs:pattern value=\"#[\\dA-Fa-f]{6}\"/>\n");
        writer.append("</xs:restriction>\n");
        writer.append("</xs:simpleType>\n");
        writer.append("<xs:simpleType name=\"rangesValue\">\n");
        writer.append("<xs:restriction base=\"xs:token\">\n");
        writer.append("<xs:pattern value=\"[0-9]*((..|,)[0-9]+)*\"/>\n");
        writer.append("</xs:restriction>\n");
        writer.append("</xs:simpleType>\n");
        writer.append("<xs:simpleType name=\"lowercaseValue\">\n");
        writer.append("<xs:restriction base=\"xs:string\">\n");
        writer.append("<xs:pattern value=\"[a-z]([a-z_0-9]){3,}\"/>\n");
        writer.append("</xs:restriction>\n");
        writer.append("</xs:simpleType>\n");
        writer.append("<xs:simpleType name=\"presenterName\">\n");
        writer.append("<xs:restriction base=\"xs:string\">\n");
        writer.append("<xs:pattern value=\"[a-zA-Z_0-9]{3,}\"/>\n");
        writer.append("</xs:restriction>\n");
        writer.append("</xs:simpleType>\n");
        writer.append("<xs:simpleType name=\"integerList\">\n");
        writer.append("<xs:list itemType=\"xs:integer\"/>\n");
        writer.append("</xs:simpleType>\n");
        for (FeatureAttribute attribute : featureAttributes) {
            if (attribute.getTypeValues() != null) {
                writer.append("<xs:simpleType name=\"");
                writer.append(attribute.name());
                writer.append("Type\">\n");
                writer.append("<xs:restriction>\n");
                writer.append("<xs:simpleType>\n");
                writer.append("<xs:list>\n");
                writer.append("<xs:simpleType>\n");
                writer.append("<xs:restriction base=\"xs:token\">\n");
                for (String value : attribute.getTypeValues()) {
                    writer.append("<xs:enumeration value=\"");
                    writer.append(value);
                    writer.append("\"/>\n");
                }
                writer.append("</xs:restriction>\n");
                writer.append("</xs:simpleType>\n");
                writer.append("</xs:list>\n");
                writer.append("</xs:simpleType>\n");
//                writer.append("<xs:minLength value=\"1\"/>\n");
                writer.append("</xs:restriction>\n");
                writer.append("</xs:simpleType>\n");
//                writer.append("</xs:attribute>\n");
            }
        }
    }
// todo: constrain back to always refer to an presenter that exists
////        writer.append("<xs:assert test=\"count(*/metadataField) le 0\"/>"); // todo: apply this test to enforce presenter type constraints if possible

    private void addAttributes(final Writer writer, final DocumentationElement currentElement) throws IOException {
        for (DocumentationAttribute attributeTypes : currentElement.attributeTypes) {
            writer.append("<xs:attribute name=\"").append(attributeTypes.name);
            if (attributeTypes.type != null) {
                writer.append("\" type=\"").append(attributeTypes.type);
            }
//            writer.append("\" use=\"" + ((attributeTypes.optional) ? "optional" : "required") + "\"" + ((attributeTypes.restriction == null) ? "/" : "") + ">\n");
            if (!attributeTypes.optional) {
                writer.append("\" use=\"required");
            }
            writer.append(((!(attributeTypes.restriction != null && attributeTypes.type == null)) ? "\"/" : "\"") + ">\n");
            if (attributeTypes.restriction != null && attributeTypes.type == null) {
                writer.append("<xs:simpleType>\n");
                writer.append("<xs:restriction>\n");
                writer.append("<xs:simpleType>\n");
                writer.append("<xs:list>\n");
                writer.append("<xs:simpleType>\n");
                writer.append("<xs:restriction base=\"xs:token\">\n");
                for (String value : attributeTypes.restriction) {
                    writer.append("<xs:enumeration value=\"");
                    writer.append(value);
                    writer.append("\"/>\n");
                }
                writer.append("</xs:restriction>\n");
                writer.append("</xs:simpleType>\n");
                writer.append("</xs:list>\n");
                writer.append("</xs:simpleType>\n");
                writer.append("<xs:minLength value=\"1\"/>\n");
                writer.append("</xs:restriction>\n");
                writer.append("</xs:simpleType>\n");
                writer.append("</xs:attribute>\n");
            }
        }
        if (currentElement.allowsCustomImplementation) {
            writer.append("<xs:attribute name=\"class\" type=\"xs:string\"/>\n");
            writer.append("<xs:anyAttribute  processContents=\"lax\"/>\n");

            writer.append("<xs:assert test=\"(@class) or (not(@class) ");
            for (final DocumentationAttribute featureAttribute : currentElement.attributeTypes) {
                if (!featureAttribute.optional) {
                    writer.append(" and @");
                    writer.append(featureAttribute.name);
                }
            }
            writer.append(")\"/>\n");
        }
//            // add documentation indicating that when true the menu bar will be hidden when no back value is given
////            The showMenuBar attribute has a relation to the back attribute. This is because if you provide a back attribute, then you presumably want it to be shown. 
////
////For example:
////
////showMenuBar="true"
////<presenter title="Toestemming"
////<presenter back="Toestemming" title=“Welcome">
////Shown in both cases.
////
////showMenuBar="false"
////<presenter title="Toestemming"
////<presenter back="Toestemming" title=“Welcome">
////Shown in the second case but not the first.
////
////
////There are some more complex cases to be considered here, but this is the reason it still shows the title bar with your configuration.
    }

    private void addElement(final Writer writer, final DocumentationElement currentElement, final boolean insertType) throws IOException {
        final boolean isPresenterType = "presenter".equals(currentElement.elementName);
        if (insertType) {
            writer.append("<xs:complexType name=\"");
            writer.append(currentElement.typeName);
        } else {
            writer.append("<xs:element name=\"");
            writer.append(currentElement.elementName);
        }
        writer.append("\">\n");
        if (!insertType) {
            writer.append("<xs:complexType>\n");
            if (currentElement.elementName.equals("experiment")) {
                writer.append("<xs:sequence");
                if (currentElement.minBounds != 1) {
                    writer.append(" minOccurs=\"");
                    writer.append(Integer.toString(currentElement.minBounds));
                }
                if (currentElement.maxBounds != 1) {
                    writer.append("\" maxOccurs=\"");
                    writer.append((currentElement.maxBounds > 0) ? Integer.toString(currentElement.maxBounds) : "unbounded");
                    writer.append("\"\n");
                }
                writer.append(">\n");
            }
        } else if (!isPresenterType && (currentElement.childElements.length > 0 || currentElement.childTypeNames.length > 0)) {
//            writer.append("<xs:").append((currentElement.areChildenOptional) ? "choice minOccurs=\"0\" maxOccurs=\"unbounded\"" : "all").append(">\n");
            writer.append("<xs:").append((currentElement.childOption == ChildType.choiceAnyCount) ? "choice minOccurs=\"0\" maxOccurs=\"unbounded\"" : (currentElement.childOption == ChildType.sequenceOnceOrdered) ? "sequence" : "all").append(">\n");
//            writer.append("<xs:sequence1>");
        }
        if (currentElement.documentationText != null) {
            writer.append("<xs:annotation>\n");
            writer.append("<xs:documentation>");
            writer.append(currentElement.documentationText);
            writer.append("</xs:documentation>\n");
            writer.append("</xs:annotation>\n");
        }
        if (isPresenterType) {
            writer.append("<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
        }
        if (currentElement.childTypeNames.length > 0) {
            if (currentElement.childOption == ChildType.choiceAnyCount) {
//                writer.append("<xs:choice2 minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
            }
            for (String childTypeName : currentElement.childTypeNames) {
                writer.append("<xs:element name=\"");
                writer.append(childTypeName);
                writer.append("\" type=\"");
                writer.append(childTypeName);
                writer.append("Type\"/>\n");
            }
            if (currentElement.childOption == ChildType.choiceAnyCount) {
//                writer.append("</xs:choice3>\n");
            }
        }
        for (DocumentationElement childElement : currentElement.childElements) {
            writer.append("<xs:element name=\"");
            writer.append(childElement.elementName);
            if (childElement.minBounds != 1) {
                writer.append("\" minOccurs=\"");
                writer.append(Integer.toString(childElement.minBounds));
            }
            if (childElement.maxBounds != 1) {
                writer.append("\" maxOccurs=\"");
                writer.append((childElement.maxBounds > 0) ? Integer.toString(childElement.maxBounds) : "unbounded");
            }
            if (childElement.childElements.length > 0 || "presenter".equals(childElement.elementName)) {
                writer.append("\" type=\"");
                writer.append(childElement.typeName);
                writer.append("\"/>\n");
            } else if (childElement.hasStringContents) {
                if (childElement.attributeTypes.size() > 0) {
                    writer.append("\">\n");
                    writer.append("<xs:complexType>\n");
                    writer.append("<xs:simpleContent>\n");
                    writer.append("<xs:extension base=\"xs:string\">\n");
                    addAttributes(writer, childElement);
                    writer.append("</xs:extension>\n");
                    writer.append("</xs:simpleContent>\n");
                    writer.append("</xs:complexType>\n");
                    writer.append("</xs:element>\n");
                } else {
                    writer.append("\" type=\"xs:string\"/>\n");
                }
            } else {
                writer.append("\">\n");
                writer.append("<xs:complexType>\n");
                addAttributes(writer, childElement);
                writer.append("</xs:complexType>\n");
                writer.append("</xs:element>\n");
            }
        }
        if (isPresenterType) {
            writer.append("</xs:choice>\n");
        }
        if (!insertType) {
            if (currentElement.elementName.equals("experiment")) {
                writer.append("</xs:sequence>\n");
            }
        } else if (!isPresenterType && (currentElement.childElements.length > 0 || currentElement.childTypeNames.length > 0)) {
//            writer.append("</xs:").append((currentElement.areChildenOptional) ? "choice" : "all").append(">\n");
            writer.append("</xs:").append((currentElement.childOption == ChildType.choiceAnyCount) ? "choice" : (currentElement.childOption == ChildType.sequenceOnceOrdered) ? "sequence" : "all").append(">\n");
//            writer.append("</xs:sequence>");
        }
        if (!currentElement.attributeTypes.isEmpty()) {
            addAttributes(writer, currentElement);
//            writer.append("</xs:complexType>\n");
//            writer.append("</xs:element>\n");
        } else {
//            writer.append("\"/>\n");
        }
        writer.append("</xs:complexType>\n");
        if (!insertType) {
            writer.append("</xs:element>\n");
        }
    }

//    private void addFeature(Writer writer, final DocumentationElement featureType) throws IOException {
//        writer.append("<xs:complexType name=\"").append(featureType.typeName).append("\">\n");
//        if (featureType.childTypeNames.length > 0 || featureType.childElements.length > 0) {
////            List<String> childTypeNames = new ArrayList<>(Arrays.asList(featureType.childTypeNames));
////            childTypeNames.sort(new AbstractComparator<String>() {
////                @Override
////                public int compare(String o1, String o2) {
////                    return o1.compareTo(o2);
////                }
////            });
//            writer.append("<xs:").append((featureType.areChildenOptional) ? "choice minOccurs=\"0\" maxOccurs=\"unbounded\"" : "all").append(">\n");
//            for (final String childType : featureType.childTypeNames) {
//                writer.append("<xs:element name=\"").append(childType).append("\" type=\"").append(childType).append("Type\"").append((featureType.areChildenOptional) ? "" : " minOccurs=\"1\" maxOccurs=\"1\"").append("/>\n");
//            }
//            for (final DocumentationElement childType : featureType.childElements) {
//                writer.append("<xs:element name=\"").append(childType.elementName).append("\" type=\"").append(childType.typeName).append("\"").append((featureType.areChildenOptional) ? "" : " minOccurs=\"0\" maxOccurs=\"1\"").append("/>\n");
//            }
//            writer.append("</xs:").append((featureType.areChildenOptional) ? "choice" : "all").append(">\n");
//        }
////        else {
////            switch (featureType.getRequiresChildType()) {
////                case hasTrueFalseCondition:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"conditionTrue\" type=\"conditionTrueType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"conditionFalse\" type=\"conditionFalseType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasCorrectIncorrect:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"responseCorrect\" type=\"responseCorrectType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"responseIncorrect\" type=\"responseIncorrectType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasMoreStimulus:
////                    //        For the XML Usage page, I also noticed that loadStimulus requires hasMoreStimulus and endOfStimulus. RandomGrouping and stimuli are missing here. Probably the same problem with mediaLoaded and mediaLoadFailed.
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"hasMoreStimulus\" type=\"hasMoreStimulusType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"endOfStimulus\" type=\"endOfStimulusType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"randomGrouping\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"stimuli\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasErrorSuccess:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"onError\" type=\"onErrorType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"onSuccess\" type=\"onSuccessType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasUserCount:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"multipleUsers\" type=\"multipleUsersType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"singleUser\" type=\"singleUserType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasThreshold:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"aboveThreshold\" type=\"aboveThresholdType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"withinThreshold\" type=\"withinThresholdType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case groupNetworkActivity:
////                    writer.append("<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
////                    writer.append("<xs:element name=\"groupNetworkActivity\" type=\"groupNetworkActivityType\"/>\n");
////                    writer.append("<xs:element name=\"sendGroupEndOfStimuli\" type=\"sendGroupEndOfStimuliType\"/>\n");
////                    writer.append("</xs:choice>\n");
////                    break;
////                case hasMediaPlayback:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaPlaybackStarted\" type=\"mediaPlaybackStartedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaPlaybackComplete\" type=\"mediaPlaybackCompleteType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasMediaRecorderPlayback:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"onError\" type=\"onErrorType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"onSuccess\" type=\"onSuccessType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaPlaybackStarted\" type=\"mediaPlaybackStartedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaPlaybackComplete\" type=\"mediaPlaybackCompleteType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasMediaLoading:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
////                case hasMediaLoadingButton:
////                    writer.append("<xs:all>\n");
////                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("<xs:element name=\"onActivate\" type=\"onActivateType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
////                    writer.append("</xs:all>\n");
////                    break;
//////                case needsConditionalParent:
//////                    break;
////                case none:
////                    break;
////                default:
////                    writer.append("<xs:all>\n");
////                    for (FeatureType featureType1 : FeatureType.values()) {
////                        if (featureType1.isChildType(featureType.getRequiresChildType())) {
////                            writer.append("<xs:element name=\"").append(featureType1.name()).append("\" type=\"").append(featureType1.name()).append("Type\"/>\n");
////                        }
////                    }
////                    if (featureType.canHaveStimulusTags() && featureType.isCanHaveRandomGrouping()) {
////                        writer.append("<xs:element name=\"randomGrouping\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
////                        writer.append("<xs:element name=\"stimuli\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
////                    }
////                    writer.append("</xs:all>\n");
////                    break;
////            }
////        }
//        // todo: canHaveStimulus check for currentStimulusHasTag etc
//        if (featureType.hasStringContents) {
//            writer.append("<xs:attribute name=\"featureText\" type=\"xs:string\" use=\"required\"/>\n");
//        }
////        if (featureType.attributeTypes != null) {
//        // todo: hotKey needs to have a list of valid strings as should styleName
//        // todo: msToNext must always and only contain positive integers
//        for (final DocumentationAttribute featureAttribute : featureType.attributeTypes) {
//            writer.append("<xs:attribute name=\"");
//            writer.append(featureAttribute.name);
////                if (featureAttribute.name().equals("ranges")) {
//            writer.append("\" type=\"").append(featureAttribute.type).append("\"");
////                } else {
////                    writer.append("\" type=\"xs:string\"");
////                }
//            if (!featureAttribute.optional /*&& !featureType.allowsCustomImplementation()*/) {
//                writer.append(" use=\"required\"");
//            }
//            writer.append("/>\n");
//        }
////        }
////        if (featureType.canHaveStimulusTags() && !featureType.isCanHaveRandomGrouping()) {
////            writer.append("<xs:attribute name=\"tags\" type=\"xs:string\" use=\"required\"/>\n");
////        }
//        if (featureType.allowsCustomImplementation) {
//            writer.append("<xs:attribute name=\"class\" type=\"xs:string\"/>\n");
//            writer.append("<xs:anyAttribute  processContents=\"lax\"/>\n");
//
//            writer.append("<xs:assert test=\"(@class) or (not(@class) ");
//            for (final DocumentationAttribute featureAttribute : featureType.attributeTypes) {
//                if (!featureAttribute.optional) {
//                    writer.append(" and @");
//                    writer.append(featureAttribute.name);
//                }
//            }
//            writer.append(")\"/>\n");
//        }
//        writer.append("</xs:complexType>\n");
//    }
    private void getEnd(Writer writer) throws IOException {
        writer.append("</xs:schema>\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        addExperiment(writer, FeatureAttribute.values());
        addElement(writer, rootElement, false);
        addElement(writer, rootElement.childElements[1], true);
        addElement(writer, rootElement.childElements[1].childElements[2], true);
        addElement(writer, rootElement.childElements[3], true);
        addElement(writer, rootElement.childElements[4], true);
        addElement(writer, rootElement.childElements[5], true);
        for (FeatureType featureType : FeatureType.values()) {
            addElement(writer, new DocumentationElement(featureType), true);
        }
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[0], true);
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[1], true);
        getEnd(writer);
        System.out.println(writer);
    }

    public void createSchemaFile(final File schemaOutputFile) throws IOException {
        FileWriter schemaOutputWriter = new FileWriter(schemaOutputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        appendContents(bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
