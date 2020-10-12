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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @since 9 Oct 2020 15:32:52 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class XmlJsonTemplate {

    private void appendJsonContents(final Document xmlDocument, final BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.append("todo: add JSON contents from XML containing template elements.");
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
            BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
            appendJsonContents(xmlDocument, bufferedWriter);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (SAXException saxe) {
            throw new XpathExperimentException(saxe.getMessage());
        }
    }

    public void validateTemplateXML(File xmlFiles) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
    }
}