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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;

/**
 * @since 13 November 2023 12:33 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaBlocksGenerator extends AbstractSchemaGenerator {

    List<String> adminTypeLists = new ArrayList<>();
    List<String> featureTypeLists = new ArrayList<>();
    List<String> templateTypeLists = new ArrayList<>();
    Map<String, List<String>> typeSubTypes = new HashMap<>();

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
    private void addJavaScriptGenerator(Writer writer) throws IOException {
        for (List<String> blockTypeLists : new List[]{adminTypeLists, featureTypeLists}) {
            for (String blockType : blockTypeLists) {
                writer.append("    javascript.javascriptGenerator.forBlock['" + blockType + "'] = function(block, generator) {\n"
                        // block.getFieldValue("appNameDisplay")
                        // + "  const statement = generator.statementToCode(block, 'MY_STATEMENT_INPUT');\n"
                        // + "  const value = generator.valueToCode(block, 'MY_VALUE_INPUT', javascript.Order.ATOMIC);\n"
                        //                    + "    var appNameDisplay = block.getFieldValue('appNameDisplay');\n"
                        + "    var childData = '';\n"
                //                        + "    block.getChildren().forEach(function (childBlock){console.log(childBlock.type)})"
                //                        + "    for (var childIndex = 0; childIndex < block.getChildren().length; childIndex++) {\n"
                //+ "     childData += generator.statementToCode(block.getChildren()[childIndex]);"
                //                        + "     childData += generator.statementToCode(block, block.getChildren()[childIndex].type);\n"
                //                        + "    }\n"
                );
                if (typeSubTypes.containsKey(blockType)) {
                    for (String currentSubType : typeSubTypes.get(blockType)) {
                        writer.append("     childData += generator.statementToCode(block, '" + currentSubType + "');\n");
                    }
                }
                writer.append("    return '" + blockType + "(\\'block_id_' + block.id + '\\', '  + childData + '\\);\\n';\n"
                        + "  };\n");
            }
        }
    }

    private void addToolbox(Writer writer) throws IOException {
        writer.append("  return {\n"
                + "    \"kind\": \"categoryToolbox\",\n"
                + "    \"contents\": [\n");
        writer.append("            {\n"
                + "                \"kind\":\"category\",\n"
                + "            \"name\":\"Experiment\",\n"
                + "            \"categorystyle\":\"logic_category\","
                + "            \"contents\":[");
        for (String blockType : adminTypeLists) {
            writer.append("      {\n"
                    + "        \"kind\": \"block\",\n"
                    + "        \"type\": \"" + blockType + "\"\n"
                    + "      },\n");
        }
        writer.append("            ]}, {\n"
                + "                \"kind\":\"category\",\n"
                + "            \"name\":\"Feature\",\n"
                + "            \"categorystyle\":\"loop_category\","
                + "            \"contents\":[");
        for (String blockType : featureTypeLists) {
            writer.append("      {\n"
                    + "        \"kind\": \"block\",\n"
                    + "        \"type\": \"" + blockType + "\"\n"
                    + "      },\n");
        }
        writer.append("            ]}, {\n"
                + "                \"kind\":\"category\",\n"
                + "            \"name\":\"Template\",\n"
                + "            \"categorystyle\":\"logic_category\","
                + "            \"contents\":[");
        for (String blockType : templateTypeLists) {
            writer.append("      {\n"
                    + "        \"kind\": \"block\",\n"
                    + "        \"type\": \"" + blockType + "\"\n"
                    + "      },\n");
        }
        writer.append("    ]}]\n"
                + "  };\n");
//        }
    }

    private int addAttributes(Writer writer, DocumentationElement currentElement, int argsCount) throws IOException {
        for (DocumentationAttribute attribute : currentElement.attributeTypes) {
            if (!attribute.optional) {
                writer.append("      \"message" + argsCount + "\": '" + attribute.name + " %1',\n"
                        + "      \"args" + argsCount + "\": [\n"
                        + "        {\n"
                        + "          \"type\": \"field_input\",\n"
                        + "          \"name\": \"" + attribute.name + "\",\n"
                        + "          \"check\": \"String\"\n"
                        + "        }\n"
                        + "      ],\n");
                argsCount++;
            }
        }
        return argsCount;
    }

    private void addElement(Writer writer, final FeatureType featureType) throws IOException {
        final String currentType = "frinex_" + featureType.name() + "Type";
        writer.append("    {\n"
                + "      \"type\": \"" + currentType + "\",\n"
                + "      \"message0\": '" + featureType.name() + " %1',\n"
                + "      \"args0\": [\n"
                + "        {\n"
                + "          \"type\": \"input_dummy\",\n"
                + "        }\n"
                + "      ],\n");
        List<String> currentSubTypes;
        if (typeSubTypes.containsKey(currentType)) {
            currentSubTypes = typeSubTypes.get(currentType);
        } else {
            currentSubTypes = new ArrayList<>();
            typeSubTypes.put(currentType, currentSubTypes);
        }
        int argsCount = 0;
        switch (featureType.getRequiresChildType()) {
            case any:
                writer.append("      \"message" + argsCount + "\": 'Features %1',\n"
                        + "      \"args" + argsCount + "\": [\n"
                        + "        {\n"
                        + "          \"type\": \"field_input\",\n"
                        + "          \"name\": \"FeatureTypes\",\n"
                        + "          \"check\": \"frinex_featureType\"\n"
                        + "        }\n"
                        + "      ],\n");
                currentSubTypes.add("FeatureTypes");
                argsCount++;
                break;
        }
        writer.append("      },\n");
        featureTypeLists.add("frinex_" + featureType.name() + "Type");
    }

    private void addTemplate(Writer writer, final String templateType) throws IOException {
        writer.append("    {\n"
                + "      \"type\": \"frinex_" + templateType + "Type\",\n"
                + "      \"message0\": '" + templateType + " %1',\n"
                + "      \"args0\": [\n"
                + "        {\n"
                + "          \"type\": \"input_dummy\",\n"
                + "        }\n"
                + "      ]},\n");
        templateTypeLists.add("frinex_" + templateType + "Type");
    }

    private void addElement(Writer writer, DocumentationElement currentElement, String[] precedingBlocks) throws IOException {
        int argsCount = 0;
        // TODO: pass the types list eg "presenterType" as a parameter to this menthod and separate them distinct input_values
//        final List<String>  = Arrays.asList(new String[]{"validationType", "fieldType", "presenterType", "stimulusType"});
        final HashMap<String, String> separatedGroups = new HashMap<>();
        separatedGroups.put("validationType", "Administration");
        separatedGroups.put("scssType", "CSS");
        separatedGroups.put("fieldType", "Metadata");
        separatedGroups.put("presenterType", "Presenters");
        separatedGroups.put("stimulusType", "Stimuli");
        final String currentType = "frinex_" + currentElement.typeName;
        adminTypeLists.add(currentType);
        List<String> currentSubTypes;
        if (typeSubTypes.containsKey(currentType)) {
            currentSubTypes = typeSubTypes.get(currentType);
        } else {
            currentSubTypes = new ArrayList<>();
            typeSubTypes.put(currentType, currentSubTypes);
        }
        writer.append("    {\n"
                + "      \"type\": \"frinex_" + currentElement.typeName + "\",\n");
        // + " \"message" + argsCount + "\": '" + currentElement.typeName + "',\n"
        // argsCount++;
        if (currentElement.documentationText != null) {
            // writer.append(currentElement.documentationText);
        }
        List<String> childTypeList = childTypeLists.containsKey(currentElement.typeExtends)
                ? childTypeLists.get(currentElement.typeExtends)
                : Collections.EMPTY_LIST;
//        if (currentElement.childElements.length + (currentElement.isRecursive ? 1 : 0) == 0 && childTypeList.isEmpty()
//                && !currentElement.hasStringContents) {
        writer.append("      \"message" + argsCount + "\": '" + currentElement.elementName + " %1',\n"
                + "      \"args" + argsCount + "\": [\n"
                + "        {\n"
                + "          \"type\": \"input_dummy\",\n"
                + "        }\n"
                + "      ],\n");
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
//            
            if (currentElement.childElements.length == 0) {
            } else if (currentElement.childElements.length == 1 && currentElement.childElements[0].maxBounds == 0) {
                writer.append("      \"message" + argsCount + "\": \" %1\",\n");
                writer.append("      \"args" + argsCount + "\": [\n"
                        + "            {\n");
//                if (childElement.maxBounds != 1) {
    // TODO: use typeSubTypes to store the fields for each tyoe or remove typeSubTypes
//                typeSubTypes
                writer.append("              \"type\": \"input_statement\",\n              \"name\": \"DO\",\n");
//                } else if (childElement.minBounds > 0) {
//                } else {
//                    writer.append("\"type\": \"input_field\", ");
//                    writer.append("\"type\": \"input_value\", ");
//                }
                writer.append(""
                        //                                        + "\"name\": \"" + childElement.elementName + "\",\n"
                        + "              \"check\": [\n");
                for (DocumentationElement childElement : currentElement.childElements[0].childElements) {
                    writer.append("                \"frinex_" + childElement.typeName + "\",\n");
                }
                writer.append("              ]\n"
                        + "          }\n        ],\n");
                argsCount++;
            } else if (currentElement.childElements.length > 1) {
                List<String> inputFields = new ArrayList<>();
                List<List<String>> inputStatementList = new ArrayList<>();
                List<String> inputStatementItem = null;
                for (DocumentationElement childElement : currentElement.childElements) {
                    if (inputStatementItem == null || separatedGroups.containsKey(childElement.typeName)) {
                        inputStatementItem = new ArrayList<>();
                        inputStatementList.add(inputStatementItem);
                    }
                    if (childElement.maxBounds == 0) {
                        inputStatementItem.add(childElement.typeName);
                    } else if (childElement.maxBounds == 1 && childElement.childElements.length > 0) {
                        for (DocumentationElement grandchildElement : childElement.childElements) {
                            inputStatementItem.add(grandchildElement.typeName);
                        }
                    } else {
                        inputFields.add(childElement.typeName);
                    }
                    if (separatedGroups.containsKey(childElement.typeName)) {
                        inputStatementItem = null;
                    }
                }
                if (!inputFields.isEmpty()) {
                    writer.append("      \"message" + argsCount + "\": \" %1\",\n");
                    writer.append("      \"args" + argsCount + "\": [\n"
                            + "        {\n");
//                if (childElement.maxBounds != 1) {
                    writer.append("          \"type\": \"input_value\",\n          \"name\": \"DO\",\n");
//                } else if (childElement.minBounds > 0) {
//                } else {
//                    writer.append("\"type\": \"input_field\", ");
//                    writer.append("\"type\": \"input_value\", ");
//                }
                    writer.append(""
                            //                                        + "\"name\": \"" + childElement.elementName + "\",\n"
                            + "          \"check\": [\n");
                    for (String inputField : inputFields) {
                        writer.append("            \"frinex_" + inputField + "\",\n");
                    }
                    writer.append("          ]\n"
                            + "        }\n      ],\n");
                    argsCount++;
                }
                for (List<String> inputStatements : inputStatementList) {
                    if (!inputStatements.isEmpty()) {
                        writer.append("      \"message" + argsCount + "\": \""
                                + ((separatedGroups.containsKey(inputStatements.get(0))) ? separatedGroups.get(inputStatements.get(0)) : inputStatements.get(0))
                                + " %1\",\n");
                        writer.append("      \"args" + argsCount + "\": [\n"
                                + "        {\n");
//                if (childElement.maxBounds != 1) {

                        writer.append("          \"type\": \"input_statement\",\n          \"name\": \"");
                        final String subTypeGroup = (separatedGroups.containsKey(inputStatements.get(0))) ? separatedGroups.get(inputStatements.get(0)) : inputStatements.get(0);
                        // TODO: use typeSubTypes to store the fields for each tyoe or remove typeSubTypes
                        currentSubTypes.add(subTypeGroup);
                        writer.append(subTypeGroup);
                        writer.append("\",\n");
//                } else if (childElement.minBounds > 0) {
//                } else {
//                    writer.append("\"type\": \"input_field\", ");
//                    writer.append("\"type\": \"input_value\", ");
//                }
                        writer.append(""
                                //                                        + "\"name\": \"" + childElement.elementName + "\",\n"
                                + "          \"check\": [\n");
                        for (String inputStatement : inputStatements) {
                            writer.append("            \"frinex_" + inputStatement + "\",\n");
                        }
                        writer.append("          ]\n"
                                + "        }\n      ],\n");
                        argsCount++;

                    }
                }
            }
        }
        if (currentElement.hasStringContents) {
            writer.append("      \"message" + argsCount + "\": '" + currentElement.elementName + " %1',\n"
                    + "      \"args" + argsCount + "\": [\n"
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
        if (precedingBlocks != null) {
            writer.append("      \"previousStatement\": [\n");
            for (String blockType : precedingBlocks) {
                writer.append("        \"frinex_" + blockType + "Type\",\n");
            }
            writer.append("      ],\n");
            writer.append("      \"nextStatement\": [\n");
            for (String blockType : precedingBlocks) {
                writer.append("        \"frinex_" + blockType + "Type\",\n");
            }
            writer.append("      ],\n");
//        } else if (currentElement.maxBounds == 0) {
//            writer.append(" \"previousStatement\": \"frinex_" + currentElement.typeName + "\",\n");
//            writer.append(" \"nextStatement\": \"frinex_" + currentElement.typeName + "\",\n");
//        } else if (!precedingBlocks.isEmpty()) {
//            writer.append(" \"previousStatement\": \"frinex_" + currentElement.typeName + "\",\n");
//            writer.append(" \"nextStatement\": \"frinex_" + currentElement.typeName + "\",\n");
        } else if (!"experimentType".equals(currentElement.typeName)) {
            writer.append("      \"output\": \"frinex_" + currentElement.typeName + "\",\n");
        }
        writer.append("      \"colour\": 160\n    },\n");
    }

    private void addTokenText(Writer writer, String tokenName, String usageDescription, String exampleUsage,
            String exampleResult) throws IOException {
    }

    private void getEnd(Writer writer) throws IOException {
        writer.append("}\n");
    }

    public void appendContents(Writer writer) throws IOException {
        getStart(writer);
        writer.append("  Blockly.defineBlocksWithJsonArray([\n");
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

        addElement(writer, rootElement, null);
//        writer.append("<!--deploymentType-->\n");
        addElement(writer, rootElement.childElements[1], null);
//        writer.append("<!--validationService-->\n");
        addElement(writer, rootElement.childElements[2], null);
//        writer.append("<!--validationType-->\n");
        addElement(writer, rootElement.childElements[2].childElements[0], new String[]{"validation"});
//        writer.append("<!--administrationType-->\n");
        addElement(writer, rootElement.childElements[3], null);
//        writer.append("<!--chartType-->\n");
        addElement(writer, rootElement.childElements[3].childElements[3], new String[]{"validation",
            "adminUser",
            "dataAgreementField",
            "dataChannel",
            "adminChart",
            "dataTable"});
//        writer.append("<!--tableType-->\n");
        addElement(writer, rootElement.childElements[3].childElements[4], new String[]{"validation",
            "adminUser",
            "dataAgreementField",
            "dataChannel",
            "adminChart",
            "dataTable"});
//        writer.append("<!--metadataType-->\n");
//        addElement(writer, rootElement.childElements[5], new String[]{"field"});
        //        writer.append("<!--fieldType-->\n");
        addElement(writer, rootElement.childElements[5].childElements[0], new String[]{"field"});
//        writer.append("<!--presenterType-->\n");
        addElement(writer, rootElement.childElements[6], new String[]{"presenter"});
//        writer.append("<!--stimuliType-->\n");
//        addElement(writer, rootElement.childElements[7], new String[]{"stimulus"});
        addElement(writer, rootElement.childElements[7].childElements[0], new String[]{"stimulus"});
        for (FeatureType featureType : FeatureType.values()) {
            addElement(writer, featureType);
        }
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[0], null);
        addElement(writer, new DocumentationElement(FeatureType.loadStimulus).childElements[1], null);
        addTemplate(writer, "TemplateA");
        addTemplate(writer, "TemplateB");
        addTemplate(writer, "TemplateC");
        writer.append("  ]);\n");
//        defineBlocks(writer);
        addJavaScriptGenerator(writer);
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
