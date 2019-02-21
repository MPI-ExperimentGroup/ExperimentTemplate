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

    private void addExperiment(Writer writer) throws IOException {
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
        writer.append("<xs:simpleType name=\"integerList\">\n");
        writer.append("<xs:list itemType=\"xs:integer\"/>\n");
        writer.append("</xs:simpleType>\n");
    }

    private void addAttributes(final Writer writer, final DocumentationElement currentElement) throws IOException {
        for (DocumentationAttribute attributeTypes : currentElement.attributeTypes) {
            writer.append("<xs:attribute name=\"").append(attributeTypes.name);
            if (attributeTypes.restriction == null) {
                writer.append("\" type=\"").append(attributeTypes.type);
            }
            writer.append("\" use=\"" + ((attributeTypes.optional) ? "optional" : "required") + "\"" + ((attributeTypes.restriction == null) ? "/" : "") + ">\n");
            if (attributeTypes.restriction != null) {
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
    }

    private void addElement(final Writer writer, final DocumentationElement currentElement, final boolean insertType) throws IOException {
//        for (final PresenterType presenterType : presenterTypes) {
//            writer.append("<xs:element ref=\"").append(presenterType.name()).append("\"/>\n");
//        }
//        for (DocumentationElement chileElement : rootElement.childElements) {
        if (insertType) {
            writer.append("<xs:complexType name=\"");
        } else {
            writer.append("<xs:element name=\"");
        }
        writer.append(currentElement.elementName);
        if (insertType) {
            writer.append("Type");
        }
        writer.append("\">\n");
        if (!insertType) {
            writer.append("<xs:complexType>\n");
            writer.append("<xs:sequence");
            writer.append(" minOccurs=\"");
            writer.append(Integer.toString(currentElement.minBounds));
            writer.append("\" maxOccurs=\"");
            writer.append((currentElement.maxBounds > 0) ? Integer.toString(currentElement.maxBounds) : "unbounded");
            writer.append("\">\n");
        } else {
            writer.append("<xs:sequence>\n");
        }
        writer.append("<xs:annotation>\n");
        writer.append("<xs:documentation>");
        writer.append(currentElement.documentationText);
        writer.append("</xs:documentation>\n");
        writer.append("</xs:annotation>\n");
        if (currentElement.childTypeNames.length > 0) {
            writer.append("<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
            for (String childTypeName : currentElement.childTypeNames) {
                writer.append("<xs:element name=\"");
                writer.append(childTypeName);
                writer.append("\" type=\"");
                writer.append(childTypeName);
                writer.append("Type\"/>\n");
            }
            writer.append("</xs:choice>\n");
        }
        for (DocumentationElement childElement : currentElement.childElements) {
            writer.append("<xs:element name=\"");
            writer.append(childElement.elementName);
            writer.append("\" minOccurs=\"");
            writer.append(Integer.toString(childElement.minBounds));
            writer.append("\" maxOccurs=\"");
            writer.append((childElement.maxBounds > 0) ? Integer.toString(childElement.maxBounds) : "unbounded");
            if (childElement.childElements.length > 0 || "presenter".equals(childElement.elementName)) {
                writer.append("\" type=\"");
                writer.append(childElement.elementName);
                writer.append("Type");
                writer.append("\"/>\n");
            } else if (childElement.hasStringContents) {
                writer.append("\" type=\"xs:string");
                writer.append("\"/>\n");
            } else {
                writer.append("\">\n");
                writer.append("<xs:complexType>\n");
                addAttributes(writer, childElement);
                writer.append("</xs:complexType>\n");
                writer.append("</xs:element>\n");
            }
        }
        writer.append("</xs:sequence>\n");
        if (!currentElement.attributeTypes.isEmpty()) {
            addAttributes(writer, currentElement);

//            writer.append("</xs:complexType>\n");
//            writer.append("</xs:element>\n");
        } else {
//            writer.append("\"/>\n");
        }

//        }
//        writer.append("<xs:element name=\"administration\" type=\"administrationType\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
//        writer.append("<xs:element name=\"scss\" type=\"xs:string\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
//        writer.append("<xs:element name=\"metadata\" type=\"metadataType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
//        writer.append("<xs:element name=\"presenter\"  minOccurs=\"1\" maxOccurs=\"unbounded\" type=\"presenterType\"/>\n");
//        writer.append("<xs:element name=\"stimuli\" type=\"stimuliType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
//        writer.append("</xs:sequence>\n");
        writer.append("</xs:complexType>\n");
        if (!insertType) {
            writer.append("</xs:element>\n");
        }
    }

    private void addFeature(Writer writer, final FeatureType featureType) throws IOException {
        writer.append("<xs:complexType name=\"").append(featureType.name()).append("Type\">\n");
        if (featureType.canHaveFeatures()) {
            writer.append("<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
            for (final FeatureType featureRef : FeatureType.values()) {
                if (featureRef.getIsChildType() == FeatureType.Contitionals.none || featureRef.getIsChildType() == featureType.getRequiresChildType()
                        || featureRef.getIsChildType() == FeatureType.Contitionals.groupNetworkAction // currently allowing all groupNetworkAction in any element
                        || featureRef.getIsChildType() == FeatureType.Contitionals.stimulusAction // currently allowing all stimulusAction in any element
                        ) {
                    writer.append("<xs:element name=\"").append(featureRef.name()).append("\" type=\"").append(featureRef.name()).append("Type\"/>\n");
                }
            }
            writer.append("</xs:choice>\n");
        } else {
            switch (featureType.getRequiresChildType()) {
                case hasTrueFalseCondition:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"conditionTrue\" type=\"conditionTrueType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"conditionFalse\" type=\"conditionFalseType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasCorrectIncorrect:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"responseCorrect\" type=\"responseCorrectType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"responseIncorrect\" type=\"responseIncorrectType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasMoreStimulus:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"hasMoreStimulus\" type=\"hasMoreStimulusType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"endOfStimulus\" type=\"endOfStimulusType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"randomGrouping\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"stimuli\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasErrorSuccess:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"onError\" type=\"onErrorType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"onSuccess\" type=\"onSuccessType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasUserCount:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"multipleUsers\" type=\"multipleUsersType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"singleUser\" type=\"singleUserType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasThreshold:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"aboveThreshold\" type=\"aboveThresholdType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"withinThreshold\" type=\"withinThresholdType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case groupNetworkActivity:
                    writer.append("<xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n");
                    writer.append("<xs:element name=\"groupNetworkActivity\" type=\"groupNetworkActivityType\"/>\n");
                    writer.append("<xs:element name=\"sendGroupEndOfStimuli\" type=\"sendGroupEndOfStimuliType\"/>\n");
                    writer.append("</xs:choice>\n");
                    break;
                case hasMediaPlayback:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaPlaybackStarted\" type=\"mediaPlaybackStartedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaPlaybackComplete\" type=\"mediaPlaybackCompleteType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasMediaRecorderPlayback:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"onError\" type=\"onErrorType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"onSuccess\" type=\"onSuccessType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaPlaybackStarted\" type=\"mediaPlaybackStartedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaPlaybackComplete\" type=\"mediaPlaybackCompleteType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasMediaLoading:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
                case hasMediaLoadingButton:
                    writer.append("<xs:all>\n");
                    writer.append("<xs:element name=\"mediaLoaded\" type=\"mediaLoadedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"mediaLoadFailed\" type=\"mediaLoadFailedType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("<xs:element name=\"onActivate\" type=\"onActivateType\" minOccurs=\"1\" maxOccurs=\"1\"/>\n");
                    writer.append("</xs:all>\n");
                    break;
//                case needsConditionalParent:
//                    break;
                case none:
                    break;
                default:
                    writer.append("<xs:all>\n");
                    for (FeatureType featureType1 : FeatureType.values()) {
                        if (featureType1.getIsChildType() == featureType.getRequiresChildType()) {
                            writer.append("<xs:element name=\"").append(featureType1.name()).append("\" type=\"").append(featureType1.name()).append("Type\"/>\n");
                        }
                    }
                    if (featureType.canHaveStimulusTags() && featureType.isCanHaveRandomGrouping()) {
                        writer.append("<xs:element name=\"randomGrouping\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
                        writer.append("<xs:element name=\"stimuli\" minOccurs=\"0\" maxOccurs=\"1\"/>\n");
                    }
                    writer.append("</xs:all>\n");
                    break;
            }
        }
        // todo: canHaveStimulus check for currentStimulusHasTag etc
        if (featureType.canHaveText()) {
            writer.append("<xs:attribute name=\"featureText\" type=\"xs:string\" use=\"required\"/>\n");
        }
        if (featureType.getFeatureAttributes() != null) {
            // todo: hotKey needs to have a list of valid strings as should styleName
            // todo: msToNext must always and only contain positive integers
            for (final FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                writer.append("<xs:attribute name=\"");
                writer.append(featureAttribute.name());
                if (featureAttribute.name().equals("ranges")) {
                    writer.append("\" type=\"rangesValue\"");
                } else {
                    writer.append("\" type=\"xs:string\"");
                }
                if (!featureAttribute.isOptional() && !featureType.allowsCustomImplementation()) {
                    writer.append(" use=\"required\"");
                }
                writer.append("/>\n");
            }
        }
        if (featureType.canHaveStimulusTags() && !featureType.isCanHaveRandomGrouping()) {
            writer.append("<xs:attribute name=\"tags\" type=\"xs:string\" use=\"required\"/>\n");
        }
        if (featureType.allowsCustomImplementation()) {
            writer.append("<xs:attribute name=\"class\" type=\"xs:string\"/>\n");
            writer.append("<xs:anyAttribute  processContents=\"lax\"/>\n");

            writer.append("<xs:assert test=\"(@class) or (not(@class) ");
            for (final FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                if (!featureAttribute.isOptional()) {
                    writer.append(" and @");
                    writer.append(featureAttribute.name());
                }
            }
            writer.append(")\"/>\n");
        }
        writer.append("</xs:complexType>\n");
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("</xs:schema>\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        addExperiment(writer);
        addElement(writer, rootElement, false);
        addElement(writer, rootElement.childElements[1], true);
        addElement(writer, rootElement.childElements[3], true);
        addElement(writer, rootElement.childElements[4], true);
        addElement(writer, rootElement.childElements[5], true);
        for (FeatureType featureType : FeatureType.values()) {
            addFeature(writer, featureType);
        }
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
