package com.straykovsky.logthedog.gui.ownerpanel.components;

import com.straykovsky.logthedog.Settings;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.TextField;

import java.awt.*;

public class FieldsPanel extends Panel {

    public TopPanel topPanel;

    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField phoneNumberTextField;
    public TextField addressTextField;
    public TextField emailTextField;

    public FieldsPanel(TopPanel topPanel) {
        this.topPanel = topPanel;

        firstNameTextField = new TextField();
        lastNameTextField = new TextField();
        phoneNumberTextField = new TextField();
        addressTextField = new TextField();
        emailTextField = new TextField();

        setLayout(new GridLayout(5, 0));
        add(firstNameTextField);
        add(lastNameTextField);
        add(phoneNumberTextField);
        add(addressTextField);
        add(emailTextField);

        firstNameTextField.setBorder(Settings.Borders.getBevelTitleBorder("FIRST NAME"));
        lastNameTextField.setBorder(Settings.Borders.getBevelTitleBorder("LAST NAME"));
        phoneNumberTextField.setBorder(Settings.Borders.getBevelTitleBorder("PHONE"));
        addressTextField.setBorder(Settings.Borders.getBevelTitleBorder("ADDRESS"));
        emailTextField.setBorder(Settings.Borders.getBevelTitleBorder("EMAIL"));
    }

    public void setData() {
        if (topPanel.ownerPanel.owner == null) {
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            phoneNumberTextField.setText("");
            addressTextField.setText("");
            emailTextField.setText("");
        } else {
            firstNameTextField.setText(topPanel.ownerPanel.owner.firstName);
            lastNameTextField.setText(topPanel.ownerPanel.owner.lastName);
            phoneNumberTextField.setText(topPanel.ownerPanel.owner.phoneNumber);
            addressTextField.setText(topPanel.ownerPanel.owner.address);
            emailTextField.setText(topPanel.ownerPanel.owner.email);
        }
    }
}
