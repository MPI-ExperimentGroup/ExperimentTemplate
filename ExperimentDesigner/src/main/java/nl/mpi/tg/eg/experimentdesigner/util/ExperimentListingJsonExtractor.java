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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PublishEvents;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @since 2 Mar 2020 14:27:47 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ExperimentListingJsonExtractor {

    public void extractListingJson(File xmlFile, File listingDirectory) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
        FileInputStream fileInputStream = new FileInputStream(xmlFile);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(fileInputStream);
        String fileName = xmlFile.getName();
        String result = "";

        final File outputFile = new File(listingDirectory, xmlFile.getName().replaceAll(".xml$", ".json"));
        Writer fileWriter = getWriter(outputFile);
        final Experiment experiment = new Experiment();
        experiment.setAppNameDisplay("asd");
        experiment.setAppNameInternal("asd");
        final Date publishDate = new Date(2020, 2, 20);
        final Date expiryDate = new Date(2020, 0, 20);
        final PublishEvents.PublishState publishState = PublishEvents.PublishState.editing;
        final boolean isWebApp = true;
        final boolean isiOS = true;
        final boolean isAndroid = true;
        final boolean isDesktop = true;
        final PublishEvents publishEvents = new PublishEvents(experiment, publishDate, expiryDate, publishState, isWebApp, isiOS, isAndroid, isDesktop);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(fileWriter, publishEvents);

        System.out.println(outputFile);
        try {
            fileWriter.close();
        } catch (IOException iOException) {
            result += "Could not write listing JSON file: ";
            result += iOException.getMessage();
            System.out.println(iOException.getMessage());
        }
        if (!result.isEmpty()) {
            throw new XpathExperimentException(result);
        }

    }

    protected Writer getWriter(final File outputFile) throws IOException {
        return new FileWriter(outputFile);
    }
}
