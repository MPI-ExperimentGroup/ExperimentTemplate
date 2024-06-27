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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
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

    final private HashMap<MetadataField, MetadataFieldWidget> fieldBoxes;
    final private HashMap<MetadataField, ListBox> fieldConnections;
    final private ArrayList<MetadataField> orderedFields;
    private MetadataFieldWidget firstTextBox = null;
    private final VerticalPanel keyboardPadding;

    public MetadataView() {
        fieldBoxes = new HashMap<>();
        fieldConnections = new HashMap<>();
        orderedFields = new ArrayList<>();
        keyboardPadding = new VerticalPanel();
        keyboardPadding.add(new Label(""));
    }

    public void addField(final MetadataField metadataField, final String existingValue, final String labelString, final List<UserLabelData> allUsersList, final List<UserId> selectedUsers, final boolean oneToMany) {
        MetadataFieldWidget stimulusMetadataField = addField(metadataField, existingValue, labelString);
        if (allUsersList != null) {
            ListBox listBox = new ListBox();
            listBox.setStylePrimaryName("metadataOK");
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
            getActivePanel().add(fieldPanel);
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
        // in this case this field should always be shown or hidden according to the other fields value, so we must trigger the initial state
        fieldBoxes.get(fieldListener.getMetadataFieldOther()).addMetadataFieldListener(fieldListener, true);
    }

    public MetadataFieldWidget addField(final MetadataField metadataField, final String existingValue, String labelString) {
        final MetadataFieldWidget stimulusMetadataField = new MetadataFieldWidget(metadataField, null, existingValue, 0);
        // TODO: do we want to provide vertical or horizontal options for the label and widget
        getActivePanel().add(stimulusMetadataField.getLabel());
        getActivePanel().add(stimulusMetadataField.getWidget());
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

    public void setButtonError(final boolean isError, final ButtonBase button, final HTML errorHtmlText, final String errorMessage) {
        if (isError) {
            if (errorMessage != null) {
                errorHtmlText.setText(errorMessage);
                errorHtmlText.setStylePrimaryName("metadataErrorMessage");
                errorHtmlText.setVisible(true);
            } else {
                errorHtmlText.setVisible(false);
            }
            button.addStyleName("metadataError");
        } else {
            clearErrors();
            errorHtmlText.setVisible(false);
            button.removeStyleName("metadataError");
        }
    }

    public boolean validateFields() {
        boolean isValid = true;
        for (MetadataFieldWidget metadataField : fieldBoxes.values()) {
            if (metadataField.isEnabled() && !metadataField.isValid()) {
                isValid = false;
            }
        }
        return isValid;
    }

    public void clearErrors() {
        for (MetadataFieldWidget stimulusMetadataFields : fieldBoxes.values()) {
            Widget focusWidget = stimulusMetadataFields.getWidget();
            focusWidget.setStylePrimaryName("metadataOK");
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
