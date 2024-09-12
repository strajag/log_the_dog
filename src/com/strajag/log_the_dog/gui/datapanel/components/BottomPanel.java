package com.strajag.log_the_dog.gui.datapanel.components;

import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.OptionPane;
import com.strajag.custom_frame.components.Panel;
import com.strajag.log_the_dog.gui.datapanel.DataPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends Panel implements ActionListener {

    public DataPanel dataPanel;

    public Button addButton;
    public Button removeButton;

    public BottomPanel(DataPanel dataPanel) {
        this.dataPanel = dataPanel;

        addButton = new Button("ADD");
        removeButton = new Button("REMOVE");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(addButton);
        gbc.gridy = 1;
        add(removeButton, gbc);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            JButton pressedButton = (JButton) actionEvent.getSource();
            if (pressedButton.equals(addButton)) {
                if (dataPanel.isOwner) { dataPanel.gui.ownerPanel.setData(null); } else { dataPanel.gui.dogPanel.setData(null, null); }
            } else if (pressedButton.equals(removeButton)) {
                Object[] selectedObjects = dataPanel.centerPanel.getCurrentTable().getSelectedItems(0);
                if (selectedObjects.length == 0) { OptionPane.showMessageDialog(dataPanel.gui.dataPanelFrame, this, "REMOVE", "NOTHING SELECTED"); return; }
                int answer = OptionPane.showConfirmDialog(dataPanel.gui.dataPanelFrame, this, "REMOVE", "DO YOU WANT TO REMOVE SELECTED ITEMS?");
                if (answer == 0) { return; }

                for (Object object : selectedObjects) { dataPanel.gui.dataManager.remove(object); }
                dataPanel.centerPanel.getCurrentTable().refresh();
            }
        } catch (Exception exception) {
            if (dataPanel.isOwner) {
                dataPanel.gui.ownerPanelFrame.throwException(exception);
            } else {
                dataPanel.gui.dogPanelFrame.throwException(exception);
            }
        }
    }
}
