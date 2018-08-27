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

import com.google.gwt.core.client.Duration;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since Aug 10, 2018 3:10:31 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HabituationParadigmListener extends TriggerListener {

    boolean isActive = true;
    private final boolean isSingleShow;
    private final int thresholdMs;
    private final int maximumShows;
    private final int baselineWindowSize = 3; // baselineWindowSize is the number of sample showings that are used to generate the baseline average
    private final int habituationThreshold = 60; // percentage of the baselineAverage at witch triggering will occur
    private Integer baselineAverage = null;
    private final List<Integer> baselineSamples = new ArrayList<>();
    private int showCounter = 0;
    private Duration lastTrigger = null;

    public HabituationParadigmListener(String listenerId, int threshold, int maximum, TimedStimulusListener triggerListener, final boolean notFirstItem) {
        super(listenerId, threshold, maximum, triggerListener);
        thresholdMs = threshold;
        maximumShows = maximum;
        isSingleShow = notFirstItem;
    }

    @Override
    public void trigger() {
        if (lastTrigger == null) {
            showCounter++;
            lastTrigger = new Duration();
        }
    }

    @Override
    public void reset() {
        if (lastTrigger != null) {
            final int elapsedMillis = lastTrigger.elapsedMillis();
            lastTrigger = null;
            evaluateReset(elapsedMillis);
        }
    }

    protected void evaluateReset(final int elapsedMillis) {
        if (isActive && elapsedMillis > thresholdMs) {
            // baseline = sampling first three
            // when current falls below 60% of the baseline then trigger
            // any viewing with a duration less that the threshold will be discarded from the average
            if (isSingleShow || showCounter > maximumShows) {
                triggerListener.postLoadTimerFired();
                isActive = false;
            } else {
                baselineSamples.add(elapsedMillis);
                if (baselineSamples.size() > baselineWindowSize) {
                    if (baselineAverage == null) {
                        int baselineTotal = 0;
                        for (int index = 0; index < baselineWindowSize; index++) {
                            baselineTotal += baselineSamples.get(index);
                        }
                        baselineAverage = baselineTotal / baselineWindowSize;
                    }
                    int currentTotal = 0;
                    for (int index = baselineSamples.size() - baselineWindowSize; index < baselineSamples.size(); index++) {
                        currentTotal += baselineSamples.get(index);
                    }
                    int currentAverage = currentTotal / baselineWindowSize;
                    if ((baselineAverage / 100 * habituationThreshold) > currentAverage) {
                        triggerListener.postLoadTimerFired();
                        isActive = false;
                    }
                }
            }
        }

    }

    public String generateJsonResults() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("listenerId:'");
        builder.append(this.getListenerId());
        builder.append("',thresholdMs:");
        builder.append(thresholdMs);
        builder.append(",maximumShows:");
        builder.append(maximumShows);
        builder.append(",baselineWindowSize:");
        builder.append(baselineWindowSize);
        builder.append(",habituationThreshold:");
        builder.append(habituationThreshold);
        builder.append(",baselineAverage:");
        builder.append(baselineAverage);
        builder.append(",baselineSamples: {");
        boolean first = true;
        for (int value : baselineSamples) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append(value);
        }
        builder.append("}");
        return builder.toString();
    }
}
