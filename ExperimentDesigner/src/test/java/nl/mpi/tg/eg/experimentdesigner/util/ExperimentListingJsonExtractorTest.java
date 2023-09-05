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

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 3 Mar 2020 13:10:04 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ExperimentListingJsonExtractorTest {

    public ExperimentListingJsonExtractorTest() {
    }

    /**
     * Test of extractListingJson method, of class
     * ExperimentListingJsonExtractor.
     */
    @Test
    public void testExtractListingJson() throws Exception {
        System.out.println("extractListingJson");

        URI outputDirectoryUri = this.getClass().getResource("/frinex-rest-output/").toURI();
        URI examplesDirectoryUri = this.getClass().getResource("/examples/").toURI();
        File listingDirectory = new File(new File(outputDirectoryUri), "listings");
        final File[] fileUnderTest = {new File(new File(outputDirectoryUri), "with_stimulus_example.xml"), new File(new File(examplesDirectoryUri), "minimal_example.xml")};
        final String[] internalName = {null, "minimal_example"};
        final String[] expResult = {"{\n"
            + "  \"publishDate\" : \"2020-02-02\",\n"
            + "  \"expiryDate\" : \"2025-02-22\",\n"
            + "  \"isWebApp\" : true,\n"
            + "  \"isDesktop\" : true,\n"
            + "  \"isiOS\" : true,\n"
            + "  \"isAndroid\" : true,\n"
            + "  \"isUnity\" : false,\n"
            + "  \"registrationUrlStaging\" : \"/with_stimulus_example-admin/validate\",\n"
            + "  \"registrationUrlProduction\" : \"/with_stimulus_example-admin/mock-nph-read_bq_exp_data.pl\",\n"
            + "  \"state\" : \"staging\",\n"
            + "  \"stagingServer\" : \"staging\",\n"
            + "  \"productionServer\" : \"production\",\n"
            + "  \"frinexVersion\" : \"latest\",\n"
            + "  \"defaultScale\" : 1.0,\n"
            + "  \"isScalable\" : \"yes\",\n"
            + "  \"isRotatable\" : true,\n"
            + "  \"allowDelete\" : true,\n"
            + "  \"experimentInternalName\" : \"with_stimulus_example\",\n"
            + "  \"experimentDisplayName\" : \"With Stimulus Example\",\n"
            + "  \"defaultLocale\" : \"nl\",\n"
            + "  \"availableLocales\" : \"en,es,de,fr,nl\"\n"
            + "}",
            "{\n"
            + "  \"publishDate\" : null,\n"
            + "  \"expiryDate\" : null,\n"
            + "  \"isWebApp\" : false,\n"
            + "  \"isDesktop\" : false,\n"
            + "  \"isiOS\" : false,\n"
            + "  \"isAndroid\" : false,\n"
            + "  \"isUnity\" : false,\n"
            + "  \"registrationUrlStaging\" : null,\n"
            + "  \"registrationUrlProduction\" : null,\n"
            + "  \"state\" : null,\n"
            + "  \"stagingServer\" : null,\n"
            + "  \"productionServer\" : null,\n"
            + "  \"frinexVersion\" : \"latest\",\n"
            + "  \"defaultScale\" : 1.0,\n"
            + "  \"isScalable\" : \"yes\",\n"
            + "  \"isRotatable\" : true,\n"
            + "  \"allowDelete\" : false,\n"
            + "  \"experimentInternalName\" : \"minimal_example\",\n"
            + "  \"experimentDisplayName\" : \"Minimal Example\",\n"
            + "  \"defaultLocale\" : \"en\",\n"
            + "  \"availableLocales\" : \"en\"\n"
            + "}"};
        for (int testFileIndex = 0; testFileIndex < 2; testFileIndex++) {
            final StringWriter stringWriter = new StringWriter();
            ExperimentListingJsonExtractor instance = new ExperimentListingJsonExtractor() {
                @Override
                protected Writer getWriter(File outputFile) throws IOException {
                    return stringWriter;
                }
            };
            instance.extractListingJson(fileUnderTest[testFileIndex], internalName[testFileIndex], listingDirectory, "latest");
            final String[] splitExpectedString = expResult[testFileIndex].split("\n");
            final String[] splitResultString = stringWriter.toString().split("\n");
            for (int index = 0; index < splitExpectedString.length || index < splitResultString.length; index++) {
                // we deliberately loop on the larger array so that we throw an error if the lengths are different
                assertTrue("Expected equal lengths but found: " + splitExpectedString.length + " : " + splitResultString.length, index < splitExpectedString.length);
                assertTrue("Expected equal lengths but found: " + splitExpectedString.length + " : " + splitResultString.length, index < splitResultString.length);
                assertEquals("listing_json_example.xml" + " at line " + index, splitExpectedString[index].trim(), splitResultString[index].trim());
//                System.out.println(splitExpectedString[index]);
//                System.out.println(splitResultString[index]);
            }
//        instance.extractListingJson(new File(new File(outputDirectoryUri), "invitation_validation_example"), listingDirectory);
//        assertEquals("listing_json_example.xml", stringWriter.toString());
            assertEquals(expResult[testFileIndex], stringWriter.toString());
        }
    }
}
