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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audiopool;

/**
 *
 * @author olhshk
 */
public class NonwordWordformCsv {

    public static String NONWORD = "Order;Round;SNR;Condition;Length_list;Word;Target_nonword;Word1;Word2;Word3;Word4;Word5;Word6;Position_target;Position_foil\n"
            + "1;0;0;Target-only;4 words;schaap;schuip_1;wamoel;schuip_2;balstel;res;;;2;0;\n"
            + "2;0;-6;NoTarget;3 words;tong;tonk;zandog;dijg;vets;;;;0;0;\n"
            + "3;0;-10;Target+Foil;5 words;zalf;zarp_1;vleek;zanskolm;kelf;zarp_2;bolpes;;4;2;\n"
            + "1;1;0;Target-only;6 words;touw;tomp_1;oormoel;karp;retnin;alveid;tomp_2;penk;5;0;\n"
            + "2;1;-2;NoTarget;3 words;smaak;smijk;veg;wieel;loor;;;;0;0;\n"
            + "3;1;-4;Target-only;5 words;kroon;broon_1;warker;lepen;pluif;broon_2;pir;;4;0;\n"
            + "4;1;-6;Target+Foil;5 words;man;zan_1;dauk;andoer;zallalk;zan_2;slon;;4;3;\n"
            + "5;1;-8;NoTarget;4 words;roem;loem;salder;voes;guitel;swi;;;0;0;\n"
            + "6;1;-10;Target+Foil;4 words;taart;toort_1;toolgmong;toort_2;veepsel;sies;;;2;1;\n"
            + "7;1;-12;Target-only;3 words;kaars;kiers_1;hog;kiers_2;waga;;;;2;0;\n"
            + "8;1;-14;NoTarget;5 words;gat;gar;lomel;strieg;snaan;sloog;rirte;;0;0;\n"
            + "9;1;-16;Target+Foil;3 words;bom;rom_1;rofreun;rom_2;zamp;;;;2;1;\n"
            + "10;1;-18;Target-only;4 words;haai;hoei_1;jerg;cigroon;hoei_2;lordel;;;3;0;\n"
            + "11;2;0;Target+Foil;6 words;bad;bam_1;wirums;mefdel;bafruip;lir;bam_2;lank;5;3;\n"
            + "12;2;-2;Target-only;6 words;kruis;fluis_1;viet;knaf;fluis_2;eidind;gruton;goeling;3;0;\n"
            + "13;2;-4;Target+Foil;5 words;hand;haps_1;harfbijg;doon;keeg;haps_2;vijnwel;;4;1;\n"
            + "14;2;-6;NoTarget;4 words;pop;lop;voorserm;muiland;fraal;kijn;;;0;0;\n"
            + "15;2;-8;Target-only;5 words;slak;slas_1;laanzan;halg;jirsel;slas_2;goe;;4;0;\n"
            + "16;2;-10;Target-only;3 words;tang;mang_1;lartmat;mang_2;bleeuw;;;;2;0;\n"
            + "17;2;-12;Target+Foil;4 words;hoed;koed_1;koegpaat;momp;koed_2;noger;;;3;1;\n"
            + "18;2;-14;Target-only;4 words;eend;oend_1;fonguin;laaps;oend_2;spunel;;;3;0;\n"
            + "19;2;-16;NoTarget;6 words;bloed;zwoed;liem;watse;kieg;firp;bioel;gebog;0;0;\n"
            + "20;2;-18;Target+Foil;3 words;bord;bops_1;bolgwap;bops_2;ziep;;;;2;1;\n"
            + "21;3;0;NoTarget;3 words;loep;luip;kleis;heerting;zwins;;;;0;0;\n"
            + "22;3;-2;Target+Foil;6 words;beest;baust_1;baaf;baundleu;foestvag;baust_2;tan;rornas;4;2;\n"
            + "23;3;-4;NoTarget;4 words;doek;deek;eerkuid;jor;vas;wijtoeg;;;0;0;\n"
            + "24;3;-6;Target-only;5 words;muis;muin_1;blan;muin_2;wemp;euzer;knekgel;;2;0;\n"
            + "25;3;-8;Target+Foil;5 words;hond;hogt_1;ontpad;holmdrins;hogt_2;toor;roog;;3;2;\n"
            + "26;3;-10;NoTarget;5 words;broer;troer;voortegt;brof;blook;spaag;afbuip;;0;0;\n"
            + "27;3;-12;NoTarget;5 words;blaas;draas;aanbag;smap;tetjel;gog;pag;;0;0;\n"
            + "28;3;-14;Target+Foil;4 words;brood;bried_1;snes;briepkimp;bried_2;jutleen;;;3;2;\n"
            + "29;3;-16;Target-only;4 words;spin;klin_1;schuik;klin_2;trauster;pozaat;;;2;0;\n"
            + "30;3;-18;NoTarget;6 words;sop;sor;raber;hieg;brots;wref;ledoer;meusel;0;0;;";

