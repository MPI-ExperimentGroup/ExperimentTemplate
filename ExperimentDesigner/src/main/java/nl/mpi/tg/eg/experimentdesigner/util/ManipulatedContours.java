/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner.util;

import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAudioTestScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardMenuScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardVideoAudioOptionStimulusScreen;

/**
 * @since Oct 21, 2016 2:08:51 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ManipulatedContours {
// two groups of participants: butons left to right and buttons right to left
    // time taken popup or other pester action to tell the participant to speed up
    // two sub groups: one gets part a (context) and part b(final, items), the other gets only part b
    // @todo: progress bar like indicator of the sound playback position 
    // buttons appear as soon as the sound ends

    private final WizardController wizardController = new WizardController();

    protected String getExperimentName() {
        return "ManipulatedContours";
    }

    final String agreementScreenText = "Merci beaucoup pour votre intérêt pour cette expérience scientifique en ligne! Les instructions détaillées pour la tâche seront données à la page suivante.<br/>"
            + "<br/>"
            + "Avant de commencer, vous devez d'abord confirmer que vous êtes d'accord pour participer à cette expérience. Veuillez noter que nous enregistrerons vos réponses pour des analyses scientifiques ultérieures. Nous utiliserons les résultats uniquement pour des buts scientifiques et nous allons les décrire dans des journaux scientifiques ou peut-être dans des quotidiens ou sur notre site web. Cependant, nous NE RAPORTERONS JAMAIS les résultats d'une manière qui permettrait de vous identifier. <br/>"
            + "<br/>"
            + "Vous êtes libre d'interrompre l'expérience à tout moment sans aucune explication. En outre, vous pourrez supprimer vos données ultérieurement sans avoir à fournir aucune explication également.<br/>"
            + "<br/>"
            + "Il n'y a aucun risque connu à participer à cette expérience. <br/>"
            + "<br/>"
            + "Si vous êtes d'accord pour participer à cette expérience, cliquez sur 'Je suis d'accord'. Si vous décidez de ne pas participer à cette expérience, cliquez sur 'Je ne suis pas d'accord'. Abandonnez l'expérience en allant sur un autre site web ou en quittant le site web.<br/>"
            + "<br/>";
    final String informationScreenText1 = "Cette expérience en ligne est une expérience audio. Pour cette raison, nous vous demandons de tester les réglages sonores de votre ordinateur en cliquant sur le gros bouton rouge ci-dessous.<br/>"
            + "<br/>"
            + "<b>Vous n'entendez aucun son?</b> Dans ce cas, il y a quelque chose qui ne va pas avec les réglages sonores de votre ordinateur. Personnalisez les réglages et essayez de nouveau.<br/>"
            + "<br/>"
            + "<b>Entendez-vous un son?</b> Dans ce cas, vos réglages sonores sont bons. Ajustez le volume de l'ordinateur à un niveau confortable.<br/>"
            + "<br/>"
            + "----------------------------------------------------------------<br/>"
            + "ATTENTION: Faites UNIQUEMENT cette expérience si vous vous trouvez dans un environment calme sans aucun bruit de fond. C'est très important!<br/>"
            + "----------------------------------------------------------------<br/>"
            + "<br/>"
            + "<br/>"
            + "<br/>"
            + "[Cliquez sur SUIVANT si vos réglages sonores sont bons...]";

    protected String informationScreenText2a() {
        return "Cette expérience en ligne est une expérience audio. Vous entendrez à plusieurs reprises un extrait de conversation naturelle suivi d'un silence. Votre tâche est d'indiquer si le locuteur qui parle avant le silence va continuer ou arrêter de parler après le silence.<br/>"
                + "<br/>"
                + "Par example:<br/>"
                + "Vous entendez l'extrait puis deux boutons apparaissent sur l'écran:<br/>"
                + "à droite, il est marqué “continue” et à gauche, il est marqué “arrête”.<br/>"
                + "Votre tâche est alors de cliquer sur le bouton à droite ou à gauche, suivant votre décision.<br/>"
                + "<br/>"
                + "Il y a environ 80 extraits dans cette expérience. Une session dure environ 20 minutes. Votre progrès est indiqué en haut de chaque écran.<br/>"
                + "<br/>"
                + "Attention: vous NE POUVEZ PAS mettre en pause, interrompre, ou reprendre l'expérience. Faites UNIQUEMENT cette expérience si vous avez vraiment le temps de la faire en entier. Faites l'expérience entièrement et sérieusement.<br/>"
                + "<br/>"
                + "Si l'expérience est claire et que vous êtes prêt(e) à commencer, cliquez sur SUIVANT.<br/>"
                + "L'expérience commencera IMMEDIATEMENT";
    }

    protected String informationScreenText2b() {
        return "Cette expérience en ligne est une expérience audio. Vous entendrez à plusieurs reprises un extrait de conversation naturelle suivi d'un silence. Votre tâche est d'indiquer si le locuteur qui parle avant le silence va continuer ou arrêter de parler après le silence.<br/>"
                + "<br/>"
                + "Par example:<br/>"
                + "Vous entendez l'extrait puis deux boutons apparaissent sur l'écran:<br/>"
                + "à gauche, il est marqué “continue” et à droite, il est marqué “arrête”.<br/>"
                + "Votre tâche est alors de cliquer sur le bouton à droite ou à gauche, suivant votre décision.<br/>"
                + "<br/>"
                + "Il y a environ 80 extraits dans cette expérience. Une session dure environ 20 minutes. Votre progrès est indiqué en haut de chaque écran.<br/>"
                + "<br/>"
                + "Attention: vous NE POUVEZ PAS mettre en pause, interrompre, ou reprendre l'expérience. Faites UNIQUEMENT cette expérience si vous avez vraiment le temps de la faire en entier. Faites l'expérience entièrement et sérieusement.<br/>"
                + "<br/>"
                + "Si l'expérience est claire et que vous êtes prêt(e) à commencer, cliquez sur SUIVANT.<br/>"
                + "L'expérience commencera IMMEDIATEMENT";
    }
    final String completionScreenText1 = "L'expérience est terminée.<br/>"
            + "<br/>"
            + "<br/>"
            + "Merci beaucoup pour votre participation!";

    protected int repeatCount() {
        return 4;
    }

    protected String[] getStimuliStringA() {
        return new String[]{
            "999:03-12-07_1_p1_M19R_i_54470_manipulated.wav:continue,arrête",
            "999:05-12-07_1_p1_M23R_i_359359_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_208696_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_608165_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_483828_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21R_f_164284_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_254252_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_f_929888_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1377242_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_358114_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_530691_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_i_1450339_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1397300_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_f_226523_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_660313_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_369646_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_r_782925_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_325139_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_i_315485_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_50507_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1398960_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07R_i_1198900_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_831537_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_11468_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_i_146083_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_i_219580_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_416402_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_255747_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_i_22560_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_542926_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_r_343273_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_518943_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07R_f_1086927_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_f_619771_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05L_i_106723_original.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_i_129603_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_i_779215_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_f_222400_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_i_75209_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_f_340735_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_242530_manipulated.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_i_146255_manipulated.wav:continue,arrête",
            "999:22-11-07_1_p1_F06R_f_1696027_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_662936_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_i_280359_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_f_624120_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_160211_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_529986_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_r_838332_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_669651_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_23773_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_394807_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_888933_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_427727_original.wav:continue,arrête",
            "999:05-12-07_1_p1_M23L_i_259230_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_i_353600_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21R_i_770591_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_397825_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_803331_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_i_60085_original.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_r_226308_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_374050_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_139907_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_681124_original.wav:continue,arrête",
            "999:22-11-07_1_p1_F06R_f_1430740_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_431951_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_r_380821_manipulated.wav:continue,arrête"
        };
    }

    protected String[] getStimuliStringB() {
        return new String[]{
            "999:03-12-07_1_p1_M19R_i_54470_original.wav:continue,arrête",
            "999:05-12-07_1_p1_M23R_i_359359_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_208696_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_608165_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_483828_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21R_f_164284_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_254252_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_f_929888_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1377242_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_358114_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_530691_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_i_1450339_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1397300_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_f_226523_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_660313_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_369646_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_r_782925_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_325139_manipulated.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_i_315485_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_50507_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_1398960_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07R_i_1198900_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_831537_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_11468_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_i_146083_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_i_219580_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_416402_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_255747_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_i_22560_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_542926_manipulated.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_r_343273_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_518943_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07R_f_1086927_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_f_619771_original.wav:continue,arrête",
            "999:20-11-07_1_p1_F05L_i_106723_manipulated.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_i_129603_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_i_779215_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_f_222400_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02L_i_75209_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_f_340735_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_242530_original.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_i_146255_original.wav:continue,arrête",
            "999:22-11-07_1_p1_F06R_f_1696027_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_i_662936_original.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_i_280359_manipulated.wav:continue,arrête",
            "999:20-11-07_1_p1_F05R_f_624120_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_160211_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_529986_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19L_r_838332_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_669651_original.wav:continue,arrête",
            "999:14-11-07_1_p1_M01L_r_23773_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_394807_manipulated.wav:continue,arrête",
            "999:22-11-07_2_p1_F07L_f_888933_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_427727_original.wav:continue,arrête",
            "999:05-12-07_1_p1_M23L_i_259230_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_i_353600_manipulated.wav:continue,arrête",
            "999:04-12-07_2_p1_M21R_i_770591_manipulated.wav:continue,arrête",
            "999:14-11-07_1_p1_M01R_r_397825_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_803331_original.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_i_60085_manipulated.wav:continue,arrête",
            "999:04-12-07_1_p1_M20R_r_226308_manipulated.wav:continue,arrête",
            "999:03-12-07_1_p1_M19R_r_374050_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_i_139907_original.wav:continue,arrête",
            "999:14-11-07_2_p1_M02R_f_681124_original.wav:continue,arrête",
            "999:22-11-07_1_p1_F06R_f_1430740_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_f_431951_original.wav:continue,arrête",
            "999:04-12-07_2_p1_M21L_r_380821_original.wav:continue,arrête"
        };
    }

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName(getExperimentName());
        wizardData.setShowMenuBar(false);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen2a = new WizardTextScreen("InformationScreen1a", informationScreenText2a(),
                "volgende [ spatiebalk ]"
        );
        WizardTextScreen wizardTextScreen2b = new WizardTextScreen("InformationScreen1b", informationScreenText2b(),
                "volgende [ spatiebalk ]"
        );
        WizardAudioTestScreen wizardTextScreen1 = new WizardAudioTestScreen("AudioTest", informationScreenText1, "suivant [ barre d'espacement ]", "bienvenue");
        //Information screen 
        //Agreement
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Accord", agreementScreenText, "Je suis d'accord");
//        wizardData.setAgreementText("agreementText");
//        wizardData.setDisagreementScreenText("disagreementScreenText");
        //metadata
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Edit User");
        wizardEditUserScreen.setMenuLabel("Edit User");
        wizardEditUserScreen.setScreenTag("Edit_User");
        wizardEditUserScreen.setNextButton("Suivant");
        wizardEditUserScreen.setScreenText("Entrez votre code identifiant ici:");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Impossible de contacter le serveur, vérifiez votre connexion Internet s'il vous plaît.");
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:code identifiant:.'{'3,'}':Entrez au moins trois lettres."
//            "firstName:Prénom:.'{'3,'}':Entrez au moins trois lettres.",
//            "lastName:Nom de famille:.'{'3,'}':Entrez au moins trois lettres.",
//            "age:Âge:[0-9]+:Entrez un nombre.",
//            "gender:Sexe:|homme|femme|autre:."
        });

        wizardData.addScreen(wizardEditUserScreen);
        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen1);
        wizardData.addScreen(wizardTextScreen2a);
        wizardData.addScreen(wizardTextScreen2b);

        final WizardVideoAudioOptionStimulusScreen list1234ScreenA = new WizardVideoAudioOptionStimulusScreen("StimuliA", false, getStimuliStringA(), false,
                null, 1000, repeatCount(), 20, true, 100, "", "", true);
        final WizardVideoAudioOptionStimulusScreen list1234ScreenB = new WizardVideoAudioOptionStimulusScreen("StimuliB", false, getStimuliStringB(), false,
                null, 1000, repeatCount(), 20, true, 100, "", "", true);
//        list1234Screen.setStimulusResponseOptions("1,2,3,4,5");
//        list1234Screen.setStimulusResponseLabelLeft("très probable négatif");
//        list1234Screen.setStimulusResponseLabelRight("très probable positif");
        wizardData.addScreen(list1234ScreenA);
        wizardData.addScreen(list1234ScreenB);

        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, false, true,
                null, //Si quelqu'un d'autre veut participer à l'expérience sur cet ordinateur, veuillez cliquer sur le bouton ci-dessous.",
                "Redémarrer",
                "Fini",
                "Impossible de contacter le serveur, vérifiez votre connexion Internet s'il vous plaît.", "Réessayer");
        wizardData.addScreen(completionScreen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        wizardAboutScreen.setBackWizardScreen(wizardEditUserScreen);
        wizardData.addScreen(wizardAboutScreen);

        wizardEditUserScreen.setNextWizardScreen(agreementScreen);
        agreementScreen.setNextWizardScreen(wizardTextScreen1);
        WizardMenuScreen menuScreen = new WizardMenuScreen("temporary menu", "temporary menu", "temporary menu");
        menuScreen.addTargetScreen(wizardTextScreen2a);
        menuScreen.addTargetScreen(wizardTextScreen2b);
        wizardData.addScreen(menuScreen);
        wizardTextScreen1.setNextWizardScreen(menuScreen);

        wizardTextScreen2a.setNextWizardScreen(list1234ScreenA);
        wizardTextScreen2b.setNextWizardScreen(list1234ScreenB);
        list1234ScreenA.setNextWizardScreen(completionScreen);
        list1234ScreenB.setNextWizardScreen(completionScreen);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }

//final RandomGrouping randomGrouping = new RandomGrouping();
//        if (storedWizardScreenData.getStimuliRandomTags() != null) {
//            for (String randomTag : storedWizardScreenData.getStimuliRandomTags()) {
//                randomGrouping.addRandomTag(randomTag);
//            }
//            final String metadataFieldname = "groupAllocation_" + storedWizardScreenData.getScreenTag();
//            randomGrouping.setStorageField(metadataFieldname);
//            loadStimuliFeature.setRandomGrouping(randomGrouping);
//            experiment.getMetadata().add(new Metadata(metadataFieldname, metadataFieldname, ".*", ".", false, null));
//        }
}
