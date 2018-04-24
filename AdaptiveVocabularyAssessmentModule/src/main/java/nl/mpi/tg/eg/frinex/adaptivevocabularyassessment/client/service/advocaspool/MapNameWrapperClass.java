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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool;

import java.util.HashMap;

/**
 *
 * @author olhshk
 */
public class MapNameWrapperClass {

    public static final HashMap<String, CsvStringWrapper> STIMULI_FILES_INDEX;
    static {
        STIMULI_FILES_INDEX = new HashMap<String, CsvStringWrapper>();
        STIMULI_FILES_INDEX.put("NonWords_EN_2rounds_1", new NonWords_EN_2rounds_1());
        STIMULI_FILES_INDEX.put("NonWords_EN_2rounds_2", new NonWords_EN_2rounds_2());
        STIMULI_FILES_INDEX.put("Words_EN_2rounds_1", new Words_EN_2rounds_1());
        STIMULI_FILES_INDEX.put("Words_EN_2rounds_2", new NonWords_EN_2rounds_2());
        STIMULI_FILES_INDEX.put("NonWords_NL_2rounds_1", new NonWords_NL_2rounds_1());
        STIMULI_FILES_INDEX.put("NonWords_NL_2rounds_2", new NonWords_NL_2rounds_2());
        STIMULI_FILES_INDEX.put("Words_NL_2rounds_1", new Words_NL_2rounds_1());
        STIMULI_FILES_INDEX.put("Words_NL_2rounds_2", new Words_NL_2rounds_2());
        STIMULI_FILES_INDEX.put("NonWords_NL_1round", new NonWords_NL_1round());
        STIMULI_FILES_INDEX.put("Words_NL_1round", new Words_NL_1round());
    }

  

}
