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
package nl.mpi.tg.eg.experiment.client.listener;

import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Aug 27, 2018 4:27:43 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HabituationParadigmListenerTest {

    public HabituationParadigmListenerTest() {
    }

    /**
     * Test of evaluateReset method, of class HabituationParadigmListener.
     */
    @Test
    public void testEvaluateReset() {
        System.out.println("evaluateReset");
        int maximumShowsData[] = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,};
        int habituationThresholdData[] = {10000, 10000, 10000, 10000, 10000, 10000, 1000, 1000, 1000, 10000, 10000, 10000, 10000, 10000, 100, 100, 100, 10000, 10000, 10000, 10000, 10000, 10000, 10, 10, 10, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,};
        int thresholdMsData[] = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 1000, 10000, 10000, 10000, 10000, 100, 10000, 10000, 10000, 10, 10000, 10000, 10000, 10000, 10, 10000, 10000, 10, 100, 1000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,};
        int isSingleShowData[] = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,};
        HabituationParadigmListener maximumShowsInstance = new HabituationParadigmListener("maximumShowsInstance", 1000, 10, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                System.out.print("<triggered>");
            }
        }, false);
        System.out.print("input: {");
        for (int value : maximumShowsData) {
            System.out.print(value);
            maximumShowsInstance.evaluateReset(value);
            System.out.print(", ");
        }
        System.out.println("}");
        System.out.println(maximumShowsInstance.generateJsonResults());
        HabituationParadigmListener habituationThresholdInstance = new HabituationParadigmListener("habituationThresholdInstance", 1000, 10, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                System.out.print("<triggered>");
            }
        }, false);
        System.out.print("input: {");
        for (int value : habituationThresholdData) {
            System.out.print(value);
            habituationThresholdInstance.evaluateReset(value);
            System.out.print(", ");
        }
        System.out.println("}");
        System.out.println(habituationThresholdInstance.generateJsonResults());
        HabituationParadigmListener thresholdMsInstance = new HabituationParadigmListener("thresholdMsInstance", 1000, 10, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                System.out.print("<triggered>");
            }
        }, false);
        System.out.print("input: {");
        for (int value : thresholdMsData) {
            System.out.print(value);
            thresholdMsInstance.evaluateReset(value);
            System.out.print(", ");
        }
        System.out.println("}");
        System.out.println(thresholdMsInstance.generateJsonResults());
        HabituationParadigmListener isSingleShowInstance = new HabituationParadigmListener("isSingleShowInstance", 1000, 10, new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                System.out.print("<triggered>");
            }
        }, true);
        System.out.print("input: {");
        for (int value : isSingleShowData) {
            System.out.print(value);
            isSingleShowInstance.evaluateReset(value);
            System.out.print(", ");
        }
        System.out.println("}");
        System.out.println(isSingleShowInstance.generateJsonResults());

        assertEquals("{listenerId:'maximumShowsInstance',thresholdMs:1000,maximumShows:10,baselineWindowSize:3,habituationThreshold:60,baselineAverage:10000,baselineSamples: {10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}",
                maximumShowsInstance.generateJsonResults());
        assertEquals("{listenerId:'habituationThresholdInstance',thresholdMs:1000,maximumShows:10,baselineWindowSize:3,habituationThreshold:60,baselineAverage:10000,baselineSamples: {10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}",
                habituationThresholdInstance.generateJsonResults());
        assertEquals("{listenerId:'thresholdMsInstance',thresholdMs:1000,maximumShows:10,baselineWindowSize:3,habituationThreshold:60,baselineAverage:10000,baselineSamples: {10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}",
                thresholdMsInstance.generateJsonResults());
        assertEquals("{listenerId:'isSingleShowInstance',thresholdMs:1000,maximumShows:10,baselineWindowSize:3,habituationThreshold:60,baselineAverage:null,baselineSamples: {}",
                isSingleShowInstance.generateJsonResults());
    }
}
