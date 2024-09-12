package com.straykovsky.logthedog.gui.ownerpanel.components;

import com.straykovsky.logthedog.application.Dog;
import com.straykovsky.logthedog.Settings;
import com.straykovsky.custom_frame.components.*;
import com.straykovsky.logthedog.gui.DogsTableModel;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.ScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TablePanel extends Panel implements ActionListener, MouseListener {

    public CenterPanel centerPanel;

    public DogsTableModel tableModel;
    public Table table;
    public ScrollPane tableScrollPane;

    public Panel buttonTablePanel;

    public Button addButton;
    public Button removeButton;

    public TablePanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;

        tableModel = new DogsTableModel(Settings.Text.ownerDogColumnNames);
        table = new Table(tableModel);
        tableScrollPane = new ScrollPane(table);
        addButton = new Button("ADD");
        removeButton = new Button("REMOVE");

        buttonTablePanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;
        buttonTablePanel.add(addButton, gbc);
        gbc.gridx = 1;
        buttonTablePanel.add(removeButton, gbc);

        add(tableScrollPane);
        add(buttonTablePanel, BorderLayout.SOUTH);

        setBorder(Settings.Borders.getBevelTitleBorder("DOGS"));
        table.addMouseListener(this);
        tableScrollPane.addMouseListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        for (int i = 0; i < table.getColumnCount(); i++) { table.getColumnModel().getColumn(i).setPreferredWidth(Settings.Dimensions.ownerDogColumnWidths[i]); }
    }

    public void setData() {
        tableModel.setData(centerPanel.ownerPanel.owner.dogList, Settings.Text.ownerDogColumnNames);
        table.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Button pressedButton = (Button) actionEvent.getSource();
            if (pressedButton.equals(addButton)) {
                centerPanel.ownerPanel.gui.dogPanel.setData(null, centerPanel.ownerPanel.owner);
            } else if (pressedButton.equals(removeButton)) {
                Object[] selectedObjects = table.getSelectedItems(0);

                if (selectedObjects.length == 0) { OptionPane.showMessageDialog(centerPanel.ownerPanel.gui.ownerPanelFrame, this, "REMOVE", "NOTHING SELECTED"); return; }
                int answer = OptionPane.showConfirmDialog(centerPanel.ownerPanel.gui.ownerPanelFrame, this, "REMOVE", "DO YOU WANT TO REMOVE SELECTED DOGS?");
                if (answer == 0) { return; }

                for (int i = 0; i < selectedObjects.length; i++) { centerPanel.ownerPanel.gui.dataManager.removeDog((Dog) selectedObjects[i]); }
                table.refresh();
            }
        } catch (Exception exception) { centerPanel.ownerPanel.gui.dataPanelFrame.throwException(exception); }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            if (mouseEvent.getClickCount() == 2) {
                if (table.getSelectedRows().length == 1) {
                    centerPanel.ownerPanel.gui.dogPanel.setData((Dog) table.getSelectedItem(0), centerPanel.ownerPanel.owner);
                }
            }
        } else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            table.clearSelection();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
