package com.straykovsky.custom_frame.components.datetimepicker.timepicker.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.datetimepicker.timepicker.TimePickerTextField;

import java.awt.*;

public class DialogPanel extends Panel {

    public TimePickerTextField timePickerTextField;

    public SliderPanel hoursSliderPanel;
    public SliderPanel minutesSliderPanel;

    public DialogPanel(TimePickerTextField timePickerTextField) {
        this.timePickerTextField = timePickerTextField;

        hoursSliderPanel = new SliderPanel(this, "H");
        minutesSliderPanel = new SliderPanel(this, "M");

        setLayout(new GridLayout(2, 0));
        add(hoursSliderPanel);
        add(minutesSliderPanel);

        setBorder(Settings.Borders.frameBorder1);
    }
}
