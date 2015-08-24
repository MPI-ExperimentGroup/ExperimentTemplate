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
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;

/**
 * @since Jan 8, 2015 5:01:05 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ComplexView extends SimpleView {

    protected final Messages messages = GWT.create(Messages.class);
    final protected VerticalPanel outerPanel;

    public ComplexView() {
        outerPanel = new VerticalPanel();
        setContent(outerPanel);
    }

    public void clearGui() {
        outerPanel.clear();
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

    public void addWidget(IsWidget isWidget) {
        outerPanel.add(isWidget);
    }

    public void addImage(SafeUri imagePath, final SafeUri linkUrl, int percentWidth, String align) {
        final Image image = new Image(imagePath);
        image.setWidth(percentWidth + "%");
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

    public void addOptionButton(final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName("optionButton");
        nextButton.setEnabled(true);
        outerPanel.add(nextButton);
        final SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    presenterListerner.eventFired(nextButton);
                }
                resetSingleShot();
            }
        };
        nextButton.addClickHandler(singleShotEventListner);
        nextButton.addTouchStartHandler(singleShotEventListner);
        nextButton.addTouchMoveHandler(singleShotEventListner);
        nextButton.addTouchEndHandler(singleShotEventListner);
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
                    presenterListerner.eventFired(imageButton);
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
                    saveEventListner.eventFired(null);
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

    public void addTextField(String value) {
        final TextBox textBox = new TextBox();
        textBox.setStylePrimaryName("metadataOK");
        textBox.setText(value);
        outerPanel.add(textBox);
    }
}
