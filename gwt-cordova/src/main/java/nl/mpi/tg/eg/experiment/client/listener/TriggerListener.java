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

import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jan 30, 2018 4:29:59 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class TriggerListener {

    final private String listenerId;
    final private int threshold;
    final private int maximum;
    final protected SingleStimulusListener triggerListener;
    private int maximumCounter = 0;
    private int thresholdCounter = 0;
    final private Stimulus definitionScopeStimulus;

    public TriggerListener(final Stimulus definitionScopeStimulus, String listenerId, int threshold, int maximum, SingleStimulusListener triggerListener) {
        this.listenerId = listenerId;
        this.threshold = threshold;
        this.maximum = maximum;
        this.triggerListener = triggerListener;
        this.definitionScopeStimulus = definitionScopeStimulus;
    }

    public String getListenerId() {
        return listenerId;
    }

    public void reset() {
        maximumCounter = 0;
        thresholdCounter = 0;
    }

    public boolean canTrigger() {
        boolean noMaximum = maximum <= 0;
        return (noMaximum || maximumCounter < maximum);
    }

    public void trigger(final Stimulus triggerScopeStimulus) {
        thresholdCounter++;
        boolean noMaximum = maximum <= 0;
        if (thresholdCounter >= threshold && (noMaximum || maximumCounter < maximum)) {
            maximumCounter++;
            if (definitionScopeStimulus != null) {
                // when the trigger was defined in an existing stimulus scope only that stimulus is used
                triggerListener.postLoadTimerFired(definitionScopeStimulus);
            } else {
                // when the trigger was defined outside of any stimulus scope then the stimulus from the trigger is used
                triggerListener.postLoadTimerFired(triggerScopeStimulus);
            }
        }
    }
}
