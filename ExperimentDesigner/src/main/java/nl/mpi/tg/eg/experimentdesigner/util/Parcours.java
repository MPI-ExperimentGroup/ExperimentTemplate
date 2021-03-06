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

import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilEnum;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilMetadata;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilSendData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilStimuliData;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardUtilText;

/**
 * @since Jan 25, 2017 16:39:41 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Parcours extends WizardUtilData {

    final String agreementScreenText = "Toestemmingsverklaring voor deelname aan het onderzoek:<br/>"
            + "<br/>"
            + "Ik stem geheel vrijwillig in met deelname aan dit onderzoek. Ik behoud daarbij het recht deze instemming weer in te trekken zonder dat ik daarvoor een reden hoef op te geven en besef dat ik op elk moment mag stoppen met het experiment. Als mijn onderzoeksresultaten gebruikt zullen worden in wetenschappelijke publicaties, dan wel op een andere manier openbaar worden gemaakt, zal dit volledig geanonimiseerd gebeuren. Mijn persoonsgegevens zullen niet door derden worden ingezien zonder mijn uitdrukkelijke toestemming. <br/>"
            + "<br/>"
            + "Als u ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan.<br/>"
            + "<br/>"
            + "Als u besluit niet deel te nemen aan het experiment, kunt u de pagina sluiten of naar een andere website gaan.";
    final String informationScreenText = "Geachte deelnemer aan het onderzoek 'PARCOURS',<br/>"
            + "<br/>"
            + "Alvast ontzettend bedankt voor uw deelname aan dit onderzoek.<br/>"
            + "U gaat steeds korte gesprekjes lezen die eindigen met een onafgemaakte zin; het is uw taak om die zin af te maken. Voordat u gaat beginnen is het belangrijk dat u kennis neemt van de procedure die in het onderzoek wordt gevolgd. Leest u daarom onderstaande instructies zorgvuldig door. <br/>"
            + "<br/>"
            + "<b>Doel van het onderzoek</b><br/>"
            + "U gaat meedoen aan een onderzoek waarin we de mate van overeenstemming tussen mensen onderzoeken met betrekking tot hun voorspellingen over het einde van een zin.<br/>"
            + "<br/>"
            + "<b>Instructies</b><br/>"
            + "U krijgt steeds korte gesprekjes te zien die eindigen met een onafgemaakte zin. Maak de zin telkens af met <b>één of enkele woorden</b>. U hoeft niet te lang na te denken; vul gewoon in wat het eerste in u opkomt.<br/>"
            + "<br/>"
            + "Een voorbeeld:<br/>"
            + "Jan is op bezoek geweest bij een bedrijf waar hij regelmatig mee samenwerkt.<br/>"
            + "Zijn collega vraagt: hoe is het gegaan?<br/>"
            + "Jan zegt: ze hebben me ontzettend veel verteld over de ...<br/>"
            + "<br/>"
            + "Hier zou u bijvoorbeeld \"leveranciers\" in kunnen vullen.<br/>"
            + " <br/>"
            + " Nog een voorbeeld:<br/>"
            + " Loek heeft zijn broer aan de telefoon.<br/>"
            + "Zijn broer zegt: de verbinding is niet zo goed, wat zei je?<br/>"
            + "Loek zegt: Ik begon net over de ...<br/>"
            + " <br/>"
            + "Deze zin zou u bijvoorbeeld kunnen afmaken met: \"familiedag te vertellen.\"<br/>"
            + " <br/>"
            + " <br/>"
            + "<br/>"
            + "<br/>"
            + "Enkele aandachtspunten: <br/>"
            + "- Probeer niet te origineel te zijn;<br/>"
            + "- Maak volledige, grammaticale zinnen;<br/>"
            + "- Vermijd typ- en spelfouten;<br/>"
            + "- Vul overal iets in.<br/>"
            + "<br/>"
            + "In totaal zal het onderzoek ongeveer <b>30 minuten</b> duren. Zorg ervoor dat u tijdens het experiment in een rustige omgeving zit, zonder afleiding van bijvoorbeeld uw mobiele telefoon, TV of van andere mensen. <br/>"
            + "<br/>"
            + "Zo begint u:<br/>"
            + "Door te klikken op 'Volgende' gaat u naar de volgende pagina. Voer hier alsublieft uw gegevens in: uw leeftijd, opleidingsniveau en geslacht.  De proefpersoon-ID code is al voor u ingevuld.<br/>"
            + "<br/>"
            + "U begint het onderzoek door weer te klikken op 'Volgende'. U ziet dan een gesprekje, dat eindigt met een onafgemaakte zin. U kunt de zin afmaken door één of enkele woorden in te vullen in de textbox onder het gesprekje.  Door te klikken op 'Volgende' gaat u naar het volgende gesprekje. Let op: het is niet mogelijk om terug te gaan naar eerdere gesprekjes!<br/>"
            + "<br/>"
            + "<b>Proefpersonen die niet mee kunnen doen aan het onderzoek</b><br/>"
            + "Het is niet mogelijk om deel te nemen aan dit onderzoek als Nederlands niet uw moedertaal is of wanneer u jonger bent dan 18 jaar of ouder dan 45 jaar. <br/>"
            + "<br/>"
            + "<b>Vergoeding</b><br/>"
            + "Voor deelname aan dit experiment ontvant u <b>€ 6,-</b>. Dit bedrag zal worden overgemaakt op uw bankrekening. <br/>"
            + "<br/>"
            + "<br/>"
            + "Alvast heel erg bedankt voor uw deelname en succes!<br/>"
            + "<br/>"
            + "<br/>"
            + "Marlou Rasenberg<br/>"
            + "Max Planck Instituut voor Psycholinguïstiek";
    final String completionScreenText1 = "Dit is het einde van het experiment.<br/>"
            + "Hartelijk dank voor uw deelname! <br/>"
            + "<br/>";
    private final String[] stimuliString = {
        "setnr_1/cond_c/list_a:Hanna leest een moeilijke tekst voor.<br/>De docent vraagt: wat vond je lastig om uit te spreken?<br/>Hanna zegt: ik twijfelde eventjes bij het woord",
        "setnr_2/cond_c/list_a:Piet bladert in een woordenboek.<br/>Zijn vriendin vraagt: wat ben je aan het opzoeken?<br/>Piet zegt: ik wil graag de betekenis van het woord",
        "setnr_3/cond_c/list_a:Irene is een tekst aan het typen op haar laptop.<br/>Een studiegenoot vraagt: wat ben je aan het doen?<br/>Irene zegt: ik zoek eventjes een synoniem voor het woord",
        "setnr_4/cond_c/list_a:Anja wordt plotseling wakker.<br/>Haar man vraagt: wat is er aan de hand?<br/>Anja zegt: ik had net een levendige droom over een",
        "setnr_5/cond_c/list_a:Cor en Denise lopen langs een grote menigte.<br/>Cor vraagt: wat doen al die mensen hier? <br/>Denise zegt: ze staan allemaal te kijken naar de",
        "setnr_6/cond_c/list_a:Wilma is de hele zolder aan het doorspitten.<br/>Haar zoon vraagt: wat ben je kwijt?<br/>Wilma zegt: ik ben al uren op zoek naar de",
        "setnr_7/cond_c/list_a:Tieneke komt terug van een meeting.<br/>Haar collega vraagt: en, hoe was het?<br/>Tieneke zegt: het viel me vooral op dat iedereen erg",
        "setnr_8/cond_c/list_a:Sven komt na een werkoverleg terug op kantoor.<br/>Zijn collega vraagt: hoe is het gegaan?<br/>Sven zegt: er waren heel veel vragen over de",
        "setnr_9/cond_c/list_a:Dennis laat Judith een van zijn kunstwerken zien.<br/>Dennis vraagt: wat vind je ervan?<br/>Judith zegt: het doet me sterk denken aan mijn",
        "setnr_10/cond_c/list_a:Marjolein en Lennart zijn buiten aan het klussen.<br/>Marjolein vraagt: wat is dat, daar achter die stapel bakstenen?<br/>Lennart zegt: er liggen daar een aantal",
        "setnr_11/cond_c/list_a:Jaap en Sanne gaan er straks op uit om inkopen te doen voor de zaak.<br/>Jaap vraagt: wat moet er verder nog op het lijstje?<br/>Sanne zegt: we hebben sowieso nog wat van die",
        "setnr_12/cond_c/list_a:Ans en Ine gaan boodschappen doen.<br/>Ans vraagt: wat hebben we allemaal nodig?<br/>Ine zegt: we moeten in ieder geval een aantal",
        "setnr_13/cond_c/list_a:Monique en Sofia staan op het punt te vertrekken, als Monique opeens verschrikt kijkt.<br/>Sofia vraagt: wat is er?<br/>Monique zegt: ik ben helemaal vergeten om een",
        "setnr_14/cond_c/list_a:Mark en Erika kijken naar een talkshow op televisie.<br/>Mark vraagt: waarom mag jij die man niet?<br/>Erika zegt: hij vertelt steeds allerlei leugens over de",
        "setnr_15/cond_c/list_a:Diana en Thomas ruimen hun oude DVD's op.<br/>Diana vraagt: waar ging deze film ook alweer over?<br/>Thomas zegt: ik herinner me nu alleen die scène met de",
        "setnr_16/cond_c/list_a:Tim werd net gebeld door zijn broer.<br/>Zijn vriendin vraagt: wat vertelde je broer?<br/>Tim zegt: hij heeft gisteren voor het eerst een",
        "setnr_17/cond_c/list_a:Claudia komt terug van een familiebijeenkomst.<br/>Haar vriend vraagt: waar heb je het met je nicht over gehad?<br/>Claudia zegt: ze vertelde vanmiddag dat ze een nieuwe ",
        "setnr_18/cond_c/list_a:René en Liam zijn aan het kletsen over hun kindertijd.<br/>René vraagt: wat herinner je je nog van de kleuterschool?<br/>Liam zegt: ik was toen de enige die een",
        "setnr_19/cond_c/list_a:Henk en Wouter spreken elkaar op de vrijdagmiddagborrel. <br/>Henk vraagt: wat zijn je plannen voor dit weekend?<br/>Wouter zegt: ik heb met Jan afgesproken om naar de",
        "setnr_20/cond_c/list_a:Op hun derde date hebben Sharon en Arno het over vroeger. <br/>Sharon vraagt: wat deed je het liefst als kind?<br/>Arno zegt: ik maakte altijd veel tekeningen van de",
        "setnr_21/cond_c/list_a:Esmee zit bij Puck thuis aan tafel, als Puck's moeder opeens opstaat.<br/>Esmee vraagt: wat gaat je moeder doen?<br/>Puck zegt: ze heeft vandaag besloten om een",
        "setnr_22/cond_c/list_a:Martin en Elske hebben het over de voordelen het digitale tijdperk.<br/>Martin vraagt: wat is het laatste dat je op Wikipedia hebt opgezocht?<br/>Elske zegt: ik las toevallig gisteren nog een stuk over de",
        "setnr_23/cond_c/list_a:Leon leest een tijdschrift.<br/>Zijn huisgenoot vraagt: wat voor artikel ben je aan het lezen?<br/>Leon zegt: hierin wordt heel duidelijk verteld hoe het zit met de",
        "setnr_24/cond_c/list_a:Dennis en Anna hebben in een restaurant het verrassingsmenu besteld. <br/>Dennis vraagt: wat vind je van dit gerecht?<br/>Anna zegt: ik ben persoonlijk niet zo dol op",
        "setnr_25/cond_c/list_a:Martin en Thea gaan hun favoriete serie kijken.<br/>Martin vraagt: waar ging de vorige aflevering ook alweer over?<br/>Thea zegt: ik herinner me nog dat Sarah van plan was om een",
        "setnr_26/cond_c/list_a:Olaf en Lotte stellen een evaluatierapport op.<br/>Olaf vraagt: wat voor problemen hebben zich voorgedaan?<br/>Lotte zegt: er waren vooral veel klachten over de",
        "setnr_27/cond_c/list_a:Het is Pasen, en Martine en Bart verstoppen paaseitjes voor hun zoontje.<br/>Martine vraagt: waar heb je die laatste ook alweer gelaten?<br/>Bart zegt: ik heb hem daarstraks daar achter die",
        "setnr_28/cond_c/list_a:Nancy en Linda spelen een spelletje.<br/>Nancy zegt: ik zie, ik zie, wat jij niet ziet en het is.. oranje!<br/>Linda zegt: ik denk echt dat het die",
        "setnr_29/cond_c/list_a:Vera en Fred maken een uitstapje.<br/>Vera vraagt: waarom is het hier zo druk?<br/>Fred zegt: ik denk dat ze allemaal afkomen op de",
        "setnr_30/cond_c/list_a:Loek is thuis zijn huiswerk aan het maken.<br/>Zijn vader vraagt: waar ben je mee bezig?<br/>Loek zegt: ik moet deze week een verlag schrijven over de",
        "setnr_31/cond_c/list_a:Yvette is naar een conferentie geweest.<br/>Haar collega vraagt: wat vond je ervan?<br/>Yvette zegt: ik vond vooral het praatje over",
        "setnr_32/cond_c/list_a:Sabine en Lea zijn aan het shoppen bij de Hema.<br/>Sabine vraagt: wat hebben ze op de 1e verdieping?<br/>Lea zegt: daar staan volgens mij altijd de",
        "setnr_33/cond_c/list_a:Het is vrijdagmiddag, en Nick en Tess sluiten het kantoor af voor het weekend.<br/>Nick zegt: wat kijk jij opeens verschrikt!<br/>Tess zegt: ik ben helemaal vergeten om de",
        "setnr_34/cond_c/list_a:Sarah en Lynn gaan beginnen aan een fotoshoot.<br/>Sarah zegt: wat is een goede plek?<br/>Lynn zegt: ik denk dat je het beste daar naast die",
        "setnr_35/cond_c/list_a:Nina is al heel de middag in de werkplaats.<br/>Ruben zegt: wat ben je aan het doen?<br/>Nina zegt: ik ben nu bezig met het repareren van de",
        "setnr_36/cond_c/list_a:Sven en Maud hebben het over familie.<br/>Sven vraagt: wat hebben jij en je broertje gemeen?<br/>Maud zegt: we houden toevallig allebei heel erg van",
        "setnr_37/cond_i/list_a:Cas komt terug van een geslaagd uitje met de familie.<br/>Merel vraagt: wat was het leukst?<br/>Cas zegt: iedereen heeft inderdaad genoten van de ",
        "setnr_38/cond_i/list_a:Rick loopt te ijsberen in de woonkamer. <br/>Eline vraagt: waar zit je mee?<br/>Rick zegt: ik probeer inderdaad een oplossing te bedenken voor de",
        "setnr_39/cond_i/list_a:Dylan komt geërgerd binnenlopen.<br/>Zijn vriendin vraagt: waarom ben je zo overstuur?<br/>Dylan zegt: we hebben inderdaad problemen met de",
        "setnr_40/cond_i/list_a:Jesse komt opgewekt de kamer binnen.<br/>Vera vraagt: waarom ben jij zo vrolijk?<br/>Jesse zegt: ik heb inderdaad een hele mooie",
        "setnr_41/cond_i/list_a:De shift van Thijmen zit erop en Robin neemt het over.<br/>Robin vraagt: wat zijn er nog aan klusjes?<br/>Thijmen zegt: je moet inderdaad niet vergeten de",
        "setnr_42/cond_i/list_a:Jasmijn komt een oud-collega van de universiteit tegen.<br/>Jasmijn vraagt: waar houdt je nieuwe vakgroep zich zoal mee bezig?<br/>Mike zegt: we doen inderdaad onderzoek naar",
        "setnr_43/cond_i/list_a:Gijs werkt als journalist bij een krant.<br/>Zijn collega vraagt: hoe is het interview gegaan?<br/>Gijs zegt: ze heeft me inderdaad veel verteld over het ",
        "setnr_44/cond_i/list_a:Milou en Jasper zoeken een origineel cadeau voor een van hun vrienden.<br/>Milou vraagt: waar dacht jij aan?<br/>Jasper zegt: het lijkt me inderdaad leuk om een grote",
        "setnr_45/cond_i/list_a:Marit en Tim zoeken muurverf voor hun nieuwe huis.<br/>Marit vraagt: wat vind je van deze kleur?<br/>Tim zegt: die doet me inderdaad denken aan mijn",
        "setnr_46/cond_i/list_a:Max en Roos zijn in een grote bouwmarkt en vragen een medewerker om hulp.<br/>Max vraagt: waar kunnen we de vlaggenstokhouders vinden?<br/>De medewerker zegt: ze liggen inderdaad in de gang waar ook de",
        "setnr_47/cond_i/list_a:Sem komt een aantal doze champagne afleveren bij een bedrijf.<br/>Sem vraagt: wat gaan jullie vieren?<br/>Willem zegt: we onthullen inderdaad de nieuwe",
        "setnr_48/cond_i/list_a:Amber zit met volle mond te praten.<br/>Tijn zegt: ik verstond je niet, wat zei je?<br/>Amber zegt: ik had het inderdaad over de nieuwe",
        "setnr_49/cond_i/list_a:Leny en Diedre zitten in oude fotoboeken te bladeren.<br/>Leny vraagt: waar is deze genomen?<br/>Diedre zegt: dat is inderdaad een foto van de ",
        "setnr_50/cond_i/list_a:Jos en Mirjam zitten op een bankje in het park.<br/>Jos vraagt: wat zit je allemaal voor je uit te staren?<br/>Mirjam zegt: ik was inderdaad met mijn gedachten bij",
        "setnr_51/cond_i/list_a:Frank heeft een nieuwe baan.<br/>Een vriend vraagt: hoe bevalt het je tot nu toe?<br/>Frank zegt: ik ben inderdaad nog niet gewend aan de",
        "setnr_52/cond_i/list_a:John en Ilona bezoeken een oud pand.<br/>John vraagt: wat zit er achter die deur?<br/>Ilona zegt: er liggen daar inderdaad een aantal",
        "setnr_53/cond_i/list_a:Caroline en Jochem kijken naar het 8 uur journaal.<br/>Caroline vraagt: wat vind je daar nou van? <br/>Jochem zegt: ik ben inderdaad niet zo op de hoogte van de",
        "setnr_54/cond_i/list_a:Nel zit wat te mompelen.<br/>Justus vraagt: waar heb je het allemaal over?<br/>Nel zegt: ik zei inderdaad dat we nog even de",
        "setnr_55/cond_i/list_a:Stan vertelt een vriend over zijn businessplan.<br/>Arjan vraagt: wat is het meest innovatieve aspect?<br/>Stan zegt: we zijn inderdaad de allereersten met zo'n grote",
        "setnr_56/cond_i/list_a:Elly en Teun hebben een luie zaterdagochtend.<br/>Elly vraagt: was jij nog bezig met de krant?<br/>Teun zegt: ik ben daarin inderdaad een stuk aan het lezen over",
        "setnr_57/cond_i/list_a:Juliette hoort plots een geluid en gaat erop af.<br/>Julliette vraagt: wat is er gebeurd?<br/>Haar dochter zegt: ik brak inderdaad per ongeluk het",
        "setnr_58/cond_i/list_a:Steffi komt terug van een vergadering.<br/>Haar collega vraagt: wat werd er aan belangrijke informatie verteld?<br/>Steffi zegt: ik verwachtte inderdaad al dat we het zouden hebben over de",
        "setnr_59/cond_i/list_a:Naomi en Jordy zitten bij de Nederlandse les.<br/>De docent vraagt: waar denken jullie dat deze tekst over gaat?<br/>Naomi zegt: het zal inderdaad wel weer over de",
        "setnr_60/cond_i/list_a:Rea en Fleur luisteren naar een popnummer.<br/>Rea vraagt: waar zou deze songtekst over gaan?<br/>Fleur zegt: ik geloof inderdaad dat ze zingt hoe het is om te",
        "setnr_61/cond_i/list_a:Bram en Shirley zijn pas verhuisd.<br/>Bram vraagt: waar ben je naar op zoek?<br/>Shirley zegt: we zijn inderdaad onze oude",
        "setnr_62/cond_i/list_a:Lotte en Andrea hebben het over de gebeurtenis van afgelopen weekend.<br/>Lotte vraagt: waarom heb je niks gedaan?<br/>Andrea zegt: ik heb inderdaad niet gemerkt dat er",
        "setnr_63/cond_i/list_a:Fabian en Simon praten over het toenemende vandalisme in de stad.<br/>Fabian vraagt: wat was er laatst ook alweer op het nieuws?<br/>Simon zegt: ik hoorde inderdaad over de vernielde",
        "setnr_64/cond_i/list_a:Bente's moeder is druk bezig in huis.<br/>Bente vraagt: waar kan ik je mee helpen? <br/>Haar moeder zegt: het zou inderdaad fijn zijn als je uit de kast de",
        "setnr_65/cond_i/list_a:Wessel komt trillend van de schrik binnen bij Bea.<br/>Bea zegt: wat kijk jij verschrikt!<br/>Wessel zegt: ik zag inderdaad op straat een",
        "setnr_66/cond_i/list_a:Marte heeft kort na haar verjaardag haar vriendin Annemarie op bezoek.<br/>Annemarie vraagt: wat voor cadeaus heb je gekregen voor je verjaardag?<br/>Marte zegt: ik heb inderdaad van mijn vriendinnen deze",
        "setnr_67/cond_i/list_a:Froukje is net terug van haar vakantie.<br/>Een vriendin vraagt: wat is je het best bijgebleven?<br/>Froukje zegt: ik heb inderdaad het meest genoten van de",
        "setnr_68/cond_i/list_a:Er wordt bij Niels en Rosanne een pakketje bezorgd.<br/>Rosanne vraagt: wat zit er in dat pakketje?<br/>Niels zegt: ik heb inderdaad op internet een nieuwe",
        "setnr_69/cond_i/list_a:Bart vindt het vreemd ruiken in de kamer van zijn vriendin Annemiek.<br/>Bart vraagt: wat is dat wat ik ruik?<br/>Annemiek zegt: ik morste inderdaad een beetje",
        "setnr_70/cond_i/list_a:Dennis komt met een grote glimlach de keuken ingelopen.<br/>Zijn vriendin zegt: wat kijk jij vrolijk!<br/>Bart zegt: ik heb inderdaad in de berging mijn allereerste",
        "setnr_71/cond_i/list_a:Kim en Levi ruimen de bijkeuken op.<br/>Kim zegt: wat ligt daar nou in de hoek?<br/>Levi zegt: daar heb ik inderdaad vorige week een",
        "setnr_72/cond_i/list_a:Bregje en Marian gaan een weekendje weg, en Bregje heeft een gigantische koffer bij zich.<br/>Marian vraagt: wat heb je toch allemaal bij je?<br/>Bregje zegt: ik heb inderdaad voor de zekerheid zelfs een",
        "setnr_73/cond_e/list_a:Petra laat haar vakantiefoto's aan Wernard zien.<br/>Wernard vraagt: wat was een van de highlights?<br/>Petra zegt: er komen eigenlijk heel veel mensen naar deze",
        "setnr_74/cond_e/list_a:Marlies en Isabeau volgen samen een schildercursus.<br/>Marlies vraagt: wat ga jij schilderen?<br/>Isabeau zegt: ik ga eigenlijk een mooie",
        "setnr_75/cond_e/list_a:Lisa en Bernard zitten een film te kijken, als Lisa ineens opspringt.<br/>Bernard vraagt: wat is er aan de hand?<br/>Lisa zegt: ik ben eigenlijk vergeten dat ik vandaag  een",
        "setnr_76/cond_e/list_a:Tijdens de lunch zitten Anneloes en Charlotte te roddelen over hun studiegenoten.<br/>Anneloes vraagt: waar liep Judith nou laatst weer zo'n aandacht mee te trekken?<br/>Charlotte zegt: ze liep eigenlijk met haar nieuwe",
        "setnr_77/cond_e/list_a:Clara gaat vanmiddag een workshop geven.<br/>Marloes vraagt: welke voorbereidingen moet je nog treffen?<br/>Clara zegt: ik heb eigenlijk nog een extra",
        "setnr_78/cond_e/list_a:Annie is met haar kleine baby bij haar zus Malou op bezoek.<br/>Malou zegt: wat is dat voor getik?<br/>Annie zegt: de baby zit eigenlijk met een",
        "setnr_79/cond_e/list_a:Voordat Lauran en Saar naar een verjaardag gaan, pakken ze nog snel de cadeautjes in.<br/>Lauran vraagt: waar ligt de schaar ook alweer?<br/>Saar zegt: die ligt eigenlijk in de la waar ook de",
        "setnr_80/cond_e/list_a:Hugo en Mante rijden over een afgelegen weggetje als Hugo ineens remt.<br/>Mante vraagt: wat is daar te zien?<br/>Hugo zegt: het lijkt eigenlijk alsof daar verderop een",
        "setnr_81/cond_e/list_a:Pieter en Olivia zijn helemaal klaar voor de verhuizing van morgen.<br/>Pieter vraagt: waarom ben jij die verhuisdoos weer aan het openmaken?<br/>Olivia zegt: ik heb eigenlijk voor vanavond nog een",
        "setnr_82/cond_e/list_a:Op de bridgeclub raken Wouter en Benjamin aan de praat over vroeger.<br/>Wouter vraagt: wat is jou het best bijgebleven van je jeugd?<br/>Benjamin zegt: ik weet eigenlijk nog goed dat ik op mijn tiende een",
        "setnr_83/cond_e/list_a:Vera en Peter merken dat er in huis een vreemde lucht hangt.<br/>Vera vraagt: waar zou het toch vandaan komen?<br/>Peter zegt: er is eigenlijk iets mis met de",
        "setnr_84/cond_e/list_a:Frans wil zijn huis verbouwen, maar de begroting is ruimschoots boven zijn budget.<br/>Gerard vraagt: hoe heb je het opgelost?<br/>Frans zegt: ik heb eigenlijk gekozen voor goedkopere",
        "setnr_85/cond_e/list_a:Rick en Adriana willen de zaak gaan verbouwen.<br/>Rick vraagt: hoe kunnen we ervoor zorgen dat het allemaal past?<br/>Adriana zegt: we moeten eigenlijk even opmeten hoe groot de",
        "setnr_86/cond_e/list_a:Elisa en Sandra bekijken samen oude klassenfoto's.<br/>Elisa vraagt: wat weet je nog van de basisschool?<br/>Sandra zegt: ik heb eigenlijk in groep 5 een werkstuk over",
        "setnr_87/cond_e/list_a:Iris en Martin spelen samen Memory.<br/>Iris vraagt: wat staat hier ook alweer op?<br/>Martin zegt: dat is eigenlijk het kaartje met de",
        "setnr_88/cond_e/list_a:Marco en Karin zijn met hun ouders aan het kwartetten.<br/>Marco zegt: jij bent aan de beurt.<br/>Karin zegt: ik wil eigenlijk van jou de",
        "setnr_89/cond_e/list_a:Paul en Saskia zijn een hele grote legpuzzel aan het maken.<br/>Paul vraagt: waar zou dit stukje passen?<br/>Saskia zegt: ik denk eigenlijk dat het onderdeel is van het",
        "setnr_90/cond_e/list_a:Marcel speelt met zijn vrienden het spel Hints.<br/>Marcel zegt: pfoe, wat zijn dat voor gebaren?<br/>Stefan zegt: hij beeldt eigenlijk uit dat hij een",
        "setnr_91/cond_e/list_a:Michel speelt met zijn vrienden het spel Pictionary.<br/>Michel zegt: wat is dat voor tekening?<br/>Roy zegt: het lijkt eigenlijk nog het meest op een",
        "setnr_92/cond_e/list_a:Maartje komt terug na een middagje spelen.<br/>Haar moeder vraagt: wat heb je nu weer meegenomen?<br/>Maartje zegt: ik heb eigenlijk van Jannies moeder deze bijzondere",
        "setnr_93/cond_e/list_a:Sylvia en Lucas spelen het spel Verboden Woord, waarbij Sylvia moet raden waar Lucas het over heeft.<br/>Lucas zegt: het is een gebruiksvoorwerp.<br/>Sylvia zegt: ik denk eigenlijk dat je het over een",
        "setnr_94/cond_e/list_a:Geert zit vol spanning te wachten tot Simone thuis komt.<br/>Geert vraagt: hoe was het?<br/>Simone zegt: ik had eigenlijk een goed gevoel bij de",
        "setnr_95/cond_e/list_a:Marijke komt terug van een workshop.<br/>Evert vraagt: wat heb je allemaal geleerd?<br/>Marijke zegt: ze hebben me eigenlijk van alles uitgelegd over de",
        "setnr_96/cond_e/list_a:Erwin en Louise denken na over een gerecht voor het avondeten.<br/>Erwin vraagt: welke dingen hebben we al in huis?<br/>Louise zegt: we hebben eigenlijk in de vooraadkast nog wat",
        "setnr_97/cond_e/list_a:Daphne en Michiel hebben het over hun oom Maurice.<br/>Daphne vraagt: wat vind jij van al zijn verhalen?<br/>Michiel zegt: ik word eigenlijk enthousiast als hij heeft over de",
        "setnr_98/cond_e/list_a:Sander en Kirsten hebben een nieuwe klusjesman in dienst.<br/>Sander vraagt: waar heeft hij vanmiddag aan gewerkt?<br/>Kirsten zegt: hij heeft eigenlijk een van de",
        "setnr_99/cond_e/list_a:Nathalie is bezig met het bouwen van haar eigen website.<br/>Pascal vraagt: welke informatie ontbreekt nog op je site?<br/>Nathalie zegt: ik heb eigenlijk nog geen aandacht besteed aan de",
        "setnr_100/cond_e/list_a:Janneke is sinds kort begonnen met een eigen blog.<br/>Haar zus vraagt: waar ga je zoal over schrijven?<br/>Janneke zegt: ik ben eigenlijk bezig met een verhaal over",
        "setnr_101/cond_e/list_a:Evelien vertelt Pim over haar werk, ze schrijft voor verschillende bladen.<br/>Pim vraagt: wat beschouw je als je beste werk?<br/>Evelien zegt: ik ben eigenlijk trots op mijn column over",
        "setnr_102/cond_e/list_a:Roel en Susanne hebben een fototentoonstelling bezocht.<br/>Roel vraagt: welk werk heeft de meeste indruk gemaakt?<br/>Susanne zegt: ik heb eigenlijk bewondering voor de fotoreportage over",
        "setnr_103/cond_e/list_a:Brian en Renske zjin bezig met een Franse spreekopdracht, als Brian plotseling stilvalt.<br/>Renske vraagt: wat wilde je zeggen?<br/>Brian zegt: ik kan eigenlijk niet op het Franse woord voor",
        "setnr_104/cond_e/list_a:Nico en Alex zitten samen in de trein en Nico bladert wat door het Metro krantje.<br/>Alex vraagt: wat ben je aan het lezen?<br/>Nico zegt: er staat eigenlijk een geinig artikeltje in over de",
        "setnr_105/cond_e/list_a:Harmen en Marleen zitten in de wachtkamer bij de tandarts en Marleen leest een tijdschrift. <br/>Harmen vraagt: waarom moet je zo lachen?<br/>Marleen zegt: er staat eigenlijk een grappige strip in over de",
        "setnr_106/cond_e/list_a:Ralph en Veronique zijn uit eten geweest bij een sterrenrestaurant.<br/>Ralph vraagt: wat was het meest bijzonder?<br/>Veronique zegt: er was eigenlijk een mousse van",
        "setnr_107/cond_e/list_a:Joep is in de kelder aan het rommelen.<br/>Jolijne vraagt: wat ben je daar beneden aan het doen?<br/>Joep zegt: ik ben eigenlijk op zoek naar de",
        "setnr_108/cond_e/list_a:Rutger werkt als restaurateur en is al een tijd bezig aan een groot project.<br/>Margriet vraagt: hoe ziet het er nu uit?<br/>Rutger zegt: je kunt eigenlijk nog steeds de beschadigingen op de",
        "setnr_1/cond_e/list_b:Hanna leest een moeilijke tekst voor.<br/>De docent vraagt: wat vond je lastig om uit te spreken?<br/>Hanna zegt: ik twijfelde eigenlijk bij het woord",
        "setnr_2/cond_e/list_b:Piet bladert in een woordenboek.<br/>Zijn vriendin vraagt: wat ben je aan het opzoeken?<br/>Piet zegt: ik wil eigenlijk de betekenis van het woord",
        "setnr_3/cond_e/list_b:Irene is een tekst aan het typen op haar laptop.<br/>Een studiegenoot vraagt: wat ben je aan het doen?<br/>Irene zegt: ik zoek eigenlijk een synoniem voor het woord",
        "setnr_4/cond_e/list_b:Anja wordt plotseling wakker.<br/>Haar man vraagt: wat is er aan de hand?<br/>Anja zegt: ik had eigenlijk een levendige droom over een",
        "setnr_5/cond_e/list_b:Cor en Denise lopen langs een grote menigte.<br/>Cor vraagt: wat doen al die mensen hier? <br/>Denise zegt: ze staan eigenlijk te kijken naar de",
        "setnr_6/cond_e/list_b:Wilma is de hele zolder aan het doorspitten.<br/>Haar zoon vraagt: wat ben je kwijt?<br/>Wilma zegt: ik ben eigenlijk op zoek naar de",
        "setnr_7/cond_e/list_b:Tieneke komt terug van een meeting.<br/>Haar collega vraagt: en, hoe was het?<br/>Tieneke zegt: het viel me eigenlijk op dat iedereen erg",
        "setnr_8/cond_e/list_b:Sven komt na een werkoverleg terug op kantoor.<br/>Zijn collega vraagt: hoe is het gegaan?<br/>Sven zegt: er waren eigenlijk veel vragen over de",
        "setnr_9/cond_e/list_b:Dennis laat Judith een van zijn kunstwerken zien.<br/>Dennis vraagt: wat vind je ervan?<br/>Judith zegt: het doet me eigenlijk denken aan mijn",
        "setnr_10/cond_e/list_b:Marjolein en Lennart zijn buiten aan het klussen.<br/>Marjolein vraagt: wat is dat, daar achter die stapel bakstenen?<br/>Lennart zegt: er liggen eigenlijk een aantal",
        "setnr_11/cond_e/list_b:Jaap en Sanne gaan er straks op uit om inkopen te doen voor de zaak.<br/>Jaap vraagt: wat moet er verder nog op het lijstje?<br/>Sanne zegt: we hebben eigenlijk nog wat van die",
        "setnr_12/cond_e/list_b:Ans en Ine gaan boodschappen doen.<br/>Ans vraagt: wat hebben we allemaal nodig?<br/>Ine zegt: we moeten eigenlijk een aantal",
        "setnr_13/cond_e/list_b:Monique en Sofia staan op het punt te vertrekken, als Monique opeens verschrikt kijkt.<br/>Sofia vraagt: wat is er?<br/>Monique zegt: ik ben eigenlijk vergeten om een",
        "setnr_14/cond_e/list_b:Mark en Erika kijken naar een talkshow op televisie.<br/>Mark vraagt: waarom mag jij die man niet?<br/>Erika zegt: hij vertelt eigenlijk allerlei leugens over de",
        "setnr_15/cond_e/list_b:Diana en Thomas ruimen hun oude DVD's op.<br/>Diana vraagt: waar ging deze film ook alweer over?<br/>Thomas zegt: ik herinner me eigenlijk alleen die scène met de",
        "setnr_16/cond_e/list_b:Tim werd net gebeld door zijn broer.<br/>Zijn vriendin vraagt: wat vertelde je broer?<br/>Tim zegt: hij heeft eigenlijk voor het eerst een",
        "setnr_17/cond_e/list_b:Claudia komt terug van een familiebijeenkomst.<br/>Haar vriend vraagt: waar heb je het met je nicht over gehad?<br/>Claudia zegt: ze vertelde eigenlijk dat ze een nieuwe ",
        "setnr_18/cond_e/list_b:René en Liam zijn aan het kletsen over hun kindertijd.<br/>René vraagt: wat herinner je je nog van de kleuterschool?<br/>Liam zegt: ik was  eigenlijk de enige die een",
        "setnr_19/cond_e/list_b:Henk en Wouter spreken elkaar op de vrijdagmiddagborrel. <br/>Henk vraagt: wat zijn je plannen voor dit weekend?<br/>Wouter zegt: ik heb eigenlijk afgesproken om naar de",
        "setnr_20/cond_e/list_b:Op hun derde date hebben Sharon en Arno het over vroeger. <br/>Sharon vraagt: wat deed je het liefst als kind?<br/>Arno zegt: ik maakte eigenlijk veel tekeningen van de",
        "setnr_21/cond_e/list_b:Esmee zit bij Puck thuis aan tafel, als Puck's moeder opeens opstaat.<br/>Esmee vraagt: wat gaat je moeder doen?<br/>Puck zegt: ze heeft  eigenlijk besloten om een",
        "setnr_22/cond_e/list_b:Martin en Elske hebben het over de voordelen het digitale tijdperk.<br/>Martin vraagt: wat is het laatste dat je op Wikipedia hebt opgezocht?<br/>Elske zegt: ik las eigenlijk gisteren nog een stuk over de",
        "setnr_23/cond_e/list_b:Leon leest een tijdschrift.<br/>Zijn huisgenoot vraagt: wat voor artikel ben je aan het lezen?<br/>Leon zegt: hierin wordt eigenlijk verteld hoe het zit met de",
        "setnr_24/cond_e/list_b:Dennis en Anna hebben in een restaurant het verrassingsmenu besteld. <br/>Dennis vraagt: wat vind je van dit gerecht?<br/>Anna zegt: ik ben eigenlijk niet zo dol op",
        "setnr_25/cond_e/list_b:Martin en Thea gaan hun favoriete serie kijken.<br/>Martin vraagt: waar ging de vorige aflevering ook alweer over?<br/>Thea zegt: ik herinner me eigenlijk dat Sarah van plan was om een",
        "setnr_26/cond_e/list_b:Olaf en Lotte stellen een evaluatierapport op.<br/>Olaf vraagt: wat voor problemen hebben zich voorgedaan?<br/>Lotte zegt: er waren eigenlijk veel klachten over de",
        "setnr_27/cond_e/list_b:Het is Pasen, en Martine en Bart verstoppen paaseitjes voor hun zoontje.<br/>Martine vraagt: waar heb je die laatste ook alweer gelaten?<br/>Bart zegt: ik heb hem eigenlijk daar achter die",
        "setnr_28/cond_e/list_b:Nancy en Linda spelen een spelletje.<br/>Nancy zegt: ik zie, ik zie, wat jij niet ziet en het is.. oranje!<br/>Linda zegt: ik denk eigenlijk dat het die",
        "setnr_29/cond_e/list_b:Vera en Fred maken een uitstapje.<br/>Vera vraagt: waarom is het hier zo druk?<br/>Fred zegt: ik denk dat ze eigenlijk afkomen op de",
        "setnr_30/cond_e/list_b:Loek is thuis zijn huiswerk aan het maken.<br/>Zijn vader vraagt: waar ben je mee bezig?<br/>Loek zegt: ik moet eigenlijk een verlag schrijven over de",
        "setnr_31/cond_e/list_b:Yvette is naar een conferentie geweest.<br/>Haar collega vraagt: wat vond je ervan?<br/>Yvette zegt: ik vond eigenlijk het praatje over",
        "setnr_32/cond_e/list_b:Sabine en Lea zijn aan het shoppen bij de Hema.<br/>Sabine vraagt: wat hebben ze op de 1e verdieping?<br/>Lea zegt: daar staan eigenlijk altijd de",
        "setnr_33/cond_e/list_b:Het is vrijdagmiddag, en Nick en Tess sluiten het kantoor af voor het weekend.<br/>Nick zegt: wat kijk jij opeens verschrikt!<br/>Tess zegt: ik ben eigenlijk vergeten om de",
        "setnr_34/cond_e/list_b:Sarah en Lynn gaan beginnen aan een fotoshoot.<br/>Sarah zegt: wat is een goede plek?<br/>Lynn zegt: ik denk dat je eigenlijk daar naast die",
        "setnr_35/cond_e/list_b:Nina is al heel de middag in de werkplaats.<br/>Ruben zegt: wat ben je aan het doen?<br/>Nina zegt: ik ben eigenlijk bezig met het repareren van de",
        "setnr_36/cond_e/list_b:Sven en Maud hebben het over familie.<br/>Sven vraagt: wat hebben jij en je broertje gemeen?<br/>Maud zegt: we houden eigenlijk allebei heel erg van",
        "setnr_37/cond_c/list_b:Cas komt terug van een geslaagd uitje met de familie.<br/>Merel vraagt: wat was het leukst?<br/>Cas zegt: iedereen heeft ontzettend genoten van de ",
        "setnr_38/cond_c/list_b:Rick loopt te ijsberen in de woonkamer. <br/>Eline vraagt: waar zit je mee?<br/>Rick zegt: ik probeer al een tijdje een oplossing te bedenken voor de",
        "setnr_39/cond_c/list_b:Dylan komt geërgerd binnenlopen.<br/>Zijn vriendin vraagt: waarom ben je zo overstuur?<br/>Dylan zegt: we hebben vandaag problemen met de",
        "setnr_40/cond_c/list_b:Jesse komt opgewekt de kamer binnen.<br/>Vera vraagt: waarom ben jij zo vrolijk?<br/>Jesse zegt: ik heb vandaag een hele mooie",
        "setnr_41/cond_c/list_b:De shift van Thijmen zit erop en Robin neemt het over.<br/>Robin vraagt: wat zijn er nog aan klusjes?<br/>Thijmen zegt: je moet in ieder geval niet vergeten de",
        "setnr_42/cond_c/list_b:Jasmijn komt een oud-collega van de universiteit tegen.<br/>Jasmijn vraagt: waar houdt je nieuwe vakgroep zich zoal mee bezig?<br/>Mike zegt: we doen voornamelijk onderzoek naar",
        "setnr_43/cond_c/list_b:Gijs werkt als journalist bij een krant.<br/>Zijn collega vraagt: hoe is het interview gegaan?<br/>Gijs zegt: ze heeft me ontzettend veel verteld over het",
        "setnr_44/cond_c/list_b:Milou en Jasper zoeken een origineel cadeau voor een van hun vrienden.<br/>Milou vraagt: waar dacht jij aan?<br/>Jasper zegt: het lijkt me erg leuk om een grote",
        "setnr_45/cond_c/list_b:Marit en Tim zoeken muurverf voor hun nieuwe huis.<br/>Marit vraagt: wat vind je van deze kleur?<br/>Tim zegt: die doet me sterk denken aan mijn",
        "setnr_46/cond_c/list_b:Max en Roos zijn in een grote bouwmarkt en vragen een medewerker om hulp.<br/>Max vraagt: waar kunnen we de vlaggenstokhouders vinden?<br/>De medewerker zegt: ze liggen verderop in de gang waar ook de",
        "setnr_47/cond_c/list_b:Sem komt een aantal doze champagne afleveren bij een bedrijf.<br/>Sem vraagt: wat gaan jullie vieren?<br/>Willem zegt: we onthullen straks de nieuwe",
        "setnr_48/cond_c/list_b:Amber zit met volle mond te praten.<br/>Tijn zegt: ik verstond je niet, wat zei je?<br/>Amber zegt: ik had het net over de nieuwe",
        "setnr_49/cond_c/list_b:Leny en Diedre zitten in oude fotoboeken te bladeren.<br/>Leny vraagt: waar is deze genomen?<br/>Diedre zegt: dat is volgens mij een foto van de",
        "setnr_50/cond_c/list_b:Jos en Mirjam zitten op een bankje in het park.<br/>Jos vraagt: wat zit je allemaal voor je uit te staren?<br/>Mirjam zegt: ik was even met mijn gedachten bij",
        "setnr_51/cond_c/list_b:Frank heeft een nieuwe baan.<br/>Een vriend vraagt: hoe bevalt het je tot nu toe?<br/>Frank zegt: ik ben alleen nog niet gewend aan de",
        "setnr_52/cond_c/list_b:John en Ilona bezoeken een oud pand.<br/>John vraagt: wat zit er achter die deur?<br/>Ilona zegt: er liggen daar volgens mij een aantal",
        "setnr_53/cond_c/list_b:Caroline en Jochem kijken naar het 8 uur journaal.<br/>Caroline vraagt: wat vind je daar nou van? <br/>Jochem zegt: ik ben geloof ik niet zo op de hoogte van de",
        "setnr_54/cond_c/list_b:Nel zit wat te mompelen.<br/>Justus vraagt: waar heb je het allemaal over?<br/>Nel zegt: ik zei net dat we nog even de",
        "setnr_55/cond_c/list_b:Stan vertelt een vriend over zijn businessplan.<br/>Arjan vraagt: wat is het meest innovatieve aspect?<br/>Stan zegt: we zijn denk ik de allereersten met zo'n grote",
        "setnr_56/cond_c/list_b:Elly en Teun hebben een luie zaterdagochtend.<br/>Elly vraagt: was jij nog bezig met de krant?<br/>Teun zegt: ik ben daarin nu een stuk aan het lezen over",
        "setnr_57/cond_c/list_b:Juliette hoort plots een geluid en gaat erop af.<br/>Julliette vraagt: wat is er gebeurd?<br/>Haar dochter zegt: ik brak net per ongeluk het",
        "setnr_58/cond_c/list_b:Steffi komt terug van een vergadering.<br/>Haar collega vraagt: wat werd er aan belangrijke informatie verteld?<br/>Steffi zegt: ik verwachtte gisteren al dat we het zouden hebben over de",
        "setnr_59/cond_c/list_b:Naomi en Jordy zitten bij de Nederlandse les.<br/>De docent vraagt: waar denken jullie dat deze tekst over gaat?<br/>Naomi zegt: het zal waarschijnlijk wel weer over de",
        "setnr_60/cond_c/list_b:Rea en Fleur luisteren naar een popnummer.<br/>Rea vraagt: waar zou deze songtekst over gaan?<br/>Fleur zegt: ik geloof persoonlijk dat ze zingt hoe het is om te",
        "setnr_61/cond_c/list_b:Bram en Shirley zijn pas verhuisd.<br/>Bram vraagt: waar ben je naar op zoek?<br/>Shirley zegt: we zijn volgens  mij onze oude",
        "setnr_62/cond_c/list_b:Lotte en Andrea hebben het over de gebeurtenis van afgelopen weekend.<br/>Lotte vraagt: waarom heb je niks gedaan?<br/>Andrea zegt: ik heb helemaal niet gemerkt dat er",
        "setnr_63/cond_c/list_b:Fabian en Simon praten over het toenemende vandalisme in de stad.<br/>Fabian vraagt: wat was er laatst ook alweer op het nieuws?<br/>Simon zegt: ik hoorde onlangs over de vernielde",
        "setnr_64/cond_c/list_b:Bente's moeder is druk bezig in huis.<br/>Bente vraagt: waar kan ik je mee helpen? <br/>Haar moeder zegt: het zou heel fijn zijn als je uit de kast de",
        "setnr_65/cond_c/list_b:Wessel komt trillend van de schrik binnen bij Bea.<br/>Bea zegt: wat kijk jij verschrikt!<br/>Wessel zegt: ik zag daarnet op straat een",
        "setnr_66/cond_c/list_b:Marte heeft kort na haar verjaardag haar vriendin Annemarie op bezoek.<br/>Annemarie vraagt: wat voor cadeaus heb je gekregen voor je verjaardag?<br/>Marte zegt: ik heb toen van mijn vriendinnen deze",
        "setnr_67/cond_c/list_b:Froukje is net terug van haar vakantie.<br/>Een vriendin vraagt: wat is je het best bijgebleven?<br/>Froukje zegt: ik heb echt het meest genoten van de",
        "setnr_68/cond_c/list_b:Er wordt bij Niels en Rosanne een pakketje bezorgd.<br/>Rosanne vraagt: wat zit er in dat pakketje?<br/>Niels zegt: ik heb gisteren op internet een nieuwe",
        "setnr_69/cond_c/list_b:Bart vindt het vreemd ruiken in de kamer van zijn vriendin Annemiek.<br/>Bart vraagt: wat is dat wat ik ruik?<br/>Annemiek zegt: ik morste daarnet een beetje",
        "setnr_70/cond_c/list_b:Dennis komt met een grote glimlach de keuken ingelopen.<br/>Zijn vriendin zegt: wat kijk jij vrolijk!<br/>Bart zegt: ik heb daarnet in de berging mijn allereerste",
        "setnr_71/cond_c/list_b:Kim en Levi ruimen de bijkeuken op.<br/>Kim zegt: wat ligt daar nou in de hoek?<br/>Levi zegt: daar heb ik geloof ik vorige week een",
        "setnr_72/cond_c/list_b:Bregje en Marian gaan een weekendje weg, en Bregje heeft een gigantische koffer bij zich.<br/>Marian vraagt: wat heb je toch allemaal bij je?<br/>Bregje zegt: ik heb daarstraks voor de zekerheid zelfs een",
        "setnr_73/cond_i/list_b:Petra laat haar vakantiefoto's aan Wernard zien.<br/>Wernard vraagt: wat was een van de highlights?<br/>Petra zegt: er komen inderdaad heel veel mensen naar deze",
        "setnr_74/cond_i/list_b:Marlies en Isabeau volgen samen een schildercursus.<br/>Marlies vraagt: wat ga jij schilderen?<br/>Isabeau zegt: ik ga inderdaad een mooie",
        "setnr_75/cond_i/list_b:Lisa en Bernard zitten een film te kijken, als Lisa ineens opspringt.<br/>Bernard vraagt: wat is er aan de hand?<br/>Lisa zegt: ik ben inderdaad vergeten dat ik vandaag  een",
        "setnr_76/cond_i/list_b:Tijdens de lunch zitten Anneloes en Charlotte te roddelen over hun studiegenoten.<br/>Anneloes vraagt: waar liep Judith nou laatst weer zo'n aandacht mee te trekken?<br/>Charlotte zegt: ze liep inderdaad met haar nieuwe",
        "setnr_77/cond_i/list_b:Clara gaat vanmiddag een workshop geven.<br/>Marloes vraagt: welke voorbereidingen moet je nog treffen?<br/>Clara zegt: ik heb inderdaad nog een extra",
        "setnr_78/cond_i/list_b:Annie is met haar kleine baby bij haar zus Malou op bezoek.<br/>Malou zegt: wat is dat voor getik?<br/>Annie zegt: de baby zit inderdaad met een",
        "setnr_79/cond_i/list_b:Voordat Lauran en Saar naar een verjaardag gaan, pakken ze nog snel de cadeautjes in.<br/>Lauran vraagt: waar ligt de schaar ook alweer?<br/>Saar zegt: die ligt inderdaad in de la waar ook de",
        "setnr_80/cond_i/list_b:Hugo en Mante rijden over een afgelegen weggetje als Hugo ineens remt.<br/>Mante vraagt: wat is daar te zien?<br/>Hugo zegt: het lijkt inderdaad alsof daar verderop een",
        "setnr_81/cond_i/list_b:Pieter en Olivia zijn helemaal klaar voor de verhuizing van morgen.<br/>Pieter vraagt: waarom ben jij die verhuisdoos weer aan het openmaken?<br/>Olivia zegt: ik heb inderdaad voor vanavond nog een",
        "setnr_82/cond_i/list_b:Op de bridgeclub raken Wouter en Benjamin aan de praat over vroeger.<br/>Wouter vraagt: wat is jou het best bijgebleven van je jeugd?<br/>Benjamin zegt: ik weet inderdaad nog goed dat ik op mijn tiende een",
        "setnr_83/cond_i/list_b:Vera en Peter merken dat er in huis een vreemde lucht hangt.<br/>Vera vraagt: waar zou het toch vandaan komen?<br/>Peter zegt: er is inderdaad iets mis met de",
        "setnr_84/cond_i/list_b:Frans wil zijn huis verbouwen, maar de begroting is ruimschoots boven zijn budget.<br/>Gerard vraagt: hoe heb je het opgelost?<br/>Frans zegt: ik heb inderdaad gekozen voor goedkopere",
        "setnr_85/cond_i/list_b:Rick en Adriana willen de zaak gaan verbouwen.<br/>Rick vraagt: hoe kunnen we ervoor zorgen dat het allemaal past?<br/>Adriana zegt: we moeten inderdaad even opmeten hoe groot de",
        "setnr_86/cond_i/list_b:Elisa en Sandra bekijken samen oude klassenfoto's.<br/>Elisa vraagt: wat weet je nog van de basisschool?<br/>Sandra zegt: ik heb inderdaad in groep 5 een werkstuk over",
        "setnr_87/cond_i/list_b:Iris en Martin spelen samen Memory.<br/>Iris vraagt: wat staat hier ook alweer op?<br/>Martin zegt: dat is inderdaad het kaartje met de",
        "setnr_88/cond_i/list_b:Marco en Karin zijn met hun ouders aan het kwartetten.<br/>Marco zegt: jij bent aan de beurt.<br/>Karin zegt: ik wil inderdaad van jou de",
        "setnr_89/cond_i/list_b:Paul en Saskia zijn een hele grote legpuzzel aan het maken.<br/>Paul vraagt: waar zou dit stukje passen?<br/>Saskia zegt: ik denk inderdaad dat het onderdeel is van het",
        "setnr_90/cond_i/list_b:Marcel speelt met zijn vrienden het spel Hints.<br/>Marcel zegt: pfoe, wat zijn dat voor gebaren?<br/>Stefan zegt: hij beeldt inderdaad uit dat hij een",
        "setnr_91/cond_i/list_b:Michel speelt met zijn vrienden het spel Pictionary.<br/>Michel zegt: wat is dat voor tekening?<br/>Roy zegt: het lijkt inderdaad nog het meest op een",
        "setnr_92/cond_i/list_b:Maartje komt terug na een middagje spelen.<br/>Haar moeder vraagt: wat heb je nu weer meegenomen?<br/>Maartje zegt: ik heb inderdaad van Jannies moeder deze bijzondere",
        "setnr_93/cond_i/list_b:Sylvia en Lucas spelen het spel Verboden Woord, waarbij Sylvia moet raden waar Lucas het over heeft.<br/>Lucas zegt: het is een gebruiksvoorwerp.<br/>Sylvia zegt: ik denk inderdaad dat je het over een",
        "setnr_94/cond_i/list_b:Geert zit vol spanning te wachten tot Simone thuis komt.<br/>Geert vraagt: hoe was het?<br/>Simone zegt: ik had inderdaad een goed gevoel bij de",
        "setnr_95/cond_i/list_b:Marijke komt terug van een workshop.<br/>Evert vraagt: wat heb je allemaal geleerd?<br/>Marijke zegt: ze hebben me inderdaad van alles uitgelegd over de",
        "setnr_96/cond_i/list_b:Erwin en Louise denken na over een gerecht voor het avondeten.<br/>Erwin vraagt: welke dingen hebben we al in huis?<br/>Louise zegt: we hebben inderdaad in de vooraadkast nog wat",
        "setnr_97/cond_i/list_b:Daphne en Michiel hebben het over hun oom Maurice.<br/>Daphne vraagt: wat vind jij van al zijn verhalen?<br/>Michiel zegt: ik word inderdaad enthousiast als hij heeft over de",
        "setnr_98/cond_i/list_b:Sander en Kirsten hebben een nieuwe klusjesman in dienst.<br/>Sander vraagt: waar heeft hij vanmiddag aan gewerkt?<br/>Kirsten zegt: hij heeft inderdaad een van de",
        "setnr_99/cond_i/list_b:Nathalie is bezig met het bouwen van haar eigen website.<br/>Pascal vraagt: welke informatie ontbreekt nog op je site?<br/>Nathalie zegt: ik heb inderdaad nog geen aandacht besteed aan de",
        "setnr_100/cond_i/list_b:Janneke is sinds kort begonnen met een eigen blog.<br/>Haar zus vraagt: waar ga je zoal over schrijven?<br/>Janneke zegt: ik ben inderdaad bezig met een verhaal over",
        "setnr_101/cond_i/list_b:Evelien vertelt Pim over haar werk, ze schrijft voor verschillende bladen.<br/>Pim vraagt: wat beschouw je als je beste werk?<br/>Evelien zegt: ik ben inderdaad trots op mijn column over",
        "setnr_102/cond_i/list_b:Roel en Susanne hebben een fototentoonstelling bezocht.<br/>Roel vraagt: welk werk heeft de meeste indruk gemaakt?<br/>Susanne zegt: ik heb inderdaad bewondering voor de fotoreportage over",
        "setnr_103/cond_i/list_b:Brian en Renske zjin bezig met een Franse spreekopdracht, als Brian plotseling stilvalt.<br/>Renske vraagt: wat wilde je zeggen?<br/>Brian zegt: ik kan inderdaad niet op het Franse woord voor",
        "setnr_104/cond_i/list_b:Nico en Alex zitten samen in de trein en Nico bladert wat door het Metro krantje.<br/>Alex vraagt: wat ben je aan het lezen?<br/>Nico zegt: er staat inderdaad een geinig artikeltje in over de",
        "setnr_105/cond_i/list_b:Harmen en Marleen zitten in de wachtkamer bij de tandarts en Marleen leest een tijdschrift. <br/>Harmen vraagt: waarom moet je zo lachen?<br/>Marleen zegt: er staat inderdaad een grappige strip in over de",
        "setnr_106/cond_i/list_b:Ralph en Veronique zijn uit eten geweest bij een sterrenrestaurant.<br/>Ralph vraagt: wat was het meest bijzonder?<br/>Veronique zegt: er was inderdaad een mousse van",
        "setnr_107/cond_i/list_b:Joep is in de kelder aan het rommelen.<br/>Jolijne vraagt: wat ben je daar beneden aan het doen?<br/>Joep zegt: ik ben inderdaad op zoek naar de",
        "setnr_108/cond_i/list_b:Rutger werkt als restaurateur en is al een tijd bezig aan een groot project.<br/>Margriet vraagt: hoe ziet het er nu uit?<br/>Rutger zegt: je kunt inderdaad nog steeds de beschadigingen op de",
        "setnr_1/cond_i/list_c:Hanna leest een moeilijke tekst voor.<br/>De docent vraagt: wat vond je lastig om uit te spreken?<br/>Hanna zegt: ik twijfelde inderdaad bij het woord",
        "setnr_2/cond_i/list_c:Piet bladert in een woordenboek.<br/>Zijn vriendin vraagt: wat ben je aan het opzoeken?<br/>Piet zegt: ik wil inderdaad de betekenis van het woord",
        "setnr_3/cond_i/list_c:Irene is een tekst aan het typen op haar laptop.<br/>Een studiegenoot vraagt: wat ben je aan het doen?<br/>Irene zegt: ik zoek inderdaad een synoniem voor het woord",
        "setnr_4/cond_i/list_c:Anja wordt plotseling wakker.<br/>Haar man vraagt: wat is er aan de hand?<br/>Anja zegt: ik had inderdaad een levendige droom over een",
        "setnr_5/cond_i/list_c:Cor en Denise lopen langs een grote menigte.<br/>Cor vraagt: wat doen al die mensen hier? <br/>Denise zegt: ze staan inderdaad te kijken naar de",
        "setnr_6/cond_i/list_c:Wilma is de hele zolder aan het doorspitten.<br/>Haar zoon vraagt: wat ben je kwijt?<br/>Wilma zegt: ik ben inderdaad op zoek naar de",
        "setnr_7/cond_i/list_c:Tieneke komt terug van een meeting.<br/>Haar collega vraagt: en, hoe was het?<br/>Tieneke zegt: het viel me inderdaad op dat iedereen erg",
        "setnr_8/cond_i/list_c:Sven komt na een werkoverleg terug op kantoor.<br/>Zijn collega vraagt: hoe is het gegaan?<br/>Sven zegt: er waren inderdaad veel vragen over de",
        "setnr_9/cond_i/list_c:Dennis laat Judith een van zijn kunstwerken zien.<br/>Dennis vraagt: wat vind je ervan?<br/>Judith zegt: het doet me inderdaad denken aan mijn",
        "setnr_10/cond_i/list_c:Marjolein en Lennart zijn buiten aan het klussen.<br/>Marjolein vraagt: wat is dat, daar achter die stapel bakstenen?<br/>Lennart zegt: er liggen inderdaad een aantal",
        "setnr_11/cond_i/list_c:Jaap en Sanne gaan er straks op uit om inkopen te doen voor de zaak.<br/>Jaap vraagt: wat moet er verder nog op het lijstje?<br/>Sanne zegt: we hebben inderdaad nog wat van die",
        "setnr_12/cond_i/list_c:Ans en Ine gaan boodschappen doen.<br/>Ans vraagt: wat hebben we allemaal nodig?<br/>Ine zegt: we moeten inderdaad een aantal",
        "setnr_13/cond_i/list_c:Monique en Sofia staan op het punt te vertrekken, als Monique opeens verschrikt kijkt.<br/>Sofia vraagt: wat is er?<br/>Monique zegt: ik ben inderdaad vergeten om een",
        "setnr_14/cond_i/list_c:Mark en Erika kijken naar een talkshow op televisie.<br/>Mark vraagt: waarom mag jij die man niet?<br/>Erika zegt: hij vertelt inderdaad allerlei leugens over de",
        "setnr_15/cond_i/list_c:Diana en Thomas ruimen hun oude DVD's op.<br/>Diana vraagt: waar ging deze film ook alweer over?<br/>Thomas zegt: ik herinner me inderdaad alleen die scène met de",
        "setnr_16/cond_i/list_c:Tim werd net gebeld door zijn broer.<br/>Zijn vriendin vraagt: wat vertelde je broer?<br/>Tim zegt: hij heeft inderdaad voor het eerst een",
        "setnr_17/cond_i/list_c:Claudia komt terug van een familiebijeenkomst.<br/>Haar vriend vraagt: waar heb je het met je nicht over gehad?<br/>Claudia zegt: ze vertelde inderdaad dat ze een nieuwe ",
        "setnr_18/cond_i/list_c:René en Liam zijn aan het kletsen over hun kindertijd.<br/>René vraagt: wat herinner je je nog van de kleuterschool?<br/>Liam zegt: ik was inderdaad de enige die een",
        "setnr_19/cond_i/list_c:Henk en Wouter spreken elkaar op de vrijdagmiddagborrel. <br/>Henk vraagt: wat zijn je plannen voor dit weekend?<br/>Wouter zegt: ik heb inderdaad afgesproken om naar de",
        "setnr_20/cond_i/list_c:Op hun derde date hebben Sharon en Arno het over vroeger. <br/>Sharon vraagt: wat deed je het liefst als kind?<br/>Arno zegt: ik maakte inderdaad veel tekeningen van de",
        "setnr_21/cond_i/list_c:Esmee zit bij Puck thuis aan tafel, als Puck's moeder opeens opstaat.<br/>Esmee vraagt: wat gaat je moeder doen?<br/>Puck zegt: ze heeft inderdaad besloten om een",
        "setnr_22/cond_i/list_c:Martin en Elske hebben het over de voordelen het digitale tijdperk.<br/>Martin vraagt: wat is het laatste dat je op Wikipedia hebt opgezocht?<br/>Elske zegt: ik las inderdaad gisteren nog een stuk over de",
        "setnr_23/cond_i/list_c:Leon leest een tijdschrift.<br/>Zijn huisgenoot vraagt: wat voor artikel ben je aan het lezen?<br/>Leon zegt: hierin wordt inderdaad verteld hoe het zit met de",
        "setnr_24/cond_i/list_c:Dennis en Anna hebben in een restaurant het verrassingsmenu besteld. <br/>Dennis vraagt: wat vind je van dit gerecht?<br/>Anna zegt: ik ben inderdaad niet zo dol op",
        "setnr_25/cond_i/list_c:Martin en Thea gaan hun favoriete serie kijken.<br/>Martin vraagt: waar ging de vorige aflevering ook alweer over?<br/>Thea zegt: ik herinner me inderdaad dat Sarah van plan was om een",
        "setnr_26/cond_i/list_c:Olaf en Lotte stellen een evaluatierapport op.<br/>Olaf vraagt: wat voor problemen hebben zich voorgedaan?<br/>Lotte zegt: er waren inderdaad veel klachten over de",
        "setnr_27/cond_i/list_c:Het is Pasen, en Martine en Bart verstoppen paaseitjes voor hun zoontje.<br/>Martine vraagt: waar heb je die laatste ook alweer gelaten?<br/>Bart zegt: ik heb hem inderdaad daar achter die",
        "setnr_28/cond_i/list_c:Nancy en Linda spelen een spelletje.<br/>Nancy zegt: ik zie, ik zie, wat jij niet ziet en het is.. oranje!<br/>Linda zegt: ik denk inderdaad dat het die",
        "setnr_29/cond_i/list_c:Vera en Fred maken een uitstapje.<br/>Vera vraagt: waarom is het hier zo druk?<br/>Fred zegt: ik denk dat ze inderdaad afkomen op de",
        "setnr_30/cond_i/list_c:Loek is thuis zijn huiswerk aan het maken.<br/>Zijn vader vraagt: waar ben je mee bezig?<br/>Loek zegt: ik moet inderdaad een verlag schrijven over de",
        "setnr_31/cond_i/list_c:Yvette is naar een conferentie geweest.<br/>Haar collega vraagt: wat vond je ervan?<br/>Yvette zegt: ik vond inderdaad het praatje over",
        "setnr_32/cond_i/list_c:Sabine en Lea zijn aan het shoppen bij de Hema.<br/>Sabine vraagt: wat hebben ze op de 1e verdieping?<br/>Lea zegt: daar staan inderdaad altijd de",
        "setnr_33/cond_i/list_c:Het is vrijdagmiddag, en Nick en Tess sluiten het kantoor af voor het weekend.<br/>Nick zegt: wat kijk jij opeens verschrikt!<br/>Tess zegt: ik ben  inderdaad vergeten om de",
        "setnr_34/cond_i/list_c:Sarah en Lynn gaan beginnen aan een fotoshoot.<br/>Sarah zegt: wat is een goede plek?<br/>Lynn zegt: ik denk dat je inderdaad daar naast die",
        "setnr_35/cond_i/list_c:Nina is al heel de middag in de werkplaats.<br/>Ruben zegt: wat ben je aan het doen?<br/>Nina zegt: ik ben inderdaad bezig met het repareren van de",
        "setnr_36/cond_i/list_c:Sven en Maud hebben het over familie.<br/>Sven vraagt: wat hebben jij en je broertje gemeen?<br/>Maud zegt: we houden inderdaad allebei heel erg van",
        "setnr_37/cond_e/list_c:Cas komt terug van een geslaagd uitje met de familie.<br/>Merel vraagt: wat was het leukst?<br/>Cas zegt: iedereen heeft eigenlijk genoten van de ",
        "setnr_38/cond_e/list_c:Rick loopt te ijsberen in de woonkamer. <br/>Eline vraagt: waar zit je mee?<br/>Rick zegt: ik probeer eigenlijk een oplossing te bedenken voor de",
        "setnr_39/cond_e/list_c:Dylan komt geërgerd binnenlopen.<br/>Zijn vriendin vraagt: waarom ben je zo overstuur?<br/>Dylan zegt: we hebben eigenlijk problemen met de",
        "setnr_40/cond_e/list_c:Jesse komt opgewekt de kamer binnen.<br/>Vera vraagt: waarom ben jij zo vrolijk?<br/>Jesse zegt: ik heb eigenlijk een hele mooie",
        "setnr_41/cond_e/list_c:De shift van Thijmen zit erop en Robin neemt het over.<br/>Robin vraagt: wat zijn er nog aan klusjes?<br/>Thijmen zegt: je moet eigenlijk niet vergeten de",
        "setnr_42/cond_e/list_c:Jasmijn komt een oud-collega van de universiteit tegen.<br/>Jasmijn vraagt: waar houdt je nieuwe vakgroep zich zoal mee bezig?<br/>Mike zegt: we doen eigenlijk onderzoek naar",
        "setnr_43/cond_e/list_c:Gijs werkt als journalist bij een krant.<br/>Zijn collega vraagt: hoe is het interview gegaan?<br/>Gijs zegt: ze heeft me eigenlijk veel verteld over het ",
        "setnr_44/cond_e/list_c:Milou en Jasper zoeken een origineel cadeau voor een van hun vrienden.<br/>Milou vraagt: waar dacht jij aan?<br/>Jasper zegt: het lijkt me eigenlijk leuk om een grote",
        "setnr_45/cond_e/list_c:Marit en Tim zoeken muurverf voor hun nieuwe huis.<br/>Marit vraagt: wat vind je van deze kleur?<br/>Tim zegt: die doet me eigenlijk denken aan mijn",
        "setnr_46/cond_e/list_c:Max en Roos zijn in een grote bouwmarkt en vragen een medewerker om hulp.<br/>Max vraagt: waar kunnen we de vlaggenstokhouders vinden?<br/>De medewerker zegt: ze liggen eigenlijk in de gang waar ook de",
        "setnr_47/cond_e/list_c:Sem komt een aantal doze champagne afleveren bij een bedrijf.<br/>Sem vraagt: wat gaan jullie vieren?<br/>Willem zegt: we onthullen eigenlijk de nieuwe",
        "setnr_48/cond_e/list_c:Amber zit met volle mond te praten.<br/>Tijn zegt: ik verstond je niet, wat zei je?<br/>Amber zegt: ik had het eigenlijk over de nieuwe",
        "setnr_49/cond_e/list_c:Leny en Diedre zitten in oude fotoboeken te bladeren.<br/>Leny vraagt: waar is deze genomen?<br/>Diedre zegt: dat is eigenlijk een foto van de ",
        "setnr_50/cond_e/list_c:Jos en Mirjam zitten op een bankje in het park.<br/>Jos vraagt: wat zit je allemaal voor je uit te staren?<br/>Mirjam zegt: ik was eigenlijk met mijn gedachten bij",
        "setnr_51/cond_e/list_c:Frank heeft een nieuwe baan.<br/>Een vriend vraagt: hoe bevalt het je tot nu toe?<br/>Frank zegt: ik ben eigenlijk nog niet gewend aan de",
        "setnr_52/cond_e/list_c:John en Ilona bezoeken een oud pand.<br/>John vraagt: wat zit er achter die deur?<br/>Ilona zegt: er liggen daar eigenlijk een aantal",
        "setnr_53/cond_e/list_c:Caroline en Jochem kijken naar het 8 uur journaal.<br/>Caroline vraagt: wat vind je daar nou van? <br/>Jochem zegt: ik ben eigenlijk niet zo op de hoogte van de",
        "setnr_54/cond_e/list_c:Nel zit wat te mompelen.<br/>Justus vraagt: waar heb je het allemaal over?<br/>Nel zegt: ik zei eigenlijk dat we nog even de",
        "setnr_55/cond_e/list_c:Stan vertelt een vriend over zijn businessplan.<br/>Arjan vraagt: wat is het meest innovatieve aspect?<br/>Stan zegt: we zijn eigenlijk de allereersten met zo'n grote",
        "setnr_56/cond_e/list_c:Elly en Teun hebben een luie zaterdagochtend.<br/>Elly vraagt: was jij nog bezig met de krant?<br/>Teun zegt: ik ben daarin eigenlijk een stuk aan het lezen over",
        "setnr_57/cond_e/list_c:Juliette hoort plots een geluid en gaat erop af.<br/>Julliette vraagt: wat is er gebeurd?<br/>Haar dochter zegt: ik brak eigenlijk per ongeluk het",
        "setnr_58/cond_e/list_c:Steffi komt terug van een vergadering.<br/>Haar collega vraagt: wat werd er aan belangrijke informatie verteld?<br/>Steffi zegt: ik verwachtte eigenlijk al dat we het zouden hebben over de",
        "setnr_59/cond_e/list_c:Naomi en Jordy zitten bij de Nederlandse les.<br/>De docent vraagt: waar denken jullie dat deze tekst over gaat?<br/>Naomi zegt: het zal eigenlijk wel weer over de",
        "setnr_60/cond_e/list_c:Rea en Fleur luisteren naar een popnummer.<br/>Rea vraagt: waar zou deze songtekst over gaan?<br/>Fleur zegt: ik geloof eigenlijk dat ze zingt hoe het is om te",
        "setnr_61/cond_e/list_c:Bram en Shirley zijn pas verhuisd.<br/>Bram vraagt: waar ben je naar op zoek?<br/>Shirley zegt: we zijn eigenlijk onze oude",
        "setnr_62/cond_e/list_c:Lotte en Andrea hebben het over de gebeurtenis van afgelopen weekend.<br/>Lotte vraagt: waarom heb je niks gedaan?<br/>Andrea zegt: ik heb eigenlijk niet gemerkt dat er",
        "setnr_63/cond_e/list_c:Fabian en Simon praten over het toenemende vandalisme in de stad.<br/>Fabian vraagt: wat was er laatst ook alweer op het nieuws?<br/>Simon zegt: ik hoorde eigenlijk over de vernielde",
        "setnr_64/cond_e/list_c:Bente's moeder is druk bezig in huis.<br/>Bente vraagt: waar kan ik je mee helpen? <br/>Haar moeder zegt: het zou eigenlijk fijn zijn als je uit de kast de",
        "setnr_65/cond_e/list_c:Wessel komt trillend van de schrik binnen bij Bea.<br/>Bea zegt: wat kijk jij verschrikt!<br/>Wessel zegt: ik zag eigenlijk op straat een",
        "setnr_66/cond_e/list_c:Marte heeft kort na haar verjaardag haar vriendin Annemarie op bezoek.<br/>Annemarie vraagt: wat voor cadeaus heb je gekregen voor je verjaardag?<br/>Marte zegt: ik heb eigenlijk van mijn vriendinnen deze",
        "setnr_67/cond_e/list_c:Froukje is net terug van haar vakantie.<br/>Een vriendin vraagt: wat is je het best bijgebleven?<br/>Froukje zegt: ik heb eigenlijk het meest genoten van de",
        "setnr_68/cond_e/list_c:Er wordt bij Niels en Rosanne een pakketje bezorgd.<br/>Rosanne vraagt: wat zit er in dat pakketje?<br/>Niels zegt: ik heb eigenlijk op internet een nieuwe",
        "setnr_69/cond_e/list_c:Bart vindt het vreemd ruiken in de kamer van zijn vriendin Annemiek.<br/>Bart vraagt: wat is dat wat ik ruik?<br/>Annemiek zegt: ik morste eigenlijk een beetje",
        "setnr_70/cond_e/list_c:Dennis komt met een grote glimlach de keuken ingelopen.<br/>Zijn vriendin zegt: wat kijk jij vrolijk!<br/>Bart zegt: ik heb eigenlijk in de berging mijn allereerste",
        "setnr_71/cond_e/list_c:Kim en Levi ruimen de bijkeuken op.<br/>Kim zegt: wat ligt daar nou in de hoek?<br/>Levi zegt: daar heb ik eigenlijk vorige week een",
        "setnr_72/cond_e/list_c:Bregje en Marian gaan een weekendje weg, en Bregje heeft een gigantische koffer bij zich.<br/>Marian vraagt: wat heb je toch allemaal bij je?<br/>Bregje zegt: ik heb eigenlijk voor de zekerheid zelfs een",
        "setnr_73/cond_c/list_c:Petra laat haar vakantiefoto's aan Wernard zien.<br/>Wernard vraagt: wat was een van de highlights?<br/>Petra zegt: er komen elk jaar heel veel mensen naar deze",
        "setnr_74/cond_c/list_c:Marlies en Isabeau volgen samen een schildercursus.<br/>Marlies vraagt: wat ga jij schilderen?<br/>Isabeau zegt: ik ga denk ik een mooie",
        "setnr_75/cond_c/list_c:Lisa en Bernard zitten een film te kijken, als Lisa ineens opspringt.<br/>Bernard vraagt: wat is er aan de hand?<br/>Lisa zegt: ik ben helemaal vergeten dat ik vandaag  een",
        "setnr_76/cond_c/list_c:Tijdens de lunch zitten Anneloes en Charlotte te roddelen over hun studiegenoten.<br/>Anneloes vraagt: waar liep Judith nou laatst weer zo'n aandacht mee te trekken?<br/>Charlotte zegt: ze liep onzettend met haar nieuwe",
        "setnr_77/cond_c/list_c:Clara gaat vanmiddag een workshop geven.<br/>Marloes vraagt: welke voorbereidingen moet je nog treffen?<br/>Clara zegt: ik heb alleen nog een extra",
        "setnr_78/cond_c/list_c:Annie is met haar kleine baby bij haar zus Malou op bezoek.<br/>Malou zegt: wat is dat voor getik?<br/>Annie zegt: de baby zit geloof ik met een",
        "setnr_79/cond_c/list_c:Voordat Lauran en Saar naar een verjaardag gaan, pakken ze nog snel de cadeautjes in.<br/>Lauran vraagt: waar ligt de schaar ook alweer?<br/>Saar zegt: die ligt volgens mij in de la waar ook de",
        "setnr_80/cond_c/list_c:Hugo en Mante rijden over een afgelegen weggetje als Hugo ineens remt.<br/>Mante vraagt: wat is daar te zien?<br/>Hugo zegt: het lijkt net alsof daar verderop een",
        "setnr_81/cond_c/list_c:Pieter en Olivia zijn helemaal klaar voor de verhuizing van morgen.<br/>Pieter vraagt: waarom ben jij die verhuisdoos weer aan het openmaken?<br/>Olivia zegt: ik heb waarschijnlijk voor vanavond nog een",
        "setnr_82/cond_c/list_c:Op de bridgeclub raken Wouter en Benjamin aan de praat over vroeger.<br/>Wouter vraagt: wat is jou het best bijgebleven van je jeugd?<br/>Benjamin zegt: ik weet vooral nog goed dat ik op mijn tiende een",
        "setnr_83/cond_c/list_c:Vera en Peter merken dat er in huis een vreemde lucht hangt.<br/>Vera vraagt: waar zou het toch vandaan komen?<br/>Peter zegt: er is volgens mij iets mis met de",
        "setnr_84/cond_c/list_c:Frans wil zijn huis verbouwen, maar de begroting is ruimschoots boven zijn budget.<br/>Gerard vraagt: hoe heb je het opgelost?<br/>Frans zegt: ik heb uiteindelijk gekozen voor goedkopere",
        "setnr_85/cond_c/list_c:Rick en Adriana willen de zaak gaan verbouwen.<br/>Rick vraagt: hoe kunnen we ervoor zorgen dat het allemaal past?<br/>Adriana zegt: we moeten nog even opmeten hoe groot de",
        "setnr_86/cond_c/list_c:Elisa en Sandra bekijken samen oude klassenfoto's.<br/>Elisa vraagt: wat weet je nog van de basisschool?<br/>Sandra zegt: ik heb geloof ik in groep 5 een werkstuk over",
        "setnr_87/cond_c/list_c:Iris en Martin spelen samen Memory.<br/>Iris vraagt: wat staat hier ook alweer op?<br/>Martin zegt: dat is volgens mij het kaartje met de",
        "setnr_88/cond_c/list_c:Marco en Karin zijn met hun ouders aan het kwartetten.<br/>Marco zegt: jij bent aan de beurt.<br/>Karin zegt: ik wil graag van jou de",
        "setnr_89/cond_c/list_c:Paul en Saskia zijn een hele grote legpuzzel aan het maken.<br/>Paul vraagt: waar zou dit stukje passen?<br/>Saskia zegt: ik denk echt dat het onderdeel is van het",
        "setnr_90/cond_c/list_c:Marcel speelt met zijn vrienden het spel Hints.<br/>Marcel zegt: pfoe, wat zijn dat voor gebaren?<br/>Stefan zegt: hij beeldt volgens mij uit dat hij een",
        "setnr_91/cond_c/list_c:Michel speelt met zijn vrienden het spel Pictionary.<br/>Michel zegt: wat is dat voor tekening?<br/>Roy zegt: het lijkt volgens mij nog het meest op een",
        "setnr_92/cond_c/list_c:Maartje komt terug na een middagje spelen.<br/>Haar moeder vraagt: wat heb je nu weer meegenomen?<br/>Maartje zegt: ik heb net van Jannies moeder deze bijzondere",
        "setnr_93/cond_c/list_c:Sylvia en Lucas spelen het spel Verboden Woord, waarbij Sylvia moet raden waar Lucas het over heeft.<br/>Lucas zegt: het is een gebruiksvoorwerp.<br/>Sylvia zegt: ik denk echt dat je het over een",
        "setnr_94/cond_c/list_c:Geert zit vol spanning te wachten tot Simone thuis komt.<br/>Geert vraagt: hoe was het?<br/>Simone zegt: ik had echt een goed gevoel bij de",
        "setnr_95/cond_c/list_c:Marijke komt terug van een workshop.<br/>Evert vraagt: wat heb je allemaal geleerd?<br/>Marijke zegt: ze hebben me werkelijk van alles uitgelegd over de",
        "setnr_96/cond_c/list_c:Erwin en Louise denken na over een gerecht voor het avondeten.<br/>Erwin vraagt: welke dingen hebben we al in huis?<br/>Louise zegt: we hebben volgens mij in de vooraadkast nog wat",
        "setnr_97/cond_c/list_c:Daphne en Michiel hebben het over hun oom Maurice.<br/>Daphne vraagt: wat vind jij van al zijn verhalen?<br/>Michiel zegt: ik word altijd enthousiast als hij heeft over de",
        "setnr_98/cond_c/list_c:Sander en Kirsten hebben een nieuwe klusjesman in dienst.<br/>Sander vraagt: waar heeft hij vanmiddag aan gewerkt?<br/>Kirsten zegt: hij heeft daarstraks een van de",
        "setnr_99/cond_c/list_c:Nathalie is bezig met het bouwen van haar eigen website.<br/>Pascal vraagt: welke informatie ontbreekt nog op je site?<br/>Nathalie zegt: ik heb tot nu toe nog geen aandacht besteed aan de",
        "setnr_100/cond_c/list_c:Janneke is sinds kort begonnen met een eigen blog.<br/>Haar zus vraagt: waar ga je zoal over schrijven?<br/>Janneke zegt: ik ben druk bezig met een verhaal over",
        "setnr_101/cond_c/list_c:Evelien vertelt Pim over haar werk, ze schrijft voor verschillende bladen.<br/>Pim vraagt: wat beschouw je als je beste werk?<br/>Evelien zegt: ik ben enorm trots op mijn column over",
        "setnr_102/cond_c/list_c:Roel en Susanne hebben een fototentoonstelling bezocht.<br/>Roel vraagt: welk werk heeft de meeste indruk gemaakt?<br/>Susanne zegt: ik heb veel bewondering voor de fotoreportage over",
        "setnr_103/cond_c/list_c:Brian en Renske zjin bezig met een Franse spreekopdracht, als Brian plotseling stilvalt.<br/>Renske vraagt: wat wilde je zeggen?<br/>Brian zegt: ik kan opeens niet op het Franse woord voor",
        "setnr_104/cond_c/list_c:Nico en Alex zitten samen in de trein en Nico bladert wat door het Metro krantje.<br/>Alex vraagt: wat ben je aan het lezen?<br/>Nico zegt: er staat vandaag een geinig artikeltje in over de",
        "setnr_105/cond_c/list_c:Harmen en Marleen zitten in de wachtkamer bij de tandarts en Marleen leest een tijdschrift. <br/>Harmen vraagt: waarom moet je zo lachen?<br/>Marleen zegt: er staat hier een grappige strip in over de",
        "setnr_106/cond_c/list_c:Ralph en Veronique zijn uit eten geweest bij een sterrenrestaurant.<br/>Ralph vraagt: wat was het meest bijzonder?<br/>Veronique zegt: er was onder andere een mousse van",
        "setnr_107/cond_c/list_c:Joep is in de kelder aan het rommelen.<br/>Jolijne vraagt: wat ben je daar beneden aan het doen?<br/>Joep zegt: ik ben eventjes op zoek naar de",
        "setnr_108/cond_c/list_c:Rutger werkt als restaurateur en is al een tijd bezig aan een groot project.<br/>Margriet vraagt: hoe ziet het er nu uit?<br/>Rutger zegt: je kunt momenteel nog steeds de beschadigingen op de"};

    @Override
    public WizardUtilScreen[] getScreenData() {
        return new WizardUtilScreen[]{new WizardUtilScreen() {
            @Override
            public WizardUtilText getAgreementScreen() {
                return new WizardUtilText() {
                    @Override
                    public String getText() {
                        return agreementScreenText;
                    }

                    @Override
                    public String getButonLabel() {
                        return "Akkoord";
                    }

                    @Override
                    public String getTitle() {
                        return "Toestemming";
                    }

                    @Override
                    public String getMenuLabel() {
                        return "Terug";
                    }
                };
            }

        }, new WizardUtilScreen() {
            @Override
            public WizardUtilText getTextScreen() {
                return new WizardUtilText() {
                    @Override
                    public String getText() {
                        return informationScreenText;
                    }

                    @Override
                    public String getButonLabel() {
                        return "volgende [ spatiebalk ]";
                    }

                    @Override
                    public String getTitle() {
                        return "Informatie";
                    }

                    @Override
                    public String getMenuLabel() {
                        return "Terug";
                    }

                    @Override
                    public String getHotkey() {
                        return "SPACE";
                    }
                };
            }
        }, new WizardUtilScreen() {
            @Override
            public WizardUtilMetadata getMetadataScreen() {
                return new WizardUtilMetadata() {
                    @Override
                    public String getHotkey() {
                        return null;
                    }

                    @Override
                    public String getButonLabel() {
                        return "Volgende";
                    }

                    @Override
                    public String getText() {
                        return null;
                    }

                    @Override
                    public String getTitle() {
                        return "Gegevens";
                    }

                    @Override
                    public String getMenuLabel() {
                        return "Terug";
                    }

                    @Override
                    public boolean isSendData() {
                        return true;
                    }

                    @Override
                    public String getConnectionErrorText() {
                        return "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.";
                    }

                    @Override
                    public String[] getMetadataFields() {
                        return new String[]{
                            "workerId:Proefpersoon ID:.'{'3,'}':Voer minimaal drie letters.",
                            "age:Leeftijd:[0-9]+:Voer een getal.",
                            //            "firstName:Voornaam:.'{'3,'}':Voer minimaal drie letters.",
                            //            "lastName:Achternaam:.'{'3,'}':Voer minimaal drie letters.",
                            //            "education:Opleidingsniveau:primair onderwijs (basisschool)|voortgezet onderwijs|middelbaar beroepsonderwijs (MBO)|hoger onderwijs (HBO, universiteit)|anders:.",
                            "education:Opleidingsniveau:basisonderwijs|voortgezet onderwijs|MBO|HBO|universiteit|anders:.",
                            "educationOther:Opleidingsniveau (anders, namelijk):.*:.",
                            //            "education:Opleidingsniveau:.'{'3,'}':Voer minimaal drie letters.",
                            "gender:Geslacht:|man|vrouw|anders:."
                        };
                    }
                };
            }
        }, new WizardUtilScreen() {
            @Override
            public WizardUtilStimuliData getStimuliData() {
                return new WizardUtilStimuliData() {
                    @Override
                    public String getStimuliName() {
                        return "Zinnen afmaken";
                    }

                    @Override
                    public WizardUtilStimuliData.StimuliFields[] getStimuliFields() {
                        return new StimuliFields[]{StimuliFields.label};
                    }

                    @Override
                    public boolean isShowProgress() {
                        return false;
                    }

                    @Override
                    public String[] getStimuliArray() {
                        for (int index = 0; index < stimuliString.length; index++) {
                            stimuliString[index] = stimuliString[index].replace("\n", "<br/>");
                        }
                        return stimuliString;
                    }

                    @Override
                    public String[] getRandomStimuliTags() {
                        return new String[]{"list_a",
                            "list_b",
                            "list_c"};
                    }

                    @Override
                    public String getFreeTextAllowedCharCodes() {
                        return null;
                    }

                    @Override
                    public String getFreeTextValidationMessage() {
                        return "Vul één of enkele woorden in die volgens u het beste aan het eind van de zin passen.";
                    }

                    @Override
                    public String getFreeTextValidationRegex() {
                        return ".{2,}";
                    }
                };
            }
        }, new WizardUtilScreen() {

            @Override
            public WizardUtilSendData getSendDataScreen() {

                return new WizardUtilSendData() {
                    @Override
                    public String getText() {
                        return completionScreenText1;
                    }

                    @Override
                    public String getPostCompletionCodeText() {
                        return "<br/>"
                                + "Het bovenstaande nummer is het bewijs dat u het experiment heeft voltooid, en is vereist voor het in orde maken van uw vergoeding. Gelieve het nummer te kopieëren en per email terug te sturen naar de onderzoeker:  <br/>"
                                + "marlou.rasenberg@mpi.nl";
                    }

                    @Override
                    public boolean isAllowUserRestart() {
                        return false;
                    }

                    @Override
                    public boolean isGenerateCompletionCode() {
                        return true;
                    }
                };
            }
        }};
    }

    @Override
    public WizardUtilEnum getTemplateType() {
        return WizardUtilEnum.SentenceCompletion;
    }

    @Override
    public boolean isShowMenuBar() {
        return true;
    }

    @Override
    public String getExperimentTitle() {
        return "Parcours01";
    }
}
