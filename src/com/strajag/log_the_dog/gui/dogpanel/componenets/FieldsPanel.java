package com.strajag.log_the_dog.gui.dogpanel.componenets;

import com.strajag.log_the_dog.Settings;
import com.strajag.log_the_dog.application.Owner;
import com.strajag.custom_frame.components.ComboBox;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.TextField;
import com.strajag.custom_frame.components.datetimepicker.DateTimePickerPanel;

import javax.swing.*;
import java.awt.*;

class FieldsPanel extends Panel {

    public TopPanel topPanel;

    public DefaultComboBoxModel<Owner> ownerComboBoxModel;
    public ComboBox<Owner> ownerComboBox;
    public TextField nameTextField;
    public TextField trainingReasonTextField;
    public TextField trainingPriceTextField;
    public DateTimePickerPanel timeArrivedDateTimePicker;
    public DateTimePickerPanel timeTrainingEndsDateTimePicker;

    public FieldsPanel(TopPanel topPanel) {
        this.topPanel = topPanel;

        ownerComboBoxModel = new DefaultComboBoxModel<>();
        ownerComboBox = new ComboBox<>(ownerComboBoxModel);
        nameTextField = new TextField();
        trainingReasonTextField = new TextField();
        trainingPriceTextField = new TextField();
        timeArrivedDateTimePicker = new DateTimePickerPanel(topPanel.dogPanel.gui.dogPanelFrame);
        timeTrainingEndsDateTimePicker = new DateTimePickerPanel(topPanel.dogPanel.gui.dogPanelFrame);

        setLayout(new GridLayout(6, 0, 0, 0));
        add(ownerComboBox);
        add(nameTextField);
        add(trainingReasonTextField);
        add(trainingPriceTextField);
        add(timeArrivedDateTimePicker);
        add(timeTrainingEndsDateTimePicker);

        ownerComboBox.setBorder(Settings.Borders.getBevelTitleBorder("OWNER"));
        nameTextField.setBorder(Settings.Borders.getBevelTitleBorder("NAME"));
        trainingReasonTextField.setBorder(Settings.Borders.getBevelTitleBorder("TRAINING REASON"));
        trainingPriceTextField.setBorder(Settings.Borders.getBevelTitleBorder("PRICE"));
        timeArrivedDateTimePicker.setBorder(Settings.Borders.getBevelTitleBorder("ARRIVED"));
        timeTrainingEndsDateTimePicker.setBorder(Settings.Borders.getBevelTitleBorder("END"));
    }

    public void setData() {
        ownerComboBoxModel.removeAllElements();
        if (topPanel.dogPanel.dog == null) {
            ownerComboBoxModel.addAll(topPanel.dogPanel.gui.dataManager.ownerList);
            if (topPanel.dogPanel.owner != null) { ownerComboBox.setSelectedItem(topPanel.dogPanel.owner); } else { ownerComboBox.setSelectedIndex(-1); }

            nameTextField.setText("");
            trainingReasonTextField.setText("");
            trainingPriceTextField.setText("");

            timeArrivedDateTimePicker.setTime(System.currentTimeMillis());
            timeTrainingEndsDateTimePicker.setTime(System.currentTimeMillis());
        } else {
            ownerComboBoxModel.addElement(topPanel.dogPanel.dog.owner);
            ownerComboBox.setSelectedItem(topPanel.dogPanel.dog.owner);

            nameTextField.setText(topPanel.dogPanel.dog.name);
            trainingReasonTextField.setText(topPanel.dogPanel.dog.trainingReason);
            trainingPriceTextField.setText(topPanel.dogPanel.dog.trainingPrice + "");

            timeArrivedDateTimePicker.setTime(topPanel.dogPanel.dog.timeArrived);
            timeTrainingEndsDateTimePicker.setTime(topPanel.dogPanel.dog.timeTrainingEnds);
        }
    }
}
