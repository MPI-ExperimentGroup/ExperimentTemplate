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
package nl.mpi.tg.eg.experimentdesigner.util;

import nl.mpi.tg.eg.experimentdesigner.controller.WizardController;
import nl.mpi.tg.eg.experimentdesigner.model.Experiment;
import nl.mpi.tg.eg.experimentdesigner.model.WizardData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAboutScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardAgreementScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Jul 28, 2017 2:29:54 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class SentencesRatingTask {

    private final WizardController wizardController = new WizardController();
    final String agreementScreenText = "Toestemmingsverklaring voor deelname aan het onderzoek:<br/>"
            + "ONION rating survey<br/>"
            + "Voordat je begint met dit experiment, dien je eerst te bevestigen dat je toestemt met deelname aan dit experiment. We zullen je antwoorden opslaan voor latere analyse. We gebruiken de resultaten alleen voor onderzoeksdoeleinden, en zullen ze beschrijven in gespecialiseerde tijschriften of wellicht in kranten of op onze website. Echter, we zullen de resultaten NOOIT rapporteren op zo'n manier dat je zou kunnen worden geïdentificeerd.<br/>"
            + "<br/>"
            + "Door akkoord te gaan, verklaar je dat je voldoende bent geïnformeerd over het onderzoek en dat je goed over deelname aan het onderzoek hebt na kunnen denken. Je bent tijdens dit experiment op elk moment vrij om je terug te trekken zonder uitleg te geven. Ook kun je je gegevens laten verwijderen tot het moment van publicatie, zonder uit te leggen waarom je dat doet.<br/>"
            + "<br/>"
            + "Er zijn geen risico's bekend met het meedoen aan dit experiment.<br/>"
            + "<br/>"
            + "Als je ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan.<br/>"
            + "Als je besluit niet deel te nemen aan het experiment, kun je de pagina sluiten of naar een andere website gaan.<br/>"
            + "<br/>"
            + "";
    final String informationScreenText1 = "In the following you will read short action phrases consisting of a verb and an object (e.g., drive a car). For each phrase we would like you to answer questions along three dimensions by using a 7-point scale. The dimensions include object state change,  action familiarity and imageability of the action.";
    final String informationScreenText2 = "For object state change, you are asked to indicate to which degree the object is changed during the action (7 = substantial change; 1 = no change). For action familiarity, please indicate how familiar you are with the action (7 = very familiar; 1 = not familiar at all). For imageability of the action, tell us how well you can image the action ( 7 = very imageable; 1 = not imageable at all).";
    final String informationScreenText3 = "Note that each dimension will be presented to you as a block and that the order of blocks may be different to the order of you just read. You will be reminded of the question you are supposed to answer";
    final String completionScreenText1 = "Dit is het einde van het experiment.<br/>"
            + "<br/>"
            + "Ter bevestiging van je deelname willen we je vragen om de volgende code te sturen naar Maarten.vandenHeuvel@mpi.nl. Pas na ontvangst van deze code, kun je worden uitbetaald.<br/>"
            + "<br/>"
            + "Bedankt voor je deelname!";
    private final String[] stimuliString = {
        "0/1SCa/List1:beat the cream",
        "1/2NSCb/List1:pick the butter",
        "2/1Fa/List1:build the lego tower",
        "3/3SCa/List1:bite the sandwich",
        "4/4NSCb/List1:smell the cookie",
        "5/3Fa/List1:draw a flower",
        "6/5SCa/List1:blend the blueberries",
        "7/6NSCb/List1:gather the strawberries",
        "8/5Fa/List1:knit the scarf",
        "9/7SCa/List1:boil the pasta",
        "10/8NSCb/List1:inspect the water",
        "11/7Fa/List1:sculpt a cup",
        "12/9SCa/List1:burn the toast",
        "13/10NSCb/List1:grab a match",
        "14/9Fa/List1:fold the paper plane",
        "15/11SCa/List1:carve the pumpkin",
        "16/12NSCb/List1:purchase wood",
        "17/11Fa/List1:bead the necklace",
        "18/13SCa/List1:chop the onion",
        "19/14NSCb/List1:collect the leeks",
        "20/13Fa/List1:hammer the nail",
        "21/15SCa/List1:clean the glasses",
        "22/16NSCb/List1:touch the knife",
        "23/15Fa/List1:drink the coke",
        "24/17SCa/List1:crack the chocolate bunny",
        "25/18NSCb/List1:choose a walnut",
        "26/17Fa/List1:eat the schnitzel",
        "27/19SCa/List1:crush the pepper",
        "28/20NSCb/List1:avoid the ice cubes",
        "29/19Fa/List1:get the bubble gum",
        "30/21SCa/List1:cube the cucumber",
        "31/22NSCb/List1:savor the cheese",
        "32/21Fa/List1:print a sentence",
        "33/23SCa/List1:dice the tomato",
        "34/24NSCb/List1:fetch the garlic",
        "35/23Fa/List1:tie the shoes",
        "36/25SCa/List1:dip the crackers",
        "37/26NSCb/List1:dislike the bread",
        "38/25Fa/List1:reach for the box",
        "39/27SCa/List1:dissolve the powder",
        "40/28NSCb/List1:prescribe the pill",
        "41/27Fa/List1:write a poem",
        "42/29SCa/List1:fry the mushrooms",
        "43/30NSCb/List1:deliver the steak",
        "44/29Fa/List1:taste the curry",
        "45/31SCa/List1:grate the parmesan",
        "46/32NSCb/List1:relish the carrot",
        "47/31Fa/List1:bake the brownies",
        "48/33SCa/List1:grind chillies",
        "49/34NSCb/List1:despise coffee",
        "50/33Fa/List1:empty the bin",
        "51/35SCa/List1:halve the avocado",
        "52/36NSCb/List1:display the melon",
        "53/35Fa/List1:suck the lollipop",
        "54/37SCa/List1:ice the cake",
        "55/38NSCb/List1:store the muffins",
        "56/37Fa/List1:search the package",
        "57/39SCa/List1:juice grapefruits",
        "58/40NSCb/List1:carry oranges",
        "59/39Fa/List1:cuddle the doll",
        "60/41SCa/List1:mash the peas",
        "61/42NSCb/List1:fancy the beans",
        "62/41Fa/List1:bring the Barbie",
        "63/43SCa/List1:melt the ice cream",
        "64/44NSCb/List1:lick the chocolate",
        "65/43Fa/List1:type the email",
        "66/45SCa/List1:mince parsley",
        "67/46NSCb/List1:detest almonds",
        "68/45Fa/List1:read the book",
        "69/47SCa/List1:open the can",
        "70/48NSCb/List1:hold the present",
        "71/47Fa/List1:pack the backpack",
        "72/49SCa/List1:peel the banana",
        "73/50NSCb/List1:weigh the mandarin",
        "74/49Fa/List1:have the puppet",
        "75/51SCa/List1:roast the chicken",
        "76/52NSCb/List1:select the peppers",
        "77/51Fa/List1:throw the frisbee",
        "78/53SCa/List1:skin the potato",
        "79/54NSCb/List1:serve the apple",
        "80/53Fa/List1:solve the puzzle",
        "81/55SCa/List1:slice the bagel",
        "82/56NSCb/List1:crave the pizza",
        "83/55Fa/List1:try the chips",
        "84/57SCa/List1:squeeze the lime",
        "85/58NSCb/List1:examine the lemon",
        "86/57Fa/List1:sew the cushion",
        "87/59SCa/List1:wash plate",
        "88/60NSCb/List1:offer bowl",
        "89/59Fa/List1:wear the socks",
        "90/61SCa/List1:break the vase",
        "91/62NSCb/List1:watch the clock",
        "92/61Fa/List1:catch the ball",
        "93/63SCa/List1:color the drawing",
        "94/64NSCb/List1:rank the painting",
        "95/63Fa/List1:knead the dough",
        "96/65SCa/List1:cut some rope",
        "97/66NSCb/List1:buy some cardboard",
        "98/65Fa/List1:lose a pencil",
        "99/67SCa/List1:destroy the files",
        "100/68NSCb/List1:forward the notes",
        "101/67Fa/List1:count the coins",
        "102/69SCa/List1:drop the bottle",
        "103/70NSCb/List1:win the mug",
        "104/69Fa/List1:sort the clothes",
        "105/71SCa/List1:light the cigarette",
        "106/72NSCb/List1:hate the candles",
        "107/71Fa/List1:forget the keys",
        "108/73SCa/List1:paint the easter egg",
        "109/74NSCb/List1:admire her nails",
        "110/73Fa/List1:use a fork",
        "111/75SCa/List1:rip the papers",
        "112/76NSCb/List1:scan the receipts",
        "113/75Fa/List1:make the shopping list",
        "114/77SCa/List1:scratch the screen",
        "115/78NSCb/List1:monitor the phone",
        "116/77Fa/List1:enjoy the soup",
        "117/79SCa/List1:spill the paint",
        "118/80NSCb/List1:sniff the whiskey",
        "119/79Fa/List1:share the pear",
        "120/81SCa/List1:stain the bib",
        "121/82NSCb/List1:donate the blanket",
        "122/81Fa/List1:water the cactus",
        "123/83SCa/List1:staple the forms",
        "124/84NSCb/List1:utilize the documents",
        "125/83Fa/List1:pour the tea",
        "126/85SCa/List1:tear the photo",
        "127/86NSCb/List1:receive the shirt",
        "128/85Fa/List1:steal the ring",
        "129/87SCa/List1:shred the contract",
        "130/88NSCb/List1:fax the bank statements",
        "131/87Fa/List1:like the magazine",
        "0/2NSCa/List2:pick the cream",
        "1/1SCb/List2:beat the butter",
        "2/1Fb/List2:build the card house",
        "3/4NSCa/List2:smell the sandwich",
        "4/3SCb/List2:bite the cookie",
        "5/3Fb/List2:draw a house",
        "6/6NSCa/List2:gather the blueberries",
        "7/5SCb/List2:blend the strawberries",
        "8/5Fb/List2:knit the hat",
        "9/8NSCa/List2:inspect the pasta",
        "10/7SCb/List2:boil the water",
        "11/7Fb/List2:sculpt a pot",
        "12/10NSCa/List2:grab the toast",
        "13/9SCb/List2:burn a match",
        "14/9Fb/List2:fold the napkin",
        "15/12NSCa/List2:purchase the pumpkin",
        "16/11SCb/List2:carve wood",
        "17/11Fb/List2:bead the bracelet",
        "18/14NSCa/List2:collect the onion",
        "19/13SCb/List2:chop the leeks",
        "20/13Fb/List2:hammer the dowel",
        "21/16NSCa/List2:touch the glasses",
        "22/15SCb/List2:clean the knife",
        "23/15Fb/List2:drink the milk",
        "24/18NSCa/List2:choose the chocolate bunny",
        "25/17SCb/List2:crack a walnut",
        "26/17Fb/List2:eat the waffle",
        "27/20NSCa/List2:avoid the pepper",
        "28/19SCb/List2:crush the ice cubes",
        "29/19Fb/List2:get the candy",
        "30/22NSCa/List2:savor the cucumber",
        "31/21SCb/List2:cube the cheese",
        "32/21Fb/List2:print an essay",
        "33/24NSCa/List2:fetch the tomato",
        "34/23SCb/List2:dice the garlic",
        "35/23Fb/List2:tie the knot",
        "36/26NSCa/List2:dislike the crackers",
        "37/25SCb/List2:dip the bread",
        "38/25Fb/List2:reach for the lamp",
        "39/28NSCa/List2:prescribe the powder",
        "40/27SCb/List2:dissolve the pill",
        "41/27Fb/List2:write a postcard",
        "42/30NSCa/List2:deliver the mushrooms",
        "43/29SCb/List2:fry the steak",
        "44/29Fb/List2:taste the hummus",
        "45/32NSCa/List2:relish the parmesan",
        "46/31SCb/List2:grate the carrot",
        "47/31Fb/List2:bake the lasagna",
        "48/34NSCa/List2:despise chillies",
        "49/33SCb/List2:grind coffee",
        "50/33Fb/List2:empty the bucket",
        "51/36NSCa/List2:display the avocado",
        "52/35SCb/List2:halve the melon",
        "53/35Fb/List2:suck the bonbon",
        "54/38NSCa/List2:store the cake",
        "55/37SCb/List2:ice the muffins",
        "56/37Fb/List2:search the plastic bag",
        "57/40NSCa/List2:carry grapefruits",
        "58/39SCb/List2:juice oranges",
        "59/39Fb/List2:cuddle the teddy bear",
        "60/42NSCa/List2:fancy the peas",
        "61/41SCb/List2:mash the beans",
        "62/41Fb/List2:bring the balloon",
        "63/44NSCa/List2:lick the ice cream",
        "64/43SCb/List2:melt the chocolate",
        "65/43Fb/List2:type the text message",
        "66/46NSCa/List2:detest parsley",
        "67/45SCb/List2:mince almonds",
        "68/45Fb/List2:read the newspaper",
        "69/48NSCa/List2:hold the can",
        "70/47SCb/List2:open the present",
        "71/47Fb/List2:pack the handbag",
        "72/50NSCa/List2:weigh the banana",
        "73/49SCb/List2:peel the mandarin",
        "74/49Fb/List2:have the dice",
        "75/52NSCa/List2:select the chicken",
        "76/51SCb/List2:roast the peppers",
        "77/51Fb/List2:throw the rock",
        "78/54NSCa/List2:serve the potato",
        "79/53SCb/List2:skin the apple",
        "80/53Fb/List2:solve the crossword",
        "81/56NSCa/List2:crave the bagel",
        "82/55SCb/List2:slice the pizza",
        "83/55Fb/List2:try the pudding",
        "84/58NSCa/List2:examine the lime",
        "85/57SCb/List2:squeeze the lemon",
        "86/57Fb/List2:sew the skirt",
        "87/60NSCa/List2:offer plate",
        "88/59SCb/List2:wash bowl",
        "89/59Fb/List2:wear the tights",
        "90/62NSCa/List2:watch the vase",
        "91/61SCb/List2:break the clock",
        "92/61Fb/List2:catch the boomerang",
        "93/64NSCa/List2:rank the drawing",
        "94/63SCb/List2:color the painting",
        "95/63Fb/List2:knead the Play-Doh",
        "96/66NSCa/List2:buy some rope",
        "97/65SCb/List2:cut some cardboard",
        "98/65Fb/List2:lose a crayon",
        "99/68NSCa/List2:forward the files",
        "100/67SCb/List2:destroy the notes",
        "101/67Fb/List2:count the marbles",
        "102/70NSCa/List2:win the bottle",
        "103/69SCb/List2:drop the mug",
        "104/69Fb/List2:sort the toys",
        "105/72NSCa/List2:hate the cigarette",
        "106/71SCb/List2:light the candles",
        "107/71Fb/List2:forget the wallet",
        "108/74NSCa/List2:admire the easter egg",
        "109/73SCb/List2:paint her nails",
        "110/73Fb/List2:use a spoon",
        "111/76NSCa/List2:scan the papers",
        "112/75SCb/List2:rip the receipts",
        "113/75Fb/List2:make the salad",
        "114/78NSCa/List2:monitor the screen",
        "115/77SCb/List2:scratch the phone",
        "116/77Fb/List2:enjoy the smoothie",
        "117/80NSCa/List2:sniff the paint",
        "118/79SCb/List2:spill the whiskey",
        "119/79Fb/List2:share the fries",
        "120/82NSCa/List2:donate the bib",
        "121/81SCb/List2:stain the blanket",
        "122/81Fb/List2:water the plant",
        "123/84NSCa/List2:utilize the forms",
        "124/83SCb/List2:staple the documents",
        "125/83Fb/List2:pour the juice",
        "126/86NSCa/List2:receive the photo",
        "127/85SCb/List2:tear the shirt",
        "128/85Fb/List2:steal the cherries",
        "129/88NSCa/List2:fax the contract",
        "130/87SCb/List2:shred the bank statements",
        "131/87Fb/List2:like the catalogue"
    };
//    String[] stimuliExperimentArray = new String[]{
//        "experiment/groupA/E_001_N_A.JPG",

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("Sentences Rating Task");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(22);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen1 = new WizardTextScreen("Informatie1", informationScreenText1,
                "volgende [ spatiebalk ]"
        );
        WizardTextScreen wizardTextScreen2 = new WizardTextScreen("Informatie2", informationScreenText2,
                "volgende [ spatiebalk ]"
        );
        WizardTextScreen wizardTextScreen3 = new WizardTextScreen("Informatie3", informationScreenText3,
                "volgende [ spatiebalk ]"
        );
        wizardTextScreen1.setMenuLabel("Terug");
        wizardTextScreen2.setMenuLabel("Terug");
        wizardTextScreen3.setMenuLabel("Terug");
        //Information screen 
        //Agreement
        WizardAgreementScreen agreementScreen = new WizardAgreementScreen("Toestemming", agreementScreenText, "Akkoord");
        agreementScreen.setMenuLabel("Terug");
//        wizardData.setAgreementText("agreementText");
//        wizardData.setDisagreementScreenText("disagreementScreenText");
        //metadata
        final WizardEditUserScreen wizardEditUserScreen = new WizardEditUserScreen();
        wizardEditUserScreen.setScreenTitle("Gegevens");
        wizardEditUserScreen.setMenuLabel("Terug");
        wizardEditUserScreen.setScreenTag("Gegevens");
        wizardEditUserScreen.setNextButton("Volgende");
        wizardEditUserScreen.setSendData(true);
        wizardEditUserScreen.setOn_Error_Text("Could not contact the server, please check your internet connection and try again.");
//        wizardEditUserScreen.setMetadataScreen(true);
//        wizardData.setAgeField(true);
        wizardEditUserScreen.setCustomFields(new String[]{
            "age:Leeftijd:[0-9]+:Voer een getal.",
            "gender:Geslacht:|man|vrouw|anders:.",
            "language:What is your native language?:.'{'3,'}':Please enter at least three letters.",
            "languageOthers:Which other languages do you speak?:.'{'3,'}':Please enter at least three letters.",
            "nationality:What is your nationality?:.'{'3,'}':Please enter at least three letters."
        });

        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen1);
        wizardData.addScreen(wizardTextScreen2);
        wizardData.addScreen(wizardTextScreen3);
        wizardData.addScreen(wizardEditUserScreen);

        final WizardRandomStimulusScreen list1234Screen = new WizardRandomStimulusScreen("Stimulus", false, stimuliString,
                new String[]{"List1", "List2"}, 1000, true, null, 0, 0, null, null, null, null, "volgende [ spatiebalk ]");
        list1234Screen.getWizardScreenData().setStimulusResponseOptions("1,2,3,4,5,6,7");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelLeft("helemaal niet plausibel");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelRight("heel erg plausibel");
        wizardData.addScreen(list1234Screen);

        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, true, true,
                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                "Opnieuw beginnen",
                "Finished",
                "Could not contact the server, please check your internet connection and try again.", "Retry");
        wizardData.addScreen(completionScreen);

        wizardTextScreen1.setNextWizardScreen(wizardTextScreen2);
        wizardTextScreen2.setNextWizardScreen(wizardTextScreen3);
        wizardTextScreen3.setNextWizardScreen(list1234Screen);
        agreementScreen.setNextWizardScreen(wizardEditUserScreen);
        wizardEditUserScreen.setNextWizardScreen(wizardTextScreen1);
        list1234Screen.setNextWizardScreen(completionScreen);

        wizardEditUserScreen.setBackWizardScreen(agreementScreen);
        wizardTextScreen3.setBackWizardScreen(wizardTextScreen3);
        wizardTextScreen2.setBackWizardScreen(wizardTextScreen2);
        wizardTextScreen1.setBackWizardScreen(wizardEditUserScreen);
        list1234Screen.setBackWizardScreen(wizardTextScreen3);
        //completionScreen.setBackWizardScreen(list1234Screen);
        final WizardAboutScreen wizardAboutScreen = new WizardAboutScreen("Over", false);
        wizardAboutScreen.setBackWizardScreen(wizardEditUserScreen);
        wizardData.addScreen(wizardAboutScreen);
        return wizardData;
    }

    public Experiment getExperiment() {
        return wizardController.getExperiment(getWizardData());
    }
}
