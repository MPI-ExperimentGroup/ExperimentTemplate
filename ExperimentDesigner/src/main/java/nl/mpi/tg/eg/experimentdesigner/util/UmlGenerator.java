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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @since 16 Oct 2020 11:16:45 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class UmlGenerator {

    public enum DiagramType {
        state, activity
    }

    public void generateUml(final File xmlFile, final File umlFileOut, final File svgFileOut, DiagramType diagramType) throws IOException, ParserConfigurationException, SAXException, JAXBException {
        final StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(xmlFile);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(fileInputStream);
        switch (diagramType) {
            case state:
                populateExperimentStateUml(xmlFile.getName().replaceAll("\\.xml$", ""), xmlDocument, stringBuilder);
                break;
            case activity:
                populateExperimentActivityUml(xmlFile.getName().replaceAll("\\.xml$", ""), xmlDocument, stringBuilder);
                break;
        }
//        JAXBContext jaxbContext = JAXBContext.newInstance(Experiment.class);
//        Unmarshaller jaxbMarshaller = jaxbContext.<Experiment>createUnmarshaller();
//        final Experiment experiment = (Experiment) jaxbMarshaller.unmarshal(xmlFile);
//        populateExperimentUml(experiment, stringBuilder);

        SourceStringReader reader = new SourceStringReader(stringBuilder.toString());
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        String desc = reader.generateImage(arrayOutputStream, new FileFormatOption(FileFormat.SVG));
        arrayOutputStream.close();
        //System.out.println(desc);
        //System.out.println(stringBuilder.toString());
        final String svg = new String(arrayOutputStream.toByteArray(), Charset.forName("UTF-8"));
        FileWriter umlOutputWriter = new FileWriter(umlFileOut);
        final BufferedWriter umlBufferedWriter = new BufferedWriter(umlOutputWriter);
        umlBufferedWriter.write(stringBuilder.toString());
        umlBufferedWriter.flush();
        umlBufferedWriter.close();
        FileWriter svgOutputWriter = new FileWriter(svgFileOut);
        final BufferedWriter svgBufferedWriter = new BufferedWriter(svgOutputWriter);
        svgBufferedWriter.write(svg);
        svgBufferedWriter.flush();
        svgBufferedWriter.close();
    }

    private void populateExperimentStateUml(String appNameInternal, final Document xmlDocument, final StringBuilder stringBuilder) {
        stringBuilder.append("@startuml\n");
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        try {
            // String appNameInternal = (String) validationXPath.compile("/experiment/@appNameInternal").evaluate(xmlDocument, XPathConstants.STRING);
            stringBuilder.append("title ").append(appNameInternal).append("\n");
            NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/presenter").evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList1.getLength(); index++) {
                final NamedNodeMap attributes = nodeList1.item(index).getAttributes();
                final String selfName = (attributes.getNamedItem("self") != null) ? attributes.getNamedItem("self").getNodeValue() : "";
                if (index == 0) {
                    stringBuilder.append("[*] --> ").append(selfName).append("\n");
                }
                stringBuilder.append("state ").append(selfName).append("\n");
                if (attributes.getNamedItem("back") != null) {
                    stringBuilder.append(selfName).append(" --> ").append(attributes.getNamedItem("back").getNodeValue()).append(" : back\n");
                }
                if (attributes.getNamedItem("next") != null) {
                    stringBuilder.append(selfName).append(" --> ").append(attributes.getNamedItem("next").getNodeValue()).append(" : next\n");
                }
                NodeList targetNodes = (NodeList) validationXPath.compile("descendant::*[@target]").evaluate(nodeList1.item(index), XPathConstants.NODESET);
                for (int targetNodeIndex = 0; targetNodeIndex < targetNodes.getLength(); targetNodeIndex++) {
                    final NamedNodeMap targetAttributes = targetNodes.item(targetNodeIndex).getAttributes();
                    if (targetAttributes.getNamedItem("target") != null) {
                        stringBuilder.append(selfName).append(" --> ").append(targetAttributes.getNamedItem("target").getNodeValue());
                        if (targetAttributes.getNamedItem("featureText") != null) {
                            stringBuilder.append(" : ").append(targetAttributes.getNamedItem("featureText").getNodeValue());
                        }
                        stringBuilder.append("\n");
                    }
                }
                NodeList allMenuItemsNodes = (NodeList) validationXPath.compile("descendant::allMenuItems").evaluate(nodeList1.item(index), XPathConstants.NODESET);
                if (allMenuItemsNodes.getLength() > 0) {
                    // add presenters for allMenuItems
                    for (int allMenuItemsIndex = 0; allMenuItemsIndex < nodeList1.getLength(); allMenuItemsIndex++) {
                        if (nodeList1.item(allMenuItemsIndex).getAttributes().getNamedItem("self") != null) {
                            stringBuilder.append(selfName).append(" --> ").append(nodeList1.item(allMenuItemsIndex).getAttributes().getNamedItem("self").getNodeValue()).append("\n");
                        }
                    }
                }
            }
        } catch (XPathExpressionException exception) {
            stringBuilder.append("note right: ").append(exception).append("\n");
            stringBuilder.append("end note\n");
        }
        stringBuilder.append("@enduml\n");
    }

    private void populateExperimentActivityUml(String appNameInternal, final Document xmlDocument, final StringBuilder stringBuilder) {
        stringBuilder.append("@startuml\n");
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        try {
            // String appNameInternal = (String) validationXPath.compile("/experiment/@appNameInternal").evaluate(xmlDocument, XPathConstants.STRING);
            stringBuilder.append("title ").append(appNameInternal).append("\n");
            stringBuilder.append("start\n");

            NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/presenter").evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList1.getLength(); index++) {
                final NamedNodeMap attributes = nodeList1.item(index).getAttributes();
                stringBuilder.append(":").append(attributes.getNamedItem("self").getNodeValue()).append(";\n");
                if (attributes.getNamedItem("back") != null) {
                    stringBuilder.append("if (navigation) then (back)\n");
                    stringBuilder.append(":").append(attributes.getNamedItem("back").getNodeValue()).append(";\n");
                }
                if (attributes.getNamedItem("next") != null) {
                    if (attributes.getNamedItem("back") != null) {
                        stringBuilder.append("else (next)\n");
                    } else {
                        stringBuilder.append("if (navigation) then (next)\n");
                    }
                    stringBuilder.append("  :").append(attributes.getNamedItem("next").getNodeValue()).append(";\n");
                }
                if (attributes.getNamedItem("next") != null || attributes.getNamedItem("back") != null) {
                    stringBuilder.append("endif\n");
                }
            }
        } catch (XPathExpressionException exception) {
            stringBuilder.append("note right: ").append(exception).append("\n");
            stringBuilder.append("end note\n");
        }
        stringBuilder.append("@enduml\n");
    }
}
