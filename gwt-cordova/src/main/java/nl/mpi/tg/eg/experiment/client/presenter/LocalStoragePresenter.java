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
package nl.mpi.tg.eg.experiment.client.presenter;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InsertPanel.ForIsWidget;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListener;
import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListener;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.service.SimuliValidationRunner;
import nl.mpi.tg.eg.experiment.client.view.ComplexView;
import nl.mpi.tg.eg.experiment.client.view.TimedStimulusView;

/**
 * @since Mar 10, 2015 2:43:42 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class LocalStoragePresenter extends AbstractTimedPresenter {

    public LocalStoragePresenter(RootLayoutPanel widgetTag) {
        super(widgetTag, new TimedStimulusView(), null, null, null, null);
    }

    @Override
    protected String getTitle() {
        return "Storage Viewer";
    }

//    @Override
//    protected void setContent(final AppEventListener appEventListener) {
//
//        ((ComplexView) simpleView).addOptionButton(new PresenterEventListener() {
//
//            @Override
//            public String getLabel() {
//                return "Edit Current User Data";
//            }
//
//            @Override
//            public String getStyleName() {
//                return null;
//            }
//
//            @Override
//            public int getHotKey() {
//                return -1;
//            }
//
//            @Override
//            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
//                appEventListener.requestApplicationState(ApplicationState.metadata);
//            }
//        });
//        ((ComplexView) simpleView).addOptionButton(new PresenterEventListener() {
//
//            @Override
//            public String getLabel() {
//                return ApplicationState.scores.label;
//            }
//
//            @Override
//            public String getStyleName() {
//                return null;
//            }
//
//            @Override
//            public int getHotKey() {
//                return -1;
//            }
//
//            @Override
//            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
//                appEventListener.requestApplicationState(ApplicationState.scores);
//            }
//        });
//    }

    protected void eraseLocalStorageButton(final String styleName, final String buttonGroup) {
        optionButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return "Erase Stored Data";
            }

            @Override
            public String getStyleName() {
                return styleName;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                final Storage localStorage = Storage.getLocalStorageIfSupported();
                localStorage.clear();
                Window.Location.replace(Window.Location.getPath());
            }
        }, buttonGroup);
    }

    protected void stimuliValidation() {
        final ListBox tagsListBox = new ListBox();
//        tagsListBox.setMultipleSelect(true);
        for (final GeneratedStimulus.Tag tag : GeneratedStimulus.Tag.values()) {
            tagsListBox.addItem(tag.name());
        }
//        ForIsWidget horizontalPanel = ((ComplexView) simpleView).startHorizontalPanel();
        ((ComplexView) simpleView).startTable("gridTable");
        ForIsWidget startRow = ((ComplexView) simpleView).startRow();
        ForIsWidget cell1 = ((ComplexView) simpleView).startCell(null);
        ((ComplexView) simpleView).addText("tag");
        ((ComplexView) simpleView).addWidget(tagsListBox);
        ((ComplexView) simpleView).endCell(cell1);
        ForIsWidget cell2 = ((ComplexView) simpleView).startCell(null);
        final IntegerBox adjacencyThreshold = new IntegerBox();
        adjacencyThreshold.setValue(3);
        ((ComplexView) simpleView).addText("adjacencyThreshold");
        ((ComplexView) simpleView).addWidget(adjacencyThreshold);
        ((ComplexView) simpleView).endCell(cell2);
        ForIsWidget cell3 = ((ComplexView) simpleView).startCell(null);
        final IntegerBox maxStimuli = new IntegerBox();
        maxStimuli.setValue(100);
        ((ComplexView) simpleView).addText("maxStimuli");
        ((ComplexView) simpleView).addWidget(maxStimuli);
        ((ComplexView) simpleView).endCell(cell3);
        ForIsWidget cell4 = ((ComplexView) simpleView).startCell(null);
        final CheckBox randomise = new CheckBox();
        randomise.setValue(true);
        ((ComplexView) simpleView).addText("randomise");
        ((ComplexView) simpleView).addWidget(randomise);
        ((ComplexView) simpleView).endCell(cell4);
        ForIsWidget cell5 = ((ComplexView) simpleView).startCell(null);
        final IntegerBox repeatCount = new IntegerBox();
        repeatCount.setValue(1);
        ((ComplexView) simpleView).addText("repeatCount");
        ((ComplexView) simpleView).addWidget(repeatCount);
        ((ComplexView) simpleView).endCell(cell5);
        ForIsWidget cell6 = ((ComplexView) simpleView).startCell(null);
        final IntegerBox repeatRandomWindow = new IntegerBox();
        repeatRandomWindow.setValue(6);
        ((ComplexView) simpleView).addText("repeatRandomWindow");
        ((ComplexView) simpleView).addWidget(repeatRandomWindow);
        ((ComplexView) simpleView).endCell(cell6);
        ForIsWidget cell7 = ((ComplexView) simpleView).startCell(null);
        final IntegerBox cyclesToRun = new IntegerBox();
        cyclesToRun.setValue(1000000);
        ((ComplexView) simpleView).addText("cyclesToRun");
        ((ComplexView) simpleView).addWidget(cyclesToRun);
        ((ComplexView) simpleView).endCell(cell7);
        ((ComplexView) simpleView).endRow(startRow);
        ((ComplexView) simpleView).endTable();
//        ((ComplexView) simpleView).endHorizontalPanel(horizontalPanel);
        ((ComplexView) simpleView).addOptionButton(new PresenterEventListener() {
            @Override
            public String getLabel() {
                return "Calculate Transition Table";
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener shotEventListener) {
                final HTML simuliValidationHtmlText = ((ComplexView) simpleView).addHtmlText("stimuliValidation: " + tagsListBox.getSelectedItemText(), null);
                final HTML sampleCount = ((ComplexView) simpleView).addHtmlText("sampleCount", null);
                final HTML uniqueCount = ((ComplexView) simpleView).addHtmlText("uniqueCount", null);
                final FlexTable outputTable = new FlexTable();
                ((ComplexView) simpleView).addWidget(outputTable);
                final FlexTable transitionTable = new FlexTable();
                ((ComplexView) simpleView).addWidget(transitionTable);
                new SimuliValidationRunner() {
                    @Override
                    public void appendOutput(String outputString) {
                        simuliValidationHtmlText.setHTML(simuliValidationHtmlText.getHTML() + "<br/>" + outputString);
                    }

                    @Override
                    public void sampleCount(int outputString) {
                        sampleCount.setHTML("sampleCount: " + outputString);
                    }

                    @Override
                    public void uniqueCount(int outputString) {
                        uniqueCount.setHTML("uniqueCount: " + outputString);
                    }

                    @Override
                    public void transitionTableValue(int column, int row, String value) {
                        transitionTable.setText(row, column, value);
                    }

                    @Override
                    public void appendUniqueStimuliList(String outputString) {
                        ((ComplexView) simpleView).addText(outputString);
                    }

                    @Override
                    public void outputTableValue(int column, int row, String value) {
                        outputTable.setText(row, column, value);
                    }

                }.calculate(GeneratedStimulus.Tag.valueOf(tagsListBox.getSelectedItemText()), adjacencyThreshold.getValue(), maxStimuli.getValue(), randomise.getValue(), repeatCount.getValue(), repeatRandomWindow.getValue(), cyclesToRun.getValue());
            }

            @Override
            public int getHotKey() {
                return -1;
            }
        });
    }

    final Label animationCounterLabel = new Label();
    private void updateRateCounter(Double updatedValue){
        animationCounterLabel.setText(Double.toString(updatedValue));
    }

    protected native void startRateIndicator()/*-{
        var localStoragePresenter = this;
        $wnd.displayRateIndicatorValue = 0;
        function updateRateIndicator() {
            var hasListeners = localStoragePresenter.@nl.mpi.tg.eg.experiment.client.presenter.LocalStoragePresenter::updateRateCounter(Ljava/lang/Double;)($wnd.displayRateIndicatorValue);
            $wnd.displayRateIndicatorValue++;
            requestAnimationFrame(updateRateIndicator);
        }
        requestAnimationFrame(updateRateIndicator);
    }-*/;

    protected void addDebugWidgets() {
        optionButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return "Start Native Animation Frame Rate Indicator";
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                ((ComplexView) simpleView).addWidget(animationCounterLabel);
                startRateIndicator();
            }
        }, null);
        optionButton(new PresenterEventListener() {

            @Override
            public String getLabel() {
                return "Start GWT Animation Frame Rate Indicator";
            }

            @Override
            public String getStyleName() {
                return null;
            }

            @Override
            public int getHotKey() {
                return -1;
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListener singleShotEventListener) {
                final Label animationGwtCounterLabel = new Label();
                Double updatedGwtValue = 0;
                AnimationScheduler.get().requestAnimationFrame(new AnimationScheduler.AnimationCallback() {
                    @Override
                    public void execute(double arg0) {
                        animationGwtCounterLabel.setText(Double.toString(updatedGwtValue));
                        updatedGwtValue++;
                        AnimationScheduler.get().requestAnimationFrame(this);
                    }
                });
            }
        }, null);
    }
    
    protected void addKeyboardDebug() {
        final Label clickLabel = new Label();
        final Label mouseLabel = new Label();
        final Label wheelLabel = new Label();
        final Label keyDownLabel = new Label();

        ((ComplexView) simpleView).addWidget(clickLabel);
        ((ComplexView) simpleView).addWidget(mouseLabel);
        ((ComplexView) simpleView).addWidget(wheelLabel);
        ((ComplexView) simpleView).addWidget(keyDownLabel);
        RootPanel root = RootPanel.get();
        root.addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                clickLabel.setText(event.toDebugString());
            }
        }, KeyUpEvent.getType());
        root.addDomHandler(new MouseWheelHandler() {
            @Override
            public void onMouseWheel(MouseWheelEvent event) {
                wheelLabel.setText(event.getX() + " : " + event.getY() + " DeltaY: " + event.getDeltaY());
            }
        }, MouseWheelEvent.getType());
        root.addDomHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                mouseLabel.setText(event.getX() + " : " + event.getY());
            }
        }, MouseMoveEvent.getType());
        root.addDomHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
//                final int nativeKeyCode = event.getNativeKeyCode();
                keyDownLabel.setText("NativeKeyCode: " + event.getNativeKeyCode()
                        + " AltKey:" + event.isAltKeyDown()
                        + " ControlKey:" + event.isControlKeyDown()
                        + " DownArrow:" + event.isDownArrow()
                        + " LeftArrow:" + event.isLeftArrow()
                        + " MetaKey:" + event.isMetaKeyDown()
                        + " RightArrow:" + event.isRightArrow()
                        + " ShiftKey:" + event.isShiftKeyDown()
                        + " UpArrow:" + event.isUpArrow());
            }
        }, KeyDownEvent.getType());
    }

    protected void localStorageData() {
        final Storage localStorage = Storage.getLocalStorageIfSupported();
        for (int itemIndex = 0; itemIndex < localStorage.getLength(); itemIndex++) {
            final String key = localStorage.key(itemIndex);
            ((ComplexView) simpleView).addHtmlText(key, "highlightedText");
            ((ComplexView) simpleView).addText(localStorage.getItem(key));
        }
    }
}
