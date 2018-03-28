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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olhshk
 */
public class BandStimuliProviderTest {

    public final BandStimulus stimulus = new BandStimulus("smoer_xx", new Stimulus.Tag[0], "smoer", "", 900, "aud", "vid", "img",
            "a,b,c", "b,c", "plus10db", 10);

    private class BandStimuliProviderImp extends BandStimuliProvider<BandStimulus> {

        public BandStimuliProviderImp(final Stimulus[] stimulusArray) {
            super(stimulusArray);

        }

        ;
        @Override
        public BookkeepingStimulus<BandStimulus> deriveNextFastTrackStimulus() {
            return null;
        }

        @Override
        protected boolean analyseCorrectness(Stimulus stimulus, String stimulusResponse) {
            return true;
        }

        @Override
        public HashMap<String, BandStimulus> makeStimuliHashMap() {
            return null;
        }

        @Override
        public boolean fastTrackToBeContinuedWithSecondChance() {
            return true;
        }

        @Override
        public boolean enoughStimuliForFastTrack() {
            return true;
        }

        @Override
        public boolean initialiseNextFineTuningTuple() {
            return true;
        }

        @Override
        public void recycleUnusedStimuli() {
        }

        @Override
        public String getStringFastTrack(String startRow, String endRow, String startColumn, String endColumn) {
            return "";
        }
        
        @Override
        public String getStringFineTuningHistory(String startRow, String endRow, String startColumn, String endColumn, String format){
            return "";
        }

    };

    private final BandStimuliProvider<BandStimulus> instance = new BandStimuliProviderImp(new Stimulus[0]);

