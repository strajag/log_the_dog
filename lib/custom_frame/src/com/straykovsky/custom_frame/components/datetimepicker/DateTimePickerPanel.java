package com.straykovsky.custom_frame.components.datetimepicker;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.datetimepicker.datepicker.DatePickerTextField;
import com.straykovsky.custom_frame.components.datetimepicker.timepicker.TimePickerTextField;
import com.straykovsky.custom_frame.components.frame.Frame;

import java.awt.*;
import java.util.Date;

public class DateTimePickerPanel extends Panel {

    public DatePickerTextField datePickerTextField;
    public TimePickerTextField timePickerTextField;

    public DateTimePickerPanel(Frame frame) {
        datePickerTextField = new DatePickerTextField(frame);
        timePickerTextField = new TimePickerTextField(frame);

        setLayout(new GridLayout(0, 2));
        add(datePickerTextField);
        add(timePickerTextField);

        datePickerTextField.setBorder(Settings.Borders.getBevelTitleBorder(""));
        timePickerTextField.setBorder(Settings.Borders.getBevelTitleBorder(""));
    }

    public long getTime() {
        Date date = new Date();
        date.setYear(datePickerTextField.date.getYear());
        date.setMonth(datePickerTextField.date.getMonth());
        date.setDate(datePickerTextField.date.getDate());
        date.setHours(timePickerTextField.date.getHours());
        date.setMinutes(timePickerTextField.date.getMinutes());
        date.setSeconds(timePickerTextField.date.getSeconds());
        return date.getTime();
    }

    public void setTime(long time) { datePickerTextField.setTime(time); timePickerTextField.setTime(time); }
}