    public static String WORDFORM = "Order;Round;SNR;Condition;Length_list;Word;Target_nonword;Word1;Word2;Word3;Word4;Word5;Word6;Position_target;Position_foil\n"
            + "1;0;0;Target-only;4 words;schuip;schaap_1;wamoel;schaap_2;balstel;res;;;2;0\n"
            + "2;0;-6;NoTarget;3 words;tonk;tong_1;zandog;dijg;vets;;;;0;0\n"
            + "3;0;-10;Target+Foil;5 words;zarp;zalf_1;vleek;zanskolm;kelf;zalf_2;bolpes;;4;2\n"
            + "1;1;0;Target-only;6 words;tomp;touw_1;oormoel;karp;retnin;alveid;touw_2;penk;5;0\n"
            + "2;1;-2;NoTarget;3 words;smijk;smaak;veg;wieel;loor;;;;0;0\n"
            + "3;1;-4;Target-only;5 words;broon;kroon_1;warker;lepen;pluif;kroon_2;pir;;4;0\n"
            + "4;1;-6;Target+Foil;5 words;zan;man_1;dauk;andoer;mallalk;man_2;slon;;4;3\n"
            + "5;1;-8;NoTarget;4 words;loem;roem;salder;voes;guitel;swi;;;0;0\n"
            + "6;1;-10;Target+Foil;4 words;toort;taart_1;taalgmong;taart_2;veepsel;sies;;;2;1\n"
            + "7;1;-12;Target-only;3 words;kiers;kaars_1;hog;kaars_2;waga;;;;2;0\n"
            + "8;1;-14;NoTarget;5 words;gar;gat;lomel;strieg;snaan;sloog;rirte;;0;0\n"
            + "9;1;-16;Target+Foil;3 words;rom;bom_1;bofreun;bom_2;zamp;;;;2;1\n"
            + "10;1;-18;Target-only;4 words;hoei;haai_1;jerg;cigroon;haai_2;lordel;;;3;0\n"
            + "11;2;0;Target+Foil;6 words;bam;bad_1;wirums;mefdel;bafruip;lir;bad_2;lank;5;3\n"
            + "12;2;-2;Target-only;6 words;fluis;kruis_1;viet;knaf;kruis_2;eidind;gruton;goeling;3;0\n"
            + "13;2;-4;Target+Foil;5 words;haps;hand_1;harfbijg;doon;keeg;hand_2;vijnwel;;4;1\n"
            + "14;2;-6;NoTarget;4 words;lop;pop;voorserm;muiland;fraal;kijn;;;0;0\n"
            + "15;2;-8;Target-only;5 words;slas;slak_1;laanzan;halg;jirsel;slak_2;goe;;4;0\n"
            + "16;2;-10;Target-only;3 words;mang;tang_1;lartmat;tang_2;bleeuw;;;;2;0\n"
            + "17;2;-12;Target+Foil;4 words;koed;hoed_1;hoegpaat;momp;hoed_2;noger;;;3;1\n"
            + "18;2;-14;Target-only;4 words;oend;eend_1;fonguin;laaps;eend_2;spunel;;;3;0\n"
            + "19;2;-16;NoTarget;6 words;zwoed;bloed;liem;watse;kieg;firp;bioel;gebog;0;0\n"
            + "20;2;-18;Target+Foil;3 words;bops;bord_1;bolgwap;bord_2;ziep;;;;2;1\n"
            + "21;3;0;NoTarget;3 words;luip;loep;kleis;heerting;zwins;;;;0;0\n"
            + "22;3;-2;Target+Foil;6 words;baust;beest_1;baaf;beendleu;foestvag;beest_2;tan;rornas;4;2\n"
            + "23;3;-4;NoTarget;4 words;deek;doek;eerkuid;jor;vas;wijtoeg;;;0;0\n"
            + "24;3;-6;Target-only;5 words;muin;muis_1;blan;muis_2;wemp;euzer;knekgel;;2;0\n"
            + "25;3;-8;Target+Foil;5 words;hogt;hond_1;ontpad;holmdrins;hond_2;toor;roog;;3;2\n"
            + "26;3;-10;NoTarget;5 words;troer;broer;voortegt;brof;blook;spaag;afbuip;;0;0\n"
            + "27;3;-12;NoTarget;5 words;draas;blaas;aanbag;smap;tetjel;gog;pag;;0;0\n"
            + "28;3;-14;Target+Foil;4 words;bried;brood_1;snes;broopkimp;brood_2;jutleen;;;3;2\n"
            + "29;3;-16;Target-only;4 words;klin;spin_1;schuik;spin_2;trauster;pozaat;;;2;0\n"
            + "30;3;-18;NoTarget;6 words;sor;sop;raber;hieg;brots;wref;ledoer;meusel;0;0";
}
