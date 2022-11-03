/*
 * Copyright (C) 2022 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.model;

/**
 * @since 26 October 2022 16:36 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public enum TokenMethod {
    addTime("Adds an amount of hours and minutes to the time provided.", "Initial time: ::metadataField_notificationWeekendUntilSettings::\nAfter adding 12 hours: addTime('::metadataField_notificationWeekendUntilSettings::',12:00)\nAfter subtracting 1 hour and 45 minutes: addTime('::metadataField_notificationWeekendUntilSettings::',-01:45)", "Initial time: 15:20\nAfter adding 12 hours: 03:20\nAfter subtracting 1 hour and 45 minutes: 13:35"),
    daysBetween("Calculates the days between the dates provided.", "Difference in days: daysBetween('::metadataField_dateOfBirth::','::currentDateDDMMYYYY::')", "Difference in days: " + (6 + 29 + 12)),
    length("Determines the length of the provided text.", "There are length('Donau­dampf­schiffahrts­elektrizitäten­haupt­betriebs­werk­bau­unter­beamten­gesellschaft') letters in the word 'Donau­dampf­schiffahrts­elektrizitäten­haupt­betriebs­werk­bau­unter­beamten­gesellschaft'", "There are 89 letters in the word 'Donau­dampf­schiffahrts­elektrizitäten­haupt­betriebs­werk­bau­unter­beamten­gesellschaft'"),
    random("Generates a random number less than the parameter given.", "random(5)", "Will produce one of 0, 1, 2, 3, 4"),
    replaceAll("Replace all characters that match the regular expression with the second parameter given.", "metadataField_dateOfBirth: replaceAll('::metadataField_dateOfBirth::', '/', ':')\ncurrentDateDDMMYYYY: replaceAll('::currentDateDDMMYYYY::', '/', ':')", "metadataField_dateOfBirth: 25:01:2020\ncurrentDateDDMMYYYY: 12:03:2020");

    public final String usageDescription;
    public final String exampleUsage;
    public final String exampleResult;

    private TokenMethod(String usageDescription, String exampleUsage, String exampleResult) {
        this.usageDescription = usageDescription;
        this.exampleUsage = exampleUsage;
        this.exampleResult = exampleResult;
    }
}
