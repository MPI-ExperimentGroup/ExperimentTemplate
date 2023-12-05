/*
 * Copyright (C) 2023 Max Planck Institute for Psycholinguistics, Nijmegen
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
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 05 December 2023 10:44 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SchemaBlocksGeneratorTest {
    
    /**
     * Test of createJsFile method, of class SchemaBlocksGenerator.
     */
    @Test
    public void testCreateJsFile() throws Exception {
        System.out.println("createJsFile");
        final String inputDirectory = "/frinex-rest-output/";
        URI outputDirectoryUri = this.getClass().getResource(inputDirectory).toURI();
        final File blocksOutputFile = new File(new File(outputDirectoryUri), "frinexblocks-testoutput.js");

        SchemaBlocksGenerator instance = new SchemaBlocksGenerator();
        instance.createJsFile(blocksOutputFile);
        final String name = "/frinex-rest-output/" + "frinexblocks.js";
        System.out.println(name);
        URI testXmlUri = this.getClass().getResource(name).toURI();
        String expectedResult = new String(Files.readAllBytes(Paths.get(testXmlUri)), StandardCharsets.UTF_8);
        String actualResult = new String(Files.readAllBytes(Paths.get(blocksOutputFile.toURI())), StandardCharsets.UTF_8);
        assertEquals("frinexblocks.js", expectedResult, actualResult);
    }
}
