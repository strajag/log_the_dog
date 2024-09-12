package com.straykovsky.custom_frame.components.datetimepicker.datepicker.components;

import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BottomPanel extends Panel implements ActionListener {

    public DialogPanel dialogPanel;

    public Button dateNowButton;

    public LocalDate localDate;

    public BottomPanel(DialogPanel dialogPanel) {
        this.dialogPanel = dialogPanel;

        localDate = new Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateNowButton = new Button(localDate.getDayOfMonth() + "." + localDate.getMonthValue() + "." + localDate.getYear());

        setLayout(new GridBagLayout());
        add(dateNowButton);

        dateNowButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialogPanel.datePickerTextField.setTime(System.currentTimeMillis());
        dialogPanel.datePickerTextField.dialog.dispose();
    }
}
