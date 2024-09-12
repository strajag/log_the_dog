package com.straykovsky.custom_frame.components.datetimepicker.datepicker.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends Panel implements ActionListener {

    public DialogPanel DialogPanel;

    public Panel leftPanel;
    public Panel rightPanel;

    public Button previousYearButton;
    public Button nextYearButton;
    public Button previousMonthButton;
    public Button nextMonthButton;
    public Button yearMonthButton;

    public TopPanel(DialogPanel DialogPanel) {
        this.DialogPanel = DialogPanel;

        previousYearButton = new Button("⇇");
        nextYearButton = new Button("⇉");
        previousMonthButton = new Button("←");
        nextMonthButton = new Button("→");
        yearMonthButton = new Button();

        leftPanel = new Panel(new GridLayout(0, 2)) { @Override public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.datePickerTopButtonsPanelWidth, getHeight()); }};
        leftPanel.add(previousYearButton);
        leftPanel.add(previousMonthButton);

        rightPanel = new Panel(new GridLayout(0, 2)) { @Override public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.datePickerTopButtonsPanelWidth, getHeight()); }};
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
        Button pressedButton = (Button) actionEvent.getSource();
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
