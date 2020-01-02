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

    public static String CSV_STRING = "Phase;Item;Cue;Stimulus;Position_cue;Position_stimulus;Correct_response;Fixation_point_interval_ms\n"
            + "Practice;1;square;arrowup;left;right;up;2448\n"
            + "Practice;2;square;arrowup;right;left;up;1716\n"
            + "Practice;3;square;arrowleft;left;right;left;1507\n"
            + "Practice;4;square;arrowright;right;left;right;2077\n"
            + "Practice;5;square;arrowleft;right;left;left;2557\n"
            + "Practice;6;square;arrowright;left;right;right;2153\n"
            + "Practice;7;square;arrowup;right;left;up;2682\n"
            + "Practice;8;square;arrowup;right;left;up;1514\n"
            + "Practice;9;square;arrowright;left;right;right;1425\n"
            + "Practice;10;square;arrowleft;right;left;left;2040\n"
            + "Practice;11;square;arrowup;left;right;up;2724\n"
            + "Practice;12;square;arrowright;right;left;right;1276\n"
            + "Practice;13;square;arrowup;right;left;up;1977\n"
            + "Practice;14;square;arrowright;left;right;right;1174\n"
            + "Practice;15;square;arrowright;left;right;right;2160\n"
            + "Practice;16;square;arrowleft;right;left;left;1699\n"
            + "Practice;17;square;arrowright;left;right;right;2907\n"
            + "Practice;18;square;arrowleft;right;left;left;1023\n"
            + "Practice;19;square;arrowleft;left;right;left;1305\n"
            + "Practice;20;square;arrowup;left;right;up;1641\n"
            + "Practice;21;square;arrowleft;right;left;left;2975\n"
            + "Practice;22;square;arrowright;left;right;right;1099\n"
            + "Main;1;square;arrowleft;right;left;left;2753\n"
            + "Main;2;square;arrowright;left;right;right;2075\n"
            + "Main;3;square;arrowleft;left;right;left;1930\n"
            + "Main;4;square;arrowright;right;left;right;1545\n"
            + "Main;5;square;arrowup;right;left;up;1630\n"
            + "Main;6;square;arrowup;left;right;up;1989\n"
            + "Main;7;square;arrowright;left;right;right;1677\n"
            + "Main;8;square;arrowup;right;left;up;2157\n"
            + "Main;9;square;arrowright;left;right;right;2650\n"
            + "Main;10;square;arrowleft;right;left;left;1135\n"
            + "Main;11;square;arrowright;right;left;right;1455\n"
            + "Main;12;square;arrowleft;left;right;left;2104\n"
            + "Main;13;square;arrowup;left;right;up;1607\n"
            + "Main;14;square;arrowleft;right;left;left;1798\n"
            + "Main;15;square;arrowright;left;right;right;1309\n"
            + "Main;16;square;arrowleft;right;left;left;1008\n"
            + "Main;17;square;arrowup;left;right;up;2248\n"
            + "Main;18;square;arrowright;left;right;right;1878\n"
            + "Main;19;square;arrowup;right;left;up;1861\n"
            + "Main;20;square;arrowleft;right;left;left;2702\n"
            + "Main;21;square;arrowright;left;right;right;2471\n"
            + "Main;22;square;arrowleft;right;left;left;2526\n"
            + "Main;23;square;arrowup;left;right;up;1892\n"
            + "Main;24;square;arrowup;right;left;up;1232\n"
            + "Main;25;square;arrowright;right;left;right;1234\n"
            + "Main;26;square;arrowup;left;right;up;1272\n"
            + "Main;27;square;arrowup;right;left;up;1558\n"
            + "Main;28;square;arrowleft;left;right;left;1500\n"
            + "Main;29;square;arrowright;left;right;right;1970\n"
            + "Main;30;square;arrowup;right;left;up;1650\n"
            + "Main;31;square;arrowright;left;right;right;2062\n"
            + "Main;32;square;arrowleft;left;right;left;1270\n"
            + "Main;33;square;arrowright;right;left;right;2067\n"
            + "Main;34;square;arrowup;right;left;up;2088\n"
            + "Main;35;square;arrowright;left;right;right;2543\n"
            + "Main;36;square;arrowleft;left;right;left;1037\n"
            + "Main;37;square;arrowleft;right;left;left;1207\n"
            + "Main;38;square;arrowright;left;right;right;1747\n"
            + "Main;39;square;arrowleft;right;left;left;2768\n"
            + "Main;40;square;arrowleft;right;left;left;2918\n"
            + "Main;41;square;arrowup;left;right;up;1632\n"
            + "Main;42;square;arrowleft;left;right;left;2568\n"
            + "Main;43;square;arrowleft;right;left;left;2991\n"
            + "Main;44;square;arrowright;right;left;right;1560\n"
            + "Main;45;square;arrowleft;left;right;left;2632\n"
            + "Main;46;square;arrowup;right;left;up;2192\n"
            + "Main;47;square;arrowleft;left;right;left;1831\n"
            + "Main;48;square;arrowleft;left;right;left;1132\n"
            + "Main;49;square;arrowup;right;left;up;2739\n"
            + "Main;50;square;arrowleft;right;left;left;2024\n"
            + "Main;51;square;arrowright;left;right;right;1336\n"
            + "Main;52;square;arrowright;left;right;right;2208\n"
            + "Main;53;square;arrowup;right;left;up;2856\n"
            + "Main;54;square;arrowleft;left;right;left;1375\n"
            + "Main;55;square;arrowright;right;left;right;2903\n"
            + "Main;56;square;arrowleft;right;left;left;2392\n"
            + "Main;57;square;arrowup;left;right;up;1458\n"
            + "Main;58;square;arrowright;left;right;right;1947\n"
            + "Main;59;square;arrowright;right;left;right;1255\n"
            + "Main;60;square;arrowleft;left;right;left;1455\n"
            + "Main;61;square;arrowleft;right;left;left;1273\n"
            + "Main;62;square;arrowup;left;right;up;2729\n"
            + "Main;63;square;arrowup;left;right;up;2918\n"
            + "Main;64;square;arrowleft;right;left;left;2766\n"
            + "Main;65;square;arrowleft;left;right;left;1572\n"
            + "Main;66;square;arrowright;left;right;right;1500\n"
            + "Main;67;square;arrowright;right;left;right;1735\n"
            + "Main;68;square;arrowup;left;right;up;2455\n"
            + "Main;69;square;arrowleft;left;right;left;1662\n"
            + "Main;70;square;arrowup;right;left;up;1127\n"
            + "Main;71;square;arrowright;right;left;right;2020\n"
            + "Main;72;square;arrowright;left;right;right;2982\n"
            + "Main;73;square;arrowup;right;left;up;1162\n"
            + "Main;74;square;arrowright;left;right;right;1537\n"
            + "Main;75;square;arrowright;left;right;right;1072\n"
            + "Main;76;square;arrowleft;right;left;left;1443\n"
            + "Main;77;square;arrowleft;right;left;left;2633\n"
            + "Main;78;square;arrowup;left;right;up;2422\n"
            + "Main;79;square;arrowup;right;left;up;1999\n"
            + "Main;80;square;arrowleft;left;right;left;1587\n"
            + "Main;81;square;arrowright;right;left;right;1078\n"
            + "Main;82;square;arrowup;right;left;up;2474\n"
            + "Main;83;square;arrowright;left;right;right;1262\n"
            + "Main;84;square;arrowup;right;left;up;1448\n"
            + "Main;85;square;arrowup;right;left;up;2197\n"
            + "Main;86;square;arrowright;left;right;right;1720\n"
            + "Main;87;square;arrowup;right;left;up;1859\n"
            + "Main;88;square;arrowright;left;right;right;1975\n"
            + "Main;89;square;arrowup;right;left;up;1731\n"
            + "Main;90;square;arrowup;right;left;up;2456";
}