    public BandStimuliProviderTest() {
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

    /**
     * Test of settype method, of class BandStimuliProvider.
     */
    @Test
    public void tesSetGettype() {
        System.out.println("settype");
        String type = "1";
        assertTrue(0 == this.instance.gettype());
        this.instance.settype(type);
        assertTrue(1 == this.instance.gettype());
    }

    /**
     * Test of setfastTrackPresent method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetfastTrackPresent() {
        System.out.println("setfastTrackPresent");
        assertTrue(this.instance.getfastTrackPresent());
        String fastTrackPresent = "false";
        this.instance.setfastTrackPresent(fastTrackPresent);
        assertFalse(this.instance.getfastTrackPresent());
    }

    /**
     * Test of setfineTuningFirstWrongOut method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetfineTuningFirstWrongOut() {
        System.out.println("setfineTuningFirstWrongOut");
        String fineTuningFirstWrongOut = "false";
        assertTrue(this.instance.getfineTuningFirstWrongOut());
        instance.setfineTuningFirstWrongOut(fineTuningFirstWrongOut);
        assertFalse(this.instance.getfineTuningFirstWrongOut());
    }

    /**
     * Test of setnumberOfBands method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetnumberOfBands() {
        System.out.println("setnumberOfBands");
        String numberOfBands = "40";
        assertEquals(0, this.instance.getnumberOfBands());
        instance.setnumberOfBands(numberOfBands);
        assertEquals(40, this.instance.getnumberOfBands());
    }

    /**
     * Test of setnumberOfSeries method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetnumberOfSeries() {
        System.out.println("setnumberOfSeries");
        String numberOfSeries = "2";
        assertEquals(0, this.instance.getnumberOfSeries());
        this.instance.setnumberOfSeries(numberOfSeries);
        assertEquals(2, this.instance.getnumberOfSeries());
    }

    /**
     * Test of setstartBand method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetstartBand() {
        System.out.println("setstartBand");
        String startBand = "20";
        assertEquals(0, this.instance.getstartBand());
        this.instance.setstartBand(startBand);
        assertEquals(20, this.instance.getstartBand());
    }

    /**
     * Test of setfineTuningTupleLength method, of class BandStimuliProvider.
     */
    @Test
    public void testGetSetfineTuningTupleLength() {
        System.out.println("setfineTuningTupleLength");
        String fineTuningTupleLength = "4";
        assertEquals(0, this.instance.getfineTuningTupleLength());
        instance.setfineTuningTupleLength(fineTuningTupleLength);
        assertEquals(4, this.instance.getfineTuningTupleLength());
    }

    /**
     * Test of setfineTuningUpperBoundForCycles method, of class
     * BandStimuliProvider.
     */
    @Test
    public void testGetSetfineTuningUpperBoundForCycles() {
        System.out.println("setfineTuningUpperBoundForCycles");
        String fineTuningUpperBoundForCycles = "2";
        assertEquals(0, this.instance.getfineTuningUpperBoundForCycles());
        instance.setfineTuningUpperBoundForCycles(fineTuningUpperBoundForCycles);
        assertEquals(2, this.instance.getfineTuningUpperBoundForCycles());
    }

    /**
     * Test of initialiseStimuliState method, of class BandStimuliProvider.
     */
    @Test
    public void testInitialiseStimuliStateEmpty() {
        System.out.println("initialiseStimuliState");
        String stimuliStateSnapshot = "";

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
// this.bandScore = -1;
//            this.percentageScore = 0;
//            this.isCorrectCurrentResponse = null;
//            this.currentBandIndex = this.startBand - 1;
//            this.bandVisitCounter = new int[this.numberOfBands];
//
//            //this.totalStimuli: see the child class
//            this.enoughFineTuningStimulae = true;
//            for (int i = 0; i < this.numberOfBands; i++) {
//                this.bandVisitCounter[i] = 0;
//            }
//
//            this.cycle2helper = new int[this.fineTuningUpperBoundForCycles * 2 + 1];
//            for (int i = 0; i < this.fineTuningUpperBoundForCycles * 2 + 1; i++) {
//                this.cycle2helper[i] = 0;
//            }
//            this.cycle2 = false;
//            this.champion = false;
//            this.looser = false;
//            this.justVisitedLastBand = false;
//            this.percentageBandTable = this.generatePercentageBandTable();
        assertEquals(-1, this.instance.getBandScore());
        assertEquals(19, this.instance.getCurrentBandIndex());
        assertTrue(this.instance.getEnoughFinetuningStimuli());
        assertTrue(this.instance.getnumberOfBands() == this.instance.getbandVisitCounter().length);
        for (int i = 0; i < this.instance.getnumberOfBands(); i++) {
            assertEquals(0, this.instance.getbandVisitCounter()[i]);
        }
        assertTrue(this.instance.getfineTuningUpperBoundForCycles() * 2 + 1 == this.instance.getcycle2helper().length);
        for (int i = 0; i < this.instance.getfineTuningUpperBoundForCycles() * 2 + 1; i++) {
            assertEquals(0, this.instance.getcycle2helper()[i]);
        }
        assertFalse(this.instance.getCycel2());
        assertFalse(this.instance.getChampion());
        assertFalse(this.instance.getLooser());
    }

    /**
     * Test of generateStimuliStateSnapshot method, of class
     * BandStimuliProvider.
     */
    @Test
    public void testGenerateStimuliStateSnapshot() {
        System.out.println("generateStimuliStateSnapshot");
        String stimuliStateSnapshot = "";

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        assertEquals(this.instance.toString(), this.instance.generateStimuliStateSnapshot());
    }

    /**
     * Test of getPercentageBandTable method, of class BandStimuliProvider.
     */
    @Test
    public void testGetPercentageBandTable() {
        System.out.println("getPercentageBandTable");
        String stimuliStateSnapshot = "";

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState(stimuliStateSnapshot);

        LinkedHashMap<Long, Integer> table = this.instance.getPercentageBandTable();
        assertEquals(11, table.size());

        Long[] keys = table.keySet().toArray(new Long[0]);
        assertTrue(1 == keys[0]);
        assertTrue(10 == keys[1]);
        assertTrue(20 == keys[2]);
        assertTrue(30 == keys[3]);
        assertTrue(40 == keys[4]);
        assertTrue(50 == keys[5]);
        assertTrue(60 == keys[6]);
        assertTrue(70 == keys[7]);
        assertTrue(80 == keys[8]);
        assertTrue(90 == keys[9]);
        assertTrue(99 == keys[10]);

        assertTrue(0 == table.get(keys[0]));
        assertTrue(4 == table.get(keys[1]));
        assertTrue(8 == table.get(keys[2]));
        assertTrue(12 == table.get(keys[3]));
        assertTrue(16 == table.get(keys[4]));
        assertTrue(20 == table.get(keys[5]));
        assertTrue(24 == table.get(keys[6]));
        assertTrue(28 == table.get(keys[7]));
        assertTrue(32 == table.get(keys[8]));
        assertTrue(36 == table.get(keys[9]));
        assertTrue(40 == table.get(keys[10]));
    }

    /**
     * Test of getResponseRecord method, of class BandStimuliProvider.
     */
    @Test
    public void testGetResponseRecord() {
        System.out.println("getResponseRecord");
        String stimuliStateSnapshot = "";

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState(stimuliStateSnapshot);
        ArrayList<BookkeepingStimulus<BandStimulus>> result = instance.getResponseRecord();
        assertEquals(0, result.size());
    }

    /**
     * Test of getBestFastTrackBand method, of class BandStimuliProvider.
     */
    @Test
    public void testGetBestFastTrackBand() {
        System.out.println("getBestFastTrackBand");

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        int result = instance.getBestFastTrackBand();
        assertEquals(-1, result);
    }

    /**
     * Test of getBandScore method, of class BandStimuliProvider.
     */
    @Test
    public void testGetBandScore() {
        System.out.println("getBandScore");

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        int result = instance.getBandScore();
        assertEquals(-1, result);
    }

    /**
     * Test of getPercentageScore method, of class BandStimuliProvider.
     */
    @Test
    public void testbandNumberIntoPercentage() {
        System.out.println("getPercentageScore");

        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");

        assertEquals(50, instance.bandNumberIntoPercentage(20));
    }

    /**
     * Test of getFTtuple method, of class BandStimuliProvider.
     */
    @Test
    public void testGetFTtuple() {
        System.out.println("getFTtuple");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");

        ArrayList<BookkeepingStimulus<BandStimulus>> result = instance.getFTtuple();
        assertEquals(0, result.size());
    }

    /**
     * Test of getEndFastTrackTimeTick method, of class BandStimuliProvider.
     */
    @Test
    public void testGetEndFastTrackTimeTick() {
        System.out.println("getEndFastTrackTimeTick");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        int result = instance.getEndFastTrackTimeTick();
        assertEquals(-1, result);
    }

    /**
     * Test of getCurrentStimulus method, of class BandStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulus() {
        System.out.println("getCurrentStimulus");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        try {
            Stimulus result = instance.getCurrentStimulus();
        } catch (Exception e) {
            System.out.println("Yes, there must be the following exception here (the recorrds are not initialised) : " + e);
        }
    }

    /**
     * Test of getCurrentStimulusIndex method, of class BandStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusIndex() {
        System.out.println("getCurrentStimulusIndex");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        int result = instance.getCurrentStimulusIndex();
        assertEquals(-1, result);
    }

    /**
     * Test of getTotalStimuli method, of class BandStimuliProvider.
     */
    @Test
    public void testGetTotalStimuli() {
        System.out.println("getTotalStimuli");
        int result = this.instance.getTotalStimuli();
        assertEquals(0, result);
    }

    /**
     * Test of nextStimulus method, of class BandStimuliProvider.
     */
    @Test
    public void testNextStimulus() {
        System.out.println("nextStimulus");
        int increment = 0;
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");

        this.instance.nextStimulus(increment);
    }

    /**
     * Test of deriveNextFastTrackStimulus method, of class BandStimuliProvider.
     */
    @Test
    public void testDeriveNextFastTrackStimulus() {
        System.out.println("deriveNextFastTrackStimulus");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        BookkeepingStimulus result = instance.deriveNextFastTrackStimulus();
        assertEquals(null, result);
    }

    /**
     * Test of setCurrentStimuliIndex method, of class BandStimuliProvider.
     */
    @Test
    public void testSetCurrentStimuliIndex() {
        System.out.println("setCurrentStimuliIndex");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        this.instance.setCurrentStimuliIndex(0); // the method actually does nothing and not relevant for band stimuli
        assertEquals(-1, this.instance.getCurrentStimulusIndex());

    }

    /**
     * Test of getCurrentStimulusUniqueId method, of class BandStimuliProvider.
     */
    @Test
    public void testGetCurrentStimulusUniqueId() {
        System.out.println("getCurrentStimulusUniqueId");
        System.out.println("setCurrentStimuliIndex");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        try {
            String result = instance.getCurrentStimulusUniqueId();
        } catch (Exception e) {
            System.out.println("Yes, there must be an exception here, the stimuli list is not initialised yet: " + e);
        }

    }

    /**
     * Test of getHtmlStimuliReport method, of class BandStimuliProvider.
     */
    @Test
    public void testGetHtmlStimuliReport() {
        System.out.println("getHtmlStimuliReport");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        String result = this.instance.getHtmlStimuliReport();
        assertNotNull(result);
    }

    /**
     * Test of getStimuliReport method, of class BandStimuliProvider.
     */
    @Test
    public void testGetStimuliReport() {
        System.out.println("getStimuliReport");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        Map<String, String> result = instance.getStimuliReport("");
        assertEquals(0, result.size());
    }

    /**
     * Test of isCorrectResponse method, of class BandStimuliProvider.
     */
    @Test
    public void testIsCorrectResponse() {
        System.out.println("isCorrectResponse");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        try {
            boolean result = instance.isCorrectResponse(stimulus, "");
        } catch (Exception e) {
            System.out.println("Yes, there must be an exception here, the record stimuli list is not initialised yet: " + e);
        }
    }

    /**
     * Test of tupleIsNotEmpty method, of class BandStimuliProvider.
     */
    @Test
    public void testTupleIsNotEmpty() {
        System.out.println("tupleIsNotEmpty");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        assertFalse(this.instance.tupleIsNotEmpty());
    }

    /**
     * Test of allTupleIsCorrect method, of class BandStimuliProvider.
     */
    @Test
    public void testAllTupleIsCorrect() {
        System.out.println("allTupleIsCorrect");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        // false positibe: the tuple is empty
        assertTrue(this.instance.allTupleIsCorrect());
    }

    /**
     * Test of bandNumberIntoPercentage method, of class BandStimuliProvider.
     */
    @Test
    public void testBandNumberIntoPercentage() {
        System.out.println("bandNumberIntoPercentage");
        assertEquals(0L, this.instance.bandNumberIntoPercentage(0));

        this.instance.setnumberOfBands("40");
        assertEquals(100L, this.instance.bandNumberIntoPercentage(40));
        assertEquals(50L, this.instance.bandNumberIntoPercentage(20));
        assertEquals(75L, this.instance.bandNumberIntoPercentage(30));
        assertEquals(25L, this.instance.bandNumberIntoPercentage(10));
        assertEquals(13L, this.instance.bandNumberIntoPercentage(5));
    }

    /**
     * Test of percentageIntoBandNumber method, of class BandStimuliProvider.
     */
    @Test
    public void testPercentageIntoBandNumber() {
        System.out.println("percentageIntoBandNumber");
        assertEquals(0, this.instance.percentageIntoBandNumber(0));
        this.instance.setnumberOfBands("54");
        assertEquals(54, instance.percentageIntoBandNumber(100));
        assertEquals(53, instance.percentageIntoBandNumber(99));
        assertEquals(27, instance.percentageIntoBandNumber(50));
        assertEquals(26, instance.percentageIntoBandNumber(49));
        assertEquals(5, instance.percentageIntoBandNumber(10));
        assertEquals(1, instance.percentageIntoBandNumber(1));
        assertEquals(1, instance.percentageIntoBandNumber(2));

    }

    /**
     * Test of toBeContinuedLoopChecker method, of class BandStimuliProvider.
     */
    @Test
    public void testToBeContinuedLoopChecker() {
        System.out.println("toBeContinuedLoopChecker");
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");

        assertTrue(instance.toBeContinuedLoopChecker());

    }

    /**
     * Test of detectLoop method, of class BandStimuliProvider.
     */
    /**
     * Test of detectLoop method, of class this.instance.
     */
    @Test
    public void testDetectLoop() {
        System.out.println("detectLoop");
        int[] arr1 = {42, 43, 42, 43, 42, 43, 42};
        boolean result1 = BandStimuliProvider.detectLoop(arr1);
        assertEquals(true, result1);
        int[] arr2 = {42, 43, 42, 43, 42, 43, 45};
        boolean result2 = BandStimuliProvider.detectLoop(arr2);
        assertEquals(false, result2);
        int[] arr3 = {43, 42, 43, 42, 43, 42, 45, 42};
        boolean result3 = BandStimuliProvider.detectLoop(arr3);
        assertEquals(false, result3);
    }

    /**
     * Test of shiftFIFO method, of class this.instance.
     */
    @Test
    public void testShiftFIFO() {
        System.out.println("shiftFIFO");
        int[] fifo = {0, 1, 2, 3, 4, 5, 6};
        int newelement = 7;
        BandStimuliProvider.shiftFIFO(fifo, newelement);
        for (int i = 0; i < 7; i++) {
            assertEquals(i + 1, fifo[i]);
        }
    }

    /**
     * Test of shiftFIFO method, of class this.instance.
     */
    @Test
    public void testMostOftenVisitedBandNumber() {
        System.out.println(" MostOftenVisitedBandMumber");
        int[] visitCounter = {1, 3, 2, 3, 3, 3, 1};
        // indices {1,3,4,5}
        // ind = 1, indSym = 2
        int currentIndex1 = 2; // at 2
        int bandNumber1 = this.instance.mostOftenVisitedBandNumber(visitCounter, currentIndex1);
        assertEquals(4, bandNumber1);

        int currentIndex2 = 3; // at 3
        int bandNumber2 = this.instance.mostOftenVisitedBandNumber(visitCounter, currentIndex2);
        assertEquals(4, bandNumber2);
    }

    private int getListOfListLength(ArrayList<ArrayList<BandStimulus>> ll) {
        int retVal = 0;
        for (ArrayList<BandStimulus> l : ll) {
            retVal += l.size();
        }
        return retVal;
    }


    /**
     * Test of getStringSummary method, of class BandStimuliProvider.
     */
    @Test
    public void testGetStringSummary() {
        System.out.println("getStringSummary");
        
        this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        
        String startRow = "";
        String endRow = "\n";
        String startColumn = "";
        String endColumn = ";";
        String result = this.instance.getStringSummary(startRow, endRow, startColumn, endColumn);
        String expResult = "Score;BestFastTrack;Cycel2oscillation;EnoughFineTuningStimuli;Champion;Looser;\n-1;-1;false;true;false;false;\n";
        assertEquals(expResult,result);
        
        this.instance.setfastTrackPresent("false");
        String result2 = this.instance.getStringSummary(startRow, endRow, startColumn, endColumn);
        String expResult2 = "Score;Cycel2oscillation;EnoughFineTuningStimuli;Champion;Looser;\n-1;false;true;false;false;\n";
        assertEquals(expResult2,result2);
    }

    /**
     * Test of toString method, of class BandStimuliProvider.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
         this.instance.setnumberOfBands("40");
        this.instance.settype("0");
        this.instance.setfastTrackPresent("true");
        this.instance.setfineTuningFirstWrongOut("false");
        this.instance.setfineTuningTupleLength("4");
        this.instance.setfineTuningUpperBoundForCycles("2");
        this.instance.setnumberOfSeries("2");
        this.instance.setstartBand("20");
        this.instance.initialiseStimuliState("");
        String expectedResult = "type:{0},"
                + "numberOfBands:{40},"
                + "numberOfSeries:{2},"
                + "startBand:{20},"
                + "fineTuningTupleLength:{4},"
                + "fineTuningUpperBoundForCycles:{2},"
                +"fastTrackPresent:{true},"
                + "fineTuningFirstWrongOut:{false},"
                + "bandScore:{-1},"
                + "percentageScore:{0},"
                + "isCorrectCurrentResponse:{null},"
                + "currentBandIndex:{19},"
                + "totalStimuli:{0},"
                + "responseRecord:{},"
                + "bestBandFastTrack:{-1},"
                + "isFastTrackIsStillOn:{true},"
                + "secondChanceFastTrackIsFired:{false},"
                + "timeTickEndFastTrack:{-1},"
                + "tupleFT:{},"
                + "enoughFineTuningStimulae:{true},"
                + "bandVisitCounter:{0:{0},1:{0},2:{0},3:{0},4:{0},5:{0},6:{0},7:{0},8:{0},9:{0},10:{0},11:{0},12:{0},13:{0},14:{0},15:{0},16:{0},17:{0},18:{0},19:{0},20:{0},21:{0},22:{0},23:{0},24:{0},25:{0},26:{0},27:{0},28:{0},29:{0},30:{0},31:{0},32:{0},33:{0},34:{0},35:{0},36:{0},37:{0},38:{0},39:{0}},"
                + "cycle2helper:{0:{0},1:{0},2:{0},3:{0},4:{0}},"
                + "cycle2:{false},"
                + "champion:{false},"
                + "looser:{false},"
                + "justVisitedLastBand:{false},"
                + "justVisitedFirstBand:{false},"
                + "errorMessage:{null},";
        assertEquals(expectedResult, this.instance.toString());
    }
    
     /**
     * Test of toString method, of class BandStimuliProvider.
     */
    @Test
    public void deserialisation() {
        System.out.println("toString");
        String input = "type:{1},"
                + "numberOfBands:{54},"
                + "numberOfSeries:{1},"
                + "startBand:{25},"
                + "fineTuningTupleLength:{4},"
                + "fineTuningUpperBoundForCycles:{3},"
                + "fastTrackPresent:{false},"
                + "fineTuningFirstWrongOut:{false},"
                + "bandScore:{27},"
                + "percentageScore:{50},"
                + "isCorrectCurrentResponse:{true},"
                + "currentBandIndex:{28},"
                + "totalStimuli:{10},"
                + "responseRecord:{null},"
                + "bestBandFastTrack:{20},"
                + "isFastTrackIsStillOn:{false},"
                + "secondChanceFastTrackIsFired:{true},"
                + "timeTickEndFastTrack:{10},"
                + "tupleFT:{null},"
                + "enoughFineTuningStimulae:{false},"
                + "bandVisitCounter:{0:{1},1:{2},2:{3},3:{4},4:{5},5:{6},6:{7},7:{8},8:{9},9:{10},10:{11},11:{0}},"
                + "cycle2helper:{0:{10},1:{9},2:{8},3:{7},4:{6}},"
                + "cycle2:{true},"
                + "champion:{true},"
                + "looser:{true},"
                + "justVisitedLastBand:{true},"
                + "justVisitedFirstBand:{true},"
                + "errorMessage:{noError}";
        this.instance.initialiseStimuliState(input);
        
        assertEquals(new Integer(1),this.instance.gettype());
        assertEquals(54,this.instance.getnumberOfBands());
        assertEquals(1,this.instance.getnumberOfSeries());
        assertEquals(25,this.instance.getstartBand());
        
        assertEquals(4,this.instance.getfineTuningTupleLength());
        assertEquals(3,this.instance.getfineTuningUpperBoundForCycles());
        assertFalse(this.instance.getfastTrackPresent());
        assertFalse(this.instance.getfineTuningFirstWrongOut());
        
        assertEquals(27,this.instance.getBandScore());
        assertEquals(50,this.instance.getPercentageScore());
        assertTrue(this.instance.isCorrectCurrentResponse);
        assertEquals(28,this.instance.getCurrentBandIndex());
        assertEquals(10,this.instance.getTotalStimuli());
        assertNull(this.instance.getResponseRecord());
        assertEquals(20,this.instance.getBestFastTrackBand());
        assertFalse(this.instance.getIsFastTrackIsStillOn());
        assertTrue(this.instance.getSecondChanceFastTrackIsFired());
        assertEquals(10,this.instance.getEndFastTrackTimeTick());
        assertNull(this.instance.getFTtuple());
        assertFalse(this.instance.getEnoughFinetuningStimuli());
        
        int[] counter=this.instance.getbandVisitCounter();
        assertEquals(12, counter.length);
        for (int i=0; i<11; i++) {
            assertEquals(i+1, counter[i]);
        }
        assertEquals(0, counter[11]);
        
        int[] helper=this.instance.getcycle2helper();
        assertEquals(5, helper.length);
        for (int i=0; i<helper.length; i++) {
            assertEquals(10-i, helper[i]);
        }
        
        assertTrue(this.instance.getCycel2());
        assertTrue(this.instance.getLooser());
        assertTrue(this.instance.getChampion());
        assertTrue(this.instance.getJustVisitedFirstBand());
        assertTrue(this.instance.getJustVisitedLastBand());
        assertEquals("noError", this.instance.getErrorMessage());
    }
    
   

}
