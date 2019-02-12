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
        final DocumentationElement[] childElements;

        public DocumentationElement(String elementName, String documentationText, int minBounds, int maxBounds, String[] attributes, DocumentationElement[] childElements) {
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
            this.childElements = childElements;
        }

        public DocumentationElement(String elementName, String documentationText, int minBounds, int maxBounds, String[] attributeStrings, String[] attributeLowercase, String[] attributeRGBs, String[] attributeBooleans, String[] attributeFloats, String[] attributeIntegers, String[] attributeIntegerLists, DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributeStrings = (attributeStrings == null) ? new String[0] : attributeStrings;
            this.attributeLowercase = (attributeLowercase == null) ? new String[0] : attributeLowercase;
            this.attributeRGBs = (attributeRGBs == null) ? new String[0] : attributeRGBs;
            this.attributeBooleans = (attributeBooleans == null) ? new String[0] : attributeBooleans;
            this.attributeFloats = (attributeFloats == null) ? new String[0] : attributeFloats;;
            this.attributeIntegers = (attributeIntegers == null) ? new String[0] : attributeIntegers;;
            this.attributeIntegerLists = (attributeIntegerLists == null) ? new String[0] : attributeIntegerLists;;
            this.childElements = childElements;
        }

    }
    protected final DocumentationElement rootElement = new DocumentationElement("experiment", "<h3>Experiment</h3>Root element of the experiment configuration file of which only one is permitted.", 1, 1,
            new String[]{"appNameDisplay"},
            new String[]{"appNameInternal"},
            new String[]{"backgroundColour", "complementColour0", "complementColour1", "complementColour2", "complementColour3", "complementColour4", "primaryColour0", "primaryColour1", "primaryColour2", "primaryColour3", "primaryColour4"},
            new String[]{"isScalable", "preserveLastState", "rotatable", "showMenuBar"},
            new String[]{"defaultScale"},
            new String[]{"textFontSize"},
            new String[0],
            new DocumentationElement[]{
                new DocumentationElement("preventWindowClose", "When true the a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1, new String[]{"featureText"}, null),
                new DocumentationElement("administration", "<h3>Administration</h3>", 0, 1, null, new DocumentationElement[]{
            new DocumentationElement("dataChannel", "", 0, 0, new String[]{"label"}, null, null, new String[]{"logToSdCard"}, null, new String[]{"channel"}, null, null)
        }),
                new DocumentationElement("scss", "", 0, 1, null, null),
                new DocumentationElement("metadata", "", 1, 1, null, new DocumentationElement[]{}),
                new DocumentationElement("presenter", "", 1, 0, null, new DocumentationElement[]{}),
                new DocumentationElement("stimuli", "", 1, 1, null, new DocumentationElement[]{})
            });
}
