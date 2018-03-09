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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.audio;

/**
 *
 * @author olhshk
 */
public enum WordType {
    WORD, EXAMPLE_TARGET_NON_WORD, TARGET_NON_WORD, FOIL, NON_WORD;

    public static WordType stringToWordType(String typeString) {
        WordType retVal;
        switch (typeString) {
            case "WORD":
                retVal = WordType.WORD;
                break;
            case "EXAMPLE_TARGET_NON_WORD":
                retVal = WordType.EXAMPLE_TARGET_NON_WORD;
                break;
            case "TARGET_NON_WORD":
                retVal = WordType.TARGET_NON_WORD;
                break;
            case "FOIL":
                retVal = WordType.FOIL;
                break;
            case "NON_WORD":
                retVal = WordType.NON_WORD;
                break;
            default:
                throw new IllegalArgumentException("No word type value for the string " + typeString);
        }
        return retVal;
    }
}
