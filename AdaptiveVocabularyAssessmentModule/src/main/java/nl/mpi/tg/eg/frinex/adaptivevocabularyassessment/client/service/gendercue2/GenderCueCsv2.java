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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.gendercue2;

/**
 *
 * @author olhshk
 */
public class GenderCueCsv2 {

    public static String CSV = "Trial;Condition;Item;Gender;Log_frequency_target;Log_frequency_distractor;Syllables_target;Syllables_distractor;Picture_left;Picture_right;Position_target;Wav;determiner_onset;determiner_offset;determiner_duration;target_onset;target_offset;target_duration;Timer;Correct_response\n"
            + "1;practice;de_fles;de;4_66;4_72;1;1;slot_png;fles_png;right;de_fles_wav;1889_017941;2133_410765;244;4097_519172;4654_354318;557;1110_982059;1\n"
            + "2;practice;het_wiel;het;3_85;3_97;1;1;wiel_png;haai_png;left;het_wiel_wav;1945_940744;2272_924759;327;4123_210832;4615_697924;492;1054_059256;2\n"
            + "3;non-pred;een_tandenborstel;een;3_62;4_85;4;1;toothbrush_png;window_png;left;een_tandenborstel_wav;1857_165366;2178_836998;322;4412_669902;5200_351558;788;1142_834634;2\n"
            + "4;non-pred;een_spin;een;3_89;4_44;1;1;plate_png;spin_png;right;een_spin_wav;1802_648505;2122_122283;319;3963_344019;4464_9257;502;1197_351495;1\n"
            + "5;pred;de_fontein;de;3_60;2_85;2;3;fountain_png;horseshoe_png;left;de_fontein_wav;2046_692613;2282_17697;235;4078_736225;4746_672811;668;953_3073875;2\n"
            + "6;pred;het_strijkijzer;het;2_85;4_05;3;1;mouse_png;iron_png;right;het_strijkijzer_wav;1819_610349;2166_793678;347;4047_050204;5018_295423;971;1180_389651;1\n"
            + "7;pred;het_kasteel;het;4_44;2_70;2;2;slide_png;castle_png;right;het_kasteel_wav;1829_857497;2185_084872;355;4087_555452;4648_430242;561;1170_142503;1\n"
            + "8;non-pred;een_vleugel;een;3_95;2_36;2;2;wing_png;palet_png;left;een_vleugel_wav;1846_626096;2174_7343;328;4066_123022;4749_660558;684;1153_373904;2\n"
            + "9;non-pred;een_bezem;een;3_58;3_08;2;2;antlers_png;broom_png;right;een_bezem_wav;1988_359202;2328_698499;340;4399_72229;4990_317058;591;1011_640798;1\n"
            + "10;pred;de_hoed;de;4_56;4_76;1;1;hat_png;glass_png;left;de_hoed_wav;1936_014965;2175_424061;239;4137_40123;4551_853698;414;1063_985035;2\n"
            + "11;non-pred;een_gordijn;een;3_65;3_88;2;2;gordijn_png;backpack_png;left;een_gordijn_wav;1815_749478;2113_244715;297;3929_793998;4561_273073;631;1184_250522;2\n"
            + "12;pred;de_fiets;de;4_34;4_40;1;1;ear_png;bicycle_png;right;de_fiets_wav;1923_696341;2176_161555;252;4039_500026;4563_892543;524;1076_303659;1\n"
            + "13;non-pred;een_weegschaal;een;3_26;3_84;2;2;scale_png;saddle_png;left;een_weegschaal_wav;1859_352361;2178_041191;319;4184_628146;4881_531798;697;1140_647639;2\n"
            + "14;pred;de_piano;de;4_15;4_39;3;2;pig_png;piano_png;right;de_piano_wav;1889_413883;2137_35409;248;4209_017058;4767_081456;558;1110_586117;1\n"
            + "15;non-pred;een_piramide;een;3_44;4_42;4;1;rope_png;pyramid_png;right;een_piramide_wav;1935_622491;2274_719965;339;4225_730445;4870_694572;645;1064_377509;1\n"
            + "16;pred;het_kruis;het;4_32;4_72;1;1;cross_png;ring_png;left;het_kruis_wav;1805_57879;2150_39952;345;3943_415378;4541_767665;598;1194_42121;2\n"
            + "17;pred;het_brood;het;4_53;3_91;1;1;goat_png;bread_png;right;het_brood_wav;1825_270816;2138_884141;314;4095_1494;4613_449284;518;1174_729184;1\n"
            + "18;non-pred;een_koelkast;een;4_17;4_47;2;2;refrigerator_png;present_png;left;een_koelkast_wav;1947_789183;2239_004838;291;4247_338299;4945_264618;698;1052_210817;2\n"
            + "19;pred;de_sneeuwpop;de;3_03;2_96;2;2;racket_png;snowman_png;right;de_sneeuwpop_wav;1788_052294;2014_902224;227;4099_723733;4812_456387;713;1211_947706;1\n"
            + "20;non-pred;een_krokodil;een;3_69;3_70;3;2;anchor_png;alligator_png;right;een_krokodil_wav;1797_437412;2105_956176;309;4091_072143;4801_619161;711;1202_562588;1\n"
            + "21;non-pred;een_neus;een;4_85;3_74;1;2;nose_png;pencil_png;left;een_neus_wav;1914_010297;2232_699127;319;4225_943981;4795_140109;569;1085_989703;2\n"
            + "22;pred;de_appel;de;4_01;4_16;2;1;bone_png;apple_png;right;de_appel_wav;1862_992863;2127_146281;264;4134_41249;4581_296807;447;1137_007137;1\n"
            + "23;pred;de_auto;de;5_66;4_36;2;1;car_png;fence_png;left;de_auto_wav;1931_953269;2207_794891;276;4110_735517;4657_642522;547;1068_046731;2\n"
            + "24;non-pred;een_mes;een;4_67;3_39;1;2;knife_png;penguin_png;left;een_mes_wav;1810_997555;2134_396072;323;4195_503498;4684_085625;489;1189_002445;2\n"
            + "25;non-pred;een_roos;een;4_07;3_82;1;1;sheep_png;rose_png;right;een_roos_wav;1781_28294;2078_100038;297;4002_914842;4671_317817;668;1218_71706;1\n"
            + "26;pred;het_masker;het;4_28;4_72;2;1;mask_png;tree_png;left;het_masker_wav;1866_771713;2213_889132;347;4064_874788;4614_510955;550;1133_228287;2\n"
            + "27;pred;het_oog;het;4_84;3_38;1;1;eye_png;snail_png;left;het_oog_wav;1724_449462;2067_67473;343;3875_954766;4335_011269;459;1275_550538;2\n"
            + "28;non-pred;een_kanon;een;3_79;3_47;2;2;beaver_png;cannon_png;right;een_kanon_wav;1866_913425;2169_903298;303;4107_829953;4568_594345;461;1133_086575;1\n"
            + "29;pred;de_vork;de;3_72;5_29;1;1;fork_png;heart_png;left;de_vork_wav;1980_756993;2231_155359;250;4258_2564;4846_994093;589;1019_243007;2\n"
            + "30;non-pred;een_stuur;een;5_06;3_49;1;2;steeringwheel_png;zebra_png;left;een_stuur_wav;1838_129632;2125_420548;287;4109_143107;4658_670751;550;1161_870368;2\n"
            + "31;pred;het_vliegtuig;het;4_95;2_51;2;2;wateringcan_png;airplane_png;right;het_vliegtuig_wav;1812_871399;2157_335018;344;3981_316679;4762_644344;781;1187_128601;1\n"
            + "32;non-pred;een_konijn;een;4_28;4_77;2;3;radio_png;rabbit_png;right;een_konijn_wav;1872_250597;2208_993228;337;4174_773149;4715_122853;540;1127_749403;1\n"
            + "33;pred;het_graf;het;4_54;4_91;1;1;grave_png;ball_png;left;het_graf_wav;1893_853777;2212_082852;318;4247_138529;4798_594979;551;1106_146223;2\n"
            + "34;non-pred;een_ei;een;4_21;3_93;1;1;branch_png;ei_png;right;een_ei_wav;1801_742243;2087_463262;286;4199_936754;4570_271046;370;1198_257757;1\n"
            + "35;non-pred;een_paard;een;4_92;4_58;1;1;horse_png;box_png;left;een_paard_wav;1814_114709;2120_636847;307;4397_898219;4840_2067;442;1185_885291;2\n"
            + "36;pred;de_spijker;de;3_55;4_05;2;1;nest_png;nail_png;right;de_spijker_wav;1846_138435;2099_676593;254;3936_454578;4597_673903;661;1153_861565;1\n"
            + "37;pred;het_blik;het;4_56;4_71;1;1;can_png;chair_png;left;het_blik_wav;1933_892001;2263_773832;330;4389_051857;4772_502793;383;1066_107999;2\n"
            + "38;non-pred;een_aquarium;een;3_46;3_82;4;2;glove_png;fishtank_png;right;een_aquarium_wav;1772_106086;2102_569134;330;4004_135412;4745_102916;741;1227_893914;1\n"
            + "39;pred;de_trompet;de;3_43;4_45;2;3;trumpet_png;watch_png;left;de_trompet_wav;1868_453176;2147_894614;279;4080_884984;4674_62809;594;1131_546824;2\n"
            + "40;non-pred;een_boek;een;5_18;3_85;1;1;book_png;arrow_png;left;een_boek_wav;1805_490862;2146_773464;341;4121_702273;4527_883326;406;1194_509138;2\n"
            + "41;non-pred;een_hert;een;3_79;4_36;1;1;cheese_png;deer_png;right;een_hert_wav;1847_506975;2152_066744;305;4090_383745;4577_018589;487;1152_493025;1\n"
            + "42;pred;het_balkon;het;3_82;3_64;2;1;zipper_png;balcony_png;right;het_balkon_wav;1870_757496;2194_787011;324;4064_544267;4642_415355;578;1129_242504;1";

}
