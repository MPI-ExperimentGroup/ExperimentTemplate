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
package nl.mpi.tg.eg.experimentdesigner.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import nl.mpi.tg.eg.experimentdesigner.util.SchemaDocumentationGenerator;
import nl.mpi.tg.eg.experimentdesigner.util.SchemaGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since Dec 11, 2018 11:50:50 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class SchemaDocumentationController {

    @RequestMapping(value = "/frinex.html", method = RequestMethod.GET)
    public String getHtml() throws IOException {
        StringWriter schemaOutputWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        new SchemaDocumentationGenerator().appendContents(bufferedWriter);
        return schemaOutputWriter.toString();
    }

    @RequestMapping(value = "/frinex.xsd", method = RequestMethod.GET)
    public String getXsd() throws IOException {
        StringWriter schemaOutputWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(schemaOutputWriter);
        new SchemaGenerator().appendContents(bufferedWriter);
        bufferedWriter.flush();
        return schemaOutputWriter.toString();
    }
}
