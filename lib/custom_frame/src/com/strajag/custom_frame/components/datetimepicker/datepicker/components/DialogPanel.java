package com.strajag.custom_frame.components.datetimepicker.datepicker.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.datetimepicker.datepicker.DatePickerTextField;

import java.awt.*;

public class DialogPanel extends Panel
{

    public DatePickerTextField datePickerTextField;

    public TopPanel topPanel;
    public CenterPanel centerPanel;
    public BottomPanel bottomPanel;

    public DialogPanel(DatePickerTextField datePickerTextField) {
        this.datePickerTextField = datePickerTextField;

        topPanel = new TopPanel(this);
        centerPanel = new CenterPanel(this);
        bottomPanel = new BottomPanel(this);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setBorder(Settings.Borders.frameBorder1);
    }
}
