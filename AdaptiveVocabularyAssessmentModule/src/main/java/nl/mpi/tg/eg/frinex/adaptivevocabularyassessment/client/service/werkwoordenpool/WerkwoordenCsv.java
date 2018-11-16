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

    

    public static String CSV_PICTURES_AUDIO = "trial;condition;verb;inflected_verb;target; nchar_target;syllabes_target; SUBTLEXWF_target;log_target;prevalence_target;picture_target;position_target;distractor; nchar_distractor;syllabes_distractor; SUBTLEXWF_distractor;log_distractor;prevalence_distractor;picture_distractor;wav_file;verb_onset;target_onset;timer_ms;correct_response\n" +
"1;practice_pred;onthullen;onthult;standbeeld;10;2;4,64;3,666517981;0,996604356;statue.jpg;right;zaklamp;7;2;5,08;3,705863712;0,999528049;flashlight.jpg;onthult_standbeeld.wav;1734,791625;4622,401389;1265;right\n" +
"2;practice_non-pred;schilderen;schildert;bril;4;1;24,49;4,388988785;0,999573477;glasses.jpg;left;slang;5;1;21,59;4,334252642;0,999598743;snake.jpg;schildert_bril.wav;1741,158184;5113,250885;1259;left\n" +
"3;non-pred;pakken;pakt;hanger;6;2;1,81;3,257678575;0,983163104;hanger.jpg;right;muffin;6;2;1,81;3,257678575;0,982336332;muffin.jpg;pakt_hanger.wav;1813,972801;4772,257794;1186;right\n" +
"4;pred;planten;plant;bloem;5;1;13,49;4,13001195;0,999662587;flower.jpg;right;spuit;5;1;9,79;3,990782692;0,996463923;injectionneedle.jpg;plant_bloem.wav;1589,958033;4794,296856;1410;right\n" +
"5;non-pred;beschrijven;beschrijft;uil;3;1;3,13;3,495544338;0,999516061;owl.jpg;left;boor;4;1;3,45;3,537819095;0,9963946;drill.jpg;beschrijft_uil.wav;1743,954333;4998,845876;1256;left\n" +
"6;pred;bakken;bakt;taart;5;1;31,35;4,496237545;0,99961864;cake.jpg;right;berg;4;1;34,3;4,53529412;0,999656066;mountain.jpg;bakt_taart.wav;1665,750588;4815,209596;1334;right\n" +
"7;non-pred;verkopen;verkoopt;overhemd;8;3;6,75;3,829303773;0,99491487;shirt.jpg;left;lippenstift;11;3;6,47;3,810904281;0,99669279;lipstick.jpg;verkoopt_overhemd.wav;1608,006172;5289,4941;1392;left\n" +
"8;non-pred;verbergen;verbergt;ballon;6;2;5,28;3,722633923;0,999499153;balloon.jpg;left;laptop;6;2;5,88;3,769377326;0,995703868;laptop.jpg;verbergt_ballon.wav;1454,859707;5022,536634;1545;left\n" +
"9;pred;bekleden;bekleedt;bank;4;1;91,91;4,963362766;0,998167059;couch.jpg;right;schip;5;1;115,28;5,061753968;0,998614053;ship.jpg;bekleedt_bank.wav;1841,531735;5301,100445;1158;right\n" +
"10;pred;openen;opent;deur;4;1;247,48;5,393540107;0,998332041;door.jpg;right;pak;3;1;313,2;5,495821753;0,997384174;suit.jpg;opent_deur.wav;1839,469961;5038,539914;1161;right\n" +
"11;non-pred;stelen;steelt;pen;3;1;21,66;4,335658452;0,999618383;pen.jpg;left;klok;4;1;23,9;4,378397901;0,999601352;clock.jpg;steelt_pen.wav;1756,787932;5250,341803;1243;left\n" +
"12;non-pred;betalen;betaalt;frisbee;7;2;1,83;3,26245109;0,985307249;frisbee.jpg;right;trommel;7;2;1,85;3,267171728;0,999568314;drum.jpg;betaalt_frisbee.wav;1603,323182;5132,100273;1397;right\n" +
"13;pred;persen;perst;sinaasappel;11;4;1,76;3,245512668;0,981619704;orange.jpg;left;typemachine;11;4;2,15;3,33243846;0,990836677;typewriter.jpg;perst_sinaasappel.wav;1716,57753;4526,861652;1283;left\n" +
"14;non-pred;overhandigen;overhandigt;veer;4;1;3,43;3,53529412;0,996335062;feather.jpg;right;mand;4;1;4,3;3,633468456;0,999595427;basket.jpg;overhandigt_veer.wav;1508,843515;4796,867816;1491;right\n" +
"15;pred;vangen;vangt;vis;3;1;50,08;4,69966432;0,999632584;fish.jpg;left;tent;4;1;40,93;4,612041745;0,99892679;tent.jpg;vangt_vis.wav;1541,803045;4594,771335;1458;left\n" +
"16;pred;behangen;behangt;muur;4;1;66,89;4,825361196;0,999617463;wall.jpg;right;trein;5;1;73,15;4,86421433;0,993522952;train.jpg.jpg;behangt_muur.wav;1684,654305;5133,937532;1315;right\n" +
"17;non-pred;zoeken;zoekt;fluit;5;1;7,39;3,868644438;0,997469908;flute.jpg;right;schaar;6;1;6,36;3,803457116;0,993628681;scissors.jpg;zoekt_fluit.wav;1684,749926;4535,753006;1315;right\n" +
"18;non-pred;ontvangen;ontvangt;sjaal;5;1;5,31;3,725094521;0,991341825;scarf.jpg;left;kaars;5;1;5,88;3,769377326;0,99594592;candle.jpg;ontvangt_sjaal.wav;1646,840134;4591,835629;1353;left\n" +
"19;pred;passen;past;broek;5;1;67,28;4,827885983;0,999476439;pants.jpg;right;voet;4;1;50,81;4,705949195;0,996637582;foot.jpg;past_broek.wav;1657,935144;4270,868641;1342;right\n" +
"20;non-pred;kopen;koopt;spiegel;7;2;27,44;4,438384107;0,999571151;mirror.jpg;left;pizza;5;2;24,38;4,387033701;0,998289428;pizza.jpg;koopt_spiegel.wav;1601,847549;4267,845044;1398;left\n" +
"21;pred;scheren;scheert;baard;5;1;11,64;4,06595298;0,999541553;beard.jpg;right;trui;4;1;11,62;4,065206128;0,998112307;sweater.jpg;scheert_baard.wav;1505,22699;4646,657043;1495;right\n" +
"22;non-pred;verstoppen;verstopt;muts;4;1;4,46;3,649334859;0,998764606;cap.jpg;left;zaag;4;1;3,54;3,549003262;0,99403697;saw.jpg;verstopt_muts.wav;1340,350128;4316,709819;1660;left\n" +
"23;non-pred;tekenen;tekent;magneet;7;2;1,58;3,198657087;0,998758314;magnet.jpg;left;stoplicht;9;2;1,62;3,209515015;0,99668267;stoplight.jpg;tekent_magneet.wav;1953,362659;5219,513847;1047;left\n" +
"24;pred;dekken;dekt;tafel;5;2;83,4;4,921166051;0,998489953;table.jpg;right;sleutel;7;2;80,7;4,906873535;0,9985757;key.jpg;dekt_tafel.wav;1895,799025;5007,713288;1104;right\n" +
"25;non-pred;zien;ziet;kind;4;1;333,3;5,522835314;0,999259704;child.jpg;left;bel;3;1;311,67;5,493695001;0,996352808;bell.jpg;ziet_kind.wav;1545,615754;4524,030834;1454;left\n" +
"26;pred;hechten;hecht;wond;4;1;14,02;4,146748014;0,997070522;wound.jpg;right;vlag;4;1;17,79;4,250175948;0,995817157;flag.jpg;hecht_wond.wav;1512,802561;4370,694913;1487;right\n" +
"27;non-pred;kiezen;kiest;zeilboot;8;2;1,37;3,136720567;0,997643259;sailboat.jpg;left;colbert;7;2;1,49;3,173186268;0,992624149;jacket.jpg;kiest_zeilboot.wav;1452,508058;4421,061017;1547;left\n" +
"28;non-pred;lenen;leent;bijl;4;1;9,26;3,966610987;0,999272858;ax.jpg;left;pan;3;1;9,38;3,972202838;0,99622373;pan.jpg;leent_bijl.wav;1418,204116;4382,419099;1582;left\n" +
"29;pred;vervangen;vervangt;lamp;4;1;13,88;4,142389466;0,994091452;lightbulb.jpg;right;schop;5;1;16,19;4,209246849;0,997071093;shovel.jpg;vervangt_lamp.wav;1253,521019;4558,300738;1746;right\n" +
"30;pred;likken;likt;ijsje;5;2;7,43;3,870988814;0,997409493;icecream.jpg;left;emmer;5;2;6,72;3,827369273;0,996607705;bucket.jpg;likt_ijsje.wav;1201,16065;4477,871906;1799;left\n" +
"31;non-pred;bewaken;bewaakt;gitaar;6;2;11,46;4,059184618;0,997865564;guitar.jpg;right;robot;5;2;12,39;4,093071306;0,997193109;robot.jpg;bewaakt_gitaar.wav;1086,051839;4528,671823;1914;right\n" +
"32;pred;verwisselen;verwisselt;band;4;1;79,28;4,899163641;0,999581298;tube.jpg;left;krant;5;1;58,2;4,764922985;0,998333328;newspaper.jpg;verwisselt_band.wav;1266,568661;4415,811813;1733;left\n" +
"33;pred;winnen;wint;beker;5;2;8,78;3,943494516;0,998206061;cup.jpg;right;kikker;6;2;8,23;3,915399835;0,99869903;frog.jpg;wint_beker.wav;1245,281467;3989,080008;1755;right\n" +
"34;non-pred;verliezen;verliest;pijp;4;1;13,81;4,140193679;0,993942651;pipe.jpg;left;ei;2;1;16,19;4,209246849;0,995194099;egg.jpg;verliest_pijp.wav;1219,90788;4351,120725;1780;left\n" +
"35;non-pred;filmen;filmt;sigaar;6;2;9,83;3,992553518;0,99961048;cigar.jpg;right;viool;5;2;4,3;3,633468456;0,998417965;violin.jpg;filmt_sigaar.wav;1460,241312;4294,442854;1540;right\n" +
"36;pred;smeren;smeert;boterham;8;3;6,08;3,783903579;0,997402687;sandwich.jpg;right;lucifer;7;3;6,45;3,809559715;0,998616143;match.jpg;smeert_boterham.wav;1411,8463;4914,233565;1588;right\n" +
"37;pred;dragen;draagt;tas;3;1;58,36;4,766115283;0,997822975;bag.jpg;left;bus;3;1;64,83;4,811776022;0,999710824;bus.jpg;draagt_tas.wav;1271,080414;4472,373913;1729;left\n" +
"38;non-pred;bekijken;bekijkt;stethoscoop;11;3;0,5;2,698970004;0,971594932;stethoscope.jpg;left;paddestoel;10;3;0,57;2,755874856;0,962149657;mushroom.jpg;bekijkt_stethoscoop.wav;1642,716472;5048,526335;1357;left\n" +
"39;pred;drinken;drinkt;biertje;7;2;34,9;4,542825427;0,999450185;beer.jpg;right;koffer;6;2;33,87;4,529815197;0,998376893;suitcase.jpg;drinkt_biertje.wav;1680,914106;4765,68951;1319;right\n" +
"40;non-pred;fotograferen;fotografeert;zwaard;6;1;37,48;4,573799582;0,998784167;sword.jpg;left;jurk;4;1;55,75;4,746244872;0,998399082;dress.jpg;fotografeert_zwaard.wav;1233,693131;5024,280026;1766;left\n" +
"41;pred;ondertekenen;ondertekent;contract;8;2;35,49;4,550105999;0,99614875;contract.jpg;right;vogel;5;2;32,27;4,508798965;0,99852956;bird.jpg;ondertekent_contract.wav;1615,179961;5138,993693;1385;right\n" +
"42;pred;arresteren;arresteert;dief;4;1;29,96;4,476541809;0,99667072;thief.jpg;left;aap;3;1;28,56;4,455758203;0,994830171;monkey.jpg;arresteert_dief.wav;1507,172947;4961,929441;1493;left\n";
}
