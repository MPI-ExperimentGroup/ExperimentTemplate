/*
 * Copyright (C) 2016 Max Planck Institute for Psycholinguistics, Nijmegen
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
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import java.util.Date;

/**
 * @since Aug 23, 2016 5:02:34 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class DateOfBirthField extends HorizontalPanel {

    final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private final IntegerBox dayBox = new IntegerBox() { // todo: make this numerical only

        @Override
        public void setStylePrimaryName(String style) {
            super.setStylePrimaryName(style);
            monthSelect.setStylePrimaryName(style);
            yearBox.setStylePrimaryName(style);
        }

        @Override
        public void setFocus(boolean focused) {
            if (yearBox.getValue().length() != 4) {
                yearBox.setFocus(focused);
                return;
            }
            final int monthInteger = Integer.parseInt(monthSelect.getSelectedValue());
            if (monthInteger < 1 || monthInteger > 12) {
                monthSelect.setFocus(focused);
                return;
            }
            super.setFocus(focused);
        }
    };
    private final ListBox monthSelect = new ListBox();
    private final TextBox yearBox = new TextBox();

    public DateOfBirthField() {
        int index = 0;
        monthSelect.addItem("", Integer.toString(index));
        for (String monthString : LocaleInfo.getCurrentLocale().getDateTimeFormatInfo().monthsFull()) {
            index++;
            monthSelect.addItem(monthString, Integer.toString(index));
        }
    }

    @Override
    public void setStylePrimaryName(String style) {
        dayBox.setStylePrimaryName(style);
        monthSelect.setStylePrimaryName(style);
        yearBox.setStylePrimaryName(style);
    }

    public IntegerBox getTextBox() {
        dayBox.setWidth("50px");
        yearBox.setWidth("100px");
        dayBox.setMaxLength(2);
        yearBox.setMaxLength(4);
        this.add(dayBox);
        this.add(monthSelect);
        this.add(yearBox);
        dayBox.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
                valueChanged();
            }
        });
        dayBox.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final char charCode = event.getCharCode();
                if (0 > "0123456789".indexOf(charCode)) {
                    event.getNativeEvent().preventDefault();
                }
            }
        });
        monthSelect.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                valueChanged();
            }
        });
        yearBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                valueChanged();
            }
        });
        yearBox.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final char charCode = event.getCharCode();
                if (0 > "0123456789".indexOf(charCode)) {
                    event.getNativeEvent().preventDefault();
                }
            }
        });
        return dayBox;
    }

    public void setDate(String dateString) {
        if (dateString != null) {
            String[] splitString = dateString.split("/");
            if (splitString.length == 3) {
                monthSelect.setSelectedIndex(Integer.parseInt(splitString[1]));
                dayBox.setValue(Integer.parseInt(splitString[0]));
                yearBox.setValue(splitString[2]);
            }
        }
    }

    public void setEnabled(boolean enabled) {
        monthSelect.setEnabled(enabled);
        dayBox.setEnabled(enabled);
        yearBox.setEnabled(enabled);
    }

    public String getValue() {
        try {
            // format the date without localisation and without timezones
            final Integer dayInteger = dayBox.getValue();
            final int monthInteger = Integer.parseInt(monthSelect.getSelectedValue());
            if (dayInteger == null) {
                return "";
            }
            if (dayInteger < 1 || dayInteger > 31 || monthInteger < 1 || monthInteger > 12) {
                return "";
            }
            final String formattedDate
                    = ((dayInteger < 10) ? "0" : "") + dayInteger
                    + "/"
                    + ((monthInteger < 10) ? "0" : "") + monthInteger
                    + "/"
                    + yearBox.getValue(); // do not pad the year so that the date validator can check it
//                DateOfBirthField.this.add(new Label(formattedDate));
            return formattedDate;
        } catch (IllegalArgumentException exception) {
//                DateOfBirthField.this.add(new Label(exception.getMessage()));
            return "";
        }
    }

    @SuppressWarnings("deprecation")
    public long getDaysSince() {
        // we cannot use java.text.DateFormat, java.text.SimpleDateFormat, java.util.Calendar in GWT hence the deprecated usages here
        final Date parseStrict = dateFormat.parseStrict(getValue());
        final Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        long diffMs = currentDate.getTime() - parseStrict.getTime();
        return (diffMs / (1000 * 60 * 60 * 24));
    }

    abstract void valueChanged();
}
