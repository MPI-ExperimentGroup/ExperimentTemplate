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
     * Test of extractListingJson method, of class ExperimentListingJsonExtractor.
     */
    @Test
    public void testExtractListingJson() throws Exception {
        System.out.println("extractListingJson");
        final String inputDirectory = "/frinex-rest-output/";
        final StringWriter stringWriter = new StringWriter();

        URI outputDirectoryUri = this.getClass().getResource(inputDirectory).toURI();
        File listingDirectory = new File(new File(outputDirectoryUri), "listings");
        ExperimentListingJsonExtractor instance = new ExperimentListingJsonExtractor() {
            @Override
            protected Writer getWriter(File outputFile) throws IOException {
                return stringWriter;
            }
        };
        instance.extractListingJson(new File(new File(outputDirectoryUri), "with_stimulus_example.xml"), listingDirectory);
        assertEquals("{\n"
                + "\"publishDate\": 2020-02-20,\n"
                + "\"expiryDate\": 2020-02-20,\n"
                + "\"isWebApp\": true,\n"
                + "\"isDesktop\": true,\n"
                + "\"isiOS\": true,\n"
                + "\"isAndroid\": true,\n"
                + "\"buildName\": \"with_stimulus_example\",\n"
                + "\"registrationUrlStaging\": \"/with_stimulus_example-admin/mock-nph-read_bq_exp_data.pl\",\n"
                + "\"state\": \"production\",\n"
                + "\"defaultScale\": 1.0,\n"
                + "\"experimentInternalName\": \"with_stimulus_example\",\n"
                + "\"experimentDisplayName\": \"with_stimulus_example\"\n"
                + "}", stringWriter.toString());
        instance.extractListingJson(new File(new File(outputDirectoryUri), "listing_json_example.xml"), listingDirectory);
        assertEquals("listing_json_example.xml", stringWriter.toString());
    }
}
