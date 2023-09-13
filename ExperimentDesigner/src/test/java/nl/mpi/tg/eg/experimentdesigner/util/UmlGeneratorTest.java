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
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 16 Oct 2020 12:21:15 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class UmlGeneratorTest {

    /**
     * Test of generateStateUml method, of class UmlGenerator.
     */
    @Test
    public void testGenerateStateUml() throws Exception {
        System.out.println("generateStateUml");
        UmlGenerator instance = new UmlGenerator();
        final String outputDirectory = "/frinex-rest-output/";
        URI outputDirectoryUri = this.getClass().getResource(outputDirectory).toURI();
        System.out.println(outputDirectory);
        final File umlActualFile = new File(new File(outputDirectoryUri), "with_stimulus_example-state-output.uml");
        final File umlExpectedFile = new File(new File(outputDirectoryUri), "with_stimulus_example-state.uml");
        final File svgActualFile = new File(new File(outputDirectoryUri), "with_stimulus_example-state-output.svg");
        final File svgExpectedFile = new File(new File(outputDirectoryUri), "with_stimulus_example-state.svg");

        instance.generateUml(new File(new File(outputDirectoryUri), "with_stimulus_example.xml"), umlActualFile, svgActualFile, UmlGenerator.DiagramType.state);
        String expectedUmlResult = new String(Files.readAllBytes(Paths.get(umlExpectedFile.toURI())), StandardCharsets.UTF_8);
        String actualUmlResult = new String(Files.readAllBytes(Paths.get(umlActualFile.toURI())), StandardCharsets.UTF_8);
        String expectedSvgResult = new String(Files.readAllBytes(Paths.get(svgExpectedFile.toURI())), StandardCharsets.UTF_8);
        String actualSvgResult = new String(Files.readAllBytes(Paths.get(svgActualFile.toURI())), StandardCharsets.UTF_8);
//        final String substring = actualSvgResult.substring(393, 400);
//        System.out.println(substring);
//        actualSvgResult = actualSvgResult.replaceAll(substring, "f8hjwa7");
        assertEquals("with_stimulus_example.uml", expectedUmlResult, actualUmlResult);
        if (actualSvgResult.contains("Dot Executable: ")) {
            System.out.println("graphviz not found, ignoring test");
        } else {
            //actualSvgResult = actualSvgResult.replaceAll("\\\"[ -.%#:;\\(\\)0-9a-zA-Z]*\\\"", "\"blank\"");
            //expectedSvgResult = expectedSvgResult.replaceAll("\\\"[ -.%#:;\\(\\)0-9a-zA-Z]*\\\"", "\"blank\"");
            //actualSvgResult = actualSvgResult.replaceAll("><", ">\n<");
            //expectedSvgResult = expectedSvgResult.replaceAll("><", ">\n<");
            //System.out.println("expectedSvgResult: " + expectedSvgResult);
            // it is not necessary to check the SVG output on regular builds since the UML file should catch most issues
            //assertEquals("with_stimulus_example.svg", expectedSvgResult, actualSvgResult);
        }
    }

    /**
     * Test of generateActivityUml method, of class UmlGenerator.
     */
    @Test
    public void testGenerateActivityUml() throws Exception {
        System.out.println("generateActivityUml");
        UmlGenerator instance = new UmlGenerator();
        final String outputDirectory = "/frinex-rest-output/";
        final String examplesDirectory = "/examples/";
        URI outputDirectoryUri = this.getClass().getResource(outputDirectory).toURI();
        System.out.println(outputDirectory);
        final File umlActualFile = new File(new File(outputDirectoryUri), "with_stimulus_example-activity-output.uml");
        final File umlExpectedFile = new File(new File(outputDirectoryUri), "with_stimulus_example-activity.uml");
        final File svgActualFile = new File(new File(outputDirectoryUri), "with_stimulus_example-activity-output.svg");
        final File svgExpectedFile = new File(new File(outputDirectoryUri), "with_stimulus_example-activity.svg");

        instance.generateUml(new File(new File(examplesDirectory), "with_stimulus_example.xml"), umlActualFile, svgActualFile, UmlGenerator.DiagramType.activity);
        String expectedUmlResult = new String(Files.readAllBytes(Paths.get(umlExpectedFile.toURI())), StandardCharsets.UTF_8);
        String actualUmlResult = new String(Files.readAllBytes(Paths.get(umlActualFile.toURI())), StandardCharsets.UTF_8);
        String expectedSvgResult = new String(Files.readAllBytes(Paths.get(svgExpectedFile.toURI())), StandardCharsets.UTF_8);
        String actualSvgResult = new String(Files.readAllBytes(Paths.get(svgActualFile.toURI())), StandardCharsets.UTF_8);
//        final String substring = actualSvgResult.substring(393, 400);
//        System.out.println(substring);
//        actualSvgResult = actualSvgResult.replaceAll(substring, "f8hjwa7");
        actualSvgResult = actualSvgResult.replaceAll("\\\"[ -.%#:;\\(\\)0-9a-zA-Z]*\\\"", "\"blank\"");
        expectedSvgResult = expectedSvgResult.replaceAll("\\\"[ -.%#:;\\(\\)0-9a-zA-Z]*\\\"", "\"blank\"");
        actualSvgResult = actualSvgResult.replaceAll("><", ">\n<");
        expectedSvgResult = expectedSvgResult.replaceAll("><", ">\n<");
        assertEquals("with_stimulus_example.uml", expectedUmlResult, actualUmlResult);
        // it is not necessary to check the SVG output on regular builds since the UML file should catch most issues
        //assertEquals("with_stimulus_example.svg", expectedSvgResult, actualSvgResult);
    }
}
