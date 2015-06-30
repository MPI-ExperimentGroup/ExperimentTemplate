/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.frinex.rest;

import java.util.Random;
import nl.mpi.tg.eg.frinex.model.ExperimentData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since Jun 30, 2015 11:46:06 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@RestController
public class ExperimentService {

    @RequestMapping("/experiment/{name}")
    public String test(@PathVariable String name) {
        return "Experiment: " + name;
    }

    @RequestMapping(value = "/experimentData", method = RequestMethod.GET)
    public @ResponseBody
    ExperimentData getOne(@RequestParam(value = "name", required = false, defaultValue = "param12") String name) {
        return new ExperimentData(new Random().nextLong(), name, new Random().nextBoolean() + "");
    }
}
