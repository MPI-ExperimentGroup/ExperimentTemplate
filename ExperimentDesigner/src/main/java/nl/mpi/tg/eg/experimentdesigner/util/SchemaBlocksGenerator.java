/*
 * Copyright (C) 2023 Max Planck Institute for Psycholinguistics, Nijmegen
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.TokenMethod;
import nl.mpi.tg.eg.experimentdesigner.model.TokenText;

/**
 * @since 13 November 2023 12:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */

public class SchemaBlocksGenerator extends AbstractSchemaGenerator {

    private void getStart(Writer writer) throws IOException {
        writer.append("function getFeatureBlocks() {\n" +
                "return {\n" + //
                "  \"kind\": \"flyoutToolbox\",\n" + //
                "  \"contents\": [\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"controls_if\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"controls_repeat_ext\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"logic_compare\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"math_number\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"math_arithmetic\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"text\"\n" + //
                "      },\n" + //
                "      {\n" + //
                "          \"kind\": \"block\",\n" + //
                "          \"type\": \"text_print\"\n" + //
                "      },\n" + //
                "  ]\n" + //
                "};");
    }

    private void addAttributes(Writer writer, DocumentationElement currentElement) throws IOException {
    }

    private void addElement(Writer writer, DocumentationElement currentElement) throws IOException {
        addAttributes(writer, currentElement);
    }

    private void addTokenText(Writer writer, String tokenName, String usageDescription, String exampleUsage,
            String exampleResult) throws IOException {
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("}\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        addElement(writer, rootElement);
        FeatureType[] sortedFeatureTypes = FeatureType.values();
        Arrays.sort(sortedFeatureTypes, new Comparator<FeatureType>() {
            @Override
            public int compare(FeatureType o1, FeatureType o2) {
                return o1.name().compareTo(o2.name());
            }
        });
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.none)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.stimulusAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        for (FeatureType featureType : sortedFeatureTypes) {
            if (featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        for (FeatureType featureType : sortedFeatureTypes) {
            if (!featureType.isChildType(FeatureType.Contitionals.none)
                    && !featureType.isChildType(FeatureType.Contitionals.stimulusAction)
                    && !featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
                addElement(writer, new DocumentationElement(featureType));
            }
        }
        for (TokenText currentToken : TokenText.values()) {
            addTokenText(writer, currentToken.name(), currentToken.usageDescription, currentToken.exampleUsage,
                    currentToken.exampleResult);
        }
        for (TokenMethod currentToken : TokenMethod.values()) {
            addTokenText(writer, currentToken.name(), currentToken.usageDescription, currentToken.exampleUsage,
                    currentToken.exampleResult);
        }
        getEnd(writer);
    }

    public void createJsFile(final File schemaOutputFile) throws IOException {
        System.out.println("blocksFile: " + schemaOutputFile);
        FileWriter schemaOutputWriter = new FileWriter(schemaOutputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        appendContents(bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
