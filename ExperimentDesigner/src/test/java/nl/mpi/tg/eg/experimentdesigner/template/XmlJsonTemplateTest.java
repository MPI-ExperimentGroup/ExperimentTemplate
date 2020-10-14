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

import java.io.File;
import java.net.URI;
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
import org.junit.Ignore;
import org.xml.sax.SAXException;

/**
 * @since 12 Oct 2020 09:12 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class XmlJsonTemplateTest {

    /**
     * Test of generateJsonFromXML method, of class XmlJsonTemplate.
     */
    @Test
    public void testGenerateJsonFromXML() throws Exception {
        System.out.println("generateJsonFromXML");
        final String targetDirectory = "/frinex-rest-output/";
        URI targetDirectoryUri = this.getClass().getResource(targetDirectory).toURI();
        File xmlFile = new File(new File(targetDirectoryUri), "template_example.xml");
        File jsonFileOut = new File(new File(targetDirectoryUri), "template_example-output.json");
        File jsonFileExpected = new File(new File(targetDirectoryUri), "template_example-expected.json");
        XmlJsonTemplate instance = new XmlJsonTemplate();
        instance.generateJsonFromXML(xmlFile, jsonFileOut);
        String expectedResult = new String(Files.readAllBytes(Paths.get(jsonFileExpected.toURI())), StandardCharsets.UTF_8);
        String actualResult = new String(Files.readAllBytes(Paths.get(jsonFileOut.toURI())), StandardCharsets.UTF_8);
        assertEquals("template_example-template.json", expectedResult, actualResult);
    }

    /**
     * Test of validateTemplateXML method, of class XmlJsonTemplate.
     */
    @Ignore
    @Test
    public void testValidateTemplateXML() throws Exception {
        System.out.println("validateTemplateXML");
        final String testDataDirectory = "/frinex-rest-output/";
        URI testDataDirectoryUri = this.getClass().getResource(testDataDirectory).toURI();
        Source xmlFile = new StreamSource(new File(new File(testDataDirectoryUri), "template_example.xml"));
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
        try {
//            final File frinexSchemaFile = new File(new File(outputDirectoryUri), "frinex.xsd");
//            final File templateSchemaFile = new File(new File(outputDirectoryUri), "template.xsd");
//            Schema schema = schemaFactory.newSchema(new StreamSource[]{new StreamSource(frinexSchemaFile), new StreamSource(templateSchemaFile)});
            Schema schema = schemaFactory.newSchema(new File(new File(testDataDirectoryUri), "template.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
//            Schema frinexSchema = schemaFactory.newSchema(frinexSchemaFile);
//            Schema templateSchema = schemaFactory.newSchema(templateSchemaFile);
//            Validator frinexValidator = frinexSchema.newValidator();
//            frinexValidator.validate(xmlFile);
//            Validator templateValidator = templateSchema.newValidator();
//            templateValidator.validate(xmlFile);
        } catch (SAXException saxe) {
            System.out.println(saxe.getMessage());
            fail(saxe.getMessage());
        }
    }
}
