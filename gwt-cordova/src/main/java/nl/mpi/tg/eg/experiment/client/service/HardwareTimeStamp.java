/*
 * Copyright (C) 2020 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.service;

import nl.mpi.tg.eg.experiment.client.model.BooleanToggle;

/**
 * @since 15 May 2020 16:03:59 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HardwareTimeStamp {

    final String hardwareTimeStampOptions;
    private boolean opto1State = true;
    private boolean opto2State = true;
    private DTMF currentTone = DTMF.codeoff;

    public HardwareTimeStamp(String hardwareTimeStampOptions, final boolean dtmfOnly) {
        this.hardwareTimeStampOptions = hardwareTimeStampOptions;
        initialiseDtmf();
        if (!dtmfOnly) {
            initialiseOpto();
        }
    }

    public enum DTMF {
        //              1209 Hz	1336 Hz	1477 Hz	1633 Hz
        //    697 Hz	1	2	3	A
        //    770 Hz	4	5	6	B
        //    852 Hz	7	8	9	C
        //    941 Hz	*	0	#	D

        code1(1209, 697), code2(1336, 697), code3(1477, 697), codeA(1633, 697),
        code4(1209, 770), code5(1336, 770), code6(1477, 770), codeB(1633, 770),
        code7(1209, 852), code8(1336, 852), code9(1477, 852), codeC(1633, 852),
        codeAsterisk(1209, 941), code0(1336, 941), codeHash(1477, 941), codeD(1633, 941),
        codeoff(0, 0);

        private DTMF(int tone1, int tone2) {
            this.tone1 = tone1;
            this.tone2 = tone2;
        }
        final int tone1;
        final int tone2;

    }

    final public void setOpto1(BooleanToggle optoState) {
        switch (optoState) {
            case OPTO_FALSE:
                opto1State = false;
                break;
            case OPTO_TRUE:
                opto1State = true;
                break;
            case OPTO_INVERT:
                opto1State = !opto1State;
                break;
        }
        setOpto1(opto1State);
    }

    final public void setOpto2(BooleanToggle optoState) {
        switch (optoState) {
            case OPTO_FALSE:
                opto2State = false;
                break;
            case OPTO_TRUE:
                opto2State = true;
                break;
            case OPTO_INVERT:
                opto2State = !opto2State;
                break;
        }
        setOpto2(opto2State);
    }

    final public native void setOpto1(boolean optoState) /*-{
            if (optoState) {
                $wnd.opto1.css('background', 'white');
            } else {
                $wnd.opto1.css('background', 'black');
            }
    }-*/;

    final public native void setOpto2(boolean optoState) /*-{
            if (optoState) {
                $wnd.opto2.css('background', 'white');
            } else {
                $wnd.opto2.css('background', 'black');
            }
    }-*/;

    public DTMF getCurrentTone() {
        return currentTone;
    }

    public void setDtmf(DTMF dtmf) {
        startDtmf(dtmf.tone1, dtmf.tone2);
        currentTone = dtmf;
    }

    final protected native boolean initialiseDtmf() /*-{
        if (!$wnd.oscillator1 || !$wnd.oscillator2) {
            var audioContext = new ($wnd.AudioContext || $wnd.webkitAudioContext)();
            $wnd.oscillator1 = audioContext.createOscillator();
            $wnd.oscillator2 = audioContext.createOscillator();
            $wnd.oscillator1.type = 'sine';
            $wnd.oscillator2.type = 'sine';
            var gainNode = audioContext.createGain();
            gainNode.gain.value = 0.5;
            $wnd.oscillator1.connect(gainNode);
            $wnd.oscillator2.connect(gainNode);
            gainNode.connect(audioContext.destination);
            $wnd.oscillator1.frequency.value = 0;
            $wnd.oscillator2.frequency.value = 0;
            $wnd.oscillator1.start();
            $wnd.oscillator2.start();
        }
    }-*/;

    final protected native boolean initialiseOpto() /*-{
        if (!$wnd.opto1 || !$wnd.opto2) {
            $wnd.$("#widgetTag").append("<div id='opto1'>opto1</div>");
            $wnd.opto1 = $wnd.$("#opto1");
            $wnd.opto1.css('background', 'white');
            $wnd.opto1.css('color', 'grey');
            $wnd.opto1.css('display', 'block');
            $wnd.opto1.css('z-index', '100');
            $wnd.opto1.css('position', 'fixed');
            $wnd.opto1.css('top', '50px');
            $wnd.opto1.css('left', '50%');
            $wnd.opto1.css('transform', 'translate(-125%,0)');
            $wnd.opto1.css('height', '200px');
            $wnd.opto1.css('width', '200px');
            $wnd.opto1.css('border', '1px grey solid');
            
            $wnd.$("#widgetTag").append("<div id='opto2'>opto2</div>");
            $wnd.opto2 = $wnd.$("#opto2");
            $wnd.opto2.css('background', 'white');
            $wnd.opto2.css('color', 'grey');
            $wnd.opto2.css('display', 'block');
            $wnd.opto2.css('z-index', '100');
            $wnd.opto2.css('position', 'fixed');
            $wnd.opto2.css('top', '50px');
            $wnd.opto2.css('left', '50%');
            $wnd.opto2.css('transform', 'translate(25%,0)');
            $wnd.opto2.css('height', '200px');
            $wnd.opto2.css('width', '200px');
            $wnd.opto2.css('border', '1px grey solid');
        }
    }-*/;

    final protected native boolean startDtmf(final int tone1, final int tone2) /*-{
        $wnd.oscillator1.frequency.value = tone1;
        $wnd.oscillator2.frequency.value = tone2;
    }-*/;
}
