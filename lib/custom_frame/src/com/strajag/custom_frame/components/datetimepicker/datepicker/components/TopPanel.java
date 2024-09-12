package com.strajag.custom_frame.components.datetimepicker.datepicker.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends com.strajag.custom_frame.components.Panel implements ActionListener {

    public DialogPanel DialogPanel;

    public com.strajag.custom_frame.components.Panel leftPanel;
    public com.strajag.custom_frame.components.Panel rightPanel;

    public com.strajag.custom_frame.components.Button previousYearButton;
    public com.strajag.custom_frame.components.Button nextYearButton;
    public com.strajag.custom_frame.components.Button previousMonthButton;
    public com.strajag.custom_frame.components.Button nextMonthButton;
    public com.strajag.custom_frame.components.Button yearMonthButton;

    public TopPanel(DialogPanel DialogPanel) {
        this.DialogPanel = DialogPanel;

        previousYearButton = new com.strajag.custom_frame.components.Button("⇇");
        nextYearButton = new com.strajag.custom_frame.components.Button("⇉");
        previousMonthButton = new com.strajag.custom_frame.components.Button("←");
        nextMonthButton = new com.strajag.custom_frame.components.Button("→");
        yearMonthButton = new com.strajag.custom_frame.components.Button();

        leftPanel = new com.strajag.custom_frame.components.Panel(new GridLayout(0, 2)) { @Override public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.datePickerTopButtonsPanelWidth, getHeight()); }};
        leftPanel.add(previousYearButton);
        leftPanel.add(previousMonthButton);

        rightPanel = new com.strajag.custom_frame.components.Panel(new GridLayout(0, 2)) { @Override public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.datePickerTopButtonsPanelWidth, getHeight()); }};
        rightPanel.add(nextMonthButton);
        rightPanel.add(nextYearButton);

        add(leftPanel, BorderLayout.WEST);
        add(yearMonthButton, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        previousYearButton.addActionListener(this);
        nextYearButton.addActionListener(this);
        previousMonthButton.addActionListener(this);
        nextMonthButton.addActionListener(this);
        yearMonthButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        com.strajag.custom_frame.components.Button pressedButton = (Button) actionEvent.getSource();
        if (pressedButton.equals(previousYearButton)) {
            DialogPanel.datePickerTextField.date.setYear(DialogPanel.datePickerTextField.date.getYear() - 1);
        } else if (pressedButton.equals(nextYearButton)) {
            DialogPanel.datePickerTextField.date.setYear(DialogPanel.datePickerTextField.date.getYear() + 1);
        } else if (pressedButton.equals(previousMonthButton)) {
            DialogPanel.datePickerTextField.date.setMonth(DialogPanel.datePickerTextField.date.getMonth() - 1);
        } else if (pressedButton.equals(nextMonthButton)) {
            DialogPanel.datePickerTextField.date.setMonth(DialogPanel.datePickerTextField.date.getMonth() + 1);
        } else if (pressedButton.equals(yearMonthButton)) {
            DialogPanel.datePickerTextField.date.setTime(System.currentTimeMillis());
        }
        DialogPanel.datePickerTextField.update();
    }
}
