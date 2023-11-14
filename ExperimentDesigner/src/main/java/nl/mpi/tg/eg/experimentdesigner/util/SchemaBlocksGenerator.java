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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;

/**
 * @since 13 November 2023 12:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaBlocksGenerator extends AbstractSchemaGenerator {

    List<String> blockTypeLists = new ArrayList<>();

    private void getStart(Writer writer) throws IOException {
        writer.append("function getFeatureBlocks() {\n"
        // + " \"type\": \"frinex_experiment\",\n"
        // + " \"message0\": 'Experiment name %1',\n"
        // + " \"args0\": [\n"
        // + " {\n"
        // + " \"type\": \"field_input\",\n"
        // + " \"name\": \"appNameDisplay\",\n"
        // + " \"check\": \"String\"\n"
        // + " }\n"
        // + " ],\n"
        // + " \"message1\": \"Metadata %1\",\n"
        // + " \"args1\": [\n"
        // + " { \"type\": \"input_statement\", \"name\": \"DO\" }\n"
        // + " ],\n"
        // + " \"message2\": \"Presenters %1\",\n"
        // + " \"args2\": [\n"
        // + " { \"type\": \"input_statement\", \"name\": \"DO\" }\n"
        // + " ],\n"
        // + " \"message3\": \"Stimuli %1\",\n"
        // + " \"args3\": [\n"
        // + " { \"type\": \"input_statement\", \"name\": \"DO\" }\n"
        // + " ],"
        // + " \"colour\": 160\n"
        // + " }\n"
        // + "}"
        );
    }

//    private void defineBlocks(Writer writer) throws IOException {
//        for (String baseType : childTypeLists.keySet()) {
////            if ("allOnceUnordered_hasTrueFalseCondition".equals(baseType)) {
////            writer.append("<xs:group name=\"" + baseType + "Type\">\n");
////            writer.append("<xs:").append(baseType.startsWith("presenter") || baseType.startsWith(ChildType.choiceAnyCount.name()) ? "choice" : baseType.startsWith(ChildType.sequenceOnceOrdered.name()) ? "sequence" : "all").append(">\n");
////            for (String childTypeName : childTypeLists.get(baseType)) {
////                writer.append("<xs:element name=\"");
////                writer.append(childTypeName);
////                writer.append("\" type=\"");
////                writer.append(childTypeName);
////                writer.append("Type\"/>\n");
////            }
////            writer.append("</xs:").append(baseType.startsWith("presenter") || baseType.startsWith(ChildType.choiceAnyCount.name()) ? "choice" : baseType.startsWith(ChildType.sequenceOnceOrdered.name()) ? "sequence" : "all").append(">\n");
////            writer.append("</xs:group>\n");
//        }
////        }
//    }
    private void addToolbox(Writer writer) throws IOException {
        writer.append("    return {\n"
                + "        \"kind\": \"flyoutToolbox\",\n"
                + "        \"contents\": [\n");
//        writer.append("            {\n"
//                + "                \"kind\": \"block\",\n"
//                + "                \"type\": \"frinex_" + rootElement.typeName + "\"\n"
//                + "            },\n");
        for (String blockType : blockTypeLists) {
            writer.append("            {\n"
                    + "                \"kind\": \"block\",\n"
                    + "                \"type\": \"" + blockType + "\"\n"
                    + "            },\n");
        }
        writer.append("        ]\n"
                + "    };\n");
//        }
    }

    private int addAttributes(Writer writer, DocumentationElement currentElement, int argsCount) throws IOException {
        for (DocumentationAttribute attribute : currentElement.attributeTypes) {
            writer.append(" \"message" + argsCount + "\": '" + attribute.name + " %1',\n"
                    + " \"args" + argsCount + "\": [\n"
                    + " {\n"
                    + " \"type\": \"field_input\",\n"
                    + " \"name\": \"" + attribute.name + "\",\n"
                    + " \"check\": \"String\"\n"
                    + " }\n"
                    + " ],\n");
            argsCount++;
        }
        return argsCount;
    }

    private void addElement(Writer writer, final FeatureType featureType) throws IOException {
//blockTypeLists
    }

    private void addElement(Writer writer, DocumentationElement currentElement) throws IOException {
        int argsCount = 0;
        blockTypeLists.add("frinex_" + currentElement.typeName);
        writer.append("{\n"
                + "        \"type\": \"frinex_" + currentElement.typeName + "\",\n");
        // + "        \"message" + argsCount + "\": '" + currentElement.typeName + "',\n"
        // argsCount++;
        if (currentElement.documentationText != null) {
            // writer.append(currentElement.documentationText);
        }
        List<String> childTypeList = childTypeLists.containsKey(currentElement.typeExtends)
                ? childTypeLists.get(currentElement.typeExtends)
                : Collections.EMPTY_LIST;
//        if (currentElement.childElements.length + (currentElement.isRecursive ? 1 : 0) == 0 && childTypeList.isEmpty()
//                && !currentElement.hasStringContents) {
        writer.append(" \"message" + argsCount + "\": '" + currentElement.elementName + " %1',\n"
                + " \"args" + argsCount + "\": [\n"
                + " {\n"
                + " \"type\": \"input_dummy\",\n"
                + " }\n"
                + " ],\n");
        argsCount++;
        argsCount = addAttributes(writer, currentElement, argsCount);
//        }
        if (currentElement.childElements.length + (currentElement.isRecursive ? 1 : 0) > 0
                || !childTypeList.isEmpty()) {
        }
        if (!childTypeList.isEmpty()
                || currentElement.childElements.length + (currentElement.isRecursive ? 1 : 0) > 0) {
            if (childTypeList.size() + currentElement.childElements.length
                    + (currentElement.isRecursive ? 1 : 0) > 10) {
            }
            if (childTypeList.size() > 10) {
            }
            if (currentElement.isRecursive) {
            }
            for (String childElement : childTypeList) {
            }
            if (currentElement.childElements.length == 0) {
            } else if (currentElement.childElements.length > 1) {
                writer.append("\"message" + argsCount + "\": \" %1\",\n");
                writer.append("        \"args" + argsCount + "\": [\n"
                        + "            { ");
//                if (childElement.maxBounds != 1) {
                writer.append("\"type\": \"input_statement\", ");
//                } else if (childElement.minBounds > 0) {
//                } else {
//                    writer.append("\"type\": \"input_field\", ");
//                    writer.append("\"type\": \"input_value\", ");
//                }
                writer.append(""
                        //                                        + "\"name\": \"" + childElement.elementName + "\",\n"
                        + "\"check\": [");
                for (DocumentationElement childElement : currentElement.childElements) {
                    writer.append("\"frinex_" + childElement.typeName + "\",");
                }
                writer.append("]"
                        + "}\n        ],\n");
                argsCount++;
            }
        }
        if (currentElement.hasStringContents) {
            writer.append(" \"message" + argsCount + "\": '" + currentElement.elementName + " %1',\n"
                    + " \"args" + argsCount + "\": [\n"
                    + " {\n"
                    + " \"type\": \"field_input\",\n"
                    + " \"name\": \"" + currentElement.elementName + "\",\n"
                    + " \"check\": \"String\"\n"
                    + " }\n"
                    + " ],\n");
            argsCount++;
        }
        if (currentElement.childElements.length > 0 || !childTypeList.isEmpty() || currentElement.hasStringContents) {
        }
        for (DocumentationElement childElement : currentElement.childElements) {
            // omitting the translation elements here because they are already defined in
            // their parent elements
            if (!childElement.elementName.equals("translation")) {
            }
        }
        if (currentElement.allowsCustomImplementation) {
        }
        if (currentElement.maxBounds == 0) {
            writer.append("        \"previousStatement\": \"frinex_" + currentElement.typeName + "\",\n");
            writer.append("        \"nextStatement\": \"frinex_" + currentElement.typeName + "\",\n");
        } else if (!"experimentType".equals(currentElement.typeName)) {
            writer.append("        \"output\": \"frinex_" + currentElement.typeName + "\",\n");
        }
        writer.append("        \"colour\": 160\n},\n");
    }

    private void addTokenText(Writer writer, String tokenName, String usageDescription, String exampleUsage,
            String exampleResult) throws IOException {
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("}\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        writer.append("    Blockly.defineBlocksWithJsonArray([\n");
//        addElement(writer, rootElement);
//        FeatureType[] sortedFeatureTypes = FeatureType.values();
//        Arrays.sort(sortedFeatureTypes, new Comparator<FeatureType>() {
//            @Override
//            public int compare(FeatureType o1, FeatureType o2) {
//                return o1.name().compareTo(o2.name());
//            }
//        });
//        for (FeatureType featureType : sortedFeatureTypes) {
//            if (featureType.isChildType(FeatureType.Contitionals.none)) {
//                addElement(writer, new DocumentationElement(featureType));
//            }
//        }
//        for (FeatureType featureType : sortedFeatureTypes) {
//            if (featureType.isChildType(FeatureType.Contitionals.stimulusAction)) {
//                addElement(writer, new DocumentationElement(featureType));
//            }
//        }
//        for (FeatureType featureType : sortedFeatureTypes) {
//            if (featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
//                addElement(writer, new DocumentationElement(featureType));
//            }
//        }
//        for (FeatureType featureType : sortedFeatureTypes) {
//            if (!featureType.isChildType(FeatureType.Contitionals.none)
//                    && !featureType.isChildType(FeatureType.Contitionals.stimulusAction)
//                    && !featureType.isChildType(FeatureType.Contitionals.groupNetworkAction)) {
//                addElement(writer, new DocumentationElement(featureType));
//            }
//        }
//        for (TokenText currentToken : TokenText.values()) {
//            addTokenText(writer, currentToken.name(), currentToken.usageDescription, currentToken.exampleUsage,
//                    currentToken.exampleResult);
//        }
//        for (TokenMethod currentToken : TokenMethod.values()) {
//            addTokenText(writer, currentToken.name(), currentToken.usageDescription, currentToken.exampleUsage,
//                    currentToken.exampleResult);
//        }

        addElement(writer, rootElement);
//        writer.append("<!--deploymentType-->\n");
        addElement(writer, rootElement.childElements[1]);
//        writer.append("<!--validationService-->\n");
        addElement(writer, rootElement.childElements[2]);
//        writer.append("<!--validationType-->\n");
        addElement(writer, rootElement.childElements[2].childElements[0]);
//        writer.append("<!--administrationType-->\n");
        addElement(writer, rootElement.childElements[3]);
//        writer.append("<!--chartType-->\n");
        addElement(writer, rootElement.childElements[3].childElements[3]);
//        writer.append("<!--tableType-->\n");
        addElement(writer, rootElement.childElements[3].childElements[4]);
//        writer.append("<!--metadataType-->\n");
        addElement(writer, rootElement.childElements[5]);
        //        writer.append("<!--fieldType-->\n");
        addElement(writer, rootElement.childElements[5].childElements[0]);
//        writer.append("<!--presenterType-->\n");
        addElement(writer, rootElement.childElements[6]);
//        writer.append("<!--stimuliType-->\n");
        addElement(writer, rootElement.childElements[7]);
        addElement(writer, rootElement.childElements[7].childElements[0]);
        for (FeatureType featureType : FeatureType.values()) {
            addElement(writer, featureType);
        }
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[0]);
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[1]);
        writer.append("]);\n");
//        defineBlocks(writer);
        addToolbox(writer);
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
