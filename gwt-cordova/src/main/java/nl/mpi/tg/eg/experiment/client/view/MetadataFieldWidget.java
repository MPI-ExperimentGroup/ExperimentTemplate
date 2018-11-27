/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.exception.MetadataFieldException;
import nl.mpi.tg.eg.experiment.client.listener.MetadataFieldListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.StimulusFreeText;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Aug 2, 2017 3:41:23 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class MetadataFieldWidget implements StimulusFreeText {

    final private MetadataField metadataField;
    final private Stimulus stimulus;
    final private String initialValue;
    final private FocusWidget focusWidget;
    final private Widget widget;
    final private Label label;
    final private Label errorLabel;
    final private VerticalPanel labelPanel;
    final private int dataChannel;
    final DateOfBirthField dateOfBirthField;
    final private List< MetadataFieldListener> metadataFieldListeners = new ArrayList<>();

    public MetadataFieldWidget(MetadataField metadataField, Stimulus stimulus, String initialValue, final int dataChannel) {
        this.metadataField = metadataField;
        this.stimulus = stimulus;
        this.initialValue = initialValue;
        this.dataChannel = dataChannel;
        if (metadataField.isDate()) {
            label = new Label(metadataField.getFieldLabel());
//            final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
//            final DateBox dateBox = new DateBox();
//            dateBox.getDatePicker().setYearAndMonthDropdownVisible(true);
//            dateBox.setFormat(new DateBox.DefaultFormat(dateFormat)); 
            dateOfBirthField = new DateOfBirthField() {
                @Override
                void valueChanged() {
                    triggerFieldListeners(true);
                }
            };
            focusWidget = dateOfBirthField.getTextBox();
            if (initialValue != null) {
                dateOfBirthField.setDate(initialValue);
            }
            widget = dateOfBirthField;
        } else if (metadataField.isCheckBox()) {
            dateOfBirthField = null;
            label = new Label();
            focusWidget = new CheckBox(metadataField.getFieldLabel());
            ((CheckBox) focusWidget).setValue((initialValue == null) ? false : Boolean.parseBoolean(initialValue));
            ((CheckBox) focusWidget).addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    triggerFieldListeners(true);
                }
            });
            widget = focusWidget;
        } else if (metadataField.isListBox()) {
            dateOfBirthField = null;
            label = new Label(metadataField.getFieldLabel());
            focusWidget = new ListBox();
            int selectedIndex = 0;
            int itemCounter = 0;
            ((ListBox) focusWidget).addItem(""); // make sure there is an empty item at the top of the list
            for (String listItem : metadataField.getListValues()) {
                if (!listItem.isEmpty()) { // allow only one empty item in this list
                    ((ListBox) focusWidget).addItem(listItem);
                    itemCounter++;
                }
                if (initialValue != null && initialValue.equals(listItem)) {
                    selectedIndex = itemCounter;
                }
            }
            ((ListBox) focusWidget).setSelectedIndex(selectedIndex);
            ((ListBox) focusWidget).addChangeHandler(new ChangeHandler() {
                @Override
                public void onChange(ChangeEvent event) {
                    triggerFieldListeners(true);
                }
            });
            widget = focusWidget;
        } else {
            dateOfBirthField = null;
            label = new Label(metadataField.getFieldLabel());
            if (metadataField.isMultiLine()) {
                focusWidget = new TextArea();
            } else {
                focusWidget = new TextBox();
            }
            ((TextBoxBase) focusWidget).setText((initialValue == null) ? "" : initialValue);
            ((TextBoxBase) focusWidget).addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    triggerFieldListeners(true);
                }
            });
            widget = focusWidget;
        }
        widget.setStylePrimaryName("metadataOK");
        errorLabel = new Label();
        labelPanel = new VerticalPanel();
        labelPanel.add(label);
        labelPanel.add(errorLabel);
    }

    private void triggerFieldListeners(boolean allowSelectionChange) {
        if (!metadataFieldListeners.isEmpty()) {
            String value = getValue();
            Long daysSince = null;
            if (allowSelectionChange) {
                if (dateOfBirthField != null) {
                    daysSince = dateOfBirthField.getDaysSince();
                }
            }
            for (MetadataFieldListener metadataFieldListener : metadataFieldListeners) {
                metadataFieldListener.matadataFieldValueChanged(daysSince, value);
            }
        }
    }

    public void addMetadataFieldListener(MetadataFieldListener metadataFieldListener, boolean allowSelectionChange) {
        this.metadataFieldListeners.add(metadataFieldListener);
        // trigger the field listeners of existing fields at this point to get the initital state correct
        // For age dependant field where the user can change the selection, we do not trigger the initial state. For visibility dependant fields the field should always be shown or hidden according to the other fields value, so we do trigger the initial state.
        triggerFieldListeners(allowSelectionChange);
    }

    @Override
    public Stimulus getStimulus() {
        return stimulus;
    }

    @Override
    public void setFocus(boolean wantsFocus) {
        focusWidget.setFocus(wantsFocus);
    }

    public Widget getWidget() {
        return widget;
    }

    public void setVisible(boolean visible) {
        labelPanel.setVisible(visible);
        if (dateOfBirthField != null) {
            dateOfBirthField.setVisible(visible);
        } else if (focusWidget instanceof CheckBox) {
            ((CheckBox) focusWidget).setVisible(visible);
        } else if (focusWidget instanceof ListBox) {
            ((ListBox) focusWidget).setVisible(visible);
        } else if (focusWidget instanceof TextBoxBase) {
            ((TextBoxBase) focusWidget).setVisible(visible);
        } else {
            throw new UnsupportedOperationException("Unexpected type for: " + focusWidget.getClass());
        }
    }

    public void setEnabled(boolean enabled) {
        if (dateOfBirthField != null) {
            dateOfBirthField.setEnabled(enabled);
        } else if (focusWidget instanceof CheckBox) {
            ((CheckBox) focusWidget).setEnabled(enabled);
        } else if (focusWidget instanceof ListBox) {
            ((ListBox) focusWidget).setEnabled(enabled);
        } else if (focusWidget instanceof TextBoxBase) {
            ((TextBoxBase) focusWidget).setEnabled(enabled);
        } else {
            throw new UnsupportedOperationException("Unexpected type for: " + focusWidget.getClass());
        }
    }

    public IsWidget getLabel() {
        return labelPanel;
    }

    public void setValue(String fieldValue) {
        if (focusWidget instanceof CheckBox) {
            ((CheckBox) focusWidget).setValue(Boolean.valueOf(fieldValue));
        } else if (focusWidget instanceof ListBox) {
            for (int itemIndex = 0; itemIndex < ((ListBox) focusWidget).getItemCount(); itemIndex++) {
                if (((ListBox) focusWidget).getValue(itemIndex).equals(fieldValue)) {
                    ((ListBox) focusWidget).setSelectedIndex(itemIndex);
                }
            }
        } else if (focusWidget instanceof TextBoxBase) {
            ((TextBoxBase) focusWidget).setValue(fieldValue);
        }
    }

    @Override
    public String getValue() {
        if (dateOfBirthField != null) {
            return dateOfBirthField.getValue();
        } else if (focusWidget instanceof CheckBox) {
            return Boolean.toString(((CheckBox) focusWidget).getValue());
        } else if (focusWidget instanceof ListBox) {
            return ((ListBox) focusWidget).getSelectedValue();
        } else if (focusWidget instanceof TextBoxBase) {
            return ((TextBoxBase) focusWidget).getValue();
        } else {
            throw new UnsupportedOperationException("Unexpected type for: " + focusWidget.getClass());
        }
    }

    @Override
    public String getResponseTimes() {
        return null;
    }

    @Override
    public int getDataChannel() {
        return dataChannel;
    }

    @Override
    public boolean isValid() {
        try {
            metadataField.validateValue(getValue());
            focusWidget.setStylePrimaryName("metadataOK");
            label.setStylePrimaryName("metadataOK");
            errorLabel.setStylePrimaryName("metadataOK");
            errorLabel.setText("");
            return true;
        } catch (MetadataFieldException exception) {
            focusWidget.setStylePrimaryName("metadataError");
            label.setStylePrimaryName("metadataError");
            errorLabel.setStylePrimaryName("metadataErrorMessage");
            errorLabel.setText(metadataField.getControlledMessage());
            return false;
        }
    }

    @Override
    public String getPostName() {
        return metadataField.getPostName();
    }
}
