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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.lexicaldecision;

/**
 *
 * @author olhshk
 */
public class LexicalDecisionCsv {

    public static String CSV_PRACTICE = "condition;stimulus;freq_mio;freq_log;ND;freq_mio_neighbors;freq_nd_log;freq_triphone;group;file_name;SNR;trigger_code;exp_response\n" +
"exp;bek;51.13;1.71;24;288.87;2.46;0.193;freq+ND+;bek.wav;\\;251;1\n" +
"filler;bun;\\;\\;\\;\\;\\;\\;filler;bun.wav;\\;250;2\n" +
"filler;vor;\\;\\;\\;\\;\\;\\;filler;vor.wav;\\;250;2";

    public static String CSV_STIMULI = "trial_nr;word;length;freq_zipf;wav;expected\n"
            + "1;raket;5;4.17;raket.wav;1\n"
            + "2;kuig;4;NA;kuig.wav;2\n"
            + "3;sardaat;7;NA;sardaat.wav;2\n"
            + "4;korf;4;2.63;korf.wav;1\n"
            + "5;gral;4;NA;gral.wav;2\n"
            + "6;schebes;7;NA;schebes.wav;2\n"
            + "7;clonx;5;NA;clonx.wav;2\n"
            + "8;aardbei;7;3.19;aardbei.wav;1\n"
            + "9;faver;5;NA;faver.wav;2\n"
            + "10;kegel;5;2.63;kegel.wav;1\n"
            + "11;huig;4;2.04;huig.wav;1\n"
            + "12;miklohien;9;NA;miklohien.wav;2\n"
            + "13;pantoffel;9;2.36;pantoffel.wav;1\n"
            + "14;schelg;6;NA;schelg.wav;2\n"
            + "15;papier;6;4.49;papier.wav;1\n"
            + "16;gesp;4;2.98;gesp.wav;1\n"
            + "17;kever;5;3.34;kever.wav;1\n"
            + "18;kollanje;8;NA;kollanje.wav;2\n"
            + "19;gork;4;NA;gork.wav;2\n"
            + "20;pedaal;6;2.95;pedaal.wav;1\n"
            + "21;periel;6;NA;periel.wav;2\n"
            + "22;pizza;5;4.39;pizza.wav;1\n"
            + "23;dijg;4;NA;dijg.wav;2\n"
            + "24;marmot;6;2.63;marmot.wav;1\n"
            + "25;ropaan;6;NA;ropaan.wav;2\n"
            + "26;vril;4;NA;vril.wav;2\n"
            + "27;gromp;5;NA;gromp.wav;2\n"
            + "28;clown;5;4.07;clown.wav;1\n"
            + "29;geschenk;8;4.27;geschenk.wav;1\n"
            + "30;klarinet;8;3.01;klarinet.wav;1\n"
            + "31;peeuw;5;NA;peeuw.wav;2\n"
            + "32;pantabbel;9;NA;pantabbel.wav;2\n"
            + "33;microfoon;9;4.01;microfoon.wav;1\n"
            + "34;boevel;6;NA;boevel.wav;2\n"
            + "35;pistool;7;5.01;pistool.wav;1\n"
            + "36;panoer;6;NA;panoer.wav;2\n"
            + "37;kasteel;7;4.44;kasteel.wav;1\n"
            + "38;vijg;4;2.36;vijg.wav;1\n"
            + "39;gedijgte;8;NA;gedijgte.wav;2\n"
            + "40;quosirade;9;NA;quosirade.wav;2\n"
            + "41;parfum;6;4.04;parfum.wav;1\n"
            + "42;schors;6;3.06;schors.wav;1\n"
            + "43;hagel;5;3.13;hagel.wav;1\n"
            + "44;kastanje;8;2.48;kastanje.wav;1\n"
            + "45;mazer;5;NA;mazer.wav;2\n"
            + "46;klomp;5;2.95;klomp.wav;1\n"
            + "47;olend;5;NA;olend.wav;2\n"
            + "48;scherm;6;4.14;scherm.wav;1\n"
            + "49;gong;4;2.68;gong.wav;1\n"
            + "50;jager;5;4.05;jager.wav;1\n"
            + "51;daarn;5;NA;daarn.wav;2\n"
            + "52;meeuw;5;2.81;meeuw.wav;1\n"
            + "53;letel;5;NA;letel.wav;2\n"
            + "54;halg;4;NA;halg.wav;2\n"
            + "55;gras;4;4.27;gras.wav;1\n"
            + "56;auli;4;NA;auli.wav;2\n"
            + "57;gebouw;6;4.83;gebouw.wav;1\n"
            + "58;schars;6;NA;schars.wav;2\n"
            + "59;aardloe;7;NA;aardloe.wav;2\n"
            + "60;smaai;5;NA;smaai.wav;2\n"
            + "61;veter;5;3.12;veter.wav;1\n"
            + "62;hark;4;3.18;hark.wav;1\n"
            + "63;grader;6;NA;grader.wav;2\n"
            + "64;bitier;6;NA;bitier.wav;2\n"
            + "65;rozijn;6;2.48;rozijn.wav;1\n"
            + "66;postiel;7;NA;postiel.wav;2\n"
            + "67;brien;5;NA;brien.wav;2\n"
            + "68;zwoem;5;NA;zwoem.wav;2\n"
            + "69;krater;6;3.33;krater.wav;1\n"
            + "70;haver;5;2.95;haver.wav;1\n"
            + "71;lard;4;NA;lard.wav;2\n"
            + "72;reber;5;NA;reber.wav;2\n"
            + "73;terekapie;9;NA;terekapie.wav;2\n"
            + "74;magneet;7;3.2;magneet.wav;1\n"
            + "75;mastiel;7;NA;mastiel.wav;2\n"
            + "76;auto;4;5.66;auto.wav;1\n"
            + "77;ameen;5;NA;ameen.wav;2\n"
            + "78;bijbel;6;4.36;bijbel.wav;1\n"
            + "79;professor;9;4.83;professor.wav;1\n"
            + "80;marbit;6;NA;marbit.wav;2\n"
            + "81;gitaar;6;4.06;gitaar.wav;1\n"
            + "82;ambtenaar;9;3.58;ambtenaar.wav;1\n"
            + "83;soldaat;7;4.72;soldaat.wav;1\n"
            + "84;gelg;4;NA;gelg.wav;2\n"
            + "85;voetbal;7;4.11;voetbal.wav;1\n"
            + "86;smes;4;NA;smes.wav;2\n"
            + "87;bloem;5;4.13;bloem.wav;1\n"
            + "88;vaatlal;7;NA;vaatlal.wav;2\n"
            + "89;arend;5;3.31;arend.wav;1\n"
            + "90;schemer;7;2.57;schemer.wav;1\n"
            + "91;pebza;5;NA;pebza.wav;2\n"
            + "92;vlaai;5;2.26;vlaai.wav;1\n"
            + "93;geraamte;8;2.85;geraamte.wav;1\n"
            + "94;palmus;6;NA;palmus.wav;2\n"
            + "95;anktekier;9;NA;anktekier.wav;2\n"
            + "96;vliemkuig;9;NA;vliemkuig.wav;2\n"
            + "97;trein;5;4.86;trein.wav;1\n"
            + "98;rapek;5;NA;rapek.wav;2\n"
            + "99;bril;4;4.39;bril.wav;1\n"
            + "100;gralinek;8;NA;gralinek.wav;2\n"
            + "101;doorn;5;3.3;doorn.wav;1\n"
            + "102;televisie;9;4.35;televisie.wav;1\n"
            + "103;lamp;4;4.14;lamp.wav;1\n"
            + "104;gebamp;6;NA;gebamp.wav;2\n"
            + "105;azijn;5;3.23;azijn.wav;1\n"
            + "106;kries;5;NA;kries.wav;2\n"
            + "107;droksem;7;NA;droksem.wav;2\n"
            + "108;brein;5;4.22;brein.wav;1\n"
            + "109;fles;4;4.66;fles.wav;1\n"
            + "110;treik;5;NA;treik.wav;2\n"
            + "111;chocolade;9;4.14;chocolade.wav;1\n"
            + "112;madveet;7;NA;madveet.wav;2\n"
            + "113;vlijs;5;NA;vlijs.wav;2\n"
            + "114;hepel;5;NA;hepel.wav;2\n"
            + "115;kolm;4;NA;kolm.wav;2\n"
            + "116;bliksem;7;4.03;bliksem.wav;1\n"
            + "117;kruis;5;4.32;kruis.wav;1\n"
            + "118;mavel;5;NA;mavel.wav;2\n"
            + "119;geschork;8;NA;geschork.wav;2\n"
            + "120;vlees;5;4.79;vlees.wav;1";
}
