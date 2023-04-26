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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureAttribute;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since Feb 11, 2019 5:09:05 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class AbstractSchemaGenerator {

    Map<String, List<String>> childTypeLists = new HashMap<>();

    enum ChildType {
        allOnceUnordered, choiceAnyCount, sequenceOnceOrdered
    }

    public enum AttributeType {
        xsString("xs:string", "String"),
        presenterName("presenterName", "PresenterName"),
        xsInteger("xs:integer", "Integer"),
        xsDecimal("xs:decimal", "Decimal"),
        xsBoolean("xs:boolean", "Boolean"),
        xsBooleanToggle("booleanToggle", "false, true, invert"),
        rgbHexValue("rgbHexValue", "RGB Hex Value"),
        dateValue("dateValue", "Date Value YYYY-MM-DD"),
        stimulusIdentifier("stimulusIdentifier", "Stimulus Identifier"),
        stimulusTags("stimulusTags", "Stimulus Tags"),
        stimulusTag("stimulusTag", "Stimulus Tag"),
        integerList("integerList", "Integer List"),
        groupMembers("groupMembers", "Group Members"),
        groupChannels("groupChannels", "Group Channels"),
        groupPhases("groupPhases", "Group Phases"),
        lowercaseValue("lowercaseValue", "Lowercase Value"),
        postName("postName", "Post Name String");
//        presenterKind("type", "Presenter Type");
        public final String typeName;
        public final String typeLabel;

        private AttributeType(String typeName, String typeLabel) {
            this.typeName = typeName;
            this.typeLabel = typeLabel;
        }
    }

    protected class DocumentationAttribute {

        final String name;
        final String documentation;
        public final String typeName;
        public final String typeLabel;
        final boolean optional;
        final String[] restriction;

        public DocumentationAttribute(final String name, final String documentation, final AttributeType type, final boolean optional) {
            this.name = name;
            this.documentation = documentation;
            this.typeName = type.typeName;
            this.typeLabel = type.typeLabel;
            this.optional = optional;
            this.restriction = null;
        }

        public DocumentationAttribute(final String name, final String documentation, final String type, final boolean optional, String[] restriction) {
            this.name = name;
            this.documentation = documentation;
            this.typeName = type;
            this.typeLabel = type;
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
        final String typeExtends;
        final DocumentationElement[] childElements;
        final ChildType childOption;
        final boolean hasStringContents;
        final boolean allowsCustomImplementation;
        final boolean isRecursive;

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final boolean hasStringContents) {
            this.elementName = elementName;
            this.typeName = elementName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.typeExtends = null;
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = hasStringContents;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
            this.isRecursive = false;
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.typeName = elementName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.typeExtends = null;
            this.childElements = childElements;
            this.hasStringContents = false;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
            this.isRecursive = false;
        }

        public DocumentationElement(final String elementName, final String typeName, final String documentationText, final int minBounds, final int maxBounds, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.typeName = typeName + "Type";
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.typeExtends = null;
            this.childElements = childElements;
            this.hasStringContents = false;
            this.childOption = ChildType.sequenceOnceOrdered;
            this.allowsCustomImplementation = false;
            this.isRecursive = false;
        }

        public DocumentationElement(final FeatureType featureType) {
            this.elementName = featureType.name();
            this.typeName = featureType.name() + "Type";
            this.documentationText = featureType.getDocumentationText();
            this.minBounds = 0;
            this.maxBounds = 0;
            this.isRecursive = featureType.isChildType(FeatureType.Contitionals.isRecursiveType);
            final List<String> translatableAttribites = new ArrayList<>();
            if (featureType.canHaveText()) {
                documentedAttribute("featureText", AttributeType.xsString, "Text that will be visible to the user.", false);
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
                    : ChildType.sequenceOnceOrdered; // using all in a group or extension is not allowed: (!translatableAttribites.isEmpty()) ? ChildType.sequenceOnceOrdered : ChildType.allOnceUnordered;                    
            // calculate a name for the extendsType
            // add all the child types to the extendsType once
            this.typeExtends = this.childOption.name() + "_" + featureType.getRequiresChildType().name();
            if (!childTypeLists.containsKey(this.typeExtends)) {
                List<String> childTypeList = new ArrayList<>();
                childTypeLists.put(this.typeExtends, childTypeList);
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
                        childTypeList.add("onSuccess");
                        childTypeList.add("onError");
                        break;
//                case hasMoreStimulus:
//                    childTypeList.add("onError");
//                    childTypeList.add("onSuccess");
//                    break;
                }
                if (featureType.getRequiresChildType() != FeatureType.Contitionals.none) {
                    for (final FeatureType featureRef : FeatureType.values()) {
                        if (featureRef.isChildType(featureType.getRequiresChildType())
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.any && featureRef.isChildType(FeatureType.Contitionals.none))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.any && featureRef.isChildType(FeatureType.Contitionals.stimulusAction))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.any && featureRef.isChildType(FeatureType.Contitionals.groupNetworkAction))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.any && featureRef.isChildType(FeatureType.Contitionals.svgGroupsLoaded))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.any && featureRef.isChildType(FeatureType.Contitionals.touchInputStartType))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.svgGroupsLoaded && featureRef.isChildType(FeatureType.Contitionals.none))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.touchInputStartType && featureRef.isChildType(FeatureType.Contitionals.none))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.touchInputStartType && featureRef.isChildType(FeatureType.Contitionals.stimulusAction))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.stimulusAction && featureRef.isChildType(FeatureType.Contitionals.none))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.stimulusAction && featureRef.isChildType(FeatureType.Contitionals.groupNetworkAction))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.groupNetworkAction && featureRef.isChildType(FeatureType.Contitionals.none))
                                || (featureType.getRequiresChildType() == FeatureType.Contitionals.groupNetworkAction && featureRef.isChildType(FeatureType.Contitionals.stimulusAction))) {
                            // note: we are falsely allowing all stimulus types here because the XSD does not consider the parent element which might be loadStimulus for example
                            // note: we are falsely allowing all group types here because the XSD does not consider the parent element
                            // currently allowing all groupNetworkAction in any element
                            // currently allowing all stimulusAction in any element
                            childTypeList.add(featureRef.name());
                            if (featureRef.allowsCustomImplementation()) {
                                childTypeList.add(featureRef.name() + "Plugin");
                            }
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
            }
            List<DocumentationElement> documentationElements = new ArrayList<>();
            if (featureType.isCanHaveRandomGrouping() && featureType.canHaveStimulusTags()) {
                documentationElements.add(new DocumentationElement("randomGrouping", "List of stimuli tag names one of which will be randomly selected, unless the selection has been predetermined by a metadata field value or by URL GET parameters.", 0, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("tag", "Optional stimuli tag which will be used to select stimuli when the value of the storageField equals the alias. In practice the storageField would usually be set by GET parameter in the URL.", 0, -1, true).documentedAttribute("alias", AttributeType.xsString, "This tag will be used when the contents of the storageField matches the value of alias.", true),
                            new DocumentationElement("list", "Optional list of stimulus IDs separated by - which will be used when the value of the storageField equals the alias. In practice the storageField would usually be set by GET parameter in the URL. Any whitespace will be ignored so the configuration can have one stimulus per line (providing the - is present), if needed for readability.", 0, -1, true).documentedAttribute("alias", AttributeType.xsString, "This list will be used when the contents of the storageField matches the value of alias. For example storageField=&quot;selectList&quot; and alias=&quot;s6&quot; would match with GET parameter selectList=s6 in the URL.", false)
                        })
                        .documentedAttribute("storageField", AttributeType.xsString, "If this metadata field contains one of the tags or alias, it will be used. Otherwise a random tag is selected for use, it is then stored in this metadata field. This allows a stimuli selection process to carry over between presenters.", true)
                        .documentedAttribute("consumedTagGroup", AttributeType.xsString, "When a tag is selected for use, it is put into this named list and will not be selected again, providing this named list is used. This allows a stimuli selection process to carry over between presenters."/* todo: document how consumedTagGroup is used */, true)
                // TODO: (3 of 3) allocateMetadata participant allocation service with attribute allocationServiceName, to preserve the allocation across browsers and devices
                //.documentedAttribute("allocationServiceName", AttributeType.xsString, "When provided the named service will be created on the sever and used to allocate the least used tag for use in stimuli allocation. If multiple tags are equally the least used, one of them will randomly be allocated. Any number of allocation service names can be used and they can be shared across different stimuli lists. Please note that the use of this feature prevents the experiment being used offline. The allocation of a tag does not mean that it will be used by the participant, for example if after allocation the browser is closed and the participant does not complete the experiment then the distribution will necessarily be affected.", true));
                );
                documentationElements.add(new DocumentationElement("stimuli", "stimuliSelect", "List of stimuli tag names which determine which stimuli are selected. All stimuli which contain any of the tags will be included in the list of stimuli. The number of selected stimuli will be limited to the maximum that has been requested. The stimuli selected by these tags will always be selected even if a randomGrouping is used.", 0, 1,
                        new DocumentationElement[]{new DocumentationElement("tag", "", 0, -1, true)})
                        .documentedAttribute("idListField", AttributeType.xsString, "When provided this metadata field must contain a list of stimuli IDs which will then be used in the stimuli selection process. This allows stimuli lists to be generated and stored between presenters.", true));
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
            this.childElements = documentationElements.toArray(new DocumentationElement[documentationElements.size()]);
            this.hasStringContents = false;
            this.allowsCustomImplementation = featureType.allowsCustomImplementation();
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds) {
            this.elementName = elementName;
            this.isRecursive = false;
            this.typeName = elementName + "Type";
            String documentationTemp = documentationText;
//            for (final PresenterType presenterType : presenterTypes) {
//                documentationTemp += presenterType.name() + ",";
//            }
            this.documentationText = documentationTemp;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            // calculate a name for the extendsType
            // add all the child types to the extendsType once
            this.typeExtends = elementName + "BaseType";
            if (!childTypeLists.containsKey(this.typeExtends)) {
                List<String> childTypeList = new ArrayList<>();
                childTypeLists.put(this.typeExtends, childTypeList);
                for (final FeatureType featureRef : FeatureType.values()) {
                    if (featureRef.isChildType(FeatureType.Contitionals.none)) {
                        childTypeList.add(featureRef.name());
                        if (featureRef.allowsCustomImplementation()) {
                            childTypeList.add(featureRef.name() + "Plugin");
                        }
                    }
                }
                childTypeList.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
            }
//            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
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
    protected final DocumentationElement rootElement = new DocumentationElement("experiment", "Each experiment XML configuration file must have one \"experiment\" element of which only one is permitted and it must contain all other elements of the configuration file.", 1, 1,
            new DocumentationElement[]{
                new DocumentationElement("preventWindowClose", "When true a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1,
                        new DocumentationElement[0]).documentedAttribute("featureText", AttributeType.xsString, "Text that will be visible to the user.", false),
                new DocumentationElement("deployment", "", 0, 1,
                        new DocumentationElement[0])
                        .documentedAttribute("publishDate", AttributeType.dateValue, "The date from which the experiment will be deployed.", false)
                        .documentedAttribute("expiryDate", AttributeType.dateValue, "The date after which the experiment can be undeployed.", false)
                        .documentedAttribute("stagingServer", AttributeType.xsString, "When provided determines which staging server the experiment is deployed to.", true)
                        .documentedAttribute("productionServer", AttributeType.xsString, "When provided determines which production server the experiment is deployed to.", true)
                        .documentedAttribute("frinexVersion", AttributeType.xsString, "When provided determines which version of Frinex is used to compile the experiment (the specified version must be available on the build server at the time of compilation).", true)
                        .documentedAttribute("stunServer", AttributeType.xsString, "When provided determines which stun server will be used for group experiments which stream the participants camera, microphone or canvas. When not provided no stun server will be used and any group streaming will have to be within the same local area network.", true)
                        .booleanAttribute("isWebApp", true, "If true a web version of this experiment will be generated.")
                        .booleanAttribute("isDesktop", true, "If true a desktop version of this experiment will be generated. "
                                + "To debug the desktop app you can run pass the --debug-mode switch from the terminal. This is only available in the non production versions. "
                                + "On osX this can be done with &quot;open with_stimulus_example.app --args --debug-mode&quot; and on Windows &quot;with_stimulus_example.exe --debug-mode&quot;.")
                        .booleanAttribute("isiOS", true, "If true an iOS version of this experiment will be generated.")
                        .booleanAttribute("isAndroid", true, "If true an Android version of this experiment will be generated.")
                        .booleanAttribute("isUnity", true, "Experimental feature, if true a Unity version of this experiment will be generated.")
                        .restrictedAttribute("state", null, "The type of deployment to be run when changes are pushed to the build server.", false, "validate", "debug", "draft", "staging", "production", "undeploy"),
                new DocumentationElement("validationService", "Server side validation / registration services.", 0, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("validation", "When metadata is sent to the server via transmitResults, this validation section defines the server side validation of metadata fields and optional restoring the value of metadata fields from the most recent validated record. "
                                    + "Note that the intention of validation is not to authenticate, but to compare values in the admin system to client side equivalents such as an invitation code. Optionally metadata values in the admin system can be returned to the client to restore a session to the last stored values on a new device or browser. "
                                    + "This section is not used when an external &lt;validationService&gt; is specified.", 0, 1, new DocumentationElement[]{
                                new DocumentationElement("recordMatch", "The value sent to the server must match the last valid record stored on in the admin system.", 0, 0, new DocumentationElement[0])
                                        .documentedAttribute("postField", AttributeType.xsString, "The value of this metadatafield is sent to the admin server for validation.", false)
                                        .documentedAttribute("adminField", AttributeType.xsString, "The value of the admin systems copy of this metadatafield is compared to the postField for validation.", false)
                                        .documentedAttribute("responseField", AttributeType.xsString, "If validation succeeds then the matched value (of the postField and adminField) is returned to this metadata field. Usually this is the only way that this field is populated on the client.", false)
                                        .documentedAttribute("errorField", AttributeType.xsString, "If a record match error occurs the error message will be returned from the admin server in this metadatafield.", false)
                                        .documentedAttribute("errorMessage", AttributeType.xsString, "If a record match error occurs this is the error message that will be returned.", false)
                                        .documentedAttribute("validationRegex", AttributeType.xsString, "If provided then this regex is matched against the postField for validation.", true),
                                new DocumentationElement("fieldMatch", "On successful validation the values stored in the admin system can be returned to the client. For example to restore a previous session on a new device or browser.", 0, 0, new DocumentationElement[0])
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
                        }).documentedAttribute("stagingUrl", AttributeType.xsString, "The URL to an external staging registration service. When not specified the Frinex &lt;validation&gt; service is used.", true)
                        .documentedAttribute("productionUrl", AttributeType.xsString, "The URL to an external production registration service. When not specified the Frinex &lt;validation&gt; service is used.", true),
                new DocumentationElement("administration", "Administration", 0, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("adminUser", "User that can access to the administration system and JSON REST interface for this experiment. Multiple users can be defined.", 0, 0, new DocumentationElement[0])
                                    .documentedAttribute("name", AttributeType.xsString, "For use with additional or external users a username for access to the administration system and JSON REST interface for this experiment.", false),
                            // .documentedAttribute("password", AttributeType.xsString, "User password for access to the administration system and JSON REST interface for this experiment.", false),
                            new DocumentationElement("dataAgreementField", "When present the named metadata field is used to prevented specific data types from being stored or sent until the agreement field matches the required value.", 0, 1, new DocumentationElement[0])
                                    .stringAttribute("fieldName", false)
                                    .stringAttribute("matchingRegex", false),
                            new DocumentationElement("dataChannel", "", 0, 0, new DocumentationElement[0])
                                    .stringAttribute("label", false)
                                    .booleanAttribute("logToSdCard", false, "Boolean")
                                    .integerAttribute("channel", false),
                            new DocumentationElement("chart", "adminChart", "When present defines a chart that will be displayed on the main page of the experiment admin. Multiple charts can be used.", 0, 0,
                                    new DocumentationElement[]{
                                        new DocumentationElement("metadata", "Adds matching metadata as a dataset to the graph.", 0, 0, new DocumentationElement[0])
                                                .stringAttribute("label", false)
                                                .stringAttribute("fieldName", false)
                                                .documentedAttribute("matching", AttributeType.xsString, "Only records matching this string will be counted for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                                .colourRGBAttribute("colour", false),
                                        new DocumentationElement("stimulusResponse", "Adds matching stimuli responses as a dataset to the graph.", 0, 0, new DocumentationElement[0])
                                                .stringAttribute("label", false)
                                                .stringAttribute("coloumName", false)
                                                .documentedAttribute("matching", AttributeType.xsString, "Only records matching this string will be counted for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                                .colourRGBAttribute("colour", false)
                                    })
                                    .stringAttribute("label", false)
                                    .restrictedAttribute("type", null, "The type of chart to be displayed.", false, "bar", "line", "pie", "bubble", "radar"), //, "boxplot", "scatter"
                            new DocumentationElement("dataTable", "dataTable", "When present defines a table that will be displayed on the main page of the experiment admin. Multiple table can be used.", 0, 0,
                                    new DocumentationElement[]{
                                        //                                        new DocumentationElement("metadata", "Adds matching metadata as a dataset to the table.", 0, 0, new DocumentationElement[0])
                                        //                                                .stringAttribute("label", false)
                                        //                                                .stringAttribute("fieldName", false)
                                        //                                                .documentedAttribute("matching", AttributeType.xsString, "Only records matching this string will be counted for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false),
                                        //                                        new DocumentationElement("stimuli", "Adds matching stimuli responses as a dataset to the table.", 0, 0, new DocumentationElement[0])
                                        //                                                .stringAttribute("label", false)
                                        //                                                .stringAttribute("coloumName", false)
                                        //                                                .documentedAttribute("matching", AttributeType.xsString, "Only records matching this string will be counted for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false),
                                        new DocumentationElement("tagpair", "Adds matching tag pair records as a dataset to the table.", 0, 0, new DocumentationElement[0])
                                                .stringAttribute("coloumNames", false)
                                                .documentedAttribute("screenName", AttributeType.xsString, "Only records matching this screenName will be included for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                                .documentedAttribute("eventTag", AttributeType.xsString, "Only records matching this eventTag will be included for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                                .documentedAttribute("tagValue1", AttributeType.xsString, "Only records matching this TagValue1 will be included for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                                .documentedAttribute("tagValue2", AttributeType.xsString, "Only records matching this TagValue2 will be included for this dataset. The percent sign will match zero, one, or multiple characters. The underscore will match any single character.", false)
                                    }).stringAttribute("label", false)
                        })
                        //                              new DocumentationElement("dataManagement", "Settings for managing collected data in the administration system.", 0, 0, new DocumentationElement[0])
                        .documentedAttribute("allowDataDeletion", AttributeType.xsBoolean, "Participant data cannot be deleted when this is omitted or false. Participant data can be deleted via the administration system when this is set to true.", true),
                new DocumentationElement("scss", "Custom SCSS or CSS styles can be added in this element. The SCSS content will be processed into CSS and the combined result will be included in the experiments CSS file. The resulting styles can then be used on any feature that takes a styleName attribute.", 0, 1, true),
                new DocumentationElement("metadata", "The fields of data to be collected for each participant and for use as storage data. The value of each field will be stored in the admin metadata table (participant listing). It is advisable to explicitly sendMetadata at relevant points in the experiment. "
                        + "If the URL used to access the experiment contains a GET parameter matching the postName of a metadata field, the value of the field will be set to the value of the provided GET parameter. This for example allows links to be distributed each of which determines the initial parameters as required for a given experiment.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("field", "", 1, 0, new DocumentationElement[]{
                        new DocumentationElement("translation", "Translated attributes for the parent element.", 0, 0, false)
                        .stringAttribute("locale", false)
                        .stringAttribute("controlledMessage", true)
                        .stringAttribute("registrationField", true)
                    })
                                    .stringAttribute("controlledMessage", false)
                                    .stringAttribute("controlledRegex", false)
                                    .documentedAttribute("postName", AttributeType.postName, "An identifying name for each metadata field, also used in the column names of the admin system.", false) // this must not be empty and should have constrained characters
                                    .stringAttribute("registrationField", false)
                                    .stringAttribute("duplicatesControlledMessage", true)
                                    .booleanAttribute("preventServerDuplicates", true, "Boolean")
                        }),
                new DocumentationElement("presenter", "Each screen in an experiment configuration is described in a PRESENTER element.", 1, 0)
                        .documentedAttribute("self", AttributeType.presenterName, "The name of the presenter, which must be unique per configuration file.", false)
                        .stringAttribute("title", true)
                        .stringAttribute("menuLabel", true)
                        .presenterNameAttribute("back", "If the back attribute is provided the back button will be shown and it will cause the menu/title bar to be shown in the presenter even if it is otherwise hidden.", true)
                        .presenterNameAttribute("next", "The value of this attribute is used as the target for gotoNextPresenter etc..", true)
                        .restrictedAttribute("type", null, "The type of presenter which also determines the features that can be used in the presenter.", false, "transmission", "metadata", "preload", "stimulus", "colourPicker", "colourReport", "kindiagram", "menu", "debug", "text", "timeline"),
                new DocumentationElement(
                        "stimuli", "All stimulus elements must be contained in the stimuli element.", 1, 1,
                        new DocumentationElement[]{
                            new DocumentationElement("stimulus", "Each individual stimulus can be described in the form of label, image, audio and video.", 0, 0, new DocumentationElement[]{
                        new DocumentationElement("translation", "Translated attributes for the parent stimulus element.", 0, 0, false)
                        .stringAttribute("locale", false)
                        .stringAttribute("ratingLabels", true)
                        .stringAttribute("label", true)
                        .stringAttribute("code", true)
                    })
                                    // notes on stimulusId: the methods around loadStoredStimulusList use "-" as the list separator and might have unintended effects if "-" is used in the stimulusID
                                    .documentedAttribute("identifier", AttributeType.stimulusIdentifier, "An identifier for the stimulus consisting of three or more [a-Z0-9_] characters, which must be unique per configuration file.", false)
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
            .documentedAttribute("defaultLocale", AttributeType.xsString, "The default locale to be shown when the user preferred locale is not specified. For example &quot;en&quot; or &quot;en_GB&quot;.", true)
            .documentedAttribute("availableLocales", AttributeType.xsString, "List of all locales provided in this experiment. The list of locales must be comma separated without whitespace characters. For example &quot;en,de,nl,es&quot; or &quot;en_GB,en_US,en_SG&quot;.", true)
            .booleanAttribute("isScalable", false, "Boolean")
            // TODO: preserveLastState would perhaps be better renamed to restrictBrowserNavigation
            .booleanAttribute("preserveLastState", false, "When true the use of browser navigation buttons will be restricted.")
            .documentedAttribute("splashPresenter", AttributeType.presenterName, "When provided the named presenter will be used as the initial presenter on page reloads.", true)
            .stringAttribute("userIdGetParam", true)
            .booleanAttribute("rotatable", false, "Boolean")
            .booleanAttribute("showMenuBar", false, "Boolean")
            .decimalAttribute("defaultScale", false)
            .integerAttribute("textFontSize", false)
            .booleanAttribute("obfuscateBrowserStorage", true, "Boolean: By default the browser local storage is obfuscated to make it difficult to cheat the system, by setting this to false the obfuscation can be disabled making it easier to debug the application. This can also be achieved by adding the get parameter '?debug=true' to the URL.");
}
