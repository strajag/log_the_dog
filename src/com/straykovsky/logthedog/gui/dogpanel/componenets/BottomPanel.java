package com.straykovsky.logthedog.gui.dogpanel.componenets;

import com.straykovsky.logthedog.application.Dog;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.OptionPane;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.logthedog.gui.dogpanel.DogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends Panel implements ActionListener {

    public DogPanel dogPanel;

    public Button saveButton;
    public Button removeButton;
    public Button closeButton;

    public BottomPanel(DogPanel dogPanel) {
        this.dogPanel = dogPanel;

        saveButton = new Button("SAVE");
        removeButton = new Button("REMOVE");
        closeButton = new Button("CLOSE");

        setLayout(new GridBagLayout());

        saveButton.addActionListener(this);
        removeButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    public void setData() {
        GridBagConstraints gbc = new GridBagConstraints();
        removeAll();
        add(saveButton);
        if (dogPanel.dog == null) {
            gbc.gridy = 1;
        } else {
            gbc.gridy = 1;
            add(removeButton, gbc);
            gbc.gridy = 2;
        }
        add(closeButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Button pressedButton = (Button) actionEvent.getSource();
            if (pressedButton.equals(saveButton)) {
                if (dogPanel.dog != null) {
                    int answer = OptionPane.showConfirmDialog(dogPanel.gui.dogPanelFrame, this, "EDIT", "DO YOU WANT TO EDIT THIS DOG?");
                    if (answer == 0) { return; }
                    dogPanel.dog.name = dogPanel.topPanel.fieldsPanel.nameTextField.getText();
                    dogPanel.dog.trainingReason = dogPanel.topPanel.fieldsPanel.trainingReasonTextField.getText();
                    dogPanel.dog.notes = dogPanel.centerPanel.notesTextArea.getText().replace("\n", "\\n");
                    dogPanel.dog.trainingPrice = Integer.parseInt(dogPanel.topPanel.fieldsPanel.trainingPriceTextField.getText());
                    dogPanel.dog.timeArrived = dogPanel.topPanel.fieldsPanel.timeArrivedDateTimePicker.getTime();
                    dogPanel.dog.timeTrainingEnds = dogPanel.topPanel.fieldsPanel.timeTrainingEndsDateTimePicker.getTime();
                    dogPanel.dog.image = dogPanel.topPanel.imagePanel.getImage();
                    dogPanel.gui.dataManager.editDog(dogPanel.dog);
                } else {
                    Dog dog = new Dog();
                    dog.owner = dogPanel.topPanel.fieldsPanel.ownerComboBox.getItemAt(dogPanel.topPanel.fieldsPanel.ownerComboBox.getSelectedIndex());
                    dog.name = dogPanel.topPanel.fieldsPanel.nameTextField.getText();
                    dog.trainingReason = dogPanel.topPanel.fieldsPanel.trainingReasonTextField.getText();
                    dog.notes = dogPanel.centerPanel.notesTextArea.getText().replace("\n", "\\n");
                    dog.trainingPrice = Integer.parseInt(dogPanel.topPanel.fieldsPanel.trainingPriceTextField.getText());
                    dog.timeArrived = dogPanel.topPanel.fieldsPanel.timeArrivedDateTimePicker.getTime();
                    dog.timeTrainingEnds = dogPanel.topPanel.fieldsPanel.timeTrainingEndsDateTimePicker.getTime();
                    dog.image = dogPanel.topPanel.imagePanel.getImage();
                    dogPanel.gui.dataManager.addDog(dog);
                    dogPanel.gui.dogPanel.setData(dogPanel.gui.dataManager.dogList.get(dogPanel.gui.dataManager.dogList.size() - 1), null);
                }
                if (dogPanel.gui.dataPanel.isOwner) {
                    dogPanel.gui.dataPanel.centerPanel.ownersTable.refresh();
                } else {
                    dogPanel.gui.dataPanel.centerPanel.dogsTable.refresh();
                }
            } else if (pressedButton.equals(removeButton)) {
                int answer = OptionPane.showConfirmDialog(dogPanel.gui.dogPanelFrame, this, "REMOVE", "DO YOU WANT TO REMOVE THIS DOG?");
                if (answer == 0) { return; }
                dogPanel.gui.dogPanel.setData(null, null);
                dogPanel.gui.dataManager.removeDog(dogPanel.dog);
                if (dogPanel.gui.dataPanel.isOwner) {
                    dogPanel.gui.dataPanel.centerPanel.ownersTable.refresh();
                } else {
                    dogPanel.gui.dataPanel.centerPanel.dogsTable.refresh();
                }
            } else if (pressedButton.equals(closeButton)) {
                dogPanel.gui.dogPanelFrame.setVisible(false);
            }
        } catch (Exception exception) { dogPanel.gui.dogPanelFrame.throwException(exception); }
    }
}
