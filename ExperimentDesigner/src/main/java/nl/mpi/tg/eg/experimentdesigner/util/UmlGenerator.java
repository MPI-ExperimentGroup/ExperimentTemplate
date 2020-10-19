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
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import org.xml.sax.SAXException;

/**
 * @since 16 Oct 2020 11:16:45 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class UmlGenerator {

    public void generateUml(File xmlFile, File svgFileOut) throws IOException, ParserConfigurationException, SAXException, JAXBException {
//        FileInputStream fileInputStream = new FileInputStream(xmlFile);
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = builderFactory.newDocumentBuilder();
//        Document xmlDocument = builder.parse(fileInputStream);
        JAXBContext jaxbContext = JAXBContext.newInstance(Experiment.class);
        Unmarshaller jaxbMarshaller = jaxbContext.<Experiment>createUnmarshaller();
        final Experiment experiment = (Experiment) jaxbMarshaller.unmarshal(xmlFile);
        final StringBuilder stringBuilder = new StringBuilder();
        populateExperimentUml(experiment, stringBuilder);

        SourceStringReader reader = new SourceStringReader(stringBuilder.toString());
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        String desc = reader.generateImage(arrayOutputStream, new FileFormatOption(FileFormat.SVG));
        arrayOutputStream.close();
        System.out.println(desc);
        System.out.println(stringBuilder.toString());
        final String svg = new String(arrayOutputStream.toByteArray(), Charset.forName("UTF-8"));
        FileWriter schemaOutputWriter = new FileWriter(svgFileOut);
        final BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        bufferedWriter.write(svg);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void populateExperimentUml(final Experiment experiment, final StringBuilder stringBuilder) {
        stringBuilder.append("@startuml\n");
        for (PresenterScreen presenter : experiment.getPresenterScreen()) {
            if (presenter.getBackPresenterTag() != null) {
                stringBuilder.append(presenter.getSelfPresenterTag());
                stringBuilder.append(" <- ");
                stringBuilder.append(presenter.getBackPresenterTag());
                stringBuilder.append(" : back\n");
            }
            if (presenter.getNextPresenterTag() != null) {
                stringBuilder.append(presenter.getSelfPresenterTag());
                stringBuilder.append(" -> ");
                stringBuilder.append(presenter.getNextPresenterTag());
                stringBuilder.append(" : next\n");
            }
        }
        stringBuilder.append("@enduml\n");
    }
}
