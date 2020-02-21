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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.categorisationpool;

/**
 *
 * @author olhshk
 */
public class CategorisationCsv {

    public static String CSV = "Trial_Nr;Category;Target_Word;Syllables;Letters;ZipfF;Prev;PTAN;PTAF;Filename;Target_length;Expected_button\n" +
"Practice_1;Foil;woestijn;2;8;4.44;0.996421001;0;0;woestijn.wav;700;2\n" +
"Practice_2;Professions;tandarts;2;8;4.16;0.997296494;0;0;tandarts.wav;720;1\n" +
"Practice_3;Professions;danseres;3;8;3.77;0.996769637;1;2.86;danseres.wav;903;1\n" +
"Practice_4;Foil;kofferbak;3;9;4.05;0.998047607;0;0;kofferbak.wav;802;2\n" +
"1;Professions;timmerman;3;9;3.57;0.998940296;0;0;timmerman.wav;680;1\n" +
"2;Foil;poster;2;6;3.57;0.993255008;6;2.405;poster.wav;602;2\n" +
"3;Professions;serveerster;3;11;3.99;0.999497455;0;0;serveerster.wav;969;1\n" +
"4;Professions;postbode;3;8;3.80;0.998765103;2;0.62;postbode.wav;730;1\n" +
"5;Professions;lerares;3;7;4.07;0.999450185;2;4.26;lerares.wav;949;1\n" +
"6;Foil;vanille;3;7;3.39;0.999412035;0;0;vanille.wav;718;2\n" +
"7;Foil;mobiel;2;6;3.96;0.999550194;1;9.513;mobiel.wav;650;2\n" +
"8;Professions;architect;3;9;3.76;0.99469912;0;0;architect.wav;898;1\n" +
"9;Foil;batterij;3;8;3.83;0.998633041;1;2.47;batterij.wav;682;2\n" +
"10;Foil;regenwoud;3;9;3.24;0.999462262;0;0;regenwoud.wav;940;2\n" +
"11;Professions;directrice;4;10;3.36;0.995325009;0;0;directrice.wav;948;1\n" +
"12;Professions;juwelier;3;8;3.31;0.999512894;0;0;juwelier.wav;888;1\n" +
"13;Foil;balkon;2;6;3.82;0.99853381;2;3.29;balkon.wav;647;2\n" +
"14;Professions;zangeres;3;8;3.77;0.996611344;1;1.74;zangeres.wav;848;1\n" +
"15;Professions;fotograaf;3;9;4.00;0.999538264;2;1.41;fotograaf.wav;1104;1\n" +
"16;Foil;krokodil;3;8;3.69;0.997044749;0;0;krokodil.wav;751;2\n" +
"17;Foil;zaklamp;2;7;3.71;0.999528049;0;0;zaklamp.wav;839;2\n" +
"18;Professions;chirurg;2;7;3.95;0.996758601;0;0;chirurg.wav;884;1\n" +
"19;Foil;bushalte;3;8;3.36;0.998222897;0;0;bushalte.wav;751;2\n" +
"20;Professions;slager;2;6;3.82;0.999522158;5;6.38;slager.wav;750;1\n" +
"21;Professions;kapster;2;7;2.97;0.994368123;0;0;kapster.wav;599;1\n" +
"22;Foil;trampoline;4;10;2.88;0.99134042;0;0;trampoline.wav;879;2\n" +
"23;Professions;bakker;2;6;3.60;0.998202739;10;26.68;bakker.wav;579;1\n" +
"24;Professions;therapeute;4;10;2.89;0.993343698;2;3.93;therapeute.wav;960;1\n" +
"25;Professions;boerin;2;6;2.81;0.997692911;1;15.28;boerin.wav;735;1\n" +
"26;Foil;container;3;9;3.59;0.993021633;1;1.44;container.wav;843;2\n" +
"27;Professions;stewardess;3;10;3.58;0.98521211;0;0;stewardess.wav;989;1\n" +
"28;Professions;verkoopster;3;11;3.17;0.998561701;0;0;verkoopster.wav;980;1\n" +
"29;Foil;schoorsteen;2;11;3.64;0.998395208;0;0;schoorsteen.wav;1001;2\n" +
"30;Professions;makelaar;3;8;3.71;0.999623191;0;0;makelaar.wav;846;1\n" +
"31;Professions;verpleger;3;9;3.43;0.998963209;1;1.28;verpleger.wav;840;1\n" +
"32;Professions;schrijfster;2;11;3.54;0.992215924;0;0;schrijfster.wav;831;1\n" +
"Practice_5;vehicles;helikopter;4;10;4.34;0.983536966;1;5.97;helikopter.wav;941;1\n" +
"Practice_6;Foil;schilderij;3;10;4.33;0.999459603;3;4.95;schilderij.wav;880;2\n" +
"Practice_7;vehicles;vrachtwagen;3;11;4.24;0.996412011;1;4.23;vrachtwagen.wav;965;1\n" +
"Practice_8;Foil;oceaan;3;6;4.29;0.99241666;0;0;oceaan.wav;808;2\n" +
"33;vehicles;tandem;2;6;2.20;0.993347342;0;0;tandem.wav;584;1\n" +
"34;vehicles;sloep;1;5;3.30;0.998212366;12;20.65;sloep.wav;598;1\n" +
"35;Foil;blokhut;2;7;2.88;0.997433392;0;0;blokhut.wav;747;2\n" +
"36;vehicles;scooter;2;7;3.69;0.994792494;2;5.65;scooter.wav;649;1\n" +
"37;Foil;pijl;1;4;3.85;0.997766085;14;24.81;pijl.wav;448;2\n" +
"38;Foil;hamster;2;7;3.28;0.997203536;1;0.73;hamster.wav;728;2\n" +
"39;vehicles;camper;2;6;3.57;0.99245262;0;0;camper.wav;587;1\n" +
"40;vehicles;speedboot;2;9;2.95;0.998436444;0;0;speedboot.wav;910;1\n" +
"41;vehicles;limousine;4;9;3.69;0.984915507;1;0.869;limousine.wav;848;1\n" +
"42;Foil;contactlens;3;11;2.66;0.999210334;0;0;contactlens.wav;1013;2\n" +
"43;vehicles;tram;1;4;3.26;0.996284425;6;11.82;tram.wav;510;1\n" +
"44;vehicles;motorfiets;3;3;3.28;0.999527176;0;0;motorfiets.wav;940;1\n" +
"45;Foil;lippenstift;3;11;3.81;0.999352511;0;0;lippenstift.wav;1079;2\n" +
"46;vehicles;vrachtschip;2;11;3.40;0.998119165;0;0;vrachtschip.wav;929;1\n" +
"47;Foil;kachel;2;6;3.46;0.99927824;0;0;kachel.wav;551;2\n" +
"48;vehicles;brommer;2;7;3.03;0.997794087;1;0.91;brommer.wav;572;1\n" +
"49;vehicles;tractor;2;7;3.38;0.996062127;2;5;tractor.wav;596;1\n" +
"50;Foil;pruik;1;5;3.81;0.998432783;7;7.07;pruik.wav;549;2\n" +
"51;vehicles;roeiboot;2;8;2.98;0.996517423;0;0;roeiboot.wav;822;1\n" +
"52;Foil;kurk;1;4;3.21;0.99816027;3;46.32;kurk.wav;549;2\n" +
"53;vehicles;politieauto;2;11;3.31;0.994478327;0;0;politieauto.wav;1055;1\n" +
"54;vehicles;veerpont;2;8;2.15;0.986679854;0;0;veerpont.wav;912;1\n" +
"55;Foil;roman;2;5;4.04;0.990547411;2;2.42;roman.wav;730;2\n" +
"56;vehicles;sneltrein;2;9;2.74;0.99519793;0;0;sneltrein.wav;909;1\n" +
"57;vehicles;metro;2;5;4.12;0.996843695;0;0;metro.wav;719;1\n" +
"58;vehicles;kano;2;4;3.35;0.987371971;1;29.29;kano.wav;651;1\n" +
"59;Foil;schatkist;2;9;3.20;0.999662642;0;0;schatkist.wav;898;2\n" +
"60;vehicles;koets;1;5;3.87;0.997341147;8;8.64;koets.wav;590;1\n" +
"61;Foil;borstel;2;7;3.32;0.999515951;3;7.09;borstel.wav;701;2\n" +
"62;vehicles;jeep;1;4;3.89;0.980650812;9;6.76;jeep.wav;619;1\n" +
"63;vehicles;schoolbus;2;9;3.22;0.995264058;0;0;schoolbus.wav;908;1\n" +
"64;Foil;badjas;2;6;3.35;0.999528372;0;0;badjas.wav;806;2";

}
