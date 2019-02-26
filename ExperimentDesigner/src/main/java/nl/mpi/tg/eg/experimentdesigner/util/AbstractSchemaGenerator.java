/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Feb 11, 2019 5:09:05 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AbstractSchemaGenerator {

    protected class DocumentationAttribute {

        final String name;
        final String documentation;
        final String type;
        final boolean optional;
        final String[] restriction;

        public DocumentationAttribute(final String name, final String documentation, final String type, final boolean optional, String[] restriction) {
            this.name = name;
            this.documentation = documentation;
            this.type = type;
            this.optional = optional;
            this.restriction = restriction;
        }
    }

    protected class DocumentationElement {

        final String elementName;
        final String documentationText;
        final int minBounds;
        final int maxBounds;
        final List<DocumentationAttribute> attributeTypes = new ArrayList<>();
        final String[] childTypeNames;
        final DocumentationElement[] childElements;
        final boolean hasStringContents;

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final boolean hasStringContents) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.childTypeNames = new String[0];
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = hasStringContents;
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.childTypeNames = new String[0];
            this.childElements = childElements;
            this.hasStringContents = false;
        }

        public DocumentationElement(final FeatureType featureType) {
            this.elementName = featureType.name();
            this.documentationText = "";
            this.minBounds = 0;
            this.maxBounds = 0;
            List<String> childTypeList = new ArrayList<>();
            if (featureType.getRequiresChildType() != FeatureType.Contitionals.none) {
                for (final FeatureType featureRef : FeatureType.values()) {
                    if (//featureRef.getIsChildType() == FeatureType.Contitionals.none || 
                            featureRef.getIsChildType() == featureType.getRequiresChildType() //|| featureRef.getIsChildType() == FeatureType.Contitionals.groupNetworkAction // currently allowing all groupNetworkAction in any element
                            //|| featureRef.getIsChildType() == FeatureType.Contitionals.stimulusAction // currently allowing all stimulusAction in any element
                            ) {
                        childTypeList.add(featureRef.name());
                    }
                }
            }
            switch (featureType.getRequiresChildType()) {
                // these items link to separate lists of element groups: general, stimuli, group...
                case stimulusAction:
                    childTypeList.add("...Stimulus Features...");
                    break;
                case groupNetworkAction:
                    childTypeList.add("...Group Features...");
                    break;
                case none:
                    childTypeList.add("...General Features...");
                    break;
            }
            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = false;
            if (featureType.getFeatureAttributes() != null) {
                for (FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                    if (featureAttribute.getTypeValues() == null) {
                        stringAttribute(featureAttribute.name(), featureAttribute.isOptional());
                    } else {
                        restrictedAttribute(featureAttribute.name(), featureAttribute.isOptional(), featureAttribute.getTypeValues());
                    }
                }
            }
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final FeatureType[] featureTypes, final PresenterType[] presenterTypes) {
            this.elementName = elementName;
            String documentationTemp = documentationText;
            for (final PresenterType presenterType : presenterTypes) {
                documentationTemp += presenterType.name() + ",";
            }
            this.documentationText = documentationTemp;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            List<String> childTypeList = new ArrayList<>();
            for (final FeatureType featureRef : FeatureType.values()) {
                if (featureRef.getIsChildType() == FeatureType.Contitionals.none) {
                    childTypeList.add(featureRef.name());
                }
            }
            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = false;
        }

        public final DocumentationElement stringAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "String", "xs:string", optional, null));
            return this;
        }

        public final DocumentationElement restrictedAttribute(final String attributeName, final boolean optional, final String... restriction) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Option List", null, optional, restriction));
            return this;
        }

        public final DocumentationElement stringLowercaseAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "String Lowercase", "lowercaseValue", optional, null));
            return this;
        }

        public final DocumentationElement colourRGBAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "RGB Hex Value", "rgbHexValue", optional, null));
            return this;
        }

        public final DocumentationElement booleanAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Boolean", "xs:boolean", optional, null));
            return this;
        }

        public final DocumentationElement decimalAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Decimal Number", "xs:decimal", optional, null));
            return this;
        }

        public final DocumentationElement integerAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Integral Number", "xs:integer", optional, null));
            return this;
        }

        public final DocumentationElement integerListAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Integer List", "integerList", optional, null));
            return this;
        }
    }
    protected final DocumentationElement rootElement = new DocumentationElement("experiment", "Each experiment XML configuration file must have one EXPERIMENT element of which only one is permitted and it must contain all other elements of the configuration file.", 1, 1,
            new DocumentationElement[]{
                new DocumentationElement("preventWindowClose", "When true the a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1,
                        new DocumentationElement[0]).stringAttribute("featureText", false),
                new DocumentationElement("administration", "Administration", 0, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("dataChannel", "", 0, 0, new DocumentationElement[0])
                                    .stringAttribute("label", false)
                                    .booleanAttribute("logToSdCard", false)
                                    .integerAttribute("channel", false)
                        }),
                new DocumentationElement("scss", "", 0, 1, true),
                new DocumentationElement("metadata", "The fields of data to be collected for each participant and for use as storage data that will be reported in the admin tables.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("field", "", 1, 0, new DocumentationElement[0])
                                    .stringAttribute("controlledMessage", false)
                                    .stringAttribute("controlledRegex", false)
                                    .stringAttribute("postName", false)
                                    .stringAttribute("registrationField", false)
                                    .stringAttribute("duplicatesControlledMessage", true)
                                    .booleanAttribute("preventServerDuplicates", true)
                        }),
                new DocumentationElement("presenter", "Each screen in an experiment configuration is described in a PRESENTER element.", 1, 0, FeatureType.values(), PresenterType.values())
                        .stringAttribute("self", false)
                        .stringAttribute("title", true)
                        .stringAttribute("menuLabel", false)
                        .stringAttribute("back", true)
                        .stringAttribute("next", true)
                        .restrictedAttribute("type", false, "transmission", "metadata", "preload", "stimulus", "colourPicker", "colourReport", "kindiagram", "menu", "debug", "text", "timeline"),
                new DocumentationElement(
                        "stimuli", "All stimulus elements must be contained in the stimuli element.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("stimulus", "Each individual stimulus can be described in the form of label, audio, video", 0, 0, new DocumentationElement[0])
                                    .stringAttribute("identifier", false)
                                    .stringAttribute("videoPath", true)
                                    .stringAttribute("imagePath", true)
                                    .stringAttribute("code", true)
                                    .stringAttribute("audioPath", true)
                                    .stringAttribute("label", true)
                                    .stringAttribute("correctResponses", true)
                                    .stringAttribute("ratingLabels", true)
                                    .stringAttribute("tags", true)
                                    .integerAttribute("pauseMs", true)
                        }
                )
            })
            .stringAttribute("appNameDisplay", false)
            .stringLowercaseAttribute("appNameInternal", false)
            .colourRGBAttribute("backgroundColour", false)
            .colourRGBAttribute("complementColour0", false)
            .colourRGBAttribute("complementColour1", false)
            .colourRGBAttribute("complementColour2", false)
            .colourRGBAttribute("complementColour3", false)
            .colourRGBAttribute("complementColour4", false)
            .colourRGBAttribute("primaryColour0", false)
            .colourRGBAttribute("primaryColour1", false)
            .colourRGBAttribute("primaryColour2", false)
            .colourRGBAttribute("primaryColour3", false)
            .colourRGBAttribute("primaryColour4", false)
            .booleanAttribute("isScalable", false)
            .booleanAttribute("preserveLastState", false)
            .stringAttribute("userIdGetParam", true)
            .booleanAttribute("rotatable", false)
            .booleanAttribute("showMenuBar", false)
            .decimalAttribute("defaultScale", false)
            .integerAttribute("textFontSize", false);
}
