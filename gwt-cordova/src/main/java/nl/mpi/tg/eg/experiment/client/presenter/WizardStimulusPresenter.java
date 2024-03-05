/*
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;

/**
 * @since 29 February 2024 16:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardStimulusPresenter extends AbstractStimulusPresenter implements Presenter {

    private final String titleString = "WizardStimulusPresenter";
    private final String blocksData;
    private final String selectedBlockId;

    public WizardStimulusPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService, UserResults userResults, final LocalStorage localStorage, final TimerService timerService, final String blocksData, final String selectedBlockId) {
        super(widgetTag, submissionService, userResults, localStorage, timerService);
        this.blocksData = blocksData;
        this.selectedBlockId = selectedBlockId;
    }

    @Override
    protected String getTitle() {
        return titleString;
    }

    @Override
    protected String getSelfTag() {
        return "WizardStimulusPresenter";
    }

    @Override
    protected void setContent(AppEventListener appEventListener) {
        addText("selectedBlockId");
        addText(selectedBlockId);
        addPadding();
        try {
            Document messageDom = XMLParser.parse(blocksData);
            iterateBlocks(messageDom.getFirstChild());
        } catch (DOMException e) {
            addText("Error parsing blocksData");
            addText(e.getMessage());
        }
    }

    private void iterateBlocks(Node currentNode) {
        addText(currentNode.getNodeName());
        addText(((Element) currentNode).getAttribute("block_id"));
        NodeList childNodes = currentNode.getChildNodes();
        for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
            iterateBlocks(childNodes.item(nodeCount));
        }
    }

    @Override
    protected String[] getStopwatchValues() {
        return new String[]{};
    }
}
