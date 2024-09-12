package com.straykovsky.custom_frame.components.datetimepicker.datepicker.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;

public class CenterPanel extends Panel implements ActionListener {

    public DialogPanel dialogPanel;

    public Button[] buttons;

    public String[] dayNames;

    public YearMonth yearMonthObject;
    public int startDay;

    public CenterPanel(DialogPanel dialogPanel) {
        this.dialogPanel = dialogPanel;

        buttons = new Button[49];
        dayNames = new String[]{"M", "T", "W", "T", "F", "S", "S"};

        setLayout(new GridLayout(7, 7));

        for (int i = 0; i < 49; i++) {
            buttons[i] = new Button();
            if (i < 7) { buttons[i].setText(dayNames[i]); }
            buttons[i].removeMouseListener(buttons[i]);
            add(buttons[i]);
        }

        setBorder(Settings.Borders.getBevelTitleBorder(""));
    }

    public void update() {
        yearMonthObject = YearMonth.of(dialogPanel.datePickerTextField.localDate.getYear(), dialogPanel.datePickerTextField.localDate.getMonthValue());
        startDay = yearMonthObject.atDay(1).getDayOfWeek().getValue();
        for (int i = 7; i < buttons.length; i++) {
            if (i - 7 < yearMonthObject.lengthOfMonth() + startDay - 1 && i - 7 >= startDay - 1) {
                dialogPanel.centerPanel.buttons[i].setText((i - 6 - startDay + 1) + "");
                buttons[i].addMouseListener(buttons[i]);
                buttons[i].addActionListener(this);
            } else {
                dialogPanel.centerPanel.buttons[i].setText("");
                buttons[i].removeMouseListener(buttons[i]);
                buttons[i].removeActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Button pressedButton = (Button) actionEvent.getSource();
        dialogPanel.datePickerTextField.date.setDate(Integer.parseInt(pressedButton.getText()));
        dialogPanel.datePickerTextField.update();
        dialogPanel.datePickerTextField.dialog.dispose();
    }
}
