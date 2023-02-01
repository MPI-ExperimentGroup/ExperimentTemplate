/*
 * Copyright (C) 2014 Language In Interaction
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

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

/**
 * @since Oct 7, 2014 2:06:28 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleView extends AbstractView {

    public enum ButtonType {

        menu, back, next
    }
    private final HorizontalPanel footerPanel;
    private Grid headerPanel = null;
    private final VerticalPanel borderedContentPanel;
    private final ScrollPanel scrollPanel;
    protected int HEADER_SIZE;
    protected final List<Timer> timerList = new ArrayList<>();

    public SimpleView() {

        footerPanel = new HorizontalPanel();
        borderedContentPanel = new VerticalPanel();
        borderedContentPanel.setStylePrimaryName("contentPanel");
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//        addSouth(footerPanel, 50);
        scrollPanel = new ScrollPanel();
        borderedContentPanel.add(scrollPanel);
        borderedContentPanel.add(footerPanel);
        footerPanel.setVisible(false);
    }

    public final void setContent(Panel panel) {
        panel.setStylePrimaryName("contentBody");
        scrollPanel.setWidget(panel);
    }

    public void setDisplayText(String text) {
        final HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(text).toSafeHtml());
        html.setStylePrimaryName("contentBody");
        scrollPanel.setWidget(html);
    }

    public void addInfoButton(final PresenterEventListener presenterListerner) {
        if (headerPanel != null) {
            final Label headerButton = new Label(presenterListerner.getLabel());
            headerButton.addStyleName("headerButton");
            SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

                @Override
                protected void singleShotFired() {
                    presenterListerner.eventFired(null, this);
                }
            };
            headerButton.addClickHandler(singleShotEventListener);
            headerButton.addTouchStartHandler(singleShotEventListener);
            headerButton.addTouchMoveHandler(singleShotEventListener);
            headerButton.addTouchEndHandler(singleShotEventListener);
            headerPanel.setWidget(0, 2, headerButton);
        }
    }

    public void addTitle(String label, final PresenterEventListener presenterListerner) {
        if (ApplicationController.SHOW_HEADER || presenterListerner != null) {
            HEADER_SIZE = 50;
            headerPanel = new Grid(1, 3);
            headerPanel.setWidth("100%");
            headerPanel.setStylePrimaryName("headerPanel");
            addNorth(headerPanel, HEADER_SIZE);
            final Label headerLabel = new Label(label);
            headerLabel.setStylePrimaryName("headerLabel");
            headerPanel.setWidget(0, 0, new MenuButton(presenterListerner));
            headerPanel.setWidget(0, 1, headerLabel);
            headerPanel.setStylePrimaryName("headerPanel");
            headerPanel.getColumnFormatter().setWidth(1, "100%");
        } else {
            HEADER_SIZE = 0;
            headerPanel = null;
        }
        add(borderedContentPanel);
    }

    public void addToFooter(IsWidget button) {
        footerPanel.setVisible(true);
        footerPanel.add(button);
        resizeView();
    }

    public Button setButton(ButtonType buttonType, final PresenterEventListener presenterListerner) {
        footerPanel.setVisible(true);
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName(buttonType.name() + "Button");
        nextButton.setEnabled(true);
        footerPanel.add(nextButton);
        SingleShotEventListener singleShotEventListener = new SingleShotEventListener() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    presenterListerner.eventFired(nextButton, this);
                } else {
                    resetSingleShot();
                }
            }
        };
        nextButton.addTouchStartHandler(singleShotEventListener);
        nextButton.addTouchMoveHandler(singleShotEventListener);
        nextButton.addTouchEndHandler(singleShotEventListener);
        nextButton.addClickHandler(singleShotEventListener);
        return nextButton;
    }

    public void removeFooterButtons() {
        footerPanel.clear();
        footerPanel.setVisible(footerPanel.getWidgetCount() > 0);
    }

    public void addBackgroundImage(final SafeUri imagePath, final String styleName, final int postLoadMs, final TimedStimulusListener timedStimulusListener) {
        //        final Image image = new Image(imagePath);
        //            this.getElement().getStyle().setBackgroundColor("green");
                if (imagePath == null) {
                    this.getElement().getStyle().clearBackgroundImage();
                } else {
                    this.getElement().getStyle().setBackgroundImage("url(" + imagePath.asString() + ")");
                }
                this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
        //            this.getElement().getStyle().setProperty("backgroundSize", "100% 100%");
                this.getElement().getStyle().setProperty("backgroundRepeat", "no-repeat");
                this.getElement().getStyle().setProperty("backgroundPosition", "50% 50%");
                // remove the custom styles but keep the page width style
                this.setStyleName(this.getStyleName().contains("normalWidth") ? "normalWidth" : "narrowWidth");
                if (styleName != null && !styleName.isEmpty()) {
                    this.addStyleName(styleName);
                } else {
                    this.getElement().getStyle().setProperty("backgroundSize", "cover");
        //            resizeView(); // this is to put back the screen size styles
                }
        //        image.addLoadHandler(new LoadHandler() {
        //
        //            @Override
        //            public void onLoad(LoadEvent event) {
                if (postLoadMs > 0) {
                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            timedStimulusListener.postLoadTimerFired();
                        }
                    };
                    timerList.add(timer);
                    timer.schedule(postLoadMs);
                } else {
                    timedStimulusListener.postLoadTimerFired();
                }
        //            }
        //        });
        //        outerPanel.add(image);
    }

    protected void scrollToPosition(int position) {
        scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() + position - scrollPanel.getAbsoluteTop());
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        footerPanel.setWidth(width + "px");
        scrollPanel.setWidth(width + "px");
        if (footerPanel.getWidgetCount() > 0) {
            final int footerHeight;
            if (footerPanel.getOffsetHeight() < HEADER_SIZE) {
                if (footerPanel.getWidget(0) instanceof VerticalPanel) {
                    footerHeight = HEADER_SIZE * ((VerticalPanel) footerPanel.getWidget(0)).getWidgetCount();
                } else {
                    footerHeight = HEADER_SIZE; // footerPanel.getOffsetHeight();
                }
            } else {
                footerHeight = footerPanel.getOffsetHeight();
            }
            scrollPanel.setHeight(height - HEADER_SIZE - ((footerHeight < HEADER_SIZE) ? HEADER_SIZE : footerHeight) + "px");
        } else {
            scrollPanel.setHeight(height - HEADER_SIZE + "px");
        }
        setStyleByWidth(width);
    }
}
