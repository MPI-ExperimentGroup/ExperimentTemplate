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
 * Foundation, Inc., 59 Temple Place  min Suite 330, Boston, MA  02111 min1307, USA.
 */
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.antisaccade;



/**
 *
 * @author olhshk
 */
public class Antisaccade {

    

    public static String CSV_STIMULI_MAIN = "Item;Cue;Stimulus;Position_cue;Position_stimulus;Correct_response\n" +
"1;square;arrowleft;right;left;left\n" +
"2;square;arrowright;left;right;right\n" +
"3;square;arrowleft;left;right;left\n" +
"4;square;arrowright;right;left;right\n" +
"5;square;arrowup;right;left;up\n" +
"6;square;arrowup;left;right;up\n" +
"7;square;arrowright;left;right;right\n" +
"8;square;arrowup;right;left;up\n" +
"9;square;arrowright;left;right;right\n" +
"10;square;arrowleft;right;left;left\n" +
"11;square;arrowright;right;left;right\n" +
"12;square;arrowleft;left;right;left\n" +
"13;square;arrowup;left;right;up\n" +
"14;square;arrowleft;right;left;left\n" +
"15;square;arrowright;left;right;right\n" +
"16;square;arrowleft;right;left;left\n" +
"17;square;arrowup;left;right;up\n" +
"18;square;arrowright;left;right;right\n" +
"19;square;arrowup;right;left;up\n" +
"20;square;arrowleft;right;left;left\n" +
"21;square;arrowright;left;right;right\n" +
"22;square;arrowleft;right;left;left\n" +
"23;square;arrowup;left;right;up\n" +
"24;square;arrowup;right;left;up\n" +
"25;square;arrowright;right;left;right\n" +
"26;square;arrowup;left;right;up\n" +
"27;square;arrowup;right;left;up\n" +
"28;square;arrowleft;left;right;left\n" +
"29;square;arrowright;left;right;right\n" +
"30;square;arrowup;right;left;up\n" +
"31;square;arrowright;left;right;right\n" +
"32;square;arrowleft;left;right;left\n" +
"33;square;arrowright;right;left;right\n" +
"34;square;arrowup;right;left;up\n" +
"35;square;arrowright;left;right;right\n" +
"36;square;arrowleft;left;right;left\n" +
"37;square;arrowleft;right;left;left\n" +
"38;square;arrowright;left;right;right\n" +
"39;square;arrowleft;right;left;left\n" +
"40;square;arrowleft;right;left;left\n" +
"41;square;arrowup;left;right;up\n" +
"42;square;arrowleft;left;right;left\n" +
"43;square;arrowleft;right;left;left\n" +
"44;square;arrowright;right;left;right\n" +
"45;square;arrowleft;left;right;left\n" +
"46;square;arrowup;right;left;up\n" +
"47;square;arrowleft;left;right;left\n" +
"48;square;arrowleft;left;right;left\n" +
"49;square;arrowup;right;left;up\n" +
"50;square;arrowleft;right;left;left\n" +
"51;square;arrowright;left;right;right\n" +
"52;square;arrowright;left;right;right\n" +
"53;square;arrowup;right;left;up\n" +
"54;square;arrowleft;left;right;left\n" +
"55;square;arrowright;right;left;right\n" +
"56;square;arrowleft;right;left;left\n" +
"57;square;arrowup;left;right;up\n" +
"58;square;arrowright;left;right;right\n" +
"59;square;arrowright;right;left;right\n" +
"60;square;arrowleft;left;right;left\n" +
"61;square;arrowleft;right;left;left\n" +
"62;square;arrowup;left;right;up\n" +
"63;square;arrowup;left;right;up\n" +
"64;square;arrowleft;right;left;left\n" +
"65;square;arrowleft;left;right;left\n" +
"66;square;arrowright;left;right;right\n" +
"67;square;arrowright;right;left;right\n" +
"68;square;arrowup;left;right;up\n" +
"69;square;arrowleft;left;right;left\n" +
"70;square;arrowup;right;left;up\n" +
"71;square;arrowright;right;left;right\n" +
"72;square;arrowright;left;right;right\n" +
"73;square;arrowup;right;left;up\n" +
"74;square;arrowright;left;right;right\n" +
"75;square;arrowright;left;right;right\n" +
"76;square;arrowleft;right;left;left\n" +
"77;square;arrowleft;right;left;left\n" +
"78;square;arrowup;left;right;up\n" +
"79;square;arrowup;right;left;up\n" +
"80;square;arrowleft;left;right;left\n" +
"81;square;arrowright;right;left;right\n" +
"82;square;arrowup;right;left;up\n" +
"83;square;arrowright;left;right;right\n" +
"84;square;arrowup;right;left;up\n" +
"85;square;arrowup;right;left;up\n" +
"86;square;arrowright;left;right;right\n" +
"87;square;arrowup;right;left;up\n" +
"88;square;arrowright;left;right;right\n" +
"89;square;arrowup;right;left;up\n" +
"90;square;arrowup;right;left;up\n";
    
    public static String CSV_STIMULI_PRACTICE = "Item;Cue;Stimulus;Position_cue;Position_stimulus;Correct_response\n" +
"1;square;arrowup;left;right;up\n" +
"2;square;arrowup;right;left;up\n" +
"3;square;arrowleft;left;right;left\n" +
"4;square;arrowright;right;left;right\n" +
"5;square;arrowleft;right;left;left\n" +
"6;square;arrowright;left;right;right\n" +
"7;square;arrowup;right;left;up\n" +
"8;square;arrowup;right;left;up\n" +
"9;square;arrowright;left;right;right\n" +
"10;square;arrowleft;right;left;left\n" +
"11;square;arrowup;left;right;up\n" +
"12;square;arrowright;right;left;right\n" +
"13;square;arrowup;right;left;up\n" +
"14;square;arrowright;left;right;right\n" +
"15;square;arrowright;left;right;right\n" +
"16;square;arrowleft;right;left;left\n" +
"17;square;arrowright;left;right;right\n" +
"18;square;arrowleft;right;left;left\n" +
"19;square;arrowleft;left;right;left\n" +
"20;square;arrowup;left;right;up\n" +
"21;square;arrowleft;right;left;left\n" +
"22;square;arrowright;left;right;right";
}
