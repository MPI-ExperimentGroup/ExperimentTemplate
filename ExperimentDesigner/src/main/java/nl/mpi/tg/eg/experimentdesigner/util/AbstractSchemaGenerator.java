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

    protected static final String ROOT_ELEMENT_DOCUMENTATION = "Root element of the experiment configuration file of which only one is permitted.";
    protected final String[] rootAttributeStrings = new String[]{"appNameDisplay"};
    protected final String[] rootAttributeLowercase = new String[]{"appNameInternal"};
    protected final String[] rootAttributeRGBs = new String[]{"backgroundColour", "complementColour0", "complementColour1", "complementColour2", "complementColour3", "complementColour4", "primaryColour0", "primaryColour1", "primaryColour2", "primaryColour3", "primaryColour4"};
    protected final String[] rootAttributeBooleans = new String[]{"isScalable", "preserveLastState", "rotatable", "showMenuBar"};
    protected final String[] rootAttributeFloats = new String[]{"defaultScale"};
    protected final String[] rootAttributeIntegers = new String[]{"textFontSize"};
    protected final String[] rootAttributeIntegerLists = new String[]{};
    protected final String[] rootChildElements = new String[]{"preventWindowClose", "administration", "scss", "metadata", "presenter", "stimuli"};

    protected class DocumentationElement {

        final String elementName;
        final String documentationText;
        final int minBounds;
        final int maxBounds;
        final String[] attributes;
        final DocumentationElement[] childElements;

        public DocumentationElement(String elementName, String documentationText, int minBounds, int maxBounds, String[] attributes, DocumentationElement[] childElements) {
            this.elementName = elementName;
            this.documentationText = documentationText;
            this.minBounds = minBounds;
            this.maxBounds = maxBounds;
            this.attributes = attributes;
            this.childElements = childElements;
        }
    }
    protected final DocumentationElement rootElement = new DocumentationElement("Experiment", ROOT_ELEMENT_DOCUMENTATION, 1, 1, null, new DocumentationElement[]{
        new DocumentationElement("preventWindowClose", "When true the a popup will warn before closing the browser window by showing the message in 'featureText'. Not all browsers will respect this in the same way, so test this on the intended platforms.", 0, 1, new String[]{"featureText"}, null),
        new DocumentationElement("administration", "", 0, 1, null, new DocumentationElement[]{}),
        new DocumentationElement("scss", "", 0, 1, null, null),
        new DocumentationElement("metadata", "", 1, 1, null, new DocumentationElement[]{}),
        new DocumentationElement("presenter", "", 1, 0, null, new DocumentationElement[]{}),
        new DocumentationElement("stimuli", "", 1, 1, null, new DocumentationElement[]{})
    });
}
