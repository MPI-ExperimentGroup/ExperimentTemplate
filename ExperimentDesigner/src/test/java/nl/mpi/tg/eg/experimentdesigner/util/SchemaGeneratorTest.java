/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.junit.Test;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;

/**
 * @since May 9, 2018 5:56:27 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaGeneratorTest {

    /**
     * Test of createSchemaFile method, of class SchemaGenerator.
     *
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    @Test
    public void testCreateSchemaFile() throws IOException, URISyntaxException {
        System.out.println("createSchemaFile");
        final String inputDirectory = "/frinex-rest-output/";
        URI outputDirectoryUri = this.getClass().getResource(inputDirectory).toURI();
        SchemaGenerator instance = new SchemaGenerator();
        final File schemaFile = new File(new File(outputDirectoryUri), "frinex.xsd");
        instance.createSchemaFile(schemaFile);

        // todo: add more configuration files to the test
        Source xmlFile = new StreamSource(new File(new File(outputDirectoryUri), "playhouse_study.xml"));
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        } catch (SAXException saxe) {
            System.out.println(saxe.getMessage());
            fail(saxe.getMessage());
        }
    }
}
