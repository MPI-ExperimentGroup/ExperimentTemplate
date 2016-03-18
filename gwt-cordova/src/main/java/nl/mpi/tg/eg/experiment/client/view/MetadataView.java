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

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends ComplexView {

    private FlexTable flexTable = null;
    final private HashMap<MetadataField, FocusWidget> fieldBoxes;
    final private ArrayList<MetadataField> orderedFields;
    private FocusWidget firstTextBox = null;
    private final Label errorText;
    private final VerticalPanel keyboardPadding;

    public MetadataView() {
        fieldBoxes = new HashMap<>();
        orderedFields = new ArrayList<>();
        errorText = new Label();
        keyboardPadding = new VerticalPanel();
        keyboardPadding.add(new Label(""));
        errorText.setStylePrimaryName("metadataErrorMessage");
    }

    public void addField(final MetadataField metadataField, final String existingValue, String labelString) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        final FocusWidget focusWidget;
        if (metadataField.isCheckBox()) {
            flexTable.setWidget(rowCount, 0, new Label());
            focusWidget = new CheckBox(labelString);
            ((CheckBox) focusWidget).setValue((existingValue == null) ? false : Boolean.parseBoolean(existingValue));
        } else {
            final Label label = new Label(labelString);
            flexTable.setWidget(rowCount, 0, label);
            focusWidget = new TextBox();
            ((TextBox) focusWidget).setText((existingValue == null) ? "" : existingValue);
            ((TextBox) focusWidget).addFocusHandler(new FocusHandler() {

                @Override
                public void onFocus(FocusEvent event) {
                    addKeyboardPadding();
//                scrollToPosition(label.getAbsoluteTop());
                }
            });
        }
        focusWidget.setStylePrimaryName("metadataOK");
//        textBox.addBlurHandler(new BlurHandler() {
//
//            @Override
//            public void onBlur(BlurEvent event) {
//                removeKeyboardPadding();
//            }
//        });
        flexTable.setWidget(rowCount + 1, 0, focusWidget);
        fieldBoxes.put(metadataField, focusWidget);
        orderedFields.add(metadataField);
        if (firstTextBox == null) {
            firstTextBox = focusWidget;
        }
    }

    public void focusFirstTextBox() {
        if (firstTextBox != null) {
            firstTextBox.setFocus(true);
        }
    }

    public List<MetadataField> getFieldNames() {
        return orderedFields;
    }

    public void setFieldValue(MetadataField metadataField, String fieldValue) {
        final FocusWidget focusWidget = fieldBoxes.get(metadataField);
        if (focusWidget instanceof CheckBox) {
            ((CheckBox) focusWidget).setValue(Boolean.valueOf(fieldValue));
        } else if (focusWidget instanceof TextBox) {
            ((TextBox) focusWidget).setValue(fieldValue);
        }
    }

    public String getFieldValue(MetadataField metadataField) {
        final FocusWidget focusWidget = fieldBoxes.get(metadataField);
        if (focusWidget instanceof CheckBox) {
            return Boolean.toString(((CheckBox) focusWidget).getValue());
        } else if (focusWidget instanceof TextBox) {
            return ((TextBox) focusWidget).getValue();
        } else {
            throw new UnsupportedOperationException("Unexpected type for: " + focusWidget.getClass());
        }
    }

    public void showFieldError(MetadataField metadataField) {
        final FocusWidget focusWidget = fieldBoxes.get(metadataField);
        focusWidget.setStylePrimaryName("metadataError");
        errorText.setText(metadataField.getControlledMessage());
        for (int rowCounter = 0; rowCounter < flexTable.getRowCount(); rowCounter++) {
            if (focusWidget.equals(flexTable.getWidget(rowCounter, 0))) {
                flexTable.insertRow(rowCounter);
                flexTable.setWidget(rowCounter, 0, errorText);
                break;
            }
        }
        focusWidget.setFocus(true);
    }

    public void setButtonError(boolean isError, ButtonBase button, String errorMessage) {
        if (isError) {
            if (errorMessage != null) {
                errorText.setText(errorMessage);
                final int rowCount = flexTable.getRowCount();
                flexTable.insertRow(rowCount);
                flexTable.setWidget(rowCount, 0, errorText);
            }
            button.addStyleName("metadataError");
        } else {
            clearErrors();
            button.removeStyleName("metadataError");
        }
    }

    public void clearErrors() {
        for (FocusWidget focusWidget : fieldBoxes.values()) {
            focusWidget.setStylePrimaryName("metadataOK");
        }
        for (int rowCounter = 0; rowCounter < flexTable.getRowCount(); rowCounter++) {
            if (flexTable.getWidget(rowCounter, 0) == errorText) {
                // remove the error message and the tabel row that was added for the error message
                flexTable.removeRow(rowCounter);
                break;
            }
        }
    }

    private void addKeyboardPadding() {
        outerPanel.add(keyboardPadding);
    }

    private void removeKeyboardPadding() {
        outerPanel.remove(keyboardPadding);
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        super.parentResized(height, width, units);
        keyboardPadding.setHeight(height * 0.5 + units);
        setStyleByWidth(width);
    }
}
