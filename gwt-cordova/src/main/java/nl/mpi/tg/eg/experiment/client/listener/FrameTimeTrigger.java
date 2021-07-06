/*
 * Copyright (C) 2021 Max Planck Institute for Psycholinguistics, Nijmegen
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
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since 16 Jun 2021 16:58:14 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class FrameTimeTrigger {

    final private Stimulus currentStimulus;
    final TimedStimulusListener onLateListener;
    final private SingleStimulusListener onTimeListener;
    final public int triggerMs;
    final public int threshold;

    public FrameTimeTrigger(Stimulus currentStimulus, final TimedStimulusListener onLateListener, SingleStimulusListener onTimeListener, int triggerMs, final int threshold) {
        this.currentStimulus = currentStimulus;
        this.onLateListener = onLateListener;
        this.onTimeListener = onTimeListener;
        this.triggerMs = triggerMs;
        this.threshold = threshold;
    }

    public void trigger() {
        onTimeListener.postLoadTimerFired(currentStimulus);
    }

    public void triggerLateError() {
        onLateListener.postLoadTimerFired();
    }
}
