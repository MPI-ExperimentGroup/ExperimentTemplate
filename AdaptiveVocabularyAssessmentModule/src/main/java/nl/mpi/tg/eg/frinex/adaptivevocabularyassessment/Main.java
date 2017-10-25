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

import java.util.ArrayList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.Bands;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassesment.bands.LexicalUnit;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack.FastTrack;

/**
 * @since Oct 20, 2017 11:38:57 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("starting work ... ");
        FastTrack fastTrack = new FastTrack(Constants.DEFAULT_USER, Constants.START_BAND,  Constants.AVRERAGE_NON_WORD_POSITION);
        try {
            fastTrack.initialiseWordLists();
            fastTrack.makeStimulaeSequence(Constants.NONWORDS_PER_BLOCK);
            ArrayList<LexicalUnit> fastTrackSequence = fastTrack.getStimulaeSequence();
            System.out.println("Fast track example sequence");
            int counter=0;
            int counterNonwords = 0;
            for(LexicalUnit unit: fastTrackSequence) {
               counter++;
               System.out.print(unit.getSpelling());
               System.out.print("  ");
               System.out.print(unit.getBandNumber());
               if (unit.getBandNumber() == -1){
                   counterNonwords++;
               }
               System.out.print(" ");
               double check = ((double) counterNonwords) / ((double) counter);
               System.out.println(check);
            }
            System.out.println("END of example sequence");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        /*Bands bands = new Bands();
        try {
           
            bands.parseWordInputCSV();
            bands.parseNonWordInputCSV();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        testPrint(bands);
         */
        System.out.println("Done. ");

    }

    private static void testPrint(Bands bands) {
        LexicalUnit[][] tmpwords = bands.getWords();
        System.out.println("Words \n");

        for (int i = 0; i < tmpwords.length; i++) {
            System.out.println(i + 1);
            for (LexicalUnit unit : tmpwords[i]) {
                System.out.println(unit.getSpelling());
                System.out.println(unit.getIsUsed());
            }
        }
        ArrayList<LexicalUnit> tmpnonwords = bands.getNonWords();
        System.out.println("Non words \n");
        for (LexicalUnit nonword : tmpnonwords) {
            System.out.println(nonword.getSpelling());
            System.out.println(nonword.getIsUsed());
        }
    }

}
