package com.straykovsky.logthedog.gui.ownerpanel.components;

import com.straykovsky.logthedog.application.Owner;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.OptionPane;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.logthedog.gui.ownerpanel.OwnerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends Panel implements ActionListener {

    public OwnerPanel ownerPanel;

    public Button saveButton;
    public Button removeButton;
    public Button cancelButton;

    public BottomPanel(OwnerPanel ownerPanel) {
        this.ownerPanel = ownerPanel;

        saveButton = new Button("SAVE");
        removeButton = new Button("REMOVE");
        cancelButton = new Button("CANCEL");

        setLayout(new GridBagLayout());

        saveButton.addActionListener(this);
        removeButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void setData() {
        GridBagConstraints gbc = new GridBagConstraints();
        removeAll();
        add(saveButton);
        if (ownerPanel.owner == null) {
            gbc.gridy = 1;
        } else {
            gbc.gridy = 1;
            add(removeButton, gbc);
            gbc.gridy = 2;
        }
        add(cancelButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Button pressedButton = (Button) actionEvent.getSource();
            if (pressedButton.equals(saveButton)) {
                if (ownerPanel.owner != null) {
                    int answer = OptionPane.showConfirmDialog(ownerPanel.gui.ownerPanelFrame, this, "EDIT", "DO YOU WANT TO EDIT THIS OWNER?");
                    if (answer == 0) { return; }
                    ownerPanel.owner.firstName = ownerPanel.topPanel.fieldsPanel.firstNameTextField.getText();
                    ownerPanel.owner.lastName = ownerPanel.topPanel.fieldsPanel.lastNameTextField.getText();
                    ownerPanel.owner.phoneNumber = ownerPanel.topPanel.fieldsPanel.phoneNumberTextField.getText();
                    ownerPanel.owner.address = ownerPanel.topPanel.fieldsPanel.addressTextField.getText();
                    ownerPanel.owner.email = ownerPanel.topPanel.fieldsPanel.emailTextField.getText();
                    ownerPanel.owner.notes = ownerPanel.centerPanel.notesTextArea.getText().replace("\n", "\\n");
                    ownerPanel.owner.image = ownerPanel.topPanel.imagePanel.getImage();
                    ownerPanel.gui.dataManager.editOwner(ownerPanel.owner);
                } else {
                    Owner owner = new Owner();
                    owner.firstName = ownerPanel.topPanel.fieldsPanel.firstNameTextField.getText();
                    owner.lastName = ownerPanel.topPanel.fieldsPanel.lastNameTextField.getText();
                    owner.phoneNumber = ownerPanel.topPanel.fieldsPanel.phoneNumberTextField.getText();
                    owner.address = ownerPanel.topPanel.fieldsPanel.addressTextField.getText();
                    owner.email = ownerPanel.topPanel.fieldsPanel.emailTextField.getText();
                    owner.notes = ownerPanel.centerPanel.notesTextArea.getText().replace("\n", "\\n");
                    owner.image = ownerPanel.topPanel.imagePanel.getImage();
                    ownerPanel.gui.dataManager.addOwner(owner);
                    ownerPanel.gui.ownerPanel.setData(ownerPanel.gui.dataManager.ownerList.get(ownerPanel.gui.dataManager.ownerList.size() - 1));
                }
                if (ownerPanel.gui.dataPanel.isOwner) {
                    ownerPanel.gui.dataPanel.centerPanel.ownersTable.refresh();
                } else {
                    ownerPanel.gui.dataPanel.centerPanel.dogsTable.refresh();
                }
            } else if (pressedButton.equals(removeButton)) {
                int answer = OptionPane.showConfirmDialog(ownerPanel.gui.ownerPanelFrame, this, "REMOVE", "DO YOU WANT TO REMOVE THIS OWNER?");
                if (answer == 0) { return; }
                ownerPanel.gui.ownerPanel.setData(null);
                ownerPanel.gui.dataManager.removeOwner(ownerPanel.owner);
                if (ownerPanel.gui.dataPanel.isOwner) {
                    ownerPanel.gui.dataPanel.centerPanel.ownersTable.refresh();
                } else {
                    ownerPanel.gui.dataPanel.centerPanel.dogsTable.refresh();
                }
            } else if (pressedButton.equals(cancelButton)) {
                ownerPanel.gui.ownerPanelFrame.setVisible(false);
            }
        } catch (Exception exception) { ownerPanel.gui.ownerPanelFrame.throwException(exception); }
    }
}
