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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;

/**
 * @since Jan 8, 2015 5:01:05 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ComplexView extends SimpleView {

    private Label recordingLabel = null;

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
        setContent(outerPanel);
    }

    public void clearPage() {
        outerPanel.clear();
        removeFooterButtons();
        clearDomHandlers();
        scaledImagesList.clear();
    }

    public void addText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addHtmlText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendHtmlConstant(textString).toSafeHtml());
        outerPanel.add(html);
    }

    public void addHighlightedText(String textString) {
        HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(textString).toSafeHtml());
        html.addStyleName("highlightedText");
        outerPanel.add(html);
    }

    public void addPadding() {
        outerPanel.add(new HTML("&nbsp;"));
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
        outerPanel.add(isWidget);
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
        outerPanel.add(image);
    }

    public void centrePage() {
        outerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    }

    public void addLink(String label, final String target) {
        final Anchor anchor = new Anchor(new SafeHtmlBuilder().appendEscapedLines(label).toSafeHtml());
        // this link relies on the org.apache.cordova.inappbrowser which offers secure viewing of external html pages and handles user navigation such as back navigation.
        // in this case the link will be opend in the system browser rather than in the cordova application.
        outerPanel.add(anchor);
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

    public Button addOptionButton(final PresenterEventListner presenterListerner) {
        return addOptionButton(presenterListerner, null);
    }

    public Button addOptionButton(final PresenterEventListner presenterListerner, String styleName) {
        Button nextButton = getOptionButton(presenterListerner);
        if (styleName != null) {
            nextButton.addStyleName(styleName);
        }
        outerPanel.add(nextButton);
        return nextButton;
    }

    public Button addFooterButton(final PresenterEventListner presenterListener) {
        Button nextButton = getOptionButton(presenterListener);
        nextButton.addStyleName("footerButton");
        addToFooter(nextButton);
        return nextButton;
    }

    public void addRatingButtons(final List<PresenterEventListner> presenterListeners, final String ratingLabelLeft, final String ratingLabelRight, boolean footerButtons) {
        final VerticalPanel verticalPanel = new VerticalPanel();
        final HorizontalPanel labelsPanel = new HorizontalPanel();
        if (ratingLabelLeft != null) {
            labelsPanel.add(new Label(ratingLabelLeft));
        }
        if (ratingLabelRight != null) {
            final Label label = new Label(ratingLabelRight);
            labelsPanel.add(label);
            label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        }
        final HorizontalPanel buttonsPanel = new HorizontalPanel();
        for (PresenterEventListner listener : presenterListeners) {
            Button nextButton = getOptionButton(listener);
            nextButton.addStyleName("footerButton");
            buttonsPanel.add(nextButton);
        }
        verticalPanel.setWidth("100%");
        labelsPanel.setWidth("100%");
        buttonsPanel.setWidth("100%");
        verticalPanel.add(labelsPanel);
        verticalPanel.add(buttonsPanel);
        if (footerButtons) {
            addToFooter(verticalPanel);
        } else {
            addWidget(verticalPanel);
        }
    }

    public Button getOptionButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    presenterListerner.eventFired(nextButton, this);
                }
                resetSingleShot();
            }
        };
        nextButton.addClickHandler(singleShotEventListner);
        nextButton.addTouchStartHandler(singleShotEventListner);
        nextButton.addTouchMoveHandler(singleShotEventListner);
        nextButton.addTouchEndHandler(singleShotEventListner);
        if (presenterListerner.getHotKey() > 0) {
            RootPanel root = RootPanel.get();
            domHandlerArray.add(root.addDomHandler(new KeyDownHandler() {
                @Override
                public void onKeyDown(KeyDownEvent event) {
                    final int nativeKeyCode = event.getNativeKeyCode();
                    // we map 190 which is the period key to the numeric period key
                    if (((nativeKeyCode == 190) ? KeyCodes.KEY_NUM_PERIOD : nativeKeyCode) == presenterListerner.getHotKey()) {
                        event.stopPropagation();
                        clearDomHandlers();
                        singleShotEventListner.eventFired();
                    }
                }
            }, KeyDownEvent.getType()));
        }
        return nextButton;
    }

    public void clearDomHandlers() {
        // todo: make sure this is cleared before the screen exits
        for (HandlerRegistration domHandler : domHandlerArray) {
            domHandler.removeHandler();
        }
    }

    public void addImageButton(final PresenterEventListner presenterListerner, final SafeUri imagePath) {

        final Image image = new Image(imagePath);
        final Button imageButton = new Button();
        imageButton.getElement().appendChild(image.getElement());
        imageButton.addStyleName("imageButton");
        imageButton.setEnabled(true);
        outerPanel.add(imageButton);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (imageButton.isEnabled()) {
                    presenterListerner.eventFired(imageButton, this);
                }
                resetSingleShot();
            }
        };
        imageButton.addClickHandler(singleShotEventListner);
        imageButton.addTouchStartHandler(singleShotEventListner);
        imageButton.addTouchMoveHandler(singleShotEventListner);
        imageButton.addTouchEndHandler(singleShotEventListner);
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
        outerPanel.add(bargraphOuter);
        return bargraphInner;
    }

    public void updateProgressBar(HorizontalPanel bargraphInner, int minimum, int value, int maximum) {
        bargraphInner.setWidth((int) (100.0 / maximum * value) + "%");
    }

    public void showHtmlPopup(final PresenterEventListner saveEventListner, String popupText) {
        final PopupPanel popupPanel = new PopupPanel(false); // the close action to this panel causes background buttons to be clicked
        popupPanel.setGlassEnabled(true);
        popupPanel.setStylePrimaryName("svgPopupPanel");
        final VerticalPanel popupverticalPanel = new VerticalPanel();
        final HTML htmlText = new HTML(new SafeHtmlBuilder().appendEscapedLines(popupText).toSafeHtml());
        htmlText.setStylePrimaryName("popupTextBox");
        popupverticalPanel.add(htmlText);

        popupverticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        final SingleShotEventListner cancelSingleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                popupPanel.hide();
            }
        };
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        final Button cancelButton = new Button((saveEventListner != null) ? messages.popupCancelButtonLabel() : messages.popupOkButtonLabel());
        cancelButton.addClickHandler(cancelSingleShotEventListner);
        cancelButton.addTouchStartHandler(cancelSingleShotEventListner);
        cancelButton.addTouchMoveHandler(cancelSingleShotEventListner);
        cancelButton.addTouchEndHandler(cancelSingleShotEventListner);
        buttonPanel.add(cancelButton);
        if (saveEventListner != null) {
            final SingleShotEventListner okSingleShotEventListner = new SingleShotEventListner() {

                @Override
                protected void singleShotFired() {
                    popupPanel.hide();
                    saveEventListner.eventFired(null, null);
                }
            };
            final Button okButton = new Button(messages.popupOkButtonLabel());
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
        outerPanel.add(textBox);
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
