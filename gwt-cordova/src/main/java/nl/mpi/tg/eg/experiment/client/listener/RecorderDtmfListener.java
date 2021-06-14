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

import java.util.HashMap;
import java.util.Map;
import nl.mpi.tg.eg.experiment.client.service.HardwareTimeStamp;

/**
 * @since 11 Jun 2021 14:12:46 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class RecorderDtmfListener {

    private final Map<HardwareTimeStamp.DTMF, SingleStimulusListener> listenerMap = new HashMap();
    private HardwareTimeStamp.DTMF lastCodeDetected = null;
    private int lastCodeCounter = 0;

    public boolean addDtmfListener(HardwareTimeStamp.DTMF triggerCode, SingleStimulusListener singleStimulusListener) {
        boolean isFirst = listenerMap.isEmpty();
        listenerMap.put(triggerCode, singleStimulusListener);
        return isFirst;
    }

    public boolean triggerOnMatching(Integer peekLevel1, Integer peekLevel2, Integer peekLevel3, Integer peekLevel4, Integer peekLevel5, Integer peekLevel6, Integer peekLevel7, Integer peekLevel8, Integer averageLevel, Integer peekLevel) {
        int triggerThreshold = averageLevel + ((peekLevel - averageLevel) / 4);
        final HardwareTimeStamp.DTMF codeRow[];
        if (peekLevel1 > triggerThreshold && peekLevel2 < triggerThreshold && peekLevel3 < triggerThreshold && peekLevel4 < triggerThreshold) {
            // 697 Hz	1	2	3	A
            codeRow = new HardwareTimeStamp.DTMF[]{HardwareTimeStamp.DTMF.code1, HardwareTimeStamp.DTMF.code2, HardwareTimeStamp.DTMF.code3, HardwareTimeStamp.DTMF.codeA};
        } else if (peekLevel1 < triggerThreshold && peekLevel2 > triggerThreshold && peekLevel3 < triggerThreshold && peekLevel4 < triggerThreshold) {
            // 770 Hz	4	5	6	B
            codeRow = new HardwareTimeStamp.DTMF[]{HardwareTimeStamp.DTMF.code4, HardwareTimeStamp.DTMF.code5, HardwareTimeStamp.DTMF.code6, HardwareTimeStamp.DTMF.codeB};
        } else if (peekLevel1 < triggerThreshold && peekLevel2 < triggerThreshold && peekLevel3 > triggerThreshold && peekLevel4 < triggerThreshold) {
            // 852 Hz	7	8	9	C    
            codeRow = new HardwareTimeStamp.DTMF[]{HardwareTimeStamp.DTMF.code7, HardwareTimeStamp.DTMF.code8, HardwareTimeStamp.DTMF.code9, HardwareTimeStamp.DTMF.codeC};
        } else if (peekLevel1 < triggerThreshold && peekLevel2 < triggerThreshold && peekLevel3 < triggerThreshold && peekLevel4 > triggerThreshold) {
            // 941 Hz	*	0	#	D
            codeRow = new HardwareTimeStamp.DTMF[]{HardwareTimeStamp.DTMF.codeAsterisk, HardwareTimeStamp.DTMF.code0, HardwareTimeStamp.DTMF.codeHash, HardwareTimeStamp.DTMF.codeD};
        } else {
            codeRow = null;
        }
        if (codeRow != null) {
            final HardwareTimeStamp.DTMF codeDetected;
            if (peekLevel5 > triggerThreshold && peekLevel6 < triggerThreshold && peekLevel7 < triggerThreshold && peekLevel8 < triggerThreshold) {
                // 1209 Hz
                codeDetected = codeRow[0];
            } else if (peekLevel5 < triggerThreshold && peekLevel6 > triggerThreshold && peekLevel7 < triggerThreshold && peekLevel8 < triggerThreshold) {
                // 1336 Hz
                codeDetected = codeRow[1];
            } else if (peekLevel5 < triggerThreshold && peekLevel6 < triggerThreshold && peekLevel7 > triggerThreshold && peekLevel8 < triggerThreshold) {
                // 1477 Hz
                codeDetected = codeRow[2];
            } else if (peekLevel5 < triggerThreshold && peekLevel6 < triggerThreshold && peekLevel7 < triggerThreshold && peekLevel8 > triggerThreshold) {
                // 1633 Hz
                codeDetected = codeRow[3];
            } else {
                codeDetected = null;
            }
            if (codeDetected != null) {
                if (codeDetected.equals(lastCodeDetected)) {
                    lastCodeCounter++;
                } else {
                    lastCodeCounter = 1;
                }
                if (lastCodeCounter > 100) {
                    final SingleStimulusListener triggerForCode = listenerMap.get(codeDetected);
                    if (triggerForCode != null) {
                        triggerForCode.postLoadTimerFired(null);
                    }
                }
            } else {
                lastCodeDetected = null;
            }
        }
        return true;
    }

    public void clearTriggers() {
        listenerMap.clear();
    }
}
