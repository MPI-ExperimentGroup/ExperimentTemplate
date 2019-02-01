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

import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.listener.MetadataFieldListener;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserLabelData;

/**
 * @since Oct 21, 2014 11:56:23 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataView extends TimedStimulusView {

    private FlexTable flexTable = null;
    final private HashMap<MetadataField, MetadataFieldWidget> fieldBoxes;
    final private HashMap<MetadataField, ListBox> fieldConnections;
    final private ArrayList<MetadataField> orderedFields;
    private MetadataFieldWidget firstTextBox = null;
    private final Label errorText;
    private final VerticalPanel keyboardPadding;

    public MetadataView() {
        fieldBoxes = new HashMap<>();
        fieldConnections = new HashMap<>();
        orderedFields = new ArrayList<>();
        errorText = new Label();
        keyboardPadding = new VerticalPanel();
        keyboardPadding.add(new Label(""));
        errorText.setStylePrimaryName("metadataErrorMessage");
    }

    public void addField(final MetadataField metadataField, final String existingValue, final String labelString, final List<UserLabelData> allUsersList, final List<UserId> selectedUsers, final boolean oneToMany) {
        MetadataFieldWidget stimulusMetadataField = addField(metadataField, existingValue, labelString);
        final int rowCount = flexTable.getRowCount();
        if (allUsersList != null) {
            ListBox listBox = new ListBox();
            listBox.setMultipleSelect(oneToMany);
            if (!oneToMany) {
                ((ListBox) listBox).addItem(""); // make sure there is an empty item at the top of the list
            }
            for (UserLabelData userLabelData : allUsersList) {
                listBox.addItem(userLabelData.getUserName(), userLabelData.getUserId().toString());
                if (selectedUsers.contains(userLabelData.getUserId())) {
                    final int index = listBox.getItemCount() - 1;
                    // allowing multiple selections here 
                    listBox.setItemSelected(index, true);
                }
            }
            HorizontalPanel fieldPanel = new HorizontalPanel();
            fieldPanel.add(stimulusMetadataField.getWidget());
            fieldPanel.add(listBox);
            flexTable.setWidget(rowCount - 1, 0, fieldPanel);
            fieldConnections.put(metadataField, listBox);
        }
    }

    public void addField(final MetadataField metadataField, final String existingValue, String labelString, final MetadataField metadataFieldOther, final int[] daysThresholds, final String visibleRegex, final String enabledRegex) {
        addField(metadataField, existingValue, labelString);
        MetadataFieldListener fieldListener = new MetadataFieldListener() {
            @Override
            public void matadataFieldValueChanged(Long daysSince, String value) {
                if (daysSince != null) {
                    final MetadataFieldWidget fieldBox = fieldBoxes.get(metadataField);
                    if (fieldBox != null) {
                        int currentIndex = 0;
                        for (int currentDays : daysThresholds) {
                            if (currentDays < daysSince) {
                                currentIndex++;
                            }
                        }
                        fieldBox.setValue(metadataField.getListValues()[currentIndex]);
                    }
                }
                if (value != null) {
                    final MetadataFieldWidget fieldBox = fieldBoxes.get(metadataField);
                    if (fieldBox != null) {
                        fieldBox.setVisible(value.matches(visibleRegex));
                        fieldBox.setEnabled(value.matches(enabledRegex));
                    }
                }
//                addText(existingValue);
//                if (daysSince != null) {
//                    addText(Long.toString(daysSince));
//                }
            }

            @Override
            public MetadataField getMetadataField() {
                return metadataField;
            }

            @Override
            public MetadataField getMetadataFieldOther() {
                return metadataFieldOther;
            }
        };
        // in this case if the other field has a value, then this field also has a value, so we do not trigger the initial state, but do trigger the initial hidden/visible states
        fieldBoxes.get(fieldListener.getMetadataFieldOther()).addMetadataFieldListener(fieldListener, false);
    }

    public void addField(final MetadataField metadataField, final String existingValue, String labelString, final MetadataField metadataFieldOther, final String visibleRegex, final String enabledRegex) {
        addField(metadataField, existingValue, labelString);
        MetadataFieldListener fieldListener = new MetadataFieldListener() {
            @Override
            public void matadataFieldValueChanged(Long daysSince, String value) {
                if (value != null) {
                    final MetadataFieldWidget fieldBox = fieldBoxes.get(metadataField);
                    if (fieldBox != null) {
                        fieldBox.setVisible(value.matches(visibleRegex));
                        fieldBox.setEnabled(value.matches(enabledRegex));
                    }
                }
                addText(existingValue);
                if (daysSince != null) {
                    addText(Long.toString(daysSince));
                }
            }

            @Override
            public MetadataField getMetadataField() {
                return metadataField;
            }

            @Override
            public MetadataField getMetadataFieldOther() {
                return metadataFieldOther;
            }
        };
        // in this case this field should always be shown or hidden according to the other fields value, so we must trigger the initial state
        fieldBoxes.get(fieldListener.getMetadataFieldOther()).addMetadataFieldListener(fieldListener, true);
    }

    public MetadataFieldWidget addField(final MetadataField metadataField, final String existingValue, String labelString) {
        if (flexTable == null) {
            flexTable = new FlexTable();
            flexTable.setStylePrimaryName("metadataTable");
            outerPanel.add(flexTable);
        }
        final int rowCount = flexTable.getRowCount();
        final MetadataFieldWidget stimulusMetadataField = new MetadataFieldWidget(metadataField, null, existingValue, 0);

        flexTable.setWidget(rowCount, 0, stimulusMetadataField.getLabel());
        flexTable.setWidget(rowCount + 1, 0, stimulusMetadataField.getWidget());

//        textBox.addBlurHandler(new BlurHandler() {
//
//            @Override
//            public void onBlur(BlurEvent event) {
//                removeKeyboardPadding();
//            }
//        });
//        stimulusMetadataField.getFocusWidget().addFocusHandler(new FocusHandler() {
//
//            @Override
//            public void onFocus(FocusEvent event) {
//                addKeyboardPadding();
//                Timer timer = new Timer() {
//                    @Override
//                    public void run() {
//                        scrollToPosition(stimulusMetadataField.getFocusWidget().getAbsoluteTop());
////                        stimulusMetadataField.getFocusWidget().getElement().scrollIntoView();
//                    }
//                };
//                timer.schedule(100);
//            }
//        });
        fieldBoxes.put(metadataField, stimulusMetadataField);
        orderedFields.add(metadataField);
        if (firstTextBox == null) {
            firstTextBox = stimulusMetadataField;
        }
        return stimulusMetadataField;
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
        fieldBoxes.get(metadataField).setValue(fieldValue);
    }

    public String getFieldValue(MetadataField metadataField) {
        return fieldBoxes.get(metadataField).getValue();
    }

    public List<UserId> getFieldConnection(MetadataField metadataField) throws UserIdException {
        List<UserId> selectedUserIds = new ArrayList<>();
        final ListBox listBox = fieldConnections.get(metadataField);
        if (listBox != null) {
            for (int index = 0; index < listBox.getItemCount(); index++) {
                if (listBox.isItemSelected(index)) {
                    selectedUserIds.add(new UserId(listBox.getValue(index)));
                }
            }
        }
        return selectedUserIds;
    }

    public void showFieldError(MetadataField metadataField) {
        final Widget focusWidget = fieldBoxes.get(metadataField).getWidget();
        focusWidget.setStylePrimaryName("metadataError");
        errorText.setText(metadataField.getControlledMessage());
        for (int rowCounter = 0; rowCounter < flexTable.getRowCount(); rowCounter++) {
            if (focusWidget.equals(flexTable.getWidget(rowCounter, 0))) {
                flexTable.insertRow(rowCounter);
                flexTable.setWidget(rowCounter, 0, errorText);
                break;
            }
        }
        fieldBoxes.get(metadataField).setFocus(true);
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
        for (MetadataFieldWidget stimulusMetadataFields : fieldBoxes.values()) {
            Widget focusWidget = stimulusMetadataFields.getWidget();
            focusWidget.setStylePrimaryName("metadataOK");
        }
        if (flexTable != null) {
            for (int rowCounter = 0; rowCounter < flexTable.getRowCount(); rowCounter++) {
                if (flexTable.getWidget(rowCounter, 0) == errorText) {
                    // remove the error message and the tabel row that was added for the error message
                    flexTable.removeRow(rowCounter);
                    break;
                }
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
