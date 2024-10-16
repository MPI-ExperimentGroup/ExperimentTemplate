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

import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.CancelableStimulusListener;
import nl.mpi.tg.eg.experiment.client.listener.FrameTimeTrigger;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleStimulusListener;
import nl.mpi.tg.eg.experiment.client.model.ExtendedKeyCodes;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since 29 February 2024 16:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardStimulusPresenter extends AbstractStimulusPresenter implements Presenter {

    private final String titleString = "WizardStimulusPresenter";
    private final String blocksData;
    private final String selectedBlockId;
    private final String staticFilesPath;

    public WizardStimulusPresenter(RootLayoutPanel widgetTag, DataSubmissionService submissionService,
            UserResults userResults, final LocalStorage localStorage, final TimerService timerService,
            final String blocksData, final String selectedBlockId, final String staticFilesPath) {
        super(widgetTag, submissionService, userResults, localStorage, timerService);
        this.blocksData = blocksData;
        this.selectedBlockId = selectedBlockId;
        this.staticFilesPath = staticFilesPath;
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
        // addText("selectedBlockId");
        // addText(selectedBlockId);
        addPadding();
        try {
            Document messageDom = XMLParser.parse(blocksData);
            iterateBlocks(messageDom.getFirstChild());
        } catch (DOMException e) {
            addText("Error parsing blocksData");
            addText(e.getMessage());
        }
    }

    private TimedStimulusListener getTimedStimulusListener(final Node currentNode) {
        return new TimedStimulusListener() {
            @Override
            public void postLoadTimerFired() {
                NodeList childNodes = currentNode.getChildNodes();
                for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
                    iterateBlocks(childNodes.item(nodeCount));
                }
            }
        };
    }

    private CancelableStimulusListener getNamedListener(final Node currentNode, final String nodeName) {
        return new CancelableStimulusListener() {
            @Override
            protected void trigggerCancelableEvent() {
                NodeList childNodes = currentNode.getChildNodes();
                for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
                    if (nodeName.equals(childNodes.item(nodeCount).getNodeName())) {
                        iterateBlocks(childNodes.item(nodeCount));
                    }
                }
            }
        };
    }

    private SingleStimulusListener getNamedListener(final Node currentNode, final String nodeName, final Stimulus currentStimulus) {
        return new SingleStimulusListener() {
            @Override
            public void postLoadTimerFired(Stimulus currentStimulus) {
                NodeList childNodes = currentNode.getChildNodes();
                for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
                    if (nodeName.equals(childNodes.item(nodeCount).getNodeName())) {
                        iterateBlocks(childNodes.item(nodeCount));
                    }
                }
            }
        };
    }

    private FrameTimeTrigger[] getFrameTimeTriggers(final Node currentNode, final Stimulus currentStimulus) {
        List<FrameTimeTrigger> frameTimeTriggerList = new ArrayList<>();
        NodeList childNodes = currentNode.getChildNodes();
        for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
            if ("addFrameTimeTrigger".equals(childNodes.item(nodeCount).getNodeName())) {
                int triggerMs = 0;
                int threshold = 0;
                if (((Element) childNodes.item(nodeCount)).hasAttribute("triggerMs")) {
                    triggerMs = Integer.parseInt(((Element) childNodes.item(nodeCount)).getAttribute("triggerMs"));
                }
                if (((Element) childNodes.item(nodeCount)).hasAttribute("threshold")) {
                    threshold = Integer.parseInt(((Element) childNodes.item(nodeCount)).getAttribute("threshold"));
                }
                frameTimeTriggerList.add(new FrameTimeTrigger(currentStimulus, getNamedListener(childNodes.item(nodeCount), "onLate"), getNamedListener(childNodes.item(nodeCount), "onTime", currentStimulus), triggerMs, threshold) {
                });
            }
        }
        return frameTimeTriggerList.toArray(new FrameTimeTrigger[]{});
    }

    private PresenterEventListener getPresenterEventListener(final Node currentNode, final String featureText, final String styleName, final String hotKey) {
        return new PresenterEventListener() {
            @Override
            public String getLabel() {
                return featureText;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                NodeList childNodes = currentNode.getChildNodes();
                for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
                    iterateBlocks(childNodes.item(nodeCount));
                }
            }

            @Override
            public int getHotKey() {
                final Integer hotKeyCode = new ExtendedKeyCodes().getExtendedKeyCodesMap().get("KEY_" + hotKey);
                return (hotKeyCode != null) ? hotKeyCode : -1;
            }
        };
    }

    private PresenterEventListener getPresenterTargetListener(final Node currentNode, final String featureText, final String styleName, final String hotKey, final String target) {
        return new PresenterEventListener() {
            @Override
            public String getLabel() {
                return featureText;
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                // TODO: replace all with the target presenter XML
                // target
                clearPage();
                addText("TODO: handle presenter: " + target);
            }

            @Override
            public int getHotKey() {
                final Integer hotKeyCode = new ExtendedKeyCodes().getExtendedKeyCodesMap().get("KEY_" + hotKey);
                return (hotKeyCode != null) ? hotKeyCode : -1;
            }
        };
    }

    private void iterateBlocks(final Node currentNode) throws DOMException {
        if (currentNode instanceof Element) {
            XmlId xmlId = null;
            Stimulus currentStimulus = null;
            String featureText = "";
            if (((Element) currentNode).hasAttribute("featureText")) {
                featureText = ((Element) currentNode).getAttribute("featureText");
            }
            String styleName = "";
            if (((Element) currentNode).hasAttribute("styleName")) {
                styleName = ((Element) currentNode).getAttribute("styleName");
            }
            String buttonGroup = "";
            if (((Element) currentNode).hasAttribute("buttonGroup")) {
                buttonGroup = ((Element) currentNode).getAttribute("buttonGroup");
            }
            String hotKey = "";
            if (((Element) currentNode).hasAttribute("hotKey")) {
                hotKey = ((Element) currentNode).getAttribute("hotKey");
            }
            String target = "";
            if (((Element) currentNode).hasAttribute("target")) {
                target = ((Element) currentNode).getAttribute("target");
            }
            String regionId = "";
            if (((Element) currentNode).hasAttribute("regionId")) {
                regionId = ((Element) currentNode).getAttribute("regionId");
            }
            int msToNext = 0;
            if (((Element) currentNode).hasAttribute("msToNext")) {
                msToNext = Integer.parseInt(((Element) currentNode).getAttribute("msToNext"));
            }
            int dataChannel = 0;
            if (((Element) currentNode).hasAttribute("dataChannel")) {
                dataChannel = Integer.parseInt(((Element) currentNode).getAttribute("dataChannel"));
            }
            int minimumMs = 0;
            if (((Element) currentNode).hasAttribute("minimumMs")) {
                minimumMs = Integer.parseInt(((Element) currentNode).getAttribute("minimumMs"));
            }
            int maximumMs = 0;
            if (((Element) currentNode).hasAttribute("maximumMs")) {
                maximumMs = Integer.parseInt(((Element) currentNode).getAttribute("maximumMs"));
            }
            String listenerId = "";
            if (((Element) currentNode).hasAttribute("listenerId")) {
                listenerId = ((Element) currentNode).getAttribute("listenerId");
            }
            String eventTag = "";
            if (((Element) currentNode).hasAttribute("eventTag")) {
                eventTag = ((Element) currentNode).getAttribute("eventTag");
            }
            String src = "";
            if (((Element) currentNode).hasAttribute("src")) {
                src = staticFilesPath + ((Element) currentNode).getAttribute("src");
            }
            String msLabelFormat = "";
            if (((Element) currentNode).hasAttribute("msLabelFormat")) {
                msLabelFormat = ((Element) currentNode).getAttribute("msLabelFormat");
            }
            String evaluateTokens = "";
            if (((Element) currentNode).hasAttribute("evaluateTokens")) {
                evaluateTokens = ((Element) currentNode).getAttribute("evaluateTokens");
            }
            switch (currentNode.getNodeName()) {
                case "plainText":
                    addText(featureText);
                    break;
                case "htmlText":
                    htmlText(featureText, styleName, xmlId);
                    break;
                case "htmlTokenText":
                    htmlTokenText(currentStimulus, featureText, styleName, xmlId);
                    break;
                case "targetButton":
                    targetButton(getPresenterTargetListener(currentNode, featureText, styleName, hotKey, target), buttonGroup);
                    break;
                case "actionButton":
                    actionButton(getPresenterEventListener(currentNode, featureText, styleName, hotKey), buttonGroup);
                    break;
                case "regionStyle":
                    regionStyle(currentStimulus, regionId, styleName);
                    break;
                case "regionCodeStyle":
                    regionCodeStyle(currentStimulus, regionId, styleName);
                    break;
                case "regionAppend":
                    regionAppend(currentStimulus, regionId, styleName, getTimedStimulusListener(currentNode));
                    break;
                case "regionReplace":
                    regionReplace(currentStimulus, regionId, styleName, getTimedStimulusListener(currentNode));
                    break;
                case "regionClear":
                    regionClear(currentStimulus, regionId);
                    break;
                case "startTimer":
                    startTimer(msToNext, listenerId, getTimedStimulusListener(currentNode));
                    break;
                case "image":
                    image(src, msToNext, getNamedListener(currentNode, "mediaLoaded"), getNamedListener(currentNode, "mediaLoadFailed"));
                    break;
                case "clearTimer":
                    clearTimer(listenerId);
                    break;
                case "compareTimer":
                    compareTimer(msToNext, listenerId, getNamedListener(currentNode, "aboveThreshold"), getNamedListener(currentNode, "withinThreshold"));
                    break;
                case "logTimerValue":
                    logTimerValue(listenerId, eventTag, dataChannel);
                    break;
                case "timerLabel":
                    timerLabel(styleName, msToNext, listenerId, msLabelFormat);
                    break;
                case "cancelPauseTimers":
                    cancelPauseTimers();
                    break;
                case "addTimerTrigger":
                    addTimerTrigger(currentStimulus, listenerId, minimumMs, maximumMs, evaluateTokens, getNamedListener(currentNode, "onError"), getNamedListener(currentNode, "onTimer"));
                    break;
                case "startFrameRateTimer":
                    startFrameRateTimer(getFrameTimeTriggers(currentNode, currentStimulus));
                    break;
//                case "zeroStimulusStopwatch":
//                    zeroStimulusStopwatch();
//                    break;
//                case "stopStimulusStopwatch":
//                    stopStimulusStopwatch();
//                    break;
                case "experiment":
                case "presenter":
                    NodeList childNodes = currentNode.getChildNodes();
                    for (int nodeCount = 0; nodeCount < childNodes.getLength(); nodeCount++) {
                        iterateBlocks(childNodes.item(nodeCount));
                    }
                    break;
                default:
                    addText(currentNode.getNodeName());
                    if (((Element) currentNode).hasAttribute("block_id")) {
                        addText(((Element) currentNode).getAttribute("block_id"));
                    }
            }
        }
    }

    @Override
    protected String[] getStopwatchValues() {
        return new String[]{};
    }
}
