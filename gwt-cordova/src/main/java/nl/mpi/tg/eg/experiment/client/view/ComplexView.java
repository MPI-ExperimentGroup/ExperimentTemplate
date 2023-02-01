/*
 * Copyright (C) 2015 Language In Interaction
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
package nl.mpi.tg.eg.experiment.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
//import com.google.gwt.event.dom.client.DragStartEvent;
//import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.listener.ButtonGroupMember;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.listener.TouchInputCapture;
import nl.mpi.tg.eg.experiment.client.listener.ValueChangeListener;
import nl.mpi.tg.eg.experiment.client.model.XmlId;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractStimulusPresenter.OrientationType;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since Jan 8, 2015 5:01:05 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ComplexView extends SimpleView {

    private Label recordingLabel = null;
    private HorizontalPanel horizontalPanel = null;
    private final List<InsertPanel.ForIsWidget> activePanels = new ArrayList<>();
    private final HashMap<String, VerticalPanel> regionPanels = new HashMap<>();
    private final ArrayList<FlexTable> gridPanelList = new ArrayList<>();

    private class ImageEntry {

        final Element imageElement;
        final int percentOfPage;

        public ImageEntry(final Element imageElement, int percentOfPage) {
            this.imageElement = imageElement;
            this.percentOfPage = percentOfPage;
        }

    }
    protected final Messages messages = GWT.create(Messages.class);
    final protected VerticalPanel outerPanel;
    final ArrayList<HandlerRegistration> domHandlerArray = new ArrayList<>();
    private final ArrayList<ImageEntry> scaledImagesList = new ArrayList<>();

    public ComplexView() {
        super();
        outerPanel = new VerticalPanel();
        activePanels.add(outerPanel);
// TODO: draggable stimilus responses
//        outerPanel.getElement().setDraggable(Element.DRAGGABLE_TRUE);
//        outerPanel.addDragStartHandler(new DragStartHandler() {
//
//            @Override
//            public void onDragStart(DragStartEvent event) {
//            }
//        ;
//        });
        setContent(outerPanel);
    }

    private void clearAllRegions() {
        regionPanels.clear();
    }

    public InsertPanel.ForIsWidget startRegion(final String regionId, final String styleName) {
        VerticalPanel regionTemp = regionPanels.get(regionId);
        if (regionTemp == null) {
            regionTemp = new VerticalPanel();
            regionPanels.put(regionId, regionTemp);
            regionTemp.getElement().setId(regionId);
        }
        if (regionTemp.getParent() == null) {
            getActivePanel().add(regionTemp);
        }
        if (styleName != null) {
            // we set or remove the style if it is provided, however if the style is null (no style attribute) we allow the old style to persist.
            regionTemp.setStyleName(styleName);
        }
        activePanels.add(regionTemp);
        return regionTemp;
    }

    public void endRegion(InsertPanel.ForIsWidget isWidget) {
        activePanels.remove(isWidget);
    }

    public void setRegionStyle(final String regionId, final String styleName) {
        VerticalPanel regionTemp = regionPanels.get(regionId);
        if (regionTemp != null) {
            regionTemp.setStyleName(styleName);
        }
    }

    public void clearRegion(final String regionId) {
        VerticalPanel regionTemp = regionPanels.get(regionId);
        if (regionTemp != null) {
            regionTemp.clear();
        }
    }

//    public void setRegionId(final String regionId) {
//        VerticalPanel regionTemp = regionPanels.get(regionId);
//        if (regionTemp != null) {
//            regionTemp.getElement().setId(regionId);
//        }
//    }
    public boolean hasRegion(final String regionId) {
        return regionPanels.containsKey(regionId);
    }

    public FlexTable gridPanel() {
        final int index = gridPanelList.size() - 1;
        return (index >= 0) ? gridPanelList.get(index) : null;
    }

    public InsertPanel.ForIsWidget startCell(String styleName) {
        VerticalPanel cellPanel = new VerticalPanel();
        activePanels.add(cellPanel);
        if (styleName != null && !styleName.isEmpty()) {
            cellPanel.addStyleName(styleName);
        }
        cellPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        if (gridPanel() != null) {
            gridPanel().addCell(gridPanel().getRowCount() - 1);
            gridPanel().setWidget(gridPanel().getRowCount() - 1, gridPanel().getCellCount(gridPanel().getRowCount() - 1) - 1, cellPanel);
        } else {
            horizontalPanel.add(cellPanel);
        }
        return cellPanel;
    }

    public void endCell(InsertPanel.ForIsWidget isWidget) {
        activePanels.remove(isWidget);
    }

    public InsertPanel.ForIsWidget startRow() {
        if (gridPanel() != null) {
            gridPanel().insertRow(gridPanel().getRowCount());
            return null;
        } else {
            return startHorizontalPanel();
        }
    }

    public void endRow(InsertPanel.ForIsWidget isWidget) {
        if (gridPanel() == null) {
            endHorizontalPanel(isWidget);
        }
    }

    public Widget startTable(final String styleName) {
        FlexTable gridPanel = new FlexTable();
        if (styleName != null && !styleName.isEmpty()) {
            gridPanel.addStyleName(styleName);
        }
        getActivePanel().add(gridPanel);
        gridPanelList.add(gridPanel);
        return gridPanel();
    }

    public void endTable() {
        gridPanelList.remove(gridPanelList.size() - 1);
    }

    public InsertPanel.ForIsWidget startHorizontalPanel() {
        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        outerPanel.add(horizontalPanel);
        activePanels.add(horizontalPanel);
        return horizontalPanel;
    }

    public void endHorizontalPanel(InsertPanel.ForIsWidget isWidget) {
        horizontalPanel = null;
        activePanels.remove(isWidget);
    }

    protected InsertPanel.ForIsWidget getActivePanel() {
        return activePanels.get(activePanels.size() - 1);
    }

    public void clearPageAndTimers(String styleName) {
        outerPanel.clear();
        activePanels.clear();
        activePanels.add(outerPanel);
        if (styleName != null && !styleName.isEmpty()) {
            outerPanel.setStyleName(styleName);
        } else {
            outerPanel.setStyleName("contentBody");
        }
        removeFooterButtons();
        clearDomHandlers();
        scaledImagesList.clear();
        clearAllRegions();
    }

    public void addText(String textString) {
        addText(textString, null);
    }

    public void addText(String textString, XmlId xmlId) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        if (xmlId != null) {
            html.getElement().setId(xmlId.getXmlId());
        }
        getActivePanel().add(html);
    }

    public HTML addHtmlText(String textString, String styleName) {
        return addHtmlText(textString, styleName, null);
    }

    public HTML addHtmlText(String textString, String styleName, XmlId xmlId) {
        HTML html = new HTML(new SafeHtmlBuilder().appendHtmlConstant(textString).toSafeHtml());
        if (styleName != null && !styleName.isEmpty()) {
            html.addStyleName(styleName);
        }
        if (xmlId != null) {
            html.getElement().setId(xmlId.getXmlId());
        }
        getActivePanel().add(html);
        return html;
    }

//    public HTML addHighlightedText(String textString) {
//        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
//        html.addStyleName("highlightedText");
//        getActivePanel().add(html);
//        return html;
//    }
    public void addPadding() {
        getActivePanel().add(new HTML("&nbsp;"));
    }

    public void clearRecorderIndicator() {
        if (recordingLabel != null) {
            outerPanel.remove(recordingLabel);
        }
    }

    public void setRecorderState(String message, boolean isRecording) {
        if (recordingLabel == null) {
            recordingLabel = new Label();
        }
        recordingLabel.setStyleName((isRecording) ? "recordingLabel" : "notRecordingLabel");
        if (isRecording || message != null) {
            recordingLabel.setText((message != null && !message.isEmpty()) ? message : "");
            outerPanel.add(recordingLabel);
        } else {
            outerPanel.remove(recordingLabel);
        }
    }

    public void addWidget(IsWidget isWidget) {
        getActivePanel().add(isWidget);
    }

    protected void addSizeAttributes(final Element imageElement, int percentOfPage, int maxHeight, int maxWidth) {
        imageElement.getStyle().setProperty("imageOrientation", "from-image"); // the image-orientation style is not supported by most browsers yet
        if (percentOfPage > 0) {
            scaledImagesList.add(new ImageEntry(imageElement, percentOfPage));
//            image.getElement().getStyle().setProperty("width", percentOfPage + "%");
//            image.getElement().getStyle().setProperty("height", "auto");
            resizeImage(imageElement, Window.getClientHeight(), Window.getClientWidth(), percentOfPage);
        } else {
            if (maxWidth > 0) {
                imageElement.getStyle().setProperty("maxWidth", maxWidth + "%");
            }
            if (maxHeight > 0) {
                imageElement.getStyle().setProperty("maxHeight", maxHeight + "%");
            }
        }
    }

    public void addImage(SafeUri imagePath, final SafeUri linkUrl, int percentOfPage, int maxHeight, int maxWidth, String align) {
        final Image image = new Image(imagePath);
        addSizeAttributes(image.getElement(), percentOfPage, maxHeight, maxWidth);
        final SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

            @Override
            protected void singleShotFired() {
                Window.open(linkUrl.asString(), "_system", "");
            }
        };
        image.addClickHandler(singleShotEventListener);
        image.addTouchStartHandler(singleShotEventListener);
        image.addTouchMoveHandler(singleShotEventListener);
        image.addTouchEndHandler(singleShotEventListener);
        getActivePanel().add(image);
    }

    public void centrePage() {
        outerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    }

    public void addLink(String label, final String target) {
        final Anchor anchor = new Anchor(new SafeHtmlBuilder().appendEscapedLines(label).toSafeHtml());
        // this link relies on the org.apache.cordova.inappbrowser which offers secure viewing of external html pages and handles user navigation such as back navigation.
        // in this case the link will be opend in the system browser rather than in the cordova application.
        getActivePanel().add(anchor);
        final SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

            @Override
            protected void singleShotFired() {
                Window.open(target, "_system", "");
            }
        };
        anchor.addClickHandler(singleShotEventListener);
        anchor.addTouchStartHandler(singleShotEventListener);
        anchor.addTouchMoveHandler(singleShotEventListener);
        anchor.addTouchEndHandler(singleShotEventListener);
        anchor.addStyleName("pageLink");
    }

    public StimulusButton addOptionButton(final PresenterEventListener presenterListener) {
        StimulusButton nextButton = getOptionButton(presenterListener);
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            nextButton.addStyleName(presenterListener.getStyleName());
        }
        getActivePanel().add(nextButton.getWidget());
        return nextButton;
    }

    public StimulusButton addFooterButton(final PresenterEventListener presenterListener) {
        StimulusButton nextButton = getOptionButton(presenterListener);
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            nextButton.addStyleName(presenterListener.getStyleName());
        } else {
            nextButton.addStyleName("footerButton");
        }
        addToFooter(nextButton.getWidget());
        return nextButton;
    }

    public List<StimulusButton> addRatingButtons(final List<PresenterEventListener> presenterListeners, final String ratingLabelLeft, final String ratingLabelRight, boolean footerButtons, String styleName, final String radioGroupName, final boolean allowMultiple, final String savedValue, final Panel ratingStylePanel, final OrientationType orientationType) {
        final ArrayList<StimulusButton> stimulusButtonList = new ArrayList<>();
        final Panel ratingOuterPanel = (ratingStylePanel == null) ? new VerticalPanel() : ratingStylePanel;
        final Panel buttonsPanel;
        final Panel labelsPanel;
        switch (orientationType) {
            case horizontal:
                buttonsPanel = new HorizontalPanel();
                labelsPanel = new HorizontalPanel();
                break;
            case vertical:
                buttonsPanel = new VerticalPanel();
                labelsPanel = buttonsPanel;
                break;
            default:
                buttonsPanel = new FlowPanel();
                labelsPanel = buttonsPanel;
        }
        if (ratingLabelLeft != null) {
            labelsPanel.add(new Label(ratingLabelLeft));
        }
        for (PresenterEventListener listener : presenterListeners) {
            // stimulusRatingRadio needs stimulusFreeText objects to validate them
            StimulusButton nextButton = (radioGroupName != null) ? (allowMultiple) ? getCheckbox(listener, savedValue) : getRadioButton(listener, radioGroupName, savedValue) : getOptionButton(listener);
            if (styleName != null && !styleName.isEmpty()) {
                nextButton.addStyleName(styleName);
            } else if (footerButtons) {
                nextButton.addStyleName("footerButton");
            }
            buttonsPanel.add(nextButton.getWidget());
            switch (orientationType) {
                case horizontal:
                    ((HorizontalPanel) buttonsPanel).setCellWidth(nextButton.getWidget(), (100 / presenterListeners.size()) + "%");
                    break;
                case vertical:
                    ((VerticalPanel) buttonsPanel).setCellHeight(nextButton.getWidget(), (100 / presenterListeners.size()) + "%");
                    break;
                default:
                // we dont do anything for a FlowPanel here
            }
            stimulusButtonList.add(nextButton);
        }
        if (ratingLabelRight != null) {
            final Label label = new Label(ratingLabelRight);
            labelsPanel.add(label);
            if (orientationType == OrientationType.horizontal) {
                label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
            }
        }
        if (orientationType != OrientationType.flow) {
            ratingOuterPanel.setWidth("100%");
            labelsPanel.setWidth("100%");
            buttonsPanel.setWidth("100%");
        }
        ratingOuterPanel.add(labelsPanel);
        ratingOuterPanel.add(buttonsPanel);
        if (footerButtons) {
            addToFooter(ratingOuterPanel);
        } else {
            addWidget(ratingOuterPanel);
        }
        return stimulusButtonList;
    }

    public void addTouchInputCapture(TouchInputCapture touchInputCapture) {
//        RootPanel root = RootPanel.get();
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, TouchStartEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, TouchMoveEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, TouchEndEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, TouchCancelEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, MouseMoveEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, MouseDownEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, MouseUpEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, MouseOutEvent.getType()));
//        domHandlerArray.add(root.addDomHandler(touchInputCapture, MouseOverEvent.getType()));
        domHandlerArray.add(Event.addNativePreviewHandler(touchInputCapture));
    }

    public void addHotKeyListener(final int hotKey, final ButtonGroupMember buttonGroupMember, final TimedStimulusListener onKeyDownListener, final TimedStimulusListener onKeyUpListener) {
        if (hotKey > 0) {
            RootPanel root = RootPanel.get();
            domHandlerArray.add(root.addDomHandler(new KeyDownHandler() {
                @Override
                public void onKeyDown(KeyDownEvent event) {
                    if (buttonGroupMember.isEnabled()) {
                        final int nativeKeyCode = event.getNativeKeyCode();
                        // we map 190 which is the period key to the numeric period key
                        if (((nativeKeyCode == 190) ? KeyCodes.KEY_NUM_PERIOD : nativeKeyCode) == hotKey) {
                            event.stopPropagation();
                            onKeyDownListener.postLoadTimerFired();
                        }
                    }
                }
            }, KeyDownEvent.getType()));
            domHandlerArray.add(root.addDomHandler(new KeyUpHandler() {
                @Override
                public void onKeyUp(KeyUpEvent event) {
                    if (buttonGroupMember.isEnabled()) {
                        final int nativeKeyCode = event.getNativeKeyCode();
                        // we map 190 which is the period key to the numeric period key
                        if (((nativeKeyCode == 190) ? KeyCodes.KEY_NUM_PERIOD : nativeKeyCode) == hotKey) {
                            event.stopPropagation();
                            onKeyUpListener.postLoadTimerFired();
                        }
                    }
                }
            }, KeyUpEvent.getType()));
        }
    }

    private void addHotKeyListener(final PresenterEventListener presenterListener, final SingleShotEventListener singleShotEventListener) {
        if (presenterListener.getHotKey() > 0) {
            RootPanel root = RootPanel.get();
            domHandlerArray.add(root.addDomHandler(new KeyDownHandler() {
                @Override
                public void onKeyDown(KeyDownEvent event) {
                    final int nativeKeyCode = event.getNativeKeyCode();
                    // we map 190 which is the period key to the numeric period key
                    if (((nativeKeyCode == 190) ? KeyCodes.KEY_NUM_PERIOD : nativeKeyCode) == presenterListener.getHotKey()) {
                        event.stopPropagation();
//                        clearDomHandlers();
                        singleShotEventListener.eventFired();
                    }
                }
            }, KeyDownEvent.getType()));
        }
    }

    public StimulusButton getOptionButton(final PresenterEventListener presenterListener) {
        final Button nextButton = new Button(presenterListener.getLabel());
        return configureButton(nextButton, presenterListener);
    }

    public StimulusButton getRadioButton(final PresenterEventListener presenterListener, final String radioGroupName, final String savedValue) {
        final RadioButton nextButton = new RadioButton(radioGroupName, presenterListener.getLabel(), true);
        nextButton.setValue(presenterListener.getLabel().equals(savedValue));
        if (nextButton.getValue()) {
            nextButton.addStyleName("optionButtonActivated");
        }
//        nextButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                nextButton.setValue(!nextButton.getValue());
//            }
//        });
        Event.sinkEvents(nextButton.getElement(), Event.ONCLICK);
        Event.setEventListener(nextButton.getElement(), new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                nextButton.setValue(!nextButton.getValue());
            }
        });
        return configureButton(nextButton, presenterListener);
    }

    public StimulusButton getCheckbox(final PresenterEventListener presenterListener, final String savedValue) {
        final CheckBox nextButton = new CheckBox(presenterListener.getLabel(), true);
        nextButton.setValue(savedValue.matches("^([^,]*,)*" + presenterListener.getLabel() + "(,[^,]*)*$"));
        if (nextButton.getValue()) {
            nextButton.addStyleName("optionButtonActivated");
        }
        Event.sinkEvents(nextButton.getElement(), Event.ONCLICK);
        Event.setEventListener(nextButton.getElement(), new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                nextButton.setValue(!nextButton.getValue());
            }
        });
        return configureButton(nextButton, presenterListener);
    }

    private StimulusButton configureButton(final ButtonBase nextButton, final PresenterEventListener presenterListener) {
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        final SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    if (nextButton instanceof RadioButton) {
                        ((RadioButton) nextButton).setValue(Boolean.TRUE);
                    } else if (nextButton instanceof CheckBox) {
                        ((CheckBox) nextButton).setValue(!((CheckBox) nextButton).getValue());
                    } else {
                        nextButton.addStyleName("optionButtonActivated");
                    }
                    presenterListener.eventFired(nextButton, this);
                }
                resetSingleShot();
            }
        };
        nextButton.addClickHandler(singleShotEventListener);
        nextButton.addTouchStartHandler(singleShotEventListener);
        nextButton.addTouchMoveHandler(singleShotEventListener);
        nextButton.addTouchEndHandler(singleShotEventListener);
        addHotKeyListener(presenterListener, singleShotEventListener);
        return new StimulusButton() {

            @Override
            public Widget getWidget() {
                return nextButton;
            }

            @Override
            public void addStyleName(String styleName) {
                if (styleName != null && !styleName.isEmpty()) {
                    nextButton.addStyleName(styleName);
                }
            }

            @Override
            public void setStyleName(String styleName) {
                if (styleName != null) {
                    nextButton.setStyleName(styleName);
                }
            }

            @Override
            public void removeStyleName(String styleName) {
                nextButton.removeStyleName(styleName);
            }

            @Override
            public void setEnabled(boolean enabled) {
                nextButton.setEnabled(enabled);
            }

            @Override
            public boolean isEnabled() {
                return nextButton.isEnabled();
            }

            @Override
            public boolean isChecked() {
                if (nextButton instanceof RadioButton) {
                    return ((RadioButton) nextButton).getValue();
                } else if (nextButton instanceof CheckBox) {
                    return ((CheckBox) nextButton).getValue();
                }
                return false;
            }

            @Override
            public String getValue() {
                return presenterListener.getLabel();
            }

            @Override
            public void setVisible(boolean visible) {
                nextButton.setVisible(visible);
            }

            @Override
            public void triggerSingleShotEventListener() {
                singleShotEventListener.eventFired();
            }
        };
    }

    public void clearDomHandlers() {
        // todo: make sure this is cleared before the screen exits
        for (HandlerRegistration domHandler : domHandlerArray) {
            domHandler.removeHandler();
        }
        domHandlerArray.clear();
    }

    public StimulusButton addImageButton(final PresenterEventListener presenterListener, final SafeUri imagePath, final boolean isTouchZone) {
        final Image image = new Image(imagePath);
        final Button imageButton = new Button();
        imageButton.getElement().appendChild(image.getElement());
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            image.addStyleName(presenterListener.getStyleName());
        }
        imageButton.addStyleName((presenterListener.getStyleName() == null || presenterListener.getStyleName().isEmpty()) ? "imageButton" : presenterListener.getStyleName());
        getActivePanel().add(imageButton);
        final SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

            @Override
            protected void singleShotFired() {
                if (imageButton.isEnabled()) {
                    presenterListener.eventFired(imageButton, this);
                }
                resetSingleShot();
            }
        };
        if (!isTouchZone) {
            imageButton.setEnabled(true);
            imageButton.addClickHandler(singleShotEventListener);
            imageButton.addTouchStartHandler(singleShotEventListener);
            imageButton.addTouchMoveHandler(singleShotEventListener);
            imageButton.addTouchEndHandler(singleShotEventListener);
            addHotKeyListener(presenterListener, singleShotEventListener);
        } else {
            // setting this to false breaks the touch input capture
//            imageButton.setEnabled(false);
        }
        final StimulusButton stimulusButton = new StimulusButton() {
            boolean isEnabled = true;

            @Override
            public Widget getWidget() {
                return imageButton;
            }

            @Override
            public void addStyleName(String styleName) {
                if (styleName != null && !styleName.isEmpty()) {
                    imageButton.addStyleName(styleName);
                    image.addStyleName(styleName);
                }
            }

            @Override
            public void setStyleName(String styleName) {
                if (styleName != null) {
                    imageButton.setStyleName(styleName);
                    image.setStyleName(styleName);
                }
            }

            @Override
            public void removeStyleName(String styleName) {
                imageButton.removeStyleName(styleName);
                image.removeStyleName(styleName);
            }

            @Override
            public void setEnabled(boolean enabled) {
                isEnabled = enabled;
                if (!isTouchZone) {
                    imageButton.setEnabled(enabled);
                }
            }

            @Override
            public boolean isEnabled() {
                return isEnabled;
            }

            @Override
            public boolean isChecked() {
                return false;
            }

            @Override
            public String getValue() {
                return null;
            }

            @Override
            public void setVisible(boolean visible) {
                imageButton.setVisible(visible);
            }

            @Override
            public void triggerSingleShotEventListener() {
                if (isEnabled) {
                    if (isTouchZone) {
                        presenterListener.eventFired(imageButton, null);
                    } else {
                        singleShotEventListener.eventFired();
                    }
                }
            }
        };
        return stimulusButton;
    }

    public HorizontalPanel addProgressBar(int minimum, int value, int maximum) {
        final HorizontalPanel bargraphOuter = new HorizontalPanel();
        final HorizontalPanel bargraphInner = new HorizontalPanel();
        bargraphOuter.setWidth("100%");
        bargraphOuter.setHeight("10px");
        bargraphInner.setWidth((int) (100.0 / maximum * value) + "%");
        bargraphInner.setHeight("10px");
        bargraphOuter.setStyleName("bargraphOuter");
        bargraphInner.setStyleName("bargraphInner");
        bargraphOuter.add(bargraphInner);
        getActivePanel().add(bargraphOuter);
        return bargraphInner;
    }

    public void updateProgressBar(HorizontalPanel bargraphInner, int minimum, int value, int maximum) {
        bargraphInner.setWidth((int) (100.0 / maximum * value) + "%");
    }

    public void showHtmlPopup(String popupText, final PresenterEventListener... buttonListeners) {
        final PopupPanel popupPanel = new PopupPanel(false); // the close action to this panel causes background buttons to be clicked
        popupPanel.setGlassEnabled(true);
        popupPanel.setStylePrimaryName("svgPopupPanel");
        final VerticalPanel popupverticalPanel = new VerticalPanel();
        final HTML htmlText = new HTML(new SafeHtmlBuilder().appendHtmlConstant(popupText).toSafeHtml());
        htmlText.setStylePrimaryName("popupTextBox");
        popupverticalPanel.add(htmlText);

        popupverticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        for (final PresenterEventListener buttonListener : buttonListeners) {
            final SingleShotEventListener okSingleShotEventListener = new SingleShotEventListener() {

                @Override
                protected void singleShotFired() {
                    popupPanel.hide();
                    buttonListener.eventFired(null, null);
                }
            };
            final Button okButton = new Button(buttonListener.getLabel());
            if (buttonListener.getStyleName() != null && !buttonListener.getStyleName().isEmpty()) {
                okButton.addStyleName(buttonListener.getStyleName());
            }
            okButton.addClickHandler(okSingleShotEventListener);
            okButton.addTouchStartHandler(okSingleShotEventListener);
            okButton.addTouchMoveHandler(okSingleShotEventListener);
            okButton.addTouchEndHandler(okSingleShotEventListener);
            buttonPanel.add(okButton);
        }
        popupverticalPanel.add(buttonPanel);
        popupPanel.setWidget(popupverticalPanel);
        popupPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {

            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                final int topPosition = Window.getClientHeight() / 2 - offsetHeight;
                // topPosition is used to make sure the dialogue is above the half way point on the screen to avoid the software keyboard covering the box
                // topPosition is also checked to make sure it does not show above the top of the page
                popupPanel.setPopupPosition(Window.getClientWidth() / 2 - offsetWidth / 2, (topPosition < 0) ? 0 : topPosition);
            }
        });
    }

    public void addTextField(String value, boolean readOnly) {
        final TextBox textBox = new TextBox();
        textBox.setReadOnly(readOnly);
        textBox.setStylePrimaryName("metadataOK");
        textBox.setText(value);
        getActivePanel().add(textBox);
    }

    public ValueChangeListener addListBox(final String selectedItem, final String[] initialItems, final String styleName, final ValueChangeListener changeListener) {
        final ListBox listBox = new ListBox();
        if (styleName != null && !styleName.isEmpty()) {
            listBox.setStylePrimaryName(styleName);
        }
        if (initialItems != null) {
            int selectedIndex = 0;
            for (int index = 0; index < initialItems.length; index++) {
                listBox.addItem(initialItems[index]);
                if (selectedItem != null && selectedItem.equals(initialItems[index])) {
                    selectedIndex = index;
                }
            }
            listBox.setSelectedIndex(selectedIndex);
        }
        listBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                changeListener.onValueChange(listBox.getSelectedValue());
            }
        });
        final ValueChangeListener itemAddedListener = new ValueChangeListener<String>() {
            @Override
            public void onValueChange(String value) {
                listBox.addItem(value);
                if (selectedItem != null && selectedItem.equals(value)) {
                    listBox.setSelectedIndex(listBox.getItemCount() - 1);
                }
            }
        };
        getActivePanel().add(listBox);
        return itemAddedListener;
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        super.parentResized(height, width, units);
        for (ImageEntry imageEntry : scaledImagesList) {
            resizeImage(imageEntry.imageElement, height, width, imageEntry.percentOfPage);
        }
    }

    private void resizeImage(final Element imageElement, int height, int width, int percentOfPage) {
        imageElement.getStyle().clearHeight();
        imageElement.getStyle().clearWidth();
        imageElement.getStyle().setProperty("maxHeight", (height - HEADER_SIZE - HEADER_SIZE - 50 - 50 /* the  "- 50 - 50" comes from contentBody in the CSS */) * (percentOfPage / 100.0) + "px");
        imageElement.getStyle().setProperty("maxWidth", (width * (percentOfPage / 100.0)) + "px");
    }
}
