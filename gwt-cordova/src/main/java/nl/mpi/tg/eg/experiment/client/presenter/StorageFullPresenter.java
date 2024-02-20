/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.Version;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.UserLabelData;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Dec 8, 2016 2:36:10 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class StorageFullPresenter extends LocalStoragePresenter implements Presenter {

    private final Version version = GWT.create(Version.class);
    private final String errorMessage;

    public StorageFullPresenter(RootLayoutPanel widgetTag, LocalStorage localStorage, final DataSubmissionService submissionService, String errorMessage) {
        super(widgetTag, localStorage, submissionService);
        this.errorMessage = errorMessage;
    }

    @Override
    protected String getTitle() {
        return "StorageFullPresenter";
    }

    @Override
    protected String getSelfTag() {
        return "StorageFullPresenter";
    }

    @Override
    protected void setContent(final AppEventListener appEventListener) {
        ((ComplexView) simpleView).addHtmlText(messages.errorScreenText(errorMessage), "highlightedText");
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addText("Framework For Interactive Experiments\n"
                + "https://hdl.handle.net/21.11116/0000-000C-2B81-2" + "\n"
                + "FRINEX Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "\n"
                + "Project Version: "
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Commit Date: " + version.lastCommitDate());
        ((ComplexView) simpleView).addPadding();
        ((ComplexView) simpleView).addPadding();
        uploadUsersDataMenu();
        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addPadding();
//        ((ComplexView) simpleView).addPadding();
//        eraseLocalStorageButton(null, "eraseLocalStorageButton");
//        ((ComplexView) simpleView).addPadding();
//        localStorageData();
    }

    protected void uploadUsersDataMenu() {
        List<UserLabelData> userList = localStorage.getUserIdList(metadataFieldProvider.getMetadataFieldArray()[0]);
        for (final UserLabelData labelData : userList) {
            if (!localStorage.getDataAgreementValue(labelData.getUserId(), metadataFieldProvider)) {
                final InsertPanel.ForIsWidget userRegion = ((TimedStimulusView) simpleView).startRegion(labelData.getUserId().toString(), null);
                ((ComplexView) simpleView).addPadding();
                ((ComplexView) simpleView).addText("User data agreement is not complete so cannot submit data: " + labelData.getUserName());
                ((TimedStimulusView) simpleView).endRegion(userRegion);
            } else {
                localStorage.clearStorageException();
                submissionService.submitAllData(labelData.getUserId(), new DataSubmissionListener() {

                    @Override
                    public void scoreSubmissionFailed(DataSubmissionException exception) {
                        final InsertPanel.ForIsWidget userRegion = ((TimedStimulusView) simpleView).startRegion(labelData.getUserId().toString(), null);
                        ((ComplexView) simpleView).addPadding();
                        ((ComplexView) simpleView).addText("Data submision failed: " + labelData.getUserName());
                        ((TimedStimulusView) simpleView).endRegion(userRegion);
                    }

                    @Override
                    public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                        final InsertPanel.ForIsWidget userRegion = ((TimedStimulusView) simpleView).startRegion(labelData.getUserId().toString(), null);
                        ((ComplexView) simpleView).addPadding();
                        ((ComplexView) simpleView).addText("Data submission complete: " + labelData.getUserName());
                        ((TimedStimulusView) simpleView).addOptionButton(new PresenterEventListener() {

                            @Override
                            public String getLabel() {
                                return "Delete local data: " + labelData.getUserName();
                            }

                            @Override
                            public int getHotKey() {
                                return -1;
                            }

                            @Override
                            public String getStyleName() {
                                return null;
                            }

                            @Override
                            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                                localStorage.clearUserData(labelData.getUserId());
                                final InsertPanel.ForIsWidget userRegion = ((TimedStimulusView) simpleView).startRegion(labelData.getUserId().toString(), null);
                                ((ComplexView) simpleView).addPadding();
                                ((TimedStimulusView) simpleView).clearRegion(labelData.getUserId().toString());
                                ((ComplexView) simpleView).addText("Local data deleted: " + labelData.getUserName());
                                ((TimedStimulusView) simpleView).endRegion(userRegion);
                            }
                        });
                        ((TimedStimulusView) simpleView).endRegion(userRegion);
                    }
                });
            }
        }
    }
}
