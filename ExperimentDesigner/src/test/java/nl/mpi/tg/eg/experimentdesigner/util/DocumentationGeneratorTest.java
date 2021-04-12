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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 * @since 9 April 2021 14:22 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class DocumentationGeneratorTest {

    /**
     * Test of main method, of class DocumentationGenerator.
     *
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws org.xml.sax.SAXException
     */
    @Test
    public void testMain() throws IOException, URISyntaxException, SAXException {
        System.out.println("main");

        URI outputDirectoryUri = this.getClass().getResource("/update_schema_docs").toURI();
        System.out.println(outputDirectoryUri);
        String[] args = new String[]{outputDirectoryUri.getPath(), outputDirectoryUri.getPath()};
        DocumentationGenerator.main(args);

        System.out.println("compareDocumentationGeneratorSchemaFile");
        final File xsdOutputFile = new File(new File(outputDirectoryUri), "frinex.xsd");
        URI testXsdUri = this.getClass().getResource("/frinex-rest-output/frinex.xsd").toURI();
        String expectedXsdResult = new String(Files.readAllBytes(Paths.get(testXsdUri)), StandardCharsets.UTF_8);
        String actualXsdResult = new String(Files.readAllBytes(Paths.get(xsdOutputFile.toURI())), StandardCharsets.UTF_8);
        assertEquals("frinex.xsd", expectedXsdResult, actualXsdResult);

        System.out.println("createDocumentationGeneratorHtmlFile");
        final File htmlOutputFile = new File(new File(outputDirectoryUri), "frinex.html");
        URI testHtmlUri = this.getClass().getResource("/frinex-rest-output/frinex.html").toURI();
        String expectedHtmlResult = new String(Files.readAllBytes(Paths.get(testHtmlUri)), StandardCharsets.UTF_8);
        String actualHtmlResult = new String(Files.readAllBytes(Paths.get(htmlOutputFile.toURI())), StandardCharsets.UTF_8);
        assertEquals("frinex.html", expectedHtmlResult, actualHtmlResult);

        System.out.println("createDocumentationGeneratorXmlFile");
        final File schemaFile = new File(new File(outputDirectoryUri), "frinex.xsd");
        Source xmlFile = new StreamSource(new File(new File(outputDirectoryUri), "minimal_example.xml"));
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }
}
