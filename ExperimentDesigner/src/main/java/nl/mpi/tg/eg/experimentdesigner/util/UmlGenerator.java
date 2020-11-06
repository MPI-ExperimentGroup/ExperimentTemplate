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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
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
                populateExperimentStateUml(xmlDocument, stringBuilder);
                break;
            case activity:
                populateExperimentActivityUml(xmlDocument, stringBuilder);
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
        System.out.println(desc);
        System.out.println(stringBuilder.toString());
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

    private void populateExperimentStateUml(final Document xmlDocument, final StringBuilder stringBuilder) {
        stringBuilder.append("@startuml\n");
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        try {
            String appNameInternal = (String) validationXPath.compile("/experiment/@appNameInternal").evaluate(xmlDocument, XPathConstants.STRING);
            stringBuilder.append("title ").append(appNameInternal).append("\n");
            NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/presenter").evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList1.getLength(); index++) {
                final NamedNodeMap attributes = nodeList1.item(index).getAttributes();
                if (index == 0) {
                    stringBuilder.append("[*] --> ").append(attributes.getNamedItem("self").getNodeValue()).append("\n");
                }
                stringBuilder.append("state ").append(attributes.getNamedItem("self").getNodeValue()).append("\n");
                if (attributes.getNamedItem("back") != null) {
                    stringBuilder.append(attributes.getNamedItem("self").getNodeValue()).append(" --> ").append(attributes.getNamedItem("back").getNodeValue()).append("\n");
                }
                if (attributes.getNamedItem("next") != null) {
                    stringBuilder.append(attributes.getNamedItem("self").getNodeValue()).append(" --> ").append(attributes.getNamedItem("next").getNodeValue()).append("\n");
                }
            }
        } catch (XPathExpressionException exception) {
            stringBuilder.append("note right: ").append(exception).append("\n");
            stringBuilder.append("end note\n");
        }
        stringBuilder.append("@enduml\n");
    }

    private void populateExperimentActivityUml(final Document xmlDocument, final StringBuilder stringBuilder) {
        stringBuilder.append("@startuml\n");
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        try {
            String appNameInternal = (String) validationXPath.compile("/experiment/@appNameInternal").evaluate(xmlDocument, XPathConstants.STRING);
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
