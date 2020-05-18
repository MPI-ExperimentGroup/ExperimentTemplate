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

/**
 * @since 15 May 2020 16:03:59 (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HardwareTimeStamp {

    final String hardwareTimeStampOptions;

    public HardwareTimeStamp(String hardwareTimeStampOptions) {
        this.hardwareTimeStampOptions = hardwareTimeStampOptions;
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
        codeAsterisk(1209, 941), code0(1336, 941), codeHash(1477, 941), codeD(1633, 941);

        private DTMF(int tone1, int tone2) {
            this.tone1 = tone1;
            this.tone2 = tone2;
        }
        final int tone1;
        final int tone2;

    }

    public void setOpto1(boolean optoState) {

    }

    public void setOpto2(boolean optoState) {

    }

    public void setDtmf(DTMF dtmf) {

    }
}
