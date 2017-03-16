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
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardCompletionScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardEditUserScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardRandomStimulusScreen;
import nl.mpi.tg.eg.experimentdesigner.model.wizard.WizardTextScreen;

/**
 * @since Jan 25, 2017 16:39:41 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class Parcours {

    private final WizardController wizardController = new WizardController();
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
            + "<b>Doel van het onderzoek</b><br/>"
            + "U gaat meedoen aan een onderzoek waarin we de mate van overeenstemming tussen mensen onderzoeken met betrekking tot hun voorspellingen over het einde van een zin.<br/>"
            + "<b>Instructies</b><br/>"
            + "U krijgt steeds korte gesprekjes te zien die eindigen met een onafgemaakte zin. Maak de zin telkens af met <b>één of enkele woorden</b>. U hoeft niet te lang na te denken; vul gewoon in wat het eerste in u opkomt.<br/>"
            + "<br/>"
            + "Een voorbeeld:<br/>"
            + " Jan en Piet rijden terug naar de camping na een dagje strand.<br/>"
            + "Jan vraagt: Wat zullen we zo eens gaan doen?<br/>"
            + "Piet zegt: Ik heb best zin in een potje ...<br/>"
            + "<br/>"
            + "Hier zou u bijvoorbeeld \"badminton\" in kunnen vullen.<br/>"
            + " <br/>"
            + " Nog een voorbeeld:<br/>"
            + " Loek heeft zijn broer aan de telefoon.<br/>"
            + "Zijn broer vraagt: Heb je nog iets leuks gedaan dit weekend?<br/>"
            + "Loek zegt: Ik ben zaterdagmiddag weer eens naar de ...<br/>"
            + " <br/>"
            + "Deze zin zou u bijvoorbeeld kunnen afmaken met: \"sportschool geweest.\"<br/>"
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
            + "In totaal zal het onderzoek ongeveer <b>60 minuten</b> duren. Zorg ervoor dat u tijdens het experiment in een rustige omgeving zit, zonder afleiding van bijvoorbeeld uw mobiele telefoon, TV of van andere mensen. <br/>"
            + "<br/>"
            + "Zo begint u:<br/>"
            + "Door te klikken op 'Volgende' gaat u naar de volgende pagina. Voer hier alsublieft uw gegevens in: uw voornaam, achternaam, leeftijd en geslacht. Vul bij proefpersoon ID het nummer in dat u per email van de onderzoeker heeft ontvangen. <br/>"
            + "<br/>"
            + "U begint het onderzoek door weer te klikken op 'Volgende'. U ziet dan een gesprekje, dat eindigt met een onafgemaakte zin. U kunt de zin afmaken door één of enkele woorden in te vullen in de textbox onder het gesprekje.  Door te klikken op 'Volgende' gaat u naar het volgende gesprekje. Let op: het is niet mogelijk om terug te gaan naar eerdere gesprekjes!<br/>"
            + "<br/>"
            + "<b>Proefpersonen die niet mee kunnen doen aan het onderzoek</b><br/>"
            + "Het is niet mogelijk om deel te nemen aan dit onderzoek als Nederlands niet uw moedertaal is of wanneer u jonger bent dan 18 jaar of ouder dan 45 jaar. <br/>"
            + "<br/>"
            + "<b>Vergoeding</b><br/>"
            + "Voor deelname aan dit experiment ontvant u <b>€ 10,-</b>. Dit bedrag zal worden overgemaakt op uw bankrekening. <br/>"
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
        "setnr_1/cond_c/list_a:Piet is jarig en Anja neemt hem mee naar de beste Italiaan van de stad.<br/>"
        + "Anja vraagt: je gaat vast een klassieker bestellen? <br/>"
        + "Piet zegt: ik heb ontzettende trek in een ",
        "setnr_2/cond_c/list_a:Hanna doet auditie bij een strijkkwartet en speelt de sterren van de hemel. <br/>"
        + "De dirigent vraagt: je hebt vast veel ervaring?<br/>"
        + "Hanna zegt: ik speelde vroeger heel vaak vaak op de ",
        "setnr_3/cond_c/list_a:Simon gaat met zijn zus een weekendje naar Londen.<br/>"
        + "Zijn zus vraagt: we gaan zeker onder het kanaal door naar Engeland?<br/>"
        + "Simon zegt: ik heb gisteren twee tickets voor de  ",
        "setnr_4/cond_c/list_a:Ondanks haar angst voor dieren is Irene naar het circus geweest.<br/>"
        + "Jan vraagt: je vond de dierenact zeker doodeng?<br/>"
        + "Irene zegt: ik schrok heel erg van die grote ",
        "setnr_5/cond_c/list_a:In de Efteling komt Cor vloekend van de pijn het snoephuisje van Hans en Grietje uit.<br/>"
        + "Wilma vraagt: de deurposten waren vast te laag voor jou?<br/>"
        + "Cor zegt: ik stootte er keihard met mijn ",
        "setnr_6/cond_c/list_a:Denise is sinds twee weken op dieet en is al 5 kilo afgevallen.<br/>"
        + "Sanne vraagt: je eet zeker heel gezond tussen de middag?<br/>"
        + "Denise zegt: ik koop op mijn werk meestal een ",
        "setnr_7/cond_c/list_a:Ondanks de regen gaan Sophie en Ewoud te voet naar het centrum.<br/>"
        + "Ewoud vraagt: moet je geen andere schoenen aandoen?<br/>"
        + "Sophie zegt: ik was net van plan mijn ",
        "setnr_8/cond_c/list_a:Tineke is net terug van haar vakantie op Ibiza, en ze is hartstikke bruin.<br/>"
        + "Marjolein vraagt: het was daar zeker heerlijk weer?<br/>"
        + "Tineke zegt: ik heb de hele week behoorlijk wat ",
        "setnr_9/cond_c/list_a:Het is zaterdagavond, en Judith komt opgedoft de kamer in lopen.<br/>"
        + "Lennart vraagt: jij gaat zeker met Lara dansen vanavond?<br/>"
        + "Judith zegt: we hebben gisteren afgesproken om naar de  ",
        "setnr_10/cond_c/list_a:Sven heeft een jaar in China gewoond, en Dennis heeft beloofd voor hem te koken.<br/>"
        + "Sven zegt: ik hoop dat je Hollandse kost hebt gemaakt!<br/>"
        + "Dennis zegt: ik heb vanmiddag een flinke pan ",
        "setnr_11/cond_c/list_a:Bea en Christine gaan na een bergtocht door de sneeuw een cafÈ in om op te warmen. <br/>"
        + "Bea vraagt: neem jij ook een lekker warm drankje?<br/>"
        + "Christine zegt: ik heb ontzettende trek in een ",
        "setnr_12/cond_c/list_a:Ans heeft een enorme bos bloemen van Jaap gekregen.<br/>"
        + "Jaap vraagt: heb je ze wel een mooi plekje gegeven?<br/>"
        + "Ans zegt: ik heb ze daar in die grote ronde ",
        "setnr_13/cond_c/list_a:Monique is zwanger, en komt haar vriendin Ine tegen in de kroeg.<br/>"
        + "Ine vraagt: jij bent zeker de Bob vandaag?<br/>"
        + "Monique zegt: ik stond net op het punt een ",
        "setnr_14/cond_c/list_a:Angela heeft een week bij Betty gelogeerd, en haar moeder komt haar weer ophalen.<br/>"
        + "Betty vraagt: is Angela altijd al zo'n ongezonde eter geweest?<br/>"
        + "Haar moeder zegt: ze eet thuis bijna elke dag een bord ",
        "setnr_15/cond_c/list_a:Het is Valentijnsdag en Jurgen komt Wim tegen bij de bloemist.<br/>"
        + "Wim vraagt: ga je je vriendin met een bloemetje verrassen?<br/>"
        + "Jurgen zegt: ik wil haar graag een mooie witte ",
        "setnr_16/cond_c/list_a:Sofia staat bekend om haar Italiaanse kookkunsten.<br/>"
        + "Erika vraagt: krijgen we als toetje weer een van je specialiteiten?<br/>"
        + "Sofia zegt: ik heb vanmiddag een hele schaal ",
        "setnr_17/cond_c/list_a:Omdat Mark gaat verhuizen, gaat hij samen met Pieter shoppen.<br/>"
        + "Pieter vraagt: je wilt dus ook nieuwe meubels aanschaffen?<br/>"
        + "Mark zegt: ik wil onder andere een nieuwe  ",
        "setnr_18/cond_c/list_a:Diana is met haar klas van de kunstacademie een weekend naar Parijs geweest.<br/>"
        + "Haar vriendin vraagt: jullie hebben zeker veel kunst gezien?<br/>"
        + "Diana zegt: we zijn daar elke dag naar een ",
        "setnr_19/cond_c/list_a:Thomas maakt zich op voor een cultureel dagje Amsterdam.<br/>"
        + "Tim vraagt: je gaat zeker de meesterwerken in het Rijksmuseum bewonderen? <br/>"
        + "Thomas zegt: ik kijk er vooral naar uit om de ",
        "setnr_20/cond_c/list_a:RenÈ moet naar de tandarts om drie gaatjes te laten boren.<br/>"
        + "De tandarts vraagt: eet je toevallig veel zoetigheid?<br/>"
        + "RenÈ zegt: ik heb volgens mij de laatste tijd best vaak ",
        "setnr_21/cond_c/list_a:Liam wil zijn afstuderen groots vieren. <br/>"
        + "Zijn moeder vraagt: wil je al je vrienden hier thuis uitnodigen?<br/>"
        + "Liam zegt: ik wil heel graag een feestje in de  ",
        "setnr_22/cond_c/list_a:Op een zonnige middag heeft Casper zijn moeder aan de telefoon.<br/>"
        + "Zijn moeder vraagt: ga je straks buiten eten met dit mooie weer?<br/>"
        + "Casper zegt: ik ga gezellig met vrienden ",
        "setnr_23/cond_c/list_a:Arno gaat met Claudia een weekendje logeren op de boerderij van haar ouders.<br/>"
        + "Arno vraagt: het ontbijt komt zeker rechtstreeks uit het kippenhok?<br/>"
        + "Claudia zegt: ze hebben hier alleen maar verse ",
        "setnr_24/cond_c/list_a:Joris zit aan de waterkant als Henk langs komt lopen.<br/>"
        + "Henk vraagt: zit je lekker te vissen?<br/>"
        + "Joris zegt: ik probeer even mijn nieuwe ",
        "setnr_25/cond_c/list_a:Iris ziet erg bleek en ze voelt zich niet zo lekker.<br/>"
        + "Haar zus vraagt: je laat je toch wel onderzoeken vandaag?<br/>"
        + "Iris zegt: ik moet vanmiddag om twee uur bij de ",
        "setnr_26/cond_c/list_a:Na een saai college zit Sharon uitgebreid te gapen. <br/>"
        + "Olivier zegt: volgens mij kan jij wel een caffeÔneboost gebruiken!<br/>"
        + "Sharon zegt: ik heb best trek in een ",
        "setnr_27/cond_c/list_a:Ilse is samen met Sybrine de was aan het ophangen.<br/>"
        + "Sybrine vraagt: heb je iets speciaals gebruikt om het beddengoed zo zacht te krijgen? <br/>"
        + "Ilse zegt: ik heb toevallig gisteren nieuwe ",
        "setnr_28/cond_c/list_a:Puck en Pleun gaan op bezoek bij Esmee, die met gym haar arm gebroken heeft.<br/>"
        + "Puck vraagt: zullen we iets gezonds voor haar meenemen?<br/>"
        + "Pleun zegt: het lijkt me best leuk om een ",
        "setnr_29/cond_c/list_a:De hond begint te blaffen en rondjes te rennen als Marius de kamer binnenkomt. <br/>"
        + "Evelien vraagt: ga je een rondje wandelen met Fikkie?<br/>"
        + "Marius zegt: ik was net op zoek naar zijn ",
        "setnr_30/cond_c/list_a:Wouter is ceremoniemeester op de bruiloft van zijn beste vriend Klaas.<br/>"
        + "Klaas vraagt: je hebt toch wel aan de alcohol gedacht?<br/>"
        + "Wouter zegt: ik heb minstens twaalf flessen ",
        "setnr_31/cond_c/list_a:De avond voor zijn zoontjes verjaardag komt Henk de trap afgestommeld. <br/>"
        + "Jolanda vraagt: je hebt zeker de versiering van zolder gehaald?<br/>"
        + "Henk zegt: ik heb alvast de doos met ",
        "setnr_32/cond_c/list_a:Anna en Elske willen graag goede cijfers halen dit studiejaar.<br/>"
        + "Anna vraagt: ga jij ook alvast het tentamen van volgende week voorbereiden?<br/>"
        + "Elske zegt: ik wil vandaag een paar uur naar de ",
        "setnr_33/cond_c/list_a:Martin en Leon lopen samen over de markt.<br/>"
        + "Martin vraagt: heb jij ook zo'n trek in gebakken vis?<br/>"
        + "Leon zegt: doe mij zometeen maar een bakje ",
        "setnr_34/cond_c/list_a:Op zijn vrije dag komt Olaf Thea tegen in het dorp.<br/>"
        + "Thea vraagt: ben je ook op weg naar de manege?<br/>"
        + "Olaf zegt: ik wilde best een ritje gaan maken op mijn ",
        "setnr_35/cond_c/list_a:Teun en Bella zitten 's avonds te lezen op de bank, als Teun zijn boek even opzij legt.<br/>"
        + "Bella vraagt: het wordt wel een beetje donker om te lezen hË?<br/>"
        + "Teun zegt: ik was net van plan de ",
        "setnr_36/cond_c/list_a:Stefan gaat vanavond een serenade brengen aan de liefde van zijn leven.<br/>"
        + "Bernard vraagt: je gaat jezelf zeker begeleiden bij het zingen?<br/>"
        + "Stefan zegt: ik heb vanmorgen besloten om mijn ",
        "setnr_37/cond_c/list_a:Dennis en Lotte liggen in hun tent te lezen, als Dennis wild om zich heen begint te zwaaien.<br/>"
        + "Dennis vraagt: hoorde jij ook dat irritante gezoem?<br/>"
        + "Lotte zegt: ik dacht echt dat ik een ",
        "setnr_38/cond_c/list_a:Bij binnenkomst trekt Felix met zijn kaplaarzen een spoor van modder door de kamer.<br/>"
        + "Mien vraagt:  zeg, maak je de vloer wel weer schoon?<br/>"
        + "Felix zegt: ik was sowieso van plan om in de bijkeuken een ",
        "setnr_39/cond_c/list_a:Martine en Nancy zitten een zielige film te kijken als Martine ineens sniffend opstaat.<br/>"
        + "Nancy vraagt: je wilt zeker je tranen wegvegen?<br/>"
        + "Martine zegt: ik ga even een pakje ",
        "setnr_40/cond_c/list_a:Bruno komt de kamer binnen als zijn moeder verwoed de bank aan het stofzuigen is.<br/>"
        + "Zijn moeder vraagt: jij hebt zeker zitten kruimelen voor de tv?<br/>"
        + "Bruno zegt: ik heb gisteren tijdens de film een zak ",
        "setnr_41/cond_c/list_a:Vlak voor zijn sollicitatiegesprek heeft Maarten een broodje met pesto gegeten. <br/>"
        + "Maarten vraagt: heb ik nog iets tussen mijn tanden?<br/>"
        + "Bea zegt: ik zie geloof ik daar rechts iets in je ",
        "setnr_42/cond_c/list_a:Hanneke brengt een bezoek aan een brouwerij in de stad.<br/>"
        + "De barman vraagt: lust je iets van de tap?<br/>"
        + "Hanneke zegt: ik wil onzettend graag jullie nieuwste ",
        "setnr_43/cond_c/list_a:Mira en haar zus Linda gaan een avondje naar de opera.<br/>"
        + "Mira vraagt: ga jij je ook mooi uitdossen?<br/>"
        + "Linda zegt: ik wil heel graag mijn nieuwe ",
        "setnr_44/cond_c/list_a:Het is winter en na schooltijd komt Vera Bart tegen in de fietsenstalling.<br/>"
        + "Vera vraagt: jij gaat toch ook mee naar de ijsbaan?<br/>"
        + "Bart zegt: ik heb best zin om lekker te ",
        "setnr_45/cond_c/list_a:Jans team heeft de eerste voetbalwedstrijd na de winterstop ruim gewonnen.<br/>"
        + "Ans vraagt: je hebt zeker geen bal doorgelaten?<br/>"
        + "Jan zegt: ik heb uren kou staan lijden in de  ",
        "setnr_46/cond_c/list_a:Op oudjaarsavond komt Fred met een auto vol dozen terug uit de stad.<br/>"
        + "Zijn buurman zegt: zo te zien ga je weer knallend het jaar uit?<br/>"
        + "Fred zegt: ik heb dit jaar voor een kapitaal aan ",
        "setnr_47/cond_c/list_a:Na een concert in het stadion komt Loek Sabine tegen.<br/>"
        + "Loek vraagt: ga je ook met het openbaar vervoer terug naar de stad?<br/>"
        + "Sabine zegt: ik sta hier al een tijdje op een ",
        "setnr_48/cond_c/list_a:Yvette komt lijkbleek thuis van de kermis.<br/>"
        + "Haar vader vraagt: had je weer last van hoogtevrees?<br/>"
        + "Yvette zegt: ik werd helemaal niet goed in het ",
        "setnr_49/cond_c/list_a:Lea en Fred hebben een heerlijke steak gegeten in een restaurant, en Lea komt terug van het toilet. <br/>"
        + "Lea vraagt: weet de ober al dat we willen betalen?<br/>"
        + "Fred zegt: ik heb net gevraagd of we de ",
        "setnr_50/cond_c/list_a:Nick en Lynn plannen een citytrip voor de meivakantie.<br/>"
        + "Nick vraagt: jij wilt zeker naar Spanje toe?<br/>"
        + "Lynn zegt: het lijkt me erg leuk om naar ",
        "setnr_51/cond_c/list_a:Sarah en Koen zijn bezig om een wandelvakantie te boeken.<br/>"
        + "Sarah vraagt: is ScandinaviÎ een idee?<br/>"
        + "Koen zegt: ik zou heel graag naar ",
        "setnr_52/cond_c/list_a:Tess en Ruben willen een weekje naar een all-inclusive hotel in de zon. <br/>"
        + "Ruben vraagt: jij dacht aan de Antillen toch? <br/>"
        + "Tess zegt: ik wil heel graag naar ",
        "setnr_53/cond_c/list_a:Emma en Isa gaan een middagje shoppen, Emma past een groene blouse.<br/>"
        + "Emma vraagt: groen is echt mijn kleur hË?<br/>"
        + "Isa zegt: ik vind echt dat deze blouse je ",
        "setnr_54/cond_c/list_a:Rianne en Lotte werken op een HR-afdeling en zijn in gesprek over de sollicitanten.<br/>"
        + "Rianne vraagt: gaat jouw voorkeur ook uit naar Pieter? <br/>"
        + "Lotte zegt: ik vind hem vooralsnog vergeleken met de rest de ",
        "setnr_55/cond_c/list_a:Nina en Sven willen dit weekend uit eten, en bekijken de website van een Grieks restaurant.<br/>"
        + "Sven vraagt: dit restaurant was de vorige keer erg goed bevallen hË?<br/>"
        + "Nina zegt: ik herinner me nog dat de moussaka daar ",
        "setnr_56/cond_c/list_a:Jesse en Maud willen binnenkort met de familie uit eten, en bekijken de website van een restaurant. <br/>"
        + "Maud zegt: hier hebben we de vorige keer goed gegeten hË?<br/>"
        + "Jesse zegt: ik herinner me nog dat de porties daar erg ",
        "setnr_57/cond_c/list_a:Lieke en Finn werken aan de promotie van een evenement, en zoeken een drukker voor de posters.<br/>"
        + "Lieke zegt: volgens mij waren we de vorige keer erg tevreden over deze drukker?<br/>"
        + "Finn zegt: ik herinner me nog dat de kwaliteit ",
        "setnr_58/cond_c/list_a:Cas en Merel willen een rondreis door de VS gaan maken en zoeken een geschikt reisbureau.<br/>"
        + "Cas zegt: de vorige keer werden we bij dit reisbureau erg goed geholpen toch?<br/>"
        + "Merel zegt: ik herinner me nog dat de service ",
        "setnr_59/cond_c/list_a:Eline en Rick willen komende week de achtertuin flink opknappen. <br/>"
        + "Eline vraagt: zullen we van dat hoekje een moestuin maken?<br/>"
        + "Rick zegt: het lijkt me heel leuk om daar allerlei soorten ",
        "setnr_60/cond_c/list_a:Dylan en Esmee willen hun woonkamer opnieuw indelen.<br/>"
        + "Dylan zegt: hier wilden we de eethoek van maken toch?<br/>"
        + "Esmee zegt: die plek lijkt me heel geschikt om de grote houten ",
        "setnr_61/cond_i/list_a:Vera en Thijmen bespreken de inrichting van hun nieuwe woning.<br/>"
        + "Vera vraagt: zullen we voor boven de tafel een mooi kunstwerk uitzoeken?<br/>"
        + "Thijmen zegt: het lijkt me inderdaad mooi om daar een ",
        "setnr_62/cond_i/list_a:Robin en Jasmijn gaan vanmiddag naar het asiel om een huisdier uit te zoeken.<br/>"
        + "Robin vraagt: je houdt van grote honden toch?<br/>"
        + "Jasmijn zegt: ik wil inderdaad graag een mooie ",
        "setnr_63/cond_i/list_a:Demi en Jasper gaan uit eten bij een restaurant aan de haven.<br/>"
        + "Jasper vraagt: ga jij ook voor een van de visgerechten?<br/>"
        + "Demi zegt: ik wil inderdaad als hoofdgerecht de ",
        "setnr_64/cond_i/list_a:Mike en Marit gaan in de kerstvakantie uit eten bij een nieuw restaurant in de stad.<br/>"
        + "Marit zegt: we zouden hier zeker de wildgerechten moeten uitproberen!<br/>"
        + "Mike zegt: ik wil inderdaad als hoofdgerecht ",
        "setnr_65/cond_i/list_a:Gijs en Milou zijn een dagje naar een pretpark, en hebben alle attracties al gehad.<br/>"
        + "Gijs vraagt: zullen we als afsluiter nog even in een enge attractie gaan?<br/>"
        + "Milou zegt: ik wil inderdaad nog een ritje in de ",
        "setnr_66/cond_i/list_a:Tim en Roos zijn een weekendje in Parijs, en bespreken bij het ontbijt welke musea ze al hebben bezocht.<br/>"
        + "Een andere hotelgast vraagt: gaan jullie vanmiddag nog naar een van de beroemde kerken?<br/>"
        + "Tim zegt: we willen inderdaad  nog heel graag de ",
        "setnr_67/cond_i/list_a:Het is zomer en na schooltijd komt Max Sem tegen op het schoolplein.<br/>"
        + "Max vraagt: jij gaat toch ook mee naar het strand?<br/>"
        + "Sem zegt: ik heb inderdaad zin om lekker te gaan ",
        "setnr_68/cond_i/list_a:Na de laatste les komen Amber en Diedre elkaar tegen bij de kluisjes.<br/>"
        + "Amber vraagt: jij gaat toch ook mee naar het winkelcentrum?<br/>"
        + "Diedre zegt: ik heb inderdaad zin om te ",
        "setnr_69/cond_i/list_a:Willem en Tijn voeren een discussie over milieu en energie.<br/>"
        + "Willem zegt: ik denk dat groene stroom de toekomst is!<br/>"
        + "Tijn zegt: ik voorspel inderdaad een filnke toename van het aantal ",
        "setnr_70/cond_i/list_a:Jos en Leny ploffen op de bank voor een filmavondje. <br/>"
        + "Jos zegt: zullen we er iets lekker zouts bij pakken?<br/>"
        + "Leny zegt: ik heb inderdaad al de hele dag zin in ",
        "setnr_71/cond_i/list_a:Frank en Mirjam drinken een biertje op een terrasje in de namiddagzon. <br/>"
        + "Frank zegt: zullen we er lekker wat snacks uit de frituur bij bestellen?<br/>"
        + "Mirjam zegt: ik heb inderdaad trek in een portie ",
        "setnr_72/cond_i/list_a:John en Caroline zitten in een restaurant en hebben net hun hoofdgerecht op.<br/>"
        + "Caroline zegt: heb jij ook zin in iets zoets na?<br/>"
        + "John zegt: ik heb inderdaad zin in een ",
        "setnr_73/cond_i/list_a:Nel en Piet bespreken met hun dochter wat ze vanmiddag met hun kleinzoon gaan doen.<br/>"
        + "Piet zegt: met deze sneeuw kunnen we wel de helling af!<br/>"
        + "Nel zegt: volgens mij wil hij inderdaad niets liever dan  ",
        "setnr_74/cond_i/list_a:Ilse en Patty hebben het over de boeken van hun lievelings auteur.<br/>"
        + "Ilse zegt: haar vroege werk is wel het mooiste hË?<br/>"
        + "Patty zegt: ik vind haar debuutroman inderdaad een van haar ",
        "setnr_75/cond_i/list_a:Jochem en Ilona hebben het over de acteur Tom Hanks.<br/>"
        + "Jochem zegt: hij is erg gegroeid als acteur in zijn carriËre hË?<br/>"
        + "Ilona zegt: ik vind hem inderdaad in zijn laastste film het ",
        "setnr_76/cond_i/list_a:Stan en Romy luisteren naar muziek van hun favoriete rockband.<br/>"
        + "Stan zegt: hun recente muziek is echt geweldig!<br/>"
        + "Romy zegt: ik vind dat ze inderdaad met die nieuwe drummer steeds ",
        "setnr_77/cond_i/list_a:Rianne en Justus bespreken wat voor sport leuk zou zijn voor hun zoon.<br/>"
        + "Rianne vraagt: zullen we hem voor een teamsport opgeven?<br/>"
        + "Justus zegt: ik denk dat hij het inderdaad leuk zou vinden om op ",
        "setnr_78/cond_i/list_a:Ben en Kim staan te kibbelen; Kim laat altijd de huistelefoon ergens slingeren.<br/>"
        + "Ben vraagt: heb je de telefoon zeker weer in de slaapkamer laten liggen?<br/>"
        + "Kim zegt: ik denk dat ik hem inderdaad bovenop het ",
        "setnr_79/cond_i/list_a:Peter en Elly zoeken bij de bloemist iets uit voor hun moeders verjaardag.<br/>"
        + "Peter vraagt: ze vindt warme kleuren erg mooi hË?<br/>"
        + "Elly zegt: ze houdt inderdaad erg veel van  ",
        "setnr_80/cond_i/list_a:Anne en Jens hebben het over de verkiezingen.<br/>"
        + "Anne vraagt: jij gaat vast voor een partij die zich sterk maakt voor het milieu?<br/>"
        + "Jens zegt: ik ga inderdaad stemmen op een ",
        "setnr_81/cond_i/list_a:Senna en Thom kijken in de gids naar wat er vanavond op tv komt.<br/>"
        + "Senne vraagt: jij houdt toch ook van thrillers?<br/>"
        + "Thom zegt: ik kijk inderdaad graag naar hele ",
        "setnr_82/cond_i/list_a:Juliette en Teun maken plannen voor hun weekendje aan zee.<br/>"
        + "Juliette zegt: jij wil vast lekker de natuur in gaan!<br/>"
        + "Teun zegt: ik wil inderdaad een wandeling door de ",
        "setnr_83/cond_i/list_a:Het is winter en Steffi en Arjan zijn bij de ijsbaan op het dorpsplein.<br/>"
        + "Steffi vraagt: wil jij ook een lekker warm drankje? <br/>"
        + "Arjan zegt: ik heb inderdaad veel zin in ",
        "setnr_84/cond_i/list_a:Naomi bespreekt haar toekomstplannen met haar ouders.<br/>"
        + "Haar vader zegt: je zat aan een leidinggevende functie in de horeca te denken toch?<br/>"
        + "Naomi zegt: ik wil inderdaad manager van een ",
        "setnr_85/cond_i/list_a:Rea studeert Frans, en praat met haar ouders over wat voor werk ze daarna wil gaan doen.<br/>"
        + "Haar moeder zegt: je wil graag het onderwijs in toch?<br/>"
        + "Rea zegt: ik wil inderdaad op zoek naar een baan als ",
        "setnr_86/cond_i/list_a:Fleur is met haar opa en oma aan het praten over haar toekomstplannen.<br/>"
        + "Haar oma zegt: je wil een baan in de zorg gaan zoeken toch?<br/>"
        + "Fleur zegt: ik wil inderdaad graag gaan werken als ",
        "setnr_87/cond_i/list_a:Jordy en Elke zijn bij een outdoorzaak aan het kijken naar skimutsen.<br/>"
        + "Jordy zegt: zo'n felgekleurde is echt wat voor jou!<br/>"
        + "Elke zegt: ik wil inderdaad graag die ",
        "setnr_88/cond_i/list_a:Hannah heeft binnenkort een belangrijke meeting, en ze zoekt samen met Julie naar een outfit. <br/>"
        + "Julie zegt: ik denk dat je het beste voor een blouse met een neutrale kleur kunt gaan.<br/>"
        + "Hannah zegt: ik wil inderdaad graag die ",
        "setnr_89/cond_i/list_a:Bram wil graag een nieuwe auto kopen, maar weet nog niet in welke kleur. <br/>"
        + "Zijn zus zegt: als je een opvallende kleur neemt, vind je 'm altijd snel terug in een parkeergarage!<br/>"
        + "Bram zegt: ik zat er inderdaad aan te denken om een ",
        "setnr_90/cond_i/list_a:Shirley komt erg moe terug van haar werk en ploft naast Fabian neer op de bank. <br/>"
        + "Fabian zegt: als ik jou was zou ik vanavond lekker wat ontspannen!<br/>"
        + "Shirley zegt: het lijkt me inderdaad goed om even te gaan ",
        "setnr_91/cond_i/list_a:De eigenaar en manager van een restaurant zijn in gesprek; het loopt niet goed. <br/>"
        + "De eigenaar vraagt: zou het met ons keukenteam te maken hebben?<br/>"
        + "De manager zegt: ik heb inderdaad de indruk dat het aan de nieuwe ",
        "setnr_92/cond_i/list_a:De eigenaar en manager van een camping zijn in gesprek over hun tarieven.<br/>"
        + "De eigenaar zegt: misschien kunnen we onze luxe accomodaties wat duurder maken?<br/>"
        + "De manager zegt: ik denk dat we inderdaad iets aan de prijzen van de ",
        "setnr_93/cond_i/list_a:De medewerkers van een hotel zijn in gesprek; er is veel ontevredenheid onder de gasten.<br/>"
        + "Wouter zegt: misschien zijn de kamers erg vies?<br/>"
        + "Andre zegt: er zijn inderdaad veel klachten over de ",
        "setnr_94/cond_i/list_a:Lotte is wel 15 kilo kwijt geraakt in de afgelopen maanden.<br/>"
        + "Haar zus vraagt: ben je zoveel afgevallen door je nieuwe eetpatroon?<br/>"
        + "Lotte zegt: het komt inderdaad doordat ik een zeer strikt ",
        "setnr_95/cond_i/list_a:Dorien en Bea willen graag flink afvallen voordat het bikiniseizoen aanbreekt.<br/>"
        + "Dorien vraagt: ga jij ook weer trainen vanavond?<br/>"
        + "Bea zegt: ik wil inderdaad nog even naar de ",
        "setnr_96/cond_i/list_a:Violaine en Maaike zitten op een terrasje en bekijken de kaart met fruitsappen.<br/>"
        + "Violaine vraagt: neem jij ook een sapje met tropisch fruit?<br/>"
        + "Maaike zegt: ik wil inderdaad een smoothie met  ",
        "setnr_97/cond_i/list_a:Simon en Andrea willen graag meer wandelen, en denken erover zich aan te sluiten bij een clubje.<br/>"
        + "Simon zegt: volgens mij lopen ze bij die club stevig door!<br/>"
        + "Andrea zegt: ik hoorde inderdaad dat hun wandeltempo heel ",
        "setnr_98/cond_i/list_a:Bente overweegt over te stappen naar een nieuwe dansschool die binnenkort opent.<br/>"
        + "Haar zus zegt: volgens mij betaal je bij die school veel minder lesgeld!<br/>"
        + "Bente zegt: ik had inderdaad gezien dat de lessen daar een stuk ",
        "setnr_99/cond_i/list_a:Wessel en Marte willen een dagje naar een pretpark, maar hebben een hekel aan lang wachten.<br/>"
        + "Wessel zegt: misschien is eind november een optie?<br/>"
        + "Marte zegt: ik denk dat de rijen dan inderdaad best wel  ",
        "setnr_100/cond_i/list_a:Justin en Niels hebben net hun wiskundehuiswerk voor volgende week opgekregen.<br/>"
        + "Justin zegt: pfoe het volgende hoofdstuk wordt echt verschrikkelijk!<br/>"
        + "Niels zegt: die sommen zien er inderdaad heel erg ",
        "setnr_101/cond_i/list_a:De docent geeft de leerlingen huiswerk op dat ze individueel of in tweetallen kunnen maken.<br/>"
        + "De docent vraagt: Thom, jij wil deze opdracht zeker alleen doen?<br/>"
        + "Thom zegt: in mijn eentje is het inderdaad een heel stuk ",
        "setnr_102/cond_i/list_a:Bart wil na de zomer graag op muziekles.<br/>"
        + "Zijn broer zegt: ik denk dat een blaasinstrument wel iets voor jou is!<br/>"
        + "Bart zegt: ik wil inderdaad leren om een ",
        "setnr_103/cond_i/list_a:Froukje gaat binnenkort beginnen met danslessen.<br/>"
        + "Haar vriendin vraagt: je zat te denken aan klassieke dans toch?<br/>"
        + "Froukje zegt: ik wil me inderdaad gaan opgeven voor  ",
        "setnr_104/cond_i/list_a:Dennis meldt zich aan bij een nieuw sportcentrum.<br/>"
        + "Zijn vriendin vraagt: je wil daar graag groepslessen in vechtsport nemen toch?<br/>"
        + "Dennis zegt: ik wil me inderdaad gaan opgeven voor  ",
        "setnr_105/cond_i/list_a:Op de camping willen Levi en Noah met hun vriendje Mike spelen.<br/>"
        + "Levi zegt: hij is vast al aan het eten..<br/>"
        + "Noah zegt: ik zag hem inderdaad in de richting van de  ",
        "setnr_106/cond_i/list_a:Gerard en Annemarie hebben het over de schoolresultaten van hun dochter.<br/>"
        + "Gerard zegt: vooral met wiskunde heeft ze erg veel moeite hËÖ<br/>"
        + "Annemarie zegt: ik begreep inderdaad dat haar gemiddelde zo rond de  ",
        "setnr_107/cond_i/list_a:Henk en Annemieke bespreken hoe het met de schoolprestaties van hun zoon gaat.<br/>"
        + "Annemieke zegt: een talenknobbel heeft hij niet hË?<br/>"
        + "Henk zegt: hij heeft inderdaad de meeste moeite met het vak ",
        "setnr_108/cond_i/list_a:Bregje en Rosanne gaan samen een middagje shoppen.<br/>"
        + "Bregje zegt: je wil graag wat leuke winterkleding kopen toch?<br/>"
        + "Rosanne zegt: ik hoop inderdaad een mooie nieuwe ",
        "setnr_109/cond_i/list_a:Kim en Sophia gaan samen eventjes naar de stad.<br/>"
        + "Kim vraagt: wilde je nog gaan kijken voor een mooi sieraad?<br/>"
        + "Sophia zegt: ik wil inderdaad gaan kijken voor een ",
        "setnr_110/cond_i/list_a:Ome Willem uit Canada komt een weekje op bezoek bij zijn familie.<br/>"
        + "Anna zegt: hij zou het vast super vinden als we 'm meenemen op een cultureel uitje!<br/>"
        + "Petra zegt: ik denk dat hij inderdaad graag naar een ",
        "setnr_111/cond_i/list_a:Theo geeft een feestje bij hem thuis.<br/>"
        + "Frank zegt: ik heb Theo al een tijdje niet gezien.. zou hij wiet aan het halen zijn?<br/>"
        + "Janne zegt: ik hoorde hem inderdaad zeggen dat hij even naar de ",
        "setnr_112/cond_i/list_a:Bernard en Petra werken aan de faculteit Betawetenschappen, en zoeken hun collega Wernard.<br/>"
        + "Bernard zegt: misschien is hij in het laboratorium aan het werk?<br/>"
        + "Petra zegt: ik geloof dat hij inderdaad bezig is met een ",
        "setnr_113/cond_i/list_a:Nu hun college willen David en Max gaan lunchen met Nick, dus David stuurt een berichtje naar Nick. <br/>"
        + "David zegt: en, komt Nick er al aan?<br/>"
        + "Max zegt: hij is inderdaad op weg naar de ",
        "setnr_114/cond_i/list_a:Het bestuur van een basisschool bespreekt de lage CITO-scores van groep 8.<br/>"
        + "Herman vraagt: zou het door een gebrek aan motivatie komen? <br/>"
        + "Rita zegt: ik denk inderdaad dat het aan de matige inzet van de  ",
        "setnr_115/cond_i/list_a:Marian was weer eens haar sleutelbos kwijt, en komt opgelucht aangelopen. <br/>"
        + "Fred zegt: ze lagen zeker gewoon in de keuken?<br/>"
        + "Marian zegt: ik heb ze inderdaad teruggevonden op het ",
        "setnr_116/cond_i/list_a:Marlies is pas verhuisd; Isabeau en Lisa komen zaterdag op bezoek in haar nieuwe huisje.<br/>"
        + "Isabeau vraagt: zullen we iets moois uitzoeken voor in de woonkamer?<br/>"
        + "Lisa zegt: het lijkt me inderdaad leuk om een ",
        "setnr_117/cond_i/list_a:Marloes en Charlotte gaan bij Kirsten op kraamvisite. <br/>"
        + "Marloes vraagt: zullen we leuke kleertjes voor de baby meenemen?<br/>"
        + "Charlotte zegt: het lijkt me inderdaad leuk om een ",
        "setnr_118/cond_i/list_a:Anneloes en Mees gaan binnenkort naar een bruiloft, en denken na over een cadeau.<br/>"
        + "Annelose vraagt: zullen we voor een keukenartikel gaan dat ze op de verlanglijst hebben gezet?<br/>"
        + "Mees zegt: het lijkt me inderdaad leuk om een ",
        "setnr_119/cond_i/list_a:Mante en Clara organiseren de Sinterklaasviering op het kantoor.<br/>"
        + "Mante vraagt: zullen we de kinderen iets lekkers meegeven?<br/>"
        + "Clara zegt: het lijkt me inderdaad leuk om voor iedereen een ",
        "setnr_120/cond_i/list_a:Lauran en Annie wegen hun koffers voordat ze op vakantie gaan.<br/>"
        + "Lauran zegt: er kan nog een kilo bij, dus je kunt nog wat meer kleding meenemen? <br/>"
        + "Annie zegt: ik wil dan inderdaad nog een extra ",
        "setnr_121/cond_e/list_a:Jens en Marly wegen hun koffer voordat ze naar het vliegveld vertrekken.<br/>"
        + "Jens zegt: net te zwaar.. kunnen we er niet wat toiletspullen uithalen?<br/>"
        + "Marly zegt: we hebben eigenlijk veel te veel ",
        "setnr_122/cond_e/list_a:Malou en Pieter gaan verhuizen, en stapelen wat dozen op elkaar.<br/>"
        + "Malou zegt: pfoe deze doos is zwaar, zitten er studiespullen in?<br/>"
        + "Pieter zegt: volgens mij zitten er eigenlijk een aantal ",
        "setnr_123/cond_e/list_a:Saar en Hugo willen binnenkort een dagje naar een dierentuin.<br/>"
        + "Saar vraagt: zullen we half september gaan? dan is het hoogseizoen vast voorbij!<br/>"
        + "Hugo zegt: ik hoorde eigenlijk dat het dan weer wat ",
        "setnr_124/cond_e/list_a:Benjamin en Olivia zijn de zolder van hun oma aan het opruimen, en vinden daar een afgesloten kistje.<br/>"
        + "Benjamin vraagt: zou oma hier haar sieraden in bewaren?<br/>"
        + "Olivia zegt: ik denk dat er eigenlijk een paar zilveren ",
        "setnr_125/cond_e/list_a:Leo en Nadia beheren een hotelketen, en bekijken de opties voor nieuwe hotels in het buitenland.<br/>"
        + "Leo vraagt: wat dacht je van een Zuid-Europees land?<br/>"
        + "Nadia zegt: het lijkt me eigenlijk goed om uit te breiden naar ",
        "setnr_126/cond_e/list_a:Pieter en Vera zijn op vakantie, en gaan te voet van de camping naar het dorp.<br/>"
        + "Pieter zegt: zou je niet wat steviger schoeisel aantrekken?<br/>"
        + "Vera zegt: ik was eigenlijk van plan mijn ",
        "setnr_127/cond_e/list_a:Wouter gaat naar een kerstborrel van het werk.<br/>"
        + "Zijn vriendin vraagt: je trekt toch wel iets netjes aan?<br/>"
        + "Wouter zegt: ik was eigenlijk van plan een ",
        "setnr_128/cond_e/list_a:Bart en Karlijn gaan een bergwandeling maken.<br/>"
        + "Bart zegt: moet je niet wat sportievers aantrekken?<br/>"
        + "Karlijn zegt: ik was eigenlijk van plan een korte ",
        "setnr_129/cond_e/list_a:Familie de Wit staat op een camping vlakbij een middeleeuwse vesting.<br/>"
        + "Diane zegt: de kinderen willen vast mee op een uitstapje vanmiddag?<br/>"
        + "Andy zegt: ik denk dat ze eigenlijk graag naar het ",
        "setnr_130/cond_e/list_a:Peter en Bea zijn aan het ontbijten.<br/>"
        + "Bea vraagt: wil je ook een stukje fruit toe?<br/>"
        + "Peter zegt: ik lust eigenlijk nog wel een ",
        "setnr_131/cond_e/list_a:Marian en Tijn maken lunchpakketjes voor een uitstapje.<br/>"
        + "Marian vraagt: zal ik je brood hartig beleggen?<br/>"
        + "Tijn zegt: ik wil eigenlijk twee boterhammen met ",
        "setnr_132/cond_e/list_a:Frans en Mirjam bereiden een kaasplankje.<br/>"
        + "Frans vraagt: wil jij er ook een lekker borreltje bij?<br/>"
        + "Mirjam zegt: doe mij eigenlijk maar een glaasje ",
        "setnr_133/cond_e/list_a:Arnold en Helena zijn net klaar met dineren, en zetten nog wat koffie.<br/>"
        + "Arnold vraagt: zullen we ook een lekker likeurtje inschenken?<br/>"
        + "Helena zegt: doe mij eigenlijk maar een glaasje ",
        "setnr_134/cond_e/list_a:Rick en Gerard zitten op te scheppen over hun wagenpark.<br/>"
        + "Rick vraagt: jij houdt toch het meest van sportwagens?<br/>"
        + "Gerard zegt: ik rijd eigenlijk het liefst in mijn ",
        "setnr_135/cond_e/list_a:Joris en Noor willen een lang weekend weg naar BelgiÎ.<br/>"
        + "Joris vraagt: zullen we een huisje aan het strand zoeken?<br/>"
        + "Noor zegt: het lijkt me eigenlijk leuk om naar de ",
        "setnr_136/cond_e/list_a:Cornelis en Elisa zijn aan het overleggen over het avondeten.<br/>"
        + "Cornelis vraagt: zullen we een ovenschotel maken?<br/>"
        + "Elisa zegt: ik heb eigenlijk veel zin in ",
        "setnr_137/cond_e/list_a:Sandra en Jeroen zijn aan het barbecueÎn.<br/>"
        + "Sandra vraagt: zal ik er voor jou nog wat vlees op leggen?<br/>"
        + "Jeroen zegt: doe mij eigenlijk nog maar een ",
        "setnr_138/cond_e/list_a:Vanmiddag komt de familie op bezoek bij Adriana en Martin.<br/>"
        + "Martin vraagt: ga jij nog even langs de bakker voor wat lekkers?<br/>"
        + "Adriana zegt: ik was eigenlijk van plan om een paar ",
        "setnr_139/cond_e/list_a:Marks trainers bespreken zijn matige prestatie bij een belangrijke hardloopwedstrijd.<br/>"
        + "Emile zegt: misschien moet hij het wat rustiger aan doen.<br/>"
        + "Pierre zegt: ik denk eigenlijk dat we het aantal trainingen moeten ",
        "setnr_140/cond_e/list_a:Iris en Marco zijn met Pasen bij hun oma op bezoek; Iris voelt zich niet zo lekker.<br/>"
        + "Marco vraagt: je bent zeker misselijk van al die zoete troep?<br/>"
        + "Iris zegt: ik heb eigenlijk veel te veel ",
        "setnr_141/cond_e/list_a:Saskia en Marcel vieren Sinterklaasavond met de familie; Marcel voelt zich niet zo lekker.<br/>"
        + "Saskia vraagt: je hebt je zeker misselijk gegeten aan alle zoetigheid?<br/>"
        + "Marcel zegt: ik heb eigenlijk veel te veel ",
        "setnr_142/cond_e/list_a:Paul en Monique vieren Oudjaarsavond; Paul is een beetje misselijk.<br/>"
        + "Monique vraagt: je hebt je zeker te vol gegeten?<br/>"
        + "Paul zegt: ik heb eigenlijk te veel ",
        "setnr_143/cond_e/list_a:Karin en Arie zijn net terug van wintersport in Zwitserland.<br/>"
        + "Een vriend van hen vraagt: hebben jullie genoten van de traditionele gerechten daar?<br/>"
        + "Karin zegt: we hebben eigenlijk bijna elke dag ",
        "setnr_144/cond_e/list_a:Iris en Chantal gaan na het stappen naar de cafetaria; Chantal is een flexitariÎr.<br/>"
        + "Iris vraagt: jij wil zeker een vega snack?<br/>"
        + "Chantal zegt: doe mij eigenlijk maar een ",
        "setnr_145/cond_e/list_a:Michael en Bianca gaan gezellig ergens lunchen; Bianca eet de laatste tijd niet meer zo veel vlees.<br/>"
        + "Michael vraagt: bestel jij de vegetarische salade?<br/>"
        + "Bianca zegt: ik neem eigenlijk de salade met ",
        "setnr_146/cond_e/list_a:Patricia en RenÈ gaan wat drinken bij een nieuw koffietentje; RenÈ is lactose-intolerant. <br/>"
        + "Patricia zegt: Ooh RenÈ je kunt hier lekkere koffie met sojamelk bestellen!<br/>"
        + "RenÈ zegt: ik heb eigenlijk enorme zin in ",
        "setnr_147/cond_e/list_a:Jacqueline en Anouk zijn op zoek naar een outfit voor een feestje.<br/>"
        + "Jacqueline zegt: jij hebt zo'n mooi figuur, dat je kun je gerust laten zien!<br/>"
        + "Anouk zegt: Ik draag eigenlijk graag kleding die in de taille ",
        "setnr_148/cond_e/list_a:Ellen en DaniÎl hebben het over de familiereunie van vorige week.<br/>"
        + "Ellen zegt: tante Marie is flink aangekomen hË?<br/>"
        + "DaniÎl zegt: Ik dacht eigenlijk dat ze vorig jaar een stuk ",
        "setnr_149/cond_e/list_a:Wendy en Edwin gaan op bezoek bij hun opa die al een tijd in het ziekenhuis ligt.<br/>"
        + "Wendy zegt: de vorige keer leek hij al wel wat opgeknapt te zijn hË?<br/>"
        + "Edwin zegt: ik herinner me eigenlijk dat hij er toen best wel ",
        "setnr_150/cond_e/list_a:Francisca en Stefan blikken terug op het vorige familieweekend.<br/>"
        + "Francisca zegt: tante Gerda was weer het zonnetje in huis hË?<br/>"
        + "Stefan zegt: ik had eigenlijk het idee dat ze ontzettend ",
        "setnr_151/cond_e/list_a:Maartje en Michel hebben het over hun klasgenootjes op de basisschool. <br/>"
        + "Maartje zegt: Sjors deed nooit een vlieg kwaad hË?<br/>"
        + "Michel zegt: ik herinner me eigenlijk dat hij in de klas heel ",
        "setnr_152/cond_e/list_a:Roy en Simone hebben het over hun klasgenootjes van de middelbare school.<br/>"
        + "Roy zegt: Mats had altijd een grote mond hË?<br/>"
        + "Simone zegt: ik herinner me eigenlijk dat hij in de klas heel ",
        "setnr_153/cond_e/list_a:Sylvia en Roelof blikken terug op de schooltijd van hun kinderen. <br/>"
        + "Sylvia zegt: dat handschrift van Remko was niet te lezen hË?<br/>"
        + "Roelof zegt: ik herinner me eigenlijk dat zijn schrift altijd erg ",
        "setnr_154/cond_e/list_a:Geert en Marijke blikken terug op hun studententijd.<br/>"
        + "Marijke zegt: van studeren kwam bij jou niet zo veel terecht hË..<br/>"
        + "Geert zegt: ik herinner me eigenlijk dat ik de eerste twee jaar heel ",
        "setnr_155/cond_e/list_a:Gerda en Lucas hebben het over hun oude vriendengroep. <br/>"
        + "Gerda zegt: Joeri was altijd de gangmaker hË?<br/>"
        + "Lucas zegt: ik herinner me eigenlijk dat hij qua karakter erg ",
        "setnr_156/cond_e/list_a:Michiel en Barbara hebben het over hun neefjes en nichtjes met wie ze vroeger veel optrokken.<br/>"
        + "Michiel zegt: Veerle was altijd het muurbloempje hË?<br/>"
        + "Barbara zegt: ik herinner me eigenlijk dat zij altijd erg ",
        "setnr_157/cond_e/list_a:Evert en Louise zitten in de lerarenkamer, en hebben het over een oud-collega. <br/>"
        + "Evert zegt: als leerlingen het niet snapten, legde zij het gerust nog tien keer uit!<br/>"
        + "Louise zegt: ik herinner me eigenlijk dat ze tijdens het lesgeven erg ",
        "setnr_158/cond_e/list_a:Maurice en Carla zitten in de kantine, en hebben het over een oud-collega.<br/>"
        + "Maurice zegt: wat was zijn bureau altijd een zootje hË!<br/>"
        + "Carla zegt: ik herinner me eigenlijk dat de manier waarop hij werkte erg ",
        "setnr_159/cond_e/list_a:Erwin en Daphne zijn bezig met het voorbereiden van een lunch in hun tuin. <br/>"
        + "Erwin vraagt: zullen we de tafel een beetje chic dekken? <br/>"
        + "Daphne zegt: ik dacht er eigenlijk aan om de jus d'orange in ",
        "setnr_160/cond_e/list_a:De zoon van Nathalie en Marcus is woensdag jarig.<br/>"
        + "Marcus zegt: tegenwoordig trakteren kinderen altijd iets gezonds toch?<br/>"
        + "Nathalie zegt: hij wilde eigenlijk prikkers met ",
        "setnr_161/cond_e/list_a:Sander komt zijn beste vriend Luuc tegen.<br/>"
        + "Luuc zegt: HË Sander, je hebt zo college toch?<br/>"
        + "Sander zegt: ik ben eigenlijk op weg naar de ",
        "setnr_162/cond_e/list_a:Melanie zet wat water op voor een kopje thee.<br/>"
        + "Melanie vraagt: wil jij ook fruitige thee?<br/>"
        + "Sabine zegt: doe mij eigenlijk maar zo'n zakje ",
        "setnr_163/cond_e/list_a:Pascal en Janneke hebben het over hun toekomstdromen.<br/>"
        + "Pascal zegt: jij wilt zeker naar het platteland verhuizen?<br/>"
        + "Janneke zegt: het lijkt me eigenlijk fijn om later in een ",
        "setnr_164/cond_e/list_a:Arjan en Kirsten hebben het over hun droomhuizen.<br/>"
        + "Arjan zegt: jij droomt zeker van een mooie woning in de stad?<br/>"
        + "Kirsten zegt: ik wil eigenlijk graag in een ",
        "setnr_165/cond_e/list_a:Pim is niet op het werk, want hij moest naar het ziekenhuis.<br/>"
        + "Gert zegt: het zal wel niet voor iets ernstigs zijn toch?<br/>"
        + "Zijn collega zegt: hij zei eigenlijk dat hij er voor een ",
        "setnr_166/cond_e/list_a:Eveline en Roel hebben het over de toekomstplannen van hun muzikale zoon.<br/>"
        + "Evelien zegt: misschien dat hij later verder wil in de muziek hË..<br/>"
        + "Roel zegt: hij wil eigenlijk zijn brood verdienen als ",
        "setnr_167/cond_e/list_a:Nancy en Sam gaan samen studeren in de bibliotheek.<br/>"
        + "Nancy vraagt: zullen we in die ene studiezaal achterin gaan zitten?<br/>"
        + "Sam zegt: dat vind ik eigenlijk een best wel ",
        "setnr_168/cond_e/list_a:Susanne en Brian hebben in Afrika een safaritocht gemaakt.<br/>"
        + "Een vriend van hen vraagt: lopen daar nou echt allerlei wilde dieren rond?<br/>"
        + "Brian zegt: we hebben eigenlijk vooral heel veel ",
        "setnr_169/cond_e/list_a:Marleen en Alex lopen in het bos en zien allerlei sporen op de grond.<br/>"
        + "Marleen vraagt: zouden dit sporen van wilde dieren zijn?<br/>"
        + "Alex zegt: dat zijn eigenlijk afdrukken van een ",
        "setnr_170/cond_e/list_a:Rutger en Renske maken een wandeling.<br/>"
        + "Rutger zegt: volgens mij zie ik daar een roofvogel vliegen!<br/>"
        + "Renske zegt: dat is eigenlijk een grote ",
        "setnr_171/cond_e/list_a:Pauline en Nico kamperen in het bos en schrikken van een geluid.<br/>"
        + "Pauline vraagt: zou dat van een wild dier zijn?<br/>"
        + "Nico zegt: ik dacht eigenlijk dat ik een ",
        "setnr_172/cond_e/list_a:Harmen en Margriet maken een wandeling door de bergen, als Margriet opeens een bijzondere steen ziet liggen.<br/>"
        + "Harmen zegt: het lijkt wel een edelsteen!<br/>"
        + "Margriet zegt: het is eigenlijk een stukje ",
        "setnr_173/cond_e/list_a:Op 23 december gaan Ralph en Silvia shoppen om het huis aan te kleden.<br/>"
        + "Ralph vraagt: nemen we nog wat dingen mee voor in de boom?<br/>"
        + "Silvia zegt: ik wil eigenlijk een aantal ",
        "setnr_174/cond_e/list_a:Kornelis en Loes lopen door een kunstgalerie.<br/>"
        + "Kornelis zegt: dit schilderij past helemaal bij jouw smaak Loes!<br/>"
        + "Loes zegt: ik vind het eigenlijk heel erg ",
        "setnr_175/cond_e/list_a:Joep en Dominique wachten op het station op hun trein, en gaan nog even langs de kiosk.<br/>"
        + "Dominique vraagt: heb jij ook zin in een lekkere vette hap?<br/>"
        + "Joep zegt: ik wil eigenlijk graag een ",
        "setnr_176/cond_e/list_a:Stefanie en Marvin maken een lange autorit; Stefanie verveelt zich en begint achter de stoel te rommelen.<br/>"
        + "Marvin vraagt: pak je iets om te lezen?<br/>"
        + "Stefanie zegt: ik ben eigenlijk aan het zoeken naar mijn  ",
        "setnr_177/cond_e/list_a:Veronique is naar een lezing van een beroemde hoogleraar geweest. <br/>"
        + "Haar collega zegt: hij kan zijn verhaal altijd erg goed overbrengen hË?<br/>"
        + "Veronique zegt: ik vond zijn lezing eigenlijk ontzettend ",
        "setnr_178/cond_e/list_a:Vivian is naar een feestje geweest en komt laat thuis.<br/>"
        + "Haar huisgenoot zegt: je hebt je vast goed vermaakt?<br/>"
        + "Vivian zegt: Het was eigenlijk heel erg ",
        "setnr_179/cond_e/list_a:Op een warme zomerdag gaan Andrea en Leo een ijsje halen bij de ijssalon.<br/>"
        + "Andrea zegt: ga jij ook voor een frisse sorbetsmaak?<br/>"
        + "Leo zegt: Ik wil eigenlijk twee bolletjes ",
        "setnr_180/cond_e/list_a:Jolijne en Wernard bereiden de lunch voor hun zoontjes kinderfeestje.<br/>"
        + "Wernard vraagt: zullen we nog wat broodjes zoet beleggen?<br/>"
        + "Jolijne zegt: we kunnen er eigenlijk nog wel een paar met ",
        "setnr_1/cond_e/list_b:Piet is jarig en Anja neemt hem mee naar de beste Italiaan van de stad.<br/>"
        + "Anja vraagt: je gaat vast een klassieker bestellen? <br/>"
        + "Piet zegt: ik heb eigenlijk trek in een ",
        "setnr_2/cond_e/list_b:Hanna doet auditie bij een strijkkwartet en speelt de sterren van de hemel. <br/>"
        + "De dirigent vraagt: je hebt vast veel ervaring?<br/>"
        + "Hanna zegt: ik speelde vroeger eigenlijk vaak op de ",
        "setnr_3/cond_e/list_b:Simon gaat met zijn zus een weekendje naar Londen.<br/>"
        + "Zijn zus vraagt: we gaan zeker onder het kanaal door naar Engeland?<br/>"
        + "Simon zegt: ik heb eigenlijk twee tickets voor de  ",
        "setnr_4/cond_e/list_b:Ondanks haar angst voor dieren is Irene naar het circus geweest.<br/>"
        + "Jan vraagt: je vond de dierenact zeker doodeng?<br/>"
        + "Irene zegt: ik schrok eigenlijk van die grote ",
        "setnr_5/cond_e/list_b:In de Efteling komt Cor vloekend van de pijn het snoephuisje van Hans en Grietje uit.<br/>"
        + "Wilma vraagt: de deurposten waren vast te laag voor jou?<br/>"
        + "Cor zegt: ik stootte er eigenlijk met mijn ",
        "setnr_6/cond_e/list_b:Denise is sinds twee weken op dieet en is al 5 kilo afgevallen.<br/>"
        + "Sanne vraagt: je eet zeker heel gezond tussen de middag?<br/>"
        + "Denise zegt: ik koop eigenlijk meestal een ",
        "setnr_7/cond_e/list_b:Ondanks de regen gaan Sophie en Ewoud te voet naar het centrum.<br/>"
        + "Ewoud vraagt: moet je geen andere schoenen aandoen?<br/>"
        + "Sophie zegt: ik was eigenlijk van plan mijn ",
        "setnr_8/cond_e/list_b:Tineke is net terug van haar vakantie op Ibiza, en ze is hartstikke bruin.<br/>"
        + "Marjolein vraagt: het was daar zeker heerlijk weer?<br/>"
        + "Tineke zegt: ik heb eigenlijk behoorlijk wat ",
        "setnr_9/cond_e/list_b:Het is zaterdagavond, en Judith komt opgedoft de kamer in lopen.<br/>"
        + "Lennart vraagt: jij gaat zeker met Lara dansen vanavond?<br/>"
        + "Judith zegt: we hebben eigenlijk afgesproken om naar de  ",
        "setnr_10/cond_e/list_b:Sven heeft een jaar in China gewoond, en Dennis heeft beloofd voor hem te koken.<br/>"
        + "Sven zegt: ik hoop dat je Hollandse kost hebt gemaakt!<br/>"
        + "Dennis zegt: ik heb eigenlijk een flinke pan ",
        "setnr_11/cond_e/list_b:Bea en Christine gaan na een bergtocht door de sneeuw een cafÈ in om op te warmen. <br/>"
        + "Bea vraagt: neem jij ook een lekker warm drankje?<br/>"
        + "Christine zegt: ik heb eigenlijk trek in een ",
        "setnr_12/cond_e/list_b:Ans heeft een enorme bos bloemen van Jaap gekregen.<br/>"
        + "Jaap vraagt: heb je ze wel een mooi plekje gegeven?<br/>"
        + "Ans zegt: ik heb ze eigenlijk in die grote ronde ",
        "setnr_13/cond_e/list_b:Monique is zwanger, en komt haar vriendin Ine tegen in de kroeg.<br/>"
        + "Ine vraagt: jij bent zeker de Bob vandaag?<br/>"
        + "Monique zegt: ik stond eigenlijk op het punt een ",
        "setnr_14/cond_e/list_b:Angela heeft een week bij Betty gelogeerd, en haar moeder komt haar weer ophalen.<br/>"
        + "Betty vraagt: is Angela altijd al zo'n ongezonde eter geweest?<br/>"
        + "Haar moeder zegt: ze eet eigenlijk bijna elke dag een bord ",
        "setnr_15/cond_e/list_b:Het is Valentijnsdag en Jurgen komt Wim tegen bij de bloemist.<br/>"
        + "Wim vraagt: ga je je vriendin met een bloemetje verrassen?<br/>"
        + "Jurgen zegt: ik wil haar eigenlijk een mooie witte ",
        "setnr_16/cond_e/list_b:Sofia staat bekend om haar Italiaanse kookkunsten.<br/>"
        + "Erika vraagt: krijgen we als toetje weer een van je specialiteiten?<br/>"
        + "Sofia zegt: ik heb eigenlijk een hele schaal ",
        "setnr_17/cond_e/list_b:Omdat Mark gaat verhuizen, gaat hij samen met Pieter shoppen.<br/>"
        + "Pieter vraagt: je wilt dus ook nieuwe meubels aanschaffen?<br/>"
        + "Mark zegt: ik wil eigenlijk een nieuwe  ",
        "setnr_18/cond_e/list_b:Diana is met haar klas van de kunstacademie een weekend naar Parijs geweest.<br/>"
        + "Haar vriendin vraagt: jullie hebben zeker veel kunst gezien?<br/>"
        + "Diana zegt: we zijn eigenlijk elke dag naar een ",
        "setnr_19/cond_e/list_b:Thomas maakt zich op voor een cultureel dagje Amsterdam.<br/>"
        + "Tim vraagt: je gaat zeker de meesterwerken in het Rijksmuseum bewonderen? <br/>"
        + "Thomas zegt: ik kijk er eigenlijk naar uit om de ",
        "setnr_20/cond_e/list_b:RenÈ moet naar de tandarts om drie gaatjes te laten boren.<br/>"
        + "De tandarts vraagt: eet je toevallig veel zoetigheid?<br/>"
        + "RenÈ zegt: ik heb eigenlijk de laatste tijd best vaak ",
        "setnr_21/cond_e/list_b:Liam wil zijn afstuderen groots vieren. <br/>"
        + "Zijn moeder vraagt: wil je al je vrienden hier thuis uitnodigen?<br/>"
        + "Liam zegt: ik wil eigenlijk een feestje in de  ",
        "setnr_22/cond_e/list_b:Op een zonnige middag heeft Casper zijn moeder aan de telefoon.<br/>"
        + "Zijn moeder vraagt: ga je straks buiten eten met dit mooie weer?<br/>"
        + "Casper zegt: ik ga eigenlijk met vrienden ",
        "setnr_23/cond_e/list_b:Arno gaat met Claudia een weekendje logeren op de boerderij van haar ouders.<br/>"
        + "Arno vraagt: het ontbijt komt zeker rechtstreeks uit het kippenhok?<br/>"
        + "Claudia zegt: ze hebben eigenlijk alleen maar verse ",
        "setnr_24/cond_e/list_b:Joris zit aan de waterkant als Henk langs komt lopen.<br/>"
        + "Henk vraagt: zit je lekker te vissen?<br/>"
        + "Joris zegt: ik probeer eigenlijk mijn nieuwe ",
        "setnr_25/cond_e/list_b:Iris ziet erg bleek en ze voelt zich niet zo lekker.<br/>"
        + "Haar zus vraagt: je laat je toch wel onderzoeken vandaag?<br/>"
        + "Iris zegt: ik moet eigenlijk om twee uur bij de ",
        "setnr_26/cond_e/list_b:Na een saai college zit Sharon uitgebreid te gapen. <br/>"
        + "Olivier zegt: volgens mij kan jij wel een caffeÔneboost gebruiken!<br/>"
        + "Sharon zegt: ik heb eigenlijk trek in een ",
        "setnr_27/cond_e/list_b:Ilse is samen met Sybrine de was aan het ophangen.<br/>"
        + "Sybrine vraagt: heb je iets speciaals gebruikt om het beddengoed zo zacht te krijgen? <br/>"
        + "Ilse zegt: ik heb eigenlijk gisteren nieuwe ",
        "setnr_28/cond_e/list_b:Puck en Pleun gaan op bezoek bij Esmee, die met gym haar arm gebroken heeft.<br/>"
        + "Puck vraagt: zullen we iets gezonds voor haar meenemen?<br/>"
        + "Pleun zegt: het lijkt me eigenlijk leuk om een ",
        "setnr_29/cond_e/list_b:De hond begint te blaffen en rondjes te rennen als Marius de kamer binnenkomt. <br/>"
        + "Evelien vraagt: ga je een rondje wandelen met Fikkie?<br/>"
        + "Marius zegt: ik was eigenlijk op zoek naar zijn ",
        "setnr_30/cond_e/list_b:Wouter is ceremoniemeester op de bruiloft van zijn beste vriend Klaas.<br/>"
        + "Klaas vraagt: je hebt toch wel aan de alcohol gedacht?<br/>"
        + "Wouter zegt: ik heb eigenlijk twaalf flessen ",
        "setnr_31/cond_e/list_b:De avond voor zijn zoontjes verjaardag komt Henk de trap afgestommeld. <br/>"
        + "Jolanda vraagt: je hebt zeker de versiering van zolder gehaald?<br/>"
        + "Henk zegt: ik heb eigenlijk de doos met ",
        "setnr_32/cond_e/list_b:Anna en Elske willen graag goede cijfers halen dit studiejaar.<br/>"
        + "Anna vraagt: ga jij ook alvast het tentamen van volgende week voorbereiden?<br/>"
        + "Elske zegt: ik wil eigenlijk een paar uur naar de ",
        "setnr_33/cond_e/list_b:Martin en Leon lopen samen over de markt.<br/>"
        + "Martin vraagt: heb jij ook zo'n trek in gebakken vis?<br/>"
        + "Leon zegt: doe mij eigenlijk maar een bakje ",
        "setnr_34/cond_e/list_b:Op zijn vrije dag komt Olaf Thea tegen in het dorp.<br/>"
        + "Thea vraagt: ben je ook op weg naar de manege?<br/>"
        + "Olaf zegt: ik wilde eigenlijk een ritje gaan maken op mijn ",
        "setnr_35/cond_e/list_b:Teun en Bella zitten 's avonds te lezen op de bank, als Teun zijn boek even opzij legt.<br/>"
        + "Bella vraagt: het wordt wel een beetje donker om te lezen hË?<br/>"
        + "Teun zegt: ik was eigenlijk van plan de ",
        "setnr_36/cond_e/list_b:Stefan gaat vanavond een serenade brengen aan de liefde van zijn leven.<br/>"
        + "Bernard vraagt: je gaat jezelf zeker begeleiden bij het zingen?<br/>"
        + "Stefan zegt: ik heb eigenlijk besloten om mijn ",
        "setnr_37/cond_e/list_b:Dennis en Lotte liggen in hun tent te lezen, als Dennis wild om zich heen begint te zwaaien.<br/>"
        + "Dennis vraagt: hoorde jij ook dat irritante gezoem?<br/>"
        + "Lotte zegt: ik dacht eigenlijk dat ik een ",
        "setnr_38/cond_e/list_b:Bij binnenkomst trekt Felix met zijn kaplaarzen een spoor van modder door de kamer.<br/>"
        + "Mien vraagt:  zeg, maak je de vloer wel weer schoon?<br/>"
        + "Felix zegt: ik was eigenlijk van plan om in de bijkeuken een ",
        "setnr_39/cond_e/list_b:Martine en Nancy zitten een zielige film te kijken als Martine ineens sniffend opstaat.<br/>"
        + "Nancy vraagt: je wilt zeker je tranen wegvegen?<br/>"
        + "Martine zegt: ik ga eigenlijk een pakje ",
        "setnr_40/cond_e/list_b:Bruno komt de kamer binnen als zijn moeder verwoed de bank aan het stofzuigen is.<br/>"
        + "Zijn moeder vraagt: jij hebt zeker zitten kruimelen voor de tv?<br/>"
        + "Bruno zegt: ik heb eigenlijk tijdens de film een zak ",
        "setnr_41/cond_e/list_b:Vlak voor zijn sollicitatiegesprek heeft Maarten een broodje met pesto gegeten. <br/>"
        + "Maarten vraagt: heb ik nog iets tussen mijn tanden?<br/>"
        + "Bea zegt: ik zie eigenlijk daar rechts iets in je ",
        "setnr_42/cond_e/list_b:Hanneke brengt een bezoek aan een brouwerij in de stad.<br/>"
        + "De barman vraagt: lust je iets van de tap?<br/>"
        + "Hanneke zegt: ik wil eigenlijk graag jullie nieuwste ",
        "setnr_43/cond_e/list_b:Mira en haar zus Linda gaan een avondje naar de opera.<br/>"
        + "Mira vraagt: ga jij je ook mooi uitdossen?<br/>"
        + "Linda zegt: ik wil eigenlijk mijn nieuwe ",
        "setnr_44/cond_e/list_b:Het is winter en na schooltijd komt Vera Bart tegen in de fietsenstalling.<br/>"
        + "Vera vraagt: jij gaat toch ook mee naar de ijsbaan?<br/>"
        + "Bart zegt: ik heb eigenlijk zin om lekker te ",
        "setnr_45/cond_e/list_b:Jans team heeft de eerste voetbalwedstrijd na de winterstop ruim gewonnen.<br/>"
        + "Ans vraagt: je hebt zeker geen bal doorgelaten?<br/>"
        + "Jan zegt: ik heb eigenlijk kou staan lijden in de  ",
        "setnr_46/cond_e/list_b:Op oudjaarsavond komt Fred met een auto vol dozen terug uit de stad.<br/>"
        + "Zijn buurman zegt: zo te zien ga je weer knallend het jaar uit?<br/>"
        + "Fred zegt: ik heb eigenlijk voor een kapitaal aan ",
        "setnr_47/cond_e/list_b:Na een concert in het stadion komt Loek Sabine tegen.<br/>"
        + "Loek vraagt: ga je ook met het openbaar vervoer terug naar de stad?<br/>"
        + "Sabine zegt: ik sta eigenlijk al een tijdje op een ",
        "setnr_48/cond_e/list_b:Yvette komt lijkbleek thuis van de kermis.<br/>"
        + "Haar vader vraagt: had je weer last van hoogtevrees?<br/>"
        + "Yvette zegt: ik werd eigenlijk niet goed in het ",
        "setnr_49/cond_e/list_b:Lea en Fred hebben een heerlijke steak gegeten in een restaurant, en Lea komt terug van het toilet. <br/>"
        + "Lea vraagt: weet de ober al dat we willen betalen?<br/>"
        + "Fred zegt: ik heb eigenlijk gevraagd of we de ",
        "setnr_50/cond_e/list_b:Nick en Lynn plannen een citytrip voor de meivakantie.<br/>"
        + "Nick vraagt: jij wilt zeker naar Spanje toe?<br/>"
        + "Lynn zegt: het lijkt me eigenlijk leuk om naar ",
        "setnr_51/cond_e/list_b:Sarah en Koen zijn bezig om een wandelvakantie te boeken.<br/>"
        + "Sarah vraagt: is ScandinaviÎ een idee?<br/>"
        + "Koen zegt: ik zou eigenlijk graag naar ",
        "setnr_52/cond_e/list_b:Tess en Ruben willen een weekje naar een all-inclusive hotel in de zon. <br/>"
        + "Ruben vraagt: jij dacht aan de Antillen toch? <br/>"
        + "Tess zegt: ik wil eigenlijk graag naar ",
        "setnr_53/cond_e/list_b:Emma en Isa gaan een middagje shoppen, Emma past een groene blouse.<br/>"
        + "Emma vraagt: groen is echt mijn kleur hË?<br/>"
        + "Isa zegt: ik vind eigenlijk dat deze blouse je ",
        "setnr_54/cond_e/list_b:Rianne en Lotte werken op een HR-afdeling en zijn in gesprek over de sollicitanten.<br/>"
        + "Rianne vraagt: gaat jouw voorkeur ook uit naar Pieter? <br/>"
        + "Lotte zegt: ik vind hem eigenlijk vergeleken met de rest de ",
        "setnr_55/cond_e/list_b:Nina en Sven willen dit weekend uit eten, en bekijken de website van een Grieks restaurant.<br/>"
        + "Sven vraagt: dit restaurant was de vorige keer erg goed bevallen hË?<br/>"
        + "Nina zegt: ik herinner me eigenlijk dat de moussaka daar ",
        "setnr_56/cond_e/list_b:Jesse en Maud willen binnenkort met de familie uit eten, en bekijken de website van een restaurant. <br/>"
        + "Maud zegt: hier hebben we de vorige keer goed gegeten hË?<br/>"
        + "Jesse zegt: ik herinner me eigenlijk dat de porties daar erg ",
        "setnr_57/cond_e/list_b:Lieke en Finn werken aan de promotie van een evenement, en zoeken een drukker voor de posters.<br/>"
        + "Lieke zegt: volgens mij waren we de vorige keer erg tevreden over deze drukker?<br/>"
        + "Finn zegt: ik herinner me eigenlijk dat de kwaliteit ",
        "setnr_58/cond_e/list_b:Cas en Merel willen een rondreis door de VS gaan maken en zoeken een geschikt reisbureau.<br/>"
        + "Cas zegt: de vorige keer werden we bij dit reisbureau erg goed geholpen toch?<br/>"
        + "Merel zegt: ik herinner me eigenlijk dat de service ",
        "setnr_59/cond_e/list_b:Eline en Rick willen komende week de achtertuin flink opknappen. <br/>"
        + "Eline vraagt: zullen we van dat hoekje een moestuin maken?<br/>"
        + "Rick zegt: het lijkt me eigenlijk leuk om daar allerlei soorten ",
        "setnr_60/cond_e/list_b:Dylan en Esmee willen hun woonkamer opnieuw indelen.<br/>"
        + "Dylan zegt: hier wilden we de eethoek van maken toch?<br/>"
        + "Esmee zegt: die plek lijkt me eigenlijk geschikt om de grote houten ",
        "setnr_61/cond_c/list_b:Vera en Thijmen bespreken de inrichting van hun nieuwe woning.<br/>"
        + "Vera vraagt: zullen we voor boven de tafel een mooi kunstwerk uitzoeken?<br/>"
        + "Thijmen zegt: het lijkt me erg mooi om daar een ",
        "setnr_62/cond_c/list_b:Robin en Jasmijn gaan vanmiddag naar het asiel om een huisdier uit te zoeken.<br/>"
        + "Robin vraagt: je houdt van grote honden toch?<br/>"
        + "Jasmijn zegt: ik wil heel erg graag een mooie ",
        "setnr_63/cond_c/list_b:Demi en Jasper gaan uit eten bij een restaurant aan de haven.<br/>"
        + "Jasper vraagt: ga jij ook voor een van de visgerechten?<br/>"
        + "Demi zegt: ik wil denk ik als hoofdgerecht de ",
        "setnr_64/cond_c/list_b:Mike en Marit gaan in de kerstvakantie uit eten bij een nieuw restaurant in de stad.<br/>"
        + "Marit zegt: we zouden hier zeker de wildgerechten moeten uitproberen!<br/>"
        + "Mike zegt: ik wil denk ik als hoofdgerecht ",
        "setnr_65/cond_c/list_b:Gijs en Milou zijn een dagje naar een pretpark, en hebben alle attracties al gehad.<br/>"
        + "Gijs vraagt: zullen we als afsluiter nog even in een enge attractie gaan?<br/>"
        + "Milou zegt: ik wil heel graag nog een ritje in de ",
        "setnr_66/cond_c/list_b:Tim en Roos zijn een weekendje in Parijs, en bespreken bij het ontbijt welke musea ze al hebben bezocht.<br/>"
        + "Een andere hotelgast vraagt: gaan jullie vanmiddag nog naar een van de beroemde kerken?<br/>"
        + "Tim zegt: we willen straks nog heel graag de ",
        "setnr_67/cond_c/list_b:Het is zomer en na schooltijd komt Max Sem tegen op het schoolplein.<br/>"
        + "Max vraagt: jij gaat toch ook mee naar het strand?<br/>"
        + "Sem zegt: ik heb best zin om lekker te gaan ",
        "setnr_68/cond_c/list_b:Na de laatste les komen Amber en Diedre elkaar tegen bij de kluisjes.<br/>"
        + "Amber vraagt: jij gaat toch ook mee naar het winkelcentrum?<br/>"
        + "Diedre zegt: ik heb best zin om te ",
        "setnr_69/cond_c/list_b:Willem en Tijn voeren een discussie over milieu en energie.<br/>"
        + "Willem zegt: ik denk dat groene stroom de toekomst is!<br/>"
        + "Tijn zegt: ik voorspel echt een filnke toename van het aantal ",
        "setnr_70/cond_c/list_b:Jos en Leny ploffen op de bank voor een filmavondje. <br/>"
        + "Jos zegt: zullen we er iets lekker zouts bij pakken?<br/>"
        + "Leny zegt: ik heb echt al de hele dag zin in ",
        "setnr_71/cond_c/list_b:Frank en Mirjam drinken een biertje op een terrasje in de namiddagzon. <br/>"
        + "Frank zegt: zullen we er lekker wat snacks uit de frituur bij bestellen?<br/>"
        + "Mirjam zegt: ik heb enorme trek in een portie ",
        "setnr_72/cond_c/list_b:John en Caroline zitten in een restaurant en hebben net hun hoofdgerecht op.<br/>"
        + "Caroline zegt: heb jij ook zin in iets zoets na?<br/>"
        + "John zegt: ik heb erg veel zin in een ",
        "setnr_73/cond_c/list_b:Nel en Piet bespreken met hun dochter wat ze vanmiddag met hun kleinzoon gaan doen.<br/>"
        + "Piet zegt: met deze sneeuw kunnen we wel de helling af!<br/>"
        + "Nel zegt: volgens mij wil hij echt niets liever dan  ",
        "setnr_74/cond_c/list_b:Ilse en Patty hebben het over de boeken van hun lievelings auteur.<br/>"
        + "Ilse zegt: haar vroege werk is wel het mooiste hË?<br/>"
        + "Patty zegt: ik vind haar debuutroman al jaren een van haar ",
        "setnr_75/cond_c/list_b:Jochem en Ilona hebben het over de acteur Tom Hanks.<br/>"
        + "Jochem zegt: hij is erg gegroeid als acteur in zijn carriËre hË?<br/>"
        + "Ilona zegt: ik vind hem geloof ik in zijn laastste film het ",
        "setnr_76/cond_c/list_b:Stan en Romy luisteren naar muziek van hun favoriete rockband.<br/>"
        + "Stan zegt: hun recente muziek is echt geweldig!<br/>"
        + "Romy zegt: ik vind dat ze de laatste jaren met die nieuwe drummer steeds ",
        "setnr_77/cond_c/list_b:Rianne en Justus bespreken wat voor sport leuk zou zijn voor hun zoon.<br/>"
        + "Rianne vraagt: zullen we hem voor een teamsport opgeven?<br/>"
        + "Justus zegt: ik denk dat hij het heel leuk zou vinden om op ",
        "setnr_78/cond_c/list_b:Ben en Kim staan te kibbelen; Kim laat altijd de huistelefoon ergens slingeren.<br/>"
        + "Ben vraagt: heb je de telefoon zeker weer in de slaapkamer laten liggen?<br/>"
        + "Kim zegt: ik denk dat ik hem daarnet bovenop het ",
        "setnr_79/cond_c/list_b:Peter en Elly zoeken bij de bloemist iets uit voor hun moeders verjaardag.<br/>"
        + "Peter vraagt: ze vindt warme kleuren erg mooi hË?<br/>"
        + "Elly zegt: ze houdt heel erg veel van  ",
        "setnr_80/cond_c/list_b:Anne en Jens hebben het over de verkiezingen.<br/>"
        + "Anne vraagt: jij gaat vast voor een partij die zich sterk maakt voor het milieu?<br/>"
        + "Jens zegt: ik ga denk ik stemmen op een ",
        "setnr_81/cond_c/list_b:Senna en Thom kijken in de gids naar wat er vanavond op tv komt.<br/>"
        + "Senne vraagt: jij houdt toch ook van thrillers?<br/>"
        + "Thom zegt: ik kijk 's avonds graag naar hele ",
        "setnr_82/cond_c/list_b:Juliette en Teun maken plannen voor hun weekendje aan zee.<br/>"
        + "Juliette zegt: jij wil vast lekker de natuur in gaan!<br/>"
        + "Teun zegt: ik wil zaterdag een wandeling door de ",
        "setnr_83/cond_c/list_b:Het is winter en Steffi en Arjan zijn bij de ijsbaan op het dorpsplein.<br/>"
        + "Steffi vraagt: wil jij ook een lekker warm drankje? <br/>"
        + "Arjan zegt: ik heb erg veel zin in ",
        "setnr_84/cond_c/list_b:Naomi bespreekt haar toekomstplannen met haar ouders.<br/>"
        + "Haar vader zegt: je zat aan een leidinggevende functie in de horeca te denken toch?<br/>"
        + "Naomi zegt: ik wil later manager van een ",
        "setnr_85/cond_c/list_b:Rea studeert Frans, en praat met haar ouders over wat voor werk ze daarna wil gaan doen.<br/>"
        + "Haar moeder zegt: je wil graag het onderwijs in toch?<br/>"
        + "Rea zegt: ik wil later op zoek naar een baan als ",
        "setnr_86/cond_c/list_b:Fleur is met haar opa en oma aan het praten over haar toekomstplannen.<br/>"
        + "Haar oma zegt: je wil een baan in de zorg gaan zoeken toch?<br/>"
        + "Fleur zegt: ik wil heel graag gaan werken als ",
        "setnr_87/cond_c/list_b:Jordy en Elke zijn bij een outdoorzaak aan het kijken naar skimutsen.<br/>"
        + "Jordy zegt: zo'n felgekleurde is echt wat voor jou!<br/>"
        + "Elke zegt: ik wil heel graag die ",
        "setnr_88/cond_c/list_b:Hannah heeft binnenkort een belangrijke meeting, en ze zoekt samen met Julie naar een outfit. <br/>"
        + "Julie zegt: ik denk dat je het beste voor een blouse met een neutrale kleur kunt gaan.<br/>"
        + "Hannah zegt: ik wil denk ik graag die ",
        "setnr_89/cond_c/list_b:Bram wil graag een nieuwe auto kopen, maar weet nog niet in welke kleur. <br/>"
        + "Zijn zus zegt: als je een opvallende kleur neemt, vind je 'm altijd snel terug in een parkeergarage!<br/>"
        + "Bram zegt: ik zat er sterk aan te denken om een ",
        "setnr_90/cond_c/list_b:Shirley komt erg moe terug van haar werk en ploft naast Fabian neer op de bank. <br/>"
        + "Fabian zegt: als ik jou was zou ik vanavond lekker wat ontspannen!<br/>"
        + "Shirley zegt: het lijkt me heel goed om even te gaan ",
        "setnr_91/cond_c/list_b:De eigenaar en manager van een restaurant zijn in gesprek; het loopt niet goed. <br/>"
        + "De eigenaar vraagt: zou het met ons keukenteam te maken hebben?<br/>"
        + "De manager zegt: ik heb sterk de indruk dat het aan de nieuwe ",
        "setnr_92/cond_c/list_b:De eigenaar en manager van een camping zijn in gesprek over hun tarieven.<br/>"
        + "De eigenaar zegt: misschien kunnen we onze luxe accomodaties wat duurder maken?<br/>"
        + "De manager zegt: ik denk dat we dit seizoen iets aan de prijzen van de ",
        "setnr_93/cond_c/list_b:De medewerkers van een hotel zijn in gesprek; er is veel ontevredenheid onder de gasten.<br/>"
        + "Wouter zegt: misschien zijn de kamers erg vies?<br/>"
        + "Andre zegt: er zijn deze maand veel klachten over de ",
        "setnr_94/cond_c/list_b:Lotte is wel 15 kilo kwijt geraakt in de afgelopen maanden.<br/>"
        + "Haar zus vraagt: ben je zoveel afgevallen door je nieuwe eetpatroon?<br/>"
        + "Lotte zegt: het komt waarschijnlijk doordat ik een zeer strikt ",
        "setnr_95/cond_c/list_b:Dorien en Bea willen graag flink afvallen voordat het bikiniseizoen aanbreekt.<br/>"
        + "Dorien vraagt: ga jij ook weer trainen vanavond?<br/>"
        + "Bea zegt: ik wil straks nog even naar de ",
        "setnr_96/cond_c/list_b:Violaine en Maaike zitten op een terrasje en bekijken de kaart met fruitsappen.<br/>"
        + "Violaine vraagt: neem jij ook een sapje met tropisch fruit?<br/>"
        + "Maaike zegt: ik wil graag een smoothie met  ",
        "setnr_97/cond_c/list_b:Simon en Andrea willen graag meer wandelen, en denken erover zich aan te sluiten bij een clubje.<br/>"
        + "Simon zegt: volgens mij lopen ze bij die club stevig door!<br/>"
        + "Andrea zegt: ik hoorde van Piet dat hun wandeltempo heel ",
        "setnr_98/cond_c/list_b:Bente overweegt over te stappen naar een nieuwe dansschool die binnenkort opent.<br/>"
        + "Haar zus zegt: volgens mij betaal je bij die school veel minder lesgeld!<br/>"
        + "Bente zegt: ik had op de website gezien dat de lessen daar een stuk ",
        "setnr_99/cond_c/list_b:Wessel en Marte willen een dagje naar een pretpark, maar hebben een hekel aan lang wachten.<br/>"
        + "Wessel zegt: misschien is eind november een optie?<br/>"
        + "Marte zegt: ik denk dat de rijen dan overal best wel  ",
        "setnr_100/cond_c/list_b:Justin en Niels hebben net hun wiskundehuiswerk voor volgende week opgekregen.<br/>"
        + "Justin zegt: pfoe het volgende hoofdstuk wordt echt verschrikkelijk!<br/>"
        + "Niels zegt: die sommen zien er echt heel erg ",
        "setnr_101/cond_c/list_b:De docent geeft de leerlingen huiswerk op dat ze individueel of in tweetallen kunnen maken.<br/>"
        + "De docent vraagt: Thom, jij wil deze opdracht zeker alleen doen?<br/>"
        + "Thom zegt: in mijn eentje is het altijd een heel stuk ",
        "setnr_102/cond_c/list_b:Bart wil na de zomer graag op muziekles.<br/>"
        + "Zijn broer zegt: ik denk dat een blaasinstrument wel iets voor jou is!<br/>"
        + "Bart zegt: ik wil graag leren om een ",
        "setnr_103/cond_c/list_b:Froukje gaat binnenkort beginnen met danslessen.<br/>"
        + "Haar vriendin vraagt: je zat te denken aan klassieke dans toch?<br/>"
        + "Froukje zegt: ik wil me zometeen gaan opgeven voor ",
        "setnr_104/cond_c/list_b:Dennis meldt zich aan bij een nieuw sportcentrum.<br/>"
        + "Zijn vriendin vraagt: je wil daar graag groepslessen in vechtsport nemen toch?<br/>"
        + "Dennis zegt: ik wil me zometeen gaan opgeven voor  ",
        "setnr_105/cond_c/list_b:Op de camping willen Levi en Noah met hun vriendje Mike spelen.<br/>"
        + "Levi zegt: hij is vast al aan het eten..<br/>"
        + "Noah zegt: ik zag hem met zijn broer in de richting van de  ",
        "setnr_106/cond_c/list_b:Gerard en Annemarie hebben het over de schoolresultaten van hun dochter.<br/>"
        + "Gerard zegt: vooral met wiskunde heeft ze erg veel moeite hËÖ<br/>"
        + "Annemarie zegt: ik begreep van haar dat haar gemiddelde zo rond de  ",
        "setnr_107/cond_c/list_b:Henk en Annemieke bespreken hoe het met de schoolprestaties van hun zoon gaat.<br/>"
        + "Annemieke zegt: een talenknobbel heeft hij niet hË?<br/>"
        + "Henk zegt: hij heeft veruit de meeste moeite met het vak ",
        "setnr_108/cond_c/list_b:Bregje en Rosanne gaan samen een middagje shoppen.<br/>"
        + "Bregje zegt: je wil graag wat leuke winterkleding kopen toch?<br/>"
        + "Rosanne zegt: ik hoop vanmiddag een mooie nieuwe ",
        "setnr_109/cond_c/list_b:Kim en Sophia gaan samen eventjes naar de stad.<br/>"
        + "Kim vraagt: wilde je nog gaan kijken voor een mooi sieraad?<br/>"
        + "Sophia zegt: ik wil graag gaan kijken voor een ",
        "setnr_110/cond_c/list_b:Ome Willem uit Canada komt een weekje op bezoek bij zijn familie.<br/>"
        + "Anna zegt: hij zou het vast super vinden als we 'm meenemen op een cultureel uitje!<br/>"
        + "Petra zegt: ik denk dat hij in ieder geval graag naar een ",
        "setnr_111/cond_c/list_b:Theo geeft een feestje bij hem thuis.<br/>"
        + "Frank zegt: ik heb Theo al een tijdje niet gezien.. zou hij wiet aan het halen zijn?<br/>"
        + "Janne zegt: ik hoorde hem tegen Piet zeggen dat hij even naar de ",
        "setnr_112/cond_c/list_b:Bernard en Petra werken aan de faculteit Betawetenschappen, en zoeken hun collega Wernard.<br/>"
        + "Bernard zegt: misschien is hij in het laboratorium aan het werk?<br/>"
        + "Petra zegt: ik geloof dat hij druk bezig is met een ",
        "setnr_113/cond_c/list_b:Nu hun college willen David en Max gaan lunchen met Nick, dus David stuurt een berichtje naar Nick. <br/>"
        + "David zegt: en, komt Nick er al aan?<br/>"
        + "Max zegt: hij is nu op weg naar de ",
        "setnr_114/cond_c/list_b:Het bestuur van een basisschool bespreekt de lage CITO-scores van groep 8.<br/>"
        + "Herman vraagt: zou het door een gebrek aan motivatie komen? <br/>"
        + "Rita zegt: ik denk echt dat het aan de matige inzet van de  ",
        "setnr_115/cond_c/list_b:Marian was weer eens haar sleutelbos kwijt, en komt opgelucht aangelopen. <br/>"
        + "Fred zegt: ze lagen zeker gewoon in de keuken?<br/>"
        + "Marian zegt: ik heb ze net teruggevonden op het ",
        "setnr_116/cond_c/list_b:Marlies is pas verhuisd; Isabeau en Lisa komen zaterdag op bezoek in haar nieuwe huisje.<br/>"
        + "Isabeau vraagt: zullen we iets moois uitzoeken voor in de woonkamer?<br/>"
        + "Lisa zegt: het lijkt me best leuk om een ",
        "setnr_117/cond_c/list_b:Marloes en Charlotte gaan bij Kirsten op kraamvisite. <br/>"
        + "Marloes vraagt: zullen we leuke kleertjes voor de baby meenemen?<br/>"
        + "Charlotte zegt: het lijkt me heel leuk om een ",
        "setnr_118/cond_c/list_b:Anneloes en Mees gaan binnenkort naar een bruiloft, en denken na over een cadeau.<br/>"
        + "Annelose vraagt: zullen we voor een keukenartikel gaan dat ze op de verlanglijst hebben gezet?<br/>"
        + "Mees zegt: het lijkt me best leuk om een ",
        "setnr_119/cond_c/list_b:Mante en Clara organiseren de Sinterklaasviering op het kantoor.<br/>"
        + "Mante vraagt: zullen we de kinderen iets lekkers meegeven?<br/>"
        + "Clara zegt: het lijkt me best leuk om voor iedereen een ",
        "setnr_120/cond_c/list_b:Lauran en Annie wegen hun koffers voordat ze op vakantie gaan.<br/>"
        + "Lauran zegt: er kan nog een kilo bij, dus je kunt nog wat meer kleding meenemen? <br/>"
        + "Annie zegt: ik wil dan denk ik nog een extra ",
        "setnr_121/cond_i/list_b:Jens en Marly wegen hun koffer voordat ze naar het vliegveld vertrekken.<br/>"
        + "Jens zegt: net te zwaar.. kunnen we er niet wat toiletspullen uithalen?<br/>"
        + "Marly zegt: we hebben inderdaad veel te veel ",
        "setnr_122/cond_i/list_b:Malou en Pieter gaan verhuizen, en stapelen wat dozen op elkaar.<br/>"
        + "Malou zegt: pfoe deze doos is zwaar, zitten er studiespullen in?<br/>"
        + "Pieter zegt: volgens mij zitten er inderdaad een aantal ",
        "setnr_123/cond_i/list_b:Saar en Hugo willen binnenkort een dagje naar een dierentuin.<br/>"
        + "Saar vraagt: zullen we half september gaan? dan is het hoogseizoen vast voorbij!<br/>"
        + "Hugo zegt: ik hoorde inderdaad dat het dan weer wat ",
        "setnr_124/cond_i/list_b:Benjamin en Olivia zijn de zolder van hun oma aan het opruimen, en vinden daar een afgesloten kistje.<br/>"
        + "Benjamin vraagt: zou oma hier haar sieraden in bewaren?<br/>"
        + "Olivia zegt: ik denk dat er inderdaad een paar zilveren ",
        "setnr_125/cond_i/list_b:Leo en Nadia beheren een hotelketen, en bekijken de opties voor nieuwe hotels in het buitenland.<br/>"
        + "Leo vraagt: wat dacht je van een Zuid-Europees land?<br/>"
        + "Nadia zegt: het lijkt me inderdaad goed om uit te breiden naar ",
        "setnr_126/cond_i/list_b:Pieter en Vera zijn op vakantie, en gaan te voet van de camping naar het dorp.<br/>"
        + "Pieter zegt: zou je niet wat steviger schoeisel aantrekken?<br/>"
        + "Vera zegt: ik was inderdaad van plan mijn ",
        "setnr_127/cond_i/list_b:Wouter gaat naar een kerstborrel van het werk.<br/>"
        + "Zijn vriendin vraagt: je trekt toch wel iets netjes aan?<br/>"
        + "Wouter zegt: ik was inderdaad van plan een ",
        "setnr_128/cond_i/list_b:Bart en Karlijn gaan een bergwandeling maken.<br/>"
        + "Bart zegt: moet je niet wat sportievers aantrekken?<br/>"
        + "Karlijn zegt: ik was inderdaad van plan een korte ",
        "setnr_129/cond_i/list_b:Familie de Wit staat op een camping vlakbij een middeleeuwse vesting.<br/>"
        + "Diane zegt: de kinderen willen vast mee op een uitstapje vanmiddag?<br/>"
        + "Andy zegt: ik denk dat ze inderdaad graag naar het ",
        "setnr_130/cond_i/list_b:Peter en Bea zijn aan het ontbijten.<br/>"
        + "Bea vraagt: wil je ook een stukje fruit toe?<br/>"
        + "Peter zegt: ik lust inderdaad nog wel een ",
        "setnr_131/cond_i/list_b:Marian en Tijn maken lunchpakketjes voor een uitstapje.<br/>"
        + "Marian vraagt: zal ik je brood hartig beleggen?<br/>"
        + "Tijn zegt: ik wil inderdaad twee boterhammen met ",
        "setnr_132/cond_i/list_b:Frans en Mirjam bereiden een kaasplankje.<br/>"
        + "Frans vraagt: wil jij er ook een lekker borreltje bij?<br/>"
        + "Mirjam zegt: doe mij inderdaad maar een glaasje ",
        "setnr_133/cond_i/list_b:Arnold en Helena zijn net klaar met dineren, en zetten nog wat koffie.<br/>"
        + "Arnold vraagt: zullen we ook een lekker likeurtje inschenken?<br/>"
        + "Helena zegt: doe mij inderdaad maar een glaasje ",
        "setnr_134/cond_i/list_b:Rick en Gerard zitten op te scheppen over hun wagenpark.<br/>"
        + "Rick vraagt: jij houdt toch het meest van sportwagens?<br/>"
        + "Gerard zegt: ik rijd inderdaad het liefst in mijn ",
        "setnr_135/cond_i/list_b:Joris en Noor willen een lang weekend weg naar BelgiÎ.<br/>"
        + "Joris vraagt: zullen we een huisje aan het strand zoeken?<br/>"
        + "Noor zegt: het lijkt me inderdaad leuk om naar de ",
        "setnr_136/cond_i/list_b:Cornelis en Elisa zijn aan het overleggen over het avondeten.<br/>"
        + "Cornelis vraagt: zullen we een ovenschotel maken?<br/>"
        + "Elisa zegt: ik heb inderdaad veel zin in ",
        "setnr_137/cond_i/list_b:Sandra en Jeroen zijn aan het barbecueÎn.<br/>"
        + "Sandra vraagt: zal ik er voor jou nog wat vlees op leggen?<br/>"
        + "Jeroen zegt: doe mij inderdaad nog maar een ",
        "setnr_138/cond_i/list_b:Vanmiddag komt de familie op bezoek bij Adriana en Martin.<br/>"
        + "Martin vraagt: ga jij nog even langs de bakker voor wat lekkers?<br/>"
        + "Adriana zegt: ik was inderdaad van plan om een paar ",
        "setnr_139/cond_i/list_b:Marks trainers bespreken zijn matige prestatie bij een belangrijke hardloopwedstrijd.<br/>"
        + "Emile zegt: misschien moet hij het wat rustiger aan doen.<br/>"
        + "Pierre zegt: ik denk inderdaad dat we het aantal trainingen moeten ",
        "setnr_140/cond_i/list_b:Iris en Marco zijn met Pasen bij hun oma op bezoek; Iris voelt zich niet zo lekker.<br/>"
        + "Marco vraagt: je bent zeker misselijk van al die zoete troep?<br/>"
        + "Iris zegt: ik heb inderdaad veel te veel ",
        "setnr_141/cond_i/list_b:Saskia en Marcel vieren Sinterklaasavond met de familie; Marcel voelt zich niet zo lekker.<br/>"
        + "Saskia vraagt: je hebt je zeker misselijk gegeten aan alle zoetigheid?<br/>"
        + "Marcel zegt: ik heb inderdaad veel te veel ",
        "setnr_142/cond_i/list_b:Paul en Monique vieren Oudjaarsavond; Paul is een beetje misselijk.<br/>"
        + "Monique vraagt: je hebt je zeker te vol gegeten?<br/>"
        + "Paul zegt: ik heb inderdaad te veel ",
        "setnr_143/cond_i/list_b:Karin en Arie zijn net terug van wintersport in Zwitserland.<br/>"
        + "Een vriend van hen vraagt: hebben jullie genoten van de traditionele gerechten daar?<br/>"
        + "Karin zegt: we hebben inderdaad bijna elke dag ",
        "setnr_144/cond_i/list_b:Iris en Chantal gaan na het stappen naar de cafetaria; Chantal is een flexitariÎr.<br/>"
        + "Iris vraagt: jij wil zeker een vega snack?<br/>"
        + "Chantal zegt: doe mij inderdaad maar een ",
        "setnr_145/cond_i/list_b:Michael en Bianca gaan gezellig ergens lunchen; Bianca eet de laatste tijd niet meer zo veel vlees.<br/>"
        + "Michael vraagt: bestel jij de vegetarische salade?<br/>"
        + "Bianca zegt: ik neem inderdaad de salade met ",
        "setnr_146/cond_i/list_b:Patricia en RenÈ gaan wat drinken bij een nieuw koffietentje; RenÈ is lactose-intolerant. <br/>"
        + "Patricia zegt: Ooh RenÈ je kunt hier lekkere koffie met sojamelk bestellen!<br/>"
        + "RenÈ zegt: ik heb inderdaad enorme zin in ",
        "setnr_147/cond_i/list_b:Jacqueline en Anouk zijn op zoek naar een outfit voor een feestje.<br/>"
        + "Jacqueline zegt: jij hebt zo'n mooi figuur, dat je kun je gerust laten zien!<br/>"
        + "Anouk zegt: Ik draag inderdaad graag kleding die in de taille ",
        "setnr_148/cond_i/list_b:Ellen en DaniÎl hebben het over de familiereunie van vorige week.<br/>"
        + "Ellen zegt: tante Marie is flink aangekomen hË?<br/>"
        + "DaniÎl zegt: Ik dacht inderdaad dat ze vorig jaar een stuk ",
        "setnr_149/cond_i/list_b:Wendy en Edwin gaan op bezoek bij hun opa die al een tijd in het ziekenhuis ligt.<br/>"
        + "Wendy zegt: de vorige keer leek hij al wel wat opgeknapt te zijn hË?<br/>"
        + "Edwin zegt: ik herinner me inderdaad dat hij er toen best wel ",
        "setnr_150/cond_i/list_b:Francisca en Stefan blikken terug op het vorige familieweekend.<br/>"
        + "Francisca zegt: tante Gerda was weer het zonnetje in huis hË?<br/>"
        + "Stefan zegt: ik had inderdaad het idee dat ze ontzettend ",
        "setnr_151/cond_i/list_b:Maartje en Michel hebben het over hun klasgenootjes op de basisschool. <br/>"
        + "Maartje zegt: Sjors deed nooit een vlieg kwaad hË?<br/>"
        + "Michel zegt: ik herinner me inderdaad dat hij in de klas heel ",
        "setnr_152/cond_i/list_b:Roy en Simone hebben het over hun klasgenootjes van de middelbare school.<br/>"
        + "Roy zegt: Mats had altijd een grote mond hË?<br/>"
        + "Simone zegt: ik herinner me inderdaad dat hij in de klas heel ",
        "setnr_153/cond_i/list_b:Sylvia en Roelof blikken terug op de schooltijd van hun kinderen. <br/>"
        + "Sylvia zegt: dat handschrift van Remko was niet te lezen hË?<br/>"
        + "Roelof zegt: ik herinner me inderdaad dat zijn schrift altijd erg ",
        "setnr_154/cond_i/list_b:Geert en Marijke blikken terug op hun studententijd.<br/>"
        + "Marijke zegt: van studeren kwam bij jou niet zo veel terecht hË..<br/>"
        + "Geert zegt: ik herinner me inderdaad dat ik de eerste twee jaar heel ",
        "setnr_155/cond_i/list_b:Gerda en Lucas hebben het over hun oude vriendengroep. <br/>"
        + "Gerda zegt: Joeri was altijd de gangmaker hË?<br/>"
        + "Lucas zegt: ik herinner me inderdaad dat hij qua karakter erg ",
        "setnr_156/cond_i/list_b:Michiel en Barbara hebben het over hun neefjes en nichtjes met wie ze vroeger veel optrokken.<br/>"
        + "Michiel zegt: Veerle was altijd het muurbloempje hË?<br/>"
        + "Barbara zegt: ik herinner me inderdaad dat zij altijd erg ",
        "setnr_157/cond_i/list_b:Evert en Louise zitten in de lerarenkamer, en hebben het over een oud-collega. <br/>"
        + "Evert zegt: als leerlingen het niet snapten, legde zij het gerust nog tien keer uit!<br/>"
        + "Louise zegt: ik herinner me inderdaad dat ze tijdens het lesgeven erg ",
        "setnr_158/cond_i/list_b:Maurice en Carla zitten in de kantine, en hebben het over een oud-collega.<br/>"
        + "Maurice zegt: wat was zijn bureau altijd een zootje hË!<br/>"
        + "Carla zegt: ik herinner me inderdaad dat de manier waarop hij werkte erg ",
        "setnr_159/cond_i/list_b:Erwin en Daphne zijn bezig met het voorbereiden van een lunch in hun tuin. <br/>"
        + "Erwin vraagt: zullen we de tafel een beetje chic dekken? <br/>"
        + "Daphne zegt: ik dacht er inderdaad aan om de jus d'orange in ",
        "setnr_160/cond_i/list_b:De zoon van Nathalie en Marcus is woensdag jarig.<br/>"
        + "Marcus zegt: tegenwoordig trakteren kinderen altijd iets gezonds toch?<br/>"
        + "Nathalie zegt: hij wilde inderdaad prikkers met ",
        "setnr_161/cond_i/list_b:Sander komt zijn beste vriend Luuc tegen.<br/>"
        + "Luuc zegt: HË Sander, je hebt zo college toch?<br/>"
        + "Sander zegt: ik ben inderdaad op weg naar de ",
        "setnr_162/cond_i/list_b:Melanie zet wat water op voor een kopje thee.<br/>"
        + "Melanie vraagt: wil jij ook fruitige thee?<br/>"
        + "Sabine zegt: doe mij inderdaad maar zo'n zakje ",
        "setnr_163/cond_i/list_b:Pascal en Janneke hebben het over hun toekomstdromen.<br/>"
        + "Pascal zegt: jij wilt zeker naar het platteland verhuizen?<br/>"
        + "Janneke zegt: het lijkt me inderdaad fijn om later in een ",
        "setnr_164/cond_i/list_b:Arjan en Kirsten hebben het over hun droomhuizen.<br/>"
        + "Arjan zegt: jij droomt zeker van een mooie woning in de stad?<br/>"
        + "Kirsten zegt: ik wil inderdaad graag in een ",
        "setnr_165/cond_i/list_b:Pim is niet op het werk, want hij moest naar het ziekenhuis.<br/>"
        + "Gert zegt: het zal wel niet voor iets ernstigs zijn toch?<br/>"
        + "Zijn collega zegt: hij zei inderdaad dat hij er voor een ",
        "setnr_166/cond_i/list_b:Eveline en Roel hebben het over de toekomstplannen van hun muzikale zoon.<br/>"
        + "Evelien zegt: misschien dat hij later verder wil in de muziek hË..<br/>"
        + "Roel zegt: hij wil inderdaad zijn brood verdienen als ",
        "setnr_167/cond_i/list_b:Nancy en Sam gaan samen studeren in de bibliotheek.<br/>"
        + "Nancy vraagt: zullen we in die ene studiezaal achterin gaan zitten?<br/>"
        + "Sam zegt: dat vind ik inderdaad een best wel ",
        "setnr_168/cond_i/list_b:Susanne en Brian hebben in Afrika een safaritocht gemaakt.<br/>"
        + "Een vriend van hen vraagt: lopen daar nou echt allerlei wilde dieren rond?<br/>"
        + "Brian zegt: we hebben inderdaad vooral heel veel ",
        "setnr_169/cond_i/list_b:Marleen en Alex lopen in het bos en zien allerlei sporen op de grond.<br/>"
        + "Marleen vraagt: zouden dit sporen van wilde dieren zijn?<br/>"
        + "Alex zegt: dat zijn inderdaad afdrukken van een ",
        "setnr_170/cond_i/list_b:Rutger en Renske maken een wandeling.<br/>"
        + "Rutger zegt: volgens mij zie ik daar een roofvogel vliegen!<br/>"
        + "Renske zegt: dat is inderdaad een grote ",
        "setnr_171/cond_i/list_b:Pauline en Nico kamperen in het bos en schrikken van een geluid.<br/>"
        + "Pauline vraagt: zou dat van een wild dier zijn?<br/>"
        + "Nico zegt: ik dacht inderdaad dat ik een ",
        "setnr_172/cond_i/list_b:Harmen en Margriet maken een wandeling door de bergen, als Margriet opeens een bijzondere steen ziet liggen.<br/>"
        + "Harmen zegt: het lijkt wel een edelsteen!<br/>"
        + "Margriet zegt: het is inderdaad een stukje ",
        "setnr_173/cond_i/list_b:Op 23 december gaan Ralph en Silvia shoppen om het huis aan te kleden.<br/>"
        + "Ralph vraagt: nemen we nog wat dingen mee voor in de boom?<br/>"
        + "Silvia zegt: ik wil inderdaad een aantal ",
        "setnr_174/cond_i/list_b:Kornelis en Loes lopen door een kunstgalerie.<br/>"
        + "Kornelis zegt: dit schilderij past helemaal bij jouw smaak Loes!<br/>"
        + "Loes zegt: ik vind het inderdaad heel erg ",
        "setnr_175/cond_i/list_b:Joep en Dominique wachten op het station op hun trein, en gaan nog even langs de kiosk.<br/>"
        + "Dominique vraagt: heb jij ook zin in een lekkere vette hap?<br/>"
        + "Joep zegt: ik wil inderdaad graag een ",
        "setnr_176/cond_i/list_b:Stefanie en Marvin maken een lange autorit; Stefanie verveelt zich en begint achter de stoel te rommelen.<br/>"
        + "Marvin vraagt: pak je iets om te lezen?<br/>"
        + "Stefanie zegt: ik ben inderdaad aan het zoeken naar mijn  ",
        "setnr_177/cond_i/list_b:Veronique is naar een lezing van een beroemde hoogleraar geweest. <br/>"
        + "Haar collega zegt: hij kan zijn verhaal altijd erg goed overbrengen hË?<br/>"
        + "Veronique zegt: ik vond zijn lezing inderdaad ontzettend ",
        "setnr_178/cond_i/list_b:Vivian is naar een feestje geweest en komt laat thuis.<br/>"
        + "Haar huisgenoot zegt: je hebt je vast goed vermaakt?<br/>"
        + "Vivian zegt: Het was inderdaad heel erg ",
        "setnr_179/cond_i/list_b:Op een warme zomerdag gaan Andrea en Leo een ijsje halen bij de ijssalon.<br/>"
        + "Andrea zegt: ga jij ook voor een frisse sorbetsmaak?<br/>"
        + "Leo zegt: Ik wil inderdaad twee bolletjes ",
        "setnr_180/cond_i/list_b:Jolijne en Wernard bereiden de lunch voor hun zoontjes kinderfeestje.<br/>"
        + "Wernard vraagt: zullen we nog wat broodjes zoet beleggen?<br/>"
        + "Jolijne zegt: we kunnen er inderdaad nog wel een paar met ",
        "setnr_1/cond_i/list_c:Piet is jarig en Anja neemt hem mee naar de beste Italiaan van de stad.<br/>"
        + "Anja vraagt: je gaat vast een klassieker bestellen? <br/>"
        + "Piet zegt: ik heb inderdaad trek in een ",
        "setnr_2/cond_i/list_c:Hanna doet auditie bij een strijkkwartet en speelt de sterren van de hemel. <br/>"
        + "De dirigent vraagt: je hebt vast veel ervaring?<br/>"
        + "Hanna zegt: ik speelde vroeger inderdaad vaak op de ",
        "setnr_3/cond_i/list_c:Simon gaat met zijn zus een weekendje naar Londen.<br/>"
        + "Zijn zus vraagt: we gaan zeker onder het kanaal door naar Engeland?<br/>"
        + "Simon zegt: ik heb inderdaad twee tickets voor de  ",
        "setnr_4/cond_i/list_c:Ondanks haar angst voor dieren is Irene naar het circus geweest.<br/>"
        + "Jan vraagt: je vond de dierenact zeker doodeng?<br/>"
        + "Irene zegt: ik schrok inderdaad van die grote ",
        "setnr_5/cond_i/list_c:In de Efteling komt Cor vloekend van de pijn het snoephuisje van Hans en Grietje uit.<br/>"
        + "Wilma vraagt: de deurposten waren vast te laag voor jou?<br/>"
        + "Cor zegt: ik stootte er inderdaad met mijn ",
        "setnr_6/cond_i/list_c:Denise is sinds twee weken op dieet en is al 5 kilo afgevallen.<br/>"
        + "Sanne vraagt: je eet zeker heel gezond tussen de middag?<br/>"
        + "Denise zegt: ik koop inderdaad meestal een ",
        "setnr_7/cond_i/list_c:Ondanks de regen gaan Sophie en Ewoud te voet naar het centrum.<br/>"
        + "Ewoud vraagt: moet je geen andere schoenen aandoen?<br/>"
        + "Sophie zegt: ik was inderdaad van plan mijn ",
        "setnr_8/cond_i/list_c:Tineke is net terug van haar vakantie op Ibiza, en ze is hartstikke bruin.<br/>"
        + "Marjolein vraagt: het was daar zeker heerlijk weer?<br/>"
        + "Tineke zegt: ik heb inderdaad behoorlijk wat ",
        "setnr_9/cond_i/list_c:Het is zaterdagavond, en Judith komt opgedoft de kamer in lopen.<br/>"
        + "Lennart vraagt: jij gaat zeker met Lara dansen vanavond?<br/>"
        + "Judith zegt: we hebben inderdaad afgesproken om naar de  ",
        "setnr_10/cond_i/list_c:Sven heeft een jaar in China gewoond, en Dennis heeft beloofd voor hem te koken.<br/>"
        + "Sven zegt: ik hoop dat je Hollandse kost hebt gemaakt!<br/>"
        + "Dennis zegt: ik heb inderdaad een flinke pan ",
        "setnr_11/cond_i/list_c:Bea en Christine gaan na een bergtocht door de sneeuw een cafÈ in om op te warmen. <br/>"
        + "Bea vraagt: neem jij ook een lekker warm drankje?<br/>"
        + "Christine zegt: ik heb inderdaad trek in een ",
        "setnr_12/cond_i/list_c:Ans heeft een enorme bos bloemen van Jaap gekregen.<br/>"
        + "Jaap vraagt: heb je ze wel een mooi plekje gegeven?<br/>"
        + "Ans zegt: ik heb ze inderdaad in die grote ronde ",
        "setnr_13/cond_i/list_c:Monique is zwanger, en komt haar vriendin Ine tegen in de kroeg.<br/>"
        + "Ine vraagt: jij bent zeker de Bob vandaag?<br/>"
        + "Monique zegt: ik stond inderdaad op het punt een ",
        "setnr_14/cond_i/list_c:Angela heeft een week bij Betty gelogeerd, en haar moeder komt haar weer ophalen.<br/>"
        + "Betty vraagt: is Angela altijd al zo'n ongezonde eter geweest?<br/>"
        + "Haar moeder zegt: ze eet inderdaad bijna elke dag een bord ",
        "setnr_15/cond_i/list_c:Het is Valentijnsdag en Jurgen komt Wim tegen bij de bloemist.<br/>"
        + "Wim vraagt: ga je je vriendin met een bloemetje verrassen?<br/>"
        + "Jurgen zegt: ik wil haar inderdaad een mooie witte ",
        "setnr_16/cond_i/list_c:Sofia staat bekend om haar Italiaanse kookkunsten.<br/>"
        + "Erika vraagt: krijgen we als toetje weer een van je specialiteiten?<br/>"
        + "Sofia zegt: ik heb inderdaad een hele schaal ",
        "setnr_17/cond_i/list_c:Omdat Mark gaat verhuizen, gaat hij samen met Pieter shoppen.<br/>"
        + "Pieter vraagt: je wilt dus ook nieuwe meubels aanschaffen?<br/>"
        + "Mark zegt: ik wil inderdaad een nieuwe  ",
        "setnr_18/cond_i/list_c:Diana is met haar klas van de kunstacademie een weekend naar Parijs geweest.<br/>"
        + "Haar vriendin vraagt: jullie hebben zeker veel kunst gezien?<br/>"
        + "Diana zegt: we zijn inderdaad elke dag naar een ",
        "setnr_19/cond_i/list_c:Thomas maakt zich op voor een cultureel dagje Amsterdam.<br/>"
        + "Tim vraagt: je gaat zeker de meesterwerken in het Rijksmuseum bewonderen? <br/>"
        + "Thomas zegt: ik kijk er inderdaad naar uit om de ",
        "setnr_20/cond_i/list_c:RenÈ moet naar de tandarts om drie gaatjes te laten boren.<br/>"
        + "De tandarts vraagt: eet je toevallig veel zoetigheid?<br/>"
        + "RenÈ zegt: ik heb inderdaad de laatste tijd best vaak ",
        "setnr_21/cond_i/list_c:Liam wil zijn afstuderen groots vieren. <br/>"
        + "Zijn moeder vraagt: wil je al je vrienden hier thuis uitnodigen?<br/>"
        + "Liam zegt: ik wil inderdaad een feestje in de  ",
        "setnr_22/cond_i/list_c:Op een zonnige middag heeft Casper zijn moeder aan de telefoon.<br/>"
        + "Zijn moeder vraagt: ga je straks buiten eten met dit mooie weer?<br/>"
        + "Casper zegt: ik ga inderdaad met vrienden ",
        "setnr_23/cond_i/list_c:Arno gaat met Claudia een weekendje logeren op de boerderij van haar ouders.<br/>"
        + "Arno vraagt: het ontbijt komt zeker rechtstreeks uit het kippenhok?<br/>"
        + "Claudia zegt: ze hebben inderdaad alleen maar verse ",
        "setnr_24/cond_i/list_c:Joris zit aan de waterkant als Henk langs komt lopen.<br/>"
        + "Henk vraagt: zit je lekker te vissen?<br/>"
        + "Joris zegt: ik probeer inderdaad mijn nieuwe ",
        "setnr_25/cond_i/list_c:Iris ziet erg bleek en ze voelt zich niet zo lekker.<br/>"
        + "Haar zus vraagt: je laat je toch wel onderzoeken vandaag?<br/>"
        + "Iris zegt: ik moet inderdaad om twee uur bij de ",
        "setnr_26/cond_i/list_c:Na een saai college zit Sharon uitgebreid te gapen. <br/>"
        + "Olivier zegt: volgens mij kan jij wel een caffeÔneboost gebruiken!<br/>"
        + "Sharon zegt: ik heb inderdaad trek in een ",
        "setnr_27/cond_i/list_c:Ilse is samen met Sybrine de was aan het ophangen.<br/>"
        + "Sybrine vraagt: heb je iets speciaals gebruikt om het beddengoed zo zacht te krijgen? <br/>"
        + "Ilse zegt: ik heb inderdaad gisteren nieuwe ",
        "setnr_28/cond_i/list_c:Puck en Pleun gaan op bezoek bij Esmee, die met gym haar arm gebroken heeft.<br/>"
        + "Puck vraagt: zullen we iets gezonds voor haar meenemen?<br/>"
        + "Pleun zegt: het lijkt me inderdaad leuk om een ",
        "setnr_29/cond_i/list_c:De hond begint te blaffen en rondjes te rennen als Marius de kamer binnenkomt. <br/>"
        + "Evelien vraagt: ga je een rondje wandelen met Fikkie?<br/>"
        + "Marius zegt: ik was inderdaad op zoek naar zijn ",
        "setnr_30/cond_i/list_c:Wouter is ceremoniemeester op de bruiloft van zijn beste vriend Klaas.<br/>"
        + "Klaas vraagt: je hebt toch wel aan de alcohol gedacht?<br/>"
        + "Wouter zegt: ik heb inderdaad twaalf flessen ",
        "setnr_31/cond_i/list_c:De avond voor zijn zoontjes verjaardag komt Henk de trap afgestommeld. <br/>"
        + "Jolanda vraagt: je hebt zeker de versiering van zolder gehaald?<br/>"
        + "Henk zegt: ik heb inderdaad de doos met ",
        "setnr_32/cond_i/list_c:Anna en Elske willen graag goede cijfers halen dit studiejaar.<br/>"
        + "Anna vraagt: ga jij ook alvast het tentamen van volgende week voorbereiden?<br/>"
        + "Elske zegt: ik wil inderdaad een paar uur naar de ",
        "setnr_33/cond_i/list_c:Martin en Leon lopen samen over de markt.<br/>"
        + "Martin vraagt: heb jij ook zo'n trek in gebakken vis?<br/>"
        + "Leon zegt: doe mij inderdaad maar een bakje ",
        "setnr_34/cond_i/list_c:Op zijn vrije dag komt Olaf Thea tegen in het dorp.<br/>"
        + "Thea vraagt: ben je ook op weg naar de manege?<br/>"
        + "Olaf zegt: ik wilde inderdaad een ritje gaan maken op mijn ",
        "setnr_35/cond_i/list_c:Teun en Bella zitten 's avonds te lezen op de bank, als Teun zijn boek even opzij legt.<br/>"
        + "Bella vraagt: het wordt wel een beetje donker om te lezen hË?<br/>"
        + "Teun zegt: ik was inderdaad van plan de ",
        "setnr_36/cond_i/list_c:Stefan gaat vanavond een serenade brengen aan de liefde van zijn leven.<br/>"
        + "Bernard vraagt: je gaat jezelf zeker begeleiden bij het zingen?<br/>"
        + "Stefan zegt: ik heb inderdaad besloten om mijn ",
        "setnr_37/cond_i/list_c:Dennis en Lotte liggen in hun tent te lezen, als Dennis wild om zich heen begint te zwaaien.<br/>"
        + "Dennis vraagt: hoorde jij ook dat irritante gezoem?<br/>"
        + "Lotte zegt: ik dacht inderdaad dat ik een ",
        "setnr_38/cond_i/list_c:Bij binnenkomst trekt Felix met zijn kaplaarzen een spoor van modder door de kamer.<br/>"
        + "Mien vraagt:  zeg, maak je de vloer wel weer schoon?<br/>"
        + "Felix zegt: ik was inderdaad van plan om in de bijkeuken een ",
        "setnr_39/cond_i/list_c:Martine en Nancy zitten een zielige film te kijken als Martine ineens sniffend opstaat.<br/>"
        + "Nancy vraagt: je wilt zeker je tranen wegvegen?<br/>"
        + "Martine zegt: ik ga inderdaad een pakje ",
        "setnr_40/cond_i/list_c:Bruno komt de kamer binnen als zijn moeder verwoed de bank aan het stofzuigen is.<br/>"
        + "Zijn moeder vraagt: jij hebt zeker zitten kruimelen voor de tv?<br/>"
        + "Bruno zegt: ik heb inderdaad tijdens de film een zak ",
        "setnr_41/cond_i/list_c:Vlak voor zijn sollicitatiegesprek heeft Maarten een broodje met pesto gegeten. <br/>"
        + "Maarten vraagt: heb ik nog iets tussen mijn tanden?<br/>"
        + "Bea zegt: ik zie inderdaad daar rechts iets in je ",
        "setnr_42/cond_i/list_c:Hanneke brengt een bezoek aan een brouwerij in de stad.<br/>"
        + "De barman vraagt: lust je iets van de tap?<br/>"
        + "Hanneke zegt: ik wil inderdaad graag jullie nieuwste ",
        "setnr_43/cond_i/list_c:Mira en haar zus Linda gaan een avondje naar de opera.<br/>"
        + "Mira vraagt: ga jij je ook mooi uitdossen?<br/>"
        + "Linda zegt: ik wil inderdaad mijn nieuwe ",
        "setnr_44/cond_i/list_c:Het is winter en na schooltijd komt Vera Bart tegen in de fietsenstalling.<br/>"
        + "Vera vraagt: jij gaat toch ook mee naar de ijsbaan?<br/>"
        + "Bart zegt: ik heb inderdaad zin om lekker te ",
        "setnr_45/cond_i/list_c:Jans team heeft de eerste voetbalwedstrijd na de winterstop ruim gewonnen.<br/>"
        + "Ans vraagt: je hebt zeker geen bal doorgelaten?<br/>"
        + "Jan zegt: ik heb inderdaad kou staan lijden in de  ",
        "setnr_46/cond_i/list_c:Op oudjaarsavond komt Fred met een auto vol dozen terug uit de stad.<br/>"
        + "Zijn buurman zegt: zo te zien ga je weer knallend het jaar uit?<br/>"
        + "Fred zegt: ik heb inderdaad voor een kapitaal aan ",
        "setnr_47/cond_i/list_c:Na een concert in het stadion komt Loek Sabine tegen.<br/>"
        + "Loek vraagt: ga je ook met het openbaar vervoer terug naar de stad?<br/>"
        + "Sabine zegt: ik sta inderdaad al een tijdje op een ",
        "setnr_48/cond_i/list_c:Yvette komt lijkbleek thuis van de kermis.<br/>"
        + "Haar vader vraagt: had je weer last van hoogtevrees?<br/>"
        + "Yvette zegt: ik werd inderdaad niet goed in het ",
        "setnr_49/cond_i/list_c:Lea en Fred hebben een heerlijke steak gegeten in een restaurant, en Lea komt terug van het toilet. <br/>"
        + "Lea vraagt: weet de ober al dat we willen betalen?<br/>"
        + "Fred zegt: ik heb inderdaad gevraagd of we de ",
        "setnr_50/cond_i/list_c:Nick en Lynn plannen een citytrip voor de meivakantie.<br/>"
        + "Nick vraagt: jij wilt zeker naar Spanje toe?<br/>"
        + "Lynn zegt: het lijkt me inderdaad leuk om naar ",
        "setnr_51/cond_i/list_c:Sarah en Koen zijn bezig om een wandelvakantie te boeken.<br/>"
        + "Sarah vraagt: is ScandinaviÎ een idee?<br/>"
        + "Koen zegt: ik zou inderdaad graag naar ",
        "setnr_52/cond_i/list_c:Tess en Ruben willen een weekje naar een all-inclusive hotel in de zon. <br/>"
        + "Ruben vraagt: jij dacht aan de Antillen toch? <br/>"
        + "Tess zegt: ik wil inderdaad graag naar ",
        "setnr_53/cond_i/list_c:Emma en Isa gaan een middagje shoppen, Emma past een groene blouse.<br/>"
        + "Emma vraagt: groen is echt mijn kleur hË?<br/>"
        + "Isa zegt: ik vind inderdaad dat deze blouse je ",
        "setnr_54/cond_i/list_c:Rianne en Lotte werken op een HR-afdeling en zijn in gesprek over de sollicitanten.<br/>"
        + "Rianne vraagt: gaat jouw voorkeur ook uit naar Pieter? <br/>"
        + "Lotte zegt: ik vind hem inderdaad vergeleken met de rest de ",
        "setnr_55/cond_i/list_c:Nina en Sven willen dit weekend uit eten, en bekijken de website van een Grieks restaurant.<br/>"
        + "Sven vraagt: dit restaurant was de vorige keer erg goed bevallen hË?<br/>"
        + "Nina zegt: ik herinner me inderdaad dat de moussaka daar ",
        "setnr_56/cond_i/list_c:Jesse en Maud willen binnenkort met de familie uit eten, en bekijken de website van een restaurant. <br/>"
        + "Maud zegt: hier hebben we de vorige keer goed gegeten hË?<br/>"
        + "Jesse zegt: ik herinner me inderdaad dat de porties daar erg ",
        "setnr_57/cond_i/list_c:Lieke en Finn werken aan de promotie van een evenement, en zoeken een drukker voor de posters.<br/>"
        + "Lieke zegt: volgens mij waren we de vorige keer erg tevreden over deze drukker?<br/>"
        + "Finn zegt: ik herinner me inderdaad dat de kwaliteit ",
        "setnr_58/cond_i/list_c:Cas en Merel willen een rondreis door de VS gaan maken en zoeken een geschikt reisbureau.<br/>"
        + "Cas zegt: de vorige keer werden we bij dit reisbureau erg goed geholpen toch?<br/>"
        + "Merel zegt: ik herinner me inderdaad dat de service ",
        "setnr_59/cond_i/list_c:Eline en Rick willen komende week de achtertuin flink opknappen. <br/>"
        + "Eline vraagt: zullen we van dat hoekje een moestuin maken?<br/>"
        + "Rick zegt: het lijkt me inderdaad leuk om daar allerlei soorten ",
        "setnr_60/cond_i/list_c:Dylan en Esmee willen hun woonkamer opnieuw indelen.<br/>"
        + "Dylan zegt: hier wilden we de eethoek van maken toch?<br/>"
        + "Esmee zegt: die plek lijkt me inderdaad geschikt om de grote houten ",
        "setnr_61/cond_e/list_c:Vera en Thijmen bespreken de inrichting van hun nieuwe woning.<br/>"
        + "Vera vraagt: zullen we voor boven de tafel een mooi kunstwerk uitzoeken?<br/>"
        + "Thijmen zegt: het lijkt me eigenlijk mooi om daar een ",
        "setnr_62/cond_e/list_c:Robin en Jasmijn gaan vanmiddag naar het asiel om een huisdier uit te zoeken.<br/>"
        + "Robin vraagt: je houdt van grote honden toch?<br/>"
        + "Jasmijn zegt: ik wil eigenlijk graag een mooie ",
        "setnr_63/cond_e/list_c:Demi en Jasper gaan uit eten bij een restaurant aan de haven.<br/>"
        + "Jasper vraagt: ga jij ook voor een van de visgerechten?<br/>"
        + "Demi zegt: ik wil eigenlijk als hoofdgerecht de ",
        "setnr_64/cond_e/list_c:Mike en Marit gaan in de kerstvakantie uit eten bij een nieuw restaurant in de stad.<br/>"
        + "Marit zegt: we zouden hier zeker de wildgerechten moeten uitproberen!<br/>"
        + "Mike zegt: ik wil eigenlijk als hoofdgerecht ",
        "setnr_65/cond_e/list_c:Gijs en Milou zijn een dagje naar een pretpark, en hebben alle attracties al gehad.<br/>"
        + "Gijs vraagt: zullen we als afsluiter nog even in een enge attractie gaan?<br/>"
        + "Milou zegt: ik wil eigenlijk nog een ritje in de ",
        "setnr_66/cond_e/list_c:Tim en Roos zijn een weekendje in Parijs, en bespreken bij het ontbijt welke musea ze al hebben bezocht.<br/>"
        + "Een andere hotelgast vraagt: gaan jullie vanmiddag nog naar een van de beroemde kerken?<br/>"
        + "Tim zegt: we willen eigenlijk nog heel graag de ",
        "setnr_67/cond_e/list_c:Het is zomer en na schooltijd komt Max Sem tegen op het schoolplein.<br/>"
        + "Max vraagt: jij gaat toch ook mee naar het strand?<br/>"
        + "Sem zegt: ik heb eigenlijk zin om lekker te gaan ",
        "setnr_68/cond_e/list_c:Na de laatste les komen Amber en Diedre elkaar tegen bij de kluisjes.<br/>"
        + "Amber vraagt: jij gaat toch ook mee naar het winkelcentrum?<br/>"
        + "Diedre zegt: ik heb eigenlijk zin om te ",
        "setnr_69/cond_e/list_c:Willem en Tijn voeren een discussie over milieu en energie.<br/>"
        + "Willem zegt: ik denk dat groene stroom de toekomst is!<br/>"
        + "Tijn zegt: ik voorspel eigenlijk een filnke toename van het aantal ",
        "setnr_70/cond_e/list_c:Jos en Leny ploffen op de bank voor een filmavondje. <br/>"
        + "Jos zegt: zullen we er iets lekker zouts bij pakken?<br/>"
        + "Leny zegt: ik heb eigenlijk al de hele dag zin in ",
        "setnr_71/cond_e/list_c:Frank en Mirjam drinken een biertje op een terrasje in de namiddagzon. <br/>"
        + "Frank zegt: zullen we er lekker wat snacks uit de frituur bij bestellen?<br/>"
        + "Mirjam zegt: ik heb eigenlijk trek in een portie ",
        "setnr_72/cond_e/list_c:John en Caroline zitten in een restaurant en hebben net hun hoofdgerecht op.<br/>"
        + "Caroline zegt: heb jij ook zin in iets zoets na?<br/>"
        + "John zegt: ik heb eigenlijk zin in een ",
        "setnr_73/cond_e/list_c:Nel en Piet bespreken met hun dochter wat ze vanmiddag met hun kleinzoon gaan doen.<br/>"
        + "Piet zegt: met deze sneeuw kunnen we wel de helling af!<br/>"
        + "Nel zegt: volgens mij wil hij eigenlijk niets liever dan  ",
        "setnr_74/cond_e/list_c:Ilse en Patty hebben het over de boeken van hun lievelings auteur.<br/>"
        + "Ilse zegt: haar vroege werk is wel het mooiste hË?<br/>"
        + "Patty zegt: ik vind haar debuutroman eigenlijk een van haar ",
        "setnr_75/cond_e/list_c:Jochem en Ilona hebben het over de acteur Tom Hanks.<br/>"
        + "Jochem zegt: hij is erg gegroeid als acteur in zijn carriËre hË?<br/>"
        + "Ilona zegt: ik vind hem eigenlijk in zijn laastste film het ",
        "setnr_76/cond_e/list_c:Stan en Romy luisteren naar muziek van hun favoriete rockband.<br/>"
        + "Stan zegt: hun recente muziek is echt geweldig!<br/>"
        + "Romy zegt: ik vind dat ze eigenlijk met die nieuwe drummer steeds ",
        "setnr_77/cond_e/list_c:Rianne en Justus bespreken wat voor sport leuk zou zijn voor hun zoon.<br/>"
        + "Rianne vraagt: zullen we hem voor een teamsport opgeven?<br/>"
        + "Justus zegt: ik denk dat hij het eigenlijk leuk zou vinden om op ",
        "setnr_78/cond_e/list_c:Ben en Kim staan te kibbelen; Kim laat altijd de huistelefoon ergens slingeren.<br/>"
        + "Ben vraagt: heb je de telefoon zeker weer in de slaapkamer laten liggen?<br/>"
        + "Kim zegt: ik denk dat ik hem eigenlijk bovenop het ",
        "setnr_79/cond_e/list_c:Peter en Elly zoeken bij de bloemist iets uit voor hun moeders verjaardag.<br/>"
        + "Peter vraagt: ze vindt warme kleuren erg mooi hË?<br/>"
        + "Elly zegt: ze houdt eigenlijk erg veel van  ",
        "setnr_80/cond_e/list_c:Anne en Jens hebben het over de verkiezingen.<br/>"
        + "Anne vraagt: jij gaat vast voor een partij die zich sterk maakt voor het milieu?<br/>"
        + "Jens zegt: ik ga eigenlijk stemmen op een ",
        "setnr_81/cond_e/list_c:Senna en Thom kijken in de gids naar wat er vanavond op tv komt.<br/>"
        + "Senne vraagt: jij houdt toch ook van thrillers?<br/>"
        + "Thom zegt: ik kijk eigenlijk graag naar hele ",
        "setnr_82/cond_e/list_c:Juliette en Teun maken plannen voor hun weekendje aan zee.<br/>"
        + "Juliette zegt: jij wil vast lekker de natuur in gaan!<br/>"
        + "Teun zegt: ik wil eigenlijk een wandeling door de ",
        "setnr_83/cond_e/list_c:Het is winter en Steffi en Arjan zijn bij de ijsbaan op het dorpsplein.<br/>"
        + "Steffi vraagt: wil jij ook een lekker warm drankje? <br/>"
        + "Arjan zegt: ik heb eigenlijk veel zin in ",
        "setnr_84/cond_e/list_c:Naomi bespreekt haar toekomstplannen met haar ouders.<br/>"
        + "Haar vader zegt: je zat aan een leidinggevende functie in de horeca te denken toch?<br/>"
        + "Naomi zegt: ik wil eigenlijk manager van een ",
        "setnr_85/cond_e/list_c:Rea studeert Frans, en praat met haar ouders over wat voor werk ze daarna wil gaan doen.<br/>"
        + "Haar moeder zegt: je wil graag het onderwijs in toch?<br/>"
        + "Rea zegt: ik wil eigenlijk op zoek naar een baan als ",
        "setnr_86/cond_e/list_c:Fleur is met haar opa en oma aan het praten over haar toekomstplannen.<br/>"
        + "Haar oma zegt: je wil een baan in de zorg gaan zoeken toch?<br/>"
        + "Fleur zegt: ik wil eigenlijk graag gaan werken als ",
        "setnr_87/cond_e/list_c:Jordy en Elke zijn bij een outdoorzaak aan het kijken naar skimutsen.<br/>"
        + "Jordy zegt: zo'n felgekleurde is echt wat voor jou!<br/>"
        + "Elke zegt: ik wil eigenlijk graag die ",
        "setnr_88/cond_e/list_c:Hannah heeft binnenkort een belangrijke meeting, en ze zoekt samen met Julie naar een outfit. <br/>"
        + "Julie zegt: ik denk dat je het beste voor een blouse met een neutrale kleur kunt gaan.<br/>"
        + "Hannah zegt: ik wil eigenlijk graag die ",
        "setnr_89/cond_e/list_c:Bram wil graag een nieuwe auto kopen, maar weet nog niet in welke kleur. <br/>"
        + "Zijn zus zegt: als je een opvallende kleur neemt, vind je 'm altijd snel terug in een parkeergarage!<br/>"
        + "Bram zegt: ik zat er eigenlijk aan te denken om een ",
        "setnr_90/cond_e/list_c:Shirley komt erg moe terug van haar werk en ploft naast Fabian neer op de bank. <br/>"
        + "Fabian zegt: als ik jou was zou ik vanavond lekker wat ontspannen!<br/>"
        + "Shirley zegt: het lijkt me eigenlijk goed om even te gaan ",
        "setnr_91/cond_e/list_c:De eigenaar en manager van een restaurant zijn in gesprek; het loopt niet goed. <br/>"
        + "De eigenaar vraagt: zou het met ons keukenteam te maken hebben?<br/>"
        + "De manager zegt: ik heb eigenlijk de indruk dat het aan de nieuwe ",
        "setnr_92/cond_e/list_c:De eigenaar en manager van een camping zijn in gesprek over hun tarieven.<br/>"
        + "De eigenaar zegt: misschien kunnen we onze luxe accomodaties wat duurder maken?<br/>"
        + "De manager zegt: ik denk dat we eigenlijk iets aan de prijzen van de ",
        "setnr_93/cond_e/list_c:De medewerkers van een hotel zijn in gesprek; er is veel ontevredenheid onder de gasten.<br/>"
        + "Wouter zegt: misschien zijn de kamers erg vies?<br/>"
        + "Andre zegt: er zijn eigenlijk veel klachten over de ",
        "setnr_94/cond_e/list_c:Lotte is wel 15 kilo kwijt geraakt in de afgelopen maanden.<br/>"
        + "Haar zus vraagt: ben je zoveel afgevallen door je nieuwe eetpatroon?<br/>"
        + "Lotte zegt: het komt eigenlijk doordat ik een zeer strikt ",
        "setnr_95/cond_e/list_c:Dorien en Bea willen graag flink afvallen voordat het bikiniseizoen aanbreekt.<br/>"
        + "Dorien vraagt: ga jij ook weer trainen vanavond?<br/>"
        + "Bea zegt: ik wil eigenlijk nog even naar de ",
        "setnr_96/cond_e/list_c:Violaine en Maaike zitten op een terrasje en bekijken de kaart met fruitsappen.<br/>"
        + "Violaine vraagt: neem jij ook een sapje met tropisch fruit?<br/>"
        + "Maaike zegt: ik wil eigenlijk een smoothie met  ",
        "setnr_97/cond_e/list_c:Simon en Andrea willen graag meer wandelen, en denken erover zich aan te sluiten bij een clubje.<br/>"
        + "Simon zegt: volgens mij lopen ze bij die club stevig door!<br/>"
        + "Andrea zegt: ik hoorde eigenlijk dat hun wandeltempo heel ",
        "setnr_98/cond_e/list_c:Bente overweegt over te stappen naar een nieuwe dansschool die binnenkort opent.<br/>"
        + "Haar zus zegt: volgens mij betaal je bij die school veel minder lesgeld!<br/>"
        + "Bente zegt: ik had eigenlijk gezien dat de lessen daar een stuk ",
        "setnr_99/cond_e/list_c:Wessel en Marte willen een dagje naar een pretpark, maar hebben een hekel aan lang wachten.<br/>"
        + "Wessel zegt: misschien is eind november een optie?<br/>"
        + "Marte zegt: ik denk dat de rijen dan eigenlijk best wel  ",
        "setnr_100/cond_e/list_c:Justin en Niels hebben net hun wiskundehuiswerk voor volgende week opgekregen.<br/>"
        + "Justin zegt: pfoe het volgende hoofdstuk wordt echt verschrikkelijk!<br/>"
        + "Niels zegt: die sommen zien er eigenlijk heel erg ",
        "setnr_101/cond_e/list_c:De docent geeft de leerlingen huiswerk op dat ze individueel of in tweetallen kunnen maken.<br/>"
        + "De docent vraagt: Thom, jij wil deze opdracht zeker alleen doen?<br/>"
        + "Thom zegt: in mijn eentje is het eigenlijk een heel stuk ",
        "setnr_102/cond_e/list_c:Bart wil na de zomer graag op muziekles.<br/>"
        + "Zijn broer zegt: ik denk dat een blaasinstrument wel iets voor jou is!<br/>"
        + "Bart zegt: ik wil eigenlijk leren om een ",
        "setnr_103/cond_e/list_c:Froukje gaat binnenkort beginnen met danslessen.<br/>"
        + "Haar vriendin vraagt: je zat te denken aan klassieke dans toch?<br/>"
        + "Froukje zegt: ik wil me eigenlijk gaan opgeven voor ",
        "setnr_104/cond_e/list_c:Dennis meldt zich aan bij een nieuw sportcentrum.<br/>"
        + "Zijn vriendin vraagt: je wil daar graag groepslessen in vechtsport nemen toch?<br/>"
        + "Dennis zegt: ik wil me eigenlijk gaan opgeven voor  ",
        "setnr_105/cond_e/list_c:Op de camping willen Levi en Noah met hun vriendje Mike spelen.<br/>"
        + "Levi zegt: hij is vast al aan het eten..<br/>"
        + "Noah zegt: ik zag hem eigenlijk in de richting van de  ",
        "setnr_106/cond_e/list_c:Gerard en Annemarie hebben het over de schoolresultaten van hun dochter.<br/>"
        + "Gerard zegt: vooral met wiskunde heeft ze erg veel moeite hËÖ<br/>"
        + "Annemarie zegt: ik begreep eigenlijk dat haar gemiddelde zo rond de  ",
        "setnr_107/cond_e/list_c:Henk en Annemieke bespreken hoe het met de schoolprestaties van hun zoon gaat.<br/>"
        + "Annemieke zegt: een talenknobbel heeft hij niet hË?<br/>"
        + "Henk zegt: hij heeft eigenlijk de meeste moeite met het vak ",
        "setnr_108/cond_e/list_c:Bregje en Rosanne gaan samen een middagje shoppen.<br/>"
        + "Bregje zegt: je wil graag wat leuke winterkleding kopen toch?<br/>"
        + "Rosanne zegt: ik hoop eigenlijk een mooie nieuwe ",
        "setnr_109/cond_e/list_c:Kim en Sophia gaan samen eventjes naar de stad.<br/>"
        + "Kim vraagt: wilde je nog gaan kijken voor een mooi sieraad?<br/>"
        + "Sophia zegt: ik wil eigenlijk gaan kijken voor een ",
        "setnr_110/cond_e/list_c:Ome Willem uit Canada komt een weekje op bezoek bij zijn familie.<br/>"
        + "Anna zegt: hij zou het vast super vinden als we 'm meenemen op een cultureel uitje!<br/>"
        + "Petra zegt: ik denk dat hij eigenlijk graag naar een ",
        "setnr_111/cond_e/list_c:Theo geeft een feestje bij hem thuis.<br/>"
        + "Frank zegt: ik heb Theo al een tijdje niet gezien.. zou hij wiet aan het halen zijn?<br/>"
        + "Janne zegt: ik hoorde hem eigenlijk zeggen dat hij even naar de ",
        "setnr_112/cond_e/list_c:Bernard en Petra werken aan de faculteit Betawetenschappen, en zoeken hun collega Wernard.<br/>"
        + "Bernard zegt: misschien is hij in het laboratorium aan het werk?<br/>"
        + "Petra zegt: ik geloof dat hij eigenlijk bezig is met een ",
        "setnr_113/cond_e/list_c:Nu hun college willen David en Max gaan lunchen met Nick, dus David stuurt een berichtje naar Nick. <br/>"
        + "David zegt: en, komt Nick er al aan?<br/>"
        + "Max zegt: hij is eigenlijk op weg naar de ",
        "setnr_114/cond_e/list_c:Het bestuur van een basisschool bespreekt de lage CITO-scores van groep 8.<br/>"
        + "Herman vraagt: zou het door een gebrek aan motivatie komen? <br/>"
        + "Rita zegt: ik denk eigenlijk dat het aan de matige inzet van de  ",
        "setnr_115/cond_e/list_c:Marian was weer eens haar sleutelbos kwijt, en komt opgelucht aangelopen. <br/>"
        + "Fred zegt: ze lagen zeker gewoon in de keuken?<br/>"
        + "Marian zegt: ik heb ze eigenlijk teruggevonden op het ",
        "setnr_116/cond_e/list_c:Marlies is pas verhuisd; Isabeau en Lisa komen zaterdag op bezoek in haar nieuwe huisje.<br/>"
        + "Isabeau vraagt: zullen we iets moois uitzoeken voor in de woonkamer?<br/>"
        + "Lisa zegt: het lijkt me eigenlijk leuk om een ",
        "setnr_117/cond_e/list_c:Marloes en Charlotte gaan bij Kirsten op kraamvisite. <br/>"
        + "Marloes vraagt: zullen we leuke kleertjes voor de baby meenemen?<br/>"
        + "Charlotte zegt: het lijkt me eigenlijk leuk om een ",
        "setnr_118/cond_e/list_c:Anneloes en Mees gaan binnenkort naar een bruiloft, en denken na over een cadeau.<br/>"
        + "Annelose vraagt: zullen we voor een keukenartikel gaan dat ze op de verlanglijst hebben gezet?<br/>"
        + "Mees zegt: het lijkt me eigenlijk leuk om een ",
        "setnr_119/cond_e/list_c:Mante en Clara organiseren de Sinterklaasviering op het kantoor.<br/>"
        + "Mante vraagt: zullen we de kinderen iets lekkers meegeven?<br/>"
        + "Clara zegt: het lijkt me eigenlijk leuk om voor iedereen een ",
        "setnr_120/cond_e/list_c:Lauran en Annie wegen hun koffers voordat ze op vakantie gaan.<br/>"
        + "Lauran zegt: er kan nog een kilo bij, dus je kunt nog wat meer kleding meenemen? <br/>"
        + "Annie zegt: ik wil dan eigenlijk nog een extra ",
        "setnr_121/cond_c/list_c:Jens en Marly wegen hun koffer voordat ze naar het vliegveld vertrekken.<br/>"
        + "Jens zegt: net te zwaar.. kunnen we er niet wat toiletspullen uithalen?<br/>"
        + "Marly zegt: we hebben echt veel te veel ",
        "setnr_122/cond_c/list_c:Malou en Pieter gaan verhuizen, en stapelen wat dozen op elkaar.<br/>"
        + "Malou zegt: pfoe deze doos is zwaar, zitten er studiespullen in?<br/>"
        + "Pieter zegt: volgens mij zitten er best een aantal ",
        "setnr_123/cond_c/list_c:Saar en Hugo willen binnenkort een dagje naar een dierentuin.<br/>"
        + "Saar vraagt: zullen we half september gaan? dan is het hoogseizoen vast voorbij!<br/>"
        + "Hugo zegt: ik hoorde van Hella dat het dan weer wat ",
        "setnr_124/cond_c/list_c:Benjamin en Olivia zijn de zolder van hun oma aan het opruimen, en vinden daar een afgesloten kistje.<br/>"
        + "Benjamin vraagt: zou oma hier haar sieraden in bewaren?<br/>"
        + "Olivia zegt: ik denk dat er misschien een paar zilveren ",
        "setnr_125/cond_c/list_c:Leo en Nadia beheren een hotelketen, en bekijken de opties voor nieuwe hotels in het buitenland.<br/>"
        + "Leo vraagt: wat dacht je van een Zuid-Europees land?<br/>"
        + "Nadia zegt: het lijkt me best goed om uit te breiden naar ",
        "setnr_126/cond_c/list_c:Pieter en Vera zijn op vakantie, en gaan te voet van de camping naar het dorp.<br/>"
        + "Pieter zegt: zou je niet wat steviger schoeisel aantrekken?<br/>"
        + "Vera zegt: ik was net van plan mijn ",
        "setnr_127/cond_c/list_c:Wouter gaat naar een kerstborrel van het werk.<br/>"
        + "Zijn vriendin vraagt: je trekt toch wel iets netjes aan?<br/>"
        + "Wouter zegt: ik was net van plan een ",
        "setnr_128/cond_c/list_c:Bart en Karlijn gaan een bergwandeling maken.<br/>"
        + "Bart zegt: moet je niet wat sportievers aantrekken?<br/>"
        + "Karlijn zegt: ik was net van plan een korte ",
        "setnr_129/cond_c/list_c:Familie de Wit staat op een camping vlakbij een middeleeuwse vesting.<br/>"
        + "Diane zegt: de kinderen willen vast mee op een uitstapje vanmiddag?<br/>"
        + "Andy zegt: ik denk dat ze heel graag naar het ",
        "setnr_130/cond_c/list_c:Peter en Bea zijn aan het ontbijten.<br/>"
        + "Bea vraagt: wil je ook een stukje fruit toe?<br/>"
        + "Peter zegt: ik lust best nog wel een ",
        "setnr_131/cond_c/list_c:Marian en Tijn maken lunchpakketjes voor een uitstapje.<br/>"
        + "Marian vraagt: zal ik je brood hartig beleggen?<br/>"
        + "Tijn zegt: ik wil graag twee boterhammen met ",
        "setnr_132/cond_c/list_c:Frans en Mirjam bereiden een kaasplankje.<br/>"
        + "Frans vraagt: wil jij er ook een lekker borreltje bij?<br/>"
        + "Mirjam zegt: doe mij zometeen maar een glaasje ",
        "setnr_133/cond_c/list_c:Arnold en Helena zijn net klaar met dineren, en zetten nog wat koffie.<br/>"
        + "Arnold vraagt: zullen we ook een lekker likeurtje inschenken?<br/>"
        + "Helena zegt: doe mij dadelijk maar een glaasje ",
        "setnr_134/cond_c/list_c:Rick en Gerard zitten op te scheppen over hun wagenpark.<br/>"
        + "Rick vraagt: jij houdt toch het meest van sportwagens?<br/>"
        + "Gerard zegt: ik rijd al jaren het liefst in mijn ",
        "setnr_135/cond_c/list_c:Joris en Noor willen een lang weekend weg naar BelgiÎ.<br/>"
        + "Joris vraagt: zullen we een huisje aan het strand zoeken?<br/>"
        + "Noor zegt: het lijkt me best leuk om naar de ",
        "setnr_136/cond_c/list_c:Cornelis en Elisa zijn aan het overleggen over het avondeten.<br/>"
        + "Cornelis vraagt: zullen we een ovenschotel maken?<br/>"
        + "Elisa zegt: ik heb best veel zin in ",
        "setnr_137/cond_c/list_c:Sandra en Jeroen zijn aan het barbecueÎn.<br/>"
        + "Sandra vraagt: zal ik er voor jou nog wat vlees op leggen?<br/>"
        + "Jeroen zegt: doe mij zo nog maar een ",
        "setnr_138/cond_c/list_c:Vanmiddag komt de familie op bezoek bij Adriana en Martin.<br/>"
        + "Martin vraagt: ga jij nog even langs de bakker voor wat lekkers?<br/>"
        + "Adriana zegt: ik was net van plan om een paar ",
        "setnr_139/cond_c/list_c:Marks trainers bespreken zijn matige prestatie bij een belangrijke hardloopwedstrijd.<br/>"
        + "Emile zegt: misschien moet hij het wat rustiger aan doen.<br/>"
        + "Pierre zegt: ik denk echt dat we het aantal trainingen moeten ",
        "setnr_140/cond_c/list_c:Iris en Marco zijn met Pasen bij hun oma op bezoek; Iris voelt zich niet zo lekker.<br/>"
        + "Marco vraagt: je bent zeker misselijk van al die zoete troep?<br/>"
        + "Iris zegt: ik heb daarstraks veel te veel ",
        "setnr_141/cond_c/list_c:Saskia en Marcel vieren Sinterklaasavond met de familie; Marcel voelt zich niet zo lekker.<br/>"
        + "Saskia vraagt: je hebt je zeker misselijk gegeten aan alle zoetigheid?<br/>"
        + "Marcel zegt: ik heb daarstraks veel te veel ",
        "setnr_142/cond_c/list_c:Paul en Monique vieren Oudjaarsavond; Paul is een beetje misselijk.<br/>"
        + "Monique vraagt: je hebt je zeker te vol gegeten?<br/>"
        + "Paul zegt: ik heb veel te veel ",
        "setnr_143/cond_c/list_c:Karin en Arie zijn net terug van wintersport in Zwitserland.<br/>"
        + "Een vriend van hen vraagt: hebben jullie genoten van de traditionele gerechten daar?<br/>"
        + "Karin zegt: we hebben toen bijna elke dag ",
        "setnr_144/cond_c/list_c:Iris en Chantal gaan na het stappen naar de cafetaria; Chantal is een flexitariÎr.<br/>"
        + "Iris vraagt: jij wil zeker een vega snack?<br/>"
        + "Chantal zegt: doe mij zometeen maar een ",
        "setnr_145/cond_c/list_c:Michael en Bianca gaan gezellig ergens lunchen; Bianca eet de laatste tijd niet meer zo veel vlees.<br/>"
        + "Michael vraagt: bestel jij de vegetarische salade?<br/>"
        + "Bianca zegt: ik neem denk ik de salade met ",
        "setnr_146/cond_c/list_c:Patricia en RenÈ gaan wat drinken bij een nieuw koffietentje; RenÈ is lactose-intolerant. <br/>"
        + "Patricia zegt: Ooh RenÈ je kunt hier lekkere koffie met sojamelk bestellen!<br/>"
        + "RenÈ zegt: ik heb echt enorme zin in ",
        "setnr_147/cond_c/list_c:Jacqueline en Anouk zijn op zoek naar een outfit voor een feestje.<br/>"
        + "Jacqueline zegt: jij hebt zo'n mooi figuur, dat je kun je gerust laten zien!<br/>"
        + "Anouk zegt: Ik draag op feestjes graag kleding die in de taille ",
        "setnr_148/cond_c/list_c:Ellen en DaniÎl hebben het over de familiereunie van vorige week.<br/>"
        + "Ellen zegt: tante Marie is flink aangekomen hË?<br/>"
        + "DaniÎl zegt: Ik dacht echt dat ze vorig jaar een stuk ",
        "setnr_149/cond_c/list_c:Wendy en Edwin gaan op bezoek bij hun opa die al een tijd in het ziekenhuis ligt.<br/>"
        + "Wendy zegt: de vorige keer leek hij al wel wat opgeknapt te zijn hË?<br/>"
        + "Edwin zegt: ik herinner me nog dat hij er toen best wel ",
        "setnr_150/cond_c/list_c:Francisca en Stefan blikken terug op het vorige familieweekend.<br/>"
        + "Francisca zegt: tante Gerda was weer het zonnetje in huis hË?<br/>"
        + "Stefan zegt: ik had echt het idee dat ze ontzettend ",
        "setnr_151/cond_c/list_c:Maartje en Michel hebben het over hun klasgenootjes op de basisschool. <br/>"
        + "Maartje zegt: Sjors deed nooit een vlieg kwaad hË?<br/>"
        + "Michel zegt: ik herinner me nog dat hij in de klas heel ",
        "setnr_152/cond_c/list_c:Roy en Simone hebben het over hun klasgenootjes van de middelbare school.<br/>"
        + "Roy zegt: Mats had altijd een grote mond hË?<br/>"
        + "Simone zegt: ik herinner me nog dat hij in de klas heel ",
        "setnr_153/cond_c/list_c:Sylvia en Roelof blikken terug op de schooltijd van hun kinderen. <br/>"
        + "Sylvia zegt: dat handschrift van Remko was niet te lezen hË?<br/>"
        + "Roelof zegt: ik herinner me nog dat zijn schrift altijd erg ",
        "setnr_154/cond_c/list_c:Geert en Marijke blikken terug op hun studententijd.<br/>"
        + "Marijke zegt: van studeren kwam bij jou niet zo veel terecht hË..<br/>"
        + "Geert zegt: ik herinner me nog dat ik de eerste twee jaar heel ",
        "setnr_155/cond_c/list_c:Gerda en Lucas hebben het over hun oude vriendengroep. <br/>"
        + "Gerda zegt: Joeri was altijd de gangmaker hË?<br/>"
        + "Lucas zegt: ik herinner me nog dat hij qua karakter erg ",
        "setnr_156/cond_c/list_c:Michiel en Barbara hebben het over hun neefjes en nichtjes met wie ze vroeger veel optrokken.<br/>"
        + "Michiel zegt: Veerle was altijd het muurbloempje hË?<br/>"
        + "Barbara zegt: ik herinner me nog dat zij altijd erg ",
        "setnr_157/cond_c/list_c:Evert en Louise zitten in de lerarenkamer, en hebben het over een oud-collega. <br/>"
        + "Evert zegt: als leerlingen het niet snapten, legde zij het gerust nog tien keer uit!<br/>"
        + "Louise zegt: ik herinner me nog dat ze tijdens het lesgeven erg ",
        "setnr_158/cond_c/list_c:Maurice en Carla zitten in de kantine, en hebben het over een oud-collega.<br/>"
        + "Maurice zegt: wat was zijn bureau altijd een zootje hË!<br/>"
        + "Carla zegt: ik herinner me nog dat de manier waarop hij werkte erg ",
        "setnr_159/cond_c/list_c:Erwin en Daphne zijn bezig met het voorbereiden van een lunch in hun tuin. <br/>"
        + "Erwin vraagt: zullen we de tafel een beetje chic dekken? <br/>"
        + "Daphne zegt: ik dacht er net aan om de jus d'orange in ",
        "setnr_160/cond_c/list_c:De zoon van Nathalie en Marcus is woensdag jarig.<br/>"
        + "Marcus zegt: tegenwoordig trakteren kinderen altijd iets gezonds toch?<br/>"
        + "Nathalie zegt: hij wilde geloof ik prikkers met ",
        "setnr_161/cond_c/list_c:Sander komt zijn beste vriend Luuc tegen.<br/>"
        + "Luuc zegt: HË Sander, je hebt zo college toch?<br/>"
        + "Sander zegt: ik ben net op weg naar de ",
        "setnr_162/cond_c/list_c:Melanie zet wat water op voor een kopje thee.<br/>"
        + "Melanie vraagt: wil jij ook fruitige thee?<br/>"
        + "Sabine zegt: doe mij dadelijk maar zo'n zakje ",
        "setnr_163/cond_c/list_c:Pascal en Janneke hebben het over hun toekomstdromen.<br/>"
        + "Pascal zegt: jij wilt zeker naar het platteland verhuizen?<br/>"
        + "Janneke zegt: het lijkt me erg fijn om later in een ",
        "setnr_164/cond_c/list_c:Arjan en Kirsten hebben het over hun droomhuizen.<br/>"
        + "Arjan zegt: jij droomt zeker van een mooie woning in de stad?<br/>"
        + "Kirsten zegt: ik wil later graag in een ",
        "setnr_165/cond_c/list_c:Pim is niet op het werk, want hij moest naar het ziekenhuis.<br/>"
        + "Gert zegt: het zal wel niet voor iets ernstigs zijn toch?<br/>"
        + "Zijn collega zegt: hij zei gisteren dat hij er voor een ",
        "setnr_166/cond_c/list_c:Eveline en Roel hebben het over de toekomstplannen van hun muzikale zoon.<br/>"
        + "Evelien zegt: misschien dat hij later verder wil in de muziek hË..<br/>"
        + "Roel zegt: hij wil geloof ik zijn brood verdienen als ",
        "setnr_167/cond_c/list_c:Nancy en Sam gaan samen studeren in de bibliotheek.<br/>"
        + "Nancy vraagt: zullen we in die ene studiezaal achterin gaan zitten?<br/>"
        + "Sam zegt: dat vind ik meestal een best wel ",
        "setnr_168/cond_c/list_c:Susanne en Brian hebben in Afrika een safaritocht gemaakt.<br/>"
        + "Een vriend van hen vraagt: lopen daar nou echt allerlei wilde dieren rond?<br/>"
        + "Brian zegt: we hebben daar vooral heel veel ",
        "setnr_169/cond_c/list_c:Marleen en Alex lopen in het bos en zien allerlei sporen op de grond.<br/>"
        + "Marleen vraagt: zouden dit sporen van wilde dieren zijn?<br/>"
        + "Alex zegt: dat zijn waarschijnlijk afdrukken van een ",
        "setnr_170/cond_c/list_c:Rutger en Renske maken een wandeling.<br/>"
        + "Rutger zegt: volgens mij zie ik daar een roofvogel vliegen!<br/>"
        + "Renske zegt: dat is waarschijnlijk een grote ",
        "setnr_171/cond_c/list_c:Pauline en Nico kamperen in het bos en schrikken van een geluid.<br/>"
        + "Pauline vraagt: zou dat van een wild dier zijn?<br/>"
        + "Nico zegt: ik dacht echt dat ik een ",
        "setnr_172/cond_c/list_c:Harmen en Margriet maken een wandeling door de bergen, als Margriet opeens een bijzondere steen ziet liggen.<br/>"
        + "Harmen zegt: het lijkt wel een edelsteen!<br/>"
        + "Margriet zegt: het is zo te zien een stukje ",
        "setnr_173/cond_c/list_c:Op 23 december gaan Ralph en Silvia shoppen om het huis aan te kleden.<br/>"
        + "Ralph vraagt: nemen we nog wat dingen mee voor in de boom?<br/>"
        + "Silvia zegt: ik wil graag een aantal ",
        "setnr_174/cond_c/list_c:Kornelis en Loes lopen door een kunstgalerie.<br/>"
        + "Kornelis zegt: dit schilderij past helemaal bij jouw smaak Loes!<br/>"
        + "Loes zegt: ik vind het echt heel erg ",
        "setnr_175/cond_c/list_c:Joep en Dominique wachten op het station op hun trein, en gaan nog even langs de kiosk.<br/>"
        + "Dominique vraagt: heb jij ook zin in een lekkere vette hap?<br/>"
        + "Joep zegt: ik wil heel graag een ",
        "setnr_176/cond_c/list_c:Stefanie en Marvin maken een lange autorit; Stefanie verveelt zich en begint achter de stoel te rommelen.<br/>"
        + "Marvin vraagt: pak je iets om te lezen?<br/>"
        + "Stefanie zegt: ik ben tussen de spullen aan het zoeken naar mijn  ",
        "setnr_177/cond_c/list_c:Veronique is naar een lezing van een beroemde hoogleraar geweest. <br/>"
        + "Haar collega zegt: hij kan zijn verhaal altijd erg goed overbrengen hË?<br/>"
        + "Veronique zegt: ik vond zijn lezing echt ontzettend ",
        "setnr_178/cond_c/list_c:Vivian is naar een feestje geweest en komt laat thuis.<br/>"
        + "Haar huisgenoot zegt: je hebt je vast goed vermaakt?<br/>"
        + "Vivian zegt: Het was echt heel erg ",
        "setnr_179/cond_c/list_c:Op een warme zomerdag gaan Andrea en Leo een ijsje halen bij de ijssalon.<br/>"
        + "Andrea zegt: ga jij ook voor een frisse sorbetsmaak?<br/>"
        + "Leo zegt: Ik wil graag twee bolletjes ",
        "setnr_180/cond_c/list_c:Jolijne en Wernard bereiden de lunch voor hun zoontjes kinderfeestje.<br/>"
        + "Wernard vraagt: zullen we nog wat broodjes zoet beleggen?<br/>"
        + "Jolijne zegt: we kunnen er zo nog wel een paar met"};

    public WizardData getWizardData() {
        WizardData wizardData = new WizardData();
        wizardData.setAppName("Parcours01");
        wizardData.setShowMenuBar(true);
        wizardData.setTextFontSize(17);
        wizardData.setObfuscateScreenNames(false);
        WizardTextScreen wizardTextScreen = new WizardTextScreen("Informatie", informationScreenText,
                "volgende [ spatiebalk ]"
        );
        wizardTextScreen.setMenuLabel("Terug");
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
//        wizardData.setAgeField(true);
        wizardEditUserScreen.setCustomFields(new String[]{
            "workerId:Proefpersoon ID:.*:.",
            "firstName:Voornaam:.'{'3,'}':Voer minimaal drie letters.",
            "lastName:Achternaam:.'{'3,'}':Voer minimaal drie letters.",
            "age:Leeftijd:[0-9]+:Voer een getal.",
            "gender:Geslacht:|man|vrouw|anders:."
        });

        wizardData.addScreen(agreementScreen);
        wizardData.addScreen(wizardTextScreen);
        wizardData.addScreen(wizardEditUserScreen);

        final WizardRandomStimulusScreen list1234Screen = new WizardRandomStimulusScreen("Zinnen afmaken", false, stimuliString,
                new String[]{"list_a",
                    "list_b",
                    "list_c"}, 1000, true, null, 0, 0, null, null, null, null, "volgende");
        list1234Screen.setStimulusFreeText(true, ".{2,}",
                "Vul één of enkele woorden in die volgens u het beste aan het eind van de zin passen.");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelLeft("");
        list1234Screen.getWizardScreenData().setStimulusResponseLabelRight("");
        list1234Screen.getWizardScreenData().setAllowHotkeyButtons(false);
        wizardData.addScreen(list1234Screen);

        // @todo: remove the restart button
        // 
        WizardCompletionScreen completionScreen = new WizardCompletionScreen(completionScreenText1, false, true,
                //                "Wil nog iemand op dit apparaat deelnemen aan dit onderzoek, klik dan op de onderstaande knop.",
                "<br/>"
                + "Het onderstaande nummer is het bewijs dat u het experiment heeft voltooid, en is vereist voor het in orde maken van uw vergoeding. Gelieve het nummer te kopieëren en per email terug te sturen naar de onderzoeker:  <br/>"
                + "marlou.rasenberg@mpi.nl",
                "Opnieuw beginnen",
                "Einde van het experiment",
                "Geen verbinding met de server. Controleer alstublieft uw internetverbinding en probeer het opnieuw.",
                "Probeer opnieuw");
        wizardData.addScreen(completionScreen);
        completionScreen.setScreenTag("completion");
        wizardTextScreen.setNextWizardScreen(wizardEditUserScreen);
        agreementScreen.setNextWizardScreen(wizardTextScreen);
        wizardEditUserScreen.setNextWizardScreen(list1234Screen);
        list1234Screen.setNextWizardScreen(completionScreen);

        wizardEditUserScreen.setBackWizardScreen(wizardTextScreen);
        wizardTextScreen.setBackWizardScreen(agreementScreen);
//        list1234Screen.setBackWizardScreen(wizardEditUserScreen);
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
