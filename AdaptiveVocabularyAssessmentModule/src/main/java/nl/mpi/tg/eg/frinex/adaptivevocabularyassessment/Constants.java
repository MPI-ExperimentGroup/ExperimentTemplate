/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment;

/**
 *
 * @author olhshk
 */
public class Constants {
    
    public static final int NUMBER_OF_BANDS = 54;
    
    public static final int WORDS_PER_BAND = 40;
    
    public static final int AVRERAGE_NON_WORD_POSITION = 3;
    
    public static final String DEFAULT_USER ="Anonimous";
    
    public static final int START_BAND = 20;
    
    public static final int NONWORDS_PER_BLOCK = 2; // the lesser it is, the nicer but more predictable positions of nonwords one will get
    
    public static final double EPSILON = 0.000001;
    
    public static final int FINE_TUNING_NUMBER_OF_ATOMS_PER_TUPLE = 3;
    
    public static final String WORD_FILE_LOCATION = "../../Data/2.selection_words_nonwords_w.csv";
    
    public static final String NONWORD_FILE_LOCATION = "../../Data/2.selection_words_nonwords.csv";
    
    public static final String OUTPUT_DIRECTORY = "../../Data/";
    
}
