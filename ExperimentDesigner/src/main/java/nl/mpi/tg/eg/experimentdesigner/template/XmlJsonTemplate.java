/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nl.mpi.tg.eg.experimentdesigner.util.XpathExperimentException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @since 9 Oct 2020 15:32:52 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class XmlJsonTemplate {

    private void appendJsonContents(final Node templateElement, final JSONObject jSONObject, final BufferedWriter bufferedWriter) throws IOException {
        Node currentElement = templateElement;
        while (currentElement != null) {
            System.out.println(currentElement.getLocalName());
            System.out.println(currentElement.getNodeName());
            if ("template".equals(currentElement.getNodeName())) {
                final NamedNodeMap attributes = currentElement.getAttributes();
                final Node jsonPath = attributes.getNamedItem("jsonPath");
                final Node ifJsonPath = attributes.getNamedItem("ifJsonPath");
                final Node defaultValue = attributes.getNamedItem("defaultValue");
                final Node attributeName = attributes.getNamedItem("attributeName");
                final Node metadataJsonPath = attributes.getNamedItem("metadataJsonPath");
                final Node stimuliJsonPath = attributes.getNamedItem("stimuliJsonPath");
                final Node description = attributes.getNamedItem("description");
                final JSONObject jsonChildObject = new JSONObject();
                if (description != null) {
                    //bufferedWriter.append("\n// " + description.getNodeValue() + "\n");
                    jsonChildObject.put("description", description.getNodeValue());
                }
                if (jsonPath != null) {
                    //bufferedWriter.append("\n\"" + jsonPath.getNodeValue() + "\": ");
//                    jSONObject.put(jsonPath.getNodeValue(), "value");
                    jSONObject.put(jsonPath.getNodeValue(), jsonChildObject);
                }
                if (ifJsonPath != null) {
                    /*bufferedWriter.append("\n\"" + ifJsonPath.getNodeValue() + "\": ");
                    if (defaultValue != null) {
                        bufferedWriter.append("\"" + defaultValue.getNodeValue() + "\";\n");
                    } else {
                        bufferedWriter.append("\"\";\n");
                    }*/
                    jSONObject.put(ifJsonPath.getNodeValue(), jsonChildObject);
                    jsonChildObject.put("value", true);
                }
                if (metadataJsonPath != null) {
                    //bufferedWriter.append("\n\"" + metadataJsonPath.getNodeValue() + "\": ");
//                    jSONObject.put(metadataJsonPath.getNodeValue(), "value");
                    jSONObject.put(metadataJsonPath.getNodeValue(), jsonChildObject);
                    jsonChildObject.put("metadata", new JSONArray());
                }
                if (stimuliJsonPath != null) {
                    //bufferedWriter.append("\n\"" + stimuliJsonPath.getNodeValue() + "\": ");
//                    jSONObject.put(stimuliJsonPath.getNodeValue(), "value");
                    jSONObject.put(stimuliJsonPath.getNodeValue(), jsonChildObject);
                    jsonChildObject.put("stimuli", new JSONArray());
                }
                if (attributeName != null) {
                    final NamedNodeMap parentElementAttributes = currentElement.getParentNode().getAttributes();
                    final Node attributeNameValue = parentElementAttributes.getNamedItem(attributeName.getNodeValue());
                    if (attributeNameValue != null) {
                        //bufferedWriter.append("\"" + attributeNameValue.getNodeValue() + "\";");
                        jsonChildObject.put("value", attributeNameValue.getNodeValue());
                    } else {
                        //bufferedWriter.append("\"no attribute value found for: " + attributeName.getNodeValue() + "\";\n");
                        jsonChildObject.put("value", "");
                    }
                }
            } else {
                appendJsonContents(currentElement.getFirstChild(), jSONObject, bufferedWriter);
            }
            currentElement = currentElement.getNextSibling();
        }
    }

    // use template.xsd
    // and use template_example.xml
    // take example XML with template elements and generate an exampe JSON file with comments and values from the XML such that it will rebuild that experiment
    public void generateJsonFromXML(File xmlFile, File jsonFileOut) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
        try {
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(fileInputStream);
            FileWriter schemaOutputWriter = new FileWriter(jsonFileOut);
            final BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
            final JSONObject jsonObject = new JSONObject();
            //bufferedWriter.append("{\n");
            appendJsonContents(xmlDocument.getFirstChild(), jsonObject, bufferedWriter);
            //bufferedWriter.append("}\n");
            bufferedWriter.write(jsonObject.toString(3));
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (SAXException saxe) {
            throw new XpathExperimentException(saxe.getMessage());
        }
    }

    public void validateTemplateXML(final File templateFile) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
    }
}
