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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.werkwoordenpool;

/**
 *
 * @author olhshk
 */
public class WerkwoordenCsv {

    public static String CSV = "trial;condition;verb;inflected_verb;target; nchar_target;syllabes_target;Zipf_freq_target;prevalence_target;Picture_left;Picture_right;position_target;distractor; nchar_distractor;syllabes_distractor;Zipf_freq_distractor;prevalence_distractor;wav_file;verb_onset;target_onset;timer;correct_response\n"
            + "1;practice_pred;onthullen;onthult;standbeeld;10;2;3_67;1_00;flashlight.jpg;statue.jpg;right;zaklamp;7;2;3_71;1_00;onthult_standbeeld.wav;1431;3810;1569;1\n"
            + "2;practice_non-pred;schilderen;schildert;bril;4;1;4_39;1_00;glasses.jpg;snake.jpg;left;slang;5;1;4_33;1_00;schildert_bril.wav;1440;4216;1560;2\n"
            + "3;non-pred;pakken;pakt;hanger;6;2;3_26;0_98;muffin.jpg;hanger.jpg;right;muffin;6;2;3_26;0_98;pakt_hanger.wav;1496;3933;1504;1\n"
            + "4;pred;planten;plant;bloem;5;1;4_13;1_00;injectionneedle.jpg;flower.jpg;right;spuit;5;1;3_99;1_00;plant_bloem.wav;1311;3915;1689;1\n"
            + "5;non-pred;beschrijven;beschrijft;uil;3;1;3_50;1_00;owl.jpg;drill.jpg;left;boor;4;1;3_54;1_00;beschrijft_uil.wav;1439;4124;1561;2\n"
            + "6;pred;bakken;bakt;taart;5;1;4_50;1_00;mountain.jpg;cake.jpg;right;berg;4;1;4_54;1_00;bakt_taart.wav;1384;3972;1616;1\n"
            + "7;non-pred;verkopen;verkoopt;overhemd;8;3;3_83;0_99;shirt.jpg;lipstick.jpg;left;lippenstift;11;3;3_81;1_00;verkoopt_overhemd.wav;1337;4361;1663;2\n"
            + "8;non-pred;verbergen;verbergt;ballon;6;2;3_72;1_00;balloon.jpg;laptop.jpg;left;laptop;6;2;3_77;1_00;verbergt_ballon.wav;1225;4136;1775;2\n"
            + "9;pred;bekleden;bekleedt;bank;4;1;4_96;1_00;ship.jpg;couch.jpg;right;schip;5;1;5_06;1_00;bekleedt_bank.wav;1512;4344;1488;1\n"
            + "10;pred;openen;opent;deur;4;1;5_39;1_00;suit.jpg;door.jpg;right;pak;3;1;5_50;1_00;opent_deur.wav;1517;4155;1483;1\n"
            + "11;non-pred;stelen;steelt;pen;3;1;4_34;1_00;pen.jpg;clock.jpg;left;klok;4;1;4_38;1_00;steelt_pen.wav;1448;4330;1552;2\n"
            + "12;non-pred;betalen;betaalt;frisbee;7;2;3_26;0_99;drum.jpg;frisbee.jpg;right;trommel;7;2;3_27;1_00;betaalt_frisbee.wav;1407;4234;1593;1\n"
            + "13;pred;persen;perst;sinaasappel;11;4;3_25;0_98;orange.jpg;typewriter.jpg;left;typemachine;11;4;3_33;0_99;perst_sinaasappel.wav;1417;3739;1583;2\n"
            + "14;non-pred;overhandigen;overhandigt;veer;4;1;3_54;1_00;basket.jpg;feather.jpg;right;mand;4;1;3_63;1_00;overhandigt_veer.wav;1247;3959;1753;1\n"
            + "15;pred;vangen;vangt;vis;3;1;4_70;1_00;fish.jpg;tent.jpg;left;tent;4;1;4_61;1_00;vangt_vis.wav;1285;3796;1715;2\n"
            + "16;pred;behangen;behangt;muur;4;1;4_83;1_00;train.jpg;wall.jpg;right;trein;5;1;4_86;0_99;behangt_muur.wav;1485;4237;1515;1\n"
            + "17;non-pred;zoeken;zoekt;fluit;5;1;3_87;1_00;scissors.jpg;flute.jpg;right;schaar;6;1;3_80;0_99;zoekt_fluit.wav;1398;3748;1602;1\n"
            + "18;non-pred;ontvangen;ontvangt;sjaal;5;1;3_73;0_99;scarf.jpg;candle.jpg;left;kaars;5;1;3_77;1_00;ontvangt_sjaal.wav;1357;3794;1643;2\n"
            + "19;pred;passen;past;broek;5;1;4_83;1_00;foot.jpg;pants.jpg;right;voet;4;1;4_71;1_00;past_broek.wav;1367;3498;1633;1\n"
            + "20;non-pred;kopen;koopt;spiegel;7;2;4_44;1_00;mirror.jpg;pizza.jpg;left;pizza;5;2;4_39;1_00;koopt_spiegel.wav;1323;3524;1677;2\n"
            + "21;pred;scheren;scheert;baard;5;1;4_07;1_00;sweater.jpg;beard.jpg;right;trui;4;1;4_07;1_00;scheert_baard.wav;1243;3809;1757;1\n"
            + "22;non-pred;verstoppen;verstopt;muts;4;1;3_65;1_00;cap.jpg;saw.jpg;left;zaag;4;1;3_55;0_99;verstopt_muts.wav;1106;3538;1894;2\n"
            + "23;non-pred;tekenen;tekent;magneet;7;2;3_20;1_00;magnet.jpg;stoplight.jpg;left;stoplicht;9;2;3_21;1_00;tekent_magneet.wav;1609;4314;1391;2\n"
            + "24;pred;dekken;dekt;tafel;5;2;4_92;1_00;key.jpg;table.jpg;right;sleutel;7;2;4_91;1_00;dekt_tafel.wav;1565;4130;1435;1\n"
            + "25;non-pred;zien;ziet;kind;4;1;5_52;1_00;child.jpg;bell.jpg;left;bel;3;1;5_49;1_00;ziet_kind.wav;1271;3733;1729;2\n"
            + "26;pred;hechten;hecht;wond;4;1;4_15;1_00;flag.jpg;wound.jpg;right;vlag;4;1;4_25;1_00;hecht_wond.wav;1253;3602;1747;1\n"
            + "27;non-pred;kiezen;kiest;zeilboot;8;2;3_14;1_00;sailboat.jpg;jacket.jpg;left;colbert;7;2;3_17;0_99;kiest_zeilboot.wav;1198;3643;1802;2\n"
            + "28;non-pred;lenen;leent;bijl;4;1;3_97;1_00;ax.jpg;pan.jpg;left;pan;3;1;3_97;1_00;leent_bijl.wav;1170;3621;1830;2\n"
            + "29;pred;vervangen;vervangt;lamp;4;1;4_14;0_99;shovel.jpg;lightbulb.jpg;right;schop;5;1;4_21;1_00;vervangt_lamp.wav;1046;3793;1954;1\n"
            + "30;pred;likken;likt;ijsje;5;2;3_87;1_00;icecream.jpg;bucket.jpg;left;emmer;5;2;3_83;1_00;likt_ijsje.wav;1003;3693;1997;2\n"
            + "31;non-pred;bewaken;bewaakt;gitaar;6;2;4_06;1_00;robot.jpg;guitar.jpg;right;robot;5;2;4_09;1_00;bewaakt_gitaar.wav;914;3734;2086;1\n"
            + "32;pred;verwisselen;verwisselt;band;4;1;4_90;1_00;tube.jpg;newspaper.jpg;left;krant;5;1;4_76;1_00;verwisselt_band.wav;1054;3646;1946;2\n"
            + "33;pred;winnen;wint;beker;5;2;3_94;1_00;frog.jpg;cup.jpg;right;kikker;6;2;3_92;1_00;wint_beker.wav;1087;3290;1913;1\n"
            + "34;non-pred;verliezen;verliest;pijp;4;1;4_14;0_99;pipe.jpg;egg.jpg;left;ei;2;1;4_21;1_00;verliest_pijp.wav;1017;3590;1983;2\n"
            + "35;non-pred;filmen;filmt;sigaar;6;2;3_99;1_00;violin.jpg;cigar.jpg;right;viool;5;2;3_63;1_00;filmt_sigaar.wav;1217;3541;1783;1\n"
            + "36;pred;smeren;smeert;boterham;8;3;3_78;1_00;match.jpg;sandwich.jpg;right;lucifer;7;3;3_81;1_00;smeert_boterham.wav;1162;4051;1838;1\n"
            + "37;pred;dragen;draagt;tas;3;1;4_77;1_00;bag.jpg;bus.jpg;left;bus;3;1;4_81;1_00;draagt_tas.wav;1049;3691;1951;2\n"
            + "38;non-pred;bekijken;bekijkt;stethoscoop;11;3;2_70;0_97;stethoscope.jpg;mushroom.jpg;left;paddestoel;10;3;2_76;0_96;bekijkt_stethoscoop.wav;1359;4166;1641;2\n"
            + "39;pred;drinken;drinkt;biertje;7;2;4_54;1_00;suitcase.jpg;beer.jpg;right;koffer;6;2;4_53;1_00;drinkt_biertje.wav;1386;3933;1614;1\n"
            + "40;non-pred;fotograferen;fotografeert;zwaard;6;1;4_57;1_00;sword.jpg;dress.jpg;left;jurk;4;1;4_75;1_00;fotografeert_zwaard.wav;1024;4147;1976;2\n"
            + "41;pred;ondertekenen;ondertekent;contract;8;2;4_55;1_00;bird.jpg;contract.jpg;right;vogel;5;2;4_51;1_00;ondertekent_contract.wav;1336;4258;1664;1\n"
            + "42;pred;arresteren;arresteert;dief;4;1;4_48;1_00;thief.jpg;monkey.jpg;left;aap;3;1;4_46;0_99;arresteert_dief.wav;1227;4098;1773;2";

}
