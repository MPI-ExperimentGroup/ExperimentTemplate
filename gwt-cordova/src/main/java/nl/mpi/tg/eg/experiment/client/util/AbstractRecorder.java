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
package nl.mpi.tg.eg.experiment.client.util;

import nl.mpi.tg.eg.experiment.client.listener.MediaSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.MediaTriggerListener;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractPresenter;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp;
import nl.mpi.tg.eg.experiment.client.service.TimedEventMonitor;

/**
 * @since 15 Oct 2021 10:14:33 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class AbstractRecorder {

    public abstract void getRecorderTime(final AbstractPresenter abstractPresenter);

    public abstract void startRecorderTriggersWeb(final MediaTriggerListener recorderMediaTriggerListenerL);

    public abstract void startRecorderWeb(final AbstractPresenter abstractPresenter, final DataSubmissionService dataSubmissionService, final String recordingVideoLabelString, final String deviceRegex, final boolean noiseSuppressionL, final boolean echoCancellationL, final boolean autoGainControlL, final String stimulusIdString, final String userIdString, final String screenName, final MediaSubmissionListener mediaSubmissionListener, final int downloadPermittedWindowMs, final String recordingFormat);

    public abstract void stopRecorder(final AbstractPresenter abstractPresenter);

    public abstract void requestRecorderPermissions(final AbstractPresenter abstractPresenter);

    public abstract void startRecorderTag(final AbstractPresenter abstractPresenter, int tier, final TimedEventMonitor timedEventMonitor);

    public abstract void startRecorderDtmfTriggersWeb(final AbstractPresenter abstractPresenter, final MediaTriggerListener recorderMediaTriggerListenerL);

    public abstract void endRecorderTag(final AbstractPresenter abstractPresenter, int tier, String stimulusId, String stimulusCode, String eventTag, final TimedEventMonitor timedEventMonitor);

    public abstract void requestFilePermissions(final AbstractPresenter abstractPresenter);

    public abstract void injectTone(final HardwareTimeStamp.DTMF dtmf, final TimedEventMonitor timedEventMonitor);
}
