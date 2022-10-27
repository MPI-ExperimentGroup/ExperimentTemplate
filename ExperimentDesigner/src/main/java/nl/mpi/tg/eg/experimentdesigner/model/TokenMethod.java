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
    addTime("Adds an amount of hours and minutes to the time provided.", "addTime(&quot;&lt;metadataField_notificationWeekendUntilSettings&gt;&quot;,12:00) addTime(&quot;&lt;metadataField_notificationWeekendUntilSettings&gt;&quot;,-01:45)", "TODO: fill out the all of the tokens with examples and expected result "),
    daysBetween("Calculates the days between the dates provided.", "daysBetween(&quot;&lt;metadataField_dateOfBirth&gt;&quot;,&quot;&lt;currentDateDDMMYYYY&gt;&quot;)", ""),
    length("Determines the length of the provided text.", "length(&quot;&lt;metadataField_educationOther&gt;&quot;)", ""),
    random("Generates a random number less than the parameter given.", "random(5)", "0|1|2|3|4"),
    replaceAll("Replace all characters that match the regular expression with the second parameter given.", "replaceAll(&quot;[Tt][Ff][Jf][Qq][Yy][Pp][Kk]|TFJQYPK&quot;, &quot;[^|]*\\\\|&quot;, &quot;&quot;)", "");

    public final String usageDescription;
    public final String exampleUsage;
    public final String exampleResult;

    private TokenMethod(String usageDescription, String exampleUsage, String exampleResult) {
        this.usageDescription = usageDescription;
        this.exampleUsage = exampleUsage;
        this.exampleResult = exampleResult;
    }

