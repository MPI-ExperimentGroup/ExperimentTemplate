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
import java.util.Collections;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import org.codehaus.groovy.runtime.AbstractComparator;

/**
 * @since Feb 11, 2019 5:09:05 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AbstractSchemaGenerator {

    enum ChildType {
        allOnceUnordered, choiceAnyCount, sequenceOnceOrdered
    }

    public enum AttributeType {
        xsString("xs:string"),
        presenterName("presenterName"),
        xsInteger("xs:integer"),
        xsDecimal("xs:decimal"),
        xsBoolean("xs:boolean"),
        rgbHexValue("rgbHexValue"),
        stimulusTags("stimulusTags"),
        stimulusTag("stimulusTag"),
        integerList("integerList"),
        lowercaseValue("lowercaseValue"),
        presenterKind("type");
        public final String typeString;

        private AttributeType(String typeString) {
            this.typeString = typeString;
        }
    }

    protected class DocumentationAttribute {

        final String name;
        final String documentation;
        final String type;
        final boolean optional;
        final String[] restriction;

        public DocumentationAttribute(final String name, final String documentation, final AttributeType type, final boolean optional) {
            this.name = name;
            this.documentation = documentation;
            this.type = type.typeString;
            this.optional = optional;
            this.restriction = null;
        }

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
        final String typeName;
        final String documentationText;
        final int minBounds;
        final int maxBounds;
        final List<DocumentationAttribute> attributeTypes = new ArrayList<>();
        final String[] childTypeNames;
        final DocumentationElement[] childElements;
        final ChildType childOption;
        final boolean hasStringContents;
        final boolean allowsCustomImplementation;

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final boolean hasStringContents) {
            this.elementName = elementName;
            this.typeName = elementName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.childTypeNames = new String[0];
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = hasStringContents;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.typeName = elementName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.childTypeNames = new String[0];
            this.childElements = childElements;
            this.hasStringContents = false;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
        }

        public DocumentationElement(final String elementName, final String typeName, final String documentationText, final int minBounds, final int maxBounds, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.typeName = typeName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.childTypeNames = new String[0];
            this.childElements = childElements;
            this.hasStringContents = false;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
        }

        public DocumentationElement(final FeatureType featureType) {
            this.elementName = featureType.name();
            this.typeName = featureType.name() + "Type";
            this.documentationText = featureType.getDocumentationText();
            this.minBounds = 0;
            this.maxBounds = 0;
            final List<String> translatableAttribites = new ArrayList<>();
            if (featureType.canHaveText()) {
                stringAttribute("featureText", false);
                translatableAttribites.add("featureText");
            }
            if (featureType.getFeatureAttributes() != null) {
                for (FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                    if (featureAttribute.getTypeValues() != null) {
                        restrictedAttribute(featureAttribute.name(), featureAttribute.name() + "Type", featureAttribute.getDocumentation(), featureAttribute.isOptional(), featureAttribute.getTypeValues());
                    } else if (featureAttribute.getType() != null) {
                        documentedAttribute(featureAttribute.name(), featureAttribute.getType(), featureAttribute.getDocumentation(), featureAttribute.isOptional());
                    } else {
                        stringAttribute(featureAttribute.name(), featureAttribute.isOptional());
                    }
                    if (FeatureAttribute.closeButtonLabel == featureAttribute) {
                        translatableAttribites.add("closeButtonLabel");
                    }
                }
            }
            this.childOption = (featureType.getRequiresChildType().areChildenOptional) ? ChildType.choiceAnyCount
                    : (!translatableAttribites.isEmpty()) ? ChildType.sequenceOnceOrdered : ChildType.allOnceUnordered;
            List<String> childTypeList = new ArrayList<>();
            switch (featureType.getRequiresChildType()) {
                // these items link to separate lists of element groups: general, stimuli, group...
                case stimulusAction:
//                    childTypeList.add("...Stimulus Features...");
//                    childTypeList.add("...General Features...");
                    break;
                case groupNetworkAction:
//                    childTypeList.add("...Group Features...");
//                    childTypeList.add("...General Features...");
                    break;
                case none:
                    break;
//                case hasMediaLoading:
//                    childTypeList.add("mediaLoaded");
//                    childTypeList.add("mediaLoadFailed");
//                    break;
                case hasMediaPlayback:
                case hasMediaLoadingButton:
                    childTypeList.add("mediaLoaded");
                    childTypeList.add("mediaLoadFailed");
//                    childTypeList.add("mediaPlaybackStarted");
//                    childTypeList.add("mediaPlaybackComplete");
                    break;
                case hasMediaRecorderPlayback:
                    childTypeList.add("onError");
                    childTypeList.add("onSuccess");
                    break;
//                case hasMoreStimulus:
//                    childTypeList.add("onError");
//                    childTypeList.add("onSuccess");
//                    break;
            }
//            if (featureType.getRequiresChildType() != FeatureType.Contitionals.none) {
            if (featureType.canHaveFeatures()) {
                for (final FeatureType featureRef : FeatureType.values()) {
                    if (featureRef.isChildType(FeatureType.Contitionals.stimulusAction)
                            || featureRef.isChildType(FeatureType.Contitionals.none)
                            /* note: we are falsely allowing all stimulus types here because the XSD does not consider the parent element which might be loadStimulus for example */ || featureRef.isChildType(FeatureType.Contitionals.groupNetworkAction)
                            /* note: we are falsely allowing all group types here because the XSD does not consider the parent element */ || featureRef.isChildType(featureType.getRequiresChildType()) //|| featureRef.getIsChildType() == FeatureType.Contitionals.groupNetworkAction // currently allowing all groupNetworkAction in any element
                            //|| featureRef.getIsChildType() == FeatureType.Contitionals.stimulusAction // currently allowing all stimulusAction in any element
                            ) {
                        childTypeList.add(featureRef.name());
                    }
                }
            } else {
                for (final FeatureType featureRef : FeatureType.values()) {
                    if (featureType.getRequiresChildType() != FeatureType.Contitionals.none
                            && featureRef.isChildType(featureType.getRequiresChildType())) {
                        childTypeList.add(featureRef.name());
                    }
                }
            }
            List<DocumentationElement> documentationElements = new ArrayList<>();
            if (featureType.isCanHaveRandomGrouping() && featureType.canHaveStimulusTags()) {
                documentationElements.add(new DocumentationElement("randomGrouping", "List of stimuli tag names one of which will be randomly selected, or determined by metadata fields or get parameters.", 0, 1, new DocumentationElement[]{new DocumentationElement("tag", "", 0, -1, true).stringAttribute("alias", true)}).stringAttribute("storageField", true).stringAttribute("consumedTagGroup", /* todo: document how consumedTagGroup is used */ true));
                documentationElements.add(new DocumentationElement("stimuli", "stimuliSelect", "List of stimuli tag names which determine which stimuli are selected.", 0, 1, new DocumentationElement[]{new DocumentationElement("tag", "", 0, -1, true)}));
            }
            if (!translatableAttribites.isEmpty()) {
                Collections.sort(translatableAttribites);
                final String translationType = "translation_" + String.join("_", translatableAttribites);
                final DocumentationElement translationElement = new DocumentationElement("translation", translationType, "Translated attributes for the parent element.", 0, 0, new DocumentationElement[]{});
                translationElement.stringAttribute("locale", false);
                for (final String attributeName : translatableAttribites) {
                    translationElement.stringAttribute(attributeName, true);
                }
                documentationElements.add(translationElement);
            }
            if (featureType.canHaveStimulusTags() && !featureType.isCanHaveRandomGrouping()) {
                tagsAttribute("tags", false);
            }
            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
            this.childElements = documentationElements.toArray(new DocumentationElement[documentationElements.size()]);
            this.hasStringContents = false;
            this.allowsCustomImplementation = featureType.allowsCustomImplementation();
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final FeatureType[] featureTypes, final PresenterType[] presenterTypes) {
            this.elementName = elementName;
            this.typeName = elementName + "Type";
            String documentationTemp = documentationText;
//            for (final PresenterType presenterType : presenterTypes) {
//                documentationTemp += presenterType.name() + ",";
//            }
            this.documentationText = documentationTemp;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            List<String> childTypeList = new ArrayList<>();
            for (final FeatureType featureRef : FeatureType.values()) {
                if (featureRef.isChildType(FeatureType.Contitionals.none)) {
                    childTypeList.add(featureRef.name());
                }
            }
            childTypeList.sort(new AbstractComparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
            this.childElements = new DocumentationElement[]{
                new DocumentationElement("translation", "Translated attributes for the parent element.", 0, 0, false)
                .stringAttribute("locale", false)
                .stringAttribute("title", true)
                .stringAttribute("menuLabel", true)
            };
            this.hasStringContents = false;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
        }

        public final DocumentationElement stringAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "String", AttributeType.xsString, optional));
            return this;
        }

        public final DocumentationElement presenterNameAttribute(final String attributeName, final String documentation, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "The value of this attribute must exist in one presenter self attributes. " + documentation, AttributeType.presenterName, optional));
            return this;
        }

        public final DocumentationElement restrictedAttribute(final String attributeName, final String typeName, final String documentation, final boolean optional, final String... restriction) {
            attributeTypes.add(new DocumentationAttribute(attributeName, (documentation != null) ? documentation : "Option List", typeName, optional, restriction));
            return this;
        }

        public final DocumentationElement stringLowercaseAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "String Lowercase", AttributeType.lowercaseValue, optional));
            return this;
        }

        public final DocumentationElement colourRGBAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "RGB Hex Value", AttributeType.rgbHexValue, optional));
            return this;
        }

        public final DocumentationElement tagsAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Space separated list of stimulus tags [a-Z0-9]", AttributeType.stimulusTags, optional));
            return this;
        }

        public final DocumentationElement tagAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Stimulus selection tag [a-Z0-9]", AttributeType.stimulusTag, optional));
            return this;
        }

        public final DocumentationElement booleanAttribute(final String attributeName, final boolean optional, final String documentation) {
            attributeTypes.add(new DocumentationAttribute(attributeName, documentation, AttributeType.xsBoolean, optional));
            return this;
        }

        public final DocumentationElement decimalAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Decimal Number", AttributeType.xsDecimal, optional));
            return this;
        }

        public final DocumentationElement integerAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Integral Number", AttributeType.xsInteger, optional));
            return this;
        }

        public final DocumentationElement integerListAttribute(final String attributeName, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, "Integer List", AttributeType.integerList, optional));
            return this;
        }

        public final DocumentationElement documentedAttribute(final String attributeName, final AttributeType attributeType, final String documentation, final boolean optional) {
            attributeTypes.add(new DocumentationAttribute(attributeName, documentation, attributeType, optional));
            return this;
        }
    }
    protected final DocumentationElement rootElement = new DocumentationElement("experiment", "Each experiment XML configuration file must have one EXPERIMENT element of which only one is permitted and it must contain all other elements of the configuration file.", 1, 1,
            new DocumentationElement[]{
                new DocumentationElement("preventWindowClose", "When true the a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1,
                        new DocumentationElement[0]).stringAttribute("featureText", false),
                new DocumentationElement("administration", "Administration", 0, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("dataAgreementField", "When present the named metadata field is used to prevented specific data types from being stored or sent until the agreement field matches the required value.", 0, 1, new DocumentationElement[0])
                                    .stringAttribute("fieldName", false)
                                    .stringAttribute("matchingRegex", false),
                            new DocumentationElement("dataChannel", "", 0, 0, new DocumentationElement[0])
                                    .stringAttribute("label", false)
                                    .booleanAttribute("logToSdCard", false, "Boolean")
                                    .integerAttribute("channel", false),
                            new DocumentationElement("validation", "When metadata is sent to the server via transmitResults, this validation section defines the server side validation of metadata fields and optional restoring the value of metadata fields from the most recent validated record."
                                    + "Note that the intention of validation is not to authenticate, but to compare values in the admin system to client side equivalents such as an invitation code. Optionally metadata values in the admin system can be returned to the client to restore a session to the last stored values on a new device or browser.", 0, 1, new DocumentationElement[]{
                                new DocumentationElement("recordMatch", "The value sent to the server must match the last valid record stored on in the admin system.", 0, 0, new DocumentationElement[0])
                                        .documentedAttribute("postField", AttributeType.xsString, "The value of this metadatafield is sent to the admin server for validation.", false)
                                        .documentedAttribute("adminField", AttributeType.xsString, "The value of the admin systems copy of this metadatafield is compared to the postField for validation.", false)
                                        .documentedAttribute("responseField", AttributeType.xsString, "If validation succeeds then the matched value (of the postField and adminField) is returned to this metadata field. Usually this is the only way that this field is populated on the client.", false)
                                        .documentedAttribute("errorField", AttributeType.xsString, "If a record match error occurs the error message will be returned from the admin server in this metadatafield.", false)
                                        .documentedAttribute("errorMessage", AttributeType.xsString, "If a record match error occurs this is the error message that will be returned.", false)
                                        .documentedAttribute("validationRegex", AttributeType.xsString, "If provided then this regex is matched against the postField for validation.", true),
                                new DocumentationElement("fieldMatch", "On successful validation the values stored in the admin system can be returned to the clent. For example to restore a previous session on a new device or browser.", 0, 0, new DocumentationElement[0])
                                        .documentedAttribute("postField", AttributeType.xsString, "If provided the value of the postField must match the value of the adminField for validation to succeed.", true)
                                        .documentedAttribute("adminField", AttributeType.xsString, "If validation succeeds then the value this metadatafield is returned.", false)
                                        .documentedAttribute("responseField", AttributeType.xsString, "If validation succeeds the response value is returned to the client in this metadata field.", true)
                                        .documentedAttribute("errorField", AttributeType.xsString, "If provided the value of the postField must match the value of the adminField then the error message will be returned in this metadatafield.", false)
                                        .documentedAttribute("errorMessage", AttributeType.xsString, "If provided the value of the postField does not match the value of the adminField then this message is returned.", false)
                                        .documentedAttribute("validationRegex", AttributeType.xsString, "If provided then this regex is matched against the value for validation.", true)
                            })
                                    .documentedAttribute("errorField", AttributeType.xsString, "If a validation error occurs the error message will be returned from the admin server in this metadatafield.", false)
                                    .documentedAttribute("errorMessage", AttributeType.xsString, "If no records match and a validation error occurs this is the error message that will be returned.", false)
                                    .documentedAttribute("allowValidationOnMissing", AttributeType.xsBoolean, "If there are no records for this user then if set to true the validation will succeed, if false then the user will need to be manually added into the admin system.", false)
                        }),
                new DocumentationElement("scss", "Custom SCSS or CSS styles can be added in this element. The SCSS content will be processed into CSS and the combined result will be included in the experiments CSS file. The resulting styles can then be used on any feature that takes a styleName attribute.", 0, 1, true),
                new DocumentationElement("metadata", "The fields of data to be collected for each participant and for use as storage data that will be reported in the admin tables.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("field", "", 1, 0, new DocumentationElement[]{
                        new DocumentationElement("translation", "Translated attributes for the parent element.", 0, 0, false)
                        .stringAttribute("locale", false)
                        .stringAttribute("controlledMessage", true)
                        .stringAttribute("registrationField", true)
                    })
                                    .stringAttribute("controlledMessage", false)
                                    .stringAttribute("controlledRegex", false)
                                    .stringAttribute("postName", false)
                                    .stringAttribute("registrationField", false)
                                    .stringAttribute("duplicatesControlledMessage", true)
                                    .booleanAttribute("preventServerDuplicates", true, "Boolean")
                        }),
                new DocumentationElement("presenter", "Each screen in an experiment configuration is described in a PRESENTER element.", 1, 0, FeatureType.values(), PresenterType.values())
                        .documentedAttribute("self", AttributeType.presenterName, "The name of the presenter, which must be unique per configuration file.", false)
                        .stringAttribute("title", true)
                        .stringAttribute("menuLabel", true)
                        .presenterNameAttribute("back", "If the back attribute is provided the back button will be shown and it will cause the menu/title bar to be shown in the presenter even if it is otherwise hidden.", true)
                        .presenterNameAttribute("next", "The value of this attribute is used as the target for gotoNextPresenter etc..", true)
                        .restrictedAttribute(AttributeType.presenterKind.typeString, null, "The type of presenter which also determines the features that can be used in the presenter.", false, "transmission", "metadata", "preload", "stimulus", "colourPicker", "colourReport", "kindiagram", "menu", "debug", "text", "timeline"),
                new DocumentationElement(
                        "stimuli", "All stimulus elements must be contained in the stimuli element.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("stimulus", "Each individual stimulus can be described in the form of label, audio, video", 0, 0, new DocumentationElement[]{
                        new DocumentationElement("translation", "Translated attributes for the parent stimulus element.", 0, 0, false)
                        .stringAttribute("locale", false)
                        .stringAttribute("label", true)
                    })
                                    .stringAttribute("identifier", false)
                                    .stringAttribute("videoPath", true)
                                    .stringAttribute("imagePath", true)
                                    .stringAttribute("code", true)
                                    .stringAttribute("audioPath", true)
                                    .stringAttribute("label", true)
                                    .stringAttribute("correctResponses", true)
                                    .stringAttribute("ratingLabels", true)
                                    .tagsAttribute("tags", true)
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
            .booleanAttribute("isScalable", false, "Boolean")
            .booleanAttribute("preserveLastState", false, "Boolean")
            .stringAttribute("splashPresenter", true)
            .stringAttribute("userIdGetParam", true)
            .booleanAttribute("rotatable", false, "Boolean")
            .booleanAttribute("showMenuBar", false, "Boolean")
            .decimalAttribute("defaultScale", false)
            .integerAttribute("textFontSize", false)
            .booleanAttribute("obfuscateBrowserStorage", false, "By default the browser local storage is obfuscated to make it difficult to cheat they system, by setting this to false the obfuscation can be disabled making it easier to debug the application. This can also be achieved by adding the get parameter '?debug=true' to the URL.");
}
