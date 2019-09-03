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
import com.google.gwt.user.client.Event;
//import com.google.gwt.event.dom.client.DragStartEvent;
//import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
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
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
import nl.mpi.tg.eg.experiment.client.listener.StimulusButton;
import nl.mpi.tg.eg.experiment.client.listener.TouchInputCapture;
import nl.mpi.tg.eg.experiment.client.presenter.AbstractStimulusPresenter.OrientationType;

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
//
//        outerPanel.getElement().setDraggable(Element.DRAGGABLE_TRUE);
//        outerPanel.addDragStartHandler(new DragStartHandler() {
//
//            @Override
//            public void onDragStart(DragStartEvent event) {
//                // TODO Auto-generated method stub
//                event.setData("text", "i am widget1");
//            }
//        ;
//        });
        setContent(outerPanel);
    }

    private void clearAllRegions() {
        regionPanels.clear();
    }

    public void startRegion(final String regionId, final String styleName) {
        VerticalPanel regionTemp = regionPanels.get(regionId);
        if (regionTemp == null) {
            regionTemp = new VerticalPanel();
            regionPanels.put(regionId, regionTemp);
        }
        if (regionTemp.getParent() == null) {
            getActivePanel().add(regionTemp);
        }
        if (styleName != null) {
            // we set or remove the style if it is provided, however if the style is null (no style attribute) we allow the old style to persist.
            regionTemp.setStyleName(styleName);
        }
        activePanels.add(regionTemp);
    }

    public void endRegion() {
        activePanels.remove(activePanels.size() - 1);
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

    public FlexTable gridPanel() {
        final int index = gridPanelList.size() - 1;
        return (index >= 0) ? gridPanelList.get(index) : null;
    }

    public void startCell(String styleName) {
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
    }

    public void endCell() {
        activePanels.remove(activePanels.size() - 1);
    }

    public void startRow() {
        if (gridPanel() != null) {
            gridPanel().insertRow(gridPanel().getRowCount());
        } else {
            startHorizontalPanel();
        }
    }

    public void endRow() {
        if (gridPanel() == null) {
            endHorizontalPanel();
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

    public void startHorizontalPanel() {
        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        outerPanel.add(horizontalPanel);
        activePanels.add(horizontalPanel);
    }

    public void endHorizontalPanel() {
        horizontalPanel = null;
        activePanels.remove(activePanels.size() - 1);
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
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        getActivePanel().add(html);
    }

    public HTML addHtmlText(String textString, String styleName) {
        HTML html = new HTML(new SafeHtmlBuilder().appendHtmlConstant(textString).toSafeHtml());
        if (styleName != null && !styleName.isEmpty()) {
            html.addStyleName(styleName);
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
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                Window.open(linkUrl.asString(), "_system", "");
            }
        };
        image.addClickHandler(singleShotEventListner);
        image.addTouchStartHandler(singleShotEventListner);
        image.addTouchMoveHandler(singleShotEventListner);
        image.addTouchEndHandler(singleShotEventListner);
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
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                Window.open(target, "_system", "");
            }
        };
        anchor.addClickHandler(singleShotEventListner);
        anchor.addTouchStartHandler(singleShotEventListner);
        anchor.addTouchMoveHandler(singleShotEventListner);
        anchor.addTouchEndHandler(singleShotEventListner);
        anchor.addStyleName("pageLink");
    }

    public StimulusButton addOptionButton(final PresenterEventListner presenterListener) {
        StimulusButton nextButton = getOptionButton(presenterListener);
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            nextButton.addStyleName(presenterListener.getStyleName());
        }
        getActivePanel().add(nextButton.getWidget());
        return nextButton;
    }

    public StimulusButton addFooterButton(final PresenterEventListner presenterListener) {
        StimulusButton nextButton = getOptionButton(presenterListener);
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            nextButton.addStyleName(presenterListener.getStyleName());
        } else {
            nextButton.addStyleName("footerButton");
        }
        addToFooter(nextButton.getWidget());
        return nextButton;
    }

    public List<StimulusButton> addRatingButtons(final List<PresenterEventListner> presenterListeners, final String ratingLabelLeft, final String ratingLabelRight, boolean footerButtons, String styleName, final String buttonGroupName, final String savedValue, final Panel ratingStylePanel, final OrientationType orientationType) {
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
        for (PresenterEventListner listener : presenterListeners) {
            // stimulusRatingRadio needs stimulusFreeText objects to validate them
            StimulusButton nextButton = (buttonGroupName != null) ? getRadioButton(listener, buttonGroupName, savedValue) : getOptionButton(listener);
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

    private void addHotKeyListner(final PresenterEventListner presenterListener, final SingleShotEventListner singleShotEventListner) {
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
                        singleShotEventListner.eventFired();
                    }
                }
            }, KeyDownEvent.getType()));
        }
    }

    public StimulusButton getOptionButton(final PresenterEventListner presenterListener) {
        final Button nextButton = new Button(presenterListener.getLabel());
        return configureButton(nextButton, presenterListener);
    }

    public StimulusButton getRadioButton(final PresenterEventListner presenterListener, final String buttonGroupName, final String savedValue) {
        final RadioButton nextButton = new RadioButton(buttonGroupName, presenterListener.getLabel());
        nextButton.setValue(presenterListener.getLabel().equals(savedValue));
        return configureButton(nextButton, presenterListener);
    }

    private StimulusButton configureButton(final ButtonBase nextButton, final PresenterEventListner presenterListener) {
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    nextButton.addStyleName("optionButtonActivated");
                    presenterListener.eventFired(nextButton, this);
                    if (nextButton instanceof RadioButton) {
                        ((RadioButton) nextButton).setValue(Boolean.TRUE);
                    }
                }
                resetSingleShot();
            }
        };
        nextButton.addClickHandler(singleShotEventListner);
        nextButton.addTouchStartHandler(singleShotEventListner);
        nextButton.addTouchMoveHandler(singleShotEventListner);
        nextButton.addTouchEndHandler(singleShotEventListner);
        addHotKeyListner(presenterListener, singleShotEventListner);
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
            public void setVisible(boolean visible) {
                nextButton.setVisible(visible);
            }

            @Override
            public void triggerSingleShotEventListner() {
                singleShotEventListner.eventFired();
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

    public StimulusButton addImageButton(final PresenterEventListner presenterListener, final SafeUri imagePath, final boolean isTouchZone) {
        final Image image = new Image(imagePath);
        final Button imageButton = new Button();
        imageButton.getElement().appendChild(image.getElement());
        if (presenterListener.getStyleName() != null && !presenterListener.getStyleName().isEmpty()) {
            image.addStyleName(presenterListener.getStyleName());
        }
        imageButton.addStyleName((presenterListener.getStyleName() == null || presenterListener.getStyleName().isEmpty()) ? "imageButton" : presenterListener.getStyleName());
        getActivePanel().add(imageButton);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

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
            imageButton.addClickHandler(singleShotEventListner);
            imageButton.addTouchStartHandler(singleShotEventListner);
            imageButton.addTouchMoveHandler(singleShotEventListner);
            imageButton.addTouchEndHandler(singleShotEventListner);
            addHotKeyListner(presenterListener, singleShotEventListner);
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
            public void setVisible(boolean visible) {
                imageButton.setVisible(visible);
            }

            @Override
            public void triggerSingleShotEventListner() {
                if (isEnabled) {
                    if (isTouchZone) {
                        presenterListener.eventFired(imageButton, null);
                    } else {
                        singleShotEventListner.eventFired();
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

    public void showHtmlPopup(String popupText, final PresenterEventListner... buttonListeners) {
        final PopupPanel popupPanel = new PopupPanel(false); // the close action to this panel causes background buttons to be clicked
        popupPanel.setGlassEnabled(true);
        popupPanel.setStylePrimaryName("svgPopupPanel");
        final VerticalPanel popupverticalPanel = new VerticalPanel();
        final HTML htmlText = new HTML(new SafeHtmlBuilder().appendHtmlConstant(popupText).toSafeHtml());
        htmlText.setStylePrimaryName("popupTextBox");
        popupverticalPanel.add(htmlText);

        popupverticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        for (final PresenterEventListner buttonListener : buttonListeners) {
            final SingleShotEventListner okSingleShotEventListner = new SingleShotEventListner() {

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
            okButton.addClickHandler(okSingleShotEventListner);
            okButton.addTouchStartHandler(okSingleShotEventListner);
            okButton.addTouchMoveHandler(okSingleShotEventListner);
            okButton.addTouchEndHandler(okSingleShotEventListner);
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
