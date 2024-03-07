/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @since Oct 26, 2017 2:11:02 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ExtendedKeyCodes {

    public static final int KEY_A = 65;
    public static final int KEY_B = 66;
    public static final int KEY_C = 67;
    public static final int KEY_D = 68;
    public static final int KEY_E = 69;
    public static final int KEY_F = 70;
    public static final int KEY_G = 71;
    public static final int KEY_H = 72;
    public static final int KEY_I = 73;
    public static final int KEY_J = 74;
    public static final int KEY_K = 75;
    public static final int KEY_L = 76;
    public static final int KEY_M = 77;
    public static final int KEY_N = 78;
    public static final int KEY_O = 79;
    public static final int KEY_P = 80;
    public static final int KEY_Q = 81;
    public static final int KEY_R = 82;
    public static final int KEY_S = 83;
    public static final int KEY_T = 84;
    public static final int KEY_U = 85;
    public static final int KEY_V = 86;
    public static final int KEY_W = 87;
    public static final int KEY_X = 88;
    public static final int KEY_Y = 89;
    public static final int KEY_Z = 90;
    public static final int KEY_ZERO = 48;
    public static final int KEY_ONE = 49;
    public static final int KEY_TWO = 50;
    public static final int KEY_THREE = 51;
    public static final int KEY_FOUR = 52;
    public static final int KEY_FIVE = 53;
    public static final int KEY_SIX = 54;
    public static final int KEY_SEVEN = 55;
    public static final int KEY_EIGHT = 56;
    public static final int KEY_NINE = 57;
    public static final int KEY_NUM_ZERO = 96;
    public static final int KEY_NUM_ONE = 97;
    public static final int KEY_NUM_TWO = 98;
    public static final int KEY_NUM_THREE = 99;
    public static final int KEY_NUM_FOUR = 100;
    public static final int KEY_NUM_FIVE = 101;
    public static final int KEY_NUM_SIX = 102;
    public static final int KEY_NUM_SEVEN = 103;
    public static final int KEY_NUM_EIGHT = 104;
    public static final int KEY_NUM_NINE = 105;
    public static final int KEY_NUM_MULTIPLY = 106;
    public static final int KEY_NUM_PLUS = 107;
    public static final int KEY_NUM_MINUS = 109;
    public static final int KEY_NUM_PERIOD = 110;
    public static final int KEY_NUM_DIVISION = 111;
    public static final int KEY_ALT = 18;
    public static final int KEY_BACKSPACE = 8;
    public static final int KEY_CTRL = 17;
    public static final int KEY_DELETE = 46;
    public static final int KEY_DOWN = 40;
    public static final int KEY_END = 35;
    public static final int KEY_ENTER = 13;
    public static final int KEY_ESCAPE = 27;
    public static final int KEY_HOME = 36;
    public static final int KEY_LEFT = 37;
    public static final int KEY_PAGEDOWN = 34;
    public static final int KEY_PAGEUP = 33;
    public static final int KEY_RIGHT = 39;
    public static final int KEY_SHIFT = 16;
    public static final int KEY_TAB = 9;
    public static final int KEY_UP = 38;
    public static final int KEY_F1 = 112;
    public static final int KEY_F2 = 113;
    public static final int KEY_F3 = 114;
    public static final int KEY_F4 = 115;
    public static final int KEY_F5 = 116;
    public static final int KEY_F6 = 117;
    public static final int KEY_F7 = 118;
    public static final int KEY_F8 = 119;
    public static final int KEY_F9 = 120;
    public static final int KEY_F10 = 121;
    public static final int KEY_F11 = 122;
    public static final int KEY_F12 = 123;
    public static final int KEY_PAUSE = 19;
    public static final int KEY_SPACE = 32;

    // Bluetooth remote R1 with mode M+A
    public static final int KEY_R1_MA_A = 179;
    public static final int KEY_R1_MA_B = -1;
    public static final int KEY_R1_MA_C = -1; // vol
    public static final int KEY_R1_MA_D = -1; // vol
    public static final int KEY_R1_MA_ENTER = 13;
    public static final int KEY_R1_MA_BACK = 0; // back
    public static final int KEY_R1_MA_UP = -1; // vol
    public static final int KEY_R1_MA_DOWN = -1; // vol
    public static final int KEY_R1_MA_LEFT = 177;
    public static final int KEY_R1_MA_RIGHT = 176;

    // Bluetooth remote R1 with mode M+B
    public static final int KEY_R1_MB_A = 32;
    public static final int KEY_R1_MB_B = 13;
    public static final int KEY_R1_MB_C = 8;
    public static final int KEY_R1_MB_D = 0; // back
    public static final int KEY_R1_MB_ENTER = 0;
    public static final int KEY_R1_MB_BACK = 0;
    public static final int KEY_R1_MB_UP = -1;
    public static final int KEY_R1_MB_DOWN = -1;
    public static final int KEY_R1_MB_LEFT = -1;
    public static final int KEY_R1_MB_RIGHT = -1;

    // Bluetooth remote R1 with mode M+C
    public static final int KEY_R1_MC_A = 0; // back
    public static final int KEY_R1_MC_B = 8;
    public static final int KEY_R1_MC_C = 13;
    public static final int KEY_R1_MC_D = 32;
    public static final int KEY_R1_MC_ENTER = 0;
    public static final int KEY_R1_MC_BACK = 0;
    public static final int KEY_R1_MC_UP = -1;
    public static final int KEY_R1_MC_DOWN = -1;
    public static final int KEY_R1_MC_LEFT = -1;
    public static final int KEY_R1_MC_RIGHT = -1;

    // Bluetooth remote R1 with mode M+D
    public static final int KEY_R1_MD_A = 13;
    public static final int KEY_R1_MD_B = 0; // back
    public static final int KEY_R1_MD_C = -1; // vol
    public static final int KEY_R1_MD_D = -1; // vol
    public static final int KEY_R1_MD_ENTER = -1; // mouse
    public static final int KEY_R1_MD_BACK = -1; // mouse
    public static final int KEY_R1_MD_UP = -1; // mouse
    public static final int KEY_R1_MD_DOWN = -1; // mouse
    public static final int KEY_R1_MD_LEFT = -1; // mouse
    public static final int KEY_R1_MD_RIGHT = -1; // mouse

    // USB LP310 laser pointer remote
    public static final int KEY_LP310_UP = 38;
    public static final int KEY_LP310_UP_LONG_A = 116;
    public static final int KEY_LP310_UP_LONG_B = 27;
    public static final int KEY_LP310_DOWN = 40;
    public static final int KEY_LP310_DOWN_LONG = 66;
    public static final int KEY_LP310_MIDDLE = 9;
    public static final int KEY_LP310_MIDDLE_LONG = 18;
    public static final int KEY_LP310_MIDDLE_DOUBLE = 13;

    public Map<String, Integer> getExtendedKeyCodesMap() {
        Map<String, Integer> extendedKeyCodesMap = new HashMap<>();

        extendedKeyCodesMap.put("KEY_A", KEY_A);
        extendedKeyCodesMap.put("KEY_B", KEY_B);
        extendedKeyCodesMap.put("KEY_C", KEY_C);
        extendedKeyCodesMap.put("KEY_D", KEY_D);
        extendedKeyCodesMap.put("KEY_E", KEY_E);
        extendedKeyCodesMap.put("KEY_F", KEY_F);
        extendedKeyCodesMap.put("KEY_G", KEY_G);
        extendedKeyCodesMap.put("KEY_H", KEY_H);
        extendedKeyCodesMap.put("KEY_I", KEY_I);
        extendedKeyCodesMap.put("KEY_J", KEY_J);
        extendedKeyCodesMap.put("KEY_K", KEY_K);
        extendedKeyCodesMap.put("KEY_L", KEY_L);
        extendedKeyCodesMap.put("KEY_M", KEY_M);
        extendedKeyCodesMap.put("KEY_N", KEY_N);
        extendedKeyCodesMap.put("KEY_O", KEY_O);
        extendedKeyCodesMap.put("KEY_P", KEY_P);
        extendedKeyCodesMap.put("KEY_Q", KEY_Q);
        extendedKeyCodesMap.put("KEY_R", KEY_R);
        extendedKeyCodesMap.put("KEY_S", KEY_S);
        extendedKeyCodesMap.put("KEY_T", KEY_T);
        extendedKeyCodesMap.put("KEY_U", KEY_U);
        extendedKeyCodesMap.put("KEY_V", KEY_V);
        extendedKeyCodesMap.put("KEY_W", KEY_W);
        extendedKeyCodesMap.put("KEY_X", KEY_X);
        extendedKeyCodesMap.put("KEY_Y", KEY_Y);
        extendedKeyCodesMap.put("KEY_Z", KEY_Z);
        extendedKeyCodesMap.put("KEY_ZERO", KEY_ZERO);
        extendedKeyCodesMap.put("KEY_ONE", KEY_ONE);
        extendedKeyCodesMap.put("KEY_TWO", KEY_TWO);
        extendedKeyCodesMap.put("KEY_THREE", KEY_THREE);
        extendedKeyCodesMap.put("KEY_FOUR", KEY_FOUR);
        extendedKeyCodesMap.put("KEY_FIVE", KEY_FIVE);
        extendedKeyCodesMap.put("KEY_SIX", KEY_SIX);
        extendedKeyCodesMap.put("KEY_SEVEN", KEY_SEVEN);
        extendedKeyCodesMap.put("KEY_EIGHT", KEY_EIGHT);
        extendedKeyCodesMap.put("KEY_NINE", KEY_NINE);
        extendedKeyCodesMap.put("KEY_NUM_ZERO", KEY_NUM_ZERO);
        extendedKeyCodesMap.put("KEY_NUM_ONE", KEY_NUM_ONE);
        extendedKeyCodesMap.put("KEY_NUM_TWO", KEY_NUM_TWO);
        extendedKeyCodesMap.put("KEY_NUM_THREE", KEY_NUM_THREE);
        extendedKeyCodesMap.put("KEY_NUM_FOUR", KEY_NUM_FOUR);
        extendedKeyCodesMap.put("KEY_NUM_FIVE", KEY_NUM_FIVE);
        extendedKeyCodesMap.put("KEY_NUM_SIX", KEY_NUM_SIX);
        extendedKeyCodesMap.put("KEY_NUM_SEVEN", KEY_NUM_SEVEN);
        extendedKeyCodesMap.put("KEY_NUM_EIGHT", KEY_NUM_EIGHT);
        extendedKeyCodesMap.put("KEY_NUM_NINE", KEY_NUM_NINE);
        extendedKeyCodesMap.put("KEY_NUM_MULTIPLY", KEY_NUM_MULTIPLY);
        extendedKeyCodesMap.put("KEY_NUM_PLUS", KEY_NUM_PLUS);
        extendedKeyCodesMap.put("KEY_NUM_MINUS", KEY_NUM_MINUS);
        extendedKeyCodesMap.put("KEY_NUM_PERIOD", KEY_NUM_PERIOD);
        extendedKeyCodesMap.put("KEY_NUM_DIVISION", KEY_NUM_DIVISION);
        extendedKeyCodesMap.put("KEY_ALT", KEY_ALT);
        extendedKeyCodesMap.put("KEY_BACKSPACE", KEY_BACKSPACE);
        extendedKeyCodesMap.put("KEY_CTRL", KEY_CTRL);
        extendedKeyCodesMap.put("KEY_DELETE", KEY_DELETE);
        extendedKeyCodesMap.put("KEY_DOWN", KEY_DOWN);
        extendedKeyCodesMap.put("KEY_END", KEY_END);
        extendedKeyCodesMap.put("KEY_ENTER", KEY_ENTER);
        extendedKeyCodesMap.put("KEY_ESCAPE", KEY_ESCAPE);
        extendedKeyCodesMap.put("KEY_HOME", KEY_HOME);
        extendedKeyCodesMap.put("KEY_LEFT", KEY_LEFT);
        extendedKeyCodesMap.put("KEY_PAGEDOWN", KEY_PAGEDOWN);
        extendedKeyCodesMap.put("KEY_PAGEUP", KEY_PAGEUP);
        extendedKeyCodesMap.put("KEY_RIGHT", KEY_RIGHT);
        extendedKeyCodesMap.put("KEY_SHIFT", KEY_SHIFT);
        extendedKeyCodesMap.put("KEY_TAB", KEY_TAB);
        extendedKeyCodesMap.put("KEY_UP", KEY_UP);
        extendedKeyCodesMap.put("KEY_F1", KEY_F1);
        extendedKeyCodesMap.put("KEY_F2", KEY_F2);
        extendedKeyCodesMap.put("KEY_F3", KEY_F3);
        extendedKeyCodesMap.put("KEY_F4", KEY_F4);
        extendedKeyCodesMap.put("KEY_F5", KEY_F5);
        extendedKeyCodesMap.put("KEY_F6", KEY_F6);
        extendedKeyCodesMap.put("KEY_F7", KEY_F7);
        extendedKeyCodesMap.put("KEY_F8", KEY_F8);
        extendedKeyCodesMap.put("KEY_F9", KEY_F9);
        extendedKeyCodesMap.put("KEY_F10", KEY_F10);
        extendedKeyCodesMap.put("KEY_F11", KEY_F11);
        extendedKeyCodesMap.put("KEY_F12", KEY_F12);
        extendedKeyCodesMap.put("KEY_PAUSE", KEY_PAUSE);
        extendedKeyCodesMap.put("KEY_SPACE", KEY_SPACE);

        // Bluetooth remote R1 with mode M+A
        extendedKeyCodesMap.put("KEY_R1_MA_A", KEY_R1_MA_A);
        extendedKeyCodesMap.put("KEY_R1_MA_B", KEY_R1_MA_B);
        extendedKeyCodesMap.put("KEY_R1_MA_C", KEY_R1_MA_C);  // vol
        extendedKeyCodesMap.put("KEY_R1_MA_D", KEY_R1_MA_D);  // vol
        extendedKeyCodesMap.put("KEY_R1_MA_ENTER", KEY_R1_MA_ENTER);
        extendedKeyCodesMap.put("KEY_R1_MA_BACK", KEY_R1_MA_BACK);  // back
        extendedKeyCodesMap.put("KEY_R1_MA_UP", KEY_R1_MA_UP);  // vol
        extendedKeyCodesMap.put("KEY_R1_MA_DOWN", KEY_R1_MA_DOWN);  // vol
        extendedKeyCodesMap.put("KEY_R1_MA_LEFT", KEY_R1_MA_LEFT);
        extendedKeyCodesMap.put("KEY_R1_MA_RIGHT", KEY_R1_MA_RIGHT);

        // Bluetooth remote R1 with mode M+B
        extendedKeyCodesMap.put("KEY_R1_MB_A", KEY_R1_MB_A);
        extendedKeyCodesMap.put("KEY_R1_MB_B", KEY_R1_MB_B);
        extendedKeyCodesMap.put("KEY_R1_MB_C", KEY_R1_MB_C);
        extendedKeyCodesMap.put("KEY_R1_MB_D", KEY_R1_MB_D);  // back
        extendedKeyCodesMap.put("KEY_R1_MB_ENTER", KEY_R1_MB_ENTER);
        extendedKeyCodesMap.put("KEY_R1_MB_BACK", KEY_R1_MB_BACK);
        extendedKeyCodesMap.put("KEY_R1_MB_UP", KEY_R1_MB_UP);
        extendedKeyCodesMap.put("KEY_R1_MB_DOWN", KEY_R1_MB_DOWN);
        extendedKeyCodesMap.put("KEY_R1_MB_LEFT", KEY_R1_MB_LEFT);
        extendedKeyCodesMap.put("KEY_R1_MB_RIGHT", KEY_R1_MB_RIGHT);

        // Bluetooth remote R1 with mode M+C
        extendedKeyCodesMap.put("KEY_R1_MC_A", KEY_R1_MC_A);  // back
        extendedKeyCodesMap.put("KEY_R1_MC_B", KEY_R1_MC_B);
        extendedKeyCodesMap.put("KEY_R1_MC_C", KEY_R1_MC_C);
        extendedKeyCodesMap.put("KEY_R1_MC_D", KEY_R1_MC_D);
        extendedKeyCodesMap.put("KEY_R1_MC_ENTER", KEY_R1_MC_ENTER);
        extendedKeyCodesMap.put("KEY_R1_MC_BACK", KEY_R1_MC_BACK);
        extendedKeyCodesMap.put("KEY_R1_MC_UP", KEY_R1_MC_UP);
        extendedKeyCodesMap.put("KEY_R1_MC_DOWN", KEY_R1_MC_DOWN);
        extendedKeyCodesMap.put("KEY_R1_MC_LEFT", KEY_R1_MC_LEFT);
        extendedKeyCodesMap.put("KEY_R1_MC_RIGHT", KEY_R1_MC_RIGHT);

        // Bluetooth remote R1 with mode M+D
        extendedKeyCodesMap.put("KEY_R1_MD_A", KEY_R1_MD_A);
        extendedKeyCodesMap.put("KEY_R1_MD_B", KEY_R1_MD_B);  // back
        extendedKeyCodesMap.put("KEY_R1_MD_C", KEY_R1_MD_C);  // vol
        extendedKeyCodesMap.put("KEY_R1_MD_D", KEY_R1_MD_D);  // vol
        extendedKeyCodesMap.put("KEY_R1_MD_ENTER", KEY_R1_MD_ENTER);  // mouse
        extendedKeyCodesMap.put("KEY_R1_MD_BACK", KEY_R1_MD_BACK);  // mouse
        extendedKeyCodesMap.put("KEY_R1_MD_UP", KEY_R1_MD_UP);  // mouse
        extendedKeyCodesMap.put("KEY_R1_MD_DOWN", KEY_R1_MD_DOWN);  // mouse
        extendedKeyCodesMap.put("KEY_R1_MD_LEFT", KEY_R1_MD_LEFT);  // mouse
        extendedKeyCodesMap.put("KEY_R1_MD_RIGHT", KEY_R1_MD_RIGHT);  // mouse

        // USB LP310 laser pointer remote
        extendedKeyCodesMap.put("KEY_LP310_UP", KEY_LP310_UP);
        extendedKeyCodesMap.put("KEY_LP310_UP_LONG_A", KEY_LP310_UP_LONG_A);
        extendedKeyCodesMap.put("KEY_LP310_UP_LONG_B", KEY_LP310_UP_LONG_B);
        extendedKeyCodesMap.put("KEY_LP310_DOWN", KEY_LP310_DOWN);
        extendedKeyCodesMap.put("KEY_LP310_DOWN_LONG", KEY_LP310_DOWN_LONG);
        extendedKeyCodesMap.put("KEY_LP310_MIDDLE", KEY_LP310_MIDDLE);
        extendedKeyCodesMap.put("KEY_LP310_MIDDLE_LONG", KEY_LP310_MIDDLE_LONG);
        extendedKeyCodesMap.put("KEY_LP310_MIDDLE_DOUBLE", KEY_LP310_MIDDLE_DOUBLE);
        return extendedKeyCodesMap;
    }
}
