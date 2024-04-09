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
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nl.mpi.tg.eg.experimentdesigner.model.BuildListing;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PublishEvents;

import org.xml.sax.SAXException;

/**
 * @since 2 Mar 2020 14:27:47 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ExperimentListingJsonExtractor {

    public void extractListingJson(File xmlFile, File listingDirectory, final String frinexVersion)
            throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException,
            XPathExpressionException, XpathExperimentException {
        String result = "";
        final File outputFile = new File(listingDirectory, xmlFile.getName().replaceAll(".xml$", ".json"));
        Writer fileWriter = getWriter(outputFile);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Experiment.class);
            Unmarshaller jaxbMarshaller = jaxbContext.<Experiment>createUnmarshaller();
            final Experiment experiment = (Experiment) jaxbMarshaller.unmarshal(xmlFile);
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(fileWriter,
                    new BuildListing(experiment, frinexVersion, xmlFile.getName().replaceAll(".xml$", "")));

            System.out.println(outputFile);
            try {
                fileWriter.close();
            } catch (IOException iOException) {
                result += "Could not write listing JSON file: ";
                result += iOException.getMessage();
                System.out.println(iOException.getMessage());
            }
            for (PublishEvents publishEvents : experiment.getPublishEvents()) {
                // check for icon.png in the stimuli directory if building cordova or electron
                if (publishEvents.isIsAndroid() || publishEvents.isIsiOS() || publishEvents.isDesktop()) {
                    File iconPng = new File(new File(new File(xmlFile.getParentFile().getParentFile(), "static"),
                            xmlFile.getName().replaceAll(".xml$", "")), "icon.png");
                    System.out.println("checking for icon.png in: " + iconPng.getAbsolutePath());
                    if (!iconPng.exists()) {
                        result += "To build mobile and desktop applications an icon.png must be provided in the stimuli directory.";
                    }
                }
                if (publishEvents.isIsAndroid() || publishEvents.isIsiOS() || publishEvents.isDesktop()) {
                    File iconPng = new File(new File(new File(xmlFile.getParentFile().getParentFile(), "static"),
                            xmlFile.getName().replaceAll(".xml$", "")), "splash.png");
                    System.out.println("checking for splash.png in: " + iconPng.getAbsolutePath());
                    if (!iconPng.exists()) {
                        result += "To build mobile and desktop applications a splash.png must be provided in the stimuli directory.";
                    }
                }
            }
        } catch (JAXBException jAXBException) {
            result += "Could not parse the experiment XML needed to create the build listing: ";
            result += jAXBException.getMessage();
            System.out.println(jAXBException.getMessage());
        }
        if (!result.isEmpty()) {
            throw new XpathExperimentException(result);
        }
    }

    protected Writer getWriter(final File outputFile) throws IOException {
        return new FileWriter(outputFile);
    }
}
