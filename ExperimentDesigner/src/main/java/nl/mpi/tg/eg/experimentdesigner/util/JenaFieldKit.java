/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
import nl.mpi.tg.eg.experimentdesigner.model.PresenterScreen;
import nl.mpi.tg.eg.experimentdesigner.model.StimuliSubAction;

/**
 * @since Dec 22, 2015 11:07:54 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class JenaFieldKit {

    private final WizardController wizardController = new WizardController();

    public Experiment getJenaExperiment() {
        Experiment experiment = wizardController.getExperiment("vanuatufieldkit", "Vanuatu FieldKit", true);
        wizardController.addMetadata(experiment);

        final PresenterScreen autoMenuPresenter = wizardController.addAutoMenu(experiment, 12, false);
        final PresenterScreen welcomePresenter = wizardController.addWelcomeScreen(experiment, autoMenuPresenter, "Welkam", null, 1, "Instruksen (Blong programa)", "Stat - Go long program nao", false);
        final PresenterScreen welcomeMenuPresenter = wizardController.addWelcomeMenu(experiment, welcomePresenter, "Start", null, 2, "Niu rikording", "Gobak long wan olfala rikoding", "Makem wan niufala rikoding", "Gobak long wan rikoding we yu stat hem finis", false);
        final PresenterScreen instructionsPresenter = wizardController.addInstructionsScreen(experiment, welcomePresenter, "Instruksen", welcomeMenuPresenter, 3, "Wetem aplikasen ia yu save makem rikoding blong lanwis blong yu,"
                + " bambai ol pipol blong Vanuatu mo ol pipol blong evri ples long world save harem lanwis blong yu. I gat fulap foto blong difren ples long Malakula wea i stap insaed long aplikasen ia. "
                + "Bai yu showem ol foto ia long wan olfala woman o wan olfala man blong vilej blong yu mo askem long hem se i tokabaot ol foto ia long lanwis blong hem. Yu save transletem wanem i talem, tu.", "Go long program nao", false);
        StimuliSubAction[] featureValuesArray = new StimuliSubAction[]{new StimuliSubAction("80", "Askem long man o woman wea i toktok se i talem nem blong wanem i stap lo foto long lanwis blong hem.", "Finis"),
            new StimuliSubAction("60", "Noaia yu toktok. I talem wanem? Givim wan translesen long Bislama o Inglis o Franis.", "Finis"),
            new StimuliSubAction("80", "Askem long man o woman we i toktok se i gat sam stori blong laef blong hem long saed blong ting ia we i stap long foto ia.", "Finis"),
            new StimuliSubAction("60", "Noaia yu toktok. I talem wanem? Givim wan translesen long Bislama o Inglis o Franis.", "Finis"),
            new StimuliSubAction("80", "Askem long man o woman we i toktok se i save wan kastom stori abaot ting ia we i stap long foto ia.", "Finis"),
            new StimuliSubAction("60", "Noaia yu toktok. I talem wanem? Givim wan translesen long Bislama o Inglis o Franis.", "Finis")
        };

        final PresenterScreen stimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, "Lukluk ol foto", welcomePresenter, new String[]{"Pictures"}, featureValuesArray, true, 1000, true, "Finis olgeta", 8, false);
//        final PresenterScreen vanuatuScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, stimulusScreen, new String[]{"vanuatu"}, featureValuesArray, true, 1000, true, 7, false);
//        final PresenterScreen bowpedStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, vanuatuScreen, new String[]{"bowped"}, featureValuesArray, true, 1000, true, 9, false);
//        final PresenterScreen bodiesStimulusScreen = wizardController.createStimulusScreen(experiment, welcomePresenter, bowpedStimulusScreen, new String[]{"bodies"}, featureValuesArray, true, 1000, true, 10, false);
        final PresenterScreen metadataScreen = wizardController.createMetadataScreen(experiment, welcomePresenter, stimulusScreen, new String[]{
            "I stap rikod nao. Man o woman we i toktok bai i talem nem blong hem.",
            "Bai i talem nem blong lanwis wea it toktok long hem.",
            "Bai i talem nem blong ples wea i stap nao.",
            "Bai i talem nem blong ples wea i bon long hem.",
            "Bai i talem wanem yea i bon."
        }, "Neks", "Finis olgeta", 6, false);
        final PresenterScreen selectUserPresenter = wizardController.addUserSelectMenu(experiment, welcomePresenter, metadataScreen, 5, false);
        final PresenterScreen editUserPresenter = wizardController.addEditUserScreen(experiment, welcomePresenter, "Infomesen blong man/woman we i toktok", "Edit User", metadataScreen, 4, null, null, new String[]{"speakerName:Nem blong man/woman we i toktok:.'{'3,'}':Please enter at least three letters."}, "Savem infomesen", null, null, null, false, "Could not contact the server, please check your internet connection and try again.", false);
        final PresenterScreen debugScreenPresenter = wizardController.addDebugScreen(experiment, autoMenuPresenter, 11, false);
        welcomeMenuPresenter.setNextPresenter(editUserPresenter);
//        instructionsPresenter.setNextPresenter(metadataScreen);
        return experiment;
    }
}
