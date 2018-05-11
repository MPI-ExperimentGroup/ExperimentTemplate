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
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;

/**
 * @since May 9, 2018 5:41:05 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaGenerator {

    private void getStart(Writer writer) throws IOException {
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n");
    }

    private void addExperiment(Writer writer, final PresenterType[] presenterTypes) throws IOException {
        writer.append("<xs:simpleType name=\"rgbHexValue\">");
        writer.append("<xs:restriction base=\"xs:token\">");
        writer.append("<xs:pattern value=\"#[\\dA-F]{6}\"/>");
        writer.append("</xs:restriction>");
        writer.append("</xs:simpleType>");

        writer.append("<xs:element name=\"experiment\">\n").append("<xs:complexType>\n").append("<xs:sequence>\n");
        for (final PresenterType presenterType : presenterTypes) {
            writer.append("<xs:element ref=\"").append(presenterType.name()).append("\"/>\n");
        }
        writer.append("</xs:sequence>\n");
        for (String attributeStrings : new String[]{"appNameDisplay", "appNameInternal"}) {
            writer.append("<xs:attribute  name=\"").append(attributeStrings).append("\" type=\"xs:string\" use=\"required\"/>\n");
        }
        for (String attributeRGBs : new String[]{"backgroundColour", "complementColour0", "complementColour1", "complementColour2", "complementColour3", "complementColour4", "primaryColour0", "primaryColour1", "primaryColour2", "primaryColour3", "primaryColour4"}) {
            writer.append("<xs:attribute  name=\"").append(attributeRGBs).append("\" type=\"rgbHexValue\" use=\"required\"/>\n");
        }
        for (String attributeBooleans : new String[]{"isScalable", "preserveLastState", "rotatable", "showMenuBar"}) {
            writer.append("<xs:attribute  name=\"").append(attributeBooleans).append("\" type=\"xs:boolean\" use=\"required\"/>\n");
        }
        for (String attributeFloats : new String[]{"defaultScale"}) {
            writer.append("<xs:attribute  name=\"").append(attributeFloats).append("\" type=\"xs:decimal\" use=\"required\"/>\n");
        }
        for (String attributeIntegers : new String[]{"textFontSize"}) {
            writer.append("<xs:attribute  name=\"").append(attributeIntegers).append("\" type=\"xs:integer\" use=\"required\"/>\n");
        }
        writer.append("</xs:complexType>\n").append("</xs:element>");
    }

    private void addPresenter(Writer writer, final PresenterType presenterType) throws IOException {
        writer.append("<xs:element name=\"").append(presenterType.name()).append("\">\n").append("<xs:complexType>\n").append("<xs:sequence>\n");
        for (final FeatureType featureType : presenterType.getFeatureTypes()) {
            writer.append("<xs:element ref=\"").append(featureType.name()).append("\"/>\n");
        }
        writer.append("</xs:sequence>\n").append("</xs:complexType>\n").append("</xs:element>");
    }

    private void addFeature(Writer writer, final FeatureType featureType) throws IOException {
        writer.append("<xs:element name=\"").append(featureType.name()).append("\">\n").append("<xs:complexType>\n").append("<xs:sequence>\n");
        if (featureType.canHaveFeatures()) {
            for (final FeatureType featureRef : FeatureType.values()) {
                writer.append("<xs:element ref=\"").append(featureRef.name()).append("\"/>\n");
            }
        }
        writer.append("</xs:sequence>\n").append("</xs:complexType>\n").append("</xs:element>");
    }

    private void endState(Writer writer) throws IOException {
        writer.append("}\n");
    }

    private void getStateChange(Writer writer, final String state1, final String state2, final String... attributes) throws IOException {
        writer.append("<xs:element name=\"").append(state1).append("\">\n")
                .append("<xs:complexType>\n")
                .append("<xs:sequence>\n");
        for (final String value : attributes) {
            writer.append("<xs:element ref=\"").append(value).append("\"/>\n");
        }
        writer.append("</xs:sequence>\n").append("</xs:complexType>\n").append("</xs:element>\n");
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("</xs:schema>");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        addExperiment(writer, PresenterType.values());
        for (PresenterType presenterType : PresenterType.values()) {
            addPresenter(writer, presenterType);
//            for (FeatureType featureType : presenterType.getFeatureTypes()) {
//                getStateChange(writer, presenterType.name(), featureType.name());
//            }
//            endState(writer);
        }
        for (FeatureType featureType : FeatureType.values()) {
            addFeature(writer, featureType);
        }
        getEnd(writer);
        System.out.println(writer);
//        assertEquals(expResult, stringBuilder.toString());
    }

    public void createSchemaFile(final File schemaOutputFile) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
        FileWriter schemaOutputWriter = new FileWriter(schemaOutputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        appendContents(bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
