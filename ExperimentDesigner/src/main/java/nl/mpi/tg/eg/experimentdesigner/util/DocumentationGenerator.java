/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 * @since 8 April 2021 15:57 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DocumentationGenerator {

    public InputStream getMinimalExampleStream() throws IOException, SAXException, URISyntaxException {
        return this.getClass().getResourceAsStream("/examples/minimal_example.xml");
    }

    public static void main(String[] args) {
        System.out.println("DocumentationGenerator");
        if (args.length < 2) {
            System.out.println("both documentationDirectory and schemaDirectory must be specified");
        } else {
            final String documentationDirectory = args[0];
            final String schemaDirectory = args[1];
            System.out.println("documentationDirectory: " + documentationDirectory);
            System.out.println("schemaDirectory: " + schemaDirectory);
            if (!new File(documentationDirectory).exists()) {
                System.out.println("documentationDirectory does not exist");
            } else if (!new File(schemaDirectory).exists()) {
                System.out.println("schemaDirectory does not exist");
            } else {
                try {
                    final File schemaOutputFile = new File(schemaDirectory, "frinex.xsd");
                    new SchemaGenerator().createSchemaFile(schemaOutputFile);
                } catch (IOException exception) {
                    System.out.println("Failed to create schema file: " + exception.getMessage());
                }
                try {
                    final File htmlOutputFile = new File(documentationDirectory, "frinex.html");
                    new SchemaDocumentationGenerator().createHtmlFile(htmlOutputFile);
                } catch (IOException exception) {
                    System.out.println("Failed to create documentation file: " + exception.getMessage());
                }
                try {
                    final File minimalExampleOutputFile = new File(documentationDirectory, "minimal_example.xml");
                    Files.copy(new DocumentationGenerator().getMinimalExampleStream(), minimalExampleOutputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    Source xmlFileStream = new StreamSource(minimalExampleOutputFile);
                    SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
                    final File schemaFile = new File(schemaDirectory, "frinex.xsd");
                    Schema schema = schemaFactory.newSchema(schemaFile);
                    Validator validator = schema.newValidator();
                    validator.validate(xmlFileStream);
                    final XpathExperimentValidator experimentValidator = new XpathExperimentValidator();
                    experimentValidator.validateDocument(minimalExampleOutputFile);
                } catch (IOException | SAXException | URISyntaxException | IllegalArgumentException | ParserConfigurationException | XPathExpressionException | XpathExperimentException exception) {
                    System.out.println("Failed to provide a validated copy of the minimal_example.xml: " + exception.getMessage());
                }
            }
        }
    }
}
