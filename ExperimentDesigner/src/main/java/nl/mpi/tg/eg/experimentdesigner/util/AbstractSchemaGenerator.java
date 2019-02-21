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

    protected class DocumentationElement {

        final String elementName;
        final String documentationText;
        final int minBounds;
        final int maxBounds;
        final String[] attributeStrings;
        final String[] attributeLowercase;
        final String[] attributeRGBs;
        final String[] attributeBooleans;
        final String[] attributeFloats;
        final String[] attributeIntegers;
        final String[] attributeIntegerLists;
        final String[] attributeStringsOptional;
        final String[] attributeBooleansOptional;
        final String[] attributeIntegersOptional;
        final String[] childTypeNames;
        final DocumentationElement[] childElements;
        final boolean hasStringContents;

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final boolean hasStringContents) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributeStrings = new String[0];
            this.attributeLowercase = new String[0];
            this.attributeRGBs = new String[0];
            this.attributeBooleans = new String[0];
            this.attributeFloats = new String[0];
            this.attributeIntegers = new String[0];
            this.attributeIntegerLists = new String[0];
            this.attributeStringsOptional = new String[0];
            this.attributeBooleansOptional = new String[0];
            this.attributeIntegersOptional = new String[0];
            this.childTypeNames = new String[0];
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = hasStringContents;
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final String[] attributes, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributeStrings = (attributes == null) ? new String[0] : attributes;
            this.attributeLowercase = new String[0];
            this.attributeRGBs = new String[0];
            this.attributeBooleans = new String[0];
            this.attributeFloats = new String[0];
            this.attributeIntegers = new String[0];
            this.attributeIntegerLists = new String[0];
            this.attributeStringsOptional = new String[0];
            this.attributeBooleansOptional = new String[0];
            this.attributeIntegersOptional = new String[0];
            this.childTypeNames = new String[0];
            this.childElements = childElements;
            this.hasStringContents = false;
        }

        public DocumentationElement(final FeatureType featureType) {
            this.elementName = featureType.name();
            this.documentationText = "";
            this.minBounds = 0;
            this.maxBounds = 0;
            this.attributeLowercase = new String[0];
            this.attributeRGBs = new String[0];
            this.attributeBooleans = new String[0];
            this.attributeFloats = new String[0];
            this.attributeIntegers = new String[0];
            this.attributeIntegerLists = new String[0];
            this.attributeStringsOptional = new String[0];
            this.attributeBooleansOptional = new String[0];
            this.attributeIntegersOptional = new String[0];
            List<String> childTypeList = new ArrayList<>();
            for (final FeatureType featureRef : FeatureType.values()) {
                if (featureRef.getIsChildType() == FeatureType.Contitionals.none || featureRef.getIsChildType() == featureType.getRequiresChildType()
                        || featureRef.getIsChildType() == FeatureType.Contitionals.groupNetworkAction // currently allowing all groupNetworkAction in any element
                        || featureRef.getIsChildType() == FeatureType.Contitionals.stimulusAction // currently allowing all stimulusAction in any element
                        ) {
                    childTypeList.add(featureRef.name());
                }
            }
            this.childTypeNames = childTypeList.toArray(new String[childTypeList.size()]);
            this.childElements = new DocumentationElement[0];
            this.hasStringContents = false;
            List<String> requiredAttributeStringList = new ArrayList<>();
            if (featureType.getFeatureAttributes() != null) {
                for (FeatureAttribute featureAttribute : featureType.getFeatureAttributes()) {
                    requiredAttributeStringList.add(featureAttribute.name());
                }
            }
            this.attributeStrings = requiredAttributeStringList.toArray(new String[requiredAttributeStringList.size()]);
        }

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final String[] attributes, final String[] attributeStringsOptional, final FeatureType[] featureTypes, final PresenterType[] presenterTypes) {
            this.elementName = elementName;
            String documentationTemp = documentationText;
            for (final PresenterType presenterType : presenterTypes) {
                documentationTemp += presenterType.name() + ",";
            }
            this.documentationText = documentationTemp;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributeStrings = (attributes == null) ? new String[0] : attributes;
            this.attributeLowercase = new String[0];
            this.attributeRGBs = new String[0];
            this.attributeBooleans = new String[0];
            this.attributeFloats = new String[0];
            this.attributeIntegers = new String[0];
            this.attributeIntegerLists = new String[0];
            this.attributeStringsOptional = (attributeStringsOptional == null) ? new String[0] : attributeStringsOptional;
            this.attributeBooleansOptional = new String[0];
            this.attributeIntegersOptional = new String[0];
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

        public DocumentationElement(final String elementName, final String documentationText, final int minBounds, final int maxBounds, final String[] attributeStrings, final String[] attributeLowercase, final String[] attributeRGBs, final String[] attributeBooleans, final String[] attributeFloats, final String[] attributeIntegers, final String[] attributeIntegerLists, final String[] attributeStringsOptional, final String[] attributeBooleansOptional, final String[] attributeIntegersOptional, final DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributeStrings = (attributeStrings == null) ? new String[0] : attributeStrings;
            this.attributeLowercase = (attributeLowercase == null) ? new String[0] : attributeLowercase;
            this.attributeRGBs = (attributeRGBs == null) ? new String[0] : attributeRGBs;
            this.attributeBooleans = (attributeBooleans == null) ? new String[0] : attributeBooleans;
            this.attributeFloats = (attributeFloats == null) ? new String[0] : attributeFloats;
            this.attributeIntegers = (attributeIntegers == null) ? new String[0] : attributeIntegers;
            this.attributeIntegerLists = (attributeIntegerLists == null) ? new String[0] : attributeIntegerLists;
            this.attributeStringsOptional = (attributeStringsOptional == null) ? new String[0] : attributeStringsOptional;
            this.attributeBooleansOptional = (attributeBooleansOptional == null) ? new String[0] : attributeBooleansOptional;
            this.attributeIntegersOptional = (attributeIntegersOptional == null) ? new String[0] : attributeIntegersOptional;
            this.childTypeNames = new String[0];
            this.childElements = childElements;
            this.hasStringContents = false;
        }
    }
    protected final DocumentationElement rootElement = new DocumentationElement("experiment", "Each experiment XML configuration file must have one EXPERIMENT element of which only one is permitted and it must contain all other elements of the configuration file.", 1, 1,
            new String[]{"appNameDisplay"},
            new String[]{"appNameInternal"},
            new String[]{"backgroundColour", "complementColour0", "complementColour1", "complementColour2", "complementColour3", "complementColour4", "primaryColour0", "primaryColour1", "primaryColour2", "primaryColour3", "primaryColour4"},
            new String[]{"isScalable", "preserveLastState", "rotatable", "showMenuBar"},
            new String[]{"defaultScale"},
            new String[]{"textFontSize"},
            new String[0],
            new String[]{"userIdGetParam"},
            new String[0],
            new String[0],
            new DocumentationElement[]{
                new DocumentationElement("preventWindowClose", "When true the a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1, new String[]{"featureText"}, new DocumentationElement[0]),
                new DocumentationElement("administration", "Administration", 0, 1, null, new DocumentationElement[]{
            new DocumentationElement("dataChannel", "", 0, 0, new String[]{"label"}, null, null, new String[]{"logToSdCard"}, null, new String[]{"channel"}, null, null, null, null, new DocumentationElement[0])
        }),
                new DocumentationElement("scss", "", 0, 1, true),
                new DocumentationElement("metadata", "The fields of data to be collected for each participant and for use as storage data that will be reported in the admin tables.", 1, 1, null, new DocumentationElement[]{new DocumentationElement("field", "", 1, 0, new String[]{"controlledMessage", "controlledRegex", "postName", "registrationField"}, null, null, null, null, null, null, new String[]{"duplicatesControlledMessage"/* optional string*/}, new String[]{"preventServerDuplicates"}, null, new DocumentationElement[0])}),
                new DocumentationElement("presenter", "Each screen in an experiment configuration is described in a PRESENTER element.", 1, 0, new String[]{"self", "title", "menuLabel", "type"}, new String[]{"back", "next"}, FeatureType.values(), PresenterType.values()),
                new DocumentationElement("stimuli", "All stimulus elements must be contained in the stimuli element.", 1, 1, null, new DocumentationElement[]{new DocumentationElement("stimulus", "Each individual stimulus can be described in the form of label, audio, video", 0, 0, new String[]{"identifier"}, null, null, null, null, null, null, new String[]{"videoPath", "imagePath", "code", "audioPath", "label", "correctResponses", "ratingLabels", "tags"}, null, new String[]{"pauseMs"}, new DocumentationElement[0])})
            });
}
