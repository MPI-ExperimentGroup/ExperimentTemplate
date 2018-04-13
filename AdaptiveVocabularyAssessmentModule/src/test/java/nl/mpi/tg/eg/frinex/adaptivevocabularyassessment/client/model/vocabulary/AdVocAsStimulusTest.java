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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.model.vocabulary;

import java.util.ArrayList;
import java.util.Arrays;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.ConstantsNonWords1;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.ConstantsWords1;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.advocaspool.Vocabulary;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olhshk
 */
public class AdVocAsStimulusTest {

    private final int wordsPerBandInSeries;
    private final int wordsPerBand = 40;
    private final int numberOfSeries = 1;
    private final int numberOfBands = 54;

    private final Vocabulary vocab;
    private final ArrayList<AdVocAsStimulus> nonwords;
    private final ArrayList<ArrayList<AdVocAsStimulus>> words;

    public AdVocAsStimulusTest() {
        this.wordsPerBandInSeries = this.wordsPerBand / this.numberOfSeries;
        this.vocab = new Vocabulary(this.numberOfBands, this.wordsPerBandInSeries);

        this.words = vocab.initialiseWords(ConstantsWords1.WORDS_SERIES[0]);

        ArrayList<AdVocAsStimulus> nonwordstmp = new ArrayList<>();
        nonwordstmp.addAll(Arrays.asList(ConstantsNonWords1.NONWORDS_SERIES[0]));
        this.nonwords = vocab.initialiseNonwords(nonwordstmp);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /*
     * Test of getBandNumber method, of class AdVocAsStimulus.
     */
    @Test
    public void testGetBandNumber() {
        System.out.println("getBandNumber");
        for (int i = 0; i < this.numberOfBands; i++) {
            for (AdVocAsStimulus stimulus : this.words.get(i)) {
                assertEquals(i, stimulus.getbandIndex());
                assertEquals(i + 1, stimulus.getBandNumber());
                assertEquals(i + 1, Integer.parseInt(stimulus.getbandLabel()));
            }
        }
        for (AdVocAsStimulus stimulus : this.nonwords) {
            assertEquals(-1, stimulus.getbandIndex());
            assertEquals(0, stimulus.getBandNumber());
            assertEquals(0, Integer.parseInt(stimulus.getbandLabel()));
        }

    }

}
